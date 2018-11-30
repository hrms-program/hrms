package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentInnerpartyPostVO;
import com.paladin.hrms.model.document.DocumentInnerpartyPost;

public interface DocumentInnerpartyPostMapper extends CustomMapper<DocumentInnerpartyPost>{

	List<DocumentInnerpartyPostVO> list(Map<String, Object> map);

	void saveCurrentInnerpartyPost(DocumentInnerpartyPost currentInnerpartyPost);

	DocumentInnerpartyPost queryInnerpartyPostByBusiId(String busiId);

	void deleteInnerpartyPost(String busiId);
}