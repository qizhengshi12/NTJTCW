<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<%
  	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
  	//��ȡ��¼IP��ַ
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
	//��¼��¼��־
	String logStr = "�˳��û���"+UserInfo.getName()+";�û�����"+UserInfo.getUsername()+";IP��ַ��"+ip;
	getServletContext().log(logStr);
   session.invalidate();
   out.print("<script>top.location.href='login.jsp';</script>"); 
   
%>
