<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbLrb"%>
<%@page import="com.safety.entity.CwbbLrbsj"%>
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
    <title>新增修改利润表（站务公司）</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
			if(str=="1"){
        		verification();
			}
	   		var sbsj = document.getElementById("sbsj").value;
	   		if(sbsj==""){
	   			alert("请输入日期");
	   			return;
	   		}
	   		var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=LrbSave";
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
        	var bys = document.getElementsByName("bys");
        	var bnljs = document.getElementsByName("bnljs");
        	var sntqs = document.getElementsByName("sntqs");
         	for(var i=0;i<bys.length;i++){
         		bys[i].value = bys[i].value.replace(/,/g,"");//删除所有逗号;
         		bnljs[i].value = bnljs[i].value.replace(/,/g,"");//删除所有逗号;
         		sntqs[i].value = sntqs[i].value.replace(/,/g,"");//删除所有逗号;
         		//空项全部置零
	        	if(bys[i].value=="")bys[i].value=0;
	        	if(bnljs[i].value=="")bnljs[i].value=0;
	        	if(sntqs[i].value=="")sntqs[i].value=0;
        	}
        	//本月数
        	bys[3].value = (Number(bys[0].value)-Number(bys[1].value)-Number(bys[2].value)).toFixed(2);
        	bys[8].value = (Number(bys[3].value)+Number(bys[4].value)-Number(bys[5].value)-Number(bys[6].value)-Number(bys[7].value)).toFixed(2);
        	bys[13].value = (Number(bys[8].value)+Number(bys[9].value)+Number(bys[10].value)+Number(bys[11].value)-Number(bys[12].value)).toFixed(2);
        	bys[15].value = (Number(bys[13].value)-Number(bys[14].value)).toFixed(2);
        	//本年累计数
        	bnljs[3].value = (Number(bnljs[0].value)-Number(bnljs[1].value)-Number(bnljs[2].value)).toFixed(2);
        	bnljs[8].value = (Number(bnljs[3].value)+Number(bnljs[4].value)-Number(bnljs[5].value)-Number(bnljs[6].value)-Number(bnljs[7].value)).toFixed(2);
        	bnljs[13].value = (Number(bnljs[8].value)+Number(bnljs[9].value)+Number(bnljs[10].value)+Number(bnljs[11].value)-Number(bnljs[12].value)).toFixed(2);
        	bnljs[15].value = (Number(bnljs[13].value)-Number(bnljs[14].value)).toFixed(2);
        	//加逗号
        	for(var j=0;j<bys.length;j++){
         		bys[j].value = addCommas2(bys[j].value);
         		bnljs[j].value = addCommas2(bnljs[j].value);
         		sntqs[j].value = addCommas2(sntqs[j].value);
        	}
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbCwbbServlet?action=getLrb&flag=1&menuname="+menuname;
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
		    var path = "tempFile/lrb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readLrbZWGSExcel&URL="+msg;
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
         	CwbbLrb cwbbLrb = (CwbbLrb)request.getAttribute("cwbbLrb");
    		ArrayList<CwbbLrbsj> CwbbLrbsjList = (ArrayList)request.getAttribute("CwbbLrbsjList");
    		
		%>
		<form id="form1" name="CwbbLrb" method="post">
		<input type="hidden" name="cwbbLrb_id" id="cwbbLrb_id" <%if(cwbbLrb.getId()!=0){%>value="<%=cwbbLrb.getId()%>"<%}%> >
		<input type="hidden" name="hzbz" id="hzbz" value="ZWGS" >
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" colspan="3" style="font-weight: bold;">
					<input name="bt" type="text" size="20" <%if(cwbbLrb.getBt()!=null){%>value="<%=cwbbLrb.getBt()%>"<%}else{%>value="损益表"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left" width="30%">
						填报单位：<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
					<td align="center" width="30%">
						时间：<input name="sbsj" id="sbsj" type="text" size="10" <%if(cwbbLrb.getSbsj()!=null){%>value="<%=cwbbLrb.getSbsj()%>"<%}%> readonly/>
						<input name="Button" class="button1" onclick="setDay(document.CwbbLrb.sbsj);" type="button" value="选择">
					</td>
					<td align="right" width="30%">
						单位：元
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="50%">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目
					</td>
					<td align="center" width="5%">行次
					</td>
					<td align="center" width="22%">本月数
					</td>
					<td align="center" width="23%">本年累计数
					</td>
				</tr>
				<tr>
					<td align="left">一、主营业务收入
					</td>
					<td align="center">1
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(0).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(0).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;减：主营业务成本
					</td>
					<td align="center">2
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(1).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(1).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主营业务税金及附加
					</td>
					<td align="center">3
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(2).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(2).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">二、主营业务利润（亏损以“-”号填列）
					</td>
					<td align="center">4
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)" readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(3).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(3).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;加：其他业务利润（亏损以“-”号填列）
					</td>
					<td align="center">5
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(4).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(4).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;减：营业费用
					</td>
					<td align="center">6
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(5).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(5).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理费用
					</td>
					<td align="center">7
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(6).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(6).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;财务费用
					</td>
					<td align="center">8
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(7).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(7).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">三、营业利润（亏损以“-”号填列）
					</td>
					<td align="center">9
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(8).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(8).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;加：投资收益（损失以“-”号填列）
					</td>
					<td align="center">10
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(9).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(9).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;补贴收入
					</td>
					<td align="center">11
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(10).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(10).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业外收入
					</td>
					<td align="center">12
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(11).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(11).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;减：营业外支出
					</td>
					<td align="center">13
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(12).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(12).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">四、利润总额（亏损总额以“-”号填列）
					</td>
					<td align="center">14
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(13).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(13).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;减：所得税
					</td>
					<td align="center">15
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(14).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(14).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td align="left">五、净利润（净亏损以“-”号填列）
					</td>
					<td align="center">16
					</td>
					<td align="center">
					<input name="bys" type="text" size="15" onblur="this.value=addCommas(this.value)"  readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(15).getBys()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" readonly="readonly" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(15).getBnljs()%>"<%}%>/>
					<input name="sntqs" type="hidden"/>
					</td>
				</tr>
			</table>
			<br/>
			补充资料：
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="50%">项目：
					</td>
					<td align="center" width="27%">本年累计数
					</td>
					<td align="center" width="23%">上年实际数
					</td>
				</tr>
				<tr>
					<td align="left">1、出售、处置部门或被投资单位所得收益
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(16).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(16).getSntqs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">2、自然灾害发生的损失
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(17).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(17).getSntqs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">3、会计政策变更增加（或减少）利润总额
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(18).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(18).getSntqs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">4、会计估计变更增加（或减少）利润总额
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(19).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(19).getSntqs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">5、债务重组损失
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(20).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(20).getSntqs()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">6、其他
					</td>
					<td align="center">
					<input name="bys" type="hidden"/>
					<input name="bnljs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(21).getBnljs()%>"<%}%>/>
					</td>
					<td align="center">
					<input name="sntqs" type="text" size="15" onblur="this.value=addCommas(this.value)" <%if(CwbbLrbsjList.size()!=0){%>value="<%=CwbbLrbsjList.get(21).getSntqs()%>"<%}%>/>
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



