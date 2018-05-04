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
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentFl> queryFlByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
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
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ����
	 */
	public int queryFlByIdListCount(String idList) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from content_Flfg where id in ( "+idList+" ) order by id";
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
	/*
	 *����ID
	 *��ѯһ�����ɷ�����Ϣ 
	 */
	public  ContentFl queryFlByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentFl Fl = new ContentFl();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentFl> queryFlByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Flfg order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Flfg "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentFl> queryFlSy(int num) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentFl> FlList = new ArrayList<ContentFl>();
		String sqlString = "select * from content_Flfg order by id desc;";
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ���ɷ����¼��
	 */
	public  int queryFlByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Flfg";
		}else{
			sqlString = "select count(*) from content_Flfg "+srbt;
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
	

	/*���ݼ�¼���ɾ��ĳ�����ɷ���*/
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
	 *�������ɷ����¼  
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
	 *����ID
	 *�޸ķ��ɷ����¼  
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
