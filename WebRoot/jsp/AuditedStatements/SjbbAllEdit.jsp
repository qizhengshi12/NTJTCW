<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.SjbbAll"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*�����¼��ʱ*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('��½��ʱ��');top.location.href='login.jsp';</script>");
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
    <title>�������ͳ�Ʊ�</title>
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
	   			alert("�����뱨��ʱ��");
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
        //����EXCEL
        function infExcel(){
	        var file = document.getElementById("file1").value;
	        if (file==""){
	        	alert("��ѡ���ϴ��ļ�");
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
					alert("������С����Ϊ0M��");   
					return;
				}
				infoFile();
	        }else{ //����ϴ��ļ�����
	            alert("ֻ���ϴ�xls��ʽ���ļ�;\n������ѡ�����ϴ�.");
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
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
	       	}
		}
		
		//������֤
		 function verification(){
		 	//1����
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
		 		alert("�����1��01�С�02��");
		 		return;
		 	}
		 	if(Number(zb3)<Number(zb4)){
		 		alert("�����1��03�С�04��");
		 		return;
		 	}
		 	var f1 = Number(zb6)+Number(zb7)+Number(zb8)+Number(zb9)+Number(zb10)+Number(zb11)+Number(zb12)+Number(zb13);
		 	if(Number(zb5)!=Number(f1)){
		 		alert("�����1��05��=06��+07��+08��+09��+10��+11��+12��+13��");
		 		return;
		 	}
		 	var f2 = Number(zb16)+Number(zb17)+Number(zb18);
		 	if(Number(zb15)<Number(f2)){
		 		alert("�����1��15�С�16��+17��+18��");
		 		return;
		 	}
		 	//2����
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
		 		alert("�����2��03�С�04��+05��+06��+07��+08��");
		 		return;
		 	}
		 	//3����
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
		 		alert("�����3��01��=02��+03��+04��");
		 		return;
		 	}
		 	var f5 = Number(zbt6)+Number(zbt7)+Number(zbt8);
		 	if(Number(zbt5)!=Number(f5)){
		 		alert("�����3��05��=06��+07��+08��");
		 		return;
		 	}
		 	var f6 = Number(zbt11)+Number(zbt12)+Number(zbt13)+Number(zbt14)+Number(zbt15)+Number(zbt16);
		 	if(Number(zbt10)<Number(f6)){
		 		alert("�����3��10�С�11��+12��+13��+14��+15��+16��");
		 		return;
		 	}
		 	if(Number(zbt19)<Number(zbt20)){
		 		alert("�����3��19�С�20��");
		 		return;
		 	}
		 	//4����
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
		 		alert("�����4��01��=02��+03��+04��");
		 		return;
		 	}
		 	var f8 = Number(zbf6)+Number(zbf7)+Number(zbf8);
		 	if(Number(zbf5)!=Number(f8)){
		 		alert("�����4��05��=06��+07��+08��");
		 		return;
		 	}
		 	var f9 = Number(zbf10)+Number(zbf11)+Number(zbf12);
		 	if(Number(zbf9)<Number(f9)){
		 		alert("�����4��09�С�10��+11��+12��");
		 		return;
		 	}
		 	var f10 = Number(zbf14)+Number(zbf15)+Number(zbf16);
		 	if(Number(zbf13)<Number(f10)){
		 		alert("�����4��13�С�14��+15��+16��");
		 		return;
		 	}
		 	var f11 = Number(zbf18)+Number(zbf19)+Number(zbf20);
		 	if(Number(zbf17)<Number(f11)){
		 		alert("�����4��17�С�18��+19��+20��");
		 		return;
		 	}
		 	if(Number(zbf21)<Number(zbf22)){
		 		alert("�����4��21�С�22��");
		 		return;
		 	}
		 	var f12 = Number(zbf25)+Number(zbf26)+Number(zbf27)+Number(zbf28);
		 	if(Number(zbf24)<Number(f12)){
		 		alert("�����4��24�С�25��+26��+27��+28��");
		 		return;
		 	}
		 	//5����
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
		 		alert("�����5��01��=02��+03��+04��");
		 		return;
		 	}
		 	var f14 = Number(zbv7)+Number(zbv8);
		 	if(Number(zbv6)!=Number(f14)){
		 		alert("�����5��06��=07��+08��");
		 		return;
		 	}
		 	var f15 = Number(zbv9)+Number(zbv10);
		 	if(Number(zbv6)!=Number(f15)){
		 		alert("�����5��06��=09��+10��");
		 		return;
		 	}
		 	var f16 = Number(zbv11)+Number(zbv12)+Number(zbv13);
		 	if(Number(zbv6)!=Number(f16)){
		 		alert("�����5��06��=11��+12��+13��");
		 		return;
		 	}
		 	var f17 = Number(zbv14)+Number(zbv15)+Number(zbv16);
		 	if(Number(zbv6)!=Number(f17)){
		 		alert("�����5��06��=14��+15��+16��");
		 		return;
		 	}
		 	var f18 = Number(zbv18)+Number(zbv19)+Number(zbv20);
		 	if(Number(zbv17)!=Number(f18)){
		 		alert("�����5�� 17��=18��+19��+20��");
		 		return;
		 	}
		 	//����
		 	var f19 = Number(zbs1)+Number(zbt1)+Number(zbf5);
		 	if(Number(zb5)<Number(f19)){
		 		alert("�����1��05�Сݽ����2��01��+�����3��01��+�����4��05��");
		 		return;
		 	}
		 	if(Number(zb3)<Number(zbv6)){
		 		alert("�����1��03�Сݽ����5��06��");
		 		return;
		 	}
		 	var f20 = Number(zbs2)+Number(zbt5);
		 	if(Number(zb14)<Number(f20)){
		 		alert("�����1��14�Сݽ����2��02��+�����3��05��");
		 		return;
		 	}
		 	var f21 = Number(zbs3)+Number(zbt10)+Number(zbf9);
		 	if(Number(zb16)<Number(f21)){
		 		alert("�����1��16�Сݽ����2��03��+�����3��10��+�����4��09��");
		 		return;
		 	}
		 	var f22 = Number(zbs9)+Number(zbt17)+Number(zbf13);
		 	if(Number(zb17)<Number(f22)){
		 		alert("�����1��17�Сݽ����2��09��+�����3��17��+�����4��13��");
		 		return;
		 	}
		 	var f23 = Number(zbs10)+Number(zbt18)+Number(zbf17);
		 	if(Number(zb18)<Number(f23)){
		 		alert("�����1��18�Сݽ����2��10��+�����3��18��+�����4��17��");
		 		return;
		 	}
		 	if(Number(zb19)<Number(zbs11)){
		 		alert("�����1��19�Сݽ����2��11��");
		 		return;
		 	}
		 	var f24 = Number(zbs16)+Number(zbt21)+Number(zbf33);
		 	if(Number(zb20)<Number(f24)){
		 		alert("�����1��20�Сݽ����2��16��+�����3��21��+�����4��33��");
		 		return;
		 	}
		 	if(Number(zb22)<Number(zbs18)){
		 		alert("�����1��22�Сݽ����2��18��");
		 		return;
		 	}
		 	var f25 = Number(zbf27)+Number(zbf28);
		 	if(Number(zb23)<Number(f25)){
		 		alert("�����1��23�Сݽ����4��27��+�����4��28��");
		 		return;
		 	}
		 	alert("ȫ��У��ɹ�");
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
			//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
		         	//String permissions = (String)request.getAttribute("permissions"); 
		         	SjbbAll sjbbAll = (SjbbAll)request.getAttribute("sjbbAll");
		%>
		<form id="form1" name="SjbbAll" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<div class="ex">
			<a href="javascript:void(0)" onclick="document.getElementById('view1').scrollIntoView();">��1��������ͳ�Ʊ���</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view2').scrollIntoView();">��2��������֧��Ʊ���</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view3').scrollIntoView();">��3������������Ʊ���</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view4').scrollIntoView();">��4������������Ʊ���</a>
			<br>
			<a href="javascript:void(0)" onclick="document.getElementById('view5').scrollIntoView();">��5����ƻ�����Ա����</a>
		</div>
		<input type="hidden" name="SjbbAll_id" id="SjbbAll_id" <%if(sjbbAll.getId()!=0){%>value="<%=sjbbAll.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;font-size: 25px">
					���⣺<input name="bt" type="text" size="20" <%if(sjbbAll.getBt()!=null){%>value="<%=sjbbAll.getBt()%>"<%}else{%>value="��ͨ���ͳ�Ʊ���"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left" width="50%">
						���λ��<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="left" width="50%">
						����ʱ�䣺<input name="sj" id="sj" type="text" size="10" <%if(sjbbAll.getSj()!=null){%>value="<%=sjbbAll.getSj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.SjbbAll.sj);" type="button" value="ѡ��">
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						��λ�����ˣ�<input name="dwfzr" type="text" size="10" <%if(sjbbAll.getDwfzr()!=null){%>value="<%=sjbbAll.getDwfzr()%>"<%}%>/>
					</td>
					<td align="left">
						��Ƹ����ˣ�<input name="tjfzr" type="text" size="10" <%if(sjbbAll.getTjfzr()!=null){%>value="<%=sjbbAll.getTjfzr()%>"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						����ˣ�<%=name%>
					</td>
					<td align="left">
						��ϵ��ʽ��<input name="czrphone" type="text" size="10" <%if(sjbbAll.getCzrphone()!=null){%>value="<%=sjbbAll.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
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
					<a id="view1">������ͳ�Ʊ���</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">һ���ڲ���ƻ���
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zb1" name="zb1" type="text" size="15" <%if(sjbbAll.getZb1()!=null){%>value="<%=sjbbAll.getZb1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�רְ����
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zb2" name="zb2" type="text" size="15" <%if(sjbbAll.getZb2()!=null){%>value="<%=sjbbAll.getZb2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ڲ������Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zb3" name="zb3" type="text" size="15" <%if(sjbbAll.getZb3()!=null){%>value="<%=sjbbAll.getZb3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�רְ��Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zb4" name="zb4" type="text" size="15" <%if(sjbbAll.getZb4()!=null){%>value="<%=sjbbAll.getZb4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">������������Ŀ
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zb5" name="zb5" type="text" size="15" <%if(sjbbAll.getZb5()!=null){%>value="<%=sjbbAll.getZb5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�������֧���
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zb6" name="zb6" type="text" size="15" <%if(sjbbAll.getZb6()!=null){%>value="<%=sjbbAll.getZb6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����������
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zb7" name="zb7" type="text" size="15" <%if(sjbbAll.getZb7()!=null){%>value="<%=sjbbAll.getZb7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����������
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zb8" name="zb8" type="text" size="15" <%if(sjbbAll.getZb8()!=null){%>value="<%=sjbbAll.getZb8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ч�����
					</td>
					<td align="center">��
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zb9" name="zb9" type="text" size="15" <%if(sjbbAll.getZb9()!=null){%>value="<%=sjbbAll.getZb9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ڲ���������
					</td>
					<td align="center">��
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zb10" name="zb10" type="text" size="15" <%if(sjbbAll.getZb10()!=null){%>value="<%=sjbbAll.getZb10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ϣϵͳ���
					</td>
					<td align="center">��
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zb11" name="zb11" type="text" size="15" <%if(sjbbAll.getZb11()!=null){%>value="<%=sjbbAll.getZb11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר����ƣ����飩
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zb12" name="zb12" type="text" size="15" <%if(sjbbAll.getZb12()!=null){%>value="<%=sjbbAll.getZb12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zb13" name="zb13" type="text" size="15" <%if(sjbbAll.getZb13()!=null){%>value="<%=sjbbAll.getZb13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ġ�����ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zb14" name="zb14" type="text" size="15" <%if(sjbbAll.getZb14()!=null){%>value="<%=sjbbAll.getZb14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�塢���������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zb15" name="zb15" type="text" size="15" <%if(sjbbAll.getZb15()!=null){%>value="<%=sjbbAll.getZb15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zb16" name="zb16" type="text" size="15" <%if(sjbbAll.getZb16()!=null){%>value="<%=sjbbAll.getZb16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zb17" name="zb17" type="text" size="15" <%if(sjbbAll.getZb17()!=null){%>value="<%=sjbbAll.getZb17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zb18" name="zb18" type="text" size="15" <%if(sjbbAll.getZb18()!=null){%>value="<%=sjbbAll.getZb18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ٽ����ս�֧���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zb19" name="zb19" type="text" size="15" <%if(sjbbAll.getZb19()!=null){%>value="<%=sjbbAll.getZb19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ߡ��ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zb20" name="zb20" type="text" size="15" <%if(sjbbAll.getZb20()!=null){%>value="<%=sjbbAll.getZb20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ˡ�����������������
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zb21" name="zb21" type="text" size="15" <%if(sjbbAll.getZb21()!=null){%>value="<%=sjbbAll.getZb21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�š�����˾�����ͼ�����ش���������������
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zb22" name="zb22" type="text" size="15" <%if(sjbbAll.getZb22()!=null){%>value="<%=sjbbAll.getZb22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ������˾�����ͼ�����ش�����Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zb23" name="zb23" type="text" size="15" <%if(sjbbAll.getZb23()!=null){%>value="<%=sjbbAll.getZb23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮһ��ʵ�ʸ�����������
					</td>
					<td align="center">��
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
					��ע��
					<br>		
					1.����ϵ�������ۺϱ��� �й�ָ��Ӧ���ڻ�����������������ָͬ��֮�͡�			
					<br>		
					2.�����߼���ϵ��01�С�02�У�03�С�04�У�05��=06��+07��+08��+09��+10��+11��+12��+13�У�15�С�16��+17��+18�С�		
					<br>			
					3.����߼���ϵ�������1��05�Сݽ����2��01��+�����3��01��+�����4��05�У�				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��03�Сݽ����5��06�У�					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��14�Сݽ����2��02��+�����3��05�У�					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��16�Сݽ����2��03��+�����3��10��+�����4��09�У�				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��17�Сݽ����2��09��+�����3��17��+�����4��13�У�				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��18�Сݽ����2��10��+�����3��18��+�����4��17�У�				
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��19�Сݽ����2��11�У�					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��20�Сݽ����2��16��+�����3��21��+�����4��33�У�			
					<br>		
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��22�Сݽ����2��18�У�					
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��23�Сݽ����4��27��+�����4��28�С�	
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view2">������֧��Ʊ���</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">һ����Ƶ�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbs1" name="zbs1" type="text" size="15" <%if(sjbbAll.getZbs1()!=null){%>value="<%=sjbbAll.getZbs1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��������ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbs2" name="zbs2" type="text" size="15" <%if(sjbbAll.getZbs2()!=null){%>value="<%=sjbbAll.getZbs2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�������Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbs3" name="zbs3" type="text" size="15" <%if(sjbbAll.getZbs3()!=null){%>value="<%=sjbbAll.getZbs3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�Υ��ı��ʽ���; 
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbs4" name="zbs4" type="text" size="15" <%if(sjbbAll.getZbs4()!=null){%>value="<%=sjbbAll.getZbs4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�鱨ð��ƭȡ�ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbs5" name="zbs5" type="text" size="15" <%if(sjbbAll.getZbs5()!=null){%>value="<%=sjbbAll.getZbs5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ʲ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbs6" name="zbs6" type="text" size="15" <%if(sjbbAll.getZbs6()!=null){%>value="<%=sjbbAll.getZbs6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;δ���涨���ա����ɲ�������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbs7" name="zbs7" type="text" size="15" <%if(sjbbAll.getZbs7()!=null){%>value="<%=sjbbAll.getZbs7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���棨��֧����ʵ
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbs8" name="zbs8" type="text" size="15" <%if(sjbbAll.getZbs8()!=null){%>value="<%=sjbbAll.getZbs8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ġ������ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbs9" name="zbs9" type="text" size="15" <%if(sjbbAll.getZbs9()!=null){%>value="<%=sjbbAll.getZbs9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�塢��������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbs10" name="zbs10" type="text" size="15" <%if(sjbbAll.getZbs10()!=null){%>value="<%=sjbbAll.getZbs10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����ٽ����ս�֧���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbs11" name="zbs11" type="text" size="15" <%if(sjbbAll.getZbs11()!=null){%>value="<%=sjbbAll.getZbs11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ߡ��´���ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbs12" name="zbs12" type="text" size="15" <%if(sjbbAll.getZbs12()!=null){%>value="<%=sjbbAll.getZbs12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ˡ���ʵ��ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbs13" name="zbs13" type="text" size="15" <%if(sjbbAll.getZbs13()!=null){%>value="<%=sjbbAll.getZbs13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�š������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbs14" name="zbs14" type="text" size="15" <%if(sjbbAll.getZbs14()!=null){%>value="<%=sjbbAll.getZbs14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ��������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbs15" name="zbs15" type="text" size="15" <%if(sjbbAll.getZbs15()!=null){%>value="<%=sjbbAll.getZbs15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮһ���ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbs16" name="zbs16" type="text" size="15" <%if(sjbbAll.getZbs16()!=null){%>value="<%=sjbbAll.getZbs16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ��������Ԫ����Υ�͵�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbs17" name="zbs17" type="text" size="15" <%if(sjbbAll.getZbs17()!=null){%>value="<%=sjbbAll.getZbs17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ������Ԫ����̰�ۻ�¸����
					</td>
					<td align="center">��
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
					��ע��
					<br>		
					1.����ϵ������֧���ר�����ר���ʽ�ר��ѣ����������Ҳ���иñ�			
					<br>		
					2.�����߼���ϵ��03�С�04��+05��+06��+07��+08�С�
					<br>			
					3.����߼���ϵ�������1��05�Сݽ����2��01�У�		
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��14�Сݽ����2��02�У�		
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��16�Сݽ����2��03�У�				
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��17�Сݽ����2��09�У�		
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��18�Сݽ����2��10�У�
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��19�Сݽ����2��11�У�			
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��20�Сݽ����2��16�У�			
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��22�Сݽ����2��18�С�	
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view3">����������Ʊ���</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">һ�������Ŀ
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbt1" name="zbt1" type="text" size="15" <%if(sjbbAll.getZbt1()!=null){%>value="<%=sjbbAll.getZbt1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У������������
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbt2" name="zbt2" type="text" size="15" <%if(sjbbAll.getZbt2()!=null){%>value="<%=sjbbAll.getZbt2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbt3" name="zbt3" type="text" size="15" <%if(sjbbAll.getZbt3()!=null){%>value="<%=sjbbAll.getZbt3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ר�����
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbt4" name="zbt4" type="text" size="15" <%if(sjbbAll.getZbt4()!=null){%>value="<%=sjbbAll.getZbt4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��������ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbt5" name="zbt5" type="text" size="15" <%if(sjbbAll.getZbt5()!=null){%>value="<%=sjbbAll.getZbt5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У������������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbt6" name="zbt6" type="text" size="15" <%if(sjbbAll.getZbt6()!=null){%>value="<%=sjbbAll.getZbt6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbt7" name="zbt7" type="text" size="15" <%if(sjbbAll.getZbt7()!=null){%>value="<%=sjbbAll.getZbt7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ר�����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbt8" name="zbt8" type="text" size="15" <%if(sjbbAll.getZbt8()!=null){%>value="<%=sjbbAll.getZbt8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����˼�Ͷ�ʣ����㣩��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbt9" name="zbt9" type="text" size="15" <%if(sjbbAll.getZbt9()!=null){%>value="<%=sjbbAll.getZbt9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ġ����Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbt10" name="zbt10" type="text" size="15" <%if(sjbbAll.getZbt10()!=null){%>value="<%=sjbbAll.getZbt10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�����ģ������׼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbt11" name="zbt11" type="text" size="15" <%if(sjbbAll.getZbt11()!=null){%>value="<%=sjbbAll.getZbt11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ռŲ�ý����ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbt12" name="zbt12" type="text" size="15" <%if(sjbbAll.getZbt12()!=null){%>value="<%=sjbbAll.getZbt12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ʽ���ʵ
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbt13" name="zbt13" type="text" size="15" <%if(sjbbAll.getZbt13()!=null){%>value="<%=sjbbAll.getZbt13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����(���У����̳ɱ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbt14" name="zbt14" type="text" size="15" <%if(sjbbAll.getZbt14()!=null){%>value="<%=sjbbAll.getZbt14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;©��˰��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbt15" name="zbt15" type="text" size="15" <%if(sjbbAll.getZbt15()!=null){%>value="<%=sjbbAll.getZbt15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ֺ�Ⱥ����������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbt16" name="zbt16" type="text" size="15" <%if(sjbbAll.getZbt16()!=null){%>value="<%=sjbbAll.getZbt16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�塢�����ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbt17" name="zbt17" type="text" size="15" <%if(sjbbAll.getZbt17()!=null){%>value="<%=sjbbAll.getZbt17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">������������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbt18" name="zbt18" type="text" size="15" <%if(sjbbAll.getZbt18()!=null){%>value="<%=sjbbAll.getZbt18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ߡ�Ӧ�黹ԭ�����ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbt19" name="zbt19" type="text" size="15" <%if(sjbbAll.getZbt19()!=null){%>value="<%=sjbbAll.getZbt19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У��ѹ黹�ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zbt20" name="zbt20" type="text" size="15" <%if(sjbbAll.getZbt20()!=null){%>value="<%=sjbbAll.getZbt20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ˡ��ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zbt21" name="zbt21" type="text" size="15" <%if(sjbbAll.getZbt21()!=null){%>value="<%=sjbbAll.getZbt21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�š��´���ƾ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zbt22" name="zbt22" type="text" size="15" <%if(sjbbAll.getZbt22()!=null){%>value="<%=sjbbAll.getZbt22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ����ʵ��ƾ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zbt23" name="zbt23" type="text" size="15" <%if(sjbbAll.getZbt23()!=null){%>value="<%=sjbbAll.getZbt23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮһ�������ƽ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">24
					</td>
					<td align="center">
					<input id="zbt24" name="zbt24" type="text" size="15" <%if(sjbbAll.getZbt24()!=null){%>value="<%=sjbbAll.getZbt24()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ����������ƽ�������
					</td>
					<td align="center">��
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
					��ע��
					<br>		
					1.����ϵ�����������ר����й�ָ���ָ������������е����ݡ�					
					<br>
					2.�����߼���ϵ��01��=02��+03��+04�У�05��=06��+07��+08�У�	
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;10�С�11��+12��+13��+14��+15��+16�У�19�С�20�С�
					<br>
					3.����߼���ϵ�������1��05�Сݽ����3��01�У������1��14�Сݽ����3��05�У�
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��16�Сݽ����3��10�У������1��17�Сݽ����3��17�У�
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��18�Сݽ����3��18�У������1��20�Сݽ����3��21�С�
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view4">����������Ʊ���</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left">һ�� ��ƾ���������
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbf1" name="zbf1" type="text" size="15" <%if(sjbbAll.getZbf1()!=null){%>value="<%=sjbbAll.getZbf1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�������λ
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbf2" name="zbf2" type="text" size="15" <%if(sjbbAll.getZbf2()!=null){%>value="<%=sjbbAll.getZbf2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbf3" name="zbf3" type="text" size="15" <%if(sjbbAll.getZbf3()!=null){%>value="<%=sjbbAll.getZbf3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbf4" name="zbf4" type="text" size="15" <%if(sjbbAll.getZbf4()!=null){%>value="<%=sjbbAll.getZbf4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">������Ƶ�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbf5" name="zbf5" type="text" size="15" <%if(sjbbAll.getZbf5()!=null){%>value="<%=sjbbAll.getZbf5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�������λ
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbf6" name="zbf6" type="text" size="15" <%if(sjbbAll.getZbf6()!=null){%>value="<%=sjbbAll.getZbf6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbf7" name="zbf7" type="text" size="15" <%if(sjbbAll.getZbf7()!=null){%>value="<%=sjbbAll.getZbf7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbf8" name="zbf8" type="text" size="15" <%if(sjbbAll.getZbf8()!=null){%>value="<%=sjbbAll.getZbf8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�������Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbf9" name="zbf9" type="text" size="15" <%if(sjbbAll.getZbf9()!=null){%>value="<%=sjbbAll.getZbf9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbf10" name="zbf10" type="text" size="15" <%if(sjbbAll.getZbf10()!=null){%>value="<%=sjbbAll.getZbf10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbf11" name="zbf11" type="text" size="15" <%if(sjbbAll.getZbf11()!=null){%>value="<%=sjbbAll.getZbf11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbf12" name="zbf12" type="text" size="15" <%if(sjbbAll.getZbf12()!=null){%>value="<%=sjbbAll.getZbf12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ġ������ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbf13" name="zbf13" type="text" size="15" <%if(sjbbAll.getZbf13()!=null){%>value="<%=sjbbAll.getZbf13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbf14" name="zbf14" type="text" size="15" <%if(sjbbAll.getZbf14()!=null){%>value="<%=sjbbAll.getZbf14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbf15" name="zbf15" type="text" size="15" <%if(sjbbAll.getZbf15()!=null){%>value="<%=sjbbAll.getZbf15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbf16" name="zbf16" type="text" size="15" <%if(sjbbAll.getZbf16()!=null){%>value="<%=sjbbAll.getZbf16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�塢��������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbf17" name="zbf17" type="text" size="15" <%if(sjbbAll.getZbf17()!=null){%>value="<%=sjbbAll.getZbf17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbf18" name="zbf18" type="text" size="15" <%if(sjbbAll.getZbf18()!=null){%>value="<%=sjbbAll.getZbf18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbf19" name="zbf19" type="text" size="15" <%if(sjbbAll.getZbf19()!=null){%>value="<%=sjbbAll.getZbf19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">20
					</td>
					<td align="center">
					<input id="zbf20" name="zbf20" type="text" size="15" <%if(sjbbAll.getZbf20()!=null){%>value="<%=sjbbAll.getZbf20()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�����쵼�ɲ��漰���˾�������
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					<input id="zbf21" name="zbf21" type="text" size="15" <%if(sjbbAll.getZbf21()!=null){%>value="<%=sjbbAll.getZbf21()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�����
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					<input id="zbf22" name="zbf22" type="text" size="15" <%if(sjbbAll.getZbf22()!=null){%>value="<%=sjbbAll.getZbf22()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�漰���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">23
					</td>
					<td align="center">
					<input id="zbf23" name="zbf23" type="text" size="15" <%if(sjbbAll.getZbf23()!=null){%>value="<%=sjbbAll.getZbf23()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ߡ���Ա������
					</td>
					<td align="center">��
					</td>
					<td align="center">24
					</td>
					<td align="center">
					<input id="zbf24" name="zbf24" type="text" size="15" <%if(sjbbAll.getZbf24()!=null){%>value="<%=sjbbAll.getZbf24()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У���ְ������
					</td>
					<td align="center">��
					</td>
					<td align="center">25
					</td>
					<td align="center">
					<input id="zbf25" name="zbf25" type="text" size="15" <%if(sjbbAll.getZbf25()!=null){%>value="<%=sjbbAll.getZbf25()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��
					</td>
					<td align="center">26
					</td>
					<td align="center">
					<input id="zbf26" name="zbf26" type="text" size="15" <%if(sjbbAll.getZbf26()!=null){%>value="<%=sjbbAll.getZbf26()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����˾������
					</td>
					<td align="center">��
					</td>
					<td align="center">27
					</td>
					<td align="center">
					<input id="zbf27" name="zbf27" type="text" size="15" <%if(sjbbAll.getZbf27()!=null){%>value="<%=sjbbAll.getZbf27()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ͼͼ������
					</td>
					<td align="center">��
					</td>
					<td align="center">28
					</td>
					<td align="center">
					<input id="zbf28" name="zbf28" type="text" size="15" <%if(sjbbAll.getZbf28()!=null){%>value="<%=sjbbAll.getZbf28()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�ˡ��´���ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">29
					</td>
					<td align="center">
					<input id="zbf29" name="zbf29" type="text" size="15" <%if(sjbbAll.getZbf29()!=null){%>value="<%=sjbbAll.getZbf29()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�š���ʵ��ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">30
					</td>
					<td align="center">
					<input id="zbf30" name="zbf30" type="text" size="15" <%if(sjbbAll.getZbf30()!=null){%>value="<%=sjbbAll.getZbf30()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ�������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">31
					</td>
					<td align="center">
					<input id="zbf31" name="zbf31" type="text" size="15" <%if(sjbbAll.getZbf31()!=null){%>value="<%=sjbbAll.getZbf31()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮһ��������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">32
					</td>
					<td align="center">
					<input id="zbf32" name="zbf32" type="text" size="15" <%if(sjbbAll.getZbf32()!=null){%>value="<%=sjbbAll.getZbf32()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">ʮ�����ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
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
					��ע��
					<br>		
					1.����ϵ�����������ר����й�ָ���ָ������������е����ݡ�
					<br>		
					2.�����߼���ϵ��01��=02��+03��+04�У�05��=06��+07��+08�У�09�С�10��+11��+12�У�
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;13�С�14��+15��+16�У�17�С�18��+19��+20�У�21�С�22�У�	
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;24�С�25��+26��+27��+28�С�
					<br>
					3.����߼���ϵ�������1��05�Сݽ����4��05�У������1��16�Сݽ����4��09��
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��17�Сݽ����4��13�У������1��18�Сݽ����4��17��
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��20�Сݽ����4��33�У�
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;�����1��23�Сݽ����4��27��+�����4��28�С�
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<a id="view5">��ƻ�����Ա����</a>
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="40%" colspan="2">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">һ���ѽ�����
					</td>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input id="zbv1" name="zbv1" type="text" size="15" <%if(sjbbAll.getZbv1()!=null){%>value="<%=sjbbAll.getZbv1()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input id="zbv2" name="zbv2" type="text" size="15" <%if(sjbbAll.getZbv2()!=null){%>value="<%=sjbbAll.getZbv2()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input id="zbv3" name="zbv3" type="text" size="15" <%if(sjbbAll.getZbv3()!=null){%>value="<%=sjbbAll.getZbv3()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�Ƽ�����
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input id="zbv4" name="zbv4" type="text" size="15" <%if(sjbbAll.getZbv4()!=null){%>value="<%=sjbbAll.getZbv4()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">������Ա����
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input id="zbv5" name="zbv5" type="text" size="15" <%if(sjbbAll.getZbv5()!=null){%>value="<%=sjbbAll.getZbv5()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">�����䱸��Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input id="zbv6" name="zbv6" type="text" size="15" <%if(sjbbAll.getZbv6()!=null){%>value="<%=sjbbAll.getZbv6()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">��һ�����������ʷ�
					</td>
					<td align="left">ר&nbsp;&nbsp;ְ
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input id="zbv7" name="zbv7" type="text" size="15" <%if(sjbbAll.getZbv7()!=null){%>value="<%=sjbbAll.getZbv7()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��&nbsp;&nbsp;ְ
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input id="zbv8" name="zbv8" type="text" size="15" <%if(sjbbAll.getZbv8()!=null){%>value="<%=sjbbAll.getZbv8()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">��������ѧ����
					</td>
					<td align="left">��ר������
					</td>
					<td align="center">��
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input id="zbv9" name="zbv9" type="text" size="15" <%if(sjbbAll.getZbv9()!=null){%>value="<%=sjbbAll.getZbv9()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��ר������
					</td>
					<td align="center">��
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input id="zbv10" name="zbv10" type="text" size="15" <%if(sjbbAll.getZbv10()!=null){%>value="<%=sjbbAll.getZbv10()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">��������ְ�Ʒ�
					</td>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input id="zbv11" name="zbv11" type="text" size="15" <%if(sjbbAll.getZbv11()!=null){%>value="<%=sjbbAll.getZbv11()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input id="zbv12" name="zbv12" type="text" size="15" <%if(sjbbAll.getZbv12()!=null){%>value="<%=sjbbAll.getZbv12()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">��������
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input id="zbv13" name="zbv13" type="text" size="15" <%if(sjbbAll.getZbv13()!=null){%>value="<%=sjbbAll.getZbv13()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">���ģ��������
					</td>
					<td align="left">45������
					</td>
					<td align="center">��
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input id="zbv14" name="zbv14" type="text" size="15" <%if(sjbbAll.getZbv14()!=null){%>value="<%=sjbbAll.getZbv14()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">30��44��
					</td>
					<td align="center">��
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input id="zbv15" name="zbv15" type="text" size="15" <%if(sjbbAll.getZbv15()!=null){%>value="<%=sjbbAll.getZbv15()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">29������
					</td>
					<td align="center">��
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input id="zbv16" name="zbv16" type="text" size="15" <%if(sjbbAll.getZbv16()!=null){%>value="<%=sjbbAll.getZbv16()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">�ġ���ѵ��Ա
					</td>
					<td align="left">��&nbsp;&nbsp;��
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">17
					</td>
					<td align="center">
					<input id="zbv17" name="zbv17" type="text" size="15" <%if(sjbbAll.getZbv17()!=null){%>value="<%=sjbbAll.getZbv17()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�԰���ѵ
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">18
					</td>
					<td align="center">
					<input id="zbv18" name="zbv18" type="text" size="15" <%if(sjbbAll.getZbv18()!=null){%>value="<%=sjbbAll.getZbv18()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�μӲ�����ѵ
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">19
					</td>
					<td align="center">
					<input id="zbv19" name="zbv19" type="text" size="15" <%if(sjbbAll.getZbv19()!=null){%>value="<%=sjbbAll.getZbv19()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">�μ���ϵͳ����λ��ѵ
					</td>
					<td align="center">�˴�
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
					<td align="center">������߼���飺
					<img align="middle" alt="����" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
					</td>
				</tr>
				<tr>
					<td align="left" colspan="3">
					��ע��
					<br>		
					1.����ϵ��ƻ�����Ա���ר����������ָ���ͳ�Ʒ�Χ����������ͨ���ܲ��źͽ�ͨ����ҵ��λ��
					<br>		
					2.�����߼���ϵ��01��=02��+03��+04�У�
					<br>	
					&nbsp;&nbsp;&nbsp;&nbsp;06��=07��+08��=09��+10��=11��+12��+13��=14��+15��+16�У�
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;17��=18��+19��+20�С�
					<br>			
					3.����߼���ϵ�������1��03�Сݽ����5��06��
					</td>
				</tr>
			</table>
		</form>
		<form id="form2"  method="post"></form>
		<form id="form3" method="post"  enctype="multipart/form-data" target="ifm">
		<table  width="100%">
			<tr>
				<td align="left">
				�ϴ�EXCEL��<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
				<input type="button" class="button1" class="button1" onclick="infExcel()" value="����EXCEL" />
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="�ύ" width="45px" height="45px" title="�ύ" style="cursor: pointer;"  src="images/small/send.png" onclick="save(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="��ݸ�" width="45px" height="45px" title="��ݸ�" style="cursor: pointer;"  src="images/small/icon_cs_2.png" onclick="save(2);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="����" width="45px" height="45px" title="����" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<iframe id='ifm' name='ifm' style="display:none"></iframe>
		</form>
	<%}%>
	</body>
</html>



