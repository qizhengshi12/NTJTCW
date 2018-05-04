package com.safety.entity;

import java.sql.Timestamp;

public class SjbbLssfzcHzb {
	private int id;//审计报表——市级机关（含下属事业单位）落实收费政策季度汇总表
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String czrphone;//联系电话
	private int year;//时间
	private int jd;//季度
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public Timestamp getCzsj() {
		return czsj;
	}
	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
	public String getCzrID() {
		return czrID;
	}
	public void setCzrID(String czrID) {
		this.czrID = czrID;
	}
	public String getCzrdw() {
		return czrdw;
	}
	public void setCzrdw(String czrdw) {
		this.czrdw = czrdw;
	}
	public String getCzrphone() {
		return czrphone;
	}
	public void setCzrphone(String czrphone) {
		this.czrphone = czrphone;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getJd() {
		return jd;
	}
	public void setJd(int jd) {
		this.jd = jd;
	}

}
