package com.paladin.hrms.mapper.report;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.report.ReportPersonnelInfor;

public interface ReportInforMapper extends CustomMapper<ReportInforDTO>{

	Page<ReportInforDTO> searchTableList(@Param("query")ReportInforQuery query,@Param("permission") DataPermissionCondition permission);

	Page<ReportInforDTO> searchOrgList(@Param("query")ReportInforQuery query,@Param("permission") DataPermissionCondition permission);

	void insertReportPersonnelInfor(ReportPersonnelInfor dto);

	ReportPersonnelInfor findOldEntity(String personnelId);

	void removeReportPersonnelInfor(String personnelId);

	
	

}
