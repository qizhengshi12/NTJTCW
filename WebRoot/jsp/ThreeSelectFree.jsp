<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
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
	String roles = UserInfo.getRoles();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10_1.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
    <title>ѡ����Ա</title>
    <style type="text/css">
   .opselect{
   width: 100px;
   height: 200px;
   font-size: 15px;
   }
    </style>
    <script language="javascript">
   	function queryMenuContext(MenuId){
   		var xmlhttp;    
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	var jsons = xmlhttp.responseText;
		    	document.getElementById("selectL").innerHTML=jsons;
		    }
		  }
		var url = "ZzxxServlet?action=getLeftSelect&MenuId=" + MenuId;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
   	} 
   	function fastChoose(val){
   		var xmlhttp;    
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		    	var jsons = xmlhttp.responseText;
		    	document.getElementById("selectL").innerHTML=jsons;
		    }
		  }
		var url = "ZzxxServlet?action=fastChoose&val=" + val;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
   	} 
    $(function(){
	    var leftSel = $("#selectL");
		var rightSel = $("#selectR");
		$("#toright").bind("click",function(){		
			leftSel.find("option:selected").each(function(){
				$(this).remove().appendTo(rightSel);
			});
		});
		$("#toleft").bind("click",function(){		
			rightSel.find("option:selected").each(function(){
				$(this).remove().appendTo(leftSel);
			});
		});
		leftSel.dblclick(function(){
			$(this).find("option:selected").each(function(){
				$(this).remove().appendTo(rightSel);
			});
		});
		rightSel.dblclick(function(){
			$(this).find("option:selected").each(function(){
				$(this).remove().appendTo(leftSel);
			});
		});
		$("#choose").click(function(){
			var backname = document.getElementById("backname").value;
			var backid = document.getElementById("backid").value;
			var selVal = [];
			var selTex = [];
			rightSel.find("option").each(function(){
				selVal.push(this.value);
				selTex.push(this.innerHTML);
			});
			selVals = selVal.join(",");
			selTexs = selTex.join(",");
			if(selVals==""){
				alert("û��ѡ���κ��");
			}else{
				opener.document.getElementById(backname).value=selTexs; 
				opener.document.getElementById(backid).value=selVals;
				window.open('','_self');
				window.close();
			}
		});
	});
	function cancel(){
		window.open('','_self');
		window.close();
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
		String MenuId = (String)request.getAttribute("MenuId"); 
		if(MenuId==null)MenuId="";
		String backname = (String)request.getAttribute("backname"); 
		if(backname==null)backname="";
		String backid = (String)request.getAttribute("backid"); 
		if(backid==null)backid="";
		//String permissions = (String)request.getAttribute("permissions"); 
	%>
  <form method="post" id="form1" action="" target="content">
  </form>
  <form method="post" id="form2" action="">
  </form>
	<table width="100%" height="100%">
		<tr>
			<td width="30%" height="100%" valign="top" style="size: 15px;">
				<div id=treeviewarea >
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:��ͨ�н�ͨ�����;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				    	<% String MenuId1 = (String)request.getAttribute("MenuId");  %>
				     </SCRIPT>
	    		</div>
			</td>
			<td width="1px" bgcolor="#5CACEE">
			</td>
			<td width="70%" height="100%" valign="top" style="size: 15px;">
				<table>
					<tr>
						<td width="200px" rowspan="2" align="center">
							<p>���ѡ��</p>
							<table>
								<tr>
									<td height="40px"><input type="button" onclick="fastChoose('��ϢԱ')" class="button1" value="��λ��ϢԱ" /></td>
								</tr>
								<tr>
									<td height="40px"><input type="button" onclick="fastChoose('��������')" class="button1" value="��������" /></td>
								</tr>
								<tr>
									<td height="40px"><input type="button" onclick="fastChoose('��Ƹ�����')" class="button1" value="��Ƹ�����" /></td>
								</tr>
								<tr>
									<td height="40px"><input type="button" onclick="fastChoose('ͳ�Ƹ�����')" class="button1" value="ͳ�Ƹ�����" /></td>
								</tr>
							</table>
						</td>
						<td width="200px" rowspan="2" align="center">
							<p>��ѡ��</p>
							<select class="opselect" id="selectL" name="selectL" multiple="multiple"> 
							</select> 
						</td>
						<td height="100px" valign="bottom" align="center">
							<input type="button" id="toright" class="button1" value="���" />
						</td>
						<td width="200px" rowspan="2" align="center">
							<p>��ѡ��</p>
							<select class="opselect"  id="selectR" name="selectR" multiple="multiple"> 
							</select> 
						</td>
					</tr>
					<tr>
						<td height="100px" valign="top">
							<input type="button" id="toleft" class="button1" value="�Ƴ�" />
						</td>
					</tr>
					<tr>
						<td height="50px" colspan="4" valign="bottom" align="center">
							<input type="hidden" id="backname" value="<%=backname %>" />
							<input type="hidden" id="backid" value="<%=backid %>" />
							<input type="button" id="choose" class="button1" value="ȷ��" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" onclick="cancel()" class="button1" value="ȡ��" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<%} %>
  </body>
</html>