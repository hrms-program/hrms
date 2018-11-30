package com.paladin.hrms.service.report.confirm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.controller.org.pojo.OrgAgencyVO;
import com.paladin.hrms.controller.org.pojo.OrgPersonnelDetailVO;
import com.paladin.hrms.controller.report.confirm.pojo.ReportConfirmVO;
import com.paladin.hrms.mapper.report.confirm.ReportConfirmMapper;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.report.ReportConfirmOrg;
import com.paladin.hrms.model.report.ReportPersonnelInfor;
import com.paladin.hrms.service.org.OrgAgencyService;

@Service
public class ReportConfirmService {

	@Autowired
	private OrgAgencyService agencyService;
	
	@Autowired
	private ReportConfirmMapper reportConfirmMapper;
	
	public PageResult<ReportConfirmVO> findOrgCount(OrgAgencyQuery query) {
		Page<ReportConfirmVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		reportConfirmMapper.findOrgCount(query);
		return new PageResult<>(page);
	}

	public void reportOrg(String id) {

		//根据personnelId查询上报记录是否存在
		ReportConfirmOrg oldEntity = reportConfirmMapper.findOldOrgEntity(id);
		if(oldEntity!=null){
			reportConfirmMapper.removeReportConfirmOrg(id);
		}

		ReportConfirmOrg dto = new ReportConfirmOrg();
		OrgAgency detail = new OrgAgency();
        detail = agencyService.get(id);
        
		dto.setId(UUIDUtil.createUUID());
		dto.setOrgId(id);
		dto.setName(detail.getName());
		dto.setCode(detail.getCode());
		dto.setConfirmPeople(UserSession.getCurrentUserSession().getAccount());
        dto.setConfirmTime(new Date());
        dto.setConfirmState(SysContants.ACTIVE);
        dto.setState(SysContants.ACTIVE);
		reportConfirmMapper.insertReportConfirmOrg(dto);
	}
}
