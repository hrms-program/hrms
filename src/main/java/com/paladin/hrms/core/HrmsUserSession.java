package com.paladin.hrms.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.apache.shiro.SecurityUtils;

import com.paladin.framework.core.UserSession;
import com.paladin.hrms.core.OrgDistrictContainer.District;
import com.paladin.hrms.model.syst.SysUser;

/**
 * hrms系统会话信息
 * 
 * @author TontoZhou
 * @since 2018年10月12日
 */
public class HrmsUserSession extends UserSession implements Serializable {

	private static final long serialVersionUID = 4854607722824823403L;

	public HrmsUserSession(String userId, String userName, String account, int userType) {
		super(userId, userName, account);
		this.userType = userType;
		this.agencyId = null;
		this.districtCode = null;

		init();
	}

	public HrmsUserSession(String userId, String userName, String account, int userType, Integer districtCode, String agencyId) {
		super(userId, userName, account);
		this.userType = userType;
		this.agencyId = agencyId == null ? null : new String[] { agencyId };

		if (districtCode != null && OrgDistrictContainer.isDistrictCode(districtCode)) {
			this.districtCode = new int[] { districtCode };
		} else {
			this.districtCode = null;
		}

		init();
	}

	public HrmsUserSession(String userId, String userName, String account, int userType, Integer[] districtCode, String[] agencyId) {
		super(userId, userName, account);
		this.userType = userType;
		this.agencyId = agencyId;

		if (districtCode != null) {
			/**
			 * 权限只取最高部分区域，所以这里进行过滤，去除拥有区域的子区域代码
			 */
			List<Integer> effectiveCode = new ArrayList<>(districtCode.length);
			for (int i = 0; i < districtCode.length; i++) {
				Integer code = districtCode[i];
				if (!OrgDistrictContainer.isDistrictCode(code)) {
					continue;
				}

				int j = 0;
				for (; j < districtCode.length; j++) {
					if (i != j && OrgDistrictContainer.isBelongOrEqual(districtCode[j], code)) {
						break;
					}
				}

				if (j == districtCode.length) {
					effectiveCode.add(code);
				}
			}

			this.districtCode = list2array(effectiveCode);
		}

		init();
	}

	private void init() {
		if (districtCode != null) {
			List<Integer> citys = new ArrayList<>();
			List<Integer> towns = new ArrayList<>();
			List<Integer> others = new ArrayList<>();

			for (Integer code : districtCode) {
				District d = OrgDistrictContainer.getDistrict(code);
				if (d.isCity()) {
					citys.add(code);
				} else if (d.isTown()) {
					towns.add(code);
				} else {
					others.add(code);
				}
			}

			cityCodes = list2array(citys);
			townCodes = list2array(towns);
			otherDistrictCodes = list2array(others);
		}

		ownedDistrictCodes = null;
	}

	private int[] list2array(List<Integer> list) {
		if (list == null || list.size() == 0) {
			return null;
		}

		int[] rs = new int[list.size()];
		int i = 0;
		for (Integer item : list) {
			rs[i++] = item;
		}
		return rs;
	}

	// 用户类型
	private int userType;

	// 管理区域ID
	private int[] districtCode;
	private int[] cityCodes = null; // 可操作市区代码
	private int[] townCodes = null; // 可操作镇区代码
	private int[] otherDistrictCodes = null; // 可操作其他代码（小于镇区的下级代码）

	@Transient
	private transient Set<Integer> ownedDistrictCodes = null; // 可操作的所有区域代码
	@Transient
	private transient List<District> districts = null;

	// 管理机构ID
	private String[] agencyId;

	// 头像URL
	private String profileUrl;
	
	/**
	 * 获取当前用户会话
	 * 
	 * @return
	 */
	public static HrmsUserSession getCurrentUserSession() {
		return (HrmsUserSession) SecurityUtils.getSubject().getPrincipal();
	}

	public boolean isSystemAdmin() {
		return userType == SysUser.TYPE_SYSTEM_ADMIN;
	}

	public boolean isDistrictManager() {
		return userType == SysUser.TYPE_DISTRICT_MANAGER;
	}

	public boolean isAgencyManager() {
		return userType == SysUser.TYPE_AGENCY_MANAGER;
	}

	public boolean isPersonalUser() {
		return userType == SysUser.TYPE_PERSONAL_USER;
	}

	/**
	 * 获取管理地区代码
	 * 
	 * @return
	 */
	@Transient
	public int[] getManageDistrictCode() {
		return districtCode;
	}

	/**
	 * 获取管理区域
	 * 
	 * @return
	 */
	@Transient
	public List<District> getManageDistrict() {
		if (isSystemAdmin()) {
			return OrgDistrictContainer.getRootDistrict();
		}
		
		if (districtCode == null || districtCode.length == 0) {
			return null;
		}

		if (districts == null) {
			synchronized (districtCode) {
				if (districts == null) {
					ArrayList<District> dis = new ArrayList<>(districtCode.length);
					for (int i = 0; i < districtCode.length; i++) {
						dis.add(OrgDistrictContainer.getDistrict(districtCode[i]));
					}
					districts = dis;
				}
			}
		}

		return districts;
	}

	/**
	 * 是否拥有地区权限（任意）
	 * 
	 * @return
	 */
	public boolean hasDistrictPermission() {
		return districtCode != null && districtCode.length > 0;
	}

	/**
	 * 获取管理机构代码
	 * 
	 * @return
	 */
	@Transient
	public String[] getManageAgencyId() {
		return agencyId;
	}

	/**
	 * 是否拥有机构权限（任意）
	 * 
	 * @return
	 */
	public boolean hasAgencyPermission() {
		return agencyId != null && agencyId.length > 0;
	}

	@Transient
	public int[] getCityCodes() {
		return cityCodes;
	}

	@Transient
	public int[] getTownCodes() {
		return townCodes;
	}

	@Transient
	public int[] getOtherDistrictCodes() {
		return otherDistrictCodes;
	}

	/**
	 * 是否拥有该机构权限
	 * 
	 * @param agency
	 * @return
	 */
	public boolean hasAgencyPermission(String agency) {
		if (isSystemAdmin()) {
			return true;
		}

		if (agency != null && agencyId != null) {
			for (String id : agencyId) {
				if (id.equals(agency)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断是否有此区域权限
	 * 
	 * @param code
	 * @return
	 */
	public boolean hasDistrictPermission(int code) {
		if (isSystemAdmin()) {
			return true;
		}

		if (districtCode == null) {
			return false;
		}

		if (ownedDistrictCodes == null) {
			synchronized (districtCode) {
				if (ownedDistrictCodes == null) {
					Set<Integer> codes = new HashSet<>();
					for (int dc : districtCode) {
						District d = OrgDistrictContainer.getDistrict(dc);
						addOwnCode(codes, d);
					}

					ownedDistrictCodes = codes;
				}
			}
		}

		if (ownedDistrictCodes != null) {
			return ownedDistrictCodes.contains(code);
		}

		return false;
	}

	private void addOwnCode(Set<Integer> codes, District d) {
		codes.add(d.getCode());
		for (District c : d.getChildren()) {
			addOwnCode(codes, c);
		}
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

}
