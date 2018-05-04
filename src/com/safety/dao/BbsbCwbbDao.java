package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.CwbbLrb;
import com.safety.entity.CwbbLrbsj;
import com.safety.entity.CwbbSgjfb;
import com.safety.entity.CwbbSydwzycwzbb;
import com.safety.entity.CwbbSydwzycwzbbHzb;
import com.safety.entity.CwbbYszxb;
import com.safety.entity.CwbbYszxbHzb;
import com.safety.entity.CwbbYszxbZxm;
import com.safety.entity.CwbbZcfzb;
import com.safety.entity.CwbbZcfzbfz;
import com.safety.entity.CwbbZcfzbzc;


public class BbsbCwbbDao {
	/*
	 *根据条件
	 *查询预算执行表记录
	 */
	public  ArrayList<CwbbYszxb> queryCwbbYszxbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbYszxb> CwbbYszxbList = new ArrayList<CwbbYszxb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_yszxb  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_yszxb "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbYszxb cwbbYszxb = new CwbbYszxb();
				cwbbYszxb.setId(rs.getInt("id"));
				cwbbYszxb.setBt(rs.getString("bt"));
				cwbbYszxb.setYear(rs.getInt("year"));
				cwbbYszxb.setMonth(rs.getInt("month"));
				cwbbYszxb.setCzr(rs.getString("czr"));
				cwbbYszxb.setCzrdw(rs.getString("czrdw"));
				cwbbYszxb.setCzrID(rs.getString("czrID"));
				cwbbYszxb.setCzsj(rs.getTimestamp("czsj"));
				cwbbYszxb.setXmid(rs.getString("xmid"));
				cwbbYszxb.setTjzt(rs.getString("tjzt"));
				CwbbYszxbList.add(cwbbYszxb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbYszxbList;
	}
	/*
	 *根据条件
	 *查询预算执行表记录数
	 */
	public  int queryCwbbYszxbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_yszxb";
		}else{
			sqlString = "select count(*) from cwbb_yszxb "+srbt;
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
	 *查询
	 */
	public  CwbbYszxb queryCwbbYszxbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbYszxb cwbbYszxb = new CwbbYszxb();
		String sqlString = "select * from cwbb_yszxb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbYszxb.setId(rs.getInt("id"));
				cwbbYszxb.setBt(rs.getString("bt"));
				cwbbYszxb.setYear(rs.getInt("year"));
				cwbbYszxb.setMonth(rs.getInt("month"));
				cwbbYszxb.setCzr(rs.getString("czr"));
				cwbbYszxb.setCzrdw(rs.getString("czrdw"));
				cwbbYszxb.setCzrID(rs.getString("czrID"));
				cwbbYszxb.setCzsj(rs.getTimestamp("czsj"));
				cwbbYszxb.setXmid(rs.getString("xmid"));
				cwbbYszxb.setJb1(rs.getString("jb1"));
				cwbbYszxb.setJb2(rs.getString("jb2"));
				cwbbYszxb.setJb3(rs.getString("jb3"));
				cwbbYszxb.setJb4(rs.getString("jb4"));
				cwbbYszxb.setGz1(rs.getString("gz1"));
				cwbbYszxb.setGz2(rs.getString("gz2"));
				cwbbYszxb.setGz3(rs.getString("gz3"));
				cwbbYszxb.setGz4(rs.getString("gz4"));
				cwbbYszxb.setSp1(rs.getString("sp1"));
				cwbbYszxb.setSp2(rs.getString("sp2"));
				cwbbYszxb.setSp3(rs.getString("sp3"));
				cwbbYszxb.setSp4(rs.getString("sp4"));
				cwbbYszxb.setBz1(rs.getString("bz1"));
				cwbbYszxb.setBz2(rs.getString("bz2"));
				cwbbYszxb.setBz3(rs.getString("bz3"));
				cwbbYszxb.setBz4(rs.getString("bz4"));
				cwbbYszxb.setXm1(rs.getString("xm1"));
				cwbbYszxb.setXm2(rs.getString("xm2"));
				cwbbYszxb.setXm3(rs.getString("xm3"));
				cwbbYszxb.setXm4(rs.getString("xm4"));
				cwbbYszxb.setHj1(rs.getString("hj1"));
				cwbbYszxb.setHj2(rs.getString("hj2"));
				cwbbYszxb.setHj3(rs.getString("hj3"));
				cwbbYszxb.setHj4(rs.getString("hj4"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbYszxb;
	}
	/*
	 *根据单位和时间查询上月预算
	 *查询
	 */
	public  CwbbYszxb queryCwbbYszxbByDWSJ(int year, int month, String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbYszxb cwbbYszxb = new CwbbYszxb();
		String sqlString = "select * from cwbb_yszxb where year ="+year+" and month="+month+" and czrdw='"+dw+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbYszxb.setXmid(rs.getString("xmid"));
				cwbbYszxb.setJb1(rs.getString("jb1"));
				cwbbYszxb.setGz1(rs.getString("gz1"));
				cwbbYszxb.setSp1(rs.getString("sp1"));
				cwbbYszxb.setBz1(rs.getString("bz1"));
				cwbbYszxb.setXm1(rs.getString("xm1"));
				cwbbYszxb.setHj1(rs.getString("hj1"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbYszxb;
	}
	/*
	 *根据条件
	 *查询预算执行表记录_子项目表
	 */
	public  ArrayList<CwbbYszxbZxm> queryCwbbYszxbZxmByIDList(String IDList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbYszxbZxm> CwbbYszxbZxmList = new ArrayList<CwbbYszxbZxm>();
		String sqlString = "select * from cwbb_yszxb_zxm  where id in ("+IDList+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbYszxbZxm cwbbYszxbZxm = new CwbbYszxbZxm();
				cwbbYszxbZxm.setId(rs.getInt("id"));
				cwbbYszxbZxm.setZxmmc(rs.getString("zxmmc"));
				cwbbYszxbZxm.setZxm1(rs.getString("zxm1"));
				cwbbYszxbZxm.setZxm2(rs.getString("zxm2"));
				cwbbYszxbZxm.setZxm3(rs.getString("zxm3"));
				cwbbYszxbZxm.setZxm4(rs.getString("zxm4"));
				CwbbYszxbZxmList.add(cwbbYszxbZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbYszxbZxmList;
	}
	
	/*
	 *
	 *新增预算执行表记录
	 */
	public int insertCwbbYszxb(CwbbYszxb cwbbYszxb) {
		String sqlString = "insert into cwbb_yszxb (bt,year,month,czr,czrdw,czrID,czsj,xmid,jb1,jb2,jb3,jb4,gz1,gz2,gz3,gz4,sp1,sp2,sp3,sp4,bz1,bz2,bz3,bz4,xm1,xm2,xm3,xm4,hj1,hj2,hj3,hj4,tjzt) values('";
		sqlString += cwbbYszxb.getBt() + "',";
		sqlString += cwbbYszxb.getYear() + ",";
		sqlString += cwbbYszxb.getMonth() + ",'";
		sqlString += cwbbYszxb.getCzr() + "','";
		sqlString += cwbbYszxb.getCzrdw() + "','";
		sqlString += cwbbYszxb.getCzrID() + "','";
		sqlString += cwbbYszxb.getCzsj() + "','";
		sqlString += cwbbYszxb.getXmid() + "','";
		sqlString += cwbbYszxb.getJb1() + "','";
		sqlString += cwbbYszxb.getJb2() + "','";
		sqlString += cwbbYszxb.getJb3() + "','";
		sqlString += cwbbYszxb.getJb4() + "','";
		sqlString += cwbbYszxb.getGz1() + "','";
		sqlString += cwbbYszxb.getGz2() + "','";
		sqlString += cwbbYszxb.getGz3() + "','";
		sqlString += cwbbYszxb.getGz4() + "','";
		sqlString += cwbbYszxb.getSp1() + "','";
		sqlString += cwbbYszxb.getSp2() + "','";
		sqlString += cwbbYszxb.getSp3() + "','";
		sqlString += cwbbYszxb.getSp4() + "','";
		sqlString += cwbbYszxb.getBz1() + "','";
		sqlString += cwbbYszxb.getBz2() + "','";
		sqlString += cwbbYszxb.getBz3() + "','";
		sqlString += cwbbYszxb.getBz4() + "','";
		sqlString += cwbbYszxb.getXm1() + "','";
		sqlString += cwbbYszxb.getXm2() + "','";
		sqlString += cwbbYszxb.getXm3() + "','";
		sqlString += cwbbYszxb.getXm4() + "','";
		sqlString += cwbbYszxb.getHj1() + "','";
		sqlString += cwbbYszxb.getHj2() + "','";
		sqlString += cwbbYszxb.getHj3() + "','";
		sqlString += cwbbYszxb.getHj4() + "','";
		sqlString += cwbbYszxb.getTjzt() + "')";
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
	 *新增预算执行表记录_子项目表记录
	 */
	public int insertCwbbYszxbZxm(CwbbYszxbZxm cwbbYszxbZxm) {
		String sqlString = "insert into cwbb_yszxb_zxm (zxmmc,zxm1,zxm2,zxm3,zxm4) values('";
		sqlString += cwbbYszxbZxm.getZxmmc() + "','";
		sqlString += cwbbYszxbZxm.getZxm1() + "','";
		sqlString += cwbbYszxbZxm.getZxm2() + "','";
		sqlString += cwbbYszxbZxm.getZxm3() + "','";
		sqlString += cwbbYszxbZxm.getZxm4() + "')";
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
	 *修改预算执行表记录
	 */
	public boolean updateCwbbYszxb(CwbbYszxb cwbbYszxb) {
		String sqlString = "update cwbb_yszxb set year="; 
		sqlString += cwbbYszxb.getYear() + ",bt='";
		sqlString += cwbbYszxb.getBt()+ "',month=";
		sqlString += cwbbYszxb.getMonth() + ",czr='";
		sqlString += cwbbYszxb.getCzr() + "',czrdw='";
		sqlString += cwbbYszxb.getCzrdw() + "',czrID='";
		sqlString += cwbbYszxb.getCzrID() + "',czsj='";
		sqlString += cwbbYszxb.getCzsj() + "',xmid='";
		sqlString += cwbbYszxb.getXmid() + "',jb1='";
		sqlString += cwbbYszxb.getJb1() + "',jb2='";
		sqlString += cwbbYszxb.getJb2() + "',jb3='";
		sqlString += cwbbYszxb.getJb3() + "',jb4='";
		sqlString += cwbbYszxb.getJb4() + "',gz1='";
		sqlString += cwbbYszxb.getGz1() + "',gz2='";
		sqlString += cwbbYszxb.getGz2() + "',gz3='";
		sqlString += cwbbYszxb.getGz3() + "',gz4='";
		sqlString += cwbbYszxb.getGz4() + "',sp1='";
		sqlString += cwbbYszxb.getSp1() + "',sp2='";
		sqlString += cwbbYszxb.getSp2() + "',sp3='";
		sqlString += cwbbYszxb.getSp3() + "',sp4='";
		sqlString += cwbbYszxb.getSp4() + "',bz1='";
		sqlString += cwbbYszxb.getBz1() + "',bz2='";
		sqlString += cwbbYszxb.getBz2() + "',bz3='";
		sqlString += cwbbYszxb.getBz3() + "',bz4='";
		sqlString += cwbbYszxb.getBz4() + "',xm1='";
		sqlString += cwbbYszxb.getXm1() + "',xm2='";
		sqlString += cwbbYszxb.getXm2() + "',xm3='";
		sqlString += cwbbYszxb.getXm3() + "',xm4='";
		sqlString += cwbbYszxb.getXm4() + "',hj1='";
		sqlString += cwbbYszxb.getHj1() + "',hj2='";
		sqlString += cwbbYszxb.getHj2() + "',hj3='";
		sqlString += cwbbYszxb.getHj3() + "',hj4='";
		sqlString += cwbbYszxb.getHj4() + "',tjzt='";
		sqlString += cwbbYszxb.getTjzt() + "' where id=" + cwbbYszxb.getId();
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
	 *修改预算执行表记录
	 */
	public boolean updateCwbbYszxbRet(String id) {
		String sqlString = "update cwbb_yszxb set tjzt='3' where id=" + id;
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

	/*根据记录编号删除预算执行表记录*/
	public int deleteCwbbYszxbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_yszxb where id=" + id;
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
	/*根据记录编号删除预算执行表记录_子项目表*/
	public int deleteCwbbYszxbZxmByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from cwbb_yszxb_zxm where id in ("+IDList+")";
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
	 *根据条件
	 *查询预算执行表汇总表
	 */
	public  ArrayList<CwbbYszxbHzb> queryCwbbYszxbHzbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbYszxbHzb> CwbbYszxbHzbList = new ArrayList<CwbbYszxbHzb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_YszxbHzb  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_YszxbHzb "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
				cwbbYszxbHzb.setId(rs.getInt("id"));
				cwbbYszxbHzb.setYear(rs.getInt("year"));
				cwbbYszxbHzb.setMonth(rs.getInt("month"));
				cwbbYszxbHzb.setCzr(rs.getString("czr"));
				cwbbYszxbHzb.setCzrdw(rs.getString("czrdw"));
				cwbbYszxbHzb.setCzrID(rs.getString("czrID"));
				cwbbYszxbHzb.setCzsj(rs.getTimestamp("czsj"));
				CwbbYszxbHzbList.add(cwbbYszxbHzb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbYszxbHzbList;
	}
	/*
	 *根据条件
	 *查询预算执行表汇总表记录数
	 */
	public  int queryCwbbYszxbHzbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_YszxbHzb";
		}else{
			sqlString = "select count(*) from cwbb_YszxbHzb "+srbt;
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
	 *查询预算执行表汇总表
	 */
	public  CwbbYszxbHzb queryCwbbYszxbHzbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbYszxbHzb cwbbYszxbHzb = new CwbbYszxbHzb();
		String sqlString = "select * from cwbb_YszxbHzb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbYszxbHzb.setId(rs.getInt("id"));
				cwbbYszxbHzb.setYear(rs.getInt("year"));
				cwbbYszxbHzb.setMonth(rs.getInt("month"));
				cwbbYszxbHzb.setCzr(rs.getString("czr"));
				cwbbYszxbHzb.setCzrdw(rs.getString("czrdw"));
				cwbbYszxbHzb.setCzrID(rs.getString("czrID"));
				cwbbYszxbHzb.setCzsj(rs.getTimestamp("czsj"));
				cwbbYszxbHzb.setGlc(rs.getString("glc"));
				cwbbYszxbHzb.setHdc(rs.getString("hdc"));
				cwbbYszxbHzb.setHsj(rs.getString("hsj"));
				cwbbYszxbHzb.setYgc(rs.getString("ygc"));
				cwbbYszxbHzb.setJsc(rs.getString("jsc"));
				cwbbYszxbHzb.setZjc(rs.getString("zjc"));
				cwbbYszxbHzb.setKjzx(rs.getString("kjzx"));
				cwbbYszxbHzb.setXxzx(rs.getString("xxzx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbYszxbHzb;
	}
	/*
	 *根据年月
	 *查询各家事业单位主要财务指标表记录——用于汇总
	 */
	public  String queryCwbbYszxbBySjDW(int year, int month, String company) {
		 /* 保存符合条件的某页记录的集合链表 */
		String resStr="";
		String sqlString = "select * from cwbb_Yszxb  where year="+year+" and month="+month+" and czrdw='"+company+"' and tjzt='1' order by id desc";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				resStr = rs.getString("jb1")+" #;"+rs.getString("jb2")+" #;"
				+rs.getString("xm1")+" #;"+rs.getString("xm2")+" ";
			}else{
				resStr = " "+"#;"+" "+"#;"
				+" "+"#;"+" ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return resStr;
	}
	/*
	 *
	 *新增预算执行表汇总表记录
	 */
	public int insertCwbbYszxbHzb(CwbbYszxbHzb cwbbYszxbHzb) {
		String sqlString = "insert into cwbb_YszxbHzb (year,month,czr,czrdw,czrID,czsj,glc,hdc,hsj,ygc,jsc,zjc,kjzx,xxzx) values(";
		sqlString += cwbbYszxbHzb.getYear() + ",";
		sqlString += cwbbYszxbHzb.getMonth() + ",'";
		sqlString += cwbbYszxbHzb.getCzr() + "','";
		sqlString += cwbbYszxbHzb.getCzrdw() + "','";
		sqlString += cwbbYszxbHzb.getCzrID() + "','";
		sqlString += cwbbYszxbHzb.getCzsj() + "','";
		sqlString += cwbbYszxbHzb.getGlc() + "','";
		sqlString += cwbbYszxbHzb.getHdc() + "','";
		sqlString += cwbbYszxbHzb.getHsj() + "','";
		sqlString += cwbbYszxbHzb.getYgc() + "','";
		sqlString += cwbbYszxbHzb.getJsc() + "','";
		sqlString += cwbbYszxbHzb.getZjc() + "','";
		sqlString += cwbbYszxbHzb.getKjzx() + "','";
		sqlString += cwbbYszxbHzb.getXxzx()  + "')";
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
	 *修改预算执行表汇总表记录
	 */
	public boolean updateCwbbYszxbHzb(CwbbYszxbHzb cwbbYszxbHzb) {
		String sqlString = "update cwbb_YszxbHzb set year="; 
		sqlString += cwbbYszxbHzb.getYear() + ",month=";
		sqlString += cwbbYszxbHzb.getMonth() + ",czr='";
		sqlString += cwbbYszxbHzb.getCzr() + "',czrdw='";
		sqlString += cwbbYszxbHzb.getCzrdw() + "',czrID='";
		sqlString += cwbbYszxbHzb.getCzrID() + "',czsj='";
		sqlString += cwbbYszxbHzb.getCzsj() + "',glc='";
		sqlString += cwbbYszxbHzb.getGlc() + "',hdc='";
		sqlString += cwbbYszxbHzb.getHdc() + "',hsj='";
		sqlString += cwbbYszxbHzb.getHsj() + "',ygc='";
		sqlString += cwbbYszxbHzb.getYgc() + "',jsc='";
		sqlString += cwbbYszxbHzb.getJsc() + "',zjc='";
		sqlString += cwbbYszxbHzb.getZjc() + "',kjzx='";
		sqlString += cwbbYszxbHzb.getKjzx() + "',xxzx='";
		sqlString += cwbbYszxbHzb.getXxzx() + "' where id=" + cwbbYszxbHzb.getId();
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

	/*根据记录编号删除预算执行表汇总表记录*/
	public int deleteCwbbYszxbHzbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_YszxbHzb where id=" + id;
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
	/*根据时间删除预算执行表汇总表记录*/
	public  int deleteCwbbYszxbHzbByTime(int year, int month) {
		int result = 0;
		String sqlString = "delete from cwbb_YszxbHzb   where year="+year+" and month="+month;
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
	 *根据条件
	 *查询三公经费表记录
	 */
	public  ArrayList<CwbbSgjfb> queryCwbbSgjfbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbSgjfb> CwbbSgjfbList = new ArrayList<CwbbSgjfb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_sgjfb  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_sgjfb "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
				cwbbSgjfb.setId(rs.getInt("id"));
				cwbbSgjfb.setYear(rs.getInt("year"));
				cwbbSgjfb.setMonth(rs.getInt("month"));
				cwbbSgjfb.setCzr(rs.getString("czr"));
				cwbbSgjfb.setCzrdw(rs.getString("czrdw"));
				cwbbSgjfb.setCzrID(rs.getString("czrID"));
				cwbbSgjfb.setCzsj(rs.getTimestamp("czsj"));
				cwbbSgjfb.setTjzt(rs.getString("tjzt"));
				CwbbSgjfbList.add(cwbbSgjfb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbSgjfbList;
	}
	/*
	 *根据条件
	 *查询三公经费表记录数
	 */
	public  int queryCwbbSgjfbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_sgjfb";
		}else{
			sqlString = "select count(*) from cwbb_sgjfb "+srbt;
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
	 *根据单位和时间查询三公经费表的上月预算
	 *查询
	 */
	public  CwbbSgjfb queryCwbbSgjfbByDWSJ(int year, String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
		String sqlString = "select * from cwbb_sgjfb where year ="+year+" and czrdw='"+dw+"' and tjzt='1' order by id desc";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbSgjfb.setHj1(rs.getString("hj1"));
				cwbbSgjfb.setCgf1(rs.getString("cgf1"));
				cwbbSgjfb.setJdf1(rs.getString("jdf1"));
				cwbbSgjfb.setJbzc1(rs.getString("jbzc1"));
				cwbbSgjfb.setXmzc1(rs.getString("xmzc1"));
				cwbbSgjfb.setYcf1(rs.getString("ycf1"));
				cwbbSgjfb.setGzf1(rs.getString("gzf1"));
				cwbbSgjfb.setWhf1(rs.getString("whf1"));
				cwbbSgjfb.setHyf1(rs.getString("hyf1"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbSgjfb;
	}
	/*
	 *根据ID
	 *查询三公经费表
	 */
	public  CwbbSgjfb queryCwbbSgjfbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbSgjfb cwbbSgjfb = new CwbbSgjfb();
		String sqlString = "select * from cwbb_sgjfb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbSgjfb.setId(rs.getInt("id"));
				cwbbSgjfb.setYear(rs.getInt("year"));
				cwbbSgjfb.setMonth(rs.getInt("month"));
				cwbbSgjfb.setCzr(rs.getString("czr"));
				cwbbSgjfb.setCzrdw(rs.getString("czrdw"));
				cwbbSgjfb.setCzrID(rs.getString("czrID"));
				cwbbSgjfb.setCzsj(rs.getTimestamp("czsj"));
				cwbbSgjfb.setHj1(rs.getString("hj1"));
				cwbbSgjfb.setHj2(rs.getString("hj2"));
				cwbbSgjfb.setHj3(rs.getString("hj3"));
				cwbbSgjfb.setCgf1(rs.getString("cgf1"));
				cwbbSgjfb.setCgf2(rs.getString("cgf2"));
				cwbbSgjfb.setCgf3(rs.getString("cgf3"));
				cwbbSgjfb.setJdf1(rs.getString("jdf1"));
				cwbbSgjfb.setJdf2(rs.getString("jdf2"));
				cwbbSgjfb.setJdf3(rs.getString("jdf3"));
				cwbbSgjfb.setJbzc1(rs.getString("jbzc1"));
				cwbbSgjfb.setJbzc2(rs.getString("jbzc2"));
				cwbbSgjfb.setJbzc3(rs.getString("jbzc3"));
				cwbbSgjfb.setXmzc1(rs.getString("xmzc1"));
				cwbbSgjfb.setXmzc2(rs.getString("xmzc2"));
				cwbbSgjfb.setXmzc3(rs.getString("xmzc3"));
				cwbbSgjfb.setYcf1(rs.getString("ycf1"));
				cwbbSgjfb.setYcf2(rs.getString("ycf2"));
				cwbbSgjfb.setYcf3(rs.getString("ycf3"));
				cwbbSgjfb.setGzf1(rs.getString("gzf1"));
				cwbbSgjfb.setGzf2(rs.getString("gzf2"));
				cwbbSgjfb.setGzf3(rs.getString("gzf3"));
				cwbbSgjfb.setWhf1(rs.getString("whf1"));
				cwbbSgjfb.setWhf2(rs.getString("whf2"));
				cwbbSgjfb.setWhf3(rs.getString("whf3"));
				cwbbSgjfb.setHyf1(rs.getString("hyf1"));
				cwbbSgjfb.setHyf2(rs.getString("hyf2"));
				cwbbSgjfb.setHyf3(rs.getString("hyf3"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbSgjfb;
	}
	
	
	/*
	 *
	 *新增三公经费表记录
	 */
	public int insertCwbbSgjfb(CwbbSgjfb cwbbSgjfb) {
		String sqlString = "insert into cwbb_sgjfb (year,month,czr,czrdw,czrID,czsj,hj1,hj2,hj3,cgf1,cgf2,cgf3,jdf1,jdf2,jdf3,jbzc1,jbzc2,jbzc3,xmzc1,xmzc2,xmzc3,ycf1,ycf2,ycf3,gzf1,gzf2,gzf3,whf1,whf2,whf3,hyf1,hyf2,hyf3,tjzt) values(";
		sqlString += cwbbSgjfb.getYear() + ",";
		sqlString += cwbbSgjfb.getMonth() + ",'";
		sqlString += cwbbSgjfb.getCzr() + "','";
		sqlString += cwbbSgjfb.getCzrdw() + "','";
		sqlString += cwbbSgjfb.getCzrID() + "','";
		sqlString += cwbbSgjfb.getCzsj() + "','";
		sqlString += cwbbSgjfb.getHj1() + "','";
		sqlString += cwbbSgjfb.getHj2() + "','";
		sqlString += cwbbSgjfb.getHj3() + "','";
		sqlString += cwbbSgjfb.getCgf1() + "','";
		sqlString += cwbbSgjfb.getCgf2() + "','";
		sqlString += cwbbSgjfb.getCgf3() + "','";
		sqlString += cwbbSgjfb.getJdf1() + "','";
		sqlString += cwbbSgjfb.getJdf2() + "','";
		sqlString += cwbbSgjfb.getJdf3() + "','";
		sqlString += cwbbSgjfb.getJbzc1() + "','";
		sqlString += cwbbSgjfb.getJbzc2() + "','";
		sqlString += cwbbSgjfb.getJbzc3() + "','";
		sqlString += cwbbSgjfb.getXmzc1() + "','";
		sqlString += cwbbSgjfb.getXmzc2() + "','";
		sqlString += cwbbSgjfb.getXmzc3() + "','";
		sqlString += cwbbSgjfb.getYcf1() + "','";
		sqlString += cwbbSgjfb.getYcf2() + "','";
		sqlString += cwbbSgjfb.getYcf3() + "','";
		sqlString += cwbbSgjfb.getGzf1() + "','";
		sqlString += cwbbSgjfb.getGzf2() + "','";
		sqlString += cwbbSgjfb.getGzf3() + "','";
		sqlString += cwbbSgjfb.getWhf1() + "','";
		sqlString += cwbbSgjfb.getWhf2() + "','";
		sqlString += cwbbSgjfb.getWhf3() + "','";
		sqlString += cwbbSgjfb.getHyf1() + "','";
		sqlString += cwbbSgjfb.getHyf2()  + "','";
		sqlString += cwbbSgjfb.getHyf3() + "','";
		sqlString += cwbbSgjfb.getTjzt() + "')";
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
	 *修改三公经费表记录
	 */
	public boolean updateCwbbSgjfb(CwbbSgjfb cwbbSgjfb) {
		String sqlString = "update cwbb_sgjfb set year="; 
		sqlString += cwbbSgjfb.getYear() + ",month=";
		sqlString += cwbbSgjfb.getMonth() + ",czr='";
		sqlString += cwbbSgjfb.getCzr() + "',czrdw='";
		sqlString += cwbbSgjfb.getCzrdw() + "',czrID='";
		sqlString += cwbbSgjfb.getCzrID() + "',czsj='";
		sqlString += cwbbSgjfb.getCzsj() + "',hj1='";
		sqlString += cwbbSgjfb.getHj1() + "',hj2='";
		sqlString += cwbbSgjfb.getHj2() + "',hj3='";
		sqlString += cwbbSgjfb.getHj3() + "',cgf1='";
		sqlString += cwbbSgjfb.getCgf1() + "',cgf2='";
		sqlString += cwbbSgjfb.getCgf2() + "',cgf3='";
		sqlString += cwbbSgjfb.getCgf3() + "',jdf1='";
		sqlString += cwbbSgjfb.getJdf1() + "',jdf2='";
		sqlString += cwbbSgjfb.getJdf2() + "',jdf3='";
		sqlString += cwbbSgjfb.getJdf3() + "',jbzc1='";
		sqlString += cwbbSgjfb.getJbzc1() + "',jbzc2='";
		sqlString += cwbbSgjfb.getJbzc2() + "',jbzc3='";
		sqlString += cwbbSgjfb.getJbzc3() + "',xmzc1='";
		sqlString += cwbbSgjfb.getXmzc1() + "',xmzc2='";
		sqlString += cwbbSgjfb.getXmzc2() + "',xmzc3='";
		sqlString += cwbbSgjfb.getXmzc3() + "',ycf1='";
		sqlString += cwbbSgjfb.getYcf1() + "',ycf2='";
		sqlString += cwbbSgjfb.getYcf2() + "',ycf3='";
		sqlString += cwbbSgjfb.getYcf3() + "',gzf1='";
		sqlString += cwbbSgjfb.getGzf1() + "',gzf2='";
		sqlString += cwbbSgjfb.getGzf2() + "',gzf3='";
		sqlString += cwbbSgjfb.getGzf3() + "',whf1='";
		sqlString += cwbbSgjfb.getWhf1() + "',whf2='";
		sqlString += cwbbSgjfb.getWhf2() + "',whf3='";
		sqlString += cwbbSgjfb.getWhf3() + "',hyf1='";
		sqlString += cwbbSgjfb.getHyf1() + "',hyf2='";
		sqlString += cwbbSgjfb.getHyf2() + "',hyf3='";
		sqlString += cwbbSgjfb.getHyf3() + "',tjzt='";
		sqlString += cwbbSgjfb.getTjzt() + "' where id=" + cwbbSgjfb.getId();
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
	 *修改三公经费表记录
	 */
	public boolean updateCwbbSgjfbRet(String id) {
		String sqlString = "update cwbb_sgjfb set tjzt='3' where id=" + id;
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
	/*根据记录编号删除三公经费表记录*/
	public int deleteCwbbSgjfbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_sgjfb where id=" + id;
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
	 *根据条件
	 *查询事业单位主要财务指标表记录
	 */
	public  ArrayList<CwbbSydwzycwzbb> queryCwbbSydwzycwzbbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbSydwzycwzbb> CwbbSydwzycwzbbList = new ArrayList<CwbbSydwzycwzbb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_Sydwzycwzbb  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_Sydwzycwzbb "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
				cwbbSydwzycwzbb.setId(rs.getInt("id"));
				cwbbSydwzycwzbb.setBt(rs.getString("bt")); 
				cwbbSydwzycwzbb.setYear(rs.getInt("year"));
				cwbbSydwzycwzbb.setMonth(rs.getInt("month"));
				cwbbSydwzycwzbb.setCzr(rs.getString("czr"));
				cwbbSydwzycwzbb.setCzrdw(rs.getString("czrdw"));
				cwbbSydwzycwzbb.setCzrID(rs.getString("czrID"));
				cwbbSydwzycwzbb.setCzsj(rs.getTimestamp("czsj"));
				cwbbSydwzycwzbb.setTjzt(rs.getString("tjzt"));
				CwbbSydwzycwzbbList.add(cwbbSydwzycwzbb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbSydwzycwzbbList;
	}
	/*
	 *根据条件
	 *查询事业单位主要财务指标表记录数
	 */
	public  int queryCwbbSydwzycwzbbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_Sydwzycwzbb";
		}else{
			sqlString = "select count(*) from cwbb_Sydwzycwzbb "+srbt;
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
	 *查询事业单位主要财务指标表
	 */
	public  CwbbSydwzycwzbb queryCwbbSydwzycwzbbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbSydwzycwzbb cwbbSydwzycwzbb = new CwbbSydwzycwzbb();
		String sqlString = "select * from cwbb_Sydwzycwzbb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbSydwzycwzbb.setId(rs.getInt("id"));
				cwbbSydwzycwzbb.setBt(rs.getString("bt")); 
				cwbbSydwzycwzbb.setYear(rs.getInt("year"));
				cwbbSydwzycwzbb.setMonth(rs.getInt("month"));
				cwbbSydwzycwzbb.setCzr(rs.getString("czr"));
				cwbbSydwzycwzbb.setCzrdw(rs.getString("czrdw"));
				cwbbSydwzycwzbb.setCzrID(rs.getString("czrID"));
				cwbbSydwzycwzbb.setCzsj(rs.getTimestamp("czsj"));
				cwbbSydwzycwzbb.setZc(rs.getString("zc"));
				cwbbSydwzycwzbb.setZq(rs.getString("zq"));
				cwbbSydwzycwzbb.setDwtz(rs.getString("dwtz"));
				cwbbSydwzycwzbb.setGdzc(rs.getString("gdzc"));
				cwbbSydwzycwzbb.setHbzj(rs.getString("hbzj"));
				cwbbSydwzycwzbb.setFzze(rs.getString("fzze"));
				cwbbSydwzycwzbb.setFzye(rs.getString("fzye"));
				cwbbSydwzycwzbb.setJzc(rs.getString("jzc"));
				cwbbSydwzycwzbb.setJzczy(rs.getString("jzczy"));
				cwbbSydwzycwzbb.setJzcsy(rs.getString("jzcsy"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbSydwzycwzbb;
	}
	
	
	/*
	 *
	 *新增事业单位主要财务指标表记录
	 */
	public int insertCwbbSydwzycwzbb(CwbbSydwzycwzbb cwbbSydwzycwzbb) {
		String sqlString = "insert into cwbb_Sydwzycwzbb (year,month,bt,czr,czrdw,czrID,czsj,zc,zq,dwtz,gdzc,hbzj,fzze,fzye,jzc,jzczy,jzcsy,tjzt) values(";
		sqlString += cwbbSydwzycwzbb.getYear() + ",";
		sqlString += cwbbSydwzycwzbb.getMonth() + ",'";
		sqlString += cwbbSydwzycwzbb.getBt() + "','";
		sqlString += cwbbSydwzycwzbb.getCzr() + "','";
		sqlString += cwbbSydwzycwzbb.getCzrdw() + "','";
		sqlString += cwbbSydwzycwzbb.getCzrID() + "','";
		sqlString += cwbbSydwzycwzbb.getCzsj() + "','";
		sqlString += cwbbSydwzycwzbb.getZc() + "','";
		sqlString += cwbbSydwzycwzbb.getZq() + "','";
		sqlString += cwbbSydwzycwzbb.getDwtz() + "','";
		sqlString += cwbbSydwzycwzbb.getGdzc() + "','";
		sqlString += cwbbSydwzycwzbb.getHbzj() + "','";
		sqlString += cwbbSydwzycwzbb.getFzze() + "','";
		sqlString += cwbbSydwzycwzbb.getFzye() + "','";
		sqlString += cwbbSydwzycwzbb.getJzc() + "','";
		sqlString += cwbbSydwzycwzbb.getJzczy() + "','";
		sqlString += cwbbSydwzycwzbb.getJzcsy() + "','";
		sqlString += cwbbSydwzycwzbb.getTjzt() + "')";
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
	 *修改事业单位主要财务指标表记录
	 */
	public boolean updateCwbbSydwzycwzbb(CwbbSydwzycwzbb cwbbSydwzycwzbb) {
		String sqlString = "update cwbb_Sydwzycwzbb set year="; 
		sqlString += cwbbSydwzycwzbb.getYear() + ",month=";
		sqlString += cwbbSydwzycwzbb.getMonth() + ",bt='";
		sqlString += cwbbSydwzycwzbb.getBt()+ "',czr='";
		sqlString += cwbbSydwzycwzbb.getCzr() + "',czrID='";
		sqlString += cwbbSydwzycwzbb.getCzrID() + "',czsj='";
		sqlString += cwbbSydwzycwzbb.getCzsj() + "',zc='";
		sqlString += cwbbSydwzycwzbb.getZc() + "',zq='";
		sqlString += cwbbSydwzycwzbb.getZq() + "',dwtz='";
		sqlString += cwbbSydwzycwzbb.getDwtz() + "',gdzc='";
		sqlString += cwbbSydwzycwzbb.getGdzc() + "',hbzj='";
		sqlString += cwbbSydwzycwzbb.getHbzj() + "',fzze='";
		sqlString += cwbbSydwzycwzbb.getFzze() + "',fzye='";
		sqlString += cwbbSydwzycwzbb.getFzye() + "',jzc='";
		sqlString += cwbbSydwzycwzbb.getJzc() + "',jzczy='";
		sqlString += cwbbSydwzycwzbb.getJzczy() + "',jzcsy='";
		sqlString += cwbbSydwzycwzbb.getJzcsy() + "',tjzt='";
		sqlString += cwbbSydwzycwzbb.getTjzt() + "' where id=" + cwbbSydwzycwzbb.getId();
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
	 *修改事业单位主要财务指标表记录
	 */
	public boolean updateCwbbSydwzycwzbbRet(String id) {
		String sqlString = "update cwbb_Sydwzycwzbb set tjzt='3' where id=" + id;
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
	/*根据记录编号删除事业单位主要财务指标表记录*/
	public int deleteCwbbSydwzycwzbbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_Sydwzycwzbb where id=" + id;
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
	 *根据条件
	 *查询交通事业单位主要财务指标状况汇总表
	 */
	public  ArrayList<CwbbSydwzycwzbbHzb> queryCwbbSydwzycwzbbHzbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbSydwzycwzbbHzb> CwbbSydwzycwzbbHzbList = new ArrayList<CwbbSydwzycwzbbHzb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_SydwzycwzbbHzb  order by year desc,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_SydwzycwzbbHzb "+srbt +" order by year desc,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
				cwbbSydwzycwzbbHzb.setId(rs.getInt("id"));
				cwbbSydwzycwzbbHzb.setYear(rs.getInt("year"));
				cwbbSydwzycwzbbHzb.setMonth(rs.getInt("month"));
				cwbbSydwzycwzbbHzb.setCzr(rs.getString("czr"));
				cwbbSydwzycwzbbHzb.setCzrdw(rs.getString("czrdw"));
				cwbbSydwzycwzbbHzb.setCzrID(rs.getString("czrID"));
				cwbbSydwzycwzbbHzb.setCzsj(rs.getTimestamp("czsj"));
				CwbbSydwzycwzbbHzbList.add(cwbbSydwzycwzbbHzb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbSydwzycwzbbHzbList;
	}
	/*
	 *根据条件
	 *查询交通事业单位主要财务指标状况汇总表记录数
	 */
	public  int queryCwbbSydwzycwzbbHzbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_SydwzycwzbbHzb";
		}else{
			sqlString = "select count(*) from cwbb_SydwzycwzbbHzb "+srbt;
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
	 *查询交通事业单位主要财务指标状况汇总表
	 */
	public  CwbbSydwzycwzbbHzb queryCwbbSydwzycwzbbHzbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = new CwbbSydwzycwzbbHzb();
		String sqlString = "select * from cwbb_SydwzycwzbbHzb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbSydwzycwzbbHzb.setId(rs.getInt("id"));
				cwbbSydwzycwzbbHzb.setYear(rs.getInt("year"));
				cwbbSydwzycwzbbHzb.setMonth(rs.getInt("month"));
				cwbbSydwzycwzbbHzb.setCzr(rs.getString("czr"));
				cwbbSydwzycwzbbHzb.setCzrdw(rs.getString("czrdw"));
				cwbbSydwzycwzbbHzb.setCzrID(rs.getString("czrID"));
				cwbbSydwzycwzbbHzb.setCzsj(rs.getTimestamp("czsj"));
				cwbbSydwzycwzbbHzb.setGlc(rs.getString("glc"));
				cwbbSydwzycwzbbHzb.setHdc(rs.getString("hdc"));
				cwbbSydwzycwzbbHzb.setHsj(rs.getString("hsj"));
				cwbbSydwzycwzbbHzb.setYgc(rs.getString("ygc"));
				cwbbSydwzycwzbbHzb.setJsc(rs.getString("jsc"));
				cwbbSydwzycwzbbHzb.setZjc(rs.getString("zjc"));
				cwbbSydwzycwzbbHzb.setKjzx(rs.getString("kjzx"));
				cwbbSydwzycwzbbHzb.setXxzx(rs.getString("xxzx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbSydwzycwzbbHzb;
	}
	/*
	 *根据年月
	 *查询各家事业单位主要财务指标表记录——用于汇总
	 */
	public  String queryCwbbSydwzycwzbbBySjDW(int year, int month, String company) {
		 /* 保存符合条件的某页记录的集合链表 */
		String resStr="";
		String sqlString = "select * from cwbb_Sydwzycwzbb  where year="+year+" and month="+month+" and czrdw='"+company+"' and tjzt='1' order by id desc";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				resStr = rs.getString("zc")+" #;"+rs.getString("zq")+" #;"
				+rs.getString("dwtz")+" #;"+rs.getString("gdzc")+" #;"
				+rs.getString("hbzj")+" #;"+rs.getString("fzze")+" #;"
				+rs.getString("fzye")+" #;"+rs.getString("jzc")+" #;"
				+rs.getString("jzczy")+" #;"+rs.getString("jzcsy")+" ";
			}else{
				resStr = " "+"#;"+" "+"#;"
				+" "+"#;"+" "+"#;"
				+" "+"#;"+" "+"#;"
				+" "+"#;"+" "+"#;"
				+" "+"#;"+" ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return resStr;
	}
	/*
	 *
	 *新增交通事业单位主要财务指标状况汇总表记录
	 */
	public int insertCwbbSydwzycwzbbHzb(CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb) {
		String sqlString = "insert into cwbb_SydwzycwzbbHzb (year,month,czr,czrdw,czrID,czsj,glc,hdc,hsj,ygc,jsc,zjc,kjzx,xxzx) values(";
		sqlString += cwbbSydwzycwzbbHzb.getYear() + ",";
		sqlString += cwbbSydwzycwzbbHzb.getMonth() + ",'";
		sqlString += cwbbSydwzycwzbbHzb.getCzr() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getCzrdw() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getCzrID() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getCzsj() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getGlc() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getHdc() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getHsj() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getYgc() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getJsc() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getZjc() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getKjzx() + "','";
		sqlString += cwbbSydwzycwzbbHzb.getXxzx()  + "')";
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
	 *修改交通事业单位主要财务指标状况汇总表记录
	 */
	public boolean updateCwbbSydwzycwzbbHzb(CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb) {
		String sqlString = "update cwbb_SydwzycwzbbHzb set year="; 
		sqlString += cwbbSydwzycwzbbHzb.getYear() + ",month=";
		sqlString += cwbbSydwzycwzbbHzb.getMonth() + ",czr='";
		sqlString += cwbbSydwzycwzbbHzb.getCzr() + "',czrdw='";
		sqlString += cwbbSydwzycwzbbHzb.getCzrdw() + "',czrID='";
		sqlString += cwbbSydwzycwzbbHzb.getCzrID() + "',czsj='";
		sqlString += cwbbSydwzycwzbbHzb.getCzsj() + "',glc='";
		sqlString += cwbbSydwzycwzbbHzb.getGlc() + "',hdc='";
		sqlString += cwbbSydwzycwzbbHzb.getHdc() + "',hsj='";
		sqlString += cwbbSydwzycwzbbHzb.getHsj() + "',ygc='";
		sqlString += cwbbSydwzycwzbbHzb.getYgc() + "',jsc='";
		sqlString += cwbbSydwzycwzbbHzb.getJsc() + "',zjc='";
		sqlString += cwbbSydwzycwzbbHzb.getZjc() + "',kjzx='";
		sqlString += cwbbSydwzycwzbbHzb.getKjzx() + "',xxzx='";
		sqlString += cwbbSydwzycwzbbHzb.getXxzx() + "' where id=" + cwbbSydwzycwzbbHzb.getId();
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

	/*根据记录编号删除交通事业单位主要财务指标状况汇总表记录*/
	public int deleteCwbbSydwzycwzbbHzbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_SydwzycwzbbHzb where id=" + id;
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
	/*根据时间删除交通事业单位主要财务指标状况汇总表记录*/
	public  int deleteCwbbSydwzycwzbbHzbByTime(int year, int month) {
		int result = 0;
		String sqlString = "delete from cwbb_SydwzycwzbbHzb   where year="+year+" and month="+month;
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
	 *根据条件
	 *查询利润表记录
	 */
	public  ArrayList<CwbbLrb> queryCwbbLrbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbLrb> CwbbLrbList = new ArrayList<CwbbLrb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_Lrb  order by sbsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_Lrb "+srbt +" order by sbsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbLrb cwbbLrb = new CwbbLrb();
				cwbbLrb.setId(rs.getInt("id"));
				cwbbLrb.setBt(rs.getString("bt")); 
				cwbbLrb.setCzr(rs.getString("czr"));
				cwbbLrb.setCzrdw(rs.getString("czrdw"));
				cwbbLrb.setCzrID(rs.getString("czrID"));
				cwbbLrb.setCzsj(rs.getTimestamp("czsj"));
				cwbbLrb.setSbsj(rs.getDate("sbsj"));
				cwbbLrb.setHzbz(rs.getString("hzbz"));
				cwbbLrb.setTjzt(rs.getString("tjzt"));
				CwbbLrbList.add(cwbbLrb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbLrbList;
	}
	/*
	 *根据条件
	 *查询利润表记录数
	 */
	public  int queryCwbbLrbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_Lrb";
		}else{
			sqlString = "select count(*) from cwbb_Lrb "+srbt;
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
	 *查询
	 */
	public  CwbbLrb queryCwbbLrbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbLrb cwbbLrb = new CwbbLrb();
		String sqlString = "select * from cwbb_Lrb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbLrb.setId(rs.getInt("id"));
				cwbbLrb.setBt(rs.getString("bt"));
				cwbbLrb.setCzr(rs.getString("czr"));
				cwbbLrb.setCzrdw(rs.getString("czrdw"));
				cwbbLrb.setCzrID(rs.getString("czrID"));
				cwbbLrb.setCzsj(rs.getTimestamp("czsj"));
				cwbbLrb.setSbsj(rs.getDate("sbsj"));
				cwbbLrb.setHzbz(rs.getString("hzbz"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbLrb;
	}
	
	/*
	 *根据条件
	 *查询利润表数据
	 */
	public  ArrayList<CwbbLrbsj> queryCwbbLrbsjByLrbID(int lrbid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbLrbsj> CwbbLrbsjList = new ArrayList<CwbbLrbsj>();
		String sqlString = "select * from cwbb_Lrb_sj  where lrbID ="+lrbid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbLrbsj cwbbLrbsj = new CwbbLrbsj();
				cwbbLrbsj.setId(rs.getInt("id"));
				cwbbLrbsj.setLrbID(rs.getInt("lrbID"));
				cwbbLrbsj.setBys(rs.getString("bys"));
				cwbbLrbsj.setBnljs(rs.getString("bnljs"));
				cwbbLrbsj.setSntqs(rs.getString("sntqs"));
				CwbbLrbsjList.add(cwbbLrbsj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbLrbsjList;
	}
	
	/*
	 *
	 *新增利润表记录
	 */
	public int insertCwbbLrb(CwbbLrb cwbbLrb) {
		String sqlString = "insert into cwbb_Lrb (sbsj,bt,czr,czrdw,czrID,czsj,hzbz,tjzt) values('";
		sqlString += cwbbLrb.getSbsj() + "','";
		sqlString += cwbbLrb.getBt() + "','";
		sqlString += cwbbLrb.getCzr() + "','";
		sqlString += cwbbLrb.getCzrdw() + "','";
		sqlString += cwbbLrb.getCzrID() + "','";
		sqlString += cwbbLrb.getCzsj()  + "','";
		sqlString += cwbbLrb.getHzbz() + "','";
		sqlString += cwbbLrb.getTjzt() + "')";
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
	 *新增利润表数据记录
	 */
	public boolean insertCwbbLrbsj(ArrayList<CwbbLrbsj> CwbbLrbsjList) {
		int len = CwbbLrbsjList.size()-1;
		String sqlString = "insert into cwbb_Lrb_sj (lrbID,bys,bnljs,sntqs) values(";
		for(int i=0; i<len; i++){
			sqlString += CwbbLrbsjList.get(i).getLrbID() + ",'";
			sqlString += CwbbLrbsjList.get(i).getBys() + "','";
			sqlString += CwbbLrbsjList.get(i).getBnljs() + "','";
			sqlString += CwbbLrbsjList.get(i).getSntqs() + "'),(";
		}
		sqlString += CwbbLrbsjList.get(len).getLrbID() + ",'";
		sqlString += CwbbLrbsjList.get(len).getBys() + "','";
		sqlString += CwbbLrbsjList.get(len).getBnljs() + "','";
		sqlString += CwbbLrbsjList.get(len).getSntqs() + "')";
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
	 *修改利润表记录
	 */
	public boolean updateCwbbLrb(CwbbLrb cwbbLrb) {
		String sqlString = "update cwbb_Lrb set sbsj='"; 
		sqlString += cwbbLrb.getSbsj() + "',bt='";
		sqlString += cwbbLrb.getBt() + "',czr='";
		sqlString += cwbbLrb.getCzr() + "',czrdw='";
		sqlString += cwbbLrb.getCzrdw() + "',czrID='";
		sqlString += cwbbLrb.getCzrID() + "',czsj='";
		sqlString += cwbbLrb.getCzsj() + "',hzbz='";
		sqlString += cwbbLrb.getHzbz() + "',tjzt='";
		sqlString += cwbbLrb.getTjzt()+ "' where id=" + cwbbLrb.getId();
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
	 *修改利润表记录
	 */
	public boolean updateCwbbLrbRet(String id) {
		String sqlString = "update cwbb_Lrb set tjzt='3' where id=" + id;
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
	/*根据记录编号删除利润表记录*/
	public int deleteCwbbLrbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_Lrb where id=" + id;
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
	/*根据记录编号删除利润表数据*/
	public int deleteCwbbLrbsjByLrbID(int lrbid) {
		int result = 0;
		String sqlString = "delete from cwbb_Lrb_sj where lrbID ="+lrbid;
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
	 *根据条件
	 *查询资产负债表记录
	 */
	public  ArrayList<CwbbZcfzb> queryCwbbZcfzbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbZcfzb> CwbbZcfzbList = new ArrayList<CwbbZcfzb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from cwbb_Zcfzb  order by sbsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from cwbb_Zcfzb "+srbt +" order by sbsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
				cwbbZcfzb.setId(rs.getInt("id"));
				cwbbZcfzb.setBt(rs.getString("bt")); 
				cwbbZcfzb.setCzr(rs.getString("czr"));
				cwbbZcfzb.setCzrdw(rs.getString("czrdw"));
				cwbbZcfzb.setCzrID(rs.getString("czrID"));
				cwbbZcfzb.setCzsj(rs.getTimestamp("czsj"));
				cwbbZcfzb.setSbsj(rs.getDate("sbsj"));
				cwbbZcfzb.setHzbz(rs.getString("hzbz"));
				cwbbZcfzb.setTjzt(rs.getString("tjzt"));
				CwbbZcfzbList.add(cwbbZcfzb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbZcfzbList;
	}
	/*
	 *根据条件
	 *查询资产负债表记录数
	 */
	public  int queryCwbbZcfzbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from cwbb_Zcfzb";
		}else{
			sqlString = "select count(*) from cwbb_Zcfzb "+srbt;
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
	 *查询
	 */
	public  CwbbZcfzb queryCwbbZcfzbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		CwbbZcfzb cwbbZcfzb = new CwbbZcfzb();
		String sqlString = "select * from cwbb_Zcfzb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				cwbbZcfzb.setId(rs.getInt("id"));
				cwbbZcfzb.setBt(rs.getString("bt")); 
				cwbbZcfzb.setCzr(rs.getString("czr"));
				cwbbZcfzb.setCzrdw(rs.getString("czrdw"));
				cwbbZcfzb.setCzrID(rs.getString("czrID"));
				cwbbZcfzb.setCzsj(rs.getTimestamp("czsj"));
				cwbbZcfzb.setSbsj(rs.getDate("sbsj"));
				cwbbZcfzb.setHzbz(rs.getString("hzbz"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return cwbbZcfzb;
	}
	/*
	 *根据条件
	 *查询资产负债表——资产
	 */
	public  ArrayList<CwbbZcfzbzc> queryCwbbZcfzbzcByZcfzbID(int Zcfzbid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = new ArrayList<CwbbZcfzbzc>();
		String sqlString = "select * from cwbb_Zcfzb_zc  where ZcfzbID ="+Zcfzbid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbZcfzbzc cwbbZcfzbzc = new CwbbZcfzbzc();
				cwbbZcfzbzc.setId(rs.getInt("id"));
				cwbbZcfzbzc.setZcfzbID(rs.getInt("ZcfzbID"));
				cwbbZcfzbzc.setMc(rs.getString("mc"));
				cwbbZcfzbzc.setHc(rs.getString("hc"));
				cwbbZcfzbzc.setNcs(rs.getString("ncs"));
				cwbbZcfzbzc.setQms(rs.getString("qms"));
				CwbbZcfzbzcList.add(cwbbZcfzbzc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbZcfzbzcList;
	}
	/*
	 *根据条件
	 *查询资产负债表——负债及所有者权益数据
	 */
	public  ArrayList<CwbbZcfzbfz> queryCwbbZcfzbfzByZcfzbID(int Zcfzbid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = new ArrayList<CwbbZcfzbfz>();
		String sqlString = "select * from cwbb_Zcfzb_fz  where ZcfzbID ="+Zcfzbid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				CwbbZcfzbfz cwbbZcfzbfz = new CwbbZcfzbfz();
				cwbbZcfzbfz.setId(rs.getInt("id"));
				cwbbZcfzbfz.setZcfzbID(rs.getInt("ZcfzbID"));
				cwbbZcfzbfz.setMc(rs.getString("mc"));
				cwbbZcfzbfz.setHc(rs.getString("hc"));
				cwbbZcfzbfz.setNcs(rs.getString("ncs"));
				cwbbZcfzbfz.setQms(rs.getString("qms"));
				CwbbZcfzbfzList.add(cwbbZcfzbfz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return CwbbZcfzbfzList;
	}
	
	/*
	 *
	 *新增资产负债表记录
	 */
	public int insertCwbbZcfzb(CwbbZcfzb cwbbZcfzb) {
		String sqlString = "insert into cwbb_Zcfzb (sbsj,czr,bt,czrdw,czrID,czsj,hzbz,tjzt) values('";
		sqlString += cwbbZcfzb.getSbsj() + "','";
		sqlString += cwbbZcfzb.getCzr()  + "','";
		sqlString += cwbbZcfzb.getBt() + "','";
		sqlString += cwbbZcfzb.getCzrdw() + "','";
		sqlString += cwbbZcfzb.getCzrID() + "','";
		sqlString += cwbbZcfzb.getCzsj() + "','";
		sqlString += cwbbZcfzb.getHzbz() + "','";
		sqlString += cwbbZcfzb.getTjzt() + "')";
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
	 *新增资产负债表——资产记录
	 */
	public boolean insertCwbbZcfzbzc(ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList) {
		int len = CwbbZcfzbzcList.size()-1;
		String sqlString = "insert into cwbb_Zcfzb_zc (ZcfzbID,mc,hc,ncs,qms) values(";
		for(int i=0; i<len; i++){
			sqlString += CwbbZcfzbzcList.get(i).getZcfzbID() + ",'";
			sqlString += CwbbZcfzbzcList.get(i).getMc() + "','";
			sqlString += CwbbZcfzbzcList.get(i).getHc() + "','";
			sqlString += CwbbZcfzbzcList.get(i).getNcs() + "','";
			sqlString += CwbbZcfzbzcList.get(i).getQms() + "'),(";
		}
		sqlString += CwbbZcfzbzcList.get(len).getZcfzbID() + ",'";
		sqlString += CwbbZcfzbzcList.get(len).getMc() + "','";
		sqlString += CwbbZcfzbzcList.get(len).getHc() + "','";
		sqlString += CwbbZcfzbzcList.get(len).getNcs() + "','";
		sqlString += CwbbZcfzbzcList.get(len).getQms() + "')";
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
	 *新增资产负债表——负债及所有者权益记录
	 */
	public boolean insertCwbbZcfzbfz(ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList) {
		int len = CwbbZcfzbfzList.size()-1;
		String sqlString = "insert into cwbb_Zcfzb_fz (ZcfzbID,mc,hc,ncs,qms) values(";
		for(int i=0; i<len; i++){
			sqlString += CwbbZcfzbfzList.get(i).getZcfzbID() + ",'";
			sqlString += CwbbZcfzbfzList.get(i).getMc() + "','";
			sqlString += CwbbZcfzbfzList.get(i).getHc() + "','";
			sqlString += CwbbZcfzbfzList.get(i).getNcs() + "','";
			sqlString += CwbbZcfzbfzList.get(i).getQms() + "'),(";
		}
		sqlString += CwbbZcfzbfzList.get(len).getZcfzbID() + ",'";
		sqlString += CwbbZcfzbfzList.get(len).getMc() + "','";
		sqlString += CwbbZcfzbfzList.get(len).getHc() + "','";
		sqlString += CwbbZcfzbfzList.get(len).getNcs() + "','";
		sqlString += CwbbZcfzbfzList.get(len).getQms() + "')";
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
	 *修改资产负债表记录
	 */
	public boolean updateCwbbZcfzb(CwbbZcfzb cwbbZcfzb) {
		String sqlString = "update cwbb_Zcfzb set sbsj='"; 
		sqlString += cwbbZcfzb.getSbsj() + "',bt='";
		sqlString += cwbbZcfzb.getBt() + "',czr='";
		sqlString += cwbbZcfzb.getCzr() + "',czrdw='";
		sqlString += cwbbZcfzb.getCzrdw() + "',czrID='";
		sqlString += cwbbZcfzb.getCzrID() + "',czsj='";
		sqlString += cwbbZcfzb.getCzsj() + "',hzbz='";
		sqlString += cwbbZcfzb.getHzbz() + "',tjzt='";
		sqlString += cwbbZcfzb.getTjzt() + "' where id=" + cwbbZcfzb.getId();
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
	 *修改资产负债表记录
	 */
	public boolean updateCwbbZcfzbzcRet(String id) {
		String sqlString = "update cwbb_Zcfzb set tjzt='3' where id=" + id;
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
	/*根据记录编号删除资产负债表记录*/
	public int deleteCwbbZcfzbById(int id) {
		int result = 0;
		String sqlString = "delete from cwbb_Zcfzb where id=" + id;
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
	/*根据记录编号删除资产负债表——资产记录*/
	public int deleteCwbbZcfzbzcByZcfzbID(int Zcfzbid) {
		int result = 0;
		String sqlString = "delete from cwbb_Zcfzb_zc where ZcfzbID ="+Zcfzbid;
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
	/*根据记录编号删除资产负债表——负债及所有者权益记录*/
	public int deleteCwbbZcfzbfzByZcfzbID(int Zcfzbid) {
		int result = 0;
		String sqlString = "delete from cwbb_Zcfzb_fz where ZcfzbID ="+Zcfzbid;
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
