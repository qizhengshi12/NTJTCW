<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbJtyssjdytj"%>
<%@page import="com.safety.entity.SjbbJtyssjdytjZxm"%>
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
    <title>交通运输部门审计对象统计表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("确定导出本表单的Excel么？")){
				var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=getSjbbJtyssjdytjExcel";
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
			//prnhtml = prnhtml.replace(/"800px"/g,"100%");
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
         	SjbbJtyssjdytj sjbbJtyssjdytj = (SjbbJtyssjdytj)request.getAttribute("sjbbJtyssjdytj");
         	ArrayList SjbbJtyssjdytjZxmList = (ArrayList)request.getAttribute("SjbbJtyssjdytjZxmList");
    		
		%>
		<form id="form1" method="post">
		<input type="hidden" name="SjbbJtyssjdytj_id" id="SjbbJtyssjdytj_id" <%if(sjbbJtyssjdytj.getId()!=0){%>value="<%=sjbbJtyssjdytj.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
			<table width="1200px">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					交通运输部门审计对象统计表
					</td>
				</tr>
				<tr>
					<td align="center">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left">
						填报单位：<%= sjbbJtyssjdytj.getCzrdw() %>
					</td>
				</tr>
			</table>
			
			<table width="1200px"  border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" colspan="2" height="45px">审计对象名称
					</td>
					<td align="center" rowspan="2">单位性质
					</td>
					<td align="center" colspan="3">2011年度本级单位对所属审计对象开展内部审计情况
					</td>
					<td align="center" colspan="2">2011年度审计对象接受审计机关审计情况
					</td>
				</tr>
				<tr>
					<td align="center" height="35px">类别
					</td>
					<td align="center">名称
					</td>
					<td align="center">审计内容
					</td>
					<td align="center">审计方式
					</td>
					<td align="center">中介机构选择方式
					</td>
					<td align="center">审计机关审计事项
					</td>
					<td align="center">审计实施单位
					</td>
				</tr>
				<tr>
					<td align="center" width="100px" height="30px">（1）
					</td>
					<td align="center" width="200px">（2）
					</td>
					<td align="center" width="100px">（3）
					</td>
					<td align="center" width="200px">（4）
					</td>
					<td align="center" width="150px">（5）
					</td>
					<td align="center" width="150px">（6）
					</td>
					<td align="center" width="200px">（7）
					</td>
					<td align="center" width="100px">（8）
					</td>
				</tr>
				<%
				for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
					SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
				%>
				<tr>
					<td align="center" width="100px" height="30px">
					<%=sjbbJtyssjdytjZxm.getLb()%>
					</td>
					<td align="center" width="200px">
					<%=sjbbJtyssjdytjZxm.getMc()%>
					</td>
					<td align="center" width="100px">
					<%=sjbbJtyssjdytjZxm.getDwxz() %>
					</td>
					<td align=center width="200px">
					<%=sjbbJtyssjdytjZxm.getSjnr()%>
					</td>
					<td align=center width="150px">
					<%=sjbbJtyssjdytjZxm.getSjfs() %>
					</td>
					<td align=center width="150px">
					<%=sjbbJtyssjdytjZxm.getXzfs() %>
					</td>
					<td align=center width="200px">
					<%=sjbbJtyssjdytjZxm.getSjsx()%>
					</td>
					<td align="center" width="100px">
					<%=sjbbJtyssjdytjZxm.getSsdw() %>
					</td>
				</tr>
				<%} %>
			</table>
			
			<table width="1200px">
				<tr>
					<td align="left" width="400px" style="font-size: 12px">
						填表人：<%=sjbbJtyssjdytj.getCzr()%>
					</td>
					<td align="center" width="400px" style="font-size: 12px">
						 联系电话：<%=sjbbJtyssjdytj.getCzrphone()%>
					</td>
					<td align="right" width="400px" style="font-size: 12px">
						填表日期：<%=sjbbJtyssjdytj.getCzsj().toString().substring(0,10)%>
					</td>
				</tr>
			</table>
			<!--endprint1-->
			<table  width="1200px">
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



