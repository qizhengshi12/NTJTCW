<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.CwbbSydwzycwzbb"%>
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.Permissions"%>     
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
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
   	CwbbSydwzycwzbb cwbbSydwzycwzbb = (CwbbSydwzycwzbb)request.getAttribute("cwbbSydwzycwzbb");
	Calendar cal=Calendar.getInstance();
	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH)+1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
    <title>�����޸���ҵ��λ��Ҫ����ָ���</title>
    <script src="calendar.js"></script>
	<script type="text/javascript">
        function save(str){
			document.getElementById("tjzt").value = str;
        	var form = document.getElementById("form1");
			form.action = "BbsbCwbbServlet?action=sydwzycwzbbSave";
			form.submit();
        }
        function cancel(){
        	var form = document.getElementById("form2");
        	var menuname = document.getElementById("newcompany").value;
			form.action = "BbsbCwbbServlet?action=getSydwzycwzbb&flag=1&menuname="+menuname;
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
		    var path = "tempFile/sydwzycwzbb";
			form.action = "HandleFileServlet?action=HandleFile&path="+path;
			form.submit();
	   	}
	    function callback(msg){
	       	if(msg!='1'){
        		var form = document.getElementById("form1");
				form.action = "BbsbCwbbServlet?action=readSydwzycwzbbExcel&URL="+msg;
				form.submit();
	       	}else{
	       		alert("�ϴ���ͻ�����Ժ�����");
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
         	//��request����ȡ��Ҫ��ʾ��ĳҳ��Ϣ
         	//String permissions = (String)request.getAttribute("permissions"); 
		%>
		<form id="form1" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="cwbbSydwzycwzbb_id" id="cwbbSydwzycwzbb_id" <%if(cwbbSydwzycwzbb.getId()!=0){%>value="<%=cwbbSydwzycwzbb.getId()%>"<%}%> >
			<table  id="table1" width="100%">
				<tr>
					<td align="center" colspan="2" style="font-weight: bold;">
					<input name="bt" type="text" size="20" <%if(cwbbSydwzycwzbb.getBt()!=null){%>value="<%=cwbbSydwzycwzbb.getBt()%>"<%}else{%>value="��ͨ��ҵ��λ��Ҫ����ָ���"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<select name="year" id="year">
						<%if(cwbbSydwzycwzbb.getYear()!=0){%>
						<option value="<%= cwbbSydwzycwzbb.getYear() %>"><%= cwbbSydwzycwzbb.getYear() %>��</option>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>"><%= i %>��</option>
						<%} %>
						<%}else{ %>
						<% for(int i=nowYear-10;i<nowYear+2;i++){ %>
						<option value="<%= i %>" <%if(i==nowYear){%>selected<%}%>><%= i %>��</option>
						<%} }%>
					</select>
					&nbsp;1-
					<select name="month" id="month">
						<%if(cwbbSydwzycwzbb.getMonth()!=0){%>
						<option value="<%= cwbbSydwzycwzbb.getMonth() %>"><%= cwbbSydwzycwzbb.getMonth() %>��</option>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"><%= j %>��</option>
						<%} %>
						<%}else{ %>
						<% for(int j=1;j<13;j++){ %>
						<option value="<%= j %>"<%if(j==nowMonth){%>selected<%}%>><%= j %>��</option>
						<%} }%>
					</select>
					</td>
				</tr>
				<tr>
					<td align="left" width="80%">
						���λ��<%=cwbbSydwzycwzbb.getCzrdw() %>
						<input name="newcompany" id="newcompany" type="hidden"  value="<%=cwbbSydwzycwzbb.getCzrdw() %>" readonly="readonly" class="STYLE1"/>
					</td>
					<td align="right" width="20%">
						��λ����Ԫ
					</td>
				</tr>
			</table>
			<table id="table2" width="100%" border=1  cellpadding=0 cellspacing=0 style="">
				<tr>
					<td align="center" width="15%">���
					</td>
					<td align="center" width="60%">ָ������
					</td>
					<td align="center" width="25%">���
					</td>
				</tr>
				<tr>
					<td align="center">1
					</td>
					<td align="left">�ʲ��ܶ�ܹ�ģ��
					</td>
					<td align="center">
					<input name="zc" id="zc" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getZc()!=null){%>value="<%=cwbbSydwzycwzbb.getZc()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">2
					</td>
					<td align="left">ծȨ�ܶ�
					</td>
					<td align="center">
					<input name="zq" id="zq" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getZq()!=null){%>value="<%=cwbbSydwzycwzbb.getZq()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">3
					</td>
					<td align="left">����Ͷ���ܶ�
					</td>
					<td align="center">
					<input name="dwtz" id="dwtz" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getDwtz()!=null){%>value="<%=cwbbSydwzycwzbb.getDwtz()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">4
					</td>
					<td align="left">�̶��ʲ��ܶ�
					</td>
					<td align="center">
					<input name="gdzc" id="gdzc" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getGdzc()!=null){%>value="<%=cwbbSydwzycwzbb.getGdzc()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">5
					</td>
					<td align="left">�����ʽ�
					</td>
					<td align="center">
					<input name="hbzj" id="hbzj" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getHbzj()!=null){%>value="<%=cwbbSydwzycwzbb.getHbzj()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">6
					</td>
					<td align="left">��ծ�ܶ�
					</td>
					<td align="center">
					<input name="fzze" id="fzze" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getFzze()!=null){%>value="<%=cwbbSydwzycwzbb.getFzze()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">7
					</td>
					<td align="left">��ծ�ܶ������д������
					</td>
					<td align="center">
					<input name="fzye" id="fzye" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getFzye()!=null){%>value="<%=cwbbSydwzycwzbb.getFzye()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">8
					</td>
					<td align="left">���ʲ��ܶ�
					</td>
					<td align="center">
					<input name="jzc" id="jzc" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getJzc()!=null){%>value="<%=cwbbSydwzycwzbb.getJzc()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">9
					</td>
					<td align="left">���ʲ���ר�û����ܶ�
					</td>
					<td align="center">
					<input name="jzczy" id="jzczy" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getJzczy()!=null){%>value="<%=cwbbSydwzycwzbb.getJzczy()%>"<%}%>/>
					</td>
				</tr>
				<tr>
					<td align="center">10
					</td>
					<td align="left">���ʲ�����ҵ�����ܶ�
					</td>
					<td align="center">
					<input name="jzcsy" id="jzcsy" type="text" size="6" onkeyup="value=value.replace(/[^\d.]/g,'')" <%if(cwbbSydwzycwzbb.getJzcsy()!=null){%>value="<%=cwbbSydwzycwzbb.getJzcsy()%>"<%}%>/>
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
			<tr><td>&nbsp;</td></tr>
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
	</body>
	<%} %>
</html>



