<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.MyEmail"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  

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
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList MyEmailList = (ArrayList)request.getAttribute("MyEmailList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�ռ���</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function queryMyEmailByBt(){
	   		var cxbt = document.getElementById("cxbt").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
			var srbt = " where sczt='1' ";
	   		if(cxbt!=""){
	   			srbt = srbt +" and bt like '%25"+cxbt+"%25'";
	   		}
	   		if(cxssrq1!=""){
	   			srbt = srbt +" and jssj >='"+cxssrq1+"'";
	   		}
	   		if(cxssrq2!=""){
	   			srbt = srbt +" and jssj <='"+cxssrq2+"'";
	   		}
	   		var form = document.getElementById("form1");
			form.action = "EmailServlet?action=MyEmail&srbt=" + srbt;
			form.submit();
	   	}
	   	function goNum(){
			var chooseNum = document.getElementById("chooseNum").value;
			skipTo(chooseNum);
		}
		function skipTo(v){
			var form = document.getElementById("form1");
			var srbt = document.getElementById("srbt").value;
	        srbt = srbt.replace(/%/g,"%25");//ת��;
			form.action = "EmailServlet?action=MyEmail&srbt="+srbt+"&page_no=" + v;
			form.submit();
		}
		
	//ȫѡ
	function selectAll(){
		var selectzt = document.getElementById("checkbox1").checked;
		var objs = document.getElementsByName('checkbox2');
		if(selectzt==true){
			for(i=0;i<objs.length;i++){
				objs[i].checked = true;
			}
		}else{
			for(i=0;i<objs.length;i++){
				objs[i].checked = false;
			}
		}
	}
	
	//����ɾ��
	function deleteSome(){
		var objs = document.getElementsByName('checkbox2');
		var IDList="";
		for(i=0;i<objs.length;i++){
			if(objs[i].checked == true){
				if(IDList == ""){
					IDList +=objs[i].value;
				}else{
					IDList +=","+objs[i].value;
				}	
			}
		}
		if(IDList==""){
			alert("��ѡ����Ҫɾ����ѡ��");
			return;
		}else{
			if(confirm("ȷ��ɾ��ѡ�м�¼ô��")){
				var form = document.getElementById("form2");
				form.action = "EmailServlet?action=deleteSomeMyEmail&ly=1&IDList="+IDList;
				form.submit();
			} 
		}
	}
	function delOne(id){
		var form = document.getElementById("form2");
		form.action = "EmailServlet?action=deleteMyEmail&ly=1&id="+id;
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
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
  <form method="post" id="form1" action="" name="MSG">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	  <table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=NewEmail'><span style="font-size: 16px;">���ʼ�</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="ѡ��" align="middle" src="images/small/favorites.png"><a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px;border:1px; background:#C1FFB2;">�ռ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">������</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">�ݸ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">����վ</span></a>
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				�ʼ����⣺<input type=text name="cxbt" id="cxbt" size="25" class="STYLE1" value="<%=cxbt %>"/>
				����ʱ�䣺<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq1);" type="button" value="ѡ��">
				��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq2);" type="button" value="ѡ��">
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryMyEmailByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ɾ����
				<img align="middle" width="40px" height="40px" alt="����ɾ��" title="����ɾ��" style="cursor: pointer;" src="images/small/burn1.png" onclick="deleteSome()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
	  </table>
  </form>
  <form method="post" id="form2" action="">
	  <table width="100%">
		<tr>
			<td align="right">
				<input type="checkbox" name="cdsc" checked="checked" value="1">ɾ��������վ
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	  </table>
  </form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				<input type="checkbox" id="checkbox1" onchange="selectAll()" />
				���
			</td>
			<td align="center" width="40%" class="STYLE1">
				�ʼ�����
			</td>
			<td align="center" width="10%" class="STYLE1">
				������
			</td>
			<td align="center" width="15%" class="STYLE1">
				��������
			</td>
			<td align="center" width="10%" class="STYLE1">
				����״̬
			</td>
			<td align="center" width="15%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< MyEmailList.size();i++){
				MyEmail MyEmail = (MyEmail)MyEmailList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<input type="checkbox" name="checkbox2" value = "<%=MyEmail.getId()%>" />
				<%if(MyEmailList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getBt()!=null&&!"".equals(MyEmail.getBt())){%>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><%=MyEmail.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getFsr()!=null&&!"".equals(MyEmail.getFsr())){%>
				<%=MyEmail.getFsr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(MyEmail.getJssj()!=null&&!"".equals(MyEmail.getJssj())){%>
				<%=MyEmail.getJssj().toString().substring(0,19)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(MyEmail.getCyzt())){%>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><img  alt="δ����" title="δ����" src="images/small/envelope1.png"></a>
				<%}else{ %>
				<a href="EmailServlet?action=ShowEmail&id=<%=MyEmail.getId()%>&sendid=<%=MyEmail.getSendid()%>&zt=<%=MyEmail.getCyzt()%>&ly=1"><img  alt="�Ѳ���" title="�Ѳ���" src="images/small/envelope.png"></a>
				<%} %>
			</td>
			<td align="center">
				<img  alt="ɾ��" title="ɾ��" src="images/small/burn.png" onclick="delOne('<%=MyEmail.getId()%>')" style="cursor: pointer;">
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
	<%} %>
  </body>
</html>
