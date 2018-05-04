package com.safety.entity;

import java.sql.Timestamp;

public class CwbbSgjfb {
	private int id;//财务报表——三公经费表
	private int year;//年份
	private int month;//月份
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String hj1;//合计——预算数
	private String hj2;//合计——支出数
	private String hj3;//支出比例%
	private String cgf1;//因公出国（境）费用——预算数
	private String cgf2;//因公出国（境）费用——支出数
	private String cgf3;//支出比例%
	private String jdf1;//公务接待费——预算数
	private String jdf2;//公务接待费——支出数
	private String jdf3;//支出比例%
	private String jbzc1;//基本支出列支——预算数
	private String jbzc2;//基本支出列支——支出数
	private String jbzc3;//支出比例%
	private String xmzc1;//项目支出列支——预算数
	private String xmzc2;//项目支出列支——支出数
	private String xmzc3;//支出比例%
	private String ycf1;//公务用车费——预算数
	private String ycf2;//公务用车费——支出数
	private String ycf3;//支出比例%
	private String gzf1;//车辆购置费——预算数
	private String gzf2;//车辆购置费——支出数
	private String gzf3;//支出比例%
	private String whf1;//车辆运行维护费——预算数
	private String whf2;//车辆运行维护费——支出数
	private String whf3;//支出比例%
	private String hyf1;//会议费——预算数
	private String hyf2;//会议费—支出数
	private String hyf3;//支出比例%
	private String tjzt;//提交状态（1：提交2：保存草稿）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getHj1() {
		return hj1;
	}
	public void setHj1(String hj1) {
		this.hj1 = hj1;
	}
	public String getHj2() {
		return hj2;
	}
	public void setHj2(String hj2) {
		this.hj2 = hj2;
	}
	public String getCgf1() {
		return cgf1;
	}
	public void setCgf1(String cgf1) {
		this.cgf1 = cgf1;
	}
	public String getCgf2() {
		return cgf2;
	}
	public void setCgf2(String cgf2) {
		this.cgf2 = cgf2;
	}
	public String getJdf1() {
		return jdf1;
	}
	public void setJdf1(String jdf1) {
		this.jdf1 = jdf1;
	}
	public String getJdf2() {
		return jdf2;
	}
	public void setJdf2(String jdf2) {
		this.jdf2 = jdf2;
	}
	public String getYcf1() {
		return ycf1;
	}
	public void setYcf1(String ycf1) {
		this.ycf1 = ycf1;
	}
	public String getYcf2() {
		return ycf2;
	}
	public void setYcf2(String ycf2) {
		this.ycf2 = ycf2;
	}
	public String getGzf1() {
		return gzf1;
	}
	public void setGzf1(String gzf1) {
		this.gzf1 = gzf1;
	}
	public String getGzf2() {
		return gzf2;
	}
	public void setGzf2(String gzf2) {
		this.gzf2 = gzf2;
	}
	public String getWhf1() {
		return whf1;
	}
	public void setWhf1(String whf1) {
		this.whf1 = whf1;
	}
	public String getWhf2() {
		return whf2;
	}
	public void setWhf2(String whf2) {
		this.whf2 = whf2;
	}
	public String getHyf1() {
		return hyf1;
	}
	public void setHyf1(String hyf1) {
		this.hyf1 = hyf1;
	}
	public String getHyf2() {
		return hyf2;
	}
	public void setHyf2(String hyf2) {
		this.hyf2 = hyf2;
	}
	public String getHj3() {
		return hj3;
	}
	public void setHj3(String hj3) {
		this.hj3 = hj3;
	}
	public String getCgf3() {
		return cgf3;
	}
	public void setCgf3(String cgf3) {
		this.cgf3 = cgf3;
	}
	public String getJdf3() {
		return jdf3;
	}
	public void setJdf3(String jdf3) {
		this.jdf3 = jdf3;
	}
	public String getJbzc1() {
		return jbzc1;
	}
	public void setJbzc1(String jbzc1) {
		this.jbzc1 = jbzc1;
	}
	public String getJbzc2() {
		return jbzc2;
	}
	public void setJbzc2(String jbzc2) {
		this.jbzc2 = jbzc2;
	}
	public String getJbzc3() {
		return jbzc3;
	}
	public void setJbzc3(String jbzc3) {
		this.jbzc3 = jbzc3;
	}
	public String getXmzc1() {
		return xmzc1;
	}
	public void setXmzc1(String xmzc1) {
		this.xmzc1 = xmzc1;
	}
	public String getXmzc2() {
		return xmzc2;
	}
	public void setXmzc2(String xmzc2) {
		this.xmzc2 = xmzc2;
	}
	public String getXmzc3() {
		return xmzc3;
	}
	public void setXmzc3(String xmzc3) {
		this.xmzc3 = xmzc3;
	}
	public String getYcf3() {
		return ycf3;
	}
	public void setYcf3(String ycf3) {
		this.ycf3 = ycf3;
	}
	public String getGzf3() {
		return gzf3;
	}
	public void setGzf3(String gzf3) {
		this.gzf3 = gzf3;
	}
	public String getWhf3() {
		return whf3;
	}
	public void setWhf3(String whf3) {
		this.whf3 = whf3;
	}
	public String getHyf3() {
		return hyf3;
	}
	public void setHyf3(String hyf3) {
		this.hyf3 = hyf3;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
}
