<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Gztz"%>  
<%@page import="com.safety.entity.Menu"%>
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
    ArrayList GztzList = (ArrayList)request.getAttribute("GztzList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>����֪ͨ</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryGztzByBt(){
   		var cxmc = document.getElementById("cxmc").value;
   		var cxdd = document.getElementById("cxdd").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
		var srbt = "";
   		if(cxmc!=""){
   			srbt = " where tzmc  like '%25"+cxmc+"%25'";
   		}
   		if(cxdd!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzdd like '%25"+cxdd+"%25'";
   			}else{
   				srbt = " where tzdd like '%25"+cxdd+"%25'";
   			}
   		}
   		if(cxssrq1!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzsj >='"+cxssrq1+"'";
   			}else{
   				srbt = " where tzsj >='"+cxssrq1+"'";
   			}
   		}
   		if(cxssrq2!=""){
   			if(srbt!=""){
   				srbt = srbt +" and tzsj <='"+cxssrq2+"'";
   			}else{
   				srbt = " where tzsj <='"+cxssrq2+"'";
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "GztzServlet?action=getGztzXFList&srbt=" + srbt;
		form.submit();
   	}
    function insertGztz(){
    	window.location.href="<%=basePath%>jsp/StatisticsFile/gztzInsert.jsp";
    }
    
    
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//ת��;
		form.action = "GztzServlet?action=getGztzXFList&srbt="+srbt+"&page_no=" + v;
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
		String cxdd = (String)request.getAttribute("cxdd"); 
		if(cxdd==null)cxdd="";
		String cxssrq1 = (String)request.getAttribute("cxssrq1"); 
		if(cxssrq1==null)cxssrq1="";
		String cxssrq2 = (String)request.getAttribute("cxssrq2"); 
		if(cxssrq2==null)cxssrq2="";
	%>
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" name="WJGL">
	<input type="hidden" id="srbt" value="<%=srbt%>">
		<table width="100%" >
			<tr>
				<td width="20%">
					<img  alt="ѡ��" align="middle" src="images/small/favorites.png"><a href='GztzServlet?action=getGztzXFList&flag=1'><span style="font-size: 16px;border:1px; background:#C1FFB2;">����֪ͨ�б�</span></a>
				</td>
				<td width="80%" align="left">
					֪ͨ���ƣ�<input type=text name="cxmc" id="cxmc" size="6" class="STYLE1" value="<%=cxmc %>"/>
					����ص㣺<input type=text name="cxdd" id="cxdd" size="6" class="STYLE1" value="<%=cxdd %>"/>
				</td>
			</tr>
			<tr>
				<td>
					<img  alt="δѡ��" align="middle" src="images/small/cute-ball-favorites.png"><a href='GztzServlet?action=getGztzJSList&sfhf=0'><span style="font-size: 16px">����֪ͨ�б�</span></a>
				</td>
				<td align="left">
					֪ͨ���ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq1);" type="button" value="ѡ��">
					��<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq2);" type="button" value="ѡ��">
					<%if(UserPer.getGztz().indexOf("1")!=-1){%>
					<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryGztzByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					<%} %>
				</td>
			</tr>
			<tr>
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
			<td align="center" width="30%" class="STYLE1">
				֪ͨ����
			</td>
			<td align="center" width="15%" class="STYLE1">
				֪ͨʱ��
			</td>
			<td align="center" width="20%" class="STYLE1">
				����ص�
			</td>
			<td align="center" width="20%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< GztzList.size();i++){
				Gztz gztz = (Gztz)GztzList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(GztzList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzmc()!=null&&!"".equals(gztz.getTzmc())){%>
				<a href="GztzServlet?action=showGztzXF&gztzid=<%=gztz.getId()%>" target="_blank"><%=gztz.getTzmc()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzsj()!=null&&!"".equals(gztz.getTzsj())){%>
				<%=gztz.getTzsj().toString().substring(0,16)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(gztz.getTzdd()!=null&&!"".equals(gztz.getTzdd())){%>
				<%=gztz.getTzdd()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<a href="GztzServlet?action=showGztzHF&tzid=<%=gztz.getId()%>" target="_blank"><img  alt="�ظ����" title="�ظ����" src="images/small/icon_cinema_4d.png"></a>
				&nbsp;
				<%if(UserPer.getGztz().indexOf("4")!=-1){%>
				<a href="GztzServlet?action=deleteGztzXF&id=<%=gztz.getId()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			 	<%}%>
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
			<%if(UserPer.getGztz().indexOf("2")!=-1){%>
				<img alt="����֪ͨ" width="100px" height="60px" title="����֪ͨ" style="cursor: pointer;" src="images/small/i11.png"  onclick="insertGztz()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
