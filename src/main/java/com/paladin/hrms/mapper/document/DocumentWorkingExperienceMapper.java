package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentWorkingExperienceVO;
import com.paladin.hrms.model.document.DocumentWorkingExperience;

public interface DocumentWorkingExperienceMapper extends CustomMapper<DocumentWorkingExperience>{
	
	List<DocumentWorkingExperienceVO> list(Map<String, Object> map);

	DocumentWorkingExperience queryWorkingExperienceByBusiId(String busiId);

	void deleteWorkingExperience(String busiId);
}