<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CglibBean"%>
<%@page import="com.safety.entity.Njfb"%>
<%@page import="com.safety.entity.NjfbConsolidations"%>
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
   	ArrayList lnnjDTList = (ArrayList)request.getAttribute("lnnjDTList");
   	ArrayList njfbCLList = (ArrayList)request.getAttribute("njfbCLList");
   	Njfb njfb = (Njfb)request.getAttribute("njfb");
   	String bbls = (String)request.getAttribute("bbls");
   	String fatherid = (String)request.getAttribute("fatherid");
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�����޸�</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(){
        	var form = document.getElementById("form1");
			form.action = "LnnjServlet?action=lnnjSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
			form.action = "LnnjServlet?action=getNjfb";
			form.submit();
        }
       
        //������Ŀ
        function moreXM(ls){
        	var table2 = document.getElementById("table2");
        	var tr = document.createElement("tr");
        	table2.appendChild(tr);
        	for(var i=1; i<ls; i++){
        		var td = document.createElement("td");
        		td.align="center";
        		var input = document.createElement("input");
        		input.type = "text";
        		input.name = "zb"+i;
        		input.size = "6";
        		tr.appendChild(td);
        		td.appendChild(input);
        	}
        	var tde = document.createElement("td");
        	tde.align="center";
        	var inpute = document.createElement("input");
        	inpute.type = "text";
        	inpute.name = "zb"+ls;
        	inpute.size = "6";
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	tr.appendChild(tde);
        	tde.appendChild(inpute);
        	tde.appendChild(inputc);
        }
        //ɾ����Ŀ
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
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
		    var path = "tempFile/njfb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var sheetNum = document.getElementById("sheetNum").value;
        		var form = document.getElementById("form1");
				form.action = "LnnjServlet?action=readNjfbExcel&URL="+msg+"&sheetNum="+sheetNum;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
		}
	 	//������Ŀ(����)
        function moreCL(){
        	var table3 = document.getElementById("table3");
        	var tr = document.createElement("tr");
        	table3.appendChild(tr);
       		var td1 = document.createElement("td");
       		td1.align="center";
       		var input1 = document.createElement("input");
       		input1.type = "text";
       		input1.name = "row1";
       		input1.size = "5";
       		tr.appendChild(td1);
       		td1.appendChild(input1);
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "column1";
        	input2.size = "5";
       		tr.appendChild(td2);
       		td2.appendChild(input2);
        	var td3 = document.createElement("td");
       		td3.align="center";
       		var input3 = document.createElement("input");
       		input3.type = "text";
       		input3.name = "row2";
       		input3.size = "5";
       		tr.appendChild(td3);
       		td3.appendChild(input3);
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "column2";
        	input4.size = "5";
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	tr.appendChild(td4);
        	td4.appendChild(input4);
        	td4.appendChild(inputc);
        }
        function addrow1(){
        	var row1 = document.getElementsByName("row1");
	        for(var i=0;i<row1.length;i++){
         		//����ȫ������
	        	if(row1[i].value=="")row1[i].value=0;
	        	row1[i].value = Number(row1[i].value)+1;
        	}
        }
        function subrow1(){
        	var row1 = document.getElementsByName("row1");
	        for(var i=0;i<row1.length;i++){
         		//����ȫ������
	        	if(row1[i].value=="")row1[i].value=0;
	        	row1[i].value = Number(row1[i].value)-1;
        	}
        }
        function addcolumn1(){
        	var column1 = document.getElementsByName("column1");
	        for(var i=0;i<column1.length;i++){
         		//����ȫ������
	        	if(column1[i].value=="")column1[i].value=0;
	        	column1[i].value = Number(column1[i].value)+1;
        	}
        }
        function subcolumn1(){
        	var column1 = document.getElementsByName("column1");
	        for(var i=0;i<column1.length;i++){
         		//����ȫ������
	        	if(column1[i].value=="")column1[i].value=0;
	        	column1[i].value = Number(column1[i].value)-1;
        	}
        }
        function addrow2(){
        	var row2 = document.getElementsByName("row2");
	        for(var i=0;i<row2.length;i++){
         		//����ȫ������
	        	if(row2[i].value=="")row2[i].value=0;
	        	row2[i].value = Number(row2[i].value)+1;
        	}
        }
        function subrow2(){
        	var row2 = document.getElementsByName("row2");
	        for(var i=0;i<row2.length;i++){
         		//����ȫ������
	        	if(row2[i].value=="")row2[i].value=0;
	        	row2[i].value = Number(row2[i].value)-1;
        	}
        }
        function addcolumn2(){
        	var column2 = document.getElementsByName("column2");
	        for(var i=0;i<column2.length;i++){
         		//����ȫ������
	        	if(column2[i].value=="")column2[i].value=0;
	        	column2[i].value = Number(column2[i].value)+1;
        	}
        }
        function subcolumn2(){
        	var column2 = document.getElementsByName("column2");
	        for(var i=0;i<column2.length;i++){
         		//����ȫ������
	        	if(column2[i].value=="")column2[i].value=0;
	        	column2[i].value = Number(column2[i].value)-1;
        	}
        }
