<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%>  
<%@page import="com.safety.entity.Permissions"%>   
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
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String nodebz= "0";
	if(UserPer.getNodezzxx().indexOf("5")!=-1){
		//可对外单位节点操作
		nodebz= "1";
	}
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
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10_1.js"></SCRIPT>
    <title></title>
    <script language="javascript">
    	function queryMenuContext(MenuId){
    		var form = document.getElementById("form1");
			var cNode = tree.currentNode.text;
			form.action = "ZzxxServlet?action=query&MenuId=" + MenuId+"&cNode="+cNode;
			form.submit();
    	} 
    	//添加单位
		function showFatherDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("FatherDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "200px"; 
			popUp.style.visibility = "visible";
		}
		function CloseFatherDode(){
			var popUp = document.getElementById("FatherDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertFatherDode(){
			var name = document.getElementById("nodeName1").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("请输入要新增的单位名称");
    			document.getElementById("nodeName1").value="";
    			return;
    		}
			var form = document.getElementById("form2");
    		form.target="_self";
			form.action = "ZzxxServlet?action=insertMenu&ParentID=0&name=" + name+"&description=";
			form.submit();
		} 
		//添加部门
		function showSonDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("SonDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "220px"; 
			popUp.style.visibility = "visible";
		}
		function CloseSonDode(){
			var popUp = document.getElementById("SonDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertSonDode(dw,bz){
			var cNode = tree.currentNode;
			var cNodeIndex = cNode.sourceIndex;
			if(cNodeIndex.split("_")[0]==-1){
				alert("请选择单位后，再新增部门");
				return;
			}
			var pNode = cNode;
			var pNodeIndex = cNodeIndex;
			var i=0;
			for(i=0; i<2; i++){
				if(pNodeIndex.split("_")[0]!=0){
					pNode = pNode.parentNode;
					pNodeIndex = pNode.sourceIndex;
				}else{
					i=2;
				}
			}
    		//判断是否四级部门
    		if(pNodeIndex.split("_")[0]!=0){
				alert("无法在该部门下新增子部门");
				return;
			}
			var dwText = pNode.text;
			//判断是否可对外单位操作
			if(bz==0){
	    		if(dwText != dw){
	    			alert("不能在该节点下新增部门，请选择您所在单位后继续操作");
	    			return;
	    		}
    		}
			var id = cNodeIndex.split("_")[1];
			var name = document.getElementById("nodeName2").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("请输入要新增的部门名称");
    			document.getElementById("nodeName2").value="";
    			return;
    		}
    		var description = document.getElementById("nodeName3").value;
			var form = document.getElementById("form2");
    		form.target="_self";
			form.action = "ZzxxServlet?action=insertMenu&ParentID=" + id+"&name=" + name+"&description="+description;
			form.submit();
		}
		
    	//编辑菜单项
    	function showEditDode(){
			CloseFatherDode();
			CloseSonDode();
			CloseEditDode();
			var popUp = document.getElementById("EditDode");
			popUp.style.top = "300px";  
			popUp.style.left = "250px";
			popUp.style.width = "300px"; 
			popUp.style.height = "220px"; 
			popUp.style.visibility = "visible";
			var cNode = tree.currentNode;
			document.getElementById("nodeName4").value = cNode.text;
			document.getElementById("nodeName5").value = cNode.hint;
		}
		function CloseEditDode(){
			var popUp = document.getElementById("EditDode"); 
			popUp.style.visibility = "hidden"; 
		}
		function insertEditDode(dw,bz,roles){
			var cNode = tree.currentNode;
			var cNodeIndex = cNode.sourceIndex;
			if(cNodeIndex.split("_")[0]==-1){
				alert("不能修改总节点名称");
				return;
			}
			var pNode = cNode;
			var pNodeIndex = cNodeIndex;
			var i=0;
			for(i=0; i<3; i++){
				if(pNodeIndex.split("_")[0]!=0){
					pNode = pNode.parentNode;
					pNodeIndex = pNode.sourceIndex;
				}else{
					i=3;
				}
			}
			var dwText = pNode.text;
			//判断是否可对外单位操作
			if(bz==0){
	    		if(dwText != dw){
	    			alert("不能编辑该部门，请选择您所在单位后继续操作");
	    			return;
	    		}
    		}
			if(cNodeIndex.split("_")[0] == 0 &&roles!="管理员"){
    			alert("不能修改单位名称，请联系管理员");
    			return;
			}
			var name = document.getElementById("nodeName4").value;
			name = name.replace(/\ /g,"");
			if(name==""){
    			alert("请输入要修改的节点名称");
    			document.getElementById("nodeName4").value="";
    			return;
    		}
			var id = cNodeIndex.split("_")[1];
    		var description = document.getElementById("nodeName5").value;
			var form = document.getElementById("form2");
    		form.target="_self";
			form.action = "ZzxxServlet?action=updateMenu&id=" + id+"&name=" + name+"&description="+description;
			//form.submit();
		}
		 	
    	//删除菜单项
    	function deleteDode(dw,bz,roles){
			var cNode = tree.currentNode;
			var cNodeIndex = cNode.sourceIndex;
			if(cNodeIndex.split("_")[0]==-1){
				alert("不能删除总节点");
				return;
			}
			var pNode = cNode;
			var pNodeIndex = cNodeIndex;
			var i=0;
			for(i=0; i<3; i++){
				if(pNodeIndex.split("_")[0]!=0){
					pNode = pNode.parentNode;
					pNodeIndex = pNode.sourceIndex;
				}else{
					i=3;
				}
			}
			var dwText = pNode.text;
			//判断是否可对外单位操作
			if(bz==0){
	    		if(dwText != dw){
	    			alert("不能删除该部门，请选择您所在单位后继续操作");
	    			return;
	    		}
    		}
			if(cNodeIndex.split("_")[0] == 0 &&roles!="管理员"){
    			alert("不能删除单位，请联系管理员");
    			return;
			}
			var id = cNodeIndex.split("_")[1];
			var cNodeText = cNode.text;
        	if (!confirm("确认要删除："+cNodeText+"？")) {
				return;
    		}
    		var form = document.getElementById("form2");
    		form.target="_self";
			form.action = "ZzxxServlet?action=deleteMenu&id=" + id;
			form.submit();
		}
			
    	//新增文件
    	function insertDode(){
    		var form = document.getElementById("form2");
    		form.target="content";
			form.action = "jsp/BasicInformation/newPerson.jsp";
			form.submit();
		}
		function selChange1(){
			var sel = document.getElementById("sel1");
			document.getElementById("cxsex").value=sel.options[sel.selectedIndex].value;
		}
		function selChange2(){
			var sel = document.getElementById("sel2");
			document.getElementById("cxeducation").value=sel.options[sel.selectedIndex].value;
		}
		function selChange3(){
			var sel = document.getElementById("sel3");
			document.getElementById("cxjoblevel").value=sel.options[sel.selectedIndex].value;
		}
    	function queryContextByBt(){
    		var cxname = document.getElementById("cxname").value;
    		var cxsex = document.getElementById("cxsex").value;
    		var cxnl1 = document.getElementById("cxnl1").value;
    		var cxnl2 = document.getElementById("cxnl2").value;
    		var cxeducation = document.getElementById("cxeducation").value;
    		var cxjoblevel = document.getElementById("cxjoblevel").value;
    		var cxcompany = document.getElementById("cxcompany").value;
			var srbt = "";
    		if(cxname!=""){
    			srbt = " where name  like '%25"+cxname+"%25'";
    		}
    		if(cxsex!=""){
    			if(srbt!=""){
    				srbt = srbt +" and sex ='"+cxsex+"'";
    			}else{
    				srbt = " where sex ='"+cxsex+"'";
    			}
    		}
    		if(cxnl1!="" && cxnl1.indexOf[0]!="0"){
    			var time1 = getBirthYear(cxnl1);
    			if(srbt!=""){
    				srbt = srbt +" and birth <='"+time1+"'";
    			}else{
    				srbt = " where birth <='"+time1+"'";
    			}
    		}
    		if(cxnl2!="" && cxnl2.indexOf[0]!="0"){
    			var time2 = getBirthYear(cxnl2);
    			if(srbt!=""){
    				srbt = srbt +" and birth >='"+time2+"'";
    			}else{
    				srbt = " where birth >='"+time2+"'";
    			}
    		}
    		if(cxeducation!=""){
    			if(srbt!=""){
    				srbt = srbt +" and education ='"+cxeducation+"'";
    			}else{
    				srbt = " where education ='"+cxeducation+"'";
    			}
    		}
    		if(cxjoblevel!=""){
    			if(srbt!=""){
    				srbt = srbt +" and joblevel ='"+cxjoblevel+"'";
    			}else{
    				srbt = " where joblevel ='"+cxjoblevel+"'";
    			}
    		}
    		if(cxcompany!=""){
    			if(srbt!=""){
    				srbt = srbt +" and company ='"+cxcompany+"'";
    			}else{
    				srbt = " where company ='"+cxcompany+"'";
    			}
    		}
    		var form = document.getElementById("form1");
			form.action = "ZzxxServlet?action=query&srbt=" + srbt+"&MenuId=";
			form.submit();
    	} 
    	
	function getBirthYear(age)
	{
	    var d = new Date();
		var birthYear = d.getFullYear()-age;
	    var nowMonth = d.getMonth() + 1;
	    var nowDay = d.getDate();
		var birth = birthYear+"-"+nowMonth+"-"+nowDay;
		return birth;
	}
	
	//显示单位部门浮动层
	function newTitle(val){   
		var newTitle = document.getElementById("topdiv");
		newTitle.style.display="block";   
		document.getElementById("showTitle").value=val;
		newTitle.style.left=event.x+10;   
		newTitle.style.top=event.y+5;   
	}   
	function hideTitle(){   
		var newTitle = document.getElementById("topdiv");
		newTitle.style.display="none";   
		
	}
	function CXCompany(){
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
			    	document.getElementById("suggest1").innerHTML=jsons;
			    }
			  }
			var url = "ZzxxServlet?action=CXCompany";
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
		}
		function CXCompanyName(val){
	    	document.getElementById("cxcompany").value = val;
			document.getElementById("suggest1").innerHTML="";
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
		//String permissions = (String)request.getAttribute("permissions"); 
	%>
  <form method="post" id="form1" action="" target="content">
  </form>
  <form method="post" id="form2" action="">
  </form>
	<table width="100%" height="100%">
		<tr>
			<td width="15%" valign="top"  class="leftmenu">
				<div id=treeviewarea >
					<script language="javascript">
				    	var tree = new MzTreeView("tree");
				    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
				    	tree.nodes['-1_0'] = 'text:南通市交通运输局;';
				    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
				    	document.getElementById('treeviewarea').innerHTML = tree.toString();
				    	<% String MenuId1 = (String)request.getAttribute("MenuId");  %>
				     </SCRIPT>
	    		</div>
			</td>
			<td width="1px" bgcolor="#5CACEE">
			</td>
			<td width="85%" valign="top">	
				<table width="100%">
					<tr height="35px">
						<td colspan="1">&nbsp;</td>
						<td align="left" colspan="4">
							单位：<input type="text" name="cxcompany" id="cxcompany" onclick="CXCompany()" value="" readonly="readonly" class="STYLE1"/><span id="suggest1"></span>
            				<input type="hidden" name="companyID" id="companyID"  value="">
							姓名：<input type=text id="cxname" size="6" class="STYLE1" value=""/>
							性别：<select id="sel1" onchange="selChange1()" class="STYLE1">
								<option value=""></option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
							<input type=hidden id="cxsex" value=""/>&nbsp; 
						</td>
					</tr>
					<tr height="35px">
						<td colspan="1">&nbsp;</td>
						<td align="left" colspan="4">
							年龄：<input type=text id="cxnl1" class="STYLE1" onkeyup="this.value=this.value.replace(/\D/g,'')" size="2" value=""/>到<input type=text id="cxnl2" class="STYLE1" onkeyup="this.value=this.value.replace(/\D/g,'')" size="2" value=""/>
							学历：<select id="sel2" onchange="selChange2()" class="STYLE1">
								<option value=""></option>
								<option value="初中及以下">初中及以下</option>
								<option value="高中">高中</option>
								<option value="中专">中专</option>
								<option value="大专">大专</option>
								<option value="本科">本科</option>
								<option value="研究生">研究生</option>
								<option value="博士及以上">博士及以上</option>
							</select>
							<input type="hidden" name="cxeducation" id="cxeducation" value="">
							职称：<select id="sel3" onchange="selChange3()" class="STYLE1">
								<option value=""></option>
								<option value="会计员">会计员</option>
								<option value="助理会计师">助理会计师</option>
								<option value="会计师">会计师</option>
								<option value="高级会计师">高级会计师</option>
								<option value="经济员">经济员</option>
								<option value="助理经济师">助理经济师</option>
								<option value="经济师">经济师</option>
								<option value="高级经济师">高级经济师</option>
								<option value="审计员">审计员</option>
								<option value="助理审计师">助理审计师</option>
								<option value="审计师">审计师</option>
								<option value="高级审计师">高级审计师</option>
								<option value="其他初级职称">其他初级职称</option>
								<option value="其他中级职称">其他中级职称</option>
								<option value="其他高级职称">其他高级职称</option>
							</select>
							<input type="hidden" name="cxjoblevel" id="cxjoblevel" value="">
							<%if(UserPer.getContentzzxx().indexOf("1")!=-1){%>
							<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryContextByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
							<%} %>
						</td>
					</tr>
					<tr >
						<td width="100%" colspan="5">
							<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
						</td>
					</tr>
					<tr height="380px">
					<td height="380px" colspan="5">
					<iframe id="content" style="overflow-x: auto; overflow-y: auto;" name="content" src=""  width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0">
					</iframe>
					</td>
					</tr>
					<tr height="35px">
						<td align="center">
							<%if("管理员".equals(roles)){%>
							<img alt="新增单位" width="100px" height="60px" title="新增单位" style="cursor: pointer;" src="images/small/i6.png"  onclick="showFatherDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodezzxx().indexOf("2")!=-1){%>
							<img alt="新增部门" width="100px" height="60px" title="新增部门" style="cursor: pointer;" src="images/small/i7.png"  onclick="showSonDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodezzxx().indexOf("3")!=-1){%>
							<img alt="编辑菜单" width="100px" height="60px" title="编辑菜单" style="cursor: pointer;" src="images/small/i3.png"  onclick="showEditDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getNodezzxx().indexOf("4")!=-1){%>
							<img alt="删除菜单" width="100px" height="60px" title="删除菜单" style="cursor: pointer;" src="images/small/i4.png"  onclick="deleteDode('<%=company %>','<%=nodebz %>','<%=roles %>')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
						<td align="center">
							<%if(UserPer.getContentzzxx().indexOf("2")!=-1){%>
							<img alt="新增人员" width="100px" height="60px" title="新增人员" style="cursor: pointer;" src="images/small/i8.png"  onclick="insertDode()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
							<%} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<div id="FatherDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">新增单位</h1>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					单位名称：<input type="text" id="nodeName1" value=""/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseFatherDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="SonDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">新增部门</h1>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					名称：<input type="text" id="nodeName2" value=""/>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					职责：<textarea id="nodeName3" rows="4" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertSonDode('<%=company %>','<%=nodebz %>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseSonDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div id="EditDode" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="center" colspan="2">
					<h1 style="font-family:verdana">编辑菜单</h1>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					名称：<input type="text" id="nodeName4" value=""/>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">
					职责：<textarea id="nodeName5" rows="4" cols="30"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="确定" width="45px" height="45px" title="确定" style="cursor: pointer;"  src="images/small/4.png" onclick="insertEditDode('<%=company %>','<%=nodebz %>','<%=roles %>');" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
				<td align="center">
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseEditDode();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<div  id="topdiv" style="position:absolute;display:none; "> 
		<table width="180" height="60" border="1" bordercolor="#FFB6C1">
			<tr align="left" bgcolor="#FFFFE0">
				<td valign="top"><strong>职责说明：</strong>
				<br/><br/>&nbsp;&nbsp;
				<span id="showTitle"></span>
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>