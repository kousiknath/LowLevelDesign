<b> Design a applicationAwareMessage broker which has the following properties: </b>

1. Producers should be able to produce messages.
2. Producers can be divided into multiple partitions.
3. There can be multiple consumers in a consumer group.
4. A single consumer can consume from single topic. We don't support consuming from multiple topics.
5. In a consumer group, at a time, a consumer can consume from a single partition. In a consumer group, a 
6. single consumer always maps to a single partition.
7. Optional: If possible, messages should be persisted.