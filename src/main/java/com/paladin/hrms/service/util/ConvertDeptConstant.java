package com.paladin.hrms.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paladin.framework.excel.ConvertException;
import com.paladin.framework.excel.ICell;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ReadPropertyConvert;
import com.paladin.framework.excel.write.ValueFormator;

/**
 * Excel表中所在科室中的name转value
 * 
 * @author 蒋恒
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConvertDeptConstant implements ReadPropertyConvert<String>, ValueFormator {

	private final static Map<String, String> key2name;
	private final static Map<String, String> name2key;

	static {
		String jsonStr = null;
		key2name = new HashMap<String, String>();
		name2key = new HashMap<String, String>();
		try {
			InputStream input = ConvertTechQualificationConstant.class.getResourceAsStream("personal_tree_constant.json");
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
				StringBuilder sb = new StringBuilder();
				for (;;) {
					String line = reader.readLine();
					if (line == null)
						break;
					sb.append(line);
				}
				jsonStr = sb.toString();
			}
			Map map = (Map) JSONObject.parseObject(jsonStr);
			JSONArray json = (JSONArray) map.get("department-type");
			for (Object jsonObject : json) {
				Map<String, Object> mapObj = (Map<String, Object>) jsonObject;
				String name = mapObj.get("name").toString();
				String value = mapObj.get("value").toString();				
				name2key.put(name,value);
				key2name.put(value,name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String convert(ICell cell) throws ExcelReadException {
	      String val = null;
		if (cell != null) {
			String value;
			try {
				value = cell.getString();
				if (value != null && value.length() != 0) {
					 val = name2key.get(value);
					if (val == null) {
						throw new ExcelReadException("所在科室不正确");
					}
				}
			} catch (ConvertException e) {
				throw new ExcelReadException("所在科室不正确");
			}
		}
		return val;
	}

	@Override
	public String format(Object obj) {	
		if(obj != null) {
			return key2name.get(obj.toString());
		}
		return "";
	}
}
