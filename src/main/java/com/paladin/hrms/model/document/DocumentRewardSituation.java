package com.paladin.hrms.model.document;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.paladin.framework.common.UnDeleteModel;

//奖惩情况
public class DocumentRewardSituation implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -712508150308321903L;
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	
	private String busiId;

    private String relationId;

    private String project;//奖励项目

    private String rewardLevel;//奖励级别

    private String rewardType;//获奖类别

    private String rewardDate;//获奖时间

    private String awardDepartment;//授奖部门

    private String isCancel;//是否撤销

    private String money;//获奖金额

    private String reason;//获奖原因

    private String remark;

    private String checkState;//审核状态

    private String haveFile;

    private String createAt;

    private String createId;

    private String updateAt;

    private String updateId;

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(String rewardLevel) {
        this.rewardLevel = rewardLevel == null ? null : rewardLevel.trim();
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType == null ? null : rewardType.trim();
    }

    public String getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }

    public String getAwardDepartment() {
        return awardDepartment;
    }

    public void setAwardDepartment(String awardDepartment) {
        this.awardDepartment = awardDepartment == null ? null : awardDepartment.trim();
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel == null ? null : isCancel.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState == null ? null : checkState.trim();
    }

    public String getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(String haveFile) {
        this.haveFile = haveFile == null ? null : haveFile.trim();
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }  
}
