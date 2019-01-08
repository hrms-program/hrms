package com.paladin.hrms.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.paladin.hrms.crawler.pojo.OrgPersonnelPracticeRegistrationCrawler;

/**
 * @author 黄伟华
 * @version 2018年12月17日 下午2:05:08
 */
public class PracticeRegistrationCrawler extends HrmsCrawler {

	public List<OrgPersonnelPracticeRegistrationCrawler> crawl(String perId) throws IOException {
		List<OrgPersonnelPracticeRegistrationCrawler> reg = new ArrayList<OrgPersonnelPracticeRegistrationCrawler>();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map;
		HrmsCrawler a = new HrmsCrawler();
		Document d = a.getDocument("http://58.213.112.246/rlzy/medicalperson/show?id=" + perId + "", null, Method.POST, true);
		Elements tables = d.getElementsByClass("background-info-table");
		for (Element table : tables) {
			map = new HashMap<String, Object>();
			Elements trs = table.getElementsByTag("tr");
			for (Element tr : trs) {
				if (tr.select("td").size() >= 4) {
					map.put(tr.select("td").get(0).text(), tr.select("td").get(1).text());
					map.put(tr.select("td").get(2).text(), tr.select("td").get(3).text());
				} else {
					map.put(tr.select("td").get(0).text(), tr.select("td").get(1).text());
				}
			}
			list.add(map);
		}
		OrgPersonnelPracticeRegistrationCrawler o = new OrgPersonnelPracticeRegistrationCrawler();
		for (Map<String, Object> data : list) {
			o.setCertificateCode(data.get("执业证书编码：") != null ? data.get("执业证书编码：").toString() : null);
			o.setPlaceName(data.get("执业地点名称：") != null ? data.get("执业地点名称：").toString() : null);
			o.setCategory(data.get("执业类别：") != null ? data.get("执业类别：").toString() : null);
			o.setIssuingOrgan(data.get("发证机关：") != null ? data.get("发证机关：").toString() : null);
			o.setBusinessType(data.get("业务类型：") != null ? data.get("业务类型：").toString() : null);
			o.setQualificationCertificateCode(data.get("执业资格证书编号：") != null ? data.get("执业资格证书编号：").toString() : null);
			o.setPracticeRange(data.get("执业范围：") != null ? data.get("执业范围：").toString() : null);
			o.setLevel(data.get("执业级别：") != null ? data.get("执业级别：").toString() : null);
			o.setFirstRegisterTime(data.get("首次注册时间：") != null ? toDate(data.get("首次注册时间：").toString()) : null);
			o.setPracticeAddress(data.get("执业地点地址：") != null ? data.get("执业地点地址：").toString() : null);
			o.setRegisterIdCard(data.get("执业注册身份证：") != null ? data.get("执业注册身份证：").toString() : null);
			o.setAddress(data.get("地址：") != null ? data.get("地址：").toString() : null);
			o.setDistrictCode(data.get("行政区划代码：") != null ? data.get("行政区划代码：").toString() : null);
			o.setDistrictName(data.get("行政区划名称：") != null ? data.get("行政区划名称：").toString() : null);
			o.setCreationTime(data.get("创建时间：") != null ? toDate(data.get("创建时间：").toString()) : null);

			if (data.get("最后更新时间：") != null && !data.get("最后更新时间：").toString().equals("无")) {
				o.setLastUpdateTime(toDate(data.get("最后更新时间：").toString()));
			} else {
				o.setLastUpdateTime(null);
			}
			o.setDateOfIssue(data.get("发证日期：") != null ? toDate(data.get("发证日期：").toString()) : null);
			o.setTechnicalTitle(data.get("技术职称：") != null ? data.get("技术职称：").toString() : null);
			o.setCurrentBusinessApprovalDate(data.get("当前业务批准日期：") != null ? toDate(data.get("当前业务批准日期：").toString()) : null);
			o.setCurrentBusinessApprovalOrgans(data.get("当前业务审批机关：") != null ? data.get("当前业务审批机关：").toString() : null);
			o.setWorkingDepartment(data.get("工作科室：") != null ? data.get("工作科室：").toString() : null);
			reg.add(o);
		}

		return reg;
	}
}
