<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Wjglhf"%>  
<%@page import="com.safety.entity.Wjglxf"%>  
<%@page import="com.safety.entity.WjglTJ"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  
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
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList CompanyList = (ArrayList)request.getAttribute("CompanyList");
    ArrayList NewList = (ArrayList)request.getAttribute("NewList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>统计列表</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryWjglByBt(){
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
   		var form = document.getElementById("form1");
		var srbt = "";
   		if(cxssrq1!=""){
   			srbt = " where fqsj >='"+cxssrq1+"'";
   		}
   		if(cxssrq2!=""){
   			if(srbt!=""){
   				srbt = srbt +" and fqsj <='"+cxssrq2+"'";
   			}else{
   				srbt = " where fqsj <='"+cxssrq2+"'";
   			}
   		}
		form.action = "WjglServlet?action=getWjglTJList&srbt=" + srbt;
		form.submit();
   	} 
	function cancel(){
		window.open('','_self');
		window.close();
	}
	
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//转义;
		form.action = "WjglServlet?action=getWjglTJList&srbt="+srbt+"&page_no=" + v;
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
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
	
  <form method="post" id="form1" name="WJGL">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%">
		<tr valign="top">
			<td align="center">
				发文日期：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq1);" type="button" value="选择">
				到
				<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq2);" type="button" value="选择">
				&nbsp; 
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryWjglByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
  </form>
	<table id="table1" width="3200px" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="50px">
				序号
			</td>
			<td align="center" width="200px">
				文件名称
			</td>
			<%
				for(int i=0;i< CompanyList.size();i++){
					WjglTJ dw = (WjglTJ)CompanyList.get(i);
			%>
			<td align="center" width="220px" colspan="2">
					<%=dw.getName()%>
			</td>
			<%
				}
			%>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int j=0;j< NewList.size();j++){
				String str = (String)NewList.get(j);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(NewList.size()!=0){%>
				<%=j+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%
				String[] strList = str.split(";");
			%>
			<td align="center">
				<%=strList[1]%>
			</td>
			<%
				for(int k=2;k< strList.length;k++){
					String sfhf = strList[k];
					String sfcs = strList[k+1];
					k=k+1;
			%>
			<td align="center">
					<%=sfhf%>
			</td>
			<td align="center">
					<% if("超时".equals(sfcs)){%><span style="color: red;">超时</span><%}else {%><%=sfcs%><%}%>
			</td>
			<%
				}
			%>
		</tr>
		<%
		}
		%>
		<tr bgcolor="#EBEBEB">
			<td align="center" width="50px">
				&nbsp;
			</td>
			<td align="center" width="200px">
				统计数据
			</td>
			<%
				for(int j=0;j< CompanyList.size();j++){
					WjglTJ dw = (WjglTJ)CompanyList.get(j);
			%>
			<td align="center" width="220px">
					未回复：<%=dw.getCounthf()%>个
			</td>
			<td align="center" width="220px">
					超时：<%=dw.getCountcs()%>个
			</td>
			<%
				}
			%>
		</tr>
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
	<table  width="100%">
		<tr>
			<td align="center">
				<img alt="关闭"  width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
