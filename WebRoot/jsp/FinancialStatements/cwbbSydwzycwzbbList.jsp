<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbb"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  
<%@page import="com.safety.entity.Permissions"%>     
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
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList CwbbSydwzycwzbbList = (ArrayList)request.getAttribute("CwbbSydwzycwzbbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>��ҵ��λ��Ҫ����ָ���</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryCwbbSydwzycwzbbByBt(){
   		var username = document.getElementById("username").value;
   		var cxyear = document.getElementById("cxyear").value;
   		var cxmonth = document.getElementById("cxmonth").value;
   		var cxcompany = document.getElementById("cxcompany").value;
	   	var cxtj = document.getElementById("cxtj").value;
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
   		var form = document.getElementById("form2");
		form.action = "BbsbCwbbServlet?action=getSydwzycwzbb&srbt=" + srbt;
		form.submit();
   	}
    function insertCwbbSydwzycwzbb(){
   		var form = document.getElementById("form1");
		form.action = "BbsbCwbbServlet?action=sydwzycwzbbEdit";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbCwbbServlet?action=getSydwzycwzbb&srbt="+srbt+"&page_no=" + v;
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
  	<%
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String cxyear = (String)request.getAttribute("cxyear"); 
		if(cxyear==null)cxyear="";
		String cxmonth = (String)request.getAttribute("cxmonth"); 
		if(cxmonth==null)cxmonth="";
		String cxcompany = (String)request.getAttribute("cxcompany"); 
		if(cxcompany==null)cxcompany="";
		String cxtj = (String)request.getAttribute("cxtj"); 
		if(cxtj==null)cxtj="";
		Calendar cal=Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH)+1;
	%>
	
	<form method="post" id="form2">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					<select name="cxyear" id="cxyear">
					<%if(!"".equals(cxyear)){ %>
						<option value="<%=cxyear %>"><%=cxyear %>��</option>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					&nbsp;1-
					<select name="cxmonth" id="cxmonth">
					<%if(!"".equals(cxmonth)){ %>
						<option value="<%=cxmonth %>"><%=cxmonth %>��</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>��</option>
						<%} }%>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					�ύ״̬��
					<select id="sel3" onchange="selChange3()" class="STYLE1">
						<option value="<%=cxtj %>">
							<% if("1".equals(cxtj)) {%>
							���ύ
							<% }else if("2".equals(cxtj)){ %>
							δ�ύ
							<% }else if("3".equals(cxtj)){ %>
							����
							<% }%>
						</option>
						<option value="1">���ύ</option>
						<option value="2">δ�ύ</option>
						<option value="3">����</option>
					</select>
					<input type=hidden name="cxtj" id="cxtj" value="<%=cxtj %>"/>&nbsp;
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany %>" />
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryCwbbSydwzycwzbbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				���
			</td>
			<td align="center" width="25%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				��λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="5%" class="STYLE1">
				�·�
			</td>
			<td align="center" width="10%" class="STYLE1">
				״̬
			</td>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< CwbbSydwzycwzbbList.size();i++){
				CwbbSydwzycwzbb cwbbSydwzycwzbb = (CwbbSydwzycwzbb)CwbbSydwzycwzbbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(CwbbSydwzycwzbbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbCwbbServlet?action=sydwzycwzbbShow&id=<%=cwbbSydwzycwzbb.getId()%>" target="_blank"><%=cwbbSydwzycwzbb.getBt()%></a>
			</td>
			<td align="center">
				<%if(cwbbSydwzycwzbb.getCzrdw()!=null&&!"".equals(cwbbSydwzycwzbb.getCzrdw())){%>
				<%=cwbbSydwzycwzbb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSydwzycwzbb.getYear()!=0&&!"".equals(cwbbSydwzycwzbb.getYear())){%>
				<%=cwbbSydwzycwzbb.getYear()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(cwbbSydwzycwzbb.getMonth()!=0&&!"".equals(cwbbSydwzycwzbb.getMonth())){%>
				<%=cwbbSydwzycwzbb.getMonth()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(cwbbSydwzycwzbb.getTjzt())){%>
				���ύ
				<%}else if("2".equals(cwbbSydwzycwzbb.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if(("��������".equals(roles)||"��ϢԱ".equals(roles))&&"�ֻ���".equals(company)&&"1".equals(cwbbSydwzycwzbb.getTjzt())){%>
				<a href="BbsbCwbbServlet?action=sydwzycwzbbReturn&id=<%=cwbbSydwzycwzbb.getId()%>&bhry=<%=cwbbSydwzycwzbb.getCzrID()%>"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbSydwzycwzbb.getCzrID())&&("2".equals(cwbbSydwzycwzbb.getTjzt())||"3".equals(cwbbSydwzycwzbb.getTjzt()))&&UserPer.getCwbb().indexOf("3")!=-1)){%>
				<a href="BbsbCwbbServlet?action=sydwzycwzbbEdit&id=<%=cwbbSydwzycwzbb.getId()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbSydwzycwzbb.getCzrID())&&("2".equals(cwbbSydwzycwzbb.getTjzt())||"3".equals(cwbbSydwzycwzbb.getTjzt()))&&UserPer.getCwbb().indexOf("4")!=-1)){%>
				<a href="BbsbCwbbServlet?action=sydwzycwzbbDelete&id=<%=cwbbSydwzycwzbb.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
			<!-- ��ʾ��ҳ��������ʼ -->
			<%=pt.printPage() %>
			<!--��ʾ��ҳ����������-->
			</td>
		</tr>
	</table>

	<form method="post" id="form1" action="">
	<table width="100%" >
		<tr>
			<td width="50%" align="right">
			<%if(UserPer.getCwbb().indexOf("5")!=-1){%>
			<input name="srcompany" id="srcompany" type="text" onclick="FindCompany()" value="<%=company %>"readonly="readonly" class="STYLE1"/><span id="suggest1"></span>
			<%} %>
			</td>
			<td width="50%" align="left">
				<%if(UserPer.getCwbb().indexOf("2")!=-1){%>
				<img alt="��������" width="100px" height="60px" title="��������" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertCwbbSydwzycwzbb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>