<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.PostInformation"%>  
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
    <title>������Ϣ</title>
	
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
            'uploader': 'HandleFileServlet?action=MultiFiles&path=PostInformation/file',//�ϴ�������ķ�����
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
		    	var form = document.getElementById("form2");
				form.action = "PostInformationServlet?action=informatSave";
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
   	var extArray = new Array(".jpg",".bmp",".png");
	function postPhoto(){
		var file = document.getElementById("file1").value;
		var allowSubmit = false;
        if (file==""){
        	alert("��ѡ���ϴ��ļ�");
            return;
        }
        while (file.indexOf("\\") != -1){
            file = file.slice(file.indexOf("\\") + 1);
        }
        var ext = file.slice(file.lastIndexOf(".")).toLowerCase();
        for (var i = 0; i < extArray.length; i++) {
            if (extArray[i] == ext){
                allowSubmit = true;
                break;
            }
        }
        if (allowSubmit){
        	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
        	var target = document.getElementById("file1");
        	var fileSize = 0;
        	if (isIE && !target.files) {      
            	var filePath = target.value;     
            	var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
				var file1 = fileSystem.GetFile(filePath);  
				fileSize = file1.size;     
			} else {     
				fileSize = target.files[0].size;   
			}
			var size = fileSize/1024;     
			if(size>3000){   
				alert("������С���ܴ���3M��");   
				return;
			}    
			if(size<=0){  
				alert("������С����Ϊ0M��");   
				return;
			}   
	   		var TempURL = document.getElementById("TempURL").value;
			if(TempURL!=""){
				DeletePhoto(TempURL);
			}else{
				savePhoto();
			}
        }else{ //����ϴ��ļ�����
            alert("ֻ���ϴ����¸�ʽ���ļ�:"+ (extArray.join("")) + "\n������ѡ�����ϴ�.");
        }
	}
	
    function callbackPhoto(msg){
       	if(msg!='1'){
       		document.getElementById("TempURL").value=msg;
       		document.getElementById("photo").src = "UserFile/"+msg;
       	}else{
       		alert("�ϴ���ͻ�����Ժ�����");
       		return;
       	}
   }
    function DeletePhoto(path){
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
		    	savePhoto();
		    }
		  }
		var url = "HandleFileServlet?action=DeleteFile&path="+path;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
   }
   function savePhoto(){
   		var form = document.getElementById("form3");
	    var path = "PostInformation/temp";
		form.action = "HandleFileServlet?action=HandlePhoto&path="+path;
		form.submit();
   }
    function cancel(){
    	var form = document.getElementById("form1");
		form.action = "PostInformationServlet?action=getInformationList";
		form.submit();
    }
    function InsertFile(){
   		var bt = document.getElementById("bt").value;
    	if(bt==""){
    		alert("����д��������");
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
    	var form = document.getElementById("form2");
		form.action = "PostInformationServlet?action=informatSave";
		form.submit();
    }
    
    function DeleteTemp(){
    	var path = document.getElementById("TempURL").value;
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
		    	cancel();
		    }
		  }
		var url = "HandleFileServlet?action=DeleteFile&path="+path;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
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
			DeleteFile("/PostInformation/file/"+value);
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
		PostInformation postInformation = (PostInformation)request.getAttribute("postInformation");
	%>
	
  <form method="post" id="form1" action=""></form>
  <form method="post" id="form2" action="">
  <input name="shr" id="shr" type="hidden" value="<%=postInformation.getShr() %>"/>
  <input name="shrID" id="shrID" type="hidden" value="<%=postInformation.getShrID() %>"/>
  <input type="hidden" name="FileUrl" id="FileUrl" <%if(postInformation.getFileURL()!=null){%> value="<%=postInformation.getFileURL() %>"<%}%>>
  <input type="hidden" name="PhotoURL" id="PhotoURL" <%if(postInformation.getPhotoURL()!=null){%> value="<%=postInformation.getPhotoURL() %>"<%}%>>
  <input type="hidden" name="TempURL" id="TempURL" value="">
  <input type="hidden" id ="Infor_id" name="Infor_id" <%if(postInformation.getId()!=0){%>value="<%=postInformation.getId() %>"<%}%>/>
	<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h2><img width="80px" height="80px" align="middle" src="images/small/picture.png">������Ϣ</h2>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					���⣺
				<input type="text" name="bt"  id="bt" <%if(postInformation.getBt()!=null){%> value="<%=postInformation.getBt() %>" <%}%> size="60"/>
				</td>
			</tr>
			<tr>
				<td align="left">
					���ݣ�
					<textarea name="nr" id="nr" rows="30" cols="40"><%if(postInformation.getNr()!=null){%><%=postInformation.getNr() %><%}%></textarea>
    				<ckf:setupCKEditor basePath="/NTJTCW/ckfinder/"  editor="nr"/>  
    				<ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr>
				<td>
				��&nbsp;&nbsp;����
			  	<% if(!"".equals(postInformation.getFileURL())&&postInformation.getFileURL()!=null){
			  		String[] fileStr = postInformation.getFileURL().split(";");
			  		for(int i=0; i<fileStr.length; i++){
			  	%>
			  		<br>
					<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="PostInformationServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
					<img id="m<%=i%>" align="top" width="20px" height="20px" alt="ɾ��" title="ɾ��" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
					<%}%>
				<%}else{%>��
				<%}%>
				</td>
			</tr>
		</table>
	</form>
	<form id="form3" method="post"  enctype="multipart/form-data" target="ifm1">
		<table width="100%">
			<tr>
				<td align="left">
					��ǰͼƬ��
					 <img <%if(postInformation.getPhotoURL()!=null){%>src="UserFile/<%=postInformation.getPhotoURL() %>" <%}%> id="photo" width="300" height='200' border='1'> 
				</td>
			</tr>
		
			<tr>
				<td align="left">
					�ϴ�ͼƬ������ʽΪJPG/BMP/PNG�����3M��
					<input type="file" value="" name="file1" id="file1"/>&nbsp;&nbsp;<input type="button" class="button1" value="�ϴ�Ԥ��" onclick="postPhoto()" />
				</td>
			</tr>
		</table>
		<iframe id='ifm1' name='ifm1' style="display:none"></iframe>
	</form>	
	
  	<div id="fileQueue"></div>
	�ϴ���������ÿ���������10M������ϴ�8��������<input id="file_upload" name="file_upload" type="file">
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table width="100%">
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				����ˣ�<%=postInformation.getShr() %>
				</td>
			</tr>
		</table>
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="InsertFile();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="DeleteTemp()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
