package com.contactsunny.poc.springKafkaTest.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestTopicListener {

    /**
     * The Acknowledgment object injected into this method is used to "acknowledge"
     * that the consumer/listener received and processed a message on the specified
     * kafka topic.
     *
     * @param consumerRecord The ConsumerRecord<> object injected
     * @param acknowledgment The Acknowledgement object injected
     */
    @KafkaListener(topics = "testKafka")
    public void receive(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {

        /*
        Printing the received message to the console.
         */
        System.out.println("Received message: ");
        System.out.println(consumerRecord.value().toString());

        /*
        Manually committing the offset to Kafka by "acknowledging" that we
        received the message.
         */
        acknowledgment.acknowledge();
    }
}
