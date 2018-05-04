<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbZcfzb"%>
<%@page import="com.safety.entity.CwbbZcfzbzc"%>
<%@page import="com.safety.entity.CwbbZcfzbfz"%>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   	<style type="text/css">
   	/*字体样式*/
	.print1
	{
		font-size: 7px;
		height: 18px;
	}
	/*字体样式*/
	.print2
	{
		font-size: 7px;
		height: 18px;
		background-color: #A9A9A9;
	}
   	</style>
    <title>资产负债表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("确定导出本表单的Excel么？")){
				var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=getCwbbZcfzbTZGSExcel";
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
			prnhtml = prnhtml.replace(/"STYLE5"/g,"print1");
			prnhtml = prnhtml.replace(/"STYLE6"/g,"print2");
			prnhtml = prnhtml.replace(/"81%"/g,"100%");
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
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	CwbbZcfzb cwbbZcfzb = (CwbbZcfzb)request.getAttribute("cwbbZcfzb");
    		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = (ArrayList)request.getAttribute("CwbbZcfzbzcList");
    		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = (ArrayList)request.getAttribute("CwbbZcfzbfzList");
    		
		%>
		<form id="form1" method="post">
		<input type="hidden" name="cwbbZcfzb_id" id="cwbbZcfzb_id" <%if(cwbbZcfzb.getId()!=0){%>value="<%=cwbbZcfzb.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
			<table  id="table1" width="81%" align="center">
				<tr>
					<td align="center" colspan="3" style="font-weight: bold;font-size: 25px">
					<%=cwbbZcfzb.getBt()%>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" width="30%" class="STYLE5">
						填报单位：<%=cwbbZcfzb.getCzrdw()%>
					</td>
					<td align="center" width="30%" class="STYLE5">
						<%if(cwbbZcfzb.getSbsj()!=null){%><%=cwbbZcfzb.getSbsj()%><%}else{%>&nbsp;<%}%>
					</td>
					<td align="right" width="30%" class="STYLE5">
						单位：元
					</td>
				</tr>
			</table>
			<table id="table2" width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="20%" class="STYLE6">资&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产
					</td>
					<td align="center" width="4%" class="STYLE6">行次
					</td>
					<td align="center" width="13%" class="STYLE6">年初数
					</td>
					<td align="center" width="13%" class="STYLE6">期末数
					</td>
					<td align="center" width="20%" class="STYLE6">负债及所有者权益<br>（或股东权益）
					</td>
					<td align="center" width="4%" class="STYLE6">行次
					</td>
					<td align="center" width="13%" class="STYLE6">年初数
					</td>
					<td align="center" width="13%" class="STYLE6">期末数
					</td>
				</tr>
				<%for(int i=0; i<CwbbZcfzbzcList.size(); i++){
				%>
				<tr>
					<td align="left" class="STYLE5"><%=CwbbZcfzbzcList.get(i).getMc()%>
					</td>
					<td align="center" class="STYLE6"><%=CwbbZcfzbzcList.get(i).getHc()%>
					</td>
					<td align="right" class="STYLE5"><%=CwbbZcfzbzcList.get(i).getNcs()%>
					</td>
					<td align="right" class="STYLE5"><%=CwbbZcfzbzcList.get(i).getQms()%>
					</td>
					<td align="left" class="STYLE5"><%=CwbbZcfzbfzList.get(i).getMc()%>
					</td>
					<td align="center" class="STYLE6"><%=CwbbZcfzbfzList.get(i).getHc()%>
					</td>
					<td align="right" class="STYLE5"><%=CwbbZcfzbfzList.get(i).getNcs()%>
					</td>
					<td align="right" class="STYLE5"><%=CwbbZcfzbfzList.get(i).getQms()%>
					</td>
				</tr>
				<%}%>
			</table>
			<!--endprint1-->
			<table  width="100%">
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
	<%}%>
	</body>
</html>



