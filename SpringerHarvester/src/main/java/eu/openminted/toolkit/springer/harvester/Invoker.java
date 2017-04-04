package eu.openminted.toolkit.springer.harvester;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucasanastasiou
 */
@Component
public class Invoker implements CommandLineRunner {

    @Autowired
    SpringerHarvester springerHarvester;

    private static final Logger logger = Logger.getLogger("Invoker");

    @Override
    public void run(String... strings) throws Exception {
//        String prefix = "10.1891";
//        String prefix = "10.7603";

        String[] months = {
//            "2000-01",
//            "2000-02",
//            "2000-03",
//            "2000-04",
//            "2000-05",
//            "2000-06",
//            "2000-07",
//            "2000-08",
//            "2000-09",
//            "2000-10",
//            "2000-11",
//            "2000-12",
//            "2001-01",
//            "2001-02",
//            "2001-03",
//            "2001-04",
//            "2001-05",
//            "2001-06",
//            "2001-07",
//            "2001-08",
//            "2001-09",
//            "2001-10",
//            "2001-11",
//            "2001-12",
//            "2002-01",
//            "2002-02",
//            "2002-03",
//            "2002-04",
//            "2002-05",
//            "2002-06",
//            "2002-07",
//            "2002-08",
//            "2002-09",
//            "2002-10",
//            "2002-11",
//            "2002-12",};
//            "2003-01",
//            "2003-02",
//            "2003-03",
//            "2003-04",
//            "2003-05",
//            "2003-06",
//            "2003-07",
//            "2003-08",
//            "2003-09",
//            "2003-10",
//            "2003-11",
//            "2003-12",
//            "2004-01",
//            "2004-02",
//            "2004-03",
//            "2004-04",
//            "2004-05",
//            "2004-06",
//            "2004-07",
//            "2004-08",
//            "2004-09",
//            "2004-10",
//            "2004-11",
//            "2004-12",
//            "2005-01",
//            "2005-02",
//            "2005-03",
//            "2005-04",
//            "2005-05",
//            "2005-06",
//            "2005-07",
//            "2005-08",
//            "2005-09",
//            "2005-10",
//            "2005-11",
//            "2005-12",
//            "2006-01",
//            "2006-02",
//            "2006-03",
//            "2006-04",
//            "2006-05",
//            "2006-06",
//            "2006-07",
//            "2006-08",
//            "2006-09",
//            "2006-10",
//            "2006-11",
//            "2006-12",
//            "2007-01",
//            "2007-02",
//            "2007-03",
//            "2007-04",
//            "2007-05",
//            "2007-06",
//            "2007-07",
//            "2007-08",
//            "2007-09",
//            "2007-10",
//            "2007-11",
//            "2007-12",
//            "2008-01",
//            "2008-02",
//            "2008-03",
//            "2008-04",
//            "2008-05",
//            "2008-06",
//            "2008-07",
//            "2008-08",
//            "2008-09",
//            "2008-10",
//            "2008-11",
//            "2008-12",
//            "2009-01",
//            "2009-02",
//            "2009-03",
//            "2009-04",
//            "2009-05",
//            "2009-06",
//            "2009-07",
//            "2009-08",
//            "2009-09",
//            "2009-10",
//            "2009-11",
//            "2009-12",
//            "2010-01",
//            "2010-02",
//            "2010-03",
//            "2010-04",
//            "2010-05",
//            "2010-06",
//            "2010-07",
//            "2010-08",
//            "2010-09",
//            "2010-10",
//            "2010-11",
//            "2010-12",
//            "2011-01",
//            "2011-02",
//            "2011-03",
//            "2011-04",
//            "2011-05",
//            "2011-06",
//            "2011-07",
//            "2011-08",
//            "2011-09",
//            "2011-10",
//            "2011-11",
//            "2011-12",
//        "2012-01",
//            "2012-02",
//            "2012-03",
//            "2012-04",
//            "2012-05",
//            "2012-06",
//            "2012-07",
//            "2012-08",
//            "2012-09",
//            "2012-10",
//            "2012-11",
//            "2012-12",
//        "2013-01",
//            "2013-02",
//            "2013-03",
//            "2013-04",
//            "2013-05",
//            "2013-06",
//            "2013-07",
//            "2013-08",
//            "2013-09",
//            "2013-10",
//            "2013-11",
//            "2013-12",
//        "2014-01",
//            "2014-02",
//            "2014-03",
//            "2014-04",
//            "2014-05",
//            "2014-06",
//            "2014-07",
//            "2014-08",
//            "2014-09",
//            "2014-10",
//            "2014-11",
//            "2014-12",
//        "2015-01",
//            "2015-02",
//            "2015-03",
//            "2015-04",
//            "2015-05",
//            "2015-06",
//            "2015-07",
//            "2015-08",
//            "2015-09",
//            "2015-10",
//            "2015-11",
//            "2015-12",
//        "2016-01",
//            "2016-02",
//            "2016-03",
//            "2016-04",
            "2016-05",
            "2016-06",
            "2016-07",
            "2016-08",
            "2016-09",
            "2016-10",
            "2016-11",
            "2016-12",};

        String prefix = "10.1007";
        String month = "2000-01";
        logger.info(String.format("Scheduling for prefix:%s , month:%s", prefix, month));
//        springerHarvester.schedulePrefixMonthPublications(prefix, month);
//        month = "2000-02";
//        springerHarvester.schedulePrefixMonthPublications(prefix, month);
//        month = "2000-03";
//        springerHarvester.schedulePrefixMonthPublications(prefix, month);
//        month = "2000-04";
//        springerHarvester.schedulePrefixMonthPublications(prefix, month);

//        for (String m : months) {
//            logger.info(String.format("Scheduling for prefix:%s , month:%s", prefix, m));
//            springerHarvester.schedulePrefixMonthPublications(prefix, m);
//        }
        List<String> yearMonths = springerHarvester.getMonthsFromYearToYear(1900, 2000);
        for (String yearMonth : yearMonths) {
            logger.info(String.format("Scheduling for prefix:%s , month:%s", prefix, yearMonth));
            springerHarvester.schedulePrefixMonthPublications(prefix, yearMonth);
        }

    }

}
