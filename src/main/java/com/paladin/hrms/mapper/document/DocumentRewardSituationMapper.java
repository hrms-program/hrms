package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentRewardSituationVO;
import com.paladin.hrms.model.document.DocumentRewardSituation;

public interface DocumentRewardSituationMapper extends CustomMapper<DocumentRewardSituation>{
	
	List<DocumentRewardSituationVO> list(Map<String, Object> map);

	DocumentRewardSituation queryRewardSituationByBusiId(String busiId);

	void deleteRewardSituation(String busiId);
}