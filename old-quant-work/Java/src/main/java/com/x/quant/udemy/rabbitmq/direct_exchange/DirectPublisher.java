package com.x.quant.udemy.rabbitmq.direct_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * DirectPublisher
 * Note this requires setup in the RabbitMQ Management Console.
 * Set up an exchange with different bindings to different queues.
 */
public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Direct Publisher!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message1 = "Hello RabbitMQ, this is for the mobile queue!";
        String message2 = "Hello RabbitMQ, this is for the tv queue!";
        String message3 = "Hello RabbitMQ, this is for the ac queue!";

        channel.basicPublish("DirectExchange", "ac", null, message3.getBytes());
        channel.basicPublish("DirectExchange", "tv", null, message2.getBytes());
        channel.basicPublish("DirectExchange", "mobile", null, message1.getBytes());

        channel.close();
        connection.close();
    }

}
