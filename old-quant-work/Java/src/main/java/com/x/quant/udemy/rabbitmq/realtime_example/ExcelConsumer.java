package com.x.quant.udemy.rabbitmq.realtime_example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ExcelConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Excel Consumer!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("TestQueue", true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(delivery.getProperties());
            JSONObject json = new JSONObject(message);
            System.out.println("Received message. Request: " + json.get("request"));
        }, consumerTag -> {});
    }
}
