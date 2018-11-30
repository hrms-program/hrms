package com.paladin.hrms.controller.org.pojo;

import com.paladin.framework.common.OffsetPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/11/3 16:43
 * @since 1.0.0
 */
public class OrgPersonnelClaimQuery extends OffsetPage {

    private  static  List<Integer> MAJORS_LIST ;

    private String identificationNo;
    //姓名
    private String name;
    //机构id
    private String agencyId;
    //机构名称
    private String agencyName;
    // 从事专业类别
    private Integer major;

    private Integer sex;

    private List<Integer> majors;

    private Integer majorCode;

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

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public List<Integer> getMajors() {
        return majors;
    }

    public void setMajors(List<Integer> majors) {
        this.majors = majors;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(Integer majorCode) {
        this.majorCode = majorCode;
        switch (majorCode){
            case 1 :
                MAJORS_LIST = new ArrayList<>(4);
                Collections.addAll(MAJORS_LIST, 1, 2, 3, 6);
                setMajors(MAJORS_LIST);
                break;
            case 2 :
                MAJORS_LIST = new ArrayList<>(2);
                Collections.addAll(MAJORS_LIST,7,8);
                setMajors(MAJORS_LIST);
                break;
            case 3 :
                MAJORS_LIST = new ArrayList<>(2);
                Collections.addAll(MAJORS_LIST,4,5);
                setMajors(MAJORS_LIST);
                break;
            case 4 :
                MAJORS_LIST = new ArrayList<>(2);
                Collections.addAll(MAJORS_LIST,11,12);
                setMajors(MAJORS_LIST);
                break;
            case 5 :
                MAJORS_LIST = new ArrayList<>(2);
                Collections.addAll(MAJORS_LIST,9,10);
                setMajors(MAJORS_LIST);
                break;
            case 6 :
                setMajor(15);
                break;
            case 7 :
                setMajor(16);
                break;
            case 8 :
                MAJORS_LIST = new ArrayList<>(2);
                Collections.addAll(MAJORS_LIST,13,14);
                setMajors(MAJORS_LIST);
                break;
            default:
                setMajors(null);
                setMajor(null);
        }
    }
}
