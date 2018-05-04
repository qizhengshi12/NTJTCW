<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="com.safety.servlet.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("onlineUsersNum", MySessionServlet.getOnlineUsers().size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>南通交通财务审计统计综合信息系统</title>
</head>
<frameset rows="135,*,30" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <frame src="middle.html" name="mainFrame" id="mainFrame" />
  <frame src="down.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes>
</html>
