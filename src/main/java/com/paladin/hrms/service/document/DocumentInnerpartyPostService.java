package com.paladin.hrms.service.document;

import java.util.ArrayList;
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
import com.paladin.hrms.controller.document.pojo.DocumentInnerpartyPostVO;
import com.paladin.hrms.mapper.document.DocumentInnerpartyPostMapper;
import com.paladin.hrms.model.document.DocumentInnerpartyPost;

//党内职务
@Service
public class DocumentInnerpartyPostService{
	@Autowired
	private DocumentInnerpartyPostMapper innerpartyPostMapper;
//	@Autowired
//	private DictService dictService;
	@Autowired
	private DocumentAttachmentService attachmentService;
	
	/**
	 * 条件查询，党内职务列表
	 */
	public List<DocumentInnerpartyPostVO> list(Map<String, Object> map) {
		List<DocumentInnerpartyPostVO> list = new ArrayList<DocumentInnerpartyPostVO>();
		list = innerpartyPostMapper.list(map);
//		if(CollectionUtils.isNotEmpty(list)){
//			for(DocumentInnerpartyPost innerpartyPost:list){
//				innerpartyPost.setAttachmentList(attachmentService.selectAttachListByRemarks(innerpartyPost.getBusiId()));
//			}
//		}
		return list;
	}

	/**
	 * 批量-填充对应字段的name值
	 * @param list
	 * @return
	 */
	private List<DocumentInnerpartyPost> enhanceAllProperties(
			List<DocumentInnerpartyPost> list) {

		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		for (DocumentInnerpartyPost innerpartyPost : list) {
			enhanceAllProperties(innerpartyPost);
		}
		return list;
	}

	/**
	 * 填充对应字段的name值
	 * @param innerpartyPost
	 */
	private DocumentInnerpartyPost enhanceAllProperties(DocumentInnerpartyPost innerpartyPost) {
//		if(StringUtils.isNotBlank(innerpartyPost.getPositionLevel())){//职务层次
//			innerpartyPost.setPositionLevelName(dictService.getName(DictStore.position_level, innerpartyPost.getPositionLevel()));
//		}
//		if(StringUtils.isNotBlank(innerpartyPost.getManageLevel())){
//			innerpartyPost.setManageLevelName(dictService.getName(DictStore.management_level, innerpartyPost.getManageLevel()));
//		}
//		if(StringUtils.isNotBlank(innerpartyPost.getIsLeader())){//是否领导职务
//			innerpartyPost.setIsLeaderName(SwitchState.getMsg(innerpartyPost.getIsLeader()));
//		}
//		if(StringUtils.isNotBlank(innerpartyPost.getIsMember())){//是否班子成员
//			innerpartyPost.setIsMemberName(SwitchState.getMsg(innerpartyPost.getIsMember()));
//		}
//		if(StringUtils.isNotBlank(innerpartyPost.getIsReserveCadres())){//是否后备干部
//			innerpartyPost.setIsReserveCadres(SwitchState.getMsg(innerpartyPost.getIsReserveCadres()));
//		}
		return innerpartyPost;
	}
	
	public void saveCurrentInnerpartyPost(DocumentInnerpartyPost currentInnerpartyPost) {

		innerpartyPostMapper.saveCurrentInnerpartyPost(currentInnerpartyPost);
	}

	//列表新增
	public void saveInnerpartyPost(DocumentInnerpartyPost innerpartyPost) {

		innerpartyPost.setId(UUIDUtil.createUUID());
		innerpartyPost.setBusiId(innerpartyPost.getId());
		innerpartyPost.setCreateAt(DateUtil.getCurrentTime());
		innerpartyPost.setCreateId(UserSession.getCurrentUserSession().getAccount());
		innerpartyPost.setUpdateAt(innerpartyPost.getCreateAt());
		innerpartyPost.setUpdateId(innerpartyPost.getCreateId());
		innerpartyPost.setState(SysContants.ACTIVE);
		innerpartyPostMapper.saveCurrentInnerpartyPost(innerpartyPost);
		//TODO 如果havefile里有值，将Attachment表里的remark=busiId
		if(StringUtils.isNotBlank(innerpartyPost.getHaveFile())){
			attachmentService.updateRemarksByIds(innerpartyPost.getBusiId(),innerpartyPost.getHaveFile().split(SysContants.EN_COMMA));
		}
	}

	public void updateInnerpartyPost(DocumentInnerpartyPost innerpartyPost) {

		DocumentInnerpartyPost oldEntity = innerpartyPostMapper.queryInnerpartyPostByBusiId(innerpartyPost.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}

		//document.setCreateAt(oldEntity.getCreateAt());
		//document.setCreateId(oldEntity.getCreateId());
		innerpartyPost.setUpdateAt(DateUtil.getCurrentTime());
		innerpartyPost.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		innerpartyPostMapper.updateByPrimaryKeySelective(innerpartyPost);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(innerpartyPost.getUpdateAt());
		oldEntity.setUpdateId(innerpartyPost.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		innerpartyPostMapper.saveCurrentInnerpartyPost(oldEntity);
	}

	public void deleteInnerpartyPost(DocumentInnerpartyPost innerpartyPost) {

		if(StringUtils.isBlank(innerpartyPost.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		innerpartyPostMapper.deleteInnerpartyPost(innerpartyPost.getBusiId());
	}
}
