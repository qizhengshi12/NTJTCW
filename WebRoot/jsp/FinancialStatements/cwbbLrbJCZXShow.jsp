<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbLrb"%>
<%@page import="com.safety.entity.CwbbLrbsj"%>
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
    <title>利润表（检测中心）</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("确定导出下列表单的Excel么？")){
				var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=getCwbbLrbJCZXExcel";
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
			pagesetup_null();
			var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
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
         	CwbbLrb cwbbLrb = (CwbbLrb)request.getAttribute("cwbbLrb");
    		ArrayList<CwbbLrbsj> CwbbLrbsjList = (ArrayList)request.getAttribute("CwbbLrbsjList");
    		
		%>
		<form id="form1"  method="post">
		<input type="hidden" name="cwbbLrb_id" id="cwbbLrb_id" <%if(cwbbLrb.getId()!=0){%> value="<%=cwbbLrb.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
		<table width="81%" align="center">
			<tr>
				<td align="center" colspan="3" style="font-weight: bold;font-size: 25px">
				<%=cwbbLrb.getBt()%>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
				&nbsp;
				</td>
			</tr>
			<tr>
				<td align="left" width="30%">
					填报单位：<%=cwbbLrb.getCzrdw()%>
				</td>
				<td align="center" width="30%">
					<%if(cwbbLrb.getSbsj()!=null){%><%=cwbbLrb.getSbsj().toString().substring(0,4) %>年<%=cwbbLrb.getSbsj().toString().substring(5,7) %>月<%}%>
				</td>
				<td align="right" width="30%">
					单位：元
				</td>
			</tr>
		</table>
		<table id="table2" width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="50%" height="25px">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目
				</td>
				<td align="center" width="5%">行次
				</td>
				<td align="center" width="22%">本年累计金额
				</td>
				<td align="center" width="23%">本月数
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">一、营业收入
				</td>
				<td align="center">1
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">减：营业成本
				</td>
				<td align="center">2
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">营业税金及附加
				</td>
				<td align="center">3
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：消费税
				</td>
				<td align="center">4
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;营业税
				</td>
				<td align="center">5
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;城市维护建设税
				</td>
				<td align="center">6
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;资源税
				</td>
				<td align="center">7
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;土地增值税
				</td>
				<td align="center">8
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;城镇土地使用税、房产税、车船税、印花税
				</td>
				<td align="center">9
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;教育费附加、矿产资源补偿费、排污费
				</td>
				<td align="center">10
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">销售费用
				</td>
				<td align="center">11
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：商品维修费
				</td>
				<td align="center">12
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;广告费和业务宣传费
				</td>
				<td align="center">13
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">管理费用
				</td>
				<td align="center">14
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：开办费
				</td>
				<td align="center">15
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;业务招待费
				</td>
				<td align="center">16
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;研究费用
				</td>
				<td align="center">17
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">财务费用
				</td>
				<td align="center">18
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：利息费用（收入以“-”号填列）
				</td>
				<td align="center">19
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">加：投资收益（损失以“-”号填列）
				</td>
				<td align="center">20
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">二、营业利润（亏损以“-”号填列）
				</td>
				<td align="center">21
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(20).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(20).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">加：营业外收入
				</td>
				<td align="center">22
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(21).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(21).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：政府补助
				</td>
				<td align="center">23
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(22).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(22).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">减：营业外支出
				</td>
				<td align="center">24
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(23).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(23).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;其中：坏账损失
				</td>
				<td align="center">25
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(24).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(24).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;无法收回的长期债券投资损失
				</td>
				<td align="center">26
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(25).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(25).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;无法收回的长期股权投资损失
				</td>
				<td align="center">27
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(26).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(26).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;自然灾害等不可抗力因素造成的损失
				</td>
				<td align="center">28
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(27).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(27).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">&nbsp;&nbsp;税收滞纳金
				</td>
				<td align="center">29
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(28).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(28).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">三、利润总额（亏损总额以“-”号填列）
				</td>
				<td align="center">30
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(29).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(29).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">减：所得税费用
				</td>
				<td align="center">31
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(30).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(30).getBys()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="25px">四、净利润（净亏损以“-”号填列）
				</td>
				<td align="center">32
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(31).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(31).getBys()%><%}%>
				</td>
			</tr>
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



