<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbJtyssjdytj"%>
<%@page import="com.safety.entity.SjbbJtyssjdytjZxm"%>
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
	String workphone = UserInfo.getWorkphone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>������ͨ���䲿����ƶ���ͳ�Ʊ�</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
	   		var form = document.getElementById("form1");
			form.action = "BbsbSjbbServlet?action=JtyssjdytjSave";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbSjbbServlet?action=getJtyssjdytj&flag=1&menuname="+menuname;
			form.submit();
        }
        //����EXCEL
        function infExcel(){
	        var file = document.getElementById("file1").value;
	        if (file==""){
	        	alert("��ѡ���ϴ��ļ�");
	            return;
	        }
	        while (file.indexOf("\\") != -1){
	            file = file.slice(file.indexOf("\\") + 1);
	        }
	        var ext = file.slice(file.lastIndexOf(".")).toLowerCase();
	        if (ext==".xls"){
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
				if(size<=0){  
					alert("������С����Ϊ0M��");   
					return;
				}
				infoFile();
	        }else{ //����ϴ��ļ�����
	            alert("ֻ���ϴ�xls��ʽ���ļ�;\n������ѡ�����ϴ�.");
	        }
        }
        function infoFile(){
	   		var form = document.getElementById("form3");
		    var path = "tempFile/jtyssjdytj";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=readJtyssjdytjExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
		}
		   
        //������Ŀ
        function moreXM(num){
        	var table = document.getElementById("table"+num);
        	var tr = document.createElement("tr");
        	
        	var td1 = document.createElement("td");
        	td1.align="center";
        	td1.width="100px";
        	var input1 = document.createElement("input");
        	input1.type = "hidden";
        	input1.name = "lb";
        	
        	var td2 = document.createElement("td");
        	td2.align="center";
        	td2.width="200px";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "mc";
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	td3.width="100px";
        	var select3 = document.createElement("select");
        	select3.name = "dwxz";
        	select3.options.add(new Option("","")); 
        	select3.options.add(new Option("����","����")); 
        	select3.options.add(new Option("��ҵ","��ҵ")); 
        	select3.options.add(new Option("��ҵ","��ҵ")); 
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	td4.width="200px";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "sjnr";
        	
        	var td5 = document.createElement("td");
        	td5.align="center";
        	td5.width="150px";
        	var select5 = document.createElement("select");
        	select5.name = "sjfs";
        	select5.options.add(new Option("","")); 
        	select5.options.add(new Option("����","����")); 
        	select5.options.add(new Option("ί���н����","ί���н����")); 
        	
        	var td6 = document.createElement("td");
        	td6.align="center";
        	td6.width="150px";
        	var select6 = document.createElement("select");
        	select6.name = "xzfs";
        	select6.options.add(new Option("","")); 
        	select6.options.add(new Option("�����б�","�����б�")); 
        	select6.options.add(new Option("������̸��","������̸��")); 
        	select6.options.add(new Option("ָ��","ָ��")); 
        	select6.options.add(new Option("�����ȡ","�����ȡ"));
        	
        	var td7 = document.createElement("td");
        	td7.align="center";
        	td7.width="200px";
        	var input7 = document.createElement("input");
        	input7.type = "text";
        	input7.name = "sjsx";
        	
        	var td8 = document.createElement("td");
        	td8.align="center";
        	td8.width="100px";
        	var select8 = document.createElement("select");
        	select8.name = "ssdw";
        	select8.options.add(new Option("","")); 
        	select8.options.add(new Option("�����","�����")); 
        	select8.options.add(new Option("ʡ�����","ʡ�����")); 
        	select8.options.add(new Option("����ƾ�","����ƾ�"));
        	select8.options.add(new Option("����ƾ�","����ƾ�"));

        	var input9 = document.createElement("input");
        	input9.type = "hidden";
        	input9.name = "fl";
        	input9.value= num;
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	
        	table.appendChild(tr);
        	tr.appendChild(td1);
        	td1.appendChild(input1);
        	td1.appendChild(inputc);
        	tr.appendChild(td2);
        	td2.appendChild(input2);
        	tr.appendChild(td3);
        	td3.appendChild(select3);
        	tr.appendChild(td4);
        	td4.appendChild(input4);
        	tr.appendChild(td5);
        	td5.appendChild(select5);
        	tr.appendChild(td6);
        	td6.appendChild(select6);
        	tr.appendChild(td7);
        	td7.appendChild(input7);
        	tr.appendChild(td8);
        	td8.appendChild(select8);
        	td8.appendChild(input9);
        }
        //ɾ����Ŀ
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
		}
		
		function selChange1(obj){
			alert(obj.childNodes.tagName);
			var sel = document.getElementsByName("sel1");
			document.getElementById("education").value=sel.options[sel.selectedIndex].value;
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
         	SjbbJtyssjdytj sjbbJtyssjdytj = (SjbbJtyssjdytj)request.getAttribute("sjbbJtyssjdytj");
         		ArrayList SjbbJtyssjdytjZxmList = (ArrayList)request.getAttribute("SjbbJtyssjdytjZxmList");
    		
		%>
		<form id="form1" name="SjbbJtyssjdytj" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="SjbbJtyssjdytj_id" id="SjbbJtyssjdytj_id" <%if(sjbbJtyssjdytj.getId()!=0){%>value="<%=sjbbJtyssjdytj.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					��ͨ���䲿����ƶ���ͳ�Ʊ�
					</td>
				</tr>
				<tr>
					<td align="left">
						���λ��<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="1200px"  border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" colspan="2">��ƶ�������
					</td>
					<td align="center" rowspan="2">��λ����
					</td>
					<td align="center" colspan="3">2011��ȱ�����λ��������ƶ���չ�ڲ�������
					</td>
					<td align="center" colspan="2">2011�����ƶ��������ƻ���������
					</td>
				</tr>
				<tr>
					<td align="center">���
					</td>
					<td align="center">����
					</td>
					<td align="center">�������
					</td>
					<td align="center">��Ʒ�ʽ
					</td>
					<td align="center">�н����ѡ��ʽ
					</td>
					<td align="center">��ƻ����������
					</td>
					<td align="center">���ʵʩ��λ
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">��1��
					</td>
					<td align="center" width="200px">��2��
					</td>
					<td align="center" width="100px">��3��
					</td>
					<td align="center" width="200px">��4��
					</td>
					<td align="center" width="150px">��5��
					</td>
					<td align="center" width="150px">��6��
					</td>
					<td align="center" width="200px">��7��
					</td>
					<td align="center" width="100px">��8��
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
				<%
				for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
					SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
					if("1".equals(sjbbJtyssjdytjZxm.getFl())){
				%>
			<table width="1200px" id="table1" border=0  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" width="100px">
					������λ
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="����">����</option>
						<option value="��ҵ">��ҵ</option>
						<option value="��ҵ">��ҵ</option>
					</select>
					</td>
					<td align=center width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align=center width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="����">����</option>
						<option value="ί���н����">ί���н����</option>
					</select>
					</td>
					<td align=center width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="�����б�">�����б�</option>
						<option value="������̸��">������̸��</option>
						<option value="ָ��">ָ��</option>
						<option value="�����ȡ">�����ȡ</option>
					</select>
					</td>
					<td align=center width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="�����">�����</option>
						<option value="ʡ�����">ʡ�����</option>
						<option value="����ƾ�">����ƾ�</option>
						<option value="����ƾ�">����ƾ�</option>
					</select>
					<input name="fl" type="hidden" value="1"/>
					</td>
				</tr>
			</table>
				<%} %>
			<%} %>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%
			for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
				if("2".equals(sjbbJtyssjdytjZxm.getFl())){
			%>
			<table width="1200px" id="table2" border=0  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" width="100px">
					<%if(!"������λ".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					������λ
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="����">����</option>
						<option value="��ҵ">��ҵ</option>
						<option value="��ҵ">��ҵ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="����">����</option>
						<option value="ί���н����">ί���н����</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="�����б�">�����б�</option>
						<option value="������̸��">������̸��</option>
						<option value="ָ��">ָ��</option>
						<option value="�����ȡ">�����ȡ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="�����">�����</option>
						<option value="ʡ�����">ʡ�����</option>
						<option value="����ƾ�">����ƾ�</option>
						<option value="����ƾ�">����ƾ�</option>
					</select>
					<input name="fl" type="hidden" value="2"/>
					</td>
				</tr>
			</table>
				<%} %>
			<%} %>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM(2)">
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%
			for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
				if("3".equals(sjbbJtyssjdytjZxm.getFl())){
			%>
			<table width="1200px" id="table3" width="100%" border=0  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" width="100px">
					<%if(!"����������ϵ��λ".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					����������ϵ<br>��λ
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="����">����</option>
						<option value="��ҵ">��ҵ</option>
						<option value="��ҵ">��ҵ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="����">����</option>
						<option value="ί���н����">ί���н����</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="�����б�">�����б�</option>
						<option value="������̸��">������̸��</option>
						<option value="ָ��">ָ��</option>
						<option value="�����ȡ">�����ȡ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="�����">�����</option>
						<option value="ʡ�����">ʡ�����</option>
						<option value="����ƾ�">����ƾ�</option>
						<option value="����ƾ�">����ƾ�</option>
					</select>
					<input name="fl" type="hidden" value="3"/>
					</td>
				</tr>
			</table>
				<%} %>
			<%} %>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM(3)">
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%
			for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
				if("4".equals(sjbbJtyssjdytjZxm.getFl())){
			%>
			<table width="1200px" id="table4" width="100%" border=0  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" width="100px">
					<%if(!"������Ŀ".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					������Ŀ
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="����">����</option>
						<option value="��ҵ">��ҵ</option>
						<option value="��ҵ">��ҵ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="����">����</option>
						<option value="ί���н����">ί���н����</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="�����б�">�����б�</option>
						<option value="������̸��">������̸��</option>
						<option value="ָ��">ָ��</option>
						<option value="�����ȡ">�����ȡ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="�����">�����</option>
						<option value="ʡ�����">ʡ�����</option>
						<option value="����ƾ�">����ƾ�</option>
						<option value="����ƾ�">����ƾ�</option>
					</select>
					<input name="fl" type="hidden" value="4"/>
					</td>
				</tr>
			</table>
				<%} %>
			<%} %>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM(4)">
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%
			for(int i=0;i< SjbbJtyssjdytjZxmList.size();i++){
				SjbbJtyssjdytjZxm sjbbJtyssjdytjZxm = (SjbbJtyssjdytjZxm)SjbbJtyssjdytjZxmList.get(i);
				if("5".equals(sjbbJtyssjdytjZxm.getFl())){
			%>
			<table width="1200px" id="table5" width="100%" border=0  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" width="100px">
					<%if(!"ר���ʽ�".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					ר���ʽ�
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="����">����</option>
						<option value="��ҵ">��ҵ</option>
						<option value="��ҵ">��ҵ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="����">����</option>
						<option value="ί���н����">ί���н����</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="�����б�">�����б�</option>
						<option value="������̸��">������̸��</option>
						<option value="ָ��">ָ��</option>
						<option value="�����ȡ">�����ȡ</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="�����">�����</option>
						<option value="ʡ�����">ʡ�����</option>
						<option value="����ƾ�">����ƾ�</option>
						<option value="����ƾ�">����ƾ�</option>
					</select>
					<input name="fl" type="hidden" value="5"/>
					</td>
				</tr>
			</table>
				<%} %>
			<%} %>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM(5)">
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr height="1px">
					<td colspan="8">
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<table width="1200px">
				<tr>
					<td>
						&nbsp;&nbsp;����ˣ�<%=name %>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ϵ�绰��<input name="czrphone" type="text" size="10" <%if(sjbbJtyssjdytj.getCzrphone()!=null){%>value="<%=sjbbJtyssjdytj.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
					</td>
				</tr>
				<tr height="1px">
					<td>
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td>
						˵����
					</td>
				</tr>
				<tr>
					<td>
					1����������Ŀ�����б�����λ���мලְ��Ľ�����Ŀ��
					</td>
				</tr>
				<tr>
					<td>
					2��ר���ʽ�ġ���ƶ������ơ�����д������λ����ƻ��ؿ�չר���ʽ���������漰��λ���������δ��ר���ʽ������ƣ��������С�
					</td>
				</tr>
				<tr>
					<td>
					3���ڣ�3���е�λ����ѡ��������������ҵ������ҵ����
					</td>
				</tr>
				<tr>
					<td>
					4���ڣ�5����ѡ�����󡱻�ί���н������������ί���н���������ڵڣ�6������ѡ�񡰹����бꡱ�򡰾�����̸�С���ָ�����������ȡ����
					</td>
				</tr>
				<tr>
					<td>
					5���ڣ�8����ѡ������𡱻�ʡ�������������ƾ֡�������ƾ֡�
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center">
						<img alt="�ύ" width="45px" height="45px" title="�ύ" style="cursor: pointer;"  src="images/small/send.png" onclick="save(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img alt="��ݸ�" width="45px" height="45px" title="��ݸ�" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="save(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
	<%}%>
	</body>
</html>



