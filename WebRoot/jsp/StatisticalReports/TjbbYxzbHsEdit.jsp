<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbHs"%>
<%@page import="com.safety.entity.TjbbYxzbHsZxm"%>
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
    <title>新增海事签证月度同比表（海事局）</title>
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
			if(str=="1"){
        		if(!verification())return;
			}
	   		var shr = document.getElementById("shr").value;
	   		if(shr==""){
	   			alert("请输入审核人");
	   			return;
	   		}
	   		var czrphone = document.getElementById("czrphone").value;
	   		if(czrphone==""){
	   			alert("请输入电话");
	   			return;
	   		}
	   		var yxfx = document.getElementById("yxfx").value;
	   		if(yxfx.length>500){
	   			alert("运行分析说明内容不能超过500字");
	   			return;
	   		}
	   		var form = document.getElementById("form1");
			form.action = "BbsbTjbbServlet?action=TjbbYxzbHsSave";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbTjbbServlet?action=getTjbbYxzbHs&flag=1&menuname="+menuname;
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
		    var path = "tempFile/tjbbyxzbhs";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbTjbbServlet?action=readTjbbYxzbHsExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
		
		//数据验证
		 function verification(){
        	var  zb1= document.getElementsByName("zb1");
        	var  zb2= document.getElementsByName("zb2");
        	var  zb3= document.getElementsByName("zb3");
        	var  zb4= document.getElementsByName("zb4");
        	var  zb5= document.getElementsByName("zb5");
        	var  zb6= document.getElementsByName("zb6");
         	for(var i=0;i<12;i++){
         		zb1[i].value = zb1[i].value.replace(/,/g,"");//删除所有逗号;
         		zb2[i].value = zb2[i].value.replace(/,/g,"");//删除所有逗号;
         		zb3[i].value = zb3[i].value.replace(/,/g,"");//删除所有逗号;
         		zb4[i].value = zb4[i].value.replace(/,/g,"");//删除所有逗号;
         		zb5[i].value = zb5[i].value.replace(/,/g,"");//删除所有逗号;
         		zb6[i].value = zb6[i].value.replace(/,/g,"");//删除所有逗号;
         		//空项全部置零
	        	if(zb1[i].value=="")zb1[i].value=0;
	        	if(zb2[i].value=="")zb2[i].value=0;
	        	if(zb3[i].value=="")zb3[i].value=0;
	        	if(zb4[i].value=="")zb4[i].value=0;
	        	if(zb5[i].value=="")zb5[i].value=0;
	        	if(zb6[i].value=="")zb6[i].value=0;
	        	zb4[i].value = (Number(zb2[i].value)+Number(zb3[i].value)).toFixed(2);
	        	if(zb5[i].value!=0){
        			zb6[i].value = ((Number(zb4[i].value)-Number(zb5[i].value))/Number(zb5[i].value)*100).toFixed(2);
        		}else{
        			zb6[i].value = 0;
        		}
				//空项全部置零
				if (zb1[i].value == 0)
					zb1[i].value = "";
				if (zb2[i].value == 0)
					zb2[i].value = "";
				if (zb3[i].value == 0)
					zb3[i].value = "";
				if (zb4[i].value == 0)
					zb4[i].value = "";
				if (zb5[i].value == 0)
					zb5[i].value = "";
				if (zb6[i].value == 0)
					zb6[i].value = "";
        	}
		 	//alert("全部校验成功");
		 	return true;
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
		<%
			//从request域中取得要显示的某页信息
		    //String permissions = (String)request.getAttribute("permissions"); 
		    TjbbYxzbHs tjbbYxzbHs = (TjbbYxzbHs)request.getAttribute("tjbbYxzbHs");
         	ArrayList TjbbYxzbHsZxmList = (ArrayList)request.getAttribute("TjbbYxzbHsZxmList");
		%>
		<form id="form1" name="TjbbYxzbHs" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="TjbbYxzbHs_id" id="TjbbYxzbHs_id" <%if(tjbbYxzbHs.getId()!=0){%>value="<%=tjbbYxzbHs.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;">
					<select name="year" id="year">
						<%if(tjbbYxzbHs.getYear()!=0){%>
						<option value="<%= tjbbYxzbHs.getYear() %>"><%= tjbbYxzbHs.getYear() %>年</option>
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
						<%if(tjbbYxzbHs.getMonth()!=0){%>
						<option value="<%= tjbbYxzbHs.getMonth() %>"><%= tjbbYxzbHs.getMonth() %>月</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>月</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%} }%>
					</select>
					<input name="bt" type="text" size="40" <%if(tjbbYxzbHs.getBt()!=null){%>value="<%=tjbbYxzbHs.getBt()%>"<%}else{%>value="海事签证月度同比表（运输量统计推算补充数据）"<%}%>/>
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
			<table width="700px">
				<tr>
					<td>
						<table width="100px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="50px" height="30px">区划名称
								</td>
								<td align="center" width="50px">月份
								</td>
							</tr>
							<tr>
								<td align="center" rowspan="12">南通市
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
						<table width="600px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="100px" height="30px">海事签证站点个数
								</td>
								<td align="center" width="100px">进港签证总量（吨）
								</td>
								<td align="center" width="100px">出港签证总量（吨）
								</td>
								<td align="center" width="100px">年初进、出港累计
								</td>
								<td align="center" width="100px">上年同期进、出港累计
								</td>
								<td align="center" width="100px">同比%
								</td>
							</tr>
							<%
				         	if(TjbbYxzbHsZxmList.size()==0){
							for(int i=0;i<12;i++){
							%>
							
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" value="" readonly="readonly"/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" value=""/>
								</td>
							</tr>
							<%
								}
							}else{
								for(int j=0;j< TjbbYxzbHsZxmList.size();j++){
									TjbbYxzbHsZxm tjbbYxzbHsZxm = (TjbbYxzbHsZxm)TjbbYxzbHsZxmList.get(j);
								%>
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb1()!=null){%>value="<%=tjbbYxzbHsZxm.getZb1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb2()!=null){%>value="<%=tjbbYxzbHsZxm.getZb2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb3()!=null){%>value="<%=tjbbYxzbHsZxm.getZb3()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb4()!=null){%>value="<%=tjbbYxzbHsZxm.getZb4()%>"<%}%> readonly="readonly"/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb5()!=null){%>value="<%=tjbbYxzbHsZxm.getZb5()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" <%if(tjbbYxzbHsZxm.getZb6()!=null){%>value="<%=tjbbYxzbHsZxm.getZb6()%>"<%}%>/>
								</td>
							</tr>
							<%} %>
						<%} %>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="left" colspan="3">
						运行分析说明：<textarea rows="3" cols="80" id="yxfx" name="yxfx"><%if(tjbbYxzbHs.getYxfx()!=null){%><%=tjbbYxzbHs.getYxfx()%><%}%></textarea>（500字以内）
					</td>
				</tr>
				<tr >
					<td colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						审核人：<input name="shr" id="shr" type="text" size="10" <%if(tjbbYxzbHs.getShr()!=null){%>value="<%=tjbbYxzbHs.getShr()%>"<%}%>/>
						<input name="shrID" type="hidden" size="10" <%if(tjbbYxzbHs.getShrID()!=null){%>value="<%=tjbbYxzbHs.getShrID()%>"<%}%>/>
					</td>
					<td align="center">
						填表人：<%=name%>
					</td>
					<td align="right">
						电话：<input name="czrphone" id="czrphone" type="text" size="10" <%if(tjbbYxzbHs.getCzrphone()!=null){%>value="<%=tjbbYxzbHs.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
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



