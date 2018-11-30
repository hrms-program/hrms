package com.paladin.hrms.service.document;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentProfessionalTitleVO;
import com.paladin.hrms.mapper.document.DocumentProfessionalTitleMapper;
import com.paladin.hrms.model.document.DocumentProfessionalTitle;

//职称信息
@Service
public class DocumentProfessionalTitleService{
	@Autowired
	private DocumentProfessionalTitleMapper professionalTitleMapper;

	/**
	 * 按条件查询，职称信息列表
	 */
	public List<DocumentProfessionalTitleVO> list(Map<String, Object> map) {
		
		return professionalTitleMapper.list(map);
	}

	//新增
	public void saveProfessionalTitle(DocumentProfessionalTitle professionalTitle) {
		professionalTitle.setId(UUIDUtil.createUUID());
		professionalTitle.setBusiId(professionalTitle.getId());
		professionalTitle.setCreateAt(DateUtil.getCurrentTime());
		professionalTitle.setCreateId(UserSession.getCurrentUserSession().getAccount());
		professionalTitle.setUpdateAt(DateUtil.getCurrentTime());
		professionalTitle.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		professionalTitle.setState(SysContants.ACTIVE);
		professionalTitleMapper.insert(professionalTitle);
		
	}

	//更新
	public void updateProfessionalTitle(DocumentProfessionalTitle professionalTitle) {

		DocumentProfessionalTitle oldEntity = professionalTitleMapper.queryProfessionalTitleByBusiId(professionalTitle.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		professionalTitle.setUpdateAt(DateUtil.getCurrentTime());
		professionalTitle.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		professionalTitleMapper.updateByPrimaryKeySelective(professionalTitle);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		professionalTitleMapper.insert(oldEntity);
	}

	//删除
	public void deleteProfessionalTitle(DocumentProfessionalTitle professionalTitle) {

		if(StringUtils.isBlank(professionalTitle.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		professionalTitleMapper.deleteProfessionalTitle(professionalTitle.getBusiId());
	}
}
