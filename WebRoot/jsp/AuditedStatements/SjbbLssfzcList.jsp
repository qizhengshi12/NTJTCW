<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.SjbbLssfzc"%>  
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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList SjbbLssfzcList = (ArrayList)request.getAttribute("SjbbLssfzcList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>��ʵ�շ����߼����Բ��</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function querySjbbLssfzcByBt(){
   		var username = document.getElementById("username").value;
	   	var cxtj = document.getElementById("cxtj").value;
   		var cxcompany = document.getElementById("cxcompany").value;
   		var cxyear = document.getElementById("cxyear").value;
   		var cxjd = document.getElementById("cxjd").value;
		var srbt = "";
   		if(cxyear!=""){
   			srbt = " where year  = "+cxyear;
   		}
   		if(cxjd!=""){
   			if(srbt!=""){
   				srbt = srbt +" and jd = "+cxjd;
   			}else{
   				srbt = " where jd = "+cxjd;
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
		form.action = "BbsbSjbbServlet?action=getLssfzczcb&srbt=" + srbt;
		form.submit();
   	}
    function insertSjbbLssfzc(){
   		var form = document.getElementById("form1");
		form.action = "BbsbSjbbServlet?action=lssfzczcbEdit";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbSjbbServlet?action=getLssfzczcb&srbt="+srbt+"&page_no=" + v;
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
		String cxyear = (String)request.getAttribute("cxyear"); 
		if(cxyear==null)cxyear="";
		String cxjd = (String)request.getAttribute("cxjd"); 
		if(cxjd==null)cxjd="";
		String cxtj = (String)request.getAttribute("cxtj"); 
		if(cxtj==null)cxtj="";
		Calendar cal=Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
  	%>
	
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" name="SjbbLssfzc">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					<select name="cxyear" id="cxyear">
					<%if(!"".equals(cxyear)){ %>
						<option value="<%=cxyear %>"><%=cxyear %>��</option>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
					<%}else{ %>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					<select name="cxjd" id="cxjd">
					<%if(!"".equals(cxjd)){ %>
						<option value="<%=cxjd %>"><% if("0".equals(cxjd)){ %>ȫ��<%}else{%><%=cxjd %>����<%}%></option>
						<option value="0">ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
					<%}else{ %>
						<option value="0" selected>ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
					<%} %>	
					</select>
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany%>" />
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
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="querySjbbLssfzcByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="25%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				��λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				����ʱ��
			</td>
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="5%" class="STYLE1">
				״̬
			</td>
			<td align="center" width="15%" class="STYLE1">
				����
			</td>
		</tr>
		<%
			/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< SjbbLssfzcList.size();i++){
				SjbbLssfzc sjbbLssfzc = (SjbbLssfzc)SjbbLssfzcList.get(i);
				
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(SjbbLssfzcList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbSjbbServlet?action=lssfzczcbShow&id=<%=sjbbLssfzc.getId()%>" target="_blank">��ʵ�շ����߼����Բ��</a>
			</td>
			<td align="center">
				<%if(sjbbLssfzc.getCzrdw()!=null&&!"".equals(sjbbLssfzc.getCzrdw())){%>
				<%=sjbbLssfzc.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(sjbbLssfzc.getSbsj()!=null&&!"".equals(sjbbLssfzc.getSbsj())){%>
				<%=sjbbLssfzc.getSbsj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(sjbbLssfzc.getCzr()!=null&&!"".equals(sjbbLssfzc.getCzr())){%>
				<%=sjbbLssfzc.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(sjbbLssfzc.getTjzt())){%>
				���ύ
				<%}else if("2".equals(sjbbLssfzc.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if("��Ƹ�����".equals(roles)&&"�ֻ���".equals(company)&&"1".equals(sjbbLssfzc.getTjzt())){%>
				<a href="BbsbSjbbServlet?action=lssfzczcbReturn&id=<%=sjbbLssfzc.getId()%>&bhry=<%=sjbbLssfzc.getCzrID()%>"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if("����Ա".equals(roles)||((username.equals(sjbbLssfzc.getCzrID())&&("2".equals(sjbbLssfzc.getTjzt())||"3".equals(sjbbLssfzc.getTjzt()))&&UserPer.getSjbb().indexOf("3")!=-1))){%>
				<a href="BbsbSjbbServlet?action=lssfzczcbEdit&id=<%=sjbbLssfzc.getId()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if("����Ա".equals(roles)||((username.equals(sjbbLssfzc.getCzrID())&&("2".equals(sjbbLssfzc.getTjzt())||"3".equals(sjbbLssfzc.getTjzt()))&&UserPer.getSjbb().indexOf("4")!=-1))){%>
				<a href="BbsbSjbbServlet?action=lssfzczcbDelete&id=<%=sjbbLssfzc.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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

	<table width="100%" >
		<tr height="35px" valign="bottom">
			<%if(UserPer.getSjbb().indexOf("2")!=-1){%>
			<td align="center">
				<img alt="�������" width="100px" height="60px" title="�������" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertSjbbLssfzc()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
			<%} %>
		</tr>
	</table>
	<%} %>
  </body>
</html>
