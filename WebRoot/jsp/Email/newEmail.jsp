<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发送新邮件</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
   	<script src="calendar.js"></script>
	<!-- 上传文件 -->
	<LINK href="<%=basePath%>/css/uploadify.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/js/jquery.uploadify.js"></script> 
	<script type="text/javascript">
		 $(document).ready(function() {
	        $('#file_upload').uploadify( {
	            'debug':false,
	            'queueSizeLimit': 8,
	            'swf': 'js/uploadify.swf',//上传按钮的图片，默认是这个flash文件
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=SendEmail',//上传所处理的服务器
	            'method': 'post',
	            'queueID' : 'fileQueue',//上传显示进度条的那个div
	            'buttonText' : '请选择文件',
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
					form.action = "EmailServlet?action=insertEmail&fszt="+fszt;
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
			var iWidth = 850;//弹出窗口的宽度;
			var iHeight = 450;//弹出窗口的高度;
			var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;//获得窗口的水平位置;var form = document.getElementById("form2");
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
	        		alert("请选择收件人");
	        		return;
	        	}
	        	if(str2.length>2000){
	        		alert("邮件内容过长，请改为附件上传");
	        		return;
        		}
			}
        	var str3 = document.getElementById("fileQueue").innerHTML;
        	document.getElementById("fszt").value = str;
        	if(str3!=""){
        		doUplaod();
        	}else{
        		save(str);
        	}
		}
		function save(str){
        	var form = document.getElementById("form1");
			form.action = "EmailServlet?action=insertEmail&fszt="+str;
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
  <form method="post" id="form1" action="" name="Email">
	<input type="hidden" name="FileUrl" id="FileUrl" value="">
	<input type="hidden" name="fszt" id="fszt" value="1">
	<table width="100%">
		<tr>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img  alt="选中" align="middle" src="images/small/favorites.png"><a href='EmailServlet?action=NewEmail'><span style="font-size: 16px;border:1px; background:#C1FFB2;">新邮件</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyEmail&flag=1'><span style="font-size: 16px">收件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=AllSendEmail&flag=1'><span style="font-size: 16px">发件箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=DraftEmail&flag=1'><span style="font-size: 16px">草稿箱</span></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='EmailServlet?action=MyDelEmail&flag=1'><span style="font-size: 16px">回收站</span></a>
			</td>
		</tr>
		<tr >
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="right" width="80px">
			收件人：
			</td>
			<td align="left">
				<textarea id="jsr" name="jsr" cols="50" rows="1" onclick="openSelectFree('jsr','jsrID')" readonly="readonly"></textarea>
				<input name="jsrID" id="jsrID" type="hidden" value=""/>
				<img align="bottom" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('jsr','jsrID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="bottom" width="40px" height="40px" alt="清空人员" title="清空人员" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td align="right" width="80px">
			密送人：
			</td>
			<td align="left">
				<textarea id="msr" name="msr" cols="50" rows="1" onclick="openSelectFree('msr','msrID')" readonly="readonly"></textarea>
				<input name="msrID" id="msrID" type="hidden" value=""/>
				<img align="bottom" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('msr','msrID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="bottom" width="40px" height="40px" alt="清空人员" title="清空人员" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP1()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td align="right">
			主&nbsp;&nbsp;题：
			</td>
			<td align="left">
				<input type="text" name="bt" id="bt" size="45" value="无标题"/>
			</td>
		</tr>
		<tr>
			<td align="right">
			内&nbsp;&nbsp;容：
			</td>
			<td align="left">
			<textarea id="nr" name="nr" cols="1000" rows="50"></textarea>
		    <ckf:setupCKEditor basePath="/NTJTCW/ckfinder/" editor="nr"/>  
		    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
			</td>
		</tr>
	</table>
  </form>
  <form method="post" id="form2" action="" target="_black"></form>
  	<div id="fileQueue"></div>
	上传附件：（每个附件最大10M，最多上传8个附件）<input id="file_upload" name="file_upload" type="file">
	<table  width="100%">
		<tr>
			<td align="center">
				<img width="80px" height="80px" alt="发送" title="发送" style="cursor: pointer;" src="images/small/mail_send.png" onclick="send(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img width="80px" height="65px" alt="存草稿" title="存草稿" style="cursor: pointer;" src="images/small/mail.png" onclick="send(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	</table>
  </body>
</html>
