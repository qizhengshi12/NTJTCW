package com.safety.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Checkid;
import com.safety.entity.PostInformation;
import com.safety.entity.TopScroll;

public class PostInformationDao {
	/* 
	 *��ѯ��Ϣ�����б�
	 */
	public  ArrayList<PostInformation> queryInformatListByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from PostInformation  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from PostInformation "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				PostInformation postInformation = new PostInformation();
				postInformation.setId(rs.getInt("id"));
				postInformation.setBt(rs.getString("bt"));
				postInformation.setNr(rs.getString("nr"));
				postInformation.setTjr(rs.getString("tjr"));
				postInformation.setTjrID(rs.getString("tjrID"));
				postInformation.setTjsj(rs.getDate("tjsj"));
				postInformation.setDw(rs.getString("dw"));
				postInformation.setFileURL(rs.getString("fileURL"));
				postInformation.setPhotoURL(rs.getString("photoURL"));
				postInformation.setSfsh(rs.getString("sfsh"));
				postInformation.setShr(rs.getString("shr"));
				postInformation.setShrID(rs.getString("shrID"));
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
	 *��ѯ��Ϣ�����б�
	 */
	public  int queryInformatListByCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from PostInformation";
		}else{
			sqlString = "select count(*) from PostInformation "+srbt;
		}
		/* �������ݲ���в�ѯ */
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
	/* ����ID
	 *��ѯ��Ϣ�����б�
	 */
	public  PostInformation queryInformatByID(int id) {
		PostInformation postInformation = new PostInformation();
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select * from PostInformation where id="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				postInformation.setId(rs.getInt("id"));
				postInformation.setBt(rs.getString("bt"));
				postInformation.setNr(rs.getString("nr"));
				postInformation.setTjr(rs.getString("tjr"));
				postInformation.setTjrID(rs.getString("tjrID"));
				postInformation.setTjsj(rs.getDate("tjsj"));
				postInformation.setDw(rs.getString("dw"));
				postInformation.setFileURL(rs.getString("fileURL"));
				postInformation.setPhotoURL(rs.getString("photoURL"));
				postInformation.setSfsh(rs.getString("sfsh"));
				postInformation.setShr(rs.getString("shr"));
				postInformation.setShrID(rs.getString("shrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return postInformation;
	}
	/*
	 *����ID
	 *��Ϣ�����б������  
	 */
	public boolean PostInfSH(int id, String sfsh) {
		String sqlString = "update PostInformation set sfsh='"+sfsh+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *
	 *������¼  
	 */
	public int insertInformat(PostInformation postInfor) {
		String sqlString = "insert into PostInformation (bt,nr,tjr,tjrID,tjsj,dw,fileURL, photoURL,shr,shrID,sfsh) values ('";
		sqlString += postInfor.getBt() + "','";
		sqlString += postInfor.getNr() + "','";
		sqlString += postInfor.getTjr() + "','";
		sqlString += postInfor.getTjrID() + "','";
		sqlString += postInfor.getTjsj() + "','";
		sqlString += postInfor.getDw() + "','";
		sqlString += postInfor.getFileURL() + "','";
		sqlString += postInfor.getPhotoURL()+ "','";
		sqlString += postInfor.getShr()+ "','";
		sqlString += postInfor.getShrID()+ "','";
		sqlString += postInfor.getSfsh() + "')";
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
	 *�޸ļ�¼  
	 */
	public boolean updateInformat(PostInformation postInfor) {
		String sqlString = "update PostInformation set bt='"; 
		sqlString += postInfor.getBt() + "',nr='";
		sqlString += postInfor.getNr() + "',tjr='";
		sqlString += postInfor.getTjr() + "',tjrID='";
		sqlString += postInfor.getTjrID() + "',tjsj='";
		sqlString += postInfor.getTjsj() + "',dw='";
		sqlString += postInfor.getDw() + "',fileURL='";
		sqlString += postInfor.getFileURL() + "',photoURL='";
		sqlString += postInfor.getPhotoURL() + "',shr='";
		sqlString += postInfor.getShr() + "',shrID='";
		sqlString += postInfor.getShrID() + "',sfsh='";
		sqlString += postInfor.getSfsh() + "' where id=" + postInfor.getId();
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
	 *����ID
	 *ɾ����Ϣ
	 */
	public static int DeleteById(int id) {
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
	 *��ѯ��Ϣ�����б�ѡ���������ͼ��
	 */
	public  ArrayList<PostInformation> queryInformatFirst() {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
		String sqlString = "select id,bt,photoURL from PostInformation where photoURL<>'' and sfsh='2' order by id desc";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			int i=0;
			while(rs.next()&&i<5) {
				PostInformation postInformation = new PostInformation();
				postInformation.setId(rs.getInt("id"));
				postInformation.setBt(rs.getString("bt"));
				postInformation.setPhotoURL(rs.getString("photoURL"));
				InformatList.add(postInformation);
				i=i+1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return InformatList;
	}
	/*
	 *��ѯ���˹�����Ļ
	 */
	public  TopScroll queryTopScroll() {
		TopScroll topScroll = new TopScroll();
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select * from topScroll";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				topScroll.setId(rs.getInt("id"));
				topScroll.setContent(rs.getString("content"));
				topScroll.setSpeed(rs.getString("speed"));
				topScroll.setDirection(rs.getString("direction"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return topScroll;
	}
	/*
	 *
	 *�������˹�����Ļ  
	 */
	public int insertTopScroll(TopScroll topScroll) {
		String sqlString = "insert into topScroll (content,speed,direction) values ('";
		sqlString += topScroll.getContent() + "','";
		sqlString += topScroll.getSpeed() + "','";
		sqlString += topScroll.getDirection() + "')";
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
	 *�޸Ķ��˹�����Ļ  
	 */
	public boolean updateTopScroll(TopScroll topScroll) {
		String sqlString = "update topScroll set content='"; 
		sqlString += topScroll.getContent() + "',speed='";
		sqlString += topScroll.getSpeed() + "',direction='";
		sqlString += topScroll.getDirection() + "' where id=" + topScroll.getId();
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
	 *����ID
	 *ɾ�����˹�����Ļ  
	 */
	public static int DeleteTopScroll(int id) {
		int result = 0;
		String sqlString = "delete from topScroll where id=" + id;
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
	 *��ҳ�����ύ������ѯ�����
	 */
	public Checkid queryPostInfSHR() {
		 /* �������������ĳҳ��¼�ļ������� */
		Checkid checkid = new Checkid();
		String sqlString = "select * from checkid";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				checkid.setId(rs.getInt("id"));
				checkid.setPostInfID(rs.getString("postInfID"));
				checkid.setPostInfName(rs.getString("postInfName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return checkid;
	}
}
