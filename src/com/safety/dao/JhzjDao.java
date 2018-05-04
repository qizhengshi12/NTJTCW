package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Jhzj;

public class JhzjDao {
	/*
	 *���ݲ�ѯ����
	 *��ѯ�ƻ��ܽ��¼
	 */
	public  ArrayList<Jhzj> queryJhzjListByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Jhzj> JhzjList = new ArrayList<Jhzj>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Jhzj  order by czsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Jhzj "+srbt +" order by czsj desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Jhzj jhzj = new Jhzj();
				jhzj.setId(rs.getInt("id"));
				jhzj.setJhmc(rs.getString("jhmc"));
				jhzj.setJhlx1(rs.getString("jhlx1"));
				jhzj.setJhlx2(rs.getString("jhlx2"));
				jhzj.setJhnr(rs.getString("jhnr"));
				jhzj.setFileUrl(rs.getString("FileUrl"));
				jhzj.setJhsj1(rs.getDate("jhsj1"));
				jhzj.setJhsj2(rs.getDate("jhsj2"));
				jhzj.setCzr(rs.getString("czr"));
				jhzj.setCzrdw(rs.getString("czrdw"));
				jhzj.setCzrID(rs.getString("czrID"));
				jhzj.setCzsj(rs.getTimestamp("czsj"));
				jhzj.setTjzt(rs.getString("tjzt"));
				JhzjList.add(jhzj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return JhzjList;
	}
	/*
	 *��������
	 *��ѯ�ƻ��ܽ��¼��
	 */
	public  int queryJhzjListByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Jhzj";
		}else{
			sqlString = "select count(*) from Jhzj "+srbt;
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
	 *����ID
	 *��ѯ
	 */
	public  Jhzj queryJhzjByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		Jhzj jhzj = new Jhzj();
		String sqlString = "select * from jhzj where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				jhzj.setId(rs.getInt("id"));
				jhzj.setJhmc(rs.getString("jhmc"));
				jhzj.setJhlx1(rs.getString("jhlx1"));
				jhzj.setJhlx2(rs.getString("jhlx2"));
				jhzj.setJhnr(rs.getString("jhnr"));
				jhzj.setFileUrl(rs.getString("FileUrl"));
				jhzj.setJhsj1(rs.getDate("jhsj1"));
				jhzj.setJhsj2(rs.getDate("jhsj2"));
				jhzj.setCzr(rs.getString("czr"));
				jhzj.setCzrdw(rs.getString("czrdw"));
				jhzj.setCzrID(rs.getString("czrID"));
				jhzj.setCzsj(rs.getTimestamp("czsj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return jhzj;
	}
	/*
	 *
	 *������¼
	 */
	public int insertJhzj(Jhzj jhzj) {
		String sqlString = "insert into jhzj (jhmc,jhlx1,jhlx2,jhnr,FileUrl,jhsj1,jhsj2,czr,czrID,czsj,czrdw,tjzt) values('";
		sqlString += jhzj.getJhmc() + "','";
		sqlString += jhzj.getJhlx1() + "','";
		sqlString += jhzj.getJhlx2() + "','";
		sqlString += jhzj.getJhnr() + "','";
		sqlString += jhzj.getFileUrl() + "','";
		sqlString += jhzj.getJhsj1() + "','";
		sqlString += jhzj.getJhsj2() + "','";
		sqlString += jhzj.getCzr() + "','";
		sqlString += jhzj.getCzrID() + "','";
		sqlString += jhzj.getCzsj() + "','";
		sqlString += jhzj.getCzrdw() + "','";
		sqlString += jhzj.getTjzt() + "')";
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
	 *�޸Ļظ�����
	 */
	public boolean updateJhzj(Jhzj jhzj) {
		String sqlString = "update jhzj set jhmc='"; 
		sqlString += jhzj.getJhmc() + "',jhlx1='";
		sqlString += jhzj.getJhlx1()+ "',jhlx2='";
		sqlString += jhzj.getJhlx2()  + "',jhnr='";
		sqlString += jhzj.getJhnr() + "',FileUrl='";
		sqlString += jhzj.getFileUrl() + "',jhsj1='";
		sqlString += jhzj.getJhsj1() + "',jhsj2='";
		sqlString += jhzj.getJhsj2() + "',czr='";
		sqlString += jhzj.getCzr() + "',czrID='";
		sqlString += jhzj.getCzrID() + "',czsj='";
		sqlString += jhzj.getCzsj() + "',czrdw='";
		sqlString += jhzj.getCzrdw() + "',tjzt='";
		sqlString += jhzj.getTjzt() + "' where id=" + jhzj.getId();
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
	
	/*���ݼ�¼���ɾ��ĳ���·��ļ�*/
	public static int DeleteJhzjById(int id) {
		int result = 0;
		String sqlString = "delete from jhzj where id=" + id;
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
