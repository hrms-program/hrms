package com.paladin.hrms.controller.document.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DocumentParellelPostVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6375325507661113604L;
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String busiId;

	private String relationId;

	private String firName;

	private String firOrg;

	private String firDept;

	private String firDateBegin;

	private String firDateEnd;

	private String firIssueOrg;

	private String firPositionLevel;

	private String secName;

	private String secOrg;

	private String secDept;

	private String secDateBegin;

	private String secDateEnd;

	private String secIssueOrg;

	private String secPositionLevel;

	private String thiName;

	private String thiOrg;

	private String thiDept;

	private String thiDateBegin;

	private String thiDateEnd;

	private String thiIssueOrg;

	private String thiPositionLevel;

	private String haveFile;

	private String remark;

	private String createId;

	private Date createAt;

	private String updateId;

	private Date updateAt;

	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId == null ? null : busiId.trim();
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId == null ? null : relationId.trim();
	}

	public String getFirName() {
		return firName;
	}

	public void setFirName(String firName) {
		this.firName = firName == null ? null : firName.trim();
	}

	public String getFirOrg() {
		return firOrg;
	}

	public void setFirOrg(String firOrg) {
		this.firOrg = firOrg == null ? null : firOrg.trim();
	}

	public String getFirDept() {
		return firDept;
	}

	public void setFirDept(String firDept) {
		this.firDept = firDept == null ? null : firDept.trim();
	}

	public String getFirDateBegin() {
		return firDateBegin;
	}

	public void setFirDateBegin(String firDateBegin) {
		this.firDateBegin = firDateBegin == null ? null : firDateBegin.trim();
	}

	public String getFirDateEnd() {
		return firDateEnd;
	}

	public void setFirDateEnd(String firDateEnd) {
		this.firDateEnd = firDateEnd == null ? null : firDateEnd.trim();
	}

	public String getFirIssueOrg() {
		return firIssueOrg;
	}

	public void setFirIssueOrg(String firIssueOrg) {
		this.firIssueOrg = firIssueOrg == null ? null : firIssueOrg.trim();
	}

	public String getFirPositionLevel() {
		return firPositionLevel;
	}

	public void setFirPositionLevel(String firPositionLevel) {
		this.firPositionLevel = firPositionLevel == null ? null
				: firPositionLevel.trim();
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName == null ? null : secName.trim();
	}

	public String getSecOrg() {
		return secOrg;
	}

	public void setSecOrg(String secOrg) {
		this.secOrg = secOrg == null ? null : secOrg.trim();
	}

	public String getSecDept() {
		return secDept;
	}

	public void setSecDept(String secDept) {
		this.secDept = secDept == null ? null : secDept.trim();
	}

	public String getSecDateBegin() {
		return secDateBegin;
	}

	public void setSecDateBegin(String secDateBegin) {
		this.secDateBegin = secDateBegin == null ? null : secDateBegin.trim();
	}

	public String getSecDateEnd() {
		return secDateEnd;
	}

	public void setSecDateEnd(String secDateEnd) {
		this.secDateEnd = secDateEnd == null ? null : secDateEnd.trim();
	}

	public String getSecIssueOrg() {
		return secIssueOrg;
	}

	public void setSecIssueOrg(String secIssueOrg) {
		this.secIssueOrg = secIssueOrg == null ? null : secIssueOrg.trim();
	}

	public String getSecPositionLevel() {
		return secPositionLevel;
	}

	public void setSecPositionLevel(String secPositionLevel) {
		this.secPositionLevel = secPositionLevel == null ? null
				: secPositionLevel.trim();
	}

	public String getThiName() {
		return thiName;
	}

	public void setThiName(String thiName) {
		this.thiName = thiName == null ? null : thiName.trim();
	}

	public String getThiOrg() {
		return thiOrg;
	}

	public void setThiOrg(String thiOrg) {
		this.thiOrg = thiOrg == null ? null : thiOrg.trim();
	}

	public String getThiDept() {
		return thiDept;
	}

	public void setThiDept(String thiDept) {
		this.thiDept = thiDept == null ? null : thiDept.trim();
	}

	public String getThiDateBegin() {
		return thiDateBegin;
	}

	public void setThiDateBegin(String thiDateBegin) {
		this.thiDateBegin = thiDateBegin == null ? null : thiDateBegin.trim();
	}

	public String getThiDateEnd() {
		return thiDateEnd;
	}

	public void setThiDateEnd(String thiDateEnd) {
		this.thiDateEnd = thiDateEnd == null ? null : thiDateEnd.trim();
	}

	public String getThiIssueOrg() {
		return thiIssueOrg;
	}

	public void setThiIssueOrg(String thiIssueOrg) {
		this.thiIssueOrg = thiIssueOrg == null ? null : thiIssueOrg.trim();
	}

	public String getThiPositionLevel() {
		return thiPositionLevel;
	}

	public void setThiPositionLevel(String thiPositionLevel) {
		this.thiPositionLevel = thiPositionLevel == null ? null
				: thiPositionLevel.trim();
	}

	public String getHaveFile() {
		return haveFile;
	}

	public void setHaveFile(String haveFile) {
		this.haveFile = haveFile == null ? null : haveFile.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}
}
