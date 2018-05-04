<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gzflsr"%>
<%@page import="com.safety.entity.GzflsrZxm"%>
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
    <title>查看工资福利收入统计表</title>
    <script type="text/javascript">
	//导出Excel
	function getExcel(){
		if(confirm("确定导出下列表单的Excel么？")){
			var form = document.getElementById("form1");
			form.action = "GzflsrServlet?action=getGzflsrExcel";
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
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
   			Gzflsr gzflsr = (Gzflsr)request.getAttribute("gzflsr");
   			ArrayList GzflsrZxmList = (ArrayList)request.getAttribute("GzflsrZxmList");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=gzflsr.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="81%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				<%=gzflsr.getYear() %>年&nbsp;1-<%=gzflsr.getMonth() %>月工资福利收入统计表
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					单位：<%= gzflsr.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					单位：元
				</td>
			</tr>
		</table>
		<table id="table2"  width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="5%" rowspan="2" height="50px">编号
				</td>
				<td align="center" width="6%" rowspan="2">职工<br>姓名
				</td>
				<td align="center" width="6%" rowspan="2">岗位<br>工资
				</td>
				<td align="center" width="6%" rowspan="2">薪级<br>工资
				</td>
				<td align="center" width="18%" colspan="2">基础性绩效工资
				</td>
				<td align="center" width="9%" rowspan="2">奖励性绩效工资
				</td>
				<td align="center" width="23%" colspan="3">改革性补贴
				</td>
				<td align="center" width="9%" rowspan="2">公积金
				</td>
				<td align="center" width="9%" rowspan="2">其它收入
				</td>
				<td align="center" width="9%" rowspan="2">应发合计
				</td>
			</tr>
			<tr>
				<td align="center" width="9%">岗位津贴
				</td>
				<td align="center" width="9%">生活补贴
				</td>
				<td align="center" width="9%">住房补贴
				</td>
				<td align="center" width="5%">车贴
				</td>
				<td align="center" width="9%">应发合计
				</td>
			</tr>
			<%
				for(int i=0;i< GzflsrZxmList.size();i++){
					GzflsrZxm gzflsrZxm = (GzflsrZxm)GzflsrZxmList.get(i);
			%>
			<tr>
				<td align="center" height="30px">
				<%=i+1%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getZgxm()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getGwgz()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getXjgz()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getGwjt()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getShbt()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getJljx()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getZfbt()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getCt()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getYbbt()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getGjj()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getQtsr()%>
				</td>
				<td align="center">
				<%=gzflsrZxm.getYfhj()%>
				</td>
			</tr>
			<%} %>
		</table>
		<table width="81%" align="center">
			<tr>
				<td align="left">
					注一：奖励性绩效工资是指各单位按照职工收入考核办法确定的每月按系数定额发放的奖励性绩效工资。
				</td>
			</tr>
			<tr>
				<td align="left">
					注二：其它收入包括除每月按系数定额发放的奖励性绩效工资以外的奖励性绩效工资以及各类以奖励、补贴、福利等名义发放的个人收入及物资。
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



