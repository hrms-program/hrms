package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.model.document.DocumentAttachment;

public interface DocumentAttachmentMapper extends CustomMapper<DocumentAttachment>{

	List<DocumentAttachment> selectAttachListByIds(List<String> attachIds);

	void updateRemarksByIds(Map<String, Object> params);

	List<DocumentAttachment> selectAttachListByRemarks(String remarks);

}
