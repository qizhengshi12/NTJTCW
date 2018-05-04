<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSgjfb"%>
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
   	CwbbSgjfb cwbbSgjfb = (CwbbSgjfb)request.getAttribute("cwbbSgjfb");
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增修改三公经费表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
        		verification();
			}
        	var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=sgjfbSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
			form.action = "BbsbCwbbServlet?action=getCwbbSgjfb";
			form.submit();
        }
       
               
        //数据验证
        function verification(){
        	//2、公务接待费：（1）基本支出列支（2）项目支出列支
        	var jbzc1 = document.getElementById("jbzc1").value;
        	var jbzc2 = document.getElementById("jbzc2").value;
        	var xmzc1 = document.getElementById("xmzc1").value;
        	var xmzc2 = document.getElementById("xmzc2").value;
        	if(jbzc1=="")jbzc1=0;
        	document.getElementById("jbzc1").value=jbzc1;
        	if(jbzc2=="")jbzc2=0;
        	document.getElementById("jbzc2").value=jbzc2;
        	if(xmzc1=="")xmzc1=0;
        	document.getElementById("xmzc1").value=xmzc1;
        	if(xmzc2=="")xmzc2=0;
        	document.getElementById("xmzc2").value=xmzc2;
        	if(jbzc1==0&&xmzc1==0){
        		document.getElementById("jdf1").value = 0;
        	}else{
        		document.getElementById("jdf1").value = (Number(jbzc1)+Number(xmzc1)).toFixed(2);
        	}
        	if(jbzc2==0&&xmzc2==0){
        		document.getElementById("jdf2").value = 0;
        	}else{
        		document.getElementById("jdf2").value = (Number(jbzc2)+Number(xmzc2)).toFixed(2);
        	}
        	
        	//3、公务用车费：（1）车辆购置费（2）车辆运行维护费
        	var gzf1 = document.getElementById("gzf1").value;
        	var gzf2 = document.getElementById("gzf2").value;
        	var whf1 = document.getElementById("whf1").value;
        	var whf2 = document.getElementById("whf2").value;
        	if(gzf1=="")gzf1=0;
        	document.getElementById("gzf1").value=gzf1;
        	if(gzf2=="")gzf2=0;
        	document.getElementById("gzf2").value=gzf2;
        	if(whf1=="")whf1=0;
        	document.getElementById("whf1").value=whf1;
        	if(whf2=="")whf2=0;
        	document.getElementById("whf2").value=whf2;
        	if(gzf1==0&&whf1==0){
        		document.getElementById("ycf1").value = 0;
        	}else{
        		document.getElementById("ycf1").value = (Number(gzf1)+Number(whf1)).toFixed(2);
        	}
        	if(gzf2==0&&whf2==0){
        		document.getElementById("ycf2").value = 0;
        	}else{
        		document.getElementById("ycf2").value = (Number(gzf2)+Number(whf2)).toFixed(2);
        	}
        	//合  计:1、因公出国（境）费用2、公务接待费3、公务用车费4、会议费
        	var cgf1 = document.getElementById("cgf1").value;
        	var cgf2 = document.getElementById("cgf2").value;
        	var jdf1 = document.getElementById("jdf1").value;
        	var jdf2 = document.getElementById("jdf2").value;
        	var ycf1 = document.getElementById("ycf1").value;
        	var ycf2 = document.getElementById("ycf2").value;
        	var hyf1 = document.getElementById("hyf1").value;
        	var hyf2 = document.getElementById("hyf2").value;
        	if(cgf1=="")cgf1=0;
        	document.getElementById("cgf1").value=cgf1;
        	if(cgf2=="")cgf2=0;
        	document.getElementById("cgf2").value=cgf2;
        	if(jdf1=="")jdf1=0;
        	document.getElementById("jdf1").value=jdf1;
        	if(jdf2=="")jdf2=0;
        	document.getElementById("jdf2").value=jdf2;
        	if(ycf1=="")ycf1=0;
        	document.getElementById("ycf1").value=ycf1;
        	if(ycf2=="")ycf2=0;
        	document.getElementById("ycf2").value=ycf2;
        	if(hyf1=="")hyf1=0;
        	document.getElementById("hyf1").value=hyf1;
        	if(hyf2=="")hyf2=0;
        	document.getElementById("hyf2").value=hyf2;
        	document.getElementById("hj1").value = (Number(cgf1)+Number(jdf1)+Number(ycf1)+Number(hyf1)).toFixed(2);
        	document.getElementById("hj2").value = (Number(cgf2)+Number(jdf2)+Number(ycf2)+Number(hyf2)).toFixed(2);
        
        	//支出比例
        	if(cgf1!=0){
        		document.getElementById("cgf3").value=(Number(cgf2)/Number(cgf1)*100).toFixed(2)+"%";
        	}
        	if(jdf1!=0){
        		document.getElementById("jdf3").value=(Number(jdf2)/Number(jdf1)*100).toFixed(2)+"%";
        	}
        	if(jbzc1!=0){
        		document.getElementById("jbzc3").value=(Number(jbzc2)/Number(jbzc1)*100).toFixed(2)+"%";
        	}
        	if(xmzc1!=0){
        		document.getElementById("xmzc3").value=(Number(xmzc2)/Number(xmzc1)*100).toFixed(2)+"%";
        	}
        	if(ycf1!=0){
        		document.getElementById("ycf3").value=(Number(ycf2)/Number(ycf1)*100).toFixed(2)+"%";
        	}
        	if(hyf1!=0){
        		document.getElementById("hyf3").value=(Number(hyf2)/Number(hyf1)*100).toFixed(2)+"%";
        	}
        	if(gzf1!=0){
        		document.getElementById("gzf3").value=(Number(gzf2)/Number(gzf1)*100).toFixed(2)+"%";
        	}
        	if(whf1!=0){
        		document.getElementById("whf3").value=(Number(whf2)/Number(whf1)*100).toFixed(2)+"%";
        	}
        	
        	var hj1 = document.getElementById("hj1").value;
        	var hj2 = document.getElementById("hj2").value;
        	if(hj1!=0 && hj1!=0.00){
        		document.getElementById("hj3").value=(Number(hj2)/Number(hj1)*100).toFixed(2)+"%";
        	}
        	
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
		    var path = "tempFile/sgjfb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readSgjfbExcel&URL="+msg;
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
		<input type="hidden" name="cwbbSgjfb_id" id="cwbbSgjfb_id" <%if(cwbbSgjfb.getId()!=0){%>value="<%=cwbbSgjfb.getId()%>"<%}%> >
		<input type="hidden" name="czrdw" id="czrdw" <%if("".equals(cwbbSgjfb.getCzrdw())&&cwbbSgjfb.getCzrdw()!=null){%>value="<%=cwbbSgjfb.getCzrdw()%>"<%}else{%>value="<%=company%>"<%}%> >
			<table  id="table1" width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<select name="year" id="year">
						<%if(cwbbSgjfb.getYear()!=0){%>
						<option value="<%= cwbbSgjfb.getYear() %>"><%= cwbbSgjfb.getYear() %>年</option>
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
						<%if(cwbbSgjfb.getMonth()!=0){%>
						<option value="<%= cwbbSgjfb.getMonth() %>"><%= cwbbSgjfb.getMonth() %>月</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>月</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%} }%>
					</select>三公经费表
					</td>
				</tr>
				<tr>
					<td align="left" width="50%">
						填报单位：<%=cwbbSgjfb.getCzrdw() %>
						<input name="newcompany" id="newcompany" type="hidden"  value="<%=cwbbSgjfb.getCzrdw() %>" readonly="readonly" class="STYLE1"/>
					</td>
					<td align="right" width="50%">
						单位：万元
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">项目
					</td>
					<td align="center" width="20%">预算数
					</td>
					<td align="center" width="20%">支出数
					</td>
					<td align="center" width="20%">支出比例%
					</td>
				</tr>
				<tr>
					<td align="center">合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计
					</td>
					<td align="center">
					<input name="hj1" id="hj1" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getHj1()!=null){%>value="<%=cwbbSgjfb.getHj1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="hj2" id="hj2" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getHj2()!=null){%>value="<%=cwbbSgjfb.getHj2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="hj3" id="hj3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getHj3()!=null){%>value="<%=cwbbSgjfb.getHj3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">1、因公出国（境）费用
					</td>
					<td align="center">
					<input name="cgf1" id="cgf1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getCgf1()!=null){%>value="<%=cwbbSgjfb.getCgf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="cgf2" id="cgf2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getCgf2()!=null){%>value="<%=cwbbSgjfb.getCgf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="cgf3" id="cgf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getCgf3()!=null){%>value="<%=cwbbSgjfb.getCgf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">2、公务接待费
					</td>
					<td align="center">
					<input name="jdf1" id="jdf1" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getJdf1()!=null){%>value="<%=cwbbSgjfb.getJdf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jdf2" id="jdf2" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getJdf2()!=null){%>value="<%=cwbbSgjfb.getJdf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jdf3" id="jdf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getJdf3()!=null){%>value="<%=cwbbSgjfb.getJdf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其中：（1）基本支出列支
					</td>
					<td align="center">
					<input name="jbzc1" id="jbzc1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getJbzc1()!=null){%>value="<%=cwbbSgjfb.getJbzc1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jbzc2" id="jbzc2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getJbzc2()!=null){%>value="<%=cwbbSgjfb.getJbzc2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="jbzc3" id="jbzc3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getJbzc3()!=null){%>value="<%=cwbbSgjfb.getJbzc3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）项目支出列支
					</td>
					<td align="center">
					<input name="xmzc1" id="xmzc1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getXmzc1()!=null){%>value="<%=cwbbSgjfb.getXmzc1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xmzc2" id="xmzc2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getXmzc2()!=null){%>value="<%=cwbbSgjfb.getXmzc2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="xmzc3" id="xmzc3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getXmzc3()!=null){%>value="<%=cwbbSgjfb.getXmzc3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">3、公务用车费
					</td>
					<td align="center">
					<input name="ycf1" id="ycf1" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getYcf1()!=null){%>value="<%=cwbbSgjfb.getYcf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ycf2" id="ycf2" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getYcf2()!=null){%>value="<%=cwbbSgjfb.getYcf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ycf3" id="ycf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getYcf3()!=null){%>value="<%=cwbbSgjfb.getYcf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其中：（1）车辆购置费
					</td>
					<td align="center">
					<input name="gzf1" id="gzf1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getGzf1()!=null){%>value="<%=cwbbSgjfb.getGzf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gzf2" id="gzf2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getGzf2()!=null){%>value="<%=cwbbSgjfb.getGzf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="gzf3" id="gzf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getGzf3()!=null){%>value="<%=cwbbSgjfb.getGzf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）车辆运行维护费
					</td>
					<td align="center">
					<input name="whf1" id="whf1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getWhf1()!=null){%>value="<%=cwbbSgjfb.getWhf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="whf2" id="whf2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getWhf2()!=null){%>value="<%=cwbbSgjfb.getWhf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="whf3" id="whf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getWhf3()!=null){%>value="<%=cwbbSgjfb.getWhf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">4、会议费
					</td>
					<td align="center">
					<input name="hyf1" id="hyf1" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getHyf1()!=null){%>value="<%=cwbbSgjfb.getHyf1()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="hyf2" id="hyf2" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSgjfb.getHyf2()!=null){%>value="<%=cwbbSgjfb.getHyf2()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="hyf3" id="hyf3" type="text" size="6" readonly="readonly" <%if(cwbbSgjfb.getHyf3()!=null){%>value="<%=cwbbSgjfb.getHyf3()%>"<%}%>/>
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



