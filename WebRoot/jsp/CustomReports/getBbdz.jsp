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
    ArrayList BbdzList = (ArrayList)request.getAttribute("BbdzList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>����ģ�嶨�Ƶı����б�</title>
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
			qxfzr="��������";
		}else if("sjbb".equals(lx)){
			qx = UserPer.getSjbb();
			qxfzr="��Ƹ�����";
		}else if("tjbb".equals(lx)){
			qx = UserPer.getTjbb();
			qxfzr="ͳ�Ƹ�����";
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
					�������ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Bbdz.cxssrq1);" type="button" value="ѡ��">
					��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/>&nbsp;<input name="Button" class="button1" onclick="setDay(document.Bbdz.cxssrq2);" type="button" value="ѡ��">
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
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryBbdzByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
				���ύ
				<%}else if("2".equals(bbdz.getTjzt())){ %>
				δ�ύ
				<%}else{ %>
				����
				<%} %>
			</td>
			<td align="center">
				<%if(qxfzr.equals(roles)&&"�ֻ���".equals(company)&&"1".equals(bbdz.getTjzt())){%>
				<a href="BbdzServlet?action=returnBbdz&id=<%=bbdz.getId()%>&bhry=<%=bbdz.getCzrID()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>"><img  alt="����" title="����" src="images/small/backbh.png"></a>
			 	<%} %>
				<%if((username.equals(bbdz.getCzrID())&&("2".equals(bbdz.getTjzt())||"3".equals(bbdz.getTjzt()))&&qx.indexOf("3")!=-1)){%>
				<a href="BbdzServlet?action=editBbdz&id=<%=bbdz.getId()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if((username.equals(bbdz.getCzrID())&&("2".equals(bbdz.getTjzt())||"3".equals(bbdz.getTjzt()))&&qx.indexOf("4")!=-1)){%>
				<a href="BbdzServlet?action=deleteBbdz&id=<%=bbdz.getId()%>&lx=<%=lx%>&zbid=<%=bbdz.getZbid()%>&bbls=<%=bbdz.getBbls()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
				<%if(qx.indexOf("2")!=-1){%>
				<img alt="�������" width="100px" height="60px" title="�������" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertBbdz()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
