package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.model.document.DocumentPunishSituation;

public interface DocumentPunishSituationMapper extends CustomMapper<DocumentPunishSituation>{
	
	List<DocumentPunishSituation> list(Map<String, Object> map);

	DocumentPunishSituation queryPunishSituationByBusiId(String busiId);

	void deletePunishSituation(String busiId);
}