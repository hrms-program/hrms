package com.paladin.hrms.mapper.report;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.model.report.ReportPersonnelInfor;

public interface ReportInforMapper extends CustomMapper<ReportInforDTO>{

	Page<ReportInforDTO> searchTableList(ReportInforQuery query);

	Page<ReportInforDTO> searchOrgList(ReportInforQuery query);

	void insertReportPersonnelInfor(ReportPersonnelInfor dto);

	ReportPersonnelInfor findOldEntity(String personnelId);

	void removeReportPersonnelInfor(String personnelId);

	
	

}
