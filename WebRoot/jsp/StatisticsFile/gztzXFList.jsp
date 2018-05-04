<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Gztz"%>  
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
    ArrayList GztzList = (ArrayList)request.getAttribute("GztzList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>工作通知</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryGztzByBt(){
   		var cxmc = document.getElementById("cxmc").value;
   		var cxdd = document.getElementById("cxdd").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
		var srbt = "";
   		if(cxmc!=""){
   			srbt = " where tzmc  like '%25"+cxmc+"%25'";
   		}
   		if(cxdd!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzdd like '%25"+cxdd+"%25'";
   			}else{
   				srbt = " where tzdd like '%25"+cxdd+"%25'";
   			}
   		}
   		if(cxssrq1!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzsj >='"+cxssrq1+"'";
   			}else{
   				srbt = " where tzsj >='"+cxssrq1+"'";
   			}
   		}
   		if(cxssrq2!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzsj <='"+cxssrq2+"'";
   			}else{
   				srbt = " where tzsj <='"+cxssrq2+"'";
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "GztzServlet?action=getGztzXFList&srbt=" + srbt;
		form.submit();
   	}
    function insertGztz(){
    	window.location.href="<%=basePath%>jsp/StatisticsFile/gztzInsert.jsp";
    }
    
    
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//转义;
		form.action = "GztzServlet?action=getGztzXFList&srbt="+srbt+"&page_no=" + v;
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
		String cxmc = (String)request.getAttribute("cxmc"); 
		if(cxmc==null)cxmc="";
		String cxdd = (String)request.getAttribute("cxdd"); 
		if(cxdd==null)cxdd="";
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
					<img  alt="选中" align="middle" src="images/small/favorites.png"><a href='GztzServlet?action=getGztzXFList&flag=1'><span style="font-size: 16px;border:1px; background:#C1FFB2;">发送通知列表</span></a>
				</td>
				<td width="80%" align="left">
					通知名称：<input type=text name="cxmc" id="cxmc" size="6" class="STYLE1" value="<%=cxmc %>"/>
					会议地点：<input type=text name="cxdd" id="cxdd" size="6" class="STYLE1" value="<%=cxdd %>"/>
				</td>
			</tr>
			<tr>
				<td>
					<img  alt="未选中" align="middle" src="images/small/cute-ball-favorites.png"><a href='GztzServlet?action=getGztzJSList&sfhf=0'><span style="font-size: 16px">接收通知列表</span></a>
				</td>
				<td align="left">
					通知日期：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq1);" type="button" value="选择">
					到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq2);" type="button" value="选择">
					<%if(UserPer.getGztz().indexOf("1")!=-1){%>
					<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryGztzByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					<%} %>
				</td>
			</tr>
			<tr>
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
			<td align="center" width="30%" class="STYLE1">
				通知名称
			</td>
			<td align="center" width="15%" class="STYLE1">
				通知时间
			</td>
			<td align="center" width="20%" class="STYLE1">
				会议地点
			</td>
			<td align="center" width="20%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< GztzList.size();i++){
				Gztz gztz = (Gztz)GztzList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(GztzList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzmc()!=null&&!"".equals(gztz.getTzmc())){%>
				<a href="GztzServlet?action=showGztzXF&gztzid=<%=gztz.getId()%>" target="_blank"><%=gztz.getTzmc()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzsj()!=null&&!"".equals(gztz.getTzsj())){%>
				<%=gztz.getTzsj().toString().substring(0,16)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzdd()!=null&&!"".equals(gztz.getTzdd())){%>
				<%=gztz.getTzdd()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="GztzServlet?action=showGztzHF&tzid=<%=gztz.getId()%>" target="_blank"><img  alt="回复情况" title="回复情况" src="images/small/icon_cinema_4d.png"></a>
				&nbsp;
				<%if(UserPer.getGztz().indexOf("4")!=-1){%>
				<a href="GztzServlet?action=deleteGztzXF&id=<%=gztz.getId()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
			 	<%}%>
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
			<%if(UserPer.getGztz().indexOf("2")!=-1){%>
				<img alt="新增通知" width="100px" height="60px" title="新增通知" style="cursor: pointer;" src="images/small/i11.png"  onclick="insertGztz()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
