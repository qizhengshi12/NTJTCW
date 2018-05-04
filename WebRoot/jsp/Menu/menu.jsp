<%@ page language="java" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   <LINK href="style.css" type="text/css" rel="stylesheet"> 
   <SCRIPT type="text/javascript" src="<%=basePath%>jsp/Menu/MzTreeView10.js"></SCRIPT>
    <title></title>
    <style type="text/css">
    body, td
    {
      font-family: 宋体;
      font-size: 12px;
    }
    A:LINK, A:VISITED, A:ACTIVE, A:HOVER
    {
      color: #800080;
      font-size: 13px;
      padding-left: 3px;
      TEXT-DECORATION: NONE;
    }
    </style>
  </head>
  <body>
	<div id=treeviewarea>
		<script language="javascript">
	    	var tree = new MzTreeView("tree");
	    	tree.setIconPath("<%=basePath%>jsp/Menu/treeImages/"); 
	    	tree.nodes['-1_0'] = 'text:法律法规 ';
	    	<%  String tree = (String)request.getAttribute("menuList"); out.print(tree); %>
	    	document.getElementById('treeviewarea').innerHTML = tree.toString();
	    	tree.nodeClick = function(id){
	    		alert("id是"+id);
	    	}   	
	     </SCRIPT>
  	</div>     
  </body>
</html>