<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<frameset rows="88,*,33" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="adminTop.jsp" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="189,*" frameborder="no" border="0" framespacing="0">
		<frame src="adminLeft.jsp" name="leftFrame" scrolling="No"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="adminIndex.jsp" name="rightFrame" id="rightFrame"
			title="rightFrame" />
	</frameset>
	<frame src="adminFooter.jsp" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
</head>

<body>

</body>
</html>
