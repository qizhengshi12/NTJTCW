<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbYszxb"%>
<%@page import="com.safety.entity.CwbbYszxbZxm"%>
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
    <title>�鿴Ԥ��ִ�б�</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbYszxbExcel";
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
   			CwbbYszxb cwbbYszxb = (CwbbYszxb)request.getAttribute("cwbbYszxb");
   			ArrayList CwbbYszxbZxmList = (ArrayList)request.getAttribute("CwbbYszxbZxmList");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbYszxb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="81%" align="center">
			<tr>
				<td align="center" height="50px" colspan="2" style="font-weight: bold;font-size: 25px;">
				<%=cwbbYszxb.getYear() %>��&nbsp;1-<%=cwbbYszxb.getMonth() %>��<%=cwbbYszxb.getBt()%>
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					���λ��<%= cwbbYszxb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ����Ԫ
				</td>
			</tr>
		</table>
		<table id="table2"  width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="35%" height="50px">&nbsp;
				</td>
				<td align="center" width="15%" style="font-weight: bold;">Ԥ����
				</td>
				<td align="center" width="15%" style="font-weight: bold;">֧����
				</td>
				<td align="center" width="15%" style="font-weight: bold;">������
				</td>
				<td align="center" width="20%" style="font-weight: bold;">֧��������%��
				</td>
			</tr>
			<tr>
				<td align="left" style="font-weight: bold;" height="50px">һ������֧��
				</td>
				<td align="center">
				<%=cwbbYszxb.getJb1() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getJb2() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getJb3() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getJb4() %>
				</td>
			</tr>
			<tr>
				<td align="left" height="50px">1�����ʸ���֧��
				</td>
				<td align="center">
				<%=cwbbYszxb.getGz1() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getGz2() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getGz3() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getGz4() %>
				</td>
			</tr>
			<tr>
				<td align="left" height="50px">2����Ʒ�ͷ���֧��
				</td>
				<td align="center">
				<%=cwbbYszxb.getSp1() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getSp2() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getSp3() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getSp4() %>
				</td>
			</tr>
			<tr>
				<td align="left" height="50px">3���Ը��˺ͼ�ͥ����֧��
				</td>
				<td align="center">
				<%=cwbbYszxb.getBz1() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getBz2() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getBz3() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getBz4() %>
				</td>
			</tr>
			<tr>
				<td align="center" height="50px">&nbsp;
				</td>
				<td align="center">&nbsp;
				</td>
				<td align="center">&nbsp;
				</td>
				<td align="center">&nbsp;
				</td>
				<td align="center">&nbsp;
				</td>
			</tr>
			<tr>
				<td align="left" height="50px" style="font-weight: bold;">������Ŀ֧��
				</td>
				<td align="center">
				<%=cwbbYszxb.getXm1() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getXm2() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getXm3() %>
				</td>
				<td align="center">
				<%=cwbbYszxb.getXm4() %>
				</td>
			</tr>
			<%
				if(!"".equals(cwbbYszxb.getXmid())&&cwbbYszxb.getXmid()!=null){
					for(int i=0;i< CwbbYszxbZxmList.size();i++){
						CwbbYszxbZxm cwbbYszxbZxm = (CwbbYszxbZxm)CwbbYszxbZxmList.get(i);
			
			%>
			<tr>
				<td align="left" height="50px"><%=cwbbYszxbZxm.getZxmmc() %>
				</td>
				<td align="center">
				<%=cwbbYszxbZxm.getZxm1() %>
				</td>
				<td align="center">
				<%=cwbbYszxbZxm.getZxm2() %>
				</td>
				<td align="center">
				<%=cwbbYszxbZxm.getZxm3() %>
				</td>
				<td align="center">
				<%=cwbbYszxbZxm.getZxm4() %>
				</td>
			</tr>
				<%}%>
			<%}%>
			<tr>
				<td align="center" width="35%" height="50px" style="font-weight: bold;">�ϼ�
				</td>
				<td align="center" width="15%">
				<%=cwbbYszxb.getHj1() %>
				</td>
				<td align="center" width="15%">
				<%=cwbbYszxb.getHj2() %>
				</td>
				<td align="center" width="15%">
				<%=cwbbYszxb.getHj3() %>
				</td>
				<td align="center" width="20%">
				<%=cwbbYszxb.getHj4() %>
				</td>
			</tr>
		</table>
			<!--endprint1-->
		<table  width="100%">
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



