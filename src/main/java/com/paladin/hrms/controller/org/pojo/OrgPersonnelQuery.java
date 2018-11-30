package com.paladin.hrms.controller.org.pojo;

import java.util.List;

import com.paladin.hrms.controller.QueryParamUtil;
import com.paladin.hrms.service.org.dto.OrgPersonnelQueryDTO;

public class OrgPersonnelQuery extends OrgPersonnelQueryDTO {

	private String sexCodes;
	private String deptCodes;
	private String majorCodes;
	private String personnelTypeCodes;
	private String politicalAffiliationCodes;
	private String technicalQualificationCodes;
	private String techPostCodes;
	private String formationCodes;

	private String agencyName;

	public String getSexCodes() {
		return sexCodes;
	}

	public void setSexCodes(String sexCodes) {
		this.sexCodes = sexCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(sexCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setSex(codes.get(0));
			} else {
				setSexes(codes);
			}
		}
	}

	public String getDeptCodes() {
		return deptCodes;
	}

	public void setDeptCodes(String deptCodes) {
		this.deptCodes = deptCodes;
		List<String> codes = QueryParamUtil.parseMultiSelectConstantKey(deptCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setDept(codes.get(0));
			} else {
				setDepts(codes);
			}
		}
	}

	public String getMajorCodes() {
		return majorCodes;
	}

	public void setMajorCodes(String majorCodes) {
		this.majorCodes = majorCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(majorCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setMajor(codes.get(0));
			} else {
				setMajors(codes);
			}
		}
	}

	public String getPersonnelTypeCodes() {
		return personnelTypeCodes;
	}

	public void setPersonnelTypeCodes(String personnelTypeCodes) {
		this.personnelTypeCodes = personnelTypeCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(personnelTypeCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setPersonnelType(codes.get(0));
			} else {
				setPersonnelTypes(codes);
			}
		}
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getPoliticalAffiliationCodes() {
		return politicalAffiliationCodes;
	}

	public void setPoliticalAffiliationCodes(String politicalAffiliationCodes) {
		this.politicalAffiliationCodes = politicalAffiliationCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(politicalAffiliationCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setSex(codes.get(0));
			} else {
				setSexes(codes);
			}
		}
	}

	public String getTechnicalQualificationCodes() {
		return technicalQualificationCodes;
	}

	public void setTechnicalQualificationCodes(String technicalQualificationCodes) {
		this.technicalQualificationCodes = technicalQualificationCodes;
		List<String> codes = QueryParamUtil.parseMultiSelectConstantKey(technicalQualificationCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setDept(codes.get(0));
			} else {
				setDepts(codes);
			}
		}
	}

	public String getTechPostCodes() {
		return techPostCodes;
	}

	public void setTechPostCodes(String techPostCodes) {
		this.techPostCodes = techPostCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(techPostCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setSex(codes.get(0));
			} else {
				setSexes(codes);
			}
		}
	}

	public String getFormationCodes() {
		return formationCodes;
	}

	public void setFormationCodes(String formationCodes) {
		this.formationCodes = formationCodes;
		List<Integer> codes = QueryParamUtil.parseMultiSelectConstantCode(formationCodes);
		if (codes != null && codes.size() > 0) {
			if (codes.size() == 1) {
				setSex(codes.get(0));
			} else {
				setSexes(codes);
			}
		}
	}

}
