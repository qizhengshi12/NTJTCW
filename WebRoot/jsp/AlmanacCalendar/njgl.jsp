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
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10_1.js"></SCRIPT>
    <title></title>
    <script language="javascript">
    	//���ʱ�ڵ�ʱ������ɾ���÷�����
    	function queryMenuContext(MenuId){
			
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
			form.action = "LnnjServlet?action=insertMenu&ParentID=0&name=" + name;
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
			var bbls = document.getElementById("nodeName3").value;
			if(bbls!=""){
				var re = /^\d+$/;   //�ж��ַ����Ƿ�Ϊ������
				if (!re.test(bbls)){
					alert("��������������������");
					return;
				}
				if (bbls>50){
					alert("��������̫���������Ա��ϵ��������");
					return;
				}
			}
			var form = document.getElementById("form2");
			form.action = "LnnjServlet?action=insertMenu&ParentID=" + id+"&name=" + name+"&bbls="+bbls;
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
			var cNode = tree.currentNode;
			document.getElementById("nodeName4").value = cNode.text;
			document.getElementById("nodeName6").value = cNode.hint;
		}
		function CloseEditDode(){
			var popUp = document.getElementById("EditDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertEditDode(){
			var cNode = tree.currentNode.sourceIndex;
			var id = cNode.split("_")[1];
			var name = document.getElementById("nodeName5").value;
			name = name.replace(/\ /g,"");
			if(id == 0){
    			alert("�����޸��ܽڵ�����");
    			return;
			}
			if(name==""){
    			alert("������Ҫ�޸ĵĲ˵�����");
    			document.getElementById("nodeName5").value="";
    			return;
    		}
			var bbls = document.getElementById("nodeName6").value;
			var form = document.getElementById("form2");
			form.action = "LnnjServlet?action=updateMenu&id=" + id+"&name=" + name+"&bbls="+bbls;
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
			form.action = "LnnjServlet?action=deleteMenu&id=" + id;
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
  	<%
		String MenuId = (String)request.getAttribute("MenuId"); 
		if(MenuId==null)MenuId="";
	%>
  <form method="post" id="form1" action="" target="content"></form>
  <form method="post" id="form2" action=""></form>
	<table width="100%" height="100%">
		<tr>
			<td width="15%" valign="top"  class="leftmenu">
				<div id=treeviewarea >
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:��ͨ��ͨͳ�����;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				    	<% String MenuId1 = (String)request.getAttribute("MenuId");  %>
				     </SCRIPT>
	    		</div>
			</td>
			<td width="1px" bgcolor="#5CACEE">
			</td>
			<td width="85%" valign="top">	
				<table width="100%">
					<tr height="35px">
						<td align="center" colspan="4">
							ע�����ڵ�����Ҫ���������¼�����������ýڵ�ʱ������ڵ������������������
						</td>
					</tr>
					<tr >
						<td width="100%" colspan="4">
							<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
						</td>
					</tr>
					<tr height="35px">
						<td align="center">
							<%if(UserPer.getNjgl().indexOf("2")!=-1){%>
							<img alt="��Ӹ��˵�" width="100px" height="60px" title="��Ӹ��˵�" style="cursor: pointer;" src="images/small/i1.png"  onclick="showFatherDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNjgl().indexOf("2")!=-1){%>
							<img alt="����Ӳ˵�" width="100px" height="60px" title="����Ӳ˵�" style="cursor: pointer;" src="images/small/i2.png"  onclick="showSonDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNjgl().indexOf("3")!=-1){%>
							<img alt="�༭�˵�" width="100px" height="60px" title="�༭�˵�" style="cursor: pointer;" src="images/small/i3.png"  onclick="showEditDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNjgl().indexOf("4")!=-1){%>
							<img alt="ɾ���˵�" width="100px" height="60px" title="ɾ���˵�" style="cursor: pointer;" src="images/small/i4.png"  onclick="deleteDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
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
			<tr>
				<td align="center" colspan="2">
					�Ӳ˵����ƣ�<input type="text" id="nodeName2" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;&nbsp;����������<input type="text" id="nodeName3" value="" />
				</td>
			</tr>
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
					��ǰ�˵����ƣ�<input type="text" id="nodeName4" value="" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					�޸Ĳ˵����ƣ�<input type="text" id="nodeName5" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;&nbsp;&nbsp;&nbsp;����������<input type="text" id="nodeName6" value="" disabled="disabled"/>
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