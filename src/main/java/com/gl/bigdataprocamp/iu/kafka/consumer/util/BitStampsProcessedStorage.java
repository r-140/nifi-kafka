package com.gl.bigdataprocamp.iu.kafka.consumer.util;

import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;

import java.util.Comparator;
import java.util.TreeSet;

public enum BitStampsProcessedStorage {
    INSTANCE;
    private static final int MAX_RESULT = 10;

    private final Comparator<BitStampTrn> PRICE_COMPARATOR =
            Comparator.comparing(p->
                {
                    if (p.getData() != null && p.getData().getPrice() != null) {
                        return p.getData().getPrice();
                    } else {
                        return Double.valueOf(0);
                    }
                }
            );
    private TreeSet<BitStampTrn> bitStampTrns = new TreeSet<>(PRICE_COMPARATOR.reversed());

    public TreeSet<BitStampTrn> getBitStampTrns() {
        return bitStampTrns;
    }

    public void addBitStampTrnToCollection(BitStampTrn bitStampTrn) {
        bitStampTrns.add(bitStampTrn);
        if(bitStampTrns.size() > MAX_RESULT) {
            System.out.println("TRN HAS BEEN REMOVED " + bitStampTrns.last());
            bitStampTrns.remove(bitStampTrns.last());
        }
    }
}
