package com.safety.entity;

import java.sql.Date;

public class PostInformation {
	private int id;
	private String bt;//����
	private String nr;//����
	private Date tjsj;//�ύ����
	private String tjr;//�ύ��
	private String tjrID;//�ύ��ID
	private String dw;//��λ
	private String FileURL;//�ļ���ַ
	private String PhotoURL;//ͼƬ��ַ
	private String sfsh;//�Ƿ����
	private String shr;//�����
	private String shrID;//�����ID
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
	public String getSfsh() {
		return sfsh;
	}
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShrID() {
		return shrID;
	}
	public void setShrID(String shrID) {
		this.shrID = shrID;
	}
	
}
