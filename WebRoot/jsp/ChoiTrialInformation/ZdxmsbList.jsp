<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Zdxmsb"%>  
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
	String flag = (String)request.getAttribute("flag");
	String srbt = (String)request.getAttribute("srbt");
	if(srbt==null)srbt="";
	String cxcompany = (String)request.getAttribute("cxcompany");
	if(cxcompany==null)cxcompany="";
    ArrayList ZdxmsbList = (ArrayList)request.getAttribute("ZdxmsbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�ش���Ŀ�걨�����б�</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
		var cxcompany = document.getElementById("cxcompany").value;
        srbt = srbt.replace(/%/g,"%25");//ת��;
		form.action = "ZdxmsbServlet?action=getZdxmsbList&cxcompany="+cxcompany+"&srbt="+srbt+"&page_no=" + v;
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
	<form method="post" id="form1" action=""></form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="cxcompany" value="<%=cxcompany%>">
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1 style="">
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				���
			</td>
			<td align="center" width="30%" class="STYLE1">
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
			<td align="center" width="10%" class="STYLE1">
				�������
			</td>
			<td align="center" width="10%" class="STYLE1">
				�Ƿ��ύ
			</td>
			<td align="center" width="10%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< ZdxmsbList.size();i++){
				Zdxmsb zdxmsb = (Zdxmsb)ZdxmsbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(ZdxmsbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getLx()!=null&&!"".equals(zdxmsb.getLx())){%>
				<a href="ZdxmsbServlet?action=ZdxmsbShow&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&flag=1" target="_blank">
				<% 
				if("1".equals(zdxmsb.getLx())){
					%>ע���ʱ����<%
				}else if("2".equals(zdxmsb.getLx())){
					%>�ش����Ͷ��<%
				}else if("3".equals(zdxmsb.getLx())){
					%>�ش����ʣ���ծ��<%
				}else if("4".equals(zdxmsb.getLx())){
					%>��Ȩ���<%
				}else if("5".equals(zdxmsb.getLx())){
					%>ծ������<%
				}else if("6".equals(zdxmsb.getLx())){
					%>�����������ش��ʲ���Ѻ����<%
				}
				%>
				</a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzrdw()!=null&&!"".equals(zdxmsb.getCzrdw())){%>
				<%=zdxmsb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzr()!=null&&!"".equals(zdxmsb.getCzr())){%>
				<%=zdxmsb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getCzsj()!=null&&!"".equals(zdxmsb.getCzsj())){%>
				<%=zdxmsb.getCzsj().toString().substring(0,10)%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(zdxmsb.getShyj()!=null&&!"".equals(zdxmsb.getShyj())){%>
				<%=zdxmsb.getShyj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(zdxmsb.getTjzt())){%>
				���ύ
				<%}else{ %>
				δ�ύ
				<%} %>
			</td>
			<td align="center">
				<%if(("����".equals(zdxmsb.getShyj())||"2".equals(zdxmsb.getTjzt()))&&(username.equals(zdxmsb.getCzrID())&&UserPer.getZdxmsb().indexOf("3")!=-1)){%>
				<a href="ZdxmsbServlet?action=ZdxmsbEdit&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>" target="I2"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
			 	<%} %>
				<%if(("����".equals(zdxmsb.getShyj())||"2".equals(zdxmsb.getTjzt()))&&(username.equals(zdxmsb.getCzrID())&&UserPer.getZdxmsb().indexOf("4")!=-1)){%>
				<a href="ZdxmsbServlet?action=ZdxmsbDelete&id=<%=zdxmsb.getId()%>&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&smj=<%=zdxmsb.getSmj()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');" target="I2"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			 	<%} %>
				<%if("δ����".equals(zdxmsb.getShyj())&&(username.equals(zdxmsb.getShrID()))){%>
				<a href="ZdxmsbServlet?action=ZdxmsbShow&lx=<%=zdxmsb.getLx()%>&nrid=<%=zdxmsb.getNrid()%>&flag=2"><img  alt="����" title="����" src="images/small/icon_bitcomet.png"></a>
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
	<%} %>
  </body>
</html>
