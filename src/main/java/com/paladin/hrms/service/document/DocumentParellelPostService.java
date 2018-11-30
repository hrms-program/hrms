package com.paladin.hrms.service.document;

import java.util.Date;
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
import com.paladin.hrms.mapper.document.DocumentParellelPostMapper;
import com.paladin.hrms.model.document.DocumentParellelPost;
@Service
public class DocumentParellelPostService{

	@Autowired
	private DocumentParellelPostMapper parellelPostMapper;

	@Transactional
	public void saveDocumentParellelPost(DocumentParellelPost parellelPost) {
		parellelPost.setId(UUIDUtil.createUUID());
		parellelPost.setBusiId(parellelPost.getId());
		parellelPost.setCreateAt(new Date());
		parellelPost.setCreateId(UserSession.getCurrentUserSession().getAccount());
		parellelPost.setUpdateAt(new Date());
		parellelPost.setUpdateId(parellelPost.getCreateId());
		parellelPost.setState(SysContants.ACTIVE);
		parellelPostMapper.insert(parellelPost);
		
	}

	public void updateDocumentParellelPost(DocumentParellelPost parellelPost) {

		DocumentParellelPost oldEntity = parellelPostMapper.queryDocumentParellelPostByBusiId(parellelPost.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		parellelPost.setUpdateAt(new Date());
		parellelPost.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		parellelPostMapper.updateByPrimaryKeySelective(parellelPost);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(new Date());
		oldEntity.setUpdateId(parellelPost.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		parellelPostMapper.insert(oldEntity);
	}

	public List<DocumentParellelPost> list(Map<String, Object> params) {

		return parellelPostMapper.list(params);
	}

	public void deleteDocumentParellelPost(DocumentParellelPost parellelPost) {

		if(StringUtils.isBlank(parellelPost.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		parellelPostMapper.deleteDocumentParellelPost(parellelPost.getBusiId());
	}

}
