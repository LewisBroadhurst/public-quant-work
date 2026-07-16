package com.x.quant.udemy.rabbitmq.topic_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Topic Publisher!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Received message: " + message);
        };

        channel.basicPublish(
                "TopicExchange",
                "tv.mobile.ac",
                null,
                "Hello RabbitMQ, this is for the ... Mobile/TV queue!".getBytes()
        );

        channel.basicPublish(
                "TopicExchange",
                "ac.mobile",
                null,
                "Hello RabbitMQ, this is for the ... AC queue!".getBytes()
        );

        channel.basicPublish(
                "TopicExchange",
                "tv.ac",
                null,
                "Hello RabbitMQ, this is for the ... TV queue!".getBytes()
        );

        channel.close();
        connection.close();
    }

}
