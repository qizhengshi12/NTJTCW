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
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    <title>��ʵ�շ����߼����Բ����ܱ�</title>
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
						<option value="<%=cxyear %>"><%=cxyear %>��</option>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					<select name="cxjd" id="cxjd">
					<%if(!"".equals(cxjd)){ %>
						<option value="<%=cxjd %>"><% if("0".equals(cxjd)){ %>ȫ��<%}else{%><%=cxjd %>����<%}%></option>
						<option value="0">ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
					<%}else{ %>
						<option value="0" selected>ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
					<%} %>	
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="querySjbbLssfzcHzbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="50%" class="STYLE1">
				����
			</td>
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
			</td>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
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
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbShow&id=<%=sjbbLssfzcHzb.getId()%>" target="_blank">��ʵ�շ����߼����Բ��-���ܱ�</a>
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
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbSave&SjbbLssfzcHzb_id=<%=sjbbLssfzcHzb.getId()%>&year=<%=sjbbLssfzcHzb.getYear()%>&jd=<%=sjbbLssfzcHzb.getJd()%>"><img  alt="��������" title="��������" src="images/small/options.png"></a>
				<a href="BbsbSjbbServlet?action=LssfzczcbHzbDelete&id=<%=sjbbLssfzcHzb.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left">
			<!-- ��ʾ��ҳ��������ʼ -->
			<%=pt.printPage() %>
			<!--��ʾ��ҳ����������-->
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
					<option value="<%= m %>" <%if(m==nowYear){%>selected<%}%>><%= m %>��</option>
					<%}%>
				</select>
				&nbsp;
				<select name="jd" id="jd">
					<option value="0" selected>ȫ��</option>
					<option value="1">1����</option>
					<option value="2">2����</option>
					<option value="3">3����</option>
					<option value="4">4����</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="button1" onclick="sdsc()" value="�ֶ�����" />
			</td>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>
