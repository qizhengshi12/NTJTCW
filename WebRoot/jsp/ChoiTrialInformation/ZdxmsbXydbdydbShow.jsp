<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydb"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydbGk"%>
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
    <title>�ش���Ŀ�걨���������������ش��ʲ���Ѻ����������������</title>
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
		prnhtml = prnhtml.replace(/"85%"/g,"100%");
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
         	ZdxmsbXydbdydb zdxmsbXydbdydb = (ZdxmsbXydbdydb)request.getAttribute("zdxmsbXydbdydb");
			ArrayList ZdxmsbXydbdydbGkList = (ArrayList)request.getAttribute("ZdxmsbXydbdydbGkList");
         	String flag = (String)request.getAttribute("flag");
		%>
		<!--startprint1-->
		<table width="85%" align="center">
			<tr>
				<td style="font-size: 25px;text-align: center;">
				��ͨ�н�ͨ����ҵ��λ�ش��������浥
				</td>
			</tr>
			<tr>
				<td style="font-size: 25px;text-align: center;">
				���������������ش��ʲ���Ѻ����
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				һ���ſ�
				</td>
			</tr>
			<tr>
				<td>
				<table id="table1" width="100%" border=1 cellpadding=0 cellspacing=0>
					<tr>
						<td align="center">
							�������ʲ�������
						</td>
						<td align="center">
							�������ʲ���<br>����ʱ��
						</td>
						<td align="center">
							�������ʲ�����ֵ<br>����Ԫ��
						</td>
						<td align="center">
							�������<br>����Ԫ��
						</td>
					</tr>
					<%
					for(int i=0;i< ZdxmsbXydbdydbGkList.size();i++){
						ZdxmsbXydbdydbGk zdxmsbXydbdydbGk = (ZdxmsbXydbdydbGk)ZdxmsbXydbdydbGkList.get(i);
					%>
					<tr>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydbGk.getMc()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydbGk.getPgsj()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydbGk.getJz()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydbGk.getDbed()%>&nbsp;
						</td>
					</tr>
					<%} %>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				�������������������
				</td>
			</tr>
			<tr>
				<td height="120px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%if(zdxmsbXydbdydb.getLy()!=null){%><%=zdxmsbXydbdydb.getLy() %><%}%>
				</td>
			</tr>
			<tr>
				<td>
				�����е��������ε���Ҫ���ݣ����������嵣��Э����Ϊ��������
				</td>
			</tr>
			<tr>
				<td height="120px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%if(zdxmsbXydbdydb.getNr()!=null){%><%=zdxmsbXydbdydb.getNr() %><%}%>
				</td>
			</tr>
			<tr>
				<td>
				�ġ�������������Ҫ����״��
				</td>
			</tr>
			<tr>
				<td>
				<table width="100%" border=1 cellpadding=0 cellspacing=0>
					<tr>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							�ʲ��ܶ�<br>����Ԫ��
						</td>
						<td align="center">
							��ծ�ܶ�<br>����Ԫ��
						</td>
						<td align="center">
							���ʲ��ܶ�<br>����Ԫ��
						</td>
						<td align="center">
							��Ȼ���ˮƽ<br>����Ԫ��
						</td>
						<td align="center">
							�����ʽ��ܶ�<br>����Ԫ��
						</td>
					</tr>
					<tr>
						<td align="left">
						1����������ʵʩǰ
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getZcze1()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getFzze1()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getJzcze1()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getJlsp1()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getZjze1()%>&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left">
						2����������ʵʩ��
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getZcze2()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getFzze2()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getJzcze2()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getJlsp2()%>&nbsp;
						</td>
						<td align="center">
						&nbsp;<%=zdxmsbXydbdydb.getZjze2()%>&nbsp;
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				�塢�����������������ʵʩ�����������
				</td>
			</tr>
			<tr>
				<td height="120px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%if(zdxmsbXydbdydb.getSsqk()!=null){%><%=zdxmsbXydbdydb.getSsqk() %><%}%>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				���浥λ�� <%if(zdxmsbXydbdydb.getCzrdw()!=null){%><%=zdxmsbXydbdydb.getCzrdw() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ˣ� <%if(zdxmsbXydbdydb.getCzr()!=null){%><%=zdxmsbXydbdydb.getCzr() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺 <%if(zdxmsbXydbdydb.getCzsj()!=null){%><%=zdxmsbXydbdydb.getCzsj().toString().substring(0,4) %>��<%=zdxmsbXydbdydb.getCzsj().toString().substring(5,7) %>��<%=zdxmsbXydbdydb.getCzsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				<span id="NoPrint1">
				��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbXydbdydb.getSmj())&&zdxmsbXydbdydb.getSmj()!=null){
				  		String[] fileStr = zdxmsbXydbdydb.getSmj().split(";");
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
				<input type="hidden" name="nrid" id="nrid" value="<%=zdxmsbXydbdydb.getId()%>">
				<input type="hidden" name="lx" id="lx" value="6">
				<input type="hidden" name="bhry" id="bhry" value="<%=zdxmsbXydbdydb.getCzrID()%>">
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
				�о����������<%if(zdxmsbXydbdydb.getShyj()!=null){%><%=zdxmsbXydbdydb.getShyj() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�����ˣ�<%=zdxmsbXydbdydb.getShr() %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺<%if(zdxmsbXydbdydb.getShsj()!=null){%><%=zdxmsbXydbdydb.getShsj().toString().substring(0,4) %>��<%=zdxmsbXydbdydb.getShsj().toString().substring(5,7) %>��<%=zdxmsbXydbdydb.getShsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
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



