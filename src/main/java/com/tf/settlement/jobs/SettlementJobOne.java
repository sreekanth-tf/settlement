package com.tf.settlement.jobs;

import com.tf.settlement.services.SettlementServiceOne;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SettlementJobOne implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementJobOne.class);

    @Autowired
    private SettlementServiceOne settlementServiceOne;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("Starting Settlement Job One");
        settlementServiceOne.doSettlement();
    }
}
