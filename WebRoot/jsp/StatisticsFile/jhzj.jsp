<%@page contentType="text/html;charset=GBK"%>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script language="javascript">
    	function queryMenuContext(val){
	   		var username = document.getElementById("username").value;
    	<% if(UserPer.getJhzj().indexOf("5")!=-1){ %>
    		document.getElementById("cxcompany").value = val;
    	<% } %>
	   		var cxcompany = document.getElementById("cxcompany").value;
			var srbt = " where czrdw ='"+cxcompany+"' and (tjzt='1' or (tjzt='2' and  czrID = '"+username+"')) ";
	   		var form = document.getElementById("form1");
			form.action = "JhzjServlet?action=query&srbt=" + srbt;
			form.submit();
    	} 
    	function queryContextByBt(){
	   		var username = document.getElementById("username").value;
		   	var cxtj = document.getElementById("cxtj").value;
	   		var cxjhmc = document.getElementById("cxjhmc").value;
	   		var cxjhlx1 = document.getElementById("cxjhlx1").value;
	   		var cxjhlx2 = document.getElementById("cxjhlx2").value;
	   		var cxjhnr = document.getElementById("cxjhnr").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
	   		var cxcompany = document.getElementById("cxcompany").value;
			var srbt = "";
	   		if(cxjhmc!=""){
	   			srbt = " where jhmc  like '%25"+cxjhmc+"%25'";
	   		}
	   		if(cxjhlx1!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jhlx1 = '"+cxjhlx1+"'";
	   			}else{
	   				srbt = " where jhlx1 = '"+cxjhlx1+"'";
	   			}
	   		}
	   		if(cxjhlx2!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jhlx2 = '"+cxjhlx2+"'";
	   			}else{
	   				srbt = " where jhlx2 = '"+cxjhlx2+"'";
	   			}
	   		}
	   		if(cxjhnr!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jhnr like '%25"+cxjhnr+"%25'";
	   			}else{
	   				srbt = " where jhnr like '%25"+cxjhnr+"%25'";
	   			}
	   		}
	   		if(cxssrq1!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jhsj1 >='"+cxssrq1+"'";
	   			}else{
	   				srbt = " where jhsj1 >='"+cxssrq1+"'";
	   			}
	   		}
	   		if(cxssrq2!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jhsj2 <='"+cxssrq2+"'";
	   			}else{
	   				srbt = " where jhsj2 <='"+cxssrq2+"'";
	   			}
	   		}
	   		if(cxcompany!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and czrdw ='"+cxcompany+"'";
	   			}else{
	   				srbt = " where czrdw ='"+cxcompany+"'";
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
	   				srbt = srbt +" and tjzt ='"+cxtj+"' and czrID = '"+username+"' ";
	   			}else{
	   				srbt = " where tjzt ='"+cxtj+"' and czrID = '"+username+"' ";
	   			}
	   		}
	   		var form = document.getElementById("form1");
			form.action = "JhzjServlet?action=query&srbt=" + srbt;
			form.submit();
	   	}
    	//重置菜单
		function resetMenu(){
			var form = document.getElementById("form2");
			form.action = "JhzjServlet?action=resetMenu";
			form.submit();
		}
		 	
    	//新增文件
    	function insertDode(dw){
    		var form = document.getElementById("form2");
			form.action = "JhzjServlet?action=jhzjEdit";
			form.submit();
		}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("cxjhlx1").value=sel.options[sel.selectedIndex].value;
		}
		function selChange2(){
			var sel = document.getElementById("sel2");
			document.getElementById("cxjhlx2").value=sel.options[sel.selectedIndex].value;
		}
		function selChange3(){
			var sel = document.getElementById("sel3");
			document.getElementById("cxtj").value=sel.options[sel.selectedIndex].value;
		}
    </script>
  </head>
  <body>
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  <form method="post" id="form2" action=""></form>
  <form method="post" id="form1" name="JHZJ" action="" target="content">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
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
				    	<% String MenuId1 = (String)request.getAttribute("MenuId");  %>
				     </SCRIPT>
	    			</div>
					<%if("管理员".equals(roles)){%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="重置菜单" width="100px" height="60px" title="重置菜单" style="cursor: pointer;" src="images/small/i15.png"  onclick="resetMenu()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
					<%} %>
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td width="85%" valign="top">	
				<table width="100%">
					<tr>
						<td width="100%" align="left">
							文件名称：<input type=text name="cxjhmc" id="cxjhmc" size="6" class="STYLE1" value=""/>
							类型1：<select id="sel1" onchange="selChange1()" class="STYLE1">
								<option value="">全部</option>
								<option value="计划">计划</option>
								<option value="总结">总结</option>
							  </select>
							  <input type=hidden name="cxjhlx1" id="cxjhlx1" value=""/>&nbsp; 
							类型2：<select id="sel2" onchange="selChange2()" class="STYLE1">
								<option value="">全部</option>
								<option value="财务">财务</option>
								<option value="审计">审计</option>
							  </select>
							  <input type=hidden name="cxjhlx2" id="cxjhlx2" value=""/>&nbsp; 
							内容：<input type=text name="cxjhnr" id="cxjhnr" size="36" class="STYLE1" value=""/>
						</td>
					</tr>
					<tr>
						<td width="100%" align="left">
							提交日期：<input name="cxssrq1" id="cxssrq1" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.JHZJ.cxssrq1);" type="button" value="选择">
					到<input name="cxssrq2" id="cxssrq2" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.JHZJ.cxssrq2);" type="button" value="选择">
							<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=company %>"/>
							提交状态：
							<select id="sel3" onchange="selChange3()" class="STYLE1">
								<option value="1">已提交</option>
								<option value="2">未提交</option>
							</select>
							<input type=hidden name="cxtj" id="cxtj" value="1"/>&nbsp;
							<%if(UserPer.getJhzj().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
							<%} %>
						</td>
					</tr>
					<tr >
						<td width="100%">
							<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
						</td>
					</tr>
					<tr height="380px">
					<td height="380px">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
					</tr>
					<tr height="35px">
						<td align="center">
							<%if(UserPer.getJhzj().indexOf("2")!=-1){%>
							<img alt="新增文件" width="100px" height="60px" title="新增文件" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertDode('<%=company %>')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
  </form>
	<%} %>
  </body>
</html>