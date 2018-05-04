package com.safety.entity;

import java.sql.Timestamp;

public class Njfb {
	private int id;//年鉴发布
	private String bt;//标题
	private int year;//年鉴日期
	private String fatherid;//树节点ID
	private String czr;
	private Timestamp czsj;
	private String czrID;
	private String czrdw;
	private int bbls;//报表列数
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
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
	public int getBbls() {
		return bbls;
	}
	public void setBbls(int bbls) {
		this.bbls = bbls;
	}

}
