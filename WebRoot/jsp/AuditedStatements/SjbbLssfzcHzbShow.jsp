<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbLssfzcHzb"%>
<%@page import="com.safety.entity.SjbbLssfzcZxmHzb"%>
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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("ȷ������������Excelô��")){
				var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=getSjbbLssfzczcbHzbExcel";
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
			prnhtml = prnhtml.replace(/"1300px"/g,"100%");
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
		if(request.getAttribute("result")!= null) {
	%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<%
 		}
 	%>
		<%
			//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	SjbbLssfzcHzb sjbbLssfzcHzb = (SjbbLssfzcHzb)request.getAttribute("sjbbLssfzcHzb");
         		ArrayList SjbbLssfzcZxmHzbList = (ArrayList)request.getAttribute("sjbbLssfzcZxmHzbList");
		%>
		<form id="form1" method="post">
		<input type="hidden" name="SjbbLssfzcHzb_id" id="SjbbLssfzcHzb_id" <%if(sjbbLssfzcHzb.getId()!=0){%>value="<%=sjbbLssfzcHzb.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
			<table width="1300px" align="center">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;font-size: 25px">
					�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��<%= sjbbLssfzcHzb.getYear() %>��<% if(sjbbLssfzcHzb.getJd()==0){ %><%}else{%><%=sjbbLssfzcHzb.getJd() %>����<%}%>��
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
					���͵�λ�����£�����ͨ�н�ͨ�����
					</td>
					<td align="right">
					����ʱ�䣺<%=sjbbLssfzcHzb.getCzsj().toString().substring(0,10)%>
					</td>
				</tr>
			</table>
			<table width="1300px"  align="center" border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" rowspan="2" width="150px">�շ�<br>��λ
					</td>
					<td align="center" colspan="5" width="600px">ʵ���շ����
					</td>
					<td align="center" colspan="4" width="230px">�շѼ���������ʵ���
					</td>
					<td align="center" colspan="3" width="300px">��������
					</td>
					<td align="center" rowspan="2" width="20px">��<br>ע
					</td>
				</tr>
				<tr>
					<td align="center" width="200px">�շ�<br>��Ŀ
					</td>
					<td align="center" width="50px">�շ�<br>��׼
					</td>
					<td align="center" width="50px">�շ�<br>����
					</td>
					<td align="center" width="200px">�շ�<br>����
					</td>
					<td align="center" width="100px">�շ�<br>���<br>����Ԫ��
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>��
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>ʽ
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>��
					</td>
					<td align="center" width="80px">����<br>���<br>����Ԫ��
					</td>
					<td align="center" width="100px">����<br>δִ��<br>��������<br>�շѹ涨
					</td>
					<td align="center" width="100px">�Ƿ�<br>������<br>��������<br>�շ�
					</td>
					<td align="center" width="50px">����<br>�<br>�շ���Ϊ
					</td>
				</tr>
				<%
				for(int i=0;i< SjbbLssfzcZxmHzbList.size();i++){
					SjbbLssfzcZxmHzb sjbbLssfzcZxmHzb = (SjbbLssfzcZxmHzb)SjbbLssfzcZxmHzbList.get(i);
				%>
				<tr>
					<td align="center" height="80px">
					<%=sjbbLssfzcZxmHzb.getZb1()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb2()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb3()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb4()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb5()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb6()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb7()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb8()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb9()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb10()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb11()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb12()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb13()%>
					</td>
					<td align="center">
					<%=sjbbLssfzcZxmHzb.getZb14()%>
					</td>
				</tr>
				<%} %>
			</table>
			<table width="100%" align="center">
				<tr>
					<td>
					˵����1����������������������λ���ݣ���ȷ��д������ÿ�����¸��µ�10��ǰ������۾��շѹ�����7��10�ձ�1-6�·����ݣ�����۾��շѴ����䣺ntwjjsfc@163.com����ϵ�绰��85099592,85216544��
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2�����շ�ʵ���㱨���ơ�
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ˣ�<%=sjbbLssfzcHzb.getCzr()%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ϵ�绰��<%=sjbbLssfzcHzb.getCzrphone()%>
					</td>
				</tr>
			</table>
			
			<!--endprint1-->
			<table  width="100%" align="center">
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



