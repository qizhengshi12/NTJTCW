<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gztz"%>
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
    <title>����֪ͨ��ϸ����</title>
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
         	Gztz gztz = (Gztz)request.getAttribute("gztz");
		%>
		<table width="60%" align="center">
			<tr>
				<td colspan="2" align="center">
				<span style="font-size: 25px">֪ͨ���ƣ�<%=gztz.getTzmc() %></span>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				֪ͨʱ�䣺<%=gztz.getTzsj().toString().substring(0,16) %>
				</td>
				<td>
				����ص㣺<%=gztz.getTzdd() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
				������Ա��<%=gztz.getTzry() %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
				֪ͨ����:
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
					�����ˣ�<%=gztz.getCzr() %>
				</td>
				<td>
					����ʱ�䣺<%=gztz.getCzsj().toString().substring(0,16) %>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<img alt="�ر�" width="100px" height="60px" title="�ر�" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



