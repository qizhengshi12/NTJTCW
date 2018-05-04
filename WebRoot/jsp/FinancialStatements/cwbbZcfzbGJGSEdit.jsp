<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbZcfzb"%>
<%@page import="com.safety.entity.CwbbZcfzbzc"%>
<%@page import="com.safety.entity.CwbbZcfzbfz"%>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>新增修改资产负债表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
				if(verification()==1){
					alert("请校验后再保存");
					return;
				}
			}
	   		var sbsj = document.getElementById("sbsj").value;
	   		if(sbsj==""){
	   			alert("请输入日期");
	   			return;
	   		}
	   		var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=ZcfzbSave";
			form.submit();
	    }
	    function addCommas(nStr)  
        {  
         	nStr = nStr.replace(/,/g,"");//删除所有逗号;
         	var rgx1 = /^\-?\d+(\.\d+)?$/;
         	if(!rgx1.test(nStr)&&nStr!="") {  
            	alert("输入有误!");
            	return "";
            } 
        	nStr = Number(nStr).toFixed(2);
            nStr += '';  
            x = nStr.split('.');  
            x1 = x[0];  
            x2 = x.length > 1 ? '.' + x[1] : '';  
            var rgx2 = /(\d+)(\d{3})/;  
            while (rgx2.test(x1)) {  
                x1 = x1.replace(rgx2, '$1' + ',' + '$2');  
            }  
            return x1 + x2;  
        }  
	    
	    function addCommas2(nStr)  
        {  
            nStr += '';  
            x = nStr.split('.');  
            x1 = x[0];  
            x2 = x.length > 1 ? '.' + x[1] : '';  
            var rgx2 = /(\d+)(\d{3})/;  
            while (rgx2.test(x1)) {  
                x1 = x1.replace(rgx2, '$1' + ',' + '$2');  
            }  
            return x1 + x2;  
        }
		//数据验证
        function verification(){
        	var ncs1 = document.getElementsByName("ncs1");
        	var qms1 = document.getElementsByName("qms1");
         	for(var i=0;i<ncs1.length;i++){
         		ncs1[i].value = ncs1[i].value.replace(/,/g,"");//删除所有逗号;
         		qms1[i].value = qms1[i].value.replace(/,/g,"");//删除所有逗号;
         		//空项全部置零
	        	if(ncs1[i].value=="")ncs1[i].value=0;
	        	if(qms1[i].value=="")qms1[i].value=0;
        	}
        	ncs1[0].value="";
        	ncs1[16].value="";
        	ncs1[45].value="";
        	ncs1[46].value="";
        	ncs1[47].value="";
        	ncs1[48].value="";
        	ncs1[49].value="";
        	ncs1[50].value="";
        	ncs1[51].value="";
        	qms1[0].value="";
        	qms1[16].value="";
        	qms1[45].value="";
        	qms1[46].value="";
        	qms1[47].value="";
        	qms1[48].value="";
        	qms1[49].value="";
        	qms1[50].value="";
        	qms1[51].value="";
        	//资产年初数
        	ncs1[15].value = (Number(ncs1[1].value)+Number(ncs1[2].value)+Number(ncs1[3].value)+Number(ncs1[4].value)+Number(ncs1[5].value)+Number(ncs1[6].value)+Number(ncs1[7].value)+Number(ncs1[8].value)+Number(ncs1[9].value)+Number(ncs1[10].value)+Number(ncs1[13].value)+Number(ncs1[14].value)).toFixed(2);
        	ncs1[26].value = (Number(ncs1[24].value)-Number(ncs1[25].value)).toFixed(2);
        	ncs1[28].value = (Number(ncs1[26].value)-Number(ncs1[27].value)).toFixed(2);
        	ncs1[44].value = (Number(ncs1[17].value)+Number(ncs1[18].value)+Number(ncs1[19].value)+Number(ncs1[20].value)+Number(ncs1[21].value)+Number(ncs1[22].value)+Number(ncs1[23].value)+Number(ncs1[28].value)+Number(ncs1[29].value)+Number(ncs1[30].value)+Number(ncs1[31].value)+Number(ncs1[32].value)+Number(ncs1[33].value)+Number(ncs1[34].value)+Number(ncs1[36].value)+Number(ncs1[37].value)+Number(ncs1[38].value)+Number(ncs1[39].value)+Number(ncs1[40].value)+Number(ncs1[41].value)+Number(ncs1[42].value)).toFixed(2);
        	ncs1[52].value = (Number(ncs1[15].value)+Number(ncs1[44].value)).toFixed(2);
        	//资产期末数
        	qms1[15].value = (Number(qms1[1].value)+Number(qms1[2].value)+Number(qms1[3].value)+Number(qms1[4].value)+Number(qms1[5].value)+Number(qms1[6].value)+Number(qms1[7].value)+Number(qms1[8].value)+Number(qms1[9].value)+Number(qms1[10].value)+Number(qms1[13].value)+Number(qms1[14].value)).toFixed(2);
        	qms1[26].value = (Number(qms1[24].value)-Number(qms1[25].value)).toFixed(2);
        	qms1[28].value = (Number(qms1[26].value)-Number(qms1[27].value)).toFixed(2);
        	qms1[44].value = (Number(qms1[17].value)+Number(qms1[18].value)+Number(qms1[19].value)+Number(qms1[20].value)+Number(qms1[21].value)+Number(qms1[22].value)+Number(qms1[23].value)+Number(qms1[28].value)+Number(qms1[29].value)+Number(qms1[30].value)+Number(qms1[31].value)+Number(qms1[32].value)+Number(qms1[33].value)+Number(qms1[34].value)+Number(qms1[36].value)+Number(qms1[37].value)+Number(qms1[38].value)+Number(qms1[39].value)+Number(qms1[40].value)+Number(qms1[41].value)+Number(qms1[42].value)).toFixed(2);
        	qms1[52].value = (Number(qms1[15].value)+Number(qms1[44].value)).toFixed(2);
        	//加逗号
        	for(var j=0;j<ncs1.length;j++){
         		ncs1[j].value = addCommas2(ncs1[j].value);
         		qms1[j].value = addCommas2(qms1[j].value);
        	}
        	
        	var ncs2 = document.getElementsByName("ncs2");
        	var qms2 = document.getElementsByName("qms2");
         	for(var k=0;k<ncs2.length;k++){
         		ncs2[k].value = ncs2[k].value.replace(/,/g,"");//删除所有逗号;
         		qms2[k].value = qms2[k].value.replace(/,/g,"");//删除所有逗号;
         		//空项全部置零
	        	if(ncs2[k].value=="")ncs2[k].value=0;
	        	if(qms2[k].value=="")qms2[k].value=0;
        	}
        	ncs2[0].value="";
        	ncs2[18].value="";
        	ncs2[30].value="";
        	qms2[0].value="";
        	qms2[18].value="";
        	qms2[30].value="";
        	//负债年初数
        	ncs2[17].value = (Number(ncs2[1].value)+Number(ncs2[2].value)+Number(ncs2[3].value)+Number(ncs2[4].value)+Number(ncs2[5].value)+Number(ncs2[6].value)+Number(ncs2[7].value)+Number(ncs2[10].value)+Number(ncs2[12].value)+Number(ncs2[13].value)+Number(ncs2[14].value)+Number(ncs2[15].value)+Number(ncs2[16].value)).toFixed(2);
        	ncs2[28].value = (Number(ncs2[19].value)+Number(ncs2[20].value)+Number(ncs2[21].value)+Number(ncs2[22].value)+Number(ncs2[23].value)+Number(ncs2[24].value)+Number(ncs2[25].value)+Number(ncs2[26].value)).toFixed(2);
        	ncs2[29].value = (Number(ncs2[17].value)+Number(ncs2[28].value)).toFixed(2);
        	ncs2[49].value = (Number(ncs2[31].value)+Number(ncs2[39].value)-Number(ncs2[40].value)+Number(ncs2[41].value)+Number(ncs2[42].value)+Number(ncs2[43].value)+Number(ncs2[44].value)+Number(ncs2[46].value)+Number(ncs2[47].value)+Number(ncs2[48].value)).toFixed(2);
        	ncs2[51].value = (Number(ncs2[49].value)+Number(ncs2[50].value)).toFixed(2);
        	ncs2[52].value = (Number(ncs2[29].value)+Number(ncs2[51].value)).toFixed(2);
        	//负债期末数
        	qms2[17].value = (Number(qms2[1].value)+Number(qms2[2].value)+Number(qms2[3].value)+Number(qms2[4].value)+Number(qms2[5].value)+Number(qms2[6].value)+Number(qms2[7].value)+Number(qms2[10].value)+Number(qms2[12].value)+Number(qms2[13].value)+Number(qms2[14].value)+Number(qms2[15].value)+Number(qms2[16].value)).toFixed(2);
        	qms2[28].value = (Number(qms2[19].value)+Number(qms2[20].value)+Number(qms2[21].value)+Number(qms2[22].value)+Number(qms2[23].value)+Number(qms2[24].value)+Number(qms2[25].value)+Number(qms2[26].value)).toFixed(2);
        	qms2[29].value = (Number(qms2[17].value)+Number(qms2[28].value)).toFixed(2);
        	qms2[49].value = (Number(qms2[31].value)+Number(qms2[39].value)-Number(qms2[40].value)+Number(qms2[41].value)+Number(qms2[42].value)+Number(qms2[43].value)+Number(qms2[44].value)+Number(qms2[46].value)+Number(qms2[47].value)+Number(qms2[48].value)).toFixed(2);
        	qms2[51].value = (Number(qms2[49].value)+Number(qms2[50].value)).toFixed(2);
        	qms2[52].value = (Number(qms2[29].value)+Number(qms2[51].value)).toFixed(2);
        	//加逗号
        	for(var l=0;l<ncs2.length;l++){
         		ncs2[l].value = addCommas2(ncs2[l].value);
         		qms2[l].value = addCommas2(qms2[l].value);
        	}
        	if(ncs1[52].value!=ncs2[52].value){
        		ncs1[52].style.color='red';
        		ncs2[52].style.color='red';
        		return 1;
        	}else{
        		ncs1[52].style.color='black';
        		ncs2[52].style.color='black';
        	}
        	if(qms1[52].value!=qms2[52].value){
        		qms1[52].style.color='red';
        		qms2[52].style.color='red';
        		return 1;
        	}else{
        		qms1[52].style.color='black';
        		qms2[52].style.color='black';
        	}
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbCwbbServlet?action=getZcfzb&flag=1&menuname="+menuname;
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
		    var path = "tempFile/zcfzb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readZcfzbGJGSExcel&URL="+msg;
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
         	CwbbZcfzb cwbbZcfzb = (CwbbZcfzb)request.getAttribute("cwbbZcfzb");
    		ArrayList<CwbbZcfzbzc> CwbbZcfzbzcList = (ArrayList)request.getAttribute("CwbbZcfzbzcList");
    		ArrayList<CwbbZcfzbfz> CwbbZcfzbfzList = (ArrayList)request.getAttribute("CwbbZcfzbfzList");
    		
		%>
		<form id="form1" name="CwbbZcfzb" method="post">
		<input type="hidden" name="cwbbZcfzb_id" id="cwbbZcfzb_id" <%if(cwbbZcfzb.getId()!=0){%>value="<%=cwbbZcfzb.getId()%>"<%}%> >
		<input type="hidden" name="hzbz" id="hzbz" value="GJGS" >
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="3" style="font-weight: bold;">
					<input name="bt" type="text" size="20" <%if(cwbbZcfzb.getBt()!=null){%>value="<%=cwbbZcfzb.getBt()%>"<%}else{%>value="资产负债表"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" width="30%">
						填报单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="center" width="30%">
						时间：<input name="sbsj" id="sbsj" type="text" size="10" <%if(cwbbZcfzb.getSbsj()!=null){%>value="<%=cwbbZcfzb.getSbsj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.CwbbZcfzb.sbsj);" type="button" value="选择">
					</td>
					<td align="right" width="30%">
						单位：元
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="15%">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目
					</td>
					<td align="center" width="5%">行次
					</td>
					<td align="center" width="15%">期末余额
					</td>
					<td align="center" width="15%">期初余额
					</td>
					<td align="center" width="15%">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目
					</td>
					<td align="center" width="5%">行次
					</td>
					<td align="center" width="15%">期末余额
					</td>
					<td align="center" width="15%">期初余额
					</td>
				</tr>
				<tr>
					<td align="left">流动资产：
					<input name="mc1" type="hidden" value="流动资产："/>
					</td>
					<td align="center">1
					<input name="hc1" type="hidden" value="1"/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">流动负债：
					<input name="mc2" type="hidden" value="流动负债："/>
					</td>
					<td align="center">47
					<input name="hc2" type="hidden" value="47"/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">货币资金
					<input name="mc1" type="hidden" value="货币资金"/>
					</td>
					<td align="center">2
					<input name="hc1" type="hidden" value="2"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(1).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(1).getNcs()%>"<%}%>/>
					</td>
					<td align="left">短期借款
					<input name="mc2" type="hidden" value="短期借款"/>
					</td>
					<td align="center">48
					<input name="hc2" type="hidden" value="48"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(1).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(1).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△交易性金融资产
					<input name="mc1" type="hidden" value="△交易性金融资产"/>
					</td>
					<td align="center">3
					<input name="hc1" type="hidden" value="3"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(2).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(2).getNcs()%>"<%}%>/>
					</td>
					<td align="left">△交易性金融负债
					<input name="mc2" type="hidden" value="△交易性金融负债"/>
					</td>
					<td align="center">49
					<input name="hc2" type="hidden" value="49"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(2).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(2).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">＃短期投资
					<input name="mc1" type="hidden" value="＃短期投资"/>
					</td>
					<td align="center">4
					<input name="hc1" type="hidden" value="4"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(3).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(3).getNcs()%>"<%}%>/>
					</td>
					<td align="left">＃应付权证
					<input name="mc2" type="hidden" value="＃应付权证"/>
					</td>
					<td align="center">50
					<input name="hc2" type="hidden" value="50"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(3).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(3).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">应收票据
					<input name="mc1" type="hidden" value="应收票据"/>
					</td>
					<td align="center">5
					<input name="hc1" type="hidden" value="5"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(4).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(4).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付票据
					<input name="mc2" type="hidden" value="应付票据"/>
					</td>
					<td align="center">51
					<input name="hc2" type="hidden" value="51"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(4).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(4).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">应收股利
					<input name="mc1" type="hidden" value="应收股利"/>
					</td>
					<td align="center">6
					<input name="hc1" type="hidden" value="6"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(5).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(5).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付账款
					<input name="mc2" type="hidden" value="应付账款"/>
					</td>
					<td align="center">52
					<input name="hc2" type="hidden" value="52"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(5).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(5).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">应收利息
					<input name="mc1" type="hidden" value="应收利息"/>
					</td>
					<td align="center">7
					<input name="hc1" type="hidden" value="7"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(6).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(6).getNcs()%>"<%}%>/>
					</td>
					<td align="left">预收款项
					<input name="mc2" type="hidden" value="预收款项"/>
					</td>
					<td align="center">53
					<input name="hc2" type="hidden" value="53"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(6).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(6).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">应收账款
					<input name="mc1" type="hidden" value="应收账款"/>
					</td>
					<td align="center">8
					<input name="hc1" type="hidden" value="8"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(7).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(7).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付职工薪酬
					<input name="mc2" type="hidden" value="应付职工薪酬"/>
					</td>
					<td align="center">54
					<input name="hc2" type="hidden" value="54"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(7).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(7).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其他应收款
					<input name="mc1" type="hidden" value="其他应收款"/>
					</td>
					<td align="center">9
					<input name="hc1" type="hidden" value="9"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(8).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(8).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其中：应付工资
					<input name="mc2" type="hidden" value="其中：应付工资"/>
					</td>
					<td align="center">55
					<input name="hc2" type="hidden" value="55"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(8).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(8).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">预付款项
					<input name="mc1" type="hidden" value="预付款项"/>
					</td>
					<td align="center">10
					<input name="hc1" type="hidden" value="10"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(9).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(9).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付福利费
					<input name="mc2" type="hidden" value="应付福利费"/>
					</td>
					<td align="center">56
					<input name="hc2" type="hidden" value="56"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(9).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(9).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">存货
					<input name="mc1" type="hidden" value="存货"/>
					</td>
					<td align="center">11
					<input name="hc1" type="hidden" value="11"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(10).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(10).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应交税费
					<input name="mc2" type="hidden" value="应交税费"/>
					</td>
					<td align="center">57
					<input name="hc2" type="hidden" value="57"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(10).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(10).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其中：原材料
					<input name="mc1" type="hidden" value="其中：原材料"/>
					</td>
					<td align="center">12
					<input name="hc1" type="hidden" value="12"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(11).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(11).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其中：应交税金
					<input name="mc2" type="hidden" value="其中：应交税金"/>
					</td>
					<td align="center">58
					<input name="hc2" type="hidden" value="58"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(11).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(11).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">库存商品（产成品）
					<input name="mc1" type="hidden" value="库存商品（产成品）"/>
					</td>
					<td align="center">13
					<input name="hc1" type="hidden" value="13"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(12).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(12).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付利息
					<input name="mc2" type="hidden" value="应付利息"/>
					</td>
					<td align="center">59
					<input name="hc2" type="hidden" value="59"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(12).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(12).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">一年内到期的非流动资产
					<input name="mc1" type="hidden" value="一年内到期的非流动资产"/>
					</td>
					<td align="center">14
					<input name="hc1" type="hidden" value="14"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(13).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(13).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付股利
					<input name="mc2" type="hidden" value="应付股利"/>
					</td>
					<td align="center">60
					<input name="hc2" type="hidden" value="60"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(13).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(13).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其他流动资产
					<input name="mc1" type="hidden" value="其他流动资产"/>
					</td>
					<td align="center">15
					<input name="hc1" type="hidden" value="15"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(14).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(14).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其他应付款
					<input name="mc2" type="hidden" value="其他应付款"/>
					</td>
					<td align="center">61
					<input name="hc2" type="hidden" value="61"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(14).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(14).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">流动资产合计
					<input name="mc1" type="hidden" value="流动资产合计"/>
					</td>
					<td align="center">16
					<input name="hc1" type="hidden" value="16"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(15).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(15).getNcs()%>"<%}%>/>
					</td>
					<td align="left">一年内到期的非流动负债
					<input name="mc2" type="hidden" value="一年内到期的非流动负债"/>
					</td>
					<td align="center">62
					<input name="hc2" type="hidden" value="62"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(15).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(15).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">非流动资产：
					<input name="mc1" type="hidden" value="非流动资产："/>
					</td>
					<td align="center">17
					<input name="hc1" type="hidden" value="17"/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">其他流动负债
					<input name="mc2" type="hidden" value="其他流动负债"/>
					</td>
					<td align="center">63
					<input name="hc2" type="hidden" value="63"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(16).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(16).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△可供出售金融资产
					<input name="mc1" type="hidden" value="△可供出售金融资产"/>
					</td>
					<td align="center">18
					<input name="hc1" type="hidden" value="18"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(17).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(17).getNcs()%>"<%}%>/>
					</td>
					<td align="left">流动负债合计
					<input name="mc2" type="hidden" value="流动负债合计"/>
					</td>
					<td align="center">64
					<input name="hc2" type="hidden" value="64"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(17).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(17).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△持有至到期投资
					<input name="mc1" type="hidden" value="△持有至到期投资"/>
					</td>
					<td align="center">19
					<input name="hc1" type="hidden" value="19"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(18).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(18).getNcs()%>"<%}%>/>
					</td>
					<td align="left">非流动负债：
					<input name="mc2" type="hidden" value="非流动负债："/>
					</td>
					<td align="center">65
					<input name="hc2" type="hidden" value="65"/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">＃长期债权投资
					<input name="mc1" type="hidden" value="＃长期债权投资"/>
					</td>
					<td align="center">20
					<input name="hc1" type="hidden" value="20"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(19).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(19).getNcs()%>"<%}%>/>
					</td>
					<td align="left">长期借款
					<input name="mc2" type="hidden" value="长期借款"/>
					</td>
					<td align="center">66
					<input name="hc2" type="hidden" value="66"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(19).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(19).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△长期应收款
					<input name="mc1" type="hidden" value="△长期应收款"/>
					</td>
					<td align="center">21
					<input name="hc1" type="hidden" value="21"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(20).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(20).getNcs()%>"<%}%>/>
					</td>
					<td align="left">应付债券
					<input name="mc2" type="hidden" value="应付债券"/>
					</td>
					<td align="center">67
					<input name="hc2" type="hidden" value="67"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(20).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(20).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">长期股权投资
					<input name="mc1" type="hidden" value="长期股权投资"/>
					</td>
					<td align="center">22
					<input name="hc1" type="hidden" value="22"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(21).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(21).getNcs()%>"<%}%>/>
					</td>
					<td align="left">长期应付款
					<input name="mc2" type="hidden" value="长期应付款"/>
					</td>
					<td align="center">68
					<input name="hc2" type="hidden" value="68"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(21).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(21).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">＃股权分置流通权
					<input name="mc1" type="hidden" value="＃股权分置流通权"/>
					</td>
					<td align="center">23
					<input name="hc1" type="hidden" value="23"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(22).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(22).getNcs()%>"<%}%>/>
					</td>
					<td align="left">专项应付款
					<input name="mc2" type="hidden" value="专项应付款"/>
					</td>
					<td align="center">69
					<input name="hc2" type="hidden" value="69"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(22).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(22).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△投资性房地产
					<input name="mc1" type="hidden" value="△投资性房地产"/>
					</td>
					<td align="center">24
					<input name="hc1" type="hidden" value="24"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(23).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(23).getNcs()%>"<%}%>/>
					</td>
					<td align="left">预计负债
					<input name="mc2" type="hidden" value="预计负债"/>
					</td>
					<td align="center">70
					<input name="hc2" type="hidden" value="70"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(23).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(23).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">固定资产原价
					<input name="mc1" type="hidden" value="固定资产原价"/>
					</td>
					<td align="center">25
					<input name="hc1" type="hidden" value="25"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(24).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(24).getNcs()%>"<%}%>/>
					</td>
					<td align="left">△递延所得税负债
					<input name="mc2" type="hidden" value="△递延所得税负债"/>
					</td>
					<td align="center">71
					<input name="hc2" type="hidden" value="71"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(24).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(24).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">减：累计折旧
					<input name="mc1" type="hidden" value="减：累计折旧"/>
					</td>
					<td align="center">26
					<input name="hc1" type="hidden" value="26"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(25).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(25).getNcs()%>"<%}%>/>
					</td>
					<td align="left">＃递延税款贷项
					<input name="mc2" type="hidden" value="＃递延税款贷项"/>
					</td>
					<td align="center">72
					<input name="hc2" type="hidden" value="72"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(25).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(25).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">固定资产净值
					<input name="mc1" type="hidden" value="固定资产净值"/>
					</td>
					<td align="center">27
					<input name="hc1" type="hidden" value="27"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(26).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(26).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其他非流动负债
					<input name="mc2" type="hidden" value="其他非流动负债"/>
					</td>
					<td align="center">73
					<input name="hc2" type="hidden" value="48"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(26).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(26).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">减：固定资产减值准备
					<input name="mc1" type="hidden" value="减：固定资产减值准备"/>
					</td>
					<td align="center">28
					<input name="hc1" type="hidden" value="28"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(27).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(27).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其中：特准储备基金
					<input name="mc2" type="hidden" value="其中：特准储备基金"/>
					</td>
					<td align="center">74
					<input name="hc2" type="hidden" value="74"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(27).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(27).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">固定资产净额
					<input name="mc1" type="hidden" value="固定资产净额"/>
					</td>
					<td align="center">29
					<input name="hc1" type="hidden" value="29"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(28).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(28).getNcs()%>"<%}%>/>
					</td>
					<td align="left">非流动负债合计
					<input name="mc2" type="hidden" value="非流动负债合计"/>
					</td>
					<td align="center">75
					<input name="hc2" type="hidden" value="75"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(28).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(28).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">在建工程
					<input name="mc1" type="hidden" value="在建工程"/>
					</td>
					<td align="center">30
					<input name="hc1" type="hidden" value="30"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(29).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(29).getNcs()%>"<%}%>/>
					</td>
					<td align="left">负债合计
					<input name="mc2" type="hidden" value="负债合计"/>
					</td>
					<td align="center">76
					<input name="hc2" type="hidden" value="76"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(29).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(29).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">工程物资
					<input name="mc1" type="hidden" value="工程物资"/>
					</td>
					<td align="center">31
					<input name="hc1" type="hidden" value="31"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(30).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(30).getNcs()%>"<%}%>/>
					</td>
					<td align="left">所有者权益（或股东权益）：
					<input name="mc2" type="hidden" value="所有者权益（或股东权益）："/>
					</td>
					<td align="center">77
					<input name="hc2" type="hidden" value="77"/>
					</td>
					<td align="center">
					<input name="qms2" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs2" type="hidden" value=""/>
					</td>
				</tr>
				<tr>
					<td align="left">固定资产清理
					<input name="mc1" type="hidden" value="固定资产清理"/>
					</td>
					<td align="center">32
					<input name="hc1" type="hidden" value="30"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(31).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(31).getNcs()%>"<%}%>/>
					</td>
					<td align="left">实收资本（股本）
					<input name="mc2" type="hidden" value="实收资本（股本）"/>
					</td>
					<td align="center">78
					<input name="hc2" type="hidden" value="78"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(31).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(31).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△生产性生物资产
					<input name="mc1" type="hidden" value="△生产性生物资产"/>
					</td>
					<td align="center">33
					<input name="hc1" type="hidden" value="33"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(32).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(32).getNcs()%>"<%}%>/>
					</td>
					<td align="left">国家资本
					<input name="mc2" type="hidden" value="国家资本"/>
					</td>
					<td align="center">79
					<input name="hc2" type="hidden" value="79"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(32).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(32).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△油气资产
					<input name="mc1" type="hidden" value="△油气资产"/>
					</td>
					<td align="center">34
					<input name="hc1" type="hidden" value="34"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(33).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(33).getNcs()%>"<%}%>/>
					</td>
					<td align="left">集体资本
					<input name="mc2" type="hidden" value="集体资本"/>
					</td>
					<td align="center">80
					<input name="hc2" type="hidden" value="80"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(33).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(33).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">无形资产
					<input name="mc1" type="hidden" value="无形资产"/>
					</td>
					<td align="center">35
					<input name="hc1" type="hidden" value="35"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(34).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(34).getNcs()%>"<%}%>/>
					</td>
					<td align="left">法人资本
					<input name="mc2" type="hidden" value="法人资本"/>
					</td>
					<td align="center">81
					<input name="hc2" type="hidden" value="81"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(34).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(34).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其中：土地使用权
					<input name="mc1" type="hidden" value="其中：土地使用权"/>
					</td>
					<td align="center">36
					<input name="hc1" type="hidden" value="36"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(35).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(35).getNcs()%>"<%}%>/>
					</td>
					<td align="left">其中：国有法人资本
					<input name="mc2" type="hidden" value="其中：国有法人资本"/>
					</td>
					<td align="center">81
					<input name="hc2" type="hidden" value="81"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(35).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(35).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△开发支出
					<input name="mc1" type="hidden" value="△开发支出"/>
					</td>
					<td align="center">37
					<input name="hc1" type="hidden" value="37"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(36).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(36).getNcs()%>"<%}%>/>
					</td>
					<td align="left">集体法人资本
					<input name="mc2" type="hidden" value="集体法人资本"/>
					</td>
					<td align="center">83
					<input name="hc2" type="hidden" value="83"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(36).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(36).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△商誉
					<input name="mc1" type="hidden" value="△商誉"/>
					</td>
					<td align="center">38
					<input name="hc1" type="hidden" value="38"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(37).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(37).getNcs()%>"<%}%>/>
					</td>
					<td align="left">个人资本
					<input name="mc2" type="hidden" value="个人资本"/>
					</td>
					<td align="center">84
					<input name="hc2" type="hidden" value="84"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(37).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(37).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">＃*合并价差
					<input name="mc1" type="hidden" value="＃*合并价差"/>
					</td>
					<td align="center">39
					<input name="hc1" type="hidden" value="39"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(38).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(38).getNcs()%>"<%}%>/>
					</td>
					<td align="left">外商资本
					<input name="mc2" type="hidden" value="外商资本"/>
					</td>
					<td align="center">85
					<input name="hc2" type="hidden" value="85"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(38).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(38).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">长期待摊费用（递延资产）
					<input name="mc1" type="hidden" value="长期待摊费用（递延资产）"/>
					</td>
					<td align="center">40
					<input name="hc1" type="hidden" value="40"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(39).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(39).getNcs()%>"<%}%>/>
					</td>
					<td align="left">资本公积
					<input name="mc2" type="hidden" value="资本公积"/>
					</td>
					<td align="center">86
					<input name="hc2" type="hidden" value="86"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(39).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(39).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">△递延所得税资产
					<input name="mc1" type="hidden" value="△递延所得税资产"/>
					</td>
					<td align="center">41
					<input name="hc1" type="hidden" value="41"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(40).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(40).getNcs()%>"<%}%>/>
					</td>
					<td align="left">减：库存股
					<input name="mc2" type="hidden" value="减：库存股"/>
					</td>
					<td align="center">87
					<input name="hc2" type="hidden" value="87"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(40).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(40).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">＃递延税款借项
					<input name="mc1" type="hidden" value="＃递延税款借项"/>
					</td>
					<td align="center">42
					<input name="hc1" type="hidden" value="42"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(41).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(41).getNcs()%>"<%}%>/>
					</td>
					<td align="left">盈余公积
					<input name="mc2" type="hidden" value="盈余公积"/>
					</td>
					<td align="center">88
					<input name="hc2" type="hidden" value="88"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(41).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(41).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其他非流动资产（其他长期资产）
					<input name="mc1" type="hidden" value="其他非流动资产（其他长期资产）"/>
					</td>
					<td align="center">43
					<input name="hc1" type="hidden" value="43"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(42).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(42).getNcs()%>"<%}%>/>
					</td>
					<td align="left">△一般风险准备
					<input name="mc2" type="hidden" value="△一般风险准备"/>
					</td>
					<td align="center">89
					<input name="hc2" type="hidden" value="89"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(42).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(42).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">其中：特准储备物资
					<input name="mc1" type="hidden" value="其中：特准储备物资"/>
					</td>
					<td align="center">44
					<input name="hc1" type="hidden" value="44"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(43).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(43).getNcs()%>"<%}%>/>
					</td>
					<td align="left">＃未确认投资损失（已“-”号填列）
					<input name="mc2" type="hidden" value="＃未确认投资损失（已“-”号填列）"/>
					</td>
					<td align="center">90
					<input name="hc2" type="hidden" value="90"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(43).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(43).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">非流动资产合计
					<input name="mc1" type="hidden" value="非流动资产合计"/>
					</td>
					<td align="center">45
					<input name="hc1" type="hidden" value="45"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(44).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(44).getNcs()%>"<%}%>/>
					</td>
					<td align="left">未分配利润
					<input name="mc2" type="hidden" value="未分配利润"/>
					</td>
					<td align="center">91
					<input name="hc2" type="hidden" value="91"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(44).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(44).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">其中：现金股利
					<input name="mc2" type="hidden" value="其中：现金股利"/>
					</td>
					<td align="center">92
					<input name="hc2" type="hidden" value="92"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(45).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(45).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">*外币报表折算差额
					<input name="mc2" type="hidden" value="*外币报表折算差额"/>
					</td>
					<td align="center">93
					<input name="hc2" type="hidden" value="93"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(46).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(46).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">归属于母公司所有者权益合计
					<input name="mc2" type="hidden" value="归属于母公司所有者权益合计"/>
					</td>
					<td align="center">94
					<input name="hc2" type="hidden" value="94"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(47).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(47).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">*少数股东权益
					<input name="mc2" type="hidden" value="*少数股东权益"/>
					</td>
					<td align="center">95
					<input name="hc2" type="hidden" value="95"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(48).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(48).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">所有者权益合计
					<input name="mc2" type="hidden" value="所有者权益合计"/>
					</td>
					<td align="center">96
					<input name="hc2" type="hidden" value="96"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(49).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(49).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">＃减：资产损失
					<input name="mc2" type="hidden" value="＃减：资产损失"/>
					</td>
					<td align="center">97
					<input name="hc2" type="hidden" value="97"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(50).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(50).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
					<input name="mc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="hc1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="qms1" type="hidden" value=""/>
					</td>
					<td align="center">
					<input name="ncs1" type="hidden" value=""/>
					</td>
					<td align="left">所有者权益合计（剔除资产损失后的金额）
					<input name="mc2" type="hidden" value="所有者权益合计（剔除资产损失后的金额）"/>
					</td>
					<td align="center">98
					<input name="hc2" type="hidden" value="98"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(51).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(51).getNcs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">资产合计
					<input name="mc1" type="hidden" value="资产合计"/>
					</td>
					<td align="center">46
					<input name="hc1" type="hidden" value="46"/>
					</td>
					<td align="center">
					<input name="qms1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(52).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs1" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbzcList.size()!=0){%>value="<%=CwbbZcfzbzcList.get(52).getNcs()%>"<%}%>/>
					</td>
					<td align="left">负债和所有者权益总计
					<input name="mc2" type="hidden" value="负债和所有者权益总计"/>
					</td>
					<td align="center">99
					<input name="hc2" type="hidden" value="99"/>
					</td>
					<td align="center">
					<input name="qms2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(52).getQms()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="ncs2" type="text" size="15" readonly="readonly" onblur="this.value=addCommas(this.value)" <%if(CwbbZcfzbfzList.size()!=0){%>value="<%=CwbbZcfzbfzList.get(52).getNcs()%>"<%}%>/>
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
