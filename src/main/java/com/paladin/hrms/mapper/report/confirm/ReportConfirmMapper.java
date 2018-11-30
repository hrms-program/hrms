package com.paladin.hrms.mapper.report.confirm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.controller.report.confirm.pojo.ReportConfirmVO;
import com.paladin.hrms.model.report.ReportConfirmOrg;

@Mapper
public interface ReportConfirmMapper {

	List<ReportConfirmVO> findOrgCount(OrgAgencyQuery query);

	ReportConfirmOrg findOldOrgEntity(String id);

	void removeReportConfirmOrg(String id);

	void insertReportConfirmOrg(ReportConfirmOrg dto);

}
