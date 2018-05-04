package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Gztz;
import com.safety.entity.Gztzhf;

public class GztzDao {
	/*
	 *
	 *��ѯ����֪ͨ�·���¼
	 */
	public  ArrayList<Gztz> queryGztzXFList(String srbt , String username,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Gztz> GztzList = new ArrayList<Gztz>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from gztz where czrID='"+username+"' order by czsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from gztz "+srbt +" and czrID='"+username+"' order by czsj desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Gztz gztz = new Gztz();
				gztz.setId(rs.getInt("id"));
				gztz.setTzmc(rs.getString("tzmc"));
				gztz.setTznr(rs.getString("tznr"));
				gztz.setTzsj(rs.getTimestamp("tzsj"));
				gztz.setTzdd(rs.getString("tzdd"));
				gztz.setTzry(rs.getString("tzry"));
				gztz.setTzryID(rs.getString("tzryID"));
				gztz.setCzr(rs.getString("czr"));
				gztz.setCzrID(rs.getString("czrID"));
				gztz.setCzsj(rs.getTimestamp("czsj"));
				GztzList.add(gztz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GztzList;
	}
	/*
	 *��������
	 *��ѯ����֪ͨ�·���¼��
	 */
	public  int queryGztzXFListCount(String srbt, String username) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from gztz where czrID='"+username+"'";
		}else{
			sqlString = "select count(*) from gztz "+srbt+" and czrID='"+username+"'";
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
	 *
	 *��������֪ͨ  
	 */
	public int insertGztz(Gztz gztz) {
		String sqlString = "insert into gztz (tzmc,tznr,tzsj,tzdd,tzry,tzryID,czr,czrID,czsj) values('";
		sqlString += gztz.getTzmc() + "','";
		sqlString += gztz.getTznr() + "','";
		sqlString += gztz.getTzsj() + "','";
		sqlString += gztz.getTzdd() + "','";
		sqlString += gztz.getTzry() + "','";
		sqlString += gztz.getTzryID() + "','";
		sqlString += gztz.getCzr() + "','";
		sqlString += gztz.getCzrID() + "','";
		sqlString += gztz.getCzsj() + "')";

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
	 *�·�����֪ͨ  
	 */
	public boolean insertGztzhf(ArrayList<Gztzhf> GztzhfList) {
		int len = GztzhfList.size()-1;
		String sqlString = "insert into gztzhf (hfr,hfrID,tzid,tzmc) values('";
		for(int i=0; i<len; i++){
			sqlString += GztzhfList.get(i).getHfr() + "','";
			sqlString += GztzhfList.get(i).getHfrID() + "','";
			sqlString += GztzhfList.get(i).getTzid()  + "','";
			sqlString += GztzhfList.get(i).getTzmc() + "'),('";
		}
		sqlString += GztzhfList.get(len).getHfr() + "','";
		sqlString += GztzhfList.get(len).getHfrID() + "','";
		sqlString += GztzhfList.get(len).getTzid()  + "','";
		sqlString += GztzhfList.get(len).getTzmc() + "')";
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
	 *����ID�鿴֪ͨ
	 */
	public  Gztz queryGztzByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		Gztz gztz = new Gztz();
		String sqlString = "select * from gztz where id="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				gztz.setId(rs.getInt("id"));
				gztz.setTzmc(rs.getString("tzmc"));
				gztz.setTznr(rs.getString("tznr"));
				gztz.setTzsj(rs.getTimestamp("tzsj"));
				gztz.setTzdd(rs.getString("tzdd"));
				gztz.setTzry(rs.getString("tzry"));
				gztz.setTzryID(rs.getString("tzryID"));
				gztz.setCzr(rs.getString("czr"));
				gztz.setCzrID(rs.getString("czrID"));
				gztz.setCzsj(rs.getTimestamp("czsj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return gztz;
	}
	
	/*
	 *
	 *�鿴�ظ����
	 */
	public  ArrayList<Gztzhf> queryGztzXFByIDList(int tzid) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
		String sqlString = "select * from gztzhf where tzid ="+tzid;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Gztzhf gztzhf = new Gztzhf();
				gztzhf.setId(rs.getInt("id"));
				gztzhf.setTzmc(rs.getString("tzmc"));
				gztzhf.setTzid(rs.getInt("tzid"));
				gztzhf.setHfr(rs.getString("hfr"));
				gztzhf.setHfrID(rs.getString("hfrID"));
				gztzhf.setHfnr(rs.getString("hfnr"));
				gztzhf.setHfsj(rs.getTimestamp("hfsj"));
				GztzhfList.add(gztzhf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GztzhfList;
	}
	
	/*���ݼ�¼���ɾ��ĳ������֪ͨ*/
	public static int DeleteGztzById(int id) {
		int result = 0;
		String sqlString = "delete from gztz where id=" + id;
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

	/*����һЩ��¼���ɾ��ĳ������֪ͨ�ظ���¼*/
	public static int DeleteGztzhfByIdList(String IDList) {
		int result = 0;
		String sqlString = "delete from gztzhf where id in (" + IDList+")";
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
	 *��ѯ����֪ͨ�·���¼
	 */
	public  ArrayList<Gztzhf> queryGztzJSList(String srbt , String username,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Gztzhf> GztzhfList = new ArrayList<Gztzhf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from gztzhf where hfrID='"+username+"' order by hfsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from gztzhf "+srbt +" and hfrID='"+username+"' order by hfsj desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Gztzhf gztzhf = new Gztzhf();
				gztzhf.setId(rs.getInt("id"));
				gztzhf.setTzmc(rs.getString("tzmc"));
				gztzhf.setTzid(rs.getInt("tzid"));
				gztzhf.setHfr(rs.getString("hfr"));
				gztzhf.setHfrID(rs.getString("hfrID"));
				gztzhf.setHfnr(rs.getString("hfnr"));
				gztzhf.setHfsj(rs.getTimestamp("hfsj"));
				GztzhfList.add(gztzhf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return GztzhfList;
	}
	/*
	 *��������
	 *��ѯ����֪ͨ��¼��
	 */
	public  int queryGztzJSListCount(String srbt, String username) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from gztzhf where hfrID='"+username+"'";
		}else{
			sqlString = "select count(*) from gztzhf "+srbt+" and hfrID='"+username+"'";
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
	 *�޸Ļظ�����
	 */
	public boolean updateGztzHF(Gztzhf gztzhf) {
		String sqlString = "update gztzhf set hfnr='"; 
		sqlString += gztzhf.getHfnr()   + "',hfsj='";
		sqlString += gztzhf.getHfsj()+ "' where id=" + gztzhf.getId();
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
	 *��������
	 *��ѯδ�ظ�����֪ͨ��¼��
	 */
	public  int queryGztzWHFCount(String username) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from gztzhf where hfrID='"+username+"' and hfnr =''";
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
}
