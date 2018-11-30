package com.paladin.hrms.model.org;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.paladin.framework.utils.reflect.NameUtil;
import com.paladin.framework.utils.reflect.ReflectUtil;

public class OrgPersonnelUtil {

	public static class JsModel {

		private String name;
		private String inputType;
		private String columnName;

		public String getInputType() {
			return inputType;
		}

		public void setInputType(String inputType) {
			this.inputType = inputType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
	}

	public static List<JsModel> getJsModel(Class<?> clazz) {

		if (clazz != null) {

			List<JsModel> result = new ArrayList<>();

			while (clazz != null || clazz == Object.class) {
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {

					if (!ReflectUtil.isStatic(field) && field.getAnnotation(Transient.class) == null) {
						String property = null;

						Column columnAnno = field.getAnnotation(Column.class);
						if (columnAnno != null) {
							if (columnAnno.name().length() != 0) {
								property = columnAnno.name();
							}
						}

						if (property == null) {
							property = field.getName();
						}

						String column = NameUtil.hump2underline(property);
						String inputType = "TEXT";
						Class<?> type = field.getType();
						if (type == int.class || type == Integer.class) {
							inputType = "SELECT";
						} else if (Date.class.isAssignableFrom(type)) {
							inputType = "DATE";
						}

						JsModel model = new JsModel();

						model.name = property;
						model.inputType = inputType;
						model.columnName = column;
						
						result.add(model);
					}
				}
				clazz = clazz.getSuperclass();
			}

			return result;
		}
		return null;
	}

}
