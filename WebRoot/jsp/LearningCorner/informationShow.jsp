<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.LearningCorner"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
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
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>ѧϰ԰��</title>
   <style type="text/css">
   img {border:0px;}
   </style>
	<script type="text/javascript">
	function custom_close(){
		window.open('','_self');
		window.close();
	}
	//����
	function setGood(id,pp,ppid,num){
   		var form = document.getElementById("form1");
		form.action = "LearningCornerServlet?action=setGood&id="+id+"&pp="+pp+"&ppid="+ppid+"&num="+num;
		form.submit();
	}
	</script>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
  </head>
  
  <body>
  <% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
  	<%
		LearningCorner learningCorner = (LearningCorner)request.getAttribute("learningCorner");
	%>
	
	<form method="post" id="form1" action="">
	</form>
	<table width="95%" align="center">
			<tr>
				<td valign="bottom" align="left" width="15%">
				<span style="background-color: #E0FFFF;">
				<%if("2".equals(learningCorner.getLx3())){%>
				������
				<%}else{%>
				��ͨ��
				<%}%>
				</span>
				</td>
				<td align="right" width="40%">
				<span style="font-weight: bold;font-size: 20px"><%=learningCorner.getBt() %></span>
				</td>
				<td valign="bottom" align="right" width="45%">
				<span style="background-color: #E0FFFF;">
				<%if("ѧϰ".equals(learningCorner.getLx1())){%>
				ѧϰ���
				<%}else if("����".equals(learningCorner.getLx1())){%>
				�������
				<%}else if("����".equals(learningCorner.getLx1())){%>
				������
				<%}%>
				</span>
				&nbsp;
				<span style="background-color: #E0FFFF;">
				<%if("����".equals(learningCorner.getLx2())){%>
				����
				<%}else{%>
				����
				<%}%>
				</span>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
			<%if(!"".equals(learningCorner.getPhotoURL())&&learningCorner.getPhotoURL()!=null){%>
				<td valign="top" align="center">
				<img src="UserFile/<%=learningCorner.getPhotoURL() %>" width="300" height='200' border='1'> 
				</td>
				<td valign="top" align="left" colspan="2">
					<%if(learningCorner.getNr()!=null){%><%=learningCorner.getNr() %><%}%>
				</td>
			<%}else{%>
				<td valign="top" align="left" colspan="3">
					<%if(learningCorner.getNr()!=null){%><%=learningCorner.getNr() %><%}%>
				</td>
			<%}%>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="right" colspan="3">
					<img width="24px" height="24px" align="middle" src="images/small/48.png">
					�����ˣ�<%=learningCorner.getTjr() %>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img width="30px" height="30px" align="middle" src="images/small/buildings.png">
					��λ��<%=learningCorner.getDw() %>
					&nbsp;&nbsp;&nbsp;
					<img width="24px" height="24px" align="middle" src="images/small/internet-history.png">
					����ʱ�䣺<%=learningCorner.getTjsj() %>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%if(!"".equals(learningCorner.getFileURL())&&learningCorner.getFileURL()!=null){%>
			<tr>
				<td colspan="3">
					��&nbsp;&nbsp;����
				  	<% if(!"".equals(learningCorner.getFileURL())&&learningCorner.getFileURL()!=null){
				  		String[] fileStr = learningCorner.getFileURL().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="LearningCornerServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>��
					<%}%>
				<iframe id='ifm' name='ifm' style="display:none"></iframe>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<%}%>
			<tr>
				<td align="left" colspan="3">
				<%if(learningCorner.getPeople()!=null&&!"".equals(learningCorner.getPeople())){ 
					if(learningCorner.getPeople().length()>8){%>
					<%=learningCorner.getPeople().substring(0,8)%>���˵����ˣ�
					<%}else{%>
					<%=learningCorner.getPeople()%>�����ˣ�
					<%}%>
				<%}%>
				&nbsp;&nbsp;<img  align="top" title="�����ˣ�<%=learningCorner.getPeople()%>" src="images/small/001_18.png">&nbsp;��������<%=learningCorner.getGood() %>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
				<%
					if(!username.equals(learningCorner.getTjrID())){
						String ppid = learningCorner.getPeopleID();
						String [] ppidList = null;
						boolean goodflag = true;
						if(!"".equals(ppid)){
							ppidList = ppid.split(";");
							for(int i=0; i<ppidList.length; i++){
								if(username.equals(ppidList[i])){
									goodflag=false;
									break;
								}
							}
						}
						if(goodflag){
				%>
					<img alt="��һ��" width="45px" height="45px" title="��һ��" style="cursor: pointer;"  src="images/small/dnz.png" onclick="setGood('<%=learningCorner.getId()%>','<%=learningCorner.getPeople()%>','<%=learningCorner.getPeopleID()%>','<%=learningCorner.getGood()%>')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					<%}else{%>
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;" src="images/small/thumb_up.png" onclick="javascript:return alert('���޹�');">
					<%}%>
				<%}%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="�ر�" width="45px" height="45px" title="�ر�" style="cursor: pointer;"  src="images/small/close.png" onclick="custom_close()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
