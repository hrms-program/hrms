package com.paladin.hrms.service.document;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.DocumentAnnualCheckController;
import com.paladin.hrms.controller.document.pojo.DocumentAnnualCheckVO;
import com.paladin.hrms.mapper.document.DocumentAnnualCheckMapper;
import com.paladin.hrms.model.document.DocumentAnnualCheck;
//年度考核
@Service
public class DocumentAnnualCheckService {

	@Autowired
	private DocumentAnnualCheckMapper annualCheckMapper;
	/**
	 * 条件查询，年度考核列表
	 */
	public List<DocumentAnnualCheckVO> list(Map<String, Object> map) {
		return annualCheckMapper.list(map);
	}
	//新增
	public void saveAnnualCheck(DocumentAnnualCheck annualCheck) {
		annualCheck.setId(UUIDUtil.createUUID());
		annualCheck.setBusiId(annualCheck.getId());
		annualCheck.setCreateAt(DateUtil.getCurrentTime());
		annualCheck.setCreateId(UserSession.getCurrentUserSession().getAccount());
		annualCheck.setUpdateAt(annualCheck.getCreateAt());
		annualCheck.setUpdateId(annualCheck.getCreateId());
		annualCheck.setState(SysContants.ACTIVE);
		annualCheckMapper.insert(annualCheck);
	}
	
	//更新
	public void updateAnnualCheck(DocumentAnnualCheck annualCheck) {
		DocumentAnnualCheck oldEntity = annualCheckMapper.queryAnnualCheckByBusiId(annualCheck.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}
		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		annualCheck.setUpdateAt(DateUtil.getCurrentTime());
		annualCheck.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		annualCheckMapper.updateByPrimaryKeySelective(annualCheck);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(annualCheck.getUpdateAt());
		oldEntity.setUpdateId(annualCheck.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		annualCheckMapper.insert(oldEntity);
	}
	
	//删除
	public void deleteAnnualCheck(DocumentAnnualCheck annualCheck) {

		if(StringUtils.isBlank(annualCheck.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		annualCheckMapper.deleteAnnualCheck	(annualCheck.getBusiId());
	}
	
}
