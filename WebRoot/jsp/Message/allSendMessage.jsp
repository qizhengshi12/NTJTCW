<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SendMessage"%>  
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
    ArrayList SendMessageList = (ArrayList)request.getAttribute("SendMessageList");
%>

  	<%
		String srbt = (String)request.getAttribute("srbt"); 
		if(srbt==null)srbt="";
		String dxnr = (String)request.getAttribute("dxnr"); 
		if(dxnr==null)dxnr="";
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>������</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function querySendMessageByBt(){
	   		var dxnr = document.getElementById("dxnr").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
			var srbt = "";
	   		if(dxnr!=""){
	   			srbt = " where dxnr  like '%25"+dxnr+"%25'";
	   		}
	   		if(cxssrq1!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and fssj >='"+cxssrq1+"'";
	   			}else{
	   				srbt = " where fssj >='"+cxssrq1+"'";
	   			}
	   		}
	   		if(cxssrq2!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and fssj <='"+cxssrq2+"'";
	   			}else{
	   				srbt = " where fssj <='"+cxssrq2+"'";
	   			}
	   		}
	   		var form = document.getElementById("form1");
			form.action = "MessageServlet?action=allSendMessage&srbt=" + srbt;
			form.submit();
	   	}
	   	//�鿴������
		function ShowJsr(jsr){
			CloseMessage();
			CloseJsr();
			var popUp = document.getElementById("JsrDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
			document.getElementById("jsr").value = jsr;
		}
		function CloseJsr(){
			var popUp = document.getElementById("JsrDode"); 
			popUp.style.visibility = "hidden"; 
		}
	   	//�鿴����
		function ShowMessage(nr){
			CloseMessage();
			CloseJsr();
			var popUp = document.getElementById("MessageDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
			document.getElementById("nr").value = nr.replace(/<br>/g,"\n");
		}
		function CloseMessage(){
			var popUp = document.getElementById("MessageDode"); 
			popUp.style.visibility = "hidden"; 
		}
	   	function goNum(){
			var chooseNum = document.getElementById("chooseNum").value;
			skipTo(chooseNum);
		}
		function skipTo(v){
			var form = document.getElementById("form1");
			var srbt = document.getElementById("srbt").value;
	        srbt = srbt.replace(/%/g,"%25");//ת��;
			form.action = "MessageServlet?action=allSendMessage&srbt="+srbt+"&page_no=" + v;
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
				form.action = "MessageServlet?action=deleteSomeSendMessage&IDList="+IDList;
				form.submit();
			} 
		}
	}
	</script>
  </head>
  
  <body>
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  <form method="post" id="form1" action="" name="MSG">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	  <table width="100%">
		<tr>
			<td align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=newMessage'><span style="font-size: 16px">�����¶���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=MyMessage'><span style="font-size: 16px">�ѽ��ն���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="ѡ��" align="middle" src="images/small/favorites.png"><a href='MessageServlet?action=allSendMessage'><span style="font-size: 16px;border:1px; background:#EEE0E5;">�ѷ��Ͷ���</span></a>
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				�������ݣ�<input type=text name="dxnr" id="dxnr" size="25" class="STYLE1" value="<%=dxnr %>"/>
				����ʱ�䣺<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq1);" type="button" value="ѡ��">
				��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq2);" type="button" value="ѡ��">
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="querySendMessageByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
  <form method="post" id="form2" action=""></form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="10%" class="STYLE1">
				<input type="checkbox" id="checkbox1" onchange="selectAll()" />
				���
			</td>
			<td align="center" width="15%" class="STYLE1">
				����ʱ��
			</td>
			<td align="center" width="25%" class="STYLE1">
				�ռ���
			</td>
			<td align="center" width="30%" class="STYLE1">
				��������
			</td>
			<td align="center" width="10%" class="STYLE1">
				�Ƿ���
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< SendMessageList.size();i++){
				SendMessage myMessage = (SendMessage)SendMessageList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<input type="checkbox" name="checkbox2" value = "<%=myMessage.getId()%>" />
				<%if(SendMessageList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(myMessage.getFssj()!=null&&!"".equals(myMessage.getFssj())){%>
				<%=myMessage.getFssj().toString().substring(0,16)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center"style="cursor: pointer;" onclick="ShowJsr('<%=myMessage.getJsr()%>')">
				<%if(myMessage.getJsr()!=null&&!"".equals(myMessage.getJsr())){
					if(myMessage.getJsr().length() > 10){%>
					<%=myMessage.getJsr().substring(0,10)%>...
					<%}else{%>
					<%=myMessage.getJsr()%>
					<%}
				}else{%>
				&nbsp;
				<%} %>
			</td>
			<td align="center"style="cursor: pointer;" onclick="ShowMessage('<%=myMessage.getDxnr()%>')">
				<%if(myMessage.getDxnr()!=null&&!"".equals(myMessage.getDxnr())){
					if(myMessage.getDxnr().length() > 20){%>
					<%=myMessage.getDxnr().substring(0,20)%>...
					<%}else{%>
					<%=myMessage.getDxnr()%>
					<%}
				}else{%>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(myMessage.getSffs()!=null&&!"".equals(myMessage.getSffs())){%>
				<%=myMessage.getSffs()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="MessageServlet?action=forwarding&dxnr=<%=myMessage.getDxnr()%>"><img  alt="ת��" title="ת��" src="images/small/options.png"></a>
				<a href="MessageServlet?action=deleteSendMessage&id=<%=myMessage.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
	
	<div id="MessageDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center">
					��������
				</td>
			</tr>
			<tr>
				<td align="center">
					<textarea id="nr" rows="8" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="�ر�" width="45px" height="45px" title="�ر�" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseMessage();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="JsrDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center">
					������
				</td>
			</tr>
			<tr>
				<td align="center">
					<textarea id="jsr" rows="8" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="�ر�" width="45px" height="45px" title="�ر�" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseJsr();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>