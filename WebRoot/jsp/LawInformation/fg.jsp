<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentFg"%>  
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
   <base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script language="javascript">
    	function queryMenuContext(MenuId){
    		//alert(id);
    		var form = document.getElementById("form1");
			form.action = "FgServlet?action=query&MenuId=" + MenuId;
			form.submit();
    	} 
    	function queryContextByBt(){
    		var cxbt = document.getElementById("cxbt").value;
    		var cxlx = document.getElementById("cxlx").value;
    		var cxwh = document.getElementById("cxwh").value;
    		var cxssrq1 = document.getElementById("cxssrq1").value;
    		var cxssrq2 = document.getElementById("cxssrq2").value;1
    		var cxfbdw = document.getElementById("cxfbdw").value;
			var srbt = "";
    		if(cxbt!=""){
    			srbt = " where bt like '%25"+cxbt+"%25'";
    		}
    		if(cxwh!=""){
    			if(srbt!=""){
    				srbt = srbt +" and wh like '%25"+cxwh+"%25'";
    			}else{
    				srbt = " where wh like '%25"+cxwh+"%25'";
    			}
    		}
    		if(cxssrq1!=""){
    			if(srbt!=""){
    				srbt = srbt +" and ssrq >='"+cxssrq1+"'";
    			}else{
    				srbt = " where ssrq >='"+cxssrq1+"'";
    			}
    		}
    		if(cxssrq2!=""){
    			if(srbt!=""){
    				srbt = srbt +" and ssrq <='"+cxssrq2+"'";
    			}else{
    				srbt = " where ssrq <='"+cxssrq2+"'";
    			}
    		}
    		if(cxfbdw!=""){
    			if(srbt!=""){
    				srbt = srbt +" and fbdw like '%25"+cxfbdw+"%25'";
    			}else{
    				srbt = " where fbdw like '%25"+cxfbdw+"%25'";
    			}
    		}
    		if(cxlx!=""){
    			if(srbt!=""){
    				srbt = srbt +" and lx = '"+cxlx+"'";
    			}else{
    				srbt = " where lx = '"+cxlx+"'";
    			}
    		}
    		var form = document.getElementById("form1");
			form.action = "FgServlet?action=query&srbt=" + srbt+"&MenuId=";
			form.submit();
    	} 

    	//��Ӹ��˵�
		function showFatherDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("FatherDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
		}
		function CloseFatherDode(){
			var popUp = document.getElementById("FatherDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertFatherDode(){
			var name = document.getElementById("nodeName1").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("������Ҫ�����ĸ��˵�����");
    			document.getElementById("nodeName1").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=insertMenu&ParentID=0&name=" + name;
			form.submit();
		} 
		//����Ӳ˵�
		function showSonDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("SonDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
		}
		function CloseSonDode(){
			var popUp = document.getElementById("SonDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertSonDode(){
			var cNode = tree.currentNode.sourceIndex;
			var id = cNode.split("_")[1];
			var name = document.getElementById("nodeName2").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("������Ҫ�������Ӳ˵�����");
    			document.getElementById("nodeName2").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=insertMenu&ParentID=" + id+"&name=" + name;
			form.submit();
		}   	
    	//�༭�˵���
    	function showEditDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("EditDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
			var cNode = tree.currentNode.text;
			document.getElementById("nodeName3").value = cNode;
		}
		function CloseEditDode(){
			var popUp = document.getElementById("EditDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertEditDode(){
			var cNode = tree.currentNode.sourceIndex;
			var id = cNode.split("_")[1];
			var name = document.getElementById("nodeName4").value;
			name = name.replace(/\ /g,"");
			if(id == 0){
    			alert("�����޸��ܽڵ�����");
    			return;
			}
			if(name==""){
    			alert("������Ҫ�޸ĵĲ˵�����");
    			document.getElementById("nodeName4").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=updateMenu&id=" + id+"&name=" + name;
			form.submit();
		}
		 	
    	//ɾ���˵���
    	function deleteDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			var cNode = tree.currentNode.text;
        	if (!confirm("ȷ��Ҫɾ����"+cNode+"��")) {
				return;
    		}
    		var form = document.getElementById("form2");
			form.action = "FgServlet?action=deleteMenu&id=" + id;
			form.submit();
		}	
    	//�����ļ�
    	function insertDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			if(id==0){
				alert("�������ܽ���������ļ���");
				return;
			}
    		var form = document.getElementById("form2");
			form.action = "jsp/LawInformation/fgInsert.jsp?fatherid="+id;
			form.submit();
		}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("cxlx").value=sel.options[sel.selectedIndex].value;
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
		
		String MenuId = (String)request.getAttribute("MenuId"); 
		if(MenuId==null)MenuId="";
	%>
  <form method="post" id="form2" action=""></form>
  <form method="post" name="Fg" id="form1" action="" target="content">
	<table width="100%" height="100%">
		<tr>
			<td width="15%" valign="top" class="leftmenu">
				<div id=treeviewarea>
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:����;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				    	<% String MenuId1 = (String)request.getAttribute("MenuId");  %>
				     </SCRIPT>
	    			</div>
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td width="85%" valign="top">	
				<table width="100%">
					<tr>
						<td align="left" colspan="2">
							���ͣ�<select id="sel1" onchange="selChange1()" class="STYLE1">
								<option value="">ȫ��</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="����">����</option>
								<option value="�淶���ļ�">�淶���ļ�</option>
							</select>
							<input type="hidden" id="cxlx" value=""/>&nbsp;
						</td>
						<td align="left" colspan="3"> 
							���⣺<input type="text" id="cxbt" size="15" class="STYLE1" value=""/>
							�ĺţ�<input type="text" id="cxwh" size="10" class="STYLE1" value=""/>
							������λ��<input type=text id="cxfbdw" size="6" class="STYLE1" value=""/>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
						</td>
						<td align="left" colspan="3">   
							ʵʩ���ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.Fg.cxssrq1);" type="button" value="ѡ��">
							��
							<input name="cxssrq2" id="cxssrq2" type="text" value="" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.Fg.cxssrq2);" type="button" value="ѡ��">
							<%if(UserPer.getContentcjfg().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
							<%} %>
						</td>
					</tr>
				</table>	
				<table width="100%">
					<tr >
						<td width="100%" colspan="5">
							<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
						</td>
					</tr>
					<tr height="380px">
					<td height="380px" colspan="5">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
					</tr>
					<tr height="35px">
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("2")!=-1){%>
							<img alt="��Ӹ��˵�" width="100px" height="60px" title="��Ӹ��˵�" style="cursor: pointer;" src="images/small/i1.png"  onclick="showFatherDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("2")!=-1){%>
							<img alt="����Ӳ˵�" width="100px" height="60px" title="����Ӳ˵�" style="cursor: pointer;" src="images/small/i2.png"  onclick="showSonDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("3")!=-1){%>
							<img alt="�༭�˵�" width="100px" height="60px" title="�༭�˵�" style="cursor: pointer;" src="images/small/i3.png"  onclick="showEditDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("4")!=-1){%>
							<img alt="ɾ���˵�" width="100px" height="60px" title="ɾ���˵�" style="cursor: pointer;" src="images/small/i4.png"  onclick="deleteDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getContentcjfg().indexOf("2")!=-1){%>
							<img alt="�����ļ�" width="100px" height="60px" title="�����ļ�" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
  </form>
	<div id="FatherDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">�������˵�</h1>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" colspan="2">
					���˵����ƣ�<input type="text" id="nodeName1" value=""/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="ȷ��" width="45px" height="45px" title="ȷ��" style="cursor: pointer;"  src="images/small/4.png" onclick="insertFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="SonDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">�����Ӳ˵�</h1>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" colspan="2">
					�Ӳ˵����ƣ�<input type="text" id="nodeName2" value=""/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="ȷ��" width="45px" height="45px" title="ȷ��" style="cursor: pointer;"  src="images/small/4.png" onclick="insertSonDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseSonDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="EditDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">�༭�˵�</h1>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					��ǰ�˵����ƣ�<input type="text" id="nodeName3" value="" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					�޸Ĳ˵����ƣ�<input type="text" id="nodeName4" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="ȷ��" width="45px" height="45px" title="ȷ��" style="cursor: pointer;"  src="images/small/4.png" onclick="insertEditDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseEditDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>