package com.paladin.hrms.mapper.report;

import com.github.pagehelper.Page;
import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportPersonnelInforMapper extends  CustomMapper<ReportPersonnelInforDTO>{

	Page<ReportPersonnelInforDTO> searchTableList(
			ReportPersonnelInforQuery query);


    List<ReportPersonnelInforDTO> searchTableListExport(@Param("query") ReportPersonnelInforQuery query, @Param("permission") DataPermissionUtil.DataPermissionCondition permission);
}
