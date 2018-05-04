package com.safety.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Checkid;
import com.safety.entity.Zdxmsb;
import com.safety.entity.ZdxmsbGqbg;
import com.safety.entity.ZdxmsbGqbgGk;
import com.safety.entity.ZdxmsbXydbdydb;
import com.safety.entity.ZdxmsbXydbdydbGk;
import com.safety.entity.ZdxmsbZczbbg;
import com.safety.entity.ZdxmsbZddwtz;
import com.safety.entity.ZdxmsbZdrz;
import com.safety.entity.ZdxmsbZwcz;

public class ZdxmsbDao {
	/*
	 *根据查询条件
	 *查询重大项目申报——列表
	 */
	public  ArrayList<Zdxmsb> queryZdxmsbListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Zdxmsb> ZdxmsbList = new ArrayList<Zdxmsb>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from Zdxmsb order by czsj desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Zdxmsb "+srbt +" order by czsj desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Zdxmsb zdxmsb = new Zdxmsb();
				zdxmsb.setId(rs.getInt("id"));
				zdxmsb.setLx(rs.getString("lx"));
				zdxmsb.setNrid(rs.getInt("nrid"));
				zdxmsb.setCzr(rs.getString("czr"));
				zdxmsb.setCzrdw(rs.getString("czrdw"));
				zdxmsb.setCzrID(rs.getString("czrID"));
				zdxmsb.setCzsj(rs.getTimestamp("czsj"));
				zdxmsb.setShr(rs.getString("shr"));
				zdxmsb.setShrID(rs.getString("shrID"));
				zdxmsb.setShyj(rs.getString("shyj"));
				zdxmsb.setShsj(rs.getDate("shsj"));
				zdxmsb.setSmj(rs.getString("smj"));
				zdxmsb.setTjzt(rs.getString("tjzt"));
				ZdxmsbList.add(zdxmsb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ZdxmsbList;
	}
	/*
	 *根据条件
	 *查询重大项目申报——列表记录数
	 */
	public  int queryZdxmsbListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from Zdxmsb";
		}else{
			sqlString = "select count(*) from Zdxmsb "+srbt;
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
	 *查询重大项目申报——注册资本变更
	 */
	public  ZdxmsbZczbbg queryZdxmsbZczbbgByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbZczbbg zdxmsbZczbbg = new ZdxmsbZczbbg();
		String sqlString = "select * from Zdxmsb_zczbbg where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbZczbbg.setId(rs.getInt("id"));
				zdxmsbZczbbg.setZb(rs.getString("zb"));
				zdxmsbZczbbg.setKz(rs.getString("kz"));
				zdxmsbZczbbg.setJz(rs.getString("jz"));
				zdxmsbZczbbg.setBgzb(rs.getString("bgzb"));
				zdxmsbZczbbg.setYqk(rs.getString("yqk"));
				zdxmsbZczbbg.setBgqk(rs.getString("bgqk"));
				zdxmsbZczbbg.setBgyj(rs.getString("bgyj"));
				zdxmsbZczbbg.setGcqk(rs.getString("gcqk"));
				zdxmsbZczbbg.setFajg(rs.getString("fajg"));
				zdxmsbZczbbg.setCzr(rs.getString("czr"));
				zdxmsbZczbbg.setCzrdw(rs.getString("czrdw"));
				zdxmsbZczbbg.setCzrID(rs.getString("czrID"));
				zdxmsbZczbbg.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbZczbbg.setShr(rs.getString("shr"));
				zdxmsbZczbbg.setShrID(rs.getString("shrID"));
				zdxmsbZczbbg.setShyj(rs.getString("shyj"));
				zdxmsbZczbbg.setShsj(rs.getDate("shsj"));
				zdxmsbZczbbg.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbZczbbg;
	}

	/*
	 *根据ID
	 *查询重大项目申报——重大对外投资
	 */
	public  ZdxmsbZddwtz queryZdxmsbZddwtzByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbZddwtz zdxmsbZddwtz = new ZdxmsbZddwtz();
		String sqlString = "select * from Zdxmsb_Zddwtz where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbZddwtz.setId(rs.getInt("id"));
				zdxmsbZddwtz.setEd(rs.getString("ed"));
				zdxmsbZddwtz.setZyxm(rs.getString("zyxm"));
				zdxmsbZddwtz.setTzmd(rs.getString("tzmd"));
				zdxmsbZddwtz.setJbqk(rs.getString("jbqk"));
				zdxmsbZddwtz.setLzqk(rs.getString("lzqk"));
				zdxmsbZddwtz.setTzxy(rs.getString("tzxy"));
				zdxmsbZddwtz.setCzr(rs.getString("czr"));
				zdxmsbZddwtz.setCzrdw(rs.getString("czrdw"));
				zdxmsbZddwtz.setCzrID(rs.getString("czrID"));
				zdxmsbZddwtz.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbZddwtz.setShr(rs.getString("shr"));
				zdxmsbZddwtz.setShrID(rs.getString("shrID"));
				zdxmsbZddwtz.setShyj(rs.getString("shyj"));
				zdxmsbZddwtz.setShsj(rs.getDate("shsj"));
				zdxmsbZddwtz.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbZddwtz;
	}
	/*
	 *根据ID
	 *查询重大项目申报——重大融资
	 */
	public  ZdxmsbZdrz queryZdxmsbZdrzByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbZdrz zdxmsbZdrz = new ZdxmsbZdrz();
		String sqlString = "select * from Zdxmsb_Zdrz where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbZdrz.setId(rs.getInt("id"));
				zdxmsbZdrz.setEd(rs.getString("ed"));
				zdxmsbZdrz.setYy(rs.getString("yy"));
				zdxmsbZdrz.setJhsj1(rs.getDate("jhsj1"));
				zdxmsbZdrz.setJhsj2(rs.getDate("jhsj2"));
				zdxmsbZdrz.setLy(rs.getString("ly"));
				zdxmsbZdrz.setZfqk(rs.getString("zfqk"));
				zdxmsbZdrz.setDbqk(rs.getString("dbqk"));
				zdxmsbZdrz.setYqhk(rs.getString("yqhk"));
				zdxmsbZdrz.setNdhk(rs.getString("ndhk"));
				zdxmsbZdrz.setLjhk(rs.getString("ljhk"));
				zdxmsbZdrz.setYe(rs.getString("ye"));
				zdxmsbZdrz.setCzr(rs.getString("czr"));
				zdxmsbZdrz.setCzrdw(rs.getString("czrdw"));
				zdxmsbZdrz.setCzrID(rs.getString("czrID"));
				zdxmsbZdrz.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbZdrz.setShr(rs.getString("shr"));
				zdxmsbZdrz.setShrID(rs.getString("shrID"));
				zdxmsbZdrz.setShyj(rs.getString("shyj"));
				zdxmsbZdrz.setShsj(rs.getDate("shsj"));
				zdxmsbZdrz.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbZdrz;
	}
	/*
	 *根据ID
	 *查询重大项目申报——股权变更
	 */
	public  ZdxmsbGqbg queryZdxmsbGqbgByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbGqbg zdxmsbGqbg = new ZdxmsbGqbg();
		String sqlString = "select * from Zdxmsb_Gqbg where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbGqbg.setId(rs.getInt("id"));
				zdxmsbGqbg.setGkid(rs.getString("gkid"));
				zdxmsbGqbg.setLy(rs.getString("ly"));
				zdxmsbGqbg.setQk(rs.getString("qk"));
				zdxmsbGqbg.setCzr(rs.getString("czr"));
				zdxmsbGqbg.setCzrdw(rs.getString("czrdw"));
				zdxmsbGqbg.setCzrID(rs.getString("czrID"));
				zdxmsbGqbg.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbGqbg.setShr(rs.getString("shr"));
				zdxmsbGqbg.setShrID(rs.getString("shrID"));
				zdxmsbGqbg.setShyj(rs.getString("shyj"));
				zdxmsbGqbg.setShsj(rs.getDate("shsj"));
				zdxmsbGqbg.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbGqbg;
	}
	/*
	 *根据IDList
	 *查询重大项目申报——股权变更——概况
	 */
	public  ArrayList<ZdxmsbGqbgGk> queryZdxmsbGqbgGkByID(String IDList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ZdxmsbGqbgGk> ZdxmsbGqbgGkList = new ArrayList<ZdxmsbGqbgGk>();
		String sqlString = "select * from Zdxmsb_Gqbg_gk where id in ("+IDList+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ZdxmsbGqbgGk zdxmsbGqbgGk = new ZdxmsbGqbgGk();
				zdxmsbGqbgGk.setId(rs.getInt("id"));
				zdxmsbGqbgGk.setMc(rs.getString("mc"));
				zdxmsbGqbgGk.setYyys(rs.getString("yyys"));
				zdxmsbGqbgGk.setXz(rs.getString("xz"));
				zdxmsbGqbgGk.setCz(rs.getString("cz"));
				zdxmsbGqbgGk.setHyys(rs.getString("hyys"));
				zdxmsbGqbgGk.setCb(rs.getString("cb"));
				zdxmsbGqbgGk.setYj(rs.getString("yj"));
				zdxmsbGqbgGk.setDj(rs.getString("dj"));
				ZdxmsbGqbgGkList.add(zdxmsbGqbgGk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ZdxmsbGqbgGkList;
	}
	/*
	 *根据ID
	 *查询重大项目申报——债务重组
	 */
	public  ZdxmsbZwcz queryZdxmsbZwczByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbZwcz zdxmsbZwcz = new ZdxmsbZwcz();
		String sqlString = "select * from Zdxmsb_Zwcz where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbZwcz.setId(rs.getInt("id"));
				zdxmsbZwcz.setMdyy(rs.getString("mdyy"));
				zdxmsbZwcz.setNred(rs.getString("nred"));
				zdxmsbZwcz.setCb(rs.getString("cb"));
				zdxmsbZwcz.setFa(rs.getString("fa"));
				zdxmsbZwcz.setQk(rs.getString("qk"));
				zdxmsbZwcz.setCzr(rs.getString("czr"));
				zdxmsbZwcz.setCzrdw(rs.getString("czrdw"));
				zdxmsbZwcz.setCzrID(rs.getString("czrID"));
				zdxmsbZwcz.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbZwcz.setShr(rs.getString("shr"));
				zdxmsbZwcz.setShrID(rs.getString("shrID"));
				zdxmsbZwcz.setShyj(rs.getString("shyj"));
				zdxmsbZwcz.setShsj(rs.getDate("shsj"));
				zdxmsbZwcz.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbZwcz;
	}

	/*
	 *根据ID
	 *查询重大项目申报——信誉担保和重大资产抵押担保
	 */
	public  ZdxmsbXydbdydb queryZdxmsbXydbdydbByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ZdxmsbXydbdydb zdxmsbXydbdydb = new ZdxmsbXydbdydb();
		String sqlString = "select * from Zdxmsb_Xydbdydb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				zdxmsbXydbdydb.setId(rs.getInt("id"));
				zdxmsbXydbdydb.setGkid(rs.getString("gkid"));
				zdxmsbXydbdydb.setLy(rs.getString("ly"));
				zdxmsbXydbdydb.setNr(rs.getString("nr"));
				zdxmsbXydbdydb.setZcze1(rs.getString("zcze1"));
				zdxmsbXydbdydb.setZcze2(rs.getString("zcze2"));
				zdxmsbXydbdydb.setFzze1(rs.getString("fzze1"));
				zdxmsbXydbdydb.setFzze2(rs.getString("fzze2"));
				zdxmsbXydbdydb.setJzcze1(rs.getString("jzcze1"));
				zdxmsbXydbdydb.setJzcze2(rs.getString("jzcze2"));
				zdxmsbXydbdydb.setJlsp1(rs.getString("jlsp1"));
				zdxmsbXydbdydb.setJlsp2(rs.getString("jlsp2"));
				zdxmsbXydbdydb.setZjze1(rs.getString("zjze1"));
				zdxmsbXydbdydb.setZjze2(rs.getString("zjze2"));
				zdxmsbXydbdydb.setSsqk(rs.getString("ssqk"));
				zdxmsbXydbdydb.setCzr(rs.getString("czr"));
				zdxmsbXydbdydb.setCzrdw(rs.getString("czrdw"));
				zdxmsbXydbdydb.setCzrID(rs.getString("czrID"));
				zdxmsbXydbdydb.setCzsj(rs.getTimestamp("czsj"));
				zdxmsbXydbdydb.setShr(rs.getString("shr"));
				zdxmsbXydbdydb.setShrID(rs.getString("shrID"));
				zdxmsbXydbdydb.setShyj(rs.getString("shyj"));
				zdxmsbXydbdydb.setShsj(rs.getDate("shsj"));
				zdxmsbXydbdydb.setSmj(rs.getString("smj"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return zdxmsbXydbdydb;
	}
	/*
	 *根据IDList
	 *查询重大项目申报——信誉担保和重大资产抵押担保——概况
	 */
	public  ArrayList<ZdxmsbXydbdydbGk> queryZdxmsbXydbdydbGkByID(String IDList) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<ZdxmsbXydbdydbGk> ZdxmsbXydbdydbGkList = new ArrayList<ZdxmsbXydbdydbGk>();
		String sqlString = "select * from Zdxmsb_Xydbdydb_gk where id in ("+IDList+")";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ZdxmsbXydbdydbGk zdxmsbXydbdydbGk = new ZdxmsbXydbdydbGk();
				zdxmsbXydbdydbGk.setId(rs.getInt("id"));
				zdxmsbXydbdydbGk.setMc(rs.getString("mc"));
				zdxmsbXydbdydbGk.setPgsj(rs.getDate("pgsj"));
				zdxmsbXydbdydbGk.setJz(rs.getString("jz"));
				zdxmsbXydbdydbGk.setDbed(rs.getString("dbed"));
				ZdxmsbXydbdydbGkList.add(zdxmsbXydbdydbGk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ZdxmsbXydbdydbGkList;
	}
	/*
	 *
	 *新增重大项目申报
	 */
	public boolean insertZdxmsb(Zdxmsb zdxmsb) {
		String sqlString = "insert into Zdxmsb (lx,nrid,czr,czrID,czsj,czrdw,shr,shrID,shyj,smj,tjzt) values('";
		sqlString += zdxmsb.getLx() + "','";
		sqlString += zdxmsb.getNrid() + "','";
		sqlString += zdxmsb.getCzr() + "','";
		sqlString += zdxmsb.getCzrID() + "','";
		sqlString += zdxmsb.getCzsj() + "','";
		sqlString += zdxmsb.getCzrdw() + "','";
		sqlString += zdxmsb.getShr() + "','";
		sqlString += zdxmsb.getShrID()+ "','";
		sqlString += zdxmsb.getShyj() + "','";
		sqlString += zdxmsb.getSmj() + "','";
		sqlString += zdxmsb.getTjzt() + "')";
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	
	/*
	 *根据ID
	 *修改重大项目申报
	 */
	public boolean updateZdxmsb(Zdxmsb zdxmsb) {
		String sqlString = "update Zdxmsb set lx='";
		sqlString += zdxmsb.getLx() + "',czr='";
		sqlString += zdxmsb.getCzr() + "',czrID='";
		sqlString += zdxmsb.getCzrID() + "',czsj='";
		sqlString += zdxmsb.getCzsj() + "',czrdw='";
		sqlString += zdxmsb.getCzrdw()+ "',shr='";
		sqlString += zdxmsb.getShr() + "',shrID='";
		sqlString += zdxmsb.getShrID() + "',shyj='";
		sqlString += zdxmsb.getShyj() + "',smj='";
		sqlString += zdxmsb.getSmj() + "',tjzt='";
		sqlString += zdxmsb.getTjzt() + "' where nrid=" + zdxmsb.getNrid();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *
	 *新增重大项目申报——注册资本变更
	 */
	public int insertZdxmsbZczbbg(ZdxmsbZczbbg zdxmsbZczbbg) {
		String sqlString = "insert into Zdxmsb_Zczbbg (zb,kz,jz,bgzb,yqk,bgqk,bgyj,gcqk,fajg,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbZczbbg.getZb() + "','";
		sqlString += zdxmsbZczbbg.getKz() + "','";
		sqlString += zdxmsbZczbbg.getJz() + "','";
		sqlString += zdxmsbZczbbg.getBgzb() + "','";
		sqlString += zdxmsbZczbbg.getYqk() + "','";
		sqlString += zdxmsbZczbbg.getBgqk() + "','";
		sqlString += zdxmsbZczbbg.getBgyj() + "','";
		sqlString += zdxmsbZczbbg.getGcqk() + "','";
		sqlString += zdxmsbZczbbg.getFajg() + "','";
		sqlString += zdxmsbZczbbg.getCzr() + "','";
		sqlString += zdxmsbZczbbg.getCzrID() + "','";
		sqlString += zdxmsbZczbbg.getCzsj() + "','";
		sqlString += zdxmsbZczbbg.getCzrdw() + "','";
		sqlString += zdxmsbZczbbg.getSmj() + "','";
		sqlString += zdxmsbZczbbg.getShr() + "','";
		sqlString += zdxmsbZczbbg.getShrID()+ "','";
		sqlString += zdxmsbZczbbg.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	
	/*
	 *根据ID
	 *修改重大项目申报——注册资本变更
	 */
	public boolean updateZdxmsbZczbbg(ZdxmsbZczbbg zdxmsbZczbbg) {
		String sqlString = "update Zdxmsb_Zczbbg set zb='";
		sqlString += zdxmsbZczbbg.getZb() + "',kz='";
		sqlString += zdxmsbZczbbg.getKz() + "',jz='";
		sqlString += zdxmsbZczbbg.getJz() + "',bgzb='";
		sqlString += zdxmsbZczbbg.getBgzb() + "',yqk='";
		sqlString += zdxmsbZczbbg.getYqk() + "',bgqk='";
		sqlString += zdxmsbZczbbg.getBgqk() + "',bgyj='";
		sqlString += zdxmsbZczbbg.getBgyj() + "',gcqk='";
		sqlString += zdxmsbZczbbg.getGcqk() + "',fajg='";
		sqlString += zdxmsbZczbbg.getFajg() + "',czr='";
		sqlString += zdxmsbZczbbg.getCzr() + "',czrID='";
		sqlString += zdxmsbZczbbg.getCzrID() + "',czsj='";
		sqlString += zdxmsbZczbbg.getCzsj() + "',czrdw='";
		sqlString += zdxmsbZczbbg.getCzrdw() + "',smj='";
		sqlString += zdxmsbZczbbg.getSmj() + "',shr='";
		sqlString += zdxmsbZczbbg.getShr() + "',shrID='";
		sqlString += zdxmsbZczbbg.getShrID() + "',shyj='";
		sqlString += zdxmsbZczbbg.getShyj() + "' where id=" + zdxmsbZczbbg.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *
	 *新增重大项目申报——重大对外投资
	 */
	public int insertZdxmsbZddwtz(ZdxmsbZddwtz zdxmsbZddwtz) {
		String sqlString = "insert into Zdxmsb_Zddwtz (ed,zyxm,tzmd,jbqk,lzqk,tzxy,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbZddwtz.getEd() + "','";
		sqlString += zdxmsbZddwtz.getZyxm() + "','";
		sqlString += zdxmsbZddwtz.getTzmd() + "','";
		sqlString += zdxmsbZddwtz.getJbqk() + "','";
		sqlString += zdxmsbZddwtz.getLzqk() + "','";
		sqlString += zdxmsbZddwtz.getTzxy() + "','";
		sqlString += zdxmsbZddwtz.getCzr() + "','";
		sqlString += zdxmsbZddwtz.getCzrID() + "','";
		sqlString += zdxmsbZddwtz.getCzsj() + "','";
		sqlString += zdxmsbZddwtz.getCzrdw() + "','";
		sqlString += zdxmsbZddwtz.getSmj() + "','";
		sqlString += zdxmsbZddwtz.getShr() + "','";
		sqlString += zdxmsbZddwtz.getShrID()+ "','";
		sqlString += zdxmsbZddwtz.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	
	/*
	 *根据ID
	 *修改重大项目申报——重大对外投资
	 */
	public boolean updateZdxmsbZddwtz(ZdxmsbZddwtz zdxmsbZddwtz) {
		String sqlString = "update Zdxmsb_Zddwtz set ed='";
		sqlString += zdxmsbZddwtz.getEd() + "',zyxm='";
		sqlString += zdxmsbZddwtz.getZyxm() + "',tzmd='";
		sqlString += zdxmsbZddwtz.getTzmd() + "',jbqk='";
		sqlString += zdxmsbZddwtz.getJbqk() + "',lzqk='";
		sqlString += zdxmsbZddwtz.getLzqk() + "',tzxy='";
		sqlString += zdxmsbZddwtz.getTzxy() + "',czr='";
		sqlString += zdxmsbZddwtz.getCzr() + "',czrID='";
		sqlString += zdxmsbZddwtz.getCzrID() + "',czsj='";
		sqlString += zdxmsbZddwtz.getCzsj() + "',czrdw='";
		sqlString += zdxmsbZddwtz.getCzrdw() + "',smj='";
		sqlString += zdxmsbZddwtz.getSmj() + "',shr='";
		sqlString += zdxmsbZddwtz.getShr() + "',shrID='";
		sqlString += zdxmsbZddwtz.getShrID() + "',shyj='";
		sqlString += zdxmsbZddwtz.getShyj() + "' where id=" + zdxmsbZddwtz.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	
	/*
	 *
	 *新增重大项目申报——重大融资记录
	 */
	public int insertZdxmsbZdrz(ZdxmsbZdrz zdxmsbZdrz) {
		String sqlString = "insert into Zdxmsb_Zdrz (ed,yy,jhsj1,jhsj2,ly,zfqk,dbqk,yqhk,ndhk,ljhk,ye,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbZdrz.getEd() + "','";
		sqlString += zdxmsbZdrz.getYy() + "','";
		sqlString += zdxmsbZdrz.getJhsj1() + "','";
		sqlString += zdxmsbZdrz.getJhsj2() + "','";
		sqlString += zdxmsbZdrz.getLy() + "','";
		sqlString += zdxmsbZdrz.getZfqk() + "','";
		sqlString += zdxmsbZdrz.getDbqk() + "','";
		sqlString += zdxmsbZdrz.getYqhk() + "','";
		sqlString += zdxmsbZdrz.getNdhk() + "','";
		sqlString += zdxmsbZdrz.getLjhk() + "','";
		sqlString += zdxmsbZdrz.getYe() + "','";
		sqlString += zdxmsbZdrz.getCzr() + "','";
		sqlString += zdxmsbZdrz.getCzrID() + "','";
		sqlString += zdxmsbZdrz.getCzsj() + "','";
		sqlString += zdxmsbZdrz.getCzrdw() + "','";
		sqlString += zdxmsbZdrz.getSmj() + "','";
		sqlString += zdxmsbZdrz.getShr() + "','";
		sqlString += zdxmsbZdrz.getShrID()+ "','";
		sqlString += zdxmsbZdrz.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	
	/*
	 *根据ID
	 *修改重大项目申报——重大融资
	 */
	public boolean updateZdxmsbZdrz(ZdxmsbZdrz zdxmsbZdrz) {
		String sqlString = "update Zdxmsb_Zdrz set ed='";
		sqlString += zdxmsbZdrz.getEd() + "',yy='";
		sqlString += zdxmsbZdrz.getYy() + "',jhsj1='";
		sqlString += zdxmsbZdrz.getJhsj1() + "',jhsj2='";
		sqlString += zdxmsbZdrz.getJhsj2() + "',ly='";
		sqlString += zdxmsbZdrz.getLy() + "',zfqk='";
		sqlString += zdxmsbZdrz.getZfqk() + "',dbqk='";
		sqlString += zdxmsbZdrz.getDbqk() + "',yqhk='";
		sqlString += zdxmsbZdrz.getYqhk() + "',ndhk='";
		sqlString += zdxmsbZdrz.getNdhk() + "',ljhk='";
		sqlString += zdxmsbZdrz.getLjhk() + "',ye='";
		sqlString += zdxmsbZdrz.getYe()  + "',czr='";
		sqlString += zdxmsbZdrz.getCzr() + "',czrID='";
		sqlString += zdxmsbZdrz.getCzrID() + "',czsj='";
		sqlString += zdxmsbZdrz.getCzsj() + "',czrdw='";
		sqlString += zdxmsbZdrz.getCzrdw() + "',smj='";
		sqlString += zdxmsbZdrz.getSmj() + "',shr='";
		sqlString += zdxmsbZdrz.getShr() + "',shrID='";
		sqlString += zdxmsbZdrz.getShrID() + "',shyj='";
		sqlString += zdxmsbZdrz.getShyj() + "' where id=" + zdxmsbZdrz.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *
	 *新增重大项目申报——股权变更
	 */
	public int insertZdxmsbGqbg(ZdxmsbGqbg zdxmsbGqbg) {
		String sqlString = "insert into Zdxmsb_Gqbg (gkid,ly,qk,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbGqbg.getGkid() + "','";
		sqlString += zdxmsbGqbg.getLy() + "','";
		sqlString += zdxmsbGqbg.getQk() + "','";
		sqlString += zdxmsbGqbg.getCzr() + "','";
		sqlString += zdxmsbGqbg.getCzrID() + "','";
		sqlString += zdxmsbGqbg.getCzsj() + "','";
		sqlString += zdxmsbGqbg.getCzrdw() + "','";
		sqlString += zdxmsbGqbg.getSmj() + "','";
		sqlString += zdxmsbGqbg.getShr() + "','";
		sqlString += zdxmsbGqbg.getShrID()+ "','";
		sqlString += zdxmsbGqbg.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}

	/*
	 *
	 *新增重大项目申报——股权变更——概况
	 */
	public int insertZdxmsbGqbgGk(ZdxmsbGqbgGk zdxmsbGqbgGk) {
		String sqlString = "insert into Zdxmsb_Gqbg_Gk (mc,yyys,xz,cz,hyys,cb,yj,dj) values('";
		sqlString += zdxmsbGqbgGk.getMc() + "','";
		sqlString += zdxmsbGqbgGk.getYyys() + "','";
		sqlString += zdxmsbGqbgGk.getXz() + "','";
		sqlString += zdxmsbGqbgGk.getCz() + "','";
		sqlString += zdxmsbGqbgGk.getHyys() + "','";
		sqlString += zdxmsbGqbgGk.getCb() + "','";
		sqlString += zdxmsbGqbgGk.getYj() + "','";
		sqlString += zdxmsbGqbgGk.getDj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	/*
	 *根据ID
	 *修改重大项目申报——股权变更
	 */
	public boolean updateZdxmsbGqbg(ZdxmsbGqbg zdxmsbGqbg) {
		String sqlString = "update Zdxmsb_Gqbg set gkid='";
		sqlString += zdxmsbGqbg.getGkid() + "',ly='";
		sqlString += zdxmsbGqbg.getLy() + "',qk='";
		sqlString += zdxmsbGqbg.getQk() + "',czr='";
		sqlString += zdxmsbGqbg.getCzr() + "',czrID='";
		sqlString += zdxmsbGqbg.getCzrID() + "',czsj='";
		sqlString += zdxmsbGqbg.getCzsj() + "',czrdw='";
		sqlString += zdxmsbGqbg.getCzrdw() + "',smj='";
		sqlString += zdxmsbGqbg.getSmj() + "',shr='";
		sqlString += zdxmsbGqbg.getShr() + "',shrID='";
		sqlString += zdxmsbGqbg.getShrID() + "',shyj='";
		sqlString += zdxmsbGqbg.getShyj() + "' where id=" + zdxmsbGqbg.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*根据记录编号删除重大项目申报——股权变更——概况*/
	public int deleteZdxmsbGqbgGkByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from Zdxmsb_Gqbg_Gk where id in ("+IDList+")";
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 *
	 *新增重大项目申报——债务重组
	 */
	public int insertZdxmsbZwcz(ZdxmsbZwcz zdxmsbZwcz) {
		String sqlString = "insert into Zdxmsb_Zwcz (mdyy,nred,cb,fa,qk,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbZwcz.getMdyy() + "','";
		sqlString += zdxmsbZwcz.getNred() + "','";
		sqlString += zdxmsbZwcz.getCb() + "','";
		sqlString += zdxmsbZwcz.getFa() + "','";
		sqlString += zdxmsbZwcz.getQk() + "','";
		sqlString += zdxmsbZwcz.getCzr() + "','";
		sqlString += zdxmsbZwcz.getCzrID() + "','";
		sqlString += zdxmsbZwcz.getCzsj() + "','";
		sqlString += zdxmsbZwcz.getCzrdw() + "','";
		sqlString += zdxmsbZwcz.getSmj() + "','";
		sqlString += zdxmsbZwcz.getShr() + "','";
		sqlString += zdxmsbZwcz.getShrID()+ "','";
		sqlString += zdxmsbZwcz.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	
	/*
	 *根据ID
	 *修改重大项目申报——债务重组
	 */
	public boolean updateZdxmsbZwcz(ZdxmsbZwcz zdxmsbZwcz) {
		String sqlString = "update Zdxmsb_Zwcz set mdyy='";
		sqlString += zdxmsbZwcz.getMdyy() + "',nred='";
		sqlString += zdxmsbZwcz.getNred() + "',cb='";
		sqlString += zdxmsbZwcz.getCb() + "',fa='";
		sqlString += zdxmsbZwcz.getFa() + "',qk='";
		sqlString += zdxmsbZwcz.getQk()  + "',czr='";
		sqlString += zdxmsbZwcz.getCzr() + "',czrID='";
		sqlString += zdxmsbZwcz.getCzrID() + "',czsj='";
		sqlString += zdxmsbZwcz.getCzsj() + "',czrdw='";
		sqlString += zdxmsbZwcz.getCzrdw() + "',smj='";
		sqlString += zdxmsbZwcz.getSmj() + "',shr='";
		sqlString += zdxmsbZwcz.getShr() + "',shrID='";
		sqlString += zdxmsbZwcz.getShrID() + "',shyj='";
		sqlString += zdxmsbZwcz.getShyj() + "' where id=" + zdxmsbZwcz.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *
	 *新增重大项目申报——信誉担保和重大资产抵押担保
	 */
	public int insertZdxmsbXydbdydb(ZdxmsbXydbdydb zdxmsbXydbdydb) {
		String sqlString = "insert into Zdxmsb_Xydbdydb (gkid,ly,nr,zcze1,zcze2,fzze1,fzze2,jzcze1,jzcze2,jlsp1,jlsp2,zjze1,zjze2,ssqk,czr,czrID,czsj,czrdw,smj,shr,shrID,shyj) values('";
		sqlString += zdxmsbXydbdydb.getGkid() + "','";
		sqlString += zdxmsbXydbdydb.getLy() + "','";
		sqlString += zdxmsbXydbdydb.getNr() + "','";
		sqlString += zdxmsbXydbdydb.getZcze1() + "','";
		sqlString += zdxmsbXydbdydb.getZcze2() + "','";
		sqlString += zdxmsbXydbdydb.getFzze1() + "','";
		sqlString += zdxmsbXydbdydb.getFzze2() + "','";
		sqlString += zdxmsbXydbdydb.getJzcze1() + "','";
		sqlString += zdxmsbXydbdydb.getJzcze2() + "','";
		sqlString += zdxmsbXydbdydb.getJlsp1() + "','";
		sqlString += zdxmsbXydbdydb.getJlsp2() + "','";
		sqlString += zdxmsbXydbdydb.getZjze1() + "','";
		sqlString += zdxmsbXydbdydb.getZjze2() + "','";
		sqlString += zdxmsbXydbdydb.getSsqk() + "','";
		sqlString += zdxmsbXydbdydb.getCzr() + "','";
		sqlString += zdxmsbXydbdydb.getCzrID() + "','";
		sqlString += zdxmsbXydbdydb.getCzsj() + "','";
		sqlString += zdxmsbXydbdydb.getCzrdw() + "','";
		sqlString += zdxmsbXydbdydb.getSmj() + "','";
		sqlString += zdxmsbXydbdydb.getShr() + "','";
		sqlString += zdxmsbXydbdydb.getShrID()+ "','";
		sqlString += zdxmsbXydbdydb.getShyj() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}

	/*
	 *
	 *新增重大项目申报——信誉担保和重大资产抵押担保——概况
	 */
	public int insertZdxmsbXydbdydbGk(ZdxmsbXydbdydbGk zdxmsbXydbdydbGk) {
		String sqlString = "insert into Zdxmsb_Xydbdydb_Gk (mc,pgsj,jz,dbed) values('";
		sqlString += zdxmsbXydbdydbGk.getMc() + "','";
		sqlString += zdxmsbXydbdydbGk.getPgsj() + "','";
		sqlString += zdxmsbXydbdydbGk.getJz() + "','";
		sqlString += zdxmsbXydbdydbGk.getDbed() + "')";
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
			e.printStackTrace();
		}
		return ret_id;
	}
	/*
	 *根据ID
	 *修改重大项目申报——信誉担保和重大资产抵押担保
	 */
	public boolean updateZdxmsbXydbdydb(ZdxmsbXydbdydb zdxmsbXydbdydb) {
		String sqlString = "update Zdxmsb_Xydbdydb set gkid='";
		sqlString += zdxmsbXydbdydb.getGkid() + "',ly='";
		sqlString += zdxmsbXydbdydb.getLy() + "',nr='";
		sqlString += zdxmsbXydbdydb.getNr() + "',zcze1='";
		sqlString += zdxmsbXydbdydb.getZcze1() + "',zcze2='";
		sqlString += zdxmsbXydbdydb.getZcze2() + "',fzze1='";
		sqlString += zdxmsbXydbdydb.getFzze1() + "',fzze2='";
		sqlString += zdxmsbXydbdydb.getFzze2() + "',jzcze1='";
		sqlString += zdxmsbXydbdydb.getJzcze1() + "',jzcze2='";
		sqlString += zdxmsbXydbdydb.getJzcze2() + "',jlsp1='";
		sqlString += zdxmsbXydbdydb.getJlsp1() + "',jlsp2='";
		sqlString += zdxmsbXydbdydb.getJlsp2() + "',zjze1='";
		sqlString += zdxmsbXydbdydb.getZjze1()  + "',zjze2='";
		sqlString += zdxmsbXydbdydb.getZjze2()  + "',ssqk='";
		sqlString += zdxmsbXydbdydb.getSsqk() + "',czr='";
		sqlString += zdxmsbXydbdydb.getCzr() + "',czrID='";
		sqlString += zdxmsbXydbdydb.getCzrID() + "',czsj='";
		sqlString += zdxmsbXydbdydb.getCzsj() + "',czrdw='";
		sqlString += zdxmsbXydbdydb.getCzrdw() + "',smj='";
		sqlString += zdxmsbXydbdydb.getSmj() + "',shr='";
		sqlString += zdxmsbXydbdydb.getShr() + "',shrID='";
		sqlString += zdxmsbXydbdydb.getShrID() + "',shyj='";
		sqlString += zdxmsbXydbdydb.getShyj() + "' where id=" + zdxmsbXydbdydb.getId();
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*根据记录编号删除重大项目申报——信誉担保和重大资产抵押担保——概况*/
	public int deleteZdxmsbXydbdydbGkByIDList(String IDList) {
		int result = 0;
		String sqlString = "delete from Zdxmsb_Xydbdydb_Gk where id in ("+IDList+")";
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报*/
	public static int DeleteZdxmsbById(int id) {
		int result = 0;
		String sqlString = "delete from Zdxmsb where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——注册资本变更*/
	public static int DeleteZdxmsbZczbbgById(int id) {
		int result = 0;
		String sqlString = "delete from zdxmsb_zczbbg where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——重大对外投资*/
	public static int DeleteZdxmsbZddwtzById(int id) {
		int result = 0;
		String sqlString = "delete from zdxmsb_zddwtz where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——重大融资*/
	public static int DeleteZdxmsbZdrzById(int id) {
		int result = 0;
		String sqlString = "delete from Zdxmsb_Zdrz where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——股权变更*/
	public static int DeleteZdxmsbGqbgById(int id) {
		int result = 0;
		String sqlString = "delete from zdxmsb_gqbg where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——债务重组*/
	public static int DeleteZdxmsbZwczById(int id) {
		int result = 0;
		String sqlString = "delete from zdxmsb_zwcz where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*根据记录编号删除某个重大项目申报——信誉担保和重大资产抵押担保*/
	public static int DeleteZdxmsbXydbdydbById(int id) {
		int result = 0;
		String sqlString = "delete from zdxmsb_xydbdydb where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	 *
	 *重大项目申报——查询审核人
	 */
	public Checkid queryZdxmsbSHR() {
		 /* 保存符合条件的某页记录的集合链表 */
		Checkid checkid = new Checkid();
		String sqlString = "select * from checkid";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				checkid.setId(rs.getInt("id"));
				checkid.setZdxmsbID(rs.getString("zdxmsbID"));
				checkid.setZdxmsbName(rs.getString("zdxmsbName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return checkid;
	}
	/*
	 *根据ID
	 *重大项目申报——审核  
	 */
	public boolean ZdxmsbSH(int nrid, String shyj, Date shsj) {
		String sqlString = "update Zdxmsb set shyj='"+shyj+"',shsj='"+shsj+"' where nrid=" + nrid;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——注册资本变更——审核  
	 */
	public boolean ZdxmsbZczbbgSH(int id, String shyj, Date shsj) {
		String sqlString = "update zdxmsb_zczbbg set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——重大对外投资——审核  
	 */
	public boolean ZdxmsbZddwtzSH(int id, String shyj, Date shsj) {
		String sqlString = "update zdxmsb_zddwtz set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——重大融资——审核  
	 */
	public boolean ZdxmsbZdrzSH(int id, String shyj, Date shsj) {
		String sqlString = "update Zdxmsb_Zdrz set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——股权变更——审核  
	 */
	public boolean ZdxmsbGqbgSH(int id, String shyj, Date shsj) {
		String sqlString = "update zdxmsb_gqbg set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——债务重组——审核  
	 */
	public boolean ZdxmsbZwczSH(int id, String shyj, Date shsj) {
		String sqlString = "update zdxmsb_zwcz set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据ID
	 *重大项目申报——信誉担保和重大资产抵押担保——审核  
	 */
	public boolean ZdxmsbXydbdydbSH(int id, String shyj, Date shsj) {
		String sqlString = "update zdxmsb_xydbdydb set shyj='"+shyj+"',shsj='"+shsj+"' where id=" + id;
		int effectCount = 0;
		DB db = new DB();
		try {
			effectCount = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (effectCount>0)?true:false;
	}
	/*
	 *根据条件
	 *查询重大项目申报——重大融资记录数
	 */
	public  int queryMyZdxmsbSHCount(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString =  "select count(*) from Zdxmsb where shrID='"+username+"' and shyj='未审批' and tjzt='1' ";
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
}
