<%@page contentType="text/html;charset=GBK"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�ҵ��˻�</title>
 	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
 	<script type="text/javascript">
 		function updateInf(){
			var form = document.getElementById("form1");
			form.action = "ZzxxServlet?action=editMyAccount";
			form.submit();
 		}
 		function updatePas(){
			window.location.href = "<%=basePath%>jsp/BasicInformation/editPassWord.jsp";
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
         //��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         ContentZzxx zzxx = (ContentZzxx)request.getAttribute("Zzxx");
	%>
		<form method="post" id="form1"></form>
		<table width="100%">
			<tr>
				<td align="center" colspan="5">
					<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/about-me.png">��Ա��ϸ��Ϣ</h1>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
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
				<td align="center" width="15%" rowspan="9">
					<img style="cursor: pointer" width="150px" height="200px" src="UserFile/<%=zzxx.getFileURL() %>"/>
				</td>
			</tr>
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="5">
					<img alt="�޸��ҵĻ�����Ϣ" width="160px" height="60px" title="�޸��ҵĻ�����Ϣ" style="cursor: pointer;" src="images/small/i14.png"  onclick="updateInf()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="�޸�����" width="100px" height="60px" title="�޸�����" style="cursor: pointer;" src="images/small/i12.png"  onclick="updatePas()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
				</td>
			</tr>
		</table>
  </body>
</html>
