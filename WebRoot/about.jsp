<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>����ϵͳ������</title>
</head>

<body>
<div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">    
<img src="images/aboutBG.jpg" height="100%" width="100%"/>   
</div> 
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>����˵���飺</b><a href="HandleFileServlet?action=downLoad" onclick="javascript:return confirm('ȷ�����ش��ļ���');">��������</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ϵͳ������
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������λ����ͨ�ǻ۽�ͨ�Ƽ����޹�˾
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϵ��ʽ��0513-83534898
</body>
</html>