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
    ArrayList CwbbLrbList = (ArrayList)request.getAttribute("CwbbLrbList");
    String hzbz = "";
    if("����ʵҵ�������޹�˾".equals(company)){
    	hzbz = "QYJT";
    }else if("��ͨ����Ͷ�ʹ�˾".equals(company)){
    	hzbz = "TZGS";
    }else if("��ͨ�ۺ���Ŧվ�����޹�˾".equals(company)){
    	hzbz = "ZWGS";
    }else if("�����ۺ����ܼ������".equals(company)){
    	hzbz = "JCZX";
    }else if("������ͨ�ܹ�˾".equals(company)){
    	hzbz = "GJGS";
    }else if("����������˾".equals(company)){
    	hzbz = "CZGS";
    }else{
    	hzbz = "QYJT";//�����������˼���Ϊ׼
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�����</title>
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
					�������ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Lrb.cxssrq1);" type="button" value="ѡ��">
					��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Lrb.cxssrq2);" type="button" value="ѡ��">
					<input type="hidden" name="cxcompany" id="cxcompany"  value="<%=cxcompany %>" />
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
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryCwbbLrbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
				���
			</td>
			<td align="center" width="10%" class="STYLE1">
				����ʱ��
			</td>
			<td align="center" width="5%" class="STYLE1">
				״̬
			</td>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
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
				���ύ
				<%}else if("2".equals(cwbbLrb.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if("��������".equals(roles)&&"�ֻ���".equals(company)&&"1".equals(cwbbLrb.getTjzt())){%>
				<a href="BbsbCwbbServlet?action=LrbReturn&id=<%=cwbbLrb.getId()%>&bhry=<%=cwbbLrb.getCzrID()%>"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbLrb.getCzrID())&&("2".equals(cwbbLrb.getTjzt())||"3".equals(cwbbLrb.getTjzt()))&&UserPer.getCwbb().indexOf("3")!=-1)){%>
				<a href="BbsbCwbbServlet?action=LrbEdit&id=<%=cwbbLrb.getId()%>&hzbz=<%=cwbbLrb.getHzbz()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(cwbbLrb.getCzrID())&&("2".equals(cwbbLrb.getTjzt())||"3".equals(cwbbLrb.getTjzt()))&&UserPer.getCwbb().indexOf("4")!=-1)){%>
				<a href="BbsbCwbbServlet?action=LrbDelete&id=<%=cwbbLrb.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
			<td align="center">
				<%if(UserPer.getCwbb().indexOf("2")!=-1){%>
				<img alt="�������" width="100px" height="60px" title="�������" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertCwbbLrb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
