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
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    
    <title>������Ļ</title>
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	TopScroll topScroll = (TopScroll)request.getAttribute("topScroll");
         	if(topScroll.getId()==0){
         		topScroll.setContent("��ӭʹ�ñ�ƽ̨��");
         		topScroll.setSpeed("2");
         		topScroll.setDirection("left");
         	}
  %>
   <form method="post" id="form1">
   <input type="hidden" name="id" id="id" value="<%=topScroll.getId() %>" >
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h2><img width="80px" height="80px" align="middle" src="images/small/banner_design1.png">������Ļ</h2>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right" width="40%">
					��Ļ���ݣ�
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
					��������
				</td>
				<td align="left">
					<input type="radio" name="direction" <%if("left".equals(topScroll.getDirection())){%>checked="checked"<%}%> value="left">����
					<input type="radio" name="direction" <%if("right".equals(topScroll.getDirection())){%>checked="checked"<%}%> value="right">����
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right">
					�����ٶȣ�
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
					</select>��Ĭ��Ϊ2������Ϊ1�����Ϊ6��
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
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/save-as.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		</form>
		<%} %>
  </body>
</html>