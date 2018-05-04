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
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
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
    <title>新增修改</title>
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
	        	alert("请输入报表名称");
	        	return;
	        }
	   		var sj = document.getElementById("sj").value;
	   		if(sj==""){
	   			alert("请输入报告时间");
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
       
        //导入EXCEL
        function infExcel(){
	        var file = document.getElementById("file1").value;
	        if (file==""){
	        	alert("请选择上传文件");
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
					alert("附件大小不能为0M！");   
					return;
				}
				infoFile();
	        }else{ //检测上传文件类型
	            alert("只能上传xls格式的文件;\n请重新选择再上传.");
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
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
        //数据验证
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
         		if(jslx[i].value=="单个"){
         			//A列
         			var AH = document.getElementsByName("zb"+columnjs1[i].value);
         			var A = AH[rowjs1[i].value-1].value;
         			//B列
         			var BH = document.getElementsByName("zb"+columnjs2[i].value);
         			var B = BH[rowjs2[i].value-1].value;
         			//结果
         			var CH = document.getElementsByName("zb"+columnjs3[i].value);
         			if(jsfh[i].value=="＋"){
         				CH[rowjs3[i].value-1].value = (Number(A)+Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="－"){
         				CH[rowjs3[i].value-1].value = (Number(A)-Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="×"){
         				CH[rowjs3[i].value-1].value = (Number(A)*Number(B)).toFixed(2);
         			}else if(jsfh[i].value=="÷"){
         				CH[rowjs3[i].value-1].value = (Number(A)/Number(B)).toFixed(2);
         			}
         		}
         		else{
         			//A列
         			var AH1 = document.getElementsByName("zb"+columnjs1[i].value);
         			var A1 = 0;
         			//B列
         			var BH1 = document.getElementsByName("zb"+columnjs2[i].value);
         			var B1 = 0;
         			//结果
         			var CH1 = document.getElementsByName("zb"+columnjs3[i].value);
         			for(var j=0;j<=AH1.length-rowjs1[i].value&&j<=BH1.length-rowjs2[i].value&&j<=CH1.length-rowjs3[i].value;j++ ){
	         			A1 = AH1[rowjs1[i].value-1+j].value;
	         			B1 = BH1[rowjs2[i].value-1+j].value;
	         			if(jsfh[i].value=="＋"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)+Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="－"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)-Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="×"){
	         				CH1[rowjs3[i].value-1+j].value = (Number(A1)*Number(B1)).toFixed(2);
	         			}else if(jsfh[i].value=="÷"){
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
					报告时间：<input name="sj" id="sj" type="text" size="10" <%if(bbdz.getSj()!=null){%>value="<%=bbdz.getSj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.Bbdz.sj);" type="button" value="选择">
					</td>
					<td align="left">
					报表名称：<input type="text" name="bt" id="bt" size="30" <%if(bbdz.getBt()!=null&&!"".equals(bbdz.getBt())){%>value="<%=bbdz.getBt()%>"<%}else{%>value=""<%}%> >
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
				上传EXCEL：<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
				第<input id="sheetNum" type="text" size="5" value="1" />个文件
				<input type="button" class="button1" class="button1" onclick="infExcel()" value="导入EXCEL" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="计算" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="提交" width="45px" height="45px" title="提交" style="cursor: pointer;"  src="images/small/send.png" onclick="save(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="存草稿" width="45px" height="45px" title="存草稿" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="save(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		</form>
	</body>
	<%} %>
</html>



