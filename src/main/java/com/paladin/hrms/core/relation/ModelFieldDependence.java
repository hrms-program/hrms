package com.paladin.hrms.core.relation;

public class ModelFieldDependence {
	
	private Relation relation;
	private String[] values;
	
	public ModelFieldDependence(Relation relation, String... values) {
		this.relation = relation;
		this.values = values;	
	}
	
	public ModelFieldDependence(String relation, String... values) {
		this.relation = Relation.valueOf(relation);
		this.values = values;	
	}
	
	public Relation getRelation() {
		return relation;
	}
	public String[] getValues() {
		return values;
	}
}
