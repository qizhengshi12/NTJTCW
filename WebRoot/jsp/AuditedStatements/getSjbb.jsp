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
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    <title>��Ʊ���</title>
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
				if(company=="�ֻ���"&&roles=="��Ƹ�����"){
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
    	//���ò˵�
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
				    	tree.nodes['-1_0'] = 'text:��ͨ�н�ͨ�����;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				     </SCRIPT>
	    		</div>
				<%if("����Ա".equals(roles)){%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="���ò˵�" width="100px" height="60px" title="���ò˵�" style="cursor: pointer;" src="images/small/i15.png"  onclick="resetMenu()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
				<%} %>
			</td>
			<td width="1px" bgcolor="#5CACEE"></td>
			<td width="85%" valign="top">	
			  <table width="100%" height="100%">
				<tr>
					<td height="35px">
						��Ʊ�����
					  	<select id="sel1" onchange="selChange1()" class="STYLE1">
							<option value=""></option>
							<option value="1">��ͨ���ͳ�Ʊ�</option>
							<option value="2">��ͨ���䲿����ƶ���ͳ�Ʊ�</option>
							<option value="3">�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��</option>
    						<% if(("��Ƹ�����".equals(roles)||"��������".equals(roles))&&"�ֻ���".equals(company)){ %>
							<option value="4">�м����أ���������ҵ��λ����ʵ�շ����߼����Բ�����ܱ�</option>
							<%} %>
							<%
					         	/*����ÿ������Ϣ������ʾ*/
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