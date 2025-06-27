package com.example.ApiBlockChainFinancer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "transaction-queue";
    public static final String EXCHANGE_NAME = "transaction-exchange";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("transaction-routing-key").noargs();
    }


}
