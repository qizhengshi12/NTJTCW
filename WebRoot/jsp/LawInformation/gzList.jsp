<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentGz"%>
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
	String Gzag = (String)request.getAttribute("Gzag");
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
		<%if("1".equals(Gzag)){ %>
		form.action = "GzServlet?action=query&srbt="+srbt+"&MenuId=&page_no=" + v;
		<%}else{ %>
		form.action = "GzServlet?action=query&MenuId="+MenuId+"&srbt=&page_no=" + v;
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
         	ArrayList GzList = (ArrayList)request.getAttribute("GzList");
         	int count = GzList.size();
         	/*����ÿ������Ϣ������ʾ*/
           for(int i=0;i< GzList.size();i++){
           	ContentGz contentGz = (ContentGz)GzList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(GzList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGz.getBt()!=null&&!"".equals(contentGz.getBt())){%>
				<a class="BT" href="GzServlet?action=showGz&id=<%=contentGz.getId()%>"><%=contentGz.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGz.getWh()!=null&&!"".equals(contentGz.getWh())){%>
				<%=contentGz.getWh()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGz.getSsrq()!=null&&!"".equals(contentGz.getSsrq())){%>
				<%=contentGz.getSsrq()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGz.getFbdw()!=null&&!"".equals(contentGz.getFbdw())){%>
				<%=contentGz.getFbdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getContentcjfg().indexOf("3")!=-1 || UserPer.getContentcjfg().indexOf("4")!=-1){%>
			<td align="center">
				<%if(UserPer.getContentcjfg().indexOf("3")!=-1){%>
				<a href="GzServlet?action=editGz&id=<%=contentGz.getId()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
				<%} %>
				<%if(UserPer.getContentcjfg().indexOf("4")!=-1){%>
				<a href="GzServlet?action=delete&id=<%=contentGz.getId()%>&fatherid=<%=contentGz.getFatherid()%>&path=<%=contentGz.getFileUrl()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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