<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CglibBean"%>
<%@page import="com.safety.entity.Bbdzmb"%>
<%@page import="com.safety.entity.BbdzmbHb"%>
<%@page import="com.safety.entity.BbdzmbJs"%>
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
   	ArrayList BbdzmbHbList = (ArrayList)request.getAttribute("BbdzmbHbList");
   	ArrayList BbdzmbJsList = (ArrayList)request.getAttribute("BbdzmbJsList");
   	ArrayList BbdzmbDTList = (ArrayList)request.getAttribute("BbdzmbDTList");
   	Bbdzmb bbdzmb = (Bbdzmb)request.getAttribute("bbdzmb");
   	String dzls = (String)request.getAttribute("dzls");
   	String lx = (String)request.getAttribute("lx");
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
	        var bt = document.getElementById("bt").value;
	        if(bt==""){
	        	alert("�����뱨��ģ������");
	        	return;
	        }
        	var form = document.getElementById("form1");
			form.action = "BbdzServlet?action=saveBbdzmb";
			form.submit();
        }
        function cancel(){
	        var lx = document.getElementById("lx").value;
        	var form = document.getElementById("form2");
			form.action = "BbdzServlet?action=getBbdzmb&cxlx="+lx;
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
		    var path = "tempFile/bbdzmb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var sheetNum = document.getElementById("sheetNum").value;
        		var form = document.getElementById("form1");
				form.action = "BbdzServlet?action=readBbdzmbExcel&URL="+msg+"&sheetNum="+sheetNum;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
		}
	 	//������Ŀ(�ϲ�)
        function moreHb(){
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
        
	 	//������Ŀ(�ϲ�)
        function moreJs(){
        	var table4 = document.getElementById("table4");
        	var tr = document.createElement("tr");
        	table4.appendChild(tr);
        	
       		var td1 = document.createElement("td");
       		td1.align="center";
       		var input1 = document.createElement("input");
       		input1.type = "text";
       		input1.name = "rowjs1";
       		input1.size = "5";
       		tr.appendChild(td1);
       		td1.appendChild(input1);
       		
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "columnjs1";
        	input2.size = "5";
       		tr.appendChild(td2);
       		td2.appendChild(input2);
       		
        	var tdfh = document.createElement("td");
        	tdfh.align="center";
        	var selectfh = document.createElement("select");
        	selectfh.name = "jsfh";
        	selectfh.options.add(new Option("��","��")); 
        	selectfh.options.add(new Option("��","��")); 
        	selectfh.options.add(new Option("��","��"));
        	selectfh.options.add(new Option("��","��"));
       		tr.appendChild(tdfh);
       		tdfh.appendChild(selectfh);
        	
        	var td3 = document.createElement("td");
       		td3.align="center";
       		var input3 = document.createElement("input");
       		input3.type = "text";
       		input3.name = "rowjs2";
       		input3.size = "5";
       		tr.appendChild(td3);
       		td3.appendChild(input3);
       		
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "columnjs2";
        	input4.size = "5";
       		tr.appendChild(td4);
       		td4.appendChild(input4);
        	
        	var tdd = document.createElement("td");
       		tdd.align="center";
       		tdd.innerHTML="=";
       		tr.appendChild(tdd);
       		
        	var td5 = document.createElement("td");
       		td5.align="center";
       		var input5 = document.createElement("input");
       		input5.type = "text";
       		input5.name = "rowjs3";
       		input5.size = "5";
       		tr.appendChild(td5);
       		td5.appendChild(input5);
       		
        	var td6 = document.createElement("td");
        	td6.align="center";
        	var input6 = document.createElement("input");
        	input6.type = "text";
        	input6.name = "columnjs3";
        	input6.size = "5";
       		tr.appendChild(td6);
       		td6.appendChild(input6);
       		
        	var tdjs = document.createElement("td");
        	tdjs.align="center";
        	var selectjs = document.createElement("select");
        	selectjs.name = "jslx";
        	selectjs.options.add(new Option("����","����")); 
        	selectjs.options.add(new Option("����","����")); 
        	
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	tr.appendChild(tdjs);
        	tdjs.appendChild(selectjs);
        	tdjs.appendChild(inputc);
        }
         //������֤
        function verification(){
        	var rowjs1 = document.getElementsByName("rowjs1");
        	var columnjs1 = document.getElementsByName("columnjs1");
        	var rowjs2 = document.getElementsByName("rowjs2");
        	var columnjs2 = document.getElementsByName("columnjs2");
        	var rowjs3 = document.getElementsByName("rowjs3");
        	var columnjs3 = document.getElementsByName("columnjs3");
        	var jsfh = document.getElementsByName("jsfh");
        	var jslx = document.getElementsByName("jslx");
         	for(i=0;i<rowjs1.length;i++){
         		if(jslx[i].value=="����"){
         			//A��
         			var AH = document.getElementsByName("zb"+columnjs1[i].value);
         			var A = AH[rowjs1[i].value-1].value;
         			//B��
         			var BH = document.getElementsByName("zb"+columnjs2[i].value);
         			var B = BH[rowjs2[i].value-1].value;
         			//���
         			var CH = document.getElementsByName("zb"+columnjs3[i].value);
         			if(jsfh[i].value=="��"){
         				CH[rowjs3[i].value-1].value = (Number(A)+Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="��"){
         				CH[rowjs3[i].value-1].value = (Number(A)-Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="��"){
         				CH[rowjs3[i].value-1].value = (Number(A)*Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="��"){
         				CH[rowjs3[i].value-1].value = (Number(A)/Number(B)).toFixed(2);
         			}
         		}
         		else{
         			//A��
         			var AH1 = document.getElementsByName("zb"+columnjs1[i].value);
         			var A1 = 0;
         			//B��
         			var BH1 = document.getElementsByName("zb"+columnjs2[i].value);
         			var B1 = 0;
         			//���
         			var CH1 = document.getElementsByName("zb"+columnjs3[i].value);
         			
         			for(var j=0;j<=AH1.length-rowjs1[i].value&&j<=BH1.length-rowjs2[i].value&&j<=CH1.length-rowjs3[i].value;j++ ){
	         			A1 = AH1[rowjs1[i].value-1+j].value;
	         			B1 = BH1[rowjs2[i].value-1+j].value;
	         			if(jsfh[i].value=="��"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)+Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="��"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)-Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="��"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)*Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="��"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)/Number(B1)).toFixed(2);
	         			}
         			}
         		}
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
		<input type="hidden" name="id" id="id" <%if(bbdzmb.getId()!=0){%>value="<%=bbdzmb.getId()%>"<%}%> >
		<input type="hidden" name="lx" id="lx" value="<%=lx%>" >
		<input type="hidden" name="dzls" id="dzls" value="<%=dzls%>" >
			<table id="table1" width="100%">
				<tr>
					<td align="center">
					����ģ�����ƣ�<input type="text" name="bt" id="bt" size="30" <%if(bbdzmb.getBt()!=null&&!"".equals(bbdzmb.getBt())){%>value="<%=bbdzmb.getBt()%>"<%}else{%>value=""<%}%> >
					</td>
				</tr>
			</table>
			
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<%
					for(int h=1;h<=Integer.parseInt(dzls);h++){ %>
					<td align="center">
					��<%=h%>
					</td>
					<%}%>
				</tr>
				<%
					for(int i=0;i< BbdzmbDTList.size();i++){
						CglibBean bean = (CglibBean)BbdzmbDTList.get(i);
				%>
				<tr>
					<%
					int num = Integer.parseInt(dzls);
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
					<input type="button" class="button1" value="��������ģ��" onclick="moreXM(<%=dzls%>)">
					</td>
				</tr>
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr >
					<td align="center">
						�����ϲ���
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
					for(int i=0;i< BbdzmbHbList.size();i++){
						BbdzmbHb bbdzmbHb = (BbdzmbHb)BbdzmbHbList.get(i);
				%>
				<tr>
					<td align="center">
					<input name="row1" type="text" size="5" <%if(bbdzmbHb.getRow1()!=-1){%>value="<%=bbdzmbHb.getRow1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="column1" type="text" size="5" <%if(bbdzmbHb.getColumn1()!=-1){%>value="<%=bbdzmbHb.getColumn1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="row2" type="text" size="5" <%if(bbdzmbHb.getRow2()!=-1){%>value="<%=bbdzmbHb.getRow2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="column2" type="text" size="5" <%if(bbdzmbHb.getColumn2()!=-1){%>value="<%=bbdzmbHb.getColumn2()%>"<%}%>/>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				<%} %>
			</table>
			<table  id="table1" width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="�����ϲ���" onclick="moreHb()">
					</td>
				</tr>
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr >
					<td align="center">
						����������
					</td>
				</tr>
			</table>
			<table id="table4" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="80px">A��
					</td>
					<td align="center" width="80px">A��
					</td>
					<td align="center" width="50px">����
					</td>
					<td align="center" width="80px">B��
					</td>
					<td align="center" width="80px">B��
					</td>
					<td align="center" width="50px">����
					</td>
					<td align="center" width="80px">C��
					</td>
					<td align="center" width="80px">C��
					</td>
					<td align="center" width="150px">����
					</td>
				</tr>
				<%
					for(int i=0;i< BbdzmbJsList.size();i++){
						BbdzmbJs bbdzmbJs = (BbdzmbJs)BbdzmbJsList.get(i);
				%>
				<tr>
					<td align="center">
					<input name="rowjs1" type="text" size="5" <%if(bbdzmbJs.getRow1()!=-1){%>value="<%=bbdzmbJs.getRow1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="columnjs1" type="text" size="5" <%if(bbdzmbJs.getColumn1()!=-1){%>value="<%=bbdzmbJs.getColumn1()%>"<%}%>/>
					</td>
					<td align="center">
					<select name="jsfh">
						<option value="<%=bbdzmbJs.getFh() %>"><%=bbdzmbJs.getFh() %></option>
						<option value="��">��</option>
						<option value="��">��</option>
						<option value="��">��</option>
						<option value="��">��</option>
					</select>
					</td>
					<td align="center">
					<input name="rowjs2" type="text" size="5" <%if(bbdzmbJs.getRow2()!=-1){%>value="<%=bbdzmbJs.getRow2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="columnjs2" type="text" size="5" <%if(bbdzmbJs.getColumn2()!=-1){%>value="<%=bbdzmbJs.getColumn2()%>"<%}%>/>
					</td>
					<td align="center">=
					</td>
					<td align="center">
					<input name="rowjs3" type="text" size="5" <%if(bbdzmbJs.getRow3()!=-1){%>value="<%=bbdzmbJs.getRow3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="columnjs3" type="text" size="5" <%if(bbdzmbJs.getColumn3()!=-1){%>value="<%=bbdzmbJs.getColumn3()%>"<%}%>/>
					</td>
					<td align="center">
					<select name="jslx">
						<option value="<%=bbdzmbJs.getLx() %>"><%=bbdzmbJs.getLx() %></option>
						<option value="����">����</option>
						<option value="����">����</option>
					</select>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				<%} %>
			</table>
			<table  id="table1" width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="����������" onclick="moreJs()">
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
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



