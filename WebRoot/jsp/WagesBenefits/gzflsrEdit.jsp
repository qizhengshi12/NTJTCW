<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.Gzflsr"%>
<%@page import="com.safety.entity.GzflsrZxm"%>
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
   	Gzflsr gzflsr = (Gzflsr)request.getAttribute("gzflsr");
   	ArrayList GzflsrZxmList = (ArrayList)request.getAttribute("GzflsrZxmList");
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增修改工资福利收入统计表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		var num = <%=GzflsrZxmList.size()%>;
        function save(str){
			document.getElementById("tjzt").value = str;
        	var form = document.getElementById("form1");
			form.action = "GzflsrServlet?action=gzflsrSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
			form.action = "GzflsrServlet?action=getGzflsr";
			form.submit();
        }
        //新增项目
        function moreXM(){
        	var table2 = document.getElementById("table2");
        	var tr = document.createElement("tr");
        	
        	var td0 = document.createElement("td");
        	td0.align="center";
        	td0.innerHTML = ++num;
        	
        	var td1 = document.createElement("td");
        	td1.align="center";
        	var input1 = document.createElement("input");
        	input1.type = "text";
        	input1.name = "zgxm";
        	input1.size = "6";
        	
        	var td2 = document.createElement("td");
        	td2.align="center";
        	var input2 = document.createElement("input");
        	input2.type = "text";
        	input2.name = "gwgz";
        	input2.size = "6";
        	
        	var td3 = document.createElement("td");
        	td3.align="center";
        	var input3 = document.createElement("input");
        	input3.type = "text";
        	input3.name = "xjgz";
        	input3.size = "6";
        	
        	var td4 = document.createElement("td");
        	td4.align="center";
        	var input4 = document.createElement("input");
        	input4.type = "text";
        	input4.name = "gwjt";
        	input4.size = "6";
        	
        	var td5 = document.createElement("td");
        	td5.align="center";
        	var input5 = document.createElement("input");
        	input5.type = "text";
        	input5.name = "shbt";
        	input5.size = "6";
        	
        	var td6 = document.createElement("td");
        	td6.align="center";
        	var input6 = document.createElement("input");
        	input6.type = "text";
        	input6.name = "jljx";
        	input6.size = "6";
        	
        	var td7 = document.createElement("td");
        	td7.align="center";
        	var input7 = document.createElement("input");
        	input7.type = "text";
        	input7.name = "zfbt";
        	input7.size = "6";
        	
        	var td8 = document.createElement("td");
        	td8.align="center";
        	var input8 = document.createElement("input");
        	input8.type = "text";
        	input8.name = "ct";
        	input8.size = "6";
        	
        	var td9 = document.createElement("td");
        	td9.align="center";
        	var input9 = document.createElement("input");
        	input9.type = "text";
        	input9.name = "ybbt";
        	input9.size = "6";
        	
        	var td10 = document.createElement("td");
        	td10.align="center";
        	var input10 = document.createElement("input");
        	input10.type = "text";
        	input10.name = "gjj";
        	input10.size = "6";
        	
        	var td11 = document.createElement("td");
        	td11.align="center";
        	var input11 = document.createElement("input");
        	input11.type = "text";
        	input11.name = "qtsr";
        	input11.size = "6";
        	
        	var td12 = document.createElement("td");
        	td12.align="center";
        	var input12 = document.createElement("input");
        	input12.type = "text";
        	input12.name = "yfhj";
        	input12.size = "6";
        	
        	var inputc = document.createElement("input");
        	inputc.type = "button";
        	inputc.value = "删除本行";
        	inputc.onclick = new Function("deleteRow(this.parentElement.parentElement)");
        	inputc.className  = "button1";
        	
        	
        	table2.appendChild(tr);
        	tr.appendChild(td0);
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
        	td12.appendChild(inputc);
        }
        //删除项目
		function deleteRow(obj){   
			obj.parentElement.removeChild(obj);   
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
		    var path = "tempFile/gzflsr";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "GzflsrServlet?action=readGzflsrExcel&URL="+msg;
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
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="xmid" id="xmid" <%if(gzflsr.getXmid()!=null){%>value="<%=gzflsr.getXmid()%>"<%}%> >
		<input type="hidden" name="Gzflsr_id" id="Gzflsr_id" <%if(gzflsr.getId()!=0){%>value="<%=gzflsr.getId()%>"<%}%> >
			<table  id="table1" width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<select name="year" id="year">
						<%if(gzflsr.getYear()!=0){%>
						<option value="<%= gzflsr.getYear() %>"><%= gzflsr.getYear() %>年</option>
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
						<%if(gzflsr.getMonth()!=0){%>
						<option value="<%= gzflsr.getMonth() %>"><%= gzflsr.getMonth() %>月</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>月</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%} }%>
					</select>工资福利收入统计表
					</td>
				</tr>
				<tr>
					<td align="left" width="50%">
						单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="right" width="50%">
						单位：元
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="5%" rowspan="2">编号
					</td>
					<td align="center" width="6%" rowspan="2">职工姓名
					</td>
					<td align="center" width="6%" rowspan="2">岗位工资
					</td>
					<td align="center" width="6%" rowspan="2">薪级工资
					</td>
					<td align="center" width="18%" colspan="2">基础性绩效工资
					</td>
					<td align="center" width="9%" rowspan="2">奖励性绩效工资
					</td>
					<td align="center" width="23%" colspan="3">改革性补贴
					</td>
					<td align="center" width="9%" rowspan="2">公积金
					</td>
					<td align="center" width="9%" rowspan="2">其它收入
					</td>
					<td align="center" width="9%" rowspan="2">应发合计
					</td>
				</tr>
				<tr>
					<td align="center" width="9%">岗位津贴
					</td>
					<td align="center" width="9%">生活补贴
					</td>
					<td align="center" width="9%">住房补贴
					</td>
					<td align="center" width="5%">车贴
					</td>
					<td align="center" width="9%">应发合计
					</td>
				</tr>
				<%
					for(int i=0;i< GzflsrZxmList.size();i++){
						GzflsrZxm gzflsrZxm = (GzflsrZxm)GzflsrZxmList.get(i);
				%>
				<tr>
					<td align="center">
					<%=i+1%>
					</td>
					<td align="center">
					<input name="zgxm" type="text" size="6" <%if(gzflsrZxm.getZgxm()!=null){%>value="<%=gzflsrZxm.getZgxm()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gwgz" type="text" size="6" <%if(gzflsrZxm.getGwgz()!=null){%>value="<%=gzflsrZxm.getGwgz()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xjgz" type="text" size="6" <%if(gzflsrZxm.getXjgz()!=null){%>value="<%=gzflsrZxm.getXjgz()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gwjt" type="text" size="6" <%if(gzflsrZxm.getGwjt()!=null){%>value="<%=gzflsrZxm.getGwjt()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="shbt" type="text" size="6" <%if(gzflsrZxm.getShbt()!=null){%>value="<%=gzflsrZxm.getShbt()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jljx" type="text" size="6" <%if(gzflsrZxm.getJljx()!=null){%>value="<%=gzflsrZxm.getJljx()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="zfbt" type="text" size="6" <%if(gzflsrZxm.getZfbt()!=null){%>value="<%=gzflsrZxm.getZfbt()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ct" type="text" size="6" <%if(gzflsrZxm.getCt()!=null){%>value="<%=gzflsrZxm.getCt()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ybbt" type="text" size="6" <%if(gzflsrZxm.getYbbt()!=null){%>value="<%=gzflsrZxm.getYbbt()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gjj" type="text" size="6" <%if(gzflsrZxm.getGjj()!=null){%>value="<%=gzflsrZxm.getGjj()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="qtsr" type="text" size="6" <%if(gzflsrZxm.getQtsr()!=null){%>value="<%=gzflsrZxm.getQtsr()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="yfhj" type="text" size="6" <%if(gzflsrZxm.getYfhj()!=null){%>value="<%=gzflsrZxm.getYfhj()%>"<%}%>/>
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
			<table width="100%">
				<tr>
					<td align="left">
						注一：奖励性绩效工资是指各单位按照职工收入考核办法确定的每月按系数定额发放的奖励性绩效工资。
					</td>
				</tr>
				<tr>
					<td align="left">
						注二：其它收入包括除每月按系数定额发放的奖励性绩效工资以外的奖励性绩效工资以及各类以奖励、补贴、福利等名义发放的个人收入及物资。
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



