<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentFg"%>
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
	String Fgag = (String)request.getAttribute("Fgag");
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
        srbt = srbt.replace(/%/g,"%25");//转义;
		var MenuId = document.getElementById("MenuId").value;
		<%if("1".equals(Fgag)){ %>
		form.action = "FgServlet?action=query&srbt="+srbt+"&MenuId=&page_no=" + v;
		<%}else{ %>
		form.action = "FgServlet?action=query&MenuId="+MenuId+"&srbt=&page_no=" + v;
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
				序号
			</td>
			<td align="center" width="45%">
				标题
			</td>
			<td align="center" width="15%">
				文号
			</td>
			<td align="center" width="10%">
				实施日期
			</td>
			<td align="center" width="15%">
				发布单位
			</td>
			<%if(UserPer.getContentcjfg().indexOf("3")!=-1 || UserPer.getContentcjfg().indexOf("4")!=-1){%>
			<td align="center" width="10%">
				操作
			</td>
			<%} %>
		</tr>
		<%
         	//从request域中取得要显示的某页信息
         	ArrayList FgList = (ArrayList)request.getAttribute("FgList");
         	int count = FgList.size();
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< FgList.size();i++){
           	ContentFg contentFg = (ContentFg)FgList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(FgList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFg.getBt()!=null&&!"".equals(contentFg.getBt())){%>
				<a class="BT" href="FgServlet?action=showFg&id=<%=contentFg.getId()%>"><%=contentFg.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFg.getWh()!=null&&!"".equals(contentFg.getWh())){%>
				<%=contentFg.getWh()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFg.getSsrq()!=null&&!"".equals(contentFg.getSsrq())){%>
				<%=contentFg.getSsrq()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentFg.getFbdw()!=null&&!"".equals(contentFg.getFbdw())){%>
				<%=contentFg.getFbdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getContentcjfg().indexOf("3")!=-1 || UserPer.getContentcjfg().indexOf("4")!=-1){%>
			<td align="center">
				<%if(UserPer.getContentcjfg().indexOf("3")!=-1){%>
				<a href="FgServlet?action=editFg&id=<%=contentFg.getId()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
				<%} %>
				<%if(UserPer.getContentcjfg().indexOf("4")!=-1){%>
				<a href="FgServlet?action=delete&id=<%=contentFg.getId()%>&fatherid=<%=contentFg.getFatherid()%>&path=<%=contentFg.getFileUrl()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
			<!-- 显示分页工具栏开始 -->
			<%=pt.printPage() %>
			<!--显示分页工具栏结束-->
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>