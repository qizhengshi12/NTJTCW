<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%@ page session="true" %>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>������Ա</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
  </head>
  
  <body>
  <%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ContentZzxx zzxx = (ContentZzxx)request.getAttribute("zzxx");
  %>
   <form method="post" id="form1">
		<table width="100%">
			<tr class="tr3">
				<td align="center" colspan="5">
					<h1 style="font-family:verdana">��Ա��ϸ��Ϣ</h1>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" width="18%">
					������
				</td>
				<td align="left" width="30%">
					<input type="text" value="<%=zzxx.getName() %>" class="STYLE1" readonly/>
				</td>
				<td align="center" width="12%">
					�Ա�
				</td>
				<td align="left" width="25%">
					<input type="text" value="<%=zzxx.getSex() %>" class="STYLE1" readonly/>
				</td>
				<td align="center" width="15%" rowspan="9" valign="top">
					<img style="cursor: pointer" width="150px" height="180px" src="UserFile/<%=zzxx.getFileURL() %>"/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�������ڣ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getBirth() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					���֤�ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getPcard() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					ѧ����
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getEducation() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					��ҵԺУ��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getSchool() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�μӹ���ʱ�䣺
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getWorktime() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					ְ�ƣ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJoblevel() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�ֻ��ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getPhone() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					�칫�绰��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getWorkphone() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					������λ��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getCompany() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart1() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart2() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					�ļ����ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart3() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					��λ��ְ�񣩣�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJob() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					��λְ��
				</td>
				<td align="left">
					<textarea rows="3" cols="25"><%=zzxx.getJobdes() %></textarea>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					��ɫ��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getRoles() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					�û�����
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getUsername() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" colspan="5">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		</form>
		<%} %>
  </body>
</html>