package com.paladin.hrms.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.crawler.pojo.OrgPersonnelWorkExperienceCrawler;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <工作经历爬取>
 *
 * @author Huangguochen
 * @create 2018/12/17 13:30
 */
public class PersonnelWorkExperienceCrawler extends PersonnelHrmsCrawler {
	private static final Map<String, String> workInfo = new HashMap<String, String>();
	static {
		{
			workInfo.put("id", "id");
			workInfo.put("unitname", "workUnit");// 工作单位
			workInfo.put("site", "workPlace");// 工作地点
			workInfo.put("starttimeString", "workStartTime");// 工作开始时间
			workInfo.put("endtimeString", "workEndTime");// 工作结束时间
			workInfo.put("section", "workDept");// 工作科室 dept-type
			workInfo.put("holdoffice", "jobSituation");// 任职情况 duty
			workInfo.put("position", "workPost");// 工作岗位 work-post-type
			workInfo.put("worktec", "majorTechnologyWork");// 从事专业技术工作 major
			// put("flowcode","status");//审核状态 personal-info-status
		}
	}

	public List<OrgPersonnelWorkExperienceCrawler> crawl(String personnelId) throws IOException {
		List<OrgPersonnelWorkExperienceCrawler> workExperienceCrawlers = new ArrayList<>();
		Document doc = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid=" + personnelId + "&exptype=gzjl_div", null, Connection.Method.POST,
				true);
		List<Map<String, Object>> maps = parseDoc(doc);
		if (maps != null && maps.size() > 0) {
			String jsonString;
			OrgPersonnelWorkExperienceCrawler orgPersonnelWorkExperienceCrawler;
			for (Map<String, Object> map : maps) {
				jsonString = JSON.toJSONString(map);
				orgPersonnelWorkExperienceCrawler = JSON.parseObject(jsonString, new TypeReference<OrgPersonnelWorkExperienceCrawler>() {
				});

				orgPersonnelWorkExperienceCrawler.setId(orgPersonnelWorkExperienceCrawler.getId());
				orgPersonnelWorkExperienceCrawler.setPersonnelId(personnelId);
				orgPersonnelWorkExperienceCrawler.setUrl(readAttachment(orgPersonnelWorkExperienceCrawler.getId(), "work"));

				workExperienceCrawlers.add(orgPersonnelWorkExperienceCrawler);
			}
		}
		return workExperienceCrawlers;
	}

	public List<Map<String, Object>> parseDoc(Document document) {
		Elements forms = document.getElementsByTag("form");
		List<Map<String, Object>> workeMaps = new ArrayList<>();
		Map<String, Object> workeMap;
		if (forms != null && forms.size() > 0) {
			for (int i = 0; i < forms.size() - 1; i++) {
				workeMap = new HashMap<>(workInfo.size());
				Element form = forms.get(i);
				for (Map.Entry<String, String> coloumn : workInfo.entrySet()) {
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
								if ("section".equals(key)) {
									type = ConstantsContainer.getTypeKey("dept-type", text);
									workeMap.put(value1, type);
								} else if ("holdoffice".equals(key)) {
									type = ConstantsContainer.getTypeKey("duty", text);
									workeMap.put(value1, type);
								} else if ("position".equals(key)) {
									type = ConstantsContainer.getTypeKey("work-post-type", text);
									workeMap.put(value1, type);
								} else {
									type = ConstantsContainer.getTypeKey("major", text);
									workeMap.put(value1, type);
								}
							}
						} else {
							String value = element.attr("value");
							workeMap.put(value1, value);
						}
					} else {
						System.out.println(key + "获取不到e");
					}
				}
				String text = form.getElementsByTag("font").first().text();
				Integer typeKey = ConstantsContainer.getTypeKey("personal-info-status", text);
				workeMap.put("status", typeKey);
				workeMaps.add(workeMap);
			}
		}
		return workeMaps;
	}

}
