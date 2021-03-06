<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.ContentFl"%>  
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
    <title>查看法律</title>
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
		function cancel(){
			window.open('','_self');
			window.close();
		}
	</script>
	</head>
	<body>
		<%
			ContentFl Fl = (ContentFl)request.getAttribute("Fl"); 
			String flag = (String)request.getAttribute("flag"); 
		%>
		<form id="form1" name="Fl" method="post">
		<!--startprint1-->
			<table  width="100%">
				<tr>
					<td>
						<strong>标题：</strong><span><%=Fl.getBt()%></span>
					</td>
					<td width="250px">
						<strong>文号：</strong><span><%=Fl.getWh()%></span>
					</td>
				</tr>
				<tr>
					<td>
						<strong>实施日期：</strong><span><%=Fl.getSsrq()%></span>
					</td>
					<td>
						<strong>发布单位：</strong><span><%=Fl.getFbdw()%></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<span style="font-size: 25px;font-weight: bold;font-stretch: "><%=Fl.getBt()%></span>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<%=Fl.getFlTxt()%>
					</td>
				</tr>
			<!--endprint1-->
			<%if("1".equals(flag)){ %>
				<tr>
					<td align="center" colspan="2">
						<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			<%}else{ %>
				<tr>
					<td align="center" colspan="2">
					<img alt="打印" width="45px" height="45px" title="打印" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			<%} %>
			</table>
		</form>
		<%} %>
	</body>
</html>

