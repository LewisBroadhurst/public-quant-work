# Default Exchange
Type of Direct Exchange, used when no exchange is specified.
This is why when we send a message without a specified exchange, the message
is still published as expected.

This is the `AMPQ default` exchange that is seen in the RabbitMQ Management.
All queues are automatically bound to this exchange with a routing key that
is equal to the queue name.
- We cannot implicitly bind a queue to the default exchange.
- Nor can we delete this exchange.