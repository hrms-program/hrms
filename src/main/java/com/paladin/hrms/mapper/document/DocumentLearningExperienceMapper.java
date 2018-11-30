package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentLearningExperienceVO;
import com.paladin.hrms.model.document.DocumentLearningExperience;

public interface DocumentLearningExperienceMapper extends CustomMapper<DocumentLearningExperience>{
	
	List<DocumentLearningExperienceVO> list(Map<String, Object> map);

	DocumentLearningExperience queryLearningExperienceByBusiId(String busiId);

	void deleteLearningExperience(String busiId);
}