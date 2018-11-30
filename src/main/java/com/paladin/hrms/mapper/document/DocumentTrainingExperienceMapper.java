package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentTrainingExperienceVO;
import com.paladin.hrms.model.document.DocumentTrainingExperience;

public interface DocumentTrainingExperienceMapper extends CustomMapper<DocumentTrainingExperience>{
	
	List<DocumentTrainingExperienceVO> list(Map<String, Object> map);

	DocumentTrainingExperience queryTrainingExperienceByBusiId(String busiId);

	void deleteTrainingExperience(String busiId);
}