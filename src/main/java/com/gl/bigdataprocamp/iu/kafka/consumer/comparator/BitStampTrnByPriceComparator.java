package com.gl.bigdataprocamp.iu.kafka.consumer.comparator;

import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;

import java.util.Comparator;

public class BitStampTrnByPriceComparator implements Comparator<BitStampTrn> {
    @Override
    public int compare(BitStampTrn o1, BitStampTrn o2) {
        return o1.getData().getPrice().compareTo(o2.getData().getPrice());
    }
}
