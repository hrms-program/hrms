package com.paladin.hrms.service.complaint;

import java.util.List;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.exception.SystemException;
import com.paladin.hrms.model.complaint.ComplaintModel;

import tk.mybatis.mapper.entity.Example;

/**
 * <h2>申请-审核业务支持类
 * <h2>
 * <p>
 * 每个人员对应只能存在一个相应申请业务，业务状态分为待审核、审核成功、审核失败。只有待审核时候才能修改申请，只有没有申请或申请审核成功或审核失败之后才能提交新的申请
 * </p>
 * 
 * @author TontoZhou
 * @since 2018年11月14日
 */
public class ComplaintServiceSupport<T extends ComplaintModel> extends ServiceSupport<T> {

	/**
	 * 新增申请
	 * 
	 * @param model
	 * @return
	 */
	public int applyComplaint(T model) {
		String personnelId = model.getPersonnelId();
		if (personnelId == null || personnelId.length() == 0) {
			throw new BusinessException("没有选择申请人");
		}

		model.setStatus(ComplaintModel.STATUS_WAITING);
		saveModelWrap(model);

		List<T> result = searchAll(GeneralCriteriaBuilder.buildAnd(modelType, ComplaintModel.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, personnelId));
		if (result == null || result.size() == 0) {
			return getSqlMapper().insert(model);
		} else {
			if (result.size() == 1) {
				int status = result.get(0).getStatus();
				if (status == ComplaintModel.STATUS_WAITING) {
					throw new BusinessException("该人员已经存在待审核的申请");
				} else {
					return update(model);
				}
			} else {
				throw new SystemException("程序设计异常");
			}
		}
	}

	/**
	 * 新增或修改申请
	 * 
	 * @param model
	 * @return
	 */
	public int applyOrModifyComplaint(T model) {
		String personnelId = model.getPersonnelId();
		if (personnelId == null || personnelId.length() == 0) {
			throw new BusinessException("没有选择申请人");
		}
		model.setStatus(ComplaintModel.STATUS_WAITING);

		if (get(personnelId) != null) {
			return update(model);
		} else {
			return save(model);
		}
	}

	/**
	 * 修改申请
	 * 
	 * @param model
	 * @return
	 */
	public int modifyComplaint(T model) {
		String personnelId = model.getPersonnelId();
		if (personnelId == null || personnelId.length() == 0) {
			throw new BusinessException("没有选择申请人");
		}

		List<T> result = searchAll(GeneralCriteriaBuilder.buildAnd(modelType, ComplaintModel.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, personnelId));
		if (result == null || result.size() == 0) {
			throw new BusinessException("找不到需要修改的申请记录");
		} else {
			model.setStatus(ComplaintModel.STATUS_WAITING);
			saveModelWrap(model);

			if (result.size() == 1) {
				int status = result.get(0).getStatus();
				if (status == ComplaintModel.STATUS_WAITING) {
					return update(model);
				} else {
					throw new BusinessException("无法修改已经审核过的记录");
				}
			} else {
				throw new SystemException("程序设计异常");
			}
		}
	}

	private int complaint(String personnelId, String illustrate, int result) {
		if (personnelId == null || personnelId.length() == 0) {
			throw new BusinessException("没有选择申请人");
		}
		Example example = GeneralCriteriaBuilder.buildAnd(modelType, ComplaintModel.COLUMN_FIELD_PERSONNEL_ID, QueryType.EQUAL, personnelId);
		GeneralCriteriaBuilder.buildAnd(example, ComplaintModel.COLUMN_FIELD_STATUS, QueryType.EQUAL, ComplaintModel.STATUS_WAITING);

		try {
			T model = modelType.newInstance();
			model.setStatus(result);
			model.setIllustrate(illustrate);
			updateModelWrap(model);
			return getSqlMapper().updateByExampleSelective(model, example);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SystemException("程序设计异常", e);
		}

	}

	/**
	 * 审核成功
	 * 
	 * @param personnelId
	 * @param illustrate
	 * @return
	 */
	public int complaintSuccess(String personnelId, String illustrate) {
		return complaint(personnelId, illustrate, ComplaintModel.STATUS_SUCCESS);
	}

	/**
	 * 审核失败
	 * 
	 * @param personnelId
	 * @param illustrate
	 * @return
	 */
	public int complaintFail(String personnelId, String illustrate) {
		return complaint(personnelId, illustrate, ComplaintModel.STATUS_FAIL);
	}

}
