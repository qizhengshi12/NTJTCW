<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.TjbbYxzbHd"%>  
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
    ArrayList TjbbYxzbHdList = (ArrayList)request.getAttribute("TjbbYxzbHdList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>��Ҫ����ָ��ͳ�Ʊ�������</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryTjbbYxzbHdByBt(){
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
		form.action = "BbsbTjbbServlet?action=getTjbbYxzbHd&srbt=" + srbt;
		form.submit();
   	}
    function insertTjbbYxzbHd(){
   		var xzrq = document.getElementById("xzrq").value;
   		if(xzrq==""){
   			alert("�����뱨������");
   			return;
   		}
   		var form = document.getElementById("form1");
		form.action = "BbsbTjbbServlet?action=TjbbYxzbHdEdit";
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
		form.action = "BbsbTjbbServlet?action=getTjbbYxzbHd&srbt="+srbt+"&page_no=" + v;
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
	
	<form method="post" id="form2" name="TjbbYxzbHd">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type=hidden name="username" id="username" value="<%=username%>"/>
		<table width="100%" >
			<tr>
				<td>
					���ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1%>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.TjbbYxzbHd.cxssrq1);" type="button" value="ѡ��">
					��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2%>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.TjbbYxzbHd.cxssrq2);" type="button" value="ѡ��">
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany%>" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					�ύ״̬��
					<select id="sel3" onchange="selChange3()" class="STYLE1">
						<option value="<%=cxtj %>">
							<% if("1".equals(cxtj)) {%>
							���ύ
							<% }else{ %>
							δ�ύ
							<% }%>
						</option>
						<option value="1">���ύ</option>
						<option value="2">δ�ύ</option>
					</select>
					<input type=hidden name="cxtj" id="cxtj" value="<%=cxtj %>"/>&nbsp;
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryTjbbYxzbHdByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				���
			</td>
			<td align="center" width="30%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				��λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
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
			for(int i=0;i< TjbbYxzbHdList.size();i++){
				TjbbYxzbHd tjbbYxzbHd = (TjbbYxzbHd)TjbbYxzbHdList.get(i);
				
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(TjbbYxzbHdList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="BbsbTjbbServlet?action=TjbbYxzbHdShow&id=<%=tjbbYxzbHd.getId()%>" target="_blank"><%=tjbbYxzbHd.getBt()%></a>
			</td>
			<td align="center">
				<%if(tjbbYxzbHd.getCzrdw()!=null&&!"".equals(tjbbYxzbHd.getCzrdw())){%>
				<%=tjbbYxzbHd.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(tjbbYxzbHd.getSj()!=null&&!"".equals(tjbbYxzbHd.getSj())){%>
				<%=tjbbYxzbHd.getSj().toString().substring(0,4)%>��<%=tjbbYxzbHd.getSj().toString().substring(5,7)%>��
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(tjbbYxzbHd.getCzr()!=null&&!"".equals(tjbbYxzbHd.getCzr())){%>
				<%=tjbbYxzbHd.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(tjbbYxzbHd.getTjzt())){%>
				���ύ
				<%}else if("2".equals(tjbbYxzbHd.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if("ͳ�Ƹ�����".equals(roles)&&"�ֻ���".equals(company)&&"1".equals(tjbbYxzbHd.getTjzt())){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbHdReturn&id=<%=tjbbYxzbHd.getId()%>&bhry=<%=tjbbYxzbHd.getCzrID()%>"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(tjbbYxzbHd.getCzrID())&&("2".equals(tjbbYxzbHd.getTjzt())||"3".equals(tjbbYxzbHd.getTjzt()))&&UserPer.getTjbb().indexOf("3")!=-1)){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbHdEdit&id=<%=tjbbYxzbHd.getId()%>&xzrq=<%=tjbbYxzbHd.getSj()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(tjbbYxzbHd.getCzrID())&&("2".equals(tjbbYxzbHd.getTjzt())||"3".equals(tjbbYxzbHd.getTjzt()))&&UserPer.getTjbb().indexOf("4")!=-1)){%>
				<a href="BbsbTjbbServlet?action=TjbbYxzbHdDelete&id=<%=tjbbYxzbHd.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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

	<form method="post" id="form1" action="" name="Tjbb">
	<table width="100%" >
		<tr height="35px">
			<%if(UserPer.getTjbb().indexOf("2")!=-1){%>
			<td align="right" width="50%">
			�������ڣ�<input name="xzrq" id="xzrq" type="text" value="" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Tjbb.xzrq);" type="button" value="ѡ��">
			</td>
			<td align="left" width="50%">
				<img alt="�������" width="100px" height="60px" title="�������" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertTjbbYxzbHd()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
			<%} %>
		</tr>
	</table>
	</form>
	<%} %>
  </body>
</html>
