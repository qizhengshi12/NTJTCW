package com.safety.entity;

import java.sql.Timestamp;

public class Gzdt {
	private int id;//������̬
	private String wjmc;//��������
	private String wjnr;//��������
	private String FileUrl;//��������
	private String czr;//������
	private String czrID;//������ID
	private Timestamp czsj;//����ʱ��
	private String czrdw;//�����˵�λ
	private String tjzt;//�ύ״̬��1���ύ2������ݸ壩
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
