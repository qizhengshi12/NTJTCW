package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Bbdz {
	private int id;//����ģ���½��ı���
	private String bt;//����
	private Date sj;//�ϱ�ʱ��
	private String czr;
	private Timestamp czsj;
	private String czrID;
	private String czrdw;
	private int bbls;//��������
	private int zbid;//��Ӧ������ID
	private String tjzt;//�ύ״̬
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
