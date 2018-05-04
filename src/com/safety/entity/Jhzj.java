package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Jhzj {
	private int id;//计划总结
	private String jhmc;//名称
	private String jhlx1;//类型1（计划或总结）
	private String jhlx2;//类型2（财务或审计）
	private String jhnr;//内容
	private String FileUrl;//附件下载
	private Date jhsj1;//起始时间
	private Date jhsj2;//结束时间
	private String czr;//操作人
	private String czrID;//操作人ID
	private Timestamp czsj;//操作时间
	private String czrdw;//操作人单位
	private String tjzt;//提交状态（1：提交2：保存草稿）
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
