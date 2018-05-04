package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentFg;

public class ContentFgDao {
	/*
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentFg> queryFgByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();
		String sqlString = "select * from content_Flfg where id in ( "+idList+" ) order by id limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentFg Fg = new ContentFg();
				Fg.setId(rs.getInt("id"));
				Fg.setBt(rs.getString("bt"));
				Fg.setWh(rs.getString("wh"));
				Fg.setSsrq(rs.getDate("ssrq"));
				Fg.setFbdw(rs.getString("fbdw"));
				Fg.setFatherid(rs.getString("fatherid"));
				Fg.setFileUrl(rs.getString("fileUrl"));
				Fg.setCzr(rs.getString("czr"));
				Fg.setCzrID(rs.getString("czrID"));
				FgList.add(Fg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return FgList;
	}

	/*
	 *���ݶ��ID��
	 *��ѯ���ɷ�����Ϣ����
	 */
	public int queryFgByIdListCount(String idList) {
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
	public  ContentFg queryFgByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentFg Fg = new ContentFg();
		String sqlString = "select * from content_Flfg where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Fg.setId(rs.getInt("id"));
				Fg.setBt(rs.getString("bt"));
				Fg.setWh(rs.getString("wh"));
				Fg.setSsrq(rs.getDate("ssrq"));
				Fg.setFbdw(rs.getString("fbdw"));
				Fg.setFatherid(rs.getString("fatherid"));
				Fg.setFileUrl(rs.getString("fileUrl"));
				Fg.setCzr(rs.getString("czr"));
				Fg.setCzrID(rs.getString("czrID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Fg;
	}
	
	
	
	/*
	 *��������
	 *��ѯ���ɷ�����Ϣ 
	 */
	public  ArrayList<ContentFg> queryFgByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentFg> FgList = new ArrayList<ContentFg>();
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
				ContentFg Fg = new ContentFg();
				Fg.setId(rs.getInt("id"));
				Fg.setBt(rs.getString("bt"));
				Fg.setWh(rs.getString("wh"));
				Fg.setSsrq(rs.getDate("ssrq"));
				Fg.setFbdw(rs.getString("fbdw"));
				Fg.setFatherid(rs.getString("fatherid"));
				Fg.setFileUrl(rs.getString("fileUrl"));
				Fg.setCzr(rs.getString("czr"));
				Fg.setCzrID(rs.getString("czrID"));
				FgList.add(Fg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return FgList;
	}

	/*
	 *��������
	 *��ѯ���ɷ����¼��
	 */
	public  int queryFgByBtCount(String srbt) {
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
	public static int DeleteFgById(int id) {
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
	public int insertFg(ContentFg Fg) {
		String sqlString = "insert into content_Flfg (bt,lx,wh,ssrq,fbdw,fatherid,FileUrl,czsj,czr,czrID) values('";
		sqlString += Fg.getBt() + "','";
		sqlString += Fg.getLx() + "','";
		sqlString += Fg.getWh() + "','";
		sqlString += Fg.getSsrq() + "','";
		sqlString += Fg.getFbdw() + "','";
		sqlString += Fg.getFatherid() + "','";
		sqlString += Fg.getFileUrl() + "','";
		sqlString += Fg.getCzsj() + "','";
		sqlString += Fg.getCzr() + "','";
		sqlString += Fg.getCzrID() + "')";

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
	public boolean updateFg(ContentFg Fg) {
		String sqlString = "update content_Flfg set bt='"; 
		sqlString += Fg.getBt() + "',wh='";
		sqlString += Fg.getWh()  + "',ssrq='";
		sqlString += Fg.getSsrq() + "',fbdw='";
		sqlString += Fg.getFbdw() + "',FileUrl='";
		sqlString += Fg.getFileUrl() + "',czsj='";
		sqlString += Fg.getCzsj()+ "',czr='";
		sqlString += Fg.getCzr()+ "',czrID='";
		sqlString += Fg.getCzrID() + "' where id=" + Fg.getId();
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
