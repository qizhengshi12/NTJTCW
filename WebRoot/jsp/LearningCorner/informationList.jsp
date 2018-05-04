<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.LearningCorner"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
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
%>
<%
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	Permissions UserPer = (Permissions)session.getAttribute("UserPer");
	String username = UserInfo.getUsername();
	String name = UserInfo.getName();
	String company = UserInfo.getCompany();
	String roles = UserInfo.getRoles();
	PaginationTool pt = (PaginationTool)request.getAttribute("pt");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <title>学习园地列表</title>
   <style type="text/css">
   img {border:0px;}
   </style>
   <LINK href="cwbasic.css" type="text/css" rel="stylesheet">
     <script src="calendar.js"></script>
     <script type="text/javascript">
     function queryCornerByBt(){
   		var form = document.getElementById("form2");
		form.action = "LearningCornerServlet?action=getCornerList";
		form.submit();
   	} 
   	function insertCorner(){
   		var form = document.getElementById("form1");
		form.action = "LearningCornerServlet?action=CornerEdit";
		form.submit();
   	}
	function goNum(){
		var chooseNum = document.getElementById("chooseNum").value;
		skipTo(chooseNum);
	}
	function skipTo(v){
		var form = document.getElementById("form2");
		form.action = "LearningCornerServlet?action=getCornerList&page_no=" + v;
		form.submit();
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
		String srbt = (String)request.getAttribute("srbt");
		if(srbt==null)srbt="";
		String cxbt = (String)request.getAttribute("cxbt");
		if(cxbt==null)cxbt="";   
		String cxlx1 = (String)request.getAttribute("cxlx1");
		if(cxlx1==null)cxlx1="";  
		String cxlx2 = (String)request.getAttribute("cxlx2");
		if(cxlx2==null)cxlx2="";  
		String cxlx3 = (String)request.getAttribute("cxlx3");
		if(cxlx3==null)cxlx3="";  
		String cxlx3n = "";
		if("1".equals(cxlx3))cxlx3n = "普通帖";
		if("2".equals(cxlx3))cxlx3n = "精华帖";
		String cxczr = (String)request.getAttribute("cxczr");
		if(cxczr==null)cxczr="";           
	%>
	<form method="post" id="form1" action="">
	</form>
	<form method="post" id="form2" action="">
	<input type="hidden" id="srbt" value="<%=srbt%>">
	<table width="100%">
		<tr>
			<td align="center">
				标题:
				<input type=text id="cxbt" name="cxbt" size=25 value="<%=cxbt%>"/>
				版块：<select name="cxlx1">
						<option value="<%=cxlx1 %>"><%=cxlx1 %></option>
						<option value="">全部</option>
						<option value="学习">学习</option>
						<option value="工作">工作</option>
						<option value="生活">生活</option>
					  </select>
				类型：<select name="cxlx2">
						<option value="<%=cxlx2 %>"><%=cxlx2 %></option>
						<option value="">全部</option>
						<option value="分享">分享</option>
						<option value="求助">求助</option>
					  </select>
				等级：<select name="cxlx3">
						<option value="<%=cxlx3 %>"><%=cxlx3n %></option>
						<option value="">全部</option>
						<option value="1">普通帖</option>
						<option value="2">精华帖</option>
					  </select>
				发帖人：<select name="cxczr">
						<option value="<%=cxczr %>"><%=cxczr %></option>
						<option value="">全部</option>
						<option value="<%=username %>">我的帖子</option>
					  </select>
				<%if(UserPer.getLearningcorner().indexOf("1")!=-1){%>
				<img align="middle" width="30px" height="30px" alt="查询" title="查询" style="cursor: pointer;" src="images/small/find.png" onclick="queryCornerByBt()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<%} %>
			</td>
		</tr>
	</table>
	</form>
	<table id="table1" width="100%" border=0  cellpadding=0 cellspacing=1>
		<tr class="tr1" height="25px">
			<td align="center" width="5%">
				序号
			</td>
			<td align="center" width="18%">
				标题
			</td>
			<td align="center" width="12%">
				发表时间
			</td>
			<td align="center" width="10%">
				发表人
			</td>
			<td align="center" width="10%">
				版块
			</td>
			<td align="center" width="10%">
				分享/求助
			</td>
			<td align="center" width="10%">
				精华/普通
			</td>
			<td align="center" width="10%">
				获赞数
			</td>
			<%if(UserPer.getLearningcorner().indexOf("3")!=-1 || UserPer.getLearningcorner().indexOf("4")!=-1|| UserPer.getLearningcorner().indexOf("5")!=-1){%>
			<td align="center" width="15%">
				操作
			</td>
			<%} %>
		</tr>
		<%
         	//从request域中取得要显示的某页信息
         	ArrayList CornerList = (ArrayList)request.getAttribute("CornerList");
         	/*遍历每个的信息进行显示*/
           for(int i=0;i< CornerList.size();i++){
           	LearningCorner learningCorner = (LearningCorner)CornerList.get(i);
		%>
		<tr class="tr2" onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
			<td align="center" height="25px">
				<%if(CornerList.size()!=0){%>
				<%=i+1%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getBt()!=null&&!"".equals(learningCorner.getBt())){%>
				<a href="LearningCornerServlet?action=CornerShow&id=<%=learningCorner.getId()%>" target="_blank">
				<%if(learningCorner.getBt().length()>5){%>
				<span title="<%=learningCorner.getBt()%>"><%=learningCorner.getBt().toString().substring(0,5)%>...</span>
				<%}else{ %><%=learningCorner.getBt()%><%} %>
				</a>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getTjsj()!=null&&!"".equals(learningCorner.getTjsj())){%>
				<%=learningCorner.getTjsj()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getTjr()!=null&&!"".equals(learningCorner.getTjr())){%>
				<%=learningCorner.getTjr()%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getLx1()!=null&&!"".equals(learningCorner.getLx1())){%>
				<%if("学习".equals(learningCorner.getLx1())){%>
				学习
				<%}else if("工作".equals(learningCorner.getLx1())){%>
				工作
				<%}else if("生活".equals(learningCorner.getLx1())){%>
				生活
				<%}%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getLx2()!=null&&!"".equals(learningCorner.getLx2())){%>
				<%if("分享".equals(learningCorner.getLx2())){%>
				分享
				<%}else{%>
				求助
				<%}%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%if(learningCorner.getLx3()!=null&&!"".equals(learningCorner.getLx3())){%>
				<%if("2".equals(learningCorner.getLx3())){%>
				精华帖
				<%}else{%>
				普通帖
				<%}%>
				<%}else{ %>
				&nbsp;
				<%} %>
			</td>
			<td align="center">
				<%=learningCorner.getGood()%>
			</td>
			<%if(UserPer.getLearningcorner().indexOf("3")!=-1 || UserPer.getLearningcorner().indexOf("4")!=-1){%>
			<td align="center">
				<%if((UserPer.getLearningcorner().indexOf("5")!=-1)&&"1".equals(learningCorner.getLx3())){%>
				 	<a href="LearningCornerServlet?action=setJH&id=<%=learningCorner.getId()%>&bz=2" target="I2"><img  alt="加精" title="加精" src="images/small/star-empty.png"></a>
				<%} %>
				<%if((UserPer.getLearningcorner().indexOf("5")!=-1)&&"2".equals(learningCorner.getLx3())){%>
				 	<a href="LearningCornerServlet?action=setJH&id=<%=learningCorner.getId()%>&bz=1" target="I2"><img  alt="去精" title="去精" src="images/small/star-full.png"></a>
				<%} %>
				<%if((UserPer.getLearningcorner().indexOf("3")!=-1)&&username.equals(learningCorner.getTjrID())){%>
				 	<a href="LearningCornerServlet?action=CornerEdit&id=<%=learningCorner.getId()%>" target="I2"><img  alt="编辑" title="编辑" src="images/small/options.png"></a>
				<%} %>
				<%if(((UserPer.getLearningcorner().indexOf("4")!=-1)&&username.equals(learningCorner.getTjrID()))||(UserPer.getLearningcorner().indexOf("5")!=-1)){%>
	  				<a href="LearningCornerServlet?action=delete&id=<%=learningCorner.getId()%>&path1=<%=learningCorner.getFileURL()%>&path2=<%=learningCorner.getPhotoURL()%>" onclick="javascript:return confirm('确定删除此记录？');"><img  alt="删除" title="删除" src="images/small/burn.png"></a>
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
	<table width="100%">
		<tr>
			<td align="center">
			<%if(UserPer.getLearningcorner().indexOf("2")!=-1){%>
				<img alt="发表文章" width="100px" height="60px" title="发表文章" style="cursor: pointer;" src="images/small/i17.png"  onclick="insertCorner()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">							
			<%} %>
			</td>
		</tr>
	</table>
  </body>
</html>