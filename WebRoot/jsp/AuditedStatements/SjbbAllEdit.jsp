<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbAll"%>
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
    <title>新增审计统计表</title>
    <style type="text/css">
		.ex{top:0px; right:0px; position:fixed;background-color: #CCCCCC;} 
    </style>
    <script src="calendar.js"></script>
	<script type="text/javascript">
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
	   		var form = document.getElementById("form1");
			form.action = "BbsbSjbbServlet?action=SjbbAllSave";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbSjbbServlet?action=getSjbbAll&flag=1&menuname="+menuname;
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
		    var path = "tempFile/sjbball";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=readSjbbAllExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("上传冲突，请稍后再试");
	       		return;
	       	}
		}
		
		//数据验证
		 function verification(){
		 	//1表内
        	var  zb1= document.getElementById("zb1").value;
        	var  zb2= document.getElementById("zb2").value;
        	var  zb3= document.getElementById("zb3").value;
        	var  zb4= document.getElementById("zb4").value;
        	var  zb5= document.getElementById("zb5").value;
        	var  zb6= document.getElementById("zb6").value;
        	var  zb7= document.getElementById("zb7").value;
        	var  zb8= document.getElementById("zb8").value;
        	var  zb9= document.getElementById("zb9").value;
        	var  zb10= document.getElementById("zb10").value;
        	var  zb11= document.getElementById("zb11").value;
        	var  zb12= document.getElementById("zb12").value;
        	var  zb13= document.getElementById("zb13").value;
        	var  zb14= document.getElementById("zb14").value;
        	var  zb15= document.getElementById("zb15").value;
        	var  zb16= document.getElementById("zb16").value;
        	var  zb17= document.getElementById("zb17").value;
        	var  zb18= document.getElementById("zb18").value;
        	var  zb19= document.getElementById("zb19").value;
        	var  zb20= document.getElementById("zb20").value;
        	var  zb22= document.getElementById("zb22").value;
        	var  zb23= document.getElementById("zb23").value;
		 	if(Number(zb1)<Number(zb2)){
		 		alert("交审计1表：01行≥02行");
		 		return;
		 	}
		 	if(Number(zb3)<Number(zb4)){
		 		alert("交审计1表：03行≥04行");
		 		return;
		 	}
		 	var f1 = Number(zb6)+Number(zb7)+Number(zb8)+Number(zb9)+Number(zb10)+Number(zb11)+Number(zb12)+Number(zb13);
		 	if(Number(zb5)!=Number(f1)){
		 		alert("交审计1表：05行=06行+07行+08行+09行+10行+11行+12行+13行");
		 		return;
		 	}
		 	var f2 = Number(zb16)+Number(zb17)+Number(zb18);
		 	if(Number(zb15)<Number(f2)){
		 		alert("交审计1表：15行≥16行+17行+18行");
		 		return;
		 	}
		 	//2表内
        	var  zbs1= document.getElementById("zbs1").value;
        	var  zbs2= document.getElementById("zbs2").value;
        	var  zbs3= document.getElementById("zbs3").value;
        	var  zbs4= document.getElementById("zbs4").value;
        	var  zbs5= document.getElementById("zbs5").value;
        	var  zbs6= document.getElementById("zbs6").value;
        	var  zbs7= document.getElementById("zbs7").value;
        	var  zbs8= document.getElementById("zbs8").value;
        	var  zbs9= document.getElementById("zbs9").value;
        	var  zbs10= document.getElementById("zbs10").value;
        	var  zbs11= document.getElementById("zbs11").value;
        	var  zbs16= document.getElementById("zbs16").value;
        	var  zbs18= document.getElementById("zbs18").value;
		 	var f3 = Number(zbs4)+Number(zbs5)+Number(zbs6)+Number(zbs7)+Number(zbs8);
		 	if(Number(zbs3)<Number(f3)){
		 		alert("交审计2表：03行≥04行+05行+06行+07行+08行");
		 		return;
		 	}
		 	//3表内
        	var  zbt1= document.getElementById("zbt1").value;
        	var  zbt2= document.getElementById("zbt2").value;
        	var  zbt3= document.getElementById("zbt3").value;
        	var  zbt4= document.getElementById("zbt4").value;
        	var  zbt5= document.getElementById("zbt5").value;
        	var  zbt6= document.getElementById("zbt6").value;
        	var  zbt7= document.getElementById("zbt7").value;
        	var  zbt8= document.getElementById("zbt8").value;
        	var  zbt10= document.getElementById("zbt10").value;
        	var  zbt11= document.getElementById("zbt11").value;
        	var  zbt12= document.getElementById("zbt12").value;
        	var  zbt13= document.getElementById("zbt13").value;
        	var  zbt14= document.getElementById("zbt14").value;
        	var  zbt15= document.getElementById("zbt15").value;
        	var  zbt16= document.getElementById("zbt16").value;
        	var  zbt17= document.getElementById("zbt17").value;
        	var  zbt18= document.getElementById("zbt18").value;
        	var  zbt19= document.getElementById("zbt19").value;
        	var  zbt20= document.getElementById("zbt20").value;
        	var  zbt21= document.getElementById("zbt21").value;
		 	var f4 = Number(zbt2)+Number(zbt3)+Number(zbt4);
		 	if(Number(zbt1)!=Number(f4)){
		 		alert("交审计3表：01行=02行+03行+04行");
		 		return;
		 	}
		 	var f5 = Number(zbt6)+Number(zbt7)+Number(zbt8);
		 	if(Number(zbt5)!=Number(f5)){
		 		alert("交审计3表：05行=06行+07行+08行");
		 		return;
		 	}
		 	var f6 = Number(zbt11)+Number(zbt12)+Number(zbt13)+Number(zbt14)+Number(zbt15)+Number(zbt16);
		 	if(Number(zbt10)<Number(f6)){
		 		alert("交审计3表：10行≥11行+12行+13行+14行+15行+16行");
		 		return;
		 	}
		 	if(Number(zbt19)<Number(zbt20)){
		 		alert("交审计3表：19行≥20行");
		 		return;
		 	}
		 	//4表内
        	var  zbf1= document.getElementById("zbf1").value;
        	var  zbf2= document.getElementById("zbf2").value;
        	var  zbf3= document.getElementById("zbf3").value;
        	var  zbf4= document.getElementById("zbf4").value;
        	var  zbf5= document.getElementById("zbf5").value;
        	var  zbf6= document.getElementById("zbf6").value;
        	var  zbf7= document.getElementById("zbf7").value;
        	var  zbf8= document.getElementById("zbf8").value;
        	var  zbf9= document.getElementById("zbf9").value;
        	var  zbf10= document.getElementById("zbf10").value;
        	var  zbf11= document.getElementById("zbf11").value;
        	var  zbf12= document.getElementById("zbf12").value;
        	var  zbf13= document.getElementById("zbf13").value;
        	var  zbf14= document.getElementById("zbf14").value;
        	var  zbf15= document.getElementById("zbf15").value;
        	var  zbf16= document.getElementById("zbf16").value;
        	var  zbf17= document.getElementById("zbf17").value;
        	var  zbf18= document.getElementById("zbf18").value;
        	var  zbf19= document.getElementById("zbf19").value;
        	var  zbf20= document.getElementById("zbf20").value;
        	var  zbf21= document.getElementById("zbf21").value;
        	var  zbf22= document.getElementById("zbf22").value;
        	var  zbf24= document.getElementById("zbf24").value;
        	var  zbf25= document.getElementById("zbf25").value;
        	var  zbf26= document.getElementById("zbf26").value;
        	var  zbf27= document.getElementById("zbf27").value;
        	var  zbf28= document.getElementById("zbf28").value;
        	var  zbf33= document.getElementById("zbf33").value;
		 	var f7 = Number(zbf2)+Number(zbf3)+Number(zbf4);
		 	if(Number(zbf1)!=Number(f7)){
		 		alert("交审计4表：01行=02行+03行+04行");
		 		return;
		 	}
		 	var f8 = Number(zbf6)+Number(zbf7)+Number(zbf8);
		 	if(Number(zbf5)!=Number(f8)){
		 		alert("交审计4表：05行=06行+07行+08行");
		 		return;
		 	}
		 	var f9 = Number(zbf10)+Number(zbf11)+Number(zbf12);
		 	if(Number(zbf9)<Number(f9)){
		 		alert("交审计4表：09行≥10行+11行+12行");
		 		return;
		 	}
		 	var f10 = Number(zbf14)+Number(zbf15)+Number(zbf16);
		 	if(Number(zbf13)<Number(f10)){
		 		alert("交审计4表：13行≥14行+15行+16行");
		 		return;
		 	}
		 	var f11 = Number(zbf18)+Number(zbf19)+Number(zbf20);
		 	if(Number(zbf17)<Number(f11)){
		 		alert("交审计4表：17行≥18行+19行+20行");
		 		return;
		 	}
		 	if(Number(zbf21)<Number(zbf22)){
		 		alert("交审计4表：21行≥22行");
		 		return;
		 	}
		 	var f12 = Number(zbf25)+Number(zbf26)+Number(zbf27)+Number(zbf28);
		 	if(Number(zbf24)<Number(f12)){
		 		alert("交审计4表：24行≥25行+26行+27行+28行");
		 		return;
		 	}
		 	//5表内
        	var  zbv1= document.getElementById("zbv1").value;
        	var  zbv2= document.getElementById("zbv2").value;
        	var  zbv3= document.getElementById("zbv3").value;
        	var  zbv4= document.getElementById("zbv4").value;
        	var  zbv6= document.getElementById("zbv6").value;
        	var  zbv7= document.getElementById("zbv7").value;
        	var  zbv8= document.getElementById("zbv8").value;
        	var  zbv9= document.getElementById("zbv9").value;
        	var  zbv10= document.getElementById("zbv10").value;
        	var  zbv11= document.getElementById("zbv11").value;
        	var  zbv12= document.getElementById("zbv12").value;
        	var  zbv13= document.getElementById("zbv13").value;
        	var  zbv14= document.getElementById("zbv14").value;
        	var  zbv15= document.getElementById("zbv15").value;
        	var  zbv16= document.getElementById("zbv16").value;
        	var  zbv17= document.getElementById("zbv17").value;
        	var  zbv18= document.getElementById("zbv18").value;
        	var  zbv19= document.getElementById("zbv19").value;
        	var  zbv20= document.getElementById("zbv20").value;
		 	var f13 = Number(zbv2)+Number(zbv3)+Number(zbv4);
		 	if(Number(zbv1)!=Number(f13)){
		 		alert("交审计5表：01行=02行+03行+04行");
		 		return;
		 	}
		 	var f14 = Number(zbv7)+Number(zbv8);
		 	if(Number(zbv6)!=Number(f14)){
		 		alert("交审计5表：06行=07行+08行");
		 		return;
		 	}
		 	var f15 = Number(zbv9)+Number(zbv10);
		 	if(Number(zbv6)!=Number(f15)){
		 		alert("交审计5表：06行=09行+10行");
		 		return;
		 	}
		 	var f16 = Number(zbv11)+Number(zbv12)+Number(zbv13);
		 	if(Number(zbv6)!=Number(f16)){
		 		alert("交审计5表：06行=11行+12行+13行");
		 		return;
		 	}
		 	var f17 = Number(zbv14)+Number(zbv15)+Number(zbv16);
		 	if(Number(zbv6)!=Number(f17)){
		 		alert("交审计5表：06行=14行+15行+16行");
		 		return;
		 	}
		 	var f18 = Number(zbv18)+Number(zbv19)+Number(zbv20);
		 	if(Number(zbv17)!=Number(f18)){
		 		alert("交审计5表： 17行=18行+19行+20行");
		 		return;
		 	}
		 	//表外
		 	var f19 = Number(zbs1)+Number(zbt1)+Number(zbf5);
		 	if(Number(zb5)<Number(f19)){
		 		alert("交审计1表05行≥交审计2表01行+交审计3表01行+交审计4表05行");
		 		return;
		 	}
		 	if(Number(zb3)<Number(zbv6)){
		 		alert("交审计1表03行≥交审计5表06行");
		 		return;
		 	}
		 	var f20 = Number(zbs2)+Number(zbt5);
		 	if(Number(zb14)<Number(f20)){
		 		alert("交审计1表14行≥交审计2表02行+交审计3表05行");
		 		return;
		 	}
		 	var f21 = Number(zbs3)+Number(zbt10)+Number(zbf9);
		 	if(Number(zb16)<Number(f21)){
		 		alert("交审计1表16行≥交审计2表03行+交审计3表10行+交审计4表09行");
		 		return;
		 	}
		 	var f22 = Number(zbs9)+Number(zbt17)+Number(zbf13);
		 	if(Number(zb17)<Number(f22)){
		 		alert("交审计1表17行≥交审计2表09行+交审计3表17行+交审计4表13行");
		 		return;
		 	}
		 	var f23 = Number(zbs10)+Number(zbt18)+Number(zbf17);
		 	if(Number(zb18)<Number(f23)){
		 		alert("交审计1表18行≥交审计2表10行+交审计3表18行+交审计4表17行");
		 		return;
		 	}
		 	if(Number(zb19)<Number(zbs11)){
		 		alert("交审计1表19行≥交审计2表11行");
		 		return;
		 	}
		 	var f24 = Number(zbs16)+Number(zbt21)+Number(zbf33);
		 	if(Number(zb20)<Number(f24)){
		 		alert("交审计1表20行≥交审计2表16行+交审计3表21行+交审计4表33行");
		 		return;
		 	}
		 	if(Number(zb22)<Number(zbs18)){
		 		alert("交审计1表22行≥交审计2表18行");
		 		return;
		 	}
		 	var f25 = Number(zbf27)+Number(zbf28);
		 	if(Number(zb23)<Number(f25)){
		 		alert("交审计1表23行≥交审计4表27行+交审计4表28行");
		 		return;
		 	}
		 	alert("全部校验成功");
		 	return true;
		 }
		window.setInterval(function moveWithScroll()  
		{  
			var scrollDiv=document.getElementById('scrollDiv');  
			var w=document.documentElement.clientWidth;  
			var h=document.documentElement.clientHeight;  
			var ow=scrollDiv.style.width;  
			var oh=scrollDiv.style.height;  
			var scrollTop = window.pageYOffset  || document.documentElement.scrollTop || document.body.scrollTop||0;  
			var nowW=(w-parseInt(ow))/2;  
			var nowH=(scrollTop+h-parseInt(oh))-400/2;  
			scrollDiv.style.left=nowW+"px";  
			scrollDiv.style.top=nowH+"px";        
		},50); 
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
		         	SjbbAll sjbbAll = (SjbbAll)request.getAttribute("sjbbAll");
		%>
		<form id="form1" name="SjbbAll" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<div class="ex">
			<a href="javascript:void(0)" onclick="document.getElementById('view1').scrollIntoView();">表1：审计情况统计报表</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view2').scrollIntoView();">表2：财务收支审计报表</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view3').scrollIntoView();">表3：基本建设审计报表</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view4').scrollIntoView();">表4：经济责任审计报表</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view5').scrollIntoView();">表5：审计机构人员报表</a>
		</div>
		<input type="hidden" name="SjbbAll_id" id="SjbbAll_id" <%if(sjbbAll.getId()!=0){%>value="<%=sjbbAll.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;font-size: 25px">
					标题：<input name="bt" type="text" size="20" <%if(sjbbAll.getBt()!=null){%>value="<%=sjbbAll.getBt()%>"<%}else{%>value="交通审计统计报表"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left" width="50%">
						填报单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="left" width="50%">
						报告时间：<input name="sj" id="sj" type="text" size="10" <%if(sjbbAll.getSj()!=null){%>value="<%=sjbbAll.getSj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.SjbbAll.sj);" type="button" value="选择">
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						单位负责人：<input name="dwfzr" type="text" size="10" <%if(sjbbAll.getDwfzr()!=null){%>value="<%=sjbbAll.getDwfzr()%>"<%}%>/>
					</td>
					<td align="left">
						审计负责人：<input name="tjfzr" type="text" size="10" <%if(sjbbAll.getTjfzr()!=null){%>value="<%=sjbbAll.getTjfzr()%>"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						填表人：<%=name%>
					</td>
					<td align="left">
						联系方式：<input name="czrphone" type="text" size="10" <%if(sjbbAll.getCzrphone()!=null){%>value="<%=sjbbAll.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view1">审计情况统计报表</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">一、内部审计机构
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zb1" name="zb1" type="text" size="15" <%if(sjbbAll.getZb1()!=null){%>value="<%=sjbbAll.getZb1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：专职机构
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zb2" name="zb2" type="text" size="15" <%if(sjbbAll.getZb2()!=null){%>value="<%=sjbbAll.getZb2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">二、内部审计人员
					</td>
					<td align="center">人
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zb3" name="zb3" type="text" size="15" <%if(sjbbAll.getZb3()!=null){%>value="<%=sjbbAll.getZb3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：专职人员
					</td>
					<td align="center">人
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zb4" name="zb4" type="text" size="15" <%if(sjbbAll.getZb4()!=null){%>value="<%=sjbbAll.getZb4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">三、完成审计项目
					</td>
					<td align="center">个
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zb5" name="zb5" type="text" size="15" <%if(sjbbAll.getZb5()!=null){%>value="<%=sjbbAll.getZb5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：财务收支审计
					</td>
					<td align="center">个
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zb6" name="zb6" type="text" size="15" <%if(sjbbAll.getZb6()!=null){%>value="<%=sjbbAll.getZb6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基本建设审计
					</td>
					<td align="center">个
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zb7" name="zb7" type="text" size="15" <%if(sjbbAll.getZb7()!=null){%>value="<%=sjbbAll.getZb7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经济责任审计
					</td>
					<td align="center">个
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zb8" name="zb8" type="text" size="15" <%if(sjbbAll.getZb8()!=null){%>value="<%=sjbbAll.getZb8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;效益审计
					</td>
					<td align="center">个
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zb9" name="zb9" type="text" size="15" <%if(sjbbAll.getZb9()!=null){%>value="<%=sjbbAll.getZb9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内部控制评审
					</td>
					<td align="center">个
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zb10" name="zb10" type="text" size="15" <%if(sjbbAll.getZb10()!=null){%>value="<%=sjbbAll.getZb10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信息系统审计
					</td>
					<td align="center">个
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zb11" name="zb11" type="text" size="15" <%if(sjbbAll.getZb11()!=null){%>value="<%=sjbbAll.getZb11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专项审计（调查）
					</td>
					<td align="center">个
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zb12" name="zb12" type="text" size="15" <%if(sjbbAll.getZb12()!=null){%>value="<%=sjbbAll.getZb12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他
					</td>
					<td align="center">个
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zb13" name="zb13" type="text" size="15" <%if(sjbbAll.getZb13()!=null){%>value="<%=sjbbAll.getZb13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">四、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zb14" name="zb14" type="text" size="15" <%if(sjbbAll.getZb14()!=null){%>value="<%=sjbbAll.getZb14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">五、查出问题金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zb15" name="zb15" type="text" size="15" <%if(sjbbAll.getZb15()!=null){%>value="<%=sjbbAll.getZb15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zb16" name="zb16" type="text" size="15" <%if(sjbbAll.getZb16()!=null){%>value="<%=sjbbAll.getZb16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zb17" name="zb17" type="text" size="15" <%if(sjbbAll.getZb17()!=null){%>value="<%=sjbbAll.getZb17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zb18" name="zb18" type="text" size="15" <%if(sjbbAll.getZb18()!=null){%>value="<%=sjbbAll.getZb18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">六、促进增收节支金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zb19" name="zb19" type="text" size="15" <%if(sjbbAll.getZb19()!=null){%>value="<%=sjbbAll.getZb19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">七、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zb20" name="zb20" type="text" size="15" <%if(sjbbAll.getZb20()!=null){%>value="<%=sjbbAll.getZb20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">八、提出建议意见被采纳
					</td>
					<td align="center">条
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zb21" name="zb21" type="text" size="15" <%if(sjbbAll.getZb21()!=null){%>value="<%=sjbbAll.getZb21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">九、移送司法、纪检监察机关处理线索（案件）
					</td>
					<td align="center">件
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zb22" name="zb22" type="text" size="15" <%if(sjbbAll.getZb22()!=null){%>value="<%=sjbbAll.getZb22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十、移送司法、纪检监察机关处理人员
					</td>
					<td align="center">人
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zb23" name="zb23" type="text" size="15" <%if(sjbbAll.getZb23()!=null){%>value="<%=sjbbAll.getZb23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十一、实际给予行政处分
					</td>
					<td align="center">人
					</td>
					<td align="center">24
					</td>
					<td align="center">
					<input id="zb24" name="zb24" type="text" size="15" <%if(sjbbAll.getZb24()!=null){%>value="<%=sjbbAll.getZb24()%>"<%}%>/>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="left">
					备注：
					<br>		
					1.本表系审计情况综合报表， 有关指标应大于或等于其它各报表的相同指标之和。			
					<br>		
					2.表内逻辑关系：01行≥02行；03行≥04行；05行=06行+07行+08行+09行+10行+11行+12行+13行；15行≥16行+17行+18行。		
					<br>			
					3.表间逻辑关系：交审计1表05行≥交审计2表01行+交审计3表01行+交审计4表05行；				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表03行≥交审计5表06行；					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表14行≥交审计2表02行+交审计3表05行；					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表16行≥交审计2表03行+交审计3表10行+交审计4表09行；				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表17行≥交审计2表09行+交审计3表17行+交审计4表13行；				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表18行≥交审计2表10行+交审计3表18行+交审计4表17行；				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表19行≥交审计2表11行；					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表20行≥交审计2表16行+交审计3表21行+交审计4表33行；			
					<br>		
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表22行≥交审计2表18行；					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表23行≥交审计4表27行+交审计4表28行。	
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view2">财务收支审计报表</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">一、审计单位
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbs1" name="zbs1" type="text" size="15" <%if(sjbbAll.getZbs1()!=null){%>value="<%=sjbbAll.getZbs1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">二、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbs2" name="zbs2" type="text" size="15" <%if(sjbbAll.getZbs2()!=null){%>value="<%=sjbbAll.getZbs2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">三、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbs3" name="zbs3" type="text" size="15" <%if(sjbbAll.getZbs3()!=null){%>value="<%=sjbbAll.getZbs3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：违规改变资金用途 
					</td>
					<td align="center">万元
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbs4" name="zbs4" type="text" size="15" <%if(sjbbAll.getZbs4()!=null){%>value="<%=sjbbAll.getZbs4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;虚报冒领骗取资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbs5" name="zbs5" type="text" size="15" <%if(sjbbAll.getZbs5()!=null){%>value="<%=sjbbAll.getZbs5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账外资产
					</td>
					<td align="center">万元
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbs6" name="zbs6" type="text" size="15" <%if(sjbbAll.getZbs6()!=null){%>value="<%=sjbbAll.getZbs6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未按规定征收、缴纳财政收入
					</td>
					<td align="center">万元
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbs7" name="zbs7" type="text" size="15" <%if(sjbbAll.getZbs7()!=null){%>value="<%=sjbbAll.getZbs7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;损益（收支）不实
					</td>
					<td align="center">万元
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbs8" name="zbs8" type="text" size="15" <%if(sjbbAll.getZbs8()!=null){%>value="<%=sjbbAll.getZbs8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">四、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbs9" name="zbs9" type="text" size="15" <%if(sjbbAll.getZbs9()!=null){%>value="<%=sjbbAll.getZbs9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">五、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbs10" name="zbs10" type="text" size="15" <%if(sjbbAll.getZbs10()!=null){%>value="<%=sjbbAll.getZbs10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">六、促进增收节支金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbs11" name="zbs11" type="text" size="15" <%if(sjbbAll.getZbs11()!=null){%>value="<%=sjbbAll.getZbs11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">七、下达审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbs12" name="zbs12" type="text" size="15" <%if(sjbbAll.getZbs12()!=null){%>value="<%=sjbbAll.getZbs12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">八、落实审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbs13" name="zbs13" type="text" size="15" <%if(sjbbAll.getZbs13()!=null){%>value="<%=sjbbAll.getZbs13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">九、提出审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbs14" name="zbs14" type="text" size="15" <%if(sjbbAll.getZbs14()!=null){%>value="<%=sjbbAll.getZbs14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十、采纳审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbs15" name="zbs15" type="text" size="15" <%if(sjbbAll.getZbs15()!=null){%>value="<%=sjbbAll.getZbs15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十一、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbs16" name="zbs16" type="text" size="15" <%if(sjbbAll.getZbs16()!=null){%>value="<%=sjbbAll.getZbs16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十二、百万元以上违纪单位
					</td>
					<td align="center">个
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbs17" name="zbs17" type="text" size="15" <%if(sjbbAll.getZbs17()!=null){%>value="<%=sjbbAll.getZbs17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十三、万元以上贪污贿赂案件
					</td>
					<td align="center">件
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbs18" name="zbs18" type="text" size="15" <%if(sjbbAll.getZbs18()!=null){%>value="<%=sjbbAll.getZbs18()%>"<%}%>/>
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="left" colspan="3">
					备注：
					<br>		
					1.本表系财务收支审计专项报表，对专项资金（专项经费）的审计数据也填列该表。			
					<br>		
					2.表内逻辑关系：03行≥04行+05行+06行+07行+08行。
					<br>			
					3.表间逻辑关系：交审计1表05行≥交审计2表01行；		
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表14行≥交审计2表02行；		
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表16行≥交审计2表03行；				
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表17行≥交审计2表09行；		
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表18行≥交审计2表10行；
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表19行≥交审计2表11行；			
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表20行≥交审计2表16行；			
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表22行≥交审计2表18行。	
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view3">基本建设审计报表</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">一、审计项目
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbt1" name="zbt1" type="text" size="15" <%if(sjbbAll.getZbt1()!=null){%>value="<%=sjbbAll.getZbt1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：竣工决算审计
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbt2" name="zbt2" type="text" size="15" <%if(sjbbAll.getZbt2()!=null){%>value="<%=sjbbAll.getZbt2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪审计
					</td>
					<td align="center">个
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbt3" name="zbt3" type="text" size="15" <%if(sjbbAll.getZbt3()!=null){%>value="<%=sjbbAll.getZbt3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他专项审计
					</td>
					<td align="center">个
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbt4" name="zbt4" type="text" size="15" <%if(sjbbAll.getZbt4()!=null){%>value="<%=sjbbAll.getZbt4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">二、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbt5" name="zbt5" type="text" size="15" <%if(sjbbAll.getZbt5()!=null){%>value="<%=sjbbAll.getZbt5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：竣工决算审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbt6" name="zbt6" type="text" size="15" <%if(sjbbAll.getZbt6()!=null){%>value="<%=sjbbAll.getZbt6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbt7" name="zbt7" type="text" size="15" <%if(sjbbAll.getZbt7()!=null){%>value="<%=sjbbAll.getZbt7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他专项审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbt8" name="zbt8" type="text" size="15" <%if(sjbbAll.getZbt8()!=null){%>value="<%=sjbbAll.getZbt8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">三、核减投资（结算）额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbt9" name="zbt9" type="text" size="15" <%if(sjbbAll.getZbt9()!=null){%>value="<%=sjbbAll.getZbt9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">四、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbt10" name="zbt10" type="text" size="15" <%if(sjbbAll.getZbt10()!=null){%>value="<%=sjbbAll.getZbt10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：超规模、超标准建设
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbt11" name="zbt11" type="text" size="15" <%if(sjbbAll.getZbt11()!=null){%>value="<%=sjbbAll.getZbt11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;挤占挪用建设资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbt12" name="zbt12" type="text" size="15" <%if(sjbbAll.getZbt12()!=null){%>value="<%=sjbbAll.getZbt12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建设资金不落实
					</td>
					<td align="center">万元
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbt13" name="zbt13" type="text" size="15" <%if(sjbbAll.getZbt13()!=null){%>value="<%=sjbbAll.getZbt13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;多列(少列）工程成本
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbt14" name="zbt14" type="text" size="15" <%if(sjbbAll.getZbt14()!=null){%>value="<%=sjbbAll.getZbt14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;漏交税费
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbt15" name="zbt15" type="text" size="15" <%if(sjbbAll.getZbt15()!=null){%>value="<%=sjbbAll.getZbt15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;侵害群众利益问题
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbt16" name="zbt16" type="text" size="15" <%if(sjbbAll.getZbt16()!=null){%>value="<%=sjbbAll.getZbt16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">五、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbt17" name="zbt17" type="text" size="15" <%if(sjbbAll.getZbt17()!=null){%>value="<%=sjbbAll.getZbt17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">六、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbt18" name="zbt18" type="text" size="15" <%if(sjbbAll.getZbt18()!=null){%>value="<%=sjbbAll.getZbt18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">七、应归还原渠道资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbt19" name="zbt19" type="text" size="15" <%if(sjbbAll.getZbt19()!=null){%>value="<%=sjbbAll.getZbt19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：已归还资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zbt20" name="zbt20" type="text" size="15" <%if(sjbbAll.getZbt20()!=null){%>value="<%=sjbbAll.getZbt20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">八、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zbt21" name="zbt21" type="text" size="15" <%if(sjbbAll.getZbt21()!=null){%>value="<%=sjbbAll.getZbt21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">九、下达审计决定条数
					</td>
					<td align="center">条
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zbt22" name="zbt22" type="text" size="15" <%if(sjbbAll.getZbt22()!=null){%>value="<%=sjbbAll.getZbt22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十、落实审计决定条数
					</td>
					<td align="center">条
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zbt23" name="zbt23" type="text" size="15" <%if(sjbbAll.getZbt23()!=null){%>value="<%=sjbbAll.getZbt23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十一、提出审计建议条数
					</td>
					<td align="center">条
					</td>
					<td align="center">24
					</td>
					<td align="center">
					<input id="zbt24" name="zbt24" type="text" size="15" <%if(sjbbAll.getZbt24()!=null){%>value="<%=sjbbAll.getZbt24()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十二、采纳审计建议条数
					</td>
					<td align="center">条
					</td>
					<td align="center">25
					</td>
					<td align="center">
					<input id="zbt25" name="zbt25" type="text" size="15" <%if(sjbbAll.getZbt25()!=null){%>value="<%=sjbbAll.getZbt25()%>"<%}%>/>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="left" colspan="3">
					备注：
					<br>		
					1.本表系基本建设审计专项报表，有关指标均指基本建设审计中的数据。					
					<br>
					2.表内逻辑关系：01行=02行+03行+04行；05行=06行+07行+08行；	
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;10行≥11行+12行+13行+14行+15行+16行；19行≥20行。
					<br>
					3.表间逻辑关系：交审计1表05行≥交审计3表01行；交审计1表14行≥交审计3表05行；
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表16行≥交审计3表10行；交审计1表17行≥交审计3表17行；
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表18行≥交审计3表18行；交审计1表20行≥交审计3表21行。
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view4">经济责任审计报表</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">一、 审计经济责任人
					</td>
					<td align="center">人
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbf1" name="zbf1" type="text" size="15" <%if(sjbbAll.getZbf1()!=null){%>value="<%=sjbbAll.getZbf1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：行政单位
					</td>
					<td align="center">人
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbf2" name="zbf2" type="text" size="15" <%if(sjbbAll.getZbf2()!=null){%>value="<%=sjbbAll.getZbf2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位
					</td>
					<td align="center">人
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbf3" name="zbf3" type="text" size="15" <%if(sjbbAll.getZbf3()!=null){%>value="<%=sjbbAll.getZbf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业
					</td>
					<td align="center">人
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbf4" name="zbf4" type="text" size="15" <%if(sjbbAll.getZbf4()!=null){%>value="<%=sjbbAll.getZbf4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">二、审计单位
					</td>
					<td align="center">个
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbf5" name="zbf5" type="text" size="15" <%if(sjbbAll.getZbf5()!=null){%>value="<%=sjbbAll.getZbf5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：行政单位
					</td>
					<td align="center">个
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbf6" name="zbf6" type="text" size="15" <%if(sjbbAll.getZbf6()!=null){%>value="<%=sjbbAll.getZbf6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位
					</td>
					<td align="center">个
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbf7" name="zbf7" type="text" size="15" <%if(sjbbAll.getZbf7()!=null){%>value="<%=sjbbAll.getZbf7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业
					</td>
					<td align="center">个
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbf8" name="zbf8" type="text" size="15" <%if(sjbbAll.getZbf8()!=null){%>value="<%=sjbbAll.getZbf8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">三、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbf9" name="zbf9" type="text" size="15" <%if(sjbbAll.getZbf9()!=null){%>value="<%=sjbbAll.getZbf9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbf10" name="zbf10" type="text" size="15" <%if(sjbbAll.getZbf10()!=null){%>value="<%=sjbbAll.getZbf10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbf11" name="zbf11" type="text" size="15" <%if(sjbbAll.getZbf11()!=null){%>value="<%=sjbbAll.getZbf11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbf12" name="zbf12" type="text" size="15" <%if(sjbbAll.getZbf12()!=null){%>value="<%=sjbbAll.getZbf12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">四、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbf13" name="zbf13" type="text" size="15" <%if(sjbbAll.getZbf13()!=null){%>value="<%=sjbbAll.getZbf13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbf14" name="zbf14" type="text" size="15" <%if(sjbbAll.getZbf14()!=null){%>value="<%=sjbbAll.getZbf14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbf15" name="zbf15" type="text" size="15" <%if(sjbbAll.getZbf15()!=null){%>value="<%=sjbbAll.getZbf15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbf16" name="zbf16" type="text" size="15" <%if(sjbbAll.getZbf16()!=null){%>value="<%=sjbbAll.getZbf16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">五、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbf17" name="zbf17" type="text" size="15" <%if(sjbbAll.getZbf17()!=null){%>value="<%=sjbbAll.getZbf17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbf18" name="zbf18" type="text" size="15" <%if(sjbbAll.getZbf18()!=null){%>value="<%=sjbbAll.getZbf18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbf19" name="zbf19" type="text" size="15" <%if(sjbbAll.getZbf19()!=null){%>value="<%=sjbbAll.getZbf19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zbf20" name="zbf20" type="text" size="15" <%if(sjbbAll.getZbf20()!=null){%>value="<%=sjbbAll.getZbf20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">六、领导干部涉及个人经济问题
					</td>
					<td align="center">人
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zbf21" name="zbf21" type="text" size="15" <%if(sjbbAll.getZbf21()!=null){%>value="<%=sjbbAll.getZbf21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：人数
					</td>
					<td align="center">人
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zbf22" name="zbf22" type="text" size="15" <%if(sjbbAll.getZbf22()!=null){%>value="<%=sjbbAll.getZbf22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;涉及金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zbf23" name="zbf23" type="text" size="15" <%if(sjbbAll.getZbf23()!=null){%>value="<%=sjbbAll.getZbf23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">七、人员处理处分
					</td>
					<td align="center">人
					</td>
					<td align="center">24
					</td>
					<td align="center">
					<input id="zbf24" name="zbf24" type="text" size="15" <%if(sjbbAll.getZbf24()!=null){%>value="<%=sjbbAll.getZbf24()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：撤职、降级
					</td>
					<td align="center">人
					</td>
					<td align="center">25
					</td>
					<td align="center">
					<input id="zbf25" name="zbf25" type="text" size="15" <%if(sjbbAll.getZbf25()!=null){%>value="<%=sjbbAll.getZbf25()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他处分
					</td>
					<td align="center">人
					</td>
					<td align="center">26
					</td>
					<td align="center">
					<input id="zbf26" name="zbf26" type="text" size="15" <%if(sjbbAll.getZbf26()!=null){%>value="<%=sjbbAll.getZbf26()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移送司法机关
					</td>
					<td align="center">人
					</td>
					<td align="center">27
					</td>
					<td align="center">
					<input id="zbf27" name="zbf27" type="text" size="15" <%if(sjbbAll.getZbf27()!=null){%>value="<%=sjbbAll.getZbf27()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移送纪检监察机关
					</td>
					<td align="center">人
					</td>
					<td align="center">28
					</td>
					<td align="center">
					<input id="zbf28" name="zbf28" type="text" size="15" <%if(sjbbAll.getZbf28()!=null){%>value="<%=sjbbAll.getZbf28()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">八、下达审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">29
					</td>
					<td align="center">
					<input id="zbf29" name="zbf29" type="text" size="15" <%if(sjbbAll.getZbf29()!=null){%>value="<%=sjbbAll.getZbf29()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">九、落实审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">30
					</td>
					<td align="center">
					<input id="zbf30" name="zbf30" type="text" size="15" <%if(sjbbAll.getZbf30()!=null){%>value="<%=sjbbAll.getZbf30()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十、提出审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">31
					</td>
					<td align="center">
					<input id="zbf31" name="zbf31" type="text" size="15" <%if(sjbbAll.getZbf31()!=null){%>value="<%=sjbbAll.getZbf31()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十一、采纳审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">32
					</td>
					<td align="center">
					<input id="zbf32" name="zbf32" type="text" size="15" <%if(sjbbAll.getZbf32()!=null){%>value="<%=sjbbAll.getZbf32()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">十二、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">33
					</td>
					<td align="center">
					<input id="zbf33" name="zbf33" type="text" size="15" <%if(sjbbAll.getZbf33()!=null){%>value="<%=sjbbAll.getZbf33()%>"<%}%>/>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="left" colspan="3">
					备注：
					<br>		
					1.本表系经济责任审计专项报表，有关指标均指经济责任审计中的数据。
					<br>		
					2.表内逻辑关系：01行=02行+03行+04行；05行=06行+07行+08行；09行≥10行+11行+12行；
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;13行≥14行+15行+16行；17行≥18行+19行+20行；21行≥22行；	
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;24行≥25行+26行+27行+28行。
					<br>
					3.表间逻辑关系：交审计1表05行≥交审计4表05行；交审计1表16行≥交审计4表09行
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表17行≥交审计4表13行；交审计1表18行≥交审计4表17行
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表20行≥交审计4表33行；
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;交审计1表23行≥交审计4表27行+交审计4表28行。
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view5">审计机构人员报表</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%" colspan="2">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">一、已建机构
					</td>
					<td align="left">合&nbsp;&nbsp;计
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbv1" name="zbv1" type="text" size="15" <%if(sjbbAll.getZbv1()!=null){%>value="<%=sjbbAll.getZbv1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">处&nbsp;&nbsp;级
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbv2" name="zbv2" type="text" size="15" <%if(sjbbAll.getZbv2()!=null){%>value="<%=sjbbAll.getZbv2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">科&nbsp;&nbsp;级
					</td>
					<td align="center">个
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbv3" name="zbv3" type="text" size="15" <%if(sjbbAll.getZbv3()!=null){%>value="<%=sjbbAll.getZbv3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">科级以下
					</td>
					<td align="center">个
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbv4" name="zbv4" type="text" size="15" <%if(sjbbAll.getZbv4()!=null){%>value="<%=sjbbAll.getZbv4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">二、人员编制
					</td>
					<td align="center">人
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbv5" name="zbv5" type="text" size="15" <%if(sjbbAll.getZbv5()!=null){%>value="<%=sjbbAll.getZbv5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">三、配备人员
					</td>
					<td align="center">人
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbv6" name="zbv6" type="text" size="15" <%if(sjbbAll.getZbv6()!=null){%>value="<%=sjbbAll.getZbv6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">（一）按工作性质分
					</td>
					<td align="left">专&nbsp;&nbsp;职
					</td>
					<td align="center">人
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbv7" name="zbv7" type="text" size="15" <%if(sjbbAll.getZbv7()!=null){%>value="<%=sjbbAll.getZbv7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">兼&nbsp;&nbsp;职
					</td>
					<td align="center">人
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbv8" name="zbv8" type="text" size="15" <%if(sjbbAll.getZbv8()!=null){%>value="<%=sjbbAll.getZbv8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">（二）按学历分
					</td>
					<td align="left">大专及以上
					</td>
					<td align="center">人
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbv9" name="zbv9" type="text" size="15" <%if(sjbbAll.getZbv9()!=null){%>value="<%=sjbbAll.getZbv9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">中专及以下
					</td>
					<td align="center">人
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbv10" name="zbv10" type="text" size="15" <%if(sjbbAll.getZbv10()!=null){%>value="<%=sjbbAll.getZbv10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">（三）按职称分
					</td>
					<td align="left">高&nbsp;&nbsp;级
					</td>
					<td align="center">人
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbv11" name="zbv11" type="text" size="15" <%if(sjbbAll.getZbv11()!=null){%>value="<%=sjbbAll.getZbv11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">中&nbsp;&nbsp;级
					</td>
					<td align="center">人
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbv12" name="zbv12" type="text" size="15" <%if(sjbbAll.getZbv12()!=null){%>value="<%=sjbbAll.getZbv12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">初级以下
					</td>
					<td align="center">人
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbv13" name="zbv13" type="text" size="15" <%if(sjbbAll.getZbv13()!=null){%>value="<%=sjbbAll.getZbv13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">（四）按年龄分
					</td>
					<td align="left">45岁以上
					</td>
					<td align="center">人
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbv14" name="zbv14" type="text" size="15" <%if(sjbbAll.getZbv14()!=null){%>value="<%=sjbbAll.getZbv14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">30至44岁
					</td>
					<td align="center">人
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbv15" name="zbv15" type="text" size="15" <%if(sjbbAll.getZbv15()!=null){%>value="<%=sjbbAll.getZbv15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">29岁以下
					</td>
					<td align="center">人
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbv16" name="zbv16" type="text" size="15" <%if(sjbbAll.getZbv16()!=null){%>value="<%=sjbbAll.getZbv16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">四、培训人员
					</td>
					<td align="left">合&nbsp;&nbsp;计
					</td>
					<td align="center">人次
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbv17" name="zbv17" type="text" size="15" <%if(sjbbAll.getZbv17()!=null){%>value="<%=sjbbAll.getZbv17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">自办培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbv18" name="zbv18" type="text" size="15" <%if(sjbbAll.getZbv18()!=null){%>value="<%=sjbbAll.getZbv18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">参加部办培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbv19" name="zbv19" type="text" size="15" <%if(sjbbAll.getZbv19()!=null){%>value="<%=sjbbAll.getZbv19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">参加外系统、单位培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zbv20" name="zbv20" type="text" size="15" <%if(sjbbAll.getZbv20()!=null){%>value="<%=sjbbAll.getZbv20()%>"<%}%>/>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="center">交审表逻辑检查：
					<img align="middle" alt="计算" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
					</td>
				</tr>
				<tr>
					<td align="left" colspan="3">
					备注：
					<br>		
					1.本表系审计机构人员情况专项报表。本表各项指标的统计范围包括各级交通主管部门和交通企事业单位。
					<br>		
					2.表内逻辑关系：01行=02行+03行+04行；
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;06行=07行+08行=09行+10行=11行+12行+13行=14行+15行+16行；
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;17行=18行+19行+20行。
					<br>			
					3.表间逻辑关系：交审计1表03行≥交审计5表06行
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



