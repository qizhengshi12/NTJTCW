package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentGlzd;

public class ContentGlzdDao {
	/*
	 *根据多个ID号
	 *查询单位制度信息 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByIdList(String idList, int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "select * from Content_Glzd where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGlzd Glzd = new ContentGlzd();
				Glzd.setId(rs.getInt("id"));
				Glzd.setBt(rs.getString("bt"));
				Glzd.setWh(rs.getString("wh"));
				Glzd.setSsrq(rs.getDate("ssrq"));
				Glzd.setFbdw(rs.getString("fbdw"));
				Glzd.setSfgk(rs.getString("sfgk"));
				Glzd.setFatherid(rs.getString("fatherid"));
				Glzd.setFileUrl(rs.getString("fileUrl"));
				Glzd.setCzr(rs.getString("czr"));
				Glzd.setCzrID(rs.getString("czrID"));
				GlzdList.add(Glzd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return GlzdList;
	}

	/*
	 *根据多个ID号
	 *查询单位制度信息数量
	 */
	public int queryGlzdByIdListCount(String idList) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from Content_Glzd where id in ( "+idList+" ) order by id";
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
	 *根据多个ID号
	 *查询单位制度信息 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByIdListGK(String idList, int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "select * from Content_Glzd where id in ( "+idList+" ) and sfgk='0' order by id limit "+begin+","+pageSize;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGlzd Glzd = new ContentGlzd();
				Glzd.setId(rs.getInt("id"));
				Glzd.setBt(rs.getString("bt"));
				Glzd.setWh(rs.getString("wh"));
				Glzd.setSsrq(rs.getDate("ssrq"));
				Glzd.setFbdw(rs.getString("fbdw"));
				Glzd.setSfgk(rs.getString("sfgk"));
				Glzd.setFatherid(rs.getString("fatherid"));
				Glzd.setFileUrl(rs.getString("fileUrl"));
				Glzd.setCzr(rs.getString("czr"));
				Glzd.setCzrID(rs.getString("czrID"));
				GlzdList.add(Glzd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return GlzdList;
	}

	/*
	 *根据多个ID号
	 *查询单位制度信息数量
	 */
	public int queryGlzdByIdListCountGK(String idList) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from Content_Glzd where id in ( "+idList+" ) and sfgk='0' order by id";
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
	 *查询一条单位制度信息 
	 */
	public  ContentGlzd queryGlzdByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ContentGlzd Glzd = new ContentGlzd();
		String sqlString = "select * from content_Glzd where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Glzd.setId(rs.getInt("id"));
				Glzd.setBt(rs.getString("bt"));
				Glzd.setWh(rs.getString("wh"));
				Glzd.setSsrq(rs.getDate("ssrq"));
				Glzd.setSfgk(rs.getString("sfgk"));
				Glzd.setFbdw(rs.getString("fbdw"));
				Glzd.setFatherid(rs.getString("fatherid"));
				Glzd.setFileUrl(rs.getString("fileUrl"));
				Glzd.setCzr(rs.getString("czr"));
				Glzd.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Glzd;
	}
	
	
	
	/*
	 *根据条件
	 *查询单位制度信息 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Glzd order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Glzd "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGlzd Glzd = new ContentGlzd();
				Glzd.setId(rs.getInt("id"));
				Glzd.setBt(rs.getString("bt"));
				Glzd.setWh(rs.getString("wh"));
				Glzd.setSsrq(rs.getDate("ssrq"));
				Glzd.setSfgk(rs.getString("sfgk"));
				Glzd.setFbdw(rs.getString("fbdw"));
				Glzd.setFatherid(rs.getString("fatherid"));
				Glzd.setFileUrl(rs.getString("fileUrl"));
				Glzd.setCzr(rs.getString("czr"));
				Glzd.setCzrID(rs.getString("czrID"));
				GlzdList.add(Glzd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GlzdList;
	}

	/*
	 *根据条件
	 *查询单位制度记录数
	 */
	public  int queryGlzdByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Glzd";
		}else{
			sqlString = "select count(*) from content_Glzd "+srbt;
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
	 *根据条件
	 *查询单位制度信息 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByBtGK(String srbt ,int begin, int pageSize, String company) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Glzd where fbdw='"+company+ "' or sfgk='0' order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Glzd "+srbt +" and (fbdw='"+company+ "' or sfgk='0') order by id limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGlzd Glzd = new ContentGlzd();
				Glzd.setId(rs.getInt("id"));
				Glzd.setBt(rs.getString("bt"));
				Glzd.setWh(rs.getString("wh"));
				Glzd.setSsrq(rs.getDate("ssrq"));
				Glzd.setSfgk(rs.getString("sfgk"));
				Glzd.setFbdw(rs.getString("fbdw"));
				Glzd.setFatherid(rs.getString("fatherid"));
				Glzd.setFileUrl(rs.getString("fileUrl"));
				Glzd.setCzr(rs.getString("czr"));
				Glzd.setCzrID(rs.getString("czrID"));
				GlzdList.add(Glzd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GlzdList;
	}

	/*
	 *根据条件
	 *查询单位制度记录数
	 */
	public  int queryGlzdByBtCountGK(String srbt, String company) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Glzd where fbdw='"+company+ "' or sfgk='0'";
		}else{
			sqlString = "select count(*) from content_Glzd "+srbt+" and (fbdw='"+company+ "' or sfgk='0')";
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
	/*根据记录编号删除某个单位制度*/
	public static int DeleteGlzdById(int id) {
		int result = 0;
		String sqlString = "delete from content_Glzd where id=" + id;
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
	 *
	 *新增单位制度记录  
	 */
	public int insertGlzd(ContentGlzd Glzd) {
		String sqlString = "insert into content_Glzd (bt,wh,ssrq,sfgk,fbdw,fatherid,FileUrl,czsj,czr,czrID) values('";
		sqlString += Glzd.getBt() + "','";
		sqlString += Glzd.getWh() + "','";
		sqlString += Glzd.getSsrq() + "','";
		sqlString += Glzd.getSfgk() + "','";
		sqlString += Glzd.getFbdw() + "','";
		sqlString += Glzd.getFatherid() + "','";
		sqlString += Glzd.getFileUrl() + "','";
		sqlString += Glzd.getCzsj() + "','";
		sqlString += Glzd.getCzr() + "','";
		sqlString += Glzd.getCzrID() + "')";

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
	 *修改单位制度记录  
	 */
	public boolean updateGlzd(ContentGlzd Glzd) {
		String sqlString = "update content_Glzd set bt='"; 
		sqlString += Glzd.getBt() + "',wh='";
		sqlString += Glzd.getWh()  + "',ssrq='";
		sqlString += Glzd.getSsrq() + "',sfgk='";
		sqlString += Glzd.getSfgk() + "',FileUrl='";
		sqlString += Glzd.getFileUrl() + "',czsj='";
		sqlString += Glzd.getCzsj()+ "',czr='";
		sqlString += Glzd.getCzr()+ "',czrID='";
		sqlString += Glzd.getCzrID() + "' where id=" + Glzd.getId();
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
}
