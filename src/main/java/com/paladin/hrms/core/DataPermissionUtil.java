package com.paladin.hrms.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.paladin.hrms.core.OrgDistrictContainer.District;

/**
 * 数据权限工具类，数据权限核心算法代码
 * 
 * @author TontoZhou
 * @since 2018年10月15日
 */
public class DataPermissionUtil {

	/**
	 * 获取地区数据权限查询条件
	 * 
	 * @param districtCode
	 *            想要获取的区域权限，如果为NULL则是获取当前用户最大权限
	 * @param agencyIds
	 *            想要获取的机构权限，如果为NULL则是获取当前用户最大权限
	 * @return 如果没有权限则返回null
	 */
	public static DataPermissionCondition getPermissionCondition(int[] districtCodes, String[] agencyIds) {
		DataPermissionCondition condition = new DataPermissionCondition();
		HrmsUserSession session = HrmsUserSession.getCurrentUserSession();

		/*
		 * 结合使用场景和效率问题，没有统一处理区域问题，使用冗余代码以提高多数场景查询效率
		 */

		if (session.isSystemAdmin() || session.isDistrictManager()) {
			if (districtCodes != null && districtCodes.length > 0) {
				int size = districtCodes.length;
				if (size == 1) {
					// 只有一个区域，判断该区域是否有权限，有则判断其类型
					int code = districtCodes[0];
					if (session.hasDistrictPermission(code)) {
						District district = OrgDistrictContainer.getDistrict(code);
						if (district.isCity()) {
							condition.cityCode = code;
						} else if (district.isTown()) {
							condition.townCode = code;
						} else {
							condition.districtCode = code;
						}
					} else {
						return null;
					}
				} else {
					// 筛选有权限的区域
					List<Integer> ownCodes = new ArrayList<>(size);
					for (int code : districtCodes) {
						if (session.hasDistrictPermission(code)) {
							ownCodes.add(code);
						}
					}

					// 拥有权限的区域
					int rsize = ownCodes.size();
					if (rsize == 0) {
						// 没有返回null
						return null;
					} else if (rsize == 1) {
						// 只有一个拥有权限，则判断区域类型查询
						int code = ownCodes.get(0);
						District district = OrgDistrictContainer.getDistrict(code);
						if (district.isCity()) {
							condition.cityCode = code;
						} else if (district.isTown()) {
							condition.townCode = code;
						} else {
							condition.districtCode = code;
						}
					} else {
						List<Integer> ownCityCodes = new ArrayList<>(rsize);
						List<Integer> ownTownCodes = new ArrayList<>(rsize);
						List<Integer> ownOtherCodes = new ArrayList<>(rsize);

						for (int code : ownCodes) {
							District district = OrgDistrictContainer.getDistrict(code);
							if (district.isCity()) {
								ownCityCodes.add(code);
							} else if (district.isTown()) {
								ownTownCodes.add(code);
							} else {
								ownOtherCodes.add(code);
							}
						}

						if (ownCityCodes.size() == 1) {
							condition.cityCode = ownCityCodes.get(0);
						} else if (ownCityCodes.size() > 1) {
							condition.cityCodes = ownCityCodes;
						}

						if (ownTownCodes.size() == 1) {
							condition.townCode = ownTownCodes.get(0);
						} else if (ownTownCodes.size() > 1) {
							condition.townCodes = ownTownCodes;
						}

						if (ownOtherCodes.size() == 1) {
							condition.districtCode = ownOtherCodes.get(0);
						} else if (ownOtherCodes.size() > 1) {
							condition.districtCodes = ownOtherCodes;
						}
					}
				}
			} else {
				// 如果是系统管理员，则查询条件无，查询所有
				if (!session.isSystemAdmin()) {
					// 判断是否拥有任意区域权限
					if (!session.hasDistrictPermission()) {
						return null;
					}

					int[] ownCityCodes = session.getCityCodes();
					int[] ownTownCodes = session.getTownCodes();
					int[] ownOtherCodes = session.getOtherDistrictCodes();

					if (ownCityCodes != null) {
						if (ownCityCodes.length == 1) {
							condition.cityCode = ownCityCodes[0];
						} else if (ownCityCodes.length > 1) {
							condition.cityCodes = array2list(ownCityCodes);
						}
					}

					if (ownTownCodes != null) {
						if (ownTownCodes.length == 1) {
							condition.townCode = ownTownCodes[0];
						} else if (ownTownCodes.length > 1) {
							condition.townCodes = array2list(ownTownCodes);
						}
					}

					if (ownOtherCodes != null) {
						if (ownOtherCodes.length == 1) {
							condition.districtCode = ownOtherCodes[0];
						} else if (ownOtherCodes.length > 1) {
							condition.districtCodes = array2list(ownOtherCodes);
						}
					}
				}
			}

			if (agencyIds != null) {
				if (agencyIds.length == 1) {
					condition.agencyId = agencyIds[0];
				} else if (agencyIds.length > 1) {
					condition.agencyIds = Arrays.asList(agencyIds);
				}
			}

		} else if (session.isAgencyManager()) {
			// 判断是否拥有任意区域权限
			if (!session.hasAgencyPermission()) {
				return null;
			}

			if (agencyIds != null && agencyIds.length > 0) {
				if (agencyIds.length == 1) {
					String agencyId = agencyIds[0];
					// 判断权限
					if (session.hasAgencyPermission(agencyId)) {
						condition.agencyId = agencyId;
					} else {
						return null;
					}
				} else {
					// 筛选机构
					List<String> ownAgencyIds = new ArrayList<>(agencyIds.length);
					for (String agencyId : agencyIds) {
						if (session.hasAgencyPermission(agencyId)) {
							ownAgencyIds.add(agencyId);
						}
					}

					// 设置查询条件
					if (ownAgencyIds.size() == 0) {
						return null;
					} else if (ownAgencyIds.size() == 1) {
						condition.agencyId = ownAgencyIds.get(0);
					} else {
						condition.agencyIds = ownAgencyIds;
					}
				}
			} else {

				String[] ownAgencyIds = session.getManageAgencyId();
				if (ownAgencyIds.length == 1) {
					condition.agencyId = ownAgencyIds[0];
				} else {
					condition.agencyIds = Arrays.asList(ownAgencyIds);
				}
			}
		} else {
			return null;
		}

		return condition;
	}

