package com.safety.entity;

import java.sql.Date;

public class PostInformation {
	private int id;
	private String bt;//标题
	private String nr;//内容
	private Date tjsj;//提交日期
	private String tjr;//提交人
	private String tjrID;//提交人ID
	private String dw;//单位
	private String FileURL;//文件地址
	private String PhotoURL;//图片地址
	private String sfsh;//是否审核
	private String shr;//审核人
	private String shrID;//审核人ID
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
