<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSgjfb"%>
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
    <title>�鿴�������ѱ�</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbSgjfbExcel";
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
   			CwbbSgjfb cwbbSgjfb = (CwbbSgjfb)request.getAttribute("cwbbSgjfb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbSgjfb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="81%" height="10%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				<%=cwbbSgjfb.getYear() %>��&nbsp;1-<%=cwbbSgjfb.getMonth() %>�¡�����������Ԥ��������������
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					���λ��<%= cwbbSgjfb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ����Ԫ
				</td>
			</tr>
		</table>
		<table id="table2"  width="81%" height="80%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="40%">��Ŀ
				</td>
				<td align="center" width="20%">Ԥ����
				</td>
				<td align="center" width="20%">֧����
				</td>
				<td align="center" width="20%">֧������%
				</td>
			</tr>
			<tr>
				<td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHj1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHj2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHj3() %>
				</td>
			</tr>
			<tr>
				<td align="left">1���򹫳�������������
				</td>
				<td align="center">
				<%=cwbbSgjfb.getCgf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getCgf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getCgf3() %>
				</td>
			</tr>
			<tr>
				<td align="left">2������Ӵ���
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJdf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJdf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJdf3() %>
				</td>
			</tr>
			<tr>
				<td align="left">���У���1������֧����֧
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJbzc1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJbzc2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getJbzc3() %>
				</td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��2����Ŀ֧����֧
				</td>
				<td align="center">
				<%=cwbbSgjfb.getXmzc1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getXmzc2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getXmzc3() %>
				</td>
			</tr>
			<tr>
				<td align="left">3�������ó���
				</td>
				<td align="center">
				<%=cwbbSgjfb.getYcf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getYcf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getYcf3() %>
				</td>
			</tr>
			<tr>
				<td align="left">���У���1���������÷�
				</td>
				<td align="center">
				<%=cwbbSgjfb.getGzf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getGzf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getGzf3() %>
				</td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��2����������ά����
				</td>
				<td align="center">
				<%=cwbbSgjfb.getWhf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getWhf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getWhf3() %>
				</td>
			</tr>
			<tr>
				<td align="left">4�������
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHyf1() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHyf2() %>
				</td>
				<td align="center">
				<%=cwbbSgjfb.getHyf3() %>
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



