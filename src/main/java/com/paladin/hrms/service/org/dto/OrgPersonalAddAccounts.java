package com.paladin.hrms.service.org.dto;

import java.util.List;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/13 13:52
 */
public class OrgPersonalAddAccounts {

    private  Integer successNum;

    private Integer totalNum;

    private List<String> eIds;

    private List<String> eAccounts;

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<String> geteIds() {
        return eIds;
    }

    public void seteIds(List<String> eIds) {
        this.eIds = eIds;
    }

    public List<String> geteAccounts() {
        return eAccounts;
    }

    public void seteAccounts(List<String> eAccounts) {
        this.eAccounts = eAccounts;
    }
}
