<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.MyEmail"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>邮件详细信息</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
   	<script src="calendar.js"></script>
   	<script type="text/javascript">
		function EditEmail(lx,id){
        	var form = document.getElementById("form1");
			form.action = "EmailServlet?action=EditEmail&lx="+lx+"&id="+id;
			form.submit();
		}
		function cancel(){
        	var form = document.getElementById("form1");
        	var ly = document.getElementById("ly").value;
        	if(ly==1){
				form.action = "EmailServlet?action=MyEmail&flag=1";
        	}else{
				form.action = "EmailServlet?action=MyDelEmail&flag=1";
        	}
			form.submit();
		}
	</script>  
  </head>
  
  <body>
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
	<%
		MyEmail myEmail = (MyEmail)request.getAttribute("myEmail"); 
		String ly = (String)request.getAttribute("ly"); 
	%>
	<form method="post" id="form1" action="">
	<input type="hidden" name="email_id" id="email_id" <%if(myEmail.getId()!=0){%>value="<%=myEmail.getId()%>"<%}%> >
	<input type="hidden" name="ly" id="ly" value="<%=ly%>" >
	</form>
	<table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=NewEmail'><span style="font-size: 16px">新邮件</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px">收件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">发件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">草稿箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">回收站</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
			发件人：<%=myEmail.getFsr()%>
			</td>
			<td>
			主&nbsp;&nbsp;题：<%=myEmail.getBt()%>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="left">
			收件人：<%=myEmail.getAllfsr()%>
			</td>
			<td>
			日&nbsp;&nbsp;期：<%=myEmail.getJssj().toString().substring(0,19)%>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td colspan="2">
			内&nbsp;&nbsp;容：
			<textarea id="nr" name="nr" cols="1000" rows="50"><%=myEmail.getNr()%></textarea>
		    <ckf:setupCKEditor basePath="/NTJTCW/ckfinder/" editor="nr"/>  
		    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			附&nbsp;&nbsp;件：
		  	<% if(!"".equals(myEmail.getFileUrl())){
		  		String[] fileStr = myEmail.getFileUrl().split(";");
		  		for(int i=0; i<fileStr.length; i++){
		  	%>
		  		<br>
				<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="EmailServlet?action=downLoadMy&URL=<%=fileStr[i]%>&id=<%=myEmail.getId()%>"><%=fileStr[i]%></a>
				<%}%>
			<%}else{%>无
			<%}%>
			</td>
		</tr>
	</table>
	<table  width="100%">
		<tr>
			<td align="center">
				<img width="100px" height="60px" alt="回复" title="回复" style="cursor: pointer;" src="images/small/i18.png" onclick="EditEmail(1,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="回复全部" title="回复全部" style="cursor: pointer;" src="images/small/i19.png" onclick="EditEmail(2,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="转发" title="转发" style="cursor: pointer;" src="images/small/i20.png" onclick="EditEmail(3,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="返回" title="返回" style="cursor: pointer;" src="images/small/i21.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
  </body>
</html>
