# Spring Kafka Test Project

The topic for the listener used in this project is hardcoded in the ```listeners.TestTopicListener``` class. The topic is ```testKafka```. This could be changed at any time.

All the configuration used for the Kafka client could be found in the ```config``` package.

# Apache Kafka

## Kafka Consumer Commit Methods

- ```AckMode.MANUAL_IMMEDIATE``` will commit the offsets to kafka immediately, without waiting for any other kind of events to occur.

- ```AckMode.MANUAL``` will work similar to AckMode.BATCH, which means after the acknowledge() method is called on a message, the system will wait till all the messages received by the poll() method have been acknowledged. This could take a long time, depending on your setup.