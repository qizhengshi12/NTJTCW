<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZczbbg"%>
<%@page import="com.safety.entity.ContentZzxx" %>
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
    <title>�ش���Ŀ�걨����ע���ʱ����������������</title>
   <style type="text/css">
   img {border:0px;}
   </style>
    <script type="text/javascript">
	function cancel(){
		window.open('','_self');
		window.close();
	}
	function saveSH(){
		var form = document.getElementById("form1");
		form.action = "ZdxmsbServlet?action=ZdxmsbSH";
		form.submit();
	}
	function printbody(opr){
		//����ԭҳ��
		var bdhtml=window.document.body.innerHTML;  
		var htmlNoPrint1 =  document.getElementById("NoPrint1").innerHTML;
		//���ô�ӡҳ��
		var sprnstr="<!--startprint"+opr+"-->"; 
		var eprnstr="<!--endprint"+opr+"-->"; 
		pagesetup_null();
		var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
		var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		prnhtml = prnhtml.replace(/"65%"/g,"100%");
		prnhtml = prnhtml.replace(htmlNoPrint1,"��λ�����ˣ�");
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
         	ZdxmsbZczbbg zdxmsbZczbbg = (ZdxmsbZczbbg)request.getAttribute("zdxmsbZczbbg");
         	String flag = (String)request.getAttribute("flag");
		%>
		<!--startprint1-->
		<table width="65%" align="center">
			<tr>
				<td style="font-size: 25px;text-align: center;">
				��ͨ�н�ͨ����ҵ��λ�ش��������浥
				</td>
			</tr>
			<tr>
				<td style="font-size: 25px;text-align: center;">
				����ע���ʱ����
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				һ��ע���ʱ�����ſ�
				</td>
			</tr>
			<tr>
				<td height="" valign="top">
					<table  width="100%" border=1 cellpadding=0 cellspacing=0>
						<tr>
							<td rowspan="2" align="center">
								ԭע���ʱ�<br>����Ԫ��
							</td>
							<td colspan="2" align="center">
								������
							</td>
							<td rowspan="2" align="center">
								�����ע���ʱ�<br>����Ԫ��
							</td>
						</tr>
						<tr>
							<td align="center">
								����<br>����Ԫ��
							</td>
							<td align="center">
								����<br>����Ԫ��
							</td>
						</tr>
						<tr>
							<td align="center" height="50px">
								&nbsp;<%=zdxmsbZczbbg.getZb()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getKz()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getJz()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getBgzb()%>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
				����ע���ʱ��������
				</td>
			</tr>
			<tr>
				<td>
				��һ��ԭע���ʱ��������
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getYqk() %>
				</td>
			</tr>
			<tr>
				<td>
				�����������ע���ʱ��������
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getBgqk() %>
				</td>
			</tr>
			<tr>
				<td>
				����ע���ʱ����������
				</td>
			</tr>
			<tr>
				<td height="100px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getBgyj() %>
				</td>
			</tr>
			<tr>
				<td>
				�ġ����ʵ��ʽ���Դ�������
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getGcqk() %>
				</td>
			</tr>
			<tr>
				<td>
				�塢�����ʱ��Ĵ��÷��������
				</td>
			</tr>
			<tr>
				<td height="80px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getFajg() %>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				���浥λ�� <%if(zdxmsbZczbbg.getCzrdw()!=null){%><%=zdxmsbZczbbg.getCzrdw() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ˣ� <%if(zdxmsbZczbbg.getCzr()!=null){%><%=zdxmsbZczbbg.getCzr() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺 <%if(zdxmsbZczbbg.getCzsj()!=null){%><%=zdxmsbZczbbg.getCzsj().toString().substring(0,4) %>��<%=zdxmsbZczbbg.getCzsj().toString().substring(5,7) %>��<%=zdxmsbZczbbg.getCzsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				<span id="NoPrint1">
				��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbZczbbg.getSmj())&&zdxmsbZczbbg.getSmj()!=null){
				  		String[] fileStr = zdxmsbZczbbg.getSmj().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="ZdxmsbServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>��
					<%}%>
				</span><iframe id='ifm' name='ifm' style="display:none"></iframe>
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<%if("2".equals(flag)){%>
			<tr>
				<td height="50px" valign="middle" align="center">
				<form method="post" id="form1">
				�о����������
				<input type="radio" name="shyj" value="ͬ��" checked="checked">ͬ��
				<input type="radio" name="shyj" value="����">����
				<input type="hidden" name="nrid" id="nrid" value="<%=zdxmsbZczbbg.getId()%>">
				<input type="hidden" name="lx" id="lx" value="1">
				<input type="hidden" name="bhry" id="bhry" value="<%=zdxmsbZczbbg.getCzrID()%>">
				</form>
				</td>
			</tr>
			<tr>
				<td align="center">
				<img alt="ȷ��" width="45px" height="45px" title="ȷ��" style="cursor: pointer;"  src="images/small/save-as.png" onclick="saveSH();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
			<%} else{%>
			<tr>
				<td height="50px" valign="middle">
				�о����������<%if(zdxmsbZczbbg.getShyj()!=null){%><%=zdxmsbZczbbg.getShyj() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�����ˣ�<%=zdxmsbZczbbg.getShr() %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺<%if(zdxmsbZczbbg.getShsj()!=null){%><%=zdxmsbZczbbg.getShsj().toString().substring(0,4) %>��<%=zdxmsbZczbbg.getShsj().toString().substring(5,7) %>��<%=zdxmsbZczbbg.getShsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
				</td>
			</tr>
			<%}%>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<!--endprint1-->
		<%if("1".equals(flag)){%>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="��ӡ" width="45px" height="45px" title="��ӡ" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<%}%>
	</body>
	<%} %>
</html>



