package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentAnnualCheckVO;
import com.paladin.hrms.model.document.DocumentAnnualCheck;

public interface DocumentAnnualCheckMapper extends CustomMapper<DocumentAnnualCheck>{
	
	List<DocumentAnnualCheckVO> list(Map<String, Object> map);

	DocumentAnnualCheck queryAnnualCheckByBusiId(String busiId);

	void deleteAnnualCheck(String busiId);
}