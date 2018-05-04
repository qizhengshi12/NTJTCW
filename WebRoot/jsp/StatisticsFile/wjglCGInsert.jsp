<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<%@page import="com.safety.entity.Wjglxf"%>
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
    <title>编辑草稿</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=StatisticsFile/wjgl',//上传所处理的服务器
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
					form.action = "WjglServlet?action=insertWjglCG";
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
		function InsertFile(str){
			document.getElementById("fszt").value = str;
	   		var wjmc = document.getElementById("wjmc").value;
	   		var ry = document.getElementById("ry").value;
	   		var hfqx = document.getElementById("hfqx").value;
	   		if(ry!=""&&hfqx==""){
	   			alert("请设置回复期限");
	   			return;
	   		}
	   		if(ry==""&&hfqx!=""){
	   			alert("请选择回复人员");
	   			return;
	   		}
	   		if(wjmc==""){
	   			alert("请输入文件名称");
	   			return;
	   		}
			if(!dateCompareNow(hfqx)){
	    		alert("回复期限不能早于今天");
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
			form.action = "WjglServlet?action=insertWjglCG";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	form.target="_self";
			form.action = "WjglServlet?action=getWjglList";
			form.submit();
        }
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("wjlx").value=sel.options[sel.selectedIndex].value;
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
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Wjglxf wjglxf = (Wjglxf)request.getAttribute("wjglxf");
		%>
		<form id="form1" name="WjglXF" method="post">
		<input type="hidden" name="id" id="id" value="<%=wjglxf.getId() %>">
		<input type="hidden" name="FileUrl" id="FileUrl" value="<%=wjglxf.getFileUrl() %>">
		<input type="hidden" name="fszt" id="fszt" value="">
			<table  width="100%">
				<tr>
					<td>
					文件名称：<input name="wjmc" id="wjmc" type="text" value="<%=wjglxf.getWjmc() %>"/>
					文件类型：
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=wjglxf.getWjlx() %>"><%=wjglxf.getWjlx() %></option>
						<option value="财务">财务</option>
						<option value="审计">审计</option>
						<option value="统计">统计</option>
					</select>
					<input type="hidden" name="wjlx" id="wjlx" value="<%=wjglxf.getWjlx() %>">
					发文号：<input name="wjh" id="wjh" type="text" value="<%=wjglxf.getWjh() %>"/>
					</td>
				</tr>
				<tr>
					<td>
					需回复人员：
					<input name="ry" id="ry" type="text" value="<%=wjglxf.getHfry() %>" readonly="readonly" onclick="openSelectFree('ry','ryID')"/>
					<input name="ryID" id="ryID" type="hidden" value="<%=wjglxf.getHfryID() %>"/>
					<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('ry','ryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					回复期限：
					<input name="hfqx" id="hfqx" type="text" size="10" value="<% if(wjglxf.getHfqx()!=null){ %><%=wjglxf.getHfqx() %><%}%>" readonly/>
          			<input name="Button" class="button1" onclick="setDay(document.WjglXF.hfqx);" type="button" value="选择">
					&nbsp;&nbsp;&nbsp;&nbsp;<img width="45px" height="45px" align="middle" src="images/small/messages.png" >
					短信提醒：<input type="radio" name="SMSFlag" checked="checked" value="0">是
					<input type="radio" name="SMSFlag" value="1">否
					</td>
				</tr>
				<tr>
					<td>
					正文：
					<textarea id="gzyq" name="gzyq" cols="1000" rows="50"><%=wjglxf.getGzyq() %></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="gzyq"/>  
				    <ck:replace replace="gzyq" basePath="/NTJTCW/ckeditor"></ck:replace>
					</td>
				</tr>
				<tr>
					<td>
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(wjglxf.getFileUrl())){
				  		String[] fileStr = wjglxf.getFileUrl().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="WjglServlet?action=downLoadXF&URL=<%=fileStr[i]%>&id=<%=wjglxf.getId()%>" target="ifm"><%=fileStr[i]%></a>
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
		<table width="100%">
			<tr>
				<td align="center">
					<img alt="发送" width="45px" height="45px" title="发送" style="cursor: pointer;"  src="images/small/send.png" onclick="InsertFile(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="保存草稿" width="45px" height="45px" title="保存草稿" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="InsertFile(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
</html>



