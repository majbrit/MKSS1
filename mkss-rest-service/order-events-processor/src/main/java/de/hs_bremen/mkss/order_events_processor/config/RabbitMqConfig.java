package de.hs_bremen.mkss.order_events_processor.config;

import jakarta.annotation.PostConstruct;
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

    @Value("${my.rabbitmq.reply.exchange}")
    String replyExchange;

    @Value("${my.rabbitmq.reply.routingKey}")
    String replyRoutingKey;

    @Bean("replyExchange")
    DirectExchange replyExchange() {
        return new DirectExchange(replyExchange);
    }



    @Bean("orderExchange")
    FanoutExchange orderExchange() {
        return new FanoutExchange(orderExchange);
    }

    @Bean("orderQueue")
    Queue orderQueue() {
        return new Queue(orderQueue, false);
    }

    @Bean
    Binding orderBinding(@Qualifier("orderQueue") Queue orderQueue, @Qualifier("orderExchange") FanoutExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange);
    }

    // Konfiguration des MessageConverters (JSON)
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(); // JSON-MessageConverter für RabbitMQ
    }

    // Konfiguration des RabbitTemplate mit dem MessageConverter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());  // Setze den MessageConverter
        return rabbitTemplate;
    }

}
