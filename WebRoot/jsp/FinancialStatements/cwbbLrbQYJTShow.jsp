<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbLrb"%>
<%@page import="com.safety.entity.CwbbLrbsj"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    <title>�����</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("ȷ���������б���Excelô��")){
				var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=getCwbbLrbQYJTExcel";
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
			pagesetup_null();
			var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
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
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
		<%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
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
					���λ��<%=cwbbLrb.getCzrdw()%>
				</td>
				<td align="center" width="30%">
					<%if(cwbbLrb.getSbsj()!=null){%><%=cwbbLrb.getSbsj().toString().substring(0,4) %>��<%=cwbbLrb.getSbsj().toString().substring(5,7) %>��<%}%>
				</td>
				<td align="right" width="30%">
					��λ��Ԫ
				</td>
			</tr>
		</table>
		<table id="table2" width="81%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<tr>
				<td align="center" width="43%" height="40px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ŀ
				</td>
				<td align="center" width="6%">�д�
				</td>
				<td align="center" width="17%">������
				</td>
				<td align="center" width="17%">�����ۼ���
				</td>
				<td align="center" width="17%">����ͬ����
				</td>
			</tr>
			<%if(CwbbLrbsjList.size()<21) {%>
			<tr>
				<td align="left" height="40px">һ����Ӫҵ������
				</td>
				<td align="center">1
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;������Ӫҵ��ɱ�
				</td>
				<td align="center">2
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ӫҵ˰�𼰸���
				</td>
				<td align="center">3
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">������Ӫҵ������
				</td>
				<td align="center">4
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;�ӣ�����ҵ������
				</td>
				<td align="center">5
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У�����ҵ������
				</td>
				<td align="center">6
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;����Ӫҵ����
				</td>
				<td align="center">7
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
				</td>
				<td align="center">8
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
				</td>
				<td align="center">9
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">����Ӫҵ����
				</td>
				<td align="center">10
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;�ӣ�Ͷ������
				</td>
				<td align="center">11
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
				</td>
				<td align="center">12
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У�����ǰ������ҵ��������
				</td>
				<td align="center">13
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ӫҵ������
				</td>
				<td align="center">14
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;����Ӫҵ��֧��
				</td>
				<td align="center">15
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">�ġ������ܶ�
				</td>
				<td align="center">16
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У������������ҵ�����ܶ�
				</td>
				<td align="center">17
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;��������˰
				</td>
				<td align="center">18
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ɶ�����
				</td>
				<td align="center">19
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">�塢������
				</td>
				<td align="center">20
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getSntqs()%><%}%>
				</td>
			</tr>
			<%}else{ %>
			<tr>
				<td align="left" height="40px">һ����Ӫҵ������
				</td>
				<td align="center">1
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(0).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;������Ӫҵ��ɱ�
				</td>
				<td align="center">2
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(1).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ӫҵ˰�𼰸���
				</td>
				<td align="center">3
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(2).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">������Ӫҵ������
				</td>
				<td align="center">4
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(3).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;�ӣ�����ҵ������
				</td>
				<td align="center">5
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(4).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У�����ҵ������
				</td>
				<td align="center">6
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(5).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;�ӣ����ʼ�ֵ�䶯���棨��ʧ��"-"���У�
				</td>
				<td align="center">7
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(6).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;����Ӫҵ����
				</td>
				<td align="center">8
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(7).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
				</td>
				<td align="center">9
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(8).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
				</td>
				<td align="center">10
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(9).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ʲ���ֵ��ʧ
				</td>
				<td align="center">11
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(10).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">����Ӫҵ����
				</td>
				<td align="center">12
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(11).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;�ӣ�Ͷ������
				</td>
				<td align="center">13
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(12).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
				</td>
				<td align="center">14
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(13).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У�����ǰ������ҵ��������
				</td>
				<td align="center">15
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(14).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ӫҵ������
				</td>
				<td align="center">16
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(15).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;����Ӫҵ��֧��
				</td>
				<td align="center">17
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(16).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">�ġ������ܶ�
				</td>
				<td align="center">18
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(17).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���У������������ҵ�����ܶ�
				</td>
				<td align="center">19
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(18).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;��������˰
				</td>
				<td align="center">20
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(19).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ɶ�����
				</td>
				<td align="center">21
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(20).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(20).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(20).getSntqs()%><%}%>
				</td>
			</tr>
			<tr>
				<td align="left" height="40px">�塢������
				</td>
				<td align="center">22
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(21).getBys()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(21).getBnljs()%><%}%>
				</td>
				<td align="right">
				<%if(CwbbLrbsjList.size()!=0){%><%=CwbbLrbsjList.get(21).getSntqs()%><%}%>
				</td>
			</tr>
			<%} %>
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
	<%}%>
	</body>
</html>



