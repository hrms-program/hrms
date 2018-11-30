package com.paladin.hrms.service.org.dto;

import com.paladin.framework.excel.read.ReadProperty;

/**   
 * @author 蒋恒
 * @version 2018年11月03日 下午16:25:18 
 */
public class OrgPersonnelYearAssessUploadDTO{
	//身份证号码
	@ReadProperty(cellIndex = 0)
    private String idcard;
	//年度
	@ReadProperty(cellIndex = 1)
    private String year;
	//当年工资（元）
	@ReadProperty(cellIndex = 2)
    private String wages;
	//当年奖金（元）
	@ReadProperty(cellIndex = 3)
    private String bonus;
	//当年其他收入（元）
	@ReadProperty(cellIndex = 4)
    private String otherIncome;
	//考核结果
	@ReadProperty(cellIndex = 5, enumType="assess-result")
    private Integer assessResult;
	//受聘专业技术职务（岗位）
	@ReadProperty(cellIndex = 6,enumType="tech-post")
    private Integer technicalPost;
	//考核说明
	@ReadProperty(cellIndex = 7)
    private String assessIllustrate;
    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getWages()
    {
        return wages;
    }

    public void setWages(String wages)
    {
        this.wages = wages;
    }

    public String getBonus()
    {
        return bonus;
    }

    public void setBonus(String bonus)
    {
        this.bonus = bonus;
    }

    public String getOtherIncome()
    {
        return otherIncome;
    }

    public void setOtherIncome(String otherIncome)
    {
        this.otherIncome = otherIncome;
    }

    public Integer getAssessResult()
    {
        return assessResult;
    }

    public void setAssessResult(Integer assessResult)
    {
        this.assessResult = assessResult;
    }

    public Integer getTechnicalPost()
    {
        return technicalPost;
    }

    public void setTechnicalPost(Integer technicalPost)
    {
        this.technicalPost = technicalPost;
    }

    public String getAssessIllustrate()
    {
        return assessIllustrate;
    }

    public void setAssessIllustrate(String assessIllustrate)
    {
        this.assessIllustrate = assessIllustrate;
    }
    
}
