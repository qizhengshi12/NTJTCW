<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.ContentGlzd"%>  
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
    <title>�༭��λ�ƶ�</title>
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
			form.action = "GlzdServlet?action=saveGlzd";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "GlzdServlet?action=getGlzd";
			form.submit();
        }
	    </script>
	</head>
	<body>
		<%
			ContentGlzd Glzd = (ContentGlzd)request.getAttribute("Glzd"); 
		%>
		<form id="form1" name="Glzd" method="post"  target="I2">
		<input type="hidden" name="editGlzd_id" id="editGlzd_id" value="<%=Glzd.getId()%>">
		<input type="hidden" name="fatherid" id="fatherid" value="<%=Glzd.getFatherid()%>">
		<input type="hidden" name="FileUrl" id="FileUrl" value="<%=Glzd.getFileUrl()%>">
			<table  width="100%">
				<tr>
					<td>
					���⣺<input name="bt" id="bt" type="text" value="<%=Glzd.getBt()%>"/>
					�ĺţ�<input name="wh" id="wh" type="text" value="<%=Glzd.getWh()%>"/>
					ʵʩ���ڣ�<input name="ssrq" id="ssrq" type="text" value="<%=Glzd.getSsrq()%>" size="10" readonly/>
          <input name="Button" class="button1" onclick="setDay(document.Glzd.ssrq);" type="button" value="ѡ��">
					<input type="radio" name="sfgk"  <%if("0".equals(Glzd.getSfgk())){%>checked="checked"<%}%>  value="0">���⹫��
					<input type="radio" name="sfgk"  <%if("1".equals(Glzd.getSfgk())){%>checked="checked"<%}%>  value="1">��λ�ڲ�
					</td>
				</tr>
				<tr>
					<td>
					<textarea name="GlzdTxt" cols="1000" rows="50"><%=Glzd.getGlzdTxt()%></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="GlzdTxt"/>  
				    <ck:replace replace="GlzdTxt" basePath="/NTJTCW/ckeditor"></ck:replace>
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
