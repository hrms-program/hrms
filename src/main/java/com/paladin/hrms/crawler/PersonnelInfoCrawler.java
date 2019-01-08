package com.paladin.hrms.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.model.org.OrgPersonnel;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <人员信息爬取>
 *
 * @author Huangguochen
 * @create 2018/12/12 13:41
 */
public class PersonnelInfoCrawler extends HrmsCrawler {
	private static final Map<String, String> baseInfo = new HashMap<String, String>();
	static {
		baseInfo.put("id", "id");// id
		baseInfo.put("unitname", "name");// 姓名
		baseInfo.put("gender", "sex");// 性别
		baseInfo.put("nation", "nationality");// 国籍
		baseInfo.put("folk", "nation");// 民族
		baseInfo.put("formname", "usedName");// 曾用名
		baseInfo.put("idcardtype", "identificationType");// 身份证件种类
		baseInfo.put("idcard", "identificationNo");// 身份证件号码
		baseInfo.put("birthdateString", "birthday");// 出生日期
		baseInfo.put("joinworkdateString", "startWorkTime");// 参加工作日期
		baseInfo.put("joinpartydateString", "joinPartyTime");// 参加党派时间
		baseInfo.put("politicallandscape", "politicalAffiliation");// 政治面貌
		baseInfo.put("phone", "cellphone");// 手机号码
		baseInfo.put("hobbies", "interest");// 兴趣爱好
		baseInfo.put("contel", "officePhone");// 办公室电话
		baseInfo.put("nativePlace", "nativePlace");// 籍贯
	};

	public OrgPersonnel crawl(String personnelId) throws IOException {
		Document doc = getDocument("http://58.213.112.246/rlzy/medicalperson/show?id=" + personnelId + "&type=show", null, Connection.Method.GET, false);
		Map<String, Object> orgperson = parseDocument(doc);
		String baseInfo = JSON.toJSONString(orgperson);
		OrgPersonnel orgPersonnel = JSON.parseObject(baseInfo, new TypeReference<OrgPersonnel>() {
		});
		return orgPersonnel;
	}

	public Map<String, Object> parseDocument(Document doc) {
		Element jbxiDiv = doc.getElementById("jbxi_form");
		Map<String, Object> baseMap = new HashMap<>(baseInfo.size());
		if (jbxiDiv != null && jbxiDiv.hasText()) {
			for (Map.Entry<String, String> coloumn : baseInfo.entrySet()) {
				String key = coloumn.getKey();
				String value1 = coloumn.getValue();
				Element element = jbxiDiv.getElementsByAttributeValue("name", key).first();
				if (element != null) {
					if ("select".equals(element.tagName())) {
						Integer typeKey = null;
						Element select = element.selectFirst("option[selected]");
						if (select != null) {
							String text = select.text();
							typeKey = ConstantsContainer.getTypeKey(StringUtil.camelhumpToMiddleline(value1), text);
						}
						baseMap.put(value1, typeKey);
					} else {
						String value = element.attr("value");
						baseMap.put(coloumn.getValue(), value);
					}
				}
			}
		}
		return baseMap;
	}
}
