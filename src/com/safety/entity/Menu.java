package com.safety.entity;

import java.sql.Timestamp;

public class Menu {
	private int id;
	private String name;//�ڵ�����
	private String son_id;//�ӽڵ�
	private int parent_id;//���ڵ�
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
	private String description;//�ڵ�˵��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSon_id() {
		return son_id;
	}
	public void setSon_id(String sonId) {
		son_id = sonId;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parentId) {
		parent_id = parentId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
