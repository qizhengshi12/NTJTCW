package com.safety.entity;

import java.sql.Timestamp;

public class SendMessage {
	private int id;//���ŷ���
	private String dxnr;//��������
	private Timestamp fssj;//����ʱ��
	private String jsr;//������
	private String jsrID;//������ID
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
	private String sffs;//�Ƿ���
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
