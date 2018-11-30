package com.paladin.hrms.service.document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.mapper.document.DocumentAttachmentMapper;
import com.paladin.hrms.model.document.DocumentAttachment;


@Service
public class DocumentAttachmentService {
	
	@Autowired
	private DocumentAttachmentMapper attachmentMapper;

	@Transactional
	public DocumentAttachment saveAttachment(DocumentAttachment attach) {
		attach.setId(UUIDUtil.createUUID());
		attach.setCreatedId(UserSession.getCurrentUserSession().getAccount());
		attach.setCreatedAt(new Date());
		attach.setUpdatedId(attach.getCreatedId());
		attach.setUpdatedAt(attach.getCreatedAt());
		attach.setState(SysContants.ACTIVE);
		attachmentMapper.insert(attach);
		return attach;
	}

	public DocumentAttachment queryAttachmentById(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		return attachmentMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void deleteAttachmentById(String id) {
		if(StringUtils.isBlank(id)){
			throw new BusinessException("附件ID为空");
		}
		DocumentAttachment record=new DocumentAttachment();
		record.setId(id);
		record.setState(SysContants.DELETED);
		attachmentMapper.updateByPrimaryKeySelective(record);
	}

	public List<DocumentAttachment> selectAttachListByIds(List<String> attachIds) {

		return attachmentMapper.selectAttachListByIds(attachIds);
	}

	/**
	 * 按id批量更新相关业务ID
	 */
	public void updateRemarksByIds(String remarks, String[] ids) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("remarks", remarks);
		params.put("ids", ids);
		attachmentMapper.updateRemarksByIds(params);
	}

	/**
	 * 按remarks(关联ID),查询列表
	 */
	public List<DocumentAttachment> selectAttachListByRemarks(String remarks) {
		
		return attachmentMapper.selectAttachListByRemarks(remarks);
	}

}
