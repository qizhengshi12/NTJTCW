package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentFl;

public class ContentFlDao {
	/*
	 *根据多个ID号
	 *查询法律法规信息 
	 */
	public  ArrayList<ContentFl> queryFlByIdList(String idList, int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentFl Fl = new ContentFl();
				Fl.setId(rs.getInt("id"));
				Fl.setBt(rs.getString("bt"));
				Fl.setWh(rs.getString("wh"));
				Fl.setSsrq(rs.getDate("ssrq"));
				Fl.setFbdw(rs.getString("fbdw"));
				Fl.setFatherid(rs.getString("fatherid"));
				Fl.setFileUrl(rs.getString("fileUrl"));
				Fl.setCzr(rs.getString("czr"));
				Fl.setCzrID(rs.getString("czrID"));
				FlList.add(Fl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return FlList;
	}

	/*
	 *根据多个ID号
	 *查询法律法规信息数量
	 */
	public int queryFlByIdListCount(String idList) {
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
	public  ContentFl queryFlByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ContentFl Fl = new ContentFl();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Fl.setId(rs.getInt("id"));
				Fl.setBt(rs.getString("bt"));
				Fl.setWh(rs.getString("wh"));
				Fl.setSsrq(rs.getDate("ssrq"));
				Fl.setFbdw(rs.getString("fbdw"));
				Fl.setFatherid(rs.getString("fatherid"));
				Fl.setFileUrl(rs.getString("fileUrl"));
				Fl.setCzr(rs.getString("czr"));
				Fl.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Fl;
	}
	
	
	
	/*
	 *根据条件
	 *查询法律法规信息 
	 */
	public  ArrayList<ContentFl> queryFlByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
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
				ContentFl Fl = new ContentFl();
				Fl.setId(rs.getInt("id"));
				Fl.setBt(rs.getString("bt"));
				Fl.setWh(rs.getString("wh"));
				Fl.setSsrq(rs.getDate("ssrq"));
				Fl.setFbdw(rs.getString("fbdw"));
				Fl.setFatherid(rs.getString("fatherid"));
				Fl.setFileUrl(rs.getString("fileUrl"));
				Fl.setCzr(rs.getString("czr"));
				Fl.setCzrID(rs.getString("czrID"));
				FlList.add(Fl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return FlList;
	}
	/*
	 *根据条件
	 *查询法律法规信息 
	 */
	public  ArrayList<ContentFl> queryFlSy(int num) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
		String sqlString = "select * from content_Flfg order by id desc;";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<num&&rs.next(); i++){
				ContentFl Fl = new ContentFl();
				Fl.setId(rs.getInt("id"));
				Fl.setBt(rs.getString("bt"));
				Fl.setCzsj(rs.getTimestamp("czsj"));
				FlList.add(Fl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return FlList;
	}
	/*
	 *根据条件
	 *查询法律法规记录数
	 */
	public  int queryFlByBtCount(String srbt) {
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
	public static int DeleteFlById(int id) {
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
	public int insertFl(ContentFl Fl) {
		String sqlString = "insert into content_Flfg (bt,lx,wh,ssrq,fbdw,fatherid,FileUrl,czsj,czr,czrID) values('";
		sqlString += Fl.getBt() + "','";
		sqlString += Fl.getLx() + "','";
		sqlString += Fl.getWh() + "','";
		sqlString += Fl.getSsrq() + "','";
		sqlString += Fl.getFbdw() + "','";
		sqlString += Fl.getFatherid() + "','";
		sqlString += Fl.getFileUrl() + "','";
		sqlString += Fl.getCzsj() + "','";
		sqlString += Fl.getCzr() + "','";
		sqlString += Fl.getCzrID() + "')";

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
	public boolean updateFl(ContentFl Fl) {
		String sqlString = "update content_Flfg set bt='"; 
		sqlString += Fl.getBt() + "',wh='";
		sqlString += Fl.getWh()  + "',ssrq='";
		sqlString += Fl.getSsrq() + "',fbdw='";
		sqlString += Fl.getFbdw() + "',FileUrl='";
		sqlString += Fl.getFileUrl() + "',czsj='";
		sqlString += Fl.getCzsj()+ "',czr='";
		sqlString += Fl.getCzr()+ "',czrID='";
		sqlString += Fl.getCzrID() + "' where id=" + Fl.getId();
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
