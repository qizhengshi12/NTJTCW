<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbYszxb"%>
<%@page import="com.safety.entity.CwbbYszxbZxm"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.Permissions"%>     
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
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
   	CwbbYszxb cwbbYszxb = (CwbbYszxb)request.getAttribute("cwbbYszxb");
   	ArrayList CwbbYszxbZxmList = (ArrayList)request.getAttribute("CwbbYszxbZxmList");
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增修改预算执行表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
        		verification();
			}
        	var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=yszxbSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("newcompany").value;
			form.action = "BbsbCwbbServlet?action=getYszxb&flag=1&menuname="+menuname;
			form.submit();
        }
       
        //新增项目
        function moreXM(){
        	var table2 = document.getElementById("table2");
        	var tr = document.createElement("tr");
        	
        	var td1 = document.createElement("td");
        	td1.align="center";
        	var input1 = document.createElement("input");
        	input1.type = "text";
        	input1.name = "zxmmc";
        	
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "zxma";
        	input2.size = "6";
        	//input2.onkeyup=new Function{"value=value.replace(/[^\d.]/g,'')"};
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	var input3 = document.createElement("input");
        	input3.type = "text";
        	input3.name = "zxmb";
        	input3.size = "6";
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "zxmc";
        	input4.size = "6";
        	input4.readOnly=true;
        	
        	var td5 = document.createElement("td");
        	td5.align="center";
        	var input5 = document.createElement("input");
        	input5.type = "text";
        	input5.name = "zxmd";
        	input5.size = "6";
        	input5.readOnly=true;
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "删除本行";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	
        	table2.appendChild(tr);
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
        	td5.appendChild(inputc);
        }
        //删除项目
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
		}     
        
        //数据验证
        function verification(){
        	//1、工资福利支出
        	var gz1 = document.getElementById("gz1").value;
        	var gz2 = document.getElementById("gz2").value;
        	var gz3;
        	var gz4;
        	if(gz1=="")gz1=0;
        	if(gz2=="")gz2=0;
        	gz3 = (Number(gz1)-Number(gz2)).toFixed(2)  ;
        	if(gz1==0){
        		gz4 = "";
        	}else{
        		gz4 = (Number(gz2)/Number(gz1)*100).toFixed(2);
        	}
        	document.getElementById("gz1").value = gz1;
        	document.getElementById("gz2").value = gz2;
        	document.getElementById("gz3").value = gz3;
        	document.getElementById("gz4").value = gz4;
        	//2、商品和服务支出
        	var sp1 = document.getElementById("sp1").value;
        	var sp2 = document.getElementById("sp2").value;
        	var sp3;
        	var sp4;
        	if(sp1=="")sp1=0;
        	if(sp2=="")sp2=0;
        	sp3 = (Number(sp1)-Number(sp2)).toFixed(2)  ;
        	if(sp1==0){
        		sp4 = "";
        	}else{
        		sp4 = (Number(sp2)/Number(sp1)*100).toFixed(2);
        	}
        	document.getElementById("sp1").value = sp1;
        	document.getElementById("sp2").value = sp2;
        	document.getElementById("sp3").value = sp3;
        	document.getElementById("sp4").value = sp4;
        	//3、对个人和家庭补助支出
        	var bz1 = document.getElementById("bz1").value;
        	var bz2 = document.getElementById("bz2").value;
        	var bz3;
        	var bz4;
        	if(bz1=="")bz1=0;
        	if(bz2=="")bz2=0;
        	bz3 = (Number(bz1)-Number(bz2)).toFixed(2)  ;
        	if(bz1==0){
        		bz4 = "";
        	}else{
        		bz4 = (Number(bz2)/Number(bz1)*100).toFixed(2);
        	}
        	document.getElementById("bz1").value = bz1;
        	document.getElementById("bz2").value = bz2;
        	document.getElementById("bz3").value = bz3;
        	document.getElementById("bz4").value = bz4;
        	//一、基本支出
	        var jb1;
	        var jb2;
	        var jb3;
	        var jb4;
	        if(gz1==0&&sp1==0&&bz1==0){
	        	jb1 = 0;
	        }else{
	        	jb1 = (Number(gz1)+Number(sp1)+Number(bz1)).toFixed(2);
	        }
	        if(gz2==0&&sp2==0&&bz2==0){
	        	jb2 = 0;
	        }else{
	        	jb2 = (Number(gz2)+Number(sp2)+Number(bz2)).toFixed(2);
	        }
        	jb3 = (Number(jb1)-Number(jb2)).toFixed(2)  ;
        	if(jb1==0){
        		jb4 = "";
        	}else{
        		jb4 = (Number(jb2)/Number(jb1)*100).toFixed(2);
        	}
        	document.getElementById("jb1").value = jb1;
        	document.getElementById("jb2").value = jb2;
        	document.getElementById("jb3").value = jb3;
        	document.getElementById("jb4").value = jb4;
	        
        	//子项目支出
        	var xm1=0;
        	var xm2=0;
        	var zxma = document.getElementsByName("zxma");
        	var zxmb = document.getElementsByName("zxmb");
        	var zxmc = document.getElementsByName("zxmc");
        	var zxmd = document.getElementsByName("zxmd");
         	for(j=0;j<zxma.length;j++){
	        	if(zxma[j].value=="")zxma[j].value=0;
	        	if(zxmb[j].value=="")zxmb[j].value=0;
	        	zxmc[j].value = (Number(zxma[j].value)-Number(zxmb[j].value)).toFixed(2)  ;
	        	if(zxma[j].value==0){
	        		zxmd[j].value = "";
	        	}else{
	        		zxmd[j].value = (Number(zxmb[j].value)/Number(zxma[j].value)*100).toFixed(2);
	        	}
         		xm1= (Number(xm1)+ Number(zxma[j].value)).toFixed(2);
	         	xm2= (Number(xm2)+ Number(zxmb[j].value)).toFixed(2);
        	}
        	//二、项目支出
        	document.getElementById("xm1").value = xm1;
        	document.getElementById("xm2").value = xm2;
        	document.getElementById("xm3").value = (Number(xm1)-Number(xm2)).toFixed(2);
        	if(xm1==0){
        		document.getElementById("xm4").value = "";
        	}else{
        		document.getElementById("xm4").value = (Number(xm2)/Number(xm1)*100).toFixed(2);
        	}
        
        	//合计
	        var hj1;
	        var hj2;
	        var hj3;
	        var hj4;
	        hj1 = (Number(jb1)+Number(xm1)).toFixed(2);
	        hj2 = (Number(jb2)+Number(xm2)).toFixed(2);
        	hj3 = (Number(hj1)-Number(hj2)).toFixed(2);
        	if(hj1==0){
        		hj4 = "";
        	}else{
        		hj4 = (Number(hj2)/Number(hj1)*100).toFixed(2);
        	}
        	document.getElementById("hj1").value = hj1;
        	document.getElementById("hj2").value = hj2;
        	document.getElementById("hj3").value = hj3;
        	document.getElementById("hj4").value = hj4;
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
		    var path = "tempFile/yszxb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readYszxbExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("上传冲突，请稍后再试");
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
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
		%>
		<form id="form1" method="post">
		<input type="hidden" name="cwbbYszxb_id" id="cwbbYszxb_id" <%if(cwbbYszxb.getId()!=0){%>value="<%=cwbbYszxb.getId()%>"<%}%> >
		<input type="hidden" name="xmid" id="xmid" <%if(cwbbYszxb.getXmid()!=null){%>value="<%=cwbbYszxb.getXmid()%>"<%}%> >
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table  id="table1" width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<select name="year" id="year">
						<%if(cwbbYszxb.getYear()!=0){%>
						<option value="<%= cwbbYszxb.getYear() %>"><%= cwbbYszxb.getYear() %>年</option>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>年</option>
						<%} %>
						<%}else{ %>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
						<%} }%>
					</select>
					&nbsp;1到&nbsp;
					<select name="month" id="month">
						<%if(cwbbYszxb.getMonth()!=0){%>
						<option value="<%= cwbbYszxb.getMonth() %>"><%= cwbbYszxb.getMonth() %>月</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>月</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%} }%>
					</select>
					<input name="bt" type="text" size="20" <%if(cwbbYszxb.getBt()!=null){%>value="<%=cwbbYszxb.getBt()%>"<%}else{%>value="预算执行进度表"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" width="80%">
						填报单位：<%=cwbbYszxb.getCzrdw() %>
						<input name="newcompany" id="newcompany" type="hidden"  value="<%=cwbbYszxb.getCzrdw() %>" readonly="readonly" class="STYLE1"/>
					</td>
					<td align="right" width="20%">
						单位：万元
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="35%">&nbsp;
					</td>
					<td align="center" width="15%" style="font-weight: bold;">预算数
					</td>
					<td align="center" width="15%" style="font-weight: bold;">支出数
					</td>
					<td align="center" width="15%" style="font-weight: bold;">结余数
					</td>
					<td align="center" width="20%" style="font-weight: bold;">支出比例（%）
					</td>
				</tr>
				<tr>
					<td align="left" style="font-weight: bold;">一、基本支出
					</td>
					<td align="center">
					<input name="jb1" id="jb1" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getJb1()!=null){%>value="<%=cwbbYszxb.getJb1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jb2" id="jb2" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getJb2()!=null){%>value="<%=cwbbYszxb.getJb2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jb3" id="jb3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getJb3()!=null){%>value="<%=cwbbYszxb.getJb3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jb4" id="jb4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getJb4()!=null){%>value="<%=cwbbYszxb.getJb4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">1、工资福利支出
					</td>
					<td align="center">
					<input name="gz1" id="gz1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getGz1()!=null){%>value="<%=cwbbYszxb.getGz1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gz2" id="gz2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getGz2()!=null){%>value="<%=cwbbYszxb.getGz2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gz3" id="gz3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getGz3()!=null){%>value="<%=cwbbYszxb.getGz3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gz4" id="gz4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getGz4()!=null){%>value="<%=cwbbYszxb.getGz4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">2、商品和服务支出
					</td>
					<td align="center">
					<input name="sp1" id="sp1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getSp1()!=null){%>value="<%=cwbbYszxb.getSp1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sp2" id="sp2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getSp2()!=null){%>value="<%=cwbbYszxb.getSp2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sp3" id="sp3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getSp3()!=null){%>value="<%=cwbbYszxb.getSp3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sp4" id="sp4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getSp4()!=null){%>value="<%=cwbbYszxb.getSp4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">3、对个人和家庭补助支出
					</td>
					<td align="center">
					<input name="bz1" id="bz1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getBz1()!=null){%>value="<%=cwbbYszxb.getBz1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bz2" id="bz2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxb.getBz2()!=null){%>value="<%=cwbbYszxb.getBz2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bz3" id="bz3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getBz3()!=null){%>value="<%=cwbbYszxb.getBz3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bz4" id="bz4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getBz4()!=null){%>value="<%=cwbbYszxb.getBz4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;
					</td>
					<td align="center">&nbsp;
					</td>
					<td align="center">&nbsp;
					</td>
					<td align="center">&nbsp;
					</td>
					<td align="center">&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" style="font-weight: bold;">二、项目支出
					</td>
					<td align="center">
					<input name="xm1" id="xm1" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getXm1()!=null){%>value="<%=cwbbYszxb.getXm1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xm2" id="xm2" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getXm2()!=null){%>value="<%=cwbbYszxb.getXm2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xm3" id="xm3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getXm3()!=null){%>value="<%=cwbbYszxb.getXm3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xm4" id="xm4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getXm4()!=null){%>value="<%=cwbbYszxb.getXm4()%>"<%}%>/>
					</td>
				</tr>
				
				<%
					for(int i=0;i< CwbbYszxbZxmList.size();i++){
						CwbbYszxbZxm cwbbYszxbZxm = (CwbbYszxbZxm)CwbbYszxbZxmList.get(i);
				
				%>
				<tr>
					<td align="center" style="font-weight: bold;">
					<input name="zxmmc" type="text"  <%if(cwbbYszxbZxm.getZxmmc()!=null){%>value="<%=cwbbYszxbZxm.getZxmmc()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zxma" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxbZxm.getZxm1()!=null){%>value="<%=cwbbYszxbZxm.getZxm1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zxmb" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxbZxm.getZxm2()!=null){%>value="<%=cwbbYszxbZxm.getZxm2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zxmc" type="text" size="6" readonly="readonly" <%if(cwbbYszxbZxm.getZxm3()!=null){%>value="<%=cwbbYszxbZxm.getZxm3()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zxmd" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbYszxbZxm.getZxm4()!=null){%>value="<%=cwbbYszxbZxm.getZxm4()%>"<%}%>/>
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				
				<%} %>
				
			</table>
			<table  id="table1" width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="新增项目" onclick="moreXM()">
					</td>
				</tr>
			</table>
			<table width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="35%" style="font-weight: bold;">合计
					</td>
					<td align="center" width="15%">
					<input name="hj1" id="hj1" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getHj1()!=null){%>value="<%=cwbbYszxb.getHj1()%>"<%}%>/>
					</td>
					<td align="center" width="15%">
					<input name="hj2" id="hj2" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getHj2()!=null){%>value="<%=cwbbYszxb.getHj2()%>"<%}%>/>
					</td>
					<td align="center" width="15%">
					<input name="hj3" id="hj3" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getHj3()!=null){%>value="<%=cwbbYszxb.getHj3()%>"<%}%>/>
					</td>
					<td align="center" width="20%">
					<input name="hj4" id="hj4" type="text" size="6" readonly="readonly" <%if(cwbbYszxb.getHj4()!=null){%>value="<%=cwbbYszxb.getHj4()%>"<%}%>/>
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
		<form id="form3" method="post"  enctype="multipart/form-data" target="ifm">
		<table  width="100%">
			<tr>
				<td align="left">
				上传EXCEL：<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
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



