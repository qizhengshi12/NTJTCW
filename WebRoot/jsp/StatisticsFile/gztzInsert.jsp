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
    <title>��������֪ͨ</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function openSelectFree(name,id){
        	var form = document.getElementById("form2");
			var iWidth = 850;//�������ڵĿ��;
			var iHeight = 450;//�������ڵĸ߶�;
			var iTop = (window.screen.availHeight-30-iHeight)/2;//��ô��ڵĴ�ֱλ��;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;//��ô��ڵ�ˮƽλ��;var form = document.getElementById("form2");
        	form.target="preview_page";
			var popupWin = window.open('', 'preview_page','height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
			form.action = "ZzxxServlet?action=getThreeSelectFree&name="+name+"&id="+id;
			form.submit();
		}
		function save(){
	   		var tzmc = document.getElementById("tzmc").value;
	   		var tzsj = document.getElementById("tzsj").value;
	   		var ry = document.getElementById("ry").value;
	   		if(tzsj==""){
	   			alert("������֪ͨʱ��");
	   			return;
	   		}
	   		if(ry==""){
	   			alert("��ѡ��֪ͨ��Ա");
	   			return;
	   		}
	   		if(tzmc==""){
	   			alert("������֪ͨ����");
	   			return;
	   		}
			if(!dateTimeCompareNow(tzsj)){
	    		alert("֪ͨʱ�䲻����������");
				return;
	    	}
        	var form = document.getElementById("form1");
			form.action = "GztzServlet?action=insertGztzXF";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	form.target="_self";
			form.action = "GztzServlet?action=getGztzXFList&flag=1";
			form.submit();
        }
	</script>
	</head>
	<body>
		<form id="form1" name="Gztz" method="post">
			<table  width="100%">
				<tr>
					<td>
					֪ͨ���ƣ�<input name="tzmc" id="tzmc" type="text" value=""/>
					����ص㣺<input name="tzdd" id="tzdd" type="text" value=""/>
					</td>
				</tr>
				<tr>
					<td>
					������Ա��<input name="ry" id="ry" type="text" value="" onclick="openSelectFree('ry','ryID')" readonly="readonly"/>
					<input name="ryID" id="ryID" type="hidden" value=""/>
					<img align="middle" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					֪ͨʱ�䣺<input name="tzsj" id="tzsj" type="text" size="15" readonly/>
          			<input name="Button" class="button1" onclick="setDayHM(document.Gztz.tzsj);" type="button" value="ѡ��">
					&nbsp;&nbsp;&nbsp;&nbsp;<img width="45px" height="45px" align="middle" src="images/small/messages.png" >
					�������ѣ�<input type="radio" name="SMSFlag" checked="checked" value="0">��
					<input type="radio" name="SMSFlag" value="1">��
					</td>
				</tr>
				<tr>
					<td>
					֪ͨ���ݣ�
					<textarea id="tznr" name="tznr" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="tznr"/>  
				    <ck:replace replace="tznr" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2" method="post"></form>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



