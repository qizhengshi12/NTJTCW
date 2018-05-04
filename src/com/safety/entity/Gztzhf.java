package com.safety.entity;

import java.sql.Timestamp;

public class Gztzhf {
	private int id;//工作通知回复
	private String hfr;//回复人
	private String hfrID;//回复人ID
	private Timestamp hfsj;//回复时间
	private String hfnr;//回复内容
	private int tzid;//通知ID
	private String tzmc;//通知名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHfr() {
		return hfr;
	}
	public void setHfr(String hfr) {
		this.hfr = hfr;
	}
	public String getHfrID() {
		return hfrID;
	}
	public void setHfrID(String hfrID) {
		this.hfrID = hfrID;
	}
	public Timestamp getHfsj() {
		return hfsj;
	}
	public void setHfsj(Timestamp hfsj) {
		this.hfsj = hfsj;
	}
	public String getHfnr() {
		return hfnr;
	}
	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}
	public int getTzid() {
		return tzid;
	}
	public void setTzid(int tzid) {
		this.tzid = tzid;
	}
	public String getTzmc() {
		return tzmc;
	}
	public void setTzmc(String tzmc) {
		this.tzmc = tzmc;
	}

}
