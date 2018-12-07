package com.paladin.hrms.service.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentLearningExperienceVO;
import com.paladin.hrms.mapper.document.DocumentLearningExperienceMapper;
import com.paladin.hrms.model.document.DocumentLearningExperience;
import com.paladin.framework.utils.DateUtil;
//学习经历 
@Service
public class DocumentLearningExperienceService{
	@Autowired
	private DocumentLearningExperienceMapper learningExperienceMapper;
	
	/**
	 * 按条件查询，学习经历列表
	 */
	public List<DocumentLearningExperienceVO> list(Map<String, Object> map) {
		List<DocumentLearningExperienceVO> list = new ArrayList<DocumentLearningExperienceVO>();
		list =  learningExperienceMapper.list(map);
		/*if(CollectionUtils.isNotEmpty(list)){
			for(DocumentLearningExperienceVO learningExperience:list){
				learningExperience.setAttachmentList(attachmentService.selectAttachListByRemarks(learningExperience.getBusiId()));
			}
		}*/
		return list;
	}
	//新增
	public void saveLearningExperience(DocumentLearningExperience learningExperience) {

		learningExperience.setId(UUIDUtil.createUUID());
		learningExperience.setBusiId(learningExperience.getId());
		learningExperience.setCreateAt(DateUtil.getCurrentTime());
		learningExperience.setCreateId(UserSession.getCurrentUserSession().getAccount());
		learningExperience.setUpdateAt(DateUtil.getCurrentTime());
		learningExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		learningExperience.setState(SysContants.ACTIVE);
		learningExperienceMapper.insert(learningExperience);
		/*if(StringUtils.isNotBlank(learningExperience.getHaveFile())){
			attachmentService.updateRemarksByIds(learningExperience.getBusiId(),learningExperience.getHaveFile().split(SysContants.EN_COMMA));
		}*/
	}
	
	public void updateLearningExperience(DocumentLearningExperience learningExperience) {

		DocumentLearningExperience oldEntity = learningExperienceMapper.queryLearningExperienceByBusiId(learningExperience.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		learningExperience.setUpdateAt(com.paladin.framework.utils.DateUtil.getCurrentTime());
		learningExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		learningExperienceMapper.updateByPrimaryKeySelective(learningExperience);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(com.paladin.framework.utils.DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		learningExperienceMapper.insert(oldEntity);
	}
	//删除
	public void deleteLearningExperience(DocumentLearningExperience learningExperience) {

		if(StringUtils.isBlank(learningExperience.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		learningExperienceMapper.deleteLearningExperience(learningExperience.getBusiId());
	}
}
