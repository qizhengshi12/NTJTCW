<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CglibBean"%>
<%@page import="com.safety.entity.Bbdzmb"%>
<%@page import="com.safety.entity.BbdzmbHb"%>
<%@page import="com.safety.entity.BbdzmbJs"%>
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
    <title>ģ���������</title>
   <style type="text/css">
   img {border:0px;}
   </style>
    <script type="text/javascript">
	//����Excel
	function getExcel(){
		if(confirm("ȷ���������б���Excelô��")){
			var form = document.getElementById("form1");
			form.action = "BbdzServlet?action=getBbdzmbExcel";
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
		prnhtml = prnhtml.replace(/"95%"/g,"100%");
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
   			ArrayList BbdzmbHbList = (ArrayList)request.getAttribute("BbdzmbHbList");
   			ArrayList BbdzmbJsList = (ArrayList)request.getAttribute("BbdzmbJsList");
			ArrayList BbdzmbDTList = (ArrayList)request.getAttribute("BbdzmbDTList");
   			Bbdzmb bbdzmb = (Bbdzmb)request.getAttribute("bbdzmb");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=bbdzmb.getId() %>">
		</form>
		<!--startprint1-->
		<table width="95%" align="center">
			<tr>
				<td align="center">
				<span style="font-size: 25px"><%=bbdzmb.getBt() %></span>
				</td>
			</tr>
		</table>
		<table id="table2"  width="95%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<%
				//�½�һ��map,���ڴ洢���õ�tdλ��
				HashMap<String, String> hashmap = new HashMap<String, String>(); 
				for(int i=0;i< BbdzmbDTList.size();i++){
					CglibBean bean = (CglibBean)BbdzmbDTList.get(i);
			%>
			<tr>
				<%
				int num = bbdzmb.getDzls();
				for(int j=0;j<num;j++){
					int p=j+1;
					//ͨ��map,�ж��Ƿ�ȥ����td
					if("Yes".equals(hashmap.get(i+","+j))){
					//System.out.println(i+","+j);
					}else{
				%>
				<td align="center" 
				<%
					for(int k=0;k<BbdzmbHbList.size();k++){
						BbdzmbHb bbdzmbHb = (BbdzmbHb)BbdzmbHbList.get(k);
						if(bbdzmbHb.getRow1()==i+1&&bbdzmbHb.getColumn1()==j+1){
							if("r".equals(bbdzmbHb.getHl())){
								%>
								rowspan="<%=bbdzmbHb.getHs()%>" 
								<%
								//System.out.println(i+","+j);
								//���漸���еĲ���tdȥ��
								for(int q=1;q<bbdzmbHb.getHs();q++){
									hashmap.put(i+q+","+j,"Yes");//��������,��;ֵ��true.��
								}
							}
							if("c".equals(bbdzmbHb.getHl())){
								%>
								colspan="<%=bbdzmbHb.getLs()%>" 
								<%
								j = j+bbdzmbHb.getLs()-1;
								//System.out.println(j);
							}
							if("rc".equals(bbdzmbHb.getHl())){
								//System.out.println(i+","+j);
								//���漸���еĲ���tdȥ��
								for(int m=1;m<bbdzmbHb.getHs();m++){
									for(int n=0;n<bbdzmbHb.getLs();n++){
										hashmap.put((i+m)+","+(j+n),"Yes");//��������,��;ֵ��Yes.��
										//System.out.println(z+","+x);
									}
								}
								%>
								rowspan="<%=bbdzmbHb.getHs()%>" 
								colspan="<%=bbdzmbHb.getLs()%>" 
								<%
								j = j+bbdzmbHb.getLs()-1;
								//System.out.println(j);
							}
						}
					}
				%>
				>
				<%if(bean.getValue("zb"+p)!=null&&!"".equals(bean.getValue("zb"+p))){%><%=bean.getValue("zb"+p)%><%}else{%>&nbsp;<%}%>
				</td>
				<%}%>
				<%}%>
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
	</body>
	<%} %>
</html>



