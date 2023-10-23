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

    private static Integer TIMEOUT = 100;

    public static void main(String[] args) {
        final String topicName = getTopicName(args);

        log.info("Storage size " + BitStampsProcessedStorage.INSTANCE.getBitStampTrns());

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerConfig());

        consumer.subscribe(Collections.singletonList(topicName));

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(TIMEOUT));
            int polledRecords = records.count();
            log.info("records after poll " + polledRecords);
            if(polledRecords > 0) {
                try {
                    process(records);
//                    printResult();
                } catch (JsonProcessingException e) {
                    log.info("Exception has been thrown while processing the record " + e.getMessage());
                }

                log.info("finishing processing polled records");
//            at least once strategy
                consumer.commitSync();
            }
            log.info("Storage size ahter pull " + BitStampsProcessedStorage.INSTANCE.getBitStampTrns().size());
        }
    }

    private static String getTopicName(String[] args){
        if(args[0]==null || args[0].isEmpty()) {
            throw new IllegalArgumentException("topic name is required");
        }

        return args[0].trim();
    }

    private static void process(ConsumerRecords<String, String> records) throws JsonProcessingException {
        for (ConsumerRecord<String, String> record : records){
            final BitStampTrn bitStampTrn = convertRecordToDto(record.value());
            addBitStampTrnToCollection(bitStampTrn);
        }
    }

    private static void addBitStampTrnToCollection(BitStampTrn bitStampTrn) {
        BitStampsProcessedStorage.INSTANCE.addBitStampTrnToCollection(bitStampTrn);
    }

    private static void printResult(){
        log.info("The bitStamp transactions with 10 highest prices: \n"
                + BitStampsProcessedStorage.INSTANCE.getBitStampTrns());
    }
}
