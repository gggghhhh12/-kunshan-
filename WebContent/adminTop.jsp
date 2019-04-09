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

<title>管理员界面top</title>

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
 
file="./css/adminTop.css"%>
</style>
</head>

<body>
	<nav>
		<div class="left">
			<div class="logo">网络招聘信息系统</div>
			<div class="type">管理员界面</div>
		</div>
		<div class="right">
			<ul>
				<li><a>帮助</a></li>
				<li><a>关于</a></li>
			</ul>
			<div class="user">
				<div class="icon">
					<img src="./images/user.png">
				</div>
				<div class="username">${adminUser }</div>
				<div class="exit">
					<a href="servlet/MainPageServlet" target="_top">退出</a>
				</div>
			</div>
		</div>
	</nav>
</body>
</html>
