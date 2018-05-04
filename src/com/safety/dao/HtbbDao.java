package com.safety.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.Utils.DB;
import com.safety.entity.HtbbSetTime;

public class HtbbDao {
	/*
	 *����ID
	 *��ѯ������Ϣ 
	 */
	public  HtbbSetTime querySetTime(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		HtbbSetTime htbbSetTime = new HtbbSetTime();
		String sqlString = "select * from HtbbSetTime where bbID ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				htbbSetTime.setId(rs.getInt("id"));
				htbbSetTime.setBbID(rs.getInt("bbID"));
				htbbSetTime.setTzry(rs.getString("tzry"));
				htbbSetTime.setTzryID(rs.getString("tzryID"));
				htbbSetTime.setTznr(rs.getString("tznr"));
				htbbSetTime.setSetlx(rs.getInt("setlx"));
				htbbSetTime.setMday(rs.getInt("mday"));
				htbbSetTime.setQmonth(rs.getInt("qmonth"));
				htbbSetTime.setQmday(rs.getInt("qmday"));
				htbbSetTime.setYday(rs.getDate("yday"));
				htbbSetTime.setCzr(rs.getString("czr"));
				htbbSetTime.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return htbbSetTime;
	}

	/*
	 *
	 *������¼  
	 */
	public boolean insertSetTime(HtbbSetTime htbbSetTime) {
		String sqlString = "insert into HtbbSetTime (bbID,tzry,tzryID,tznr,setlx,mday,qmonth,qmday,yday,czsj,czr,czrID) values(";
		sqlString += htbbSetTime.getBbID() + ",'";
		sqlString += htbbSetTime.getTzry() + "','";
		sqlString += htbbSetTime.getTzryID() + "','";
		sqlString += htbbSetTime.getTznr() + "',";
		sqlString += htbbSetTime.getSetlx() + ",";
		sqlString += htbbSetTime.getMday() + ",";
		sqlString += htbbSetTime.getQmonth() + ",";
		sqlString += htbbSetTime.getQmday() + ",'";
		sqlString += htbbSetTime.getYday() + "','";
		sqlString += htbbSetTime.getCzsj() + "','";
		sqlString += htbbSetTime.getCzr() + "','";
		sqlString += htbbSetTime.getCzrID() + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *����ID
	 *�޸ļ�¼  
	 */
	public boolean updateSetTime(HtbbSetTime htbbSetTime) {
		String sqlString = "update HtbbSetTime set bbID="; 
		sqlString += htbbSetTime.getBbID() + ",tzry='";
		sqlString += htbbSetTime.getTzry() + "',tzryID='";
		sqlString += htbbSetTime.getTzryID() + "',tznr='";
		sqlString += htbbSetTime.getTznr() + "',setlx=";
		sqlString += htbbSetTime.getSetlx() + ",mday=";
		sqlString += htbbSetTime.getMday() + ",qmonth=";
		sqlString += htbbSetTime.getQmonth() + ",qmday=";
		sqlString += htbbSetTime.getQmday() + ",yday='";
		sqlString += htbbSetTime.getYday() + "',czsj='";
		sqlString += htbbSetTime.getCzsj()+ "',czr='";
		sqlString += htbbSetTime.getCzr()+ "',czrID='";
		sqlString += htbbSetTime.getCzrID() + "' where id=" + htbbSetTime.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 * ����������Ϣ
	 */
	public  ArrayList<HtbbSetTime> queryHtbbSetTimeList() {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<HtbbSetTime> HtbbSetTimeList = new ArrayList<HtbbSetTime>();
		String sqlString = "select * from HtbbSetTime ;";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				HtbbSetTime htbbSetTime = new HtbbSetTime();
				htbbSetTime.setId(rs.getInt("id"));
				htbbSetTime.setBbID(rs.getInt("bbID"));
				htbbSetTime.setTzry(rs.getString("tzry"));
				htbbSetTime.setTzryID(rs.getString("tzryID"));
				htbbSetTime.setTznr(rs.getString("tznr"));
				htbbSetTime.setSetlx(rs.getInt("setlx"));
				htbbSetTime.setMday(rs.getInt("mday"));
				htbbSetTime.setQmonth(rs.getInt("qmonth"));
				htbbSetTime.setQmday(rs.getInt("qmday"));
				htbbSetTime.setYday(rs.getDate("yday"));
				htbbSetTime.setCzr(rs.getString("czr"));
				htbbSetTime.setCzrID(rs.getString("czrID"));
				HtbbSetTimeList.add(htbbSetTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return HtbbSetTimeList;
	}
}
