<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.LearningCorner"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>学习园地</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<script type="text/javascript">
	function custom_close(){
		window.open('','_self');
		window.close();
	}
	//点赞
	function setGood(id,pp,ppid,num){
   		var form = document.getElementById("form1");
		form.action = "LearningCornerServlet?action=setGood&id="+id+"&pp="+pp+"&ppid="+ppid+"&num="+num;
		form.submit();
	}
	</script>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
  </head>
  
  <body>
  <% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  	<%
		LearningCorner learningCorner = (LearningCorner)request.getAttribute("learningCorner");
	%>
	
	<form method="post" id="form1" action="">
	</form>
	<table width="95%" align="center">
			<tr>
				<td valign="bottom" align="left" width="15%">
				<span style="background-color: #E0FFFF;">
				<%if("2".equals(learningCorner.getLx3())){%>
				精华帖
				<%}else{%>
				普通帖
				<%}%>
				</span>
				</td>
				<td align="right" width="40%">
				<span style="font-weight: bold;font-size: 20px"><%=learningCorner.getBt() %></span>
				</td>
				<td valign="bottom" align="right" width="45%">
				<span style="background-color: #E0FFFF;">
				<%if("学习".equals(learningCorner.getLx1())){%>
				学习版块
				<%}else if("工作".equals(learningCorner.getLx1())){%>
				工作版块
				<%}else if("生活".equals(learningCorner.getLx1())){%>
				生活版块
				<%}%>
				</span>
				&nbsp;
				<span style="background-color: #E0FFFF;">
				<%if("分享".equals(learningCorner.getLx2())){%>
				分享
				<%}else{%>
				求助
				<%}%>
				</span>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
			<%if(!"".equals(learningCorner.getPhotoURL())&&learningCorner.getPhotoURL()!=null){%>
				<td valign="top" align="center">
				<img src="UserFile/<%=learningCorner.getPhotoURL() %>" width="300" height='200' border='1'> 
				</td>
				<td valign="top" align="left" colspan="2">
					<%if(learningCorner.getNr()!=null){%><%=learningCorner.getNr() %><%}%>
				</td>
			<%}else{%>
				<td valign="top" align="left" colspan="3">
					<%if(learningCorner.getNr()!=null){%><%=learningCorner.getNr() %><%}%>
				</td>
			<%}%>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="right" colspan="3">
					<img width="24px" height="24px" align="middle" src="images/small/48.png">
					发表人：<%=learningCorner.getTjr() %>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img width="30px" height="30px" align="middle" src="images/small/buildings.png">
					单位：<%=learningCorner.getDw() %>
					&nbsp;&nbsp;&nbsp;
					<img width="24px" height="24px" align="middle" src="images/small/internet-history.png">
					发表时间：<%=learningCorner.getTjsj() %>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%if(!"".equals(learningCorner.getFileURL())&&learningCorner.getFileURL()!=null){%>
			<tr>
				<td colspan="3">
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(learningCorner.getFileURL())&&learningCorner.getFileURL()!=null){
				  		String[] fileStr = learningCorner.getFileURL().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="LearningCornerServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>无
					<%}%>
				<iframe id='ifm' name='ifm' style="display:none"></iframe>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%}%>
			<tr>
				<td align="left" colspan="3">
				<%if(learningCorner.getPeople()!=null&&!"".equals(learningCorner.getPeople())){ 
					if(learningCorner.getPeople().length()>8){%>
					<%=learningCorner.getPeople().substring(0,8)%>等人点赞了！
					<%}else{%>
					<%=learningCorner.getPeople()%>点赞了！
					<%}%>
				<%}%>
				&nbsp;&nbsp;<img  align="top" title="点赞人：<%=learningCorner.getPeople()%>" src="images/small/001_18.png">&nbsp;获赞数：<%=learningCorner.getGood() %>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
				<%
					if(!username.equals(learningCorner.getTjrID())){
						String ppid = learningCorner.getPeopleID();
						String [] ppidList = null;
						boolean goodflag = true;
						if(!"".equals(ppid)){
							ppidList = ppid.split(";");
							for(int i=0; i<ppidList.length; i++){
								if(username.equals(ppidList[i])){
									goodflag=false;
									break;
								}
							}
						}
						if(goodflag){
				%>
					<img alt="赞一个" width="45px" height="45px" title="赞一个" style="cursor: pointer;"  src="images/small/dnz.png" onclick="setGood('<%=learningCorner.getId()%>','<%=learningCorner.getPeople()%>','<%=learningCorner.getPeopleID()%>','<%=learningCorner.getGood()%>')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					<%}else{%>
					<img alt="已赞" width="45px" height="45px" title="已赞" style="cursor: pointer;" src="images/small/thumb_up.png" onclick="javascript:return alert('已赞过');">
					<%}%>
				<%}%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="关闭" width="45px" height="45px" title="关闭" style="cursor: pointer;"  src="images/small/close.png" onclick="custom_close()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
