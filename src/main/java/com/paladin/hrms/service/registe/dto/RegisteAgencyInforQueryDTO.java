package com.paladin.hrms.service.registe.dto;

import com.paladin.framework.common.OffsetPage;

public class RegisteAgencyInforQueryDTO extends OffsetPage {
    
    private String personnelId;

    private String applyTime;
    
    private Integer agencyCheckStatus;
    
    private Integer adminCheckStatus;

    public String getPersonnelId()
    {
        return personnelId;
    }

    public void setPersonnelId(String personnelId)
    {
        this.personnelId = personnelId;
    }

    public String getApplyTime()
    {
        return applyTime;
    }

    public void setApplyTime(String applyTime)
    {
        this.applyTime = applyTime;
    }

    public Integer getAgencyCheckStatus()
    {
        return agencyCheckStatus;
    }

    public void setAgencyCheckStatus(Integer agencyCheckStatus)
    {
        this.agencyCheckStatus = agencyCheckStatus;
    }

    public Integer getAdminCheckStatus()
    {
        return adminCheckStatus;
    }

    public void setAdminCheckStatus(Integer adminCheckStatus)
    {
        this.adminCheckStatus = adminCheckStatus;
    }
}