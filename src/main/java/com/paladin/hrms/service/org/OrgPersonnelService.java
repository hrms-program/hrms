package com.paladin.hrms.service.org;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;

import com.paladin.hrms.controller.org.pojo.OrgPersonalAccountQuery;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.core.OrgDistrictContainer;
import com.paladin.hrms.core.OrgDistrictContainer.District;
import com.paladin.hrms.model.syst.SysUser;
import com.paladin.hrms.service.assess.AssessPhysicianRecordService;
import com.paladin.hrms.service.org.dto.*;
import com.paladin.hrms.service.syst.SysUserService;
import java.lang.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.org.OrgPersonnelMapper;
import com.paladin.hrms.mapper.org.OrgPersonnelYearAssessMapper;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.model.org.OrgPersonnelClaimRecord;
import com.paladin.hrms.model.org.OrgPersonnelJob;
import com.paladin.hrms.model.org.OrgPersonnelPractice;
import com.paladin.hrms.model.org.OrgPersonnelYearAssess;

import java.util.*;

@Service
public class OrgPersonnelService extends ServiceSupport<OrgPersonnel> {

	@Autowired
	private OrgPersonnelMapper personnelMapper;

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private OrgPersonnelJobService orgPersonnelJobService;
	@Autowired
	private OrgPersonnelPracticeService orgPersonnelPracticeService;
	@Autowired
	private AssessPhysicianRecordService assessPhysicianRecordService;
	@Autowired
	private OrgPersonnelYearAssessService orgPersonnelYearAssessService;
	@Autowired
	private OrgPersonnelYearAssessMapper orgPersonnelYearAssessMapper;
	@Autowired
	private OrgPersonnelClaimRecordService personnelClaimRecordService;
	@Autowired
	private OrgAgencyService agencyService;

