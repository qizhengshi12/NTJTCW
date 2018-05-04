package com.safety.entity;

import java.sql.Timestamp;

public class MyMessage {
	private int id;//短信发送
	private String dxnr;//短信内容
	private Timestamp jssj;//接收时间
	private String jsrID;//接收人ID
	private String fsr;//发送人
	private String cybz;//查阅标志
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDxnr() {
		return dxnr;
	}
	public void setDxnr(String dxnr) {
		this.dxnr = dxnr;
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
	public String getFsr() {
		return fsr;
	}
	public void setFsr(String fsr) {
		this.fsr = fsr;
	}
	public String getCybz() {
		return cybz;
	}
	public void setCybz(String cybz) {
		this.cybz = cybz;
	}
	
}
