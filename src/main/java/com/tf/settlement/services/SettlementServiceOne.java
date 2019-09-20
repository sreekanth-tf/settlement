package com.tf.settlement.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SettlementServiceOne {

    public void doSettlement() {
        log.info("In Settlement Service One, Thread Id {}", Thread.currentThread().getName());
    }
}
