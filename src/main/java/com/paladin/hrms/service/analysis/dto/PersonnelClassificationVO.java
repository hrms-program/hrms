package com.paladin.hrms.service.analysis.dto;

public class PersonnelClassificationVO {
	
		private String agencyId;
		
		private String agencyName;

		// 合计
		private Integer total;

		// 医师
		private Integer ys;

		// 执业医师
		private Integer zyys;

		// 全科医生
		private Integer qkys;

		// 护师
		private Integer hs;

		// 乡村医生
		private Integer xcys;

		// 药师
		private Integer yaos;

		// 技师
		private Integer js;

		// 检验师
		private Integer jys;

		// 管理
		private Integer gl;

		// 工勤
		private Integer gq;

		// 其他
		private Integer qt;

		// 见习医生
		private Integer jxys;

		public String getAgencyId() {
			return agencyId;
		}

		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getYs() {
			return ys;
		}

		public void setYs(Integer ys) {
			this.ys = ys;
		}

		public Integer getZyys() {
			return zyys;
		}

		public void setZyys(Integer zyys) {
			this.zyys = zyys;
		}

		public Integer getQkys() {
			return qkys;
		}

		public void setQkys(Integer qkys) {
			this.qkys = qkys;
		}

		public Integer getHs() {
			return hs;
		}

		public void setHs(Integer hs) {
			this.hs = hs;
		}

		public Integer getXcys() {
			return xcys;
		}

		public void setXcys(Integer xcys) {
			this.xcys = xcys;
		}

		public Integer getYaos() {
			return yaos;
		}

		public void setYaos(Integer yaos) {
			this.yaos = yaos;
		}

		public Integer getJs() {
			return js;
		}

		public void setJs(Integer js) {
			this.js = js;
		}

		public Integer getJys() {
			return jys;
		}

		public void setJys(Integer jys) {
			this.jys = jys;
		}

		public Integer getGl() {
			return gl;
		}

		public void setGl(Integer gl) {
			this.gl = gl;
		}

		public Integer getGq() {
			return gq;
		}

		public void setGq(Integer gq) {
			this.gq = gq;
		}

		public Integer getQt() {
			return qt;
		}

		public void setQt(Integer qt) {
			this.qt = qt;
		}

		public Integer getJxys() {
			return jxys;
		}

		public void setJxys(Integer jxys) {
			this.jxys = jxys;
		}

		public String getAgencyName() {
			return agencyName;
		}

		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
		}

}
