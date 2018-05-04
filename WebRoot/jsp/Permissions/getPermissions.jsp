<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限列表</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
		function SetP(username){
        	var form = document.getElementById("form1");
        	document.getElementById("ThePerson").innerText = username;
			form.action = "PermissionsServlet?action=PermissionsEdit&username="+username;
			form.submit();
		}
		function openSelectFree(name,id){
        	var form = document.getElementById("form2");
			var iWidth = 850;//弹出窗口的宽度;
			var iHeight = 450;//弹出窗口的高度;
			var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;//获得窗口的水平位置;var form = document.getElementById("form2");
        	form.target="preview_page";
			var popupWin = window.open('', 'preview_page','height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
			form.action = "ZzxxServlet?action=getThreeSelectFree&name="+name+"&id="+id;
			form.submit();
		}
		function QueP(){
        	var form = document.getElementById("form1");
        	var username;
        	var ry = document.getElementById("ry").value;
        	var ryID = document.getElementById("ryID").value;
        	if(ryID==""){
        		alert("请选择人员");
        		return;
        	}else{
        		username = ryID.split(",")[0];
        		document.getElementById("ThePerson").innerText = ry.split(",")[0];
        	}
			form.action = "PermissionsServlet?action=PermissionsEdit&username="+username;
			form.submit();
		}
		function ReSetP(){
        	document.getElementById("ry").value = "";
        	document.getElementById("ryID").value = "";
		}
	</script>
  </head>
  
  <body>
  <form method="post" id="form1" action="" target="content"></form>
  <form id="form2"  method="post"></form>
	  <table width="100%" height="100%">
		<tr>
			<td width="10%" align="center" height="20px">
				主权限
			</td>
			<td width="1px" height="20px" bgcolor="#5CACEE"></td>
			<td width="18%" align="center">
				<input type="button" class="button1" value="信息员" onclick="SetP('信息员')">
			</td>
			<td width="18%" align="center">
				<input type="button" class="button1" value="一般用户" onclick="SetP('一般用户')">
			</td>
			<td width="18%" align="center">
				<input type="button" class="button1" value="财务负责人" onclick="SetP('财务负责人')">
			</td>
			<td width="18%" align="center">
				<input type="button" class="button1" value="审计负责人" onclick="SetP('审计负责人')">
			</td>
			<td width="18%" align="center">
				<input type="button" class="button1" value="统计负责人" onclick="SetP('统计负责人')">
			</td>
		</tr>
		<tr height="1px">
			<td colspan="8">
				<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="center" height="20px">
				特殊权限
			</td>
			<td width="1px" height="20px" bgcolor="#5CACEE"></td>
			<td align="left" colspan="6">
				选择人员<input name="ry" id="ry" type="text" value="" onclick="openSelectFree('ry','ryID')" readonly="readonly"/>
				<input name="ryID" id="ryID" type="hidden" value=""/>
				<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="middle" width="40px" height="40px" alt="重置" title="重置" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				（每次只能选择一个人进行操作）
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="QueP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr height="1px">
			<td colspan="8">
				<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="center">
				权限表
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td colspan="6">
			当前用户：<span id="ThePerson" style="color: #EE30A7;"></span>
			<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="90%" frameborder="0" marginwidth="0" marginheight="0">
			</iframe>
			</td>
		</tr>
	  </table>
  </body>
</html>
