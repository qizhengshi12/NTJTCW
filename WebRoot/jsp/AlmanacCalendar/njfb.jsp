<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
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
    	//新增文件
    	function insertDode(){
			var id = tree.currentNode.sourceIndex.split("_")[1];
			if(id==0){
				alert("不能在总结点下新增文件！");
				return;
			}
			var fid = tree.currentNode.sourceIndex.split("_")[0];
			if(fid==0){
				alert("请选择子项目");
				return;
			}
			var cNodeHint = tree.currentNode.hint;
			var re = /^\d+$/;   //判断字符串是否为正整数
			if (!re.test(cNodeHint)){
				alert("该节点未指定列表数，请与管理员联系")
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
				    	tree.nodes['-1_0'] = 'text:南通交通统计年鉴;';
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
							时间：
							<select id="cxyear">
							<% for(int i=nowYear-10;i<nowYear+1;i++){ %>
							<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
							<%} %>
							</select>
							<%if(UserPer.getNjfb().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
							<img alt="新增文件" width="100px" height="60px" title="新增文件" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
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
			<tr>
				<td align="center" colspan="2">
					子菜单名称：<input type="text" id="nodeName2" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;&nbsp;后缀名称：<input type="text" id="nodeName3" value=""/>
				</td>
			</tr>
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
					当前菜单名称：<input type="text" id="nodeName4" value="" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					修改菜单名称：<input type="text" id="nodeName5" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					&nbsp;&nbsp;&nbsp;&nbsp;后缀名称：<input type="text" id="nodeName6" value=""/>
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