	public PageResult<OrgPersonnelVO> findPersonnelPage(OrgPersonnelQueryDTO query) {
		Page<OrgPersonnelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelMapper.findPersonnel(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 根据身份证获取人员
	 * 
	 * @param identificationNo
	 * @return
	 */
	public OrgPersonnel getPersonnelByIdentificationNo(String identificationNo) {
		if (identificationNo != null && identificationNo.length() > 0) {
			List<OrgPersonnel> result = searchAll(new Condition(OrgPersonnel.COLUMN_FIELD_IDENTIFICATION_NO, QueryType.EQUAL, identificationNo));
			if (result != null) {
				if (result.size() > 1) {
					throw new BusinessException("存在多个身份证号码为[" + identificationNo + "]的人员");
				}

				if (result.size() == 1) {
					return result.get(0);
				}
			}
		}

		return null;
	}

	/**
	 * 通过身份证号码查询所有状态的人员（正常、离岗等）
	 * 
	 * @param identificationNo
	 * @return
	 */
	public List<OrgPersonnel> getAllStatusPersonnelByIdNo(String identificationNo) {
		if (identificationNo != null && identificationNo.length() > 0) {
			return searchAll(new Condition(OrgPersonnel.COLUMN_FIELD_IDENTIFICATION_NO, QueryType.EQUAL, identificationNo), true);
		}

		return null;
	}

	/**
	 * 变更自己人员的机构
	 * 
	 * @param personnelId
	 * @param targetAgencyId
	 * @return
	 */
	public boolean changeAgencyForOwnPersonnel(String personnelId, String targetAgencyId) {
		// TODO 判断是否管辖下人员，在SQL中增加permission
		OrgAgency agency = agencyService.get(targetAgencyId);
		if (agency != null) {
			return personnelMapper.updateAgencyAndDistrict(agency, personnelId) > 0;
		}
		return false;
	}

	@Override
	public int save(OrgPersonnel personnel) {
		if (personnel == null) {
			return 0;
		}

		String name = personnel.getName();
		Integer identificationType = personnel.getIdentificationType();
		String identificationNo = personnel.getIdentificationNo();

		if (name == null || name.length() == 0) {
			throw new BusinessException("人员姓名不能为空");
		}

		if (identificationType == null) {
			throw new BusinessException("人员身份证类型不能为空");
		}

		if (identificationNo == null || identificationNo.length() == 0) {
			throw new BusinessException("人员身份证不能为空");
		}

		// 统一转化为大写
		identificationNo = identificationNo.toUpperCase();
		personnel.setIdentificationNo(identificationNo);

		// TODO 验证证件号码
		if (getPersonnelByIdentificationNo(identificationNo) != null) {
			throw new BusinessException("已经存在身份证号码为[" + identificationNo + "]的人员");
		}

		String agencyId = personnel.getAgencyId();
		HrmsUserSession session = HrmsUserSession.getCurrentUserSession();

		// TODO 暂时只做最简单情况判断，机构管理员默认只管理一个机构，区域管理员只管理一个区域
		if (session.isAgencyManager()) {
			agencyId = session.getManageAgencyId()[0];
		} else if (session.isDistrictManager()) {
			if (agencyId == null || agencyId.length() == 0) {
				int districtCode = session.getManageDistrictCode()[0];
				District district = OrgDistrictContainer.getDistrict(districtCode);
				personnel.setCityCode(district.getCity().getCode());
				District town = district.getTown();
				personnel.setTownCode(town == null ? null : town.getCode());
				personnel.setDistrictCode(districtCode);
				personnel.setAgencyId(null);
				personnel.setAgencyName(null);
			}
		} else if (session.isSystemAdmin()) {
			if (agencyId == null || agencyId.length() == 0) {
				personnel.setCityCode(null);
				personnel.setTownCode(null);
				personnel.setDistrictCode(null);
				personnel.setAgencyId(null);
				personnel.setAgencyName(null);
			}
		} else {
			throw new BusinessException("没有新增该人员的权限");
		}

		if (agencyId != null && agencyId.length() > 0) {
			OrgAgency agency = agencyService.get(agencyId);
			if (agency == null) {
				throw new BusinessException("找不到人员所属机构");
			} else {
				personnel.setCityCode(agency.getCityCode());
				personnel.setTownCode(agency.getTownCode());
				personnel.setDistrictCode(agency.getDistrictCode());
				personnel.setAgencyId(agencyId);
				personnel.setAgencyName(agency.getName());
			}
		}

		return super.save(personnel);
	}

	/**
	 * 更新头像
	 * 
	 * @param personnelId
	 * @param attachmentId
	 * @return
	 */
	public boolean updateProfilePhoto(String personnelId, String attachmentId) {
		OrgPersonnel model = new OrgPersonnel();
		model.setId(personnelId);
		model.setProfilePhoto(attachmentId);
		return updateSelective(model) > 0;
	}

	/**
	 * 离岗
	 * 
	 * @param id
	 * @return
	 */
	public boolean levelPersonnel(String id) {
		OrgPersonnel model = new OrgPersonnel();
		model.setId(id);
		model.setIsDelete(OrgPersonnel.STATUS_LEVEL);
		return updateSelective(model) > 0;
	}

	/**
	 * 查找离岗人员
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<OrgPersonnelLevelVO> findLevelPersonnelPage(OrgPersonnelClaimQueryDTO query) {
		Page<OrgPersonnelLevelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		personnelMapper.findAllLevelPeople(query);
		return new PageResult<>(page);
	}

	/**
	 * 认领人员
	 * 
	 * @param personnelClaimParamVO
	 * @return
	 */
	@Transactional
	public boolean claimPersonnel(OrgPersonnelClaimDTO personnelClaim) {

		String personnelId = personnelClaim.getPersonnelId();
		String agencyId = personnelClaim.getAgencyId();

		OrgPersonnel personnel = get(personnelId);
		if (personnel == null) {
			throw new BusinessException("找不到对应人员");
		}

		OrgAgency agency = agencyService.get(agencyId);
		if (agency == null) {
			throw new BusinessException("找不到对应机构");
		}

		OrgPersonnel model = new OrgPersonnel();
		model.setId(personnelId);
		model.setAgencyId(agencyId);
		model.setAgencyName(agency.getName());
		model.setCityCode(agency.getCityCode());
		model.setTownCode(agency.getTownCode());
		model.setDistrictCode(agency.getDistrictCode());
		model.setIsDelete(OrgPersonnel.STATUS_NORMAL);

		if (updateSelective(model) > 0) {

			OrgPersonnelClaimRecord record = new OrgPersonnelClaimRecord();
			record.setOriginAgencyId(personnel.getAgencyId());
			record.setPersonnelId(personnelId);
			record.setTargetAgencyId(agencyId);

			personnelClaimRecordService.save(record);
			return true;
		}
		return false;
	}

	// -----------------------------------
	// 人员信息导入模块
	// -----------------------------------

	/**
	 * 人员信息上传
	 * 
	 * @param dto
	 * @return
	 */
	@Transactional
	public String importBase(OrgPersonnelBaseUploadDTO dto) {
		OrgPersonnel personnel = new OrgPersonnel();
		OrgPersonnelJob personnelJob = new OrgPersonnelJob();
		SimpleBeanCopyUtil.simpleCopy(dto, personnel);
		SimpleBeanCopyUtil.simpleCopy(dto, personnelJob);
		if (save(personnel) > 0) {
			personnel = personnelMapper.getByIdentificationNo(dto.getIdentificationNo());
			if (personnel != null) {
				if (orgPersonnelJobService.get(personnel.getId()) != null) {
					throw new BusinessException("身份证号码为[" + personnel.getIdentificationNo() + "]工作信息已存在");
				}
				personnelJob.setId(personnel.getId());
				if (orgPersonnelJobService.save(personnelJob) > 0) {
					return null;
				}
				throw new BusinessException("上传失败");
			}
			throw new BusinessException("上传失败");
		}
		throw new BusinessException("上传失败");
	}

	/**
	 * 执业信息上传
	 * 
	 * @param dto
	 * @return
	 */
	public String importPractice(OrgPersonnelPracticeUploadDTO dto) {
		OrgPersonnel personnel = personnelMapper.getByIdentificationNo(dto.getIdcard());
		OrgPersonnelPractice personnelPractice = new OrgPersonnelPractice();
		SimpleBeanCopyUtil.simpleCopy(dto, personnelPractice);
		if (personnel != null) {
			if (orgPersonnelPracticeService.get(personnel.getId()) != null) {
				throw new BusinessException("身份证号码为[" + dto.getIdcard() + "]工作信息已存在");
			}
			personnelPractice.setId(personnel.getId());
			if (orgPersonnelPracticeService.save(personnelPractice) > 0) {
				return null;
			}
			throw new BusinessException("上传失败");
		}
		throw new BusinessException("系统中没有身份证号码为[" + dto.getIdcard() + "]的个人信息,请先上传该人员的个人信息");
	}

	/**
	 * 医师定期考核信息上传
	 * 
	 * @param dto
	 * @return
	 */
	public String importPhysicianAssess(PhysicianAssessUploadDTO dto) {
		OrgPersonnel personnel = personnelMapper.getByIdentificationNo(dto.getIdentificationNo());
		AssessPhysicianRecord assessPhysicianRecord = new AssessPhysicianRecord();
		SimpleBeanCopyUtil.simpleCopy(dto, assessPhysicianRecord);
		if (personnel != null) {
			if (assessPhysicianRecordService.save(assessPhysicianRecord) > 0) {
				return null;
			}
			throw new BusinessException("上传失败");
		}
		throw new BusinessException("系统中没有身份证号码为[" + dto.getIdentificationNo() + "]的个人信息,请先上传该人员的个人信息");
	}

	/**
	 * 年度考核信息上传
	 * 
	 * @param dto
	 * @return
	 */
	public String importYearAssess(OrgPersonnelYearAssessUploadDTO dto) {
		OrgPersonnel personnel = personnelMapper.getByIdentificationNo(dto.getIdcard());
		OrgPersonnelYearAssess orgPersonnelYearAssess = new OrgPersonnelYearAssess();
		SimpleBeanCopyUtil.simpleCopy(dto, orgPersonnelYearAssess);
		if (personnel != null) {
			orgPersonnelYearAssess.setPersonnelId(personnel.getId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("personnelId", personnel.getId());
			map.put("year", orgPersonnelYearAssess.getYear());
			List<OrgPersonnelYearAssess> list = orgPersonnelYearAssessMapper.checkRepeat(map);
			if (!(list.size() > 0)) {
				if (orgPersonnelYearAssessService.save(orgPersonnelYearAssess) > 0) {
					return null;
				}
			}
			throw new BusinessException("身份证号码为[" + dto.getIdcard() + "]的人员" + dto.getYear() + "年度的考核数据已存在");
		}
		throw new BusinessException("系统中没有身份证号码为[" + dto.getIdcard() + "]的个人信息,请先上传该人员的个人信息");
	}

	// -----------------------------------
	// 人员账号模块
	// -----------------------------------

	/**
	 * 功能描述: <br>
	 * 〈查询个人账户信息〉
	 * 
	 * @param query
	 * @return:com.paladin.framework.common.PageResult<com.paladin.hrms.service.org.dto.OrgPersonalAccountVO>
	 */
	public PageResult<OrgPersonalAccountVO> findPersonalAccountPage(OrgPersonalAccountQuery query) {
		Page<OrgPersonalAccountVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelMapper.selectAccountByQuery(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 功能描述: <br>
	 * 〈查询机构下没有账号人员数量〉
	 * 
	 * @param
	 * @return:java.util.List<com.paladin.hrms.service.org.dto.OrgPersonalAccountVO>
	 */
	public Integer findPeopleNotAccount() {
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		return personnelMapper.countPeopleByAgencyId(permission);
	}

	/**
	 * 功能描述: <br>
	 * 〈批量生成账号〉
	 *
	 * @param
	 * @param
	 * @return:int
	 */
	public Object addAccounts() {
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, null);
		List<OrgPersonalAccountVO> peoples = personnelMapper.findPeopleByAgencyId(permission);
		if (peoples == null || peoples.size() == 0) {
			throw new BusinessException("无账号可生成");
		}

		String[] accounts = new String[peoples.size()];
		String[] ids = new String[peoples.size()];

		for (int i = 0; i < peoples.size(); i++) {
			accounts[i] = peoples.get(i).getIdentificationNo();
			ids[i] = peoples.get(i).getId();
		}
		int successNum = 0;
		int totalNum = accounts.length;
		List<String> eIds = new ArrayList<>(totalNum);
		List<String> eAccounts = new ArrayList<>(totalNum);
		OrgPersonalAddAccounts addAccounts = new OrgPersonalAddAccounts();
		if (accounts.length == 1) {
			successNum = sysUserService.createUserAccount(accounts[0], ids[0], SysUser.STATE_WAITING_ENABLED, SysUser.TYPE_PERSONAL_USER);
			addAccounts.setSuccessNum(successNum);
			addAccounts.setTotalNum(totalNum);
			return addAccounts;
		} else {
			for (int i = 0; i < accounts.length; i++) {
				try {
					successNum += sysUserService.createUserAccount(accounts[i], ids[i], SysUser.STATE_WAITING_ENABLED, SysUser.TYPE_PERSONAL_USER);
				} catch (BusinessException e) {
					eIds.add(ids[i]);
					eAccounts.add(accounts[i]);
					continue;
				}
			}
			addAccounts.setSuccessNum(successNum);
			addAccounts.setTotalNum(totalNum);
			addAccounts.seteAccounts(eAccounts);
			addAccounts.seteIds(eIds);
			return addAccounts;
		}
	}

}