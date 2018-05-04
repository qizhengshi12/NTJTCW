<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.ContentGlzd"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>查看单位制度</title>
	<script type="text/javascript">
		function printbody(opr){
			//保存原页面
			var bdhtml=window.document.body.innerHTML;  
			//设置打印页面
			var sprnstr="<!--startprint"+opr+"-->"; 
			var eprnstr="<!--endprint"+opr+"-->"; 
			var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			//打印
			window.print();
			//还原  
			window.document.body.innerHTML=bdhtml; 
		}
	</script>
	</head>
	<body>
		<%
			ContentGlzd Glzd = (ContentGlzd)request.getAttribute("Glzd"); 
		%>
		<form id="form1" name="Glzd" method="post">
		<!--startprint1-->
			<table  width="100%">
				<tr>
					<td align="center" width="50%">
						<strong>标题：</strong><span><%=Glzd.getBt()%></span>
					</td>
					<td align="center" width="50%">
						<strong>文号：</strong><span><%=Glzd.getWh()%></span>
					</td>
				</tr>
				<tr>
					<td align="center" width="50%">
						<strong>实施日期：</strong><span><%=Glzd.getSsrq()%></span>
					</td>
					<td align="center" width="50%">
						<strong>发布单位：</strong><span><%=Glzd.getFbdw()%></span>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
					<span style="font-size: 25px;font-weight: bold;font-stretch: "><%=Glzd.getBt()%></span>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<%=Glzd.getGlzdTxt()%>
					</td>
				</tr>
			<!--endprint1-->
				<tr>
					<td align="center" colspan="2">
					<img alt="打印" width="45px" height="45px" title="打印" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			</table>
		</form>
		<%} %>
	</body>
</html>

