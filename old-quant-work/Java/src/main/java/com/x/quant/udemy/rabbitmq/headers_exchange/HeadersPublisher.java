package com.x.quant.udemy.rabbitmq.headers_exchange;

import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Starting RabbitMQ Headers Publisher!");

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String, Object> headersMobileAC = new HashMap<>();
        headersMobileAC.put("item", "mobile");
        headersMobileAC.put("quantity", 1);
        String messageMobileAC = "Hello RabbitMQ, this is for the mobile/ac queue!";

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .headers(headersMobileAC)
                .build();

        channel.basicPublish(
                "HeadersExchange",
                "",
                properties,
                messageMobileAC.getBytes()
        );

        Map<String, Object> headersTV = new HashMap<>();
        headersMobileAC.put("item", "ac");
        headersMobileAC.put("quantity", 2);
        String messageTV = "Hello RabbitMQ, this is for the tv queue!";

        AMQP.BasicProperties propertiesMobile = new AMQP.BasicProperties.Builder()
                .headers(headersTV)
                .build();

        channel.basicPublish(
                "HeadersExchange",
                "",
                propertiesMobile,
                messageTV.getBytes()
        );


        channel.close();
        connection.close();
    }
}
