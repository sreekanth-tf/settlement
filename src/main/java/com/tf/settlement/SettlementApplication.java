package com.tf.settlement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SettlementApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SettlementApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Settlement Scheduler Job POC");
		SpringApplication.run(SettlementApplication.class, args);
		LOGGER.info("Started Settlement Scheduler Job POC");
	}
}
