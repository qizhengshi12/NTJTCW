<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.ContentGfxwj"%>  
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�༭�淶���ļ�</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function Save(){
        	var bt = document.getElementById("bt").value;
			var ssrq = document.getElementById("ssrq").value;
			bt = bt.replace(/\ /g,"");
        	if(bt==""){
        		alert("��������⣡");
        		return;
        	}
        	if(ssrq==""){
        		alert("������ʵʩ���ڣ�");
        		return;
        	}
        	var form = document.getElementById("form1");
			form.action = "GfxwjServlet?action=saveGfxwj";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "GfxwjServlet?action=getGfxwj";
			form.submit();
        }
	    </script>
	</head>
	<body>
		<%
			ContentGfxwj Gfxwj = (ContentGfxwj)request.getAttribute("Gfxwj"); 
		%>
		<form id="form1" name="Gfxwj" method="post"  target="I2">
		<input type="hidden" name="editGfxwj_id" id="editGfxwj_id" value="<%=Gfxwj.getId()%>">
		<input type="hidden" name="fatherid" id="fatherid" value="<%=Gfxwj.getFatherid()%>">
		<input type="hidden" name="FileUrl" id="FileUrl" value="<%=Gfxwj.getFileUrl()%>">
			<table  width="100%">
				<tr>
					<td>
					���⣺<input name="bt" id="bt" type="text" value="<%=Gfxwj.getBt()%>"/>
					�ĺţ�<input name="wh" id="wh" type="text" value="<%=Gfxwj.getWh()%>"/>
					ʵʩ���ڣ�<input name="ssrq" id="ssrq" type="text" value="<%=Gfxwj.getSsrq()%>" size="10" readonly/>
          <input name="Button" class="button1" onclick="setDay(document.Gfxwj.ssrq);" type="button" value="ѡ��">
					������λ��<input name="fbdw" id="fbdw" type="text" value="<%=Gfxwj.getFbdw()%>" />
					</td>
				</tr>
				<tr>
					<td>
					<textarea name="GfxwjTxt" cols="1000" rows="50"><%=Gfxwj.getGfxwjTxt()%></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="GfxwjTxt"/>  
				    <ck:replace replace="GfxwjTxt" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
			</table>
		</form>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="�ύ" width="45px" height="45px" title="�ύ" style="cursor: pointer;"  src="images/small/send.png" onclick="Save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<%} %>
	</body>
</html>

