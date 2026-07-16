package com.x.quant.udemy.rabbitmq.rabbit_with_springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitMQSpringConsumer {

//        @RabbitListener(queues = "Mobile")
//        public void consumeMessage(Person person) {
//            System.out.println("Consuming message from Mobile queue: " + person.getName());
//
//        }

        @RabbitListener(queues = "Mobile")
        public void consumeMessageHeaders(byte[] message) throws IOException, ClassNotFoundException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message);
            ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream);
            Person person = (Person) objectInput.readObject();

            byteArrayInputStream.close();
            objectInput.close();
            System.out.println("Consuming message from Mobile queue: " + person.getName() + " with id: " + person.getId());
        }

}
