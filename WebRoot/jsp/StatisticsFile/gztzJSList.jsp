<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Gztzhf"%>  
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
    ArrayList GztzhfList = (ArrayList)request.getAttribute("GztzhfList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>����֪ͨ�б�</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
   	function queryGztzJSByBt(){
   		var cxmc = document.getElementById("cxmc").value;
   		var sfhf = document.getElementById("sfhf").value;
   		var srbt = "";
   		if(sfhf!=0){
   			if(srbt==1){
   				srbt = " where hfnr <>'' ";
   			}else{
   				srbt = " where hfnr ='' ";
   			}
   		}
   		if(cxmc!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzmc like '%25"+cxmc+"%25'";
   			}else{
   				srbt = " where tzmc like '%25"+cxmc+"%25'";
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "GztzServlet?action=getGztzJSList&srbt=" + srbt;
		form.submit();
   	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("sfhf").value=sel.options[sel.selectedIndex].value;
	}
	//�ظ�
	function showDIV(id){
		CloseDode();
		var popUp = document.getElementById("showDIV");
		popUp.style.top = "300px";  
		popUp.style.left = "250px";
		popUp.style.width = "300px"; 
		popUp.style.height = "200px"; 
		popUp.style.visibility = "visible";
		document.getElementById("hfid").value=id;
	}
	function CloseDode(){
		var popUp = document.getElementById("showDIV"); 
		popUp.style.visibility = "hidden"; 
	}
    function insertDode(){
    	var hfid = document.getElementById("hfid").value;
    	var hfnr = document.getElementById("hfnr").value;
    	if(hfnr==""){
			alert("����д�ظ�����");
			return;
    	}
   		var form = document.getElementById("form1");
		form.action = "GztzServlet?action=updateGztzHF&id=" + hfid+"&hfnr=" + hfnr;
		form.submit();
    }
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
   		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//ת��;
		form.action = "GztzServlet?action=getGztzJSList&srbt="+srbt+"&page_no=" + v;
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
		String cxmc = (String)request.getAttribute("cxmc"); 
		if(cxmc==null)cxmc="";
		String sfhf = (String)request.getAttribute("sfhf"); 
		String str = "";
		if(sfhf==null)sfhf="";
		if("0".equals(sfhf)){
			str="ȫ��";
		}else if("1".equals(sfhf)){
			str="�ѻظ�";
		}else if("2".equals(sfhf)){
			str="δ�ظ�";
		}
	%>
	
  <form method="post" id="form1" action="">
  </form>
  <form method="post" id="form2" action="">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" name="sfhf" id="sfhf" value="<%=sfhf %>"/>
	<table width="100%" >
		<tr>
			<td width="20%">
				<img  alt="δѡ��" align="middle" src="images/small/cute-ball-favorites.png"><a href='GztzServlet?action=getGztzXFList&flag=1'><span style="font-size: 16px">����֪ͨ�б�</span></a>
			</td>
			<td width="80%" align="left">
				֪ͨ���ƣ�<input type=text name="cxmc" id="cxmc" class="STYLE1" value="<%=cxmc %>"/>
				�ظ�״̬��<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=sfhf %>"><%=str %></option>
						<option value="0">ȫ��</option>
						<option value="1">�ѻظ�</option>
						<option value="2">δ�ظ�</option>
					  </select>
				<%if(UserPer.getWjgl().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryGztzJSByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img  alt="ѡ��" align="middle" src="images/small/favorites.png"><a href='GztzServlet?action=getGztzJSList&sfhf=0'><span style="font-size: 16px;border:1px; background:#D1EEEE;">����֪ͨ�б�</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
	</table>
  </form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				���
			</td>
			<td align="center" width="34%" class="STYLE1">
				֪ͨ����
			</td>
			<td align="center" width="10%" class="STYLE1">
				�ظ���
			</td>
			<td align="center" width="15%" class="STYLE1">
				�ظ�ʱ��
			</td>
			<td align="center" width="35%" class="STYLE1">
				�ظ�����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< GztzhfList.size();i++){
				 	Gztzhf gztzhf = (Gztzhf)GztzhfList.get(i);
		%>
			<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
				<td align="center">
					<%if(GztzhfList.size()!=0){%>
					<%=i+1%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getTzmc()!=null&&!"".equals(gztzhf.getTzmc())){%>
					<a href="GztzServlet?action=showGztzXF&gztzid=<%=gztzhf.getTzid()%>" target="_blank"><%=gztzhf.getTzmc()%></a>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfr()!=null&&!"".equals(gztzhf.getHfr())){%>
					<%=gztzhf.getHfr()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfsj()!=null&&!"".equals(gztzhf.getHfsj())){%>
					<%=gztzhf.getHfsj().toString().substring(0,10)%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(gztzhf.getHfnr()!=null&&!"".equals(gztzhf.getHfnr())){%>
					<img  align="middle" src="images/small/flag_06.png">
					<%=gztzhf.getHfnr()%>
					<%}else{ %>
					<img alt="δ�ظ�" title="δ�ظ�" width="25px" align="middle" style="cursor: pointer;" height="25px"" src="images/small/flag_10.png" onclick="showDIV('<%=gztzhf.getId()%>')">
					<input type="button" class="button1" value="δ�ظ�" onclick="showDIV('<%=gztzhf.getId()%>')"/>
					<%} %>
				</td>
			</tr>
			<% }%>
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

	<div id="showDIV" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">�ظ�</h1>
					<input type="hidden" id="hfid" value="">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<textarea id="hfnr" rows="5" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="ȷ��" width="45px" height="45px" title="ȷ��" style="cursor: pointer;"  src="images/small/4.png" onclick="insertDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>
