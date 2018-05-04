package com.safety.entity;

import java.sql.Timestamp;

public class SendEmail {
	private int id;//邮件发送
	private String bt;//邮件主题
	private String nr;//邮件内容
	private String FileUrl;//邮件附件
	private String jsr;//接收人
	private String jsrID;//接收人ID
	private String wdr;//未读人员
	private String msr;//密送人
	private String msrID;//密送人ID
	private String fszt;//1：普通发送  2：存草稿
	private String sczt;//1：未删除 2：删除到回收站 3：彻底删除（标志3只做判断，数据库中不会出现）
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
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
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public String getJsrID() {
		return jsrID;
	}
	public void setJsrID(String jsrID) {
		this.jsrID = jsrID;
	}
	public String getWdr() {
		return wdr;
	}
	public void setWdr(String wdr) {
		this.wdr = wdr;
	}
	public String getMsr() {
		return msr;
	}
	public void setMsr(String msr) {
		this.msr = msr;
	}
	public String getMsrID() {
		return msrID;
	}
	public void setMsrID(String msrID) {
		this.msrID = msrID;
	}
	public String getFszt() {
		return fszt;
	}
	public void setFszt(String fszt) {
		this.fszt = fszt;
	}
	public String getSczt() {
		return sczt;
	}
	public void setSczt(String sczt) {
		this.sczt = sczt;
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
}
