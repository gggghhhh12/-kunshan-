<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>本月各行业职位发布情况</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
<%@
include
 
file="./css/adminNewsManage.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/highcharts.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$.get("servlet/AdminShowJobPieServlet",function(data,statusText){
				var arr = [];
				var result = data.result;
				for(var i = 0;i < result.length; i++){
					arr.push([result[i].trade,parseFloat(result[i].rate)]);
				}
				draw_pie(arr);
			},"json");
			function draw_pie(dates){
				$('#pie').highcharts({
					chart: {
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
	            		text: '本月各行业职位发布情况'
	        		},
	        		tooltip: {
	           			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        		},
	        		plotOptions: {
		            	pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
		            	}
	        		},
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: dates
			        }]
	    		}); 
			}
		});
	</script>
</head>

<body>
	<nav>职位行业分布图表</nav>
	<div id="pie"></div>
</body>
</html>
