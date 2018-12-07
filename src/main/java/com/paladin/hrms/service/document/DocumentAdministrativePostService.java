package com.paladin.hrms.service.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentAdministrativePostVO;
import com.paladin.hrms.mapper.document.DocumentAdministrativePostMapper;
import com.paladin.hrms.model.document.DocumentAdministrativePost;

//行政职务
@Service
public class DocumentAdministrativePostService {
	@Autowired
	private DocumentAdministrativePostMapper administrativePostMapper;
//	@Autowired
//	private DictService dictService;
	
	@Autowired
	private DocumentAttachmentService attachmentService;
	
	/**
	 * 条件查询，历任行政职务列表
	 */
	public List<DocumentAdministrativePostVO> list(Map<String, Object> map) {
		
		List<DocumentAdministrativePostVO> list = new ArrayList<DocumentAdministrativePostVO>();
		list =  administrativePostMapper.list(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(DocumentAdministrativePostVO administrativePost:list){
//				if(StringUtils.isNotBlank(administrativePost.getHaveFile())){//如果haveFile不为空
//					List<String> attachIds=Arrays.asList(administrativePost.getHaveFile().split(SysContants.EN_COMMA));
//					administrativePost.setAttachmentList(attachmentService.selectAttachListByIds(attachIds));
//				}
				administrativePost.setAttachmentList(attachmentService.selectAttachListByRemarks(administrativePost.getBusiId()));
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
	private List<DocumentAdministrativePost> enhanceAllProperties(
			List<DocumentAdministrativePost> list) {

		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		for (DocumentAdministrativePost administrativePost : list) {
			enhanceAllProperties(administrativePost);
		}
		return list;
	}

	/**
	 * 填充对应字段的name值
	 * @param administrativePost
	 */
	private DocumentAdministrativePost enhanceAllProperties(DocumentAdministrativePost administrativePost) {
//		if(StringUtils.isNotBlank(administrativePost.getPositionLevel())){//职务层次
//			administrativePost.setPositionLevelName(dictService.getName(DictStore.position_level, administrativePost.getPositionLevel()));
//		}
//		if(StringUtils.isNotBlank(administrativePost.getManageLevel())){
//			administrativePost.setManageLevelName(dictService.getName(DictStore.management_level, administrativePost.getManageLevel()));
//		}
//		if(StringUtils.isNotBlank(administrativePost.getIsLeader())){//是否领导职务
//			administrativePost.setIsLeaderName(SwitchState.getMsg(administrativePost.getIsLeader()));
//		}
//		if(StringUtils.isNotBlank(administrativePost.getIsMember())){//是否班子成员
//			administrativePost.setIsMemberName(SwitchState.getMsg(administrativePost.getIsMember()));
//		}
//		if(StringUtils.isNotBlank(administrativePost.getIsReserveCadres())){//是否后备干部
//			administrativePost.setIsReserveCadres(SwitchState.getMsg(administrativePost.getIsReserveCadres()));
//		}
		return administrativePost;
	}
	
	public void saveCurrentAdministrativePost(DocumentAdministrativePost currentAdministrativePost) {
		administrativePostMapper.saveCurrentAdministrativePost(currentAdministrativePost);
	}

	@Transactional
	public void saveAdministrativePost(DocumentAdministrativePost administrativePost) {
		administrativePost.setId(UUIDUtil.createUUID());
		administrativePost.setBusiId(administrativePost.getId());
		administrativePost.setCreateAt(DateUtil.getCurrentTime());
		administrativePost.setCreateId(UserSession.getCurrentUserSession().getAccount());
		administrativePost.setUpdateAt(administrativePost.getCreateAt());
		administrativePost.setUpdateId(administrativePost.getCreateId());
		administrativePost.setState(SysContants.ACTIVE);
		administrativePostMapper.saveCurrentAdministrativePost(administrativePost);
		//TODO 如果havefile里有值，将Attachment表里的remark=busiId
		if(StringUtils.isNotBlank(administrativePost.getHaveFile())){
			attachmentService.updateRemarksByIds(administrativePost.getBusiId(),administrativePost.getHaveFile().split(SysContants.EN_COMMA));
		}		
	}

	@Transactional
	public void updateAdministrativePost(DocumentAdministrativePost administrativePost) {
		DocumentAdministrativePost oldEntity = administrativePostMapper.queryAdministrativePostByBusiId(administrativePost.getBusiId());
		if(oldEntity==null){
			throw new BusinessException("数据库中查不到该记录！");
		}
		administrativePost.setUpdateAt(DateUtil.getCurrentTime());
		administrativePost.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		administrativePostMapper.updateByPrimaryKeySelective(administrativePost);   
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(administrativePost.getUpdateAt());
		oldEntity.setUpdateId(administrativePost.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		administrativePostMapper.saveCurrentAdministrativePost(oldEntity);
	}

	@Transactional
	public void deleteAdministrativePost(DocumentAdministrativePost administrativePost) {
		if(StringUtils.isBlank(administrativePost.getBusiId())){
			throw new BusinessException("该条记录不存在或已删除");
		}
		administrativePostMapper.deleteAdministrativePost(administrativePost.getBusiId());
	}
	
}
