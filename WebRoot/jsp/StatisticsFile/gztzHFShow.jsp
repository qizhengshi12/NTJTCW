<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gztzhf"%>
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
    <title>�鿴�ظ����</title>
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
         	ArrayList GztzhfList = (ArrayList)request.getAttribute("GztzhfList");
		%>
		<table width="75%" height="10%" align="center">
			<tr>
				<td colspan="5" align="center">
				<span style="font-size: 25px">�ظ����</span>
				</td>
			</tr>
			<tr >
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<table width="75%"  align="center">
			<tr class="tr1" >
				<td align="center" width="5%"  height="30px">
					���
				</td>
				<td align="center" width="34%">
					֪ͨ����
				</td>
				<td align="center" width="10%">
					�ظ���
				</td>
				<td align="center" width="15%">
					�ظ�ʱ��
				</td>
				<td align="center" width="35%">
					�ظ�����
				</td>
			</tr>
		<%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	int count = GztzhfList.size();
         	/*����ÿ������Ϣ������ʾ*/
           for(int i=0;i< GztzhfList.size();i++){
           	Gztzhf gztzhf = (Gztzhf)GztzhfList.get(i);
		%>
			<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
				<td align="center" height="30px">
					<%if(GztzhfList.size()!=0){%>
					<%=i+1%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getTzmc()!=null&&!"".equals(gztzhf.getTzmc())){%>
					<a href="GztzServlet?action=showGztzXF&gztzid=<%=gztzhf.getTzid()%>" target="_blank"><%=gztzhf.getTzmc()%></a>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfr()!=null&&!"".equals(gztzhf.getHfr())){%>
					<%=gztzhf.getHfr()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfsj()!=null&&!"".equals(gztzhf.getHfsj())){%>
					<%=gztzhf.getHfsj().toString().substring(0,10)%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfnr()!=null&&!"".equals(gztzhf.getHfnr())){%>
					<%=gztzhf.getHfnr()%>
					<%}else{ %>
					δ�ظ�
					<%} %>
				</td>
			</tr>
			<% }%>
		</table>
		<table width="75%" height="15%" align="center">
			<tr >
				<td>
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="�ر�" width="100px" height="60px" title="�ر�" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



