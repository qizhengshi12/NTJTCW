<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.Bbdzmb"%>  
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
    ArrayList BbdzmbList = (ArrayList)request.getAttribute("BbdzmbList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�����ļ��б�</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <script src="calendar.js"></script>
    <script type="text/javascript">
   	function queryBbdzmbByBt(){
   		var cxbt = document.getElementById("cxbt").value;
   		var cxlx = document.getElementById("cxlx").value;
		var srbt = "";
   		if(cxbt!=""){
   			srbt = " where bt  like '%25"+cxbt+"%25'";
   		}
   		if(cxlx!=""){
   			if(srbt!=""){
   				srbt = srbt +" and lx ='"+cxlx+"'";
   			}else{
   				srbt = " where lx ='"+cxlx+"'";
   			}
   		}
   		var form = document.getElementById("form2");
		form.action = "BbdzServlet?action=getBbdzmb&srbt=" + srbt;
		form.submit();
   	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("cxlx").value=sel.options[sel.selectedIndex].value;
	}
	function selChange2(){
		var sel = document.getElementById("sel2");
		document.getElementById("lx").value=sel.options[sel.selectedIndex].value;
	}
    function insertBbdzmb(){
   		var lx = document.getElementById("lx").value;
   		var dzls = document.getElementById("dzls").value;
   		var re = /^\d+$/;   //�ж��ַ����Ƿ�Ϊ������
		if (!re.test(dzls)){
			alert("��������������������");
			return;
		}
   		var form = document.getElementById("form1");
   		form.target="_self";
		form.action = "BbdzServlet?action=editBbdzmb&lx="+lx+"&dzls="+dzls;
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
		form.action = "BbdzServlet?action=getBbdzmb&srbt="+srbt+"&page_no=" + v;
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
		String cxlx = (String)request.getAttribute("cxlx"); 
		if(cxlx==null)cxlx="";
	%>
	
  <form method="post" id="form1" action="">
  </form>
  <form method="post" id="form2" name="Bbdz">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%" >
		<tr>
			<td align="center">
				����ģ�����ƣ�<input type=text name="cxbt" id="cxbt" size="20" class="STYLE1" value="<%=cxbt %>"/>
				���ͣ�<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=cxlx %>">
						<%if("cwbb".equals(cxlx)){%>
						����
						<%}else if("sjbb".equals(cxlx)){%>
						���
						<%}else if("tjbb".equals(cxlx)){%>
						ͳ��
						<%}else{ %>
						&nbsp;
						<%} %>
						</option>
						<option value="cwbb">����</option>
						<option value="sjbb">���</option>
						<option value="tjbb">ͳ��</option>
					  </select>
				<input type=hidden name="cxlx" id="cxlx" value="<%=cxlx %>"/>&nbsp; 
				<%if(UserPer.getBbdz().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="��ѯ" title="��ѯ" style="cursor: pointer;" src="images/small/find.png" onclick="queryBbdzmbByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
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
			<td align="center" width="25%" class="STYLE1">
				����ģ������
			</td>
			<td align="center" width="5%" class="STYLE1">
				����
			</td>
			<td align="center" width="15%" class="STYLE1">
				�Ʊ�λ
			</td>
			<td align="center" width="10%" class="STYLE1">
				�Ʊ���
			</td>
			<td align="center" width="15%" class="STYLE1">
				����
			</td>
		</tr>
		<%
         	/*����ÿ������Ϣ������ʾ*/
			for(int i=0;i< BbdzmbList.size();i++){
				Bbdzmb bbdzmb = (Bbdzmb)BbdzmbList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center">
				<%if(BbdzmbList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getBt()!=null&&!"".equals(bbdzmb.getBt())){%>
				<a href="BbdzServlet?action=showBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>" target="_blank"><%=bbdzmb.getBt()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("cwbb".equals(bbdzmb.getLx())){%>
				����
				<%}else if("sjbb".equals(bbdzmb.getLx())){%>
				���
				<%}else if("tjbb".equals(bbdzmb.getLx())){%>
				ͳ��
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getCzrdw()!=null&&!"".equals(bbdzmb.getCzrdw())){%>
				<%=bbdzmb.getCzrdw()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(bbdzmb.getCzr()!=null&&!"".equals(bbdzmb.getCzr())){%>
				<%=bbdzmb.getCzr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if("����Ա".equals(roles)||((username.equals(bbdzmb.getCzrID())&&UserPer.getBbdz().indexOf("3")!=-1))){%>
					<a href="BbdzServlet?action=editBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>&lx=<%=bbdzmb.getLx()%>"><img  alt="�༭" title="�༭" src="images/small/options.png"></a>
				<%} %>
				<%if("����Ա".equals(roles)||((username.equals(bbdzmb.getCzrID())&&UserPer.getBbdz().indexOf("4")!=-1))){%>
					<a href="BbdzServlet?action=deleteBbdzmb&id=<%=bbdzmb.getId()%>&dzls=<%=bbdzmb.getDzls()%>&lx=<%=bbdzmb.getLx()%>" onclick="javascript:return confirm('ȷ��ɾ���˼�¼��');"><img  alt="ɾ��" title="ɾ��" src="images/small/burn.png"></a>
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
	<%if(UserPer.getBbdz().indexOf("2")!=-1){%>
	<table width="100%"  align="center">
		<tr>
			<td width="50%"  align="right">
			���ͣ�<select id="sel2" onchange="selChange2()" class="STYLE1">
					<option value="<%=cxlx %>">
					<%if("cwbb".equals(cxlx)){%>
					����
					<%}else if("sjbb".equals(cxlx)){%>
					���
					<%}else if("tjbb".equals(cxlx)){%>
					ͳ��
					<%}else{ %>
					&nbsp;
					<%} %>
					</option>
					<option value="cwbb">����</option>
					<option value="sjbb">���</option>
					<option value="tjbb">ͳ��</option>
				  </select>
				<input type=hidden name="lx" id="lx" value="<%=cxlx %>"/>&nbsp; 
				��������<input type="text" size="6" name="dzls" id="dzls" value=""/>
			</td>
			<td width="50%"  align="left">
				<img alt="��������ģ��" width="100px" height="60px" title="��������ģ��" style="cursor: pointer;" src="images/small/i13.png"  onclick="insertBbdzmb()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			</td>
		</tr>
	</table>
	<%} } %>
  </body>
</html>
