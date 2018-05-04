package com.safety.entity;

import java.sql.Date;

public class LearningCorner {
	private int id;
	private String bt;//标题
	private String nr;//内容
	private Date tjsj;//提交日期
	private String tjr;//提交人
	private String tjrID;//提交人ID
	private String dw;//单位
	private String FileURL;//文件地址
	private String PhotoURL;//图片地址
	private int good;//获赞
	private String people;//点赞人
	private String peopleID;//点赞人ID
//	private String getwater;//获得泉水
//	private String fromperson;//泉水来源
//	private String sendwater;//提供泉水
//	private String toperson;//泉水去向
	private String lx1;//类型1（工作模块、学习模块）
	private String lx2;//类型2（分享、求助）
	private String lx3;//类型3（精华、普通）
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
