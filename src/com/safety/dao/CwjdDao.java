package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Cwjd;
import com.safety.entity.Jjyxfx;

public class CwjdDao {
	/*
	 *根据查询条件
	 *查询财务监督记录
	 */
	public  ArrayList<Cwjd> queryCwjdListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Cwjd> CwjdList = new ArrayList<Cwjd>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Cwjd  order by sj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Cwjd "+srbt +" order by sj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Cwjd cwjd = new Cwjd();
				cwjd.setId(rs.getInt("id"));
				cwjd.setWjmc(rs.getString("wjmc"));
				cwjd.setWjnr(rs.getString("wjnr"));
				cwjd.setFileUrl(rs.getString("FileUrl"));
				cwjd.setCzr(rs.getString("czr"));
				cwjd.setCzrdw(rs.getString("czrdw"));
				cwjd.setCzrID(rs.getString("czrID"));
				cwjd.setCzsj(rs.getTimestamp("czsj"));
				cwjd.setSj(rs.getDate("sj"));
				cwjd.setTjzt(rs.getString("tjzt"));
				CwjdList.add(cwjd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwjdList;
	}
	/*
	 *根据条件
	 *查询财务监督记录数
	 */
	public  int queryCwjdListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Cwjd";
		}else{
			sqlString = "select count(*) from Cwjd "+srbt;
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
	public  Cwjd queryCwjdByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Cwjd cwjd = new Cwjd();
		String sqlString = "select * from Cwjd where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwjd.setId(rs.getInt("id"));
				cwjd.setWjmc(rs.getString("wjmc"));
				cwjd.setWjnr(rs.getString("wjnr"));
				cwjd.setFileUrl(rs.getString("FileUrl"));
				cwjd.setCzr(rs.getString("czr"));
				cwjd.setCzrdw(rs.getString("czrdw"));
				cwjd.setCzrID(rs.getString("czrID"));
				cwjd.setCzsj(rs.getTimestamp("czsj"));
				cwjd.setSj(rs.getDate("sj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwjd;
	}
	/*
	 *
	 *新增记录
	 */
	public int insertCwjd(Cwjd cwjd) {
		String sqlString = "insert into Cwjd (wjmc,wjnr,FileUrl,czr,czrID,czsj,czrdw,sj,tjzt) values('";
		sqlString += cwjd.getWjmc() + "','";
		sqlString += cwjd.getWjnr() + "','";
		sqlString += cwjd.getFileUrl() + "','";
		sqlString += cwjd.getCzr() + "','";
		sqlString += cwjd.getCzrID() + "','";
		sqlString += cwjd.getCzsj() + "','";
		sqlString += cwjd.getCzrdw() + "','";
		sqlString += cwjd.getSj()+ "','";
		sqlString += cwjd.getTjzt()  + "')";
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
	public boolean updateCwjd(Cwjd cwjd) {
		String sqlString = "update Cwjd set wjmc='"; 
		sqlString += cwjd.getWjmc() + "',wjnr='";
		sqlString += cwjd.getWjnr() + "',FileUrl='";
		sqlString += cwjd.getFileUrl() + "',czr='";
		sqlString += cwjd.getCzr() + "',czrID='";
		sqlString += cwjd.getCzrID() + "',czsj='";
		sqlString += cwjd.getCzsj() + "',czrdw='";
		sqlString += cwjd.getCzrdw() + "',sj='";
		sqlString += cwjd.getSj() + "',tjzt='";
		sqlString += cwjd.getTjzt() + "' where id=" + cwjd.getId();
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
	public static int DeleteCwjdById(int id) {
		int result = 0;
		String sqlString = "delete from Cwjd where id=" + id;
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
	
	/*
	 *根据查询条件
	 *查询经济运行分析记录
	 */
	public  ArrayList<Jjyxfx> queryJjyxfxListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Jjyxfx> JjyxfxList = new ArrayList<Jjyxfx>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Jjyxfx  order by sj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Jjyxfx "+srbt +" order by sj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Jjyxfx jjyxfx = new Jjyxfx();
				jjyxfx.setId(rs.getInt("id"));
				jjyxfx.setWjmc(rs.getString("wjmc"));
				jjyxfx.setWjnr(rs.getString("wjnr"));
				jjyxfx.setFileUrl(rs.getString("FileUrl"));
				jjyxfx.setCzr(rs.getString("czr"));
				jjyxfx.setCzrdw(rs.getString("czrdw"));
				jjyxfx.setCzrID(rs.getString("czrID"));
				jjyxfx.setCzsj(rs.getTimestamp("czsj"));
				jjyxfx.setSj(rs.getDate("sj"));
				jjyxfx.setTjzt(rs.getString("tjzt"));
				JjyxfxList.add(jjyxfx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return JjyxfxList;
	}
	/*
	 *根据条件
	 *查询经济运行分析记录数
	 */
	public  int queryJjyxfxListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Jjyxfx";
		}else{
			sqlString = "select count(*) from Jjyxfx "+srbt;
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
	public  Jjyxfx queryJjyxfxByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Jjyxfx jjyxfx = new Jjyxfx();
		String sqlString = "select * from Jjyxfx where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				jjyxfx.setId(rs.getInt("id"));
				jjyxfx.setWjmc(rs.getString("wjmc"));
				jjyxfx.setWjnr(rs.getString("wjnr"));
				jjyxfx.setFileUrl(rs.getString("FileUrl"));
				jjyxfx.setCzr(rs.getString("czr"));
				jjyxfx.setCzrdw(rs.getString("czrdw"));
				jjyxfx.setCzrID(rs.getString("czrID"));
				jjyxfx.setCzsj(rs.getTimestamp("czsj"));
				jjyxfx.setSj(rs.getDate("sj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return jjyxfx;
	}
	/*
	 *
	 *新增记录
	 */
	public int insertJjyxfx(Jjyxfx jjyxfx) {
		String sqlString = "insert into Jjyxfx (wjmc,wjnr,FileUrl,czr,czrID,czsj,czrdw,sj,tjzt) values('";
		sqlString += jjyxfx.getWjmc() + "','";
		sqlString += jjyxfx.getWjnr() + "','";
		sqlString += jjyxfx.getFileUrl() + "','";
		sqlString += jjyxfx.getCzr() + "','";
		sqlString += jjyxfx.getCzrID() + "','";
		sqlString += jjyxfx.getCzsj() + "','";
		sqlString += jjyxfx.getCzrdw() + "','";
		sqlString += jjyxfx.getSj()+ "','";
		sqlString += jjyxfx.getTjzt()  + "')";
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
	public boolean updateJjyxfx(Jjyxfx jjyxfx) {
		String sqlString = "update Jjyxfx set wjmc='"; 
		sqlString += jjyxfx.getWjmc() + "',wjnr='";
		sqlString += jjyxfx.getWjnr() + "',FileUrl='";
		sqlString += jjyxfx.getFileUrl() + "',czr='";
		sqlString += jjyxfx.getCzr() + "',czrID='";
		sqlString += jjyxfx.getCzrID() + "',czsj='";
		sqlString += jjyxfx.getCzsj() + "',czrdw='";
		sqlString += jjyxfx.getCzrdw() + "',sj='";
		sqlString += jjyxfx.getSj() + "',tjzt='";
		sqlString += jjyxfx.getTjzt() + "' where id=" + jjyxfx.getId();
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
	public static int DeleteJjyxfxById(int id) {
		int result = 0;
		String sqlString = "delete from Jjyxfx where id=" + id;
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
