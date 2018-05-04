<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.PostInformation"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>窗口新闻</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<script type="text/javascript">
	function custom_close(){
		window.open('','_self');
		window.close();
	}
    
	function saveSH(){
		var form = document.getElementById("form1");
		form.action = "PostInformationServlet?action=postInfSH";
		form.submit();
	}
	</script>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
  </head>
  
  <body>
  <% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  	<%
		PostInformation postInformation = (PostInformation)request.getAttribute("postInformation");
        String flag = (String)request.getAttribute("flag");
	%>
	
	<table width="75%" height="100%" align="center">
			<tr>
				<td align="center" colspan="2">
					<h2><img width="80px" height="80px" align="middle" src="images/small/picture.png"><%=postInformation.getBt() %></h2>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
			<%if(!"".equals(postInformation.getPhotoURL())&&postInformation.getPhotoURL()!=null){%>
				<td valign="top" align="center" width="35%">
				<img src="UserFile/<%=postInformation.getPhotoURL() %>" width="300" height='200' border='1'> 
				</td>
				<td valign="top" align="left">
					<%if(postInformation.getNr()!=null){%><%=postInformation.getNr() %><%}%>
				</td>
			<%}else{%>
				<td valign="top" align="left" colspan="2">
					<%if(postInformation.getNr()!=null){%><%=postInformation.getNr() %><%}%>
				</td>
			<%}%>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					发布时间：
				<%=postInformation.getTjsj() %>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					发布人员：
				<%=postInformation.getTjr() %>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					发布单位：
				<%=postInformation.getDw() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%if(!"".equals(postInformation.getFileURL())&&postInformation.getFileURL()!=null){%>
			<tr>
				<td colspan="3">
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(postInformation.getFileURL())&&postInformation.getFileURL()!=null){
				  		String[] fileStr = postInformation.getFileURL().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="PostInformationServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>无
					<%}%>
				<iframe id='ifm' name='ifm' style="display:none"></iframe>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%}%>
			<%if("1".equals(flag)){%>
			<tr>
				<td align="center" colspan="2">
					<img alt="关闭" width="45px" height="45px" title="关闭" style="cursor: pointer;"  src="images/small/close.png" onclick="custom_close()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
			<%}%>
			<%if("2".equals(flag)){%>
			<tr>
				<td align="center" colspan="2">
				<form method="post" id="form1">
				审批意见：
				<input type="radio" name="sfsh" value="2" checked="checked">同意
				<input type="radio" name="sfsh" value="3">驳回
				<input type="hidden" name="nrid" id="nrid" value="<%=postInformation.getId()%>">
				</form>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/save-as.png" onclick="saveSH();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
			<%}%>
		</table>
  </body>
</html>
