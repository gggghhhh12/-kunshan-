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

<title>浏览页面</title>

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
	<frame src="browseTop.jsp" name="topFrame1" scrolling="No"
		noresize="noresize" id="topFrame1" title="topFrame" />
	<frameset cols="189,*" frameborder="no" border="0" framespacing="0">
		<frame src="browse.jsp" name="leftFrame1" scrolling="No"
			noresize="noresize" id="leftFrame1" title="leftFrame" />
		<frame src="browseIndex.jsp" name="rightFrame1" id="rightFrame1"
			title="rightFrame" />
	</frameset>
	<frame src="adminFooter.jsp" name="bottomFrame1" scrolling="No"
		noresize="noresize" id="bottomFrame1" title="bottomFrame" />
</frameset>
</head>

<body>

</body>
</html>
