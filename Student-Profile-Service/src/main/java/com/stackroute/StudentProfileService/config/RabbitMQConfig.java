package com.stackroute.StudentProfileService.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Indicates this as a configuration class
 */
@Configuration
public class RabbitMQConfig {
    /**
     * To get the property values
     */
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.sessionqueue}")
    private String sessionqueue ;

    @Value("${spring.rabbitmq.sessionexchange}")
    private String sessionexchange;

    @Value("${spring.rabbitmq.sessionroutingkey}")
    private String sessionroutingkey;


    /**
     * creating queue
     * durable queue survives a server restart.
     */
    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }


    @Bean
    Exchange myExchange() {
        /**
         * Add code to create Direct Exchange
         */
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }


    @Bean
    Binding binding() {
        /**
         * Add code to bind queue and Exchange
         */
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(routingKey)
                .noargs();
    }
    @Bean
    Queue sessionqueue() {
        return new Queue(sessionqueue, true);
    }


    @Bean
    Exchange sessionExchange() {

        return ExchangeBuilder.topicExchange(sessionexchange).durable(true).build();
    }
    @Bean
    Binding sessionBinding() {

        return BindingBuilder
                .bind(sessionqueue())
                .to(sessionExchange())
                .with(sessionroutingkey)
                .noargs();
    }


    /**
     * Creating connections to rabbitMq broker
     */
    @Bean
    ConnectionFactory connectionFactory() {
        /**
         * Add code to create connection to rabbitMq broker
         */
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);

        return cachingConnectionFactory;
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        /*
         * Jackson2JsonMessageConverter to send the message in a JSON format.
         */
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

