<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.ContentFl"%>  
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�鿴����</title>
	<script type="text/javascript">
		function printbody(opr){
			//����ԭҳ��
			var bdhtml=window.document.body.innerHTML;  
			//���ô�ӡҳ��
			var sprnstr="<!--startprint"+opr+"-->"; 
			var eprnstr="<!--endprint"+opr+"-->"; 
			var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			//��ӡ
			window.print();
			//��ԭ  
			window.document.body.innerHTML=bdhtml; 
		}
		function cancel(){
			window.open('','_self');
			window.close();
		}
	</script>
	</head>
	<body>
		<%
			ContentFl Fl = (ContentFl)request.getAttribute("Fl"); 
			String flag = (String)request.getAttribute("flag"); 
		%>
		<form id="form1" name="Fl" method="post">
		<!--startprint1-->
			<table  width="100%">
				<tr>
					<td>
						<strong>���⣺</strong><span><%=Fl.getBt()%></span>
					</td>
					<td width="250px">
						<strong>�ĺţ�</strong><span><%=Fl.getWh()%></span>
					</td>
				</tr>
				<tr>
					<td>
						<strong>ʵʩ���ڣ�</strong><span><%=Fl.getSsrq()%></span>
					</td>
					<td>
						<strong>������λ��</strong><span><%=Fl.getFbdw()%></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<span style="font-size: 25px;font-weight: bold;font-stretch: "><%=Fl.getBt()%></span>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<%=Fl.getFlTxt()%>
					</td>
				</tr>
			<!--endprint1-->
			<%if("1".equals(flag)){ %>
				<tr>
					<td align="center" colspan="2">
						<img alt="�ر�" width="100px" height="60px" title="�ر�" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			<%}else{ %>
				<tr>
					<td align="center" colspan="2">
					<img alt="��ӡ" width="45px" height="45px" title="��ӡ" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			<%} %>
			</table>
		</form>
		<%} %>
	</body>
</html>

