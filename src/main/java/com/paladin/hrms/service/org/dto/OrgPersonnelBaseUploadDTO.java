package com.paladin.hrms.service.org.dto;

import java.util.Date;
import com.paladin.framework.excel.read.CellConvert;
import com.paladin.framework.excel.read.ReadProperty;
import com.paladin.hrms.service.util.ConvertDeptConstant;
import com.paladin.hrms.service.util.ConvertTechQualificationConstant;

/**
 * 人员基础信息导入DTO
 * @author TontoZhou
 * @since 2018年11月2日
 */
public class OrgPersonnelBaseUploadDTO {
	
	// 人员类型
	@ReadProperty(cellIndex = 0, enumType="personnel-type")
	private Integer personnelType;
	
	// 身份证号码
	@ReadProperty(cellIndex = 1)
	private String identificationNo;
	
	// 身份证类型
	@ReadProperty(cellIndex = 2, enumType="identification-type")
	private Integer identificationType;
	//姓名
	@ReadProperty(cellIndex = 3)
	private String name;
	//性别
	@ReadProperty(cellIndex = 4, enumType="sex")
	private Integer sex;
	//出生日期
	@ReadProperty(cellIndex = 5)
	private Date birthday;
	//民族
	@ReadProperty(cellIndex = 6, enumType="nation")
	private Integer nation;
	//开始工作时间
	@ReadProperty(cellIndex = 7)
	private Date startWorkTime;
	//手机号码
	@ReadProperty(cellIndex = 8)
	private String cellphone;
	//所在科室
	@ReadProperty(cellIndex = 9)
	@CellConvert(convert = ConvertDeptConstant.class)
	private String dept;
	//科室实际名称
	@ReadProperty(cellIndex = 10)
	private String deptName;
	//从事专业类别代码
	@ReadProperty(cellIndex = 11,enumType="major")
	private Integer major;
	//行政/业务管理职务代码
	@ReadProperty(cellIndex = 12,enumType="duty")
	private Integer duty;
	//专业技术职务聘代码
	@ReadProperty(cellIndex = 13,enumType="tech-post")
	private Integer techPost;
	//年内人员流动情况inorout_date
	@ReadProperty(cellIndex = 14,enumType="inorout")
	private Integer inorout;
	//调入/调出时间
	@ReadProperty(cellIndex = 15)
	private Date inoroutDate;
	//编制情况
	@ReadProperty(cellIndex = 16,enumType="formation")
	private Integer formation;
	//政治面貌
	@ReadProperty(cellIndex = 17,enumType="political-affiliation")
	private Integer politicalAffiliation;
	//专业技术职务聘用岗位
	@ReadProperty(cellIndex = 18,enumType="employ-post")
	private Integer employPost;
	//工号
	@ReadProperty(cellIndex = 19)
	private String employeeNo;
	//曾用名
	@ReadProperty(cellIndex = 20)
	private String usedName;
	//国籍
	@ReadProperty(cellIndex = 21,enumType="nationality")
	private Integer nationality;
	//专业技术资格评代码
	@ReadProperty(cellIndex = 22)
	@CellConvert(convert = ConvertTechQualificationConstant.class)
	private String technicalQualification;
	//专业技术资格评定时间
	@ReadProperty(cellIndex = 23)
	private Date gainDate;
	//专业技术职务聘用时间
	@ReadProperty(cellIndex = 24)
	private Date employDate;
	//参加党派时间
	@ReadProperty(cellIndex = 25)
	private Date joinPartyTime;
	//兴趣爱好
	@ReadProperty(cellIndex = 26)
	private String interest;
	public Integer getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(Integer personnelType) {
		this.personnelType = personnelType;
	}
	public String getIdentificationNo() {
		return identificationNo;
	}
	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	public Integer getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(Integer identificationType) {
		this.identificationType = identificationType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getNation() {
		return nation;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	public Date getStartWorkTime() {
		return startWorkTime;
	}
	public void setStartWorkTime(Date startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getMajor() {
		return major;
	}
	public void setMajor(Integer major) {
		this.major = major;
	}
	public Integer getDuty() {
		return duty;
	}
	public void setDuty(Integer duty) {
		this.duty = duty;
	}
	public Integer getTechPost() {
		return techPost;
	}
	public void setTechPost(Integer techPost) {
		this.techPost = techPost;
	}
	public Integer getInorout() {
		return inorout;
	}
	public void setInorout(Integer inorout) {
		this.inorout = inorout;
	}
	public Date getInoroutDate() {
		return inoroutDate;
	}
	public void setInoroutDate(Date inoroutDate) {
		this.inoroutDate = inoroutDate;
	}
	public Integer getFormation() {
		return formation;
	}
	public void setFormation(Integer formation) {
		this.formation = formation;
	}
	public Integer getPoliticalAffiliation() {
		return politicalAffiliation;
	}
	public void setPoliticalAffiliation(Integer politicalAffiliation) {
		this.politicalAffiliation = politicalAffiliation;
	}
	public Integer getEmployPost() {
		return employPost;
	}
	public void setEmployPost(Integer employPost) {
		this.employPost = employPost;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getUsedName() {
		return usedName;
	}
	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	public Integer getNationality() {
		return nationality;
	}
	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}
	public String getTechnicalQualification() {
		return technicalQualification;
	}
	public void setTechnicalQualification(String technicalQualification) {
		this.technicalQualification = technicalQualification;
	}
	public Date getGainDate() {
		return gainDate;
	}
	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public Date getJoinPartyTime() {
		return joinPartyTime;
	}
	public void setJoinPartyTime(Date joinPartyTime) {
		this.joinPartyTime = joinPartyTime;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
}
