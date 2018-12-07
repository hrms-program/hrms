package com.paladin.hrms.service.org.dto;

import com.paladin.framework.common.OffsetPage;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/3 16:43
 * @since 1.0.0
 */
public class OrgPersonnelClaimQueryDTO extends OffsetPage {

    private String identificationNo;
    //姓名
    private String name;
    //机构名称
    private String agencyName;
    // 从事专业类别
    private Integer major;

    private Integer sex;

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

}
