package com.paladin.hrms.controller.assess.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/1 17:31
 * @since 1.0.0
 */
public class PhysicianAssessDTO {

    private String id;

    private String identificationNo;

    private String name;

    private Integer sex;

    private String agencyName;

    private String assessCycle;

    private Integer startTime;

    private Integer  endTime;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date advanceAssessTime;

    private Integer businessLevelEvaluation;

    private Integer jobPerformance;

    private Integer practiceMoralEvaluation;

    private Integer  assessResult;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {

        this.identificationNo = identificationNo == null ? null : identificationNo.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAssessCycle() {
        return assessCycle;
    }

    public void setAssessCycle(String assessCycle) {
        this.assessCycle = assessCycle;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {

        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Date getAdvanceAssessTime() {
        return advanceAssessTime;
    }

    public void setAdvanceAssessTime(Date advanceAssessTime) {
        this.advanceAssessTime = advanceAssessTime;
    }

    public Integer getBusinessLevelEvaluation() {
        return businessLevelEvaluation;
    }

    public void setBusinessLevelEvaluation(Integer businessLevelEvaluation) {
        this.businessLevelEvaluation = businessLevelEvaluation;
    }

    public Integer getJobPerformance() {
        return jobPerformance;
    }

    public void setJobPerformance(Integer jobPerformance) {
        this.jobPerformance = jobPerformance;
    }

    public Integer getPracticeMoralEvaluation() {
        return practiceMoralEvaluation;
    }

    public void setPracticeMoralEvaluation(Integer practiceMoralEvaluation) {
        this.practiceMoralEvaluation = practiceMoralEvaluation;
    }

    public Integer getAssessResult() {
        return assessResult;
    }

    public void setAssessResult(Integer assessResult) {
        this.assessResult = assessResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
