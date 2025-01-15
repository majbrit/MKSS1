package de.hs_bremen.mkss.order_events_processor.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
public class RabbitMqConfig {

    @Value("${my.rabbitmq.an.exchange}")
    String orderExchange;

    @Value("${my.rabbitmq.a.queue}")
    String orderQueue;

    // Reply exchange and queue
    @Value("${my.rabbitmq.reply.exchange}")
    private String replyExchange;

    @Value("${my.rabbitmq.reply.queue}")
    private String replyQueue;

    @Bean
    DirectExchange orderExchange() {
        return new DirectExchange("orderExchange");
    }

    @Bean("orderQueueBean")
    Queue orderQueue() {
        return new Queue("orderQueue", false);
    }

    @Bean
    Binding orderBinding(@Qualifier("orderQueueBean")Queue orderQueue, DirectExchange orderExchange) {
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

    @Bean
    DirectExchange replyExchange() {
        return new DirectExchange("replyExchange");
    }

    @Bean("replyQueueBean")
    Queue replyQueue() {
        return new Queue("replyQueue", false);
    }

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


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
