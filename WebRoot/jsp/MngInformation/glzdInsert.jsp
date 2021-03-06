<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
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
    <title>新增单位制度</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function Save(){
			var bt = document.getElementById("bt").value;
			var ssrq = document.getElementById("ssrq").value;
			bt = bt.replace(/\ /g,"");
        	if(bt==""){
        		alert("请输入标题！");
        		return;
        	}
        	if(ssrq==""){
        		alert("请输入实施日期！");
        		return;
        	}
			var form = document.getElementById("form1");
			form.action = "GlzdServlet?action=saveGlzd";
			form.submit();
		}
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "GlzdServlet?action=getGlzd";
			form.submit();
        }
	    </script>
	</head>
	<body>
		<%
			String fatherid = (String)request.getParameter("fatherid"); 
		%>
		<form id="form1" name="Glzd" method="post">
		<input type="hidden" name="fatherid" id="fatherid" value="<%=fatherid%>">
			<table  width="100%">
				<tr>
					<td>
					标题：<input name="bt" id="bt" type="text" value=""/>
					文号：<input name="wh" id="wh" type="text" value=""/>
					实施日期：<input name="ssrq" id="ssrq" type="text" size="10" readonly/>
          <input name="Button" class="button1" onclick="setDay(document.Glzd.ssrq);" type="button" value="选择">
					<input type="radio" name="sfgk" checked="checked" value="0">对外公开
					<input type="radio" name="sfgk" value="1">单位内部
					</td>
				</tr>
				<tr>
					<td>
					<textarea id="GlzdTxt" name="GlzdTxt" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="GlzdTxt"/>  
				    <ck:replace replace="GlzdTxt" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
			</table>
		</form>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="提交" width="45px" height="45px" title="提交" style="cursor: pointer;"  src="images/small/send.png" onclick="Save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>

