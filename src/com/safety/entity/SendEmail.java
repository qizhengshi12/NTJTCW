package com.safety.entity;

import java.sql.Timestamp;

public class SendEmail {
	private int id;//�ʼ�����
	private String bt;//�ʼ�����
	private String nr;//�ʼ�����
	private String FileUrl;//�ʼ�����
	private String jsr;//������
	private String jsrID;//������ID
	private String wdr;//δ����Ա
	private String msr;//������
	private String msrID;//������ID
	private String fszt;//1����ͨ����  2����ݸ�
	private String sczt;//1��δɾ�� 2��ɾ��������վ 3������ɾ������־3ֻ���жϣ����ݿ��в�����֣�
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
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
