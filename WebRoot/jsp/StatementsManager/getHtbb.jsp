<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%> 
<%@page import="com.safety.entity.Permissions"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
	else{
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>��̨����֪ͨ</title>
	<base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
		function selChange1(){
			var sel = document.getElementById("sel1");
			var str = sel.options[sel.selectedIndex].value;
			query(str);
		}
		function query(str){
        	var form = document.getElementById("form1");
			if(str==1){
				form.action = "HtbbServlet?action=setTime&bbID=1";
				form.submit();
			}else if(str==2){
				form.action = "HtbbServlet?action=setTime&bbID=2";
				form.submit();
			}else if(str==3){
				form.action = "HtbbServlet?action=setTime&bbID=3";
				form.submit();
			}else if(str==4){
				form.action = "HtbbServlet?action=setTime&bbID=4";
				form.submit();
			}else if(str==5){
				form.action = "HtbbServlet?action=setTime&bbID=5";
				form.submit();
			}else if(str==6){
				form.action = "HtbbServlet?action=setTime&bbID=6";
				form.submit();
			}else if(str==7){
				form.action = "HtbbServlet?action=setTime&bbID=7";
				form.submit();
			}else if(str==8){
				form.action = "HtbbServlet?action=setTime&bbID=8";
				form.submit();
			}else if(str==9){
				form.action = "HtbbServlet?action=setTime&bbID=9";
				form.submit();
			}else if(str==10){
				form.action = "HtbbServlet?action=setTime&bbID=10";
				form.submit();
			}
		
		}
	</script>
  </head>
  
  <body>
  <form method="post" id="form1" action="" target="content"></form>
  <form method="post" id="form2" action=""></form>
	<table width="100%" height="100%">
		<tr>
			<td height="35px">
				ѡ�񱨱�
			  	<select id="sel1" onchange="selChange1()" class="STYLE1">
					<option value=""></option>
					<option value="1">Ԥ��ִ�б�</option>
					<option value="2">��ҵ��λ��Ҫ����ָ���</option>
					<option value="3">�������ѱ�</option>
					<option value="4">�ʲ���ծ��</option>
					<option value="5">�����</option>
					<option value="6">��ʵ�շ����߼����Բ��</option>
					<option value="7">��Ҫ����ָ��ͳ�Ʊ������С��˹ܴ���</option>
					<option value="8">��Ҫ����ָ��ͳ�Ʊ���������</option>
					<option value="9">���˳�վ������ͳ�Ʊ����˼��ţ�</option>
					<option value="10">����ǩ֤�¶�ͬ�ȱ����¾֣�</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height= "1px">
				<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
			<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
			</iframe>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
