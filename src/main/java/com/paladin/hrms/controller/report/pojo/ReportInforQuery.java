package com.paladin.hrms.controller.report.pojo;

import com.paladin.framework.common.OffsetPage;

public class ReportInforQuery extends OffsetPage{

	public String area;
	
	public String agencyName;
	
	public String agencyId;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

}
