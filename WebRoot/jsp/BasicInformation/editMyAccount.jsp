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
    <title>我的账户</title>
 	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
    <script type="text/javascript">
    var extArray = new Array(".jpg",".bmp",".png");
	function postPhoto(){
		var file = document.getElementById("file1").value;
		var allowSubmit = false;
        if (file==""){
        	alert("请选择上传文件");
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
				alert("图片大小不能大于1M！");   
				return;
			}    
			if(size<=0){  
				alert("图片大小不能为0M！");   
				return;
			}   
	   		var TempURL = document.getElementById("TempURL").value;
			if(TempURL!=""){
				DeletePhoto(TempURL);
			}else{
				savePhoto();
			}
        }else{ //检测上传文件类型
            alert("只能上传以下格式的文件:"+ (extArray.join("")) + "\n请重新选择再上传.");
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
       		alert("上传冲突，请稍后再试");
       		return;
       	}
	}
    function save(){
		var birth = document.getElementById("birth").value;
       	if(birth==""){
       		alert("请输入出生日期");
       		return;
       	}
		var worktime = document.getElementById("worktime").value;
       	if(worktime==""){
       		alert("请输入参加工作时间");
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
         //从request域中取得要显示的某页信息
         ContentZzxx zzxx = (ContentZzxx)request.getAttribute("Zzxx");
	%>
	<form id="form" method="post" action="" name="myaccount">
	<input type="hidden" name = "ZHID" value="<%=zzxx.getId() %>"  size="41"/>
  	<input type="hidden" name="FileURL" id="FileURL" <%if(zzxx.getFileURL()!=null){%> value="<%=zzxx.getFileURL() %>"<%}%>>
  	<input type="hidden" name="TempURL" id="TempURL" value="">
		<table width="100%">
			<tr>
				<td align="center" colspan="5">
					<h1 style="font-family:verdana"><img width="60px" height="60px" src="images/small/about-me.png">我的账户</h1>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td align="center" width="18%">
					姓名：
				</td>
				<td align="left" width="30%">
					<input type="text" value="<%=zzxx.getName() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center" width="12%">
					性别：
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
					出生日期：
				</td>
				<td align="left">
					<input type="text" name="birth" id="birth" value="<%=zzxx.getBirth() %>" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.myaccount.birth);" type="button" value="选择">
				</td>
				<td align="center">
					身份证号：
				</td>
				<td align="left">
					<input type="text" name="pcard" id="pcard" value="<%=zzxx.getPcard() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					学历：
				</td>
				<td align="left">
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value="<%=zzxx.getEducation() %>">&nbsp;<%=zzxx.getEducation() %></option>
						<option value="初中及以下">&nbsp;初中及以下</option>
						<option value="高中">&nbsp;高中</option>
						<option value="中专">&nbsp;中专</option>
						<option value="大专">&nbsp;大专</option>
						<option value="本科">&nbsp;本科</option>
						<option value="研究生">&nbsp;研究生</option>
						<option value="博士及以上">&nbsp;博士及以上</option>
					</select>
					<input type="hidden" name="education" id="education" value="<%=zzxx.getEducation() %>">
				</td>
				<td align="center">
					毕业院校：
				</td>
				<td align="left">
					<input type="text" name="school" id="school" value="<%=zzxx.getSchool() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					参加工作时间：
				</td>
				<td align="left">
					<input type="text" name="worktime" id="worktime" value="<%=zzxx.getWorktime() %>" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.myaccount.worktime);" type="button" value="选择">
				</td>
				<td align="center">
					职称：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getJoblevel() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					手机号：
				</td>
				<td align="left">
					<input type="text" name="phone" id="phone" value="<%=zzxx.getPhone() %>"  onkeyup="this.value=this.value.replace(/\D/g,'')" class="STYLE1"/>
				</td>
				<td align="center">
					办公电话：
				</td>
				<td align="left">
					<input type="text" name="workphone" id="workphone" value="<%=zzxx.getWorkphone() %>" class="STYLE1"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					所属单位：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getCompany() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					二级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart1() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					三级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart2() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					四级部门：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getDepart3() %>" class="STYLE1" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					岗位（职务）：
				</td>
				<td align="left">
					<input type="text" name="job" id="job" value="<%=zzxx.getJob() %>" class="STYLE1"/>
				</td>
				<td align="center">
					岗位职责：
				</td>
				<td align="left">
					<textarea rows="4" cols="25" name="jobdes" id="jobdes"><%=zzxx.getJobdes() %></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					角色：
				</td>
				<td align="left">
					<input type="text" value="<%=zzxx.getRoles() %>" class="STYLE1" disabled="disabled"/>
				</td>
				<td align="center">
					用户名：
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
					上传照片：（格式为JPG/BMP/PNG，最大1M）
					<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>&nbsp;&nbsp;<input type="button" class="button1" value="上传预览" onclick="postPhoto()" />
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
					<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="back()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
  </body>
</html>
