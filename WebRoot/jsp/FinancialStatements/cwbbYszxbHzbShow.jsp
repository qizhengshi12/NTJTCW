<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbYszxbHzb"%>
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
    <title>�鿴Ԥ��ִ�б���ܱ�</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbYszxbHzbExcel";
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
   			CwbbYszxbHzb cwbbYszxbHzb = (CwbbYszxbHzb)request.getAttribute("cwbbYszxbHzb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbYszxbHzb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="100%" height="10%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				Ԥ��ִ�б���ܱ�
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<%=cwbbYszxbHzb.getYear() %>&nbsp;��&nbsp;<%=cwbbYszxbHzb.getMonth() %>&nbsp;��
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					��λ��<%= cwbbYszxbHzb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ����Ԫ
				</td>
			</tr>
		</table>
		<table id="table2" width="100%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="5%" rowspan="2" height="60px">���
				</td>
				<td align="center" width="10%" rowspan="2">֧�����
				</td>
				<td align="center" width="10%" colspan="2">��·��
				</td>
				<td align="center" width="10%" colspan="2">������
				</td>
				<td align="center" width="10%" colspan="2">���¾�
				</td>
				<td align="center" width="10%" colspan="2">�˹ܴ�
				</td>
				<td align="center" width="10%" colspan="2">���账
				</td>
				<td align="center" width="10%" colspan="2">�ʼദ
				</td>
				<td align="center" width="10%" colspan="2">�������
				</td>
				<td align="center" width="10%" colspan="2">��Ϣ����
				</td>
			</tr>
			<tr>
				<td align="center" width="5%" height="30px">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
				<td align="center" width="5%">Ԥ����
				</td>
				<td align="center" width="5%">������
				</td>
			</tr>
			<tr>
				<td align="center" height="30px">1
				</td>
				<td align="center">����֧��
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getGlc().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getGlc().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHdc().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHdc().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHsj().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHsj().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getYgc().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getYgc().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getJsc().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getJsc().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getZjc().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getZjc().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getKjzx().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getKjzx().split("#;")[1] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getXxzx().split("#;")[0] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getXxzx().split("#;")[1] %>
				</td>
			</tr>
			<tr>
				<td align="center" height="30px">2
				</td>
				<td align="center">��Ŀ֧��
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getGlc().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getGlc().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHdc().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHdc().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHsj().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getHsj().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getYgc().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getYgc().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getJsc().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getJsc().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getZjc().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getZjc().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getKjzx().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getKjzx().split("#;")[3] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getXxzx().split("#;")[2] %>
				</td>
				<td align="center">
				<%=cwbbYszxbHzb.getXxzx().split("#;")[3] %>
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



