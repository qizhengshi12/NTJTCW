package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.CglibBean;
import com.safety.entity.Njfb;
import com.safety.entity.NjfbConsolidations;

public class LnnjDao {
	
	
	/*
	 *根据条件
	 *查询是否已新建相应列数的表 
	 */
	public boolean queryNjfbLs(int bbls) {
		 /* 保存符合条件的某页记录的集合链表 */
		boolean res = false;
		String sqlString = "select * from Njfb_ls where num ="+bbls;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return res;
	}
	/*
	 *
	 *新增记录
	 */
	public int insertNjfbLs(int bbls) {
		int result = 0;
		String sqlString = "insert into Njfb_ls (num) values("+bbls+")";
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

	
	/*根据列数创建表*/
	public int createNjfbByBbls(int bbls) {
		int result = 0;
		String sqlString = "create table njfb_ls"+bbls+" (id int(10) NOT NULL AUTO_INCREMENT,";
		for(int i=1; i<=bbls;i++){
			sqlString += "zb"+i+" varchar(100) DEFAULT '',";
		}
		sqlString += "xmid int(10),";
		sqlString += " PRIMARY KEY (id)) DEFAULT CHARSET=utf8";
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
	 * 根据多个ID查找用户信息
	 */
	public  ArrayList<Njfb> queryNjfbByIdList(String fatherid, int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Njfb> NjfbList = new ArrayList<Njfb>();
		String sqlString = "select * from Njfb where fatherid='"+fatherid+"' order by id limit "+begin+","+pageSize;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Njfb njfb = new Njfb();
				njfb.setId(rs.getInt("id"));
				njfb.setBt(rs.getString("bt"));
				njfb.setYear(rs.getInt("year"));
				njfb.setFatherid(rs.getString("fatherid"));
				njfb.setCzr(rs.getString("czr"));
				njfb.setCzrID(rs.getString("czrID"));
				njfb.setCzrdw(rs.getString("czrdw"));
				njfb.setBbls(rs.getInt("bbls"));
				NjfbList.add(njfb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return NjfbList;
	}
	/*
	 * 根据多个ID查找用户信息
	 */
	public  int queryNjfbByIdListCount(String fatherid) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select count(*) from Njfb where fatherid='"+fatherid+"' order by id";
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
	 *根据条件
	 *查询信息 
	 */
	public  ArrayList<Njfb> queryNjfbByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Njfb> NjfbList = new ArrayList<Njfb>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from Njfb order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Njfb "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Njfb njfb = new Njfb();
				njfb.setId(rs.getInt("id"));
				njfb.setBt(rs.getString("bt"));
				njfb.setYear(rs.getInt("year"));
				njfb.setFatherid(rs.getString("fatherid"));
				njfb.setCzr(rs.getString("czr"));
				njfb.setCzrID(rs.getString("czrID"));
				njfb.setCzrdw(rs.getString("czrdw"));
				njfb.setBbls(rs.getInt("bbls"));
				NjfbList.add(njfb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return NjfbList;
	}
	/*
	 *根据条件
	 *查询记录数
	 */
	public  int queryNjfbByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from Njfb order by id";
		}else{
			sqlString = "select count(*) from Njfb "+srbt+"  order by id";
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
	 * 根据1个ID查找用户信息
	 */
	public  Njfb queryNjfbById(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		Njfb njfb = new Njfb();
		String sqlString = "select * from Njfb where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				njfb.setId(rs.getInt("id"));
				njfb.setBt(rs.getString("bt"));
				njfb.setYear(rs.getInt("year"));
				njfb.setFatherid(rs.getString("fatherid"));
				njfb.setCzr(rs.getString("czr"));
				njfb.setCzrID(rs.getString("czrID"));
				njfb.setCzrdw(rs.getString("czrdw"));
				njfb.setBbls(rs.getInt("bbls"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return njfb;
	}
	
	/*
	 *
	 *新增主表记录
	 */
	public int insertNjfb(Njfb njfb) {
		String sqlString = "insert into njfb (bt,year,fatherid,czr,czsj,czrID,czrdw,bbls) values('";
		sqlString += njfb.getBt() + "',";
		sqlString += njfb.getYear() + ",'";
		sqlString += njfb.getFatherid() + "','";
		sqlString += njfb.getCzr() + "','";
		sqlString += njfb.getCzsj()+ "','";
		sqlString += njfb.getCzrID() + "','";
		sqlString += njfb.getCzrdw() + "',";
		sqlString += njfb.getBbls() + ")";
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
	 *修改主表记录
	 */
	public boolean updateNjfb(Njfb njfb) {
		String sqlString = "update njfb set year="; 
		sqlString += njfb.getYear() + ",czr='";
		sqlString += njfb.getCzr() + "',czrdw='";
		sqlString += njfb.getCzrdw() + "',czrID='";
		sqlString += njfb.getCzrID() + "',czsj='";
		sqlString += njfb.getCzsj() + "',fatherid='";
		sqlString += njfb.getFatherid() + "',bbls=";
		sqlString += njfb.getBbls() + ",bt='";
		sqlString += njfb.getBt()  + "' where id=" + njfb.getId();
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
	/*根据记录编号删除_子项目表*/
	public int deleteNjfbByID(int id) {
		int result = 0;
		String sqlString = "delete from njfb where id ="+id;
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
	 *查询信息 
	 */
	public ArrayList<CglibBean> queryNjfbDTByIDList(int xmid, int bbls) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<CglibBean> lnnjDTList = new ArrayList<CglibBean>();
		String sqlString = "select * from Njfb_ls"+bbls+" where xmid ="+xmid;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			//动态创建实体bean
			HashMap propertyMap = new HashMap();
			// 设置类成员属性       
			propertyMap.put("id", Class.forName("java.lang.Integer"));
			propertyMap.put("xmid", Class.forName("java.lang.Integer"));
			for(int i=1; i<=bbls; i++){
				propertyMap.put("zb"+i, Class.forName("java.lang.String"));
			}
			while (rs.next()) {
				// 生成动态 Bean    
				CglibBean bean = new CglibBean(propertyMap);
				bean.setValue("id", rs.getInt("id"));
				bean.setValue("xmid", rs.getInt("xmid"));
				for(int j=1; j<=bbls; j++){
					bean.setValue("zb"+j, rs.getString("zb"+j));
				}
				lnnjDTList.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return lnnjDTList;
	}
	/*根据记录编号删除_子项目表*/
	public int deleteNjfbDTByIDList(int xmid, int bbls) {
		int result = 0;
		String sqlString = "delete from Njfb_ls"+bbls+"  where xmid ="+xmid+"";
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
	 *新增子项目表记录
	 */
	public int insertNjfbDT(ArrayList<CglibBean> lnnjDTList, int bbls) {
		int len = lnnjDTList.size()-1;
		String sqlString = "insert into Njfb_ls"+bbls+" (xmid,";
		for(int i=1; i<bbls; i++){
			sqlString += "zb"+i+",";
		}
		sqlString += "zb"+bbls+") values(";
		
		for(int i=0; i<len; i++){
			sqlString += lnnjDTList.get(i).getValue("xmid") + ",'";
			for(int j=1; j<bbls; j++){
				sqlString += lnnjDTList.get(i).getValue("zb"+j) + "','";
			}
			sqlString += lnnjDTList.get(i).getValue("zb"+bbls) + "'),(";
		}
		sqlString += lnnjDTList.get(len).getValue("xmid") + ",'";
		for(int k=1; k<bbls; k++){
			sqlString += lnnjDTList.get(len).getValue("zb"+k) + "','";
		}
		sqlString += lnnjDTList.get(len).getValue("zb"+bbls) + "')";
		
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
	 *根据条件
	 *查询信息 
	 */
	public ArrayList<NjfbConsolidations> queryNjfbCLById(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<NjfbConsolidations> njfbCLList = new ArrayList<NjfbConsolidations>();
		String sqlString = "select * from njfb_consolidations where xmid ="+id+" order by row1,column1,row2,column2";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				NjfbConsolidations njfbCL = new NjfbConsolidations();
				njfbCL.setId(rs.getInt("id"));
				njfbCL.setRow1(rs.getInt("row1"));
				njfbCL.setColumn1(rs.getInt("column1"));
				njfbCL.setRow2(rs.getInt("row2"));
				njfbCL.setColumn2(rs.getInt("column2"));
				njfbCL.setXmid(rs.getInt("xmid"));
				njfbCLList.add(njfbCL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return njfbCLList;
	}
	/*
	 *
	 *新增记录
	 */
	public boolean insertNjfbJTGK(ArrayList<NjfbConsolidations> njfbCLList) {
		int len = njfbCLList.size()-1;
		String sqlString = "insert into njfb_consolidations (column1,row1,column2,row2,xmid) values(";
		for(int i=0; i<len; i++){
			sqlString += njfbCLList.get(i).getColumn1() + ",";
			sqlString += njfbCLList.get(i).getRow1() + ",";
			sqlString += njfbCLList.get(i).getColumn2() + ",";
			sqlString += njfbCLList.get(i).getRow2() + ",";
			sqlString += njfbCLList.get(i).getXmid() + "),(";
		}
		sqlString += njfbCLList.get(len).getColumn1() + ",";
		sqlString += njfbCLList.get(len).getRow1() + ",";
		sqlString += njfbCLList.get(len).getColumn2() + ",";
		sqlString += njfbCLList.get(len).getRow2() + ",";
		sqlString += njfbCLList.get(len).getXmid() + ")";
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

	
	/*根据记录编号删除_子项目表*/
	public int deleteNjfbJTGKByIDList(int id) {
		int result = 0;
		String sqlString = "delete from njfb_consolidations where xmid ="+id;
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
