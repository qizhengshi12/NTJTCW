package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Gzflsr;
import com.safety.entity.GzflsrZxm;

public class GzflsrDao {
	/*
	 *根据条件
	 *查询预算执行表记录
	 */
	public  ArrayList<Gzflsr> queryGzflsrListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Gzflsr> GzflsrList = new ArrayList<Gzflsr>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from gzflsr  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from gzflsr "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Gzflsr gzflsr = new Gzflsr();
				gzflsr.setId(rs.getInt("id"));
				gzflsr.setYear(rs.getInt("year"));
				gzflsr.setMonth(rs.getInt("month"));
				gzflsr.setCzr(rs.getString("czr"));
				gzflsr.setCzrdw(rs.getString("czrdw"));
				gzflsr.setCzrID(rs.getString("czrID"));
				gzflsr.setCzsj(rs.getTimestamp("czsj"));
				gzflsr.setXmid(rs.getString("xmid"));
				gzflsr.setTjzt(rs.getString("tjzt"));
				GzflsrList.add(gzflsr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GzflsrList;
	}
	/*
	 *根据条件
	 *查询预算执行表记录数
	 */
	public  int queryGzflsrListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from gzflsr";
		}else{
			sqlString = "select count(*) from gzflsr "+srbt;
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
	public  Gzflsr queryGzflsrByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Gzflsr gzflsr = new Gzflsr();
		String sqlString = "select * from gzflsr where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				gzflsr.setId(rs.getInt("id"));
				gzflsr.setYear(rs.getInt("year"));
				gzflsr.setMonth(rs.getInt("month"));
				gzflsr.setCzr(rs.getString("czr"));
				gzflsr.setCzrdw(rs.getString("czrdw"));
				gzflsr.setCzrID(rs.getString("czrID"));
				gzflsr.setCzsj(rs.getTimestamp("czsj"));
				gzflsr.setXmid(rs.getString("xmid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return gzflsr;
	}
	/*
	 *根据条件
	 *查询预算执行表记录_子项目表
	 */
	public  ArrayList<GzflsrZxm> queryGzflsrZxmByIDList(String IDList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<GzflsrZxm> GzflsrZxmList = new ArrayList<GzflsrZxm>();
		String sqlString = "select * from gzflsr_zxm  where id in ("+IDList+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				GzflsrZxm gzflsrZxm = new GzflsrZxm();
				gzflsrZxm.setId(rs.getInt("id"));
				gzflsrZxm.setZgxm(rs.getString("zgxm"));
				gzflsrZxm.setGwgz(rs.getString("gwgz"));
				gzflsrZxm.setXjgz(rs.getString("xjgz"));
				gzflsrZxm.setGwjt(rs.getString("gwjt"));
				gzflsrZxm.setShbt(rs.getString("shbt"));
				gzflsrZxm.setJljx(rs.getString("jljx"));
				gzflsrZxm.setZfbt(rs.getString("zfbt"));
				gzflsrZxm.setCt(rs.getString("ct"));
				gzflsrZxm.setYbbt(rs.getString("ybbt"));
				gzflsrZxm.setGjj(rs.getString("gjj"));
				gzflsrZxm.setQtsr(rs.getString("qtsr"));
				gzflsrZxm.setYfhj(rs.getString("yfhj"));
				GzflsrZxmList.add(gzflsrZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GzflsrZxmList;
	}
	
	/*
	 *
	 *新增预算执行表记录
	 */
	public int insertGzflsr(Gzflsr gzflsr) {
		String sqlString = "insert into gzflsr (year,month,czr,czrdw,czrID,czsj,xmid,tjzt) values(";
		sqlString += gzflsr.getYear() + ",";
		sqlString += gzflsr.getMonth() + ",'";
		sqlString += gzflsr.getCzr() + "','";
		sqlString += gzflsr.getCzrdw() + "','";
		sqlString += gzflsr.getCzrID() + "','";
		sqlString += gzflsr.getCzsj() + "','";
		sqlString += gzflsr.getXmid() + "','";
		sqlString += gzflsr.getTjzt() + "')";
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
	 *
	 *新增预算执行表记录_子项目表记录
	 */
	public int insertGzflsrZxm(GzflsrZxm gzflsrZxm) {
		String sqlString = "insert into gzflsr_zxm (zgxm,gwgz,xjgz,gwjt,shbt,jljx,zfbt,ct,ybbt,gjj,qtsr,yfhj) values('";
		sqlString += gzflsrZxm.getZgxm() + "','";
		sqlString += gzflsrZxm.getGwgz() + "','";
		sqlString += gzflsrZxm.getXjgz() + "','";
		sqlString += gzflsrZxm.getGwjt() + "','";
		sqlString += gzflsrZxm.getShbt() + "','";
		sqlString += gzflsrZxm.getJljx() + "','";
		sqlString += gzflsrZxm.getZfbt() + "','";
		sqlString += gzflsrZxm.getCt() + "','";
		sqlString += gzflsrZxm.getYbbt() + "','";
		sqlString += gzflsrZxm.getGjj() + "','";
		sqlString += gzflsrZxm.getQtsr() + "','";
		sqlString += gzflsrZxm.getYfhj() + "')";
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
	 *修改预算执行表记录
	 */
	public boolean updateGzflsr(Gzflsr gzflsr) {
		String sqlString = "update gzflsr set year="; 
		sqlString += gzflsr.getYear() + ",month=";
		sqlString += gzflsr.getMonth() + ",czr='";
		sqlString += gzflsr.getCzr() + "',czrdw='";
		sqlString += gzflsr.getCzrdw() + "',czrID='";
		sqlString += gzflsr.getCzrID() + "',czsj='";
		sqlString += gzflsr.getCzsj() + "',xmid='";
		sqlString += gzflsr.getXmid() + "',tjzt='";
		sqlString += gzflsr.getTjzt() + "' where id=" + gzflsr.getId();
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

	/*根据记录编号删除预算执行表记录*/
	public int deleteGzflsrById(int id) {
		int result = 0;
		String sqlString = "delete from gzflsr where id=" + id;
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
	/*根据记录编号删除预算执行表记录_子项目表*/
	public int deleteGzflsrZxmByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from gzflsr_zxm where id in ("+IDList+")";
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
