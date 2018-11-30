package com.paladin.hrms.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;
import com.paladin.hrms.mapper.report.ReportPersonnelInforMapper;
import com.paladin.hrms.service.org.dto.OrgPersonnelVO;

@Service
public class ReportPersonnelInforService {

	@Autowired
	private ReportPersonnelInforMapper reportPersonnelInforMapper;

	public Page<ReportPersonnelInforDTO> searchTableList(
			ReportPersonnelInforQuery query) {
		Page<OrgPersonnelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		return  reportPersonnelInforMapper.searchTableList(query);
	}
	
	
}
