package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Zdxmsb {
	private int id;//重大项目申报
	private String lx;//类型
	private int nrid;//内容ID
	private String czr;//操作人
	private String czrID;//操作人ID
	private Timestamp czsj;//操作时间
	private String czrdw;//操作人单位
	private String shr;//审核人
	private String shrID;//审核人ID
	private String shyj;//审核意见
	private Date shsj;//审核时间
	private String smj;//扫描件
	private String tjzt;//提交状态（1：提交2：保存草稿）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public int getNrid() {
		return nrid;
	}
	public void setNrid(int nrid) {
		this.nrid = nrid;
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
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public String getSmj() {
		return smj;
	}
	public void setSmj(String smj) {
		this.smj = smj;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
