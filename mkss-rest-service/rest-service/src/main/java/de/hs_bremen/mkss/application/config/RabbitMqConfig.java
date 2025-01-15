package de.hs_bremen.mkss.application.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // Order exchange and queue
    @Value("${my.rabbitmq.an.exchange}")
    private String orderExchange;

    @Value("${my.rabbitmq.a.queue}")
    private String orderQueue;

    // Reply exchange and queue
    @Value("${my.rabbitmq.reply.exchange}")
    private String replyExchange;

    @Value("${my.rabbitmq.reply.queue}")
    private String replyQueue;

    // 1. Declare the order exchange as an Exchange
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }

    // 2. Declare the order queue
    @Bean("orderQueueBean")
    public Queue orderQueue() {
        return new Queue(orderQueue, false);
    }

    // 3. Bind the order queue to the order exchange

    @Bean
    Binding orderBinding(@Qualifier("orderQueueBean") Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with("order-created");
    }

    @Bean
    Binding orderUpdateBinding(@Qualifier("orderQueueBean")Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with("order-updated");
    }

    @Bean
    Binding orderDeleteBinding(@Qualifier("orderQueueBean")Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with("order-deleted");
    }

    // 4. Declare the reply exchange as an Exchange
    @Bean("replyExchange")
    public DirectExchange replyExchange() {
        return new DirectExchange(replyExchange);
    }

    // 5. Declare the reply queue
    @Bean("replyQueueBean")
    public Queue replyQueue() {
        return new Queue(replyQueue, false);
    }

    // 6. Bind the reply queue to the reply exchange
    @Bean
    Binding replyBinding(@Qualifier("replyQueueBean")Queue replyQueue, DirectExchange replyExchange) {
        return BindingBuilder.bind(replyQueue)
                .to(replyExchange)
                .with("emit-order-created");
    }

    @Bean
    Binding replyUpdateBinding(@Qualifier("replyQueueBean")Queue replyQueue, DirectExchange replyExchange) {
        return BindingBuilder.bind(replyQueue)
                .to(replyExchange)
                .with("emit-update-order");
    }

    @Bean
    Binding replyDeleteBinding(@Qualifier("replyQueueBean")Queue replyQueue, DirectExchange replyExchange) {
        return BindingBuilder.bind(replyQueue)
                .to(replyExchange)
                .with("emit-delete-order");
    }

    // 7. Configure the message converter to handle JSON
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 8. Configure the AMQP template
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
