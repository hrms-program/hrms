package com.paladin.hrms.core.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;

@Component
public class ModelFieldContainer implements SpringContainer  {

	
	
	
	public boolean initialize() {
		
		Map<String, List<ModelField>> modelMap = new HashMap<>();
		
		modelMap.get("ss");
		return true;
	};

}
