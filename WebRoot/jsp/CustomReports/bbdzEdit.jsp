<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CglibBean"%>
<%@page import="com.safety.entity.Bbdz"%>
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
   	ArrayList BbdzDTList = (ArrayList)request.getAttribute("BbdzDTList");
   	Bbdz bbdz = (Bbdz)request.getAttribute("bbdz");
   	String lx = (String)request.getAttribute("lx");
   	String zbid = (String)request.getAttribute("zbid");
   	int bbls = (Integer)request.getAttribute("bbls");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�����޸�</title>
    <script src="calendar.js"></script>
    <script src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		jQuery(function() {
			jQuery('input:text:first').focus();
			var $inp = jQuery('input:text');
			$inp.bind('keydown', function(e) {
				var key = e.which;
				if (key == 13) {
					e.preventDefault();
					var nxtIdx = $inp.index(this) + 1;
					jQuery(":input:text:eq(" + nxtIdx + ")").focus();
				}
			});
		});
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
        		verification();
			}
	        var bt = document.getElementById("bt").value;
	        if(bt==""){
	        	alert("�����뱨������");
	        	return;
	        }
	   		var sj = document.getElementById("sj").value;
	   		if(sj==""){
	   			alert("�����뱨��ʱ��");
	   			return;
	   		}
        	var form = document.getElementById("form1");
			form.action = "BbdzServlet?action=saveBbdz";
			form.submit();
        }
        function cancel(){
	        var lx = document.getElementById("lx").value;
	        var zbid = document.getElementById("zbid").value;
        	var menuname = document.getElementById("menuname").value;
        	var form = document.getElementById("form2");
			form.action = "BbdzServlet?action=getBbdz&flag=1&lx="+lx+"&zbid="+zbid+"&menuname="+menuname;
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
		    var path = "tempFile/bbdz";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var sheetNum = document.getElementById("sheetNum").value;
        		var form = document.getElementById("form1");
				form.action = "BbdzServlet?action=readBbdzExcel&URL="+msg+"&sheetNum="+sheetNum;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
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
		<form id="form1" name="Bbdz" method="post">
		<input type="hidden" name="id" id="id" <%if(bbdz.getId()!=0){%>value="<%=bbdz.getId()%>"<%}%> >
		<input type="hidden" name="zbid" id="zbid" value="<%=zbid%>" >
		<input type="hidden" name="lx" id="lx" value="<%=lx%>" >
		<input type="hidden" name="bbls" id="bbls" value="<%=bbls%>" >
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" id="menuname" <%if(bbdz.getCzrdw()!=null&&!"".equals(bbdz.getCzrdw())){%>value="<%=bbdz.getCzrdw()%>"<%}else{%>value="<%=company %>"<%}%>>
			<table id="table1" align="center" width="100%">
				<tr>
					<td align="right" width="50%">
					����ʱ�䣺<input name="sj" id="sj" type="text" size="10" <%if(bbdz.getSj()!=null){%>value="<%=bbdz.getSj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.Bbdz.sj);" type="button" value="ѡ��">
					</td>
					<td align="left">
					�������ƣ�<input type="text" name="bt" id="bt" size="30" <%if(bbdz.getBt()!=null&&!"".equals(bbdz.getBt())){%>value="<%=bbdz.getBt()%>"<%}else{%>value=""<%}%> >
					</td>
				</tr>
			</table>
			
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<%
					for(int i=0;i< BbdzDTList.size();i++){
						CglibBean bean = (CglibBean)BbdzDTList.get(i);
				%>
				<tr>
					<%
					for(int j=1;j<=bbls;j++){ %>
					<td align="center">
					<input name="zb<%= j %>" type="text" size="6" <%if(bean.getValue("zb"+j)!=null){%>value="<%=bean.getValue("zb"+j)%>"<%}%>/>
					</td>
					<%}%>
				</tr>
				<%} %>
			</table>
			<div style="display: none">
				<%
					for(int i=0;i< BbdzmbJsList.size();i++){
						BbdzmbJs bbdzJs = (BbdzmbJs)BbdzmbJsList.get(i);
				%>
				<input name="rowjs1" type="hidden" <%if(bbdzJs.getRow1()!=-1){%>value="<%=bbdzJs.getRow1()%>"<%}%>/>
				<input name="columnjs1" type="hidden" <%if(bbdzJs.getColumn1()!=-1){%>value="<%=bbdzJs.getColumn1()%>"<%}%>/>
				<input name="rowjs2" type="hidden" <%if(bbdzJs.getRow2()!=-1){%>value="<%=bbdzJs.getRow2()%>"<%}%>/>
				<input name="columnjs2" type="hidden" <%if(bbdzJs.getColumn2()!=-1){%>value="<%=bbdzJs.getColumn2()%>"<%}%>/>
				<input name="rowjs3" type="hidden" <%if(bbdzJs.getRow3()!=-1){%>value="<%=bbdzJs.getRow3()%>"<%}%>/>
				<input name="columnjs3" type="hidden" <%if(bbdzJs.getColumn3()!=-1){%>value="<%=bbdzJs.getColumn3()%>"<%}%>/>
				<input name="jsfh" type="hidden" value="<%=bbdzJs.getFh() %>"/>
				<input name="jslx" type="hidden" value="<%=bbdzJs.getLx() %>"/>
				<%} %>
			</div>
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
	</body>
	<%} %>
</html>



