<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentFl"%>
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
	String MenuId = (String)request.getAttribute("MenuId");
	if(MenuId==null)MenuId="";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script type="text/javascript">
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//ת��;
		var MenuId = document.getElementById("MenuId").value;
		<%if("1".equals(flag)){ %>
		form.action = "FlServlet?action=query&srbt="+srbt+"&MenuId=&page_no=" + v;
		<%}else{ %>
		form.action = "FlServlet?action=query&MenuId="+MenuId+"&srbt=&page_no=" + v;
		<%}%>
		form.submit();
	}
   </script>
  </head>
  <body>
	<form method="post" id="form1" target="content"></form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="MenuId" value="<%=MenuId%>">
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1>
		<tr class="tr1">
			<td align="center" width="5%">
				���
			</td>
			<td align="center" width="45%">
				����
			</td>
			<td align="center" width="15%">
				�ĺ�
			</td>
			<td align="center" width="10%">
				ʵʩ����
			</td>
			<td align="center" width="15%">
				������λ
			</td>
			<%if(UserPer.getContentcjfg().indexOf("3")!=-1 || UserPer.getContentcjfg().indexOf("4")!=-1){%>
			<td align="center" width="10%">
				����
			</td>
			<%} %>
		</tr>
		<%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	ArrayList FlList = (ArrayList)request.getAttribute("FlList");
         	int count = FlList.size();
         	/*����ÿ������Ϣ������ʾ*/
           for(int i=0;i< FlList.size();i++){
           	ContentFl contentFl = (ContentFl)FlList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(FlList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFl.getBt()!=null&&!"".equals(contentFl.getBt())){%>
				<a class="BT" href="FlServlet?action=showFl&id=<%=contentFl.getId()%>"><%=contentFl.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFl.getWh()!=null&&!"".equals(contentFl.getWh())){%>
				<%=contentFl.getWh()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFl.getSsrq()!=null&&!"".equals(contentFl.getSsrq())){%>
				<%=contentFl.getSsrq()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFl.getFbdw()!=null&&!"".equals(contentFl.getFbdw())){%>
				<%=contentFl.getFbdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getContentcjfg().indexOf("3")!=-1 || UserPer.getContentcjfg().indexOf("4")!=-1){%>
			<td align="center">
				<%if(UserPer.getContentcjfg().indexOf("3")!=-1){%>
				<a href="FlServlet?action=editFl&id=<%=contentFl.getId()%>"><img alt="�༭" title="�༭" src="images/small/options.png"></a>
				<%} %>
				<%if(UserPer.getContentcjfg().indexOf("4")!=-1){%>
				<a href="FlServlet?action=delete&id=<%=contentFl.getId()%>&fatherid=<%=contentFl.getFatherid()%>&path=<%=contentFl.getFileUrl()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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