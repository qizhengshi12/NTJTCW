<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Wjglxf"%>
<%@page import="com.safety.entity.Wjglhf"%>
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
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
    <title>查看下发文件</title>
	<script type="text/javascript" src="<%=path %>/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=path %>/ckfinder/ckfinder.js"></script>
   
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=StatisticsFile/wjglhf',//上传所处理的服务器
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
		        	var form = document.getElementById("form2");
					form.action = "WjglServlet?action=saveWjglHF";
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

    	function insertHF(){
    		var str = document.getElementById("fileQueue").innerHTML;
        	if(str!=""){
	    		doUplaod();
	    	}else{
	    		Save();
			}
	    }
        function Save(){
        	var form = document.getElementById("form2");
			form.action = "WjglServlet?action=saveWjglHF";
			form.submit();
        }
		function cancel(){
			window.open('','_self');
			window.close();
		}
    </script>
	</head>
	<body>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	Wjglxf wjglxf = (Wjglxf)request.getAttribute("wjglxf");
         	Wjglhf wjglhf = (Wjglhf)request.getAttribute("wjglhf");
         	ArrayList wjglhfList = (ArrayList)request.getAttribute("wjglhfList");
         	String flag = (String)request.getAttribute("flag");
		%>
  <iframe id='ifm' name='ifm' style="display:none"></iframe>
		<table width="75%" align="center">
			<tr>
				<td colspan="3" align="center">
				<span style="font-size: 25px">公文信息</span>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="left" width="33%">
				文件名称：<%=wjglxf.getWjmc() %>
				</td>
				<td align="left" width="34%">
				文件类型：<%=wjglxf.getWjlx() %>
				</td>
				<td align="left" width="33%">
				发文号：<%=wjglxf.getWjh() %>
				</td>
			</tr>
			<tr>
				<td align="left">
				发起人：<%=wjglxf.getFqr() %>
				</td>
				<td align="left">
				发文单位：<%=wjglxf.getFwdw() %>
				</td>
				<td align="left">
				发文时间：<%=wjglxf.getFqsj().toString().substring(0,16) %>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="3" valign="top">
				正文:
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<textarea id="nr" name="nr" cols="1000" rows="50"><%=wjglxf.getGzyq() %></textarea>
				    <ckf:setupCKEditor basePath="/NTJTCW/ckfinder/" editor="nr"/>  
				    <ck:replace replace="nr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="3">
				附&nbsp;&nbsp;件：
			  	<% if(!"".equals(wjglxf.getFileUrl())){
			  		String[] fileStr = wjglxf.getFileUrl().split(";");
			  		for(int i=0; i<fileStr.length; i++){
			  	%>
			  		<br>
					<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="WjglServlet?action=downLoadXF&URL=<%=fileStr[i]%>&id=<%=wjglxf.getId()%>" target="ifm"><%=fileStr[i]%></a>
					<%}%>
				<%}else{%>无
				<%}%>
				</td>
			</tr>
			<tr >
				<td colspan="3">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<%if("0".equals(flag)){%>
		<table width="75%" align="center">
			<tr>
				<td align="center">
				<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<% }%>
		<%if("1".equals(flag)){%>
		<table width="75%" align="center">
			<tr class="tr1">
				<td align="center" width="10%">
					序号
				</td>
				<td align="center" width="30%">
					单位
				</td>
				<td align="center" width="10%">
					回复人
				</td>
				<td align="center" width="20%">
					回复时间
				</td>
				<td align="center" width="10%">
					是否超时
				</td>
				<td align="center" width="20%">
					操作
				</td>
			</tr>
				<%
         	//从request域中取得要显示的某页信息
         	int count = wjglhfList.size();
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< wjglhfList.size();i++){
           	Wjglhf wjglhfqk = (Wjglhf)wjglhfList.get(i);
		%>
			<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
				<td align="center">
					<%if(wjglhfList.size()!=0){%>
					<%=i+1%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(wjglhfqk.getCompany()!=null&&!"".equals(wjglhfqk.getCompany())){%>
					<%=wjglhfqk.getCompany()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(wjglhfqk.getHfr()!=null&&!"".equals(wjglhfqk.getHfr())){%>
					<%=wjglhfqk.getHfr()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(wjglhfqk.getHfsj()!=null&&!"".equals(wjglhfqk.getHfsj())){%>
					<%=wjglhfqk.getHfsj()%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if(wjglhfqk.getSfcs()!=null&&!"".equals(wjglhfqk.getSfcs())){%>
					<%if("超时".equals(wjglhfqk.getSfcs())) {%><img align="middle"  alt="超时" title="超时" src="images/small/wait-red.png"><span style="color: red;">超时</span><%}else {%><img align="middle"  alt="未超时" title="未超时" src="images/small/wait-green.png">未超时<%}%>
					<%}else{ %>
					&nbsp;
					<%} %>
				</td>
				<td align="center">
					<%if("已回复".equals(wjglhfqk.getHfzt())){%>
					<a href="WjglServlet?action=showWjglHF&hfid=<%=wjglhfqk.getId()%>"  target="_blank">已回复</a>
					<%}else{ %>
					未回复
					<%} %>
				</td>
			</tr>
			<% }%>
		</table>
		<table width="75%" align="center">
			<tr>
				<td align="center">
				<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<% }%>
		<!-- 未回复 -->
		<%if("2".equals(flag)){%>
		<form id="form2" method="post"> 
		<input type="hidden" name="FileUrl" id="FileUrl" value=""> 
		<input type="hidden" name="hfqx" id="hfqx" value="<%=wjglhf.getHfqx() %>"> 
		<input type="hidden" name="id" id="id" value="<%=wjglhf.getId() %>"> 
		<input type="hidden" name="wjid" id="wjid" value="<%=wjglhf.getWjID() %>"> 
		<input type="hidden" name="whfid" id="whfid" value="<%=wjglxf.getWhfryID() %>"> 
		<table width="75%" align="center">
			<tr>
				<td>
				回复内容：
					<textarea id="hfnr" name="hfnr" cols="1000" rows="50"></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="hfnr"/>  
				    <ck:replace replace="hfnr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div id="fileQueue"></div>
					上传附件：（每个附件最大10M，最多上传8个附件）<input id="file_upload" name="file_upload" type="file">
				</td>
			</tr>
		</table>
		</form>
		<table width="75%" align="center">
			<tr>
				<td align="center">
					<img alt="回复" width="45px" height="45px" title="回复" style="cursor: pointer;"  src="images/small/save-as.png" onclick="insertHF();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<% }%>
		
		<!-- 已回复 -->
		<%if("3".equals(flag)){%>
		<table width="75%" align="center">
			<tr>
				<td colspan="2">
				回复内容:
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="hfnr" name="hfnr" cols="1000" rows="50"><%=wjglhf.getHfnr() %></textarea>
				    <ckf:setupCKEditor  basePath="/NTJTCW/ckfinder/"  editor="hfnr"/>  
				    <ck:replace replace="hfnr" basePath="/NTJTCW/ckeditor"></ck:replace>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td width="50%" align="left">
				回复时间：<%=wjglhf.getHfsj() %>
				</td>
				<td width="50%" align="left">
				是否超时：<%if("超时".equals(wjglhf.getSfcs())) {%><img align="middle"  alt="超时" title="超时" src="images/small/wait-red.png"><span style="color: red;">超时</span><%}else {%><img align="middle"  alt="未超时" title="未超时" src="images/small/wait-green.png">未超时<%}%>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2">
				附&nbsp;&nbsp;件：
			  	<% if(!"".equals(wjglhf.getFileUrl())){
			  		String[] fileStr1 = wjglhf.getFileUrl().split(";");
			  		for(int j=0; j<fileStr1.length; j++){
			  	%>
			  		<br>
					<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="WjglServlet?action=downLoadHF&URL=<%=fileStr1[j]%>&id=<%=wjglhf.getId()%>" target="ifm"><%=fileStr1[j]%></a>
					<%}%>
				<%}else{%>无
				<%}%>
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<table width="75%" align="center">
			<tr>
				<td align="center">
				<img alt="关闭" width="100px" height="60px" title="关闭" style="cursor: pointer;"  src="images/small/i22.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<% }%>
	</body>
	<%} %>
</html>



