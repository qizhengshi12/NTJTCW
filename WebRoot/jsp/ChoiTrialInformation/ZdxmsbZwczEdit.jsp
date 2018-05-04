<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZwcz"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
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
    <title>新增修改重大项目申报——债务重组</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=ChoiTrialInformation/zdxmsb',//上传所处理的服务器
	            'method': 'post',
	            'queueID' : 'fileQueue',//上传显示进度条的那个div
	            'buttonText' : '请选择文件',
	           	'removeTimeout': 2,
	           	'fileSizeLimit': '10MB',
	            'progressData' : 'percentage',
	            'auto' : false,
	            'multi' : true,
				'onUploadSuccess' : function(file, data, response) {
					var smj = document.getElementById("smj").value;
					document.getElementById("smj").value = smj+data+";";
		        },
		        'onQueueComplete' : function(queueData) {
			   		var form = document.getElementById("form1");
					form.action = "ZdxmsbServlet?action=ZdxmsbZwczSave";
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
	   		var shr = document.getElementById("shr").value;
	   		if(shr==""){
	   			alert("请联系管理员，预先设置审核人");
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
			form.action = "ZdxmsbServlet?action=ZdxmsbZwczSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	form.target="_self";
			form.action = "ZdxmsbServlet?action=getZdxmsb&flag=1";
			form.submit();
        }
		function delFile(num,value){
			var obj1 = document.getElementById("d"+num);
			var obj2 = document.getElementById("m"+num);
			obj1.parentElement.removeChild(obj1); 
			obj2.parentElement.removeChild(obj2);
			var smj = document.getElementById("smj").value;
			smj = smj.replace(value+";","");
			smj = smj.replace(value,"");
			document.getElementById("smj").value = smj;
			DeleteFile("/ChoiTrialInformation/zdxmsb/"+value);
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
	<% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<% } %>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ZdxmsbZwcz zdxmsbZwcz = (ZdxmsbZwcz)request.getAttribute("zdxmsbZwcz");
		%>
		<form id="form1" name="ZdxmsbZwcz" method="post">
		<input type="hidden" name="ZdxmsbZwcz_id" id="ZdxmsbZwcz_id" <%if(zdxmsbZwcz.getId()!=0){%>value="<%=zdxmsbZwcz.getId()%>"<%}%> >
		<input name="smj" id="smj" type="hidden" <%if(zdxmsbZwcz.getSmj()!=null){%>value="<%=zdxmsbZwcz.getSmj() %>"<%}%>/>
		<input name="shr" id="shr" type="hidden" value="<%=zdxmsbZwcz.getShr() %>"/>
		<input name="shrID" id="shrID" type="hidden" value="<%=zdxmsbZwcz.getShrID() %>"/>
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="2">
					南通市交通企事业单位重大财务事项报告单
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					——债务重组
					</td>
				</tr>
				<tr height="1px">
					<td colspan="2">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td width="20%">&nbsp;</td>
					<td>
					一、债务重组的目的意义
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="mdyy" id="mdyy" cols="100" rows="5"><%if(zdxmsbZwcz.getMdyy()!=null){%><%=zdxmsbZwcz.getMdyy() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					二、债务重组的主要内容及额度
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="nred" id="nred" cols="100" rows="5"><%if(zdxmsbZwcz.getNred()!=null){%><%=zdxmsbZwcz.getNred() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					三、因债务重组所需承担的成本：<input name="cb" id="cb" type="text" <%if(zdxmsbZwcz.getCb()!=null){%>value="<%=zdxmsbZwcz.getCb()%>"<%}%>/>万元。
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					四、债务重组方案（概述）
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="fa" id="fa" cols="100" rows="5"><%if(zdxmsbZwcz.getFa()!=null){%><%=zdxmsbZwcz.getFa() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					五、债务重组后的资产负债情况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="qk" id="qk" cols="100" rows="5"><%if(zdxmsbZwcz.getQk()!=null){%><%=zdxmsbZwcz.getQk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(zdxmsbZwcz.getSmj())&&zdxmsbZwcz.getSmj()!=null){
				  		String[] fileStr = zdxmsbZwcz.getSmj().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="ZdxmsbServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<img id="m<%=i%>" align="top" width="20px" height="20px" alt="删除" title="删除" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
						<%}%>
					<%}else{%>无
					<%}%>
					</td>
				</tr>
				<tr height="1px">
					<td colspan="2">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
		</form>
		<div id="fileQueue"></div>
	上传附件：（每个附件最大10M，最多上传8个附件）<input id="file_upload" name="file_upload" type="file">
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
			<table width="100%">
				<tr height="1px">
					<td colspan="2">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td width="20%">&nbsp;</td>
					<td>
					审核人：<%=zdxmsbZwcz.getShr() %>
					</td>
				</tr>
			</table>
		<form id="form2"  method="post"></form>
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
	<%}%>
	</body>
</html>



