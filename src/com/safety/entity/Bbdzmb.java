package com.safety.entity;

import java.sql.Timestamp;

public class Bbdzmb {
	private int id;//报表定制模板
	private String bt;//标题
	private String lx;//财务报表、审计报表、统计报表
	private String czr;
	private Timestamp czsj;
	private String czrID;
	private String czrdw;
	private int dzls;//定制报表列数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
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
	public int getDzls() {
		return dzls;
	}
	public void setDzls(int dzls) {
		this.dzls = dzls;
	}
	
}
