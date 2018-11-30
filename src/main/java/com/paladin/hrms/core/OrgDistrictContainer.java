package com.paladin.hrms.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paladin.framework.core.VersionContainer;
import com.paladin.hrms.model.org.OrgDistrict;
import com.paladin.hrms.service.org.OrgDistrictService;

@Component
public class OrgDistrictContainer implements VersionContainer {

	@Autowired
	private OrgDistrictService districtService;

	private static volatile Map<Integer, District> districtMap;
	private static volatile List<District> roots;

	public boolean initialize() {

		List<OrgDistrict> orgDistricts = districtService.findAll();

		Map<Integer, District> districtMap = new HashMap<>();
		List<District> roots = new ArrayList<>();

		for (OrgDistrict orgDistrict : orgDistricts) {
			District district = new District(orgDistrict);
			districtMap.put(orgDistrict.getCode(), district);
		}

		for (District district : districtMap.values()) {
			if (district.parentCode == null) {
				roots.add(district);
			} else {
				District parentDistrict = districtMap.get(district.parentCode);
				if (parentDistrict == null) {
					roots.add(district);
				} else {
					district.parent = parentDistrict;
					parentDistrict.children.add(district);
				}
			}

			district.initLevel();
		}

		OrgDistrictContainer.districtMap = Collections.unmodifiableMap(districtMap);
		OrgDistrictContainer.roots = Collections.unmodifiableList(roots);

		return true;
	}

	public static District getDistrict(Integer code) {
		return districtMap.get(code);
	}

	public static District getCity(Integer code) {
		District d = districtMap.get(code);
		if (d != null) {
			return d.getCity();
		}
		return null;
	}

	public static District getTown(Integer code) {
		District d = districtMap.get(code);
		if (d != null) {
			return d.getTown();
		}
		return null;
	}

	public static List<District> getRootDistrict() {
		return roots;
	}

	/**
	 * 是否有效区域代码
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isDistrictCode(Integer code) {
		return districtMap.get(code) != null;
	}

	/**
	 * 是否是子父关系或相等
	 * 
	 * @param parentDistrictCode
	 * @param childDistrictCode
	 * @return
	 */
	public static boolean isBelongOrEqual(Integer parentDistrictCode, Integer childDistrictCode) {
		if (parentDistrictCode == null) {
			return false;
		}

		District d = districtMap.get(childDistrictCode);
		if (d != null) {
			if (d.code.equals(parentDistrictCode)) {
				return true;
			}
			d = d.parent;
		}
		return false;
	}

	/**
	 * 检测区域数据，根据所在区域得到城市、乡镇等冗余字段，如果需要扩大冗余字段只要修改这里
	 * 
	 * @param districtData
	 * @return 如果区域存在，则返回true，如果数据为空，或找不到区域则返回false
	 */
	public static boolean checkDistrictData(DistrictData districtData) {
		if (districtData != null) {
			Integer districtCode = districtData.getDistrictCode();
			
			if (districtCode != null) {
				District district = getDistrict(districtCode);
				if (district != null) {
					District city = district.getCity();
					District town = district.getTown();

					districtData.setCityCode(city == null ? null : city.getCode());
					districtData.setTownCode(town == null ? null : town.getCode());
					
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 为了便于提高查询效率，方便增加冗余字段，我们这里定死最高区域为市，下面为区/镇，再下面则可以无限扩展，
	 * 由于这两级区域为最常规查询区域，所以能快速通过一个字段就匹配到所需数据，如若需要增加省级甚至国家， 则需要修改这里部分代码，并在数据库中增加相应冗余字段。
	 * 
	 * @author TontoZhou
	 * @since 2018年10月12日
	 */
	public static class District {

		private Integer code;
		private String name;
		private Integer parentCode;

		private int level;

		@JsonIgnore
		private District parent;
		private List<District> children;

		public District(OrgDistrict orgDistrict) {
			code = orgDistrict.getCode();
			name = orgDistrict.getName();
			parentCode = orgDistrict.getParentCode();
			children = new ArrayList<>();
		}

		/**
		 * 初始化等级，需要在树结构完善后调用
		 */
		private void initLevel() {
			int level = 1;
			District d = this;
			while (d.parent != null) {
				level++;
				d = d.parent;
			}
			this.level = level;
		}

		public Integer getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public Integer getParentCode() {
			return parentCode;
		}

		public District getParent() {
			return parent;
		}

		public List<District> getChildren() {
			return children;
		}

		public boolean isCity() {
			return level == 1;
		}

		public boolean isTown() {
			return level == 2;
		}

		@JsonIgnore
		public District getCity() {
			District a = this;
			while (a != null) {
				if (a.isCity()) {
					return a;
				}
				a = a.parent;
			}
			return null;
		}

		@JsonIgnore
		public District getTown() {
			District a = this;
			while (a != null) {
				if (a.isTown()) {
					return a;
				}
				a = a.parent;
			}
			return null;
		}

		public boolean equals(Object obj) {

			if (obj == null)
				return false;

			if (obj == this)
				return true;

			if (obj instanceof District) {
				return code.equals(((District) obj).code);
			}

			return false;
		}

		public int hashCode() {
			return code.hashCode();
		}

		public String toString() {
			if (parent != null) {
				return parent.toString() + "-" + name;
			} else {
				return name;
			}
		}

	}

	@Override
	public String getId() {
		return "sys_district_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		return initialize();
	}

}
