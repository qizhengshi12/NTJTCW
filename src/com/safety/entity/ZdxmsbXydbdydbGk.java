package com.safety.entity;

import java.sql.Date;

public class ZdxmsbXydbdydbGk {
	private int id;//重大项目申报——信誉担保和重大资产抵押担保——概况
	private String mc;//信誉（资产）名称
	private Date pgsj;//信誉（资产）评估时间
	private String jz;//信誉（资产）价值
	private String dbed;//担保额度
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public Date getPgsj() {
		return pgsj;
	}
	public void setPgsj(Date pgsj) {
		this.pgsj = pgsj;
	}
	public String getJz() {
		return jz;
	}
	public void setJz(String jz) {
		this.jz = jz;
	}
	public String getDbed() {
		return dbed;
	}
	public void setDbed(String dbed) {
		this.dbed = dbed;
	}

}
