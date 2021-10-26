package com.example.springamqp.service;

import com.example.springamqp.config.RabbitMQ;
import com.example.springamqp.model.JsonRpc;
import com.example.springamqp.model.Param;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final ObjectMapper mapper;

    public Consumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQ.QUEUE_NAME)
    public void receiveMessage(final JsonRpc<Param> customMessage) {
        try {
            log.info("Received message. Deserialized to: {}", mapper.writeValueAsString(customMessage));
        } catch (JsonProcessingException exception){
            log.debug("Problem parsing response to JSON");
            log.debug(exception.getMessage());
        }
    }
}
