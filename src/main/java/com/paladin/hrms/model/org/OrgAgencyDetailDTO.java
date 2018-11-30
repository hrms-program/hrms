package com.paladin.hrms.model.org;

/**   
 * @author 黄伟华
 * @version 2018年10月16日 下午3:09:39 
 */
public class OrgAgencyDetailDTO{
    
    private String agencyId;
    
    private String code;

    private String name;
    
    private String townStreet;

    private String address;

    private Integer cityCode;

    private Integer townCode;

    private Integer districtCode;
    
    private String sname;//单位简称
    
    private String isStateRun;//是否公立单位或医院
    
    private String orgType;//单位类别,取自数据字典org_type:机关，参公事业，全额事业，差额事业（公共单位）...
    
    private String orgLevel;//单位级别，取自数据字典org_level:正科，副科，股级...
    
    private String agencyClassify;

    private String agencyBelong;

    private String hostUnit;

    private String economicType;

    private String agencyCategory;

    private String hospitalGrade;

    private String agencyChange;

    private String agencyFoundingTime;

    private String licenseStartTime;

    private String licenseEndTime;

    private String juridicalPersonName;

    private String phone;

    private String approvalNumber;

    private String approvalAgency;

    private String registeredCapital;

    private String certificateTime;

    private String openBedNumber;

    private Integer isOutHospitalPatient;

    private Integer isEnabled;

    private Integer isSubstrateUnit;

    private Integer isBranchAgency;

    private String postalCode;

    private String unitWebsite;

    private String unitEmail;

    private String transactPeople;

    private String inputPeople;

    private Integer isReportingClinic;

    private String clinicReportingAgency;

    private Integer isVillageClinic;

    private String villageClinicAgency;

    private String agencyCreateTime;

    private String dateObsolete;
    
    private String agencysCreateTime;
    
    private String agencyUpdateTime;
    
    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTownStreet()
    {
        return townStreet;
    }

