package com.safety.entity;

import java.sql.Timestamp;

public class Gztz {
	private int id;//����֪ͨ
	private String tzmc;//֪ͨ����
	private String tznr;//֪ͨ����
	private Timestamp tzsj;//����ʱ��
	private String tzdd;//֪ͨ�ص�
	private String tzry;//֪ͨ��Ա
	private String tzryID;//֪ͨ��ԱID
	private String czr;//������
	private String czrID;//������ID
	private Timestamp czsj;//����ʱ��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTzmc() {
		return tzmc;
	}
	public void setTzmc(String tzmc) {
		this.tzmc = tzmc;
	}
	public String getTznr() {
		return tznr;
	}
	public void setTznr(String tznr) {
		this.tznr = tznr;
	}
	public Timestamp getTzsj() {
		return tzsj;
	}
	public void setTzsj(Timestamp tzsj) {
		this.tzsj = tzsj;
	}
	public String getTzdd() {
		return tzdd;
	}
	public void setTzdd(String tzdd) {
		this.tzdd = tzdd;
	}
	public String getTzry() {
		return tzry;
	}
	public void setTzry(String tzry) {
		this.tzry = tzry;
	}
	public String getTzryID() {
		return tzryID;
	}
	public void setTzryID(String tzryID) {
		this.tzryID = tzryID;
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

}
