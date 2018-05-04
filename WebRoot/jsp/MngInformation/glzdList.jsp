<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentGlzd"%>
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
        srbt = srbt.replace(/%/g,"%25");//转义;
		var MenuId = document.getElementById("MenuId").value;
		<%if("1".equals(flag)){ %>
		form.action = "GlzdServlet?action=query&srbt="+srbt+"&MenuId=&page_no=" + v;
		<%}else{ %>
		form.action = "GlzdServlet?action=query&MenuId="+MenuId+"&srbt=&page_no=" + v;
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
			<td align="center" width="40%">
				标题
			</td>
			<td align="center" width="15%">
				文号
			</td>
			<td align="center" width="10%">
				实施日期
			</td>
			<td align="center" width="10%">
				发布单位
			</td>
			<td align="center" width="10%">
				是否公开
			</td>
			<%if(UserPer.getContentglzd().indexOf("3")!=-1 || UserPer.getContentglzd().indexOf("4")!=-1){%>
			<td align="center" width="10%">
				操作
			</td>
			<%} %>
		</tr>
		<%
         	//从request域中取得要显示的某页信息
         	ArrayList GlzdList = (ArrayList)request.getAttribute("GlzdList");
         	int count = GlzdList.size();
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< GlzdList.size();i++){
           	ContentGlzd contentGlzd = (ContentGlzd)GlzdList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(GlzdList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGlzd.getBt()!=null&&!"".equals(contentGlzd.getBt())){%>
				<a class="BT" href="GlzdServlet?action=showGlzd&id=<%=contentGlzd.getId()%>"><%=contentGlzd.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGlzd.getWh()!=null&&!"".equals(contentGlzd.getWh())){%>
				<%=contentGlzd.getWh()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGlzd.getSsrq()!=null&&!"".equals(contentGlzd.getSsrq())){%>
				<%=contentGlzd.getSsrq()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentGlzd.getFbdw()!=null&&!"".equals(contentGlzd.getFbdw())){%>
				<%=contentGlzd.getFbdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("0".equals(contentGlzd.getSfgk())){%>
				对外公开
				<%}else if("1".equals(contentGlzd.getSfgk())){ %>
				单位内部
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getContentglzd().indexOf("3")!=-1 || UserPer.getContentglzd().indexOf("4")!=-1){%>
			<td align="center">
				<%if(UserPer.getContentglzd().indexOf("5")!=-1){
				%>
					<%if(UserPer.getContentglzd().indexOf("3")!=-1){%>
					<a href="GlzdServlet?action=editGlzd&id=<%=contentGlzd.getId()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
					<%} %>
					<%if(UserPer.getContentglzd().indexOf("4")!=-1){%>
					<a href="GlzdServlet?action=delete&id=<%=contentGlzd.getId()%>&fatherid=<%=contentGlzd.getFatherid()%>&path=<%=contentGlzd.getFileUrl()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
					<%} %>
				<%}else{ %>
					<%if((UserPer.getContentglzd().indexOf("3")!=-1) && (company.equals(contentGlzd.getFbdw()))){%>
					<a href="GlzdServlet?action=editGlzd&id=<%=contentGlzd.getId()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
					<%} %>
					<%if((UserPer.getContentglzd().indexOf("4")!=-1) && (company.equals(contentGlzd.getFbdw()))){%>
					<a href="GlzdServlet?action=delete&id=<%=contentGlzd.getId()%>&fatherid=<%=contentGlzd.getFatherid()%>&path=<%=contentGlzd.getFileUrl()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
					<%} %>
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