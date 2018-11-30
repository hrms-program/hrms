package com.paladin.hrms.service.complaint.dto;

import com.paladin.framework.common.OffsetPage;
/**   
 * @author 黄伟华
 * @version 2018年11月2日 上午10:46:44 
 */
public class ComplaintAgencyAppealsQueryDTO extends OffsetPage{
    
    private String name;
    
    private String agencyName;
    
    private String targetAgencyName;
    
    private String	agencyId;
    
    private String identificationNo;
    
    private String targetAgencyId;
    
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

	public String getTargetAgencyId() {
		return targetAgencyId;
	}

	public void setTargetAgencyId(String targetAgencyId) {
		this.targetAgencyId = targetAgencyId;
	}

    public String getTargetAgencyName()
    {
        return targetAgencyName;
    }

    public void setTargetAgencyName(String targetAgencyName)
    {
        this.targetAgencyName = targetAgencyName;
    }
    
    
}
