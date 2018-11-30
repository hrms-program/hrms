package com.paladin.hrms.controller.complaint.pojo;

import com.paladin.framework.common.OffsetPage;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/10/28 10:27
 * @since 1.0.0
 */
public class IdentificationBindQuery extends OffsetPage {

    private String name;

    private String agencyId;

    private String agencyName;

    private Integer type;

    private Integer result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
