package com.safety.entity;

import java.sql.Date;

public class LearningCorner {
	private int id;
	private String bt;//����
	private String nr;//����
	private Date tjsj;//�ύ����
	private String tjr;//�ύ��
	private String tjrID;//�ύ��ID
	private String dw;//��λ
	private String FileURL;//�ļ���ַ
	private String PhotoURL;//ͼƬ��ַ
	private int good;//����
	private String people;//������
	private String peopleID;//������ID
//	private String getwater;//���Ȫˮ
//	private String fromperson;//Ȫˮ��Դ
//	private String sendwater;//�ṩȪˮ
//	private String toperson;//Ȫˮȥ��
	private String lx1;//����1������ģ�顢ѧϰģ�飩
	private String lx2;//����2������������
	private String lx3;//����3����������ͨ��
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
	public Date getTjsj() {
		return tjsj;
	}
	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjrID() {
		return tjrID;
	}
	public void setTjrID(String tjrID) {
		this.tjrID = tjrID;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getFileURL() {
		return FileURL;
	}
	public void setFileURL(String fileURL) {
		FileURL = fileURL;
	}
	public String getPhotoURL() {
		return PhotoURL;
	}
	public void setPhotoURL(String photoURL) {
		PhotoURL = photoURL;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getPeopleID() {
		return peopleID;
	}
	public void setPeopleID(String peopleID) {
		this.peopleID = peopleID;
	}
	public String getLx1() {
		return lx1;
	}
	public void setLx1(String lx1) {
		this.lx1 = lx1;
	}
	public String getLx2() {
		return lx2;
	}
	public void setLx2(String lx2) {
		this.lx2 = lx2;
	}
	public String getLx3() {
		return lx3;
	}
	public void setLx3(String lx3) {
		this.lx3 = lx3;
	}

}
