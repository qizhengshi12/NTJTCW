<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Jhzj"%>
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
    <title>计划总结具体内容</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
   <style type="text/css">
   img {border:0px;}
   </style>
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
         	Jhzj jhzj = (Jhzj)request.getAttribute("jhzj");
		%>
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table width="75%" align="center">
			<tr>
				<td colspan="3" align="center">
				<span style="font-size: 25px"><%=jhzj.getJhlx2() %><%=jhzj.getJhlx1() %>:<%=jhzj.getJhmc() %></span>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td style="padding-left: 50px">
				起始时间：<%=jhzj.getJhsj1() %>
				</td>
				<td>
					&nbsp; 
				</td>
				<td style="padding-left: 50px">
				结束时间：<%=jhzj.getJhsj2() %>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="3">
				内容：
					<textarea id="nr" name="nr" cols="1000" rows="50"><%=jhzj.getJhnr() %></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="nr"/>  
				    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-left: 50px">
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(jhzj.getFileUrl())&&jhzj.getFileUrl()!=null){
				  		String[] fileStr = jhzj.getFileUrl().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="JhzjServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>无
					<%}%>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td style="padding-left: 50px">
				提交人：<%=jhzj.getCzr() %>
				</td>
				<td style="padding-left: 50px">
				提交人单位：<%=jhzj.getCzrdw() %>
				</td>
				<td style="padding-left: 50px">
				提交时间：<%=jhzj.getCzsj().toString().substring(0,16) %>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



