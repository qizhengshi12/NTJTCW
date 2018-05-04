package com.safety.entity;

import java.sql.Timestamp;

public class CwbbYszxb {
	private int id;//财务报表——预算执行表
	private String bt;//标题
	private int year;//年份
	private int month;//月份
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	private String czrdw;//操作人单位
	private String xmid;//项目表ID集合
	private String jb1;//基本支出——预算数
	private String jb2;//基本支出——支出数
	private String jb3;//基本支出——结余数
	private String jb4;//基本支出——支出比例
	private String gz1;//工资福利支出——预算数
	private String gz2;//工资福利支出——支出数
	private String gz3;//工资福利支出——结余数
	private String gz4;//工资福利支出——支出比例
	private String sp1;//商品和服务支出——预算数
	private String sp2;//商品和服务支出——支出数
	private String sp3;//商品和服务支出——结余数
	private String sp4;//商品和服务支出——支出比例
	private String bz1;//对个人和家庭补助支出——预算数
	private String bz2;//对个人和家庭补助支出——支出数
	private String bz3;//对个人和家庭补助支出——结余数
	private String bz4;//对个人和家庭补助支出——支出比例
	private String xm1;//项目支出——预算数
	private String xm2;//项目支出——支出数
	private String xm3;//项目支出——结余数
	private String xm4;//项目支出——支出比例
	private String hj1;//合计——预算数
	private String hj2;//合计——支出数
	private String hj3;//合计——结余数
	private String hj4;//合计——支出比例
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
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getJb1() {
		return jb1;
	}
	public void setJb1(String jb1) {
		this.jb1 = jb1;
	}
	public String getJb2() {
		return jb2;
	}
	public void setJb2(String jb2) {
		this.jb2 = jb2;
	}
	public String getJb3() {
		return jb3;
	}
	public void setJb3(String jb3) {
		this.jb3 = jb3;
	}
	public String getJb4() {
		return jb4;
	}
	public void setJb4(String jb4) {
		this.jb4 = jb4;
	}
	public String getGz1() {
		return gz1;
	}
	public void setGz1(String gz1) {
		this.gz1 = gz1;
	}
	public String getGz2() {
		return gz2;
	}
	public void setGz2(String gz2) {
		this.gz2 = gz2;
	}
	public String getGz3() {
		return gz3;
	}
	public void setGz3(String gz3) {
		this.gz3 = gz3;
	}
	public String getGz4() {
		return gz4;
	}
	public void setGz4(String gz4) {
		this.gz4 = gz4;
	}
	public String getSp1() {
		return sp1;
	}
	public void setSp1(String sp1) {
		this.sp1 = sp1;
	}
	public String getSp2() {
		return sp2;
	}
	public void setSp2(String sp2) {
		this.sp2 = sp2;
	}
	public String getSp3() {
		return sp3;
	}
	public void setSp3(String sp3) {
		this.sp3 = sp3;
	}
	public String getSp4() {
		return sp4;
	}
	public void setSp4(String sp4) {
		this.sp4 = sp4;
	}
	public String getBz1() {
		return bz1;
	}
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	public String getBz2() {
		return bz2;
	}
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	public String getBz3() {
		return bz3;
	}
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	public String getBz4() {
		return bz4;
	}
	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}
	public String getXm1() {
		return xm1;
	}
	public void setXm1(String xm1) {
		this.xm1 = xm1;
	}
	public String getXm2() {
		return xm2;
	}
	public void setXm2(String xm2) {
		this.xm2 = xm2;
	}
	public String getXm3() {
		return xm3;
	}
	public void setXm3(String xm3) {
		this.xm3 = xm3;
	}
	public String getXm4() {
		return xm4;
	}
	public void setXm4(String xm4) {
		this.xm4 = xm4;
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
	public String getHj3() {
		return hj3;
	}
	public void setHj3(String hj3) {
		this.hj3 = hj3;
	}
	public String getHj4() {
		return hj4;
	}
	public void setHj4(String hj4) {
		this.hj4 = hj4;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	
}
