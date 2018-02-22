package com.contactsunny.poc.springKafkaTest.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestTopicListener {


    @KafkaListener(topics = "testKafka")
    public void receive(ConsumerRecord<?, ?> consumerRecord,
            Acknowledgment acknowledgment) {

        System.out.println("Received message: ");
        System.out.println(consumerRecord.value().toString());

        acknowledgment.acknowledge();
    }
}
