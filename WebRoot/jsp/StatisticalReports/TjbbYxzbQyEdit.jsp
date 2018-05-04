<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbQy"%>
<%@page import="com.safety.entity.TjbbYxzbQyZxm"%>
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
    <title>新增客运出站运输量统计表（汽运集团）</title>
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
	   		var sj = document.getElementById("sj").value;
	   		if(sj==""){
	   			alert("请输入报告时间");
	   			return;
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
			form.action = "BbsbTjbbServlet?action=TjbbYxzbQySave";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbTjbbServlet?action=getTjbbYxzbQy&flag=1&menuname="+menuname;
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
		    var path = "tempFile/tjbbyxzbqy";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbTjbbServlet?action=readTjbbYxzbQyExcel&URL="+msg;
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
			var zb4ls = document.getElementsByName("zb4ls");
         	for(var i=0;i<18;i++){
         		zb1[i].value = zb1[i].value.replace(/,/g,"");//删除所有逗号;
         		zb2[i].value = zb2[i].value.replace(/,/g,"");//删除所有逗号;
         		zb3[i].value = zb3[i].value.replace(/,/g,"");//删除所有逗号;
         		zb4[i].value = zb4[i].value.replace(/,/g,"");//删除所有逗号;
         		zb5[i].value = zb5[i].value.replace(/,/g,"");//删除所有逗号;
         		zb6[i].value = zb6[i].value.replace(/,/g,"");//删除所有逗号;
				zb4ls[i].value = zb4ls[i].value.replace(/,/g, "");//删除所有逗号;
         		//空项全部置零
	        	if(zb1[i].value=="")zb1[i].value=0;
	        	if(zb2[i].value=="")zb2[i].value=0;
	        	if(zb3[i].value=="")zb3[i].value=0;
	        	if(zb4[i].value=="")zb4[i].value=0;
	        	if(zb5[i].value=="")zb5[i].value=0;
	        	if(zb6[i].value=="")zb6[i].value=0;
				if (zb4ls[i].value == "")zb4ls[i].value = 0;
				zb4[i].value = (Number(zb2[i].value) + Number(zb4ls[i].value)).toFixed(2); 
        	}
        	zb1[8].value =(Number(zb1[0].value)+Number(zb1[1].value)+Number(zb1[2].value)+Number(zb1[3].value)+Number(zb1[4].value)+Number(zb1[5].value)+Number(zb1[6].value)+Number(zb1[7].value)).toFixed(2);
        	zb2[8].value =(Number(zb2[0].value)+Number(zb2[1].value)+Number(zb2[2].value)+Number(zb2[3].value)+Number(zb2[4].value)+Number(zb2[5].value)+Number(zb2[6].value)+Number(zb2[7].value)).toFixed(2);
        	zb4[8].value =(Number(zb4[0].value)+Number(zb4[1].value)+Number(zb4[2].value)+Number(zb4[3].value)+Number(zb4[4].value)+Number(zb4[5].value)+Number(zb4[6].value)+Number(zb4[7].value)).toFixed(2);
        	zb5[8].value =(Number(zb5[0].value)+Number(zb5[1].value)+Number(zb5[2].value)+Number(zb5[3].value)+Number(zb5[4].value)+Number(zb5[5].value)+Number(zb5[6].value)+Number(zb5[7].value)).toFixed(2);
         	
        	zb1[17].value =(Number(zb1[9].value)+Number(zb1[10].value)+Number(zb1[11].value)+Number(zb1[12].value)+Number(zb1[13].value)+Number(zb1[14].value)+Number(zb1[15].value)+Number(zb1[16].value)).toFixed(2);
        	zb2[17].value =(Number(zb2[9].value)+Number(zb2[10].value)+Number(zb2[11].value)+Number(zb2[12].value)+Number(zb2[13].value)+Number(zb2[14].value)+Number(zb2[15].value)+Number(zb2[16].value)).toFixed(2);
        	zb4[17].value =(Number(zb4[9].value)+Number(zb4[10].value)+Number(zb4[11].value)+Number(zb4[12].value)+Number(zb4[13].value)+Number(zb4[14].value)+Number(zb4[15].value)+Number(zb4[16].value)).toFixed(2);
        	zb5[17].value =(Number(zb5[9].value)+Number(zb5[10].value)+Number(zb5[11].value)+Number(zb5[12].value)+Number(zb5[13].value)+Number(zb5[14].value)+Number(zb5[15].value)+Number(zb5[16].value)).toFixed(2);
         	
         	for(var i=0;i<18;i++){
	        	if(zb1[i].value!=0){
        			zb3[i].value = ((Number(zb2[i].value)-Number(zb1[i].value))/Number(zb1[i].value)*100).toFixed(2);
        		}else{
        			zb3[i].value = 0;
        		}
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
		    TjbbYxzbQy tjbbYxzbQy = (TjbbYxzbQy)request.getAttribute("tjbbYxzbQy");
         	ArrayList TjbbYxzbQyZxmList = (ArrayList)request.getAttribute("TjbbYxzbQyZxmList");
         	ArrayList TjbbYxzbQyZxmListHistory = (ArrayList)request.getAttribute("TjbbYxzbQyZxmListHistory");
		%>
		<%
			if(TjbbYxzbQyZxmListHistory.size()>0){
				for(int ls=0;ls<18;ls++){
					TjbbYxzbQyZxm tjbbYxzbQyZxmls = (TjbbYxzbQyZxm)TjbbYxzbQyZxmListHistory.get(ls);
		%>
		<input name="zb4ls" type="hidden" size="15" <%if(tjbbYxzbQyZxmls.getZb4()!=null){%>value="<%=tjbbYxzbQyZxmls.getZb4()%>"<%}%>/>
		<%}}else{
				for(int ls=0;ls<18;ls++){%>	
		<input name="zb4ls" type="hidden" size="15" value=""/>
		<%}}%>	
		<form id="form1" name="TjbbYxzbQy" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="TjbbYxzbQy_id" id="TjbbYxzbQy_id" <%if(tjbbYxzbQy.getId()!=0){%>value="<%=tjbbYxzbQy.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					<input name="sj" id="sj" type="text" size="10" <%if(tjbbYxzbQy.getSj()!=null){%>value="<%=tjbbYxzbQy.getSj()%>"<%}%> readonly/>
					<input name="Button" class="button1" onclick="setDay(document.TjbbYxzbQy.sj);" type="button" value="选择">
					<input name="bt" type="text" size="20" <%if(tjbbYxzbQy.getBt()!=null){%>value="<%=tjbbYxzbQy.getBt()%>"<%}else{%>value="客运出站运输量统计表"<%}%>/>
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
			<table width="1200px">
				<tr>
					<td>
						<table width="300px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="150px" height="30px">车站名称（含下属站）
								</td>
								<td align="center" width="150px">指标名称
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">南通汽车站
								</td>
								<td align="center" rowspan="8">客运量（万人）
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">客运东站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">启东汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">如皋汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">海安汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">海门汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">如东汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">通州汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">&nbsp;
								</td>
								<td align="center">合计
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">南通汽车站
								</td>
								<td align="center" rowspan="8">周转量（万人公里）
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">客运东站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">启东汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">如皋汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">海安汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">海门汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">如东汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">通州汽车站
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">&nbsp;
								</td>
								<td align="center">合计
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table width="900px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="150px" height="30px">上月数（1）
								</td>
								<td align="center" width="150px">本月数（2）
								</td>
								<td align="center" width="150px">环比增减%（3）
								</td>
								<td align="center" width="150px">本年自年初累计数（4）
								</td>
								<td align="center" width="150px">上年自年初累计数（5）
								</td>
								<td align="center" width="150px">与上年累计数同比%（6）
								</td>
							</tr>
							<%
				         	if(TjbbYxzbQyZxmList.size()==0){
							for(int i=0;i<18;i++){
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
							</tr>
							<%
								}
							}else{
								for(int j=0;j< TjbbYxzbQyZxmList.size();j++){
									TjbbYxzbQyZxm tjbbYxzbQyZxm = (TjbbYxzbQyZxm)TjbbYxzbQyZxmList.get(j);
								%>
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb1()!=null){%>value="<%=tjbbYxzbQyZxm.getZb1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb2()!=null){%>value="<%=tjbbYxzbQyZxm.getZb2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb3()!=null){%>value="<%=tjbbYxzbQyZxm.getZb3()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb4()!=null){%>value="<%=tjbbYxzbQyZxm.getZb4()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb5()!=null){%>value="<%=tjbbYxzbQyZxm.getZb5()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" <%if(tjbbYxzbQyZxm.getZb6()!=null){%>value="<%=tjbbYxzbQyZxm.getZb6()%>"<%}%>/>
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
						运行分析说明：<textarea rows="3" cols="80" id="yxfx" name="yxfx"><%if(tjbbYxzbQy.getYxfx()!=null){%><%=tjbbYxzbQy.getYxfx()%><%}%></textarea>（500字以内）
					</td>
				</tr>
				<tr >
					<td colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						审核人：<input name="shr" id="shr" type="text" size="10" <%if(tjbbYxzbQy.getShr()!=null){%>value="<%=tjbbYxzbQy.getShr()%>"<%}%>/>
						<input name="shrID" type="hidden" size="10" <%if(tjbbYxzbQy.getShrID()!=null){%>value="<%=tjbbYxzbQy.getShrID()%>"<%}%>/>
					</td>
					<td align="center">
						填表人：<%=name%>
					</td>
					<td align="right">
						电话：<input name="czrphone" id="czrphone" type="text" size="10" <%if(tjbbYxzbQy.getCzrphone()!=null){%>value="<%=tjbbYxzbQy.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
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



