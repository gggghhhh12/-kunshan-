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

<title>我的信息</title>

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
 
file="./css/stuNav.css"%>
<%@
include
 
file="./css/epShowJobWanted.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".t2 a").click(function(){
				if(!confirm("确定要删除吗？")){
					return;
				}
				var epUsername = $(this).attr("id");
				$.get("servlet/StuDelStuWantedServlet",{"epUsername":epUsername},function(){
				},"json");
				$(this).parent().parent().empty();
			});
		});
	</script>
</head>

<body>
	<%String userlevel=(String)session.getAttribute("userlevel"); %>
	<div id="header">
		<%@ include file="stuNav.jsp"%>
	</div>
	<div id="mainbody">
		<h2>我的消息</h2>
		<hr align="center" size="2px" color="#333" noshade>
		<div class="mymessage">
			<c:forEach var="ep" items="${eps }">
				<div class="single">
					<%if(userlevel.equals("1")){ %>
					<span class="t1"><a
						href="servlet/EPjobsServlet?EPusername=${ep.EPusername }"
						target="_blank">${ep.EPname }邀请您投递简历！欢迎您的加入！</a></span>
					<%}else{ %>
					<span class="t1"><a
						href="servlet/EPjobsServlet?EPusername=${ep.EPusername }"
						target="_blank">xxx公司邀请您投递简历！欢迎您的加入！</a></span>
					<%} %>
					<span class="t2"><a style="cursor: pointer"
						id="${ep.EPusername }">删除</a></span>
					<hr align="center" size="1px" color="#bbb" noshade>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
