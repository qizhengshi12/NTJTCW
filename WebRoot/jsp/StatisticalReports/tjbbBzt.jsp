<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
ArrayList xList = (ArrayList)request.getAttribute("xList");
String yname = (String)request.getAttribute("yname");
ArrayList ydataList = (ArrayList)request.getAttribute("ydataList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>统计报表——饼状图</title>
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
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: '数据饼状图表'
			},
			subtitle: {
				text: '<%=yname%>'
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
				}
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						color: '#000000',
						connectorColor: '#000000',
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
						}
					}
				}
			},
			series: [{
				type: 'pie',
				name: 'pie',
				data: [
			<%
				for(int i=0; i<xList.size(); i++){
			%>
					[<%=xList.get(i)%>,<%=ydataList.get(i)%>],
			<%
				}
			
			%>
					/**
					{
						name: '粮食',
						y: 12.8,
						sliced: true,
						selected: true
					},
					**/
				]
			}]
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
