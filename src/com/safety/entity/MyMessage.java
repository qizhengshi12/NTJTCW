package com.safety.entity;

import java.sql.Timestamp;

public class MyMessage {
	private int id;//���ŷ���
	private String dxnr;//��������
	private Timestamp jssj;//����ʱ��
	private String jsrID;//������ID
	private String fsr;//������
	private String cybz;//���ı�־
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
