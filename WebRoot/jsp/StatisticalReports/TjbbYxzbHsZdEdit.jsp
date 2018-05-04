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
			if(str=="1"){
        		verification();
			}
	   		var zdmc = document.getElementById("zdmc").value;
	   		if(zdmc==""){
	   			alert("请输入站点名称");
	   			return;
	   		}
	   		var form = document.getElementById("form1");
			form.action = "BbsbTjbbServlet?action=TjbbYxzbHsZdSave";
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
	        	var sheetNum = document.getElementById("sheetNum").value;
	        	var sheetNum1 = document.getElementById("sheetNum1").value;
	        	var sheetNum2 = document.getElementById("sheetNum2").value;
	        	if(sheetNum1!=""&&sheetNum2!=""){
					form.action = "BbsbTjbbServlet?action=readTjbbYxzbHsZdExcelAll&URL="+msg+"&sheetNum1="+sheetNum1+"&sheetNum2="+sheetNum2;
				}else{
					form.action = "BbsbTjbbServlet?action=readTjbbYxzbHsZdExcel&URL="+msg+"&sheetNum="+sheetNum;
				}
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
        	var  zb7= document.getElementsByName("zb7");
        	var  zb8= document.getElementsByName("zb8");
        	var  zb9= document.getElementsByName("zb9");
        	var  zb10= document.getElementsByName("zb10");
         	for(var i=0;i<12;i++){
         		//空项全部置零
	        	if(zb1[i].value=="")zb1[i].value=0;
	        	if(zb2[i].value=="")zb2[i].value=0;
	        	if(zb3[i].value=="")zb3[i].value=0;
	        	if(zb4[i].value=="")zb4[i].value=0;
	        	if(zb5[i].value=="")zb5[i].value=0;
	        	if(zb6[i].value=="")zb6[i].value=0;
	        	if(zb7[i].value=="")zb7[i].value=0;
	        	if(zb8[i].value=="")zb8[i].value=0;
	        	if(zb9[i].value=="")zb9[i].value=0;
	        	if(zb10[i].value=="")zb10[i].value=0;
        		zb1[i].value = Number(zb2[i].value)+Number(zb3[i].value)+Number(zb4[i].value)+Number(zb5[i].value);
        		zb6[i].value = Number(zb7[i].value)+Number(zb8[i].value)+Number(zb9[i].value)+Number(zb10[i].value);
	        		
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
				if (zb7[i].value == 0)
					zb7[i].value = "";
				if (zb8[i].value == 0)
					zb8[i].value = "";
				if (zb9[i].value == 0)
					zb9[i].value = "";
				if (zb10[i].value == 0)
					zb10[i].value = "";
        	}
		 	//alert("全部校验成功");
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
		    TjbbYxzbHsZd tjbbYxzbHsZd = (TjbbYxzbHsZd)request.getAttribute("tjbbYxzbHsZd");
         	ArrayList TjbbYxzbHsZdZxmList = (ArrayList)request.getAttribute("TjbbYxzbHsZdZxmList");
		%>
		<form id="form1" name="TjbbYxzbHsZd" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="TjbbYxzbHsZd_id" id="TjbbYxzbHsZd_id" <%if(tjbbYxzbHsZd.getId()!=0){%>value="<%=tjbbYxzbHsZd.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;">
					<select name="year" id="year">
						<%if(tjbbYxzbHsZd.getYear()!=0){%>
						<option value="<%= tjbbYxzbHsZd.getYear() %>"><%= tjbbYxzbHsZd.getYear() %>年</option>
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
						<%if(tjbbYxzbHsZd.getMonth()!=0){%>
						<option value="<%= tjbbYxzbHsZd.getMonth() %>"><%= tjbbYxzbHsZd.getMonth() %>月</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>月</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>月</option>
						<%} }%>
					</select>
					<input name="bt" type="text" size="40" <%if(tjbbYxzbHsZd.getBt()!=null){%>value="<%=tjbbYxzbHsZd.getBt()%>"<%}else{%>value="海事站点签证数据"<%}%>/>
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
				<tr>
					<td align="left">
						联系人：<input name="lxr" id="lxr" type="text" size="10" <%if(tjbbYxzbHsZd.getLxr()!=null){%>value="<%=tjbbYxzbHsZd.getLxr()%>"<%}%>/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<input name="lxrdh" id="lxrdh" type="text" size="10" <%if(tjbbYxzbHsZd.getLxrdh()!=null){%>value="<%=tjbbYxzbHsZd.getLxrdh()%>"<%}%>/>
					</td>
				</tr>
			</table>
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
				         	if(TjbbYxzbHsZdZxmList.size()==0){
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
								<input name="zb4" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb7" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb8" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb9" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb10" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb11" type="text" size="15" value=""/>
								</td>
							</tr>
							<%
								}
							}else{
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
						<%} %>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr >
					<td>
						注1：各市请在12月2日前上报2013年1至10月市县级月度运输量统计推算补充数据，11-12月数据可延迟在1月5日之前上报。
					</td>
				</tr>
				<tr >
					<td>
						注2：填报范围为全市地方海事签证情况，填写2014年各月情况。
					</td>
				</tr>
				<tr >
					<td>
						注3：签证船舶载重总量分吨位情况，按总吨吨位分类填写。
					</td>
				</tr>
				<tr >
					<td>
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
				上传单个EXCEL：
				第<input id="sheetNum" type="text" size="5" value="1" />个文件
				<img alt="计算" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
				</td>
			</tr>
			<%if(tjbbYxzbHsZd.getId()==0){%>
			<tr>
				<td align="left">
				上传多个EXCEL：
				第<input id="sheetNum1" type="text" size="5" value="" />个文件到第<input id="sheetNum2" type="text" size="5" value="" />个文件
				</td>
			</tr>
			<%} %>
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



