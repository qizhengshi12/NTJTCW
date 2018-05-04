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
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
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

    	//添加根菜单
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
    			alert("请输入要新增的根菜单名称");
    			document.getElementById("nodeName1").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=insertMenu&ParentID=0&name=" + name;
			form.submit();
		} 
		//添加子菜单
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
    			alert("请输入要新增的子菜单名称");
    			document.getElementById("nodeName2").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=insertMenu&ParentID=" + id+"&name=" + name;
			form.submit();
		}   	
    	//编辑菜单项
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
    			alert("不能修改总节点名称");
    			return;
			}
			if(name==""){
    			alert("请输入要修改的菜单名称");
    			document.getElementById("nodeName4").value="";
    			return;
    		}
			var form = document.getElementById("form2");
			form.action = "FgServlet?action=updateMenu&id=" + id+"&name=" + name;
			form.submit();
		}
		 	
    	//删除菜单项
    	function deleteDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			var cNode = tree.currentNode.text;
        	if (!confirm("确认要删除："+cNode+"？")) {
				return;
    		}
    		var form = document.getElementById("form2");
			form.action = "FgServlet?action=deleteMenu&id=" + id;
			form.submit();
		}	
    	//新增文件
    	function insertDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			if(id==0){
				alert("不能在总结点下新增文件！");
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
				    	tree.nodes['-1_0'] = 'text:法规;';
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
							类型：<select id="sel1" onchange="selChange1()" class="STYLE1">
								<option value="">全部</option>
								<option value="法律">法律</option>
								<option value="法规">法规</option>
								<option value="规章">规章</option>
								<option value="规范性文件">规范性文件</option>
							</select>
							<input type="hidden" id="cxlx" value=""/>&nbsp;
						</td>
						<td align="left" colspan="3"> 
							标题：<input type="text" id="cxbt" size="15" class="STYLE1" value=""/>
							文号：<input type="text" id="cxwh" size="10" class="STYLE1" value=""/>
							发布单位：<input type=text id="cxfbdw" size="6" class="STYLE1" value=""/>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
						</td>
						<td align="left" colspan="3">   
							实施日期：<input name="cxssrq1" id="cxssrq1" type="text" value="" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.Fg.cxssrq1);" type="button" value="选择">
							到
							<input name="cxssrq2" id="cxssrq2" type="text" value="" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.Fg.cxssrq2);" type="button" value="选择">
							<%if(UserPer.getContentcjfg().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
							<img alt="添加根菜单" width="100px" height="60px" title="添加根菜单" style="cursor: pointer;" src="images/small/i1.png"  onclick="showFatherDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("2")!=-1){%>
							<img alt="添加子菜单" width="100px" height="60px" title="添加子菜单" style="cursor: pointer;" src="images/small/i2.png"  onclick="showSonDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("3")!=-1){%>
							<img alt="编辑菜单" width="100px" height="60px" title="编辑菜单" style="cursor: pointer;" src="images/small/i3.png"  onclick="showEditDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodecjfg().indexOf("4")!=-1){%>
							<img alt="删除菜单" width="100px" height="60px" title="删除菜单" style="cursor: pointer;" src="images/small/i4.png"  onclick="deleteDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getContentcjfg().indexOf("2")!=-1){%>
							<img alt="新增文件" width="100px" height="60px" title="新增文件" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
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
					<h1 style="font-family:verdana">新增根菜单</h1>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" colspan="2">
					根菜单名称：<input type="text" id="nodeName1" value=""/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="SonDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">新增子菜单</h1>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center" colspan="2">
					子菜单名称：<input type="text" id="nodeName2" value=""/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertSonDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseSonDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="EditDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">编辑菜单</h1>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					当前菜单名称：<input type="text" id="nodeName3" value="" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					修改菜单名称：<input type="text" id="nodeName4" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertEditDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseEditDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>