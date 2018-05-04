<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.CwbbLrb"%>  
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
    ArrayList CwbbLrbList = (ArrayList)request.getAttribute("CwbbLrbList");
    String hzbz = "";
    if("汽运实业集团有限公司".equals(company)){
    	hzbz = "QYJT";
    }else if("交通建设投资公司".equals(company)){
    	hzbz = "TZGS";
    }else if("交通综合枢纽站务有限公司".equals(company)){
    	hzbz = "ZWGS";
    }else if("汽车综合性能检测中心".equals(company)){
    	hzbz = "JCZX";
    }else if("公共交通总公司".equals(company)){
    	hzbz = "GJGS";
    }else if("出租汽车公司".equals(company)){
    	hzbz = "CZGS";
    }else{
    	hzbz = "QYJT";//其他均以汽运集团为准
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>损益表</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryCwbbLrbByBt(){
   		var username = document.getElementById("username").value;
	   	var cxtj = document.getElementById("cxtj").value;
   		var cxcompany = document.getElementById("cxcompany").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
		var srbt = "";
   		if(cxcompany!=""){
   			srbt = " where czrdw ='"+cxcompany+"'";
   		}
   		if(cxssrq1!=""){
   			if(srbt!=""){
   				srbt = srbt +" and sbsj >='"+cxssrq1+"'";
   			}else{
   				srbt = " where sbsj >='"+cxssrq1+"'";
   			}
   		}
   		if(cxssrq2!=""){
   			if(srbt!=""){
   				srbt = srbt +" and sbsj <='"+cxssrq2+"'";
   			}else{
   				srbt = " where sbsj <='"+cxssrq2+"'";
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
   		var form = document.getElementById("form2");
		form.action = "BbsbCwbbServlet?action=getLrb&srbt=" + srbt;
		form.submit();
   	}
    function insertCwbbLrb(){
   		var hzbz = document.getElementById("hzbz").value;
   		var form = document.getElementById("form1");
		form.action = "BbsbCwbbServlet?action=LrbEdit&hzbz="+hzbz;
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbCwbbServlet?action=getLrb&srbt="+srbt+"&page_no=" + v;
		form.submit();
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
  	<%
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String cxcompany = (String)request.getAttribute("cxcompany"); 
		if(cxcompany==null)cxcompany="";
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
		String cxtj = (String)request.getAttribute("cxtj"); 
		if(cxtj==null)cxtj="";
	%>
	
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" name="Lrb">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="hzbz" value="<%=hzbz%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					报告日期：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Lrb.cxssrq1);" type="button" value="选择">
					到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Lrb.cxssrq2);" type="button" value="选择">
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany %>" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					提交状态：
					<select id="sel3" onchange="selChange3()" class="STYLE1">
						<option value="<%=cxtj %>">
							<% if("1".equals(cxtj)) {%>
							已提交
							<% }else if("2".equals(cxtj)){ %>
							未提交
							<% }else if("3".equals(cxtj)){ %>
							驳回
							<% }%>
						</option>
						<option value="1">已提交</option>
						<option value="2">未提交</option>
						<option value="3">驳回</option>
					</select>
					<input type=hidden name="cxtj" id="cxtj" value="<%=cxtj %>"/>&nbsp;
					<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryCwbbLrbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
				名称
			</td>
			<td align="center" width="15%" class="STYLE1">
				单位
			</td>
			<td align="center" width="10%" class="STYLE1">
				填报人
			</td>
			<td align="center" width="10%" class="STYLE1">
				报告时间
			</td>
			<td align="center" width="5%" class="STYLE1">
				状态
			</td>
			<td align="center" width="20%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< CwbbLrbList.size();i++){
				CwbbLrb cwbbLrb = (CwbbLrb)CwbbLrbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(CwbbLrbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbCwbbServlet?action=LrbShow&id=<%=cwbbLrb.getId()%>&hzbz=<%=cwbbLrb.getHzbz()%>" target="_blank"><%=cwbbLrb.getBt()%></a>
			</td>
			<td align="center">
				<%if(cwbbLrb.getCzrdw()!=null&&!"".equals(cwbbLrb.getCzrdw())){%>
				<%=cwbbLrb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbLrb.getCzr()!=null&&!"".equals(cwbbLrb.getCzr())){%>
				<%=cwbbLrb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbLrb.getSbsj()!=null&&!"".equals(cwbbLrb.getSbsj())){%>
				<%=cwbbLrb.getSbsj().toString().substring(0,10)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(cwbbLrb.getTjzt())){%>
				已提交
				<%}else if("2".equals(cwbbLrb.getTjzt())){ %>
				未提交
				<%}else{ %>
				驳回
				<%} %>
			</td>
			<td align="center">
				<%if("财务负责人".equals(roles)&&"局机关".equals(company)&&"1".equals(cwbbLrb.getTjzt())){%>
				<a href="BbsbCwbbServlet?action=LrbReturn&id=<%=cwbbLrb.getId()%>&bhry=<%=cwbbLrb.getCzrID()%>"><img  alt="驳回" title="驳回" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbLrb.getCzrID())&&("2".equals(cwbbLrb.getTjzt())||"3".equals(cwbbLrb.getTjzt()))&&UserPer.getCwbb().indexOf("3")!=-1)){%>
				<a href="BbsbCwbbServlet?action=LrbEdit&id=<%=cwbbLrb.getId()%>&hzbz=<%=cwbbLrb.getHzbz()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbLrb.getCzrID())&&("2".equals(cwbbLrb.getTjzt())||"3".equals(cwbbLrb.getTjzt()))&&UserPer.getCwbb().indexOf("4")!=-1)){%>
				<a href="BbsbCwbbServlet?action=LrbDelete&id=<%=cwbbLrb.getId()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
				<%if(UserPer.getCwbb().indexOf("2")!=-1){%>
				<img alt="新增表格" width="100px" height="60px" title="新增表格" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertCwbbLrb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
