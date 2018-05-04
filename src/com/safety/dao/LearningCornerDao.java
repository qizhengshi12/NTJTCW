package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.LearningCorner;

public class LearningCornerDao {
	/* 
	 *��ѯ��Ϣ�����б�
	 */
	public  ArrayList<LearningCorner> queryInformatListByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<LearningCorner> LearningCornerList = new ArrayList<LearningCorner>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from LearningCorner  order by lx3 desc,id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from LearningCorner "+srbt +" order by lx3 desc,id desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				LearningCorner learningCorner = new LearningCorner();
				learningCorner.setId(rs.getInt("id"));
				learningCorner.setBt(rs.getString("bt"));
				learningCorner.setNr(rs.getString("nr"));
				learningCorner.setTjr(rs.getString("tjr"));
				learningCorner.setTjrID(rs.getString("tjrID"));
				learningCorner.setTjsj(rs.getDate("tjsj"));
				learningCorner.setDw(rs.getString("dw"));
				learningCorner.setFileURL(rs.getString("fileURL"));
				learningCorner.setPhotoURL(rs.getString("photoURL"));
				learningCorner.setGood(rs.getInt("good"));
				learningCorner.setPeople(rs.getString("people"));
				learningCorner.setPeopleID(rs.getString("peopleID"));
				learningCorner.setLx1(rs.getString("lx1"));
				learningCorner.setLx2(rs.getString("lx2"));
				learningCorner.setLx3(rs.getString("lx3"));
				LearningCornerList.add(learningCorner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return LearningCornerList;
	}
	/* 
	 *��ѯ��Ϣ�����б�
	 */
	public  ArrayList<LearningCorner> queryLcSy(int num) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<LearningCorner> LearningCornerList = new ArrayList<LearningCorner>();
		String sqlString = "select * from LearningCorner order by tjsj desc;";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<num&&rs.next(); i++){
				LearningCorner learningCorner = new LearningCorner();
				learningCorner.setId(rs.getInt("id"));
				learningCorner.setBt(rs.getString("bt"));
				learningCorner.setTjsj(rs.getDate("tjsj"));
				LearningCornerList.add(learningCorner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return LearningCornerList;
	}

	/* 
	 *��ѯ��Ϣ�����б�
	 */
	public  int queryInformatListByCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from LearningCorner";
		}else{
			sqlString = "select count(*) from LearningCorner "+srbt;
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
	public  LearningCorner queryInformatByID(int id) {
		LearningCorner learningCorner = new LearningCorner();
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select * from LearningCorner where id="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				learningCorner.setId(rs.getInt("id"));
				learningCorner.setBt(rs.getString("bt"));
				learningCorner.setNr(rs.getString("nr"));
				learningCorner.setTjr(rs.getString("tjr"));
				learningCorner.setTjrID(rs.getString("tjrID"));
				learningCorner.setTjsj(rs.getDate("tjsj"));
				learningCorner.setDw(rs.getString("dw"));
				learningCorner.setFileURL(rs.getString("fileURL"));
				learningCorner.setPhotoURL(rs.getString("photoURL"));
				learningCorner.setGood(rs.getInt("good"));
				learningCorner.setPeople(rs.getString("people"));
				learningCorner.setPeopleID(rs.getString("peopleID"));
				learningCorner.setLx1(rs.getString("lx1"));
				learningCorner.setLx2(rs.getString("lx2"));
				learningCorner.setLx3(rs.getString("lx3"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return learningCorner;
	}
	/*
	 *
	 *������¼  
	 */
	public int insertInformat(LearningCorner postInfor) {
		String sqlString = "insert into LearningCorner (bt,nr,tjr,tjrID,tjsj,dw,fileURL,photoURL,good,people,peopleID,lx1,lx2,lx3) values ('";
		sqlString += postInfor.getBt() + "','";
		sqlString += postInfor.getNr() + "','";
		sqlString += postInfor.getTjr() + "','";
		sqlString += postInfor.getTjrID() + "','";
		sqlString += postInfor.getTjsj() + "','";
		sqlString += postInfor.getDw() + "','";
		sqlString += postInfor.getFileURL() + "','";
		sqlString += postInfor.getPhotoURL()  + "',";
		sqlString += postInfor.getGood()  + ",'";
		sqlString += postInfor.getPeople()   + "','";
		sqlString += postInfor.getPeopleID() + "','";
		sqlString += postInfor.getLx1()  + "','";
		sqlString += postInfor.getLx2()  + "','";
		sqlString += postInfor.getLx3() + "')";
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
	public boolean updateInformat(LearningCorner postInfor) {
		String sqlString = "update LearningCorner set bt='"; 
		sqlString += postInfor.getBt() + "',nr='";
		sqlString += postInfor.getNr() + "',tjr='";
		sqlString += postInfor.getTjr() + "',tjrID='";
		sqlString += postInfor.getTjrID() + "',tjsj='";
		sqlString += postInfor.getTjsj() + "',dw='";
		sqlString += postInfor.getDw() + "',fileURL='";
		sqlString += postInfor.getFileURL() + "',photoURL='";
		sqlString += postInfor.getPhotoURL() + "',good=";
		sqlString += postInfor.getGood() + ",people='";
		sqlString += postInfor.getPeople() + "',peopleID='";
		sqlString += postInfor.getPeopleID() + "',lx1='";
		sqlString += postInfor.getLx1() + "',lx2='";
		sqlString += postInfor.getLx2() + "',lx3='";
		sqlString += postInfor.getLx3() + "' where id=" + postInfor.getId();
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
	 *���þ��� 
	 */
	public boolean updateLx3(int id,String bz) {
		String sqlString = "update LearningCorner set lx3='"+bz+ "' where id=" + id;
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
	 *����
	 */
	public boolean updateGood(int id,String pp, String ppid, int num) {
		num = num+1;
		String sqlString = "update LearningCorner set people='"+pp+ "',peopleID='"+ppid+ "',good='"+num+"' where id=" + id;
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
		String sqlString = "delete from LearningCorner where id=" + id;
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
