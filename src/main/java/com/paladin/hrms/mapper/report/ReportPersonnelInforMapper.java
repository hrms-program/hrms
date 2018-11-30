package com.paladin.hrms.mapper.report;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;

public interface ReportPersonnelInforMapper extends  CustomMapper<ReportPersonnelInforDTO>{

	Page<ReportPersonnelInforDTO> searchTableList(
			ReportPersonnelInforQuery query);

	
	
	
}
