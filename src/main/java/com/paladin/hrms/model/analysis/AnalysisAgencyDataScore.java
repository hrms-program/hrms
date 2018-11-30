package com.paladin.hrms.model.analysis;

import java.util.Date;
import javax.persistence.Id;

public class AnalysisAgencyDataScore {

	// 
	@Id
	private String agencyId;

	// 基础得分
	private Integer baseScore;

	// 工作信息评分
	private Integer jobScore;

	// 执业信息评分
	private Integer practiceScore;

	// 教育背景评分
	private Integer educationScore;

	// 工作经历评分
	private Integer workExperienceScore;

	// 职称信息评分
	private Integer positionalInfoScore;

	// 科教信息评分
	private Integer scienceEducationScore;

	// 奖励评分
	private Integer rewardInfoScore;

	// 培训评分
	private Integer cultivateScore;
	
	// 年度考核
	private Integer yearAssessScore;
	
	// 总分
	private Double sumScore;

	// 更新时间
	private Date updateTime;

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}

	public Integer getJobScore() {
		return jobScore;
	}

	public void setJobScore(Integer jobScore) {
		this.jobScore = jobScore;
	}

	public Integer getPracticeScore() {
		return practiceScore;
	}

	public void setPracticeScore(Integer practiceScore) {
		this.practiceScore = practiceScore;
	}

	public Integer getEducationScore() {
		return educationScore;
	}

	public void setEducationScore(Integer educationScore) {
		this.educationScore = educationScore;
	}

	public Integer getWorkExperienceScore() {
		return workExperienceScore;
	}

	public void setWorkExperienceScore(Integer workExperienceScore) {
		this.workExperienceScore = workExperienceScore;
	}

	public Integer getPositionalInfoScore() {
		return positionalInfoScore;
	}

	public void setPositionalInfoScore(Integer positionalInfoScore) {
		this.positionalInfoScore = positionalInfoScore;
	}

	public Integer getScienceEducationScore() {
		return scienceEducationScore;
	}

	public void setScienceEducationScore(Integer scienceEducationScore) {
		this.scienceEducationScore = scienceEducationScore;
	}

	public Integer getRewardInfoScore() {
		return rewardInfoScore;
	}

	public void setRewardInfoScore(Integer rewardInfoScore) {
		this.rewardInfoScore = rewardInfoScore;
	}

	public Integer getCultivateScore() {
		return cultivateScore;
	}

	public void setCultivateScore(Integer cultivateScore) {
		this.cultivateScore = cultivateScore;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getSumScore() {
		return sumScore;
	}

	public void setSumScore(Double sumScore) {
		this.sumScore = sumScore;
	}

	public Integer getYearAssessScore() {
		return yearAssessScore;
	}

	public void setYearAssessScore(Integer yearAssessScore) {
		this.yearAssessScore = yearAssessScore;
	}

}