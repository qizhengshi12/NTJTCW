<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Bbdz"%>  
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
    ArrayList BbdzList = (ArrayList)request.getAttribute("BbdzList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>根据模板定制的报表列表</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryBbdzByBt(){
   		var username = document.getElementById("username").value;
	   	var cxtj = document.getElementById("cxtj").value;
   		var cxcompany = document.getElementById("cxcompany").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
   		var zbid = document.getElementById("zbid").value;
   		var lx = document.getElementById("lx").value;
		var srbt = " where zbid ="+zbid+" ";
   		if(cxcompany!=""){
   			srbt = srbt +" and czrdw ='"+cxcompany+"'";
   		}
   		if(cxssrq1!=""){
   			srbt = srbt +" and sj >='"+cxssrq1+"'";
   		}
   		if(cxssrq2!=""){
   			srbt = srbt +" and sj <='"+cxssrq2+"'";
   		}
   		if(cxtj=="1"){
   			srbt = srbt +" and tjzt ='"+cxtj+"' ";
   		}else{
   			srbt = srbt +" and tjzt ='"+cxtj+"' and czrID = '"+username+"' ";
   		}
   		var form = document.getElementById("form2");
		form.action = "BbdzServlet?action=getBbdz&srbt=" + srbt+"&lx="+lx;
		form.submit();
   	}
    function insertBbdz(){
   		var lx = document.getElementById("lx").value;
   		var zbid = document.getElementById("zbid").value;
   		var form = document.getElementById("form1");
		form.action = "BbdzServlet?action=editBbdz&lx="+lx+"&zbid="+zbid;
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
   		var lx = document.getElementById("lx").value;
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbdzServlet?action=getBbdz&srbt="+srbt+"&page_no=" + v+"&lx="+lx;
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
		String lx = (String)request.getAttribute("lx"); 
		if(lx==null)lx="";
		String qx = "";
		String qxfzr = "";
		if("cwbb".equals(lx)){
			qx = UserPer.getCwbb();
			qxfzr="财务负责人";
		}else if("sjbb".equals(lx)){
			qx = UserPer.getSjbb();
			qxfzr="审计负责人";
		}else if("tjbb".equals(lx)){
			qx = UserPer.getTjbb();
			qxfzr="统计负责人";
		}
		String zbid = (String)request.getAttribute("zbid"); 
		if(zbid==null)zbid="";
	%>
	
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" name="Bbdz">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="zbid" value="<%=zbid%>">
	<input type="hidden" id="lx" value="<%=lx%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					报告日期：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Bbdz.cxssrq1);" type="button" value="选择">
					到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Bbdz.cxssrq2);" type="button" value="选择">
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
					<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryBbdzByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
			for(int i=0;i< BbdzList.size();i++){
				Bbdz bbdz = (Bbdz)BbdzList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(BbdzList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbdzServlet?action=showBbdz&id=<%=bbdz.getId()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>&bbls=<%=bbdz.getBbls()%>" target="_blank"><%=bbdz.getBt()%></a>
			</td>
			<td align="center">
				<%if(bbdz.getCzrdw()!=null&&!"".equals(bbdz.getCzrdw())){%>
				<%=bbdz.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdz.getCzr()!=null&&!"".equals(bbdz.getCzr())){%>
				<%=bbdz.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdz.getSj()!=null&&!"".equals(bbdz.getSj())){%>
				<%=bbdz.getSj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(bbdz.getTjzt())){%>
				已提交
				<%}else if("2".equals(bbdz.getTjzt())){ %>
				未提交
				<%}else{ %>
				驳回
				<%} %>
			</td>
			<td align="center">
				<%if(qxfzr.equals(roles)&&"局机关".equals(company)&&"1".equals(bbdz.getTjzt())){%>
				<a href="BbdzServlet?action=returnBbdz&id=<%=bbdz.getId()%>&bhry=<%=bbdz.getCzrID()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>"><img  alt="驳回" title="驳回" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(bbdz.getCzrID())&&("2".equals(bbdz.getTjzt())||"3".equals(bbdz.getTjzt()))&&qx.indexOf("3")!=-1)){%>
				<a href="BbdzServlet?action=editBbdz&id=<%=bbdz.getId()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(bbdz.getCzrID())&&("2".equals(bbdz.getTjzt())||"3".equals(bbdz.getTjzt()))&&qx.indexOf("4")!=-1)){%>
				<a href="BbdzServlet?action=deleteBbdz&id=<%=bbdz.getId()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>&bbls=<%=bbdz.getBbls()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
				<%if(qx.indexOf("2")!=-1){%>
				<img alt="新增表格" width="100px" height="60px" title="新增表格" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertBbdz()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
