package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Gzdt;

public class GzdtDao {
	/*
	 *根据查询条件
	 *查询工作动态记录
	 */
	public  ArrayList<Gzdt> queryGzdtListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Gzdt> GzdtList = new ArrayList<Gzdt>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Gzdt  order by czsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Gzdt "+srbt +" order by czsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Gzdt gzdt = new Gzdt();
				gzdt.setId(rs.getInt("id"));
				gzdt.setWjmc(rs.getString("wjmc"));
				gzdt.setWjnr(rs.getString("wjnr"));
				gzdt.setFileUrl(rs.getString("FileUrl"));
				gzdt.setCzr(rs.getString("czr"));
				gzdt.setCzrdw(rs.getString("czrdw"));
				gzdt.setCzrID(rs.getString("czrID"));
				gzdt.setCzsj(rs.getTimestamp("czsj"));
				gzdt.setTjzt(rs.getString("tjzt"));
//				gzdt.setFatherid(rs.getString("fatherid"));
				GzdtList.add(gzdt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GzdtList;
	}
	/*
	 *根据条件
	 *查询工作动态记录数
	 */
	public  int queryGzdtListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Gzdt";
		}else{
			sqlString = "select count(*) from Gzdt "+srbt;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		int count=0;
		try {
			count = db.getRecordCount(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return count;
	}
	/*
	 *根据ID
	 *查询
	 */
	public  Gzdt queryGzdtByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Gzdt gzdt = new Gzdt();
		String sqlString = "select * from Gzdt where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				gzdt.setId(rs.getInt("id"));
				gzdt.setWjmc(rs.getString("wjmc"));
				gzdt.setWjnr(rs.getString("wjnr"));
				gzdt.setFileUrl(rs.getString("FileUrl"));
				gzdt.setCzr(rs.getString("czr"));
				gzdt.setCzrdw(rs.getString("czrdw"));
				gzdt.setCzrID(rs.getString("czrID"));
				gzdt.setCzsj(rs.getTimestamp("czsj"));
//				gzdt.setFatherid(rs.getString("fatherid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return gzdt;
	}
	/*
	 *
	 *新增记录
	 */
	public int insertGzdt(Gzdt gzdt) {
		String sqlString = "insert into Gzdt (wjmc,wjnr,FileUrl,czr,czrID,czsj,czrdw,tjzt) values('";
		sqlString += gzdt.getWjmc() + "','";
		sqlString += gzdt.getWjnr() + "','";
		sqlString += gzdt.getFileUrl() + "','";
		sqlString += gzdt.getCzr() + "','";
		sqlString += gzdt.getCzrID() + "','";
		sqlString += gzdt.getCzsj() + "','";
		sqlString += gzdt.getCzrdw() + "','";
		sqlString += gzdt.getTjzt() + "')";
		int ret_id=0;
		try {
			Connection conn = ConnectionPoolUtils.GetPoolInstance().getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlString,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				ret_id = rs.getInt(1);
				}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret_id;
	}
	
	/*
	 *根据ID
	 *修改内容
	 */
	public boolean updateGzdt(Gzdt gzdt) {
		String sqlString = "update Gzdt set wjmc='"; 
		sqlString += gzdt.getWjmc() + "',wjnr='";
		sqlString += gzdt.getWjnr() + "',FileUrl='";
		sqlString += gzdt.getFileUrl() + "',czr='";
		sqlString += gzdt.getCzr() + "',czrID='";
		sqlString += gzdt.getCzrID() + "',czsj='";
		sqlString += gzdt.getCzsj() + "',czrdw='";
		sqlString += gzdt.getCzrdw() + "',tjzt='";
		sqlString += gzdt.getTjzt() + "' where id=" + gzdt.getId();
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
	
	/*根据记录编号删除某个文件*/
	public static int DeleteGzdtById(int id) {
		int result = 0;
		String sqlString = "delete from Gzdt where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
