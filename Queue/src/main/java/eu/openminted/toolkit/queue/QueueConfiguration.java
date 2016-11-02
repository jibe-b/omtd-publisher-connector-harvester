package eu.openminted.toolkit.queue;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author lucasanastasiou
 */
@Configuration
@ComponentScan(basePackages = {"eu.openminted.toolkit"})
@PropertySource(value = {"classpath:queue.properties"})
public class QueueConfiguration {

    @Value("${QUEUE_HOST}")
    private String QUEUE_HOST;
    @Value("${QUEUE_PORT}")
    private Integer QUEUE_PORT;

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(QUEUE_HOST, QUEUE_PORT);
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);

        // declare queues, exchanges, binding
        TopicExchange exchange = new TopicExchange(QueueConstants.EXCHANGE_NAME, true, false);
        Queue queue = new Queue(QueueConstants.QUEUE_NAME, true, false, false);

        admin.declareExchange(exchange);
        admin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue).to(exchange).with(QueueConstants.ROUTING_KEY);
        admin.declareBinding(binding);

        return admin;
    }
    

//    @Bean
//    RabbitTemplate rabbitTemplate(RabbitAdmin rabbitAdmin){
//        RabbitTemplate rabbitTemplate = rabbitAdmin.getRabbitTemplate();
//        return rabbitTemplate;
//    }

}
