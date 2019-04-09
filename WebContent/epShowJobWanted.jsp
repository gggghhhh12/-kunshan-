<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<title>显示信息</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
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
				var option=$(this).text();
				var stuUsername = $(this).attr("id");
				var epJobname=$(this).attr("name");
				if(option=="标为已读"){
					$.get("servlet/EPChangeMessageStatusServlet",{"stuUsername":stuUsername,"epJobname":epJobname},
							function(){window.location.reload();},"json");
				}
				else if(option=="收藏"){
					$.get("servlet/EPChangeToStoreServlet",{"stuUsername":stuUsername},function(){
					},"json");
					$(this).parent().html("已收藏");
				}
				else{
					if(!confirm("确定要删除吗？")){
						return;
					}

					$.get("servlet/EPDelJobWantedServlet",{"stuUsername":stuUsername},function(){
					},"json");
					$(this).parent().parent().empty();
				}
			});
		});
	</script>
</head>
<body>
	<div id="header">

		<%@ include file="EPNav.jsp"%>

	</div>

	<div class="container">
		<%ArrayList<String> jobs=(ArrayList<String>)request.getAttribute("Jobs");
ArrayList<String> flags=(ArrayList<String>)request.getAttribute("flags");
ArrayList<String> stored=(ArrayList<String>)request.getAttribute("Stored");
ArrayList<String> unames=(ArrayList<String>)request.getAttribute("unames");
%>
		<!-- Nav pills -->
		<ul class="nav nav-pills" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="pill" href="#home">未读信息</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="pill"
				href="#menu1">已读信息</a><br></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div id="home" class="container tab-pane active">
				<br>
				<div id="mainbody">
					<hr align="center" size="2px" color="#333" noshade>
					<div class="mymessage">
						<%int i=0; %>
						<c:forEach var="stu" items="${stus }">
							<%if(flags.get(i).equals("0")) {
				%>
							<div class="single">
								<span class="t1"><a
									href="servlet/StuResumeDetailServlet?stuUsername=${stu.username }"
									target="_blank">毕业生${stu.name }向您公司投递了简历！</a>申请<%=jobs.get(i) %>职位</span>
								<span class="t2"><a id="${stu.username }"
									name="<%=jobs.get(i) %>">标为已读</a></span> <span class="t2">
									<%if(stored!=null&&stored.contains(unames.get(i))){ %>已收藏<%}else{ %><a
									id="${stu.username }" name="<%=jobs.get(i) %>">收藏</a>
									<%} %>
								</span> <span class="t2"><a id="${stu.username }"
									name="<%=jobs.get(i) %>">删除消息</a></span>
								<hr align="center" size="1px" color="#bbb" noshade>
							</div>
							<%}
			i++; %>
						</c:forEach>
					</div>
				</div>
				<div id="footer"></div>
			</div>

			<div id="menu1" class="container tab-pane fade">
				<br>
				<div id="mainbody">
					<hr align="center" size="2px" color="#333" noshade>
					<div class="mymessage">
						<%int j=0; %>
						<c:forEach var="stu" items="${stus }">
							<%if(flags.get(j).equals("1")) {%>
							<div class="single">
								<span class="t1"><a
									href="servlet/StuResumeDetailServlet?stuUsername=${stu.username }"
									target="_blank">毕业生${stu.name }向您公司投递了简历！</a>申请<%=jobs.get(j) %>职位</span>
								<span class="t2">
									<%if(stored!=null&&stored.contains(unames.get(j))){ %>已收藏<%}else{ %><a
									id="${stu.username }" name="<%=jobs.get(j) %>">收藏</a>
									<%} %>
								</span> <span class="t2"><a id="${stu.username }"
									name="<%=jobs.get(j) %>">删除消息</a></span>
								<hr align="center" size="1px" color="#bbb" noshade>
							</div>
							<%}j++; %>
						</c:forEach>
					</div>
				</div>
				<div id="footer"></div>
			</div>
		</div>
	</div>
</body>
</html>