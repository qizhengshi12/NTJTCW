<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gztzhf"%>
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
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>查看回复情况</title>
    <script type="text/javascript">
	function cancel(){
		window.open('','_self');
		window.close();
	}
    </script>
	</head>
	<body>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ArrayList GztzhfList = (ArrayList)request.getAttribute("GztzhfList");
		%>
		<table width="75%" height="10%" align="center">
			<tr>
				<td colspan="5" align="center">
				<span style="font-size: 25px">回复情况</span>
				</td>
			</tr>
			<tr >
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<table width="75%"  align="center">
			<tr class="tr1" >
				<td align="center" width="5%"  height="30px">
					序号
				</td>
				<td align="center" width="34%">
					通知名称
				</td>
				<td align="center" width="10%">
					回复人
				</td>
				<td align="center" width="15%">
					回复时间
				</td>
				<td align="center" width="35%">
					回复内容
				</td>
			</tr>
		<%
         	//从request域中取得要显示的某页信息
         	int count = GztzhfList.size();
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< GztzhfList.size();i++){
           	Gztzhf gztzhf = (Gztzhf)GztzhfList.get(i);
		%>
			<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
				<td align="center" height="30px">
					<%if(GztzhfList.size()!=0){%>
					<%=i+1%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getTzmc()!=null&&!"".equals(gztzhf.getTzmc())){%>
					<a href="GztzServlet?action=showGztzXF&gztzid=<%=gztzhf.getTzid()%>" target="_blank"><%=gztzhf.getTzmc()%></a>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfr()!=null&&!"".equals(gztzhf.getHfr())){%>
					<%=gztzhf.getHfr()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfsj()!=null&&!"".equals(gztzhf.getHfsj())){%>
					<%=gztzhf.getHfsj().toString().substring(0,10)%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfnr()!=null&&!"".equals(gztzhf.getHfnr())){%>
					<%=gztzhf.getHfnr()%>
					<%}else{ %>
					未回复
					<%} %>
				</td>
			</tr>
			<% }%>
		</table>
		<table width="75%" height="15%" align="center">
			<tr >
				<td>
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



