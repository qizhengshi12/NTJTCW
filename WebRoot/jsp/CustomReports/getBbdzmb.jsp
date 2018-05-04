<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Bbdzmb"%>  
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
    ArrayList BbdzmbList = (ArrayList)request.getAttribute("BbdzmbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>所有文件列表</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryBbdzmbByBt(){
   		var cxbt = document.getElementById("cxbt").value;
   		var cxlx = document.getElementById("cxlx").value;
		var srbt = "";
   		if(cxbt!=""){
   			srbt = " where bt  like '%25"+cxbt+"%25'";
   		}
   		if(cxlx!=""){
   			if(srbt!=""){
   				srbt = srbt +" and lx ='"+cxlx+"'";
   			}else{
   				srbt = " where lx ='"+cxlx+"'";
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "BbdzServlet?action=getBbdzmb&srbt=" + srbt;
		form.submit();
   	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("cxlx").value=sel.options[sel.selectedIndex].value;
	}
	function selChange2(){
		var sel = document.getElementById("sel2");
		document.getElementById("lx").value=sel.options[sel.selectedIndex].value;
	}
    function insertBbdzmb(){
   		var lx = document.getElementById("lx").value;
   		var dzls = document.getElementById("dzls").value;
   		var re = /^\d+$/;   //判断字符串是否为正整数
		if (!re.test(dzls)){
			alert("报表列数请输入正整数");
			return;
		}
   		var form = document.getElementById("form1");
   		form.target="_self";
		form.action = "BbdzServlet?action=editBbdzmb&lx="+lx+"&dzls="+dzls;
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
		form.action = "BbdzServlet?action=getBbdzmb&srbt="+srbt+"&page_no=" + v;
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
		String cxlx = (String)request.getAttribute("cxlx"); 
		if(cxlx==null)cxlx="";
	%>
	
  <form method="post" id="form1" action="">
  </form>
  <form method="post" id="form2" name="Bbdz">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%" >
		<tr>
			<td align="center">
				报表模板名称：<input type=text name="cxbt" id="cxbt" size="20" class="STYLE1" value="<%=cxbt %>"/>
				类型：<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=cxlx %>">
						<%if("cwbb".equals(cxlx)){%>
						财务
						<%}else if("sjbb".equals(cxlx)){%>
						审计
						<%}else if("tjbb".equals(cxlx)){%>
						统计
						<%}else{ %>
						&nbsp;
						<%} %>
						</option>
						<option value="cwbb">财务</option>
						<option value="sjbb">审计</option>
						<option value="tjbb">统计</option>
					  </select>
				<input type=hidden name="cxlx" id="cxlx" value="<%=cxlx %>"/>&nbsp; 
				<%if(UserPer.getBbdz().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryBbdzmbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
			<td align="center" width="25%" class="STYLE1">
				报表模板名称
			</td>
			<td align="center" width="5%" class="STYLE1">
				类型
			</td>
			<td align="center" width="15%" class="STYLE1">
				制表单位
			</td>
			<td align="center" width="10%" class="STYLE1">
				制表人
			</td>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< BbdzmbList.size();i++){
				Bbdzmb bbdzmb = (Bbdzmb)BbdzmbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(BbdzmbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getBt()!=null&&!"".equals(bbdzmb.getBt())){%>
				<a href="BbdzServlet?action=showBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>" target="_blank"><%=bbdzmb.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("cwbb".equals(bbdzmb.getLx())){%>
				财务
				<%}else if("sjbb".equals(bbdzmb.getLx())){%>
				审计
				<%}else if("tjbb".equals(bbdzmb.getLx())){%>
				统计
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getCzrdw()!=null&&!"".equals(bbdzmb.getCzrdw())){%>
				<%=bbdzmb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getCzr()!=null&&!"".equals(bbdzmb.getCzr())){%>
				<%=bbdzmb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("管理员".equals(roles)||((username.equals(bbdzmb.getCzrID())&&UserPer.getBbdz().indexOf("3")!=-1))){%>
					<a href="BbdzServlet?action=editBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>&lx=<%=bbdzmb.getLx()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
				<%} %>
				<%if("管理员".equals(roles)||((username.equals(bbdzmb.getCzrID())&&UserPer.getBbdz().indexOf("4")!=-1))){%>
					<a href="BbdzServlet?action=deleteBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>&lx=<%=bbdzmb.getLx()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
	<%if(UserPer.getBbdz().indexOf("2")!=-1){%>
	<table width="100%"  align="center">
		<tr>
			<td width="50%"  align="right">
			类型：<select id="sel2" onchange="selChange2()" class="STYLE1">
					<option value="<%=cxlx %>">
					<%if("cwbb".equals(cxlx)){%>
					财务
					<%}else if("sjbb".equals(cxlx)){%>
					审计
					<%}else if("tjbb".equals(cxlx)){%>
					统计
					<%}else{ %>
					&nbsp;
					<%} %>
					</option>
					<option value="cwbb">财务</option>
					<option value="sjbb">审计</option>
					<option value="tjbb">统计</option>
				  </select>
				<input type=hidden name="lx" id="lx" value="<%=cxlx %>"/>&nbsp; 
				报表列数<input type="text" size="6" name="dzls" id="dzls" value=""/>
			</td>
			<td width="50%"  align="left">
				<img alt="新增报表模板" width="100px" height="60px" title="新增报表模板" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertBbdzmb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			</td>
		</tr>
	</table>
	<%} } %>
  </body>
</html>
