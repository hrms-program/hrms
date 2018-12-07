package com.paladin.hrms.service.report;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.report.ReportInforMapper;
import com.paladin.hrms.model.report.ReportPersonnelInfor;
import com.paladin.hrms.service.org.OrgPersonnelService;


@Service
public class ReportInforService {
	
	@Autowired
	private ReportInforMapper reportInforMapper; 
	
	@Autowired
	private OrgPersonnelService personnelService;

	public PageResult<ReportInforDTO> searchTableList(ReportInforQuery query) {
	    Page<ReportInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportInforMapper.searchTableList(query, permission);
        return new PageResult<>(page);
	}

	public PageResult<ReportInforDTO> searchOrgList(ReportInforQuery query) {
	    Page<ReportInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportInforMapper.searchOrgList(query, permission);
        return new PageResult<>(page);
	}

	public void reportPersonal(String personnelId) {
		//根据personnelId查询上报记录是否存在
		ReportPersonnelInfor oldEntity = reportInforMapper.findOldEntity(personnelId);
		if(oldEntity!=null){
			reportInforMapper.removeReportPersonnelInfor(personnelId);
		}

		ReportPersonnelInfor dto = new ReportPersonnelInfor();
		dto.setId(UUIDUtil.createUUID());
		dto.setPersonnelId(personnelId);
		dto.setName(personnelService.get(personnelId).getName());
		dto.setReportState(SysContants.ACTIVE);
		dto.setReportTime(new Date());
		dto.setReportUser(UserSession.getCurrentUserSession().getAccount());
		dto.setIsDelete(SysContants.IS_DELETE);
		reportInforMapper.insertReportPersonnelInfor(dto);
	}

	
	
}
