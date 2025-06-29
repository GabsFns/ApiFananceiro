package com.example.ApiBlockChainFinancer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    public static final String TRANSACTION_QUEUE = "transaction-queue";
    public static final String TRANSACTION_EXCHANGE = "transaction-exchange";
    public static final String TRANSACTION_ROUTING_KEY = "transaction-routing-key";
    // E-mails
    public static final String EMAIL_QUEUE = "email-queue";
    public static final String EMAIL_EXCHANGE = "email-exchange";
    public static final String EMAIL_ROUTING_KEY = "email-routing-key";

    public static final String USER_QUEUE = "user-queue";
    public static final String USER_EXCHANGE = "user-exchange";
    public static final String USER_ROUTING_KEY = "user-routing-key";

    @Bean
    public Queue userQueue(){
        return new Queue(USER_QUEUE, true);
    }

    @Bean
    public DirectExchange userExchange(){
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange userExchange){
        return BindingBuilder.bind(userQueue).to(userExchange).with(USER_ROUTING_KEY);
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue(TRANSACTION_QUEUE, true);
    }

    @Bean
    public DirectExchange transactionExchange(){
        return new DirectExchange(TRANSACTION_EXCHANGE);
    }

    @Bean
    public Binding transactionBinding(Queue transactionQueue, DirectExchange transactionExchange){
        return BindingBuilder.bind(transactionQueue).to(transactionExchange).with(TRANSACTION_ROUTING_KEY);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public DirectExchange emailExchange(){
        return new DirectExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange){
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }


}
