package com.paladin.hrms.service.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SwitchState;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentWorkingExperienceVO;
import com.paladin.hrms.mapper.document.DocumentWorkingExperienceMapper;
import com.paladin.hrms.model.document.DocumentWorkingExperience;

//工作经历
@Service
public class DocumentWorkingExperienceService{

	@Autowired
	private DocumentWorkingExperienceMapper workingExperienceMapper;
	
	@Autowired
	private DocumentAttachmentService attachmentService;
	/**
	 * 条件查询工作经历列表
	 */
	public List<DocumentWorkingExperienceVO> list(Map<String, Object> map) {
		List<DocumentWorkingExperienceVO> list = new ArrayList<DocumentWorkingExperienceVO>();
		list = workingExperienceMapper.list(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(DocumentWorkingExperienceVO workingExperience:list){
				workingExperience.setAttachmentList(attachmentService.selectAttachListByRemarks(workingExperience.getBusiId()));
			}
		}
		return list;
	}
	
	/**
	 * 批量-填充对应字段的name值
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<DocumentWorkingExperience> enhanceAllProperties(
			List<DocumentWorkingExperience> list) {

		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		for (DocumentWorkingExperience workingExperience : list) {
			enhanceAllProperties(workingExperience);
		}
		return list;
	}

	/**
	 * 填充对应字段的name值
	 * @param workingExperience
	 */
	private DocumentWorkingExperience enhanceAllProperties(DocumentWorkingExperience workingExperience) {
		if(StringUtils.isNotBlank(workingExperience.getIsLeader())){//是否领导职务
			/*workingExperience.setIsLeaderName(SwitchState.getMsg(workingExperience.getIsLeader()));*/
		}
		if(StringUtils.isNotBlank(workingExperience.getIsMember())){//是否班子成员
			/*workingExperience.setIsMemberName(SwitchState.getMsg(workingExperience.getIsMember()));*/
		}
		if(StringUtils.isNotBlank(workingExperience.getIsReserveCadres())){//是否后备干部
			workingExperience.setIsReserveCadres(SwitchState.getMsg(workingExperience.getIsReserveCadres()));
		}
		return workingExperience;
	}

	/**
	 * 按idnumber,查询工作经历列表
	 */
	public List<DocumentWorkingExperienceVO> queryListByIdnumber(String idnumber) {
		if(StringUtils.isBlank(idnumber)){
			return null;
		}
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("relationId", idnumber);
		params.put("state", SysContants.ACTIVE);
		return this.list(params);
	}

    //新增
	public void saveWorkingExperience(DocumentWorkingExperience workingExperience) {

		workingExperience.setId(UUIDUtil.createUUID());
		workingExperience.setBusiId(workingExperience.getId());
		workingExperience.setCreateAt(DateUtil.getCurrentTime());
		workingExperience.setCreateId(UserSession.getCurrentUserSession().getAccount());
		workingExperience.setUpdateAt(DateUtil.getCurrentTime());
		workingExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		workingExperience.setState(SysContants.ACTIVE);
		workingExperienceMapper.insert(workingExperience);
		//TODO 如果havefile里有值，将Attachment表里的remark=busiId
				if(StringUtils.isNotBlank(workingExperience.getHaveFile())){
					attachmentService.updateRemarksByIds(workingExperience.getBusiId(),workingExperience.getHaveFile().split(SysContants.EN_COMMA));
				}
	}

    //更新
	public void updateWorkingExperience(DocumentWorkingExperience workingExperience) {

		DocumentWorkingExperience oldEntity = workingExperienceMapper.queryWorkingExperienceByBusiId(workingExperience.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		workingExperience.setUpdateAt(DateUtil.getCurrentTime());
		workingExperience.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		workingExperienceMapper.updateByPrimaryKeySelective(workingExperience);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		workingExperienceMapper.insert(oldEntity);
	}


	public void deleteWorkingExperience(DocumentWorkingExperience workingExperience) {

		if(StringUtils.isBlank(workingExperience.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		workingExperienceMapper.deleteWorkingExperience(workingExperience.getBusiId());
	}
	
	
	
}
