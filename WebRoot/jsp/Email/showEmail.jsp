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
    <title>�ʼ���ϸ��Ϣ</title>
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
				<a href='EmailServlet?action=NewEmail'><span style="font-size: 16px">���ʼ�</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px">�ռ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">������</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">�ݸ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">����վ</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
			�����ˣ�<%=myEmail.getFsr()%>
			</td>
			<td>
			��&nbsp;&nbsp;�⣺<%=myEmail.getBt()%>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="left">
			�ռ��ˣ�<%=myEmail.getAllfsr()%>
			</td>
			<td>
			��&nbsp;&nbsp;�ڣ�<%=myEmail.getJssj().toString().substring(0,19)%>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td colspan="2">
			��&nbsp;&nbsp;�ݣ�
			<textarea id="nr" name="nr" cols="1000" rows="50"><%=myEmail.getNr()%></textarea>
		    <ckf:setupCKEditor basePath="/NTJTCW/ckfinder/" editor="nr"/>  
		    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			��&nbsp;&nbsp;����
		  	<% if(!"".equals(myEmail.getFileUrl())){
		  		String[] fileStr = myEmail.getFileUrl().split(";");
		  		for(int i=0; i<fileStr.length; i++){
		  	%>
		  		<br>
				<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="EmailServlet?action=downLoadMy&URL=<%=fileStr[i]%>&id=<%=myEmail.getId()%>"><%=fileStr[i]%></a>
				<%}%>
			<%}else{%>��
			<%}%>
			</td>
		</tr>
	</table>
	<table  width="100%">
		<tr>
			<td align="center">
				<img width="100px" height="60px" alt="�ظ�" title="�ظ�" style="cursor: pointer;" src="images/small/i18.png" onclick="EditEmail(1,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="�ظ�ȫ��" title="�ظ�ȫ��" style="cursor: pointer;" src="images/small/i19.png" onclick="EditEmail(2,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="ת��" title="ת��" style="cursor: pointer;" src="images/small/i20.png" onclick="EditEmail(3,'<%=myEmail.getSendid()%>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="100px" height="60px" alt="����" title="����" style="cursor: pointer;" src="images/small/i21.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
  </body>
</html>
