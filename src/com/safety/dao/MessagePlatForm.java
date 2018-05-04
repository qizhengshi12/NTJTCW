package com.safety.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


import com.huawei.api.SMEntry;
import com.huawei.api.SMException;

public class MessagePlatForm {
	/*
	 * ���Žӿ�(��������)���ƶ�ƽ̨
	 * 
	 */
	/**
	public String submitShortMessage(String phone, String content) { 
		
		SMEntry.init("192.168.253.149", "sa", "Infox1Eies2Sps3Was4!"); 
		try { 
			java.util.Date atTime = new Date(); 
			String sourceAddr = "106573513396"; 
			String destAddr = phone; 
			String contentAll = "[�����ۺ���Ϣϵͳ]" +content; 
			int needStateReport = 0; 
			String serviceID = "MJS0019901"; 
			String feeType = "02"; 
			String feeCode = "10"; 
			int count = SMEntry.submitShortMessage("sa","Infox1Eies2Sps3Was4!",atTime, sourceAddr, destAddr, contentAll, 
			needStateReport,serviceID, feeType,feeCode); 
			SMEntry.cleanUp(); 
			if(count==1){ 
				//���ŷ��ͳɹ� 
				return "1"; 
			}else{ 
				//���ŷ���ʧ�� 
				return "0"; 
			} 
		} 
		catch(SMException e){ 
			//���ŷ���ʧ�� 
			return "0"; 
		} 
		
		return "0"; 
	}**/
	//**/
	/*
	 * ���Žӿ�(��������)���ƶ�ƽ̨
	 * 
	 */
	/**	public void submitShortMessageMany(String phoneStr, String content) { 
		
		SMEntry.init("172.16.10.10", "sa", "Infox1Eies2Sps3Was4!"); 
		try {
			java.util.Date atTime = new Date(); 
			String sourceAddr = "106573513396"; 
			String contentAll = "[�����ۺ���Ϣϵͳ]" +content; 
			int needStateReport = 0; 
			String serviceID = "MJS0019901"; 
			String feeType = "02"; 
			String feeCode = "10"; 
			String[] phoneList = phoneStr.split("#@");
			for(int i=0; i<phoneList.length; i++){
				if(!"".equals(phoneList[i])){ 
					String destAddr = phoneList[i]; 
//					System.out.println(destAddr+"��"+contentAll);
					SMEntry.submitShortMessage("sa","Infox1Eies2Sps3Was4!",atTime, sourceAddr, destAddr, contentAll, needStateReport,serviceID, feeType,feeCode); 
					SMEntry.cleanUp(); 
				}
			}
		}
		catch(SMException e){ 
			//���ŷ���ʧ�� 
//			return "0"; 
		} 
		
	}**/
	/*
	 * ���Žӿ�(��������)������ͨƽ̨
	 * 
	 */
	public String submitShortMessage(String phone, String content) {
				String info = null;
		try{
			HttpClient httpclient = new HttpClient();
			PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");
//			PostMethod post = new PostMethod("http://10.4.253.108:8899/sms/Api/Send.do");
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
			post.addParameter("SpCode", "223116");//��ҵ���
			post.addParameter("LoginName", "nt_zhjt_oa");//��½��
			post.addParameter("Password", "oa@chww72*ks");//����
//			post.addParameter("MessageContent", "[�����ۺ���Ϣϵͳ]"+content);//����
			post.addParameter("MessageContent", "OA���ѣ�"+content);//����
			post.addParameter("UserNumber", phone);//�绰
			post.addParameter("SerialNumber", "");
			post.addParameter("ScheduleTime", "");
			post.addParameter("ExtendAccessNum", "");
			post.addParameter("f", "1");
			httpclient.executeMethod(post);
			info = new String(post.getResponseBody(),"gbk");
//			System.out.println(info.split("&")[0]);
			if("result=0".equals(info.split("&")[0])){
				return "1";
			}else{
				return "0";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
//return "1";
	}
	/*
	 * ���Žӿ�(��������)������ͨƽ̨
	 * 
	 */
	public void submitShortMessageMany(String phoneStr, String content) { 
			try {

			if(!"".equals(phoneStr)){ 
				String phoneList = phoneStr.replaceAll("#@", ",");
				HttpClient httpclient = new HttpClient();
//				PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");
				PostMethod post = new PostMethod("http://10.4.253.108:8899/sms/Api/Send.do");
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
				post.addParameter("SpCode", "223116");//��ҵ���
				post.addParameter("LoginName", "nt_zhjt");//��½��
				post.addParameter("Password", "xxzx*898");//����
				post.addParameter("MessageContent", "[�����ۺ���Ϣϵͳ]"+content);//����
				post.addParameter("SerialNumber", "");
				post.addParameter("ScheduleTime", "");
				post.addParameter("ExtendAccessNum", "");
				post.addParameter("f", "1");
				post.addParameter("UserNumber", phoneList);//�绰
				httpclient.executeMethod(post);
				String info = new String(post.getResponseBody(),"gbk");
//				System.out.println(info.split("&")[0]);
			}
		}
		catch(Exception e){ 
			//���ŷ���ʧ�� 
//			return "0"; 
		} 
	}
}
