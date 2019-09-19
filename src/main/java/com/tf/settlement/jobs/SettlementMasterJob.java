package com.tf.settlement.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Component
public class SettlementMasterJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementMasterJob.class);
    private static final String SETTLEMENT_JOB_ONE_ID = "Settlement Job One";
    private static final String SETTLEMENT_JOB_TWO_ID = "Settlement Job Two";

    @Value("${settlement.job.one.cron}")
    private String settlementCronExpOne;

    @Value("${settlement.job.two.cron}")
    private String settlementCronExpTwo;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("Settlement Master Job Scheduler Starts");
        try {

            if (!context.getScheduler().checkExists(JobKey.jobKey(SETTLEMENT_JOB_ONE_ID))) {
                LOGGER.info("Scheduling Settlement Job One");
                context.getScheduler().scheduleJob(buildJobDetail(SettlementJobOne.class, SETTLEMENT_JOB_ONE_ID),
                        buildTrigger(settlementCronExpOne, "Settlement Job Trigger One"));
            }

            if (!context.getScheduler().checkExists(JobKey.jobKey(SETTLEMENT_JOB_TWO_ID))) {
                LOGGER.info("Scheduling Settlement Job Two");
                context.getScheduler().scheduleJob(buildJobDetail(SettlementJobTwo.class, SETTLEMENT_JOB_TWO_ID),
                        buildTrigger(settlementCronExpTwo, "Settlement Job Trigger Two"));
            }
        } catch (SchedulerException se) {
            LOGGER.error("Error occurred while scheduling settlement jobs", se);
        }
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass, String identity) {
        return JobBuilder.newJob(jobClass)
                .storeDurably()
                .withIdentity(identity)
                .build();
    }

    private Trigger buildTrigger(String jobCron, String identity) {
        return TriggerBuilder.newTrigger()
                .withIdentity(identity)
                .withSchedule(cronSchedule(jobCron))
                .build();
    }
}
