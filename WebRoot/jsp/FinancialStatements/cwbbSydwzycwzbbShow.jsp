<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbb"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='../../login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�鿴��ҵ��λ��Ҫ����ָ���</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbSydwzycwzbbExcel";
			form.submit();
		} 
	}
	function cancel(){
		window.open('','_self');
		window.close();
	}
	function printbody(opr){
		//����ԭҳ��
		var bdhtml=window.document.body.innerHTML;  
		//���ô�ӡҳ��
		var sprnstr="<!--startprint"+opr+"-->"; 
		var eprnstr="<!--endprint"+opr+"-->"; 
		var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
		var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		pagesetup_null();
		prnhtml = prnhtml.replace(/"81%"/g,"100%");
		window.document.body.innerHTML=prnhtml; 
		//��ӡ
		window.print();
		//��ԭ  
		window.document.body.innerHTML=bdhtml; 
	}
	//������ҳ��ӡ��ҳüҳ��Ϊ��
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
   			CwbbSydwzycwzbb cwbbSydwzycwzbb = (CwbbSydwzycwzbb)request.getAttribute("cwbbSydwzycwzbb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbSydwzycwzbb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="81%" height="10%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				<%= cwbbSydwzycwzbb.getBt() %>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<%=cwbbSydwzycwzbb.getYear() %>&nbsp;��&nbsp;1-<%=cwbbSydwzycwzbb.getMonth() %>&nbsp;��
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					��λ��<%= cwbbSydwzycwzbb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ����Ԫ
				</td>
			</tr>
		</table>
		<table id="table2" width="81%" height="80%" border=1  cellpadding=0 cellspacing=0 align="center">
			<tr>
				<td align="center" width="15%">���
				</td>
				<td align="center" width="60%">ָ������
				</td>
				<td align="center" width="25%">���
				</td>
			</tr>
			<tr>
				<td align="center">1
				</td>
				<td align="left">�ʲ��ܶ�ܹ�ģ��
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getZc() %>
				</td>
			</tr>
			<tr>
				<td align="center">2
				</td>
				<td align="left">ծȨ�ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getZq() %>
				</td>
			</tr>
			<tr>
				<td align="center">3
				</td>
				<td align="left">����Ͷ���ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getDwtz() %>
				</td>
			</tr>
			<tr>
				<td align="center">4
				</td>
				<td align="left">�̶��ʲ��ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getGdzc() %>
				</td>
			</tr>
			<tr>
				<td align="center">5
				</td>
				<td align="left">�����ʽ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getHbzj() %>
				</td>
			</tr>
			<tr>
				<td align="center">6
				</td>
				<td align="left">��ծ�ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getFzze() %>
				</td>
			</tr>
			<tr>
				<td align="center">7
				</td>
				<td align="left">��ծ�ܶ������д������
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getFzye() %>
				</td>
			</tr>
			<tr>
				<td align="center">8
				</td>
				<td align="left">���ʲ��ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getJzc() %>
				</td>
			</tr>
			<tr>
				<td align="center">9
				</td>
				<td align="left">���ʲ���ר�û����ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getJzczy() %>
				</td>
			</tr>
			<tr>
				<td align="center">10
				</td>
				<td align="left">���ʲ�����ҵ�����ܶ�
				</td>
				<td align="center">
				<%=cwbbSydwzycwzbb.getJzcsy() %>
				</td>
			</tr>
		</table>
			<!--endprint1-->
		<table  width="100%" height="10%">
			<tr>
				<td align="center">
				<img alt="��ӡ" width="45px" height="45px" title="��ӡ" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����EXCEL" width="45px" height="45px" title="����EXCEL" style="cursor: pointer;"  src="images/small/office-ms-excel.png" onclick="getExcel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="�ر�" width="45px" height="45px" title="�ر�" style="cursor: pointer;"  src="images/small/close.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



