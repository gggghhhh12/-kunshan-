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

<title>查看发布的职位</title>

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
 
file="./css/stuResume.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".button input").click(function(){
				var jobname=encodeURI(encodeURI($(this).attr("name")));
				var flag=$(this).attr("id");
				if(flag=="up"){
					alert(jobname+"up");
					$.get("servlet/EPModifyStatusServlet?status=0&jobname="+jobname,function(data,statusText){
						var callback = data.callback;
						$("."+jobname).text(callback);
					},"json");
				}
				else{
					alert(jobname+"down");
					$.get("servlet/EPModifyStatusServlet?status=1&jobname="+jobname,function(data,statusText){
						var callback = data.callback;
						$("."+jobname).text(callback);
					},"json");
				}
			});
			
			$(".button input").click(function(){
 			var jobname=$(this).attr("name");
		alert(jobname);
 			$.get("servlet/EPModifyStatusServlet",{"status":1,"jobname":jobname},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
 			});
		});
	</script>
</head>

<body>
	<%@ include file="EPNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>查看职位要求</h2>
			<div class="auth">${auth }</div>
		</div>
		<c:forEach var="EPPostJob" items="${EPPostJobs }">
			<div class="info">
				<!--职位要求-->
				<div class="basicinfo">
					<h4>职位要求</h4>
					<hr>
					<dl>
						<dt>职位名称:</dt>
						<dd>${EPPostJob.jobname }</dd>
					</dl>
					<dl>
						<dt>职位薪资:</dt>
						<dd>${EPPostJob.jobsalary }人民币/月</dd>
					</dl>
					<dl>
						<dt>学历要求:</dt>
						<dd>${EPPostJob.jobdiploma }</dd>
					</dl>
					<dl>
						<dt>英语等级要求:</dt>
						<dd>${EPPostJob.engrequest }</dd>
					</dl>
					<dl>
						<dt>招聘人数:</dt>
						<dd>${EPPostJob.reqnum }人</dd>
					</dl>
					<dl>
						<dt>公司福利:</dt>
						<dd>${EPPostJob.benefits }</dd>
					</dl>
					<dl>
						<dt class="top">职位描述:</dt>
						<dd>${EPPostJob.jobdescribe }</dd>
					</dl>
					<dl>
						<dt class="top">岗位职责:</dt>
						<dd>${EPPostJob.jobduty }</dd>
					</dl>
					<dl>
						<dt class="top">技术要求:</dt>
						<dd>${EPPostJob.techrequest }</dd>
					</dl>
					<dl>
						<dt>职能类别:</dt>
						<dd>${EPPostJob.jobkind }</dd>
					</dl>
					<dl>
						<dt>上班地址:</dt>
						<dd>${EPPostJob.jobaddr }</dd>
					</dl>
					<div class="button">
						<span><input style="cursor: pointer" id="up" type="button"
							value="上架职位" name="${EPPostJob.jobname}"></span> <span><input
							style="cursor: pointer" id="down" type="button" value="下架职位"
							name="${EPPostJob.jobname }"></span>
					</div>
					<div class="${EPPostJob.jobname }"></div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="footer"></div>
</body>
</html>
