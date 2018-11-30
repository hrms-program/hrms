package com.paladin.hrms.service.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.analysis.AnalysisPersonnelClassificationMapper;
import com.paladin.hrms.model.analysis.AnalysisPersonnelClassification;
import com.paladin.hrms.service.analysis.dto.AnalysisPersonnelClassificationQueryDTO;
import com.paladin.hrms.service.analysis.dto.PersonnelClassificationVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;

@Service
public class AnalysisPersonnelClassificationService extends ServiceSupport<AnalysisPersonnelClassification>{

	@Autowired
	private AnalysisPersonnelClassificationMapper personnelClassificationMapper;
	
	public PageResult<PersonnelClassificationVO> findPersonnelClassificationOfAgency(AnalysisPersonnelClassificationQueryDTO query){
		Page<PersonnelClassificationVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelClassificationMapper.countClassificationGroupByAgency(query, permission);
		return new PageResult<>(page);
	}
	
	public PersonnelClassificationVO findPersonnelClassificationOfDistrict(){
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);	
		return personnelClassificationMapper.countClassification( permission);
	}
	
}