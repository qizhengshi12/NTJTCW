<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String xList = (String)request.getAttribute("xList");
ArrayList ynameList = (ArrayList)request.getAttribute("ynameList");
ArrayList ydataList = (ArrayList)request.getAttribute("ydataList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计报表——柱状图</title>
	<base href="<%=basePath%>">
	<LINK href="cwbasic.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/highcharts/jquery.js"></script>
	<script src="js/highcharts/highcharts.js"></script>
	<script type="text/javascript">
	(function($){ // encapsulate jQuery
	var chart;
	$(document).ready(function() {
		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container',
				type: 'column'
			},
			title: {
				text: '数据柱形图表'
			},
			subtitle: {
				text: '演示'
			},
			xAxis: {
				categories: [<%=xList%>]
			},
			yAxis: {
				min: 0,
				title: {
					text: '数据'
				}
			},
			legend: {
				layout: 'vertical',
				backgroundColor: '#FFFFFF',
				align: 'left',
				verticalAlign: 'top',
				x: 100,
				y: 70,
				floating: true,
				shadow: true
			},
			tooltip: {
				formatter: function() {
					return ''+
						this.x +': '+ this.y;
				}
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
				series:
				[
				<%
					for(int i=0; i<ynameList.size(); i++){
				%>
					{
					name: '<%=ynameList.get(i)%>',
					data: [<%=ydataList.get(i)%>]
					},
				<%
					}
				
				%>
				]
		});
	});
	
	})(jQuery);
	function destroy() {  chart && chart.destroy();  chart = null;  } 
	</script>
  </head>
  
  <body>
  <form method="post" id="form1" action="" target="content">
  </form>
	  <table height="100%" width="100%">
		<tr>
			<td>
				<div id="container" ></div>
			</td>
		</tr>
	  </table>
  </body>
</html>
