package com.paladin.hrms.crawler;

import com.paladin.framework.utils.StringUtil;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jisanjie
 * @version 2018年12月11日 下午3:38:32
 */
public class AgencyPageCrawler extends HrmsCrawler {

	private final static String url = "http://58.213.112.246/rlzy/hospital/query";

	private int pageSize = 100;
	private int currentPage = 1;

	private List<String> agencyIds;
	private Map<String, String> idAndName;

	public void read() throws IOException {
		agencyIds = new ArrayList<>();
		idAndName = new HashMap<>();
		int total = readPage();
		while (idAndName.size() < total) {
			currentPage++;
			total = readPage();
		}
	}

	private int readPage() throws IOException {
		Map<String, String> data = new HashMap<>();
		data.put("ec_i", "ec");
		data.put("ec_crd", String.valueOf(pageSize));
		data.put("ec_p", String.valueOf(currentPage));
		data.put("ec_rd", String.valueOf(pageSize));
		Document doc = getDocument(url, data, Method.POST, false);
		return parseDocument(doc);
	}

	// private static Pattern getIdPattern = Pattern.compile("( b +
	// '=[0-9a-zA-z-_]{0,}').exec(a).toString().split('=')[1]");

	private int parseDocument(Document doc) throws IOException {

		Element table = doc.getElementsByClass("tableBody").first();
		Elements trs = table.getElementsByTag("tr");
		for (Element tr : trs) {
			String href = tr.getElementsByTag("a").first().attr("href");
			String agencyId = href.replaceAll(".*?organcode=([^&]+).*$", "$1");
			String agencyName = tr.child(0).text();
			idAndName.put(agencyName, agencyId);
			agencyIds.add(agencyId);
		}

		Elements pageTd = doc.getElementsByClass("statusBar");
		String text = pageTd.get(0).text();
		text = repairHtmlText(text);

		String totalText = text.substring(2, text.indexOf("条"));
		totalText = totalText.trim();
		totalText = totalText.replaceAll(",", "");

		return Integer.valueOf(totalText);
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

	public List<String> getAgencyIds() throws IOException {
		if(agencyIds == null) {
			read();
		}
		return agencyIds;
	}

	public Map<String, String> getIdAndName() throws IOException {
		if(idAndName == null) {
			read();
		}
		return idAndName;
	}
}
