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
    <title>新增会议通知</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
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
	   		var tzmc = document.getElementById("tzmc").value;
	   		var tzsj = document.getElementById("tzsj").value;
	   		var ry = document.getElementById("ry").value;
	   		if(tzsj==""){
	   			alert("请输入通知时间");
	   			return;
	   		}
	   		if(ry==""){
	   			alert("请选择通知人员");
	   			return;
	   		}
	   		if(tzmc==""){
	   			alert("请输入通知名称");
	   			return;
	   		}
			if(!dateTimeCompareNow(tzsj)){
	    		alert("通知时间不能早于现在");
				return;
	    	}
        	var form = document.getElementById("form1");
			form.action = "GztzServlet?action=insertGztzXF";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	form.target="_self";
			form.action = "GztzServlet?action=getGztzXFList&flag=1";
			form.submit();
        }
	</script>
	</head>
	<body>
		<form id="form1" name="Gztz" method="post">
			<table  width="100%">
				<tr>
					<td>
					通知名称：<input name="tzmc" id="tzmc" type="text" value=""/>
					会议地点：<input name="tzdd" id="tzdd" type="text" value=""/>
					</td>
				</tr>
				<tr>
					<td>
					参与人员：<input name="ry" id="ry" type="text" value="" onclick="openSelectFree('ry','ryID')" readonly="readonly"/>
					<input name="ryID" id="ryID" type="hidden" value=""/>
					<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					通知时间：<input name="tzsj" id="tzsj" type="text" size="15" readonly/>
          			<input name="Button" class="button1" onclick="setDayHM(document.Gztz.tzsj);" type="button" value="选择">
					&nbsp;&nbsp;&nbsp;&nbsp;<img width="45px" height="45px" align="middle" src="images/small/messages.png" >
					短信提醒：<input type="radio" name="SMSFlag" checked="checked" value="0">是
					<input type="radio" name="SMSFlag" value="1">否
					</td>
				</tr>
				<tr>
					<td>
					通知内容：
					<textarea id="tznr" name="tznr" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="tznr"/>  
				    <ck:replace replace="tznr" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2" method="post"></form>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



