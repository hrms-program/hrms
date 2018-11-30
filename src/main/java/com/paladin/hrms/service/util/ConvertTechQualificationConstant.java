package com.paladin.hrms.service.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConvertTechQualificationConstant implements ReadPropertyConvert<String>, ValueFormator {

	private final static Map<String, String> key2name;
	private final static Map<String, String> name2key;

	static {
		File jsonFile = null;
		String jsonStr = null;
		key2name = new HashMap<String, String>();
		name2key = new HashMap<String, String>();
		try {
			jsonFile = ResourceUtils.getFile("classpath:static/js/hrms/org/personal_tree_constant.json");
			jsonStr = FileUtils.readFileToString(jsonFile, "UTF-8");
			Map map = (Map) JSONObject.parseObject(jsonStr);
			JSONArray json = (JSONArray) map.get("technical-qualification-type");
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
				if(value != null && value.length() != 0) {
					 val = name2key.get(value);
					if(val == null) {
						throw new ExcelReadException("专业技术资格评代码不正确");
					}
				}
			} catch (ConvertException e) {
				throw new ExcelReadException("专业技术资格评代码不正确\"");
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
