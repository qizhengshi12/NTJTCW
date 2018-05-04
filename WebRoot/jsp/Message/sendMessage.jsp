<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>短信发送</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
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
		function ReSetP(){
        	document.getElementById("ry").value="";
        	document.getElementById("ryID").value="";
		}
		function send(){
        	var str1 = document.getElementById("ryID").value;
        	var str2 = document.getElementById("dxnr").value;
        	if(str1==""){
        		alert("请选择收信人");
        		return;
        	}
        	if(str2==""){
        		alert("请填写短信内容");
        		return;
        	}
        	if(str2.length>500){
        		alert("短信内容过长");
        		return;
        	}
        	var form = document.getElementById("form1");
			form.action = "MessageServlet?action=sendMessage";
			form.submit();
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
       	String dxnr = (String)request.getAttribute("dxnr");
       	if(dxnr==null)dxnr="";
	%>
  <form method="post" id="form1" action="" name="MSG">
	  <table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="选中" align="middle" src="images/small/favorites.png"><a href='MessageServlet?action=newMessage'><span style="font-size: 16px;border:1px; background:#C1FFB2;">发送新短信</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=MyMessage'><span style="font-size: 16px">已接收短信</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=allSendMessage'><span style="font-size: 16px">已发送短信</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="right" width="40%">
			收信人：
			</td>
			<td align="left">
				<textarea id="ry" name="ry" cols="35" rows="5" onclick="openSelectFree('ry','ryID')" readonly="readonly"></textarea>
				<input name="ryID" id="ryID" type="hidden" value=""/>
				<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="middle" width="40px" height="40px" alt="清空人员" title="清空人员" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td align="right">
			短信内容：
			</td>
			<td align="left">
				<textarea id="dxnr" name="dxnr" cols="35" rows="10"><%=dxnr%></textarea>（最多250个字）
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<img align="middle" width="80px" height="80px" alt="发送" title="发送" style="cursor: pointer;" src="images/small/mail_send.png" onclick="send()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	  </table>
  </form>
  <form method="post" id="form2" action="" target="_black">
  </form>
  </body>
</html>
