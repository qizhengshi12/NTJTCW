package com.safety.entity;

public class BbdzmbJs {
	private int id;//定制报表模板——单元格计算
	private int row1;//左行（默认为：-1）
	private int column1;//左列（默认为：-1）
	private int row2;//右行（默认为：-1）
	private int column2;//右列（默认为：-1）
	private int row3;//结果（默认为：-1）
	private int column3;//结果（默认为：-1）
	private int zbid;//对应的主表ID
	private String lx;//合并类型：整列或者单独两个单元格
	private String fh;//符号（加、减、乘、除）
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
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getFh() {
		return fh;
	}
	public void setFh(String fh) {
		this.fh = fh;
	}
	public int getRow3() {
		return row3;
	}
	public void setRow3(int row3) {
		this.row3 = row3;
	}
	public int getColumn3() {
		return column3;
	}
	public void setColumn3(int column3) {
		this.column3 = column3;
	}
	

}
