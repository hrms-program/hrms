package com.paladin.hrms.service.document;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentFamilyMemberVO;
import com.paladin.hrms.mapper.document.DocumentFamilyMemberMapper;
import com.paladin.hrms.model.document.DocumentFamilyMember;

//家庭成员
@Service
public class DocumentFamilyMemberService{

	@Autowired
	private DocumentFamilyMemberMapper familyMemberMapper;
	
	public List<DocumentFamilyMemberVO> list(Map<String, Object> map) {
		
		return familyMemberMapper.list(map);
	}

	//新增
	@Transactional
	public void saveFamilyMember(DocumentFamilyMember familyMember) {

		familyMember.setId(UUIDUtil.createUUID());
		familyMember.setBusiId(familyMember.getId());
		familyMember.setCreateAt(DateUtil.getCurrentTime());
		familyMember.setCreateId(UserSession.getCurrentUserSession().getAccount());
		familyMember.setUpdateAt(familyMember.getCreateAt());
		familyMember.setUpdateId(familyMember.getCreateId());
		familyMember.setState(SysContants.ACTIVE);
		familyMemberMapper.insert(familyMember);
		
	}

	//更新
	@Transactional
	public void updateFamilyMember(DocumentFamilyMember familyMember) {
		DocumentFamilyMember oldEntity = familyMemberMapper.queryFamilyMemberByBusiId(familyMember.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}
		familyMember.setUpdateAt(DateUtil.getCurrentTime());
		familyMember.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		familyMemberMapper.updateByPrimaryKeySelective(familyMember);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(familyMember.getUpdateAt());
		oldEntity.setUpdateId(familyMember.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		familyMemberMapper.insert(oldEntity);
	}

	//删除
	@Transactional
	public void deleteFamilyMember(DocumentFamilyMember familyMember) {
		if(StringUtils.isBlank(familyMember.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		familyMemberMapper.deleteFamilyMember(familyMember.getBusiId());
	}
	
}
