package com.safety.entity;

import java.sql.Timestamp;

public class SjbbJtyssjdytj {
	private int id;//审计报表——交通运输部门审计对象统计表
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String czrphone;//联系电话
	private String tjzt;//提交状态（1：提交2：保存草稿）
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
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

}
