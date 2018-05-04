package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Wjglxf {
	private int id;//文件管理下发记录
	private String wjmc;//文件名称
	private String wjlx;//文件类型
	private String fwdw;//发文单位
	private String wjh;//发文号
	private Date hfqx;//回复期限
	private String hfry;//回复人员
	private String hfryID;//回复人员ID
	private String whfryID;//未回复人员ID
	private String fqr;//发起人
	private String fqrID;//发起人ID
	private Timestamp fqsj;//发起时间
	private String gzyq;//工作要求
	private String FileUrl;//附件地址
	private String fszt;//发送状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWjmc() {
		return wjmc;
	}
	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}
	public String getWjlx() {
		return wjlx;
	}
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}
	public String getFwdw() {
		return fwdw;
	}
	public void setFwdw(String fwdw) {
		this.fwdw = fwdw;
	}
	public String getWjh() {
		return wjh;
	}
	public void setWjh(String wjh) {
		this.wjh = wjh;
	}
	public Date getHfqx() {
		return hfqx;
	}
	public void setHfqx(Date hfqx) {
		this.hfqx = hfqx;
	}
	public String getHfry() {
		return hfry;
	}
	public void setHfry(String hfry) {
		this.hfry = hfry;
	}
	public String getHfryID() {
		return hfryID;
	}
	public void setHfryID(String hfryID) {
		this.hfryID = hfryID;
	}
	public String getWhfryID() {
		return whfryID;
	}
	public void setWhfryID(String whfryID) {
		this.whfryID = whfryID;
	}
	public String getFqr() {
		return fqr;
	}
	public void setFqr(String fqr) {
		this.fqr = fqr;
	}
	public String getFqrID() {
		return fqrID;
	}
	public void setFqrID(String fqrID) {
		this.fqrID = fqrID;
	}
	public Timestamp getFqsj() {
		return fqsj;
	}
	public void setFqsj(Timestamp fqsj) {
		this.fqsj = fqsj;
	}
	public String getGzyq() {
		return gzyq;
	}
	public void setGzyq(String gzyq) {
		this.gzyq = gzyq;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	public String getFszt() {
		return fszt;
	}
	public void setFszt(String fszt) {
		this.fszt = fszt;
	}
	
}
