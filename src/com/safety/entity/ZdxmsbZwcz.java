package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class ZdxmsbZwcz {
	private int id;//重大项目申报——债务重组
	private String mdyy;//目的意义
	private String nred;//主要内容和额度
	private String cb;//所需成本
	private String fa;//方案概述
	private String qk;//债务重组后的资产负债情况
	private String czr;//操作人
	private String czrID;//操作人ID
	private Timestamp czsj;//操作时间
	private String czrdw;//操作人单位
	private String shr;//审核人
	private String shrID;//审核人ID
	private String shyj;//审核意见
	private Date shsj;//审核时间
	private String smj;//扫描件
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMdyy() {
		return mdyy;
	}
	public void setMdyy(String mdyy) {
		this.mdyy = mdyy;
	}
	public String getNred() {
		return nred;
	}
	public void setNred(String nred) {
		this.nred = nred;
	}
	public String getCb() {
		return cb;
	}
	public void setCb(String cb) {
		this.cb = cb;
	}
	public String getFa() {
		return fa;
	}
	public void setFa(String fa) {
		this.fa = fa;
	}
	public String getQk() {
		return qk;
	}
	public void setQk(String qk) {
		this.qk = qk;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String getCzrID() {
		return czrID;
	}
	public void setCzrID(String czrID) {
		this.czrID = czrID;
	}
	public Timestamp getCzsj() {
		return czsj;
	}
	public void setCzsj(Timestamp czsj) {
		this.czsj = czsj;
	}
	public String getCzrdw() {
		return czrdw;
	}
	public void setCzrdw(String czrdw) {
		this.czrdw = czrdw;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShrID() {
		return shrID;
	}
	public void setShrID(String shrID) {
		this.shrID = shrID;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public String getSmj() {
		return smj;
	}
	public void setSmj(String smj) {
		this.smj = smj;
	}
}
