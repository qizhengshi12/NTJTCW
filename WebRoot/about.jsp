<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>关于系统和作者</title>
</head>

<body>
<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
<img src="images/aboutBG.jpg" height="100%" width="100%"/>   
</div> 
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>操作说明书：</b><a href="HandleFileServlet?action=downLoad" onclick="javascript:return confirm('确定下载此文件？');">附件下载</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关于系统和作者
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开发单位：南通智慧交通科技有限公司
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系方式：0513-83534898
</body>
</html>