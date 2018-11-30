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
import com.paladin.hrms.controller.document.pojo.DocumentRewardSituationVO;
import com.paladin.hrms.mapper.document.DocumentRewardSituationMapper;
import com.paladin.hrms.model.document.DocumentRewardSituation;

@Service
public class DocumentRewardSituationService{
	@Autowired
	private DocumentRewardSituationMapper rewardSituationMapper;
	
	/**
	 * 按条件查询，奖惩情况列表
	 */
	public List<DocumentRewardSituationVO> list(Map<String, Object> map) {
		
		return rewardSituationMapper.list(map);
	}

	//新增
	public void saveRewardSituation(DocumentRewardSituation rewardSituation) {

		rewardSituation.setId(UUIDUtil.createUUID());
		rewardSituation.setBusiId(rewardSituation.getId());
		rewardSituation.setCreateAt(DateUtil.getCurrentTime());
		rewardSituation.setCreateId(UserSession.getCurrentUserSession().getAccount());
		rewardSituation.setUpdateAt(DateUtil.getCurrentTime());
		rewardSituation.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		rewardSituation.setState(SysContants.ACTIVE);
		rewardSituationMapper.insert(rewardSituation);
	}

	//更新
	public void updateRewardSituation(DocumentRewardSituation rewardSituation) {

		DocumentRewardSituation oldEntity = rewardSituationMapper.queryRewardSituationByBusiId(rewardSituation.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		rewardSituation.setUpdateAt(DateUtil.getCurrentTime());
		rewardSituation.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		rewardSituationMapper.updateByPrimaryKeySelective(rewardSituation);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		rewardSituationMapper.insert(oldEntity);
	}

	public void deleteRewardSituation(DocumentRewardSituation rewardSituation) {

		if(StringUtils.isBlank(rewardSituation.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		rewardSituationMapper.deleteRewardSituation(rewardSituation.getBusiId());
	}
	
}
