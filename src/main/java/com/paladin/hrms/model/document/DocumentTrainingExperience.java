package com.paladin.hrms.model.document;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//培训教育经历
public class DocumentTrainingExperience  implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5031022551635169524L;
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	
	private String busiId;

    private String relationId;

    private String type;//培训类型

    private String unit;//培训单位

    private String address;//培训地点

    private String dateBegin;//开始时间

    private String dateEnd;//结束时间

    private String isGraduation;//是否结业(0未结业1结业）

    private String checkState;//审核情况

    private String haveFile;//是否有文件（0没有，1有）

    private String remark;

    private String createId;

    private String createAt;

    private String updateId;

    private String updateAt;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getIsGraduation() {
        return isGraduation;
    }

    public void setIsGraduation(String isGraduation) {
        this.isGraduation = isGraduation == null ? null : isGraduation.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
