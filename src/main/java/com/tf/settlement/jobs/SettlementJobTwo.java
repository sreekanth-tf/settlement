package com.tf.settlement.jobs;

import com.tf.settlement.services.SettlementServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SettlementJobTwo implements Job {
    @Autowired
    private SettlementServiceTwo settlementServiceTwo;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Starting Settlement Job Two");
        settlementServiceTwo.doSettlement();
    }
}
