package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Bbdz {
	private int id;//根据模板新建的报表
	private String bt;//标题
	private Date sj;//上报时间
	private String czr;
	private Timestamp czsj;
	private String czrID;
	private String czrdw;
	private int bbls;//报表列数
	private int zbid;//对应的主表ID
	private String tjzt;//提交状态
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
	public Date getSj() {
		return sj;
	}
	public void setSj(Date sj) {
		this.sj = sj;
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
	public int getZbid() {
		return zbid;
	}
	public void setZbid(int zbid) {
		this.zbid = zbid;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
