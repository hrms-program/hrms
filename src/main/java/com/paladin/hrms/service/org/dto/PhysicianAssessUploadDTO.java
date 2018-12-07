package com.paladin.hrms.service.org.dto;

import java.util.Date;
import com.paladin.framework.excel.read.ReadProperty;
/**   
 * @author 蒋恒
 * @version 2018年11月03日 下午16:25:18 
 */
public class PhysicianAssessUploadDTO {
	// 身份证号
	@ReadProperty(cellIndex = 0)
	private String identificationNo;
	//考核周期（起始年份）
	@ReadProperty(cellIndex = 1)
	private Integer startTime;
	//考核周期（截止年份）
	@ReadProperty(cellIndex = 2)
	private Integer endTime;
	//提前考核时间
	@ReadProperty(cellIndex = 3)
	private Date advanceAssessTime;
	//业务水平测评
	@ReadProperty(cellIndex = 4, enumType="physician-assess-result")
	private Integer businessLevelEvaluation;
	//工作成绩
	@ReadProperty(cellIndex = 5, enumType="physician-assess-result")
	private Integer jobPerformance;
	//执业道德评定
	@ReadProperty(cellIndex = 6, enumType="physician-assess-result")
	private Integer practiceMoralEvaluation;
	//考核结果
	@ReadProperty(cellIndex = 7, enumType="physician-assess-result")
	private Integer assessResult;
	//说明
	@ReadProperty(cellIndex = 8)
	private String description;

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(Integer assessResult) {
		this.assessResult = assessResult;
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
}