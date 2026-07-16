# JMS - Java Messaging Service
- Allows different java applications to communicate with each other.
- App that sends the message is the producer
- App that receives the message is the consumer
- The messages are not sent directly. They travel via an exchange and are stored in a queue. This will
be achieved with RabbitMQ broker.

## Exchange
- Acts as a router for incoming messages, sends the messages to the correct queue.
- At the end of the queue, the message is then passed to the consumer.

___

- Note it is possible for messages to go straight to the queue without passing through an exchange.
- This is called a direct queue.
- An exchange is needed when we have conditions we want to apply to the incoming messages.
- There are 4 types of exchanges:
  - Direct
  - Fanout
  - Topic
  - Headers

___


## Queue
