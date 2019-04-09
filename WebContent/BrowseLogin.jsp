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

<title>浏览登录</title>

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

<script type="text/javascript">
	</script>
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
						<li><a href="BrowseLogin.jsp">浏览登录</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>
	<div class="login">
		<h2>浏览登录</h2>
		<div class="login-top">
			<h1>登录窗口</h1>
			<form action="servlet/BrowseLoginServlet" method="post">
				<input type="text" name="browsename" placeholder="username">
				<input type="password" name="browsepassword" placeholder="password">
				<div class="forgot">
					<div class="error">${error }</div>
					<input style="cursor: pointer" type="submit" value="登录">
				</div>
			</form>
		</div>
		<div class="login-bottom"></div>
	</div>
</body>
</html>