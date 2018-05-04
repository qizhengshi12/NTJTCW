package com.safety.entity;

import java.sql.Timestamp;

public class Gzflsr {
	private int id;//工资福利收入
	private int year;//年份
	private int month;//月份
	private String xmid;//项目ID
	private String czr;//操作人
	private String czrID;//操作人ID
	private Timestamp czsj;//操作时间
	private String czrdw;//操作人单位
	private String tjzt;//提交状态（1：提交2：保存草稿）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String getCzrID() {
		return czrID;
	}
	public void setCzrID(String czrID) {
		this.czrID = czrID;
	}
	public Timestamp getCzsj() {
		return czsj;
	}
	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
	public String getCzrdw() {
		return czrdw;
	}
	public void setCzrdw(String czrdw) {
		this.czrdw = czrdw;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
