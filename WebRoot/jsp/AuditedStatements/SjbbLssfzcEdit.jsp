<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbLssfzc"%>
<%@page import="com.safety.entity.SjbbLssfzcZxm"%>
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
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增市级机关（含下属事业单位）落实收费政策季度自查表</title>
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
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
		   
        //新增项目
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
        	inputc.value = "删除本行";
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
        //删除项目
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
         	//从request域中取得要显示的某页信息
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
					市级机关（含下属事业单位）落实收费政策季度自查表（
					
					<select name="year" id="year">
						<%if(sjbbLssfzc.getYear()!=0){%>
						<option value="<%= sjbbLssfzc.getYear() %>"><%= sjbbLssfzc.getYear() %>年</option>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>年</option>
						<%} %>
						<%}else{ %>
						<% for(int i=nowYear-1;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
						<%} }%>
					</select>
					<select name="jd" id="jd">
						<%if(sjbbLssfzc.getJd()==0){%>
						<option value="<%= sjbbLssfzc.getJd() %>">全年</option>
						<option value="1">1季度</option>
						<option value="2">2季度</option>
						<option value="3">3季度</option>
						<option value="4">4季度</option>
						<%}else{ %>
						<option value="<%= sjbbLssfzc.getJd() %>"><%= sjbbLssfzc.getJd() %>季度</option>
						<option value="0">全年</option>
						<option value="1">1季度</option>
						<option value="2">2季度</option>
						<option value="3">3季度</option>
						<option value="4">4季度</option>
						<%}%>
					</select>
					）
					</td>
				</tr>
				<tr>
					<td align="left">
						报送单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<table width="1300px" id="table1"  border=1  cellpadding=0 cellspacing=0>
				<tr>
					<td align="center" rowspan="2" width="150px">收费<br>单位
					</td>
					<td align="center" colspan="5" width="600px">实际收费情况
					</td>
					<td align="center" colspan="4" width="230px">收费减免政策落实情况
					</td>
					<td align="center" colspan="3" width="300px">存在问题
					</td>
					<td align="center" rowspan="2" width="20px">备<br>注
					</td>
				</tr>
				<tr>
					<td align="center" width="200px">收费<br>项目
					</td>
					<td align="center" width="50px">收费<br>标准
					</td>
					<td align="center" width="50px">收费<br>对象
					</td>
					<td align="center" width="200px">收费<br>依据
					</td>
					<td align="center" width="100px">收费<br>金额<br>（万元）
					</td>
					<td align="center" width="50px">减<br>免<br>对<br>象
					</td>
					<td align="center" width="50px">优<br>惠<br>方<br>式
					</td>
					<td align="center" width="50px">减<br>免<br>依<br>据
					</td>
					<td align="center" width="80px">减免<br>金额<br>（万元）
					</td>
					<td align="center" width="100px">有无<br>未执行<br>政府减免<br>收费规定
					</td>
					<td align="center" width="100px">是否<br>存在无<br>法定依据<br>收费
					</td>
					<td align="center" width="50px">有无<br>搭车<br>收费行为
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
					<input type="button" class="button1" value="删除本行" onclick="deleteRow(this.parentElement.parentElement)" />
					</td>
				</tr>
				<%} %>
			</table>
			<table width="100%">
				<tr>
					<td align="center">
					<input type="button" class="button1" value="新增项目" onclick="moreXM()">
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
					说明：1、请各部门认真汇总下属单位数据，正确填写本表，于每季度下个月的10日前报市物价局收费管理处（7月10日报1-6月份数据）。物价局收费处邮箱：ntwjjsfc@163.com。联系电话：85099592,85216544。
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、无收费实行零报告制。
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填表人：<%=name %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<input name="czrphone" type="text" size="10" <%if(sjbbLssfzc.getCzrphone()!=null){%>value="<%=sjbbLssfzc.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
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
				</td>
			</tr>
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
	<%}%>
	</body>
</html>



