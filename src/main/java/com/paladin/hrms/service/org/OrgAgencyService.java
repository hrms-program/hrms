package com.paladin.hrms.service.org;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.mapper.org.OrgAgencyMapper;
import com.paladin.hrms.service.org.dto.OrgAgencyAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.OrgDistrictContainer;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class OrgAgencyService extends ServiceSupport<OrgAgency> {
    @Autowired
    private OrgAgencyMapper orgAgencyMapper;

	public int save(OrgAgency agency) {
		if (!OrgDistrictContainer.checkDistrictData(agency)) {
			throw new BusinessException("没有有效的所属区域");
		}
		return super.save(agency);
	}

	public int update(OrgAgency agency) {
		if (!OrgDistrictContainer.checkDistrictData(agency)) {
			throw new BusinessException("没有有效的所属区域");
		}
		return super.update(agency);
	}

    /**
     * 功能描述: <br>
     * 〈查询机构账号〉
     * @param
     * @param offset
     * @param llimit
     * @return:com.paladin.framework.common.PageResult<com.paladin.hrms.service.org.dto.OrgAgencyAccountVO>
     */
	public PageResult<OrgAgencyAccountVO> findAgencyAccountPage(OffsetPage offsetPage) {
        Page<OrgAgencyAccountVO> page = PageHelper.offsetPage(offsetPage.getOffset(),offsetPage.getLimit());
        DataPermissionCondition permissionCondition = DataPermissionUtil.getPermissionCondition(null, null);
        orgAgencyMapper.findAccount(permissionCondition);
        return new PageResult<>(page);
    }
	
	public PageResult<OrgAgency>findOrgAgency(OrgAgencyQuery query){
	    Page<OrgAgency> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        orgAgencyMapper.findAll(query, permission);
        return new PageResult<>(page);
	}
}