package com.gl.bigdataprocamp.iu.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;
import com.gl.bigdataprocamp.iu.kafka.consumer.util.BitStampsProcessedStorage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.logging.Logger;

import static com.gl.bigdataprocamp.iu.kafka.consumer.config.ConsumerConfigHelper.getConsumerConfig;
import static com.gl.bigdataprocamp.iu.kafka.consumer.util.JsonConverter.convertRecordToDto;

public class BitStampKafkaConsumer {
    private final static Logger log = Logger.getLogger("BitStampKafkaConsumer");

    //    todo pass topic as params
    private static String TOPIC_NAME = "bitstamp-trn-topic";

    private static Integer TIMEOUT = 100;

    public static void main(String[] args) {
        final String topicName = args[0];

        if(topicName == null || topicName.isEmpty()) {
            throw new IllegalArgumentException("Topic name is required");
        }

        // create consumer
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerConfig());

        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(TIMEOUT));

            log.info("records after poll " + records.count());

            try {
                process(records);
                printResult();
            } catch (JsonProcessingException e) {
                log.info("Exception has been thrown while processing the record " + e.getMessage());
                throw new RuntimeException(e);
            }

            log.info("finishing processing polled records");
//            at least once strategy
            consumer.commitSync();
        }
    }

    private static void process(ConsumerRecords<String, String> records) throws JsonProcessingException {
        for (ConsumerRecord<String, String> record : records){
//            log.info("Key: " + record.key() + ", Value: " + record.value());

//            log.info("Partition: " + record.partition() + ", Offset:" + record.offset());

            final BitStampTrn bitStampTrn = convertRecordToDto(record.value());

            addBitStampTrnToCollection(bitStampTrn);
//            log.info("Converted record " + bitStampTrn);
        }
    }

    private static void addBitStampTrnToCollection(BitStampTrn bitStampTrn) {
        BitStampsProcessedStorage.INSTANCE.addBitStampTrnToCollection(bitStampTrn);
    }

    private static void printResult(){
        log.info("The bitstamp transactions with 10 highest prices: \n"
                + BitStampsProcessedStorage.INSTANCE.getBitStampTrns());
    }
}
