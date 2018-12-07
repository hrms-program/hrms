package com.paladin.hrms.model.document;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

//党内职务
public class DocumentInnerpartyPost  implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2921352559413828751L;
	
	@Id
	@GeneratedValue(generator="UUID") 
	private String id;
	
	private String busiId;

    private String relationId;

    private String name;//党内职务名称

    private String unitName;//单位名称

    private String department;//工作部门/科室

    private String positionLevel;//职务层次
    @Transient
    private String positionLevelName;//职务层次

    private String manageLevel;//管理层次
    @Transient
    private String manageLevelName;//管理层次

    private String dateBegin;//任职时间

    private String dateEnd;//结束时间

    private String isLeader;//是否领导职务
    @Transient
    private String isLeaderName;

    private String isMember;//是否班子成员
    @Transient
    private String isMemberName;

    private String isReserveCadres;//是否后备干部
    @Transient
    private String isReserveCadresName;

    private String haveFile;

    private String remark;

    private String createId;

    private String createAt;

    private String updateId;

    private String updateAt;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel == null ? null : positionLevel.trim();
    }

    public String getManageLevel() {
        return manageLevel;
    }

    public void setManageLevel(String manageLevel) {
        this.manageLevel = manageLevel == null ? null : manageLevel.trim();
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

	public List<DocumentAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<DocumentAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getPositionLevelName() {
		return positionLevelName;
	}

	public void setPositionLevelName(String positionLevelName) {
		this.positionLevelName = positionLevelName;
	}

	public String getManageLevelName() {
		return manageLevelName;
	}

	public void setManageLevelName(String manageLevelName) {
		this.manageLevelName = manageLevelName;
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
