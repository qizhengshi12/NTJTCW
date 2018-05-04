<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%@ page session="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增人员</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
  </head>
  
  <body>
  <%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ContentZzxx zzxx = (ContentZzxx)request.getAttribute("zzxx");
  %>
   <form method="post" id="form1">
		<table width="100%">
			<tr class="tr3">
				<td align="center" colspan="5">
					<h1 style="font-family:verdana">人员详细信息</h1>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" width="18%">
					姓名：
				</td>
				<td align="left" width="30%">
					<input type="text" value="<%=zzxx.getName() %>" class="STYLE1" readonly/>
				</td>
				<td align="center" width="12%">
					性别：
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
					出生日期：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getBirth() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					身份证号：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getPcard() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					学历：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getEducation() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					毕业院校：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getSchool() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					参加工作时间：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getWorktime() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					职称：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJoblevel() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					手机号：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getPhone() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					办公电话：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getWorkphone() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					所属单位：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getCompany() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					二级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart1() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					三级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart2() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					四级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart3() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					岗位（职务）：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJob() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					岗位职责：
				</td>
				<td align="left">
					<textarea rows="3" cols="25"><%=zzxx.getJobdes() %></textarea>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					角色：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getRoles() %>" class="STYLE1" readonly/>
				</td>
				<td align="center">
					用户名：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getUsername() %>" class="STYLE1" readonly/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" colspan="5">
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		</form>
		<%} %>
  </body>
</html>