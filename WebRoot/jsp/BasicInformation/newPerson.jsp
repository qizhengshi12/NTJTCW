<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%@page import="com.safety.entity.Permissions"%>       
<%@ page session="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='../../login.jsp';</script>");
	else{
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String companyID = UserInfo.getCompanyID();
	String roles = UserInfo.getRoles();
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>������Ա</title>
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <script src="calendar.js"></script>
	<script type="text/javascript">
	function insertPeron(){
			var name = document.getElementById("name").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("����������");
    			document.getElementById("name").value="";
    			return;
    		}
			var company = document.getElementById("company").value;
			company = company.replace(/\ /g,"");
			if(company==""){
    			alert("������������λ");
    			document.getElementById("company").value="";
    			return;
    		}
			var depart1 = document.getElementById("depart1").value;
			depart1 = depart1.replace(/\ /g,"");
			if(depart1==""){
    			alert("���������ڲ���");
    			document.getElementById("depart1").value="";
    			return;
    		}
    		var user = document.getElementById("user").value;
			user = user.replace(/\ /g,"");
			if(user==""){
    			alert("�������û���");
    			document.getElementById("user").value="";
    			return;
    		}
    		var userma = /^[a-zA-Z0-9_]{1,}$/;
    		if(!user.match(userma)){
    			alert("�û���ֻ������ĸ�����»������");
    			document.getElementById("user").value="";
    			return;
    		}
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
			FindSameName();
		}
		function FindSameName(){
			var user = document.getElementById("user").value;
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
			    	if(jsons!="true"){
			    		alert(jsons);
			    	}else{
			       		Save();
			    	}
			    }
			  }
			var url = "ZzxxServlet?action=FindSameUser&user=" + user;
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function FindCompany(){
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
			    	document.getElementById("suggest1").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=FindCompany&parent_id=0";
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function chooseCompany(val){
			var str = val.split("&#&");
	    	document.getElementById("company").value = str[1];
	    	document.getElementById("companyID").value = str[0];
	    	document.getElementById("depart1").value = "";
	    	document.getElementById("departID1").value = "";
	    	document.getElementById("depart2").value = "";
	    	document.getElementById("departID2").value = "";
	    	document.getElementById("depart3").value = "";
	    	document.getElementById("departID3").value = "";
			document.getElementById("suggest1").innerHTML="";
			document.getElementById("suggest2").innerHTML="";
			document.getElementById("suggest3").innerHTML="";
			document.getElementById("suggest4").innerHTML="";
	    }
		
		function FindDepart1(){
			var xmlhttp;
			var companyID = document.getElementById("companyID").value;
			companyID = companyID.replace(/\ /g,"");
			if(companyID==""){
    			alert("����ѡ��������λ");
    			return;
    		}
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
			    	document.getElementById("suggest2").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=FindDepart1&parent_id=" +companyID;
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function chooseDepart1(val){
			var str = val.split("&#&");
	    	document.getElementById("depart1").value = str[1];
	    	document.getElementById("departID1").value = str[0];
	    	document.getElementById("depart2").value = "";
	    	document.getElementById("departID2").value = "";
	    	document.getElementById("depart3").value = "";
	    	document.getElementById("departID3").value = "";
			document.getElementById("suggest1").innerHTML="";
			document.getElementById("suggest2").innerHTML="";
			document.getElementById("suggest3").innerHTML="";
			document.getElementById("suggest4").innerHTML="";
	    }
		
		function FindDepart2(){
			var xmlhttp;
			var departID1 = document.getElementById("departID1").value;
			departID1 = departID1.replace(/\ /g,"");
			if(departID1==""){
    			alert("����ѡ����һ������");
    			return;
    		}
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
			    	document.getElementById("suggest3").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=FindDepart2&parent_id=" +departID1;
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function chooseDepart2(val){
			var str = val.split("&#&");
	    	document.getElementById("depart2").value = str[1];
	    	document.getElementById("departID2").value = str[0];
	    	document.getElementById("depart3").value = "";
	    	document.getElementById("departID3").value = "";
			document.getElementById("suggest1").innerHTML="";
			document.getElementById("suggest2").innerHTML="";
			document.getElementById("suggest3").innerHTML="";
			document.getElementById("suggest4").innerHTML="";
	    }
		
		function FindDepart3(){
			var xmlhttp;
			var departID2 = document.getElementById("departID2").value;
			departID2 = departID2.replace(/\ /g,"");
			if(departID2==""){
    			alert("����ѡ����һ������");
    			return;
    		}
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
			    	document.getElementById("suggest4").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=FindDepart3&parent_id=" +departID2;
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function chooseDepart3(val){
			var str = val.split("&#&");
	    	document.getElementById("depart3").value = str[1];
	    	document.getElementById("departID3").value = str[0];
			document.getElementById("suggest1").innerHTML="";
			document.getElementById("suggest2").innerHTML="";
			document.getElementById("suggest3").innerHTML="";
			document.getElementById("suggest4").innerHTML="";
	    }
		function Save(){
			var form = document.getElementById("form1");
			form.action = "ZzxxServlet?action=insertPerson";
			form.submit();
		}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("education").value=sel.options[sel.selectedIndex].value;
		}
		function selChange2(){
			var sel = document.getElementById("sel2");
			document.getElementById("joblevel").value=sel.options[sel.selectedIndex].value;
		}
		function selChange3(){
			var sel = document.getElementById("sel3");
			document.getElementById("roles").value=sel.options[sel.selectedIndex].value;
		}
	</script>

  </head>
  
  <body>
   <form method="post" id="form1"  name="newperson">
		<table width="100%">
			<tr class="tr3">
				<td align="center" colspan="4">
					<h1 style="font-family:verdana">������Ա</h1>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" width="15%">
					������
				</td>
				<td align="left" width="35%">
					<input type="text" name="name" id="name" value="" class="STYLE1"/>
				</td>
				<td align="center" width="15%">
					�Ա�
				</td>
				<td align="left" width="35%">
					<input type="radio" name="sex" checked="checked" value="��">��
					<input type="radio" name="sex" value="Ů">Ů
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�������ڣ�
				</td>
				<td align="left">
					<input type="text" name="birth" id="birth" value="" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.newperson.birth);" type="button" value="ѡ��">
				</td>
				<td align="center">
					���֤�ţ�
				</td>
				<td align="left">
					<input type="text" name="pcard" id="pcard" value="" class="STYLE1"/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					ѧ����
				</td>
				<td align="left">
					<select id="sel1" onchange="selChange1()" class="STYLE1">
						<option value=""></option>
						<option value="���м�����">&nbsp;���м�����</option>
						<option value="����">&nbsp;����</option>
						<option value="��ר">&nbsp;��ר</option>
						<option value="��ר">&nbsp;��ר</option>
						<option value="����">&nbsp;����</option>
						<option value="�о���">&nbsp;�о���</option>
						<option value="��ʿ������">&nbsp;��ʿ������</option>
					</select>
					<input type="hidden" name="education" id="education" value="">
				</td>
				<td align="center">
					��ҵԺУ��
				</td>
				<td align="left">
					<input type="text" name="school" id="school" value="" class="STYLE1"/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�μӹ���ʱ�䣺
				</td>
				<td align="left">
					<input type="text" name="worktime" id="worktime" value="" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.newperson.worktime);" type="button" value="ѡ��">
				</td>
				<td align="center">
					ְ�ƣ�
				</td>
				<td align="left">
					<select id="sel2" onchange="selChange2()" class="STYLE1">
						<option value=""></option>
						<option value="���Ա">���Ա</option>
						<option value="������ʦ">������ʦ</option>
						<option value="���ʦ">���ʦ</option>
						<option value="�߼����ʦ">�߼����ʦ</option>
						<option value="����Ա">����Ա</option>
						<option value="������ʦ">������ʦ</option>
						<option value="����ʦ">����ʦ</option>
						<option value="�߼�����ʦ">�߼�����ʦ</option>
						<option value="���Ա">���Ա</option>
						<option value="�������ʦ">�������ʦ</option>
						<option value="���ʦ">���ʦ</option>
						<option value="�߼����ʦ">�߼����ʦ</option>
						<option value="��������ְ��">��������ְ��</option>
						<option value="�����м�ְ��">�����м�ְ��</option>
						<option value="�����߼�ְ��">�����߼�ְ��</option>
					</select>
					<input type="hidden" name="joblevel" id="joblevel" value="">
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�ֻ��ţ�
				</td>
				<td align="left">
					<input type="text" name="phone" id="phone" value=""  onkeyup="this.value=this.value.replace(/\D/g,'')" class="STYLE1"/>
				</td>
				<td align="center">
					�칫�绰��
				</td>
				<td align="left">
					<input type="text" name="workphone" id="workphone" value="" class="STYLE1"/>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					������λ��
				</td>
				<td align="left">
					<input type="text" name="company" id="company" 
					<%if("����Ա".equals(roles)||(UserPer.getContentzzxx().indexOf("5")!=-1)){%>onclick="FindCompany()"<%}else{ %> value="<%=company %>" <%} %>readonly="readonly" class="STYLE1"/><span id="suggest1"></span>
            		<input type="hidden" name="companyID" id="companyID"  value="<%=companyID%>">
				</td>
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" name="depart1" id="depart1" onclick="FindDepart1()"readonly="readonly" class="STYLE1"/><span id="suggest2"></span>
					<input type="hidden" name="departID1" id="departID1"  value="">
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					�������ţ�
				</td>
				<td align="left">
					<input type="text" name="depart2" id="depart2" onclick="FindDepart2()"readonly="readonly" class="STYLE1"/><span id="suggest3"></span>
					<input type="hidden" name="departID2" id="departID2"  value="">
				</td>
				<td align="center">
					�ļ����ţ�
				</td>
				<td align="left">
					<input type="text" name="depart3" id="depart3" onclick="FindDepart3()"readonly="readonly" class="STYLE1"/><span id="suggest4"></span>
					<input type="hidden" name="departID3" id="departID3"  value="">
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					��λ��ְ�񣩣�
				</td>
				<td align="left">
					<input type="text" name="job" id="job" value="" class="STYLE1"/>
				</td>
				<td align="center">
					��λְ��
				</td>
				<td align="left" height="50px">
					<textarea rows="4" cols="45" name="jobdes" id="jobdes"></textarea>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center">
					��ɫ��
				</td>
				<td align="left">
					<select id="sel3" onchange="selChange3()"  class="STYLE1">
						<option value="һ���û�">&nbsp;&nbsp;һ���û�&nbsp;&nbsp;</option>
						<option value="��ϢԱ" style="color:green" >&nbsp;&nbsp;��ϢԱ&nbsp;&nbsp;</option>
						<option value="��������" style="color:green" >&nbsp;&nbsp;��������&nbsp;&nbsp;</option>
						<option value="��Ƹ�����" style="color:green" >&nbsp;&nbsp;��Ƹ�����&nbsp;&nbsp;</option>
						<option value="ͳ�Ƹ�����" style="color:green" >&nbsp;&nbsp;ͳ�Ƹ�����&nbsp;&nbsp;</option>
					</select>
					<input type="hidden" name="roles" id="roles" value="һ���û�">
				</td>
				<td align="center">
					�û�����
				</td>
				<td align="left">
					<input type="text" name="user" id="user" size="8" value="" class="STYLE1"/>
					<span style="color: red;">*</span>ֻ������ĸ�����»������
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" colspan="4">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="insertPeron();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		</form>
		<%} %>
  </body>
</html>
