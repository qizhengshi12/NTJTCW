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
    	function queryMenuContext(MenuId){
			//var cNode = tree.currentNode;
    		var form = document.getElementById("form1");
			form.action = "LnnjServlet?action=query&MenuId=" + MenuId;
			form.submit();
    	} 
    	function queryContextByBt(){
    		var cxyear = document.getElementById("cxyear").value;
			var srbt = "";
    		if(cxyear!=""){
    			srbt = " where year = "+cxyear+"";
    		}
    		var form = document.getElementById("form1");
			form.action = "LnnjServlet?action=query&srbt=" + srbt;
			form.submit();
    	} 
    	//�����ļ�
    	function insertDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			if(id==0){
				alert("�������ܽ���������ļ���");
				return;
			}
			var fid = tree.currentNode.sourceIndex.split("_")[0];
			if(fid==0){
				alert("��ѡ������Ŀ");
				return;
			}
			var cNodeHint = tree.currentNode.hint;
			var re = /^\d+$/;   //�ж��ַ����Ƿ�Ϊ������
			if (!re.test(cNodeHint)){
				alert("�ýڵ�δָ���б������������Ա��ϵ")
				return;
			}
    		var form = document.getElementById("form2");
			form.action = "LnnjServlet?action=lnnjEdit&fatherid="+id+"&bbls="+cNodeHint;
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
						<td align="center" colspan="5">
							ʱ�䣺
							<select id="cxyear">
							<% for(int i=nowYear-10;i<nowYear+1;i++){ %>
							<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
							<%} %>
							</select>
							<%if(UserPer.getNjfb().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
							<%} %>
						</td>
					</tr>
					<tr >
						<td width="100%" colspan="5">
							<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
						</td>
					</tr>
					<tr height="320px">
					<td height="320px" colspan="5">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto;" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
					</tr>
					<tr height="35px">
						<td align="center">
							<%if(UserPer.getNjfb().indexOf("2")!=-1){%>
							<img alt="�����ļ�" width="100px" height="60px" title="�����ļ�" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
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
					&nbsp;&nbsp;��׺���ƣ�<input type="text" id="nodeName3" value=""/>
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
					&nbsp;&nbsp;&nbsp;&nbsp;��׺���ƣ�<input type="text" id="nodeName6" value=""/>
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