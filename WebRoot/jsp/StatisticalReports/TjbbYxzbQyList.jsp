<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.TjbbYxzbQy"%>  
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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList TjbbYxzbQyList = (ArrayList)request.getAttribute("TjbbYxzbQyList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客运出站运输量统计表（汽运集团）</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryTjbbYxzbQyByBt(){
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
   				srbt = srbt +" and sj >='"+cxssrq1+"'";
   			}else{
   				srbt = " where sj >='"+cxssrq1+"'";
   			}
   		}
   		if(cxssrq2!=""){
   			if(srbt!=""){
   				srbt = srbt +" and sj <='"+cxssrq2+"'";
   			}else{
   				srbt = " where sj <='"+cxssrq2+"'";
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
		form.action = "BbsbTjbbServlet?action=getTjbbYxzbQy&srbt=" + srbt;
		form.submit();
   	}
    function insertTjbbYxzbQy(){
   		var xzrq = document.getElementById("xzrq").value;
   		if(xzrq==""){
   			alert("请输入报告日期");
   			return;
   		}
   		var form = document.getElementById("form1");
		form.action = "BbsbTjbbServlet?action=TjbbYxzbQyEdit";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbTjbbServlet?action=getTjbbYxzbQy&srbt="+srbt+"&page_no=" + v;
		form.submit();
	}
	function selChange3(){
		var sel = document.getElementById("sel3");
		document.getElementById("cxtj").value=sel.options[sel.selectedIndex].value;
	}
    </script>
  </head>
  <body>
	<%
		if(request.getAttribute("result")!= null) {
	%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<%
 		}
 	%>
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
	
	<form method="post" id="form2" name="TjbbYxzbQy">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					日期：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1%>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.TjbbYxzbQy.cxssrq1);" type="button" value="选择">
					到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2%>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.TjbbYxzbQy.cxssrq2);" type="button" value="选择">
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany%>" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					提交状态：
					<select id="sel3" onchange="selChange3()" class="STYLE1">
						<option value="<%=cxtj %>">
							<% if("1".equals(cxtj)) {%>
							已提交
							<% }else{ %>
							未提交
							<% }%>
						</option>
						<option value="1">已提交</option>
						<option value="2">未提交</option>
					</select>
					<input type=hidden name="cxtj" id="cxtj" value="<%=cxtj %>"/>&nbsp;
					<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryTjbbYxzbQyByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				序号
			</td>
			<td align="center" width="30%" class="STYLE1">
				名称
			</td>
			<td align="center" width="15%" class="STYLE1">
				单位
			</td>
			<td align="center" width="10%" class="STYLE1">
				日期
			</td>
			<td align="center" width="10%" class="STYLE1">
				填报人
			</td>
			<td align="center" width="5%" class="STYLE1">
				状态
			</td>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
			/*遍历每个的信息进行显示*/
			for(int i=0;i< TjbbYxzbQyList.size();i++){
				TjbbYxzbQy tjbbYxzbQy = (TjbbYxzbQy)TjbbYxzbQyList.get(i);
				
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(TjbbYxzbQyList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbTjbbServlet?action=TjbbYxzbQyShow&id=<%=tjbbYxzbQy.getId()%>" target="_blank"><%=tjbbYxzbQy.getBt()%></a>
			</td>
			<td align="center">
				<%if(tjbbYxzbQy.getCzrdw()!=null&&!"".equals(tjbbYxzbQy.getCzrdw())){%>
				<%=tjbbYxzbQy.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(tjbbYxzbQy.getSj()!=null&&!"".equals(tjbbYxzbQy.getSj())){%>
				<%=tjbbYxzbQy.getSj().toString().substring(0,4)%>年<%=tjbbYxzbQy.getSj().toString().substring(5,7)%>月
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(tjbbYxzbQy.getCzr()!=null&&!"".equals(tjbbYxzbQy.getCzr())){%>
				<%=tjbbYxzbQy.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(tjbbYxzbQy.getTjzt())){%>
				已提交
				<%}else if("2".equals(tjbbYxzbQy.getTjzt())){ %>
				未提交
				<%}else{ %>
				驳回
				<%} %>
			</td>
			<td align="center">
				<%if("统计负责人".equals(roles)&&"局机关".equals(company)&&"1".equals(tjbbYxzbQy.getTjzt())){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbQyReturn&id=<%=tjbbYxzbQy.getId()%>&bhry=<%=tjbbYxzbQy.getCzrID()%>"><img  alt="驳回" title="驳回" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(tjbbYxzbQy.getCzrID())&&("2".equals(tjbbYxzbQy.getTjzt())||"3".equals(tjbbYxzbQy.getTjzt()))&&UserPer.getTjbb().indexOf("3")!=-1)){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbQyEdit&id=<%=tjbbYxzbQy.getId()%>&xzrq=<%=tjbbYxzbQy.getSj()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(tjbbYxzbQy.getCzrID())&&("2".equals(tjbbYxzbQy.getTjzt())||"3".equals(tjbbYxzbQy.getTjzt()))&&UserPer.getTjbb().indexOf("4")!=-1)){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbQyDelete&id=<%=tjbbYxzbQy.getId()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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

	<form method="post" id="form1" action="" name="Tjbb">
	<table width="100%" >
		<tr height="35px">
			<%if(UserPer.getTjbb().indexOf("2")!=-1){%>
			<td align="right" width="50%">
			报告日期：<input name="xzrq" id="xzrq" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Tjbb.xzrq);" type="button" value="选择">
			</td>
			<td align="left" width="50%">
				<img alt="新增表格" width="100px" height="60px" title="新增表格" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertTjbbYxzbQy()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
			<%} %>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>
