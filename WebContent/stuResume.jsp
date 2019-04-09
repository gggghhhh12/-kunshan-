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

<title>我的简历</title>

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
			$(".button #btnPublic").click(function(){
				$.get("servlet/StuModifyStatusServlet",{"status":0},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
			});
			
			$(".button #btnPrivate").click(function(){
				$.get("servlet/StuModifyStatusServlet",{"status":1},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
			});
			
		});
	</script>
</head>

<body>
	<%@ include file="stuNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>我的简历</h2>
		</div>
		<div class="info">
			<!--基本信息-->
			<div class="basicinfo">
				<h4>基本信息</h4>
				<hr>
				<dl>
					<dt>姓名:</dt>
					<dd>${basicInfo.name }</dd>
				</dl>
				<dl>
					<dt>性别:</dt>
					<dd>${basicInfo.sex }</dd>
				</dl>
				<dl>
					<dt>民族:</dt>
					<dd>${basicInfo.nation }</dd>
				</dl>
				<dl>
					<dt>出生日期</dt>
					<dd>${basicInfo.birthday }</dd>
				</dl>
				<dl>
					<dt>手机号码</dt>
					<dd>${basicInfo.tel }</dd>
				</dl>
				<dl>
					<dt>Email:</dt>
					<dd>${basicInfo.email }</dd>
				</dl>
				<dl>
					<dt>居住地:</dt>
					<dd>${basicInfo.liveaddr }</dd>
				</dl>
				<dl>
					<dt>户口:</dt>
					<dd>${basicInfo.residence }</dd>
				</dl>
			</div>
			<!--教育经历-->
			<div class="education">
				<h4>教育经历</h4>
				<hr>
				<dl>
					<dt>时间:</dt>
					<dd>${education.enterTime }&nbsp;到&nbsp;${education.gradTime }</dd>
				</dl>
				<dl>
					<dt>学校:</dt>
					<dd>${education.eduschool }</dd>
				</dl>
				<dl>
					<dt>专业:</dt>
					<dd>${education.edumajor }</dd>
				</dl>
				<dl>
					<dt>学历:</dt>
					<dd>${education.edudiploma }</dd>
				</dl>
				<dl>
					<dt>英语等级:</dt>
					<dd>${education.englevel }</dd>
				</dl>
				<dl>
					<dt class="top">校内职务:</dt>
					<dd>${eduduty }</dd>
				</dl>
				<dl>
					<dt class="top">在校奖励:</dt>
					<dd>${eduaward }</dd>
				</dl>
				<dl>
					<dt class="top">社会实践:</dt>
					<dd>${eduprictise }</dd>
				</dl>
				<dl>
					<dt>海外学习经历:</dt>
					<dd>${education.abroad }</dd>
				</dl>
			</div>
			<!--求职意向-->
			<div class="jobintention">
				<h4>求职意向</h4>
				<hr>
				<dl>
					<dt>关键词:</dt>
					<dd>${jobIntention.keyword }</dd>
				</dl>
				<dl>
					<dt class="top">个人评价:</dt>
					<dd>${evaluation}</dd>
				</dl>
				<dl>
					<dt>求职地点:</dt>
					<dd>${jobIntention.place }</dd>
				</dl>
				<dl>
					<dt>行业:</dt>
					<dd>${jobIntention.trade }</dd>
				</dl>
				<dl>
					<dt>期望月薪:</dt>
					<dd>${jobIntention.salary }/月</dd>
				</dl>
				<div class="callback"></div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
