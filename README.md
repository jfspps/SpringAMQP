# Spring AMQP and JSON-RPC 2.0

This project is an extension of a demo given [here](https://thepracticaldeveloper.com/produce-and-consume-json-messages-with-spring-boot-amqp/) and 
looks to show how [JSON-RPC 2.0](https://www.jsonrpc.org/specification) messages can be published and consumed.

Also see [here](https://github.com/jfspps/SpringAndRabbitMQDemo) for another demo.

The broker account settings are saved (and can be extended) in [applications.properties](/src/main/resources/application.properties).

To send a message, use 

```bash
GET http://localhost:5000/api/v1/sendAMQP
```