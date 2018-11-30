package com.paladin.hrms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParamUtil {
	
	/**
	 * 解析多选常量代码
	 * @param codeString
	 * @return
	 */
	public static List<Integer> parseMultiSelectConstantCode(String codeString){
		if(codeString != null && codeString.length() >0) {
			String[] codes = codeString.split(",");
			List<Integer> result = new ArrayList<>(codes.length);
			for(String code: codes) {
				result.add(Integer.valueOf(code));
			}
			return result;		
		}
		return null;
	}
	
	
	/**
	 * 解析多选常量代码
	 * @param codeString
	 * @return
	 */
	public static List<String> parseMultiSelectConstantKey(String codeString){
		if(codeString != null && codeString.length() >0) {
			String[] codes = codeString.split(",");
			return Arrays.asList(codes);
		}
		return null;
	}
	
	
}
