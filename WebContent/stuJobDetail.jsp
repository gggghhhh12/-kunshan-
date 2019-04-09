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

<title>职位信息</title>

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
 
file="./css/stuJobDetail.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		var i = 15;
		$(document).ready(function(){
			$(".operation .store a").click(function(){
				var epUsername = "${epData.EPusername}";
				var jobname="${epPostJob.jobname }";
				$.get("servlet/StuStoreServlet",{"epUsername":epUsername,"jobname":jobname},function(data,statusText){
					var info = data.info;
					$(".callback").text(info);
				},"json");
			});
			
			$(".recommend .change").click(function(){
				var strRecommends = "${strRecommends}";
				strRecommends = strRecommends.substring(1, strRecommends.length-1).replace(/\s+/g,"");
				var arr = strRecommends.split(",");
				var j = 0;
				var strHtml = ""; 
				if(arr != null && arr.length >= 15 ){
					$(".recommend .recdContent").empty();
					while(j < 3){
						if(i >= arr.length){
							i = 0;
						}
						strHtml += "<span class=\"s1\"><a href=\"servlet/StuJobDetail?EPusername=" + arr[i] + "&jobname="+arr[i+1]+
									"\" target=\"_blank\">" + arr[i+1] + "</a></span>" +
									"<span class=\"s2\">" + arr[i+2] + "</span>" +
									"<span class=\"s3\">" + arr[i+3] + "</span>" +
									"<span class=\"s4\">" + arr[i+4] + "</span>" + "<hr>";
						j++;
						i += 5;
					}
					$(".recommend .recdContent").html(strHtml);
				}
			});
			
			$(".operation input").click(function(){
				var epUsername = "${epData.EPusername}";
				var epJobname="${epPostJob.jobname }";
				$.get("servlet/StuJobWantedServlet",{"epUsername":epUsername,"epJobname":epJobname},function(data,statusText){
					var info = data.info;
					$(".callback").text(info);
				},"json");
			});
			
			//评论功能
			$(".comment #btnComment").click(function(){
				$(".comment .content").slideDown("slow");
			});

			$(".comment #btnConfirm").click(function(){
				var stuUsername = "${stuUser }";
				var epUsername = "${epData.EPusername }";
				var relation = $("input[name='relation']:checked").val();
				var text = $(".comment .content textarea").val();
				var date = new Date();
				var time = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
				$.get("servlet/StuAddCommentServlet",{"stuUsername":stuUsername,"epUsername":epUsername,"relation":relation,"content":text},function(data,statusText){
					
				},"json");
				var strHtml = "<div class=\"single\">" +
							"<span class=\"s1\">" + stuUsername + "</span><span class=\"s2\">与该公司关系：" + relation + "</span>" +
							"<div class=\"text\">" + text + "</div>" +
							"<div class=\"time\">" + time + "</div>" +
							"<hr align=\"center\" size=\"1px\" color=\"#bbb\" noshade>" +
							"</div>";
				$(".comment .list").prepend(strHtml);
				$(".comment .content").slideUp("slow");
			});

			$(".comment #btnCancel").click(function(){
				$(".comment .content").slideUp("slow");
			});
			
			//举报功能
			$(".title .report").click(function(){
				var epUsername = "${epData.EPusername }";
				$.get("servlet/StuReportServlet",{"epUsername":epUsername},function(data,statusText){
					var reportMsg = data.reportMsg;
					$(".title .msg").text(reportMsg);
				},"json");
			});
		});
	</script>
</head>

