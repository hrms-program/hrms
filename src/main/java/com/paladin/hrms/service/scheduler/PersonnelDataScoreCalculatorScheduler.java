package com.paladin.hrms.service.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonnelDataScoreCalculatorScheduler {

	@Bean
	public JobDetail getJobDetail() {
		return JobBuilder.newJob(PersonnelDataScoreCalculatorJob.class).withIdentity("PersonnelDataScoreCalculator").storeDurably(true).build();
	}
	
	@Bean
	public Trigger getTrigger() throws SchedulerException {
		// cron表达式 表示每隔i秒执行
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 * * ?").withMisfireHandlingInstructionDoNothing();
		return TriggerBuilder.newTrigger().withIdentity("PersonnelDataScoreCalculator").forJob(getJobDetail()).withSchedule(scheduleBuilder).build();
	}
}
