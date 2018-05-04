<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.MyMessage"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.PaginationTool"%>  

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
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
    ArrayList MyMessageList = (ArrayList)request.getAttribute("MyMessageList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>收件箱</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function queryMyMessageByBt(){
	   		var dxnr = document.getElementById("dxnr").value;
	   		var cxssrq1 = document.getElementById("cxssrq1").value;
	   		var cxssrq2 = document.getElementById("cxssrq2").value;
			var srbt = "";
	   		if(dxnr!=""){
	   			srbt = " where dxnr  like '%25"+dxnr+"%25'";
	   		}
	   		if(cxssrq1!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jssj >='"+cxssrq1+"'";
	   			}else{
	   				srbt = " where jssj >='"+cxssrq1+"'";
	   			}
	   		}
	   		if(cxssrq2!=""){
	   			if(srbt!=""){
	   				srbt = srbt +" and jssj <='"+cxssrq2+"'";
	   			}else{
	   				srbt = " where jssj <='"+cxssrq2+"'";
	   			}
	   		}
	   		var form = document.getElementById("form1");
			form.action = "MessageServlet?action=MyMessage&srbt=" + srbt;
			form.submit();
	   	}
	   	//查看短信
		function ShowMessage(id,zt,nr){
			CloseMessage();
			var popUp = document.getElementById("MessageDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
			document.getElementById("nr").value = nr.replace(/<br>/g,"\n");
			//更新状态
			if(zt=="未查阅"){
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
				    	//var jsons = xmlhttp.responseText;
				    }
				  }
				var url = "MessageServlet?action=updateMyMessage&id=" + id;
				xmlhttp.open("POST",url,true);
				xmlhttp.send();
			}
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
	        srbt = srbt.replace(/%/g,"%25");//转义;
			form.action = "MessageServlet?action=MyMessage&srbt="+srbt+"&page_no=" + v;
			form.submit();
		}
		
	//全选
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
	
	//批量删除
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
			alert("请选中需要删除的选项");
			return;
		}else{
			if(confirm("确定删除选中记录么？")){
				var form = document.getElementById("form2");
				form.action = "MessageServlet?action=deleteSomeMyMessage&IDList="+IDList;
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
  <form method="post" id="form1" action="" name="MSG">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	  <table width="100%">
		<tr>
			<td align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=newMessage'><span style="font-size: 16px">发送新短信</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="选中" align="middle" src="images/small/favorites.png"><a href='MessageServlet?action=MyMessage'><span style="font-size: 16px;border:1px; background:#D1EEEE;">已接收短信</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='MessageServlet?action=allSendMessage'><span style="font-size: 16px">已发送短信</span></a>
			</td>
		</tr>
		<tr >
			<td>
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td>
				短信内容：<input type=text name="dxnr" id="dxnr" size="25" class="STYLE1" value="<%=dxnr %>"/>
				接收时间：<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq1);" type="button" value="选择">
				到<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="15" readonly/><input name="Button" class="button1" onclick="setDayHM(document.MSG.cxssrq2);" type="button" value="选择">
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryMyMessageByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批量删除：
				<img align="middle" width="40px" height="40px" alt="批量删除" title="批量删除" style="cursor: pointer;" src="images/small/burn1.png" onclick="deleteSome()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
				序号
			</td>
			<td align="center" width="20%" class="STYLE1">
				接收时间
			</td>
			<td align="center" width="15%" class="STYLE1">
				发件人
			</td>
			<td align="center" width="40%" class="STYLE1">
				短信内容
			</td>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
		</tr>
		<%
         	/*遍历每个的信息进行显示*/
			for(int i=0;i< MyMessageList.size();i++){
				MyMessage myMessage = (MyMessage)MyMessageList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<input type="checkbox" name="checkbox2" value = "<%=myMessage.getId()%>" />
				<%if(MyMessageList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(myMessage.getJssj()!=null&&!"".equals(myMessage.getJssj())){%>
				<%=myMessage.getJssj().toString().substring(0,16)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(myMessage.getFsr()!=null&&!"".equals(myMessage.getFsr())){%>
				<%=myMessage.getFsr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center"style="cursor: pointer; <%if("未查阅".equals(myMessage.getCybz())){%>font-weight: bold; <%} %>" onclick="ShowMessage('<%=myMessage.getId()%>','<%=myMessage.getCybz()%>','<%=myMessage.getDxnr()%>')">
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
				<a href="MessageServlet?action=forwarding&dxnr=<%=myMessage.getDxnr()%>"><img  alt="转发" title="转发" src="images/small/options.png"></a>
				<a href="MessageServlet?action=deleteMyMessage&id=<%=myMessage.getId()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
	
	<div id="MessageDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center">
					短信内容
				</td>
			</tr>
			<tr>
				<td align="center">
					<textarea id="nr" rows="8" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="关闭" width="45px" height="45px" title="关闭" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseMessage();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>
