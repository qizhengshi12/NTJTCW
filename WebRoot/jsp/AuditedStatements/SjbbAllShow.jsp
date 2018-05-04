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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>审计统计表</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("确定导出本表单的Excel么？")){
				var form = document.getElementById("form1");
				form.action = "BbsbSjbbServlet?action=getSjbbAllExcel";
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
			prnhtml = prnhtml.replace(/"800px"/g,"100%");
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
		<form id="form1" method="post">
		<input type="hidden" name="SjbbAll_id" id="SjbbAll_id" <%if(sjbbAll.getId()!=0){%>value="<%=sjbbAll.getId()%>"<%}%> >
		</form>
		<!--startprint1-->
			<table width="800px" align="center">
				<tr>
					<td align="center" colspan="4" style="font-weight: bold;font-size: 30px">
					<%=sjbbAll.getBt()%>
					</td>
				</tr>
				<tr >
					<td colspan="4">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
					<%if(!"".equals(sjbbAll.getSj())&&sjbbAll.getSj()!=null){%><%=sjbbAll.getSj().toString().substring(0,4)%>年<%}%>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
					填报单位：<%=sjbbAll.getCzrdw()%>
					</td>
				</tr>
				<tr >
					<td colspan="4">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td width="15%">
					&nbsp;
					</td>
					<td align="left" width="35%">
						单位负责人：<%=sjbbAll.getDwfzr()%>
					</td>
					<td width="15%">
					&nbsp;
					</td>
					<td align="left" width="35%">
						审计负责人：<%=sjbbAll.getTjfzr()%>
					</td>
				</tr>
				<tr >
					<td colspan="4">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;
					</td>
					<td align="left">
						填表人：<%=sjbbAll.getCzr()%>
					</td>
					<td>
					&nbsp;
					</td>
					<td align="left">
						填表日期：<%=sjbbAll.getCzsj().toString().substring(0,10)%>
					</td>
				</tr>
				<tr>
					<td>
					&nbsp;
					</td>
					<td>
					&nbsp;
					</td>
					<td>
					&nbsp;
					</td>
					<td align="left">
						联系电话：<%=sjbbAll.getCzrphone()%>
					</td>
				</tr>
				<tr >
					<td colspan="4">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					审计情况统计报表
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">一、内部审计机构
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：专职机构
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">二、内部审计人员
					</td>
					<td align="center">人
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：专职人员
					</td>
					<td align="center">人
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">三、完成审计项目
					</td>
					<td align="center">个
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：财务收支审计
					</td>
					<td align="center">个
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基本建设审计
					</td>
					<td align="center">个
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经济责任审计
					</td>
					<td align="center">个
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;效益审计
					</td>
					<td align="center">个
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内部控制评审
					</td>
					<td align="center">个
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信息系统审计
					</td>
					<td align="center">个
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专项审计（调查）
					</td>
					<td align="center">个
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他
					</td>
					<td align="center">个
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">四、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">五、查出问题金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">六、促进增收节支金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">七、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">八、提出建议意见被采纳
					</td>
					<td align="center">条
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">九、移送司法、纪检监察机关处理线索（案件）
					</td>
					<td align="center">件
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十、移送司法、纪检监察机关处理人员
					</td>
					<td align="center">人
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十一、实际给予行政处分
					</td>
					<td align="center">人
					</td>
					<td align="center">24
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb24()%>&nbsp;
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					财务收支审计报表
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">一、审计单位
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">二、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">三、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：违规改变资金用途
					</td>
					<td align="center">万元
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;虚报冒领骗取资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账外资产
					</td>
					<td align="center">万元
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未按规定征收、缴纳财政收入
					</td>
					<td align="center">万元
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;损益（收支）不实
					</td>
					<td align="center">万元
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">四、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">五、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">六、促进增收节支金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">七、下达审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">八、落实审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">九、提出审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十、采纳审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十一、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十二、百万元以上违纪单位
					</td>
					<td align="center">个
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十三、万元以上贪污贿赂案件
					</td>
					<td align="center">件
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs18()%>&nbsp;
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
				<tr>
					<td align="left">
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
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					基本建设审计报表
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">一、审计项目
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：竣工决算审计
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪审计
					</td>
					<td align="center">个
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他专项审计
					</td>
					<td align="center">个
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">二、审计总金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：竣工决算审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他专项审计
					</td>
					<td align="center">万元
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">三、核减投资（结算）额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">四、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：超规模、超标准建设
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;挤占挪用建设资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建设资金不落实
					</td>
					<td align="center">万元
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;多列(少列）工程成本
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;漏交税费
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;侵害群众利益问题
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">五、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">六、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">七、应归还原渠道资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：已归还资金
					</td>
					<td align="center">万元
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">八、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">九、下达审计决定条数
					</td>
					<td align="center">条
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十、落实审计决定条数
					</td>
					<td align="center">条
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十一、提出审计建议条数
					</td>
					<td align="center">条
					</td>
					<td align="center">24
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt24()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十二、采纳审计建议条数
					</td>
					<td align="center">条
					</td>
					<td align="center">25
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt25()%>&nbsp;
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
				<tr>
					<td align="left">
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					经济责任审计报表
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">甲
					</td>
					<td align="center">乙
					</td>
					<td align="center">丙
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">一、 审计经济责任人
					</td>
					<td align="center">人
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：行政单位
					</td>
					<td align="center">人
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位
					</td>
					<td align="center">人
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业
					</td>
					<td align="center">人
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">二、审计单位
					</td>
					<td align="center">个
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：行政单位
					</td>
					<td align="center">个
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位
					</td>
					<td align="center">个
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业
					</td>
					<td align="center">个
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">三、查出违规金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">四、查出损失浪费金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">五、查出管理不规范金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：直接责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主管责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领导责任
					</td>
					<td align="center">万元
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">六、领导干部涉及个人经济问题
					</td>
					<td align="center">人
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：人数
					</td>
					<td align="center">人
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;涉及金额
					</td>
					<td align="center">万元
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">七、人员处理处分
					</td>
					<td align="center">人
					</td>
					<td align="center">24
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf24()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;其中：撤职、降级
					</td>
					<td align="center">人
					</td>
					<td align="center">25
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf25()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他处分
					</td>
					<td align="center">人
					</td>
					<td align="center">26
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf26()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移送司法机关
					</td>
					<td align="center">人
					</td>
					<td align="center">27
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf27()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移送纪检监察机关
					</td>
					<td align="center">人
					</td>
					<td align="center">28
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf28()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">八、下达审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">29
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf29()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">九、落实审计决定
					</td>
					<td align="center">条
					</td>
					<td align="center">30
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf30()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十、提出审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">31
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf31()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十一、采纳审计建议
					</td>
					<td align="center">条
					</td>
					<td align="center">32
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf32()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">十二、促进完善规章制度
					</td>
					<td align="center">条
					</td>
					<td align="center">33
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf33()%>&nbsp;
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
				<tr>
					<td align="left">
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					审计机构人员报表
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px" colspan="2">指标名称
					</td>
					<td align="center" width="15%">计算单位
					</td>
					<td align="center" width="15%">代码
					</td>
					<td align="center" width="30%">数值
					</td>
				</tr>
				<tr>
					<td align="center" height="30px" colspan="2">甲
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
					<td align="left" height="30px">合&nbsp;&nbsp;计
					</td>
					<td align="center">个
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">处&nbsp;&nbsp;级
					</td>
					<td align="center">个
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">科&nbsp;&nbsp;级
					</td>
					<td align="center">个
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">科级以下
					</td>
					<td align="center">个
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="30px">二、人员编制
					</td>
					<td align="center">人
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="30px">三、配备人员
					</td>
					<td align="center">人
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">（一）按工作性质分
					</td>
					<td align="left" height="30px">专&nbsp;&nbsp;职
					</td>
					<td align="center">人
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">兼&nbsp;&nbsp;职
					</td>
					<td align="center">人
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">（二）按学历分
					</td>
					<td align="left" height="30px">大专及以上
					</td>
					<td align="center">人
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">中专及以下
					</td>
					<td align="center">人
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">（三）按职称分
					</td>
					<td align="left" height="30px">高&nbsp;&nbsp;级
					</td>
					<td align="center">人
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">中&nbsp;&nbsp;级
					</td>
					<td align="center">人
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">初级以下
					</td>
					<td align="center">人
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">（四）按年龄分
					</td>
					<td align="left" height="30px">45岁以上
					</td>
					<td align="center">人
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">30至44岁
					</td>
					<td align="center">人
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">29岁以下
					</td>
					<td align="center">人
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">四、培训人员
					</td>
					<td align="left" height="30px">合&nbsp;&nbsp;计
					</td>
					<td align="center">人次
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">自办培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">参加部办培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">参加外系统、单位培训
					</td>
					<td align="center">人次
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv20()%>&nbsp;
					</td>
				</tr>
			</table>
			<table width="800px" align="center">
				<tr>
					<td align="left">
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
			
			<!--endprint1-->
			<table  width="800px" align="center">
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
	<%}%>
	</body>
</html>



