<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbbHzb"%>  
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
    ArrayList CwbbSydwzycwzbbHzbList = (ArrayList)request.getAttribute("CwbbSydwzycwzbbHzbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>��ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryCwbbByBt(){
   		var cxyear = document.getElementById("cxyear").value;
   		var cxmonth = document.getElementById("cxmonth").value;
		var srbt = "";
   		if(cxyear!=""){
   			srbt = " where year  = "+cxyear;
   		}
   		if(cxmonth!=""){
   			if(srbt!=""){
   				srbt = srbt +" and month = "+cxmonth;
   			}else{
   				srbt = " where month = "+cxmonth;
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "BbsbCwbbServlet?action=getSydwzycwzbbHzb&srbt=" + srbt;
		form.submit();
   	}
	
    function sdsc(){
   		var form = document.getElementById("form3");
		form.action = "BbsbCwbbServlet?action=SydwzycwzbbHzbSave";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbCwbbServlet?action=getSydwzycwzbbHzb&srbt="+srbt+"&page_no=" + v;
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
		String cxmonth = (String)request.getAttribute("cxmonth"); 
		if(cxmonth==null)cxmonth="";
		Calendar cal=Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH)+1;
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
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					&nbsp;1-
					<select name="cxmonth" id="cxmonth">
					<%if(!"".equals(cxmonth)){ %>
						<option value="<%=cxmonth %>"><%=cxmonth %>��</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>��</option>
						<%} }%>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryCwbbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
				�·�
			</td>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< CwbbSydwzycwzbbHzbList.size();i++){
				CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = (CwbbSydwzycwzbbHzb)CwbbSydwzycwzbbHzbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(CwbbSydwzycwzbbHzbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbCwbbServlet?action=SydwzycwzbbHzbShow&id=<%=cwbbSydwzycwzbbHzb.getId()%>" target="_blank">��ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�</a>
			</td>
			<td align="center">
				<%if(cwbbSydwzycwzbbHzb.getYear()!=0&&!"".equals(cwbbSydwzycwzbbHzb.getYear())){%>
				<%=cwbbSydwzycwzbbHzb.getYear()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSydwzycwzbbHzb.getMonth()!=0&&!"".equals(cwbbSydwzycwzbbHzb.getMonth())){%>
				<%=cwbbSydwzycwzbbHzb.getMonth()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbCwbbServlet?action=SydwzycwzbbHzbSave&cwbbSydwzycwzbbHzb_id=<%=cwbbSydwzycwzbbHzb.getId()%>&year=<%=cwbbSydwzycwzbbHzb.getYear()%>&month=<%=cwbbSydwzycwzbbHzb.getMonth()%>"><img  alt="��������" title="��������" src="images/small/options.png"></a>
				<a href="BbsbCwbbServlet?action=SydwzycwzbbHzbDelete&id=<%=cwbbSydwzycwzbbHzb.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
					<% for(int m=nowYear-10;m<nowYear+2;m++){ %>
					<option value="<%= m %>" <%if(m==nowYear){%>selected<%}%>><%= m %>��</option>
					<%}%>
				</select>
				&nbsp;
				<select name="month" id="month">
					<% for(int n=1;n<13;n++){ %>
					<option value="<%= n %>"<%if(n==nowMonth){%>selected<%}%>><%= n %>��</option>
					<%}%>
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
