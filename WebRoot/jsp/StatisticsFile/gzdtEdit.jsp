<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gzdt"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='../../login.jsp';</script>");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增修改工作动态</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=StatisticsFile/gzdt',//上传所处理的服务器
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
		        	var form = document.getElementById("form1");
					form.action = "GzdtServlet?action=gzdtSave";
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
		function InsertFile(str){
			document.getElementById("tjzt").value = str;
	   		var wjmc = document.getElementById("wjmc").value;
	   		if(wjmc==""){
	   			alert("请输入文件名称");
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
			form.action = "GzdtServlet?action=gzdtSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "GzdtServlet?action=getGzdt";
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
		
			DeleteFile("/StatisticsFile/gzdt/"+value);
		}
		function DeleteFile(path){
			var xmlhttp;    
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	var jsons = xmlhttp.responseText;
			    }
			  }
			var url = "HandleFileServlet?action=DeleteFile&path=" + path;
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
	</script>
	</head>
	<body>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Gzdt gzdt = (Gzdt)request.getAttribute("gzdt");
		%>
		<form id="form1" name="Gzdt" method="post" target="I2">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="gzdt_id" id="gzdt_id" <%if(gzdt.getId()!=0){%>value="<%=gzdt.getId()%>"<%}%> >
		<input type="hidden" name="FileUrl" id="FileUrl" <%if(gzdt.getFileUrl()!=null){%> value="<%=gzdt.getFileUrl()%>"<%}%>>
			<table  width="100%">
				<tr>
					<td align="center">
					名称：<input name="wjmc" id="wjmc" type="text" <%if(gzdt.getWjmc()!=null){%>value="<%=gzdt.getWjmc() %>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td>
					内容：
					<textarea id="wjnr" name="wjnr" cols="1000" rows="50"><%if(gzdt.getWjnr()!=null){%><%=gzdt.getWjnr() %><%}%></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="wjnr"/>  
				    <ck:replace replace="wjnr" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
				<tr>
					<td>
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(gzdt.getFileUrl())&&gzdt.getFileUrl()!=null){
				  		String[] fileStr = gzdt.getFileUrl().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="GzdtServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<img id="m<%=i%>" align="top" width="20px" height="20px" alt="删除" title="删除" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
						<%}%>
					<%}else{%>无
					<%}%>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
			<div id="fileQueue"></div>
	上传附件：（每个附件最大10M，最多上传8个附件）<input id="file_upload" name="file_upload" type="file">
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="提交" width="45px" height="45px" title="提交" style="cursor: pointer;"  src="images/small/send.png" onclick="InsertFile(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="存草稿" width="45px" height="45px" title="存草稿" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="InsertFile(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



