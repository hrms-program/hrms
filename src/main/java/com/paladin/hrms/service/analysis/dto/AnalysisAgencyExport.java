package com.paladin.hrms.service.analysis.dto;

import com.paladin.framework.excel.write.WriteProperty;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/12/18 15:23
 */
public class AnalysisAgencyExport {

    @WriteProperty(name = "机构名称",cellIndex = 0,width = 40)
    private String agencyName;

    @WriteProperty(name = "基本信息",cellIndex = 1)
    private Integer baseScore;

    @WriteProperty(name = "工作信息",cellIndex = 2)
    private Integer jobScore;

    @WriteProperty(name = "执业信息",cellIndex = 3)
    private Integer practiceScore;

    @WriteProperty(name = "教育背景",cellIndex = 4)
    private Integer educationScore;

    @WriteProperty(name = "工作背景",cellIndex = 5)
    private Integer workExperienceScore;

    @WriteProperty(name = "职称背景",cellIndex = 6)
    private Integer positionalInfoScore;

    @WriteProperty(name = "科教背景",cellIndex = 7)
    private Integer scienceEducationScore;

    @WriteProperty(name = "奖励背景",cellIndex = 8)
    private Integer rewardInfoScore;

    @WriteProperty(name = "培训背景",cellIndex = 9)
    private Integer cultivateScore;

    @WriteProperty(name = "年度考核",cellIndex = 10)
    private Integer yearAssessScore;

    @WriteProperty(name = "汇总",cellIndex = 11)
    private Double sumScore;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
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

    public Integer getYearAssessScore() {
        return yearAssessScore;
    }

    public void setYearAssessScore(Integer yearAssessScore) {
        this.yearAssessScore = yearAssessScore;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }
}
