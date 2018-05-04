<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>南通交通财务审计统计综合信息系统</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-position:center center;
	background-image:url('images/login.jpg');
	overflow:auto;
}
.divcenter1{
border: 1px solid #CCC;
width:1120px;
height:40px;
margin:auto;
display: table;   
*position: relative;   
overflow: hidden;  
}   

.divcenter1 {
border: 0px;
vertical-align: middle;
display: table-cell;
*top: 50%;
} 
         
.divcenter2 {
border: 0px;
*top: -50%;
padding-left:60px;
}

</style>
<script language="javascript">
	function custom_close(){
		window.open('','_self');
		window.close();
	}
	document.onkeydown=keyListener;
	function keyListener(e){
		e = e ? e : event;
		if(e.keyCode == 13){
	   		var form = document.getElementById("loginForm");
			form.submit();
		}   
	} 
	
</script>
</head>

<body>
	<% if(request.getAttribute("errMessage")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("errMessage")%>');
	    </script> 
	<% } %>
	<form method="post" action="CheckLoginServlet?action=login" name="loginForm" id="loginForm">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="80%" valign="top" align="right" colspan="4">
				<input type="button" onClick="custom_close()" value="关闭本页">
			</td>
		</tr>
		<tr>
			<td width="30%" height="20%">&nbsp;</td>
			<td width="25%" valign="middle" align="center">
				<div class="divcenter1" style="width:260px ;height: 35px;background-image: url('images/userimg.jpg');background-position: center center;">
					<div class="divcenter2">
						<input type="text" size="15" style="height: 30px;font-size: 20" name="username" id="Username"/>
					</div>  
				</div>  
			</td>
			<td width="25%" valign="middle" align="center">
				<div>
				<div class="divcenter1" style="width:252px ;height: 35px;background-image: url('images/passimg.jpg');background-position: center center;">
					<div class="divcenter2">
						<input type="password" size="15" style="height: 30px;font-size: 20" name="password" id="Password" />
					</div>
				</div>
				</div>
			</td>
			<td width="20%" valign="middle" align="left">
				<div style="width:125px ;height: 38px;">
					<img align="middle" style="cursor: pointer;" src='images/dl.jpg' onclick="javascript:loginForm.submit();"/>
				</div>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>