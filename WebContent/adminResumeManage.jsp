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

<title>管理员毕业生简历管理</title>

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
</head>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
  	$(document).ready(function(){
  		$(".tablelist a").click(function(){
  			if($(this).text()=="删除"){
  				if(!confirm("确定要删除吗？")){
  					return;
  				}
  	  			var stuUsername = $(this).attr("id");
  	  			$.get("servlet/AdminDelResumeServlet",{"stuUsername":stuUsername},function(data,statusText){
  	  			},"json");
  	  			$(this).parent().parent().empty();
  			}
  			if($(this).text()=="通过审核"){
  				if(!confirm("确定要通过审核吗？")){
  					return;
  				}
  	  			var stuUsername = $(this).attr("id");
  	  			$.post("servlet/AdminChangeBasicInfoCheckServlet",{"username":stuUsername},function(data,statusText){
  	  			},"json");
  	  			$(this).parent().parent().empty();
  			}
  		});
  		goPage(1,10);
  	});
  </script>
  <script type="text/javascript" src="./js/fenye.js"></script>
<body>
	<nav>毕业生简历管理</nav>
	<br>
	<div class="tablelist">
			<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;" id="idData">
			<thead>
			<tr>
				<th width="10%">姓名</th>
				<th width="10%">性别</th>
				<th width="20%">学校</th>
				<th width="20%">专业</th>
				<th width="15%">手机号码</th>
				<th width="15%">Email</th>
				<th width="10%">操作</th>
			</tr>
			</thead>
			
			<%String uncheck=(String)request.getAttribute("uncheck");
			if(uncheck==null){%>
			<c:forEach var="resume" items="${resumes }">
				<tr>
					<td width="10%">${resume.stuName }</td>
					<td width="10%">${resume.sex }</td>
					<td width="20%">${resume.school }</td>
					<td width="20%">${resume.major }</td>
					<td width="15%">${resume.tel }</td>
					<td width="15%">${resume.email }</td>
					<td width="10%"><a style="cursor: pointer"
						id="${resume.stuUsername }">删除</a><a
						href="servlet/AdminModifyStuResumeServlet?username=${resume.stuUsername }">修改</a></td>
				</tr>
			</c:forEach>
			<%}else{ %>
			<c:forEach var="resume" items="${resumes }">
				<tr>
					<td width="10%">${resume.stuName }</td>
					<td width="10%">${resume.sex }</td>
					<td width="20%">${resume.school }</td>
					<td width="20%">${resume.major }</td>
					<td width="15%">${resume.tel }</td>
					<td width="15%">${resume.email }</td>
					<td width="10%"><a style="cursor: pointer"
						id="${resume.stuUsername }">通过审核</a> <a
						href="servlet/AdminModifyStuResumeServlet?username=${resume.stuUsername }">修改</a>
					</td>
				</tr>
			</c:forEach>
			<%} %>
		</table>
	</div>
	<div class="tablelist2">
	<table width="98%" align="center">
            <tr><td><div id="changePages" name="changePages"></div></td></tr>
        </table>
	</div>
</body>
</html>
