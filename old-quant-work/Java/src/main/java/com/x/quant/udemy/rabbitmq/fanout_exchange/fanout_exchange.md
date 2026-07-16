## Fanout Exchange
- Producer publishes a message to the "fanout" exchange
- No condition/key applied to message
- Message is sent to all queues that are bound to the exchange
- similar to publish/subscribe pattern