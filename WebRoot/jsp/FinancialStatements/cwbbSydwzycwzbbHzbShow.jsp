<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbbHzb"%>
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
    <title>�鿴��ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�</title>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getCwbbSydwzycwzbbHzbExcel";
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
   			CwbbSydwzycwzbbHzb cwbbSydwzycwzbbHzb = (CwbbSydwzycwzbbHzb)request.getAttribute("cwbbSydwzycwzbbHzb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=cwbbSydwzycwzbbHzb.getId() %>">
		</form>
		<!--startprint1-->
		<table  id="table1" width="100%" height="10%" align="center">
			<tr>
				<td align="center" colspan="2" style="font-weight: bold;font-size: 25px;">
				��ͨ��ҵ��λ��Ҫ����ָ��״�����ܱ�
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<%=cwbbSydwzycwzbbHzb.getYear() %>&nbsp;��&nbsp;1-<%=cwbbSydwzycwzbbHzb.getMonth() %>&nbsp;��
				</td>
			</tr>
			<tr>
				<td align="left" width="50%">
					��λ��<%= cwbbSydwzycwzbbHzb.getCzrdw() %>
				</td>
				<td align="right" width="50%">
					��λ����Ԫ
				</td>
			</tr>
		</table>
		<table id="table2" width="100%" align="center" height="80%" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="5%">���
				</td>
				<td align="center" width="15%">ָ������
				</td>
				<td align="center" width="10%">��·��
				</td>
				<td align="center" width="10%">������
				</td>
				<td align="center" width="10%">���¾�
				</td>
				<td align="center" width="10%">�˹ܴ�
				</td>
				<td align="center" width="10%">���账
				</td>
				<td align="center" width="10%">�ʼദ
				</td>
				<td align="center" width="10%">�������
				</td>
				<td align="center" width="10%">��Ϣ����
				</td>
			</tr>
			<tr>
				<td align="center">1
				</td>
				<td align="left">�ʲ��ܶ�ܹ�ģ��
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[0])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[0] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">2
				</td>
				<td align="left">ծȨ�ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[1])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[1] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">3
				</td>
				<td align="left">����Ͷ���ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[2])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[2] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">4
				</td>
				<td align="left">�̶��ʲ��ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[3])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[3] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">5
				</td>
				<td align="left">�����ʽ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[4])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[4] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">6
				</td>
				<td align="left">��ծ�ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[5])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[5] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">7
				</td>
				<td align="left">��ծ�ܶ������д������
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[6])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[6] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">8
				</td>
				<td align="left">���ʲ��ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[7])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[7] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">9
				</td>
				<td align="left">���ʲ���ר�û����ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[8])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[8] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
			</tr>
			<tr>
				<td align="center">10
				</td>
				<td align="left">���ʲ�����ҵ�����ܶ�
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getGlc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getGlc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHdc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getHdc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getHsj().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getHsj().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getYgc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getYgc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getJsc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getJsc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getZjc().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getZjc().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getKjzx().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getKjzx().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
				</td>
				<td align="center">
				<%if(!"".equals(cwbbSydwzycwzbbHzb.getXxzx().split("#;")[9])){%>
				<%=cwbbSydwzycwzbbHzb.getXxzx().split("#;")[9] %>
				<%}else {%>
				&nbsp;
				<%}%>
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



