package com.safety.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class HtbbSetTime {
	private int id;//��̨��������֪ͨʱ��
	private int bbID;//���õı�������
	private String bbmc;//���õı�������
	private String tzry;//֪ͨ��Ա
	private String tzryID;//֪ͨ��ԱID
	private String tznr;//֪ͨ����
	private int setlx;//֪ͨ���ͣ�ÿ��-1��ÿ��-2��ÿ����-3��ÿ��-4����
	private int mday;//ÿ�µĵڼ���
	private int qmonth;//ÿ���ȵĵڼ�����
	private int qmday;//ÿ���ȵ�ĳ�µĵڼ���
	private Date yday;//ÿ�����һ��
	private String czr;//������
	private Timestamp czsj;//����ʱ��
	private String czrID;//������ID
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
