package com.safety.entity;

import java.sql.Timestamp;

public class TjbbYxzbHsZdhz {
	private int id;//统计报表——海事签证——站点签证——汇总
	private int year;//年份
	private int month;//月份
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private int zdgs;//站点个数
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
	public int getZdgs() {
		return zdgs;
	}
	public void setZdgs(int zdgs) {
		this.zdgs = zdgs;
	}
}
