package com.tf.settlement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SettlementServiceTwo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementServiceTwo.class);

    public void doSettlement() {
        LOGGER.info("In Settlement Service Two, Thread Id {}", Thread.currentThread().getName());
    }
}
