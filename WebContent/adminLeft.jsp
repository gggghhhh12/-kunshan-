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

<title>管理员界面left</title>

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
 
file="./css/adminLeft.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".menu .span").click(function(){
				$(this).next().slideToggle("slow");
			});

			$("ul li").click(function(){
				$(this).css("background-color","#FF8A23");
				$("ul li").not(this).css("background-color","#FFF3E1");
			});
		});
	</script>
</head>

<body>
	<div class="menu">
		<a href="adminIndex.jsp" target="rightFrame"> <span class="span1">
				<img src="./images/lefticon01.png" /> <span class="title">首页</span>
		</span>
		</a>
		<div class="span">
			<img src="./images/lefticon01.png" />
			<div class="title">管理信息</div>
		</div>
		<ul>
			<li><a href="adminUserManage.jsp" target="rightFrame">管理员账号管理&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="servlet/AdminShowNewsServlet" target="rightFrame">新闻管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="servlet/AdminShowReportServlet" target="rightFrame">举报管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
		<div class="span">
			<img src="./images/lefticon01.png" />
			<div class="title">审核管理</div>
		</div>
		<ul>
			<li><a href="servlet/AdminCheckEPServlet" target="rightFrame">待审核企业</a></li>
			<li><a href="servlet/AdminCheckJobServlet" target="rightFrame">待审核职位</a></li>
			<li><a href="servlet/AdminCheckStuServlet" target="rightFrame">待审核学生账号</a></li>
			<li><a href="servlet/AdminCheckResumeServlet" target="rightFrame">待审核学生简历</a></li>
		</ul>
		<div class="span">
			<img src="./images/lefticon01.png" />
			<div class="title">毕业生管理</div>
		</div>
		<ul>
			<li><a href="servlet/AdminShowStuUserServlet"
				target="rightFrame">毕业生账号管理</a></li>
			<li><a href="servlet/AdminShowResumeServlet" target="rightFrame">毕业生简历管理</a></li>
		</ul>
		<div class="span">
			<img src="./images/lefticon01.png" />
			<div class="title">企业管理</div>
		</div>
		<ul>
			<li><a href="servlet/AdminLatestEPDataServlet"
				target="rightFrame">最近更新的企业信息</a></li>
			<li><a href="servlet/AdminLatestJobServlet" target="rightFrame">最近更新的企业职位</a></li>
			<li><a href="servlet/AdminShowEpUserServlet" target="rightFrame">企业账号管理</a></li>
			<li><a href="servlet/AdminShowEPDataServlet" target="rightFrame">企业信息管理</a></li>
			<li><a href="servlet/AdminShowPostJobServlet"
				target="rightFrame">企业职位管理</a></li>
			<li><a href="servlet/AdminShowCommentServlet"
				target="rightFrame">企业评论管理</a></li>
		</ul>
		<div class="span">
			<img src="./images/lefticon01.png" />
			<div class="title">图表管理</div>
		</div>
		<ul>
			<li><a href="adminShowJobPie.jsp" target="rightFrame">职位行业分布图表</a></li>
			<li><a href="adminShowStuPie.jsp" target="rightFrame">求职行业分布图表</a></li>
		</ul>
	</div>
</body>
</html>
