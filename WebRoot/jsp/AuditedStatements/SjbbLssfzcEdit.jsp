<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbLssfzc"%>
<%@page import="com.safety.entity.SjbbLssfzcZxm"%>
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
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�����м����أ���������ҵ��λ����ʵ�շ����߼����Բ��</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
	   		var form = document.getElementById("form1");
			form.action = "BbsbSjbbServlet?action=lssfzczcbSave";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbSjbbServlet?action=getLssfzczcb&flag=1&menuname="+menuname;
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
		    var path = "tempFile/lssfzczcb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=readLssfzczcbExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
		}
		   
        //������Ŀ
        function moreXM(){
        	var table = document.getElementById("table1");
        	var tr = document.createElement("tr");
        	var td1 = document.createElement("td");
        	td1.align="center";
        	td1.width="150px";
        	td1.innerHTML = "<%=company%>";
        	var input1 = document.createElement("input");
        	input1.type = "hidden";
        	input1.value = "<%=company%>";
        	input1.name = "zb1";
        	
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "zb2";
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	var input3 = document.createElement("input");
        	input3.type = "text";
        	input3.name = "zb3";
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "zb4";
        	
        	var td5 = document.createElement("td");
        	td5.align="center";
        	var input5 = document.createElement("input");
        	input5.type = "text";
        	input5.name = "zb5";
        	
        	var td6 = document.createElement("td");
        	td6.align="center";
        	var input6 = document.createElement("input");
        	input6.type = "text";
        	input6.name = "zb6";
        	
        	var td7 = document.createElement("td");
        	td7.align="center";
        	var input7 = document.createElement("input");
        	input7.type = "text";
        	input7.name = "zb7";
        	
        	var td8 = document.createElement("td");
        	td8.align="center";
        	var input8 = document.createElement("input");
        	input8.type = "text";
        	input8.name = "zb8";
        	
        	var td9 = document.createElement("td");
        	td9.align="center";
        	var input9 = document.createElement("input");
        	input9.type = "text";
        	input9.name = "zb9";
        	
        	var td10 = document.createElement("td");
        	td10.align="center";
        	var input10 = document.createElement("input");
        	input10.type = "text";
        	input10.name = "zb10";
        	
        	var td11 = document.createElement("td");
        	td11.align="center";
        	var input11 = document.createElement("input");
        	input11.type = "text";
        	input11.name = "zb11";
        	
        	var td12 = document.createElement("td");
        	td12.align="center";
        	var input12 = document.createElement("input");
        	input12.type = "text";
        	input12.name = "zb12";
        	
        	var td13 = document.createElement("td");
        	td13.align="center";
        	var input13 = document.createElement("input");
        	input13.type = "text";
        	input13.name = "zb13";
        	
        	var td14 = document.createElement("td");
        	td14.align="center";
        	var input14 = document.createElement("input");
        	input14.type = "text";
        	input14.name = "zb14";
        	
        	
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "ɾ������";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	
        	table.appendChild(tr);
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
        	tr.appendChild(td9);
        	td9.appendChild(input9);
        	tr.appendChild(td10);
        	td10.appendChild(input10);
        	tr.appendChild(td11);
        	td11.appendChild(input11);
        	tr.appendChild(td12);
        	td12.appendChild(input12);
        	tr.appendChild(td13);
        	td13.appendChild(input13);
        	tr.appendChild(td14);
        	td14.appendChild(input14);
        	
        	td14.appendChild(inputc);
        }
        //ɾ����Ŀ
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
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
         	SjbbLssfzc sjbbLssfzc = (SjbbLssfzc)request.getAttribute("sjbbLssfzc");
         		ArrayList SjbbLssfzcZxmList = (ArrayList)request.getAttribute("sjbbLssfzcZxmList");
    		
		%>
		<form id="form1" name="SjbbLssfzc" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="SjbbLssfzc_id" id="SjbbLssfzc_id" <%if(sjbbLssfzc.getId()!=0){%>value="<%=sjbbLssfzc.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;">
					�м����أ���������ҵ��λ����ʵ�շ����߼����Բ��
					
					<select name="year" id="year">
						<%if(sjbbLssfzc.getYear()!=0){%>
						<option value="<%= sjbbLssfzc.getYear() %>"><%= sjbbLssfzc.getYear() %>��</option>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
						<%}else{ %>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					<select name="jd" id="jd">
						<%if(sjbbLssfzc.getJd()==0){%>
						<option value="<%= sjbbLssfzc.getJd() %>">ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
						<%}else{ %>
						<option value="<%= sjbbLssfzc.getJd() %>"><%= sjbbLssfzc.getJd() %>����</option>
						<option value="0">ȫ��</option>
						<option value="1">1����</option>
						<option value="2">2����</option>
						<option value="3">3����</option>
						<option value="4">4����</option>
						<%}%>
					</select>
					��
					</td>
				</tr>
				<tr>
					<td align="left">
						���͵�λ��<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="1300px" id="table1"  border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" rowspan="2" width="150px">�շ�<br>��λ
					</td>
					<td align="center" colspan="5" width="600px">ʵ���շ����
					</td>
					<td align="center" colspan="4" width="230px">�շѼ���������ʵ���
					</td>
					<td align="center" colspan="3" width="300px">��������
					</td>
					<td align="center" rowspan="2" width="20px">��<br>ע
					</td>
				</tr>
				<tr>
					<td align="center" width="200px">�շ�<br>��Ŀ
					</td>
					<td align="center" width="50px">�շ�<br>��׼
					</td>
					<td align="center" width="50px">�շ�<br>����
					</td>
					<td align="center" width="200px">�շ�<br>����
					</td>
					<td align="center" width="100px">�շ�<br>���<br>����Ԫ��
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>��
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>ʽ
					</td>
					<td align="center" width="50px">��<br>��<br>��<br>��
					</td>
					<td align="center" width="80px">����<br>���<br>����Ԫ��
					</td>
					<td align="center" width="100px">����<br>δִ��<br>��������<br>�շѹ涨
					</td>
					<td align="center" width="100px">�Ƿ�<br>������<br>��������<br>�շ�
					</td>
					<td align="center" width="50px">����<br>�<br>�շ���Ϊ
					</td>
				</tr>
				<%
				for(int i=0;i< SjbbLssfzcZxmList.size();i++){
					SjbbLssfzcZxm sjbbJtyssjdytjZxm = (SjbbLssfzcZxm)SjbbLssfzcZxmList.get(i);
				%>
				<tr>
					<td align="center">
					<%=sjbbJtyssjdytjZxm.getZb1()%>
					<input name="zb1" type="hidden" <%if(sjbbJtyssjdytjZxm.getZb1()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb2" type="text" <%if(sjbbJtyssjdytjZxm.getZb2()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb3" type="text" <%if(sjbbJtyssjdytjZxm.getZb3()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb4" type="text" <%if(sjbbJtyssjdytjZxm.getZb4()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb4()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb5" type="text" <%if(sjbbJtyssjdytjZxm.getZb5()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb5()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb6" type="text" <%if(sjbbJtyssjdytjZxm.getZb6()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb6()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb7" type="text" <%if(sjbbJtyssjdytjZxm.getZb7()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb7()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb8" type="text" <%if(sjbbJtyssjdytjZxm.getZb8()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb8()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb9" type="text" <%if(sjbbJtyssjdytjZxm.getZb9()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb9()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb10" type="text" <%if(sjbbJtyssjdytjZxm.getZb10()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb10()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb11" type="text" <%if(sjbbJtyssjdytjZxm.getZb11()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb11()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb12" type="text" <%if(sjbbJtyssjdytjZxm.getZb12()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb12()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb13" type="text" <%if(sjbbJtyssjdytjZxm.getZb13()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb13()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zb14" type="text" <%if(sjbbJtyssjdytjZxm.getZb14()!=null){%>value="<%=sjbbJtyssjdytjZxm.getZb14()%>"<%}%>/>
					<input type="button" class="button1" value="ɾ������" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				<%} %>
			</table>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="������Ŀ" onclick="moreXM()">
					</td>
				</tr>
			</table>
			<table width="1300px">
				<tr height="1px">
					<td>
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td>
					˵����1����������������������λ���ݣ���ȷ��д������ÿ�����¸��µ�10��ǰ������۾��շѹ�����7��10�ձ�1-6�·����ݣ�����۾��շѴ����䣺ntwjjsfc@163.com����ϵ�绰��85099592,85216544��
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2�����շ�ʵ���㱨���ơ�
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ˣ�<%=name %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ϵ�绰��<input name="czrphone" type="text" size="10" <%if(sjbbLssfzc.getCzrphone()!=null){%>value="<%=sjbbLssfzc.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
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
				<input type="button" class="button1" class="button1" onclick="infExcel()" value="����EXCEL" />
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
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
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		</form>
	<%}%>
	</body>
</html>



