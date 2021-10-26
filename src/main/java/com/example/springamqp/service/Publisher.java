package com.example.springamqp.service;

import com.example.springamqp.config.RabbitMQ;
import com.example.springamqp.model.JsonRpc;
import com.example.springamqp.model.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class Publisher {

    private static final Logger log = LoggerFactory.getLogger(Publisher.class);

    private final RabbitTemplate rabbitTemplate;

    public Publisher(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(JsonRpc<Param> jsonRpc) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQ.EXCHANGE_NAME, RabbitMQ.ROUTING_KEY, jsonRpc);
    }
}
