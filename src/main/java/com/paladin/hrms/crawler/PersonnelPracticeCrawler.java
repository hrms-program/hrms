package com.paladin.hrms.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.model.org.OrgPersonnelPractice;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <执业信息爬取>
 *
 * @author Huangguochen
 * @create 2018/12/12 16:07
 */
public class PersonnelPracticeCrawler extends PersonnelHrmsCrawler {
	private static final Map<String, String> practiceInfo = new HashMap<String, String>();
	static {

		practiceInfo.put("type", "personnelType");// 人员类型s
		practiceInfo.put("quacardcode", "docQualificationCode");// 医师资格证书编号：i
		practiceInfo.put("corpcardcode", "docCertCode");// 医师执业证书编号：i
		practiceInfo.put("pxzs", "isCountryCert");// 是否获得国家住院医师规范化培训合格证书：boolean
		practiceInfo.put("zsbm", "inpatientCertCode");// 住院医师规范化培训合格证书编号：i
		practiceInfo.put("qkyx", "isGeneralPractitioner");// 是否注册为全科医学专业：boolean
		practiceInfo.put("licensedPlace", "practiceAddress");// 执业地点：i
		practiceInfo.put("reqregisterdateString", "registrationDate");// 执业注册时间(首次注--册时间)i
		practiceInfo.put("applypratype", "practiceCategory");// 医师执业类别：s practice-category-type
		practiceInfo.put("gmpranges", "practiceScope");// 医师执业范围：多项 parent-》strong --> text <td>
		practiceInfo.put("isfromhealthcenter", "isDispatched");// 是否由乡镇卫生院或--社区卫生服务机构--派驻村卫生室工作 boolean
		practiceInfo.put("ismultisite", "isMultisite");// 是否多地点职业： boolean
		practiceInfo.put("applypratype2", "secondCategory");// 第2执业单位类别:s practice-category
		practiceInfo.put("applypratype3", "thirdCategory");// 第3执业单位类别：s practice-category
		practiceInfo.put("specialty", "expertise");// 专科特长：i
		practiceInfo.put("worklifes", "docTrainCert");// 全科医生取得培训合格证书情况：i parent-》strong --> text <td>
		practiceInfo.put("nursequacardcode", "nurseCertCode");// 护士执业证书编号：i
		practiceInfo.put("nurselogon", "nurseInstitution");// 护士执业单位 i
		practiceInfo.put("enddateString", "lastRegistrationDate");// （护士）最后注册日期：i
		practiceInfo.put("nurseexam", "isExam");// (护士)是否免考boolean
		practiceInfo.put("nurseexamdateString", "nurseExamTime");// 护考时间：i
		practiceInfo.put("workstartdateString", "startWorkDate");// 从事护士工作开始时间：：
		practiceInfo.put("jobtype", "nurseCategory");// (护士)工作类别 s --> i
		practiceInfo.put("specialpsname", "certName");// 特殊岗位证书名称i
		practiceInfo.put("specialpsunit", "issueUnit");// 特殊岗位证书发证单位：i
		practiceInfo.put("specialpsdateString", "issueDate");// 特殊岗位证书发证获得时间i
		practiceInfo.put("specialpsstartString", "startDate");// 特殊岗位证书有效期开始时间i
		practiceInfo.put("specialpsendString", "endDate");// 特殊岗位证书有效期结束时间 i
		practiceInfo.put("isincountry", "isInVillageClinic");// 是否在村卫生室工作 boolean
		practiceInfo.put("cdQuacardcode", "vdocCertCode");// 乡村医生执业证书编号 i
		practiceInfo.put("countryorgname", "registVillageAgency");// 注册村级卫生机构名称 i
		practiceInfo.put("registtimeString", "registDate");// （村医）注册时间(首次注册时间) i
		practiceInfo.put("jobyears", "totalIncome");// 上年总收入 i
		practiceInfo.put("ismedicalinsurance", "isMedicalInsurance");// 是否有医疗责任保险 boolean
		practiceInfo.put("isemployinsurance", "isInjuryInsurance");// 是否有工伤保险 boolean
		practiceInfo.put("iscountry", "isEndowmentInsurance");// 是否有养老保险 boolean
		practiceInfo.put("workyears", "workYears");// 从事乡村医生工作年限(年) i
		practiceInfo.put("istraining", "isOnjobTrain");// 高中及以下学历乡村医生是否为在职培训合格者 boolean

	}

	public OrgPersonnelPractice crawl(String personnelId, Integer type) throws IOException {

		/*
		 * Map<String, OtherColumn> columnMap = new HashMap<String, OtherColumn>(){{
		 * put("efd1d6a04d68c84f6540a0150ddb8ec9",new OtherColumn(1,null,null));
		 * put("320000004760344814",new OtherColumn(1,null,null));
		 * put("82fba5b7b895fb0132912cf784209b1c",new OtherColumn(1,null,null)); }};
		 */
		Document doc = getDocument("http://58.213.112.246/rlzy/doctor/load?id=" + personnelId + "&type=" + type, null, Connection.Method.POST, true);
		Map<String, Object> orgperson = parseDoc(doc);
		String baseInfo = JSON.toJSONString(orgperson);
		OrgPersonnelPractice practiceInfo = JSON.parseObject(baseInfo, new TypeReference<OrgPersonnelPractice>() {
		});
		practiceInfo.setId(personnelId);
		practiceInfo.setPersonnelType(type);

		return practiceInfo;
	}

	private Map<String, Object> parseDoc(Document doc) {
		Element zyxx = doc.getElementById("zyxx_form");
		Map<String, Object> practiceMap = new HashMap<>(practiceInfo.size());
		if (zyxx != null && zyxx.hasText()) {
			for (Map.Entry<String, String> coloumn : practiceInfo.entrySet()) {
				String key = coloumn.getKey();
				String value1 = coloumn.getValue();
				String attr = "";
				Element element = zyxx.getElementById(key);
				if (element != null) {
					if ("td".equals(element.tagName())) {
						Elements elements = element.parent().select("span[class=span-text]").first().children();
						List<String> strings = elements.eachText();
						for (String att : strings) {
							attr += att + " ";
						}
						practiceMap.put(value1, attr);
					} else if ("select".equals(element.tagName())) {
						Integer type;
						String text;
						Element select = element.selectFirst("option[selected]");
						if (select != null) {
							text = select.text();
							if ("jobtype".equals(key)) {
								practiceMap.put(value1, text);
							} else if ("applypratype".equals(key)) {
								type = ConstantsContainer.getTypeKey("practice-category-type", text);
								practiceMap.put(value1, type);
							} else if ("applypratype2".equals(key) || "applypratype3".equals(key)) {
								type = ConstantsContainer.getTypeKey("practice-category", text);
								practiceMap.put(value1, type);
							} else {
								type = ConstantsContainer.getTypeKey("boolean", text);
								practiceMap.put(value1, type);
							}
						}
					} else {
						attr = element.attr("value");
						practiceMap.put(value1, attr);
					}
				} else {
					System.out.println(key + "获取不到e");
				}
			}
		}
		return practiceMap;
	}

}
