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
	 *���ݶ��ID��
	 *��ѯ��λ�ƶ���Ϣ 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "select * from Content_Glzd where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
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
	 *���ݶ��ID��
	 *��ѯ��λ�ƶ���Ϣ����
	 */
	public int queryGlzdByIdListCount(String idList) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from Content_Glzd where id in ( "+idList+" ) order by id";
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
	 *���ݶ��ID��
	 *��ѯ��λ�ƶ���Ϣ 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByIdListGK(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "select * from Content_Glzd where id in ( "+idList+" ) and sfgk='0' order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
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
	 *���ݶ��ID��
	 *��ѯ��λ�ƶ���Ϣ����
	 */
	public int queryGlzdByIdListCountGK(String idList) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from Content_Glzd where id in ( "+idList+" ) and sfgk='0' order by id";
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
	 *��ѯһ����λ�ƶ���Ϣ 
	 */
	public  ContentGlzd queryGlzdByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentGlzd Glzd = new ContentGlzd();
		String sqlString = "select * from content_Glzd where id ="+id;
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ��λ�ƶ���Ϣ 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Glzd order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Glzd "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ��λ�ƶȼ�¼��
	 */
	public  int queryGlzdByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Glzd";
		}else{
			sqlString = "select count(*) from content_Glzd "+srbt;
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
	
	/*
	 *��������
	 *��ѯ��λ�ƶ���Ϣ 
	 */
	public  ArrayList<ContentGlzd> queryGlzdByBtGK(String srbt ,int begin, int pageSize, String company) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGlzd> GlzdList = new ArrayList<ContentGlzd>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_Glzd where fbdw='"+company+ "' or sfgk='0' order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_Glzd "+srbt +" and (fbdw='"+company+ "' or sfgk='0') order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ��λ�ƶȼ�¼��
	 */
	public  int queryGlzdByBtCountGK(String srbt, String company) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_Glzd where fbdw='"+company+ "' or sfgk='0'";
		}else{
			sqlString = "select count(*) from content_Glzd "+srbt+" and (fbdw='"+company+ "' or sfgk='0')";
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
	/*���ݼ�¼���ɾ��ĳ����λ�ƶ�*/
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
	 *������λ�ƶȼ�¼  
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
	 *����ID
	 *�޸ĵ�λ�ƶȼ�¼  
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
