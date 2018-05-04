<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.ZdxmsbZczbbg"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='../../login.jsp';</script>");
	else{
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>重大项目申报——注册资本变更——具体内容</title>
   <style type="text/css">
   img {border:0px;}
   </style>
    <script type="text/javascript">
	function cancel(){
		window.open('','_self');
		window.close();
	}
	function saveSH(){
		var form = document.getElementById("form1");
		form.action = "ZdxmsbServlet?action=ZdxmsbSH";
		form.submit();
	}
	function printbody(opr){
		//保存原页面
		var bdhtml=window.document.body.innerHTML;  
		var htmlNoPrint1 =  document.getElementById("NoPrint1").innerHTML;
		//设置打印页面
		var sprnstr="<!--startprint"+opr+"-->"; 
		var eprnstr="<!--endprint"+opr+"-->"; 
		pagesetup_null();
		var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
		var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		prnhtml = prnhtml.replace(/"65%"/g,"100%");
		prnhtml = prnhtml.replace(htmlNoPrint1,"单位负责人：");
		window.document.body.innerHTML=prnhtml; 
		//打印
		window.print();
		//还原  
		window.document.body.innerHTML=bdhtml; 
	}
	//设置网页打印的页眉页脚为空
	function pagesetup_null() {
		var hkey_root, hkey_path, hkey_key;
		hkey_root = "HKEY_CURRENT_USER"
		hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		try {
			var RegWsh = new ActiveXObject("WScript.Shell");
			hkey_key = "header";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
			hkey_key = "footer";
			RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		} catch (e) {
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
         	ZdxmsbZczbbg zdxmsbZczbbg = (ZdxmsbZczbbg)request.getAttribute("zdxmsbZczbbg");
         	String flag = (String)request.getAttribute("flag");
		%>
		<!--startprint1-->
		<table width="65%" align="center">
			<tr>
				<td style="font-size: 25px;text-align: center;">
				南通市交通企事业单位重大财务事项报告单
				</td>
			</tr>
			<tr>
				<td style="font-size: 25px;text-align: center;">
				——注册资本变更
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<tr>
				<td>
				一、注册资本变更概况
				</td>
			</tr>
			<tr>
				<td height="" valign="top">
					<table  width="100%" border=1 cellpadding=0 cellspacing=0>
						<tr>
							<td rowspan="2" align="center">
								原注册资本<br>（万元）
							</td>
							<td colspan="2" align="center">
								变更情况
							</td>
							<td rowspan="2" align="center">
								变更后注册资本<br>（万元）
							</td>
						</tr>
						<tr>
							<td align="center">
								扩资<br>（万元）
							</td>
							<td align="center">
								减资<br>（万元）
							</td>
						</tr>
						<tr>
							<td align="center" height="50px">
								&nbsp;<%=zdxmsbZczbbg.getZb()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getKz()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getJz()%>&nbsp;
							</td>
							<td align="center">
								&nbsp;<%=zdxmsbZczbbg.getBgzb()%>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
				二、注册资本构成情况
				</td>
			</tr>
			<tr>
				<td>
				（一）原注册资本构成情况
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getYqk() %>
				</td>
			</tr>
			<tr>
				<td>
				（二）变更后注册资本构成情况
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getBgqk() %>
				</td>
			</tr>
			<tr>
				<td>
				三、注册资本变更的依据
				</td>
			</tr>
			<tr>
				<td height="100px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getBgyj() %>
				</td>
			</tr>
			<tr>
				<td>
				四、扩资的资金来源构成情况
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getGcqk() %>
				</td>
			</tr>
			<tr>
				<td>
				五、减资资本的处置方案及结果
				</td>
			</tr>
			<tr>
				<td height="80px" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=zdxmsbZczbbg.getFajg() %>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				报告单位： <%if(zdxmsbZczbbg.getCzrdw()!=null){%><%=zdxmsbZczbbg.getCzrdw() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				填报人： <%if(zdxmsbZczbbg.getCzr()!=null){%><%=zdxmsbZczbbg.getCzr() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				报告时间： <%if(zdxmsbZczbbg.getCzsj()!=null){%><%=zdxmsbZczbbg.getCzsj().toString().substring(0,4) %>年<%=zdxmsbZczbbg.getCzsj().toString().substring(5,7) %>月<%=zdxmsbZczbbg.getCzsj().toString().substring(8,10) %>日<%} else{%>20&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日<%}%>
				</td>
			</tr>
			<tr>
				<td height="50px" valign="middle">
				<span id="NoPrint1">
				附&nbsp;&nbsp;件：
				  	<% if(!"".equals(zdxmsbZczbbg.getSmj())&&zdxmsbZczbbg.getSmj()!=null){
				  		String[] fileStr = zdxmsbZczbbg.getSmj().split(";");
				  		for(int i=0; i<fileStr.length; i++){
				  	%>
				  		<br>
						<a onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';" href="ZdxmsbServlet?action=downLoad&URL=<%=fileStr[i]%>" target="ifm"><%=fileStr[i]%></a>
						<%}%>
					<%}else{%>无
					<%}%>
				</span><iframe id='ifm' name='ifm' style="display:none"></iframe>
				</td>
			</tr>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
			<%if("2".equals(flag)){%>
			<tr>
				<td height="50px" valign="middle" align="center">
				<form method="post" id="form1">
				市局审批意见：
				<input type="radio" name="shyj" value="同意" checked="checked">同意
				<input type="radio" name="shyj" value="驳回">驳回
				<input type="hidden" name="nrid" id="nrid" value="<%=zdxmsbZczbbg.getId()%>">
				<input type="hidden" name="lx" id="lx" value="1">
				<input type="hidden" name="bhry" id="bhry" value="<%=zdxmsbZczbbg.getCzrID()%>">
				</form>
				</td>
			</tr>
			<tr>
				<td align="center">
				<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/save-as.png" onclick="saveSH();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="top.mainFrame.history.go(-1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
			<%} else{%>
			<tr>
				<td height="50px" valign="middle">
				市局审批意见：<%if(zdxmsbZczbbg.getShyj()!=null){%><%=zdxmsbZczbbg.getShyj() %><%} else{%>&nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				审批人：<%=zdxmsbZczbbg.getShr() %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				审批时间：<%if(zdxmsbZczbbg.getShsj()!=null){%><%=zdxmsbZczbbg.getShsj().toString().substring(0,4) %>年<%=zdxmsbZczbbg.getShsj().toString().substring(5,7) %>月<%=zdxmsbZczbbg.getShsj().toString().substring(8,10) %>日<%} else{%>20&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日<%}%>
				</td>
			</tr>
			<%}%>
			<tr height="1px">
				<td>
					<hr style="height: 1px; border:black; border-top:1px solid #5CACEE;" /> 
				</td>
			</tr>
		</table>
		<!--endprint1-->
		<%if("1".equals(flag)){%>
		<table  width="100%">
			<tr>
				<td align="center">
					<img alt="打印" width="45px" height="45px" title="打印" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="返回" width="45px" height="45px" title="返回" style="cursor: pointer;"  src="images/small/undo.png" onclick="cancel();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
		<%}%>
	</body>
	<%} %>
</html>



