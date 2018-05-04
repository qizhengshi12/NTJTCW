package com.safety.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.Utils.DB;
import com.safety.entity.PostInformation;
import com.safety.entity.SendMessage;

public class TimeTaskDao {
	/*
	 *根据多个ID号
	 *查询 文件管理 下发文件回复更新超时信息
	 */
	public  String querySFCSList(String now,String zt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String IDList = "";
		String sqlString = "select id from wjglhf where hfqx <'"+now+"' and hfzt='"+zt+"'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				if("".equals(IDList)){
					IDList = rs.getString("id");
				}else{
					IDList = IDList +","+rs.getString("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return IDList;
	}
	/*
	 *根据ID
	 *修改整改期限
	 */
	public void updateSFCS(String id) {
		String sqlString = "update wjglhf set sfcs='超时' where id in (" + id +")";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	/* 
	 *查询最新的五张图
	 */
	public  String querylimStr() {
		 /* 保存符合条件的某页记录的集合链表 */
		String limStr = "";
		String sqlString = "select id from PostInformation where photoURL<>'' order by id desc limit 5";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				if("".equals(limStr)){
					limStr = rs.getInt("id")+"";
				}else{
					limStr = limStr+","+rs.getInt("id");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return limStr;
	}

	/* 
	 *查询信息需删除的首页窗口新闻
	 */
	public  ArrayList<PostInformation> queryInformatDel(String limStr ,String ltime) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
		String sqlString = "select id,fileURL,photoURL from PostInformation where tjsj <'"+ltime+"' and  id not in("+limStr+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				PostInformation postInformation = new PostInformation();
				postInformation.setId(rs.getInt("id"));
				postInformation.setFileURL(rs.getString("fileURL"));
				postInformation.setPhotoURL(rs.getString("photoURL"));
				InformatList.add(postInformation);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return InformatList;
	}
	/*
	 *根据ID
	 *删除信息
	 */
	public int DeleteInformat(int id) {
		int result = 0;
		String sqlString = "delete from PostInformation where id=" + id;
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
	 *查询发件箱
	 */
	public  ArrayList<SendMessage> querySendSendMessageList(String dataBegin , String dataEnd) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
		String sqlString =  "select * from SendMessage where sffs='未发送' and fssj>='"+dataBegin+"' and fssj<='"+dataEnd+"'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SendMessage sendMessage = new SendMessage();
				sendMessage.setId(rs.getInt("id"));
				sendMessage.setDxnr(rs.getString("dxnr"));
				sendMessage.setJsr(rs.getString("jsr"));
				sendMessage.setJsrID(rs.getString("jsrID"));
				sendMessage.setFssj(rs.getTimestamp("fssj"));
				sendMessage.setCzr(rs.getString("czr"));
				sendMessage.setCzrID(rs.getString("czrID"));
				sendMessage.setCzsj(rs.getTimestamp("czsj"));
				sendMessage.setSffs(rs.getString("sffs"));
				SendMessageList.add(sendMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return SendMessageList;
	}
	/*
	 * 根据ID
	 * 更新短信发送状态
	 */
	public void updateDXFSZT(int id) {
		String sqlString = "update SendMessage set sffs='已发送' where id =" + id;
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