	private static List<Integer> array2list(int[] array) {
		ArrayList<Integer> list = new ArrayList<>(array.length);
		for (int i : array) {
			list.add(i);
		}
		return list;
	}

	/**
	 * 数据权限查询条件 通过区域代码（包括冗余字段：城市和区镇代码）和机构ID来描述用户所能操作的数据范围。 例如cityCode =
	 * 昆山，则数据表中city_code等于昆山的数据都在操作范围内
	 * 
	 * @author TontoZhou
	 * @since 2018年10月12日
	 */
	public static class DataPermissionCondition {

		private Integer cityCode;
		private Integer townCode;
		private Integer districtCode;
		private List<Integer> cityCodes;
		private List<Integer> townCodes;
		private List<Integer> districtCodes;

		private String agencyId;
		private List<String> agencyIds;

		private DataPermissionCondition() {
			// 内部创建
		}

		public Integer getCityCode() {
			return cityCode;
		}

		public Integer getTownCode() {
			return townCode;
		}

		public Integer getDistrictCode() {
			return districtCode;
		}

		public List<Integer> getDistrictCodes() {
			return districtCodes;
		}

		public String getAgencyId() {
			return agencyId;
		}

		public List<String> getAgencyIds() {
			return agencyIds;
		}

		public List<Integer> getCityCodes() {
			return cityCodes;
		}

		public List<Integer> getTownCodes() {
			return townCodes;
		}
	}

}
