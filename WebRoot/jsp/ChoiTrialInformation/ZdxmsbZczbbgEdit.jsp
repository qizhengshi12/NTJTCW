<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZczbbg"%>
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
    <title>新增修改重大项目申报——注册资本变更</title>
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
					form.action = "ZdxmsbServlet?action=ZdxmsbZczbbgSave";
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
			form.action = "ZdxmsbServlet?action=ZdxmsbZczbbgSave";
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
         	ZdxmsbZczbbg zdxmsbZczbbg = (ZdxmsbZczbbg)request.getAttribute("zdxmsbZczbbg");
		%>
		<form id="form1" name="ZdxmsbZczbbg" method="post">
		<input type="hidden" name="ZdxmsbZczbbg_id" id="ZdxmsbZczbbg_id" <%if(zdxmsbZczbbg.getId()!=0){%>value="<%=zdxmsbZczbbg.getId()%>"<%}%> >
		<input name="smj" id="smj" type="hidden" <%if(zdxmsbZczbbg.getSmj()!=null){%>value="<%=zdxmsbZczbbg.getSmj() %>"<%}%>/>
		<input name="shr" id="shr" type="hidden" value="<%=zdxmsbZczbbg.getShr() %>"/>
		<input name="shrID" id="shrID" type="hidden" value="<%=zdxmsbZczbbg.getShrID() %>"/>
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="2">
					南通市交通企事业单位重大财务事项报告单
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					——注册资本变更
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
					一、注册资本变更概况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table width="80%" border=1 cellpadding=0 cellspacing=0>
							<tr>
								<td rowspan="2" align="center">
									原注册资本<br>（万元）
								</td>
								<td colspan="2" align="center">
									变更情况
								</td>
								<td rowspan="2" align="center">
									变更后注册资本<br>（万元）
								</td>
							</tr>
							<tr>
								<td align="center">
									扩资<br>（万元）
								</td>
								<td align="center">
									减资<br>（万元）
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">
									<input name="zb" id="zb" type="text" <%if(zdxmsbZczbbg.getZb()!=null){%>value="<%=zdxmsbZczbbg.getZb()%>"<%}%>/>
								</td>
								<td align="center">
									<input name="kz" id="kz" type="text" <%if(zdxmsbZczbbg.getKz()!=null){%>value="<%=zdxmsbZczbbg.getKz()%>"<%}%>/>
								</td>
								<td align="center">
									<input name="jz" id="jz" type="text" <%if(zdxmsbZczbbg.getJz()!=null){%>value="<%=zdxmsbZczbbg.getJz()%>"<%}%>/>
								</td>
								<td align="center">
									<input name="bgzb" id="bgzb" type="text" <%if(zdxmsbZczbbg.getBgzb()!=null){%>value="<%=zdxmsbZczbbg.getBgzb()%>"<%}%>/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					二、注册资本构成情况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					（一）原注册资本构成情况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="yqk" id="yqk" cols="100" rows="5"><%if(zdxmsbZczbbg.getYqk()!=null){%><%=zdxmsbZczbbg.getYqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					（二）变更后注册资本构成情况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="bgqk" id="bgqk" cols="100" rows="5"><%if(zdxmsbZczbbg.getBgqk()!=null){%><%=zdxmsbZczbbg.getBgqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					三、注册资本变更的依据
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="bgyj" id="bgyj" cols="100" rows="5"><%if(zdxmsbZczbbg.getBgyj()!=null){%><%=zdxmsbZczbbg.getBgyj() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					四、扩资的资金来源构成情况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="gcqk" id="gcqk" cols="100" rows="5"><%if(zdxmsbZczbbg.getGcqk()!=null){%><%=zdxmsbZczbbg.getGcqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					五、减资资本的处置方案及结果
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="fajg" id="fajg" cols="100" rows="5"><%if(zdxmsbZczbbg.getFajg()!=null){%><%=zdxmsbZczbbg.getFajg() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(zdxmsbZczbbg.getSmj())&&zdxmsbZczbbg.getSmj()!=null){
				  		String[] fileStr = zdxmsbZczbbg.getSmj().split(";");
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
					审核人：<%=zdxmsbZczbbg.getShr() %>
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



