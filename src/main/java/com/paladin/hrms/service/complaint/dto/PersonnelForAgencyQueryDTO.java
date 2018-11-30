package com.paladin.hrms.service.complaint.dto;

import com.paladin.framework.common.OffsetPage;
/**   
 * @author 黄伟华
 * @version 2018年11月2日 上午10:46:44 
 */
public class PersonnelForAgencyQueryDTO extends OffsetPage{
    
    private String name;
    
    private String agencyName;
    
    private String	agencyId;
    
    private String identificationNo;
    
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

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
    
    
}
