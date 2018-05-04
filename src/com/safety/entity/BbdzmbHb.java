package com.safety.entity;

public class BbdzmbHb {
	private int id;//定制报表模板——单元格合并位置
	private int row1;//左行（默认为：-1）
	private int column1;//左列（默认为：-1）
	private int row2;//右行（默认为：-1）
	private int column2;//右列（默认为：-1）
	private int zbid;//对应的主表ID
	private String hl;//合并类型：行或列
	private int hs;//合并数——行数
	private int ls;//合并数——列数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRow1() {
		return row1;
	}
	public void setRow1(int row1) {
		this.row1 = row1;
	}
	public int getColumn1() {
		return column1;
	}
	public void setColumn1(int column1) {
		this.column1 = column1;
	}
	public int getRow2() {
		return row2;
	}
	public void setRow2(int row2) {
		this.row2 = row2;
	}
	public int getColumn2() {
		return column2;
	}
	public void setColumn2(int column2) {
		this.column2 = column2;
	}
	public int getZbid() {
		return zbid;
	}
	public void setZbid(int zbid) {
		this.zbid = zbid;
	}
	public String getHl() {
		return hl;
	}
	public void setHl(String hl) {
		this.hl = hl;
	}
	public int getHs() {
		return hs;
	}
	public void setHs(int hs) {
		this.hs = hs;
	}
	public int getLs() {
		return ls;
	}
	public void setLs(int ls) {
		this.ls = ls;
	}
	

}
