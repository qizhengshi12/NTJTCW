package com.safety.entity;

import java.sql.Timestamp;

public class Checkid {
	private int id;//审核人ID确认
	private String zdxmsbID;//重大项目申报ID
	private String zdxmsbName;//重大项目申报Name
	private String postInfID;//首页窗口ID
	private String postInfName;//首页窗口Name
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZdxmsbID() {
		return zdxmsbID;
	}
	public void setZdxmsbID(String zdxmsbID) {
		this.zdxmsbID = zdxmsbID;
	}
	public String getZdxmsbName() {
		return zdxmsbName;
	}
	public void setZdxmsbName(String zdxmsbName) {
		this.zdxmsbName = zdxmsbName;
	}
	public String getPostInfID() {
		return postInfID;
	}
	public void setPostInfID(String postInfID) {
		this.postInfID = postInfID;
	}
	public String getPostInfName() {
		return postInfName;
	}
	public void setPostInfName(String postInfName) {
		this.postInfName = postInfName;
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

}
