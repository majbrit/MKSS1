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

    // 1. Declare the order exchange as a Fanout Exchange
    @Bean("orderExchange")
    public FanoutExchange orderExchange() {
        return new FanoutExchange(orderExchange);
    }

    // 2. Declare the order queue
    @Bean("orderQueue")
    public Queue orderQueue() {
        return new Queue(orderQueue, false);
    }

    // 3. Bind the order queue to the order exchange
    @Bean
    public Binding orderBinding(@Qualifier("orderQueue") Queue queue, @Qualifier("orderExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    // 4. Declare the reply exchange as a Fanout Exchange
    @Bean("replyExchange")
    public FanoutExchange replyExchange() {
        return new FanoutExchange(replyExchange);
    }

    // 5. Declare the reply queue
    @Bean("replyQueue")
    public Queue replyQueue() {
        return new Queue(replyQueue, false);
    }

    // 6. Bind the reply queue to the reply exchange
    @Bean
    public Binding replyBinding(@Qualifier("replyQueue") Queue queue, @Qualifier("replyExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
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
