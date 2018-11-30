package com.paladin.hrms.controller.assess.pojo;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/1 16:42
 * @since 1.0.0
 */
public class PhysicianAssessQuery extends OffsetPage {

    private String identificationNo;

    private String name;

    private String agencyName;
    
    private String agencyId;

    @QueryCondition(type = QueryType.LIKE)
    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    @QueryCondition(type = QueryType.LIKE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @QueryCondition(type = QueryType.LIKE)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
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
