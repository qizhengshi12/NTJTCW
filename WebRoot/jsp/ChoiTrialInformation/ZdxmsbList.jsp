<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Zdxmsb"%>  
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
	String cxcompany = (String)request.getAttribute("cxcompany");
	if(cxcompany==null)cxcompany="";
    ArrayList ZdxmsbList = (ArrayList)request.getAttribute("ZdxmsbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>重大项目申报——列表</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
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
        srbt = srbt.replace(/%/g,"%25");//转义;
		form.action = "ZdxmsbServlet?action=getZdxmsbList&cxcompany="+cxcompany+"&srbt="+srbt+"&page_no=" + v;
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
				序号
			</td>
			<td align="center" width="30%" class="STYLE1">
				类型
			</td>
			<td align="center" width="15%" class="STYLE1">
				单位
			</td>
			<td align="center" width="10%" class="STYLE1">
				填报人
			</td>
			<td align="center" width="10%" class="STYLE1">
				报告时间
			</td>
			<td align="center" width="10%" class="STYLE1">
				审批结果
			</td>
			<td align="center" width="10%" class="STYLE1">
				是否提交
			</td>
			<td align="center" width="10%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< ZdxmsbList.size();i++){
				Zdxmsb zdxmsb = (Zdxmsb)ZdxmsbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(ZdxmsbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getLx()!=null&&!"".equals(zdxmsb.getLx())){%>
				<a href="ZdxmsbServlet?action=ZdxmsbShow&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&flag=1" target="_blank">
				<% 
				if("1".equals(zdxmsb.getLx())){
					%>注册资本变更<%
				}else if("2".equals(zdxmsb.getLx())){
					%>重大对外投资<%
				}else if("3".equals(zdxmsb.getLx())){
					%>重大融资（举债）<%
				}else if("4".equals(zdxmsb.getLx())){
					%>股权变更<%
				}else if("5".equals(zdxmsb.getLx())){
					%>债务重组<%
				}else if("6".equals(zdxmsb.getLx())){
					%>信誉担保和重大资产抵押担保<%
				}
				%>
				</a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzrdw()!=null&&!"".equals(zdxmsb.getCzrdw())){%>
				<%=zdxmsb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzr()!=null&&!"".equals(zdxmsb.getCzr())){%>
				<%=zdxmsb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzsj()!=null&&!"".equals(zdxmsb.getCzsj())){%>
				<%=zdxmsb.getCzsj().toString().substring(0,10)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getShyj()!=null&&!"".equals(zdxmsb.getShyj())){%>
				<%=zdxmsb.getShyj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(zdxmsb.getTjzt())){%>
				已提交
				<%}else{ %>
				未提交
				<%} %>
			</td>
			<td align="center">
				<%if(("驳回".equals(zdxmsb.getShyj())||"2".equals(zdxmsb.getTjzt()))&&(username.equals(zdxmsb.getCzrID())&&UserPer.getZdxmsb().indexOf("3")!=-1)){%>
				<a href="ZdxmsbServlet?action=ZdxmsbEdit&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>" target="I2"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
			 	<%} %>
				<%if(("驳回".equals(zdxmsb.getShyj())||"2".equals(zdxmsb.getTjzt()))&&(username.equals(zdxmsb.getCzrID())&&UserPer.getZdxmsb().indexOf("4")!=-1)){%>
				<a href="ZdxmsbServlet?action=ZdxmsbDelete&id=<%=zdxmsb.getId()%>&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&smj=<%=zdxmsb.getSmj()%>" onclick="javascript:return confirm('确定删除此记录？');" target="I2"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
			 	<%} %>
				<%if("未审批".equals(zdxmsb.getShyj())&&(username.equals(zdxmsb.getShrID()))){%>
				<a href="ZdxmsbServlet?action=ZdxmsbShow&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&flag=2"><img  alt="审批" title="审批" src="images/small/icon_bitcomet.png"></a>
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
			<!-- 显示分页工具栏开始 -->
			<%=pt.printPage() %>
			<!--显示分页工具栏结束-->
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
