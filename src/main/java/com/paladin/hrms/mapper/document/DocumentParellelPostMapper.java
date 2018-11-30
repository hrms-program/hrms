package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.model.document.DocumentParellelPost;

public interface DocumentParellelPostMapper extends CustomMapper<DocumentParellelPost>{

	DocumentParellelPost queryDocumentParellelPostByBusiId(String busiId);

	List<DocumentParellelPost> list(Map<String, Object> params);

	void deleteDocumentParellelPost(String busiId);

}
