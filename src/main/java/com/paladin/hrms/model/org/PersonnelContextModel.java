package com.paladin.hrms.model.org;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PersonnelContextModel extends PersonnelModel{
	
    public final static String COLUMN_FIELD_PERSONNEL_ID = "personnelId";
    
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	
	private String personnelId;

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
