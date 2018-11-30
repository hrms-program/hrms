package com.paladin.hrms.service.analysis.dto;

import java.util.Date;

public class AnalysisPersonnelDataScoreVO {

	private String personnelId;
	
	private String personnelName;
	
	private String agencyName;
	
	private String agencyId;
	
	private Integer baseScore;

	private Integer jobScore;

	private Integer practiceScore;

	private Integer educationScore;

	private Integer workExperienceScore;

	private Integer positionalInfoScore;

	private Integer scienceEducationScore;

	private Integer rewardInfoScore;

	private Integer cultivateScore;
	
	private Integer yearAssessScore;
	
	private Integer sumScore;

	private Date updateTime;

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

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

	public Integer getYearAssessScore() {
		return yearAssessScore;
	}

	public void setYearAssessScore(Integer yearAssessScore) {
		this.yearAssessScore = yearAssessScore;
	}

	public Integer getSumScore() {
		return sumScore;
	}

	public void setSumScore(Integer sumScore) {
		this.sumScore = sumScore;
	}
	
}
