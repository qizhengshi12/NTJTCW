<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.SendEmail"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�༭�ʼ�</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=SendEmail',//�ϴ�������ķ�����
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
		        	var fszt = document.getElementById("fszt").value;
		        	var form = document.getElementById("form1");
					form.action = "EmailServlet?action=insertDraftEmail&fszt="+fszt;
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
		function ReSetP(){
        	document.getElementById("jsr").value="";
        	document.getElementById("jsrID").value="";
		}
		function ReSetP1(){
        	document.getElementById("msr").value="";
        	document.getElementById("msrID").value="";
		}
		function send(str){
        	var str1 = document.getElementById("jsrID").value;
        	var str2 = document.getElementById("nr").value;
			if(str=="1"){
	        	if(str1==""){
	        		alert("��ѡ���ռ���");
	        		return;
	        	}
	        	if(str2.length>2000){
	        		alert("�ʼ����ݹ��������Ϊ�����ϴ�");
	        		return;
        		}
			}
        	document.getElementById("fszt").value = str;
        	var str3 = document.getElementById("fileQueue").innerHTML;
        	if(str3!=""){
        		doUplaod();
        	}else{
        		save(str);
        	}
		}
		function save(str){
        	var form = document.getElementById("form1");
			form.action = "EmailServlet?action=insertDraftEmail&fszt="+str;
			form.submit();
		}
		function delFile(num,value){
			var obj1 = document.getElementById("d"+num);
			var obj2 = document.getElementById("m"+num);
			obj1.parentElement.removeChild(obj1); 
			obj2.parentElement.removeChild(obj2);
			var FileUrl = document.getElementById("FileUrl").value;
			FileUrl = FileUrl.replace(value+";","");
			FileUrl = FileUrl.replace(value,"");
			document.getElementById("FileUrl").value = FileUrl;
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
		SendEmail sendEmail = (SendEmail)request.getAttribute("sendEmail"); 
	%>
  <form method="post" id="form1" action="" name="Email">
	<input type="hidden" name="id" id="id" value="<%=sendEmail.getId()%>">
	<input type="hidden" name="FileUrl" id="FileUrl" value="<%=sendEmail.getFileUrl()%>">
	<input type="hidden" name="fszt" id="fszt" value="1">
	<table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=NewEmail'><span style="font-size: 16px">���ʼ�</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px">�ռ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">������</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">�ݸ���</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">����վ</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="right" width="80px">
			�ռ��ˣ�
			</td>
			<td align="left">
				<textarea id="jsr" name="jsr" cols="50" rows="1" onclick="openSelectFree('jsr','jsrID')" readonly="readonly"><%=sendEmail.getJsr()%></textarea>
				<input name="jsrID" id="jsrID" type="hidden" value="<%=sendEmail.getJsrID()%>"/>
				<img align="bottom" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('jsr','jsrID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="bottom" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td align="right" width="80px">
			�����ˣ�
			</td>
			<td align="left">
				<textarea id="msr" name="msr" cols="50" rows="1" onclick="openSelectFree('msr','msrID')" readonly="readonly"><%=sendEmail.getMsr()%></textarea>
				<input name="msrID" id="msrID" type="hidden" value="<%=sendEmail.getMsrID()%>"/>
				<img align="bottom" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('msr','msrID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="bottom" width="40px" height="40px" alt="�����Ա" title="�����Ա" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP1()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td align="right">
			��&nbsp;&nbsp;�⣺
			</td>
			<td align="left">
				<input type="text" name="bt" id="bt" size="45" value="<%=sendEmail.getBt()%>"/>
			</td>
		</tr>
		<tr>
			<td align="right">
			��&nbsp;&nbsp;�ݣ�
			</td>
			<td align="left">
			<textarea id="nr" name="nr" cols="1000" rows="50"><%=sendEmail.getNr()%></textarea>
		    <ckf:setupCKEditor basePath="/NTJTCW/ckfinder/" editor="nr"/>  
		    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
			</td>
		</tr>
		<tr>
			<td align="right">
			��&nbsp;&nbsp;����
			</td>
			<td align="left">
		  	<% if(!"".equals(sendEmail.getFileUrl())){
		  		String[] fileStr = sendEmail.getFileUrl().split(";");
		  		for(int i=0; i<fileStr.length; i++){
		  	%>
		  		<br>
				<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="EmailServlet?action=downLoadEd&URL=<%=fileStr[i]%>&id=<%=sendEmail.getId()%>" target="ifm"><%=fileStr[i]%></a>
				<img id="m<%=i%>" align="top" width="20px" height="20px" alt="ɾ��" title="ɾ��" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
				<%}%>
			<%}else{%>��
			<%}%>
			</td>
		</tr>
	</table>
  </form>
  <iframe id='ifm' name='ifm' style="display:none"></iframe>
  <form method="post" id="form2" action="" target="_black"></form>
  	<div id="fileQueue"></div>
	�ϴ���������ÿ���������10M������ϴ�8��������<input id="file_upload" name="file_upload" type="file">
	<table width="100%">
		<tr>
			<td align="center">
				<img width="80px" height="80px" alt="����" title="����" style="cursor: pointer;" src="images/small/mail_send.png" onclick="send(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="80px" height="65px" alt="��ݸ�" title="��ݸ�" style="cursor: pointer;" src="images/small/mail.png" onclick="send(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
  </body>
</html>
