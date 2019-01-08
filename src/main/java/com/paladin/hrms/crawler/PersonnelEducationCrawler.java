package com.paladin.hrms.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.crawler.pojo.OrgPersonnelEducationCrawler;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <教育经历爬取>
 *
 * @author Huangguochen
 * @create 2018/12/16 13:07
 */
public class PersonnelEducationCrawler extends PersonnelHrmsCrawler {
	private static final Map<String, String> educationInfo = new HashMap<String, String>();

	static {
		educationInfo.put("id", "id");
		educationInfo.put("unitname", "schoolName");// 学校名称
		educationInfo.put("site", "schoolAddress");// 学校地点
		educationInfo.put("edu", "education");// 学历
		educationInfo.put("educode", "diplomaNo");// 毕业证书编号
		educationInfo.put("edudegree", "academicDegree");// 学位 education_degree
		educationInfo.put("edudegreecode", "academicDegreeNo");// 学位证书编号
		educationInfo.put("major", "major");// 所学专业 major-type
		educationInfo.put("edutype", "educationType");// 教育类型 education-type
		educationInfo.put("starttimeString", "joinSchoolTime");// 入学时间
		educationInfo.put("endtimeString", "graduationTime");// 毕业时间
		educationInfo.put("certificate", "hasRecordStatus");// 取得证书情况
		educationInfo.put("supplement", "otherInstructions");// 补充说明
		// put("flowcode","status");//审核状态 personal-info-status
	};

	public List<OrgPersonnelEducationCrawler> crawl(String personnelId) throws IOException {

		Document doc = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid=" + personnelId + "&exptype=jyjl_div&logout_date=", null,
				Connection.Method.POST, true);
		List<Map<String, Object>> maps = parseDoc(doc);

		List<OrgPersonnelEducationCrawler> result = new ArrayList<>();
		if (maps != null && maps.size() > 0) {
			String jsonString;
			OrgPersonnelEducationCrawler orgPersonnelEducationCrawler;
			for (Map<String, Object> map : maps) {
				jsonString = JSON.toJSONString(map);
				orgPersonnelEducationCrawler = JSON.parseObject(jsonString, new TypeReference<OrgPersonnelEducationCrawler>() {
				});

				orgPersonnelEducationCrawler.setId(orgPersonnelEducationCrawler.getId());
				orgPersonnelEducationCrawler.setPersonnelId(personnelId);
				orgPersonnelEducationCrawler.setUrl(readAttachment(orgPersonnelEducationCrawler.getId(), "edu"));
				result.add(orgPersonnelEducationCrawler);
			}
		}

		return result;
	}

	public List<Map<String, Object>> parseDoc(Document document) {
		Elements forms = document.getElementsByTag("form");
		List<Map<String, Object>> educationMaps = new ArrayList<>();
		Map<String, Object> educationMap;
		if (forms != null && forms.size() > 0) {
			for (int i = 0; i < forms.size() - 1; i++) {
				educationMap = new HashMap<>(educationInfo.size());
				Element form = forms.get(i);
				for (Map.Entry<String, String> coloumn : educationInfo.entrySet()) {
					String key = coloumn.getKey();
					String value1 = coloumn.getValue();
					Element element = form.getElementById(key);
					if (element != null) {
						if ("select".equals(element.tagName())) {
							Integer type;
							String text;
							Element select = element.selectFirst("option[selected]");
							if (select != null) {
								text = select.text();
								if ("edudegree".equals(key)) {
									type = ConstantsContainer.getTypeKey("education_degree", text);
									educationMap.put(value1, type);
								} else if ("major".equals(key)) {
									type = ConstantsContainer.getTypeKey("major-type", text);
									educationMap.put(value1, type);
								} else {
									type = ConstantsContainer.getTypeKey(StringUtil.camelhumpToMiddleline(value1), text);
									educationMap.put(value1, type);
								}
							}
						} else {
							String value = element.attr("value");
							educationMap.put(value1, value);
						}
					} else {
						System.out.println(key + "获取不到e");
					}
				}
				String text = form.getElementsByTag("font").first().text();
				Integer typeKey = ConstantsContainer.getTypeKey("personal-info-status", text);
				educationMap.put("status", typeKey);
				educationMaps.add(educationMap);
			}
		}
		return educationMaps;
	}
}
