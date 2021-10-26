package com.example.springamqp.service;

import com.example.springamqp.config.RabbitMQ;
import com.example.springamqp.model.JsonRpc;
import com.example.springamqp.model.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = RabbitMQ.QUEUE_NAME)
    public void receiveMessage(final JsonRpc<Param> customMessage) {
        log.info("Received message. Deserialized to: {}", customMessage.toString());
    }
}
