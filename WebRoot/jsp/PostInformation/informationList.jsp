<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.PostInformation"%>  
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
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <title>��Ϣ�����б�</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
     <script src="calendar.js"></script>
     <script type="text/javascript">
     function queryInformatByBt(){
   		var cxbt = document.getElementById("cxbt").value;
   		cxbt = cxbt.replace(/\ /g,"");
		var srbt = "";
    	if(cxbt!=""){
   			srbt = " where bt  like '%25"+cxbt+"%25'";
   		}
   		var form = document.getElementById("form2");
		form.action = "PostInformationServlet?action=getInformationList&srbt=" + srbt;
		form.submit();
   	} 
   	function insertInformat(){
   		var form = document.getElementById("form1");
		form.action = "PostInformationServlet?action=informatEdit";
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
		form.action = "PostInformationServlet?action=getInformationList&srbt="+srbt+"&page_no=" + v;
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
		//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
		String srbt = (String)request.getAttribute("srbt");
		if(srbt==null)srbt="";
		String cxbt = (String)request.getAttribute("cxbt");
		if(cxbt==null)cxbt="";           
	%>
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" action="">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%" >
		<tr>
			<td align="center">
				<h2><img width="80px" height="80px" align="middle" src="images/small/picture.png">��ҳ����</h2>
			</td>
		</tr>
		<tr valign="top">
			<td align="left">
				����:
				<input type=text id="cxbt" name="cxbt" size=25 value="<%=cxbt%>"/>
				&nbsp; 
				<%if(UserPer.getPostinformation().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryInformatByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1>
		<tr class="tr1" height="25px">
			<td align="center" width="5%">
				���
			</td>
			<td align="center" width="50%">
				����
			</td>
			<td align="center" width="10%">
				�ϱ�ʱ��
			</td>
			<td align="center" width="10%">
				�ϱ���
			</td>
			<td align="center" width="10%">
				���״̬
			</td>
			<%if(UserPer.getPostinformation().indexOf("3")!=-1 || UserPer.getPostinformation().indexOf("4")!=-1){%>
			<td align="center" width="15%">
				����
			</td>
			<%} %>
		</tr>
		<%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	ArrayList InformatList = (ArrayList)request.getAttribute("InformatList");
         	/*����ÿ������Ϣ������ʾ*/
           for(int i=0;i< InformatList.size();i++){
           	PostInformation postInformation = (PostInformation)InformatList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center" height="25px">
				<%if(InformatList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(postInformation.getBt()!=null&&!"".equals(postInformation.getBt())){%>
				<a href="PostInformationServlet?action=informatShow&id=<%=postInformation.getId()%>&flag=1" target="_blank"><%=postInformation.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(postInformation.getTjsj()!=null&&!"".equals(postInformation.getTjsj())){%>
				<%=postInformation.getTjsj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(postInformation.getTjr()!=null&&!"".equals(postInformation.getTjr())){%>
				<%=postInformation.getTjr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("1".equals(postInformation.getSfsh())){%>
				δ���
				<%}else if("2".equals(postInformation.getSfsh())){%>
				�����
				<%}else{%>
				����
				<%} %>
			</td>
			<%if(UserPer.getPostinformation().indexOf("3")!=-1 || UserPer.getPostinformation().indexOf("4")!=-1){%>
			<td align="center">
				<%if("����Ա".equals(roles)||(UserPer.getPostinformation().indexOf("3")!=-1)&&username.equals(postInformation.getTjrID())){%>
				 	<a href="PostInformationServlet?action=informatEdit&id=<%=postInformation.getId()%>" target="I2"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
				<%} %>
				<%if("����Ա".equals(roles)||(UserPer.getPostinformation().indexOf("4")!=-1)&&username.equals(postInformation.getTjrID())){%>
				 	<a href="PostInformationServlet?action=delete&id=<%=postInformation.getId()%>&path1=<%=postInformation.getFileURL()%>&path2=<%=postInformation.getPhotoURL()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
			 	<%} %>
				<%if("1".equals(postInformation.getSfsh())&&(username.equals(postInformation.getShrID()))){%>
				<a href="PostInformationServlet?action=informatShow&id=<%=postInformation.getId()%>&flag=2"><img  alt="����" title="����" src="images/small/icon_bitcomet.png"></a>
			 	<%} %>
			</td>
			<%} %>
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
	<table width="100%">
		<tr>
			<td align="center">
			<%if(UserPer.getPostinformation().indexOf("2")!=-1){%>
				<img alt="�����ļ�" width="100px" height="60px" title="�����ļ�" style="cursor: pointer;" src="images/small/i5.png"  onclick="insertInformat()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
		</tr>
	</table>
  </body>
</html>