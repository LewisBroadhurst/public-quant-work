package com.x.quant.udemy.rabbitmq.intro;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Simple RabbitMQPublisher
 */
public class RabbitMQPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Publisher!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Hello RabbitMQ!";

        channel.basicPublish("", "TestQueue", null, message.getBytes());
        channel.basicPublish("", "TestQueue", null, message.getBytes());
        channel.basicPublish("", "TestQueue", null, message.getBytes());
        channel.basicPublish("", "TestQueue", null, message.getBytes());


        // Clean up
        channel.close();
        connection.close();
    }

}
