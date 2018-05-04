package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class HtbbSetTime {
	private int id;//后台报表设置通知时间
	private int bbID;//设置的报表类型
	private String bbmc;//设置的报表名称
	private String tzry;//通知人员
	private String tzryID;//通知人员ID
	private String tznr;//通知内容
	private int setlx;//通知类型（每天-1；每月-2；每季度-3；每年-4。）
	private int mday;//每月的第几天
	private int qmonth;//每季度的第几个月
	private int qmday;//每季度的某月的第几天
	private Date yday;//每年的哪一天
	private String czr;//操作人
	private Timestamp czsj;//操作时间
	private String czrID;//操作人ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBbID() {
		return bbID;
	}
	public void setBbID(int bbID) {
		this.bbID = bbID;
	}
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	public String getTzry() {
		return tzry;
	}
	public void setTzry(String tzry) {
		this.tzry = tzry;
	}
	public String getTzryID() {
		return tzryID;
	}
	public void setTzryID(String tzryID) {
		this.tzryID = tzryID;
	}
	public String getTznr() {
		return tznr;
	}
	public void setTznr(String tznr) {
		this.tznr = tznr;
	}
	public int getSetlx() {
		return setlx;
	}
	public void setSetlx(int setlx) {
		this.setlx = setlx;
	}
	public int getMday() {
		return mday;
	}
	public void setMday(int mday) {
		this.mday = mday;
	}
	public int getQmonth() {
		return qmonth;
	}
	public void setQmonth(int qmonth) {
		this.qmonth = qmonth;
	}
	public int getQmday() {
		return qmday;
	}
	public void setQmday(int qmday) {
		this.qmday = qmday;
	}
	public Date getYday() {
		return yday;
	}
	public void setYday(Date yday) {
		this.yday = yday;
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
