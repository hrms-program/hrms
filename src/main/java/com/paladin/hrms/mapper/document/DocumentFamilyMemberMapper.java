package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentFamilyMemberVO;
import com.paladin.hrms.model.document.DocumentFamilyMember;

public interface DocumentFamilyMemberMapper extends CustomMapper<DocumentFamilyMember>{
	
	List<DocumentFamilyMemberVO> list(Map<String, Object> map);

	DocumentFamilyMember queryFamilyMemberByBusiId(String busiId);

	void deleteFamilyMember(String busiId);
}