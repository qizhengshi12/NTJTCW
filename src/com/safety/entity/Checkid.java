package com.safety.entity;

import java.sql.Timestamp;

public class Checkid {
	private int id;//�����IDȷ��
	private String zdxmsbID;//�ش���Ŀ�걨ID
	private String zdxmsbName;//�ش���Ŀ�걨Name
	private String postInfID;//��ҳ����ID
	private String postInfName;//��ҳ����Name
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
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
