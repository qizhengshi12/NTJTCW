<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.ContentZzxx"%> 
<%@page import="com.safety.entity.PaginationTool"%>  
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
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
	String flag = (String)request.getAttribute("flag");
	String srbt = (String)request.getAttribute("srbt");
	if(srbt==null)srbt="";
	String MenuId = (String)request.getAttribute("MenuId");
	if(MenuId==null)MenuId="";
	
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   <style type="text/css">
   img {border:0px;}
   </style>
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
    <title></title>
    <script type="text/javascript">
	//显示浮动层
	function show(val){   
		document.all.mydiv.style.display="block";   
		document.getElementById("showContent").innerText=val;
		document.all.mydiv.style.left=event.x+10;   
		document.all.mydiv.style.top=event.y+5;   
	}   
	function hide(val){   
		mydiv.style.display="none"   
		
	}
	 
    //添加短信
	function showMessage(name,username,phone){
		CloseMessage();
		var popUp = document.getElementById("Message");
		popUp.style.top = "230px";  
		popUp.style.left = "10px";
		popUp.style.width = "350px"; 
		popUp.style.height = "200px"; 
		popUp.style.visibility = "visible";
		document.getElementById("acceptName").value=name;
		document.getElementById("acceptUsername").value=username;
		document.getElementById("acceptPhone").value=phone;
	}
	function CloseMessage(){
		var popUp = document.getElementById("Message"); 
		popUp.style.visibility = "hidden"; 
	}
	function insertMessage(){
		var ry = document.getElementById("acceptName").value;
		var ryID = document.getElementById("acceptUsername").value;
		var phone = document.getElementById("acceptPhone").value;
		var dxnr = document.getElementById("acceptContent").value;
		var acceptContent = dxnr.replace(/\ /g,"");
		if(acceptContent==""){
   			alert("请输入要发送的短信内容");
   			document.getElementById("acceptContent").value="";
   			return;
   		}
   		
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
		    	alert(jsons);
		    }
		  }
		var url = "MessageServlet?action=sendMessageOne&ry="+ry+"&ryID="+ryID+"&phone="+phone+"&dxnr="+dxnr;
		xmlhttp.open("POST",url,true);
		xmlhttp.send();
	} 
	//排序
	function sort(){
		var objs1 = document.getElementsByName("sortnum");//最新顺序号
		var objs2 = document.getElementsByName("sortid");//记录ID号
		var objs3 = document.getElementsByName("sortold");//原始顺序号
		var List1="";//修改的顺序号
		var List2="";//对应的记录号
		for(i=0;i<objs1.length;i++){
			if(objs1[i].value != objs3[i].value){
				if(List1 == ""){
					List1 += objs1[i].value;
					List2 += objs2[i].value;
				}else{
					List1 += ","+objs1[i].value;
					List2 += ","+objs2[i].value;
				}	
			}
		}
		if(List1==""){
			return;
		}
		var fatherid = document.getElementById("fatherid").value;//所在部门ID
		var form = document.getElementById("form1");
		form.action = "ZzxxServlet?action=sortNum&sortList="+List1+"&idList="+List2+"&fatherid="+fatherid;
		form.submit();
	}
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form1");
		var srbt = document.getElementById("srbt").value;
        srbt = srbt.replace(/%/g,"%25");//转义;
		var MenuId = document.getElementById("MenuId").value;
		<%if("1".equals(flag)){ %>
		form.action = "ZzxxServlet?action=query&srbt="+srbt+"&MenuId=&page_no=" + v;
		<%}else{ %>
		form.action = "ZzxxServlet?action=query&MenuId="+MenuId+"&srbt=&page_no=" + v;
		<%}%>
		form.submit();
	}
	
    </script>
  </head>
  <body>
  	<% if(request.getAttribute("resultList")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("resultList")%>');
	    </script> 
	<% } %>
	<form method="post" id="form1" target="content"></form>
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<input type="hidden" id="MenuId" value="<%=MenuId%>">
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1>
		<tr class="tr1">
			<td align="center" width="5%" class="STYLE1">
				序号
			</td>
			<td align="center" width="8%" class="STYLE1">
				姓名
			</td>
			<td align="center" width="5%" class="STYLE1">
				性别
			</td>
			<td align="center" width="10%" class="STYLE1">
				岗位（职务）
			</td>
			<td align="center" width="10%" class="STYLE1">
				职称
			</td>
			<td align="center" width="15%" class="STYLE1">
				手机号码
			</td>
			<td align="center" width="15%" class="STYLE1">
				办公电话
			</td>
			<%if(UserPer.getContentzzxx().indexOf("3")!=-1 || UserPer.getContentzzxx().indexOf("4")!=-1){%>
			<td align="center" width="15%" class="STYLE1">
				操作
			</td>
			<%} %>
			<%if(!"1".equals(flag) && ("管理员".equals(roles)||UserPer.getContentzzxx().indexOf("3")!=-1)){%>
			<td align="center" width="5%" onclick="sort()" class="STYLE1" style="cursor: pointer">
				排序
			</td>
			<%} %>
		</tr>
		<%
         	//从request域中取得要显示的某页信息
         	//String permissions = (String)request.getAttribute("permissions"); 
         	ArrayList ZzxxList = (ArrayList)request.getAttribute("ZzxxList");
         	int count = ZzxxList.size();
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< ZzxxList.size();i++){
           	ContentZzxx contentZzxx = (ContentZzxx)ZzxxList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center" height="25px">
				<%if(ZzxxList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentZzxx.getName()!=null&&!"".equals(contentZzxx.getName())){%>
				<a class="BT" href="ZzxxServlet?action=showPerson&id=<%=contentZzxx.getId()%>"><%=contentZzxx.getName()%></a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentZzxx.getSex()!=null&&!"".equals(contentZzxx.getSex())){%>
				<%=contentZzxx.getSex()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center" onMouseOver="javascript:show('<%=contentZzxx.getJobdes()%>');" onMouseOut=hide('<%=contentZzxx.getJobdes()%>'); style="cursor:hand">
				<%if(contentZzxx.getJob()!=null&&!"".equals(contentZzxx.getJob())){%>
				<%=contentZzxx.getJob()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentZzxx.getJoblevel()!=null&&!"".equals(contentZzxx.getJoblevel())){%>
				<%=contentZzxx.getJoblevel()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentZzxx.getPhone()!=null&&!"".equals(contentZzxx.getPhone())){%>
				<span style="cursor: pointer;" onclick="showMessage('<%=contentZzxx.getName()%>','<%=contentZzxx.getUsername()%>','<%=contentZzxx.getPhone()%>')"><%=contentZzxx.getPhone()%></span>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(contentZzxx.getWorkphone()!=null&&!"".equals(contentZzxx.getWorkphone())){%>
				<%=contentZzxx.getWorkphone()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<%if(UserPer.getContentzzxx().indexOf("3")!=-1 || UserPer.getContentzzxx().indexOf("4")!=-1){%>
			<td align="center">
				<%if(UserPer.getContentzzxx().indexOf("5")!=-1){%>
					<%if(UserPer.getContentzzxx().indexOf("3")!=-1){%>
					<a href="ZzxxServlet?action=editPerson&id=<%=contentZzxx.getId()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
					<%} %>
					<%if(UserPer.getContentzzxx().indexOf("4")!=-1){%>
					<a href="ZzxxServlet?action=deletePerson&id=<%=contentZzxx.getId()%>&fatherid=<%=contentZzxx.getFatherid()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
					<%} %>
				<%}else{ %>
					<%if((UserPer.getContentzzxx().indexOf("3")!=-1) && (company.equals(contentZzxx.getCompany()))){%>
					<a href="ZzxxServlet?action=editPerson&id=<%=contentZzxx.getId()%>"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
					<%} %>
					<%if((UserPer.getContentzzxx().indexOf("4")!=-1) && (company.equals(contentZzxx.getCompany()))){%>
					<a href="ZzxxServlet?action=deletePerson&id=<%=contentZzxx.getId()%>&fatherid=<%=contentZzxx.getFatherid()%>&user=<%=contentZzxx.getUsername()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
					<%} %>
				<%}%>
			</td>
			<%} %>
			<%if(!"1".equals(flag) && ("管理员".equals(roles)||UserPer.getContentzzxx().indexOf("3")!=-1)){%>
			<td align="center">
			<%if(!"1".equals(flag) && ("管理员".equals(roles)||(UserPer.getContentzzxx().indexOf("3")!=-1 &&company.equals(contentZzxx.getCompany())))){%>
            	<input style="text-align: center;" type="text" size="5" name="sortnum" id="sortnum"  value="<%=contentZzxx.getSortnum()%>">
            	<input type="hidden" name="sortid" id="sortid"  value="<%=contentZzxx.getId()%>">
            	<input type="hidden" name="sortold" id="sortold"  value="<%=contentZzxx.getSortnum()%>">
            	<input type="hidden" name="fatherid" id="fatherid"  value="<%=contentZzxx.getFatherid()%>">
			<%} %>
            </td>
			<%} %>
		</tr>
		<%
		}
		%>
	</table>
	<table width="100%">
		<tr>
			<td align="left">
			<!-- 显示分页工具栏开始 -->
			<%=pt.printPage() %>
			<!--显示分页工具栏结束-->
			</td>
		</tr>
	</table>
	
	<div  id="mydiv" style="position:absolute;display:none;"> 
		<table width="180" height="60" border="1" bordercolor="#FFB6C1">
			<tr align="left" bgcolor="#FFFFE0">
				<td valign="top"><strong>岗位说明：</strong>
				<br/><br/>&nbsp;&nbsp;
				<span id="showContent"></span>
				</td>
			</tr>
		</table>
	</div>
	<div id="Message" class="menuDiv">
		<table width="100%">
			<tr>
				<td align="right">
					接收人员：
				</td>
				<td align="left">
					<input type="text" id="acceptName" value="" readonly="readonly"/>
					<input type="hidden" id="acceptUsername" value=""/>
					<input type="hidden" id="acceptPhone" value=""/>
				</td>
			</tr>
			<tr>
				<td align="right">
					短信内容：
				</td>
				<td align="left">
					<textarea id="acceptContent" cols="25" rows="6"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					（最多250个字）
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<img alt="发送" width="45px" height="45px" title="发送" style="cursor: pointer;"  src="images/small/4.png" onclick="insertMessage()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="取消" width="45px" height="45px" title="取消" style="cursor: pointer;"  src="images/small/3.png" onclick="CloseMessage();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				</td>
			</tr>
		</table>
	</div>
	<%} %>
  </body>
</html>