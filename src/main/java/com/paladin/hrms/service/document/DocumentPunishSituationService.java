package com.paladin.hrms.service.document;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.mapper.document.DocumentPunishSituationMapper;
import com.paladin.hrms.model.document.DocumentPunishSituation;

@Service
public class DocumentPunishSituationService {
	@Autowired
	private DocumentPunishSituationMapper punishSituationMapper;
	
	/**
	 * 按条件查询，奖惩情况列表
	 */
	public List<DocumentPunishSituation> list(Map<String, Object> map) {
		
		return enchanceAllProperties(punishSituationMapper.list(map));
	}

	/*
	 * 批量填充字段属性
	 */
	private List<DocumentPunishSituation> enchanceAllProperties(
			List<DocumentPunishSituation> list) {
		if(CollectionUtils.isEmpty(list)){
			return list;
		}
		for (DocumentPunishSituation punishSituation : list) {
			enhanceAllProperties(punishSituation);
		}
		return list;
	}
	/**
	 * 批量填充字段
	 * @param punishSituation
	 */
	private DocumentPunishSituation enhanceAllProperties(DocumentPunishSituation punishSituation) {
		/*if(StringUtils.isNotBlank(punishSituation.getIsCancel())){//是否撤销
			punishSituation.setIsCancelName(SwitchState.getMsg(punishSituation.getIsCancel()));
		}*/
		return punishSituation;
	}
	
	//新增
	public void savePunishSituation(DocumentPunishSituation punishSituation) {

		punishSituation.setId(UUIDUtil.createUUID());
		punishSituation.setBusiId(punishSituation.getId());
		punishSituation.setCreateAt(DateUtil.getCurrentTime());
		punishSituation.setCreateId(UserSession.getCurrentUserSession().getAccount());
		punishSituation.setUpdateAt(DateUtil.getCurrentTime());
		punishSituation.setUpdateId(punishSituation.getCreateId());
		punishSituation.setState(SysContants.ACTIVE);
		punishSituationMapper.insert(punishSituation);
	}

	//更新
	public void updatePunishSituation(DocumentPunishSituation punishSituation) {

		DocumentPunishSituation oldEntity = punishSituationMapper.queryPunishSituationByBusiId(punishSituation.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		punishSituation.setUpdateAt(DateUtil.getCurrentTime());
		punishSituation.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		punishSituationMapper.updateByPrimaryKeySelective(punishSituation);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(punishSituation.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		punishSituationMapper.insert(oldEntity);
	}

	public void deletePunishSituation(DocumentPunishSituation punishSituation) {

		if(StringUtils.isBlank(punishSituation.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		punishSituationMapper.deletePunishSituation(punishSituation.getBusiId());
	}
	
}
