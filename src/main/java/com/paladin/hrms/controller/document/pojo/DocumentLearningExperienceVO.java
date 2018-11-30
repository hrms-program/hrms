package com.paladin.hrms.controller.document.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.paladin.framework.common.UnDeleteModel;
import com.paladin.hrms.model.document.DocumentAttachment;

//学历教育简历
public class DocumentLearningExperienceVO extends UnDeleteModel implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7572241652476764742L;

	private String id;
	
	private String busiId;

    private String relationId;

    private String dateBegin;

    private String dateEnd;

    private String school;//学校

    private String educationLevel;//学历
    @Transient
    private String educationLevelName;

    private String educationDegree;//学位
    @Transient
    private String educationDegreeName;

    private String major;//专业

    private String isFullTime;//是否全日制

    private String createAt;

    private String createId;

    private String updateAt;

    private String updateId;

    private String state;

    private String remark;
    
    @Transient
	private List<DocumentAttachment> attachmentList;
    
    @Transient
    private String haveFile;

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

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin == null ? null : dateBegin.trim();
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd == null ? null : dateEnd.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel == null ? null : educationLevel.trim();
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree == null ? null : educationDegree.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getIsFullTime() {
        return isFullTime;
    }

    public void setIsFullTime(String isFullTime) {
        this.isFullTime = isFullTime == null ? null : isFullTime.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public List<DocumentAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<DocumentAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getHaveFile() {
		return haveFile;
	}

	public void setHaveFile(String haveFile) {
		this.haveFile = haveFile;
	}

	public String getEducationLevelName() {
		return educationLevelName;
	}

	public void setEducationLevelName(String educationLevelName) {
		this.educationLevelName = educationLevelName;
	}

	public String getEducationDegreeName() {
		return educationDegreeName;
	}

	public void setEducationDegreeName(String educationDegreeName) {
		this.educationDegreeName = educationDegreeName;
	}
	
}
