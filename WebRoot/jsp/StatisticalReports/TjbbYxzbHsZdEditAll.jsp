<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbHsZd"%>
<%@page import="com.safety.entity.TjbbYxzbHsZdZxm"%>
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
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�������¾�վ��</title>
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
	   		var form = document.getElementById("form1");
			form.action = "BbsbTjbbServlet?action=TjbbYxzbHsZdSaveAll";
			form.submit();
	    }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("menuname").value;
			form.action = "BbsbTjbbServlet?action=getTjbbYxzbHsZd&flag=1&menuname="+menuname;
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
		    var path = "tempFile/tjbbyxzbhszd";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
	        	var sheetNum1 = document.getElementById("sheetNum1").value;
	        	var sheetNum2 = document.getElementById("sheetNum2").value;
				form.action = "BbsbTjbbServlet?action=readTjbbYxzbHsZdExcelAll&URL="+msg+"&sheetNum1="+sheetNum1+"&sheetNum2="+sheetNum2;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
	       		return;
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
		<form id="form1" name="TjbbYxzbHsZd" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;">
					<select name="year" id="year">
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%}%>
					</select>
					&nbsp;1��&nbsp;
					<select name="month" id="month">
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>��</option>
						<%}%>
					</select>
					</td>
				</tr>
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						���λ��<%= company %>
						<input type="hidden" id="menuname" value="<%= company %>" >
					</td>
				</tr>
			</table>
			<%
			//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
		    //String permissions = (String)request.getAttribute("permissions"); 
		    int all = (Integer)request.getAttribute("all");
		    for(int k=1;k<=all;k++){
		    TjbbYxzbHsZd tjbbYxzbHsZd = (TjbbYxzbHsZd)request.getAttribute("tjbbYxzbHsZd"+k);
         	ArrayList TjbbYxzbHsZdZxmList = (ArrayList)request.getAttribute("TjbbYxzbHsZdZxmList"+k);
			%>
			<table width="100%">
				<tr>
					<td align="center">
					<input name="bt" type="text" size="40" <%if(tjbbYxzbHsZd.getBt()!=null){%>value="<%=tjbbYxzbHsZd.getBt()%>"<%}else{%>value="����վ��ǩ֤����"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="left">
						��ϵ�ˣ�<input name="lxr" id="lxr" type="text" size="10" <%if(tjbbYxzbHsZd.getLxr()!=null){%>value="<%=tjbbYxzbHsZd.getLxr()%>"<%}%>/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ϵ�绰��<input name="lxrdh" id="lxrdh" type="text" size="10" <%if(tjbbYxzbHsZd.getLxrdh()!=null){%>value="<%=tjbbYxzbHsZd.getLxrdh()%>"<%}%>/>
					</td>
				</tr>
			</table>
			<table width="1250px">
				<tr>
					<td>
						<table width="150px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="100px" height="100px" >վ������
								</td>
								<td align="center" width="50px">�·�
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
								<td align="center" width="500px" height="50px" colspan="5">����ǩ֤�������֣�
								</td>
								<td align="center" width="500px" colspan="5">����ǩ֤�������֣�
								</td>
								<td align="center" width="100px" rowspan="2">����ǩ֤�漰�ۿڷ�Χ
								</td>
							</tr>
							<tr>
								<td align="center" width="100px" height="50px">�ܼ�
								</td>
								<td align="center" width="100px">1000��(��)���´���
								</td>
								<td align="center" width="100px">1000��5000��(��)����
								</td>
								<td align="center" width="100px">5000��10000��(��)����
								</td>
								<td align="center" width="100px">10000�����ϴ���
								</td>
								<td align="center" width="100px">�ܼ�
								</td>
								<td align="center" width="100px">1000��(��)���´���
								</td>
								<td align="center" width="100px">1000��5000��(��)����
								</td>
								<td align="center" width="100px">5000��10000��(��)����
								</td>
								<td align="center" width="100px">10000�����ϴ���
								</td>
							</tr>
							<%
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
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr >
					<td>
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
					</td>
				</tr>
			</table>
			<%} %>
		</form>
		<form id="form2"  method="post"></form>
		<form id="form3" method="post"  enctype="multipart/form-data" target="ifm">
		<table  width="100%">
			<tr>
				<td align="left">
				�ϴ����EXCEL��
				��<input id="sheetNum1" type="text" size="5" value="" />���ļ�����<input id="sheetNum2" type="text" size="5" value="" />���ļ�
				</td>
			</tr>
			<tr>
				<td align="left">
				<input type="file" style="background-color: #FFFFE0" value="" name="file1" id="file1"/>
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



