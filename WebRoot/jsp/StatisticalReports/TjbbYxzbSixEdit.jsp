<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.TjbbYxzbSix"%>
<%@page import="com.safety.entity.TjbbYxzbSixZxm"%>
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
    <title>������Ҫ����ָ��ͳ�Ʊ�������</title>
    <style type="text/css">
		.ex{top:0px; right:0px; position:fixed;background-color: #CCCCCC;} 
    </style>
    <script src="js/jquery-1.8.3.js"></script>
    <script src="calendar.js"></script>
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

	function save(str) {
		document.getElementById("tjzt").value = str;
		if (str == "1") {
			if (!verification())
				return;
		}
		var sj = document.getElementById("sj").value;
		if (sj == "") {
			alert("�����뱨��ʱ��");
			return;
		}
		var shr = document.getElementById("shr").value;
		if (shr == "") {
			alert("�����������");
			return;
		}
		var czrphone = document.getElementById("czrphone").value;
		if (czrphone == "") {
			alert("������绰");
			return;
		}
		var yxfx = document.getElementById("yxfx").value;
		if (yxfx.length > 500) {
			alert("���з���˵�����ݲ��ܳ���500��");
			return;
		}
		var form = document.getElementById("form1");
		form.action = "BbsbTjbbServlet?action=TjbbYxzbSixSave";
		form.submit();
	}
	function cancel() {
		var form = document.getElementById("form2");
		var menuname = document.getElementById("menuname").value;
		form.action = "BbsbTjbbServlet?action=getTjbbYxzbSix&flag=1&menuname="
				+ menuname;
		form.submit();
	}
	//����EXCEL
	function infExcel() {
		var file = document.getElementById("file1").value;
		if (file == "") {
			alert("��ѡ���ϴ��ļ�");
			return;
		}
		while (file.indexOf("\\") != -1) {
			file = file.slice(file.indexOf("\\") + 1);
		}
		var ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		if (ext == ".xls") {
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
			var size = fileSize / 1024;
			if (size <= 0) {
				alert("������С����Ϊ0M��");
				return;
			}
			infoFile();
		} else { //����ϴ��ļ�����
			alert("ֻ���ϴ�xls��ʽ���ļ�;\n������ѡ�����ϴ�.");
		}
	}
	function infoFile() {
		var form = document.getElementById("form3");
		var path = "tempFile/tjbbyxzbsix";
		form.action = "HandleFileServlet?action=HandleFile&path=" + path;
		form.submit();
	}
	function callback(msg) {
		if (msg != '1') {
			var form = document.getElementById("form1");
			form.action = "BbsbTjbbServlet?action=readTjbbYxzbSixExcel&URL="
					+ msg;
			form.submit();
		} else {
			alert("�ϴ���ͻ�����Ժ�����");
			return;
		}
	}

	//������֤
	function verification() {
		var zb1 = document.getElementsByName("zb1");
		var zb2 = document.getElementsByName("zb2");
		var zb3 = document.getElementsByName("zb3");
		var zb4 = document.getElementsByName("zb4");
		var zb5 = document.getElementsByName("zb5");
		var zb6 = document.getElementsByName("zb6");
		var yxfx = document.getElementById("yxfx").value;
		var zb4ls = document.getElementsByName("zb4ls");
		for ( var i = 0; i < 12; i++) {
			zb1[i].value = zb1[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb2[i].value = zb2[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb3[i].value = zb3[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb4[i].value = zb4[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb5[i].value = zb5[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb6[i].value = zb6[i].value.replace(/,/g, "");//ɾ�����ж���;
			zb4ls[i].value = zb4ls[i].value.replace(/,/g, "");//ɾ�����ж���;
			//����ȫ������
			if (zb1[i].value == "")
				zb1[i].value = 0;
			if (zb2[i].value == "")
				zb2[i].value = 0;
			if (zb3[i].value == "")
				zb3[i].value = 0;
			if (zb4[i].value == "")
				zb4[i].value = 0;
			if (zb5[i].value == "")
				zb5[i].value = 0;
			if (zb6[i].value == "")
				zb6[i].value = 0;
			if (zb4ls[i].value == "")
				zb4ls[i].value = 0;
		}
		for ( var i = 0; i < 6; i++) {
			zb4[i].value = (Number(zb2[i].value) + Number(zb4ls[i].value)).toFixed(2); 
		}
		for ( var i = 0; i < 12; i++) {
			if (zb1[i].value != 0) {
				zb3[i].value = ((Number(zb2[i].value) - Number(zb1[i].value))
						/ Number(zb1[i].value) * 100).toFixed(2);
			} else {
				zb3[i].value = 0;
			}
			if (zb5[i].value != 0) {
				zb6[i].value = ((Number(zb4[i].value) - Number(zb5[i].value))
						/ Number(zb5[i].value) * 100).toFixed(2);
			} else {
				zb6[i].value = 0;
			}
		}
		
		for ( var i = 0; i < 12; i++) {
			var menuname = document.getElementById("menuname").value;
			if(menuname!='�ֻ���'){
				//����ȫ������
				if (zb1[i].value == 0)
					zb1[i].value = "";
				if (zb2[i].value == 0)
					zb2[i].value = "";
				if (zb3[i].value == 0)
					zb3[i].value = "";
				if (zb4[i].value == 0){
					alert("�����ۼƲ���Ϊ��");
					return;
				}
				if (zb5[i].value == 0){
					alert("����ͬ���ۼƲ���Ϊ��");
					return;
				}
				if ((zb6[i].value>10||zb6[i].value<-10)&&yxfx==""){
					alert("����ͬ��ͬ�ȳ���+-10%,����д���з���˵��");
					return;
				}
			}
		}
		
		
		//alert("ȫ��У��ɹ�");
		return true;
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
		    TjbbYxzbSix tjbbYxzbSix = (TjbbYxzbSix)request.getAttribute("tjbbYxzbSix");
         	ArrayList TjbbYxzbSixZxmList = (ArrayList)request.getAttribute("TjbbYxzbSixZxmList");
         	ArrayList TjbbYxzbSixZxmListHistory = (ArrayList)request.getAttribute("TjbbYxzbSixZxmListHistory");
		%>
		<%
			if(TjbbYxzbSixZxmListHistory.size()>0){
				for(int ls=0;ls<12;ls++){
					TjbbYxzbSixZxm tjbbYxzbSixZxmls = (TjbbYxzbSixZxm)TjbbYxzbSixZxmListHistory.get(ls);
		%>
		<input name="zb4ls" type="hidden" size="15" <%if(tjbbYxzbSixZxmls.getZb4()!=null){%>value="<%=tjbbYxzbSixZxmls.getZb4()%>"<%}%>/>
		<%}}else{
				for(int ls=0;ls<12;ls++){%>	
		<input name="zb4ls" type="hidden" size="15" value=""/>
		<%}}%>					
		<form id="form1" name="TjbbYxzbSix" method="post">
		<input name="tjzt" id="tjzt" type="hidden" value=""/>
		<input type="hidden" name="TjbbYxzbSix_id" id="TjbbYxzbSix_id" <%if(tjbbYxzbSix.getId()!=0){%>value="<%=tjbbYxzbSix.getId()%>"<%}%> >
			<table width="100%">
				<tr>
					<td align="center" style="font-weight: bold;font-size: 15px">
					<input name="sj" id="sj" type="hidden" value="<%=tjbbYxzbSix.getSj()%>"/>
					<%=tjbbYxzbSix.getSj()%>
					<input name="bt" type="text" size="20" <%if(tjbbYxzbSix.getBt()!=null){%>value="<%=tjbbYxzbSix.getBt()%>"<%}else{%>value="��Ҫ����ָ��ͳ�Ʊ�"<%}%>/>
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
			<table width="1500px">
				<tr>
					<td>
						<table width="400px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="50px" height="30px">���
								</td>
								<td align="center" width="300px" colspan="2">ָ������
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">1
								</td>
								<td align="center" width="50px" rowspan="4">��·
								</td>
								<td align="center" width="250px">�����������ˣ�
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">2
								</td>
								<td align="center">������ת�������˹��
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">3
								</td>
								<td align="center">����������֣�
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">4
								</td>
								<td align="center">������ת������ֹ��
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">5
								</td>
								<td align="center" rowspan="2">ˮ·
								</td>
								<td align="center">����������֣�
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">6
								</td>
								<td align="center">������ת������ֹ��
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">7
								</td>
								<td align="center" rowspan="4">��·Ӫ�˹���
								</td>
								<td align="center">Ӫ�˿ͳ�����
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">8
								</td>
								<td align="center">Ӫ�˿ͳ���λ��
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">9
								</td>
								<td align="center">Ӫ�˻�������
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">10
								</td>
								<td align="center">Ӫ�˻�����λ��
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">11
								</td>
								<td align="center" rowspan="2">ˮ·Ӫ�˹���
								</td>
								<td align="center">��������
								</td>
							</tr>
							<tr>
								<td align="center" height="30px">12
								</td>
								<td align="center">���ض�λ
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table width="1150px" border=1  cellpadding=0 cellspacing=0 style="">
							<tr>
								<td align="center" width="150px" height="30px">������
								</td>
								<td align="center" width="150px">������
								</td>
								<td align="center" width="150px">��������%
								</td>
								<td align="center" width="150px">�����ۼ�
								</td>
								<td align="center" width="150px">����ͬ���ۼ�
								</td>
								<td align="center" width="150px">����ͬ��ͬ��+-%
								</td>
								<td align="center" width="250px">���з�����+-10%����˵����
								</td>
							</tr>
							<%
				         	if(TjbbYxzbSixZxmList.size()==0){
							for(int i=0;i<12;i++){
							%>
							
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" value=""/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" value=""/>
								</td>
								<%if(i==0){%>
								<td align="left" rowspan="12">
									<textarea rows="20" cols="30" id="yxfx" name="yxfx"></textarea>��500�����ڣ�
								</td>
								<%}%>
							</tr>
							<%
								}
							}else{
								for(int j=0;j< TjbbYxzbSixZxmList.size();j++){
									TjbbYxzbSixZxm tjbbYxzbSixZxm = (TjbbYxzbSixZxm)TjbbYxzbSixZxmList.get(j);
								%>
							<tr>
								<td align="center" height="30px">
								<input name="zb1" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb1()!=null){%>value="<%=tjbbYxzbSixZxm.getZb1()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb2" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb2()!=null){%>value="<%=tjbbYxzbSixZxm.getZb2()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb3" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb3()!=null){%>value="<%=tjbbYxzbSixZxm.getZb3()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb4" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb4()!=null){%>value="<%=tjbbYxzbSixZxm.getZb4()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb5" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb5()!=null){%>value="<%=tjbbYxzbSixZxm.getZb5()%>"<%}%>/>
								</td>
								<td align="center">
								<input name="zb6" type="text" size="15" <%if(tjbbYxzbSixZxm.getZb6()!=null){%>value="<%=tjbbYxzbSixZxm.getZb6()%>"<%}%>/>
								</td>
								<%if(j==0){%>
								<td align="left" rowspan="12">
									<textarea rows="20" cols="30" id="yxfx" name="yxfx"><%if(tjbbYxzbSix.getYxfx()!=null){%><%=tjbbYxzbSix.getYxfx()%><%}%></textarea>��500�����ڣ�
								</td>
								<%}%>
							</tr>
							<%} %>
						<%} %>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td align="left">
						����ˣ�<input name="shr" id="shr" type="text" size="10" <%if(tjbbYxzbSix.getShr()!=null){%>value="<%=tjbbYxzbSix.getShr()%>"<%}%>/>
						<input name="shrID" type="hidden" size="10" <%if(tjbbYxzbSix.getShrID()!=null){%>value="<%=tjbbYxzbSix.getShrID()%>"<%}%>/>
					</td>
					<td align="center">
						����ˣ�<%=name%>
					</td>
					<td align="right">
						�绰��<input name="czrphone" id="czrphone" type="text" size="10" <%if(tjbbYxzbSix.getCzrphone()!=null){%>value="<%=tjbbYxzbSix.getCzrphone()%>"<%}else{%>value="<%=workphone%>"<%}%>/>
					</td>
				</tr>
				<tr >
					<td colspan="3">
						<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img alt="����" src="images/small/calculator-operations.png" style="cursor: pointer;" onclick="verification()">
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



