package com.paladin.hrms.service.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.paladin.hrms.service.analysis.AnalysisPersonnelDataScoreService;

@DisallowConcurrentExecution
public class PersonnelDataScoreCalculatorJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(PersonnelDataScoreCalculatorJob.class);

	@Autowired
	private AnalysisPersonnelDataScoreService analysisPersonnelDataScoreService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("----开始执行人员信息完整度计算任务----");
		analysisPersonnelDataScoreService.figureOutScore();
		logger.info("----结束执行人员信息完整度计算任务----");
	}

}
