package com.paladin.hrms.controller.org.pojo;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author 黄伟华
 * @version 2018年11月6日 下午1:45:25 
 */
public class OrgAgencyManagerQuery extends OffsetPage{
    
    private String agencyName;
    
    private String agencyCode;
    
    private String agencyId;
    
    private Integer state;

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public String getAgencyCode()
    {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode)
    {
        this.agencyCode = agencyCode;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
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
