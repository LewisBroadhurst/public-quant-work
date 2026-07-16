package com.x.quant.udemy.rabbitmq.rabbit_with_springboot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1/")
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private Long id = 0L;

    public TestController() {}

    @GetMapping("/{name}")
    public String testApi(@PathVariable String name) {

        Person person = new Person(id, name);

        // Sends straight to Mobile queue
        rabbitTemplate.convertAndSend("Mobile", person);

        // Direct Exchange (Only one queue will receive the message, specified by the routing key
        rabbitTemplate.convertAndSend("DirectExchange", "mobile", person);

        // Fanout Exchange (All queues will receive the message)
        rabbitTemplate.convertAndSend("FanoutExchange", "", person);

        // Topic Exchange (Follows topic logic patterns)
        rabbitTemplate.convertAndSend("TopicExchange", "tv.mobile.ac", person);

        id++;
        return "Success!";
    }

    @GetMapping("/headers/{name}")
    public String testApiHeaders(@PathVariable String name) throws IOException {

        Person person = new Person(id, name);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        objectOutput.writeObject(person);
        objectOutput.flush();
        objectOutput.close();

        byte[] byteMessage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        Message message = MessageBuilder.withBody(byteMessage)
                .setHeader("item", "mobile")
                .setHeader("quantity", 1)
                .build();

        rabbitTemplate.send("HeadersExchange", "", message);

        id++;
        return "Success!";
    }
}
