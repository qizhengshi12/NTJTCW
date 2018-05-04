package com.safety.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.database.Utils.ConnectionPoolUtils;
import com.database.Utils.DB;
import com.safety.entity.ContentZzxx;
import com.safety.entity.Menu;

public class ContentZzxxDao {
	/*
	 * ���ݶ��ID�����û���Ϣ
	 */
	public  ArrayList<ContentZzxx> queryZzxxByIdList(String idList, int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		String sqlString = "select * from content_zzxx where fatherid!='' and id in ( "+idList+" ) order by sortnum limit "+begin+","+pageSize;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentZzxx Zzxx = new ContentZzxx();
				Zzxx.setId(rs.getInt("id"));
				Zzxx.setName(rs.getString("name"));
				Zzxx.setSex(rs.getString("sex"));
				Zzxx.setEducation(rs.getString("education"));
				Zzxx.setPcard(rs.getString("pcard"));
				Zzxx.setSchool(rs.getString("school"));
				Zzxx.setWorkphone(rs.getString("workphone"));
				Zzxx.setJoblevel(rs.getString("joblevel"));
				Zzxx.setBirth(rs.getDate("birth"));
				Zzxx.setWorktime(rs.getDate("worktime"));
				Zzxx.setPhone(rs.getString("phone"));
				Zzxx.setJob(rs.getString("job"));
				Zzxx.setJobdes(rs.getString("jobdes"));
				Zzxx.setUsername(rs.getString("username"));
				Zzxx.setRoles(rs.getString("roles"));
				Zzxx.setCompany(rs.getString("company"));
				Zzxx.setCompanyID(rs.getString("companyID"));
				Zzxx.setDepart1(rs.getString("depart1"));
				Zzxx.setDepartID1(rs.getString("departID1"));
				Zzxx.setDepart2(rs.getString("depart2"));
				Zzxx.setDepartID2(rs.getString("departID2"));
				Zzxx.setDepart3(rs.getString("depart3"));
				Zzxx.setDepartID3(rs.getString("departID3"));
				Zzxx.setFatherid(rs.getString("fatherid"));
				Zzxx.setCzr(rs.getString("czr"));
				Zzxx.setCzrID(rs.getString("czrID"));
				Zzxx.setSortnum(rs.getInt("sortnum"));
				ZzxxList.add(Zzxx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return ZzxxList;
	}
	/*
	 * ���ݶ��ID�����û���Ϣ
	 */
	public  int queryZzxxByIdListCount(String idList) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "select count(*) from content_zzxx where fatherid!='' and id in ( "+idList+" ) order by sortnum";
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
	 *��ѯһ���û���Ϣ 
	 */
	public  ContentZzxx queryZzxxByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentZzxx Zzxx = new ContentZzxx();
		String sqlString = "select * from content_zzxx where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Zzxx.setId(rs.getInt("id"));
				Zzxx.setName(rs.getString("name"));
				Zzxx.setSex(rs.getString("sex"));
				Zzxx.setEducation(rs.getString("education"));
				Zzxx.setPcard(rs.getString("pcard"));
				Zzxx.setSchool(rs.getString("school"));
				Zzxx.setWorkphone(rs.getString("workphone"));
				Zzxx.setJoblevel(rs.getString("joblevel"));
				Zzxx.setBirth(rs.getDate("birth"));
				Zzxx.setWorktime(rs.getDate("worktime"));
				Zzxx.setPhone(rs.getString("phone"));
				Zzxx.setJob(rs.getString("job"));
				Zzxx.setJobdes(rs.getString("jobdes"));
				Zzxx.setUsername(rs.getString("username"));
				Zzxx.setRoles(rs.getString("roles"));
				Zzxx.setCompany(rs.getString("company"));
				Zzxx.setCompanyID(rs.getString("companyID"));
				Zzxx.setDepart1(rs.getString("depart1"));
				Zzxx.setDepartID1(rs.getString("departID1"));
				Zzxx.setDepart2(rs.getString("depart2"));
				Zzxx.setDepartID2(rs.getString("departID2"));
				Zzxx.setDepart3(rs.getString("depart3"));
				Zzxx.setDepartID3(rs.getString("departID3"));
				Zzxx.setFatherid(rs.getString("fatherid"));
				Zzxx.setCzr(rs.getString("czr"));
				Zzxx.setCzrID(rs.getString("czrID"));
				Zzxx.setSortnum(rs.getInt("sortnum"));
				Zzxx.setFileURL(rs.getString("FileURL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Zzxx;
	}
	/*
	 * ����ID�����û���Ϣ������ҳ��
	 */
	public  ArrayList<ContentZzxx> queryZzxxByIdListALL(String idList) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		String sqlString = "select name,username from content_zzxx where fatherid!='' and id in ( "+idList+" ) order by sortnum ";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentZzxx Zzxx = new ContentZzxx();
				Zzxx.setName(rs.getString("name"));
				Zzxx.setUsername(rs.getString("username"));
				ZzxxList.add(Zzxx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return ZzxxList;
	}
	/*
	 * ����roles�����û���Ϣ������ҳ��
	 */
	public  ArrayList<ContentZzxx> queryZzxxByRoles(String roles) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		String sqlString = "select name,username from content_zzxx where fatherid!='' and roles ='"+roles+"' order by sortnum ";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentZzxx Zzxx = new ContentZzxx();
				Zzxx.setName(rs.getString("name"));
				Zzxx.setUsername(rs.getString("username"));
				ZzxxList.add(Zzxx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return ZzxxList;
	}
	/*
	 * �����û��������û���Ϣ
	 */
	public  ContentZzxx queryZzxxByUserName(String UserName) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentZzxx Zzxx = new ContentZzxx();
		String sqlString = "select * from content_zzxx where fatherid!='' and username ='"+UserName+"' order by sortnum ";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Zzxx.setId(rs.getInt("id"));
				Zzxx.setName(rs.getString("name"));
				Zzxx.setSex(rs.getString("sex"));
				Zzxx.setEducation(rs.getString("education"));
				Zzxx.setPcard(rs.getString("pcard"));
				Zzxx.setSchool(rs.getString("school"));
				Zzxx.setWorkphone(rs.getString("workphone"));
				Zzxx.setJoblevel(rs.getString("joblevel"));
				Zzxx.setBirth(rs.getDate("birth"));
				Zzxx.setWorktime(rs.getDate("worktime"));
				Zzxx.setPhone(rs.getString("phone"));
				Zzxx.setJob(rs.getString("job"));
				Zzxx.setJobdes(rs.getString("jobdes"));
				Zzxx.setUsername(rs.getString("username"));
				Zzxx.setRoles(rs.getString("roles"));
				Zzxx.setCompany(rs.getString("company"));
				Zzxx.setCompanyID(rs.getString("companyID"));
				Zzxx.setDepart1(rs.getString("depart1"));
				Zzxx.setDepartID1(rs.getString("departID1"));
				Zzxx.setDepart2(rs.getString("depart2"));
				Zzxx.setDepartID2(rs.getString("departID2"));
				Zzxx.setDepart3(rs.getString("depart3"));
				Zzxx.setDepartID3(rs.getString("departID3"));
				Zzxx.setFatherid(rs.getString("fatherid"));
				Zzxx.setCzr(rs.getString("czr"));
				Zzxx.setCzrID(rs.getString("czrID"));
				Zzxx.setSortnum(rs.getInt("sortnum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return Zzxx;
	}
	/*
	 *���ݵ�½�û���
	 *��ѯһ����Ϣ 
	 */
	public static ContentZzxx queryThisZzxxByUserName(String name) {
		 /* �������������ĳҳ��¼�ļ������� */
		ContentZzxx Zzxx = new ContentZzxx();
		String sqlString = "select * from content_zzxx where username ='"+ name + "'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				Zzxx.setId(rs.getInt("id"));
				Zzxx.setName(rs.getString("name"));
				Zzxx.setJob(rs.getString("job"));
				Zzxx.setUsername(rs.getString("username"));
				Zzxx.setPhone(rs.getString("phone"));
				Zzxx.setWorkphone(rs.getString("workphone"));
				Zzxx.setRoles(rs.getString("roles"));
				Zzxx.setCompany(rs.getString("company"));
				Zzxx.setCompanyID(rs.getString("companyID"));
				Zzxx.setDepart1(rs.getString("depart1"));
				Zzxx.setDepartID1(rs.getString("departID1"));
				Zzxx.setDepart2(rs.getString("depart2"));
				Zzxx.setDepartID2(rs.getString("departID2"));
				Zzxx.setDepart3(rs.getString("depart3"));
				Zzxx.setDepartID3(rs.getString("departID3"));
				Zzxx.setFatherid(rs.getString("fatherid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return Zzxx;
	}
	/*
	 *���ݵ�λ
	 *��ѯ��ϢԱ 
	 */
	public static ArrayList<ContentZzxx> queryXxyByCompanyID(String companyID) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		
		String sqlString = "select name,username from content_zzxx where companyID ='"+ companyID + "' and roles='��ϢԱ'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				ContentZzxx Zzxx = new ContentZzxx();
				Zzxx.setName(rs.getString("name"));
				Zzxx.setUsername(rs.getString("username"));
				ZzxxList.add(Zzxx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ZzxxList;
	}

	/*
	 *��������
	 *��ѯ��Ϣ 
	 */
	public  ArrayList<ContentZzxx> queryZzxxByBt(String srbt ,int begin, int pageSize) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<ContentZzxx> ZzxxList = new ArrayList<ContentZzxx>();
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select * from content_zzxx where fatherid!='' order by sortnum limit "+begin+","+pageSize;
		}else{
			sqlString = "select * from content_zzxx "+srbt +" and fatherid!='' order by sortnum limit "+begin+","+pageSize;
		}
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				ContentZzxx Zzxx = new ContentZzxx();
				Zzxx.setId(rs.getInt("id"));
				Zzxx.setName(rs.getString("name"));
				Zzxx.setSex(rs.getString("sex"));
				Zzxx.setEducation(rs.getString("education"));
				Zzxx.setPcard(rs.getString("pcard"));
				Zzxx.setSchool(rs.getString("school"));
				Zzxx.setWorkphone(rs.getString("workphone"));
				Zzxx.setJoblevel(rs.getString("joblevel"));
				Zzxx.setBirth(rs.getDate("birth"));
				Zzxx.setWorktime(rs.getDate("worktime"));
				Zzxx.setPhone(rs.getString("phone"));
				Zzxx.setJob(rs.getString("job"));
				Zzxx.setJobdes(rs.getString("jobdes"));
				Zzxx.setUsername(rs.getString("username"));
				Zzxx.setRoles(rs.getString("roles"));
				Zzxx.setCompany(rs.getString("company"));
				Zzxx.setCompanyID(rs.getString("companyID"));
				Zzxx.setDepart1(rs.getString("depart1"));
				Zzxx.setDepartID1(rs.getString("departID1"));
				Zzxx.setDepart2(rs.getString("depart2"));
				Zzxx.setDepartID2(rs.getString("departID2"));
				Zzxx.setDepart3(rs.getString("depart3"));
				Zzxx.setDepartID3(rs.getString("departID3"));
				Zzxx.setFatherid(rs.getString("fatherid"));
				Zzxx.setCzr(rs.getString("czr"));
				Zzxx.setCzrID(rs.getString("czrID"));
				Zzxx.setSortnum(rs.getInt("sortnum"));
				ZzxxList.add(Zzxx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ZzxxList;
	}
	/*
	 *��������
	 *��ѯ��¼��
	 */
	public  int queryZzxxByBtCount(String srbt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String sqlString = "";
		if("".equals(srbt)){
			sqlString = "select count(*) from content_zzxx where fatherid!=''  order by sortnum";
		}else{
			sqlString = "select count(*) from content_zzxx "+srbt+" and fatherid!=''  order by sortnum";
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

	/*���ݼ�¼���ɾ��ĳ��������Ϣ*/
	public static int DeleteZzxxById(int id) {
		int result = 0;
		String sqlString = "delete from content_zzxx where id=" + id;
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
	 *������¼  
	 */
	public int insertZzxx(ContentZzxx Zzxx) {
		String sqlString = "insert into content_zzxx (name,sex,education,pcard,school,workphone,joblevel,birth,worktime,phone,job,jobdes,username,password,roles,company,companyID,depart1,departID1,depart2,departID2,depart3,departID3,fatherid,czr,czsj,czrID,sortnum,FileURL) values ('";
		sqlString += Zzxx.getName() + "','";
		sqlString += Zzxx.getSex() + "','";
		sqlString += Zzxx.getEducation() + "','";
		sqlString += Zzxx.getPcard() + "','";
		sqlString += Zzxx.getSchool() + "','";
		sqlString += Zzxx.getWorkphone() + "','";
		sqlString += Zzxx.getJoblevel() + "','";
		sqlString += Zzxx.getBirth() + "','";
		sqlString += Zzxx.getWorktime() + "','";
		sqlString += Zzxx.getPhone() + "','";
		sqlString += Zzxx.getJob() + "','";
		sqlString += Zzxx.getJobdes() + "','";
		sqlString += Zzxx.getUsername() + "','";
		sqlString += Zzxx.getPassword() + "','";
		sqlString += Zzxx.getRoles() + "','";
		sqlString += Zzxx.getCompany()+ "','";
		sqlString += Zzxx.getCompanyID()+ "','";
		sqlString += Zzxx.getDepart1() + "','";
		sqlString += Zzxx.getDepartID1() + "','";
		sqlString += Zzxx.getDepart2() + "','";
		sqlString += Zzxx.getDepartID2() + "','";
		sqlString += Zzxx.getDepart3() + "','";
		sqlString += Zzxx.getDepartID3() + "','";
		sqlString += Zzxx.getFatherid()+ "','";
		sqlString += Zzxx.getCzr() + "','";
		sqlString += Zzxx.getCzsj()+ "','";
		sqlString += Zzxx.getCzrID() + "','";
		sqlString += Zzxx.getSortnum()+ "','";
		sqlString += Zzxx.getFileURL()+ "')";
		
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
	 *�޸ļ�¼  
	 */
	public boolean updateZzxx(ContentZzxx Zzxx) {
		String sqlString = "update content_zzxx set name='";
		sqlString += Zzxx.getName() + "',sex='";
		sqlString += Zzxx.getSex() +"',education='";
		sqlString += Zzxx.getEducation() + "',pcard='";
		sqlString += Zzxx.getPcard() + "',school='";
		sqlString += Zzxx.getSchool() + "',workphone='";
		sqlString += Zzxx.getWorkphone() + "',joblevel='";
		sqlString += Zzxx.getJoblevel() + "',birth='";
		sqlString += Zzxx.getBirth() + "',worktime='";
		sqlString += Zzxx.getWorktime() + "',phone='";
		sqlString += Zzxx.getPhone() +"',job='";
		sqlString += Zzxx.getJob() + "',jobdes='";
		sqlString += Zzxx.getJobdes()+ "',company='";
		sqlString += Zzxx.getCompany()+ "',companyID='";
		sqlString += Zzxx.getCompanyID()+ "',depart1='";
		sqlString += Zzxx.getDepart1()+ "',departID1='";
		sqlString += Zzxx.getDepartID1()+ "',depart2='";
		sqlString += Zzxx.getDepart2()+ "',departID2='";
		sqlString += Zzxx.getDepartID2()+ "',depart3='";
		sqlString += Zzxx.getDepart3()+ "',departID3='";
		sqlString += Zzxx.getDepartID3() + "',fatherid='";
		sqlString += Zzxx.getFatherid()+ "',username='";
		sqlString += Zzxx.getUsername() + "',czr='";
		sqlString += Zzxx.getCzr()+ "',czrID='";
		sqlString += Zzxx.getCzrID() + "',czsj='";
		sqlString += Zzxx.getCzsj()+ "',roles='";
		sqlString += Zzxx.getRoles() + "' where id=" + Zzxx.getId();
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
	 *����
	 *�޸��˻�  
	 */
	public boolean updateMyAccount(ContentZzxx Zzxx) {
		String sqlString = "update content_zzxx set education='";
		sqlString += Zzxx.getEducation() + "',pcard='";
		sqlString += Zzxx.getPcard() + "',school='";
		sqlString += Zzxx.getSchool() + "',workphone='";
		sqlString += Zzxx.getWorkphone() + "',birth='";
		sqlString += Zzxx.getBirth() + "',worktime='";
		sqlString += Zzxx.getWorktime() + "',phone='";
		sqlString += Zzxx.getPhone() +"',job='";
		sqlString += Zzxx.getJob() + "',jobdes='";
		sqlString += Zzxx.getJobdes() + "',czr='";
		sqlString += Zzxx.getCzr()+ "',czrID='";
		sqlString += Zzxx.getCzrID() + "',czsj='";
		sqlString += Zzxx.getCzsj()  + "',FileURL='";
		sqlString += Zzxx.getFileURL()  + "' where id=" + Zzxx.getId();
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
	 *�޸�˳��  
	 */
	public void updateZzxxSort(int id, int sortnum) {
		String sqlString = "update content_zzxx set sortnum="+sortnum+ " where id=" + id;
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/*
	 *���ݲ˵�ID
	 *�޸ĵ�λ  
	 */
	public void updateZzxxCompany1(String id, String name) {
		String sqlString = "update content_zzxx set company='"+name+ "' where companyID='"+id+"'";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/*
	 *���ݲ˵�ID
	 *�޸Ķ�����������  
	 */
	public void updateZzxxCompany2(String id, String name) {
		String sqlString = "update content_zzxx set depart1='"+name+ "' where departID1='"+id+"'";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	/*
	 *���ݲ˵�ID
	 *�޸�������������  
	 */
	public void updateZzxxCompany3(String id, String name) {
		String sqlString = "update content_zzxx set depart2='"+name+ "' where departID2='"+id+"'";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/*
	 *���ݲ˵�ID
	 *�޸��ļ���������  
	 */
	public void updateZzxxCompany4(String id, String name) {
		String sqlString = "update content_zzxx set depart3='"+name+ "' where departID3='"+id+"'";
		DB db = new DB();
		try {
			db.executeUpdate(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	/*
	 *��ѯ�û����Ƿ����
	 *
	 */
	public  boolean FindSameUser(String UserName) {
		String sqlString = "select * from content_zzxx where username = '"+UserName.trim()+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		boolean result = false;
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
	 *����ID
	 *��ѯ����
	 */
	public  String queryPWByID(int id) {
		 /* �������������ĳҳ��¼�ļ������� */
		String ps = "";
		String sqlString = "select password from content_zzxx where id ="+id;
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				ps = rs.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return ps;
	}
	/*
	 *����ID
	 *�޸�����
	 */
	public  boolean updatePWByID(String str,int id) {
		String sqlString = "update content_zzxx set password='"+str+"' where id=" + id;
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
	/* ��֤����Ա��ݵ�½ */
	public static boolean checkLogin(String user ,String pass) {
		DB db = new DB();
		boolean flag = false;
		String sql = "select * from content_zzxx where username='"
				+ user + "' and password='"
				+ pass + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next())
				flag = true;
			else
				flag = false;
			db.all_close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} 
		return flag;
	}
	
	/*
	 *��ѯ��λ
	 *
	 */
	public  ArrayList<Menu> FindCompany(String parentid) {
		String sqlString = "select id,name from node_zzxx where parent_id = "+parentid;
		/* �������ݲ���в�ѯ */
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setName(rs.getString("name"));
				menuList.add(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return menuList;
	}
	/*
	 * ���ݶ���û��������û��ֻ�
	 */
	public  String queryPhoneByUserName(String UserName) {
		 /* �������������ĳҳ��¼�ļ������� */
		String phone = "";
		String sqlString = "select phone from content_zzxx where username ='"+UserName+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			if(rs.next()) {
				phone= rs.getString("phone");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return phone;
	}
}
