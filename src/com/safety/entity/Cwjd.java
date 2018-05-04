package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Cwjd {
	private int id;//财务监督
	private String wjmc;//工作名称
	private String wjnr;//工作内容
	private String FileUrl;//附件下载
	private Date sj;//报告时间
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
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getWjnr() {
		return wjnr;
	}
	public void setWjnr(String wjnr) {
		this.wjnr = wjnr;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
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
