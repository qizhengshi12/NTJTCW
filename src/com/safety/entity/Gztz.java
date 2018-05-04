package com.safety.entity;

import java.sql.Timestamp;

public class Gztz {
	private int id;//工作通知
	private String tzmc;//通知名称
	private String tznr;//通知内容
	private Timestamp tzsj;//工作时间
	private String tzdd;//通知地点
	private String tzry;//通知人员
	private String tzryID;//通知人员ID
	private String czr;//操作人
	private String czrID;//操作人ID
	private Timestamp czsj;//操作时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTzmc() {
		return tzmc;
	}
	public void setTzmc(String tzmc) {
		this.tzmc = tzmc;
	}
	public String getTznr() {
		return tznr;
	}
	public void setTznr(String tznr) {
		this.tznr = tznr;
	}
	public Timestamp getTzsj() {
		return tzsj;
	}
	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}
	public String getTzdd() {
		return tzdd;
	}
	public void setTzdd(String tzdd) {
		this.tzdd = tzdd;
	}
	public String getTzry() {
		return tzry;
	}
	public void setTzry(String tzry) {
		this.tzry = tzry;
	}
	public String getTzryID() {
		return tzryID;
	}
	public void setTzryID(String tzryID) {
		this.tzryID = tzryID;
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

}
