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
	String phone = UserInfo.getPhone();
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>���ͳ�Ʊ�</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
		function getExcel(){
			if(confirm("ȷ������������Excelô��")){
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
			//����ԭҳ��
			var bdhtml=window.document.body.innerHTML;  
			//���ô�ӡҳ��
			var sprnstr="<!--startprint"+opr+"-->"; 
			var eprnstr="<!--endprint"+opr+"-->"; 
			var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); 
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			pagesetup_null();
			prnhtml = prnhtml.replace(/"800px"/g,"100%");
			window.document.body.innerHTML=prnhtml; 
			//��ӡ
			window.print();
			//��ԭ  
			window.document.body.innerHTML=bdhtml; 
		}
		//������ҳ��ӡ��ҳüҳ��Ϊ��
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
			//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
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
					<%if(!"".equals(sjbbAll.getSj())&&sjbbAll.getSj()!=null){%><%=sjbbAll.getSj().toString().substring(0,4)%>��<%}%>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
					���λ��<%=sjbbAll.getCzrdw()%>
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
						��λ�����ˣ�<%=sjbbAll.getDwfzr()%>
					</td>
					<td width="15%">
					&nbsp;
					</td>
					<td align="left" width="35%">
						��Ƹ����ˣ�<%=sjbbAll.getTjfzr()%>
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
						����ˣ�<%=sjbbAll.getCzr()%>
					</td>
					<td>
					&nbsp;
					</td>
					<td align="left">
						������ڣ�<%=sjbbAll.getCzsj().toString().substring(0,10)%>
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
						��ϵ�绰��<%=sjbbAll.getCzrphone()%>
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
					������ͳ�Ʊ���
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">һ���ڲ���ƻ���
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�רְ����
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�����ڲ������Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�רְ��Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">������������Ŀ
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�������֧���
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����������
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����������
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ч�����
					</td>
					<td align="center">��
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ڲ���������
					</td>
					<td align="center">��
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ϣϵͳ���
					</td>
					<td align="center">��
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר����ƣ����飩
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ġ�����ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�塢���������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�����ٽ����ս�֧���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ߡ��ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ˡ�����������������
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�š�����˾�����ͼ�����ش���������������
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ������˾�����ͼ�����ش�����Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZb23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮһ��ʵ�ʸ�����������
					</td>
					<td align="center">��
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					������֧��Ʊ���
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">һ����Ƶ�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��������ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�������Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�Υ��ı��ʽ���;
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�鱨ð��ƭȡ�ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ʲ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;δ���涨���ա����ɲ�������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���棨��֧����ʵ
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ġ������ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�塢��������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�����ٽ����ս�֧���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ߡ��´���ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ˡ���ʵ��ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�š������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ��������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮһ���ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ��������Ԫ����Υ�͵�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbs17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ������Ԫ����̰�ۻ�¸����
					</td>
					<td align="center">��
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
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					����������Ʊ���
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">һ�������Ŀ
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У������������
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ר�����
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��������ܽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У������������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ר�����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�����˼�Ͷ�ʣ����㣩��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ġ����Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�����ģ������׼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ռŲ�ý����ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ʽ���ʵ
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����(���У����̳ɱ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;©��˰��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ֺ�Ⱥ����������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�塢�����ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">������������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ߡ�Ӧ�黹ԭ�����ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У��ѹ黹�ʽ�
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ˡ��ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�š��´���ƾ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ����ʵ��ƾ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮһ�������ƽ�������
					</td>
					<td align="center">��
					</td>
					<td align="center">24
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbt24()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ����������ƽ�������
					</td>
					<td align="center">��
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					����������Ʊ���
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" height="30px">��
					</td>
					<td align="center">��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">һ�� ��ƾ���������
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�������λ
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">������Ƶ�λ
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�������λ
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�������Υ����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ġ������ʧ�˷ѽ��
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�塢��������淶���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�ֱ������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�쵼����
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">20
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf20()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�����쵼�ɲ��漰���˾�������
					</td>
					<td align="center">��
					</td>
					<td align="center">21
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf21()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У�����
					</td>
					<td align="center">��
					</td>
					<td align="center">22
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf22()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�漰���
					</td>
					<td align="center">��Ԫ
					</td>
					<td align="center">23
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf23()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ߡ���Ա������
					</td>
					<td align="center">��
					</td>
					<td align="center">24
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf24()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;���У���ְ������
					</td>
					<td align="center">��
					</td>
					<td align="center">25
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf25()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������
					</td>
					<td align="center">��
					</td>
					<td align="center">26
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf26()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����˾������
					</td>
					<td align="center">��
					</td>
					<td align="center">27
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf27()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ͼͼ������
					</td>
					<td align="center">��
					</td>
					<td align="center">28
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf28()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�ˡ��´���ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">29
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf29()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�š���ʵ��ƾ���
					</td>
					<td align="center">��
					</td>
					<td align="center">30
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf30()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ�������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">31
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf31()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮһ��������ƽ���
					</td>
					<td align="center">��
					</td>
					<td align="center">32
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbf32()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">ʮ�����ٽ����ƹ����ƶ�
					</td>
					<td align="center">��
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
			
			<table width="800px" align="center">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 25px">
					��ƻ�����Ա����
					</td>
				</tr>
			</table>
			<table id="table2" width="800px" align="center" border=1  cellpadding=0 cellspacing=0 style="">
				
				<tr>
					<td align="center" width="40%" height="30px" colspan="2">ָ������
					</td>
					<td align="center" width="15%">���㵥λ
					</td>
					<td align="center" width="15%">����
					</td>
					<td align="center" width="30%">��ֵ
					</td>
				</tr>
				<tr>
					<td align="center" height="30px" colspan="2">��
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
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">1
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv1()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">2
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv2()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">3
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv3()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�Ƽ�����
					</td>
					<td align="center">��
					</td>
					<td align="center">4
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv4()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="30px">������Ա����
					</td>
					<td align="center">��
					</td>
					<td align="center">5
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv5()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2" height="30px">�����䱸��Ա
					</td>
					<td align="center">��
					</td>
					<td align="center">6
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv6()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">��һ�����������ʷ�
					</td>
					<td align="left" height="30px">ר&nbsp;&nbsp;ְ
					</td>
					<td align="center">��
					</td>
					<td align="center">7
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv7()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��&nbsp;&nbsp;ְ
					</td>
					<td align="center">��
					</td>
					<td align="center">8
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv8()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="2">��������ѧ����
					</td>
					<td align="left" height="30px">��ר������
					</td>
					<td align="center">��
					</td>
					<td align="center">9
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv9()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��ר������
					</td>
					<td align="center">��
					</td>
					<td align="center">10
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv10()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">��������ְ�Ʒ�
					</td>
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">11
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv11()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">��
					</td>
					<td align="center">12
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv12()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">��������
					</td>
					<td align="center">��
					</td>
					<td align="center">13
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv13()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="3">���ģ��������
					</td>
					<td align="left" height="30px">45������
					</td>
					<td align="center">��
					</td>
					<td align="center">14
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv14()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">30��44��
					</td>
					<td align="center">��
					</td>
					<td align="center">15
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv15()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">29������
					</td>
					<td align="center">��
					</td>
					<td align="center">16
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv16()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" rowspan="4">�ġ���ѵ��Ա
					</td>
					<td align="left" height="30px">��&nbsp;&nbsp;��
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">17
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv17()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�԰���ѵ
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">18
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv18()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�μӲ�����ѵ
					</td>
					<td align="center">�˴�
					</td>
					<td align="center">19
					</td>
					<td align="center">
					&nbsp;<%=sjbbAll.getZbv19()%>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" height="30px">�μ���ϵͳ����λ��ѵ
					</td>
					<td align="center">�˴�
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
			
			<!--endprint1-->
			<table  width="800px" align="center">
				<tr>
					<td align="center">
				<img alt="��ӡ" width="45px" height="45px" title="��ӡ" style="cursor: pointer;"  src="images/small/printer.png" onclick="printbody(1);" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����EXCEL" width="45px" height="45px" title="����EXCEL" style="cursor: pointer;"  src="images/small/office-ms-excel.png" onclick="getExcel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="�ر�" width="45px" height="45px" title="�ر�" style="cursor: pointer;"  src="images/small/close.png" onclick="cancel()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					</td>
				</tr>
			</table>
	<%}%>
	</body>
</html>



