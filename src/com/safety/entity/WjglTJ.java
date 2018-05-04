package com.safety.entity;

public class WjglTJ {
	private int id;
	private String name;//节点名称
	private int countcs;//超时记录数
	private int counthf;//回复记录数
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
	public int getCountcs() {
		return countcs;
	}
	public void setCountcs(int countcs) {
		this.countcs = countcs;
	}
	public int getCounthf() {
		return counthf;
	}
	public void setCounthf(int counthf) {
		this.counthf = counthf;
	}
}
