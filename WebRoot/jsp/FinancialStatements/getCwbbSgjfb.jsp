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
	
%>
  	<%
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String cxcompany = (String)request.getAttribute("cxcompany"); 
		if(cxcompany==null)cxcompany="";
		Calendar cal=Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH)+1;
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>三公经费使用</title>
	<base href="<%=basePath%>">
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
   <script src="calendar.js"></script>
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
    	function queryMenuContext(val){
    	<% if(UserPer.getSgjfsy().indexOf("5")!=-1){ %>
    		document.getElementById("cxcompany").value = val;
    	<% } %>
			var cxcompany = document.getElementById("cxcompany").value;
        	var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=getSgjfb&flag=1&cxcompany="+cxcompany;
			form.submit();
    	}
    	//重置菜单
		function resetMenu(){
			var form = document.getElementById("form2");
			form.action = "BbsbCwbbServlet?action=resetMenuSgjf";
			form.submit();
		}
		
	   	function queryCwbbSgjfbByBt(){
   			var username = document.getElementById("username").value;
	   		var cxtj = document.getElementById("cxtj").value;
	   		var cxyear = document.getElementById("cxyear").value;
	   		var cxmonth = document.getElementById("cxmonth").value;
	   		var cxcompany = document.getElementById("cxcompany").value;
			var srbt = "";
	   		if(cxyear!=""){
	   			srbt = " where year  = "+cxyear;
	   		}
	   		if(cxmonth!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and month = "+cxmonth;
	   			}else{
	   				srbt = " where month = "+cxmonth;
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
			form.action = "BbsbCwbbServlet?action=getSgjfb&srbt=" + srbt;
			form.submit();
	   	}
	    function insertCwbbSgjfb(){
	   		var form = document.getElementById("form2");
			form.action = "BbsbCwbbServlet?action=sgjfbEdit";
			form.submit();
	    }
		function selChange3(){
			var sel = document.getElementById("sel3");
			document.getElementById("cxtj").value=sel.options[sel.selectedIndex].value;
		}
			
		function FindCompany(){
			var xmlhttp;    
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	var jsons = xmlhttp.responseText;
			    	document.getElementById("suggest1").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=FindCompany&parent_id=0";
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function chooseCompany(val){
			var str = val.split("&#&");
	    	document.getElementById("srcompany").value = str[1];
			document.getElementById("suggest1").innerHTML="";
	    }
	</script>
  </head>
  
  <body>
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  <form method="post" id="form1" action="" target="content"></form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="cxcompany" value="<%=company %>" />
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
					<td align="center">
						<select name="cxyear" id="cxyear">
						
							<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
							<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
							<%} %>
						</select>
						&nbsp;1月到&nbsp;
						<select name="cxmonth" id="cxmonth">
							<% for(int j=1;j<13;j++){ %>
							<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
							<%} %>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						提交状态：
						<select id="sel3" onchange="selChange3()" class="STYLE1">
							<option value="1">已提交</option>
							<option value="2">未提交</option>
							<option value="3">驳回</option>
						</select>
						<input type=hidden name="cxtj" id="cxtj" value="1"/>&nbsp;
						<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryCwbbSgjfbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td height="350px">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
				</tr>
			</table>
			<form method="post" id="form2" action="">
			<table width="100%" >
				<tr>
					<td width="50%" align="right">
					<%if(UserPer.getSgjfsy().indexOf("5")!=-1){%>
					<input name="srcompany" id="srcompany" type="text" onclick="FindCompany()" value="<%=company %>"readonly="readonly" class="STYLE1"/><span id="suggest1"></span>
					<%} %>
					</td>
					<td width="50%" align="left">
						<%if(UserPer.getSgjfsy().indexOf("2")!=-1){%>
						<img alt="新增表格" width="100px" height="60px" title="新增表格" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertCwbbSgjfb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
						<%} %>
					</td>
				</tr>
			 </table>
			 </form>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
