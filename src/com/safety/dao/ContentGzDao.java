package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentGz;

public class ContentGzDao {
	/*
	 *根据多个ID号
	 *查询法律法规信息 
	 */
	public  ArrayList<ContentGz> queryGzByIdList(String idList, int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGz> GzList = new ArrayList<ContentGz>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGz Gz = new ContentGz();
				Gz.setId(rs.getInt("id"));
				Gz.setBt(rs.getString("bt"));
				Gz.setWh(rs.getString("wh"));
				Gz.setSsrq(rs.getDate("ssrq"));
				Gz.setFbdw(rs.getString("fbdw"));
				Gz.setFatherid(rs.getString("fatherid"));
				Gz.setFileUrl(rs.getString("fileUrl"));
				Gz.setCzr(rs.getString("czr"));
				Gz.setCzrID(rs.getString("czrID"));
				GzList.add(Gz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return GzList;
	}

	/*
	 *根据多个ID号
	 *查询法律法规信息数量
	 */
	public int queryGzByIdListCount(String idList) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from content_Flfg where id in ( "+idList+" ) order by id";
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
	 *查询一条法律法规信息 
	 */
	public  ContentGz queryGzByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ContentGz Gz = new ContentGz();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Gz.setId(rs.getInt("id"));
				Gz.setBt(rs.getString("bt"));
				Gz.setWh(rs.getString("wh"));
				Gz.setSsrq(rs.getDate("ssrq"));
				Gz.setFbdw(rs.getString("fbdw"));
				Gz.setFatherid(rs.getString("fatherid"));
				Gz.setFileUrl(rs.getString("fileUrl"));
				Gz.setCzr(rs.getString("czr"));
				Gz.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Gz;
	}
	
	
	
	/*
	 *根据条件
	 *查询法律法规信息 
	 */
	public  ArrayList<ContentGz> queryGzByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentGz> GzList = new ArrayList<ContentGz>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Flfg order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Flfg "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGz Gz = new ContentGz();
				Gz.setId(rs.getInt("id"));
				Gz.setBt(rs.getString("bt"));
				Gz.setWh(rs.getString("wh"));
				Gz.setSsrq(rs.getDate("ssrq"));
				Gz.setFbdw(rs.getString("fbdw"));
				Gz.setFatherid(rs.getString("fatherid"));
				Gz.setFileUrl(rs.getString("fileUrl"));
				Gz.setCzr(rs.getString("czr"));
				Gz.setCzrID(rs.getString("czrID"));
				GzList.add(Gz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GzList;
	}

	/*
	 *根据条件
	 *查询法律法规记录数
	 */
	public  int queryGzByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Flfg";
		}else{
			sqlString = "select count(*) from content_Flfg "+srbt;
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
	

	/*根据记录编号删除某个法律法规*/
	public static int DeleteGzById(int id) {
		int result = 0;
		String sqlString = "delete from content_Flfg where id=" + id;
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
	 *新增法律法规记录  
	 */
	public int insertGz(ContentGz Gz) {
		String sqlString = "insert into content_Flfg (bt,lx,wh,ssrq,fbdw,fatherid,FileUrl,czsj,czr,czrID) values('";
		sqlString += Gz.getBt() + "','";
		sqlString += Gz.getLx() + "','";
		sqlString += Gz.getWh() + "','";
		sqlString += Gz.getSsrq() + "','";
		sqlString += Gz.getFbdw() + "','";
		sqlString += Gz.getFatherid() + "','";
		sqlString += Gz.getFileUrl() + "','";
		sqlString += Gz.getCzsj() + "','";
		sqlString += Gz.getCzr() + "','";
		sqlString += Gz.getCzrID() + "')";

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
	 *修改法律法规记录  
	 */
	public boolean updateGz(ContentGz Gz) {
		String sqlString = "update content_Flfg set bt='"; 
		sqlString += Gz.getBt() + "',wh='";
		sqlString += Gz.getWh()  + "',ssrq='";
		sqlString += Gz.getSsrq() + "',fbdw='";
		sqlString += Gz.getFbdw() + "',FileUrl='";
		sqlString += Gz.getFileUrl() + "',czsj='";
		sqlString += Gz.getCzsj()+ "',czr='";
		sqlString += Gz.getCzr()+ "',czrID='";
		sqlString += Gz.getCzrID() + "' where id=" + Gz.getId();
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
