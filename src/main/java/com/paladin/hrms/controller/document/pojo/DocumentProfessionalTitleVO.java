package com.paladin.hrms.controller.document.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.paladin.framework.common.UnDeleteModel;

//专业技术职称简历
public class DocumentProfessionalTitleVO  extends UnDeleteModel implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -297296805512407580L;

	private String id;
	
	private String busiId;

    private String relationId;

    private String name;//职称名称
    @Transient
    private String nameName;//职称名称
    
    private String nameDesc;//其他（职称名称）

    private String type;//职称类型
    @Transient
    private String typeName;//职称类型

    private String level;//职称级别
    @Transient
    private String levelName;

    private String gainDate;//获得时间

    private String isHired;//是否聘用

    private String hireDate;//聘用时间

    private String checkState;//审核状态

    private String createAt;

    private String createId;

    private String updateAt;

    private String updateId;

    private String state;

    private String remark;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getGainDate() {
        return gainDate;
    }

    public void setGainDate(String gainDate) {
        this.gainDate = gainDate;
    }

    public String getIsHired() {
        return isHired;
    }

    public void setIsHired(String isHired) {
        this.isHired = isHired == null ? null : isHired.trim();
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState == null ? null : checkState.trim();
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

	public String getNameName() {
		return nameName;
	}

	public void setNameName(String nameName) {
		this.nameName = nameName;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}    
    
}
