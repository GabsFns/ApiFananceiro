package com.example.ApiBlockChainFinancer.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessagingConsumer {
    @RabbitListener(queues = "user-queue")
    public void receiveMessage(String message){
        System.out.println("Conta deleta com sucesso");
    }

}
