package com.tf.settlement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SettlementApplication {

    public static void main(String[] args) {
        log.info("Starting Settlement Scheduler Job POC");
        SpringApplication.run(SettlementApplication.class, args);
        log.info("Started Settlement Scheduler Job POC");
    }
}
