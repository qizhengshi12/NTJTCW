<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Wjglhf"%>  
<%@page import="com.safety.entity.Menu"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  
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
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList wjglhfList = (ArrayList)request.getAttribute("wjglhfList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>接收列表</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryWjglByBt(){
   		var cxwjmc = document.getElementById("cxwjmc").value;
   		var cxhfzt = document.getElementById("cxhfzt").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
		var srbt = "";
   		if(cxwjmc!=""){
   			srbt = " and wjmc  like '%25"+cxwjmc+"%25'";
   		}
   		if(cxhfzt!=""){
   			srbt = srbt +" and hfzt ='"+cxhfzt+"'";
   		}
   		if(cxssrq1!=""){
   			srbt = srbt +" and hfqx >='"+cxssrq1+"'";
   		}
   		if(cxssrq2!=""){
   			srbt = srbt +" and hfqx <='"+cxssrq2+"'";
   		}
   		var form = document.getElementById("form2");
		form.action = "WjglServlet?action=getWjglJSList&srbt=" + srbt;
		form.submit();
   	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("cxhfzt").value=sel.options[sel.selectedIndex].value;
	}
    function showWjglXF(){
   		var form = document.getElementById("form1");
   		form.target="_self";
		form.action = "WjglServlet?action=wjglXFInsert";
		form.submit();
    }
    function getWjglTJList(){
   		var form = document.getElementById("form1");
   		form.target="_blank";
		form.action = "WjglServlet?action=getWjglTJList&srbt=";
		form.submit();
  	}
	  
    
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//转义;
		form.action = "WjglServlet?action=getWjglJSList&srbt="+srbt+"&page_no=" + v;
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
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String cxwjmc = (String)request.getAttribute("cxwjmc"); 
		if(cxwjmc==null)cxwjmc="";
		String cxhfzt = (String)request.getAttribute("cxhfzt"); 
		if(cxhfzt==null)cxhfzt="";
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
	
  <form method="post" id="form1" action="">
  </form>
  <form method="post" id="form2" name="WJGL">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%" >
		<tr>
			<td width="20%">
				<img  alt="未选中" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglList&flag=1'><span style="font-size: 16px">所有文件列表</span></a>
			</td>
			<td width="80%" align="left" rowspan="2">
				文件名称：<input type=text name="cxwjmc" id="cxwjmc" size="6" class="STYLE1" value="<%=cxwjmc %>"/>
				是否回复：<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=cxhfzt %>"><%=cxhfzt %></option>
						<option value="">全部</option>
						<option value="未回复">未回复</option>
						<option value="已回复">已回复</option>
					  </select>
					  <input type=hidden name="cxhfzt" id="cxhfzt" value="<%=cxhfzt %>"/>&nbsp; 
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="未选中" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglXFList&flag=1'><span style="font-size: 16px">已发送文件列表</span></a>
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="选中" align="top" src="images/small/favorites.png"><a href='WjglServlet?action=getWjglJSList&flag=1'><span style="font-size: 16px;border:1px; background:#EEE0E5;">需回复文件列表</span></a>
			</td>
			<td align="left" rowspan="2">
				回复期限：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq1);" type="button" value="选择">
				到
				<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq2);" type="button" value="选择">
				<%if(UserPer.getWjgl().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryWjglByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="未选中" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglCGList&flag=1'><span style="font-size: 16px">草稿箱文件列表</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
	</table>
</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				序号
			</td>
			<td align="center" width="25%" class="STYLE1">
				文件名
			</td>
			<td align="center" width="10%" class="STYLE1">
				回复期限
			</td>
			<td align="center" width="10%" class="STYLE1">
				是否超时
			</td>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< wjglhfList.size();i++){
				Wjglhf wjglhf = (Wjglhf)wjglhfList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(wjglhfList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglhf.getWjmc()!=null&&!"".equals(wjglhf.getWjmc())){%>
				<a href="WjglServlet?action=showWjglXF&id=<%=wjglhf.getWjID()%>&flag=0" target="_blank"><%=wjglhf.getWjmc()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglhf.getHfqx()!=null&&!"".equals(wjglhf.getHfqx())){%>
				<%=wjglhf.getHfqx()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglhf.getSfcs()!=null&&!"".equals(wjglhf.getSfcs())){%>
				<%if("超时".equals(wjglhf.getSfcs())) {%><img align="middle"  alt="超时" title="超时" src="images/small/wait-red.png"><span style="color: red;">&nbsp;超时&nbsp;</span><%}else {%><img align="middle"  alt="未超时" title="未超时" src="images/small/wait-green.png"><span>未超时</span><%}%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglhf.getHfzt()!=null&&"未回复".equals(wjglhf.getHfzt())){%>
				<a href="WjglServlet?action=showWjglXF&id=<%=wjglhf.getWjID()%>&flag=2"><img " src="images/small/flag_10.png"><%=wjglhf.getHfzt()%></a>
				<%}else{ %>
				<a href="WjglServlet?action=showWjglXF&id=<%=wjglhf.getWjID()%>&flag=3" target="_blank"><img  src="images/small/flag_06.png"><%=wjglhf.getHfzt()%></a>
				<%} %>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left">
			<!-- 显示分页工具栏开始 -->
			<%=pt.printPage() %>
			<!--显示分页工具栏结束-->
			</td>
		</tr>
	</table>

	<table width="100%" >
		<tr height="35px" valign="bottom">
			<td align="center">
			<%if(UserPer.getWjgl().indexOf("2")!=-1){%>
				<img alt="下发文件" width="100px" height="60px" title="下发文件" style="cursor: pointer;" src="images/small/i9.png"  onclick="showWjglXF()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
			<td align="center">
			<%if("管理员".equals(roles)||(("财务负责人".equals(roles)||"统计负责人".equals(roles))&&"局机关".equals(company))){%>
				<img alt="统计" width="100px" height="60px" title="统计" style="cursor: pointer;" src="images/small/i10.png"  onclick="getWjglTJList()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">		
			<%} %>					
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
