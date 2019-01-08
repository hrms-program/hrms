package com.paladin.hrms.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.crawler.pojo.OrgPersonnelCultivateCrawler;

/**
 * @author 黄伟华
 * @version 2018年12月12日 下午3:19:00
 */
public class CultivateCrawler extends HrmsCrawler {

	public List<OrgPersonnelCultivateCrawler> crawl(String personnelId) throws IOException {

		List<OrgPersonnelCultivateCrawler> cultivate = new ArrayList<OrgPersonnelCultivateCrawler>();

		Document d = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid=" + personnelId + "&exptype=pxjl_div", null, Method.POST, true);
		Elements tables = d.getElementsByClass("li-text");

		for (Element table : tables) {
			OrgPersonnelCultivateCrawler crawler = new OrgPersonnelCultivateCrawler();
			String id = table.getElementById("id").val();

			Object list = getJson("http://58.213.112.246/rlzy/accessory/detail.json?id=" + id + "&type=train", null, Method.POST, true);
			String aa = JSON.toJSONString(list);
			JSONObject object = JSONObject.parseObject(aa);
			JSONArray array = object.getJSONArray("accList");

			List<String> url = new ArrayList<>();
			for (int i = 0; i < array.size(); i++) {
				JSONObject o = array.getJSONObject(i);
				url.add("58.213.112.246/rlzy/accessory/download?id=" + o.get("id"));
			}

			String edutype = table.getElementById("edutype").getElementsByAttribute("selected").text();
			crawler.setCultivateType(ConstantsContainer.getTypeKey("cultivate-type", edutype));
			crawler.setCultivateUnit(table.getElementById("unitname").val());
			crawler.setCultivatePlace(table.getElementById("site").val());
			crawler.setCultivateStartTime(toDate(table.getElementById("starttimeString").val()));
			crawler.setCultivateEndTime(toDate(table.getElementById("endtimeString").val()));

			String endtype = table.getElementById("endtype").getElementsByAttribute("selected").text();
			crawler.setEndSituation(ConstantsContainer.getTypeKey("cultivete-end-situation", endtype));

			crawler.setStatus(ConstantsContainer.getTypeKey("personal-info-status", table.getElementsByTag("font").text()));
			crawler.setUrl(url);
			cultivate.add(crawler);
		}
		return cultivate;
	}

}
