package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class TjbbYxzbHs {
	private int id;//统计报表——海事签证月度同比表（海事局）
	private String bt;//标题
	private int year;//年份
	private int month;//月份
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String czrphone;//联系电话
	private String shr;//审核人
	private String shrID;//审核人ID
	private String shyj;//审核意见
	private String yxfx;//运行分析
	private Date shsj;//审核时间
	private String tjzt;//提交状态（1：提交2：保存草稿）
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
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
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getYxfx() {
		return yxfx;
	}
	public void setYxfx(String yxfx) {
		this.yxfx = yxfx;
	}
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
