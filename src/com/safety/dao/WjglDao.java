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
	 *根据查询条件
	 *查询下发文件记录
	 */
	public  ArrayList<Wjglxf> queryWjglXFListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglxf  order by fqsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglxf "+srbt +" order by fqsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
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
	 *首页显示
	 *查询下发文件记录
	 */
	public  ArrayList<Wjglxf> queryWjglSy(int num) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "select * from Wjglxf where fszt='1' order by fqsj desc;";
		/* 调用数据层进行查询 */
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
	 *根据条件及查询条件
	 *查询下发文件记录
	 */
	public  int queryWjglXFListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglxf";
		}else{
			sqlString = "select count(*) from Wjglxf "+srbt;
		}
		/* 调用数据层进行查询 */
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
	 *根据查询条件
	 *查询统计文件记录
	 */
	public  ArrayList<Wjglxf> queryWjglTJListByBt(String fwdw, String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Wjglxf> WjglxfList = new ArrayList<Wjglxf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglxf where hfryID<>'' and fwdw='"+fwdw+"' and fszt='1' order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglxf "+srbt +" and  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1' order by id limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
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
	 *根据条件及查询条件
	 *查询统计文件记录
	 */
	public  int queryWjglTJListByBtCount(String fwdw,String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglxf where  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1'";
		}else{
			sqlString = "select count(*) from Wjglxf "+srbt+" and  hfryID<>'' and fwdw='"+fwdw+"' and fszt='1'";
		}
		/* 调用数据层进行查询 */
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
	 *根据ID
	 *查询下发文件
	 */
	public  Wjglxf queryWjglXFByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Wjglxf wjglxf = new Wjglxf();
		String sqlString = "select * from Wjglxf where id ="+id;
		/* 调用数据层进行查询 */
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
	 *根据文件ID和单位名称
	 *查询回复文件
	 */
	public  Wjglhf queryWjglhfByIDMC(int wjid, String dwmc) {
		 /* 保存符合条件的某页记录的集合链表 */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where wjID ="+wjid+" and company='"+dwmc+"'";
		/* 调用数据层进行查询 */
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
	 *新增下发记录  （需回复的）
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
	 *根据ID
	 *修改下发记录  （需回复的）
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
	 *新增下发记录  （无需回复）
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
	 *根据ID
	 *修改下发记录   （无需回复）
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
	 *新增回复记录  
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
	 *根据发文ID
	 *查询回复文件
	 */
	public  ArrayList<Wjglhf> queryWjglhfByWJID(int wjid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
		String sqlString = "select * from Wjglhf where wjID ="+wjid;
		/* 调用数据层进行查询 */
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
	 *根据回复ID
	 *查询回复文件
	 */
	public  Wjglhf queryWjglhfByHFID(int hfid) {
		 /* 保存符合条件的某页记录的集合链表 */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where id ="+hfid;
		/* 调用数据层进行查询 */
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
	 *根据用户名
	 *查询回复文件
	 */
	public  ArrayList<Wjglhf> queryWjglhfByHfrID(String hfrID, String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Wjglhf> wjglhfList = new ArrayList<Wjglhf>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Wjglhf where hfrID='"+hfrID+"' order by hfqx desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Wjglhf where hfrID='"+hfrID+"'"+srbt +" order by hfqx desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
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
	 *根据用户名
	 *查询回复文件数目
	 */
	public  int queryWjglhfByHfrIDCount(String hfrID, String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Wjglhf where hfrID='"+hfrID+"'";
		}else{
			sqlString = "select count(*) from Wjglhf where hfrID='"+hfrID+"'"+srbt;
		}
		/* 调用数据层进行查询 */
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
	 *根据发文ID
	 *查询回复文件
	 */
	public  Wjglhf queryWjglhfByWJIDHF(int wjid, String hfrID) {
		 /* 保存符合条件的某页记录的集合链表 */
		Wjglhf wjglhf = new Wjglhf();
		String sqlString = "select * from Wjglhf where wjID ="+wjid+" and hfrID='"+hfrID+"'";
		/* 调用数据层进行查询 */
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
	 *根据ID
	 *修改回复内容
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
	 *根据ID
	 *修改未回复ID字段
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
	
	/*根据记录编号删除某个下发文件*/
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

	/*根据记录编号删除某个回复文件*/
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
	 *根据条件及查询条件
	 *查询下发文件记录
	 */
	public  int queryWjglWHFCount(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from Wjglhf where hfrID ='"+username+"' and hfzt='未回复'";
		/* 调用数据层进行查询 */
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
	 * 在菜单记录中，根据父节点查询名称和ID
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
