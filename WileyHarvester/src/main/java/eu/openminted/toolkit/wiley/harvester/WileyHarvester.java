package eu.openminted.toolkit.wiley.harvester;

import com.google.gson.Gson;
import eu.openminted.toolkit.crossref.CrossRefClient;
import eu.openminted.toolkit.crossref.Utility;
import eu.openminted.toolkit.crossref.model.multiple_works.Item;
import eu.openminted.toolkit.database.exceptions.DatabaseException;
import eu.openminted.toolkit.database.services.DoiDiscoveryLogServiceDAO;
import eu.openminted.toolkit.queue.ScheduledArticle;
import eu.openminted.toolkit.queue.services.QueueService;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class WileyHarvester implements CommandLineRunner {

    @Autowired
    CrossRefClient crossRefClient;

    @Autowired
    QueueService queueService;

    @Autowired
    DoiDiscoveryLogServiceDAO doiDiscoveryLogServiceDAO;

    private Gson gson = new Gson();

    private static final Logger logger = Logger.getLogger("WileyHarvester");

    private final String queueName = "Wiley-download-queue";

    private final String publisher = "Wiley";

    @Value("${cursor:*}")
    private String cursorInput;

    @Override
    public void run(String... strings) throws Exception {

        queueService.declareDedicatedQueue(this.queueName);
        this.scheduleMonthsPublications();
    }

    //
    // TODO make single-prefix able 
    private void scheduleMonthsPublications() throws DatabaseException, IOException {

        WileyWorks works = new WileyWorks();

        String cursor = URLDecoder.decode(this.cursorInput, "UTF-8");
        AtomicInteger failCount = new AtomicInteger(0);
        JsonArray items = null;
        do {
            try {
                final long startTime = System.currentTimeMillis();
                JsonObject response = works.getWorks(cursor);
                final long endTime = System.currentTimeMillis();
                final String localCursor = response.getString("next-cursor");
                items = response.getJsonArray("items");
                items.forEach(item -> scheduleItem(this.publisher, item));                
                try {
                    Long delay = Math.round((endTime - startTime) * 0.75);
                    if (delay < 1000) {
                        delay = 1000L;
                    }
                    logger.info(String.format("Sleeping for ms " + delay));
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WileyHarvester.class.getName()).log(Level.SEVERE, null, ex);
                }

                cursor = localCursor;
            } catch (Exception ex) {
                Logger.getLogger(WileyHarvester.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                failCount.addAndGet(1);
                if (failCount.get() > 4) {
                    throw ex;
                }                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(WileyHarvester.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } while (cursor != null && !cursor.isEmpty() && (null != items && !items.isEmpty()));

    }

    private List<String> getMonths() {
        return Utility.getMonthsAsStringFromYearToYear("2010", "2017");
    }

    private List<String> getDays() {
        return Utility.getDaysAsStringFromDateToDate("2010-10-01", "2017-04-12");
    }

    private void scheduleItem(String publisherPrefix, JsonValue val) {

        Item item = gson.fromJson(val.toString(), Item.class);

        logger.info(String.format("Scheduling (pushing to queue) item : %s", item.getDOI()));
        Gson gson = new Gson();
        String metadata = gson.toJson(item);

        ScheduledArticle article = new ScheduledArticle();
        article.setDoi(item.getdOI());
        article.setDownloadUrl(null);
        article.setPublisherPrefix(publisher);
        article.setMetadata(metadata);
        queueService.scheduleArticleToDedicatedQueue(this.queueName, article);

    }
}
