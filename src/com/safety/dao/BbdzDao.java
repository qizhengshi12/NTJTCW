package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.Bbdz;
import com.safety.entity.Bbdzmb;
import com.safety.entity.BbdzmbHb;
import com.safety.entity.BbdzmbJs;
import com.safety.entity.CglibBean;

public class BbdzDao {
	
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public  ArrayList<Bbdzmb> queryBbdzmbByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from Bbdzmb order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Bbdzmb "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Bbdzmb bbdzmb = new Bbdzmb();
				bbdzmb.setId(rs.getInt("id"));
				bbdzmb.setBt(rs.getString("bt"));
				bbdzmb.setLx(rs.getString("lx"));
				bbdzmb.setCzr(rs.getString("czr"));
				bbdzmb.setCzrID(rs.getString("czrID"));
				bbdzmb.setCzrdw(rs.getString("czrdw"));
				bbdzmb.setDzls(rs.getInt("dzls"));
				BbdzmbList.add(bbdzmb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return BbdzmbList;
	}
	/*
	 *��������
	 *��ѯ��¼��
	 */
	public  int queryBbdzmbByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from Bbdzmb order by id";
		}else{
			sqlString = "select count(*) from Bbdzmb "+srbt+"  order by id";
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
	 * ����1��ID������Ϣ
	 */
	public  Bbdzmb queryBbdzmbById(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		Bbdzmb bbdzmb = new Bbdzmb();
		String sqlString = "select * from Bbdzmb where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				bbdzmb.setId(rs.getInt("id"));
				bbdzmb.setBt(rs.getString("bt"));
				bbdzmb.setLx(rs.getString("lx"));
				bbdzmb.setCzr(rs.getString("czr"));
				bbdzmb.setCzrID(rs.getString("czrID"));
				bbdzmb.setCzrdw(rs.getString("czrdw"));
				bbdzmb.setDzls(rs.getInt("dzls"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return bbdzmb;
	}
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public ArrayList<BbdzmbHb> queryBbdzmbHbList(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<BbdzmbHb> BbdzmbHbList = new ArrayList<BbdzmbHb>();
		String sqlString = "select * from Bbdzmb_Hb where zbid ="+id+" order by row1,column1,row2,column2";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				BbdzmbHb bbdzmbHb = new BbdzmbHb();
				bbdzmbHb.setId(rs.getInt("id"));
				bbdzmbHb.setRow1(rs.getInt("row1"));
				bbdzmbHb.setColumn1(rs.getInt("column1"));
				bbdzmbHb.setRow2(rs.getInt("row2"));
				bbdzmbHb.setColumn2(rs.getInt("column2"));
				bbdzmbHb.setZbid(rs.getInt("zbid"));
				BbdzmbHbList.add(bbdzmbHb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return BbdzmbHbList;
	}
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public ArrayList<BbdzmbJs> queryBbdzmbJsList(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<BbdzmbJs> BbdzmbJsList = new ArrayList<BbdzmbJs>();
		String sqlString = "select * from Bbdzmb_Js where zbid ="+id+" order by row1,column1,row2,column2";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				BbdzmbJs bbdzmbJs = new BbdzmbJs();
				bbdzmbJs.setId(rs.getInt("id"));
				bbdzmbJs.setRow1(rs.getInt("row1"));
				bbdzmbJs.setColumn1(rs.getInt("column1"));
				bbdzmbJs.setRow2(rs.getInt("row2"));
				bbdzmbJs.setColumn2(rs.getInt("column2"));
				bbdzmbJs.setRow3(rs.getInt("row3"));
				bbdzmbJs.setColumn3(rs.getInt("column3"));
				bbdzmbJs.setZbid(rs.getInt("zbid"));
				bbdzmbJs.setLx(rs.getString("lx"));
				bbdzmbJs.setFh(rs.getString("fh"));
				BbdzmbJsList.add(bbdzmbJs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return BbdzmbJsList;
	}
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public ArrayList<CglibBean> queryBbdzmbDTByIDList(int zbid, int dzls) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<CglibBean> BbdzmbDTList = new ArrayList<CglibBean>();
		String sqlString = "select * from Bbdzmb_ls"+dzls+" where zbid ="+zbid;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			//��̬����ʵ��bean
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			propertyMap.put("id", Class.forName("java.lang.Integer"));
			propertyMap.put("zbid", Class.forName("java.lang.Integer"));
			for(int i=1; i<=dzls; i++){
				propertyMap.put("zb"+i, Class.forName("java.lang.String"));
			}
			while (rs.next()) {
				// ���ɶ�̬ Bean    
				CglibBean bean = new CglibBean(propertyMap);
				bean.setValue("id", rs.getInt("id"));
				bean.setValue("zbid", rs.getInt("zbid"));
				for(int j=1; j<=dzls; j++){
					bean.setValue("zb"+j, rs.getString("zb"+j));
				}
				BbdzmbDTList.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return BbdzmbDTList;
	}
	/*
	 *��������
	 *��ѯ�Ƿ����½���Ӧ�����ı� 
	 */
	public boolean queryBbdzmbLsByLx(int dzls,String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		boolean res = false;
		String sqlString = "select * from Bbdzmb_ls where num ="+dzls+" and lx='"+lx+"'";
		/* �������ݲ���в�ѯ */
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
	 *��������
	 *��ѯ�Ƿ����½���Ӧ�����ı� 
	 */
	public boolean queryBbdzmbLs(int dzls) {
		 /* �������������ĳҳ��¼�ļ������� */
		boolean res = false;
		String sqlString = "select * from Bbdzmb_ls where num ="+dzls;
		/* �������ݲ���в�ѯ */
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
	 *������¼
	 */
	public int insertBbdzmbLs(int dzls,String lx) {
		int result = 0;
		String sqlString = "insert into Bbdzmb_ls (num,lx) values("+dzls+",'"+lx+"')";
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

	
	/*��������������*/
	public int createBbdzmbByDzls(int dzls) {
		int result = 0;
		String sqlString = "create table Bbdzmb_ls"+dzls+" (id int(10) NOT NULL AUTO_INCREMENT,";
		for(int i=1; i<=dzls;i++){
			sqlString += "zb"+i+" varchar(100) DEFAULT '',";
		}
		sqlString += "zbid int(10),";
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

	/*��������������*/
	public int createBbdzByDzls(int dzls,String lx) {
		int result = 0;
		String sqlString = "create table Bbdz_"+lx+"_ls"+dzls+" (id int(10) NOT NULL AUTO_INCREMENT,";
		for(int i=1; i<=dzls;i++){
			sqlString += "zb"+i+" varchar(100) DEFAULT '',";
		}
		sqlString += "zbid int(10),";
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
	/*���ݼ�¼���ɾ��ģ���¼*/
	public int deleteBbdzmbByID(int id) {
		int result = 0;
		String sqlString = "delete from Bbdzmb where id ="+id;
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
	/*���ݼ�¼���ɾ��ģ���ӱ��¼*/
	public int deleteBbdzmbDTByIDList(int zbid, int dzls) {
		int result = 0;
		String sqlString = "delete from Bbdzmb_ls"+dzls+"  where zbid ="+zbid+"";
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
	/*���ݼ�¼���ɾ��_�ϲ����*/
	public int deleteBbdzmbHbByIDList(int id) {
		int result = 0;
		String sqlString = "delete from Bbdzmb_hb where zbid ="+id;
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
	/*���ݼ�¼���ɾ��_�������*/
	public int deleteBbdzmbJsByIDList(int id) {
		int result = 0;
		String sqlString = "delete from Bbdzmb_js where zbid ="+id;
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
	 *����ID
	 *�޸������¼
	 */
	public boolean updateBbdzmb(Bbdzmb bbdzmb) {
		String sqlString = "update Bbdzmb set czr='";
		sqlString += bbdzmb.getCzr() + "',czrdw='";
		sqlString += bbdzmb.getCzrdw() + "',czrID='";
		sqlString += bbdzmb.getCzrID() + "',czsj='";
		sqlString += bbdzmb.getCzsj() + "',lx='";
		sqlString += bbdzmb.getLx() + "',dzls=";
		sqlString += bbdzmb.getDzls() + ",bt='";
		sqlString += bbdzmb.getBt()  + "' where id=" + bbdzmb.getId();
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
	 *���������¼
	 */
	public int insertBbdzmb(Bbdzmb bbdzmb) {
		String sqlString = "insert into bbdzmb (bt,lx,czr,czsj,czrID,czrdw,dzls) values('";
		sqlString += bbdzmb.getBt() + "','";
		sqlString += bbdzmb.getLx() + "','";
		sqlString += bbdzmb.getCzr() + "','";
		sqlString += bbdzmb.getCzsj()+ "','";
		sqlString += bbdzmb.getCzrID() + "','";
		sqlString += bbdzmb.getCzrdw() + "',";
		sqlString += bbdzmb.getDzls() + ")";
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
	 *��������Ŀ���¼
	 */
	public int insertBbdzmbDT(ArrayList<CglibBean> BbdzmbDTList, int dzls) {
		int len = BbdzmbDTList.size()-1;
		String sqlString = "insert into Bbdzmb_ls"+dzls+" (zbid,";
		for(int i=1; i<dzls; i++){
			sqlString += "zb"+i+",";
		}
		sqlString += "zb"+dzls+") values(";
		
		for(int i=0; i<len; i++){
			sqlString += BbdzmbDTList.get(i).getValue("zbid") + ",'";
			for(int j=1; j<dzls; j++){
				sqlString += BbdzmbDTList.get(i).getValue("zb"+j) + "','";
			}
			sqlString += BbdzmbDTList.get(i).getValue("zb"+dzls) + "'),(";
		}
		sqlString += BbdzmbDTList.get(len).getValue("zbid") + ",'";
		for(int k=1; k<dzls; k++){
			sqlString += BbdzmbDTList.get(len).getValue("zb"+k) + "','";
		}
		sqlString += BbdzmbDTList.get(len).getValue("zb"+dzls) + "')";
		
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
	 *������¼
	 */
	public boolean insertBbdzmbHb(ArrayList<BbdzmbHb> BbdzmbHbList) {
		int len = BbdzmbHbList.size()-1;
		String sqlString = "insert into bbdzmb_hb (column1,row1,column2,row2,zbid) values(";
		for(int i=0; i<len; i++){
			sqlString += BbdzmbHbList.get(i).getColumn1() + ",";
			sqlString += BbdzmbHbList.get(i).getRow1() + ",";
			sqlString += BbdzmbHbList.get(i).getColumn2() + ",";
			sqlString += BbdzmbHbList.get(i).getRow2() + ",";
			sqlString += BbdzmbHbList.get(i).getZbid() + "),(";
		}
		sqlString += BbdzmbHbList.get(len).getColumn1() + ",";
		sqlString += BbdzmbHbList.get(len).getRow1() + ",";
		sqlString += BbdzmbHbList.get(len).getColumn2() + ",";
		sqlString += BbdzmbHbList.get(len).getRow2() + ",";
		sqlString += BbdzmbHbList.get(len).getZbid() + ")";
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
	 *������¼
	 */
	public boolean insertBbdzmbJs(ArrayList<BbdzmbJs> BbdzmbJsList) {
		int len = BbdzmbJsList.size()-1;
		String sqlString = "insert into bbdzmb_js (column1,row1,column2,row2,column3,row3,lx,fh,zbid) values(";
		for(int i=0; i<len; i++){
			sqlString += BbdzmbJsList.get(i).getColumn1() + ",";
			sqlString += BbdzmbJsList.get(i).getRow1() + ",";
			sqlString += BbdzmbJsList.get(i).getColumn2() + ",";
			sqlString += BbdzmbJsList.get(i).getRow2() + ",";
			sqlString += BbdzmbJsList.get(i).getColumn3() + ",";
			sqlString += BbdzmbJsList.get(i).getRow3() + ",'";
			sqlString += BbdzmbJsList.get(i).getLx() + "','";
			sqlString += BbdzmbJsList.get(i).getFh() + "',";
			sqlString += BbdzmbJsList.get(i).getZbid() + "),(";
		}
		sqlString += BbdzmbJsList.get(len).getColumn1() + ",";
		sqlString += BbdzmbJsList.get(len).getRow1() + ",";
		sqlString += BbdzmbJsList.get(len).getColumn2() + ",";
		sqlString += BbdzmbJsList.get(len).getRow2() + ",";
		sqlString += BbdzmbJsList.get(len).getColumn3() + ",";
		sqlString += BbdzmbJsList.get(len).getRow3() + ",'";
		sqlString += BbdzmbJsList.get(len).getLx() + "','";
		sqlString += BbdzmbJsList.get(len).getFh() + "',";
		sqlString += BbdzmbJsList.get(len).getZbid() + ")";
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
	 *��ѯ��Ϣ 
	 */
	public  ArrayList<Bbdzmb> queryBbdzmbByLx(String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Bbdzmb> BbdzmbList = new ArrayList<Bbdzmb>();
		String sqlString = "select * from Bbdzmb where lx='"+lx+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Bbdzmb bbdzmb = new Bbdzmb();
				bbdzmb.setId(rs.getInt("id"));
				bbdzmb.setBt(rs.getString("bt"));
				bbdzmb.setLx(rs.getString("lx"));
				bbdzmb.setDzls(rs.getInt("dzls"));
				BbdzmbList.add(bbdzmb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return BbdzmbList;
	}
	
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public  ArrayList<Bbdz> queryBbdzByBt(String srbt ,int begin, int pageSize, String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<Bbdz> BbdzList = new ArrayList<Bbdz>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from Bbdz_"+lx+" order by id limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from Bbdz_"+lx+" "+srbt +" order by id limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Bbdz bbdz = new Bbdz();
				bbdz.setId(rs.getInt("id"));
				bbdz.setBt(rs.getString("bt"));
				bbdz.setSj(rs.getDate("sj"));
				bbdz.setCzr(rs.getString("czr"));
				bbdz.setCzrID(rs.getString("czrID"));
				bbdz.setCzrdw(rs.getString("czrdw"));
				bbdz.setBbls(rs.getInt("bbls"));
				bbdz.setZbid(rs.getInt("zbid"));
				bbdz.setTjzt(rs.getString("tjzt"));
				BbdzList.add(bbdz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return BbdzList;
	}
	/*
	 *��������
	 *��ѯ��¼��
	 */
	public  int queryBbdzByBtCount(String srbt, String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from Bbdz_"+lx+" order by id";
		}else{
			sqlString = "select count(*) from Bbdz_"+lx+" "+srbt+" order by id";
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
	 * ����1��ID������Ϣ
	 */
	public  Bbdz queryBbdzById(int id, String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		Bbdz bbdz = new Bbdz();
		String sqlString = "select * from Bbdz_"+lx+" where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				bbdz.setId(rs.getInt("id"));
				bbdz.setBt(rs.getString("bt"));
				bbdz.setSj(rs.getDate("sj"));
				bbdz.setCzr(rs.getString("czr"));
				bbdz.setCzrID(rs.getString("czrID"));
				bbdz.setCzrdw(rs.getString("czrdw"));
				bbdz.setBbls(rs.getInt("bbls"));
				bbdz.setZbid(rs.getInt("zbid"));
				bbdz.setTjzt(rs.getString("tjzt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return bbdz;
	}
	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public ArrayList<CglibBean> queryBbdzDTByIDList(int zbid, int dzls,String lx) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<CglibBean> BbdzDTList = new ArrayList<CglibBean>();
		String sqlString = "select * from Bbdz_"+lx+"_ls"+dzls+" where zbid ="+zbid;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			//��̬����ʵ��bean
			HashMap propertyMap = new HashMap();
			// �������Ա����       
			propertyMap.put("id", Class.forName("java.lang.Integer"));
			propertyMap.put("zbid", Class.forName("java.lang.Integer"));
			for(int i=1; i<=dzls; i++){
				propertyMap.put("zb"+i, Class.forName("java.lang.String"));
			}
			while (rs.next()) {
				// ���ɶ�̬ Bean    
				CglibBean bean = new CglibBean(propertyMap);
				bean.setValue("id", rs.getInt("id"));
				bean.setValue("zbid", rs.getInt("zbid"));
				for(int j=1; j<=dzls; j++){
					bean.setValue("zb"+j, rs.getString("zb"+j));
				}
				BbdzDTList.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return BbdzDTList;
	}
	/*
	 *����ID
	 *�޸������¼
	 */
	public boolean updateBbdz(Bbdz bbdz,String lx) {
		String sqlString = "update Bbdz_"+lx+" set czr='";
		sqlString += bbdz.getCzr() + "',czrdw='";
		sqlString += bbdz.getCzrdw() + "',czrID='";
		sqlString += bbdz.getCzrID() + "',czsj='";
		sqlString += bbdz.getCzsj() + "',sj='";
		sqlString += bbdz.getSj() + "',bbls=";
		sqlString += bbdz.getBbls()  + ",zbid=";
		sqlString += bbdz.getZbid() + ",bt='";
		sqlString += bbdz.getBt()  + "',tjzt='";
		sqlString += bbdz.getTjzt()  + "' where id=" + bbdz.getId();
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
	 *���������¼
	 */
	public int insertBbdz(Bbdz bbdz,String lx) {
		String sqlString = "insert into bbdz_"+lx+" (bt,sj,czr,czsj,czrID,czrdw,bbls,zbid,tjzt) values('";
		sqlString += bbdz.getBt() + "','";
		sqlString += bbdz.getSj() + "','";
		sqlString += bbdz.getCzr() + "','";
		sqlString += bbdz.getCzsj()+ "','";
		sqlString += bbdz.getCzrID() + "','";
		sqlString += bbdz.getCzrdw() + "',";
		sqlString += bbdz.getBbls() + ",";
		sqlString += bbdz.getZbid() + ",'";
		sqlString += bbdz.getTjzt() + "')";
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
	 *��������Ŀ���¼
	 */
	public int insertBbdzDT(ArrayList<CglibBean> BbdzDTList, int dzls, String lx) {
		int len = BbdzDTList.size()-1;
		String sqlString = "insert into Bbdz_"+lx+"_ls"+dzls+" (zbid,";
		for(int i=1; i<dzls; i++){
			sqlString += "zb"+i+",";
		}
		sqlString += "zb"+dzls+") values(";
		
		for(int i=0; i<len; i++){
			sqlString += BbdzDTList.get(i).getValue("zbid") + ",'";
			for(int j=1; j<dzls; j++){
				sqlString += BbdzDTList.get(i).getValue("zb"+j) + "','";
			}
			sqlString += BbdzDTList.get(i).getValue("zb"+dzls) + "'),(";
		}
		sqlString += BbdzDTList.get(len).getValue("zbid") + ",'";
		for(int k=1; k<dzls; k++){
			sqlString += BbdzDTList.get(len).getValue("zb"+k) + "','";
		}
		sqlString += BbdzDTList.get(len).getValue("zb"+dzls) + "')";
		
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
	/*���ݼ�¼���ɾ���ӱ��¼*/
	public int deleteBbdzDTByIDList(int zbid, int bbls, String lx) {
		int result = 0;
		String sqlString = "delete from Bbdz_"+lx+"_ls"+bbls+"  where zbid ="+zbid+"";
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
	/*���ݼ�¼���ɾ����¼*/
	public int deleteBbdzByID(int id, String lx) {
		int result = 0;
		String sqlString = "delete from Bbdz_"+lx+" where id ="+id;
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
	 *����ID
	 *�޸ļ�¼
	 */
	public boolean updateBbdzRet(String id, String lx) {
		String sqlString = "update Bbdz_"+lx+" set tjzt='3' where id=" + id;
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
