package com.paladin.hrms.controller.org.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

/**   
 * @author 黄伟华
 * @version 2018年10月16日 上午9:21:35 
 */
public class OrgAgencyQuery extends OffsetPage{
    
    private String agencyName;
    
    private String address;
    
    private String agencyId;
    
    private String districtCode;
    
    private String agencyCategory;

    @QueryCondition(type = QueryType.LIKE)
    public String getAddress()
    {
        return address;
    }

    public String getAgencyName()
    {
        return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    public void setAddress(String address)
    {
        this.address = address;
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

    public String getAgencyCategory()
    {
        return agencyCategory;
    }

    public void setAgencyCategory(String agencyCategory)
    {
        this.agencyCategory = agencyCategory;
    }

}
