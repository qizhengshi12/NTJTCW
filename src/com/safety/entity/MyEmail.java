package com.safety.entity;

import java.sql.Timestamp;

public class MyEmail {
	private int id;//�ʼ�����
	private int sendid;//�ʼ�����ID
	private String bt;//�ʼ�����
	private String nr;//�ʼ�����
	private String FileUrl;//�ʼ�����
	private String fsr;//������
	private String fsrID;//������ID
	private String allfsr;//���з�����
	private String allfsrID;//���з�����ID
	private String cyzt;//1��δ���� 2���Ѳ���
	private String sczt;//1��δɾ�� 2��ɾ��������վ 3������ɾ������־3ֻ���жϣ����ݿ��в�����֣�
	private String jsr;//������
	private Timestamp jssj;//����ʱ��
	private String jsrID;//������ID
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
