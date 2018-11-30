package com.paladin.hrms.model.complaint;
/**   
 * @author 黄伟华
 * @version 2018年11月2日 下午4:11:23 
 */
public class ComplaintAgencyChangeDTO{
    private String id;
    private String personnelId;
    private String name;
    private String sex;
    private String agencyName;
    private Integer agencyId;
    private String targetAgencyName;
    private Integer targetAgencyId;
    private String identificationNo;
    private String remarks;
    private String result;
    
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
 
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getAgencyName()
    {
        return agencyName;
    }
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }
    public String getTargetAgencyName()
    {
        return targetAgencyName;
    }
    public void setTargetAgencyName(String targetAgencyName)
    {
        this.targetAgencyName = targetAgencyName;
    }
    public String getIdentificationNo()
    {
        return identificationNo;
    }
    public void setIdentificationNo(String identificationNo)
    {
        this.identificationNo = identificationNo;
    }
    public String getResult()
    {
        return result;
    }
    public void setResult(String result)
    {
        this.result = result;
    }
    public String getRemarks()
    {
        return remarks;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
    public Integer getAgencyId()
    {
        return agencyId;
    }
    public void setAgencyId(Integer agencyId)
    {
        this.agencyId = agencyId;
    }
    public Integer getTargetAgencyId()
    {
        return targetAgencyId;
    }
    public void setTargetAgencyId(Integer targetAgencyId)
    {
        this.targetAgencyId = targetAgencyId;
    }
}
