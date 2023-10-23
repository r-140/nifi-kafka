package com.gl.bigdataprocamp.iu.kafka.consumer.util;

import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampData;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BitStampCollectionsTest {

    @Before
    public void setup(){
        populateCollection(1);
    }

    @Test
    public void fillCollectionTest(){
        final TreeSet<BitStampTrn> bitStampTrns = BitStampsProcessedStorage.INSTANCE.getBitStampTrns();

        assertNotNull(bitStampTrns);
        assertEquals(10, bitStampTrns.size());

        System.out.println(bitStampTrns);
        assertEquals(Double.valueOf(47.5), bitStampTrns.first().getData().getPrice());
        assertEquals(Double.valueOf(25.0), bitStampTrns.last().getData().getPrice());
    }

    @Test
    public void fillCollectionAfterOtherPoolTest(){
        populateCollection(5);
        final TreeSet<BitStampTrn> bitStampTrns = BitStampsProcessedStorage.INSTANCE.getBitStampTrns();

        assertNotNull(bitStampTrns);
        assertEquals(10, bitStampTrns.size());

        System.out.println(bitStampTrns);
        assertEquals(Double.valueOf(57.5), bitStampTrns.first().getData().getPrice());
        assertEquals(Double.valueOf(35.0), bitStampTrns.last().getData().getPrice());
    }

    private void populateCollection(int count){
        int max = count+19;
        for(int i = count; i < max; i++) {
            BitStampsProcessedStorage.INSTANCE.addBitStampTrnToCollection(buildTrnObj(i));
        }
    }

    private BitStampTrn buildTrnObj(int i) {
        return new BitStampTrn(buildData(i), "order_channel", "order_event");
    }

    private BitStampData buildData(int i) {
        return new BitStampData((long)i, 1, 2.3*i, 2.0, 2.0, 2.5*i);
    }
}
