<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbHsZd"%>
<%@page import="com.safety.entity.TjbbYxzbHsZdZxm"%>
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
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增海事局站点</title>
    <style type="text/css">
		.ex{top:0px; right:0px; position:fixed;background-color: #CCCCCC;} 
    </style>
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
	   		var form = document.getElementById("form1");
			form.action = "BbsbTjbbServlet?action=TjbbYxzbHsZdSaveAll";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+menuname;
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
		    var path = "tempFile/tjbbyxzbhszd";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
	        	var sheetNum1 = document.getElementById("sheetNum1").value;
	        	var sheetNum2 = document.getElementById("sheetNum2").value;
				form.action = "BbsbTjbbServlet?action=readTjbbYxzbHsZdExcelAll&URL="+msg+"&sheetNum1="+sheetNum1+"&sheetNum2="+sheetNum2;
				form.submit();
	       	}else{
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
		
	
	</script>
	</head>
	<body>
	<%
		if(request.getAttribute("result")!= null) {
	%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
	<%
 		}
 	%>
		<form id="form1" name="TjbbYxzbHsZd" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;">
					<select name="year" id="year">
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>年</option>
						<%}%>
					</select>
					&nbsp;1到&nbsp;
					<select name="month" id="month">
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%}%>
					</select>
					</td>
				</tr>
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						填报单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<%
			//从request域中取得要显示的某页信息
		    //String permissions = (String)request.getAttribute("permissions"); 
		    int all = (Integer)request.getAttribute("all");
		    for(int k=1;k<=all;k++){
		    TjbbYxzbHsZd tjbbYxzbHsZd = (TjbbYxzbHsZd)request.getAttribute("tjbbYxzbHsZd"+k);
         	ArrayList TjbbYxzbHsZdZxmList = (ArrayList)request.getAttribute("TjbbYxzbHsZdZxmList"+k);
			%>
			<table width="100%">
				<tr>
					<td align="center">
					<input name="bt" type="text" size="40" <%if(tjbbYxzbHsZd.getBt()!=null){%>value="<%=tjbbYxzbHsZd.getBt()%>"<%}else{%>value="海事站点签证数据"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
						联系人：<input name="lxr" id="lxr" type="text" size="10" <%if(tjbbYxzbHsZd.getLxr()!=null){%>value="<%=tjbbYxzbHsZd.getLxr()%>"<%}%>/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<input name="lxrdh" id="lxrdh" type="text" size="10" <%if(tjbbYxzbHsZd.getLxrdh()!=null){%>value="<%=tjbbYxzbHsZd.getLxrdh()%>"<%}%>/>
					</td>
				</tr>
			</table>
			<table width="1250px">
				<tr>
					<td>
						<table width="150px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="100px" height="100px" >站点名称
								</td>
								<td align="center" width="50px">月份
								</td>
							</tr>
							<tr>
								<td align="center" rowspan="12">
								<input name="zdmc" id="zdmc" type="text" size="10" <%if(tjbbYxzbHsZd.getZdmc()!=null){%>value="<%=tjbbYxzbHsZd.getZdmc()%>"<%}%>/>
								</td>
								<td align="center" height="30px">1
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">2
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">3
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">4
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">5
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">6
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">7
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">8
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">9
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">10
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">11
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">12
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table width="1100px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="500px" height="50px" colspan="5">进港签证总量（吨）
								</td>
								<td align="center" width="500px" colspan="5">出港签证总量（吨）
								</td>
								<td align="center" width="100px" rowspan="2">海事签证涉及港口范围
								</td>
							</tr>
							<tr>
								<td align="center" width="100px" height="50px">总计
								</td>
								<td align="center" width="100px">1000吨(含)以下船舶
								</td>
								<td align="center" width="100px">1000—5000吨(含)船舶
								</td>
								<td align="center" width="100px">5000—10000吨(含)船舶
								</td>
								<td align="center" width="100px">10000吨以上船舶
								</td>
								<td align="center" width="100px">总计
								</td>
								<td align="center" width="100px">1000吨(含)以下船舶
								</td>
								<td align="center" width="100px">1000—5000吨(含)船舶
								</td>
								<td align="center" width="100px">5000—10000吨(含)船舶
								</td>
								<td align="center" width="100px">10000吨以上船舶
								</td>
							</tr>
							<%
								for(int j=0;j< TjbbYxzbHsZdZxmList.size();j++){
									TjbbYxzbHsZdZxm tjbbYxzbHsZdZxm = (TjbbYxzbHsZdZxm)TjbbYxzbHsZdZxmList.get(j);
								%>
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb1()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb2()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb3()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb3()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb4()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb4()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb5()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb5()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb6()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb6()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb7" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb7()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb7()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb8" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb8()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb8()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb9" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb9()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb9()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb10" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb10()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb10()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb11" type="text" size="15" <%if(tjbbYxzbHsZdZxm.getZb11()!=null){%>value="<%=tjbbYxzbHsZdZxm.getZb11()%>"<%}%>/>
								</td>
							</tr>
						<%} %>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%} %>
		</form>
		<form id="form2"  method="post"></form>
		<form id="form3" method="post"  enctype="multipart/form-data" target="ifm">
		<table  width="100%">
			<tr>
				<td align="left">
				上传多个EXCEL：
				第<input id="sheetNum1" type="text" size="5" value="" />个文件到第<input id="sheetNum2" type="text" size="5" value="" />个文件
				</td>
			</tr>
			<tr>
				<td align="left">
				<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
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



