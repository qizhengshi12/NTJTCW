package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.WjglTJ;
import com.safety.entity.Wjglhf;
import com.safety.entity.Wjglxf;

public class WjglDao {
	/*
	 *���ݲ�ѯ����
	 *��ѯ�·��ļ���¼
	 */
	public  ArrayList<Wjglxf> queryWjglXFListByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglxf  order by fqsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglxf "+srbt +" order by fqsj desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Wjglxf wjglxf = new Wjglxf();
				wjglxf.setId(rs.getInt("id"));
				wjglxf.setFszt(rs.getString("fszt"));
				wjglxf.setWjmc(rs.getString("wjmc"));
				wjglxf.setWjlx(rs.getString("wjlx"));
				wjglxf.setFwdw(rs.getString("fwdw"));
				wjglxf.setWjh(rs.getString("wjh"));
				wjglxf.setHfqx(rs.getDate("hfqx"));
				wjglxf.setHfry(rs.getString("hfry"));
				wjglxf.setHfryID(rs.getString("hfryID"));
				wjglxf.setWhfryID(rs.getString("whfryID"));
				wjglxf.setFqr(rs.getString("fqr"));
				wjglxf.setFqrID(rs.getString("fqrID"));
				wjglxf.setFqsj(rs.getTimestamp("fqsj"));
				wjglxf.setGzyq(rs.getString("gzyq"));
				wjglxf.setFileUrl(rs.getString("fileUrl"));
				WjglxfList.add(wjglxf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return WjglxfList;
	}

