## RabbitMQ
Start the RabbitMQ server by running the executable:
```bash
.\rabbitmq-server.bat
# http://localhost:15672/
```
Login to the RabbitMQ server with the default credentials:
- Username: guest
- Password: guest

## Queue
Go to queues tab in the manager and add queue.
Check out RabbitMQPublisher for a basic example of how to send a message to a queue.
Consumer example can be found in RabbitMQConsumer.

## Round Robin
RabbitMQ will send messages to consumers in a round-robin fashion. 
This means that if there are 3 consumers, the first message will go to consumer 1, 
the second message will go to consumer 2, the third message will go to consumer 3, 
the fourth message will go to consumer 1, and so on.
