package com.paladin.hrms.controller.report.confirm.pojo;

import com.paladin.framework.excel.write.WriteProperty;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/12/19 11:13
 */
public class AgencyReportInforExport {

    @WriteProperty(name = "机构名称",cellIndex = 0,width = 40)
    public String agencyName;

    @WriteProperty(name = "应上报人数",cellIndex = 1,width = 15)
    public String todo;//应上报人数

    @WriteProperty(name = "已上报合格人数",cellIndex = 2,width = 15)
    public String y;//已上报合格人数

    @WriteProperty(name = "上报不合格人数",cellIndex = 3,width = 15)
    public String n;//已上报不合格人数

    @WriteProperty(name = "未上报人数",cellIndex = 4,width = 15)
    public String un;//未上报人数

    @WriteProperty(name = "机构上报状态",cellIndex = 5,width = 15)
    public String confirmState;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(String confirmState) {
        this.confirmState = confirmState;
    }
}
