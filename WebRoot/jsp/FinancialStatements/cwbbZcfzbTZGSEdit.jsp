<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbZcfzb"%>
<%@page import="com.safety.entity.CwbbZcfzbzc"%>
<%@page import="com.safety.entity.CwbbZcfzbfz"%>
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
    <title>�����޸��ʲ���ծ��</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
				if(verification()==1){
					alert("��У����ٱ���");
					return;
				}
			}
	   		var sbsj = document.getElementById("sbsj").value;
	   		if(sbsj==""){
	   			alert("����������");
	   			return;
	   		}
	   		var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=ZcfzbSave";
			form.submit();
	    }
	    function addCommas(nStr)  
        {  
         	nStr = nStr.replace(/,/g,"");//ɾ�����ж���;
         	var rgx1 = /^\-?\d+(\.\d+)?$/;
         	if(!rgx1.test(nStr)&&nStr!="") {  
            	alert("��������!");
            	return "";
            } 
        	nStr = Number(nStr).toFixed(2);
            nStr += '';  
            x = nStr.split('.');  
            x1 = x[0];  
            x2 = x.length > 1 ? '.' + x[1] : '';  
            var rgx2 = /(\d+)(\d{3})/;  
            while (rgx2.test(x1)) {  
                x1 = x1.replace(rgx2, '$1' + ',' + '$2');  
            }  
            return x1 + x2;  
        }  
	    
	    function addCommas2(nStr)  
        {  
            nStr += '';  
            x = nStr.split('.');  
            x1 = x[0];  
            x2 = x.length > 1 ? '.' + x[1] : '';  
            var rgx2 = /(\d+)(\d{3})/;  
            while (rgx2.test(x1)) {  
                x1 = x1.replace(rgx2, '$1' + ',' + '$2');  
            }  
            return x1 + x2;  
        }
		//������֤
        function verification(){
        	var ncs1 = document.getElementsByName("ncs1");
        	var qms1 = document.getElementsByName("qms1");
         	for(var i=0;i<ncs1.length;i++){
         		ncs1[i].value = ncs1[i].value.replace(/,/g,"");//ɾ�����ж���;
         		qms1[i].value = qms1[i].value.replace(/,/g,"");//ɾ�����ж���;
         		//����ȫ������
	        	if(ncs1[i].value=="")ncs1[i].value=0;
	        	if(qms1[i].value=="")qms1[i].value=0;
        	}
        	ncs1[0].value="";
        	ncs1[15].value="";
        	ncs1[19].value="";
        	ncs1[29].value="";
        	ncs1[34].value="";
        	ncs1[35].value="";
        	qms1[0].value="";
        	qms1[15].value="";
        	qms1[19].value="";
        	qms1[29].value="";
        	qms1[34].value="";
        	qms1[35].value="";
        	//�ʲ������
        	ncs1[14].value = (Number(ncs1[1].value)+Number(ncs1[2].value)+Number(ncs1[3].value)+Number(ncs1[4].value)+Number(ncs1[5].value)+Number(ncs1[6].value)+Number(ncs1[7].value)+Number(ncs1[8].value)+Number(ncs1[9].value)+Number(ncs1[10].value)+Number(ncs1[11].value)+Number(ncs1[12].value)+Number(ncs1[13].value)).toFixed(2);
        	ncs1[18].value = (Number(ncs1[16].value)+Number(ncs1[17].value)).toFixed(2);
        	ncs1[22].value = (Number(ncs1[20].value)-Number(ncs1[21].value)).toFixed(2);
        	ncs1[24].value = (Number(ncs1[22].value)-Number(ncs1[23].value)).toFixed(2);
        	ncs1[28].value = (Number(ncs1[24].value)+Number(ncs1[25].value)+Number(ncs1[26].value)+Number(ncs1[27].value)).toFixed(2);
        	ncs1[33].value = (Number(ncs1[30].value)+Number(ncs1[31].value)+Number(ncs1[32].value)).toFixed(2);
        	ncs1[37].value = (Number(ncs1[14].value)+Number(ncs1[18].value)+Number(ncs1[28].value)+Number(ncs1[33].value)+Number(ncs1[36].value)).toFixed(2);
        	//�ʲ���ĩ��
        	qms1[14].value = (Number(qms1[1].value)+Number(qms1[2].value)+Number(qms1[3].value)+Number(qms1[4].value)+Number(qms1[5].value)+Number(qms1[6].value)+Number(qms1[7].value)+Number(qms1[8].value)+Number(qms1[9].value)+Number(qms1[10].value)+Number(qms1[11].value)+Number(qms1[12].value)+Number(qms1[13].value)).toFixed(2);
        	qms1[18].value = (Number(qms1[16].value)+Number(qms1[17].value)).toFixed(2);
        	qms1[22].value = (Number(qms1[20].value)-Number(qms1[21].value)).toFixed(2);
        	qms1[24].value = (Number(qms1[22].value)-Number(qms1[23].value)).toFixed(2);
        	qms1[28].value = (Number(qms1[24].value)+Number(qms1[25].value)+Number(qms1[26].value)+Number(qms1[27].value)).toFixed(2);
        	qms1[33].value = (Number(qms1[30].value)+Number(qms1[31].value)+Number(qms1[32].value)).toFixed(2);
        	qms1[37].value = (Number(qms1[14].value)+Number(qms1[18].value)+Number(qms1[28].value)+Number(qms1[33].value)+Number(qms1[36].value)).toFixed(2);
        	//�Ӷ���
        	for(var j=0;j<ncs1.length;j++){
         		ncs1[j].value = addCommas2(ncs1[j].value);
         		qms1[j].value = addCommas2(qms1[j].value);
        	}
        	
        	var ncs2 = document.getElementsByName("ncs2");
        	var qms2 = document.getElementsByName("qms2");
         	for(var k=0;k<ncs2.length;k++){
         		ncs2[k].value = ncs2[k].value.replace(/,/g,"");//ɾ�����ж���;
         		qms2[k].value = qms2[k].value.replace(/,/g,"");//ɾ�����ж���;
         		//����ȫ������
	        	if(ncs2[k].value=="")ncs2[k].value=0;
	        	if(qms2[k].value=="")qms2[k].value=0;
        	}
        	ncs2[0].value="";
        	ncs2[15].value="";
        	ncs2[17].value="";
        	ncs2[24].value="";
        	ncs2[27].value="";
        	ncs2[28].value="";
        	qms2[0].value="";
        	qms2[15].value="";
        	qms2[17].value="";
        	qms2[24].value="";
        	qms2[27].value="";
        	qms2[28].value="";
        	//��ծ�����
        	ncs2[16].value = (Number(ncs2[1].value)+Number(ncs2[2].value)+Number(ncs2[3].value)+Number(ncs2[4].value)+Number(ncs2[5].value)+Number(ncs2[6].value)+Number(ncs2[7].value)+Number(ncs2[8].value)+Number(ncs2[9].value)+Number(ncs2[10].value)+Number(ncs2[11].value)+Number(ncs2[12].value)+Number(ncs2[13].value)+Number(ncs2[14].value)).toFixed(2);
        	ncs2[23].value = (Number(ncs2[18].value)+Number(ncs2[19].value)+Number(ncs2[20].value)+Number(ncs2[21].value)+Number(ncs2[22].value)).toFixed(2);
        	ncs2[26].value = (Number(ncs2[16].value)+Number(ncs2[23].value)+Number(ncs2[25].value)).toFixed(2);
        	ncs2[31].value = (Number(ncs2[29].value)-Number(ncs2[30].value)).toFixed(2);
        	ncs2[36].value = (Number(ncs2[31].value)+Number(ncs2[32].value)+Number(ncs2[33].value)+Number(ncs2[35].value)).toFixed(2);
        	ncs2[37].value = (Number(ncs2[26].value)+Number(ncs2[36].value)).toFixed(2);
        	//��ծ��ĩ��
        	qms2[16].value = (Number(qms2[1].value)+Number(qms2[2].value)+Number(qms2[3].value)+Number(qms2[4].value)+Number(qms2[5].value)+Number(qms2[6].value)+Number(qms2[7].value)+Number(qms2[8].value)+Number(qms2[9].value)+Number(qms2[10].value)+Number(qms2[11].value)+Number(qms2[12].value)+Number(qms2[13].value)+Number(qms2[14].value)).toFixed(2);
        	qms2[23].value = (Number(qms2[18].value)+Number(qms2[19].value)+Number(qms2[20].value)+Number(qms2[21].value)+Number(qms2[22].value)).toFixed(2);
        	qms2[26].value = (Number(qms2[16].value)+Number(qms2[23].value)+Number(qms2[25].value)).toFixed(2);
        	qms2[31].value = (Number(qms2[29].value)-Number(qms2[30].value)).toFixed(2);
        	qms2[36].value = (Number(qms2[31].value)+Number(qms2[32].value)+Number(qms2[33].value)+Number(qms2[35].value)).toFixed(2);
        	qms2[37].value = (Number(qms2[26].value)+Number(qms2[36].value)).toFixed(2);
        	//�Ӷ���
        	for(var l=0;l<ncs2.length;l++){
         		ncs2[l].value = addCommas2(ncs2[l].value);
         		qms2[l].value = addCommas2(qms2[l].value);
        	}
        	if(ncs1[37].value!=ncs2[37].value){
        		ncs1[37].style.color='red';
        		ncs2[37].style.color='red';
        		return 1;
        	}else{
        		ncs1[37].style.color='black';
        		ncs2[37].style.color='black';
        	}
        	if(qms1[37].value!=qms2[37].value){
        		qms1[37].style.color='red';
        		qms2[37].style.color='red';
        		return 1;
        	}else{
        		qms1[37].style.color='black';
        		qms2[37].style.color='black';
        	}
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbCwbbServlet?action=getZcfzb&flag=1&menuname="+menuname;
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
		    var path = "tempFile/zcfzb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readZcfzbTZGSExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
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
		<%
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
         	CwbbZcfzb cwbbZcfzb = (CwbbZcfzb)request.getAttribute("cwbbZcfzb");
    		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = (ArrayList)request.getAttribute("CwbbZcfzbzcList");
    		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = (ArrayList)request.getAttribute("CwbbZcfzbfzList");
    		
		%>
		<form id="form1" name="CwbbZcfzb" method="post">
		<input type="hidden" name="cwbbZcfzb_id" id="cwbbZcfzb_id" <%if(cwbbZcfzb.getId()!=0){%>value="<%=cwbbZcfzb.getId()%>"<%}%> >
		<input type="hidden" name="hzbz" id="hzbz" value="TZGS" >
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="3" style="font-weight: bold;">
					<input name="bt" type="text" size="20" <%if(cwbbZcfzb.getBt()!=null){%>value="<%=cwbbZcfzb.getBt()%>"<%}else{%>value="�ʲ���ծ��"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" width="30%">
						���λ��<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="center" width="30%">
						ʱ�䣺<input name="sbsj" id="sbsj" type="text" size="10" <%if(cwbbZcfzb.getSbsj()!=null){%>value="<%=cwbbZcfzb.getSbsj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.CwbbZcfzb.sbsj);" type="button" value="ѡ��">
					</td>
					<td align="right" width="30%">
						��λ��Ԫ
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="15%">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</td>
					<td align="center" width="5%">�д�
					</td>
					<td align="center" width="15%">�����
					</td>
					<td align="center" width="15%">��ĩ��
					</td>
					<td align="center" width="15%">��ծ��������Ȩ��<br>����ɶ�Ȩ�棩
					</td>
					<td align="center" width="5%">�д�
					</td>
					<td align="center" width="15%">�����
					</td>
					<td align="center" width="15%">��ĩ��
					</td>
				</tr>
				<tr>
					<td align="left">�����ʲ���
					<input name="mc1" type="hidden" value="�����ʲ���"/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">������ծ��
					<input name="mc2" type="hidden" value="������ծ��"/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ʽ�
					<input name="mc1" type="hidden" value="�����ʽ�"/>
					</td>
					<td align="center">1
					<input name="hc1" type="hidden" value="1"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(1).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(1).getQms()%>"<%}%>/>
					</td>
					<td align="left">���ڽ��
					<input name="mc2" type="hidden" value="���ڽ��"/>
					</td>
					<td align="center">33
					<input name="hc2" type="hidden" value="34"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(1).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(1).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����Ͷ��
					<input name="mc1" type="hidden" value="����Ͷ��"/>
					</td>
					<td align="center">2
					<input name="hc1" type="hidden" value="2"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(2).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(2).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ��Ʊ��
					<input name="mc2" type="hidden" value="Ӧ��Ʊ��"/>
					</td>
					<td align="center">34
					<input name="hc2" type="hidden" value="35"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(2).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(2).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ӧ��Ʊ��
					<input name="mc1" type="hidden" value="Ӧ��Ʊ��"/>
					</td>
					<td align="center">3
					<input name="hc1" type="hidden" value="3"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(3).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(3).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ���˿�
					<input name="mc2" type="hidden" value="Ӧ���˿�"/>
					</td>
					<td align="center">35
					<input name="hc2" type="hidden" value="36"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(3).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(3).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ӧ�չ���
					<input name="mc1" type="hidden" value="Ӧ�չ���"/>
					</td>
					<td align="center">4
					<input name="hc1" type="hidden" value="4"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(4).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(4).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ԥ���˿�
					<input name="mc2" type="hidden" value="Ԥ���˿�"/>
					</td>
					<td align="center">36
					<input name="hc2" type="hidden" value="37"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(4).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(4).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ӧ����Ϣ
					<input name="mc1" type="hidden" value="Ӧ����Ϣ"/>
					</td>
					<td align="center">5
					<input name="hc1" type="hidden" value="5"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(5).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(5).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ������
					<input name="mc2" type="hidden" value="Ӧ������"/>
					</td>
					<td align="center">37
					<input name="hc2" type="hidden" value="38"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(5).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(5).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ӧ���˿�
					<input name="mc1" type="hidden" value="Ӧ���˿�"/>
					</td>
					<td align="center">6
					<input name="hc1" type="hidden" value="6"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(6).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(6).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ��������
					<input name="mc2" type="hidden" value="Ӧ��������"/>
					</td>
					<td align="center">38
					<input name="hc2" type="hidden" value="39"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(6).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(6).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����Ӧ�տ�
					<input name="mc1" type="hidden" value="����Ӧ�տ�"/>
					</td>
					<td align="center">7
					<input name="hc1" type="hidden" value="7"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(7).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(7).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ������
					<input name="mc2" type="hidden" value="Ӧ������"/>
					</td>
					<td align="center">39
					<input name="hc2" type="hidden" value="40"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(7).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(7).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ԥ���˿�
					<input name="mc1" type="hidden" value="Ԥ���˿�"/>
					</td>
					<td align="center">8
					<input name="hc1" type="hidden" value="8"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(8).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(8).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ӧ��˰��
					<input name="mc2" type="hidden" value="Ӧ��˰��"/>
					</td>
					<td align="center">40
					<input name="hc2" type="hidden" value="41"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(8).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(8).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">Ӧ�ղ�����
					<input name="mc1" type="hidden" value="Ӧ�ղ�����"/>
					</td>
					<td align="center">9
					<input name="hc1" type="hidden" value="9"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(9).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(9).getQms()%>"<%}%>/>
					</td>
					<td align="left">����Ӧ����
					<input name="mc2" type="hidden" value="����Ӧ����"/>
					</td>
					<td align="center">41
					<input name="hc2" type="hidden" value="42"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(9).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(9).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">���
					<input name="mc1" type="hidden" value="���"/>
					</td>
					<td align="center">10
					<input name="hc1" type="hidden" value="10"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(10).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(10).getQms()%>"<%}%>/>
					</td>
					<td align="left">����Ӧ����
					<input name="mc2" type="hidden" value="����Ӧ����"/>
					</td>
					<td align="center">42
					<input name="hc2" type="hidden" value="43"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(10).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(10).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��̯����
					<input name="mc1" type="hidden" value="��̯����"/>
					</td>
					<td align="center">11
					<input name="hc1" type="hidden" value="11"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(11).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(11).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ԥ�����
					<input name="mc2" type="hidden" value="Ԥ�����"/>
					</td>
					<td align="center">43
					<input name="hc2" type="hidden" value="44"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(11).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(11).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">һ���ڵ��ڵĳ���ծȨͶ��
					<input name="mc1" type="hidden" value="һ���ڵ��ڵĳ���ծȨͶ��"/>
					</td>
					<td align="center">12
					<input name="hc1" type="hidden" value="12"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(12).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(12).getQms()%>"<%}%>/>
					</td>
					<td align="left">Ԥ�Ƹ�ծ
					<input name="mc2" type="hidden" value="Ԥ�Ƹ�ծ"/>
					</td>
					<td align="center">44
					<input name="hc2" type="hidden" value="45"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(12).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(12).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">���������ʲ�
					<input name="mc1" type="hidden" value="���������ʲ�"/>
					</td>
					<td align="center">13
					<input name="hc1" type="hidden" value="13"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(13).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(13).getQms()%>"<%}%>/>
					</td>
					<td align="left">һ���ڵ��ڵĳ��ڸ�ծ
					<input name="mc2" type="hidden" value="һ���ڵ��ڵĳ��ڸ�ծ"/>
					</td>
					<td align="center">45
					<input name="hc2" type="hidden" value="46"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(13).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(13).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ʲ��ϼ�
					<input name="mc1" type="hidden" value="�����ʲ��ϼ�"/>
					</td>
					<td align="center">14
					<input name="hc1" type="hidden" value="14"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(14).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(14).getQms()%>"<%}%>/>
					</td>
					<td align="left">����������ծ
					<input name="mc2" type="hidden" value="����������ծ"/>
					</td>
					<td align="center">46
					<input name="hc2" type="hidden" value="47"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(14).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(14).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����Ͷ�ʣ�
					<input name="mc1" type="hidden" value="����Ͷ�ʣ�"/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">
					<input name="mc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">���ڹ�ȨͶ��
					<input name="mc1" type="hidden" value="���ڹ�ȨͶ��"/>
					</td>
					<td align="center">15
					<input name="hc1" type="hidden" value="15"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(16).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(16).getQms()%>"<%}%>/>
					</td>
					<td align="left">������ծ�ϼ�
					<input name="mc2" type="hidden" value="������ծ�ϼ�"/>
					</td>
					<td align="center">47
					<input name="hc2" type="hidden" value="48"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(16).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(16).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����ծȨͶ��
					<input name="mc1" type="hidden" value="����ծȨͶ��"/>
					</td>
					<td align="center">16
					<input name="hc1" type="hidden" value="16"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(17).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(17).getQms()%>"<%}%>/>
					</td>
					<td align="left">���ڸ�ծ��
					<input name="mc2" type="hidden" value="���ڸ�ծ��"/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">����Ͷ�ʺϼ�
					<input name="mc1" type="hidden" value="����Ͷ�ʺϼ�"/>
					</td>
					<td align="center">17
					<input name="hc1" type="hidden" value="17"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(18).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(18).getQms()%>"<%}%>/>
					</td>
					<td align="left">���ڽ��
					<input name="mc2" type="hidden" value="���ڽ��"/>
					</td>
					<td align="center">48
					<input name="hc2" type="hidden" value="49"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(18).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(18).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ���
					<input name="mc1" type="hidden" value="�̶��ʲ���"/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">Ӧ��ծȯ
					<input name="mc2" type="hidden" value="Ӧ��ծȯ"/>
					</td>
					<td align="center">49
					<input name="hc2" type="hidden" value="49"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(19).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(19).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ�ԭ��
					<input name="mc1" type="hidden" value="�̶��ʲ�ԭ��"/>
					</td>
					<td align="center">18
					<input name="hc1" type="hidden" value="18"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(20).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(20).getQms()%>"<%}%>/>
					</td>
					<td align="left">����Ӧ����
					<input name="mc2" type="hidden" value="����Ӧ����"/>
					</td>
					<td align="center">50
					<input name="hc2" type="hidden" value="51"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(20).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(20).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ۼ��ۼ�
					<input name="mc1" type="hidden" value="�����ۼ��ۼ�"/>
					</td>
					<td align="center">19
					<input name="hc1" type="hidden" value="19"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(21).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(21).getQms()%>"<%}%>/>
					</td>
					<td align="left">ר��Ӧ����
					<input name="mc2" type="hidden" value="ר��Ӧ����"/>
					</td>
					<td align="center">51
					<input name="hc2" type="hidden" value="52"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(21).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(21).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ���ֵ
					<input name="mc1" type="hidden" value="�̶��ʲ���ֵ"/>
					</td>
					<td align="center">20
					<input name="hc1" type="hidden" value="20"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(22).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(22).getQms()%>"<%}%>/>
					</td>
					<td align="left">�������ڸ�ծ
					<input name="mc2" type="hidden" value="�������ڸ�ծ"/>
					</td>
					<td align="center">52
					<input name="hc2" type="hidden" value="53"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(22).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(22).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����̶��ʲ���ֵ׼��
					<input name="mc1" type="hidden" value="�����̶��ʲ���ֵ׼��"/>
					</td>
					<td align="center">21
					<input name="hc1" type="hidden" value="21"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(23).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(23).getQms()%>"<%}%>/>
					</td>
					<td align="left">���ڸ�ծ�ϼ�
					<input name="mc2" type="hidden" value="�������ڸ�ծ"/>
					</td>
					<td align="center">53
					<input name="hc2" type="hidden" value="54"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(23).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(23).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ�����
					<input name="mc1" type="hidden" value="�̶��ʲ�����"/>
					</td>
					<td align="center">22
					<input name="hc1" type="hidden" value="22"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(24).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(24).getQms()%>"<%}%>/>
					</td>
					<td align="left">����˰�
					<input name="mc2" type="hidden" value="����˰�"/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">��������
					<input name="mc1" type="hidden" value="��������"/>
					</td>
					<td align="center">23
					<input name="hc1" type="hidden" value="23"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(25).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(25).getQms()%>"<%}%>/>
					</td>
					<td align="left">����˰�����
					<input name="mc2" type="hidden" value="����˰�����"/>
					</td>
					<td align="center">54
					<input name="hc2" type="hidden" value="55"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(25).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(25).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ڽ�����
					<input name="mc1" type="hidden" value="�ڽ�����"/>
					</td>
					<td align="center">24
					<input name="hc1" type="hidden" value="24"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(26).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(26).getQms()%>"<%}%>/>
					</td>
					<td align="left">��ծ�ϼ�
					<input name="mc2" type="hidden" value="����˰�����"/>
					</td>
					<td align="center">55
					<input name="hc2" type="hidden" value="56"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(26).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(26).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ�����
					<input name="mc1" type="hidden" value="�̶��ʲ�����"/>
					</td>
					<td align="center">25
					<input name="hc1" type="hidden" value="25"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(27).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(27).getQms()%>"<%}%>/>
					</td>
					<td align="left">
					<input name="mc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">�̶��ʲ��ϼ�
					<input name="mc1" type="hidden" value="�̶��ʲ��ϼ�"/>
					</td>
					<td align="center">26
					<input name="hc1" type="hidden" value="26"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(28).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(28).getQms()%>"<%}%>/>
					</td>
					<td align="left">������Ȩ�棨��ɶ�Ȩ�棩��
					<input name="mc2" type="hidden" value="������Ȩ�棨��ɶ�Ȩ�棩��"/>
					</td>
					<td align="center">
					<input name="hc2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ʲ��������ʲ���
					<input name="mc1" type="hidden" value="�����ʲ��������ʲ���"/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">ʵ���ʱ�����ɱ���
					<input name="mc2" type="hidden" value="ʵ���ʱ�����ɱ���"/>
					</td>
					<td align="center">56
					<input name="hc2" type="hidden" value="57"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(29).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(29).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ʲ�
					<input name="mc1" type="hidden" value="�����ʲ�"/>
					</td>
					<td align="center">27
					<input name="hc1" type="hidden" value="27"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(30).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(30).getQms()%>"<%}%>/>
					</td>
					<td align="left">�����ѹ黹Ͷ��
					<input name="mc2" type="hidden" value="�����ѹ黹Ͷ��"/>
					</td>
					<td align="center">57
					<input name="hc2" type="hidden" value="58"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(30).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(30).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">���ڴ�̯����
					<input name="mc1" type="hidden" value="���ڴ�̯����"/>
					</td>
					<td align="center">28
					<input name="hc1" type="hidden" value="28"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(31).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(31).getQms()%>"<%}%>/>
					</td>
					<td align="left">ʵ���ʱ�����ɱ�������
					<input name="mc2" type="hidden" value="ʵ���ʱ�����ɱ�������"/>
					</td>
					<td align="center">58
					<input name="hc2" type="hidden" value="59"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(31).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(31).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��������Ͷ��
					<input name="mc1" type="hidden" value="��������Ͷ��"/>
					</td>
					<td align="center">29
					<input name="hc1" type="hidden" value="29"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(32).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(32).getQms()%>"<%}%>/>
					</td>
					<td align="left">�ʱ�����
					<input name="mc2" type="hidden" value="�ʱ�����"/>
					</td>
					<td align="center">59
					<input name="hc2" type="hidden" value="60"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(32).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(32).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ʲ��������ʲ��ϼ�
					<input name="mc1" type="hidden" value="�����ʲ��������ʲ��ϼ�"/>
					</td>
					<td align="center">30
					<input name="hc1" type="hidden" value="30"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(33).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(33).getQms()%>"<%}%>/>
					</td>
					<td align="left">ӯ�๫��
					<input name="mc2" type="hidden" value="ӯ�๫��"/>
					</td>
					<td align="center">60
					<input name="hc2" type="hidden" value="61"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(33).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(33).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">���У����������
					<input name="mc2" type="hidden" value="���У����������"/>
					</td>
					<td align="center">61
					<input name="hc2" type="hidden" value="62"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(34).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(34).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����˰�
					<input name="mc1" type="hidden" value="����˰�"/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="left">δ��������
					<input name="mc2" type="hidden" value="δ��������"/>
					</td>
					<td align="center">62
					<input name="hc2" type="hidden" value="63"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(35).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(35).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">����˰�����
					<input name="mc1" type="hidden" value="����˰�����"/>
					</td>
					<td align="center">31
					<input name="hc1" type="hidden" value="32"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(36).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(36).getQms()%>"<%}%>/>
					</td>
					<td align="left">������Ȩ�棨��ɶ�Ȩ�棩�ϼ�
					<input name="mc2" type="hidden" value="������Ȩ�棨��ɶ�Ȩ�棩�ϼ�"/>
					</td>
					<td align="center">63
					<input name="hc2" type="hidden" value="64"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(36).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(36).getQms()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ʲ��ܼ�
					<input name="mc1" type="hidden" value="�ʲ��ܼ�"/>
					</td>
					<td align="center">32
					<input name="hc1" type="hidden" value="33"/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(37).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(37).getQms()%>"<%}%>/>
					</td>
					<td align="left">��ծ��������Ȩ�棨��ɶ�Ȩ�棩�ϼ�
					<input name="mc2" type="hidden" value="��ծ��������Ȩ�棨��ɶ�Ȩ�棩�ϼ�"/>
					</td>
					<td align="center">64
					<input name="hc2" type="hidden" value="65"/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(37).getNcs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(37).getQms()%>"<%}%>/>
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
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



