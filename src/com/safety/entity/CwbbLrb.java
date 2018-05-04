package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class CwbbLrb {
	private int id;
	private String bt;//标题
	private String czr;
	private Timestamp czsj;
	private String czrID;
	private String czrdw;
	private Date sbsj;
	private String hzbz;//后缀标识
	private String tjzt;//提交状态（1：提交2：保存草稿）
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
	public Date getSbsj() {
		return sbsj;
	}
	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}
	public String getHzbz() {
		return hzbz;
	}
	public void setHzbz(String hzbz) {
		this.hzbz = hzbz;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
}
