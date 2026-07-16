package com.x.quant.udemy.rabbitmq.realtime_example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RealtimeExample {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Excel Example!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject json = new JSONObject();
        json.put("user", "lbroadhurst");
        json.put("email", "lbroadhurst@email.com");
        json.put("request", "EXPORT_TO_EXCEL");

        channel.basicPublish("", "TestQueue", null, json.toString().getBytes());

        channel.close();
        connection.close();

    }

}
