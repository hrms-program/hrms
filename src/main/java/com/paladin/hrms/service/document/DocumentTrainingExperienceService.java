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
import com.paladin.hrms.controller.document.pojo.DocumentTrainingExperienceVO;
import com.paladin.hrms.mapper.document.DocumentTrainingExperienceMapper;
import com.paladin.hrms.model.document.DocumentTrainingExperience;

@Service
public class DocumentTrainingExperienceService{
	@Autowired
	private DocumentTrainingExperienceMapper trainingExperienceMapper;
	
	public List<DocumentTrainingExperienceVO> list(Map<String, Object> map) {
		
		return trainingExperienceMapper.list(map);
	}

	//新增
	public void saveTrainingExperience(DocumentTrainingExperience trainingExperience) {

		trainingExperience.setId(UUIDUtil.createUUID());
		trainingExperience.setBusiId(trainingExperience.getId());
		trainingExperience.setCreateAt(DateUtil.getCurrentTime());
		trainingExperience.setCreateId(UserSession.getCurrentUserSession().getAccount());
		trainingExperience.setUpdateAt(DateUtil.getCurrentTime());
		trainingExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		trainingExperience.setState(SysContants.ACTIVE);
		trainingExperienceMapper.insert(trainingExperience);
	}

	//修改
	public void updateTrainingExperience(DocumentTrainingExperience trainingExperience) {

		DocumentTrainingExperience oldEntity = trainingExperienceMapper.queryTrainingExperienceByBusiId(trainingExperience.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		trainingExperience.setUpdateAt(DateUtil.getCurrentTime());
		trainingExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		trainingExperienceMapper.updateByPrimaryKeySelective(trainingExperience);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		trainingExperienceMapper.insert(oldEntity);
	}

	//删除
	public void deleteTrainingExperience(DocumentTrainingExperience trainingExperience) {

		if(StringUtils.isBlank(trainingExperience.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		trainingExperienceMapper.deleteTrainingExperience(trainingExperience.getBusiId());
	}

}
