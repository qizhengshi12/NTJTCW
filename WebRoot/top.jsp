<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String Name = UserInfo.getName();
	Integer Num = (Integer)session.getAttribute("onlineUsersNum");
	String gdcontent = (String)session.getAttribute("gdcontent");
	String gdspeed = (String)session.getAttribute("gdspeed");
	String gddirection = (String)session.getAttribute("gddirection");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>南通交通财务审计统计综合信息系统</title>
<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
A:LINK, A:VISITED, A:ACTIVE, A:HOVER
{
	TEXT-DECORATION: NONE;
}
</style>

<script language="JavaScript">
	//刷新
    function fresh(){
    	var xmlhttp;    
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	top.mainFrame.location.reload();
		    }
		  }
		var url = "CheckLoginServlet?action=Refresh";
		xmlhttp.open("post",url,true);
		xmlhttp.send();
    }
//-->
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  	<td height="100">
  	<img src="images/top.png" style="Z-INDEX: -100; LEFT: 0px; WIDTH: 100%; POSITION: absolute; TOP: 0px; HEIGHT: 100" />
		<table border="0" cellspacing="0" cellpadding="0" style="position: absolute;right: 50px;bottom: 40px">
 			<tr valign="bottom">
				<td>
					<div style="cursor: pointer;" onclick="top.mainFrame.location.href='middle.html';" onmouseover="this.style.border='solid 1px #A9A9A9'" onmouseout="this.style.border='solid 0px'">
					<img align="top" src="images/t1.png" width="20px" height="20px"/><font style="font-family: 'Microsoft YaHei'; color: white;font-weight: 100px;font-size: 15px;">首页&nbsp;</font>
					</div>
				</td>
				<td>
					<div style="cursor: pointer;" onclick="top.mainFrame.history.go(-1);" onmouseover="this.style.border='solid 1px #A9A9A9'" onmouseout="this.style.border='solid 0px'">
					<img align="top" src="images/t2.png" width="20px" height="20px"/><font style="font-family: 'Microsoft YaHei'; color: white;font-weight: 100px;font-size: 15px;">后退&nbsp;</font>
					</div>
				</td>
				<td>
					<div style="cursor: pointer;" onclick="top.mainFrame.history.go(1);" onmouseover="this.style.border='solid 1px #A9A9A9'" onmouseout="this.style.border='solid 0px'">
					<img align="top" src="images/t3.png" width="20px" height="20px"/><font style="font-family: 'Microsoft YaHei'; color: white;font-weight: 100px;font-size: 15px;">前进&nbsp;</font>
					</div>
				</td>
				<td>
					<div style="cursor: pointer;" onclick="fresh()" onmouseover="this.style.border='solid 1px #A9A9A9'" onmouseout="this.style.border='solid 0px'">
					<img align="top" src="images/t4.png" width="20px" height="20px"/><font style="font-family: 'Microsoft YaHei'; color: white;font-weight: 100px;font-size: 15px;">刷新&nbsp;</font>
					</div>
				</td>
				<td>
					<div style="cursor: pointer;" onclick="if (confirm('你确定要退出系统吗？')){top.location.href='logout.jsp';}" onmouseover="this.style.border='solid 1px #A9A9A9'" onmouseout="this.style.border='solid 0px'">
					<img align="top" src="images/t5.png" width="20px" height="20px"/><font style="font-family: 'Microsoft YaHei'; color: white;font-weight: 100px;font-size: 15px;">退出&nbsp;</font>
					</div>
				</td>
			</tr>
		</table>
  	</td>
  </tr>
  <tr>
    <td height="35" bgcolor="#E0EEEE">
		<table width="100%" height="35" border="0" cellspacing="0" cellpadding="0">
 			<tr>
				<td width="135px">
					<img src="images/top1.png" style="Z-INDEX: 1; LEFT: 15px; WIDTH: 135px; POSITION: absolute; TOP: 100px; HEIGHT: 25px" />
					<span style="Z-INDEX: 2;POSITION: absolute; LEFT: 20px; TOP: 103px;color: white;font-family: 'Microsoft YaHei';">欢迎您：<%=Name%></span>
				</td>
				<td width="100px">
					<%  Integer MyWjglHF= (Integer)session.getAttribute("MyWjglHF");
						if(MyWjglHF!=0){
					%>
						<a href="WjglServlet?action=getWjglJSList&flag=2" onmouseover="this.style.backgroundColor='#EBEBEB';"  onmouseOut="this.style.backgroundColor='';" target='I2'><font style="font-family: 'Microsoft YaHei';">待办事宜</font></a>
						<div style=" width:15px; height:15px; background-color:#FF8C00; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;"><%=session.getAttribute("MyWjglHF")%></span>
						</div>
					<%
						}else{
					%>
						<font style="font-family: 'Microsoft YaHei';">待办事宜</font>
						<div style=" width:15px; height:15px; background-color:#BDBDBD; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;">0</span>
						</div>
					<%
						}
					%>
				</td>
				<td width="100px">
					<%
						Integer MyGztzHF= (Integer)session.getAttribute("MyGztzHF");
						if(MyGztzHF!=0){
					%>
						<a href="GztzServlet?action=getGztzJSList&sfhf=3" onmouseover="this.style.backgroundColor='#EBEBEB';"  onmouseOut="this.style.backgroundColor='';" target='I2'><font style="font-family: 'Microsoft YaHei';">会议通知</font></a>
						<div style=" width:15px; height:15px; background-color:#FF8C00; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;"><%=session.getAttribute("MyGztzHF")%></span>
						</div>
					<%
						}else{
					%>
						<font style="font-family: 'Microsoft YaHei';">会议通知</font>
						<div style=" width:15px; height:15px; background-color:#BDBDBD; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;">0</span>
						</div>
					<%
						}
					%>
				</td>
				<td width="100px">
					<% 
			   			Integer MyMessage= (Integer)session.getAttribute("MyMessage");
						if(MyMessage!=0){
					%>
						<a href="MessageServlet?action=MyMessage&srbt=" onmouseover="this.style.backgroundColor='#EBEBEB';"  onmouseOut="this.style.backgroundColor='';" target='I2'><font style="font-family: 'Microsoft YaHei';">待阅短信</font></a>
						<div style=" width:15px; height:15px; background-color:#FF8C00; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;"><%=session.getAttribute("MyMessage")%></span>
						</div>
					<%
						}else{
					%>
						<font style="font-family: 'Microsoft YaHei';">待阅短信</font>
						<div style=" width:15px; height:15px; background-color:#BDBDBD; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;">0</span>
						</div>
					<%
						}
					%>
				</td>
				<td width="100px">
					<% 
			   			Integer MyEmail= (Integer)session.getAttribute("MyEmail");
						if(MyEmail!=0){
					%>
						<a href="EmailServlet?action=MyEmail&flag=1" onmouseover="this.style.backgroundColor='#EBEBEB';"  onmouseOut="this.style.backgroundColor='';" target='I2'><font style="font-family: 'Microsoft YaHei';">待阅邮件</font></a>
						<div style=" width:15px; height:15px; background-color:#FF8C00; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;"><%=session.getAttribute("MyEmail")%></span>
						</div>
					<%
						}else{
					%>
						<font style="font-family: 'Microsoft YaHei';">待阅邮件</font>
						<div style=" width:15px; height:15px; background-color:#BDBDBD; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;">0</span>
						</div>
					<%
						}
					%>
				</td>
				<td width="100px">
					<% 
			  			Integer MyZdxmsbSH= (Integer)session.getAttribute("MyZdxmsbSH");
						if(MyZdxmsbSH!=0){
					%>
						<a href="ZdxmsbServlet?action=getZdxmsbList&flag=2" onmouseover="this.style.backgroundColor='#EBEBEB';"  onmouseOut="this.style.backgroundColor='';" target='I2'><font style="font-family: 'Microsoft YaHei';">文件审核</font></a>
						<div style=" width:15px; height:15px; background-color:#FF8C00; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;"><%=session.getAttribute("MyZdxmsbSH")%></span>
						</div>
					<%
						}else{
					%>
						<font style="font-family: 'Microsoft YaHei';">文件审核</font>
						<div style=" width:15px; height:15px; background-color:#BDBDBD; border-radius:25px;display: inline;float: left;">
							<span style="height:15px; line-height:15px; display:block; color:#ffffff; text-align:center;font-size:8px;">0</span>
						</div>
					<%
						}
					%>
				</td>
				<td width="35%" nowrap="nowrap">
               		<div align="right">
                		<span>
                			<marquee style="font-family: 'Microsoft YaHei';"
			                <%if(gdspeed!=null&&!"".equals(gdspeed)){%>
								scrollamount ="<%=gdspeed%>"
							<%}else{ %>
								scrollamount ="2"
							<%} %>
			               <%if(gddirection!=null&&!"".equals(gddirection)){%>
								direction ="<%=gddirection%>"
							<%}else{ %>
								direction ="left"
							<%} %>
			                	scrolldelay ="n" behavior ="scroll" loop ="-1" onmouseover= "this.stop()" onmouseout ="this.start()">
			                <%if(gdcontent!=null&&!"".equals(gdcontent)){%>
								<%=gdcontent%>
							<%}else{ %>
							欢迎使用本平台。
							<%} %>
                			</marquee>
						</span>
					</div>
				</td>
			</tr>
		</table>
    </td>
  </tr>
</table>
		<%} %>
</body>
</html>