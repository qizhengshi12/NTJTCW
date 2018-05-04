package com.safety.entity;

import java.sql.Timestamp;

public class SendMessage {
	private int id;//短信发送
	private String dxnr;//短信内容
	private Timestamp fssj;//发送时间
	private String jsr;//接收人
	private String jsrID;//接收人ID
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String sffs;//是否发送
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
	public Timestamp getFssj() {
		return fssj;
	}
	public void setFssj(Timestamp fssj) {
		this.fssj = fssj;
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
	public String getSffs() {
		return sffs;
	}
	public void setSffs(String sffs) {
		this.sffs = sffs;
	}
	
}
