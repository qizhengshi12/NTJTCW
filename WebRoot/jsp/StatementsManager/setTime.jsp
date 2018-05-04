<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.safety.entity.HtbbSetTime"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	/*如果登录超时*/
	if (session.getAttribute("UserInfo")==null || session.getAttribute("UserInfo")=="")
		out.println("<script>alert('登陆超时！');top.location.href='login.jsp';</script>");
	else{
    HtbbSetTime htbbSetTime = (HtbbSetTime)request.getAttribute("htbbSetTime");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设置信息</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
   	<script src="calendar.js"></script>
	<script type="text/javascript">
		function openSelectFree(name,id){
        	var form = document.getElementById("form2");
			var iWidth = 850;//弹出窗口的宽度;
			var iHeight = 450;//弹出窗口的高度;
			var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-10-iWidth)/2;//获得窗口的水平位置;var form = document.getElementById("form2");
        	form.target="preview_page";
			var popupWin = window.open('', 'preview_page','height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
			form.action = "ZzxxServlet?action=getThreeSelectFree&name="+name+"&id="+id;
			form.submit();
		}
		function save(){
        	var form = document.getElementById("form1");
        	var tzry = document.getElementById("tzry").value;
        	var tzryID = document.getElementById("tzryID").value;
        	var tznr = document.getElementById("tznr").value;
        	if(tzryID==""){
        		alert("请选择通知人员");
        		return;
        	}
        	if(tznr==""){
        		alert("请输入通知内容");
        		return;
        	}
        	if(tznr.length>250){
        		alert("通知内容不能超过250字");
        		return;
        	}
			form.action = "HtbbServlet?action=SaveHtbb";
			form.submit();
		}
		function ReSetP(){
        	document.getElementById("tzry").value = "";
        	document.getElementById("tzryID").value = "";
		}
		
		function selChange1(){
			var sel = document.getElementById("sel1");
			var str = sel.options[sel.selectedIndex].value;
			document.getElementById("setlx").value = str;
			if(str==1){
				document.getElementById("suggest1").style.display ="none";
				document.getElementById("suggest2").style.display ="none";
				document.getElementById("suggest3").style.display ="none";
			}else if(str==2){
				document.getElementById("suggest1").style.display ="inline";
				document.getElementById("suggest2").style.display ="none";
				document.getElementById("suggest3").style.display ="none";
			}else if(str==3){
				document.getElementById("suggest1").style.display ="none";
				document.getElementById("suggest2").style.display ="inline";
				document.getElementById("suggest3").style.display ="none";
			}else if(str==4){
				document.getElementById("suggest1").style.display ="none";
				document.getElementById("suggest2").style.display ="none";
				document.getElementById("suggest3").style.display ="inline";
			}
		}
		function choose1(){
			var sel = document.getElementById("ch1");
			var str = sel.options[sel.selectedIndex].value;
			document.getElementById("mday").value = str;
		}
		function choose2(){
			var sel = document.getElementById("ch2");
			var str = sel.options[sel.selectedIndex].value;
			document.getElementById("qmonth").value = str;
		}
		function choose3(){
			var sel = document.getElementById("ch3");
			var str = sel.options[sel.selectedIndex].value;
			document.getElementById("qmday").value = str;
		}
	</script>
  </head>
  
  <body>
  <% if(request.getAttribute("result")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("result")%>');
	    </script> 
  <% } %>
  <form id="form1"  method="post" name="SETTIME">
  		<input name="ID" id="ID" type="hidden" value="<%=htbbSetTime.getId()%>"/>
  		<input name="bbID" id="bbID" type="hidden" value="<%=htbbSetTime.getBbID()%>"/>
  		<input name="setlx" id="setlx" type="hidden" value="<%if(htbbSetTime.getSetlx()==0){%>1<%}else{%><%=htbbSetTime.getSetlx()%><%}%>"/>
  		<input name="mday" id="mday" type="hidden" value="<%=htbbSetTime.getMday()%>"/>
  		<input name="qmonth" id="qmonth" type="hidden" value="<%=htbbSetTime.getQmonth()%>"/>
  		<input name="qmday" id="qmday" type="hidden" value="<%=htbbSetTime.getQmday()%>"/>
	  <table width="100%">
		<tr>
			<td align="right" width="20%">
			通知时间：
			</td>
			<td align="left" width="80%">
			  	<select id="sel1" onchange="selChange1()" class="STYLE1">
					<option value="1" <%if(htbbSetTime.getSetlx()==1){%>selected="selected"<%}%> >每天</option>
					<option value="2" <%if(htbbSetTime.getSetlx()==2){%>selected="selected"<%}%> >每月</option>
					<option value="3" <%if(htbbSetTime.getSetlx()==3){%>selected="selected"<%}%> >每季度</option>
					<option value="4" <%if(htbbSetTime.getSetlx()==4){%>selected="selected"<%}%> >每年</option>
				</select>
				<span id="suggest1" <%if(htbbSetTime.getSetlx()==2){%>style="display: inline;"<%}else{%>style="display: none;"<%}%> >
					<select id="ch1" onchange="choose1(this.value)" class="STYLE1">
					<%for(int i=1;i<31;i++){ %>
					<option value="<%=i %>" <%if(htbbSetTime.getMday()==i){%>selected="selected"<%}%> ><%=i %>号</option>
					<%} %>
					</select>（若选择29号，30号，则二月份默认为28号）
				</span>
				<span id="suggest2" <%if(htbbSetTime.getSetlx()==3){%>style="display: inline;"<%}else{%>style="display: none;"<%}%> >
					<select id="ch2" onchange="choose2(this.value)" class="STYLE1">
					<option value="1" <%if(htbbSetTime.getQmonth()==1){%>selected="selected"<%}%> >第一个月</option>
					<option value="2" <%if(htbbSetTime.getQmonth()==2){%>selected="selected"<%}%> >第二个月</option>
					<option value="3" <%if(htbbSetTime.getQmonth()==3){%>selected="selected"<%}%> >第三个月</option>
					</select>
					<select id="ch3" onchange="choose3(this.value)" class="STYLE1">
					<%for(int i=1;i<31;i++){ %>
					<option value="<%=i %>" <%if(htbbSetTime.getQmday()==i){%>selected="selected"<%}%> ><%=i %>号</option>
					<%} %>
					</select>（若选择29号，30号，则二月份默认为28号）
				</span>
				<span id="suggest3" <%if(htbbSetTime.getSetlx()==4){%>style="display: inline;"<%}else{%>style="display: none;"<%}%> >
					<input type="text" name="yday" id="yday" value="<%if(htbbSetTime.getYday()!=null){ %><%=htbbSetTime.getYday() %><%} %>" class="STYLE1" readonly/>
					<input name="Button" class="button1" onclick="setDay(document.SETTIME.yday);" type="button" value="选择">（只确定月和日即可）
				</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="right">
			通知内容：
			</td>
			<td align="left">
				<textarea id="tznr" name="tznr" cols="35" rows="10"><%if(!"".equals(htbbSetTime.getTznr())&&htbbSetTime.getTznr()!=null){ %><%=htbbSetTime.getTznr()%><%}else{%>请各单位上传《<%=htbbSetTime.getBbmc()%>》。<%}%></textarea>（最多250个字）
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="right">
			通知人员：
			</td>
			<td align="left">
				<input name="tzry" id="tzry" type="text" size="80" onclick="openSelectFree('tzry','tzryID')" value="<%=htbbSetTime.getTzry()%>" readonly="readonly"/>
				<input name="tzryID" id="tzryID" type="hidden" value="<%=htbbSetTime.getTzryID()%>"/>
				<img align="middle" width="40px" height="40px" alt="添加人员" title="添加人员" style="cursor: pointer;" src="images/small/add_user.png" onclick="openSelectFree('tzry','tzryID')" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
				<img align="middle" width="40px" height="40px" alt="重置" title="重置" style="cursor: pointer;" src="images/small/delete_user.png" onclick="ReSetP()" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<hr style="height: 1px; border:none; border-top:1px dashed #5CACEE;" /> 
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<img alt="保存" width="45px" height="45px" title="保存" style="cursor: pointer;"  src="images/small/send.png" onclick="save();" onmousedown="this.style.border='solid 1px #A9A9A9'" onmouseup="this.style.border='solid 0px'">
			</td>
		</tr>
	  </table>
	</form>
	<form id="form2"  method="post"></form>
	<%} %>
  </body>
</html>