    public void setTownStreet(String townStreet)
    {
        this.townStreet = townStreet;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getCityCode()
    {
        return cityCode;
    }

    public void setCityCode(Integer cityCode)
    {
        this.cityCode = cityCode;
    }

    public Integer getTownCode()
    {
        return townCode;
    }

    public void setTownCode(Integer townCode)
    {
        this.townCode = townCode;
    }

    public Integer getDistrictCode()
    {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode)
    {
        this.districtCode = districtCode;
    }

    public String getAgencyClassify()
    {
        return agencyClassify;
    }

    public void setAgencyClassify(String agencyClassify)
    {
        this.agencyClassify = agencyClassify;
    }

    public String getAgencyBelong()
    {
        return agencyBelong;
    }

    public void setAgencyBelong(String agencyBelong)
    {
        this.agencyBelong = agencyBelong;
    }

    public String getHostUnit()
    {
        return hostUnit;
    }

    public void setHostUnit(String hostUnit)
    {
        this.hostUnit = hostUnit;
    }

    public String getEconomicType()
    {
        return economicType;
    }

    public void setEconomicType(String economicType)
    {
        this.economicType = economicType;
    }

    public String getAgencyCategory()
    {
        return agencyCategory;
    }

    public void setAgencyCategory(String agencyCategory)
    {
        this.agencyCategory = agencyCategory;
    }

    public String getHospitalGrade()
    {
        return hospitalGrade;
    }

    public void setHospitalGrade(String hospitalGrade)
    {
        this.hospitalGrade = hospitalGrade;
    }

    public String getAgencyChange()
    {
        return agencyChange;
    }

    public void setAgencyChange(String agencyChange)
    {
        this.agencyChange = agencyChange;
    }

    public String getJuridicalPersonName()
    {
        return juridicalPersonName;
    }

    public void setJuridicalPersonName(String juridicalPersonName)
    {
        this.juridicalPersonName = juridicalPersonName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getApprovalNumber()
    {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber)
    {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalAgency()
    {
        return approvalAgency;
    }

    public void setApprovalAgency(String approvalAgency)
    {
        this.approvalAgency = approvalAgency;
    }

    public String getRegisteredCapital()
    {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital)
    {
        this.registeredCapital = registeredCapital;
    }

    public String getOpenBedNumber()
    {
        return openBedNumber;
    }

    public void setOpenBedNumber(String openBedNumber)
    {
        this.openBedNumber = openBedNumber;
    }

    public Integer getIsOutHospitalPatient()
    {
        return isOutHospitalPatient;
    }

    public void setIsOutHospitalPatient(Integer isOutHospitalPatient)
    {
        this.isOutHospitalPatient = isOutHospitalPatient;
    }

    public Integer getIsEnabled()
    {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public Integer getIsSubstrateUnit()
    {
        return isSubstrateUnit;
    }

    public void setIsSubstrateUnit(Integer isSubstrateUnit)
    {
        this.isSubstrateUnit = isSubstrateUnit;
    }

    public Integer getIsBranchAgency()
    {
        return isBranchAgency;
    }

    public void setIsBranchAgency(Integer isBranchAgency)
    {
        this.isBranchAgency = isBranchAgency;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getUnitWebsite()
    {
        return unitWebsite;
    }

    public void setUnitWebsite(String unitWebsite)
    {
        this.unitWebsite = unitWebsite;
    }

    public String getUnitEmail()
    {
        return unitEmail;
    }

    public void setUnitEmail(String unitEmail)
    {
        this.unitEmail = unitEmail;
    }

    public String getTransactPeople()
    {
        return transactPeople;
    }

    public void setTransactPeople(String transactPeople)
    {
        this.transactPeople = transactPeople;
    }

    public String getInputPeople()
    {
        return inputPeople;
    }

    public void setInputPeople(String inputPeople)
    {
        this.inputPeople = inputPeople;
    }

    public Integer getIsReportingClinic()
    {
        return isReportingClinic;
    }

    public void setIsReportingClinic(Integer isReportingClinic)
    {
        this.isReportingClinic = isReportingClinic;
    }

    public String getClinicReportingAgency()
    {
        return clinicReportingAgency;
    }

    public void setClinicReportingAgency(String clinicReportingAgency)
    {
        this.clinicReportingAgency = clinicReportingAgency;
    }

    public Integer getIsVillageClinic()
    {
        return isVillageClinic;
    }

    public void setIsVillageClinic(Integer isVillageClinic)
    {
        this.isVillageClinic = isVillageClinic;
    }

    public String getVillageClinicAgency()
    {
        return villageClinicAgency;
    }

    public void setVillageClinicAgency(String villageClinicAgency)
    {
        this.villageClinicAgency = villageClinicAgency;
    }

    public String getAgencysCreateTime()
    {
        return agencysCreateTime;
    }

    public void setAgencysCreateTime(String agencysCreateTime)
    {
        this.agencysCreateTime = agencysCreateTime;
    }

    public String getAgencyUpdateTime()
    {
        return agencyUpdateTime;
    }

    public void setAgencyUpdateTime(String agencyUpdateTime)
    {
        this.agencyUpdateTime = agencyUpdateTime;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }

    public String getIsStateRun()
    {
        return isStateRun;
    }

    public void setIsStateRun(String isStateRun)
    {
        this.isStateRun = isStateRun;
    }

    public String getOrgType()
    {
        return orgType;
    }

    public void setOrgType(String orgType)
    {
        this.orgType = orgType;
    }

    public String getOrgLevel()
    {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel)
    {
        this.orgLevel = orgLevel;
    }

    public String getAgencyFoundingTime()
    {
        return agencyFoundingTime;
    }

    public void setAgencyFoundingTime(String agencyFoundingTime)
    {
        this.agencyFoundingTime = agencyFoundingTime;
    }

    public String getLicenseStartTime()
    {
        return licenseStartTime;
    }

    public void setLicenseStartTime(String licenseStartTime)
    {
        this.licenseStartTime = licenseStartTime;
    }

    public String getLicenseEndTime()
    {
        return licenseEndTime;
    }

    public void setLicenseEndTime(String licenseEndTime)
    {
        this.licenseEndTime = licenseEndTime;
    }

    public String getCertificateTime()
    {
        return certificateTime;
    }

    public void setCertificateTime(String certificateTime)
    {
        this.certificateTime = certificateTime;
    }

    public String getAgencyCreateTime()
    {
        return agencyCreateTime;
    }

    public void setAgencyCreateTime(String agencyCreateTime)
    {
        this.agencyCreateTime = agencyCreateTime;
    }

    public String getDateObsolete()
    {
        return dateObsolete;
    }

    public void setDateObsolete(String dateObsolete)
    {
        this.dateObsolete = dateObsolete;
    }
    
}
