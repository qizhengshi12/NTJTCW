package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentGfxwj;

public class ContentGfxwjDao {
	/*
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentGfxwj> queryGfxwjByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGfxwj> GfxwjList = new ArrayList<ContentGfxwj>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentGfxwj Gfxwj = new ContentGfxwj();
				Gfxwj.setId(rs.getInt("id"));
				Gfxwj.setBt(rs.getString("bt"));
				Gfxwj.setWh(rs.getString("wh"));
				Gfxwj.setSsrq(rs.getDate("ssrq"));
				Gfxwj.setFbdw(rs.getString("fbdw"));
				Gfxwj.setFatherid(rs.getString("fatherid"));
				Gfxwj.setFileUrl(rs.getString("fileUrl"));
				Gfxwj.setCzr(rs.getString("czr"));
				Gfxwj.setCzrID(rs.getString("czrID"));
				GfxwjList.add(Gfxwj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return GfxwjList;
	}

	/*
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ����
	 */
	public int queryGfxwjByIdListCount(String idList) {
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
	public  ContentGfxwj queryGfxwjByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentGfxwj Gfxwj = new ContentGfxwj();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Gfxwj.setId(rs.getInt("id"));
				Gfxwj.setBt(rs.getString("bt"));
				Gfxwj.setWh(rs.getString("wh"));
				Gfxwj.setSsrq(rs.getDate("ssrq"));
				Gfxwj.setFbdw(rs.getString("fbdw"));
				Gfxwj.setFatherid(rs.getString("fatherid"));
				Gfxwj.setFileUrl(rs.getString("fileUrl"));
				Gfxwj.setCzr(rs.getString("czr"));
				Gfxwj.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Gfxwj;
	}
	
	
	
	/*
	 *��������
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentGfxwj> queryGfxwjByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentGfxwj> GfxwjList = new ArrayList<ContentGfxwj>();
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
				ContentGfxwj Gfxwj = new ContentGfxwj();
				Gfxwj.setId(rs.getInt("id"));
				Gfxwj.setBt(rs.getString("bt"));
				Gfxwj.setWh(rs.getString("wh"));
				Gfxwj.setSsrq(rs.getDate("ssrq"));
				Gfxwj.setFbdw(rs.getString("fbdw"));
				Gfxwj.setFatherid(rs.getString("fatherid"));
				Gfxwj.setFileUrl(rs.getString("fileUrl"));
				Gfxwj.setCzr(rs.getString("czr"));
				Gfxwj.setCzrID(rs.getString("czrID"));
				GfxwjList.add(Gfxwj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GfxwjList;
	}

	/*
	 *��������
	 *��ѯ���ɷ����¼��
	 */
	public  int queryGfxwjByBtCount(String srbt) {
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
	public static int DeleteGfxwjById(int id) {
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
	public int insertGfxwj(ContentGfxwj Gfxwj) {
		String sqlString = "insert into content_Flfg (bt,lx,wh,ssrq,fbdw,fatherid,FileUrl,czsj,czr,czrID) values('";
		sqlString += Gfxwj.getBt() + "','";
		sqlString += Gfxwj.getLx() + "','";
		sqlString += Gfxwj.getWh() + "','";
		sqlString += Gfxwj.getSsrq() + "','";
		sqlString += Gfxwj.getFbdw() + "','";
		sqlString += Gfxwj.getFatherid() + "','";
		sqlString += Gfxwj.getFileUrl() + "','";
		sqlString += Gfxwj.getCzsj() + "','";
		sqlString += Gfxwj.getCzr() + "','";
		sqlString += Gfxwj.getCzrID() + "')";

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
	public boolean updateGfxwj(ContentGfxwj Gfxwj) {
		String sqlString = "update content_Flfg set bt='"; 
		sqlString += Gfxwj.getBt() + "',wh='";
		sqlString += Gfxwj.getWh()  + "',ssrq='";
		sqlString += Gfxwj.getSsrq() + "',fbdw='";
		sqlString += Gfxwj.getFbdw() + "',FileUrl='";
		sqlString += Gfxwj.getFileUrl() + "',czsj='";
		sqlString += Gfxwj.getCzsj()+ "',czr='";
		sqlString += Gfxwj.getCzr()+ "',czrID='";
		sqlString += Gfxwj.getCzrID() + "' where id=" + Gfxwj.getId();
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
