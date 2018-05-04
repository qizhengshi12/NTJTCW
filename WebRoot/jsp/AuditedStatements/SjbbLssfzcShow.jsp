<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbLssfzc"%>
<%@page import="com.safety.entity.SjbbLssfzcZxm"%>
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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>市级机关（含下属事业单位）落实收费政策季度自查表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("确定导出本表单的Excel么？")){
				var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=getSjbbLssfzczcbExcel";
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
			prnhtml = prnhtml.replace(/"1300px"/g,"100%");
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
		if(request.getAttribute("result")!= null) {
	%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<%
 		}
 	%>
		<%
			//从request域中取得要显示的某页信息
         	SjbbLssfzc sjbbLssfzc = (SjbbLssfzc)request.getAttribute("sjbbLssfzc");
         		ArrayList SjbbLssfzcZxmList = (ArrayList)request.getAttribute("sjbbLssfzcZxmList");
		%>
		<form id="form1" method="post">
		<input type="hidden" name="SjbbLssfzc_id" id="SjbbLssfzc_id" <%if(sjbbLssfzc.getId()!=0){%>value="<%=sjbbLssfzc.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
			<table width="1300px" align="center">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;font-size: 25px">
					市级机关（含下属事业单位）落实收费政策季度自查表（<%= sjbbLssfzc.getYear() %>年<% if(sjbbLssfzc.getJd()==0){ %><%}else{%><%=sjbbLssfzc.getJd() %>季度<%}%>）
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
					报送单位（盖章）：<%=sjbbLssfzc.getCzrdw()%>
					</td>
					<td align="right">
					报送时间：<%=sjbbLssfzc.getSbsj()%>
					</td>
				</tr>
			</table>
			<table width="1300px"  align="center" border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" rowspan="2" width="150px">收费<br>单位
					</td>
					<td align="center" colspan="5" width="600px">实际收费情况
					</td>
					<td align="center" colspan="4" width="230px">收费减免政策落实情况
					</td>
					<td align="center" colspan="3" width="300px">存在问题
					</td>
					<td align="center" rowspan="2" width="20px">备<br>注
					</td>
				</tr>
				<tr>
					<td align="center" width="200px">收费<br>项目
					</td>
					<td align="center" width="50px">收费<br>标准
					</td>
					<td align="center" width="50px">收费<br>对象
					</td>
					<td align="center" width="200px">收费<br>依据
					</td>
					<td align="center" width="100px">收费<br>金额<br>（万元）
					</td>
					<td align="center" width="50px">减<br>免<br>对<br>象
					</td>
					<td align="center" width="50px">优<br>惠<br>方<br>式
					</td>
					<td align="center" width="50px">减<br>免<br>依<br>据
					</td>
					<td align="center" width="80px">减免<br>金额<br>（万元）
					</td>
					<td align="center" width="100px">有无<br>未执行<br>政府减免<br>收费规定
					</td>
					<td align="center" width="100px">是否<br>存在无<br>法定依据<br>收费
					</td>
					<td align="center" width="50px">有无<br>搭车<br>收费行为
					</td>
				</tr>
				<%
				for(int i=0;i< SjbbLssfzcZxmList.size();i++){
					SjbbLssfzcZxm sjbbLssfzcZxm = (SjbbLssfzcZxm)SjbbLssfzcZxmList.get(i);
				%>
				<tr>
					<td align="center" height="80px">
					<%=sjbbLssfzcZxm.getZb1()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb2()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb3()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb4()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb5()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb6()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb7()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb8()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb9()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb10()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb11()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb12()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb13()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxm.getZb14()%>
					</td>
				</tr>
				<%} %>
			</table>
			<table width="100%" align="center">
				<tr>
					<td>
					说明：1、请各部门认真汇总下属单位数据，正确填写本表，于每季度下个月的10日前报市物价局收费管理处（7月10日报1-6月份数据）。物价局收费处邮箱：ntwjjsfc@163.com。联系电话：85099592,85216544。
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、无收费实行零报告制。
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填表人：<%=name %>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<%=sjbbLssfzc.getCzrphone()%>
					</td>
				</tr>
			</table>
			
			<!--endprint1-->
			<table  width="100%" align="center">
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



