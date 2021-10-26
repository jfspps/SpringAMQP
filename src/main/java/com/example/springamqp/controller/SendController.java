package com.example.springamqp.controller;

import com.example.springamqp.model.JsonRpc;
import com.example.springamqp.model.Param;
import com.example.springamqp.service.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class SendController {

    private final Publisher publisher;

    private final ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(SendController.class);

    public SendController(Publisher publisher, ObjectMapper mapper) {
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @GetMapping("/sendAMQP")
    public String sendJsonRpc() {
        // build up a custom param list
        Param param = new Param("Jimmy", "Some random quantity");

        JsonRpc<Param> jsonRpc = new JsonRpc<>();
        jsonRpc.setJsonrpc("2.0");
        jsonRpc.setId(UUID.randomUUID().toString());
        jsonRpc.setMethod("methodName");
        jsonRpc.setParams(param);

        publisher.sendMessage(jsonRpc);
        try {
            logger.info("JSON RPC 2.0: " + mapper.writeValueAsString(jsonRpc));
        } catch (JsonProcessingException exception){
            logger.info("Problem converting POJO to JSON");
            logger.info(exception.getMessage());
        }

        return "Message sent";
    }
}
