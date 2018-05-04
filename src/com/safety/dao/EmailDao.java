package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.SendEmail;
import com.safety.entity.MyEmail;

public class EmailDao {
	/*
	 *
	 *新增记录  （发件箱）
	 */
	public int insertEmail(SendEmail Email) {
		String sqlString = "insert into SendEmail (bt,nr,FileUrl,jsr,jsrID,msr,msrID,wdr,fszt,sczt,czr,czsj,czrID) values ('";
		sqlString += Email.getBt() + "','";
		sqlString += Email.getNr() + "','";
		sqlString += Email.getFileUrl() + "','";
		sqlString += Email.getJsr() + "','";
		sqlString += Email.getJsrID() + "','";
		sqlString += Email.getMsr() + "','";
		sqlString += Email.getMsrID() + "','";
		sqlString += Email.getWdr() + "','";
		sqlString += Email.getFszt() + "','";
		sqlString += Email.getSczt() + "','";
		sqlString += Email.getCzr() + "','";
		sqlString += Email.getCzsj()+ "','";
		sqlString += Email.getCzrID() + "')";

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
	public boolean insertMyEmail(ArrayList<MyEmail> MyEmailList) {
		int len = MyEmailList.size()-1;
		String sqlString = "insert into MyEmail (sendid,bt,nr,FileUrl,fsr,fsrID,allfsr,allfsrID,cyzt,sczt,jsr,jsrID,jssj) values (";
		for(int i=0; i<len; i++){
			sqlString += MyEmailList.get(i).getSendid() + ",'";
			sqlString += MyEmailList.get(i).getBt() + "','";
			sqlString += MyEmailList.get(i).getNr() + "','";
			sqlString += MyEmailList.get(i).getFileUrl() + "','";
			sqlString += MyEmailList.get(i).getFsr() + "','";
			sqlString += MyEmailList.get(i).getFsrID()+ "','";
			sqlString += MyEmailList.get(i).getAllfsr()+ "','";
			sqlString += MyEmailList.get(i).getAllfsrID()+ "','";
			sqlString += MyEmailList.get(i).getCyzt()+ "','";
			sqlString += MyEmailList.get(i).getSczt() + "','";
			sqlString += MyEmailList.get(i).getJsr() + "','";
			sqlString += MyEmailList.get(i).getJsrID() + "','";
			sqlString += MyEmailList.get(i).getJssj() + "'),(";
		}
		sqlString += MyEmailList.get(len).getSendid() + ",'";
		sqlString += MyEmailList.get(len).getBt() + "','";
		sqlString += MyEmailList.get(len).getNr() + "','";
		sqlString += MyEmailList.get(len).getFileUrl() + "','";
		sqlString += MyEmailList.get(len).getFsr() + "','";
		sqlString += MyEmailList.get(len).getFsrID() + "','";
		sqlString += MyEmailList.get(len).getAllfsr() + "','";
		sqlString += MyEmailList.get(len).getAllfsrID() + "','";
		sqlString += MyEmailList.get(len).getCyzt() + "','";
		sqlString += MyEmailList.get(len).getSczt() + "','";
		sqlString += MyEmailList.get(len).getJsr() + "','";
		sqlString += MyEmailList.get(len).getJsrID() + "','";
		sqlString += MyEmailList.get(len).getJssj() + "')";
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
	 *修改草稿箱文件  
	 */
	public boolean updateEmail(SendEmail sendEmail) {
		String sqlString = "update SendEmail set bt='"; 
		sqlString += sendEmail.getBt() + "',nr='";
		sqlString += sendEmail.getNr() + "',FileUrl='";
		sqlString += sendEmail.getFileUrl() + "',jsr='";
		sqlString += sendEmail.getJsr() + "',jsrID='";
		sqlString += sendEmail.getJsrID() + "',wdr='";
		sqlString += sendEmail.getWdr() + "',msr='";
		sqlString += sendEmail.getMsr() + "',msrID='";
		sqlString += sendEmail.getMsrID() + "',fszt='";
		sqlString += sendEmail.getFszt() + "',sczt='";
		sqlString += sendEmail.getSczt() + "',czr='";
		sqlString += sendEmail.getCzr()+ "',czsj='";
		sqlString += sendEmail.getCzsj() + "',czrID='";
		sqlString += sendEmail.getCzrID() + "' where id=" + sendEmail.getId();
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
	public  ArrayList<MyEmail> queryMyEmailList(String srbt , String username,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<MyEmail> MyEmailList = new ArrayList<MyEmail>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from MyEmail where jsrID='"+username+"' order by jssj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from MyEmail "+srbt +" and jsrID='"+username+"' order by jssj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				MyEmail myEmail = new MyEmail();
				myEmail.setId(rs.getInt("id"));
				myEmail.setSendid(rs.getInt("sendid"));
				myEmail.setBt(rs.getString("bt"));
//				myEmail.setNr(rs.getString("nr"));
//				myEmail.setFileUrl(rs.getString("FileUrl"));
				myEmail.setFsr(rs.getString("fsr"));
				myEmail.setFsrID(rs.getString("fsrID"));
				myEmail.setAllfsr(rs.getString("allfsr"));
				myEmail.setAllfsrID(rs.getString("allfsrID"));
				myEmail.setCyzt(rs.getString("cyzt"));
				myEmail.setSczt(rs.getString("sczt"));
				myEmail.setJsr(rs.getString("jsr"));
				myEmail.setJssj(rs.getTimestamp("jssj"));
				myEmail.setJsrID(rs.getString("jsrID"));
				MyEmailList.add(myEmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return MyEmailList;
	}

	/*
	 *根据条件
	 *查询邮件未阅读数目
	 */
	public  int MyEmailCount(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from MyEmail where cyzt='1' and jsrID='"+username+"'";
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
	 *查询收件箱数目
	 */
	public  int queryMyEmailListCount(String srbt, String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from MyEmail where jsrID='"+username+"'";
		}else{
			sqlString = "select count(*) from MyEmail "+srbt+" and jsrID='"+username+"'";
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
	 *查询收件
	 */
	public  MyEmail queryMyEmailByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		MyEmail myEmail = new MyEmail();
		String sqlString = "select * from MyEmail where id="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				myEmail.setId(rs.getInt("id"));
				myEmail.setSendid(rs.getInt("sendid"));
				myEmail.setBt(rs.getString("bt"));
				myEmail.setNr(rs.getString("nr"));
				myEmail.setFileUrl(rs.getString("FileUrl"));
				myEmail.setFsr(rs.getString("fsr"));
				myEmail.setFsrID(rs.getString("fsrID"));
				myEmail.setAllfsr(rs.getString("allfsr"));
				myEmail.setAllfsrID(rs.getString("allfsrID"));
				myEmail.setCyzt(rs.getString("cyzt"));
				myEmail.setSczt(rs.getString("sczt"));
				myEmail.setJsr(rs.getString("jsr"));
				myEmail.setJssj(rs.getTimestamp("jssj"));
				myEmail.setJsrID(rs.getString("jsrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return myEmail;
	}

	
	/*
	 *
	 *修改查阅文件状态  
	 */
	public boolean updateCyztByID(int id) {
		String sqlString = "update MyEmail set cyzt=2 where id=" + id;
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
	 *修改未读人名单  
	 */
	public boolean updateWdrByID(int id, String wdr) {
		String sqlString = "update SendEmail set wdr='"+wdr+"' where id=" + id;
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
	public int DeleteMyEmailById(int id) {
		int result = 0;
		String sqlString = "delete from MyEmail where id=" + id;
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

	/*根据记录编号批量删除收件箱*/
	public int DeleteMyEmailByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from MyEmail where id in (" + IDList+")";
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
	public  ArrayList<SendEmail> querySendEmailList(String srbt , String username,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<SendEmail> SendEmailList = new ArrayList<SendEmail>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from SendEmail where czrID='"+username+"' order by czsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from SendEmail "+srbt +" and czrID='"+username+"' order by czsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SendEmail sendEmail = new SendEmail();
				sendEmail.setId(rs.getInt("id"));
				sendEmail.setBt(rs.getString("bt"));
				sendEmail.setNr(rs.getString("nr"));
				sendEmail.setFileUrl(rs.getString("FileUrl"));
				sendEmail.setJsr(rs.getString("jsr"));
				sendEmail.setJsrID(rs.getString("jsrID"));
				sendEmail.setMsr(rs.getString("msr"));
				sendEmail.setMsrID(rs.getString("msrID"));
				sendEmail.setWdr(rs.getString("wdr"));
				sendEmail.setFszt(rs.getString("fszt"));
				sendEmail.setSczt(rs.getString("sczt"));
				sendEmail.setCzr(rs.getString("czr"));
				sendEmail.setCzrID(rs.getString("czrID"));
				sendEmail.setCzsj(rs.getTimestamp("czsj"));
				SendEmailList.add(sendEmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return SendEmailList;
	}
	/*
	 *根据条件
	 *查询发件箱数目
	 */
	public  int querySendEmailListCount(String srbt, String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from SendEmail where czrID='"+username+"'";
		}else{
			sqlString = "select count(*) from SendEmail "+srbt+" and czrID='"+username+"'";
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
	 *查询发件箱
	 */
	public  SendEmail querySendEmailByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		SendEmail sendEmail = new SendEmail();
		String sqlString = "select * from SendEmail where id="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				sendEmail.setId(rs.getInt("id"));
				sendEmail.setBt(rs.getString("bt"));
				sendEmail.setNr(rs.getString("nr"));
				sendEmail.setFileUrl(rs.getString("FileUrl"));
				sendEmail.setJsr(rs.getString("jsr"));
				sendEmail.setJsrID(rs.getString("jsrID"));
				sendEmail.setMsr(rs.getString("msr"));
				sendEmail.setMsrID(rs.getString("msrID"));
				sendEmail.setWdr(rs.getString("wdr"));
				sendEmail.setFszt(rs.getString("fszt"));
				sendEmail.setSczt(rs.getString("sczt"));
				sendEmail.setCzr(rs.getString("czr"));
				sendEmail.setCzrID(rs.getString("czrID"));
				sendEmail.setCzsj(rs.getTimestamp("czsj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return sendEmail;
	}
	
	/*根据记录编号批量删除发件箱*/
	public int DeleteSendEmailByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from SendEmail where id in (" + IDList+")";
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
	public int DeleteSendEmailById(int id) {
		int result = 0;
		String sqlString = "delete from SendEmail where id=" + id;
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
	 *根据ID
	 *修改删除到回收站  
	 */
	public boolean UpdateMyEmailByID(int id) {
		String sqlString = "update MyEmail set sczt='2' where id=" + id;
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
	 *根据IDList
	 *修改删除到回收站  
	 */
	public boolean UpdateMyEmailByIDList(String idList) {
		String sqlString = "update MyEmail set sczt='2' where id in (" + idList +")";
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
	 *修改还原到收件箱
	 */
	public boolean ReturnMyEmailByID(int id) {
		String sqlString = "update MyEmail set sczt='1' where id=" + id;
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
