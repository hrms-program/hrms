package com.paladin.hrms.model.org;
/**   
 * @author 黄伟华
 * @version 2018年11月6日 下午2:19:31 
 */
public class OrgAgencyManagerVo{
    
    private String id;
    
    private String agencyId;
    
    private String districtCode;
    
    private String agencyName;
    
    private String agencyCode;
    
    private Integer state;
    
    private String userId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public String getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(String districtCode)
    {
        this.districtCode = districtCode;
    }

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

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
