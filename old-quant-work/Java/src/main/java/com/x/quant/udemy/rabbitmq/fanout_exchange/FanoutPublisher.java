package com.x.quant.udemy.rabbitmq.fanout_exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Fanout Publisher!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicPublish("Fanout", "", null, "Hello RabbitMQ, this is for the Mobile and TV queue!".getBytes());

        channel.close();
        connection.close();
    }
}
