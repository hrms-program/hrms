package com.paladin.hrms.core.relation;

import java.util.List;

public class ModelField {
	
	/**输入框，字符串*/
	public static final int TYPE_INPUT_STRING = 1;
	/**输入框，数字*/
	public static final int TYPE_INPUT_NUMBER = 2;
	/**单选框*/
	public static final int TYPE_SELECT = 3;
	/**多选框*/
	public static final int TYPE_MULTISELECT = 4;
	
	
	public ModelField(String model, String name, int type) {
		this.model = model;
		this.name = name;
		this.type = type;
	}
	
	private String model;
	private String name;
	private int type;
	private String title;
	private String comment;
	
	// 枚举常量类型，类型为选择框时
	private String enumType;
	private boolean required;
	private String defaultValue;
	// 排序号
	private int orderIndex;
	
	// 依赖，满足依赖条件，才能有效
	private List<ModelFieldDependence> dependences;

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public List<ModelFieldDependence> getDependences() {
		return dependences;
	}

	public void setDependences(List<ModelFieldDependence> dependences) {
		this.dependences = dependences;
	}
	
	
}
