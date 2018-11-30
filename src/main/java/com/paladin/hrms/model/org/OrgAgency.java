package com.paladin.hrms.model.org;

import javax.persistence.Id;

import com.paladin.framework.common.UnDeleteBaseModel;
import com.paladin.hrms.core.DistrictData;

public class OrgAgency extends UnDeleteBaseModel implements DistrictData{

	@Id
	private String id;

	private String code;

	private String name;
	
	private String townStreet;

	private String address;

	private Integer cityCode;

	private Integer townCode;

	private Integer districtCode;
	
	private String sname;	//	单位简称
	
	private String isStateRun;	//	是否公立单位或医院
	
	private String orgType;	//	单位类别,取自数据字典org_type:机关，参公事业，全额事业，差额事业（公共单位）...
	
	private String orgLevel;	//	单位级别，取自数据字典org_level:正科，副科，股级...

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
	
	public String getTownStreet()
    {
        return townStreet;
    }

    public void setTownStreet(String townStreet)
    {
        this.townStreet = townStreet;
    }

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getTownCode() {
		return townCode;
	}

	public void setTownCode(Integer townCode) {
		this.townCode = townCode;
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

	public String getIsStateRun() {
		return isStateRun;
	}

	public void setIsStateRun(String isStateRun) {
		this.isStateRun = isStateRun;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	
}