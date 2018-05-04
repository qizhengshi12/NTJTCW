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
     * 在线用户列表 
     */  
	private static List onlineUsers = new ArrayList();  
      
    /** 
     * 获取在线用户列表 
     * @return 
     */  
    public static List getOnlineUsers(){  
        return onlineUsers;  
    }  
  
    /*  
     * session 属性添加时（即首次执行setAttribute时）触发 
     */  
    public void attributeAdded(HttpSessionBindingEvent event) {  
        if(event.getSession()==null){  
            return;  
        }  
        // 当执行setAttribute("logonUserView",logonUserView)时  
        // 将当前用户添加到在线用户列表中  
        if(event.getName().equalsIgnoreCase("UserInfo")){  
            // 获取当前登陆用户信息及session  
        	ContentZzxx user = (ContentZzxx)event.getValue();  
            HttpSession session = event.getSession();  
              
            // 创建在线用户信息对象  
            ContentZzxx onlineUserView = new ContentZzxx();  
            onlineUserView.setUsername(user.getUsername());  
            onlineUserView.setName(user.getName()); 
            onlineUserView.setCompany(user.getCompany());
            onlineUserView.setUserSession(session);  
              
            // 添加到在线用户列表  
            onlineUsers.add(onlineUserView);  
//	        System.out.println("用户登陆，在线"+onlineUsers.size());
        }  

    }  
  
    /*  
     * session 属性移除时（即执行removeAttribute时）触发 
     */  
    public void attributeRemoved(HttpSessionBindingEvent event) {  
    }  
 
    /*  
     * session 属性更新时（即再次执行setAttribute时）触发 
     */  
    public void attributeReplaced(HttpSessionBindingEvent event) {  
        if(event.getSession()==null){  
            return;  
        }  
  
        // 当执行再次执行setAttribute("logonUserView")时，发生用户替换  
        if(event.getName().equalsIgnoreCase("UserInfo")){  
            // 获取被替换用户信息及session  
        	ContentZzxx user = (ContentZzxx)event.getValue();  
            // 更新在线用户信息  
            HttpSession session = event.getSession();  
            // 获取当前登陆用户信息  
            ContentZzxx newUser = (ContentZzxx)session.getAttribute("UserInfo");  
            // 循环查找被替换用户在线信息  
            for(int i=0;i<onlineUsers.size();i++){  
            	ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
                if(onlineUserView.getUsername().equals(user.getUsername())){  
                    // 更新在线用户信息  
    	            onlineUserView.setUsername(newUser.getUsername());  
    	            onlineUserView.setName(newUser.getName()); 
    	            onlineUserView.setCompany(newUser.getCompany());
                    break;  
                }  
            }
        }  
    }  
  
    /*  
     * session 创建时触发 
     */  
    public void sessionCreated(HttpSessionEvent event) {  
    }  
  
    /*  
     * session 销毁时（即用户注销、session超时时）触发 
     */  
    public void sessionDestroyed(HttpSessionEvent event) {  
        if(event.getSession()==null){  
            //System.out.println("OnlineUserListener.sessionDestroyed() session==null");  
            return;  
        }  
        // 获取当前登陆用户信息及session  
        HttpSession session = event.getSession();  
        for(int i=0; i<onlineUsers.size();i++){  
        	ContentZzxx onlineUserView = (ContentZzxx)onlineUsers.get(i);  
        	if(onlineUserView!=null && onlineUserView.getUserSession()!=null && session!=null   
        		&& onlineUserView.getUserSession().getId().equals(session.getId())){  
        		// 从在线用户列表移除用户  
	        		onlineUsers.remove(onlineUserView);  
	        		break;  
 
	            }  
	        }
//	        System.out.println("用户退出，剩余"+onlineUsers.size());
    }  
}  


