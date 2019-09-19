package com.tf.settlement.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Component
public class SettlementMasterJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettlementMasterJob.class);
    private static final Map<String, Class<? extends Job>> SETTLEMENT_JOB_CLASSES = new HashMap<>();

    static {
        SETTLEMENT_JOB_CLASSES.put("one", SettlementJobOne.class);
        SETTLEMENT_JOB_CLASSES.put("two", SettlementJobTwo.class);
    }

    @Value("#{${settlement.job.crons}}")
    private Map<String, String> settlementCronExps;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("Settlement Master Job Scheduler Starts");
        settlementCronExps.forEach((jobId, jobCron) -> {
            try {
                if (!context.getScheduler().checkExists(JobKey.jobKey(jobId))) {
                    LOGGER.info("Scheduling Settlement Job {}", jobId);
                    context.getScheduler().scheduleJob(buildJobDetail(SETTLEMENT_JOB_CLASSES.get(jobId), jobId),
                            buildTrigger(jobCron, jobId));
                }
            } catch (SchedulerException exp) {
                LOGGER.error("Error occurred while scheduling settlement jobs", exp);
            }
        });

    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass, String jobId) {
        return JobBuilder.newJob(jobClass)
                .storeDurably()
                .withIdentity(jobId)
                .build();
    }

    private Trigger buildTrigger(String jobCron, String jobId) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobId)
                .withSchedule(cronSchedule(jobCron))
                .build();
    }
}
