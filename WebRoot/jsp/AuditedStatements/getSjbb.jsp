<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%> 
<%@page import="com.safety.entity.Permissions"%>     
<%@page import="com.safety.entity.Bbdzmb"%>      
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
    ArrayList BbdzmbList = (ArrayList)request.getAttribute("BbdzmbList");
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>审计报表</title>
	<base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
    	function queryMenuContext(val){
    	<% if(UserPer.getSjbb().indexOf("5")!=-1){ %>
    		document.getElementById("menuname").value = val;
    	<% } %>
			var menuname = document.getElementById("menuname").value;
			var sel = document.getElementById("sel1");
			var str = sel.options[sel.selectedIndex].value;
			if(str!=""){
				query(menuname,str);
			}
    	}
		function selChange1(){
			var sel = document.getElementById("sel1");
			var str = sel.options[sel.selectedIndex].value;
			var menuname = document.getElementById("menuname").value;
			query(menuname,str);
		}
		function query(menuname,str){
			var company = document.getElementById("menuname").value;
			var roles = document.getElementById("roles").value;
        	var form = document.getElementById("form1");
			if(str==1){
				if(company=="局机关"&&roles=="审计负责人"){
					form.action = "BbsbSjbbServlet?action=getSjbbAll&flag=2&menuname="+menuname;
				}else{
					form.action = "BbsbSjbbServlet?action=getSjbbAll&flag=1&menuname="+menuname;
				}
				form.submit();
			}else if(str==2){
				form.action = "BbsbSjbbServlet?action=getJtyssjdytj&flag=1&menuname="+menuname;
				form.submit();
			}else if(str==3){
				form.action = "BbsbSjbbServlet?action=getLssfzczcb&flag=1&menuname="+menuname;
				form.submit();
			}else if(str==4){
				form.action = "BbsbSjbbServlet?action=getLssfzczcbHzb&flag=1";
				form.submit();
			}else if(str.indexOf("mb")!=-1){
				var zbid = str.replace("mb","");
				form.action = "BbdzServlet?action=getBbdz&flag=1&lx=sjbb&zbid="+zbid+"&menuname="+menuname;
				form.submit();
			}
		
		}
    	//重置菜单
		function resetMenu(){
			var form = document.getElementById("form2");
			form.action = "BbsbSjbbServlet?action=resetMenu";
			form.submit();
		}
	</script>
  </head>
  
  <body>
  <form method="post" id="form1" action="" target="content"></form>
  <form method="post" id="form2" action=""></form>
  <input type="hidden" id="roles" value="<%=roles %>">
  <input type="hidden" id="menuname" value="<%=company %>">
	<table width="100%" height="100%">
		<tr>
			<td width="15%" valign="top"  class="leftmenu">
				<div id=treeviewarea>
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:南通市交通运输局;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				     </SCRIPT>
	    		</div>
				<%if("管理员".equals(roles)){%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="重置菜单" width="100px" height="60px" title="重置菜单" style="cursor: pointer;" src="images/small/i15.png"  onclick="resetMenu()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
				<%} %>
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td width="85%" valign="top">	
			  <table width="100%" height="100%">
				<tr>
					<td height="35px">
						审计报表：
					  	<select id="sel1" onchange="selChange1()" class="STYLE1">
							<option value=""></option>
							<option value="1">交通审计统计表</option>
							<option value="2">交通运输部门审计对象统计表</option>
							<option value="3">市级机关（含下属事业单位）落实收费政策季度自查表</option>
    						<% if(("审计负责人".equals(roles)||"财务负责人".equals(roles))&&"局机关".equals(company)){ %>
							<option value="4">市级机关（含下属事业单位）落实收费政策季度自查表汇总表</option>
							<%} %>
							<%
					         	/*遍历每个的信息进行显示*/
								for(int i=0;i< BbdzmbList.size();i++){
									Bbdzmb bbdzmb = (Bbdzmb)BbdzmbList.get(i);
							%>
							<option value="mb<%=bbdzmb.getId() %>"><%=bbdzmb.getBt() %></option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td height= "1px">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td>
					<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
				</tr>
			  </table>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
