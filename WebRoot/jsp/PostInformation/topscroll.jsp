<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%@page import="com.safety.entity.TopScroll"%>  
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
    
    <title>滚动字幕</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
		function Save(){
			var form = document.getElementById("form1");
			form.action = "PostInformationServlet?action=saveTopScroll";
			form.submit();
		}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("speed").value=sel.options[sel.selectedIndex].value;
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
         	//String permissions = (String)request.getAttribute("permissions"); 
         	TopScroll topScroll = (TopScroll)request.getAttribute("topScroll");
         	if(topScroll.getId()==0){
         		topScroll.setContent("欢迎使用本平台。");
         		topScroll.setSpeed("2");
         		topScroll.setDirection("left");
         	}
  %>
   <form method="post" id="form1">
   <input type="hidden" name="id" id="id" value="<%=topScroll.getId() %>" >
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h2><img width="80px" height="80px" align="middle" src="images/small/banner_design1.png">滚动字幕</h2>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right" width="40%">
					字幕内容：
				</td>
				<td align="left">
					<textarea rows="5" cols="50" name="content" id="content" ><%=topScroll.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					滚动方向：
				</td>
				<td align="left">
					<input type="radio" name="direction" <%if("left".equals(topScroll.getDirection())){%>checked="checked"<%}%> value="left">向左
					<input type="radio" name="direction" <%if("right".equals(topScroll.getDirection())){%>checked="checked"<%}%> value="right">向右
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					滚动速度：
				</td>
				<td align="left">
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=topScroll.getSpeed() %>">&nbsp;<%=topScroll.getSpeed() %></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>（默认为2，最慢为1，最快为6）
					<input type="hidden" name="speed" id="speed" value="<%=topScroll.getSpeed() %>">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<img alt="更新" width="45px" height="45px" title="更新" style="cursor: pointer;"  src="images/small/save-as.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		</form>
		<%} %>
  </body>
</html>