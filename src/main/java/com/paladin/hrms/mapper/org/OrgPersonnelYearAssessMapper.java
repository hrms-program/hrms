package com.paladin.hrms.mapper.org;

import java.util.List;
import java.util.Map;

import com.paladin.hrms.model.org.OrgPersonnelYearAssess;
import com.paladin.framework.mybatis.CustomMapper;

public interface OrgPersonnelYearAssessMapper extends CustomMapper<OrgPersonnelYearAssess>{

	List<OrgPersonnelYearAssess> checkRepeat(Map<String, String> map);

}