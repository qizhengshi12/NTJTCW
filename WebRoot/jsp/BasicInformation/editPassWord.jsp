<%@page contentType="text/html;charset=GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='../../login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>修改密码</title>
    <script type="text/javascript">
    function save(){
    	var number1 = document.getElementById("number1").value;
    	var number2 = document.getElementById("number2").value;
    	var number3 = document.getElementById("number3").value;
    	if(number1==""){
    		alert("请输入原密码");
    		return;
    	}
    	if(number2==""){
    		alert("请输入新密码");
    		return;
    	}
    	if(number2.length < 6){
    		alert("密码至少为6个字符");
    		return;
    	}
    	if(number3==""){
    		alert("请确认新密码");
    		return;
    	}
    	if(number2!=number3){
    		alert("两次密码输入不相同");
    		return;
    	}
		var form = document.getElementById("form");
		form.action = "ZzxxServlet?action=editPassWord&oldPS="+number1+"&newPS="+number2;
		form.submit();
	}
	function back(){
		var form = document.getElementById("form");
		form.action = "ZzxxServlet?action=getMyAccount";
		form.submit();
	}
    </script>

  </head>
  
   <body>
  	<% if(request.getParameter("result")!= null) {%>
	    <script>
	         alert('<%=request.getParameter("result")%>');
	    </script> 
	<% } %>
	<form id="form" method="post" action=""></form>
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/postini-128.png">修改密码</h1>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="right" width="40%">
					原密码：
				</td>
				<td align="left">
					<input type="text" name="number1" id="number1"  size="41"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					新密码：
				</td>
				<td align="left">
					<input type="password" name="number2" id="number2"  size="41"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					确认密码：
				</td>
				<td align="left">
					<input type="password" name="number3" id="number3"  size="41"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" colspan="2">
				（密码至少为6个字符，由英文字母、数字、及下划线组成，不分大小写）
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="back()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
