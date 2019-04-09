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

<title>企业注册</title>

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
 
file="./css/style.css"%>
<%@
include
 
file="./css/mainPage.css"%>
</style>
</head>

<body>
	<div id="header">
		<nav>
			<div class="shouye">
				<a href="servlet/MainPageServlet">网络招聘系统</a>
			</div>
			<ul>
				<li><a>注册</a>
					<ul>
						<li><a href="stuRegister.jsp">毕业生注册</a></li>
						<li><a href="EPRegister.jsp">企业注册</a></li>
					</ul></li>
				<li><a>登录</a>
					<ul>
						<li><a href="stuLogin.jsp">毕业生登录</a></li>
						<li><a href="EPLogin.jsp">企业登录</a></li>
						<li><a href="adminLogin.jsp">管理员登录</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>
	<div class="login">
		<h2>企业注册</h2>
		<div class="login-top">
			<h1>注册窗口</h1>
			<form action="servlet/EPRegisterServlet" method="post">
				<input type="text" name="EPname" placeholder="username"> <input
					type="password" name="EPpassword" placeholder="pasword"> <input
					type="password" name="EPconfirmPassword"
					placeholder="Confirm Password">
				<div class="forgot">
					<div class="error">${error }</div>
					<input type="submit" value="注册">
				</div>
			</form>
		</div>
		<div class="login-bottom">
			<h3>
				老用户 &nbsp;<a href="EPLogin.jsp">登录</a>&nbsp;
			</h3>
		</div>
	</div>
</body>
</html>
