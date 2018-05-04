<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydb"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydbGk"%>
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
    <title>新增修改重大项目申报——信誉担保和重大资产抵押担保</title>
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
					form.action = "ZdxmsbServlet?action=ZdxmsbXydbdydbSave";
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
		var num=0;
		 //新增项目
        function moreXM(){
        	num++;
        	var table1 = document.getElementById("table1");
        	var tr = document.createElement("tr");
        	
        	var td1 = document.createElement("td");
        	td1.align="center";
        	var input1 = document.createElement("input");
        	input1.type = "text";
        	input1.name = "mc";
        	input1.size = "8";
        	
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "pgsj";
        	input2.id = "pgsjxz"+num;
        	input2.readOnly = true;
        	input2.size = "10";
        	var inputD2 = document.createElement("input");
        	inputD2.type = "button";
        	inputD2.name = "Button";
        	inputD2.className = "button1";
        	inputD2.value = "选择";
        	inputD2.onclick = new Function("setDay(document.ZdxmsbXydbdydb.pgsjxz"+num+");");
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	var input3 = document.createElement("input");
        	input3.type = "text";
        	input3.name = "jz";
        	input3.size = "6";
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "dbed";
        	input4.size = "6";
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "删除本行";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	table1.appendChild(tr);
        	tr.appendChild(td1);
        	td1.appendChild(input1);
        	tr.appendChild(td2);
        	td2.appendChild(input2);
        	td2.appendChild(inputD2);
        	tr.appendChild(td3);
        	td3.appendChild(input3);
        	tr.appendChild(td4);
        	td4.appendChild(input4);
        	td4.appendChild(inputc);
        }
        //删除项目
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
			num--;
		}     
        
		function InsertFile(str){
			document.getElementById("tjzt").value = str;
	   		var shr = document.getElementById("shr").value;
	   		var pgsj = document.getElementsByName("pgsj");
	   		for(var i=0;i<pgsj.length;i++){
	   			if(pgsj[i].value==""){
	   				alert("请填写信誉资产评估时间");
	   				return;
	   			}
	   		}
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
			form.action = "ZdxmsbServlet?action=ZdxmsbXydbdydbSave";
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
         	ZdxmsbXydbdydb zdxmsbXydbdydb = (ZdxmsbXydbdydb)request.getAttribute("zdxmsbXydbdydb");
			ArrayList ZdxmsbXydbdydbGkList = (ArrayList)request.getAttribute("ZdxmsbXydbdydbGkList");
		%>
		<form id="form1" name="ZdxmsbXydbdydb" method="post">
		<input type="hidden" name="ZdxmsbXydbdydb_id" id="ZdxmsbXydbdydb_id" <%if(zdxmsbXydbdydb.getId()!=0){%>value="<%=zdxmsbXydbdydb.getId()%>"<%}%> >
		<input name="smj" id="smj" type="hidden" <%if(zdxmsbXydbdydb.getSmj()!=null){%>value="<%=zdxmsbXydbdydb.getSmj() %>"<%}%>/>
		<input name="shr" id="shr" type="hidden" value="<%=zdxmsbXydbdydb.getShr() %>"/>
		<input name="shrID" id="shrID" type="hidden" value="<%=zdxmsbXydbdydb.getShrID() %>"/>
		<input name="gkid" id="gkid" type="hidden" <%if(zdxmsbXydbdydb.getGkid()!=null){%>value="<%=zdxmsbXydbdydb.getGkid() %>"<%}%>/>
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="2">
					南通市交通企事业单位重大财务事项报告单
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					——信誉担保和重大资产抵押担保
					</td>
				</tr>
				<tr height="1px">
					<td colspan="2">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td width="10%">&nbsp;</td>
					<td>
					一、概况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table id="table1" width="100%" border=1 cellpadding=0 cellspacing=0>
							<tr>
								<td align="center">
									信誉（资产）名称
								</td>
								<td align="center">
									信誉（资产）<br>评估时间
								</td>
								<td align="center">
									信誉（资产）价值<br>（万元）
								</td>
								<td align="center">
									担保额度<br>（万元）
								</td>
							</tr>
							<%
							for(int i=0;i< ZdxmsbXydbdydbGkList.size();i++){
								ZdxmsbXydbdydbGk zdxmsbXydbdydbGk = (ZdxmsbXydbdydbGk)ZdxmsbXydbdydbGkList.get(i);
							%>
							<tr>
								<td align="center">
								<input name="mc" type="text" size="6" <%if(zdxmsbXydbdydbGk.getMc()!=null){%>value="<%=zdxmsbXydbdydbGk.getMc()%>"<%}%>/>
								</td>
								<td align="center">
								<input type="text" name="pgsj" id="pgsj<%=i%>" value="<%=zdxmsbXydbdydbGk.getPgsj()%>" class="STYLE1" readonly/>
								<input name="Button" class="button1" onclick="setDay(document.ZdxmsbXydbdydb.pgsj<%=i%>);" type="button" value="选择">
								</td>
								<td align="center">
								<input name="jz" type="text" size="6" <%if(zdxmsbXydbdydbGk.getJz()!=null){%>value="<%=zdxmsbXydbdydbGk.getJz()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="dbed" type="text" size="6" <%if(zdxmsbXydbdydbGk.getDbed()!=null){%>value="<%=zdxmsbXydbdydbGk.getDbed()%>"<%}%>/>
								&nbsp;
								<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
								</td>
							</tr>
							<%} %>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<input type="button" class="button1" value="新增项目" onclick="moreXM()">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					二、担保事项及担保理由
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<textarea name="ly" id="ly" cols="120" rows="5"><%if(zdxmsbXydbdydb.getLy()!=null){%><%=zdxmsbXydbdydb.getLy() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					三、承担担保责任的主要内容（概述，具体担保协议做为附件附后）
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<textarea name="nr" id="nr" cols="120" rows="5"><%if(zdxmsbXydbdydb.getNr()!=null){%><%=zdxmsbXydbdydb.getNr() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					四、被担保方的主要财务状况
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table width="100%" border=1 cellpadding=0 cellspacing=0>
							<tr>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									资产总额<br>（万元）
								</td>
								<td align="center">
									负债总额<br>（万元）
								</td>
								<td align="center">
									净资产总额<br>（万元）
								</td>
								<td align="center">
									年度获净利水平<br>（万元）
								</td>
								<td align="center">
									货币资金总额<br>（万元）
								</td>
							</tr>
							<tr>
								<td align="left">
								1、担保事项实施前
								</td>
								<td align="center">
								<input name="zcze1" type="text" size="6" <%if(zdxmsbXydbdydb.getZcze1()!=null){%>value="<%=zdxmsbXydbdydb.getZcze1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="fzze1" type="text" size="6" <%if(zdxmsbXydbdydb.getFzze1()!=null){%>value="<%=zdxmsbXydbdydb.getFzze1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="jzcze1" type="text" size="6" <%if(zdxmsbXydbdydb.getJzcze1()!=null){%>value="<%=zdxmsbXydbdydb.getJzcze1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="jlsp1" type="text" size="6" <%if(zdxmsbXydbdydb.getJlsp1()!=null){%>value="<%=zdxmsbXydbdydb.getJlsp1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zjze1" type="text" size="6" <%if(zdxmsbXydbdydb.getZjze1()!=null){%>value="<%=zdxmsbXydbdydb.getZjze1()%>"<%}%>/>
								</td>
							</tr>
							<tr>
								<td align="left">
								2、担保事项实施后
								</td>
								<td align="center">
								<input name="zcze2" type="text" size="6" <%if(zdxmsbXydbdydb.getZcze2()!=null){%>value="<%=zdxmsbXydbdydb.getZcze2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="fzze2" type="text" size="6" <%if(zdxmsbXydbdydb.getFzze2()!=null){%>value="<%=zdxmsbXydbdydb.getFzze2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="jzcze2" type="text" size="6" <%if(zdxmsbXydbdydb.getJzcze2()!=null){%>value="<%=zdxmsbXydbdydb.getJzcze2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="jlsp2" type="text" size="6" <%if(zdxmsbXydbdydb.getJlsp2()!=null){%>value="<%=zdxmsbXydbdydb.getJlsp2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zjze2" type="text" size="6" <%if(zdxmsbXydbdydb.getZjze2()!=null){%>value="<%=zdxmsbXydbdydb.getZjze2()%>"<%}%>/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					五、被担保方担保事项的实施情况（简述）
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<textarea name="ssqk" id="ssqk" cols="120" rows="5"><%if(zdxmsbXydbdydb.getSsqk()!=null){%><%=zdxmsbXydbdydb.getSsqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					附&nbsp;&nbsp;件：
				  	<% if(!"".equals(zdxmsbXydbdydb.getSmj())&&zdxmsbXydbdydb.getSmj()!=null){
				  		String[] fileStr = zdxmsbXydbdydb.getSmj().split(";");
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
					<td width="10%">&nbsp;</td>
					<td>
					审核人：<%=zdxmsbXydbdydb.getShr() %>
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



