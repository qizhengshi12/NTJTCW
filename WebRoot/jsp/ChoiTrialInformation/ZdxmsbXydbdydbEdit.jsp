<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydb"%>
<%@page import="com.safety.entity.ZdxmsbXydbdydbGk"%>
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
    <title>�����޸��ش���Ŀ�걨���������������ش��ʲ���Ѻ����</title>
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
		 //������Ŀ
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
        	inputD2.value = "ѡ��";
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
        	inputc.value = "ɾ������";
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
        //ɾ����Ŀ
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
	   				alert("����д�����ʲ�����ʱ��");
	   				return;
	   			}
	   		}
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
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
					��ͨ�н�ͨ����ҵ��λ�ش��������浥
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					���������������ش��ʲ���Ѻ����
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
					һ���ſ�
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<table id="table1" width="100%" border=1 cellpadding=0 cellspacing=0>
							<tr>
								<td align="center">
									�������ʲ�������
								</td>
								<td align="center">
									�������ʲ���<br>����ʱ��
								</td>
								<td align="center">
									�������ʲ�����ֵ<br>����Ԫ��
								</td>
								<td align="center">
									�������<br>����Ԫ��
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
								<input name="Button" class="button1" onclick="setDay(document.ZdxmsbXydbdydb.pgsj<%=i%>);" type="button" value="ѡ��">
								</td>
								<td align="center">
								<input name="jz" type="text" size="6" <%if(zdxmsbXydbdydbGk.getJz()!=null){%>value="<%=zdxmsbXydbdydbGk.getJz()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="dbed" type="text" size="6" <%if(zdxmsbXydbdydbGk.getDbed()!=null){%>value="<%=zdxmsbXydbdydbGk.getDbed()%>"<%}%>/>
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
					�������������������
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
					�����е��������ε���Ҫ���ݣ����������嵣��Э����Ϊ��������
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
					�ġ�������������Ҫ����״��
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
									�ʲ��ܶ�<br>����Ԫ��
								</td>
								<td align="center">
									��ծ�ܶ�<br>����Ԫ��
								</td>
								<td align="center">
									���ʲ��ܶ�<br>����Ԫ��
								</td>
								<td align="center">
									��Ȼ���ˮƽ<br>����Ԫ��
								</td>
								<td align="center">
									�����ʽ��ܶ�<br>����Ԫ��
								</td>
							</tr>
							<tr>
								<td align="left">
								1����������ʵʩǰ
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
								2����������ʵʩ��
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
					�塢�����������������ʵʩ�����������
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
					��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbXydbdydb.getSmj())&&zdxmsbXydbdydb.getSmj()!=null){
				  		String[] fileStr = zdxmsbXydbdydb.getSmj().split(";");
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
					����ˣ�<%=zdxmsbXydbdydb.getShr() %>
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



