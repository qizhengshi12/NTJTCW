<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Checkid"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
    Checkid checkid = (Checkid)request.getAttribute("checkid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>审核权限列表</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
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
		function save(){
        	var form = document.getElementById("form1");
        	var zdxmsbName = document.getElementById("zdxmsbName").value;
        	var zdxmsbID = document.getElementById("zdxmsbID").value;
        	var postInfName = document.getElementById("postInfName").value;
        	var postInfID = document.getElementById("postInfID").value;
        	if(zdxmsbID==""||postInfID==""){
        		alert("请选择人员");
        		return;
        	}else{
        		document.getElementById("zdxmsbName").value = zdxmsbName.split(",")[0];
        		document.getElementById("zdxmsbID").value = zdxmsbID.split(",")[0];
        		document.getElementById("postInfName").value = postInfName.split(",")[0];
        		document.getElementById("postInfID").value = postInfID.split(",")[0];
        	}
			form.action = "PermissionsServlet?action=CheckIDSave";
			form.submit();
		}
		function ReSetP(){
        	document.getElementById("zdxmsbName").value = "";
        	document.getElementById("zdxmsbID").value = "";
		}
		function ReSetP1(){
        	document.getElementById("postInfName").value = "";
        	document.getElementById("postInfID").value = "";
		}
	</script>
  </head>
  
  <body>
  <% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
  <% } %>
  <form id="form1"  method="post">
  		<input name="ID" id="ID" type="hidden" value="<%=checkid.getId()%>"/>
	  <table width="100%">
		<tr>
			<td align="center">
			<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/v-card.png">设置审核人</h1>
			</td>
		</tr>
		<tr>
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				<img align="middle" width="48px" height="48px" src="images/small/accessories-text-editor1.png">
				重大项目申报
				<img align="middle" width="48px" height="48px" src="images/small/arrow-right.png">
				审核人员<input name="zdxmsbName" id="zdxmsbName" type="text" onclick="openSelectFree('zdxmsbName','zdxmsbID')" value="<%=checkid.getZdxmsbName()%>" readonly="readonly"/>
				<input name="zdxmsbID" id="zdxmsbID" type="hidden" value="<%=checkid.getZdxmsbID()%>"/>
				<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('zdxmsbName','zdxmsbID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="middle" width="40px" height="40px" alt="重置" title="重置" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				（每次只能选择一个人进行操作）
			</td>
		</tr>
		<tr>
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				<img align="middle" width="48px" height="48px" src="images/small/gallery.png">
				首页窗口新闻
				<img align="middle" width="48px" height="48px" src="images/small/arrow-right.png">
				审核人员<input name="postInfName" id="postInfName" type="text" onclick="openSelectFree('postInfName','postInfID')" value="<%=checkid.getPostInfName()%>" readonly="readonly"/>
				<input name="postInfID" id="postInfID" type="hidden" value="<%=checkid.getPostInfID()%>"/>
				<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('postInfName','postInfID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="middle" width="40px" height="40px" alt="重置" title="重置" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP1()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				（每次只能选择一个人进行操作）
			</td>
		</tr>
		<tr>
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="center">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center">
				<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/save-as.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	  </table>
	</form>
	<form id="form2"  method="post"></form>
	<%} %>
  </body>
</html>
