package com.tf.settlement.config;

import com.tf.settlement.jobs.SettlementMasterJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class SettlementMasterJobBeanConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(SettlementMasterJob.class)
                .storeDurably()
                .withIdentity("Settlement Master Job", "Settlement Job")
                .withDescription("Settlement Master Job")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail, @Value("${settlement.job.master.cron}") String masterJobCron) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("Settlement Master Trigger", "Settlement Trigger")
                .withDescription("Settlement Master Trigger")
                .withSchedule(cronSchedule(masterJobCron))
                .build();
    }
}
