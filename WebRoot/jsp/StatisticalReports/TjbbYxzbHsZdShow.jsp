<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbHsZd"%>
<%@page import="com.safety.entity.TjbbYxzbHsZdZxm"%>
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
    <title>����վ��ǩ֤����</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("ȷ������������Excelô��")){
				var form = document.getElementById("form1");
				form.action = "BbsbTjbbServlet?action=getTjbbYxzbHsZdExcel";
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
			//�������и�ѡ��
        	var xAxis = document.getElementsByName("xAxis");
        	var yAxis = document.getElementsByName("yAxis");
        	for(var i=0;i<xAxis.length;i++){
         		xAxis[i].type = "hidden";//�������и�ѡ��;
        	}
        	for(var j=0;j<yAxis.length;j++){
         		yAxis[j].type = "hidden";//�������и�ѡ��;
        	}
        	var bdhtmlnew=window.document.body.innerHTML; 
			//���ô�ӡҳ��
			var sprnstr="<!--startprint"+opr+"-->"; 
			var eprnstr="<!--endprint"+opr+"-->"; 
			var prnhtml=bdhtmlnew.substring(bdhtmlnew.indexOf(sprnstr)+18); 
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
		//����ͼ
		function TjbbQxt(){
        	var xAxis = document.getElementsByName("xAxis");
        	var xbl=false;
        	var yAxis = document.getElementsByName("yAxis");
        	var ybl=false;
        	for(var i=0;i<12;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<10;j++){
        		if(yAxis[j].checked){
        			ybl=true;
        			break;
        		}
        	}
			if(!xbl||!ybl){
				alert("����ѡ��һ��ָ�����ƺ�һ��ͳ������");
				return;
			}
        	var id = document.getElementById("TjbbYxzbHsZd_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbQxtHsZd&id="+id;
			form.submit();
		}
		
		//��״ͼ
		function TjbbZzt(){
        	var xAxis = document.getElementsByName("xAxis");
        	var xbl=false;
        	var yAxis = document.getElementsByName("yAxis");
        	var ybl=false;
        	for(var i=0;i<12;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<10;j++){
        		if(yAxis[j].checked){
        			ybl=true;
        			break;
        		}
        	}
			if(!xbl||!ybl){
				alert("����ѡ��һ��ָ�����ƺ�һ��ͳ������");
				return;
			}
        	var id = document.getElementById("TjbbYxzbHsZd_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbZztHsZd&id="+id;
			form.submit();
		}
		//��״ͼ
		function TjbbBzt(){
        	var xAxis = document.getElementsByName("xAxis");
        	var xbl=false;
        	var yAxis = document.getElementsByName("yAxis");
        	var ybl=0;
        	for(var i=0;i<12;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<10;j++){
        		if(yAxis[j].checked){
        			ybl=ybl+1;
        		}
        	}
			if(!xbl){
				alert("����ѡ��һ��ָ������");
				return;
			}
			if(ybl!=1){
				alert("��״ͼֻ��ѡ��һ��ͳ������");
				return;
			}
        	var id = document.getElementById("TjbbYxzbHsZd_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbBztHsZd&id="+id;
			form.submit();
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
		    //String permissions = (String)request.getAttribute("permissions"); 
		   	TjbbYxzbHsZd tjbbYxzbHsZd = (TjbbYxzbHsZd)request.getAttribute("tjbbYxzbHsZd");
         	ArrayList TjbbYxzbHsZdZxmStr = (ArrayList)request.getAttribute("TjbbYxzbHsZdZxmList");
         	
			ArrayList<TjbbYxzbHsZdZxm> TjbbYxzbHsZdZxmList = new ArrayList<TjbbYxzbHsZdZxm>();
         	for(int j=0;j< TjbbYxzbHsZdZxmStr.size();j++){
				TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = (TjbbYxzbHsZdZxm)TjbbYxzbHsZdZxmStr.get(j);
				TjbbYxzbHsZdZxmList.add(tjbbYxzbHsZdZxm);
			}
		%>
		<form id="form1" method="post">
		<input type="hidden" name="TjbbYxzbHsZd_id" id="TjbbYxzbHsZd_id" <%if(tjbbYxzbHsZd.getId()!=0){%>value="<%=tjbbYxzbHsZd.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
		<form id="form2" method="post" target="_blank">
			<table width="1300px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					<%=tjbbYxzbHsZd.getYear()%>��<%=tjbbYxzbHsZd.getMonth()%>��<%=tjbbYxzbHsZd.getBt()%>
					</td>
				</tr>
				<tr>
					<td align="left">
						���λ��<%= tjbbYxzbHsZd.getCzrdw() %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="1300px" border=1 cellpadding=0 cellspacing=0 style="" align="center">
				<tr>
					<td align="center" width="100px" height="100px" rowspan="2">վ������
					</td>
					<td align="center" width="100px" height="100px" rowspan="2">�·�
					</td>
					<td align="center" width="500px" height="50px" colspan="5">����ǩ֤�������֣�
					</td>
					<td align="center" width="500px" height="50px" colspan="5">����ǩ֤�������֣�
					</td>
					<td align="center" width="100px" height="100px" rowspan="2">����ǩ֤�漰�ۿڷ�Χ
					</td>
				</tr>
				<tr>
					<td align="center" width="100px" height="50px">
						<input type="checkbox" name="yAxis" value="1" >�ܼ�
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="2" >1000��(��)���´���
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="3" >1000��5000��(��)����
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="4" >5000��10000��(��)����
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="5" >10000�����ϴ���
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="6" >�ܼ�
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="7" >1000��(��)���´���
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="8" >1000��5000��(��)����
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="9" >5000��10000��(��)����
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="10" >10000�����ϴ���
					</td>
				</tr>
				<tr>
					<td align="center" width="30px" rowspan="12"><%=tjbbYxzbHsZd.getZdmc()%>
					</td>
					<td align="center">
					<input type="checkbox" name="xAxis" value="1">1
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(0).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="2">2
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(1).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="3">3
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(2).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="4">4
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(3).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="5">5
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(4).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="6">6
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(5).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="7">7
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(6).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="8">8
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(7).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center">
					<input type="checkbox" name="xAxis" value="9">9
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(8).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="10">10
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(9).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="11">11
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(10).getZb11()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="12">12
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb6()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb7()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb8()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb9()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb10()%>
					</td>
					<td align="center">
					<%=TjbbYxzbHsZdZxmList.get(11).getZb11()%>
					</td>
				</tr>
			</table>
			<table width="1300px" align="center">
				<tr >
					<td colspan="3">
						ע1����������12��2��ǰ�ϱ�2013��1��10�����ؼ��¶�������ͳ�����㲹�����ݣ�11-12�����ݿ��ӳ���1��5��֮ǰ�ϱ���
					</td>
				</tr>
				<tr >
					<td colspan="3">
						ע2�����ΧΪȫ�еط�����ǩ֤�������д2014����������
					</td>
				</tr>
				<tr >
					<td colspan="3">
						ע3��ǩ֤�������������ֶ�λ��������ֶܶ�λ������д��
					</td>
				</tr>
				<tr >
					<td colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left" width="30%">
						��ϵ�ˣ�<%if(tjbbYxzbHsZd.getLxr()!=null){%><%=tjbbYxzbHsZd.getLxr()%><%}%>
					</td>
					<td align="center" width="40%">
						��ϵ�绰��<%if(tjbbYxzbHsZd.getLxrdh()!=null){%><%=tjbbYxzbHsZd.getLxrdh()%><%}%>
					</td>
					<td align="right" width="30%">
						�ϱ����ڣ�<%=tjbbYxzbHsZd.getCzsj().toString().substring(0,10)%>
					</td>
				</tr>
			</table>
		</form>
		<!--endprint1-->
			<table  width="1300px" align="center">
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left" width="40%">
				<img alt="����ͼ" width="32px" height="32px" align="top" title="����ͼ" style="cursor: pointer;"  src="images/small/qxt.png" onclick="TjbbQxt();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;
				<img alt="��״ͼ" width="32px" height="32px" align="top" title="��״ͼ" style="cursor: pointer;"  src="images/small/zzt.png" onclick="TjbbZzt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;
				<img alt="��״ͼ" width="32px" height="32px" align="top" title="��״ͼ" style="cursor: pointer;"  src="images/small/bzt.png" onclick="TjbbBzt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
					<td align="left" width="60%">
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



