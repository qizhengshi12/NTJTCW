<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gztz"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='../../login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>会议通知详细内容</title>
    <script type="text/javascript">
	function cancel(){
		window.open('','_self');
		window.close();
	}
    </script>
	</head>
	<body>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Gztz gztz = (Gztz)request.getAttribute("gztz");
		%>
		<table width="60%" align="center">
			<tr>
				<td colspan="2" align="center">
				<span style="font-size: 25px">通知名称：<%=gztz.getTzmc() %></span>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				通知时间：<%=gztz.getTzsj().toString().substring(0,16) %>
				</td>
				<td>
				会议地点：<%=gztz.getTzdd() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
				参与人员：<%=gztz.getTzry() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
				通知内容:
				</td>
			</tr>
			<tr >
				<td colspan="2">
				&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" height="300px" align="center" valign="top" style="text-align: left">
				<%=gztz.getTznr() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr >
				<td>
					发起人：<%=gztz.getCzr() %>
				</td>
				<td>
					发起时间：<%=gztz.getCzsj().toString().substring(0,16) %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



