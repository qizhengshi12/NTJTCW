package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.MyMessage;
import com.safety.entity.SendMessage;

public class MessageDao {

	/*
	 *
	 *新增记录  （发件箱）
	 */
	public int insertMessage(SendMessage Message) {
		String sqlString = "insert into SendMessage (dxnr,fssj,jsr,jsrID,czr,czsj,czrID,sffs) values ('";
		sqlString += Message.getDxnr() + "','";
		sqlString += Message.getFssj() + "','";
		sqlString += Message.getJsr() + "','";
		sqlString += Message.getJsrID() + "','";
		sqlString += Message.getCzr() + "','";
		sqlString += Message.getCzsj()+ "','";
		sqlString += Message.getCzrID() + "','";
		sqlString += Message.getSffs() + "')";

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
	 *新增到各人收件箱中 
	 */
	public boolean insertMyMessage(ArrayList<MyMessage> MyMessageList) {
		int len = MyMessageList.size()-1;
		String sqlString = "insert into mymessage (dxnr,jssj,jsrID,fsr,cybz) values ('";
		for(int i=0; i<len; i++){
			sqlString += MyMessageList.get(i).getDxnr() + "','";
			sqlString += MyMessageList.get(i).getJssj() + "','";
			sqlString += MyMessageList.get(i).getJsrID() + "','";
			sqlString += MyMessageList.get(i).getFsr() + "','";
			sqlString += MyMessageList.get(i).getCybz() + "'),('";
		}
		sqlString += MyMessageList.get(len).getDxnr() + "','";
		sqlString += MyMessageList.get(len).getJssj() + "','";
		sqlString += MyMessageList.get(len).getJsrID() + "','";
		sqlString += MyMessageList.get(len).getFsr() + "','";
		sqlString += MyMessageList.get(len).getCybz() + "')";
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
	 *
	 *新增到某人收件箱中 
	 */
	public boolean insertMyMessageOne(MyMessage myMessage) {
		String sqlString = "insert into mymessage (dxnr,jssj,jsrID,fsr,cybz) values ('";
		sqlString += myMessage.getDxnr() + "','";
		sqlString += myMessage.getJssj() + "','";
		sqlString += myMessage.getJsrID() + "','";
		sqlString += myMessage.getFsr() + "','";
		sqlString += myMessage.getCybz() + "')";

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
	 *
	 *查询收件箱
	 */
	public  ArrayList<MyMessage> queryMyMessageList(String srbt , String username,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<MyMessage> MyMessageList = new ArrayList<MyMessage>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from MyMessage where jsrID='"+username+"' order by jssj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from MyMessage "+srbt +" and jsrID='"+username+"' order by jssj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				MyMessage myMessage = new MyMessage();
				myMessage.setId(rs.getInt("id"));
				myMessage.setDxnr(rs.getString("dxnr"));
				myMessage.setFsr(rs.getString("fsr"));
				myMessage.setJssj(rs.getTimestamp("jssj"));
				myMessage.setJsrID(rs.getString("jsrID"));
				myMessage.setCybz(rs.getString("cybz"));
				MyMessageList.add(myMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return MyMessageList;
	}
	/*
	 *根据条件
	 *查询收件箱数目
	 */
	public  int queryMyMessageListCount(String srbt, String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from MyMessage where jsrID='"+username+"'";
		}else{
			sqlString = "select count(*) from MyMessage "+srbt+" and jsrID='"+username+"'";
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
	 *查询收件箱未阅读数目
	 */
	public  int MyMessageCount(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from MyMessage where cybz='未查阅' and jsrID='"+username+"'";
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
	/*更新状态*/
	public boolean updateMyMessageById(int id) {
		String sqlString = "update MyMessage set cybz='已查阅' where id=" + id;
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
	/*根据记录编号删除某个收件箱*/
	public int DeleteMyMessageById(int id) {
		int result = 0;
		String sqlString = "delete from MyMessage where id=" + id;
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

	/*根据记录编号删除某个收件箱*/
	public int DeleteMyMessageByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from MyMessage where id in (" + IDList+")";
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
	public  ArrayList<SendMessage> querySendMessageList(String srbt , String username,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from SendMessage where czrID='"+username+"' order by fssj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from SendMessage "+srbt +" and czrID='"+username+"' order by fssj desc limit "+begin+","+pageSize;
		}
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
	 *根据条件
	 *查询发件箱数目
	 */
	public  int querySendMessageListCount(String srbt, String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from SendMessage where czrID='"+username+"'";
		}else{
			sqlString = "select count(*) from SendMessage "+srbt+" and czrID='"+username+"'";
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
	/*根据记录编号删除某个发件箱*/
	public int DeleteSendMessageByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from SendMessage where id in (" + IDList+")";
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

	/*根据记录编号删除某个发件箱*/
	public int DeleteSendMessageById(int id) {
		int result = 0;
		String sqlString = "delete from SendMessage where id=" + id;
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
