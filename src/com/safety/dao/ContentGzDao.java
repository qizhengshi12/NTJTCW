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
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentGz> queryGzByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGz> GzList = new ArrayList<ContentGz>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
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
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ����
	 */
	public int queryGzByIdListCount(String idList) {
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
	public  ContentGz queryGzByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentGz Gz = new ContentGz();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentGz> queryGzByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGz> GzList = new ArrayList<ContentGz>();
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
	 *��������
	 *��ѯ���ɷ����¼��
	 */
	public  int queryGzByBtCount(String srbt) {
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
	 *�������ɷ����¼  
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
	 *����ID
	 *�޸ķ��ɷ����¼  
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