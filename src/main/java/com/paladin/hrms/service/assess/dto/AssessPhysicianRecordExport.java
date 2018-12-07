package com.paladin.hrms.service.assess.dto;

import com.paladin.framework.excel.write.CellFormat;
import com.paladin.framework.excel.write.WriteProperty;

import java.util.Date;

/**
 * <医师考核导出类>
 *
 * @author Huangguochen
 * @create 2018/11/30 16:02
 */
public class AssessPhysicianRecordExport {
    @WriteProperty(name = "姓名",cellIndex = 0)
    private String name;

    @WriteProperty(name = "证件号码",cellIndex = 1,width = 20)
    private String identificationNo;

    @WriteProperty(name = "性别" ,cellIndex = 2 , enumType = "sex")
    private Integer sex;

    @WriteProperty(name = "所属机构" ,cellIndex = 3,width = 25)
    private String agencyName;

    @WriteProperty(name = "考核周期",cellIndex = 4,width = 15)
    private String assessCycle;

    @WriteProperty(name = "提前考核时间", cellIndex = 5,width = 15)
    @CellFormat(format = DateValueFormator.class)
    private Date advanceAssessTime;

    @WriteProperty(name = "考核结果" , cellIndex = 6 ,enumType = "physician-assess-result")
    private Integer assessResult;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
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

    public String getAssessCycle() {
        return assessCycle;
    }

    public void setAssessCycle(String assessCycle) {
        this.assessCycle = assessCycle;
    }

    public Date getAdvanceAssessTime() {
        return advanceAssessTime;
    }

    public void setAdvanceAssessTime(Date advanceAssessTime) {
        this.advanceAssessTime = advanceAssessTime;
    }

    public Integer getAssessResult() {
        return assessResult;
    }

    public void setAssessResult(Integer assessResult) {
        this.assessResult = assessResult;
    }
}
