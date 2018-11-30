package com.paladin.hrms.service.util;

public class IdentificationUtil {

	public static String replacePartForHidden(String str) {
		if (str != null) {
			int len = str.length();
			if (len > 0) {
				if (len <= 1) {
					return "*";
				} else if (len == 2) {
					return replace(str, 0, 1);
				} else if (len >= 3 && len <= 6) {
					return replace(str, 1, 1);
				} else if (len == 7) {
					return replace(str, 1, 2);
				} else if (len == 8) {
					return replace(str, 2, 2);
				} else if (len == 9) {
					return replace(str, 2, 3);
				} else if (len == 10) {
					return replace(str, 3, 3);
				} else if (len >= 11) {
					return replace(str, 3, 4);
				}
			}
		}
		return "";
	}

	private static String replace(String str, int a, int b) {
		byte[] bytes = str.getBytes();

		int end = bytes.length - b;
		for (int i = a; i < end; i++) {
			bytes[i] = '*';
		}

		return new String(bytes);
	}

}
