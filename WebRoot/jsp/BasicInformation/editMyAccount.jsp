<%@page contentType="text/html;charset=GBK"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>�ҵ��˻�</title>
 	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
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
			if(size>1000){   
				alert("ͼƬ��С���ܴ���1M��");   
				return;
			}    
			if(size<=0){  
				alert("ͼƬ��С����Ϊ0M��");   
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
		    	//cancel();
		    }
		  }
		var url = "HandleFileServlet?action=DeleteFile&path="+path;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
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
	    var path = "BasicInformation/temp";
		form.action = "HandleFileServlet?action=HandlePhoto&path="+path;
		form.submit();
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
    function save(){
		var birth = document.getElementById("birth").value;
       	if(birth==""){
       		alert("�������������");
       		return;
       	}
		var worktime = document.getElementById("worktime").value;
       	if(worktime==""){
       		alert("������μӹ���ʱ��");
       		return;
       	}
		var form = document.getElementById("form");
		form.action = "ZzxxServlet?action=saveMyAccount";
		form.submit();
	}
	function selChange1(){
		var sel = document.getElementById("sel1");
		document.getElementById("education").value=sel.options[sel.selectedIndex].value;
	}
	function back(){
		DeleteTemp();
		var form = document.getElementById("form");
		form.action = "ZzxxServlet?action=getMyAccount";
		form.submit();
	}
    </script>

  </head>
  
  <body>
	<%
         //��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         ContentZzxx zzxx = (ContentZzxx)request.getAttribute("Zzxx");
	%>
	<form id="form" method="post" action="" name="myaccount">
	<input type="hidden" name = "ZHID" value="<%=zzxx.getId() %>"  size="41"/>
  	<input type="hidden" name="FileURL" id="FileURL" <%if(zzxx.getFileURL()!=null){%> value="<%=zzxx.getFileURL() %>"<%}%>>
  	<input type="hidden" name="TempURL" id="TempURL" value="">
		<table width="100%">
			<tr>
				<td align="center" colspan="5">
					<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/about-me.png">�ҵ��˻�</h1>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" width="18%">
					������
				</td>
				<td align="left" width="30%">
					<input type="text" value="<%=zzxx.getName() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center" width="12%">
					�Ա�
				</td>
				<td align="left" width="25%">
					<input type="text" value="<%=zzxx.getSex() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center" valign="top" rowspan="9"  width="15%">
					<img id="photo" style="cursor: pointer" width="150px" height="200px" src="UserFile/<%=zzxx.getFileURL() %>"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					�������ڣ�
				</td>
				<td align="left">
					<input type="text" name="birth" id="birth" value="<%=zzxx.getBirth() %>" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.myaccount.birth);" type="button" value="ѡ��">
				</td>
				<td align="center">
					���֤�ţ�
				</td>
				<td align="left">
					<input type="text" name="pcard" id="pcard" value="<%=zzxx.getPcard() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					ѧ����
				</td>
				<td align="left">
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=zzxx.getEducation() %>">&nbsp;<%=zzxx.getEducation() %></option>
						<option value="���м�����">&nbsp;���м�����</option>
						<option value="����">&nbsp;����</option>
						<option value="��ר">&nbsp;��ר</option>
						<option value="��ר">&nbsp;��ר</option>
						<option value="����">&nbsp;����</option>
						<option value="�о���">&nbsp;�о���</option>
						<option value="��ʿ������">&nbsp;��ʿ������</option>
					</select>
					<input type="hidden" name="education" id="education" value="<%=zzxx.getEducation() %>">
				</td>
				<td align="center">
					��ҵԺУ��
				</td>
				<td align="left">
					<input type="text" name="school" id="school" value="<%=zzxx.getSchool() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					�μӹ���ʱ�䣺
				</td>
				<td align="left">
					<input type="text" name="worktime" id="worktime" value="<%=zzxx.getWorktime() %>" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.myaccount.worktime);" type="button" value="ѡ��">
				</td>
				<td align="center">
					ְ�ƣ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJoblevel() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					�ֻ��ţ�
				</td>
				<td align="left">
					<input type="text" name="phone" id="phone" value="<%=zzxx.getPhone() %>"  onkeyup="this.value=this.value.replace(/\D/g,'')" class="STYLE1"/>
				</td>
				<td align="center">
					�칫�绰��
				</td>
				<td align="left">
					<input type="text" name="workphone" id="workphone" value="<%=zzxx.getWorkphone() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					������λ��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getCompany() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart1() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart2() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					�ļ����ţ�
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart3() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					��λ��ְ�񣩣�
				</td>
				<td align="left">
					<input type="text" name="job" id="job" value="<%=zzxx.getJob() %>" class="STYLE1"/>
				</td>
				<td align="center">
					��λְ��
				</td>
				<td align="left">
					<textarea rows="4" cols="25" name="jobdes" id="jobdes"><%=zzxx.getJobdes() %></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					��ɫ��
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getRoles() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					�û�����
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getUsername() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
		</table>
	</form>
	<form id="form3" method="post"  enctype="multipart/form-data" target="ifm1">
		<table width="100%">
			<tr>
				<td align="center">
					�ϴ���Ƭ������ʽΪJPG/BMP/PNG�����1M��
					<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>&nbsp;&nbsp;<input type="button" class="button1" value="�ϴ�Ԥ��" onclick="postPhoto()" />
				</td>
			</tr>
		</table>
		<iframe id='ifm1' name='ifm1' style="display:none"></iframe>
	</form>	
		<table width="100%">
			<tr>
				<td>
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="back()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
