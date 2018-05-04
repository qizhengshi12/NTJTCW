<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbbHzb"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='../../login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>查看交通事业单位主要财务指标状况汇总表</title>
    <script type="text/javascript">
	//导出Excel
	function getExcel(){
		if(confirm("确定导出下列表单的Excel么？")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbSydwzycwzbbHzbExcel";
			form.submit();
		} 
	}
	function cancel(){
		window.open('','_self');
		window.close();
	}
	function printbody(opr){
		//保存原页面
		var bdhtml=window.document.body.innerHTML;  
		//设置打印页面
		var sprnstr="<!--startprint"+opr+"-->"; 
		var eprnstr="<!--endprint"+opr+"-->"; 
		var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
		var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		pagesetup_null();
		window.document.body.innerHTML=prnhtml; 
		//打印
		window.print();
		//还原  
		window.document.body.innerHTML=bdhtml; 
	}
	//设置网页打印的页眉页脚为空
	function pagesetup_null() {
		var hkey_root, hkey_path, hkey_key;
		hkey_root = "HKEY_CURRENT_USER"
		hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		try {
			var RegWsh = new ActiveXObject("WScript.Shell");
			hkey_key = "header";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
			hkey_key = "footer";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		} catch (e) {
		}
	}
    </script>
	</head>
	<body>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
   			CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = (CwbbSydwzycwzbbHzb)request.getAttribute("cwbbSydwzycwzbbHzb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbSydwzycwzbbHzb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="100%" height="10%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				交通事业单位主要财务指标状况汇总表
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<%=cwbbSydwzycwzbbHzb.getYear() %>&nbsp;年&nbsp;1-<%=cwbbSydwzycwzbbHzb.getMonth() %>&nbsp;月
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					单位：<%= cwbbSydwzycwzbbHzb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					单位：万元
				</td>
			</tr>
		</table>
		<table id="table2" width="100%" align="center" height="80%" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="5%">序号
				</td>
				<td align="center" width="15%">指标名称
				</td>
				<td align="center" width="10%">公路处
				</td>
				<td align="center" width="10%">航道处
				</td>
				<td align="center" width="10%">海事局
				</td>
				<td align="center" width="10%">运管处
				</td>
				<td align="center" width="10%">建设处
				</td>
				<td align="center" width="10%">质监处
				</td>
				<td align="center" width="10%">会计中心
				</td>
				<td align="center" width="10%">信息中心
				</td>
			</tr>
			<tr>
				<td align="center">1
				</td>
				<td align="left">资产总额（总规模）
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">2
				</td>
				<td align="left">债权总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">3
				</td>
				<td align="left">对外投资总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">4
				</td>
				<td align="left">固定资产总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">5
				</td>
				<td align="left">货币资金
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">6
				</td>
				<td align="left">负债总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">7
				</td>
				<td align="left">负债总额中银行贷款余额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">8
				</td>
				<td align="left">净资产总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">9
				</td>
				<td align="left">净资产中专用基金总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">10
				</td>
				<td align="left">净资产中事业结余总额
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
		</table>
			<!--endprint1-->
		<table  width="100%" height="10%">
			<tr>
				<td align="center">
				<img alt="打印" width="45px" height="45px" title="打印" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="导出EXCEL" width="45px" height="45px" title="导出EXCEL" style="cursor: pointer;"  src="images/small/office-ms-excel.png" onclick="getExcel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="关闭" width="45px" height="45px" title="关闭" style="cursor: pointer;"  src="images/small/close.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



