package com.paladin.hrms.controller.org.pojo;


/**   
 * @author 黄伟华
 * @version 2018年10月30日 下午5:14:09 
 */
public class OrgPersonnelYearAssessVO{
    
    private String id;

    private String personnelId;

    private String year;

    private String wages;

    private String bonus;

    private String otherIncome;

    private Integer assessResult;

    private String technicalPost;

    private String assessIllustrate;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPersonnelId()
    {
        return personnelId;
    }

    public void setPersonnelId(String personnelId)
    {
        this.personnelId = personnelId;
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

    public String getTechnicalPost()
    {
        return technicalPost;
    }

    public void setTechnicalPost(String technicalPost)
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
