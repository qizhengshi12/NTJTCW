package com.safety.servlet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession; 
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent; 
import javax.servlet.http.HttpSessionListener; 

import com.safety.entity.ContentZzxx;

public class MySessionServlet implements  HttpSessionAttributeListener,HttpSessionListener {
	/** 
     * �����û��б� 
     */  
	private static List onlineUsers = new ArrayList();  
      
    /** 
     * ��ȡ�����û��б� 
     * @return 
     */  
    public static List getOnlineUsers(){  
        return onlineUsers;  
    }  
  
    /*  
     * session �������ʱ�����״�ִ��setAttributeʱ������ 
     */  
    public void attributeAdded(HttpSessionBindingEvent event) {  
        if(event.getSession()==null){  
            return;  
        }  
        // ��ִ��setAttribute("logonUserView",logonUserView)ʱ  
        // ����ǰ�û���ӵ������û��б���  
        if(event.getName().equalsIgnoreCase("UserInfo")){  
            // ��ȡ��ǰ��½�û���Ϣ��session  
        	ContentZzxx user = (ContentZzxx)event.getValue();  
            HttpSession session = event.getSession();  
              
            // ���������û���Ϣ����  
            ContentZzxx onlineUserView = new ContentZzxx();  
            onlineUserView.setUsername(user.getUsername());  
            onlineUserView.setName(user.getName()); 
            onlineUserView.setCompany(user.getCompany());
            onlineUserView.setUserSession(session);  
              
            // ��ӵ������û��б�  
            onlineUsers.add(onlineUserView);  
//	        System.out.println("�û���½������"+onlineUsers.size());
        }  

    }  
  
    /*  
     * session �����Ƴ�ʱ����ִ��removeAttributeʱ������ 
     */  
    public void attributeRemoved(HttpSessionBindingEvent event) {  
    }  
 
    /*  
     * session ���Ը���ʱ�����ٴ�ִ��setAttributeʱ������ 
     */  
    public void attributeReplaced(HttpSessionBindingEvent event) {  
        if(event.getSession()==null){  
            return;  
        }  
  
        // ��ִ���ٴ�ִ��setAttribute("logonUserView")ʱ�������û��滻  
        if(event.getName().equalsIgnoreCase("UserInfo")){  
            // ��ȡ���滻�û���Ϣ��session  
        	ContentZzxx user = (ContentZzxx)event.getValue();  
            // ���������û���Ϣ  
            HttpSession session = event.getSession();  
            // ��ȡ��ǰ��½�û���Ϣ  
            ContentZzxx newUser = (ContentZzxx)session.getAttribute("UserInfo");  
            // ѭ�����ұ��滻�û�������Ϣ  
            for(int i=0;i<onlineUsers.size();i++){  
            	ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
                if(onlineUserView.getUsername().equals(user.getUsername())){  
                    // ���������û���Ϣ  
    	            onlineUserView.setUsername(newUser.getUsername());  
    	            onlineUserView.setName(newUser.getName()); 
    	            onlineUserView.setCompany(newUser.getCompany());
                    break;  
                }  
            }
        }  
    }  
  
    /*  
     * session ����ʱ���� 
     */  
    public void sessionCreated(HttpSessionEvent event) {  
    }  
  
    /*  
     * session ����ʱ�����û�ע����session��ʱʱ������ 
     */  
    public void sessionDestroyed(HttpSessionEvent event) {  
        if(event.getSession()==null){  
            //System.out.println("OnlineUserListener.sessionDestroyed() session==null");  
            return;  
        }  
        // ��ȡ��ǰ��½�û���Ϣ��session  
        HttpSession session = event.getSession();  
        for(int i=0; i<onlineUsers.size();i++){  
        	ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
        	if(onlineUserView!=null && onlineUserView.getUserSession()!=null && session!=null   
        		&& onlineUserView.getUserSession().getId().equals(session.getId())){  
        		// �������û��б��Ƴ��û�  
	        		onlineUsers.remove(onlineUserView);  
	        		break;  
 
	            }  
	        }
//	        System.out.println("�û��˳���ʣ��"+onlineUsers.size());
    }  
}  


