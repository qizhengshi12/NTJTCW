<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Wjglxf"%>  
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
    ArrayList WjglxfList = (ArrayList)request.getAttribute("WjglxfList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�·��б�</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryWjglByBt(){
   		var cxwjmc = document.getElementById("cxwjmc").value;
   		var cxwjlx = document.getElementById("cxwjlx").value;
   		var cxwjh = document.getElementById("cxwjh").value;
   		var cxfqr = document.getElementById("cxfqr").value;
   		var cxssrq1 = document.getElementById("cxssrq1").value;
   		var cxssrq2 = document.getElementById("cxssrq2").value;
		var srbt = " where fqrID ='"+cxfqr+"'";
   		if(cxwjmc!=""){
   			srbt = srbt +" and wjmc  like '%25"+cxwjmc+"%25'";
   		}
   		if(cxwjlx!=""){
   			srbt = srbt +" and wjlx ='"+cxwjlx+"'";
   		}
   		if(cxwjh!=""){
   			srbt = srbt +" and wjh like '%25"+cxwjh+"%25'";
   		}
   		if(cxssrq1!=""){
   			srbt = srbt +" and fqsj >='"+cxssrq1+"'";
   		}
   		if(cxssrq2!=""){
   			srbt = srbt +" and fqsj <='"+cxssrq2+"'";
   		}
   		var form = document.getElementById("form2");
		form.action = "WjglServlet?action=getWjglXFList&srbt=" + srbt;
		form.submit();
   	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("cxwjlx").value=sel.options[sel.selectedIndex].value;
	}
    function showWjglXF(){
   		var form = document.getElementById("form1");
   		form.target="_self";
		form.action = "WjglServlet?action=wjglXFInsert";
		form.submit();
    }
    function getWjglTJList(){
   		var form = document.getElementById("form1");
   		form.target="_blank";
		form.action = "WjglServlet?action=getWjglTJList&srbt=";
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
		form.action = "WjglServlet?action=getWjglXFList&srbt="+srbt+"&page_no=" + v;
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
		String cxwjmc = (String)request.getAttribute("cxwjmc"); 
		if(cxwjmc==null)cxwjmc="";
		String cxwjlx = (String)request.getAttribute("cxwjlx"); 
		if(cxwjlx==null)cxwjlx="";
		String cxwjh = (String)request.getAttribute("cxwjh"); 
		if(cxwjh==null)cxwjh="";
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
				<img  alt="δѡ��" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglList&flag=1'><span style="font-size: 16px">�����ļ��б�</span></a>
			</td>
			<td width="80%" align="left" rowspan="2">
				�ļ����ƣ�<input type=text name="cxwjmc" id="cxwjmc" size="6" class="STYLE1" value="<%=cxwjmc %>"/>
				���ͣ�<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=cxwjlx %>"><%=cxwjlx %></option>
						<option value="">ȫ��</option>
						<option value="����">����</option>
						<option value="���">���</option>
						<option value="ͳ��">ͳ��</option>
					  </select>
					  <input type=hidden name="cxwjlx" id="cxwjlx" value="<%=cxwjlx %>"/>&nbsp; 
				�ĺţ�<input type=text name="cxwjh" id="cxwjh" size="6" class="STYLE1" value="<%=cxwjh %>"/>
				<input type=hidden name="cxfqr" id="cxfqr" value="<%=username %>"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="ѡ��" align="top" src="images/small/favorites.png"><a href='WjglServlet?action=getWjglXFList&flag=1'><span style="font-size: 16px;border:1px; background:#D1EEEE;">�ѷ����ļ��б�</span></a>
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="δѡ��" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglJSList&flag=1'><span style="font-size: 16px">��ظ��ļ��б�</span></a>
			</td>
			<td align="left" rowspan="2">
				�������ڣ�<input name="cxssrq1" id="cxssrq1" type="text" value="<%=cxssrq1 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq1);" type="button" value="ѡ��">
				��
				<input name="cxssrq2" id="cxssrq2" type="text" value="<%=cxssrq2 %>" size="10" readonly/><input name="Button" class="button1" onclick="setDay(document.WJGL.cxssrq2);" type="button" value="ѡ��">
				<%if(UserPer.getWjgl().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryWjglByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
		<tr>
			<td>
				<img  alt="δѡ��" align="top" src="images/small/cute-ball-favorites.png"><a href='WjglServlet?action=getWjglCGList&flag=1'><span style="font-size: 16px">�ݸ����ļ��б�</span></a>
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
			<td align="center" width="25%" class="STYLE1">
				�ļ���
			</td>
			<td align="center" width="10%" class="STYLE1">
				�ĺ�
			</td>
			<td align="center" width="5%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				���ĵ�λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				����ʱ��
			</td>
			<td align="center" width="10%" class="STYLE1">
				�ظ�����
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< WjglxfList.size();i++){
				Wjglxf wjglxf = (Wjglxf)WjglxfList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(WjglxfList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getWjmc()!=null&&!"".equals(wjglxf.getWjmc())){%>
				<a href="WjglServlet?action=showWjglXF&id=<%=wjglxf.getId()%>" target="_blank"><%=wjglxf.getWjmc()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getWjh()!=null&&!"".equals(wjglxf.getWjh())){%>
				<%=wjglxf.getWjh()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getWjlx()!=null&&!"".equals(wjglxf.getWjlx())){%>
				<%=wjglxf.getWjlx()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getFwdw()!=null&&!"".equals(wjglxf.getFwdw())){%>
				<%=wjglxf.getFwdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getFqsj()!=null&&!"".equals(wjglxf.getFqsj())){%>
				<%=wjglxf.getFqsj().toString().substring(0,10)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(wjglxf.getHfqx()!=null&&!"".equals(wjglxf.getHfqx())){%>
				<%=wjglxf.getHfqx()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(!"".equals(wjglxf.getHfryID())&&wjglxf.getHfryID()!=null){%>
					<a href="WjglServlet?action=showWjglXF&id=<%=wjglxf.getId()%>&flag=1" target="_blank"><img  alt="�ظ����" title="�ظ����" src="images/small/icon_cinema_4d.png"></a>
			 	<%}%>
				<%if(UserPer.getWjgl().indexOf("4")!=-1){%>
  				<a href="WjglServlet?action=deleteWjglXF&id=<%=wjglxf.getId() %>&FileUrl=<%=wjglxf.getFileUrl()%>&address=2" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
			<%if(UserPer.getWjgl().indexOf("2")!=-1){%>
				<img alt="�·��ļ�" width="100px" height="60px" title="�·��ļ�" style="cursor: pointer;" src="images/small/i9.png"  onclick="showWjglXF()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
			<td align="center">
			<%if("����Ա".equals(roles)||(("��������".equals(roles)||"ͳ�Ƹ�����".equals(roles))&&"�ֻ���".equals(company))){%>
				<img alt="ͳ��" width="100px" height="60px" title="ͳ��" style="cursor: pointer;" src="images/small/i10.png"  onclick="getWjglTJList()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">		
			<%} %>					
			</td>
		</tr>
	</table>
	<%} %>
  </body>
</html>
