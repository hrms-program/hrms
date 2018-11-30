package com.paladin.hrms.controller.complaint.pojo;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author 黄伟华
 * @version 2018年11月21日 上午10:12:15 
 */
public class ComplaintPositionalTitleConfirmQuery extends OffsetPage{
    
    private String name;
    
    private String agencyName;

    private String agencyId;
    

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }
}
