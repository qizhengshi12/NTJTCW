<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Jhzj"%>
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
    <title>�����޸ļƻ��ܽ�</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=StatisticsFile/jhzj',//�ϴ�������ķ�����
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
					form.action = "JhzjServlet?action=jhzjSave";
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
	   		var jhmc = document.getElementById("jhmc").value;
	   		var jhsj1 = document.getElementById("jhsj1").value;
	   		var jhsj2 = document.getElementById("jhsj2").value;
	   		if(jhmc==""){
	   			alert("����������");
	   			return;
	   		}
	   		if(jhsj1==""){
	   			alert("�����뿪ʼʱ��");
	   			return;
	   		}
	   		if(jhsj2==""){
	   			alert("���������ʱ��");
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
			form.action = "JhzjServlet?action=jhzjSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form1");
			form.action = "JhzjServlet?action=getJhzj";
			form.submit();
        }
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("jhlx1").value=sel.options[sel.selectedIndex].value;
		}
		function selChange2(){
			var sel = document.getElementById("sel2");
			document.getElementById("jhlx2").value=sel.options[sel.selectedIndex].value;
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
			DeleteFile("/StatisticsFile/jhzj/"+value);
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Jhzj jhzj = (Jhzj)request.getAttribute("jhzj");
		%>
		<form id="form1" name="JHZJ" method="post" target="I2">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="jhzj_id" id="jhzj_id" <%if(jhzj.getId()!=0){%>value="<%=jhzj.getId()%>"<%}%> >
		<input type="hidden" name="FileUrl" id="FileUrl" <%if(jhzj.getFileUrl()!=null){%> value="<%=jhzj.getFileUrl()%>"<%}%>>
			<table  width="100%">
				<tr>
					<td>
					���ƣ�<input name="jhmc" id="jhmc" type="text" <%if(jhzj.getJhmc()!=null){%>value="<%=jhzj.getJhmc() %>"<%}%>/>
					����1��
					<select id="sel1" onchange="selChange1()" class="STYLE1">
					<%if(jhzj.getJhlx1()!=null){%>
						<option value="<%=jhzj.getJhlx1() %>"><%=jhzj.getJhlx1() %></option>
					<%}%>
						<option value="�ƻ�">�ƻ�</option>
						<option value="�ܽ�">�ܽ�</option>
					</select>
					<input type="hidden" name="jhlx1" id="jhlx1" <%if(jhzj.getJhlx1()!=null){%>value="<%=jhzj.getJhlx1() %>"<%}else{%>value="�ƻ�"<%}%>>
					����2��
					<select id="sel2" onchange="selChange2()" class="STYLE1">
					<%if(jhzj.getJhlx2()!=null){%>
						<option value="<%=jhzj.getJhlx2() %>"><%=jhzj.getJhlx2() %></option>
					<%}%>
						<option value="����">����</option>
						<option value="���">���</option>
					</select>
					<input type="hidden" name="jhlx2" id="jhlx2" <%if(jhzj.getJhlx2()!=null){%>value="<%=jhzj.getJhlx2() %>"<%}else{%>value="����"<%}%>>
					��ʼʱ�䣺<input name="jhsj1" id="jhsj1" type="text" size="10" <%if(jhzj.getJhsj1()!=null){%>value="<%=jhzj.getJhsj1() %>"<%}%> readonly/>
          <input name="Button" class="button1" onclick="setDay(document.JHZJ.jhsj1);" type="button" value="ѡ��">
					����ʱ�䣺<input name="jhsj2" id="jhsj2" type="text" size="10" <%if(jhzj.getJhsj2()!=null){%>value="<%=jhzj.getJhsj2() %>"<%}%> readonly/>
          <input name="Button" class="button1" onclick="setDay(document.JHZJ.jhsj2);" type="button" value="ѡ��">
					</td>
				</tr>
				<tr>
					<td>
					���ݣ�
					<textarea id="jhnr" name="jhnr" cols="1000" rows="50"><%if(jhzj.getJhnr()!=null){%><%=jhzj.getJhnr() %><%}%></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="jhnr"/>  
				    <ck:replace replace="jhnr" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
				<tr>
					<td>
					��&nbsp;&nbsp;����
				  	<% if(!"".equals(jhzj.getFileUrl())&&jhzj.getFileUrl()!=null){
				  		String[] fileStr = jhzj.getFileUrl().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="JhzjServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<img id="m<%=i%>" align="top" width="20px" height="20px" alt="ɾ��" title="ɾ��" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
						<%}%>
					<%}else{%>��
					<%}%>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
	
  	<div id="fileQueue"></div>
	�ϴ���������ÿ���������10M������ϴ�8��������<input id="file_upload" name="file_upload" type="file">
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="�ύ" width="45px" height="45px" title="�ύ" style="cursor: pointer;"  src="images/small/send.png" onclick="InsertFile(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="��ݸ�" width="45px" height="45px" title="��ݸ�" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="InsertFile(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



