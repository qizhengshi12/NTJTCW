<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZdrz"%>
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
    <title>�����޸��ش���Ŀ�걨�����ش�����</title>
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
					form.action = "ZdxmsbServlet?action=ZdxmsbZdrzSave";
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
	   		var jhsj1 = document.getElementById("jhsj1").value;
	   		var jhsj2 = document.getElementById("jhsj2").value;
	   		if(jhsj1==""){
	   			alert("������ƻ����ʼʱ��");
	   			return;
	   		}
	   		if(jhsj2==""){
	   			alert("������ƻ��������ʱ��");
	   			return;
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
			form.action = "ZdxmsbServlet?action=ZdxmsbZdrzSave";
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
         	ZdxmsbZdrz zdxmsbZdrz = (ZdxmsbZdrz)request.getAttribute("zdxmsbZdrz");
		%>
		<form id="form1" name="ZdxmsbZdrz" method="post">
		<input type="hidden" name="ZdxmsbZdrz_id" id="ZdxmsbZdrz_id" <%if(zdxmsbZdrz.getId()!=0){%>value="<%=zdxmsbZdrz.getId()%>"<%}%> >
		<input name="smj" id="smj" type="hidden" <%if(zdxmsbZdrz.getSmj()!=null){%>value="<%=zdxmsbZdrz.getSmj() %>"<%}%>/>
		<input name="shr" id="shr" type="hidden" value="<%=zdxmsbZdrz.getShr() %>"/>
		<input name="shrID" id="shrID" type="hidden" value="<%=zdxmsbZdrz.getShrID() %>"/>
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="2">
					��ͨ�н�ͨ����ҵ��λ�ش��������浥
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					�����ش����ʣ���ծ��
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
					һ�����ʣ���ծ����ȣ�<input name="ed" id="ed" type="text" <%if(zdxmsbZdrz.getEd()!=null){%>value="<%=zdxmsbZdrz.getEd() %>"<%}%>/>��Ԫ��
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					�������ʣ���ծ����Ŀ������
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="yy" id="yy" cols="100" rows="5"><%if(zdxmsbZdrz.getYy()!=null){%><%=zdxmsbZdrz.getYy() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					�����ƻ�����ʱ�䣺<input name="jhsj1" id="jhsj1" type="text" size="10" <%if(zdxmsbZdrz.getJhsj1()!=null){%>value="<%=zdxmsbZdrz.getJhsj1() %>"<%}%> readonly/>
          <input name="Button" class="button1" onclick="setDay(document.ZdxmsbZdrz.jhsj1);" type="button" value="ѡ��">
					����<input name="jhsj2" id="jhsj2" type="text" size="10" <%if(zdxmsbZdrz.getJhsj2()!=null){%>value="<%=zdxmsbZdrz.getJhsj2() %>"<%}%> readonly/>
          <input name="Button" class="button1" onclick="setDay(document.ZdxmsbZdrz.jhsj2);" type="button" value="ѡ��">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					�ġ�������Դ
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="ly" id="ly" cols="100" rows="5"><%if(zdxmsbZdrz.getLy()!=null){%><%=zdxmsbZdrz.getLy() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					�塢Ԥ�Ʒ������Ϣ֧�����
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="zfqk" id="zfqk" cols="100" rows="5"><%if(zdxmsbZdrz.getZfqk()!=null){%><%=zdxmsbZdrz.getZfqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					���������Ѻ�򵣱����
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					<textarea name="dbqk" id="dbqk" cols="100" rows="5"><%if(zdxmsbZdrz.getDbqk()!=null){%><%=zdxmsbZdrz.getDbqk() %><%}%></textarea>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					�ߡ����ծ��������
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					��һ����ǰ����ۼƻ��<input name="yqhk" id="yqhk" type="text" <%if(zdxmsbZdrz.getYqhk()!=null){%>value="<%=zdxmsbZdrz.getYqhk() %>"<%}%>/>��Ԫ��
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					������������ѻ��<input name="ndhk" id="ndhk" type="text" size="5" <%if(zdxmsbZdrz.getNdhk()!=null){%>value="<%=zdxmsbZdrz.getNdhk() %>"<%}%>/>��Ԫ��
					������ֹ�ۼ��ѻ��<input name="ljhk" id="ljhk" type="text" size="5" <%if(zdxmsbZdrz.getLjhk()!=null){%>value="<%=zdxmsbZdrz.getLjhk() %>"<%}%>/>��Ԫ��
					Ŀǰծ����<input name="ye" id="ye" type="text" size="5" <%if(zdxmsbZdrz.getYe()!=null){%>value="<%=zdxmsbZdrz.getYe() %>"<%}%>/>��Ԫ��
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
					��&nbsp;&nbsp;����
				  	<% if(!"".equals(zdxmsbZdrz.getSmj())&&zdxmsbZdrz.getSmj()!=null){
				  		String[] fileStr = zdxmsbZdrz.getSmj().split(";");
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
					<td width="30%">&nbsp;</td>
					<td>
					����ˣ�<%=zdxmsbZdrz.getShr() %>
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



