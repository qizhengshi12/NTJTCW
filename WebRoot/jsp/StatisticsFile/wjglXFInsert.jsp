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
    <title>�������ʼ�</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
   	<script src="calendar.js"></script>
	<!-- �ϴ��ļ� -->
	<LINK href="<%=basePath%>/css/uploadify.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/jquery.uploadify.js"></script> 
	<script type="text/javascript">
		$(document).ready(function() {
	       $('#file_upload').uploadify( {
	            'debug':false,
	            'queueSizeLimit': 8,
	            'swf': 'js/uploadify.swf',//�ϴ���ť��ͼƬ��Ĭ�������flash�ļ�
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=StatisticsFile/wjgl',//�ϴ�������ķ�����
	            'method': 'post',
	            'queueID' : 'fileQueue',//�ϴ���ʾ���������Ǹ�div
	            'buttonText' : '��ѡ���ļ�',
	           	'removeTimeout': 2,
	           	'fileSizeLimit': '10MB',
	            'progressData' : 'percentage',
	            'auto' : false,
	            'multi' : true,
				'onUploadSuccess' : function(file, data, response) {
					var FileUrl = document.getElementById("FileUrl").value;
					document.getElementById("FileUrl").value = FileUrl+data+";";
		        },
		        'onQueueComplete' : function(queueData) {
		        	var form = document.getElementById("form1");
					form.action = "WjglServlet?action=insertWjglXF";
					form.submit();
        		}
			});  
		});  
	  
		function doUplaod(){  
			$('#file_upload').uploadify('upload','*');  
		}  
		function closeLoad(){  
			$('#file_upload').uploadify('cancel','*');  
		}  

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
		function InsertFile(str){
			document.getElementById("fszt").value = str;
	   		var wjmc = document.getElementById("wjmc").value;
	   		var ry = document.getElementById("ry").value;
	   		var hfqx = document.getElementById("hfqx").value;
	   		if(ry!=""&&hfqx==""){
	   			alert("�����ûظ�����");
	   			return;
	   		}
	   		if(ry==""&&hfqx!=""){
	   			alert("��ѡ��ظ���Ա");
	   			return;
	   		}
	   		if(wjmc==""){
	   			alert("�������ļ�����");
	   			return;
	   		}
			if(!dateCompareNow(hfqx)){
	    		alert("�ظ����޲������ڽ���");
				return;
	    	}
        	var str3 = document.getElementById("fileQueue").innerHTML;
        	if(str3!=""){
	    		doUplaod();
	    	}else{
	    		Save();
			}
	    }
        function Save(){
        	var form = document.getElementById("form1");
			form.action = "WjglServlet?action=insertWjglXF";
			form.submit();
        }
        function cancelthis(){
        	var form = document.getElementById("form2");
        	form.target="_self";
			form.action = "WjglServlet?action=getWjglList";
			form.submit();
        }
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("wjlx").value=sel.options[sel.selectedIndex].value;
		}
	</script>
	</head>
	<body>
		<form id="form1" name="WjglXF" method="post" action="">
		<input type="hidden" name="FileUrl" id="FileUrl" value="">
		<input type="hidden" name="fszt" id="fszt" value="">
			<table  width="100%">
				<tr>
					<td>
					�ļ����ƣ�<input name="wjmc" id="wjmc" type="text" value=""/>
					�ļ����ͣ�
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value=""></option>
						<option value="����">����</option>
						<option value="���">���</option>
						<option value="ͳ��">ͳ��</option>
					</select>
					<input type="hidden" name="wjlx" id="wjlx" value="">
					���ĺţ�<input name="wjh" id="wjh" type="text" value=""/>
					</td>
				</tr>
				<tr>
					<td>
					��ظ���Ա��
					<input name="ry" id="ry" type="text" value="" readonly="readonly" onclick="openSelectFree('ry','ryID')"/>
					<input name="ryID" id="ryID" type="hidden" value=""/>
					<img align="middle" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					�ظ����ޣ�
					<input name="hfqx" id="hfqx" type="text" size="10" readonly/>
          			<input name="Button" class="button1" onclick="setDay(document.WjglXF.hfqx);" type="button" value="ѡ��">
					&nbsp;&nbsp;&nbsp;&nbsp;<img width="45px" height="45px" align="middle" src="images/small/messages.png" >
					�������ѣ�<input type="radio" name="SMSFlag" checked="checked" value="0">��
					<input type="radio" name="SMSFlag" value="1">��
					</td>
				</tr>
				<tr>
					<td>
					���ģ�
					<textarea id="gzyq" name="gzyq" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="gzyq"/>  
				    <ck:replace replace="gzyq" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post" action=""></form>
  	<div id="fileQueue"></div>
	�ϴ���������ÿ���������10M������ϴ�8��������<input id="file_upload" name="file_upload" type="file">
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="InsertFile(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����ݸ�" width="45px" height="45px" title="����ݸ�" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="InsertFile(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="ȡ��" width="45px" height="45px" title="ȡ��" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancelthis()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



