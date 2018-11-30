package com.paladin.hrms.controller.org.pojo;

import java.util.List;

public class OrgPersonnelDetailVO {

	private OrgPersonnelBaseVO base;
	private OrgPersonnelJobVO job;
	private OrgPersonnelPracticeVO practice;
	private List<OrgPersonnelEducationVO> educations;
	private List<OrgPersonnelWorkExperienceVO> experience;
	private List<OrgPersonnelPositionalInfoVO> positionalInfo;
	private List<OrgPersonnelRewardInfoVO> rewardInfo;
	private List<OrgPersonnelCultivateVO> cultivate;
	private List<OrgPersonnelPracticeRegistrationVO> registrations;
	private List<OrgPersonnelYearAssessVO> yearassess;
	private List<OrgPersonnelScienceEducationVO>  scienceEducation;
	
	public OrgPersonnelBaseVO getBase() {
		return base;
	}

	public void setBase(OrgPersonnelBaseVO base) {
		this.base = base;
	}

	public List<OrgPersonnelEducationVO> getEducations() {
		return educations;
	}

	public void setEducations(List<OrgPersonnelEducationVO> educations) {
		this.educations = educations;
	}

	public OrgPersonnelJobVO getJob() {
		return job;
	}

	public void setJob(OrgPersonnelJobVO job) {
		this.job = job;
	}
	
    public List<OrgPersonnelWorkExperienceVO> getExperience()
    {
        return experience;
    }

    public void setExperience(List<OrgPersonnelWorkExperienceVO> experience)
    {
        this.experience = experience;
    }

    public List<OrgPersonnelPositionalInfoVO> getPositionalInfo()
    {
        return positionalInfo;
    }

    public void setPositionalInfo(List<OrgPersonnelPositionalInfoVO> positionalInfo)
    {
        this.positionalInfo = positionalInfo;
    }

    public List<OrgPersonnelRewardInfoVO> getRewardInfo()
    {
        return rewardInfo;
    }

    public void setRewardInfo(List<OrgPersonnelRewardInfoVO> rewardInfo)
    {
        this.rewardInfo = rewardInfo;
    }

    public List<OrgPersonnelCultivateVO> getCultivate()
    {
        return cultivate;
    }

    public void setCultivate(List<OrgPersonnelCultivateVO> cultivate)
    {
        this.cultivate = cultivate;
    }

    public List<OrgPersonnelPracticeRegistrationVO> getRegistrations()
    {
        return registrations;
    }

    public void setRegistrations(List<OrgPersonnelPracticeRegistrationVO> registrations)
    {
        this.registrations = registrations;
    }

    public List<OrgPersonnelYearAssessVO> getYearassess()
    {
        return yearassess;
    }

    public void setYearassess(List<OrgPersonnelYearAssessVO> yearassess)
    {
        this.yearassess = yearassess;
    }

    public List<OrgPersonnelScienceEducationVO> getScienceEducation()
    {
        return scienceEducation;
    }

    public void setScienceEducation(List<OrgPersonnelScienceEducationVO> scienceEducation)
    {
        this.scienceEducation = scienceEducation;
    }

	public OrgPersonnelPracticeVO getPractice() {
		return practice;
	}

	public void setPractice(OrgPersonnelPracticeVO practice) {
		this.practice = practice;
	}





}
