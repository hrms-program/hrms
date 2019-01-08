package com.paladin.hrms.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.crawler.pojo.OrgPersonnelJobCrewler;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <工作信息爬取>
 *
 * @author Huangguochen
 * @create 2018/12/12 14:24
 */
public class PersonnelJobInfoCrawler extends PersonnelHrmsCrawler {
	private static final Map<String, String> jobInfo = new HashMap<String, String>(16);

	static {
		jobInfo.put("work_card_id", "employeeNo");// 工号
		jobInfo.put("sectionid", "dept");// 所在科室
		jobInfo.put("subsection", "deptName");// 科室具体名称
		jobInfo.put("hospitalname", "agencyName");// 机构名称
		jobInfo.put("praclevel", "major");// 从事专业类别
		jobInfo.put("administrationcode", "duty");// 行政／业务管理职务
		jobInfo.put("telqualificationname", "technicalQualification");// 专业技术资格（评或考试）
		jobInfo.put("telqualificationtimeString", "gainDate");// 专业技术资格取得时间
		jobInfo.put("technicaltitle", "techPost");// 专业技术职务（聘)
		jobInfo.put("technicaltitletimeString", "employDate");// 专业技术聘用时间
		jobInfo.put("position", "employPost");// 专业技术职务聘用岗--位
		jobInfo.put("organizationcode", "formation");// 编制情况
		jobInfo.put("flowcode", "inorout");// 年内人员流动情况
		jobInfo.put("flowtimeString", "inoroutDate");// 流入/流出时间
		jobInfo.put("tjxxh", "isStatistical");// 是否从事统计信息化业务工作： boolean
		jobInfo.put("tjxxhContent", "workContent");// 从事统计信息化业务工作内容： string
	};

	public OrgPersonnelJobCrewler crewl(String personnelId) throws IOException {

		Document doc = getDocument("http://58.213.112.246/rlzy/medicalperson/load?id=" + personnelId + "&type=workinfo", null, Connection.Method.POST, true);
		Map<String, Object> orgpersonjob = parseDoc(doc);
		String jobInfo = JSON.toJSONString(orgpersonjob);
		OrgPersonnelJobCrewler orgPersonnelJobCrewler = JSON.parseObject(jobInfo, new TypeReference<OrgPersonnelJobCrewler>() {
		});

		orgPersonnelJobCrewler.setId(personnelId);
		orgPersonnelJobCrewler.setUrl(readAttachment(personnelId, "medicalperson"));

		return orgPersonnelJobCrewler;
	}

	public Map<String, Object> parseDoc(Document document) {
		Element gzxxDiv = document.getElementById("gzxx_form");
		Map<String, Object> jobMap = new HashMap<>(jobInfo.size());
		if (gzxxDiv != null && gzxxDiv.hasText()) {
			for (Map.Entry<String, String> coloumn : jobInfo.entrySet()) {
				String key = coloumn.getKey();
				String value1 = coloumn.getValue();
				Element element = gzxxDiv.getElementById(key);
				if (element != null) {
					if ("select".equals(element.tagName())) {
						Integer type;
						String text;
						Element select = element.selectFirst("option[selected]");
						if (select != null) {
							text = select.text();
							if ("tjxxhContent".equals(key)) {
								jobMap.put(value1, text);
							} else if ("tjxxh".equals(key)) {
								type = ConstantsContainer.getTypeKey("boolean", text);
								jobMap.put(value1, type);
							} else {
								type = ConstantsContainer.getTypeKey(StringUtil.camelhumpToMiddleline(value1), text);
								jobMap.put(value1, type);
							}
						}
					} else {
						String value = element.attr("value");
						jobMap.put(value1, value);
					}
				} else {
					System.out.println(key + "获取不到e");
				}
			}
		}
		return jobMap;
	}
}
