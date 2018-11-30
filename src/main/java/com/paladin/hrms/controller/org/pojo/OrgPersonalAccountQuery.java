package com.paladin.hrms.controller.org.pojo;

import com.paladin.framework.common.OffsetPage;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/10 10:02
 */
public class OrgPersonalAccountQuery extends OffsetPage {

    //身份证件号码
    private String identificationNo;
    //姓名
    private String name;

    //性别
    private Integer sex;

    //机构名称
    private String agencyName;

    private String	agencyId;

    private String account;

    private Integer state;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
