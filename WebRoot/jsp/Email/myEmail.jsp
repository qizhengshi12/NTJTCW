<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.MyEmail"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  

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
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList MyEmailList = (ArrayList)request.getAttribute("MyEmailList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>收件箱</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function queryMyEmailByBt(){
	   		var cxbt = document.getElementById("cxbt").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
			var srbt = " where sczt='1' ";
	   		if(cxbt!=""){
	   			srbt = srbt +" and bt like '%25"+cxbt+"%25'";
	   		}
	   		if(cxssrq1!=""){
	   			srbt = srbt +" and jssj >='"+cxssrq1+"'";
	   		}
	   		if(cxssrq2!=""){
	   			srbt = srbt +" and jssj <='"+cxssrq2+"'";
	   		}
	   		var form = document.getElementById("form1");
			form.action = "EmailServlet?action=MyEmail&srbt=" + srbt;
			form.submit();
	   	}
	   	function goNum(){
			var chooseNum = document.getElementById("chooseNum").value;
			skipTo(chooseNum);
		}
		function skipTo(v){
			var form = document.getElementById("form1");
			var srbt = document.getElementById("srbt").value;
	        srbt = srbt.replace(/%/g,"%25");//转义;
			form.action = "EmailServlet?action=MyEmail&srbt="+srbt+"&page_no=" + v;
			form.submit();
		}
		
	//全选
	function selectAll(){
		var selectzt = document.getElementById("checkbox1").checked;
		var objs = document.getElementsByName('checkbox2');
		if(selectzt==true){
			for(i=0;i<objs.length;i++){
				objs[i].checked = true;
			}
		}else{
			for(i=0;i<objs.length;i++){
				objs[i].checked = false;
			}
		}
	}
	
	//批量删除
	function deleteSome(){
		var objs = document.getElementsByName('checkbox2');
		var IDList="";
		for(i=0;i<objs.length;i++){
			if(objs[i].checked == true){
				if(IDList == ""){
					IDList +=objs[i].value;
				}else{
					IDList +=","+objs[i].value;
				}	
			}
		}
		if(IDList==""){
			alert("请选中需要删除的选项");
			return;
		}else{
			if(confirm("确定删除选中记录么？")){
				var form = document.getElementById("form2");
				form.action = "EmailServlet?action=deleteSomeMyEmail&ly=1&IDList="+IDList;
				form.submit();
			} 
		}
	}
	function delOne(id){
		var form = document.getElementById("form2");
		form.action = "EmailServlet?action=deleteMyEmail&ly=1&id="+id;
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
		String cxbt = (String)request.getAttribute("cxbt"); 
		if(cxbt==null)cxbt="";
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
  <form method="post" id="form1" action="" name="MSG">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	  <table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=NewEmail'><span style="font-size: 16px;">新邮件</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="选中" align="middle" src="images/small/favorites.png"><a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px;border:1px; background:#C1FFB2;">收件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">发件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">草稿箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">回收站</span></a>
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				邮件主题：<input type=text name="cxbt" id="cxbt" size="25" class="STYLE1" value="<%=cxbt %>"/>
				接收时间：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq1);" type="button" value="选择">
				到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq2);" type="button" value="选择">
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryMyEmailByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批量删除：
				<img align="middle" width="40px" height="40px" alt="批量删除" title="批量删除" style="cursor: pointer;" src="images/small/burn1.png" onclick="deleteSome()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
	  </table>
  </form>
  <form method="post" id="form2" action="">
	  <table width="100%">
		<tr>
			<td align="right">
				<input type="checkbox" name="cdsc" checked="checked" value="1">删除到回收站
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	  </table>
  </form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				<input type="checkbox" id="checkbox1" onchange="selectAll()" />
				序号
			</td>
			<td align="center" width="40%" class="STYLE1">
				邮件主题
			</td>
			<td align="center" width="10%" class="STYLE1">
				发件人
			</td>
			<td align="center" width="15%" class="STYLE1">
				发件日期
			</td>
			<td align="center" width="10%" class="STYLE1">
				查阅状态
			</td>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< MyEmailList.size();i++){
				MyEmail MyEmail = (MyEmail)MyEmailList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<input type="checkbox" name="checkbox2" value = "<%=MyEmail.getId()%>" />
				<%if(MyEmailList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getBt()!=null&&!"".equals(MyEmail.getBt())){%>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><%=MyEmail.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getFsr()!=null&&!"".equals(MyEmail.getFsr())){%>
				<%=MyEmail.getFsr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getJssj()!=null&&!"".equals(MyEmail.getJssj())){%>
				<%=MyEmail.getJssj().toString().substring(0,19)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(MyEmail.getCyzt())){%>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><img  alt="未查阅" title="未查阅" src="images/small/envelope1.png"></a>
				<%}else{ %>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><img  alt="已查阅" title="已查阅" src="images/small/envelope.png"></a>
				<%} %>
			</td>
			<td align="center">
				<img  alt="删除" title="删除" src="images/small/burn.png" onclick="delOne('<%=MyEmail.getId()%>')" style="cursor: pointer;">
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
	<%} %>
  </body>
</html>
