<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CglibBean"%>
<%@page import="com.safety.entity.Bbdz"%>
<%@page import="com.safety.entity.BbdzmbHb"%>
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
    <title>模板具体内容</title>
   <style type="text/css">
   img {border:0px;}
   </style>
    <script type="text/javascript">
	//导出Excel
	function getExcel(){
		if(confirm("确定导出下列表单的Excel么？")){
			var form = document.getElementById("form1");
			form.action = "BbdzServlet?action=getBbdzExcel";
			form.submit();
		} 
	}
	function cancel(){
		window.open('','_self');
		window.close();
	}
	function printbody(opr){
		//保存原页面
		var bdhtml=window.document.body.innerHTML;  
		//设置打印页面
		var sprnstr="<!--startprint"+opr+"-->"; 
		var eprnstr="<!--endprint"+opr+"-->"; 
		var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
		var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		pagesetup_null();
		prnhtml = prnhtml.replace(/"95%"/g,"100%");
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
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
   			ArrayList BbdzmbHbList = (ArrayList)request.getAttribute("BbdzmbHbList");
			ArrayList BbdzDTList = (ArrayList)request.getAttribute("BbdzDTList");
   			Bbdz bbdz = (Bbdz)request.getAttribute("bbdz");
			String lx = (String)request.getAttribute("lx");
		%>
		<form id="form1" method="post" action="">
		<input type="hidden" name="exc_id" id="exc_id" value="<%=bbdz.getId() %>">
		<input type="hidden" name="zbid" id="zbid" value="<%=bbdz.getZbid() %>">
		<input type="hidden" name="lx" id="lx" value="<%=lx %>">
		</form>
		<!--startprint1-->
		<table width="95%" align="center">
			<tr>
				<td align="center">
				<span style="font-size: 25px"><%=bbdz.getSj() %>&nbsp;<%=bbdz.getBt() %></span>
				</td>
			</tr>
		</table>
		<table id="table2"  width="95%" align="center" border=1  cellpadding=0 cellspacing=0 style="">
			<%
				//新建一个map,用于存储无用的td位置
				HashMap<String, String> hashmap = new HashMap<String, String>(); 
				for(int i=0;i< BbdzDTList.size();i++){
					CglibBean bean = (CglibBean)BbdzDTList.get(i);
			%>
			<tr>
				<%
				int num = bbdz.getBbls();
				for(int j=0;j<num;j++){
					int p=j+1;
					//通过map,判断是否去掉该td
					if("Yes".equals(hashmap.get(i+","+j))){
					//System.out.println(i+","+j);
					}else{
				%>
				<td align="center" 
				<%
					for(int k=0;k<BbdzmbHbList.size();k++){
						BbdzmbHb bbdzmbHb = (BbdzmbHb)BbdzmbHbList.get(k);
						if(bbdzmbHb.getRow1()==i+1&&bbdzmbHb.getColumn1()==j+1){
							if("r".equals(bbdzmbHb.getHl())){
								%>
								rowspan="<%=bbdzmbHb.getHs()%>" 
								<%
								//System.out.println(i+","+j);
								//下面几行中的部分td去掉
								for(int q=1;q<bbdzmbHb.getHs();q++){
									hashmap.put(i+q+","+j,"Yes");//（键：行,列;值：true.）
								}
							}
							if("c".equals(bbdzmbHb.getHl())){
								%>
								colspan="<%=bbdzmbHb.getLs()%>" 
								<%
								j = j+bbdzmbHb.getLs()-1;
								//System.out.println(j);
							}
							if("rc".equals(bbdzmbHb.getHl())){
								//System.out.println(i+","+j);
								//下面几行中的部分td去掉
								for(int m=1;m<bbdzmbHb.getHs();m++){
									for(int n=0;n<bbdzmbHb.getLs();n++){
										hashmap.put((i+m)+","+(j+n),"Yes");//（键：行,列;值：Yes.）
										//System.out.println(z+","+x);
									}
								}
								%>
								rowspan="<%=bbdzmbHb.getHs()%>" 
								colspan="<%=bbdzmbHb.getLs()%>" 
								<%
								j = j+bbdzmbHb.getLs()-1;
								//System.out.println(j);
							}
						}
					}
				%>
				>
				<%if(bean.getValue("zb"+p)!=null&&!"".equals(bean.getValue("zb"+p))){%><%=bean.getValue("zb"+p)%><%}else{%>&nbsp;<%}%>
				</td>
				<%}%>
				<%}%>
			</tr>
			<%} %>
		</table>
			<!--endprint1-->
		<table  width="100%">
			<tr>
				<td align="center">
				<img alt="打印" width="45px" height="45px" title="打印" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="导出EXCEL" width="45px" height="45px" title="导出EXCEL" style="cursor: pointer;"  src="images/small/office-ms-excel.png" onclick="getExcel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="关闭" width="45px" height="45px" title="关闭" style="cursor: pointer;"  src="images/small/close.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</body>
	<%} %>
</html>



