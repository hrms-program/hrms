package com.paladin.hrms.controller.org.pojo;

import com.paladin.hrms.core.OrgDistrictContainer;
import com.paladin.hrms.core.OrgDistrictContainer.District;

/**
 * @author 黄伟华
 * @version 2018年11月1日 下午2:19:00
 */
public class OrgAgencyVO {

	private String id;

	private String code;

	private String name;

	private String townStreet;

	private String address;

	private Integer districtCode;

	private String sname;// 单位简称

	private Integer isStateRun;// 是否公立单位或医院

	private Integer orgType;// 单位类别,取自数据字典org_type:机关，参公事业，全额事业，差额事业（公共单位）...

	private Integer orgLevel;// 单位级别，取自数据字典org_level:正科，副科，股级...
    
	private String creditCode;
    
    private String technicalCatalogue;
    
    private String PracticeScore;	
	

	public String getDistrictName() {
		if (districtCode != null) {
			District district = OrgDistrictContainer.getDistrict(districtCode);
			if (district != null) {
				return district.toString();
			}
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTownStreet() {
		return townStreet;
	}

	public void setTownStreet(String townStreet) {
		this.townStreet = townStreet;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getIsStateRun() {
		return isStateRun;
	}

	public void setIsStateRun(Integer isStateRun) {
		this.isStateRun = isStateRun;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

    public String getCreditCode()
    {
        return creditCode;
    }

    public void setCreditCode(String creditCode)
    {
        this.creditCode = creditCode;
    }

    public String getTechnicalCatalogue()
    {
        return technicalCatalogue;
    }

    public void setTechnicalCatalogue(String technicalCatalogue)
    {
        this.technicalCatalogue = technicalCatalogue;
    }

    public String getPracticeScore()
    {
        return PracticeScore;
    }

    public void setPracticeScore(String practiceScore)
    {
        PracticeScore = practiceScore;
    }

}
