package com.paladin.hrms.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PersonnelHrmsCrawler extends HrmsCrawler {

	public List<String> readAttachment(String id, String type) throws IOException {
		List<String> urls = new ArrayList<>();
		
		Object json = getJson("http://58.213.112.246/rlzy/accessory/detail.json?id=" + id + "&type=" + type, null, Connection.Method.POST, true);
		String jsonStr = JSON.toJSONString(json);
		JSONObject object = JSONObject.parseObject(jsonStr);
		JSONArray array = object.getJSONArray("accList");
		if (array != null && array.size() > 0) {
			urls = new ArrayList<>(array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				urls.add("58.213.112.246/rlzy/accessory/download?id=" + jsonObject.get("id"));
			}
		}
		return urls;
	}
}
