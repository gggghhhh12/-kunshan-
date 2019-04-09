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

<title>合作企业</title>

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
	
});
</script>
<script type="text/javascript" src="./js/fenye.js"></script>


</head>

<body>
	<nav>企业</nav>
	<br>
	<div class="tablelist">
		<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;" id="idData">
			<thead>
			<tr>
				<th width="20%">公司名</th>
				<th width="20%">公司代码</th>
				<th width="20%">性质</th>
				<th width="20%">公司地点</th>
			</tr>
			</thead>
			<c:forEach var="EPdata" items="${EPDatas }">
				<tr>
					<td width="25%">${EPdata.EPname }</td>
					<td width="25%">${EPdata.EPcode }</td>
					<td width="25%">${EPdata.EPnature }</td>
					<td width="25%">${EPdata.EPaddr }</td>
				</tr>
			</c:forEach>
		</table>
		<table width="98%" align="center">
            <tr><td><div id="changePages" name="changePages"></div></td></tr>
	</div>
</body>
</html>