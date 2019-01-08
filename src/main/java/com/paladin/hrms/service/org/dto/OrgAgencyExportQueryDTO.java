package com.paladin.hrms.service.org.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.excel.write.WriteProperty;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/12/18 13:49
 */
public class OrgAgencyExportQueryDTO extends OffsetPage {

    @WriteProperty(name = "机构名称",cellIndex = 0,width = 38)
    private String name;

    @WriteProperty(name = "机构代码",cellIndex = 1,width = 15)
    private String code;

    @WriteProperty(name = "机构类别",cellIndex = 2,enumType = "org-type",width = 10)
    private Integer orgType;

    @WriteProperty(name = "机构级别",cellIndex = 3,enumType = "org-level",width = 10)
    private Integer orgLevel;

    @WriteProperty(name = "机构地址",cellIndex = 4,width = 30)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
