<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbQy"%>
<%@page import="com.safety.entity.TjbbYxzbQyZxm"%>
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
    <title>���˳�վ������ͳ�Ʊ����˼��ţ�</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("ȷ������������Excelô��")){
				var form = document.getElementById("form1");
				form.action = "BbsbTjbbServlet?action=getTjbbYxzbQyExcel";
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
			prnhtml = prnhtml.replace(/"850px"/g,"100%");
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
        	for(var i=0;i<18;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<6;j++){
        		if(yAxis[j].checked){
        			ybl=true;
        			break;
        		}
        	}
			if(!xbl||!ybl){
				alert("����ѡ��һ��ָ�����ƺ�һ��ͳ������");
				return;
			}
        	var id = document.getElementById("TjbbYxzbQy_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbQxtQy&id="+id;
			form.submit();
		}
		
		//��״ͼ
		function TjbbZzt(){
        	var xAxis = document.getElementsByName("xAxis");
        	var xbl=false;
        	var yAxis = document.getElementsByName("yAxis");
        	var ybl=false;
        	for(var i=0;i<18;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<6;j++){
        		if(yAxis[j].checked){
        			ybl=true;
        			break;
        		}
        	}
			if(!xbl||!ybl){
				alert("����ѡ��һ��ָ�����ƺ�һ��ͳ������");
				return;
			}
        	var id = document.getElementById("TjbbYxzbQy_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbZztQy&id="+id;
			form.submit();
		}
		//��״ͼ
		function TjbbBzt(){
        	var xAxis = document.getElementsByName("xAxis");
        	var xbl=false;
        	var yAxis = document.getElementsByName("yAxis");
        	var ybl=0;
        	for(var i=0;i<18;i++){
        		if(xAxis[i].checked){
        			xbl=true;
        			break;
        		}
        	}
        	for(var j=0;j<6;j++){
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
        	var id = document.getElementById("TjbbYxzbQy_id").value;
			var form = document.getElementById("form2");
			form.action = "BbsbTjbbServlet?action=getTjbbBztQy&id="+id;
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
		   	TjbbYxzbQy tjbbYxzbQy = (TjbbYxzbQy)request.getAttribute("tjbbYxzbQy");
         	ArrayList TjbbYxzbQyZxmStr = (ArrayList)request.getAttribute("TjbbYxzbQyZxmList");
         	
			ArrayList<TjbbYxzbQyZxm> TjbbYxzbQyZxmList = new ArrayList<TjbbYxzbQyZxm>();
         	for(int j=0;j< TjbbYxzbQyZxmStr.size();j++){
				TjbbYxzbQyZxm tjbbYxzbQyZxm = (TjbbYxzbQyZxm)TjbbYxzbQyZxmStr.get(j);
				TjbbYxzbQyZxmList.add(tjbbYxzbQyZxm);
			}
		%>
		<form id="form1" method="post">
		<input type="hidden" name="TjbbYxzbQy_id" id="TjbbYxzbQy_id" <%if(tjbbYxzbQy.getId()!=0){%>value="<%=tjbbYxzbQy.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
		<form id="form2" method="post" target="_blank">
			<table width="850px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					<%=tjbbYxzbQy.getSj().toString().substring(0,4)%>��<%=tjbbYxzbQy.getSj().toString().substring(5,7)%>��<%=tjbbYxzbQy.getBt()%>
					</td>
				</tr>
				<tr>
					<td align="left">
						���λ��<%= tjbbYxzbQy.getCzrdw() %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="850px" border=1 cellpadding=0 cellspacing=0 style="" align="center">
				<tr>
					<td align="center" width="150px" height="50px">��վ���ƣ�������վ��
					</td>
					<td align="center" width="100px">ָ������
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="1" >��������1��
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="2" >��������2��
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="3" >��������%��3��
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="4" >����������ۼ�����4��
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="5" >����������ۼ�����5��
					</td>
					<td align="center" width="100px">
						<input type="checkbox" name="yAxis" value="6" >�������ۼ���ͬ��%��6��
					</td>
				</tr>
				<tr>
					<td align="center">
					<input type="checkbox" name="xAxis" value="1">��ͨ����վ
					</td>
					<td align="center" width="30px" rowspan="8">�����������ˣ�
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(0).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="2">���˶�վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(1).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="3">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(2).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="4">�������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(3).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="5">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(4).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="6">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(5).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="7">�綫����վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(6).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="8">ͨ������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(7).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">&nbsp;
					</td>
					<td align="center">
					<input type="checkbox" name="xAxis" value="9">�ϼ�
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(8).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="10">��ͨ����վ
					</td>
					<td align="center" rowspan="8">
					��ת�������˹��
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(9).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="11">���˶�վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(10).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="12">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(11).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="13">�������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(12).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="14">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(13).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="15">��������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(14).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="16">�綫����վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(15).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">
					<input type="checkbox" name="xAxis" value="17">ͨ������վ
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(16).getZb6()%>
					</td>
				</tr>
				<tr>
					<td align="center" width="30px">&nbsp;
					</td>
					<td align="center">
					<input type="checkbox" name="xAxis" value="18">�ϼ�
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb1()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb2()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb3()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb4()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb5()%>
					</td>
					<td align="center">
					<%=TjbbYxzbQyZxmList.get(17).getZb6()%>
					</td>
				</tr>
			</table>
			<table width="850px" align="center">
				<tr>
					<td align="left" colspan="4">
						���з���˵����<%if(tjbbYxzbQy.getYxfx()!=null){%><%=tjbbYxzbQy.getYxfx()%><%}%>
					</td>
				</tr>
				<tr >
					<td colspan="4">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left" width="25%">
						����ˣ�<%if(tjbbYxzbQy.getShr()!=null){%><%=tjbbYxzbQy.getShr()%><%}%>
					</td>
					<td align="center" width="25%">
						����ˣ�<%=tjbbYxzbQy.getCzr()%>
					</td>
					<td align="center" width="25%">
						�绰��<%if(tjbbYxzbQy.getCzrphone()!=null){%><%=tjbbYxzbQy.getCzrphone()%><%}%>
					</td>
					<td align="right" width="25%">
						�ϱ����ڣ�<%=tjbbYxzbQy.getCzsj().toString().substring(0,10)%>
					</td>
				</tr>
			</table>
		</form>
		<!--endprint1-->
			<table  width="850px" align="center">
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



