<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gzflsr"%>
<%@page import="com.safety.entity.GzflsrZxm"%>
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
    <title>�鿴���ʸ�������ͳ�Ʊ�</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
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
				<%=gzflsr.getYear() %>��&nbsp;1-<%=gzflsr.getMonth() %>�¹��ʸ�������ͳ�Ʊ�
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					��λ��<%= gzflsr.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ��Ԫ
				</td>
			</tr>
		</table>
		<table id="table2"  width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="5%" rowspan="2" height="50px">���
				</td>
				<td align="center" width="6%" rowspan="2">ְ��<br>����
				</td>
				<td align="center" width="6%" rowspan="2">��λ<br>����
				</td>
				<td align="center" width="6%" rowspan="2">н��<br>����
				</td>
				<td align="center" width="18%" colspan="2">�����Լ�Ч����
				</td>
				<td align="center" width="9%" rowspan="2">�����Լ�Ч����
				</td>
				<td align="center" width="23%" colspan="3">�ĸ��Բ���
				</td>
				<td align="center" width="9%" rowspan="2">������
				</td>
				<td align="center" width="9%" rowspan="2">��������
				</td>
				<td align="center" width="9%" rowspan="2">Ӧ���ϼ�
				</td>
			</tr>
			<tr>
				<td align="center" width="9%">��λ����
				</td>
				<td align="center" width="9%">�����
				</td>
				<td align="center" width="9%">ס������
				</td>
				<td align="center" width="5%">����
				</td>
				<td align="center" width="9%">Ӧ���ϼ�
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
					עһ�������Լ�Ч������ָ����λ����ְ�����뿼�˰취ȷ����ÿ�°�ϵ������ŵĽ����Լ�Ч���ʡ�
				</td>
			</tr>
			<tr>
				<td align="left">
					ע�����������������ÿ�°�ϵ������ŵĽ����Լ�Ч��������Ľ����Լ�Ч�����Լ������Խ��������������������巢�ŵĸ������뼰���ʡ�
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



