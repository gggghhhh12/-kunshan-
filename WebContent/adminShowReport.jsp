<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>举报信息管理</title>

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
<script type="text/javascript">
		$(document).ready(function(){
			goPage(1,10);
			$(".tablelist tr td a").click(function(){
	  			if(!confirm("确定要删除吗？")){
					return;
				}
				var stuUsername = $(this).parent().prev().prev().text();
				var epUsername = $(this).parent().prev().text();
				$.get("servlet/AdminDelReportServlet",{"stuUsername":stuUsername,"epUsername":epUsername},function(data,statusText){
				},"json");
				$(this).parent().parent().empty();
			});
		});
	</script>
	<script type="text/javascript" src="./js/fenye.js"></script>
</head>

<body>
	<nav>举报信息管理</nav>
	<br>
	<div class="tablelist">
		<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;">
			<tr>
				<th width="40%">举报者用户名</th>
				<th width="40%">被举报企业用户名</th>
				<th width="20%">操作</th>
			</tr>
			</table>
			<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;" id="idData">
			<c:forEach var="report" items="${reports }">
				<tr>
					<td width="40%">${report.stuUsername }</td>
					<td width="40%">${report.epUsername }</td>
					<td width="20%"><a>已处理</a></td>
				</tr>
			</c:forEach>
		</table>
		<table width="98%" align="center">
            <tr><td><div id="changePages" name="changePages"></div></td></tr>
        </table>
	</div>
</body>
</html>
