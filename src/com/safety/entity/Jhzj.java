package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Jhzj {
	private int id;//�ƻ��ܽ�
	private String jhmc;//����
	private String jhlx1;//����1���ƻ����ܽᣩ
	private String jhlx2;//����2���������ƣ�
	private String jhnr;//����
	private String FileUrl;//��������
	private Date jhsj1;//��ʼʱ��
	private Date jhsj2;//����ʱ��
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
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public String getJhlx1() {
		return jhlx1;
	}
	public void setJhlx1(String jhlx1) {
		this.jhlx1 = jhlx1;
	}
	public String getJhlx2() {
		return jhlx2;
	}
	public void setJhlx2(String jhlx2) {
		this.jhlx2 = jhlx2;
	}
	public String getJhnr() {
		return jhnr;
	}
	public void setJhnr(String jhnr) {
		this.jhnr = jhnr;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	public Date getJhsj1() {
		return jhsj1;
	}
	public void setJhsj1(Date jhsj1) {
		this.jhsj1 = jhsj1;
	}
	public Date getJhsj2() {
		return jhsj2;
	}
	public void setJhsj2(Date jhsj2) {
		this.jhsj2 = jhsj2;
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
