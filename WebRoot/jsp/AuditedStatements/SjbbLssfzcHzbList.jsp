<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.SjbbLssfzcHzb"%>   
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>   
<%@page import="com.safety.entity.Permissions"%>     
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
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList SjbbLssfzcHzbList = (ArrayList)request.getAttribute("SjbbLssfzcHzbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>落实收费政策季度自查表汇总表</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function querySjbbLssfzcHzbByBt(){
   		var cxyear = document.getElementById("cxyear").value;
   		var cxjd = document.getElementById("cxjd").value;
		var srbt = "";
   		if(cxyear!=""){
   			srbt = " where year  = "+cxyear;
   		}
   		if(cxjd!=""){
   			if(srbt!=""){
   				srbt = srbt +" and jd = "+cxjd;
   			}else{
   				srbt = " where jd = "+cxjd;
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "BbsbSjbbServlet?action=getLssfzczcbHzb&srbt=" + srbt;
		form.submit();
   	}
	
    function sdsc(){
   		var form = document.getElementById("form3");
		form.action = "BbsbSjbbServlet?action=LssfzczcbHzbSave";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbSjbbServlet?action=getLssfzczcbHzb&srbt="+srbt+"&page_no=" + v;
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
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String cxyear = (String)request.getAttribute("cxyear"); 
		if(cxyear==null)cxyear="";
		String cxjd = (String)request.getAttribute("cxjd"); 
		if(cxjd==null)cxjd="";
		Calendar cal=Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
	%>
	
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2">
	<input type="hidden" id="srbt" value="<%=srbt%>">
		<table width="100%" >
			<tr>
				<td>
					<select name="cxyear" id="cxyear">
					<%if(!"".equals(cxyear)){ %>
						<option value="<%=cxyear %>"><%=cxyear %>年</option>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>年</option>
						<%} %>
					<%}else{ %>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
						<%} }%>
					</select>
					<select name="cxjd" id="cxjd">
					<%if(!"".equals(cxjd)){ %>
						<option value="<%=cxjd %>"><% if("0".equals(cxjd)){ %>全年<%}else{%><%=cxjd %>季度<%}%></option>
						<option value="0">全年</option>
						<option value="1">1季度</option>
						<option value="2">2季度</option>
						<option value="3">3季度</option>
						<option value="4">4季度</option>
					<%}else{ %>
						<option value="0" selected>全年</option>
						<option value="1">1季度</option>
						<option value="2">2季度</option>
						<option value="3">3季度</option>
						<option value="4">4季度</option>
					<%} %>	
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="querySjbbLssfzcHzbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				序号
			</td>
			<td align="center" width="50%" class="STYLE1">
				名称
			</td>
			<td align="center" width="10%" class="STYLE1">
				年份
			</td>
			<td align="center" width="10%" class="STYLE1">
				季度
			</td>
			<td align="center" width="20%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< SjbbLssfzcHzbList.size();i++){
				SjbbLssfzcHzb sjbbLssfzcHzb = (SjbbLssfzcHzb)SjbbLssfzcHzbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(SjbbLssfzcHzbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbShow&id=<%=sjbbLssfzcHzb.getId()%>" target="_blank">落实收费政策季度自查表-汇总表</a>
			</td>
			<td align="center">
				<%if(sjbbLssfzcHzb.getYear()!=0&&!"".equals(sjbbLssfzcHzb.getYear())){%>
				<%=sjbbLssfzcHzb.getYear()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(sjbbLssfzcHzb.getJd()!=0&&!"".equals(sjbbLssfzcHzb.getJd())){%>
				<%=sjbbLssfzcHzb.getJd()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbSave&SjbbLssfzcHzb_id=<%=sjbbLssfzcHzb.getId()%>&year=<%=sjbbLssfzcHzb.getYear()%>&jd=<%=sjbbLssfzcHzb.getJd()%>"><img  alt="重新生成" title="重新生成" src="images/small/options.png"></a>
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbDelete&id=<%=sjbbLssfzcHzb.getId()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left">
			<!-- 显示分页工具栏开始 -->
			<%=pt.printPage() %>
			<!--显示分页工具栏结束-->
			</td>
		</tr>
	</table>
	<br/><br/><br/><br/><br/><br/>
	<form method="post" id="form3" action="">
	<table width="100%" >
		<tr height="35px" valign="bottom">
			<td align="left">
				<select name="year" id="year">
					<% for(int m=nowYear-1;m<nowYear+2;m++){ %>
					<option value="<%= m %>" <%if(m==nowYear){%>selected<%}%>><%= m %>年</option>
					<%}%>
				</select>
				&nbsp;
				<select name="jd" id="jd">
					<option value="0" selected>全年</option>
					<option value="1">1季度</option>
					<option value="2">2季度</option>
					<option value="3">3季度</option>
					<option value="4">4季度</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="button1" onclick="sdsc()" value="手动汇总" />
			</td>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>
