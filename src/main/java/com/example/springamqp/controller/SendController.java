package com.example.springamqp.controller;

import com.example.springamqp.model.JsonRpc;
import com.example.springamqp.model.Param;
import com.example.springamqp.service.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class SendController {

    private final Publisher publisher;

    public SendController(Publisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/sendAMQP")
    public String sendJsonRpc(){
        // build up a custom param list
        Param param = new Param("Jimmy", "Some random quantity");

        JsonRpc<Param> jsonRpc = new JsonRpc<>();
        jsonRpc.setJsonrpc("2.0");
        jsonRpc.setId(UUID.randomUUID().toString());
        jsonRpc.setMethod("methodName");
        jsonRpc.setParams(param);

        publisher.sendMessage(jsonRpc);

        return "Sent" + jsonRpc;
    }
}
