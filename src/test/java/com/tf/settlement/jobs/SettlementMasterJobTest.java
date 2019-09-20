package com.tf.settlement.jobs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettlementMasterJobTest {
    String jobKey = "Settlement Master Job";
    String triggerKey = "Settlement Master Trigger";

    @Autowired
    Scheduler scheduler;

    @Test
    public void testSchedulerInitiated() {
        assertNotNull(scheduler);
    }

    @Test
    public void testSettlementMasterJobStarts() throws Exception {
        assertNotNull("Job Doesn't Exist", scheduler.checkExists(jobKey(jobKey)));
        JobDetail job = JobBuilder.newJob(SettlementMasterJob.class)
                .storeDurably()
                .withIdentity(jobKey)
                .build();
        Trigger trigger = newTrigger()
                .withIdentity(triggerKey)
                .startNow()
                .withSchedule(simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(1))
                .build();
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(1000);
        scheduler.shutdown(true);
    }
}
