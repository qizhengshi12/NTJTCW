<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbGqbg"%>
<%@page import="com.safety.entity.ZdxmsbGqbgGk"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    <title>�����޸��ش���Ŀ�걨������Ȩ���</title>
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
	            'uploader': 'HandleFileServlet?action=MultiFiles&path=ChoiTrialInformation/zdxmsb',//�ϴ�������ķ�����
	            'method': 'post',
	            'queueID' : 'fileQueue',//�ϴ���ʾ���������Ǹ�div
	            'buttonText' : '��ѡ���ļ�',
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
					form.action = "ZdxmsbServlet?action=ZdxmsbGqbgSave";
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
		  //������Ŀ
        function moreXM(){
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
        	input2.name = "yyys";
        	input2.size = "6";
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	var input3 = document.createElement("input");
        	input3.type = "text";
        	input3.name = "xz";
        	input3.size = "6";
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "cz";
        	input4.size = "6";
        	
        	var td5 = document.createElement("td");
        	td5.align="center";
        	var input5 = document.createElement("input");
        	input5.type = "text";
        	input5.name = "hyys";
        	input5.size = "6";
        	
        	var td6 = document.createElement("td");
        	td6.align="center";
        	var input6 = document.createElement("input");
        	input6.type = "text";
        	input6.name = "cb";
        	input6.size = "6";
        	
        	var td7 = document.createElement("td");
        	td7.align="center";
        	var input7 = document.createElement("input");
        	input7.type = "text";
        	input7.name = "yj";
        	input7.size = "6";
        	
        	var td8 = document.createElement("td");
        	td8.align="center";
        	var input8 = document.createElement("input");
        	input8.type = "text";
        	input8.name = "dj";
        	input8.size = "6";
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	
        	table1.appendChild(tr);
        	tr.appendChild(td1);
        	td1.appendChild(input1);
        	tr.appendChild(td2);
        	td2.appendChild(input2);
        	tr.appendChild(td3);
        	td3.appendChild(input3);
        	tr.appendChild(td4);
        	td4.appendChild(input4);
        	tr.appendChild(td5);
        	td5.appendChild(input5);
        	tr.appendChild(td6);
        	td6.appendChild(input6);
        	tr.appendChild(td7);
        	td7.appendChild(input7);
        	tr.appendChild(td8);
        	td8.appendChild(input8);
        	td8.appendChild(inputc);
        }
        //ɾ����Ŀ
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
		}     
        
		function InsertFile(str){
			document.getElementById("tjzt").value = str;
	   		var shr = document.getElementById("shr").value;
	   		if(shr==""){
	   			alert("����ϵ����Ա��Ԥ�����������");
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
			form.action = "ZdxmsbServlet?action=ZdxmsbGqbgSave";
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ZdxmsbGqbg zdxmsbGqbg = (ZdxmsbGqbg)request.getAttribute("zdxmsbGqbg");
			ArrayList ZdxmsbGqbgGkList = (ArrayList)request.getAttribute("ZdxmsbGqbgGkList");
		%>
		<form id="form1" name="ZdxmsbGqbg" method="post">
		<input type="hidden" name="ZdxmsbGqbg_id" id="ZdxmsbGqbg_id" <%if(zdxmsbGqbg.getId()!=0){%>value="<%=zdxmsbGqbg.getId()%>"<%}%> >
		<input name="smj" id="smj" type="hidden" <%if(zdxmsbGqbg.getSmj()!=null){%>value="<%=zdxmsbGqbg.getSmj() %>"<%}%>/>
		<input name="shr" id="shr" type="hidden" value="<%=zdxmsbGqbg.getShr() %>"/>
		<input name="shrID" id="shrID" type="hidden" value="<%=zdxmsbGqbg.getShrID() %>"/>
		<input name="gkid" id="gkid" type="hidden" <%if(zdxmsbGqbg.getGkid()!=null){%>value="<%=zdxmsbGqbg.getGkid() %>"<%}%>/>
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="2">
					��ͨ�н�ͨ����ҵ��λ�ش��������浥
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					������Ȩ���
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
					һ����Ȩ����ſ�
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table id="table1" width="100%" border=1 cellpadding=0 cellspacing=0>
							<tr>
								<td align="center">
									��Ȩ����
								</td>
								<td align="center">
									ԭӵ����<br>����Ԫ��
								</td>
								<td align="center">
									����<br>����Ԫ��
								</td>
								<td align="center">
									����<br>����Ԫ��
								</td>
								<td align="center">
									�����<br>ӵ����<br>����Ԫ��
								</td>
								<td align="center">
									������<br>Ȩ�ɱ�<br>����Ԫ��
								</td>
								<td align="center">
									��Ȩ����<br>���<br>����Ԫ��
								</td>
								<td align="center">
									��Ȩ����<br>����<br>����Ԫ��
								</td>
							</tr>
							<%
							for(int i=0;i< ZdxmsbGqbgGkList.size();i++){
								ZdxmsbGqbgGk zdxmsbGqbgGk = (ZdxmsbGqbgGk)ZdxmsbGqbgGkList.get(i);
							%>
							<tr>
								<td align="center">
								<input name="mc" type="text" size="6" <%if(zdxmsbGqbgGk.getMc()!=null){%>value="<%=zdxmsbGqbgGk.getMc()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="yyys" type="text" size="6" <%if(zdxmsbGqbgGk.getYyys()!=null){%>value="<%=zdxmsbGqbgGk.getYyys()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="xz" type="text" size="6" <%if(zdxmsbGqbgGk.getXz()!=null){%>value="<%=zdxmsbGqbgGk.getXz()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="cz" type="text" size="6" <%if(zdxmsbGqbgGk.getCz()!=null){%>value="<%=zdxmsbGqbgGk.getCz()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="hyys" type="text" size="6" <%if(zdxmsbGqbgGk.getHyys()!=null){%>value="<%=zdxmsbGqbgGk.getHyys()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="cb" type="text" size="6" <%if(zdxmsbGqbgGk.getCb()!=null){%>value="<%=zdxmsbGqbgGk.getCb()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="yj" type="text" size="6" <%if(zdxmsbGqbgGk.getYj()!=null){%>value="<%=zdxmsbGqbgGk.getYj()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="dj" type="text" size="6" <%if(zdxmsbGqbgGk.getDj()!=null){%>value="<%=zdxmsbGqbgGk.getDj()%>"<%}%>/>
								&nbsp;
								<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
								</td>
							</tr>
							<%} %>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM()">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					������Ȩ������ɣ�����Ȩ���Ʒֱ𱨸棩
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<textarea name="ly" id="ly" cols="120" rows="5"><%if(zdxmsbGqbg.getLy()!=null){%><%=zdxmsbGqbg.getLy() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					������Ȩ��������������
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
					<textarea name="qk" id="qk" cols="120" rows="5"><%if(zdxmsbGqbg.getQk()!=null){%><%=zdxmsbGqbg.getQk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbGqbg.getSmj())&&zdxmsbGqbg.getSmj()!=null){
				  		String[] fileStr = zdxmsbGqbg.getSmj().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a id="d<%=i%>" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="ZdxmsbServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<img id="m<%=i%>" align="top" width="20px" height="20px" alt="ɾ��" title="ɾ��" style="cursor: pointer;" src="images/small/trash-can-delete.png" onclick="delFile('<%=i%>','<%=fileStr[i]%>');">
						<%}%>
					<%}else{%>��
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
	�ϴ���������ÿ���������10M������ϴ�8��������<input id="file_upload" name="file_upload" type="file">
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
					����ˣ�<%=zdxmsbGqbg.getShr() %>
					</td>
				</tr>
			</table>
		<form id="form2"  method="post"></form>
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
	<%}%>
	</body>
</html>



