package com.tf.settlement.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Component
@Slf4j
public class SettlementMasterJob implements Job {
    @Value("#{${settlement.job.crons}}")
    private Map<String, String> settlementJobCrons;

    @Value("#{${settlement.job.classes}}")
    private Map<String, Class<Job>> settlementJobClasses;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Settlement Master Job Scheduler Starts");
        settlementJobCrons.forEach(this::scheduleSettlementJobs);
    }

    private void scheduleSettlementJobs(String jobId, String jobCron) {
        try {
            if (!scheduler.checkExists(JobKey.jobKey(jobId))) {
                log.info("Scheduling Settlement Job {}", jobId);
                scheduler.scheduleJob(buildJobDetail(jobId), buildTrigger(jobId, jobCron));
            }
        } catch (SchedulerException exp) {
            log.error("Error occurred while scheduling settlement jobs", exp);
        }
    }

    private Trigger buildTrigger(String jobId, String jobCron) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobId)
                .withSchedule(cronSchedule(jobCron))
                .build();
    }

    private JobDetail buildJobDetail(String jobId) {
        return JobBuilder.newJob(settlementJobClasses.get(jobId))
                .storeDurably()
                .withIdentity(jobId)
                .build();
    }
}
