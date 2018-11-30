package com.paladin.hrms.service.complaint.dto;
/**   
 * @author 黄伟华
 * @version 2018年11月2日 下午4:11:23 
 */
public class ComplaintAgencyChangeDTO{
	
    private String id;
    private String name;
    private String sex;
    private String identificationNo;
    private String agencyName;
    private String agencyId;
    private String targetAgencyName;
    private String targetAgencyId;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getTargetAgencyName() {
		return targetAgencyName;
	}
	public void setTargetAgencyName(String targetAgencyName) {
		this.targetAgencyName = targetAgencyName;
	}
	public String getTargetAgencyId() {
		return targetAgencyId;
	}
	public void setTargetAgencyId(String targetAgencyId) {
		this.targetAgencyId = targetAgencyId;
	}
	public String getIdentificationNo() {
		return identificationNo;
	}
	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
