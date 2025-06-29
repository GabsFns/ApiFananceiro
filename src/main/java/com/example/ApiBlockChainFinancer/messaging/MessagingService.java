package com.example.ApiBlockChainFinancer.messaging;

import com.example.ApiBlockChainFinancer.config.RabbitMQConfig;
import com.example.ApiBlockChainFinancer.model.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTransactionMessage(String message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TRANSACTION_EXCHANGE,
                RabbitMQConfig.TRANSACTION_ROUTING_KEY,
                message
        );
    }

    public void sendUserMessage(String message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_ROUTING_KEY,
                message
        );

    }

    public void sendEmailMessage(EmailMessage emailMessage) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EXCHANGE,
                RabbitMQConfig.EMAIL_ROUTING_KEY,
                emailMessage
        );
    }

}
