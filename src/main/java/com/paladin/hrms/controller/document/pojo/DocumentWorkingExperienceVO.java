package com.paladin.hrms.controller.document.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.paladin.framework.common.UnDeleteModel;
import com.paladin.hrms.model.document.DocumentAttachment;

//工作简历
public class DocumentWorkingExperienceVO extends UnDeleteModel implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6831950584818446687L;

	private String id;
	
	private String busiId;

    private String relationId;//关联身份证号

    private String dateBegin;//开始时间

    private String dateEnd;//结束时间

    private String unit;//单位

    private String position;//职务

    private String isLeader;//是否领导职务
    @Transient
    private String isLeaderName;

    private String isMember;//是否班子成员
    @Transient
    private String isMemberName;

    private String isReserveCadres;//是否储备干部
    @Transient
    private String isReserveCadresName;
    
    private String haveFile;

    private String createAt;
    
    private String createId;

    private String updateAt;
    
    private String updateId;

    private String remark;

    private String state;
    
    @Transient
	private List<DocumentAttachment> attachmentList;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader == null ? null : isLeader.trim();
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember == null ? null : isMember.trim();
    }

    public String getIsReserveCadres() {
        return isReserveCadres;
    }

    public void setIsReserveCadres(String isReserveCadres) {
        this.isReserveCadres = isReserveCadres == null ? null : isReserveCadres.trim();
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

	public String getHaveFile() {
		return haveFile;
	}

	public void setHaveFile(String haveFile) {
		this.haveFile = haveFile;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public List<DocumentAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<DocumentAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getIsLeaderName() {
		return isLeaderName;
	}

	public void setIsLeaderName(String isLeaderName) {
		this.isLeaderName = isLeaderName;
	}

	public String getIsMemberName() {
		return isMemberName;
	}

	public void setIsMemberName(String isMemberName) {
		this.isMemberName = isMemberName;
	}

	public String getIsReserveCadresName() {
		return isReserveCadresName;
	}

	public void setIsReserveCadresName(String isReserveCadresName) {
		this.isReserveCadresName = isReserveCadresName;
	}
    
    
}
