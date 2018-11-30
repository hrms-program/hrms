package com.paladin.hrms.service.org.dto;

import java.util.List;

import com.paladin.framework.common.OffsetPage;

public class OrgPersonnelQueryDTO extends OffsetPage {
	
	private String name;
	
	private String identificationNo;
	
	private String agencyId;
	
	private Integer sex;
	
	private List<Integer> sexes;
	
	private String dept;
	
	private List<String> depts;
	
	private String technicalQualification;
	
	private List<String> technicalQualifications;
	
	private Integer major;
	
	private List<Integer> majors;
	
	private Integer personnelType;
	
	private List<Integer> personnelTypes;
	
	private Integer politicalAffiliation;
	
	private List<Integer> politicalAffiliations;
	
	private Integer techPost;
	
	private List<Integer> techPosts;
	
	private Integer formation;
	
	private List<Integer> formations;
	
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

	public List<Integer> getSexes() {
		return sexes;
	}

	public void setSexes(List<Integer> sexes) {
		this.sexes = sexes;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<String> getDepts() {
		return depts;
	}

	public void setDepts(List<String> depts) {
		this.depts = depts;
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

	public Integer getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(Integer personnelType) {
		this.personnelType = personnelType;
	}

	public List<Integer> getPersonnelTypes() {
		return personnelTypes;
	}

	public void setPersonnelTypes(List<Integer> personnelTypes) {
		this.personnelTypes = personnelTypes;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getTechnicalQualification() {
		return technicalQualification;
	}

	public void setTechnicalQualification(String technicalQualification) {
		this.technicalQualification = technicalQualification;
	}

	public List<String> getTechnicalQualifications() {
		return technicalQualifications;
	}

	public void setTechnicalQualifications(List<String> technicalQualifications) {
		this.technicalQualifications = technicalQualifications;
	}

	public Integer getPoliticalAffiliation() {
		return politicalAffiliation;
	}

	public void setPoliticalAffiliation(Integer politicalAffiliation) {
		this.politicalAffiliation = politicalAffiliation;
	}

	public List<Integer> getPoliticalAffiliations() {
		return politicalAffiliations;
	}

	public void setPoliticalAffiliations(List<Integer> politicalAffiliations) {
		this.politicalAffiliations = politicalAffiliations;
	}

	public Integer getTechPost() {
		return techPost;
	}

	public void setTechPost(Integer techPost) {
		this.techPost = techPost;
	}

	public List<Integer> getTechPosts() {
		return techPosts;
	}

	public void setTechPosts(List<Integer> techPosts) {
		this.techPosts = techPosts;
	}

	public Integer getFormation() {
		return formation;
	}

	public void setFormation(Integer formation) {
		this.formation = formation;
	}

	public List<Integer> getFormations() {
		return formations;
	}

	public void setFormations(List<Integer> formations) {
		this.formations = formations;
	}
	
	
	
	
}
