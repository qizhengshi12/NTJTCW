<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbJtyssjdytj"%>
<%@page import="com.safety.entity.SjbbJtyssjdytjZxm"%>
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
	String workphone = UserInfo.getWorkphone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增交通运输部门审计对象统计表</title>
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
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
		   
        //新增项目
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
        	select3.options.add(new Option("行政","行政")); 
        	select3.options.add(new Option("事业","事业")); 
        	select3.options.add(new Option("企业","企业")); 
        	
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
        	select5.options.add(new Option("自审","自审")); 
        	select5.options.add(new Option("委托中介机构","委托中介机构")); 
        	
        	var td6 = document.createElement("td");
        	td6.align="center";
        	td6.width="150px";
        	var select6 = document.createElement("select");
        	select6.name = "xzfs";
        	select6.options.add(new Option("","")); 
        	select6.options.add(new Option("公开招标","公开招标")); 
        	select6.options.add(new Option("竞争性谈判","竞争性谈判")); 
        	select6.options.add(new Option("指定","指定")); 
        	select6.options.add(new Option("随机抽取","随机抽取"));
        	
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
        	select8.options.add(new Option("审计署","审计署")); 
        	select8.options.add(new Option("省审计厅","省审计厅")); 
        	select8.options.add(new Option("市审计局","市审计局"));
        	select8.options.add(new Option("县审计局","县审计局"));

        	var input9 = document.createElement("input");
        	input9.type = "hidden";
        	input9.name = "fl";
        	input9.value= num;
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "删除本行";
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
        //删除项目
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
         	//从request域中取得要显示的某页信息
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
					交通运输部门审计对象统计表
					</td>
				</tr>
				<tr>
					<td align="left">
						填报单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="1200px"  border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" colspan="2">审计对象名称
					</td>
					<td align="center" rowspan="2">单位性质
					</td>
					<td align="center" colspan="3">2011年度本级单位对所属审计对象开展内部审计情况
					</td>
					<td align="center" colspan="2">2011年度审计对象接受审计机关审计情况
					</td>
				</tr>
				<tr>
					<td align="center">类别
					</td>
					<td align="center">名称
					</td>
					<td align="center">审计内容
					</td>
					<td align="center">审计方式
					</td>
					<td align="center">中介机构选择方式
					</td>
					<td align="center">审计机关审计事项
					</td>
					<td align="center">审计实施单位
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">（1）
					</td>
					<td align="center" width="200px">（2）
					</td>
					<td align="center" width="100px">（3）
					</td>
					<td align="center" width="200px">（4）
					</td>
					<td align="center" width="150px">（5）
					</td>
					<td align="center" width="150px">（6）
					</td>
					<td align="center" width="200px">（7）
					</td>
					<td align="center" width="100px">（8）
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
					本级单位
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="行政">行政</option>
						<option value="事业">事业</option>
						<option value="企业">企业</option>
					</select>
					</td>
					<td align=center width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align=center width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="自审">自审</option>
						<option value="委托中介机构">委托中介机构</option>
					</select>
					</td>
					<td align=center width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="公开招标">公开招标</option>
						<option value="竞争性谈判">竞争性谈判</option>
						<option value="指定">指定</option>
						<option value="随机抽取">随机抽取</option>
					</select>
					</td>
					<td align=center width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="审计署">审计署</option>
						<option value="省审计厅">省审计厅</option>
						<option value="市审计局">市审计局</option>
						<option value="县审计局">县审计局</option>
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
					<%if(!"下属单位".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					下属单位
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="行政">行政</option>
						<option value="事业">事业</option>
						<option value="企业">企业</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="自审">自审</option>
						<option value="委托中介机构">委托中介机构</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="公开招标">公开招标</option>
						<option value="竞争性谈判">竞争性谈判</option>
						<option value="指定">指定</option>
						<option value="随机抽取">随机抽取</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="审计署">审计署</option>
						<option value="省审计厅">省审计厅</option>
						<option value="市审计局">市审计局</option>
						<option value="县审计局">县审计局</option>
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
					<input type="button" class="button1" value="新增项目" onclick="moreXM(2)">
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
					<%if(!"财务隶属关系单位".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					财务隶属关系<br>单位
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="行政">行政</option>
						<option value="事业">事业</option>
						<option value="企业">企业</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="自审">自审</option>
						<option value="委托中介机构">委托中介机构</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="公开招标">公开招标</option>
						<option value="竞争性谈判">竞争性谈判</option>
						<option value="指定">指定</option>
						<option value="随机抽取">随机抽取</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="审计署">审计署</option>
						<option value="省审计厅">省审计厅</option>
						<option value="市审计局">市审计局</option>
						<option value="县审计局">县审计局</option>
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
					<input type="button" class="button1" value="新增项目" onclick="moreXM(3)">
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
					<%if(!"建设项目".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					建设项目
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="行政">行政</option>
						<option value="事业">事业</option>
						<option value="企业">企业</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="自审">自审</option>
						<option value="委托中介机构">委托中介机构</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="公开招标">公开招标</option>
						<option value="竞争性谈判">竞争性谈判</option>
						<option value="指定">指定</option>
						<option value="随机抽取">随机抽取</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="审计署">审计署</option>
						<option value="省审计厅">省审计厅</option>
						<option value="市审计局">市审计局</option>
						<option value="县审计局">县审计局</option>
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
					<input type="button" class="button1" value="新增项目" onclick="moreXM(4)">
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
					<%if(!"专项资金".equals(sjbbJtyssjdytjZxm.getLb())){%>
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					<%}else{%>
					专项资金
					<%} %>
					<input name="lb" type="hidden" <%if(sjbbJtyssjdytjZxm.getLb()!=null){%>value="<%=sjbbJtyssjdytjZxm.getLb()%>"<%}%>/>
					</td>
					<td align="center" width="200px">
					<input name="mc" type="text" <%if(sjbbJtyssjdytjZxm.getMc()!=null){%>value="<%=sjbbJtyssjdytjZxm.getMc()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="dwxz">
						<option value="<%=sjbbJtyssjdytjZxm.getDwxz() %>"><%=sjbbJtyssjdytjZxm.getDwxz() %></option>
						<option value="行政">行政</option>
						<option value="事业">事业</option>
						<option value="企业">企业</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjnr" type="text" <%if(sjbbJtyssjdytjZxm.getSjnr()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjnr()%>"<%}%>/>
					</td>
					<td align="center" width="150px">
					<select name="sjfs">
						<option value="<%=sjbbJtyssjdytjZxm.getSjfs() %>"><%=sjbbJtyssjdytjZxm.getSjfs() %></option>
						<option value="自审">自审</option>
						<option value="委托中介机构">委托中介机构</option>
					</select>
					</td>
					<td align="center" width="150px">
					<select name="xzfs">
						<option value="<%=sjbbJtyssjdytjZxm.getXzfs() %>"><%=sjbbJtyssjdytjZxm.getXzfs() %></option>
						<option value="公开招标">公开招标</option>
						<option value="竞争性谈判">竞争性谈判</option>
						<option value="指定">指定</option>
						<option value="随机抽取">随机抽取</option>
					</select>
					</td>
					<td align="center" width="200px">
					<input name="sjsx" type="text" <%if(sjbbJtyssjdytjZxm.getSjsx()!=null){%>value="<%=sjbbJtyssjdytjZxm.getSjsx()%>"<%}%>/>
					</td>
					<td align="center" width="100px">
					<select name="ssdw">
						<option value="<%=sjbbJtyssjdytjZxm.getSsdw() %>"><%=sjbbJtyssjdytjZxm.getSsdw() %></option>
						<option value="审计署">审计署</option>
						<option value="省审计厅">省审计厅</option>
						<option value="市审计局">市审计局</option>
						<option value="县审计局">县审计局</option>
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
					<input type="button" class="button1" value="新增项目" onclick="moreXM(5)">
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
						&nbsp;&nbsp;填表人：<%=name %>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<input name="czrphone" type="text" size="10" <%if(sjbbJtyssjdytj.getCzrphone()!=null){%>value="<%=sjbbJtyssjdytj.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
					</td>
				</tr>
				<tr height="1px">
					<td>
						<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td>
						说明：
					</td>
				</tr>
				<tr>
					<td>
					1、“建设项目”填列本级单位负有监督职责的建设项目。
					</td>
				</tr>
				<tr>
					<td>
					2、专项资金的“审计对象名称”栏填写本级单位及审计机关开展专项资金审计中所涉及单位。若当年度未对专项资金进行审计，则不需填列。
					</td>
				</tr>
				<tr>
					<td>
					3、第（3）列单位性质选择“行政”、“事业”或“企业”。
					</td>
				</tr>
				<tr>
					<td>
					4、第（5）列选择“自审”或“委托中介机构”；如是委托中介机构，请在第（6）列中选择“公开招标”或“竞争性谈判”或“指定”或“随机抽取”。
					</td>
				</tr>
				<tr>
					<td>
					5、第（8）列选择“审计署”或“省审计厅”或“市审计局”或“县审计局”
					</td>
				</tr>
			</table>
			<table width="100%">
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
		</form>
		<form id="form2"  method="post"></form>
	<%}%>
	</body>
</html>



