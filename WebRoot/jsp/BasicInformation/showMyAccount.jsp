<%@page contentType="text/html;charset=GBK"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>我的账户</title>
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
         //从request域中取得要显示的某页信息
         ContentZzxx zzxx = (ContentZzxx)request.getAttribute("Zzxx");
	%>
		<form method="post" id="form1"></form>
		<table width="100%">
			<tr>
				<td align="center" colspan="5">
					<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/about-me.png">人员详细信息</h1>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
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
				<td align="center" width="15%" rowspan="9">
					<img style="cursor: pointer" width="150px" height="200px" src="UserFile/<%=zzxx.getFileURL() %>"/>
				</td>
			</tr>
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="5">
					<img alt="修改我的基本信息" width="160px" height="60px" title="修改我的基本信息" style="cursor: pointer;" src="images/small/i14.png"  onclick="updateInf()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="修改密码" width="100px" height="60px" title="修改密码" style="cursor: pointer;" src="images/small/i12.png"  onclick="updatePas()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
				</td>
			</tr>
		</table>
  </body>
</html>
