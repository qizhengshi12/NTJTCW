<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Permissions"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
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
    Permissions permissions = (Permissions)request.getAttribute("permissions");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限表</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
   	function savePermissions(){
   		var form = document.getElementById("form1");
		form.action = "PermissionsServlet?action=PermissionsSave";
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
	<form method="post" id="form1" action="">
    <input type="hidden" name="username" id="username"  value="<%=permissions.getUsername()%>">
	<table id="table1" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
		<tr>
			<td align="center" width="25%" class="STYLE3">
				模块名称
			</td>
			<td align="center" width="15%" class="STYLE3">
				查询
			</td>
			<td align="center" width="15%" class="STYLE3">
				新增
			</td>
			<td align="center" width="15%" class="STYLE3">
				修改
			</td>
			<td align="center" width="15%" class="STYLE3">
				删除
			</td>
			<td align="center" width="15%" class="STYLE3">
				其他单位
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				财经法规菜单
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodecjfg" value="1"
				<% if(permissions.getNodecjfg()!=null&&permissions.getNodecjfg().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodecjfg" value="2"
				<% if(permissions.getNodecjfg()!=null&&permissions.getNodecjfg().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodecjfg" value="3"
				<% if(permissions.getNodecjfg()!=null&&permissions.getNodecjfg().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodecjfg" value="4"
				<% if(permissions.getNodecjfg()!=null&&permissions.getNodecjfg().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodecjfg" value="5"
				<% if(permissions.getNodecjfg()!=null&&permissions.getNodecjfg().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				财经法规内容
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentcjfg" value="1"
				<% if(permissions.getContentcjfg()!=null&&permissions.getContentcjfg().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentcjfg" value="2"
				<% if(permissions.getContentcjfg()!=null&&permissions.getContentcjfg().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentcjfg" value="3"
				<% if(permissions.getContentcjfg()!=null&&permissions.getContentcjfg().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentcjfg" value="4"
				<% if(permissions.getContentcjfg()!=null&&permissions.getContentcjfg().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentcjfg" value="5"
				<% if(permissions.getContentcjfg()!=null&&permissions.getContentcjfg().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				管理制度菜单
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodeglzd" value="1"
				<% if(permissions.getNodeglzd()!=null&&permissions.getNodeglzd().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodeglzd" value="2"
				<% if(permissions.getNodeglzd()!=null&&permissions.getNodeglzd().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodeglzd" value="3"
				<% if(permissions.getNodeglzd()!=null&&permissions.getNodeglzd().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodeglzd" value="4"
				<% if(permissions.getNodeglzd()!=null&&permissions.getNodeglzd().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodeglzd" value="5"
				<% if(permissions.getNodeglzd()!=null&&permissions.getNodeglzd().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				管理制度内容
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentglzd" value="1"
				<% if(permissions.getContentglzd()!=null&&permissions.getContentglzd().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentglzd" value="2"
				<% if(permissions.getContentglzd()!=null&&permissions.getContentglzd().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentglzd" value="3"
				<% if(permissions.getContentglzd()!=null&&permissions.getContentglzd().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentglzd" value="4"
				<% if(permissions.getContentglzd()!=null&&permissions.getContentglzd().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentglzd" value="5"
				<% if(permissions.getContentglzd()!=null&&permissions.getContentglzd().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				组织体系菜单
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodezzxx" value="1"
				<% if(permissions.getNodezzxx()!=null&&permissions.getNodezzxx().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodezzxx" value="2"
				<% if(permissions.getNodezzxx()!=null&&permissions.getNodezzxx().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodezzxx" value="3"
				<% if(permissions.getNodezzxx()!=null&&permissions.getNodezzxx().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodezzxx" value="4"
				<% if(permissions.getNodezzxx()!=null&&permissions.getNodezzxx().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="nodezzxx" value="5"
				<% if(permissions.getNodezzxx()!=null&&permissions.getNodezzxx().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				组织体系内容
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentzzxx" value="1"
				<% if(permissions.getContentzzxx()!=null&&permissions.getContentzzxx().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentzzxx" value="2"
				<% if(permissions.getContentzzxx()!=null&&permissions.getContentzzxx().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentzzxx" value="3"
				<% if(permissions.getContentzzxx()!=null&&permissions.getContentzzxx().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentzzxx" value="4"
				<% if(permissions.getContentzzxx()!=null&&permissions.getContentzzxx().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="contentzzxx" value="5"
				<% if(permissions.getContentzzxx()!=null&&permissions.getContentzzxx().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				文件管理
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="wjgl" value="1"
				<% if(permissions.getWjgl()!=null&&permissions.getWjgl().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="wjgl" value="2"
				<% if(permissions.getWjgl()!=null&&permissions.getWjgl().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="wjgl" value="3"
				<% if(permissions.getWjgl()!=null&&permissions.getWjgl().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="wjgl" value="4"
				<% if(permissions.getWjgl()!=null&&permissions.getWjgl().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="wjgl" value="5"
				<% if(permissions.getWjgl()!=null&&permissions.getWjgl().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				会议通知
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gztz" value="1"
				<% if(permissions.getGztz()!=null&&permissions.getGztz().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gztz" value="2"
				<% if(permissions.getGztz()!=null&&permissions.getGztz().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gztz" value="3"
				<% if(permissions.getGztz()!=null&&permissions.getGztz().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gztz" value="4"
				<% if(permissions.getGztz()!=null&&permissions.getGztz().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gztz" value="5"
				<% if(permissions.getGztz()!=null&&permissions.getGztz().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				计划总结
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jhzj" value="1"
				<% if(permissions.getJhzj()!=null&&permissions.getJhzj().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jhzj" value="2"
				<% if(permissions.getJhzj()!=null&&permissions.getJhzj().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jhzj" value="3"
				<% if(permissions.getJhzj()!=null&&permissions.getJhzj().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jhzj" value="4"
				<% if(permissions.getJhzj()!=null&&permissions.getJhzj().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jhzj" value="5"
				<% if(permissions.getJhzj()!=null&&permissions.getJhzj().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				工作动态
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzdt" value="1"
				<% if(permissions.getGzdt()!=null&&permissions.getGzdt().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzdt" value="2"
				<% if(permissions.getGzdt()!=null&&permissions.getGzdt().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzdt" value="3"
				<% if(permissions.getGzdt()!=null&&permissions.getGzdt().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzdt" value="4"
				<% if(permissions.getGzdt()!=null&&permissions.getGzdt().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzdt" value="5"
				<% if(permissions.getGzdt()!=null&&permissions.getGzdt().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				财务报表
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwbb" value="1"
				<% if(permissions.getCwbb()!=null&&permissions.getCwbb().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwbb" value="2"
				<% if(permissions.getCwbb()!=null&&permissions.getCwbb().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwbb" value="3"
				<% if(permissions.getCwbb()!=null&&permissions.getCwbb().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwbb" value="4"
				<% if(permissions.getCwbb()!=null&&permissions.getCwbb().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwbb" value="5"
				<% if(permissions.getCwbb()!=null&&permissions.getCwbb().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				审计报表
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sjbb" value="1"
				<% if(permissions.getSjbb()!=null&&permissions.getSjbb().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sjbb" value="2"
				<% if(permissions.getSjbb()!=null&&permissions.getSjbb().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sjbb" value="3"
				<% if(permissions.getSjbb()!=null&&permissions.getSjbb().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sjbb" value="4"
				<% if(permissions.getSjbb()!=null&&permissions.getSjbb().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sjbb" value="5"
				<% if(permissions.getSjbb()!=null&&permissions.getSjbb().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				统计报表
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="tjbb" value="1"
				<% if(permissions.getTjbb()!=null&&permissions.getTjbb().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="tjbb" value="2"
				<% if(permissions.getTjbb()!=null&&permissions.getTjbb().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="tjbb" value="3"
				<% if(permissions.getTjbb()!=null&&permissions.getTjbb().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="tjbb" value="4"
				<% if(permissions.getTjbb()!=null&&permissions.getTjbb().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="tjbb" value="5"
				<% if(permissions.getTjbb()!=null&&permissions.getTjbb().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				重大项目申报
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="zdxmsb" value="1"
				<% if(permissions.getZdxmsb()!=null&&permissions.getZdxmsb().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="zdxmsb" value="2"
				<% if(permissions.getZdxmsb()!=null&&permissions.getZdxmsb().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="zdxmsb" value="3"
				<% if(permissions.getZdxmsb()!=null&&permissions.getZdxmsb().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="zdxmsb" value="4"
				<% if(permissions.getZdxmsb()!=null&&permissions.getZdxmsb().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="zdxmsb" value="5"
				<% if(permissions.getZdxmsb()!=null&&permissions.getZdxmsb().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				三公经费使用
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sgjfsy" value="1"
				<% if(permissions.getSgjfsy()!=null&&permissions.getSgjfsy().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sgjfsy" value="2"
				<% if(permissions.getSgjfsy()!=null&&permissions.getSgjfsy().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sgjfsy" value="3"
				<% if(permissions.getSgjfsy()!=null&&permissions.getSgjfsy().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sgjfsy" value="4"
				<% if(permissions.getSgjfsy()!=null&&permissions.getSgjfsy().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="sgjfsy" value="5"
				<% if(permissions.getSgjfsy()!=null&&permissions.getSgjfsy().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				工资福利
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzfl" value="1"
				<% if(permissions.getGzfl()!=null&&permissions.getGzfl().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzfl" value="2"
				<% if(permissions.getGzfl()!=null&&permissions.getGzfl().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzfl" value="3"
				<% if(permissions.getGzfl()!=null&&permissions.getGzfl().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzfl" value="4"
				<% if(permissions.getGzfl()!=null&&permissions.getGzfl().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="gzfl" value="5"
				<% if(permissions.getGzfl()!=null&&permissions.getGzfl().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				财务监督
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwjd" value="1"
				<% if(permissions.getCwjd()!=null&&permissions.getCwjd().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwjd" value="2"
				<% if(permissions.getCwjd()!=null&&permissions.getCwjd().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwjd" value="3"
				<% if(permissions.getCwjd()!=null&&permissions.getCwjd().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwjd" value="4"
				<% if(permissions.getCwjd()!=null&&permissions.getCwjd().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="cwjd" value="5"
				<% if(permissions.getCwjd()!=null&&permissions.getCwjd().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				经济运行分析
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jjyxfx" value="1"
				<% if(permissions.getJjyxfx()!=null&&permissions.getJjyxfx().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jjyxfx" value="2"
				<% if(permissions.getJjyxfx()!=null&&permissions.getJjyxfx().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jjyxfx" value="3"
				<% if(permissions.getJjyxfx()!=null&&permissions.getJjyxfx().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jjyxfx" value="4"
				<% if(permissions.getJjyxfx()!=null&&permissions.getJjyxfx().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="jjyxfx" value="5"
				<% if(permissions.getJjyxfx()!=null&&permissions.getJjyxfx().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				年鉴管理
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njgl" value="1"
				<% if(permissions.getNjgl()!=null&&permissions.getNjgl().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njgl" value="2"
				<% if(permissions.getNjgl()!=null&&permissions.getNjgl().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njgl" value="3"
				<% if(permissions.getNjgl()!=null&&permissions.getNjgl().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njgl" value="4"
				<% if(permissions.getNjgl()!=null&&permissions.getNjgl().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njgl" value="5"
				<% if(permissions.getNjgl()!=null&&permissions.getNjgl().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				年鉴发布
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njfb" value="1"
				<% if(permissions.getNjfb()!=null&&permissions.getNjfb().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njfb" value="2"
				<% if(permissions.getNjfb()!=null&&permissions.getNjfb().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njfb" value="3"
				<% if(permissions.getNjfb()!=null&&permissions.getNjfb().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njfb" value="4"
				<% if(permissions.getNjfb()!=null&&permissions.getNjfb().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="njfb" value="5"
				<% if(permissions.getNjfb()!=null&&permissions.getNjfb().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				学习园地
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="learningcorner"  value="1"
				<% if(permissions.getLearningcorner()!=null&&permissions.getLearningcorner().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="learningcorner"  value="2"
				<% if(permissions.getLearningcorner()!=null&&permissions.getLearningcorner().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="learningcorner"  value="3"
				<% if(permissions.getLearningcorner()!=null&&permissions.getLearningcorner().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="learningcorner"  value="4"
				<% if(permissions.getLearningcorner()!=null&&permissions.getLearningcorner().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="learningcorner"  value="5"
				<% if(permissions.getLearningcorner()!=null&&permissions.getLearningcorner().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				首页窗口
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="postinformation"  value="1"
				<% if(permissions.getPostinformation()!=null&&permissions.getPostinformation().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="postinformation"  value="2"
				<% if(permissions.getPostinformation()!=null&&permissions.getPostinformation().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="postinformation"  value="3"
				<% if(permissions.getPostinformation()!=null&&permissions.getPostinformation().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="postinformation"  value="4"
				<% if(permissions.getPostinformation()!=null&&permissions.getPostinformation().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="postinformation"  value="5"
				<% if(permissions.getPostinformation()!=null&&permissions.getPostinformation().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
		<tr>
			<td align="center" width="25%" class="STYLE3">
				滚动字幕
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="topscroll"  value="1"
				<% if(permissions.getTopscroll()!=null&&permissions.getTopscroll().indexOf("1")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="topscroll"  value="2"
				<% if(permissions.getTopscroll()!=null&&permissions.getTopscroll().indexOf("2")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="topscroll"  value="3"
				<% if(permissions.getTopscroll()!=null&&permissions.getTopscroll().indexOf("3")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="topscroll"  value="4"
				<% if(permissions.getTopscroll()!=null&&permissions.getTopscroll().indexOf("4")!=-1) {%>checked="checked"<% } %> >
			</td>
			<td align="center" width="15%" class="STYLE4">
				<input type="checkbox" name="topscroll"  value="5"
				<% if(permissions.getTopscroll()!=null&&permissions.getTopscroll().indexOf("5")!=-1) {%>checked="checked"<% } %> >
			</td>
		</tr>
	</table>
	</form>
	<table width="100%" >
		<tr height="35px" valign="bottom">
			<td align="center">
				<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/save-as.png" onclick="savePermissions();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
