package com.paladin.hrms.core;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.container.AttachmentContainer;
import com.paladin.hrms.core.OrgDistrictContainer.District;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgAgencyManager;
import com.paladin.hrms.model.org.OrgDistrictManager;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.model.syst.SysUser;
import com.paladin.hrms.service.org.OrgAgencyManagerService;
import com.paladin.hrms.service.org.OrgAgencyService;
import com.paladin.hrms.service.org.OrgDistrictManagerService;
import com.paladin.hrms.service.org.OrgPersonnelService;

@Component
public class HrmsUserSessionFactory {

	@Autowired
	private OrgPersonnelService personnelService;

	@Autowired
	private OrgAgencyService agencyService;

	@Autowired
	private OrgAgencyManagerService agencyManagerService;

	@Autowired
	private OrgDistrictManagerService districtManagerService;

	public UserSession createUserSession(SysUser sysUser) {
		int type = sysUser.getType();
		String userId = sysUser.getUserId();
		String account = sysUser.getAccount();

		HrmsUserSession userSession = null;

		if (type == SysUser.TYPE_SYSTEM_ADMIN) {
			/*
			 * 系统管理员
			 */
			userSession = new HrmsUserSession(userId, "系统管理员", account, type);
		} else if (type == SysUser.TYPE_DISTRICT_MANAGER) {
			/*
			 * 区域管理员
			 */
			OrgDistrictManager manager = districtManagerService.get(account);
			if (manager == null) {
				throw new AuthenticationException("找不到账号对应的区域管理员");
			}

			// 查找对应区域
			District district = null;
			Integer districtCode = manager.getDistrictCode();
			if (districtCode != null) {
				district = OrgDistrictContainer.getDistrict(districtCode);
			}
			if (district == null) {
				throw new AuthenticationException("找不到区域管理员对应的区域");
			}

			// 姓名显示区域名称+管理员
			userSession = new HrmsUserSession(userId, district.getName() + "管理员", account, type, manager.getDistrictCode(), null);
		} else if (type == SysUser.TYPE_AGENCY_MANAGER) {
			/*
			 * 机构管理员
			 * 
			 */
			OrgAgencyManager manager = agencyManagerService.get(userId);
			if (manager == null) {
				throw new AuthenticationException("找不到账号对应的机构管理员");
			}

			// 查找对应机构
			OrgAgency agency = null;
			String agencyId = manager.getAgencyId();
			if (agencyId != null && agencyId.length() != 0) {
				agency = agencyService.get(agencyId);
			}

			if (agency == null) {
				throw new AuthenticationException("找不到机构管理员对应的机构");
			}

			// 姓名显示机构名称+管理员
			userSession = new HrmsUserSession(userId, agency.getName() + "管理员", account, type, null, manager.getAgencyId());
		} else if (type == SysUser.TYPE_PERSONAL_USER) {
			/*
			 * 人员用户
			 */

			OrgPersonnel personnel = personnelService.get(userId);
			if (personnel == null) {
				throw new AuthenticationException("找不到账号对应的个人用户");
			}
			userSession = new HrmsUserSession(userId, personnel.getName(), sysUser.getAccount(), type);
			String profilePhoto = personnel.getProfilePhoto();
			if (profilePhoto != null && profilePhoto.length() != 0) {
				SysAttachment att = AttachmentContainer.getAttachment(profilePhoto);
				if (att != null) {
					userSession.setProfileUrl("/file/" + att.getPelativePath());
				}
			}
		}

		if (userSession != null && userSession.getProfileUrl() == null) {
			userSession.setProfileUrl("/static/image/user.png");
		}

		return userSession;
	}

}
