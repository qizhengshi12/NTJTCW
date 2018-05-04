package com.safety.entity;

import java.sql.Timestamp;

public class MyEmail {
	private int id;//邮件接收
	private int sendid;//邮件接收ID
	private String bt;//邮件主题
	private String nr;//邮件内容
	private String FileUrl;//邮件附件
	private String fsr;//发送人
	private String fsrID;//发送人ID
	private String allfsr;//所有发送人
	private String allfsrID;//所有发送人ID
	private String cyzt;//1：未查阅 2：已查阅
	private String sczt;//1：未删除 2：删除到回收站 3：彻底删除（标志3只做判断，数据库中不会出现）
	private String jsr;//接收人
	private Timestamp jssj;//接收时间
	private String jsrID;//接收人ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSendid() {
		return sendid;
	}
	public void setSendid(int sendid) {
		this.sendid = sendid;
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
	public String getFsr() {
		return fsr;
	}
	public void setFsr(String fsr) {
		this.fsr = fsr;
	}
	public String getFsrID() {
		return fsrID;
	}
	public void setFsrID(String fsrID) {
		this.fsrID = fsrID;
	}
	public String getAllfsr() {
		return allfsr;
	}
	public void setAllfsr(String allfsr) {
		this.allfsr = allfsr;
	}
	public String getAllfsrID() {
		return allfsrID;
	}
	public void setAllfsrID(String allfsrID) {
		this.allfsrID = allfsrID;
	}
	public String getCyzt() {
		return cyzt;
	}
	public void setCyzt(String cyzt) {
		this.cyzt = cyzt;
	}
	public String getSczt() {
		return sczt;
	}
	public void setSczt(String sczt) {
		this.sczt = sczt;
	}
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public Timestamp getJssj() {
		return jssj;
	}
	public void setJssj(Timestamp jssj) {
		this.jssj = jssj;
	}
	public String getJsrID() {
		return jsrID;
	}
	public void setJsrID(String jsrID) {
		this.jsrID = jsrID;
	}

}
