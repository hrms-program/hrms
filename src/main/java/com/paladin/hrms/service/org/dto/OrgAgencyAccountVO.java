package com.paladin.hrms.service.org.dto;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/14 9:29
 */
public class OrgAgencyAccountVO {

    private String id;

    private String code;

    private String name;

    private String account;

    private Integer state;

    private  Integer times;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
