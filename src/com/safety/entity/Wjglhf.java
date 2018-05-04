package com.safety.entity;

import java.sql.Date;

public class Wjglhf {
	private int id;//文件管理回复记录
	private int wjID;//文件ID
	private String wjmc;//文件名称
	private String hfr;//回复人
	private String hfrID;//回复人ID
	private String company;//回复人单位
	private String companyID;//回复人单位ID
	private String hfnr;//回复内容
	private String FileUrl;//附件地址
	private Date hfsj;//回复时间
	private Date hfqx;//回复期限
	private String sfcs;//是否超时
	private String hfzt;//回复状态
	private String cyzt;//查阅状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWjID() {
		return wjID;
	}
	public void setWjID(int wjID) {
		this.wjID = wjID;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getHfr() {
		return hfr;
	}
	public void setHfr(String hfr) {
		this.hfr = hfr;
	}
	public String getHfrID() {
		return hfrID;
	}
	public void setHfrID(String hfrID) {
		this.hfrID = hfrID;
	}
	public String getHfnr() {
		return hfnr;
	}
	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	public Date getHfsj() {
		return hfsj;
	}
	public void setHfsj(Date hfsj) {
		this.hfsj = hfsj;
	}
	public String getSfcs() {
		return sfcs;
	}
	public void setSfcs(String sfcs) {
		this.sfcs = sfcs;
	}
	public String getHfzt() {
		return hfzt;
	}
	public void setHfzt(String hfzt) {
		this.hfzt = hfzt;
	}
	public String getCyzt() {
		return cyzt;
	}
	public void setCyzt(String cyzt) {
		this.cyzt = cyzt;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public Date getHfqx() {
		return hfqx;
	}
	public void setHfqx(Date hfqx) {
		this.hfqx = hfqx;
	}
	
}
