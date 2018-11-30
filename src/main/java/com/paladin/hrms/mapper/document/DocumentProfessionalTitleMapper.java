package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentProfessionalTitleVO;
import com.paladin.hrms.model.document.DocumentProfessionalTitle;

public interface DocumentProfessionalTitleMapper extends CustomMapper<DocumentProfessionalTitle>{
	
	List<DocumentProfessionalTitleVO> list(Map<String, Object> map);

	DocumentProfessionalTitle queryProfessionalTitleByBusiId(String busiId);

	void deleteProfessionalTitle(String busiId);
}