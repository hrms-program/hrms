package com.paladin.hrms.model.org;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgPersonnelPracticeRegistration extends PersonnelModel{
    
    public final static String COLUMN_FIELD_PERSONNEL_ID = "personnelId";

    @Id
    @GeneratedValue(generator = "UUID")
	private String id;
    
	private String  personnelId;
	
	private String  certificateCode;
	
	private String  placeName;
	
	private String  category;
	
	private String  issuingOrgan;
	
	private String  businessType;
	
	private String  qualificationCertificateCode;
	
	private String  practiceRange;
	
	private String  level;
	
	private Date  firstRegisterTime;
	
	private String  practiceAddress;
	
	private String  registerIdCard;
	
	private String  address;
	
	private String  districtCode;
	
	private String  districtName;
	
	private Date  creationTime;
	
	private Date  lastUpdateTime;
	
	private String  attachments;
	
	private Date  dateOfIssue;
	
	private String  technicalTitle;
	
	private Date  currentBusinessApprovalDate;
	
	private String  currentBusinessApprovalOrgans;
	
	private String  workingDepartment;

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

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
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

    public String getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(String businessType)
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

    public String getPracticeRange()
    {
        return practiceRange;
    }

    public void setPracticeRange(String practiceRange)
    {
        this.practiceRange = practiceRange;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
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

    public String getAttachments()
    {
        return attachments;
    }

    public void setAttachments(String attachments)
    {
        this.attachments = attachments;
    }

    public Date getCurrentBusinessApprovalDate()
    {
        return currentBusinessApprovalDate;
    }

    public void setCurrentBusinessApprovalDate(Date currentBusinessApprovalDate)
    {
        this.currentBusinessApprovalDate = currentBusinessApprovalDate;
    }

    public String getCurrentBusinessApprovalOrgans()
    {
        return currentBusinessApprovalOrgans;
    }

    public void setCurrentBusinessApprovalOrgans(String currentBusinessApprovalOrgans)
    {
        this.currentBusinessApprovalOrgans = currentBusinessApprovalOrgans;
    }

    public String getWorkingDepartment()
    {
        return workingDepartment;
    }

    public void setWorkingDepartment(String workingDepartment)
    {
        this.workingDepartment = workingDepartment;
    }

    public Date getFirstRegisterTime()
    {
        return firstRegisterTime;
    }

    public void setFirstRegisterTime(Date firstRegisterTime)
    {
        this.firstRegisterTime = firstRegisterTime;
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

    public Date getDateOfIssue()
    {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue)
    {
        this.dateOfIssue = dateOfIssue;
    }

    public String getTechnicalTitle()
    {
        return technicalTitle;
    }

    public void setTechnicalTitle(String technicalTitle)
    {
        this.technicalTitle = technicalTitle;
    }
	
}