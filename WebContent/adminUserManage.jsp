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

<title>管理员账号管理</title>

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
 
	file="./css/adminUserManage.css"%>
</style>
</head>

<body>
	<nav>管理员账号管理</nav>
	<div class="modify">
		<form action="servlet/AdminModifyPasswordServlet" method="post">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="username"
						style="height: 35px; width: 300px;" readonly="readonly"
						value="${adminUser }"></input></td>
				</tr>
				<tr>
					<td>原密码:</td>
					<td><input type="password" name="password"
						style="height: 35px; width: 300px;"></input></td>
				</tr>
				<tr>
					<td>新密码:</td>
					<td><input type="password" name="newPassword"
						style="height: 35px; width: 300px;"></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" name="confirmPassword"
						style="height: 35px; width: 300px;"></input></td>
				</tr>
			</table>
			<div class="error">${error }</div>
			<div class="sure">
				<input style="cursor: pointer" type="submit" value="确认修改"></input>
			</div>
		</form>
	</div>
</body>
</html>
