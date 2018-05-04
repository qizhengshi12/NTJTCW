<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.safety.entity.PostInformation"%>  
<%@page import="com.safety.entity.ContentZzxx" %>
<%@page import="com.safety.entity.Wjglxf"%>  
<%@page import="com.safety.entity.ContentFl"%>
<%@page import="com.safety.entity.LearningCorner"%>
<%@page import="java.util.Date"%>  
<%@page import="java.text.SimpleDateFormat"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果还未登录系统*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")==""){
		out.println("<script>alert('登录超时！');top.location.href='login.jsp';</script>");
	}
	else{
	
	ContentZzxx UserInfo = (ContentZzxx)session.getAttribute("UserInfo");
	String Name = UserInfo.getName();
%>
<% if(request.getAttribute("result")!= null) {%>
    <script>
         alert('<%=request.getAttribute("result")%>');
    </script> 
<% } %>
<%
	ArrayList list1=new ArrayList();
	ArrayList list2=new ArrayList();
	ArrayList list3=new ArrayList();
	int FisetlinkID = 0;
	//从request域中取得要显示的某页信息
	ArrayList Informat = (ArrayList)session.getAttribute("Informat");
	/*遍历每个的信息进行显示*/
	for(int i=0;i< Informat.size();i++){
		PostInformation postInformation = (PostInformation)Informat.get(i);
		list1.add("UserFile/"+postInformation.getPhotoURL());
		list2.add(postInformation.getBt());
		list3.add(postInformation.getId());
	}
	FisetlinkID = ((PostInformation)Informat.get(0)).getId();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>关于系统和作者- 桌面</title>
<style type="text/css">
	a:link {color:black;text-decoration: none;}
	a:visited {color:black;}
</style>
<script type="text/javascript">
	function getNr(bz){
		var form = document.getElementById("form1");
		if(bz =="1"){
			form.action = "WjglServlet?action=getWjglList&flag=0";
		}else if(bz =="2"){
			form.action = "FlServlet?action=getFl";
		}else if(bz =="3"){
			form.action = "LearningCornerServlet?action=getCornerList&flag=1";
		}
		form.submit();
	}
</script>
</head>
<img width="100%" height="100%" style="z-index: -100;position: absolute;" src="images/d1.jpg">
<form method="post" id="form1" action=""></form>
   	<table align="center" height="100%" cellpadding="0" cellspacing="0" bordercolor="#E0EEE0" border="1px" style="background-color:rgba(255,255,255,0.7);">
   		<tr>
   			<td align="left" valign="top">
			     <table>
				   <tr valign="top">
				     <td valign="top">
				      <a href="PostInformationServlet?action=informatShow&id=<%=FisetlinkID %>" id="link" target="_blank">
				      <img src="" id="turn" width="350px" height='200px' border='1' style="filter:revealTrans(duration=1); margin：0em 0em 0em 0em;">
				      </a> 
				    </td>
				   </tr>
				   <tr>
				   	<td width="350px">
				      <div id='picName' align="center" style="height: 20px;color: white;background-color: #828282;"></div>
				   	</td>
				   </tr>
				   <tr>
				   	<td width="350px" align="right">
				      <img id="s1" onmouseout="sback(1)" onmouseover="choose(1);" width="18px" height="18px" src="images/s1.jpg"/><img id="s2" onmouseout="sback(2)" onmouseover="choose(2);" width="18px" height="18px" src="images/s2.jpg"/><img id="s3" onmouseout="sback(3)" onmouseover="choose(3);" width="18px" height="18px" src="images/s3.jpg"/><img id="s4" onmouseout="sback(4)" onmouseover="choose(4);" width="18px" height="18px" src="images/s4.jpg"/><img id="s5" onmouseout="sback(5)" onmouseover="choose(5);" width="18px" height="18px" src="images/s5.jpg"/>
				   	<img alt="更多" title="更多" style="cursor: pointer" onclick="more()"  src="images/more.png"/></td>
				   </tr>
			     </table>
   			</td>
   			<td valign="top">
   			<img onclick="getNr(1)" style="cursor: pointer;" width="390" height="30" alt="" src="images/tz1.png">
   			<table  width="390">
	   		<%
				ArrayList Wjglsy = (ArrayList)session.getAttribute("Wjglsy");
	         	/*遍历每个的信息进行显示*/
				for(int i=0;i< Wjglsy.size();i++){
					Wjglxf wjglxf = (Wjglxf)Wjglsy.get(i);
			%>
				<tr onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="left">
						<%if(wjglxf.getWjmc()!=null&&!"".equals(wjglxf.getWjmc())){%>
						<a href="WjglServlet?action=showWjglXF&id=<%=wjglxf.getId()%>" target="_blank">
						<%if(wjglxf.getWjmc().length()>15){%>
						<span title="<%=wjglxf.getWjmc()%>"><%=wjglxf.getWjmc().toString().substring(0,15)%>...</span>
						<%}else{ %><%=wjglxf.getWjmc()%><%} %>
						</a>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="right">
						<%if(wjglxf.getFqsj()!=null&&!"".equals(wjglxf.getFqsj())){%>
						<%
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM"); 
						String nd = df.format(new Date());
						if(nd.equals(wjglxf.getFqsj().toString().substring(0,7))){
						%>
						<img alt="new" title="new" style="cursor: pointer"  src="images/small/new04.png"/>
						<%}%>
						<%=wjglxf.getFqsj().toString().substring(0,10)%>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
				</tr>
				<%} %>
   			</table>
   			</td>
   		</tr>
   		<tr>
   			<td valign="top">
   			<img onclick="getNr(2)" style="cursor: pointer;" width="390" height="30" alt="" src="images/tz2.png">
   			<table  width="390">
	   		<%
				ArrayList FlSy = (ArrayList)session.getAttribute("FlSy");
	         	/*遍历每个的信息进行显示*/
				for(int i=0;i< FlSy.size();i++){
					ContentFl contentFl = (ContentFl)FlSy.get(i);
			%>
				<tr onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="left">
						<%if(contentFl.getBt()!=null&&!"".equals(contentFl.getBt())){%>
						<a href="FlServlet?action=showFl&id=<%=contentFl.getId()%>&flag=1" target="_blank">
						<%if(contentFl.getBt().length()>15){%>
						<span title="<%=contentFl.getBt()%>"><%=contentFl.getBt().toString().substring(0,15)%>...</span>
						<%}else{ %><%=contentFl.getBt()%><%} %>
						</a>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="right">
						<%if(contentFl.getCzsj()!=null&&!"".equals(contentFl.getCzsj())){%>
						<%
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM"); 
						String nd = df.format(new Date());
						if(nd.equals(contentFl.getCzsj().toString().substring(0,7))){
						%>
						<img alt="new" title="new" style="cursor: pointer"  src="images/small/new02.png"/>
						<%}%>
						<%=contentFl.getCzsj().toString().substring(0,10)%>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
				</tr>
				<%} %>
   			</table>
   			</td>
   			<td valign="top">
   			<img onclick="getNr(3)" style="cursor: pointer;" width="390" height="30" alt="" src="images/tz3.png">
   			<table  width="390">
	   		<%
				ArrayList LearningCornerSy = (ArrayList)session.getAttribute("LearningCornerSy");
	         	/*遍历每个的信息进行显示*/
				for(int i=0;i< LearningCornerSy.size();i++){
					LearningCorner learningCorner = (LearningCorner)LearningCornerSy.get(i);
			%>
				<tr onmouseover="this.style.backgroundColor='#EBEBEB';" onmouseOut="this.style.backgroundColor='';">
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="left">
						<%if(learningCorner.getBt()!=null&&!"".equals(learningCorner.getBt())){%>
						<a href="LearningCornerServlet?action=CornerShow&id=<%=learningCorner.getId()%>" target="_blank">
						<%if(learningCorner.getBt().length()>15){%>
						<span title="<%=learningCorner.getBt()%>"><%=learningCorner.getBt().toString().substring(0,15)%>...</span>
						<%}else{ %><%=learningCorner.getBt()%><%} %>
						</a>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
					<td style="font-family: 'Microsoft YaHei';border-bottom: dotted 1px;" align="right">
						<%if(learningCorner.getTjsj()!=null&&!"".equals(learningCorner.getTjsj())){%>
						<%
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM"); 
						String nd = df.format(new Date());
						if(nd.equals(learningCorner.getTjsj().toString().substring(0,7))){
						%>
						<img alt="new" title="new" style="cursor: pointer"  src="images/small/new01.png"/>
						<%}%>
						<%=learningCorner.getTjsj()%>
						<%}else{ %>
						&nbsp;
						<%} %>
					</td>
				</tr>
				<%} %>
   			</table>
   			</td>
   		</tr>
   	</table>
<script type="text/javascript"> 
	var a_url=new Array(4);
	var a_name=new Array(4);
	var a_link=new Array(4);
<%
	//把ArrayList的信息存放到数组中--图片路径传输入口
	for(int i=0;i<5;i++){
		out.println("a_url["+i+"]='"+list1.get(i)+"'");
		out.println("a_name["+i+"]='"+list2.get(i)+"'");
		out.println("a_link["+i+"]='PostInformationServlet?action=informatShow&id="+list3.get(i)+"'");
	}
	}
%>
	//全局变量组
	var tag=true;//是否循环播放标志(不能删除，默认为false)
	var total=5;//总页数
	var current=1;//当前页数
	var time=5000;//轮换时间间隔,单位毫秒
	var pic_obj=document.getElementById("turn") ;//捕获轮换图片对象
	var link_obj=document.getElementById("link") ;//捕获轮换图片链接
	var name_obj=document.getElementById("picName") ;//捕获轮换名对象

	//初始化
	name_obj.innerText=a_name[current-1];
	pic_obj.src=a_url[current-1];
	link_obj.src=a_link[current-1];
	setTimeout("change()",time);
 	//图片循环
	function change()
	{
		if(tag){
			if(current!=total){
				current+=1;   
			}else{
				current=1;   
			}
			name_obj.innerText=a_name[current-1];
			pic_obj.src=a_url[current-1];
			link_obj.href=a_link[current-1];  
			setTimeout("change()",time);//无限循环
		} 
	}
	//选择图片
	function choose(cu)
	{	
		current=cu;
		name_obj.innerText=a_name[current-1];
		pic_obj.src=a_url[current-1];
		link_obj.href=a_link[current-1];
		var ss=document.getElementById("s"+cu);
		ss.style.width="25px";
		ss.style.height="25px";
	}
	//离开图片
	function sback(cu)
	{	
		var ss=document.getElementById("s"+cu);
		ss.style.width="18px";
		ss.style.height="18px";
	}
	//更多
	function more(){
		var form = document.getElementById("form1");
		form.action = "PostInformationServlet?action=getInformationList";
		form.submit();
	}
</script>
</body>
</html>