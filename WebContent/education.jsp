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

<title>教育经历</title>

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
<script type="text/javascript" src="./js/education.js"></script>
</head>

<body>
	<%@ include file="stuNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>我的简历</h2>
		</div>
		<div class="info">
			<!--教育经历-->
			<div class="education">
				<h4>教育经历</h4>
				<hr>
				<form action="servlet/EducationServlet" method="post">
					<dl>
						<dt>时间</dt>

						<dd>
							<input type="date" style="width: 180px; height: 30px"
								placeholder="开始时间" name="entertime" id="entertime" value="">到&nbsp;

							<input type="date" style="width: 180px; height: 30px"
								placeholder="结束时间" name="gradtime" id="gradtime" value="">&nbsp;(最高学历)
							<!--  <input type="text" name="eduyear" style="width:60px;height:30px">年
						<input type="text" name="edumonth" style="width:60px;height:30px">月&nbsp;到&nbsp;
						<input type="text" name="eduyear1" style="width:60px;height:30px">年
						<input type="text" name="edumonth1" style="width:60px;height:30px">月&nbsp;(最高学历)-->
						</dd>
					</dl>
					<dl>
						<dt>学校</dt>
						<dd>
							<input type="text" name="eduschool"
								style="width: 240px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>专业</dt>
						<dd>
							<input type="text" name="edumajor"
								style="width: 240px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>学历</dt>
						<dd>
							<select name="edudiploma">
								<option value="专科">专科</option>
								<option value="本科">本科</option>
								<option value="硕士">硕士</option>
								<option value="博士">博士</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>英语等级</dt>
						<dd>
							<select name="englevel">
								<option value="未参加">未参加</option>
								<option value="未通过">未通过</option>
								<option value="英语四级">英语四级</option>
								<option value="英语六级">英语六级</option>
								<option value="专业四级">专业四级</option>
								<option value="专业八级">专业八级</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>校内职务</dt>
						<dd>
							<textarea id="eduduty" name="eduduty"
								style="width: 300px; height: 100px"
								placeholder="格式如:2012年9月-2013年6月 班长，不超过120个字"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>在校奖励</dt>
						<dd>
							<textarea id="eduaward" name="eduaward"
								style="width: 300px; height: 100px"
								placeholder="格式如:2012年9月-2013年6月 一等奖学金，不超过120个字"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>社会实践</dt>
						<dd>
							<textarea id="eduprictise" name="eduprictise"
								style="width: 300px; height: 100px"
								placeholder="格式如:2012年9月-2013年6月 xx实习，不超过120个字"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>海外学习经历</dt>
						<dd>
							<input style="cursor: pointer" type="radio" name="abroad"
								value="是">是&nbsp;
						</dd>
						<dd>
							<input style="cursor: pointer" type="radio" name="abroad"
								value="否">否
						</dd>
					</dl>
					<div class="button">
						<input style="cursor: pointer" type="submit" value="下一步"
							onclick="return myfunction()">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
