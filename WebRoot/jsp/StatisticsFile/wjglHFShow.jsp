<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Wjglhf"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='../../login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�ظ��ļ���������</title>
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Wjglhf wjglhf = (Wjglhf)request.getAttribute("wjglhf");
		%>
  <iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table width="60%" align="center">
			<tr>
				<td colspan="2" align="center">
				<span style="font-size: 25px">�ظ�����</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="hfnr" name="hfnr" cols="1000" rows="50"><%=wjglhf.getHfnr() %></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="hfnr"/>  
				    <ck:replace replace="hfnr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td width="50%" align="left">
				�ظ�ʱ�䣺<%=wjglhf.getHfsj() %>
				</td>
				<td width="50%" align="left">
				�Ƿ�ʱ��<%if("��ʱ".equals(wjglhf.getSfcs())) {%><img align="middle"  alt="��ʱ" title="��ʱ" src="images/small/wait-red.png"><span style="color: red;">��ʱ</span><%}else {%><img align="middle"  alt="δ��ʱ" title="δ��ʱ" src="images/small/wait-green.png">δ��ʱ<%}%>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
				��&nbsp;&nbsp;����
			  	<% if(!"".equals(wjglhf.getFileUrl())){
			  		String[] fileStr1 = wjglhf.getFileUrl().split(";");
			  		for(int j=0; j<fileStr1.length; j++){
			  	%>
			  		<br>
					<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="WjglServlet?action=downLoadHF&URL=<%=fileStr1[j]%>&id=<%=wjglhf.getId()%>" target="ifm"><%=fileStr1[j]%></a>
					<%}%>
				<%}else{%>��
				<%}%>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<table  width="100%">
			<tr>
				<td align="center">
				<img alt="�ر�" width="100px" height="60px" title="�ر�" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



