package com.paladin.hrms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfig {

	private static String defaultValuePassword = "1";

	public static String getDefaultPassword() {
		return defaultValuePassword;
	}

	@Value("${default.value.password}")
	public void setDefaultValuePassword(String defaultValuePassword) {
		BusinessConfig.defaultValuePassword = defaultValuePassword;
	}

}
