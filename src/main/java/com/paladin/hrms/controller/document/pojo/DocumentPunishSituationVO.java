package com.paladin.hrms.controller.document.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.paladin.framework.common.UnDeleteModel;

//奖惩情况
public class DocumentPunishSituationVO extends UnDeleteModel implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8555742934928422855L;

	private String id;
	
	private String busiId;

    private String relationId;

    private String project;//处罚项目

    private String punishLevel;//处罚级别

    private String punishType;//处罚类别

    private String punishDate;//处罚时间

    private String punishDepartment;//处罚部门

    private String isCancel;//是否撤销
    @Transient
    private String isCancelName;

    private String money;//处罚金额

    private String reason;//处罚原因

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

    public String getPunishLevel() {
		return punishLevel;
	}

	public void setPunishLevel(String punishLevel) {
		this.punishLevel = punishLevel;
	}

	public String getPunishType() {
		return punishType;
	}

	public void setPunishType(String punishType) {
		this.punishType = punishType;
	}

	public String getPunishDate() {
		return punishDate;
	}

	public void setPunishDate(String punishDate) {
		this.punishDate = punishDate;
	}

	public String getPunishDepartment() {
		return punishDepartment;
	}

	public void setPunishDepartment(String punishDepartment) {
		this.punishDepartment = punishDepartment;
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

	public String getIsCancelName() {
		return isCancelName;
	}

	public void setIsCancelName(String isCancelName) {
		this.isCancelName = isCancelName;
	}  
    
}
