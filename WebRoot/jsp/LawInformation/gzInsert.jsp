<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='../../login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>��������</title>
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
			form.action = "GzServlet?action=saveGz";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "GzServlet?action=getGz";
			form.submit();
        }
	    </script>
	</head>
	<body>
		<%
			String fatherid = (String)request.getParameter("fatherid"); 
		%>
		<form id="form1" name="Gz" method="post">
		<input type="hidden" name="fatherid" id="fatherid" value="<%=fatherid%>">
			<table  width="100%">
				<tr>
					<td>
					���⣺<input name="bt" id="bt" type="text" value=""/>
					�ĺţ�<input name="wh" id="wh" type="text" value=""/>
					ʵʩ���ڣ�<input name="ssrq" id="ssrq" type="text" size="10" readonly/>
          <input name="Button" class="button1" onclick="setDay(document.Gz.ssrq);" type="button" value="ѡ��">
					������λ��<input name="fbdw" id="fbdw" type="text" value="" />
					</td>
				</tr>
				<tr>
					<td>
					<textarea id="GzTxt" name="GzTxt" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="GzTxt"/>  
				    <ck:replace replace="GzTxt" basePath="/NTJTCW/ckeditor"></ck:replace>
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
	</body>
</html>

