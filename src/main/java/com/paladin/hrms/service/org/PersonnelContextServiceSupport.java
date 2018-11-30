package com.paladin.hrms.service.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.hrms.model.org.PersonnelContextModel;
import com.paladin.hrms.service.complaint.ComplaintPersonnelArchivesCheckService;

/**
 * 用于人员背景信息服务支持类
 * 
 * @author TontoZhou
 * @since 2018年11月28日
 */
public abstract class PersonnelContextServiceSupport<T extends PersonnelContextModel> extends ServiceSupport<T> {

	@Autowired
	protected ComplaintPersonnelArchivesCheckService complaintPersonnelArchivesCheckService;

	/**
	 * 返回人员信息审核类型
	 * 
	 * @return
	 */
	public abstract int getPersonnelArchivesCheckType();

	@Override
	@Transactional
	public int save(T model) {
		model.setStatus(PersonnelContextModel.STATUS_DEFAULT);
		int effect = super.save(model);
		if (effect > 0) {
			return complaintPersonnelArchivesCheckService.addArchivesCheck(model.getId(), model.getPersonnelId(), getPersonnelArchivesCheckType()) ? 1 : 0;
		}
		return 0;
	}

	@Override
	@Transactional
	public int update(T model) {
		model.setStatus(PersonnelContextModel.STATUS_DEFAULT);
		int effect = super.update(model);
		if (effect > 0) {
			return complaintPersonnelArchivesCheckService.addArchivesCheck(model.getId(), model.getPersonnelId(), getPersonnelArchivesCheckType()) ? 1 : 0;
		}
		return 0;
	}

	@Override
	public int updateSelective(T model) {
		throw new RuntimeException("禁止使用该方法");
	}

	public int checkPassHandler(String id, boolean success) {
		try {
			T model = modelType.newInstance();
			model.setId(id);
			model.setStatus(success ? PersonnelContextModel.STATUS_VALID : PersonnelContextModel.STATUS_INVALID);
			return super.updateSelective(model);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("系统代码异常");
		}
	}

}
