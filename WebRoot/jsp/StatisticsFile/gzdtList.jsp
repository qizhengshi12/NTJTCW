<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Gzdt"%>  
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
	String flag = (String)request.getAttribute("flag");
	String srbt = (String)request.getAttribute("srbt");
	if(srbt==null)srbt="";
	String cxcompany = (String)request.getAttribute("cxcompany");
	if(cxcompany==null)cxcompany="";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>������̬</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
		var cxcompany = document.getElementById("cxcompany").value;
        srbt = srbt.replace(/%/g,"%25");//ת��;
		form.action = "GzdtServlet?action=query&cxcompany="+cxcompany+"&srbt="+srbt+"&page_no=" + v;
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
	<form method="post" id="form1" action="" target="content">
	</form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="cxcompany" value="<%=cxcompany%>">
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				���
			</td>
			<td align="center" width="30%" class="STYLE1">
				����
			</td>
			<td align="center" width="20%" class="STYLE1">
				��λ
			</td>
			<%if(UserPer.getGzdt().indexOf("3")!=-1 || UserPer.getGzdt().indexOf("4")!=-1){%>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
			<%} %>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
         	ArrayList GzdtList = (ArrayList)request.getAttribute("GzdtList");
			for(int i=0;i< GzdtList.size();i++){
				Gzdt gzdt = (Gzdt)GzdtList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(GzdtList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gzdt.getWjmc()!=null&&!"".equals(gzdt.getWjmc())){%>
				<a href="GzdtServlet?action=gzdtShow&id=<%=gzdt.getId()%>" target="_blank"><%=gzdt.getWjmc()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gzdt.getCzrdw()!=null&&!"".equals(gzdt.getCzrdw())){%>
				<%=gzdt.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getGzdt().indexOf("3")!=-1 || UserPer.getGzdt().indexOf("4")!=-1){%>
			<td align="center">
				<%if("����Ա".equals(roles)||((username.equals(gzdt.getCzrID())&&"2".equals(gzdt.getTjzt())&&UserPer.getGzdt().indexOf("3")!=-1))){%>
					<a href="GzdtServlet?action=gzdtEdit&id=<%=gzdt.getId()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
				<%} %>
				<%if("����Ա".equals(roles)||((username.equals(gzdt.getCzrID())&&"2".equals(gzdt.getTjzt())&&UserPer.getGzdt().indexOf("4")!=-1))){%>
					<a href="GzdtServlet?action=gzdtDelete&id=<%=gzdt.getId()%>&FileUrl=<%=gzdt.getFileUrl()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			 	<%} %>
			</td>
			<%} %>
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
	<%} %>
  </body>
</html>
