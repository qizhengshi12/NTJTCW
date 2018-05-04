package com.safety.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.Utils.DB;
import com.safety.entity.PostInformation;
import com.safety.entity.SendMessage;

public class TimeTaskDao {
	/*
	 *���ݶ��ID��
	 *��ѯ �ļ����� �·��ļ��ظ����³�ʱ��Ϣ
	 */
	public  String querySFCSList(String now,String zt) {
		 /* �������������ĳҳ��¼�ļ������� */
		String IDList = "";
		String sqlString = "select id from wjglhf where hfqx <'"+now+"' and hfzt='"+zt+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				if("".equals(IDList)){
					IDList = rs.getString("id");
				}else{
					IDList = IDList +","+rs.getString("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return IDList;
	}
	/*
	 *����ID
	 *�޸���������
	 */
	public void updateSFCS(String id) {
		String sqlString = "update wjglhf set sfcs='��ʱ' where id in (" + id +")";
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
	 *��ѯ���µ�����ͼ
	 */
	public  String querylimStr() {
		 /* �������������ĳҳ��¼�ļ������� */
		String limStr = "";
		String sqlString = "select id from PostInformation where photoURL<>'' order by id desc limit 5";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				if("".equals(limStr)){
					limStr = rs.getInt("id")+"";
				}else{
					limStr = limStr+","+rs.getInt("id");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return limStr;
	}

	/* 
	 *��ѯ��Ϣ��ɾ������ҳ��������
	 */
	public  ArrayList<PostInformation> queryInformatDel(String limStr ,String ltime) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<PostInformation> InformatList = new ArrayList<PostInformation>();
		String sqlString = "select id,fileURL,photoURL from PostInformation where tjsj <'"+ltime+"' and  id not in("+limStr+")";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while(rs.next()) {
				PostInformation postInformation = new PostInformation();
				postInformation.setId(rs.getInt("id"));
				postInformation.setFileURL(rs.getString("fileURL"));
				postInformation.setPhotoURL(rs.getString("photoURL"));
				InformatList.add(postInformation);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		return InformatList;
	}
	/*
	 *����ID
	 *ɾ����Ϣ
	 */
	public int DeleteInformat(int id) {
		int result = 0;
		String sqlString = "delete from PostInformation where id=" + id;
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
	 *��ѯ������
	 */
	public  ArrayList<SendMessage> querySendSendMessageList(String dataBegin , String dataEnd) {
		 /* �������������ĳҳ��¼�ļ������� */
		ArrayList<SendMessage> SendMessageList = new ArrayList<SendMessage>();
		String sqlString =  "select * from SendMessage where sffs='δ����' and fssj>='"+dataBegin+"' and fssj<='"+dataEnd+"'";
		/* �������ݲ���в�ѯ */
		DB db = new DB();
		ResultSet rs; 
		try {
			rs = db.executeQuery(sqlString);
			while (rs.next()) {
				SendMessage sendMessage = new SendMessage();
				sendMessage.setId(rs.getInt("id"));
				sendMessage.setDxnr(rs.getString("dxnr"));
				sendMessage.setJsr(rs.getString("jsr"));
				sendMessage.setJsrID(rs.getString("jsrID"));
				sendMessage.setFssj(rs.getTimestamp("fssj"));
				sendMessage.setCzr(rs.getString("czr"));
				sendMessage.setCzrID(rs.getString("czrID"));
				sendMessage.setCzsj(rs.getTimestamp("czsj"));
				sendMessage.setSffs(rs.getString("sffs"));
				SendMessageList.add(sendMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		} 
		
		return SendMessageList;
	}
	/*
	 * ����ID
	 * ���¶��ŷ���״̬
	 */
	public void updateDXFSZT(int id) {
		String sqlString = "update SendMessage set sffs='�ѷ���' where id =" + id;
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
}
