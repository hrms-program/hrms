package com.paladin.hrms.crawler;

import com.paladin.framework.core.container.ConstantsContainer;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonnelPagerCrawler extends HrmsCrawler {

	private final static String url = "http://58.213.112.246/rlzy/medicalperson/query";

	private int pageSize = 100;
	private int currentPage = 1;

	private List<String> personnelIds;
	private Map<String, AdditionalData> otherColumnMap;

	int total = -1;

	public void read() throws IOException {
		personnelIds = new ArrayList<>();
		otherColumnMap = new HashMap<>();
		int t = readPage();
		if (t > total) {
			total = t;
		}
		while (personnelIds.size() < total) {
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

	// 找到想要的ID，获取到类似delcfm('320000001852333671'字符串
	private static Pattern getIdPattern = Pattern.compile("delcfm\\('([^']+)'");

	private int parseDocument(Document doc) {
		Elements tables = doc.getElementsByClass("tableBody");
		for (Element table : tables) {
			Elements trs = table.getElementsByTag("tr");
			for (Element tr : trs) {
				String trText = tr.html();
				Matcher matcher = getIdPattern.matcher(trText);
				if (matcher.find()) {
					String idText = matcher.group();
					// 获取到类似delcfm('320000001852333671'字符串
					idText = idText.substring(8, idText.length() - 1);
					personnelIds.add(idText);
					String text = tr.child(5).text();
					Integer type = ConstantsContainer.getTypeKey("personnel-type", text);
					otherColumnMap.put(idText, new AdditionalData(type, text, tr.child(3).text()));
				}
			}
		}

		Elements pageTd = doc.getElementsByClass("statusBar");
		String text = pageTd.get(0).text();
		text = repairHtmlText(text);

		String totalText = text.substring(2, text.indexOf("条"));
		totalText = totalText.trim();
		totalText = totalText.replaceAll(",", "");

		return Integer.valueOf(totalText);
	}

	public List<String> getPersonnelIds() throws IOException {
		if (personnelIds == null) {
			read();
		}
		return personnelIds;
	}

	public Map<String, AdditionalData> getAdditionalData() throws IOException {
		if (otherColumnMap == null) {
			read();
		}
		return otherColumnMap;
	}

	public static class AdditionalData {
		private Integer type;
		private String typeStr;
		private String agencyName;

		public AdditionalData(Integer type, String typeStr, String agencyName) {
			this.type = type;
			this.typeStr = typeStr;
			this.agencyName = agencyName;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getAgencyName() {
			return agencyName;
		}

		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
		}

		public String getTypeStr() {
			return typeStr;
		}

		public void setTypeStr(String typeStr) {
			this.typeStr = typeStr;
		}
	}

}
