<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.CwbbSgjfb"%>  
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
    ArrayList CwbbSgjfbList = (ArrayList)request.getAttribute("CwbbSgjfbList");
	String srbt = (String)request.getAttribute("srbt");
	if(srbt==null)srbt="";
	String cxcompany = (String)request.getAttribute("cxcompany");
	if(cxcompany==null)cxcompany="";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�������ѱ�</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
		var cxcompany = document.getElementById("cxcompany").value;
		form.action = "BbsbCwbbServlet?action=getSgjfb&cxcompany="+cxcompany+"&srbt="+srbt+"&page_no=" + v;
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
	<form method="post" id="form1" action=""></form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="cxcompany" value="<%=cxcompany%>">
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				���
			</td>
			<td align="center" width="25%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				��λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="5%" class="STYLE1">
				�·�
			</td>
			<td align="center" width="10%" class="STYLE1">
				״̬
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< CwbbSgjfbList.size();i++){
				CwbbSgjfb cwbbSgjfb = (CwbbSgjfb)CwbbSgjfbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(CwbbSgjfbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbCwbbServlet?action=sgjfbShow&id=<%=cwbbSgjfb.getId()%>" target="_blank">�������ѱ�</a>
			</td>
			<td align="center">
				<%if(cwbbSgjfb.getCzrdw()!=null&&!"".equals(cwbbSgjfb.getCzrdw())){%>
				<%=cwbbSgjfb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSgjfb.getCzr()!=null&&!"".equals(cwbbSgjfb.getCzr())){%>
				<%=cwbbSgjfb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSgjfb.getYear()!=0&&!"".equals(cwbbSgjfb.getYear())){%>
				<%=cwbbSgjfb.getYear()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSgjfb.getMonth()!=0&&!"".equals(cwbbSgjfb.getMonth())){%>
				<%=cwbbSgjfb.getMonth()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(cwbbSgjfb.getTjzt())){%>
				���ύ
				<%}else if("2".equals(cwbbSgjfb.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if("��������".equals(roles)&&"�ֻ���".equals(company)&&"1".equals(cwbbSgjfb.getTjzt())){%>
				<a href="BbsbCwbbServlet?action=sgjfbReturn&id=<%=cwbbSgjfb.getId()%>&bhry=<%=cwbbSgjfb.getCzrID()%>" target="I2"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbSgjfb.getCzrID())&&("2".equals(cwbbSgjfb.getTjzt())||"3".equals(cwbbSgjfb.getTjzt()))&&UserPer.getSgjfsy().indexOf("3")!=-1)){%>
				<a href="BbsbCwbbServlet?action=sgjfbEdit&id=<%=cwbbSgjfb.getId()%>" target="I2"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbSgjfb.getCzrID())&&("2".equals(cwbbSgjfb.getTjzt())||"3".equals(cwbbSgjfb.getTjzt()))&&UserPer.getSgjfsy().indexOf("4")!=-1)){%>
				<a href="BbsbCwbbServlet?action=sgjfbDelete&id=<%=cwbbSgjfb.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			 	<%} %>
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
	<%} %>
  </body>
</html>