	/*
	 *��ҳ��ʾ
	 *��ѯ�·��ļ���¼
	 */
	public  ArrayList<Wjglxf> queryWjglSy(int num) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "select * from Wjglxf where fszt='1' order by fqsj desc;";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<num&&rs.next(); i++){
				Wjglxf wjglxf = new Wjglxf();
				wjglxf.setId(rs.getInt("id"));
				wjglxf.setWjmc(rs.getString("wjmc"));
				wjglxf.setFqsj(rs.getTimestamp("fqsj"));
				WjglxfList.add(wjglxf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return WjglxfList;
	}
	/*
	 *������������ѯ����
	 *��ѯ�·��ļ���¼
	 */
	public  int queryWjglXFListByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglxf";
		}else{
			sqlString = "select count(*) from Wjglxf "+srbt;
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
	 *���ݲ�ѯ����
	 *��ѯͳ���ļ���¼
	 */
	public  ArrayList<Wjglxf> queryWjglTJListByBt(String fwdw, String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglxf where hfryID<>'' and fwdw='"+fwdw+"' and fszt='1' order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglxf "+srbt +" and  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1' order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Wjglxf wjglxf = new Wjglxf();
				wjglxf.setId(rs.getInt("id"));
				wjglxf.setWjmc(rs.getString("wjmc"));
				WjglxfList.add(wjglxf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return WjglxfList;
	}
	/*
	 *������������ѯ����
	 *��ѯͳ���ļ���¼
	 */
	public  int queryWjglTJListByBtCount(String fwdw,String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglxf where  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1'";
		}else{
			sqlString = "select count(*) from Wjglxf "+srbt+" and  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1'";
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
	 *��ѯ�·��ļ�
	 */
	public  Wjglxf queryWjglXFByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		Wjglxf wjglxf = new Wjglxf();
		String sqlString = "select * from Wjglxf where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				wjglxf.setId(rs.getInt("id"));
				wjglxf.setFszt(rs.getString("fszt"));
				wjglxf.setWjmc(rs.getString("wjmc"));
				wjglxf.setWjlx(rs.getString("wjlx"));
				wjglxf.setFwdw(rs.getString("fwdw"));
				wjglxf.setWjh(rs.getString("wjh"));
				wjglxf.setHfqx(rs.getDate("hfqx"));
				wjglxf.setHfry(rs.getString("hfry"));
				wjglxf.setHfryID(rs.getString("hfryID"));
				wjglxf.setWhfryID(rs.getString("whfryID"));
				wjglxf.setFqr(rs.getString("fqr"));
				wjglxf.setFqrID(rs.getString("fqrID"));
				wjglxf.setFqsj(rs.getTimestamp("fqsj"));
				wjglxf.setGzyq(rs.getString("gzyq"));
				wjglxf.setFileUrl(rs.getString("FileUrl"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglxf;
	}
	
	/*
	 *�����ļ�ID�͵�λ����
	 *��ѯ�ظ��ļ�
	 */
	public  Wjglhf queryWjglhfByIDMC(int wjid, String dwmc) {
		 /* �������������ĳҳ��¼�ļ������� */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where wjID ="+wjid+" and company='"+dwmc+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				wjglhf.setId(rs.getInt("id"));
				wjglhf.setWjID(rs.getInt("wjID"));
				wjglhf.setHfr(rs.getString("hfr"));
				wjglhf.setHfrID(rs.getString("hfrID"));
				wjglhf.setHfnr(rs.getString("hfnr"));
				wjglhf.setFileUrl(rs.getString("fileUrl"));
				wjglhf.setHfsj(rs.getDate("hfsj"));
				wjglhf.setSfcs(rs.getString("sfcs"));
				wjglhf.setHfzt(rs.getString("hfzt"));
				wjglhf.setCyzt(rs.getString("cyzt"));
				wjglhf.setCompany(rs.getString("company"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglhf;
	}
	/*
	 *
	 *�����·���¼  ����ظ��ģ�
	 */
	public int insertWjglxf1(Wjglxf wjglxf) {
		String sqlString = "insert into wjglxf (fszt,wjmc,wjlx,fwdw,wjh,hfqx,hfry,hfryID,whfryID,fqr,fqrID,fqsj,gzyq,FileUrl) values('";
		sqlString += wjglxf.getFszt() + "','";
		sqlString += wjglxf.getWjmc() + "','";
		sqlString += wjglxf.getWjlx() + "','";
		sqlString += wjglxf.getFwdw() + "','";
		sqlString += wjglxf.getWjh() + "','";
		sqlString += wjglxf.getHfqx() + "','";
		sqlString += wjglxf.getHfry() + "','";
		sqlString += wjglxf.getHfryID() + "','";
		sqlString += wjglxf.getWhfryID() + "','";
		sqlString += wjglxf.getFqr() + "','";
		sqlString += wjglxf.getFqrID() + "','";
		sqlString += wjglxf.getFqsj() + "','";
		sqlString += wjglxf.getGzyq() + "','";
		sqlString += wjglxf.getFileUrl() + "')";
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
	 *�޸��·���¼  ����ظ��ģ�
	 */
	public boolean updateWjglxf1(Wjglxf wjglxf) {
		String sqlString = "update wjglxf set fszt='"; 
		sqlString += wjglxf.getFszt() + "',wjlx='";
		sqlString += wjglxf.getWjlx()  + "',wjmc='";
		sqlString += wjglxf.getWjmc() + "',fwdw='";
		sqlString += wjglxf.getFwdw() + "',wjh='";
		sqlString += wjglxf.getWjh() + "',hfqx='";
		sqlString += wjglxf.getHfqx() + "',hfry='";
		sqlString += wjglxf.getHfry() + "',hfryID='";
		sqlString += wjglxf.getHfryID() + "',whfryID='";
		sqlString += wjglxf.getWhfryID() + "',fqr='";
		sqlString += wjglxf.getFqr() + "',fqrID='";
		sqlString += wjglxf.getFqrID() + "',fqsj='";
		sqlString += wjglxf.getFqsj() + "',gzyq='";
		sqlString += wjglxf.getGzyq() + "',FileUrl='";
		sqlString += wjglxf.getFileUrl() + "' where id=" + wjglxf.getId();
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
	 *�����·���¼  ������ظ���
	 */
	public int insertWjglxf2(Wjglxf wjglxf) {
		String sqlString = "insert into wjglxf (fszt,wjmc,wjlx,fwdw,wjh,fqr,fqrID,fqsj,gzyq,FileUrl) values('";
		sqlString += wjglxf.getFszt() + "','";
		sqlString += wjglxf.getWjmc() + "','";
		sqlString += wjglxf.getWjlx() + "','";
		sqlString += wjglxf.getFwdw() + "','";
		sqlString += wjglxf.getWjh() + "','";
		sqlString += wjglxf.getFqr() + "','";
		sqlString += wjglxf.getFqrID() + "','";
		sqlString += wjglxf.getFqsj() + "','";
		sqlString += wjglxf.getGzyq() + "','";
		sqlString += wjglxf.getFileUrl() + "')";
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
	 *�޸��·���¼   ������ظ���
	 */
	public boolean updateWjglxf2(Wjglxf wjglxf) {
		String sqlString = "update wjglxf set fszt='"; 
		sqlString += wjglxf.getFszt() + "',wjlx='";
		sqlString += wjglxf.getWjlx()  + "',wjmc='";
		sqlString += wjglxf.getWjmc() + "',fwdw='";
		sqlString += wjglxf.getFwdw() + "',wjh='";
		sqlString += wjglxf.getWjh() + "',fqr='";
		sqlString += wjglxf.getFqr() + "',fqrID='";
		sqlString += wjglxf.getFqrID() + "',fqsj='";
		sqlString += wjglxf.getFqsj() + "',gzyq='";
		sqlString += wjglxf.getGzyq() + "',FileUrl='";
		sqlString += wjglxf.getFileUrl() + "' where id=" + wjglxf.getId();
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
	 *�����ظ���¼  
	 */
	public boolean insertWjglhf(ArrayList<Wjglhf> WjglhfList) {
		int len = WjglhfList.size()-1;
		String sqlString = "insert into wjglhf (wjID,wjmc,hfr,hfrID,sfcs,hfzt,cyzt,company,companyID,hfqx) values('";
		for(int i=0; i<len; i++){
			sqlString += WjglhfList.get(i).getWjID() + "','";
			sqlString += WjglhfList.get(i).getWjmc() + "','";
			sqlString += WjglhfList.get(i).getHfr() + "','";
			sqlString += WjglhfList.get(i).getHfrID() + "','";
			sqlString += WjglhfList.get(i).getSfcs() + "','";
			sqlString += WjglhfList.get(i).getHfzt() + "','";
			sqlString += WjglhfList.get(i).getCyzt() + "','";
			sqlString += WjglhfList.get(i).getCompany()+ "','";
			sqlString += WjglhfList.get(i).getCompanyID()+ "','";
			sqlString += WjglhfList.get(i).getHfqx() + "'),('";
		}
		sqlString += WjglhfList.get(len).getWjID() + "','";
		sqlString += WjglhfList.get(len).getWjmc() + "','";
		sqlString += WjglhfList.get(len).getHfr() + "','";
		sqlString += WjglhfList.get(len).getHfrID() + "','";
		sqlString += WjglhfList.get(len).getSfcs() + "','";
		sqlString += WjglhfList.get(len).getHfzt() + "','";
		sqlString += WjglhfList.get(len).getCyzt() + "','";
		sqlString += WjglhfList.get(len).getCompany()+ "','";
		sqlString += WjglhfList.get(len).getCompanyID()+ "','";
		sqlString += WjglhfList.get(len).getHfqx()  + "')";
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
	 *���ݷ���ID
	 *��ѯ�ظ��ļ�
	 */
	public  ArrayList<Wjglhf> queryWjglhfByWJID(int wjid) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
		String sqlString = "select * from Wjglhf where wjID ="+wjid;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				Wjglhf wjglhf = new Wjglhf();
				wjglhf.setId(rs.getInt("id"));
				wjglhf.setWjID(rs.getInt("wjID"));
				wjglhf.setWjmc(rs.getString("wjmc"));
				wjglhf.setHfr(rs.getString("hfr"));
				wjglhf.setHfrID(rs.getString("hfrID"));
				wjglhf.setHfnr(rs.getString("hfnr"));
				wjglhf.setFileUrl(rs.getString("fileUrl"));
				wjglhf.setHfsj(rs.getDate("hfsj"));
				wjglhf.setHfqx(rs.getDate("hfqx"));
				wjglhf.setSfcs(rs.getString("sfcs"));
				wjglhf.setHfzt(rs.getString("hfzt"));
				wjglhf.setCyzt(rs.getString("cyzt"));
				wjglhf.setCompany(rs.getString("company"));
				wjglhfList.add(wjglhf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglhfList;
	}

	/*
	 *���ݻظ�ID
	 *��ѯ�ظ��ļ�
	 */
	public  Wjglhf queryWjglhfByHFID(int hfid) {
		 /* �������������ĳҳ��¼�ļ������� */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where id ="+hfid;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				wjglhf.setId(rs.getInt("id"));
				wjglhf.setWjID(rs.getInt("wjID"));
				wjglhf.setWjmc(rs.getString("wjmc"));
				wjglhf.setHfr(rs.getString("hfr"));
				wjglhf.setHfrID(rs.getString("hfrID"));
				wjglhf.setHfnr(rs.getString("hfnr"));
				wjglhf.setFileUrl(rs.getString("fileUrl"));
				wjglhf.setHfqx(rs.getDate("hfqx"));
				wjglhf.setHfsj(rs.getDate("hfsj"));
				wjglhf.setSfcs(rs.getString("sfcs"));
				wjglhf.setHfzt(rs.getString("hfzt"));
				wjglhf.setCyzt(rs.getString("cyzt"));
				wjglhf.setCompany(rs.getString("company"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglhf;
	}

	/*
	 *�����û���
	 *��ѯ�ظ��ļ�
	 */
	public  ArrayList<Wjglhf> queryWjglhfByHfrID(String hfrID, String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglhf where hfrID='"+hfrID+"' order by hfqx desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglhf where hfrID='"+hfrID+"'"+srbt +" order by hfqx desc limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				Wjglhf wjglhf = new Wjglhf();
				wjglhf.setId(rs.getInt("id"));
				wjglhf.setWjID(rs.getInt("wjID"));
				wjglhf.setWjmc(rs.getString("wjmc"));
				wjglhf.setHfr(rs.getString("hfr"));
				wjglhf.setHfrID(rs.getString("hfrID"));
				wjglhf.setHfnr(rs.getString("hfnr"));
				wjglhf.setFileUrl(rs.getString("fileUrl"));
				wjglhf.setHfqx(rs.getDate("hfqx"));
				wjglhf.setHfsj(rs.getDate("hfsj"));
				wjglhf.setSfcs(rs.getString("sfcs"));
				wjglhf.setHfzt(rs.getString("hfzt"));
				wjglhf.setCyzt(rs.getString("cyzt"));
				wjglhf.setCompany(rs.getString("company"));
				wjglhfList.add(wjglhf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglhfList;
	}
	 /*
	 *�����û���
	 *��ѯ�ظ��ļ���Ŀ
	 */
	public  int queryWjglhfByHfrIDCount(String hfrID, String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglhf where hfrID='"+hfrID+"'";
		}else{
			sqlString = "select count(*) from Wjglhf where hfrID='"+hfrID+"'"+srbt;
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
	 *���ݷ���ID
	 *��ѯ�ظ��ļ�
	 */
	public  Wjglhf queryWjglhfByWJIDHF(int wjid, String hfrID) {
		 /* �������������ĳҳ��¼�ļ������� */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where wjID ="+wjid+" and hfrID='"+hfrID+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				wjglhf.setId(rs.getInt("id"));
				wjglhf.setWjID(rs.getInt("wjID"));
				wjglhf.setWjmc(rs.getString("wjmc"));
				wjglhf.setHfr(rs.getString("hfr"));
				wjglhf.setHfrID(rs.getString("hfrID"));
				wjglhf.setHfnr(rs.getString("hfnr"));
				wjglhf.setFileUrl(rs.getString("fileUrl"));
				wjglhf.setHfqx(rs.getDate("hfqx"));
				wjglhf.setHfsj(rs.getDate("hfsj"));
				wjglhf.setSfcs(rs.getString("sfcs"));
				wjglhf.setHfzt(rs.getString("hfzt"));
				wjglhf.setCyzt(rs.getString("cyzt"));
				wjglhf.setCompany(rs.getString("company"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return wjglhf;
	}
	/*
	 *����ID
	 *�޸Ļظ�����
	 */
	public boolean updateWjglHF(Wjglhf wjglhf) {
		String sqlString = "update wjglhf set hfnr='"; 
		sqlString += wjglhf.getHfnr() + "',FileUrl='";
		sqlString += wjglhf.getFileUrl()  + "',hfsj='";
		sqlString += wjglhf.getHfsj() + "',hfzt='";
		sqlString += wjglhf.getHfzt() + "',sfcs='";
		sqlString += wjglhf.getSfcs() + "' where id=" + wjglhf.getId();
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
	 *�޸�δ�ظ�ID�ֶ�
	 */
	public boolean updateWjglWhfID(String id, String newWhfID) {
		String sqlString = "update wjglxf set whfryID='"+newWhfID+"' where id=" + id;
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
	public static int DeleteWjglXFById(int id) {
		int result = 0;
		String sqlString = "delete from wjglxf where id=" + id;
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

	/*���ݼ�¼���ɾ��ĳ���ظ��ļ�*/
	public static int DeleteWjglHFById(int id) {
		int result = 0;
		String sqlString = "delete from Wjglhf where id=" + id;
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
	 *������������ѯ����
	 *��ѯ�·��ļ���¼
	 */
	public  int queryWjglWHFCount(String username) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from Wjglhf where hfrID ='"+username+"' and hfzt='δ�ظ�'";
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
	 * �ڲ˵���¼�У����ݸ��ڵ��ѯ���ƺ�ID
	 */
	public static ArrayList<WjglTJ> QueryCompanyNameById(String DBName,int parent_id) {
		String sqlString = "select id,name from "+DBName+" where parent_id = "+parent_id+" order by id";
		DB db = new DB();
		ResultSet rs; 
		ArrayList<WjglTJ> WjglTJList = new ArrayList<WjglTJ>();
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				WjglTJ wjglTJ = new WjglTJ();
				wjglTJ.setId(rs.getInt("id"));
				wjglTJ.setName(rs.getString("name"));
				wjglTJ.setCountcs(0);
				wjglTJ.setCounthf(0);
				WjglTJList.add(wjglTJ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return WjglTJList;
	}
}
