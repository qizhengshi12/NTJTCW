<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZdrz"%>
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
    <title>�ش���Ŀ�걨�����ش����ʡ�����������</title>
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
         	ZdxmsbZdrz zdxmsbZdrz = (ZdxmsbZdrz)request.getAttribute("zdxmsbZdrz");
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
				�����ش����ʣ���ծ��
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td height="50px" valign="top">
				һ�����ʣ���ծ����ȣ�<%if(zdxmsbZdrz.getEd()!=null&&!"".equals(zdxmsbZdrz.getEd())){%><%=zdxmsbZdrz.getEd() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>��Ԫ��
				</td>
			</tr>
			<tr>
				<td>
				�������ʣ���ծ����Ŀ������
				</td>
			</tr>
			<tr>
				<td height="80px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZdrz.getYy() %>
				</td>
			</tr>
			<tr>
				<td>
				�����ƻ�����ʱ��
				</td>
			</tr>
			<tr>
				<td height="40px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZdrz.getJhsj1() %>&nbsp;��&nbsp;<%=zdxmsbZdrz.getJhsj2() %>
				</td>
			</tr>
			<tr>
				<td>
				�ġ�������Դ
				</td>
			</tr>
			<tr>
				<td height="60px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZdrz.getLy() %>
				</td>
			</tr>
			<tr>
				<td>
				�塢Ԥ�Ʒ������Ϣ֧�����
				</td>
			</tr>
			<tr>
				<td height="60px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZdrz.getZfqk() %>
				</td>
			</tr>
			<tr>
				<td>
				���������Ѻ�򵣱����
				</td>
			</tr>
			<tr>
				<td height="60px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZdrz.getDbqk() %>
				</td>
			</tr>
			<tr>
				<td>
				�ߡ����ծ��������
				</td>
			</tr>
			<tr>
				<td height="40px" valign="middle" style="font-size: 14px">
				��һ����ǰ����ۼƻ��<%if(zdxmsbZdrz.getYqhk()!=null&&!"".equals(zdxmsbZdrz.getYqhk())){%><%=zdxmsbZdrz.getYqhk() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>��Ԫ��
				</td>
			</tr>
			<tr>
				<td height="30px" valign="middle" style="font-size: 14px">
				������������ѻ��<%if(zdxmsbZdrz.getNdhk()!=null&&!"".equals(zdxmsbZdrz.getNdhk())){%><%=zdxmsbZdrz.getNdhk() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>��Ԫ��
				������ֹ�ۼ��ѻ��<%if(zdxmsbZdrz.getLjhk()!=null&&!"".equals(zdxmsbZdrz.getLjhk())){%><%=zdxmsbZdrz.getLjhk() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>��Ԫ��
				Ŀǰծ����<%if(zdxmsbZdrz.getYe()!=null&&!"".equals(zdxmsbZdrz.getYe())){%><%=zdxmsbZdrz.getYe() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>��Ԫ��
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				���浥λ�� <%if(zdxmsbZdrz.getCzrdw()!=null){%><%=zdxmsbZdrz.getCzrdw() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��ˣ� <%if(zdxmsbZdrz.getCzr()!=null){%><%=zdxmsbZdrz.getCzr() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺 <%if(zdxmsbZdrz.getCzsj()!=null){%><%=zdxmsbZdrz.getCzsj().toString().substring(0,4) %>��<%=zdxmsbZdrz.getCzsj().toString().substring(5,7) %>��<%=zdxmsbZdrz.getCzsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				<span id="NoPrint1">
				��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbZdrz.getSmj())&&zdxmsbZdrz.getSmj()!=null){
				  		String[] fileStr = zdxmsbZdrz.getSmj().split(";");
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
				<input type="hidden" name="nrid" id="nrid" value="<%=zdxmsbZdrz.getId()%>">
				<input type="hidden" name="lx" id="lx" value="3">
				<input type="hidden" name="bhry" id="bhry" value="<%=zdxmsbZdrz.getCzrID()%>">
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
				�о����������<%if(zdxmsbZdrz.getShyj()!=null){%><%=zdxmsbZdrz.getShyj() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�����ˣ�<%=zdxmsbZdrz.getShr() %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				����ʱ�䣺<%if(zdxmsbZdrz.getShsj()!=null){%><%=zdxmsbZdrz.getShsj().toString().substring(0,4) %>��<%=zdxmsbZdrz.getShsj().toString().substring(5,7) %>��<%=zdxmsbZdrz.getShsj().toString().substring(8,10) %>��<%} else{%>20&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��<%}%>
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



