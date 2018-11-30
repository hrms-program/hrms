package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentAdministrativePostVO;
import com.paladin.hrms.model.document.DocumentAdministrativePost;

public interface DocumentAdministrativePostMapper extends CustomMapper<DocumentAdministrativePost>{
	
	List<DocumentAdministrativePostVO> list(Map<String, Object> map);

	void saveCurrentAdministrativePost(DocumentAdministrativePost currentAdministrativePost);

	DocumentAdministrativePost queryByBusiId(String busiId);

	DocumentAdministrativePost queryAdministrativePostByBusiId(String busiId);

	void deleteAdministrativePost(String busiId);

}