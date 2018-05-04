package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.TjbbYxzbHdZxm;
import com.safety.entity.TjbbYxzbHd;
import com.safety.entity.TjbbYxzbHs;
import com.safety.entity.TjbbYxzbHsZd;
import com.safety.entity.TjbbYxzbHsZdZxm;
import com.safety.entity.TjbbYxzbHsZdhz;
import com.safety.entity.TjbbYxzbHsZdhzZxm;
import com.safety.entity.TjbbYxzbHsZxm;
import com.safety.entity.TjbbYxzbQy;
import com.safety.entity.TjbbYxzbQyZxm;
import com.safety.entity.TjbbYxzbSix;
import com.safety.entity.TjbbYxzbSixZxm;

public class BbsbTjbbDao {
	/*
	 *根据条件
	 *查询主要运行指标统计表航道记录
	 */
	public  ArrayList<TjbbYxzbHd> queryTjbbYxzbHdListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHd> tjbbYxzbHdList = new ArrayList<TjbbYxzbHd>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_hd  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_hd "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
				tjbbYxzbHd.setId(rs.getInt("id"));
				tjbbYxzbHd.setBt(rs.getString("bt"));
				tjbbYxzbHd.setSj(rs.getDate("sj"));
				tjbbYxzbHd.setCzr(rs.getString("czr"));
				tjbbYxzbHd.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHd.setCzrID(rs.getString("czrID"));
				tjbbYxzbHd.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHd.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbHd.setShr(rs.getString("shr"));
				tjbbYxzbHd.setShrID(rs.getString("shrID"));
				tjbbYxzbHd.setShyj(rs.getString("shyj"));
				tjbbYxzbHd.setShsj(rs.getDate("shsj"));
				tjbbYxzbHd.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHdList.add(tjbbYxzbHd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHdList;
	}
	/*
	 *根据条件
	 *查询主要运行指标统计表航道记录数
	 */
	public  int queryTjbbYxzbHdListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_hd";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_hd "+srbt;
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
	public  TjbbYxzbHd queryTjbbYxzbHdByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbHd tjbbYxzbHd = new TjbbYxzbHd();
		String sqlString = "select * from tjbb_yxzb_hd where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbHd.setId(rs.getInt("id"));
				tjbbYxzbHd.setBt(rs.getString("bt"));
				tjbbYxzbHd.setSj(rs.getDate("sj"));
				tjbbYxzbHd.setCzr(rs.getString("czr"));
				tjbbYxzbHd.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHd.setCzrID(rs.getString("czrID"));
				tjbbYxzbHd.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHd.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbHd.setShr(rs.getString("shr"));
				tjbbYxzbHd.setShrID(rs.getString("shrID"));
				tjbbYxzbHd.setShyj(rs.getString("shyj"));
				tjbbYxzbHd.setShsj(rs.getDate("shsj"));
				tjbbYxzbHd.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHd.setYxfx(rs.getString("yxfx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHd;
	}
	/*
	 *根据时间
	 *查询上月记录ID
	 */
	public  int queryTjbbYxzbHdByYM(String datebegin,String dateend,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		int id=0;
		String sqlString = "select * from tjbb_yxzb_hd where czrdw ='"+dw+"' and sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return id;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHdZxm> queryTjbbYxzbHdZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList = new ArrayList<TjbbYxzbHdZxm>();
		String sqlString = "select * from tjbb_yxzb_hd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
				tjbbYxzbHdZxm.setId(rs.getInt("id"));
				tjbbYxzbHdZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbHdZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbHdZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbHdZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbHdZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbHdZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbHdZxm.setZb6(rs.getString("zb6"));
				tjbbYxzbHdZxm.setZb7(rs.getString("zb7"));
				TjbbYxzbHdZxmList.add(tjbbYxzbHdZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHdZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHdZxm> queryYxzbHdZxmByLastMonth(ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbHdZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
					tjbbYxzbHdZxm.setZb1(rs.getString("zb2"));
					TjbbYxzbHdZxmList.add(tjbbYxzbHdZxm);
				}else{
					TjbbYxzbHdZxmList.get(i).setZb1(rs.getString("zb2"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHdZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHdZxm> queryYxzbHdZxmByLastMonth1(int xmid, int hs) {
		ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmListHistory = new ArrayList<TjbbYxzbHdZxm>();
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<hs&&rs.next(); i++){
				TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
				tjbbYxzbHdZxm.setZb4(rs.getString("zb4"));
				TjbbYxzbHdZxmListHistory.add(tjbbYxzbHdZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHdZxmListHistory;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHdZxm> queryYxzbHdZxmByLastYear(ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbHdZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbHdZxm tjbbYxzbHdZxm = new TjbbYxzbHdZxm();
					tjbbYxzbHdZxm.setZb5(rs.getString("zb4"));
					TjbbYxzbHdZxmList.add(tjbbYxzbHdZxm);
				}else{
					TjbbYxzbHdZxmList.get(i).setZb5(rs.getString("zb4"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHdZxmList;
	}
	/*
	 *
	 *新增主要运行指标统计表航道记录
	 */
	public int insertTjbbYxzbHd(TjbbYxzbHd tjbbYxzbHd) {
		String sqlString = "insert into tjbb_yxzb_hd (bt,sj,czr,czrdw,czrID,czsj,czrphone,shr,shrID,shyj,tjzt,yxfx) values('";
		sqlString += tjbbYxzbHd.getBt() + "','";
		sqlString += tjbbYxzbHd.getSj() + "','";
		sqlString += tjbbYxzbHd.getCzr() + "','";
		sqlString += tjbbYxzbHd.getCzrdw() + "','";
		sqlString += tjbbYxzbHd.getCzrID()+ "','";
		sqlString += tjbbYxzbHd.getCzsj()  + "','";
		sqlString += tjbbYxzbHd.getCzrphone()  + "','";
		sqlString += tjbbYxzbHd.getShr()  + "','";
		sqlString += tjbbYxzbHd.getShrID()  + "','";
		sqlString += tjbbYxzbHd.getShyj()  + "','";
		sqlString += tjbbYxzbHd.getTjzt()  + "','";
		sqlString += tjbbYxzbHd.getYxfx() + "')";
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
	 *新增主要运行指标统计表航道数据记录
	 */
	public boolean insertTjbbYxzbHdZxm(ArrayList<TjbbYxzbHdZxm> TjbbYxzbHdZxmList) {
		int len = TjbbYxzbHdZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_hd_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6,zb7) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbHdZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb6() + "','";
			sqlString += TjbbYxzbHdZxmList.get(i).getZb7() + "'),(";
		}
		sqlString += TjbbYxzbHdZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb6() + "','";
		sqlString += TjbbYxzbHdZxmList.get(len).getZb7() + "')";
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
	 *修改主要运行指标统计表航道记录
	 */
	public boolean updateTjbbYxzbHd(TjbbYxzbHd tjbbYxzbHd) {
		String sqlString = "update tjbb_yxzb_hd set sj='"; 
		sqlString += tjbbYxzbHd.getSj() + "',bt='";
		sqlString += tjbbYxzbHd.getBt() + "',czr='";
		sqlString += tjbbYxzbHd.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbHd.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbHd.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbHd.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbHd.getCzrdw() + "',czrphone='";
		sqlString += tjbbYxzbHd.getCzrphone() + "',shr='";
		sqlString += tjbbYxzbHd.getShr() + "',shrID='";
		sqlString += tjbbYxzbHd.getShrID() + "',shyj='";
		sqlString += tjbbYxzbHd.getShyj() + "',tjzt='";
		sqlString += tjbbYxzbHd.getTjzt() + "',yxfx='";
		sqlString += tjbbYxzbHd.getYxfx() + "' where id=" + tjbbYxzbHd.getId();
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

	/*根据记录编号删除统计情况统计表航道记录*/
	public int deleteTjbbYxzbHdById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hd where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbHdZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hd_zxm where xmid ="+xmid;
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
	 *根据ID
	 *修改运行执行表记录
	 */
	public boolean updateTjbbYxzbHdRet(String id) {
		String sqlString = "update tjbb_yxzb_hd set tjzt='3' where id=" + id;
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
	 *根据条件
	 *查询主要运行指标统计表六县记录
	 */
	public  ArrayList<TjbbYxzbSix> queryTjbbYxzbSixListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbSix> tjbbYxzbSixList = new ArrayList<TjbbYxzbSix>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_six  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_six "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
				tjbbYxzbSix.setId(rs.getInt("id"));
				tjbbYxzbSix.setBt(rs.getString("bt"));
				tjbbYxzbSix.setSj(rs.getDate("sj"));
				tjbbYxzbSix.setCzr(rs.getString("czr"));
				tjbbYxzbSix.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbSix.setCzrID(rs.getString("czrID"));
				tjbbYxzbSix.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbSix.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbSix.setShr(rs.getString("shr"));
				tjbbYxzbSix.setShrID(rs.getString("shrID"));
				tjbbYxzbSix.setShyj(rs.getString("shyj"));
				tjbbYxzbSix.setShsj(rs.getDate("shsj"));
				tjbbYxzbSix.setTjzt(rs.getString("tjzt"));
				tjbbYxzbSixList.add(tjbbYxzbSix);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbSixList;
	}
	/*
	 *根据条件
	 *查询主要运行指标统计表六县记录数
	 */
	public  int queryTjbbYxzbSixListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_six";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_six "+srbt;
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
	public  TjbbYxzbSix queryTjbbYxzbSixByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbSix tjbbYxzbSix = new TjbbYxzbSix();
		String sqlString = "select * from tjbb_yxzb_six where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbSix.setId(rs.getInt("id"));
				tjbbYxzbSix.setBt(rs.getString("bt"));
				tjbbYxzbSix.setSj(rs.getDate("sj"));
				tjbbYxzbSix.setCzr(rs.getString("czr"));
				tjbbYxzbSix.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbSix.setCzrID(rs.getString("czrID"));
				tjbbYxzbSix.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbSix.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbSix.setShr(rs.getString("shr"));
				tjbbYxzbSix.setShrID(rs.getString("shrID"));
				tjbbYxzbSix.setShyj(rs.getString("shyj"));
				tjbbYxzbSix.setShsj(rs.getDate("shsj"));
				tjbbYxzbSix.setTjzt(rs.getString("tjzt"));
				tjbbYxzbSix.setYxfx(rs.getString("yxfx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbSix;
	}
	/*
	 *根据时间
	 *查询上月记录ID
	 */
	public  int queryTjbbYxzbSixByYM(String datebegin,String dateend,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		int id=0;
		String sqlString = "select * from tjbb_yxzb_six where czrdw ='"+dw+"' and sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return id;
	}
	/*
	 *根据时间
	 *查询上月六县+运管是否上报记录
	 */
	public String queryTjbbYxzbSixByYMWH(String datebegin,String dateend) {
		 /* 保存符合条件的某页记录的集合链表 */
		String dw="";
		String sqlString = "select czrdw from tjbb_yxzb_six where  sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				dw = dw+ rs.getString("czrdw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return dw;
	}
	/*
	 *根据时间
	 *查询上月航道是否上报记录
	 */
	public boolean queryTjbbYxzbHdByYMWH(String datebegin,String dateend,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		boolean result=false;
		String sqlString = "select * from tjbb_yxzb_hd where czrdw='"+dw+"' and sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return result;
	}

	/*
	 *根据时间
	 *查询上月汽运是否上报记录
	 */
	public boolean queryTjbbYxzbQyByYMWH(String datebegin,String dateend,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		boolean result=false;
		String sqlString = "select * from tjbb_yxzb_qy where czrdw='"+dw+"' and sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return result;
	}
	/*
	 *根据时间
	 *查询上月海事是否上报记录
	 */
	public boolean queryTjbbYxzbHsByYMWH(int year,int month,String dw) {
		boolean result=false;
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hs_zd where czrdw ='"+dw+"' and year ="+year+" and month ="+month+" and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return result;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbSixZxm> queryTjbbYxzbSixZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList = new ArrayList<TjbbYxzbSixZxm>();
		String sqlString = "select * from tjbb_yxzb_six_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
				tjbbYxzbSixZxm.setId(rs.getInt("id"));
				tjbbYxzbSixZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbSixZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbSixZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbSixZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbSixZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbSixZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbSixZxm.setZb6(rs.getString("zb6"));
				TjbbYxzbSixZxmList.add(tjbbYxzbSixZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbSixZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 *
	 */
	public  ArrayList<TjbbYxzbSixZxm> queryYxzbSixZxmByLastMonth(ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_six_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbSixZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
					tjbbYxzbSixZxm.setZb1(rs.getString("zb2"));
					TjbbYxzbSixZxmList.add(tjbbYxzbSixZxm);
				}else{
					TjbbYxzbSixZxmList.get(i).setZb1(rs.getString("zb2"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbSixZxmList;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 *
	 */
	public  ArrayList<TjbbYxzbSixZxm> queryYxzbSixZxmByLastMonth1(int xmid, int hs) {
		ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmListHistory = new ArrayList<TjbbYxzbSixZxm>();
		/* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_six_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<hs&&rs.next(); i++){
				TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
				tjbbYxzbSixZxm.setZb4(rs.getString("zb4"));
				TjbbYxzbSixZxmListHistory.add(tjbbYxzbSixZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbSixZxmListHistory;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbSixZxm> queryYxzbSixZxmByLastYear(ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_six_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbSixZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbSixZxm tjbbYxzbSixZxm = new TjbbYxzbSixZxm();
					tjbbYxzbSixZxm.setZb5(rs.getString("zb4"));
					TjbbYxzbSixZxmList.add(tjbbYxzbSixZxm);
				}else{
					TjbbYxzbSixZxmList.get(i).setZb5(rs.getString("zb4"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbSixZxmList;
	}
	/*
	 *
	 *新增主要运行指标统计表六县记录
	 */
	public int insertTjbbYxzbSix(TjbbYxzbSix tjbbYxzbSix) {
		String sqlString = "insert into tjbb_yxzb_six (bt,sj,czr,czrdw,czrID,czsj,czrphone,shr,shrID,shyj,tjzt,yxfx) values('";
		sqlString += tjbbYxzbSix.getBt() + "','";
		sqlString += tjbbYxzbSix.getSj() + "','";
		sqlString += tjbbYxzbSix.getCzr() + "','";
		sqlString += tjbbYxzbSix.getCzrdw() + "','";
		sqlString += tjbbYxzbSix.getCzrID()+ "','";
		sqlString += tjbbYxzbSix.getCzsj()  + "','";
		sqlString += tjbbYxzbSix.getCzrphone()  + "','";
		sqlString += tjbbYxzbSix.getShr()  + "','";
		sqlString += tjbbYxzbSix.getShrID()  + "','";
		sqlString += tjbbYxzbSix.getShyj()  + "','";
		sqlString += tjbbYxzbSix.getTjzt()  + "','";
		sqlString += tjbbYxzbSix.getYxfx() + "')";
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
	 *新增主要运行指标统计表六县数据记录
	 */
	public boolean insertTjbbYxzbSixZxm(ArrayList<TjbbYxzbSixZxm> TjbbYxzbSixZxmList) {
		int len = TjbbYxzbSixZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_six_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbSixZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbSixZxmList.get(i).getZb6() + "'),(";
		}
		sqlString += TjbbYxzbSixZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbSixZxmList.get(len).getZb6() + "')";
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
	 *修改主要运行指标统计表六县记录
	 */
	public boolean updateTjbbYxzbSix(TjbbYxzbSix tjbbYxzbSix) {
		String sqlString = "update tjbb_yxzb_six set sj='"; 
		sqlString += tjbbYxzbSix.getSj() + "',bt='";
		sqlString += tjbbYxzbSix.getBt() + "',czr='";
		sqlString += tjbbYxzbSix.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbSix.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbSix.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbSix.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbSix.getCzrdw() + "',czrphone='";
		sqlString += tjbbYxzbSix.getCzrphone() + "',shr='";
		sqlString += tjbbYxzbSix.getShr() + "',shrID='";
		sqlString += tjbbYxzbSix.getShrID() + "',shyj='";
		sqlString += tjbbYxzbSix.getShyj() + "',tjzt='";
		sqlString += tjbbYxzbSix.getTjzt() + "',yxfx='";
		sqlString += tjbbYxzbSix.getYxfx() + "' where id=" + tjbbYxzbSix.getId();
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

	/*根据记录编号删除统计情况统计表六县记录*/
	public int deleteTjbbYxzbSixById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_six where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbSixZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_six_zxm where xmid ="+xmid;
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
	 *根据ID
	 *修改运行执行表记录
	 */
	public boolean updateTjbbYxzbSixRet(String id) {
		String sqlString = "update tjbb_yxzb_six set tjzt='3' where id=" + id;
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
	 *根据条件
	 *查询客运出站运输量统计表（汽运集团）记录
	 */
	public  ArrayList<TjbbYxzbQy> queryTjbbYxzbQyListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbQy> tjbbYxzbQyList = new ArrayList<TjbbYxzbQy>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_qy  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_qy "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
				tjbbYxzbQy.setId(rs.getInt("id"));
				tjbbYxzbQy.setBt(rs.getString("bt"));
				tjbbYxzbQy.setSj(rs.getDate("sj"));
				tjbbYxzbQy.setCzr(rs.getString("czr"));
				tjbbYxzbQy.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbQy.setCzrID(rs.getString("czrID"));
				tjbbYxzbQy.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbQy.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbQy.setShr(rs.getString("shr"));
				tjbbYxzbQy.setShrID(rs.getString("shrID"));
				tjbbYxzbQy.setShyj(rs.getString("shyj"));
				tjbbYxzbQy.setShsj(rs.getDate("shsj"));
				tjbbYxzbQy.setTjzt(rs.getString("tjzt"));
				tjbbYxzbQyList.add(tjbbYxzbQy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbQyList;
	}
	/*
	 *根据条件
	 *查询客运出站运输量统计表（汽运集团）记录数
	 */
	public  int queryTjbbYxzbQyListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_qy";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_qy "+srbt;
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
	public  TjbbYxzbQy queryTjbbYxzbQyByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbQy tjbbYxzbQy = new TjbbYxzbQy();
		String sqlString = "select * from tjbb_yxzb_qy where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbQy.setId(rs.getInt("id"));
				tjbbYxzbQy.setBt(rs.getString("bt"));
				tjbbYxzbQy.setSj(rs.getDate("sj"));
				tjbbYxzbQy.setCzr(rs.getString("czr"));
				tjbbYxzbQy.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbQy.setCzrID(rs.getString("czrID"));
				tjbbYxzbQy.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbQy.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbQy.setShr(rs.getString("shr"));
				tjbbYxzbQy.setShrID(rs.getString("shrID"));
				tjbbYxzbQy.setShyj(rs.getString("shyj"));
				tjbbYxzbQy.setShsj(rs.getDate("shsj"));
				tjbbYxzbQy.setTjzt(rs.getString("tjzt"));
				tjbbYxzbQy.setYxfx(rs.getString("yxfx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbQy;
	}
	/*
	 *根据时间
	 *查询上月记录ID
	 */
	public  int queryTjbbYxzbQyByYM(String datebegin,String dateend,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		int id=0;
		String sqlString = "select * from tjbb_yxzb_qy where czrdw ='"+dw+"' and sj>='"+datebegin+"' and sj<='"+dateend+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return id;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbQyZxm> queryTjbbYxzbQyZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
		String sqlString = "select * from tjbb_yxzb_qy_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
				tjbbYxzbQyZxm.setId(rs.getInt("id"));
				tjbbYxzbQyZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbQyZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbQyZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbQyZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbQyZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbQyZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbQyZxm.setZb6(rs.getString("zb6"));
				TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbQyZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbQyZxm> queryYxzbQyZxmByLastMonth(ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_qy_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbQyZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
					tjbbYxzbQyZxm.setZb1(rs.getString("zb2"));
					TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
				}else{
					TjbbYxzbQyZxmList.get(i).setZb1(rs.getString("zb2"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbQyZxmList;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbQyZxm> queryYxzbQyZxmByLastMonth1(int xmid, int hs) {
		ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmListHistory = new ArrayList<TjbbYxzbQyZxm>();
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_qy_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			for(int i=0; i<hs&&rs.next(); i++){
				TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
				tjbbYxzbQyZxm.setZb4(rs.getString("zb4"));
				TjbbYxzbQyZxmListHistory.add(tjbbYxzbQyZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbQyZxmListHistory;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbQyZxm> queryYxzbQyZxmByLastYear(ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_qy_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbQyZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbQyZxm tjbbYxzbQyZxm = new TjbbYxzbQyZxm();
					tjbbYxzbQyZxm.setZb5(rs.getString("zb4"));
					TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
				}else{
					TjbbYxzbQyZxmList.get(i).setZb5(rs.getString("zb4"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbQyZxmList;
	}
	/*
	 *
	 *新增客运出站运输量统计表（汽运集团）记录
	 */
	public int insertTjbbYxzbQy(TjbbYxzbQy tjbbYxzbQy) {
		String sqlString = "insert into tjbb_yxzb_qy (bt,sj,czr,czrdw,czrID,czsj,czrphone,shr,shrID,shyj,tjzt,yxfx) values('";
		sqlString += tjbbYxzbQy.getBt() + "','";
		sqlString += tjbbYxzbQy.getSj() + "','";
		sqlString += tjbbYxzbQy.getCzr() + "','";
		sqlString += tjbbYxzbQy.getCzrdw() + "','";
		sqlString += tjbbYxzbQy.getCzrID()+ "','";
		sqlString += tjbbYxzbQy.getCzsj()  + "','";
		sqlString += tjbbYxzbQy.getCzrphone()  + "','";
		sqlString += tjbbYxzbQy.getShr()  + "','";
		sqlString += tjbbYxzbQy.getShrID()  + "','";
		sqlString += tjbbYxzbQy.getShyj()  + "','";
		sqlString += tjbbYxzbQy.getTjzt()  + "','";
		sqlString += tjbbYxzbQy.getYxfx() + "')";
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
	 *新增客运出站运输量统计表（汽运集团）数据记录
	 */
	public boolean insertTjbbYxzbQyZxm(ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList) {
		int len = TjbbYxzbQyZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_qy_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbQyZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbQyZxmList.get(i).getZb6() + "'),(";
		}
		sqlString += TjbbYxzbQyZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbQyZxmList.get(len).getZb6() + "')";
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
	 *修改客运出站运输量统计表（汽运集团）记录
	 */
	public boolean updateTjbbYxzbQy(TjbbYxzbQy tjbbYxzbQy) {
		String sqlString = "update tjbb_yxzb_qy set sj='"; 
		sqlString += tjbbYxzbQy.getSj() + "',bt='";
		sqlString += tjbbYxzbQy.getBt() + "',czr='";
		sqlString += tjbbYxzbQy.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbQy.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbQy.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbQy.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbQy.getCzrdw() + "',czrphone='";
		sqlString += tjbbYxzbQy.getCzrphone() + "',shr='";
		sqlString += tjbbYxzbQy.getShr() + "',shrID='";
		sqlString += tjbbYxzbQy.getShrID() + "',shyj='";
		sqlString += tjbbYxzbQy.getShyj() + "',tjzt='";
		sqlString += tjbbYxzbQy.getTjzt() + "',yxfx='";
		sqlString += tjbbYxzbQy.getYxfx() + "' where id=" + tjbbYxzbQy.getId();
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

	/*根据记录编号删除统计情况统计表六县记录*/
	public int deleteTjbbYxzbQyById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_qy where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbQyZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_qy_zxm where xmid ="+xmid;
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
	 *根据ID
	 *修改运行执行表记录
	 */
	public boolean updateTjbbYxzbQyRet(String id) {
		String sqlString = "update tjbb_yxzb_qy set tjzt='3' where id=" + id;
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
	 *根据条件
	 *查询海事签证月度同比表（海事局）记录
	 */
	public  ArrayList<TjbbYxzbHs> queryTjbbYxzbHsListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHs> tjbbYxzbHsList = new ArrayList<TjbbYxzbHs>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_hs  order by year,month desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_hs "+srbt +" order by year,month desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
				tjbbYxzbHs.setId(rs.getInt("id"));
				tjbbYxzbHs.setBt(rs.getString("bt"));
				tjbbYxzbHs.setYear(rs.getInt("year"));
				tjbbYxzbHs.setMonth(rs.getInt("month"));
				tjbbYxzbHs.setCzr(rs.getString("czr"));
				tjbbYxzbHs.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHs.setCzrID(rs.getString("czrID"));
				tjbbYxzbHs.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHs.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbHs.setShr(rs.getString("shr"));
				tjbbYxzbHs.setShrID(rs.getString("shrID"));
				tjbbYxzbHs.setShyj(rs.getString("shyj"));
				tjbbYxzbHs.setShsj(rs.getDate("shsj"));
				tjbbYxzbHs.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHsList.add(tjbbYxzbHs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsList;
	}
	/*
	 *根据条件
	 *查询海事签证月度同比表（海事局）记录数
	 */
	public  int queryTjbbYxzbHsListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_hs";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_hs "+srbt;
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
	public  TjbbYxzbHs queryTjbbYxzbHsByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbHs tjbbYxzbHs = new TjbbYxzbHs();
		String sqlString = "select * from tjbb_yxzb_hs where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbHs.setId(rs.getInt("id"));
				tjbbYxzbHs.setBt(rs.getString("bt"));
				tjbbYxzbHs.setYear(rs.getInt("year"));
				tjbbYxzbHs.setMonth(rs.getInt("month"));
				tjbbYxzbHs.setCzr(rs.getString("czr"));
				tjbbYxzbHs.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHs.setCzrID(rs.getString("czrID"));
				tjbbYxzbHs.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHs.setCzrphone(rs.getString("czrphone"));
				tjbbYxzbHs.setShr(rs.getString("shr"));
				tjbbYxzbHs.setShrID(rs.getString("shrID"));
				tjbbYxzbHs.setShyj(rs.getString("shyj"));
				tjbbYxzbHs.setShsj(rs.getDate("shsj"));
				tjbbYxzbHs.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHs.setYxfx(rs.getString("yxfx"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHs;
	}
	/*
	 *根据时间
	 *查询上年记录ID
	 */
	public  int queryTjbbYxzbHsByYM(int year,int month,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		int id=0;
		String sqlString = "select * from tjbb_yxzb_hs where czrdw ='"+dw+"' and year="+year+" and month="+month+" and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return id;
	}
	/*
	 *根据时间
	 *查询汇总记录
	 */
	public  TjbbYxzbHsZdhz queryTjbbYxzbHsZdhzByYM(int year,int month,String dw) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
		String sqlString = "select * from tjbb_yxzb_hs_zdhz where czrdw ='"+dw+"' and year="+year+" and month="+month;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbHsZdhz.setId(rs.getInt("id"));
				tjbbYxzbHsZdhz.setZdgs(rs.getInt("zdgs"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsZdhz;
	}
	
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZxm> queryTjbbYxzbHsZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList = new ArrayList<TjbbYxzbHsZxm>();
		String sqlString = "select * from tjbb_yxzb_hs_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZxm tjbbYxzbHsZxm = new TjbbYxzbHsZxm();
				tjbbYxzbHsZxm.setId(rs.getInt("id"));
				tjbbYxzbHsZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbHsZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbHsZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbHsZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbHsZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbHsZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbHsZxm.setZb6(rs.getString("zb6"));
				TjbbYxzbHsZxmList.add(tjbbYxzbHsZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZxm> queryYxzbHsZxmByHZ(ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList,TjbbYxzbHsZdhz tjbbYxzbHsZdhz, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hs_zdhz_zxm where xmid ="+tjbbYxzbHsZdhz.getId()+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbHsZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbHsZxm tjbbYxzbHsZxm = new TjbbYxzbHsZxm();
					tjbbYxzbHsZxm.setZb1(tjbbYxzbHsZdhz.getZdgs()+"");
					tjbbYxzbHsZxm.setZb2(rs.getString("zb1"));
					tjbbYxzbHsZxm.setZb3(rs.getString("zb6"));
					TjbbYxzbHsZxmList.add(tjbbYxzbHsZxm);
				}else{
					TjbbYxzbHsZxmList.get(i).setZb1(tjbbYxzbHsZdhz.getZdgs()+"");
					TjbbYxzbHsZxmList.get(i).setZb2(rs.getString("zb1"));
					TjbbYxzbHsZxmList.get(i).setZb3(rs.getString("zb6"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZxmList;
	}

	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZxm> queryYxzbHsZxmByLastYear(ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList,int xmid, int hs) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hs_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			boolean flag = false;
			if(TjbbYxzbHsZxmList.size()==0)flag = true;
			for(int i=0; i<hs&&rs.next(); i++){
				if(flag){
					TjbbYxzbHsZxm tjbbYxzbHsZxm = new TjbbYxzbHsZxm();
					tjbbYxzbHsZxm.setZb5(rs.getString("zb4"));
					TjbbYxzbHsZxmList.add(tjbbYxzbHsZxm);
				}else{
					TjbbYxzbHsZxmList.get(i).setZb5(rs.getString("zb4"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZxmList;
	}
	/*
	 *
	 *新增海事签证月度同比表（海事局）记录
	 */
	public int insertTjbbYxzbHs(TjbbYxzbHs tjbbYxzbHs) {
		String sqlString = "insert into tjbb_yxzb_hs (bt,year,month,czr,czrdw,czrID,czsj,czrphone,shr,shrID,shyj,tjzt,yxfx) values('";
		sqlString += tjbbYxzbHs.getBt() + "',";
		sqlString += tjbbYxzbHs.getYear()+ ",";
		sqlString += tjbbYxzbHs.getMonth() + ",'";
		sqlString += tjbbYxzbHs.getCzr() + "','";
		sqlString += tjbbYxzbHs.getCzrdw() + "','";
		sqlString += tjbbYxzbHs.getCzrID()+ "','";
		sqlString += tjbbYxzbHs.getCzsj()  + "','";
		sqlString += tjbbYxzbHs.getCzrphone()  + "','";
		sqlString += tjbbYxzbHs.getShr()  + "','";
		sqlString += tjbbYxzbHs.getShrID()  + "','";
		sqlString += tjbbYxzbHs.getShyj()  + "','";
		sqlString += tjbbYxzbHs.getTjzt()  + "','";
		sqlString += tjbbYxzbHs.getYxfx() + "')";
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
	 *新增海事签证月度同比表（海事局）数据记录
	 */
	public boolean insertTjbbYxzbHsZxm(ArrayList<TjbbYxzbHsZxm> TjbbYxzbHsZxmList) {
		int len = TjbbYxzbHsZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_hs_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbHsZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbHsZxmList.get(i).getZb6() + "'),(";
		}
		sqlString += TjbbYxzbHsZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbHsZxmList.get(len).getZb6() + "')";
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
	 *修改海事签证月度同比表（海事局）记录
	 */
	public boolean updateTjbbYxzbHs(TjbbYxzbHs tjbbYxzbHs) {
		String sqlString = "update tjbb_yxzb_hs set year="; 
		sqlString += tjbbYxzbHs.getYear() + ",month=";
		sqlString += tjbbYxzbHs.getMonth() + ",bt='";
		sqlString += tjbbYxzbHs.getBt() + "',czr='";
		sqlString += tjbbYxzbHs.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbHs.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbHs.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbHs.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbHs.getCzrdw() + "',czrphone='";
		sqlString += tjbbYxzbHs.getCzrphone() + "',shr='";
		sqlString += tjbbYxzbHs.getShr() + "',shrID='";
		sqlString += tjbbYxzbHs.getShrID() + "',shyj='";
		sqlString += tjbbYxzbHs.getShyj() + "',tjzt='";
		sqlString += tjbbYxzbHs.getTjzt() + "',yxfx='";
		sqlString += tjbbYxzbHs.getYxfx() + "' where id=" + tjbbYxzbHs.getId();
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

	/*根据记录编号删除统计情况统计表六县记录*/
	public int deleteTjbbYxzbHsById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbHsZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs_zxm where xmid ="+xmid;
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
	 *根据ID
	 *修改海事签证月度表记录
	 */
	public boolean updateTjbbYxzbHsRet(String id) {
		String sqlString = "update tjbb_yxzb_hs set tjzt='3' where id=" + id;
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
	 *根据条件
	 *查询海事签证——站点签证（海事局）记录
	 */
	public  ArrayList<TjbbYxzbHsZd> queryTjbbYxzbHsZdListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHsZd> tjbbYxzbHsZdList = new ArrayList<TjbbYxzbHsZd>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_hs_zd  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_hs_zd "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
				tjbbYxzbHsZd.setId(rs.getInt("id"));
				tjbbYxzbHsZd.setBt(rs.getString("bt"));
				tjbbYxzbHsZd.setYear(rs.getInt("year"));
				tjbbYxzbHsZd.setMonth(rs.getInt("month"));
				tjbbYxzbHsZd.setCzr(rs.getString("czr"));
				tjbbYxzbHsZd.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHsZd.setCzrID(rs.getString("czrID"));
				tjbbYxzbHsZd.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHsZd.setLxr(rs.getString("lxr"));
				tjbbYxzbHsZd.setLxrdh(rs.getString("lxrdh"));
				tjbbYxzbHsZd.setShr(rs.getString("shr"));
				tjbbYxzbHsZd.setShrID(rs.getString("shrID"));
				tjbbYxzbHsZd.setShyj(rs.getString("shyj"));
				tjbbYxzbHsZd.setShsj(rs.getDate("shsj"));
				tjbbYxzbHsZd.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHsZd.setZdmc(rs.getString("zdmc"));
				tjbbYxzbHsZdList.add(tjbbYxzbHsZd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsZdList;
	}
	/*
	 *根据条件
	 *查询海事签证——站点签证（海事局）记录数
	 */
	public  int queryTjbbYxzbHsZdListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_hs_zd";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_hs_zd "+srbt;
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
	public  TjbbYxzbHsZd queryTjbbYxzbHsZdByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbHsZd tjbbYxzbHsZd = new TjbbYxzbHsZd();
		String sqlString = "select * from tjbb_yxzb_hs_zd where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbHsZd.setId(rs.getInt("id"));
				tjbbYxzbHsZd.setBt(rs.getString("bt"));
				tjbbYxzbHsZd.setYear(rs.getInt("year"));
				tjbbYxzbHsZd.setMonth(rs.getInt("month"));
				tjbbYxzbHsZd.setCzr(rs.getString("czr"));
				tjbbYxzbHsZd.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHsZd.setCzrID(rs.getString("czrID"));
				tjbbYxzbHsZd.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHsZd.setLxr(rs.getString("lxr"));
				tjbbYxzbHsZd.setLxrdh(rs.getString("lxrdh"));
				tjbbYxzbHsZd.setShr(rs.getString("shr"));
				tjbbYxzbHsZd.setShrID(rs.getString("shrID"));
				tjbbYxzbHsZd.setShyj(rs.getString("shyj"));
				tjbbYxzbHsZd.setShsj(rs.getDate("shsj"));
				tjbbYxzbHsZd.setTjzt(rs.getString("tjzt"));
				tjbbYxzbHsZd.setZdmc(rs.getString("zdmc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsZd;
	}
	/*
	 *根据时间
	 *查询上月记录ID
	 */
	public  int queryTjbbYxzbHsZdByYM(int year,int month,String dw,String zdmc) {
		 /* 保存符合条件的某页记录的集合链表 */
		int id=0;
		String sqlString = "select id from tjbb_yxzb_hs_zd where czrdw ='"+dw+"' and year ="+year+" and month ="+month+" and zdmc ='"+zdmc+"' and tjzt='1'";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return id;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZdZxm> queryTjbbYxzbHsZdZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
		String sqlString = "select * from tjbb_yxzb_hs_zd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
				tjbbYxzbHsZdZxm.setId(rs.getInt("id"));
				tjbbYxzbHsZdZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbHsZdZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbHsZdZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbHsZdZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbHsZdZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbHsZdZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbHsZdZxm.setZb6(rs.getString("zb6"));
				tjbbYxzbHsZdZxm.setZb7(rs.getString("zb7"));
				tjbbYxzbHsZdZxm.setZb8(rs.getString("zb8"));
				tjbbYxzbHsZdZxm.setZb9(rs.getString("zb9"));
				tjbbYxzbHsZdZxm.setZb10(rs.getString("zb10"));
				tjbbYxzbHsZdZxm.setZb11(rs.getString("zb11"));
				TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZdZxmList;
	}

	/*
	 *根据xmid上月
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZdZxm> queryYxzbHsZdZxmByLastMonth(ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList,int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from tjbb_yxzb_hs_zd_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = new TjbbYxzbHsZdZxm();
				tjbbYxzbHsZdZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbHsZdZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbHsZdZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbHsZdZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbHsZdZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbHsZdZxm.setZb6(rs.getString("zb6"));
				tjbbYxzbHsZdZxm.setZb7(rs.getString("zb7"));
				tjbbYxzbHsZdZxm.setZb8(rs.getString("zb8"));
				tjbbYxzbHsZdZxm.setZb9(rs.getString("zb9"));
				tjbbYxzbHsZdZxm.setZb10(rs.getString("zb10"));
				tjbbYxzbHsZdZxm.setZb11(rs.getString("zb11"));
				TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZdZxmList;
	}
	/*
	 *
	 *新增海事站点签证数据记录
	 */
	public int insertTjbbYxzbHsZd(TjbbYxzbHsZd tjbbYxzbHsZd) {
		String sqlString = "insert into tjbb_yxzb_hs_zd (bt,year,month,czr,czrdw,czrID,czsj,lxr,lxrdh,tjzt,zdmc) values('";
		sqlString += tjbbYxzbHsZd.getBt() + "',";
		sqlString += tjbbYxzbHsZd.getYear() + ",";
		sqlString += tjbbYxzbHsZd.getMonth() + ",'";
		sqlString += tjbbYxzbHsZd.getCzr() + "','";
		sqlString += tjbbYxzbHsZd.getCzrdw() + "','";
		sqlString += tjbbYxzbHsZd.getCzrID()+ "','";
		sqlString += tjbbYxzbHsZd.getCzsj()  + "','";
		sqlString += tjbbYxzbHsZd.getLxr()+ "','";
		sqlString += tjbbYxzbHsZd.getLxrdh() + "','";
		sqlString += tjbbYxzbHsZd.getTjzt()  + "','";
		sqlString += tjbbYxzbHsZd.getZdmc() + "')";
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
	 *新增海事站点签证数据记录
	 */
	public boolean insertTjbbYxzbHsZdZxm(ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList) {
		int len = TjbbYxzbHsZdZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_hs_zd_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6,zb7,zb8,zb9,zb10,zb11) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbHsZdZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb6() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb7() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb8() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb9() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb10() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb11() + "'),(";
		}
		sqlString += TjbbYxzbHsZdZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb6() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb7() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb8() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb9() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb10() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb11() + "')";
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
	 *修改海事站点签证数据记录
	 */
	public boolean updateTjbbYxzbHsZd(TjbbYxzbHsZd tjbbYxzbHsZd) {
		String sqlString = "update tjbb_yxzb_hs_zd set year="; 
		sqlString += tjbbYxzbHsZd.getYear()  + ",month=";
		sqlString += tjbbYxzbHsZd.getMonth() + ",bt='";
		sqlString += tjbbYxzbHsZd.getBt() + "',czr='";
		sqlString += tjbbYxzbHsZd.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbHsZd.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbHsZd.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbHsZd.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbHsZd.getCzrdw() + "',lxr='";
		sqlString += tjbbYxzbHsZd.getLxr()+ "',lxrdh='";
		sqlString += tjbbYxzbHsZd.getLxrdh() + "',tjzt='";
		sqlString += tjbbYxzbHsZd.getTjzt() + "',zdmc='";
		sqlString += tjbbYxzbHsZd.getZdmc() + "' where id=" + tjbbYxzbHsZd.getId();
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

	/*根据记录编号删除海事站点签证数据记录*/
	public int deleteTjbbYxzbHsZdById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs_zd where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbHsZdZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs_zd_zxm where xmid ="+xmid;
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
	 *根据ID
	 *修改海事站点签证数据记录
	 */
	public boolean updateTjbbYxzbHsZdRet(String id) {
		String sqlString = "update tjbb_yxzb_hs_zd set tjzt='3' where id=" + id;
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
	 *根据条件
	 *查询海事签证——站点签证（汇总）记录
	 */
	public  ArrayList<TjbbYxzbHsZdhz> queryTjbbYxzbHsZdhzListByBt(String srbt ,int begin, int pageSize) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHsZdhz> tjbbYxzbHsZdhzList = new ArrayList<TjbbYxzbHsZdhz>();
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select * from tjbb_yxzb_hs_zdhz  order by id desc limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from tjbb_yxzb_hs_zdhz "+srbt +" order by id desc limit "+begin+","+pageSize;
		}
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
				tjbbYxzbHsZdhz.setId(rs.getInt("id"));
				tjbbYxzbHsZdhz.setYear(rs.getInt("year"));
				tjbbYxzbHsZdhz.setMonth(rs.getInt("month"));
				tjbbYxzbHsZdhz.setCzr(rs.getString("czr"));
				tjbbYxzbHsZdhz.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHsZdhz.setCzrID(rs.getString("czrID"));
				tjbbYxzbHsZdhz.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHsZdhz.setZdgs(rs.getInt("zdgs"));
				tjbbYxzbHsZdhzList.add(tjbbYxzbHsZdhz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsZdhzList;
	}
	/*
	 *根据条件
	 *查询海事签证——站点签证（汇总）记录数
	 */
	public  int queryTjbbYxzbHsZdhzListByBtCount(String srbt) {
		 /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "";
		if("".equals(srbt)||srbt==null){
			sqlString = "select count(*) from tjbb_yxzb_hs_zdhz";
		}else{
			sqlString = "select count(*) from tjbb_yxzb_hs_zdhz "+srbt;
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
	public  TjbbYxzbHsZdhz queryTjbbYxzbHsZdhzByID(int id) {
		 /* 保存符合条件的某页记录的集合链表 */
		TjbbYxzbHsZdhz tjbbYxzbHsZdhz = new TjbbYxzbHsZdhz();
		String sqlString = "select * from tjbb_yxzb_hs_zdhz where id ="+id;
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if (rs.next()) {
				tjbbYxzbHsZdhz.setId(rs.getInt("id"));
				tjbbYxzbHsZdhz.setYear(rs.getInt("year"));
				tjbbYxzbHsZdhz.setMonth(rs.getInt("month"));
				tjbbYxzbHsZdhz.setCzr(rs.getString("czr"));
				tjbbYxzbHsZdhz.setCzrdw(rs.getString("czrdw"));
				tjbbYxzbHsZdhz.setCzrID(rs.getString("czrID"));
				tjbbYxzbHsZdhz.setCzsj(rs.getTimestamp("czsj"));
				tjbbYxzbHsZdhz.setZdgs(rs.getInt("zdgs"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return tjbbYxzbHsZdhz;
	}
	/*
	 *根据xmid
	 *查询子表内容
	 */
	public  ArrayList<TjbbYxzbHsZdhzZxm> queryTjbbYxzbHsZdhzZxmByID(int xmid) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<TjbbYxzbHsZdhzZxm> TjbbYxzbHsZdhzZxmList = new ArrayList<TjbbYxzbHsZdhzZxm>();
		String sqlString = "select * from tjbb_yxzb_hs_zdhz_zxm where xmid ="+xmid+" order by id";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				TjbbYxzbHsZdhzZxm tjbbYxzbHsZdhzZxm = new TjbbYxzbHsZdhzZxm();
				tjbbYxzbHsZdhzZxm.setId(rs.getInt("id"));
				tjbbYxzbHsZdhzZxm.setXmid(rs.getInt("xmid"));
				tjbbYxzbHsZdhzZxm.setZb1(rs.getString("zb1"));
				tjbbYxzbHsZdhzZxm.setZb2(rs.getString("zb2"));
				tjbbYxzbHsZdhzZxm.setZb3(rs.getString("zb3"));
				tjbbYxzbHsZdhzZxm.setZb4(rs.getString("zb4"));
				tjbbYxzbHsZdhzZxm.setZb5(rs.getString("zb5"));
				tjbbYxzbHsZdhzZxm.setZb6(rs.getString("zb6"));
				tjbbYxzbHsZdhzZxm.setZb7(rs.getString("zb7"));
				tjbbYxzbHsZdhzZxm.setZb8(rs.getString("zb8"));
				tjbbYxzbHsZdhzZxm.setZb9(rs.getString("zb9"));
				tjbbYxzbHsZdhzZxm.setZb10(rs.getString("zb10"));
				TjbbYxzbHsZdhzZxmList.add(tjbbYxzbHsZdhzZxm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return TjbbYxzbHsZdhzZxmList;
	}
	/*根据记录编号删除海事站点签证数据记录*/
	public int deleteTjbbYxzbHsZdhzById(int id) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs_zdhz where id=" + id;
		DB db = new DB();
		try {
			result = db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*根据记录编号删除子表数据*/
	public int deleteTjbbYxzbHsZdhzZxmByID(int xmid) {
		int result = 0;
		String sqlString = "delete from tjbb_yxzb_hs_zdhz_zxm where xmid ="+xmid;
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
	 *根据年月、单位，查询符合条件的
	 *海事签证——站点签证记录
	 */
	public  ArrayList<Integer> queryHsZdHzIdListByYM(String dw ,int year, int month) {
		 /* 保存符合条件的某页记录的集合链表 */
		ArrayList<Integer> idList = new ArrayList<Integer>();
		String sqlString = "select id from tjbb_yxzb_hs_zd where czrdw ='"+dw+"' and year ="+year+" and month ="+month+" and tjzt='1' order by id desc ";
		/* 调用数据层进行查询 */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				idList.add(rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return idList;
	}
	/*
	 *根据ID
	 *修改海事站点签证数据汇总记录
	 */
	public boolean updateTjbbYxzbHsZdhz(TjbbYxzbHsZdhz tjbbYxzbHsZdhz) {
		String sqlString = "update tjbb_yxzb_hs_zdhz set year="; 
		sqlString += tjbbYxzbHsZdhz.getYear()  + ",month=";
		sqlString += tjbbYxzbHsZdhz.getMonth() + ",czr='";
		sqlString += tjbbYxzbHsZdhz.getCzr() + "',czrdw='";
		sqlString += tjbbYxzbHsZdhz.getCzrdw() + "',czrID='";
		sqlString += tjbbYxzbHsZdhz.getCzrID() + "',czsj='";
		sqlString += tjbbYxzbHsZdhz.getCzsj() + "',czrdw='";
		sqlString += tjbbYxzbHsZdhz.getCzrdw()+ "',zdgs=";
		sqlString += tjbbYxzbHsZdhz.getZdgs() + " where id=" + tjbbYxzbHsZdhz.getId();
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
	 *新增海事站点签证数据汇总记录
	 */
	public int insertTjbbYxzbHsZdhz(TjbbYxzbHsZdhz tjbbYxzbHsZdhz) {
		String sqlString = "insert into tjbb_yxzb_hs_zdhz (year,month,czr,czrdw,czrID,czsj,zdgs) values(";
		sqlString += tjbbYxzbHsZdhz.getYear() + ",";
		sqlString += tjbbYxzbHsZdhz.getMonth() + ",'";
		sqlString += tjbbYxzbHsZdhz.getCzr() + "','";
		sqlString += tjbbYxzbHsZdhz.getCzrdw() + "','";
		sqlString += tjbbYxzbHsZdhz.getCzrID()+ "','";
		sqlString += tjbbYxzbHsZdhz.getCzsj()  + "',";
		sqlString += tjbbYxzbHsZdhz.getZdgs() + ")";
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
	 *新增海事站点签证数据汇总记录
	 */
	public boolean insertTjbbYxzbHsZdhzZxm(ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList) {
		int len = TjbbYxzbHsZdZxmList.size()-1;
		String sqlString = "insert into tjbb_yxzb_hs_zdhz_zxm (xmid,zb1,zb2,zb3,zb4,zb5,zb6,zb7,zb8,zb9,zb10) values(";
		for(int i=0; i<len; i++){
			sqlString += TjbbYxzbHsZdZxmList.get(i).getXmid() + ",'";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb1() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb2() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb3() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb4() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb5() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb6() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb7() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb8() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb9() + "','";
			sqlString += TjbbYxzbHsZdZxmList.get(i).getZb10() + "'),(";
		}
		sqlString += TjbbYxzbHsZdZxmList.get(len).getXmid() + ",'";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb1() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb2() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb3() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb4() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb5() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb6() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb7() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb8() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb9() + "','";
		sqlString += TjbbYxzbHsZdZxmList.get(len).getZb10() + "')";
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
}
