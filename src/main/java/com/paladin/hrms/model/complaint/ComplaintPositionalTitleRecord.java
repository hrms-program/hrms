package com.paladin.hrms.model.complaint;

import java.util.Date;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class ComplaintPositionalTitleRecord extends BaseModel {

	@Id
	private String id;

	private String personnelId;

	private String oldPositionalTitleName;

	private Integer oldPositionalLevelLevel;

	private String oldPositionalTitleTime;

	private String oldAttachmentId;

	private Integer modifyType;

	private String applyTime;

    private String newPositionalTitleName;
    
    private Integer newPositionalLevelLevel;
    
    private Date newPositionalTitleTime;

	private String newAttachmentId;

	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getOldPositionalTitleName() {
		return oldPositionalTitleName;
	}

	public void setOldPositionalTitleName(String oldPositionalTitleName) {
		this.oldPositionalTitleName = oldPositionalTitleName;
	}

	public String getOldAttachmentId() {
		return oldAttachmentId;
	}

	public void setOldAttachmentId(String oldAttachmentId) {
		this.oldAttachmentId = oldAttachmentId;
	}

	public Integer getModifyType() {
		return modifyType;
	}

	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}

	public String getOldPositionalTitleTime()
    {
        return oldPositionalTitleTime;
    }

    public void setOldPositionalTitleTime(String oldPositionalTitleTime)
    {
        this.oldPositionalTitleTime = oldPositionalTitleTime;
    }

    public String getApplyTime()
    {
        return applyTime;
    }

    public void setApplyTime(String applyTime)
    {
        this.applyTime = applyTime;
    }

    public String getNewPositionalTitleName()
    {
        return newPositionalTitleName;
    }

    public void setNewPositionalTitleName(String newPositionalTitleName)
    {
        this.newPositionalTitleName = newPositionalTitleName;
    }

    public Integer getOldPositionalLevelLevel()
    {
        return oldPositionalLevelLevel;
    }

    public void setOldPositionalLevelLevel(Integer oldPositionalLevelLevel)
    {
        this.oldPositionalLevelLevel = oldPositionalLevelLevel;
    }

    public Integer getNewPositionalLevelLevel()
    {
        return newPositionalLevelLevel;
    }

    public void setNewPositionalLevelLevel(Integer newPositionalLevelLevel)
    {
        this.newPositionalLevelLevel = newPositionalLevelLevel;
    }

    public Date getNewPositionalTitleTime()
    {
        return newPositionalTitleTime;
    }

    public void setNewPositionalTitleTime(Date newPositionalTitleTime)
    {
        this.newPositionalTitleTime = newPositionalTitleTime;
    }

    public String getNewAttachmentId() {
		return newAttachmentId;
	}

	public void setNewAttachmentId(String newAttachmentId) {
		this.newAttachmentId = newAttachmentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}