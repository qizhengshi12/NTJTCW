<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%> 
<%@page import="com.safety.entity.Permissions"%>    
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
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�ش���Ŀ�걨</title>
	<base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
    	function queryMenuContext(val){
    	<% if(UserPer.getZdxmsb().indexOf("5")!=-1){ %>
    		document.getElementById("cxcompany").value = val;
    	<% } %>
	   		var cxcompany = document.getElementById("cxcompany").value;
	   		var form = document.getElementById("form2");
			form.action = "ZdxmsbServlet?action=getZdxmsbList&flag=1&cxcompany="+cxcompany;
			form.submit();
    	}
		
	   	function queryzdxmsbByBt(){
   			var username = document.getElementById("username").value;
	   		var cxlx = document.getElementById("cxlx").value;
	   		var cxtj = document.getElementById("cxtj").value;
	   		var cxsh = document.getElementById("cxsh").value;
	   		var cxcompany = document.getElementById("cxcompany").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
			var srbt = "";
	   		if(cxlx!=""){
	   			srbt = " where lx  = '"+cxlx+"'";
	   		}
	   		if(cxsh!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and shyj like '%25"+cxsh+"%25'";
	   			}else{
	   				srbt = " where shyj like '%25"+cxsh+"%25'";
	   			}
	   		}
	   		if(cxtj=="1"){
	   			if(srbt!=""){
	   				srbt = srbt +" and tjzt ='"+cxtj+"'";
	   			}else{
	   				srbt = " where tjzt ='"+cxtj+"'";
	   			}
	   		}else{
	   			if(srbt!=""){
	   				srbt = srbt +" and tjzt ='"+cxtj+"' and czrID =  '"+username+"' ";
	   			}else{
	   				srbt = " where tjzt ='"+cxtj+"' and czrID =  '"+username+"' ";
	   			}
	   		}
	   		if(cxcompany!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and czrdw ='"+cxcompany+"'";
	   			}else{
	   				srbt = " where czrdw ='"+cxcompany+"'";
	   			}
	   		}
	   		if(cxssrq1!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and czsj >='"+cxssrq1+"'";
	   			}else{
	   				srbt = " where czsj >='"+cxssrq1+"'";
	   			}
	   		}
	   		if(cxssrq2!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and czsj <='"+cxssrq2+"'";
	   			}else{
	   				srbt = " where czsj <='"+cxssrq2+"'";
	   			}
	   		}
	   		var form = document.getElementById("form2");
			form.action = "ZdxmsbServlet?action=getZdxmsbList&srbt=" + srbt;
			form.submit();
	   	}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("cxlx").value=sel.options[sel.selectedIndex].value;
		}
		function selChange2(){
			var sel = document.getElementById("sel2");
			document.getElementById("cxsh").value=sel.options[sel.selectedIndex].value;
		}
		function selChange3(){
			var sel = document.getElementById("sel3");
			document.getElementById("cxtj").value=sel.options[sel.selectedIndex].value;
		}
	    function insertZdxmsb(){
	   		var form = document.getElementById("form1");
			var sel = document.getElementById("sel4");
			var str = sel.options[sel.selectedIndex].value;
			if(str!=""){
				form.action = "ZdxmsbServlet?action=ZdxmsbEdit&lx="+str;
				form.submit();
			}else{
				alert("��ѡ��Ҫ�����ı�");
				return;
			}
	    }
    	//���ò˵�
		function resetMenu(){
			var form = document.getElementById("form1");
			form.action = "ZdxmsbServlet?action=resetMenu";
			form.submit();
		}
	</script>
  </head>
  
  <body>
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  <form method="post" id="form1" action=""></form>
  <form method="post" id="form2" name="Zdxmsb" target="content">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
	<table width="100%" height="100%">
		<tr>
			<td width="15%" valign="top"  class="leftmenu">
				<div id=treeviewarea>
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:��ͨ�н�ͨ�����;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				     </SCRIPT>
	    		</div>
				<%if("����Ա".equals(roles)){%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="���ò˵�" width="100px" height="60px" title="���ò˵�" style="cursor: pointer;" src="images/small/i15.png"  onclick="resetMenu()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
				<%} %>
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td width="85%" valign="top">	
			  <table width="100%" >
				<tr>
					<td align="left" colspan="2">
						��Ŀ���ͣ�<select id="sel1" onchange="selChange1()" class="STYLE1">
							<option value="">ȫ��</option>
							<option value="1">ע���ʱ����</option>
							<option value="2">�ش����Ͷ��</option>
							<option value="3">�ش����ʣ���ծ��</option>
							<option value="4">��Ȩ���</option>
							<option value="5">ծ������</option>
							<option value="6">�����������ش��ʲ���Ѻ����</option>
						  </select>
						  <input type=hidden name="cxlx" id="cxlx" value=""/>&nbsp; 
						����״̬��<select id="sel2" onchange="selChange2()" class="STYLE1">
							<option value="">ȫ��</option>
							<option value="δ����">δ����</option>
							<option value="ͬ��">ͬ��</option>
							<option value="����">����</option>
						  </select>
						  <input type=hidden name="cxsh" id="cxsh" value=""/>&nbsp;
						�ύ״̬��<select id="sel3" onchange="selChange3()" class="STYLE1">
							<option value="1">���ύ</option>
							<option value="2">δ�ύ</option>
						  </select>
						  <input type=hidden name="cxtj" id="cxtj" value="1"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">
						�������ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Zdxmsb.cxssrq1);" type="button" value="ѡ��">
						��<input name="cxssrq2" id="cxssrq2" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Zdxmsb.cxssrq2);" type="button" value="ѡ��">
						<input type="hidden" name="cxcompany" id="cxcompany" value="<%=company %>" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryzdxmsbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td height="350px" colspan="2">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
				</tr>
				<%if(UserPer.getZdxmsb().indexOf("2")!=-1){%>
				<tr>
					<td width="50%" align="right">
					  	<select id="sel4" class="STYLE1">
							<option value=""></option>
							<option value="1">ע���ʱ����</option>
							<option value="2">�ش����Ͷ��</option>
							<option value="3">�ش����ʣ���ծ��</option>
							<option value="4">��Ȩ���</option>
							<option value="5">ծ������</option>
							<option value="6">�����������ش��ʲ���Ѻ����</option>
						</select>
					</td>
					<td align="left">
						<img alt="�������浥" width="100px" height="60px" title="�������浥" style="cursor: pointer;" src="images/small/i16.png"  onclick="insertZdxmsb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
					</td>
				</tr>
				<%} %>
			  </table>
			</td>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>
