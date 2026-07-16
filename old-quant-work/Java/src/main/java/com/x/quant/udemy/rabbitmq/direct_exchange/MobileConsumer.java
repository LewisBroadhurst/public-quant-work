package com.x.quant.udemy.rabbitmq.direct_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MobileConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Mobile Consumer!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Received message: " + message);
        };

        channel.basicConsume("Mobile", true, deliverCallback, consumerTag -> {});
    }
}
