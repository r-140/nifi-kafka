package com.gl.bigdataprocamp.iu.kafka.consumer.util;

import com.gl.bigdataprocamp.iu.kafka.consumer.comparator.BitStampTrnByPriceComparator;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;

import java.util.TreeSet;

public enum BitStampsProcessedStorage {
    INSTANCE;
    private static final int MAX_RESULT = 10;

    private TreeSet<BitStampTrn> bitStampTrns =
            new TreeSet<>((new BitStampTrnByPriceComparator()).reversed());

    public TreeSet<BitStampTrn> getBitStampTrns() {
        return bitStampTrns;
    }

    public void addBitStampTrnToCollection(BitStampTrn bitStampTrn) {
        bitStampTrns.add(bitStampTrn);
        if(bitStampTrns.size() > MAX_RESULT) {
            bitStampTrns.remove(bitStampTrns.last());
        }
    }
}
