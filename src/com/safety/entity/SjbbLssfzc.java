package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class SjbbLssfzc {
	private int id;//��Ʊ������м����أ���������ҵ��λ����ʵ�շ����߼����Բ��
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
	private String czrdw;//�����˵�λ
	private String czrphone;//��ϵ�绰
	private Date sbsj;//�ϱ�ʱ��
	private int year;//ʱ��
	private int jd;//����
	private String tjzt;//�ύ״̬��1���ύ2������ݸ壩
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCzrphone() {
		return czrphone;
	}
	public void setCzrphone(String czrphone) {
		this.czrphone = czrphone;
	}
	public Date getSbsj() {
		return sbsj;
	}
	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getJd() {
		return jd;
	}
	public void setJd(int jd) {
		this.jd = jd;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
