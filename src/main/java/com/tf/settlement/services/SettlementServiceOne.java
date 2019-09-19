package com.tf.settlement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SettlementServiceOne {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementServiceOne.class);

    public void doSettlement() {
        LOGGER.info("In Settlement Service One, Thread Id {}", Thread.currentThread().getName());
    }
}