</script>
	</head>
	<body>
		<% if(request.getAttribute("result")!= null) {%>
		    <script>
		         alert('<%=request.getAttribute("result")%>');
		    </script> 
		<% } %>
		<form id="form1" method="post">
		<input type="hidden" name="njID" id="njID" <%if(njfb.getId()!=0){%>value="<%=njfb.getId()%>"<%}%> >
		<input type="hidden" name="fatherid" id="fatherid" value="<%=fatherid%>" >
		<input type="hidden" name="bbls" id="bbls" value="<%=bbls%>" >
			<table id="table1" width="100%">
				<tr>
					<td align="right" width="55%">
					���⣺<input type="text" name="bt" id="bt" size="30" <%if(njfb.getBt()!=null&&!"".equals(njfb.getBt())){%>value="<%=njfb.getBt()%>"<%}else{%>value=""<%}%> >
					</td>
					<td align="left">
					ʱ�䣺
					<select id="year" name="year">
						<%if(njfb.getYear()!=0){%>
						<option value="<%= njfb.getYear() %>"><%= njfb.getYear() %>��</option>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
						<%}else{ %>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					</td>
				</tr>
			</table>
			
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<%
					for(int i=0;i< lnnjDTList.size();i++){
						CglibBean bean = (CglibBean)lnnjDTList.get(i);
				%>
				<tr>
					<%
					int num = Integer.parseInt(bbls);
					for(int j=1;j<=num;j++){ %>
					<td align="center">
					<input name="zb<%= j %>" type="text" size="6" <%if(bean.getValue("zb"+j)!=null){%>value="<%=bean.getValue("zb"+j)%>"<%}%>/>
					<%if(num==j){%>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}%>
					</td>
					<%}%>
				</tr>
				<%} %>
			</table>
			<table  width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM(<%=bbls%>)">
					</td>
				</tr>
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<table id="table3" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="120px"><input type="button" value="+" onclick="addrow1()">����<input type="button" value="-" onclick="subrow1()">
					</td>
					<td align="center" width="120px"><input type="button" value="+" onclick="addcolumn1()">����<input type="button" value="-" onclick="subcolumn1()">
					</td>
					<td align="center" width="120px"><input type="button" value="+" onclick="addrow2()">����<input type="button" value="-" onclick="subrow2()">
					</td>
					<td align="center" width="200px"><input type="button" value="+" onclick="addcolumn2()">����<input type="button" value="-" onclick="subcolumn2()">
					</td>
				</tr>
				<%
					for(int i=0;i< njfbCLList.size();i++){
						NjfbConsolidations njfbCL = (NjfbConsolidations)njfbCLList.get(i);
				%>
				<tr>
					<td align="center">
					<input name="row1" type="text" size="5" <%if(njfbCL.getRow1()!=-1){%>value="<%=njfbCL.getRow1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="column1" type="text" size="5" <%if(njfbCL.getColumn1()!=-1){%>value="<%=njfbCL.getColumn1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="row2" type="text" size="5" <%if(njfbCL.getRow2()!=-1){%>value="<%=njfbCL.getRow2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="column2" type="text" size="5" <%if(njfbCL.getColumn2()!=-1){%>value="<%=njfbCL.getColumn2()%>"<%}%>/>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				<%} %>
			</table>
			<table  id="table1" width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreCL()">
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
		<form id="form3" method="post"  enctype="multipart/form-data" target="ifm">
		<table  width="100%">
			<tr>
				<td align="left">
				�ϴ�EXCEL��<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
				��<input id="sheetNum" type="text" size="5" value="1" />���ļ�
				<input type="button" class="button1" class="button1" onclick="infExcel()" value="����EXCEL" />
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		</form>
	</body>
	<%} %>
</html>



