<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<%
  	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
  	//获取登录IP地址
	String ip = request.getHeader("x-forwarded-for");
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}
	//记录登录日志
	String logStr = "退出用户："+UserInfo.getName()+";用户名："+UserInfo.getUsername()+";IP地址："+ip;
	getServletContext().log(logStr);
   session.invalidate();
   out.print("<script>top.location.href='login.jsp';</script>"); 
   
%>
