package com.paladin.hrms.model.complaint;

import java.util.Date;

import javax.persistence.Id;

import com.paladin.framework.common.BaseModel;

public class ComplaintPositionalTitle extends BaseModel {

    public static final int STATUS_WAITING =1;
    public static final int STATUS_SUCCESS = 2;//审核通过
    public static final int STATUS_FAIL= 3;//审核不通过

	public static final int MODIFY_TYPE_UPDATE = 1;// 内容修改
	public static final int MODIFY_TYPE_DELETE = 2;// 记录删除

	@Id
	private String id;

	private String newPositionalTitleName;

	private Integer newPositionalLevelLevel;

	private Date newPositionalTitleTime;

	private Integer modifyType;

	private String attachmentId;

	private Integer status = ComplaintModel.STATUS_WAITING;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewPositionalTitleName() {
		return newPositionalTitleName;
	}

	public void setNewPositionalTitleName(String newPositionalTitleName) {
		this.newPositionalTitleName = newPositionalTitleName;
	}

	public Integer getNewPositionalLevelLevel() {
		return newPositionalLevelLevel;
	}

	public void setNewPositionalLevelLevel(Integer newPositionalLevelLevel) {
		this.newPositionalLevelLevel = newPositionalLevelLevel;
	}

	public Date getNewPositionalTitleTime() {
		return newPositionalTitleTime;
	}

	public void setNewPositionalTitleTime(Date newPositionalTitleTime) {
		this.newPositionalTitleTime = newPositionalTitleTime;
	}

	public Integer getModifyType() {
		return modifyType;
	}

	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}