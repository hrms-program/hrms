package com.paladin.hrms.model.document;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.paladin.framework.common.UnDeleteModel;

//家庭成员
public class DocumentFamilyMember  implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1177225756358055582L;
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	
	private String busiId;

    private String relationId;

    private String title;//称谓

    private String name;//姓名

    private String politics;//政治面貌

    private String unit;//工作单位

    private String position;//职务

    private String idnumber;//身份证号

    private String isOverseas;//是否具有外国国籍、港澳台居民身份，或者已经取得国（境）外永久居留权、长期居留许可？

    private String overseasDesc;//居留海外，情况说明

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics == null ? null : politics.trim();
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

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public String getIsOverseas() {
        return isOverseas;
    }

    public void setIsOverseas(String isOverseas) {
        this.isOverseas = isOverseas == null ? null : isOverseas.trim();
    }

    public String getOverseasDesc() {
        return overseasDesc;
    }

    public void setOverseasDesc(String overseasDesc) {
        this.overseasDesc = overseasDesc == null ? null : overseasDesc.trim();
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
