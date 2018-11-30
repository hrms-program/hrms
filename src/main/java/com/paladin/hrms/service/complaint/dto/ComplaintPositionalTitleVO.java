package com.paladin.hrms.service.complaint.dto;

import java.util.Date;
import java.util.List;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.container.AttachmentContainer;

/**   
 * @author 黄伟华
 * @version 2018年10月22日 下午1:20:01 
 */
public class ComplaintPositionalTitleVO{
    
    private String id;
    
    private String personnelId;
    
    private String identificationNo;
    
    private Integer sex;
    
    private String name;
    
    private String agencyName;
    
    private String positionalTitleName;
    
    private Integer positionalTitleType;
    
    private Integer positionalLevelLevel;
    
    private Date positionalTitleTime;
    
    private Integer modifyType;
    
    private String newPositionalTitleName;
    
    private Integer newPositionalLevelLevel;
    
    private Date newPositionalTitleTime;
    
    private Integer status;
    
    private String remarks;
    
    private String attachmentId;
    
    private List<SysAttachment> attachmentFiles;
    
 // 获取附件文件
    public List<SysAttachment> getAttachmentFiles() {
        if (attachmentFiles == null && attachmentId != null && attachmentId.length() != 0) {
            return AttachmentContainer.getAttachments(attachmentId.split(","));
        }
        return attachmentFiles;
    }

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

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getPositionalTitleName() {
		return positionalTitleName;
	}

	public void setPositionalTitleName(String positionalTitleName) {
		this.positionalTitleName = positionalTitleName;
	}

	public Integer getPositionalTitleType() {
		return positionalTitleType;
	}

	public void setPositionalTitleType(Integer positionalTitleType) {
		this.positionalTitleType = positionalTitleType;
	}

	public Integer getPositionalLevelLevel() {
		return positionalLevelLevel;
	}

	public void setPositionalLevelLevel(Integer positionalLevelLevel) {
		this.positionalLevelLevel = positionalLevelLevel;
	}

	public Date getPositionalTitleTime() {
		return positionalTitleTime;
	}

	public void setPositionalTitleTime(Date positionalTitleTime) {
		this.positionalTitleTime = positionalTitleTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getModifyType() {
		return modifyType;
	}

	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}
    
    
}
