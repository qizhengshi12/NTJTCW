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
	 * 短信接口(单条短信)局移动平台
	 * 
	 */
	/**
	public String submitShortMessage(String phone, String content) { 
		
		SMEntry.init("192.168.253.149", "sa", "Infox1Eies2Sps3Was4!"); 
		try { 
			java.util.Date atTime = new Date(); 
			String sourceAddr = "106573513396"; 
			String destAddr = phone; 
			String contentAll = "[财审综合信息系统]" +content; 
			int needStateReport = 0; 
			String serviceID = "MJS0019901"; 
			String feeType = "02"; 
			String feeCode = "10"; 
			int count = SMEntry.submitShortMessage("sa","Infox1Eies2Sps3Was4!",atTime, sourceAddr, destAddr, contentAll, 
			needStateReport,serviceID, feeType,feeCode); 
			SMEntry.cleanUp(); 
			if(count==1){ 
				//短信发送成功 
				return "1"; 
			}else{ 
				//短信发送失败 
				return "0"; 
			} 
		} 
		catch(SMException e){ 
			//短信发送失败 
			return "0"; 
		} 
		
		return "0"; 
	}**/
	//**/
	/*
	 * 短信接口(多条短信)局移动平台
	 * 
	 */
	/**	public void submitShortMessageMany(String phoneStr, String content) { 
		
		SMEntry.init("172.16.10.10", "sa", "Infox1Eies2Sps3Was4!"); 
		try {
			java.util.Date atTime = new Date(); 
			String sourceAddr = "106573513396"; 
			String contentAll = "[财审综合信息系统]" +content; 
			int needStateReport = 0; 
			String serviceID = "MJS0019901"; 
			String feeType = "02"; 
			String feeCode = "10"; 
			String[] phoneList = phoneStr.split("#@");
			for(int i=0; i<phoneList.length; i++){
				if(!"".equals(phoneList[i])){ 
					String destAddr = phoneList[i]; 
//					System.out.println(destAddr+"："+contentAll);
					SMEntry.submitShortMessage("sa","Infox1Eies2Sps3Was4!",atTime, sourceAddr, destAddr, contentAll, needStateReport,serviceID, feeType,feeCode); 
					SMEntry.cleanUp(); 
				}
			}
		}
		catch(SMException e){ 
			//短信发送失败 
//			return "0"; 
		} 
		
	}**/
	/*
	 * 短信接口(单条短信)中心联通平台
	 * 
	 */
	public String submitShortMessage(String phone, String content) {
				String info = null;
		try{
			HttpClient httpclient = new HttpClient();
			PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");
//			PostMethod post = new PostMethod("http://10.4.253.108:8899/sms/Api/Send.do");
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
			post.addParameter("SpCode", "223116");//企业编号
			post.addParameter("LoginName", "nt_zhjt_oa");//登陆名
			post.addParameter("Password", "oa@chww72*ks");//密码
//			post.addParameter("MessageContent", "[财审综合信息系统]"+content);//内容
			post.addParameter("MessageContent", "OA提醒："+content);//内容
			post.addParameter("UserNumber", phone);//电话
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
	 * 短信接口(多条短信)中心联通平台
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
				post.addParameter("SpCode", "223116");//企业编号
				post.addParameter("LoginName", "nt_zhjt");//登陆名
				post.addParameter("Password", "xxzx*898");//密码
				post.addParameter("MessageContent", "[财审综合信息系统]"+content);//内容
				post.addParameter("SerialNumber", "");
				post.addParameter("ScheduleTime", "");
				post.addParameter("ExtendAccessNum", "");
				post.addParameter("f", "1");
				post.addParameter("UserNumber", phoneList);//电话
				httpclient.executeMethod(post);
				String info = new String(post.getResponseBody(),"gbk");
//				System.out.println(info.split("&")[0]);
			}
		}
		catch(Exception e){ 
			//短信发送失败 
//			return "0"; 
		} 
	}
}
