package com.safety.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.Utils.DB;
import com.safety.entity.HtbbSetTime;

public class HtbbDao {
	/*
	 *根据ID
	 *查询设置信息 
	 */
	public  HtbbSetTime querySetTime(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		HtbbSetTime htbbSetTime = new HtbbSetTime();
		String sqlString = "select * from HtbbSetTime where bbID ="+id;
		/* 调用数据层进行查询 */
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
	 *新增记录  
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
	 *根据ID
	 *修改记录  
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
	 * 查找所有信息
	 */
	public  ArrayList<HtbbSetTime> queryHtbbSetTimeList() {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<HtbbSetTime> HtbbSetTimeList = new ArrayList<HtbbSetTime>();
		String sqlString = "select * from HtbbSetTime ;";
		/* 调用数据层进行查询 */
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
