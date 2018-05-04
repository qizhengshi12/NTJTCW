package com.safety.dao;

import java.sql.ResultSet;

import com.database.Utils.DB;
import com.safety.entity.Checkid;
import com.safety.entity.Permissions;

public class PermissionsDao {
	/*
	 *根据登陆用户名
	 *查询一条权限
	 */
	public Permissions queryPermissionsByUsername(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		Permissions permissions = new Permissions();
		String sqlString = "select * from permissions where username ='"+ username + "'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				permissions.setId(rs.getInt("id"));
				permissions.setUsername(rs.getString("username"));
				permissions.setCzr(rs.getString("czr"));
				permissions.setCzrID(rs.getString("czrID"));
				permissions.setContentzzxx(rs.getString("contentzzxx"));
				permissions.setNodezzxx(rs.getString("nodezzxx"));
				permissions.setContentcjfg(rs.getString("contentcjfg"));
				permissions.setNodecjfg(rs.getString("nodecjfg"));
				permissions.setContentglzd(rs.getString("contentglzd"));
				permissions.setNodeglzd(rs.getString("nodeglzd"));
				permissions.setWjgl(rs.getString("wjgl"));
				permissions.setGztz(rs.getString("gztz"));
				permissions.setJhzj(rs.getString("jhzj"));
				permissions.setGzdt(rs.getString("gzdt"));
				permissions.setCwbb(rs.getString("cwbb"));
				permissions.setSjbb(rs.getString("sjbb"));
				permissions.setTjbb(rs.getString("tjbb"));
				permissions.setZdxmsb(rs.getString("zdxmsb"));
				permissions.setSgjfsy(rs.getString("sgjfsy"));
				permissions.setGzfl(rs.getString("gzfl"));
				permissions.setCwjd(rs.getString("cwjd"));
				permissions.setPostinformation(rs.getString("postinformation"));
				permissions.setTopscroll(rs.getString("topscroll"));
				permissions.setLearningcorner(rs.getString("learningcorner"));
				permissions.setNjgl(rs.getString("njgl"));
				permissions.setNjfb(rs.getString("njfb"));
				permissions.setJjyxfx(rs.getString("jjyxfx"));
				permissions.setBbdz(rs.getString("bbdz"));
			}else{
				permissions.setUsername("");
				permissions.setCzr("");
				permissions.setCzrID("");
				permissions.setContentzzxx("");
				permissions.setNodezzxx("");
				permissions.setContentcjfg("");
				permissions.setNodecjfg("");
				permissions.setContentglzd("");
				permissions.setNodeglzd("");
				permissions.setWjgl("");
				permissions.setGztz("");
				permissions.setJhzj("");
				permissions.setGzdt("");
				permissions.setCwbb("");
				permissions.setSjbb("");
				permissions.setTjbb("");
				permissions.setZdxmsb("");
				permissions.setSgjfsy("");
				permissions.setGzfl("");
				permissions.setCwjd("");
				permissions.setPostinformation("");
				permissions.setTopscroll("");
				permissions.setLearningcorner("");
				permissions.setNjgl("");
				permissions.setNjfb("");
				permissions.setJjyxfx("");
				permissions.setBbdz("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return permissions;
	}
	/*
	 *根据登陆用户名
	 *查询是否有权限
	 */
	public boolean judgePermissionsByUsername(String username) {
		 /* 保存符合条件的某页记录的集合链表 */
		boolean res = false;
		String sqlString = "select * from permissions where username ='"+ username + "'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
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
	 * 删除权限
	 */
	public static void DeletePermissionsByUsername(String username) {
		String sqlString = "delete from permissions where username ='"+ username + "'";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
	}
	/*
	 *
	 *新增记录  
	 */
	public boolean insertPermissions(Permissions permissions) {
		String sqlString = "insert into permissions (username,czr,czsj,czrID,contentzzxx,nodezzxx,contentcjfg,nodecjfg,contentglzd,nodeglzd,wjgl,gztz,jhzj,gzdt,cwbb,sjbb,tjbb,zdxmsb,sgjfsy,gzfl,cwjd,postinformation,topscroll,learningcorner,njgl,njfb,jjyxfx,bbdz) values ('";
		sqlString += permissions.getUsername() + "','";
		sqlString += permissions.getCzr() + "','";
		sqlString += permissions.getCzsj()+ "','";
		sqlString += permissions.getCzrID() + "','";
		sqlString += permissions.getContentzzxx() + "','";
		sqlString += permissions.getNodezzxx()+ "','";
		sqlString += permissions.getContentcjfg() + "','";
		sqlString += permissions.getNodecjfg() + "','";
		sqlString += permissions.getContentglzd() + "','";
		sqlString += permissions.getNodeglzd() + "','";
		sqlString += permissions.getWjgl() + "','";
		sqlString += permissions.getGztz() + "','";
		sqlString += permissions.getJhzj() + "','";
		sqlString += permissions.getGzdt() + "','";
		sqlString += permissions.getCwbb() + "','";
		sqlString += permissions.getSjbb() + "','";
		sqlString += permissions.getTjbb() + "','";
		sqlString += permissions.getZdxmsb() + "','";
		sqlString += permissions.getSgjfsy() + "','";
		sqlString += permissions.getGzfl() + "','";
		sqlString += permissions.getCwjd() + "','";
		sqlString += permissions.getPostinformation() + "','";
		sqlString += permissions.getTopscroll()  + "','";
		sqlString += permissions.getLearningcorner()+ "','";
		sqlString += permissions.getNjgl() + "','";
		sqlString += permissions.getNjfb() + "','";
		sqlString += permissions.getJjyxfx() + "','";
		sqlString += permissions.getBbdz() + "')";
		
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
	 *修改记录  
	 */
	public boolean updatePermissions(Permissions permissions) {
		String sqlString = "update permissions set czr='";
		sqlString += permissions.getCzr()+ "',czrID='";
		sqlString += permissions.getCzrID() + "',czsj='";
		sqlString += permissions.getCzsj()+ "',contentzzxx='";
		sqlString += permissions.getContentzzxx() +"',nodezzxx='";
		sqlString += permissions.getNodezzxx()  +"',contentcjfg='";
		sqlString += permissions.getContentcjfg()  +"',nodecjfg='";
		sqlString += permissions.getNodecjfg()  +"',contentglzd='";
		sqlString += permissions.getContentglzd()  +"',nodeglzd='";
		sqlString += permissions.getNodeglzd()  +"',wjgl='";
		sqlString += permissions.getWjgl()  +"',gztz='";
		sqlString += permissions.getGztz()  +"',jhzj='";
		sqlString += permissions.getJhzj()  +"',gzdt='";
		sqlString += permissions.getGzdt()  +"',cwbb='";
		sqlString += permissions.getCwbb()  +"',sjbb='";
		sqlString += permissions.getSjbb()  +"',tjbb='";
		sqlString += permissions.getTjbb()  +"',zdxmsb='";
		sqlString += permissions.getZdxmsb()  +"',sgjfsy='";
		sqlString += permissions.getSgjfsy()  +"',gzfl='";
		sqlString += permissions.getGzfl()  +"',cwjd='";
		sqlString += permissions.getCwjd()  +"',postinformation='";
		sqlString += permissions.getPostinformation()  +"',topscroll='";
		sqlString += permissions.getTopscroll() +"',learningcorner='";
		sqlString += permissions.getLearningcorner() +"',njgl='";
		sqlString += permissions.getNjgl() +"',njfb='";
		sqlString += permissions.getNjfb() +"',jjyxfx='";
		sqlString += permissions.getJjyxfx() +"',bbdz='";
		sqlString += permissions.getBbdz() + "' where username='" + permissions.getUsername()+"'";
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
	 *查询是否有审核权限记录
	 */
	public boolean judgeCheckid() {
		 /* 保存符合条件的某页记录的集合链表 */
		boolean res = false;
		String sqlString = "select * from checkid";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
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
	public boolean insertCheckid(Checkid checkid) {
		String sqlString = "insert into checkid (czr,czsj,czrID,zdxmsbID,zdxmsbName,postInfID,postInfName) values ('";
		sqlString += checkid.getCzr() + "','";
		sqlString += checkid.getCzsj()+ "','";
		sqlString += checkid.getCzrID() + "','";
		sqlString += checkid.getZdxmsbID() + "','";
		sqlString += checkid.getZdxmsbName() + "','";
		sqlString += checkid.getPostInfID() + "','";
		sqlString += checkid.getPostInfName() + "')";
		
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
	 *修改记录  
	 */
	public boolean updateCheckid(Checkid checkid) {
		String sqlString = "update checkid set czr='";
		sqlString += checkid.getCzr()+ "',czrID='";
		sqlString += checkid.getCzrID() + "',czsj='";
		sqlString += checkid.getCzsj()+ "',zdxmsbID='";
		sqlString += checkid.getZdxmsbID() + "',zdxmsbName='";
		sqlString += checkid.getZdxmsbName() + "',postInfID='";
		sqlString += checkid.getPostInfID() + "',postInfName='";
		sqlString += checkid.getPostInfName() + "' where id=" + checkid.getId();
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
	 *查询一条审核权限
	 */
	public Checkid queryCheckid() {
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
				checkid.setPostInfID(rs.getString("postInfID"));
				checkid.setPostInfName(rs.getString("postInfName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return checkid;
	}
}
