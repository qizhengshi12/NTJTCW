<%@page contentType="text/html;charset=GBK"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Integer Num = (Integer)session.getAttribute("onlineUsersNum");
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String company = UserInfo.getCompany();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
<head>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</head>
<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="images/down.png">
  <tr>
    <td width="20%" align="left">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <font color="#ffffff" style="font-family: 'Microsoft YaHei';" size="2">在线人数：<%=Num%>人</font>
    </td>
    <td width="20%" align="center">
    <font color="#ffffff" style="font-family: 'Microsoft YaHei';" size="2">当前登陆：<%=company %></font>
    </td>
    <td width="40%" align="center">
    <font color="#ffffff" style="font-family: 'Microsoft YaHei';" size="2">开发单位：南通智慧交通科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;联系电话：0513-83534898</font>
    </td>
    <td width="20%" align="right">
    <font color="#ffffff" style="font-family: 'Microsoft YaHei';" size="2">当前版本：2014 V1.0</font>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
