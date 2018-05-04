<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计报表</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
  </head>
  
  <body>
 <table height="100%">
		<tr>
			<td width="150px" valign="top">
		  	<a href='jsp/StatisticalReports/tjbbBzt.jsp' target="TJBB"><span style="font-size: 16px">饼状图</span></a>
		  	<br>
		  	<br>
		  	<a href='jsp/StatisticalReports/tjbbZzt.jsp' target="TJBB"><span style="font-size: 16px">柱状图</span></a>
		  	<br>
		  	<br>
		  	<a href='jsp/StatisticalReports/tjbbQxt.jsp' target="TJBB"><span style="font-size: 16px">曲线图</span></a>
			</td>
			<td width="1000px">
			<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="TJBB" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
			</iframe>
			</td>
		</tr>
	  </table>
</html>
