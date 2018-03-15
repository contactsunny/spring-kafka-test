package com.contactsunny.poc.springKafkaTest.listeners;

import com.contactsunny.poc.springKafkaTest.exceptions.CustomException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TestTopicListener {

    private static final Logger logger = Logger.getLogger(TestTopicListener.class);
    
    /**
     * The Acknowledgment object injected into this method is used to "acknowledge"
     * that the consumer/listener received and processed a message on the specified
     * kafka topic.`
     *
     * @param consumerRecord The ConsumerRecord<> object injected
     * @param acknowledgment The Acknowledgement object injected
     */
    @KafkaListener(topics = "testKafka")
    public void receive(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {

        /*
        Reading the message into a String variable.
         */
        String message = consumerRecord.value().toString();

        /*
        Printing the received message to the console.
         */
        logger.info("Received message: ");
        logger.info(message);

        /*
        Declaring a flag to decide if offsets have to be committed.
         */
        boolean commitOffsets = false;

        /*
        Looping till the flag becomes true.
         */
        while (!commitOffsets) {
            /*
            Calling the handleMessage() method to process the message received from Kafka.
             */
            try {
                handleMessage(message);

                commitOffsets = true;
            } catch (CustomException e) {
//                e.printStackTrace();
                logger.error("Exception caught. Not committing offset to Kafka.");
                commitOffsets = false;
            }
        }

        /*
        If the flag is set, committing the offsets.
         */
        if (commitOffsets) {
            logger.info("No exceptions, committing offsets.");

            /*
            Committing the offset to Kafka.
             */
            acknowledgment.acknowledge();
        }
    }

    /**
     * Function to handle the message received from Kafka.
     *
     * @param message The String message
     */
    private void handleMessage(String message) throws CustomException {

        /*
        Printing a random message.
         */
        logger.info("Busy handling message!");

        /*
        Processing the message => calculating the length of the String.
         */
        int messageLength = message.length();
        logger.info("Message length: " + messageLength);

        /*
        Generating a random integer. If the integer is odd, a CustomException will be
        thrown, to see if the offset will be committed. If not, how will this be handled?
         */
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        logger.info("Random number: " + randomNumber);

        if ((randomNumber % 2) != 0) {
            throw new CustomException("Odd number generated, so throwing this exception");
        }

        logger.info("Even number generated, committing offsets.");
    }
}
