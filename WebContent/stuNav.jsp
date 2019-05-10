<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".flag").mouseenter(function(){
			$.get("servlet/StuShowInviteServlet",function(data,statusText){
				var invitations = data.invitations;
				var strHtml = "";
				for(var i = 0;i < invitations.length;i++ ){
					strHtml += "<p><a href=\"servlet/EPjobsServlet?EPusername=" + invitations[i].epUsername + 
								"\" target=\"_blank\">" + "有公司邀请您投递简历" + "</a></p>";
				}
				$(".message").html(strHtml);
			},"json");
		});
	});
</script>
<div id="header">
	<nav id="wrap">
		<div class="logo">网络招聘系统</div>
		<ul>
			<li><a href="servlet/MainPageServlet_new">首页</a></li>
			<li><a>简历管理</a>
				<ul>
					<li class="add"><a href="basicinfo.jsp">填写简历</a></li>
					<li class="modify"><a href="servlet/stuModifyResumeServlet">修改简历</a></li>
					<li class="show"><a href="servlet/StuResumeServlet">查看简历</a></li>
				</ul></li>
			<li><a href="servlet/StuShowStoreServlet">我的收藏</a></li>
			<li><a href="servlet/StuShowStuWantedServlet" class="flag">我的邀约</a>
				<div class="message"></div></li>
		</ul>
		<div class="user">
			<ul>
				<li><a href="servlet/MainPageServlet_new">退出</a></li>
			</ul>
		</div>
		<div class="keyword">
			<!-- <form action="servlet/StuKeywordSelectServlet" method="post"> -->

			<form action="servlet/StuSearchJobByKeyWordServlet" method="post">
				<input type="text" name="keyword" class="texttype"
					placeholder="输入你要搜索的公司或职位"> <input style="cursor: pointer"
					type="submit" value="搜索" class="subtype">
			</form>
		</div>
	</nav>
</div>
