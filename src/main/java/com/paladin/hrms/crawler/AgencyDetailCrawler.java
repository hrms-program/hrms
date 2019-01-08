package com.paladin.hrms.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.paladin.framework.utils.StringUtil;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.model.org.OrgAgencyDetail;

/**
 * 爬取单位查看页信息
 * 
 * @author jisanjie
 * @version 2018年12月11日 下午3:38:32
 */

public class AgencyDetailCrawler extends HrmsCrawler {

	/**
	 * 爬取单位查看页信息
	 * 
	 * @throws IOException
	 */
	public AgencyResult parseAgencyDetail(String agencyId) throws IOException {
		AgencyResult agencyVO = new AgencyResult();
		OrgAgency agencyBase = new OrgAgency();
		OrgAgencyDetail orgAgencyDetail = new OrgAgencyDetail();
		agencyVO.setOrgAgency(agencyBase);
		agencyVO.setOrgAgencyDetail(orgAgencyDetail);

		// 单位查看页请求地址
		String agencyDetailUrl = "http://58.213.112.246/rlzy/hospital/load?organcode=" + agencyId + "&type=show";
		Document d = getDocument(agencyDetailUrl, null, Method.POST, true);
		Elements trs = d.getElementsByClass("caidan").select("tr");
		// 单位基础信息
		agencyVO.getOrgAgency().setId(agencyId);
		agencyVO.getOrgAgency().setName(isEmptyValue(trs.get(0).select("td").get(1).text()));
		agencyVO.getOrgAgency().setCode(isEmptyValue(trs.get(0).select("td").get(3).text()));
		agencyVO.getOrgAgency().setTownStreet(isEmptyValue(trs.get(1).select("td").get(1).text()));
		agencyVO.getOrgAgency().setAddress(isEmptyValue(trs.get(1).select("td").get(3).text()));
		// 单位详情
		agencyVO.getOrgAgencyDetail().setAgencyId(agencyId);
		agencyVO.getOrgAgencyDetail().setAgencyClassify(isEmptyValue(trs.get(2).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setAgencyBelong(isEmptyValue(trs.get(2).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setHostUnit(isEmptyValue(trs.get(3).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setEconomicType(isEmptyValue(trs.get(3).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setAgencyCategory(isEmptyValue(trs.get(4).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setHospitalGrade(isEmptyValue(trs.get(4).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setAgencyChange(isEmptyValue(trs.get(5).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setAgencyFoundingTime(isEmptyDate(trs.get(5).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setLicenseStartTime(isEmptyDate(trs.get(6).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setLicenseEndTime(isEmptyDate(trs.get(6).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setJuridicalPersonName(isEmptyValue(trs.get(7).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setPhone(isEmptyValue(trs.get(7).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setApprovalNumber(isEmptyValue(trs.get(8).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setApprovalAgency(isEmptyValue(trs.get(8).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setRegisteredCapital(isEmptyValue(trs.get(9).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setCertificateTime(isEmptyDate(trs.get(9).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setOpenBedNumber(isEmptyValue(trs.get(10).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setIsOutHospitalPatient(isEmptyConstant(trs.get(10).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setIsEnabled(isEmptyConstant(trs.get(11).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setIsSubstrateUnit(isEmptyConstant(trs.get(11).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setIsBranchAgency(isEmptyConstant(trs.get(12).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setPostalCode(isEmptyValue(trs.get(12).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setUnitWebsite(isEmptyValue(trs.get(13).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setUnitEmail(isEmptyValue(trs.get(13).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setTransactPeople(isEmptyValue(trs.get(14).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setInputPeople(isEmptyValue(trs.get(14).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setIsReportingClinic(isEmptyConstant(trs.get(15).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setClinicReportingAgency(isEmptyValue(trs.get(15).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setIsVillageClinic(isEmptyConstant(trs.get(16).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setVillageClinicAgency(isEmptyValue(trs.get(16).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setAgencyCreateTime(isEmptyDate(trs.get(17).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setDateObsolete(isEmptyDate(trs.get(17).select("td").get(3).text()));
		agencyVO.getOrgAgencyDetail().setAgencysCreateTime(isEmptyDate(trs.get(18).select("td").get(1).text()));
		agencyVO.getOrgAgencyDetail().setAgencyUpdateTime(isEmptyDate(trs.get(18).select("td").get(3).text()));

		return agencyVO;
	}

	/**
	 * 如果获取的字段为空值，设置为null
	 * 
	 */
	public String isEmptyValue(String tdValue) {
		if (StringUtil.isNotEmpty(tdValue)) {
			return tdValue;
		} else {
			return null;
		}
	}

	/**
	 * 参数为日期的，格式化
	 * 
	 */
	public Date isEmptyDate(String tdValue) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotEmpty(tdValue)) {
			try {
				date = sdf.parse(tdValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			return date;
		}
		return date;
	}

	/**
	 * 参数为常量的，格式化
	 * 
	 */
	public Integer isEmptyConstant(String tdValue) {
		if (StringUtil.isNotEmpty(tdValue)) {
			if (tdValue.equals("是")) {
				return 1;
			} else if (tdValue.equals("否")) {
				return 0;
			}
		}
		return null;
	}

	public static class AgencyResult {

		private OrgAgency orgAgency;

		private OrgAgencyDetail orgAgencyDetail;

		public OrgAgency getOrgAgency() {
			return orgAgency;
		}

		public void setOrgAgency(OrgAgency orgAgency) {
			this.orgAgency = orgAgency;
		}

		public OrgAgencyDetail getOrgAgencyDetail() {
			return orgAgencyDetail;
		}

		public void setOrgAgencyDetail(OrgAgencyDetail orgAgencyDetail) {
			this.orgAgencyDetail = orgAgencyDetail;
		}

	}
}
