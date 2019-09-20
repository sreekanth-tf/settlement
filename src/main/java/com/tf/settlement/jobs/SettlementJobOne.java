package com.tf.settlement.jobs;

import com.tf.settlement.services.SettlementServiceOne;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SettlementJobOne implements Job {
    @Autowired
    private SettlementServiceOne settlementServiceOne;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Starting Settlement Job One");
        settlementServiceOne.doSettlement();
    }
}
