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

<title>简历细节</title>

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
 
file="./css/stuResumeDetail.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$(".operation .store a").click(function(){
				var stuUsername = "${basicInfo.username }";
				$.get("servlet/EPStoreServlet",{stuUsername:stuUsername},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
			});
			
			$(".operation input").click(function(){
				var stuUsername = "${basicInfo.username }";
				$.get("servlet/EPStuWantedServlet",{stuUsername:stuUsername},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
			});
		});
	</script>
</head>

<body>
	<div id="header"><%@ include file="EPNav.jsp"%></div>
	<div id="mainbody">
		<div class="leftInfo">
			<div class="stuInfo">
				<h3>${basicInfo.name }</h3>
				<span>${basicInfo.residence }</span> <span>${age }岁</span>
				<p>学校：${education.eduschool }</p>
				<p>专业：${education.edumajor }</p>
			</div>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<div class="operation">
				<span><input type="button" value="发出邀请"></input></span> <span
					class="store"><a>收藏</a></span>
			</div>
			<div class="callback"></div>
		</div>
		<div class="rightInfo">
			<h3>${basicInfo.name }的简历</h3>
			<hr align="center" size="4px" color="#666" noshade>
			<h4>基本信息</h4>
			<hr align="center" color="#DCE0E7" noshade>
			<div class="basicInfo">
				<span>姓名：${basicInfo.name }</span><span>性别：${basicInfo.sex }</span><span>民族：${basicInfo.nation }</span>
				<p>出生年月：${basicInfo.birthday }</p>
				<% String EPlevel = (String)session.getAttribute("EPlevel");
					if(EPlevel.equals("0")){
						//没有查看权限
				%>
				<p>联系电话: XXXXXXXXXXXX</p>
				<p>电子邮箱: XXXXXXXXXXXX</p>
				<%}else{ %>
				<p>联系电话：${basicInfo.tel }</p>
				<p>电子邮箱：${basicInfo.email }</p>
				<%} %>


				<p>户籍所在地：${basicInfo.residence }</p>
				<p>居住地：${basicInfo.liveaddr }</p>
			</div>
			<hr align="center" color="#DCE0E7" noshade>
			<h4>教育信息</h4>
			<hr align="center" color="#DCE0E7" noshade>
			<div class="education">
				<p>最高学历起止时间：${education.enterTime }至${education.gradTime }</p>
				<p>最高学历：${education.edudiploma }</p>
				<p>毕业院校：${education.eduschool }</p>
				<p>专业：${education.edumajor }</p>
				<p>外语能力：${education.englevel }</p>
				<dl>
					<dt>在校职务：</dt>
					<dd>${eduduty }</dd>
				</dl>
				<dl>
					<dt>在校奖励：</dt>
					<dd>${eduaward }</dd>
				</dl>
				<dl>
					<dt>实习经历：</dt>
					<dd>${eduprictise }</dd>
				</dl>
				<p>是否有海外学习经历：${education.abroad }</p>
			</div>
			<hr align="center" color="#DCE0E7" noshade>
			<h4>求职意向</h4>
			<hr align="center" color="#DCE0E7" noshade>
			<div class="jobIntention">
				<span>求职地点：${jobIntention.place }</span><span>求职行业：${jobIntention.trade }</span>
				<p>期望月薪：${jobIntention.salary }/月</p>
				<dl>
					<dt>自我评价：</dt>
					<dd>${evaluation }</dd>
				</dl>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
