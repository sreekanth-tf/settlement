package com.tf.settlement.jobs;

import com.tf.settlement.services.SettlementServiceTwo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SettlementJobTwo implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementJobTwo.class);

    @Autowired
    private SettlementServiceTwo settlementServiceTwo;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("Starting Settlement Job Two");
        settlementServiceTwo.doSettlement();
    }
}