<body>
	<div id="header">
		<%@ include file="stuNav.jsp"%>
	</div>
	<div id="mainbody">
		<div class="leftInfo">
			<div class="epInfo">
				<%  String userlevel = (String)session.getAttribute("userlevel");
					if(userlevel.equals("0"))
					{
				%>
				<h3>XXXX公司</h3>
				<%}else{%>
				<h3>${epData.EPname }</h3>
				<%} %>
				<div class="info">
					<p>规模：${epData.EPscale }</p>
					<p>行业：${epData.EPtrade }</p>
					<p>性质：${epData.EPnature }</p>
					<p>地址：${epData.EPaddr }</p>
				</div>
				<hr align="center" size="4px" width="230px" color="#DCE0E7" noshade>
				<div class="operation">
					<span><input style="cursor: pointer" type="button"
						value="申请职位"></input></span> <span class="store"><a>收藏</a></span>
				</div>
				<div class="callback"></div>
			</div>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<div class="recommend">
				<h4>相似的职位</h4>
				<!-- <div class="change">换一换</div> -->
				<c:if test="${!empty recommends }">
					<div class="recdContent">

						<span class="s1"> <a
							href="servlet/StuJobDetail?EPusername=${recommends[0].EPusername }&jobname=${recommends[0].jobname }"
							target="_blank">${recommends[0].jobname }</a>
						</span> <span class="s2">${recommends[0].salary }</span>
						<% 
					if(userlevel.equals("0"))
					{
					%>
						<span class="s2">XXX公司</span>
						<%}else{ %>
						<span class="s3">${recommends[0].EPname }</span>
						<%} %>
						<span class="s4">${recommends[0].jobaddr }</span>
						<hr>
						<span class="s1"> <a
							href="servlet/StuJobDetail?EPusername=${recommends[1].EPusername }&jobname=${recommends[1].jobname }"
							target="_blank">${recommends[1].jobname }</a>
						</span> <span class="s2">${recommends[1].salary }</span>
						<% 
					if(userlevel.equals("0"))
					{
					%>
						<span class="s2">XXX公司</span>
						<%}else{ %>
						<span class="s3">${recommends[1].EPname }</span>
						<%} %>
						<span class="s4">${recommends[1].jobaddr }</span>

						<hr>
						<span class="s1"> <a
							href="servlet/StuJobDetail?EPusername=${recommends[2].EPusername }&jobname=${recommends[2].jobname }"
							target="_blank">${recommends[2].jobname }</a>
						</span> <span class="s2">${recommends[2].salary }</span>
						<% 
					if(userlevel.equals("0"))
					{
					%>
						<span class="s2">XXX公司</span>
						<%}else{ %>
						<span class="s3">${recommends[2].EPname }</span>
						<%} %>
						<span class="s4">${recommends[2].jobaddr }</span>

						<hr>
					</div>
				</c:if>
			</div>
		</div>
		<div class="rightInfo">
			<div class="title">
				<div class="jobname">
					<h1>${epPostJob.jobname }</h1>
				</div>
				<div class="report">举报</div>
				<div class="msg"></div>
				<div class="salary">
					<h1>${epPostJob.jobsalary }/月</h1>
				</div>
			</div>
			<div class="detail">
				<span class="postdate">发布于${epPostJob.postdate }</span>
			</div>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<div class="benefits">
				<span class="t1">公司福利</span>
				<c:forEach var="benefit" items="${benefits }">
					<span class="t2">${benefit }</span>
				</c:forEach>
			</div>
			<div class="diploma">
				<span class="t1">学历要求</span> <span class="t2">${epPostJob.jobdiploma }</span>
			</div>
			<div class="engreq">
				<span class="t1">英语能力要求</span> <span class="t2">${epPostJob.engrequest }</span>
			</div>
			<div class="reqnum">
				<span class="t1">招聘人数</span> <span class="t2">${epPostJob.reqnum }人</span>
			</div>
			<h3>职位详情</h3>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<dl>
				<dt>职位描述</dt>
				<dd>${describe }</dd>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>岗位职责
				<dt>
				<dd>${duty }</dd>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>任职要求</dt>
				<dd>${request }</dd>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>

				<dt>公司电话</dt>
				<% 
					System.out.print(userlevel);
					if(userlevel.equals("0")){
				%>
				<dd>XXXXXXXXXXXXXXX</dd>
				<%}else{ %>
				<dd>${epData.EPtel }</dd>
				<%} %>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>公司邮箱</dt>
				<% if(userlevel.equals("0")) {%>
				<dd>XXXXXXXXXXXXXXXXXXXXX</dd>
				<%}else{ %>
				<dd>${epData.EPemail }</dd>
				<%} %>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>上班地址</dt>
				<dd>${epPostJob.jobaddr }</dd>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>联系人</dt>
				<dd>${epData.EPcontact }</dd>
			</dl>
			<hr align="center" size="2px" color="#DCE0E7" noshade>
			<dl>
				<dt>联系人电话</dt>
				<% if(userlevel.equals("0")) {%>
				<dd>XXXXXXXXXXXXXXXXXXXXX</dd>
				<%}else{ %>
				<dd>${epData.EPmobile }</dd>
				<%} %>
			</dl>
			<h3>公司信息</h3>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<div class="epinfo">${epInfo }</div>
			<hr align="center" size="4px" color="#DCE0E7" noshade>
			<br>
			<br>

			<%--评论功能 --%>
			<div class="comment">
				<h2>公司点评</h2>
				<input id="btnComment" type="button" value="我要点评"></input>
				<div class="content">
					<h4>与被评价公司的关系</h4>
					<input type="radio" name="relation" value="在该公司工作过">在该公司工作过
					<input type="radio" name="relation" value="员工好友">员工好友 <input
						type="radio" name="relation" value="合作伙伴">合作伙伴 <input
						type="radio" name="relation" value="客户">客户 <input
						type="radio" name="relation" value="关注者">关注者
					<textarea placeholder="请写下您的点评，50字以内"></textarea>
					<input id="btnConfirm" type="button" value="提交"></input> <input
						id="btnCancel" type="button" value="取消"></input>
					<hr align="center" size="4px" color="#bbb" noshade>
				</div>
				<div class="list">
					<c:forEach var="comment" items="${comments }">
						<div class="single">
							<span class="s1">${comment.stuUsername }</span><span class="s2">与该公司关系：${comment.relation }</span>
							<div class="text">${comment.content }</div>
							<div class="time">${comment.date }</div>
							<hr align="center" size="1px" color="#bbb" noshade>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
