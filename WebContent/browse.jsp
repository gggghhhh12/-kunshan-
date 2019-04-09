<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>浏览界面left</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		<%@ include file="./css/adminLeft.css"%>
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
		<a href="browseIndex.jsp" target="rightFrame1">
			<span class="span1">
				<img src="./images/lefticon01.png"/>
				<span class="title">首页</span>
			</span>
		</a>
		<div class="span"><img src="./images/lefticon01.png"/><div class="title">浏览页面</div></div>
		<ul>
			<li><a href="servlet/browseEpServlet" target="rightFrame1">合作企业</a></li>
			<li><a href="servlet/browseStuServlet" target="rightFrame1">合作学生</a></li>
			<li><a href="servlet/BrowseModifyStatusServlet" target="rightFrame1">上架职位</a></li>
			<li><a href="servlet/BrowsePostJobDownServlet" target="rightFrame1">已下架职位</a></li>
		</ul>
	</div>
  </body>
</html>