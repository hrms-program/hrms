package com.paladin.hrms.controller.org.pojo;

import java.util.Date;

/**   
 * @author 黄伟华
 * @version 2018年10月30日 下午2:51:38 
 */
public class OrgPersonnelPracticeRegistrationDTO{
	
	private String id;
    
    private String personnelId;

    private String certificateCode;

    private String placeName;

    private Integer category;

    private String issuingOrgan;

    private Integer businessType;

    private String qualificationCertificateCode;

    private Integer practiceRange;

    private Integer level;

    private Date firstRegisterTime;

    private String practiceAddress;

    private String registerIdCard;

    private String address;

    private String districtCode;

    private String districtName;

    private Date creationTime;

    private Date lastUpdateTime;
    
    private String attachments;

    private Integer status;

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

    public String getCertificateCode()
    {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode)
    {
        this.certificateCode = certificateCode;
    }

    public String getPlaceName()
    {
        return placeName;
    }

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public Integer getCategory()
    {
        return category;
    }

    public void setCategory(Integer category)
    {
        this.category = category;
    }

    public String getIssuingOrgan()
    {
        return issuingOrgan;
    }

    public void setIssuingOrgan(String issuingOrgan)
    {
        this.issuingOrgan = issuingOrgan;
    }

    public Integer getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(Integer businessType)
    {
        this.businessType = businessType;
    }

    public String getQualificationCertificateCode()
    {
        return qualificationCertificateCode;
    }

    public void setQualificationCertificateCode(String qualificationCertificateCode)
    {
        this.qualificationCertificateCode = qualificationCertificateCode;
    }

    public Integer getPracticeRange()
    {
        return practiceRange;
    }

    public void setPracticeRange(Integer practiceRange)
    {
        this.practiceRange = practiceRange;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Date getFirstRegisterTime()
    {
        return firstRegisterTime;
    }

    public void setFirstRegisterTime(Date firstRegisterTime)
    {
        this.firstRegisterTime = firstRegisterTime;
    }

    public String getPracticeAddress()
    {
        return practiceAddress;
    }

    public void setPracticeAddress(String practiceAddress)
    {
        this.practiceAddress = practiceAddress;
    }

    public String getRegisterIdCard()
    {
        return registerIdCard;
    }

    public void setRegisterIdCard(String registerIdCard)
    {
        this.registerIdCard = registerIdCard;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(String districtCode)
    {
        this.districtCode = districtCode;
    }

    public String getDistrictName()
    {
        return districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    public Date getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(Date creationTime)
    {
        this.creationTime = creationTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAttachments()
    {
        return attachments;
    }

    public void setAttachments(String attachments)
    {
        this.attachments = attachments;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    } 
    
}
