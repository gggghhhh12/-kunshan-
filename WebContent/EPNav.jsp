<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".flag").mouseenter(function(){
			$.get("servlet/EPShowInviteServlet",function(data,statusText){
				var invitations = data.invitations;
				var strHtml = "";
				for(var i = 0;i < invitations.length;i++ ){
						strHtml += "<p>毕业生<a href=\"servlet/StuResumeDetailServlet?stuUsername=" + invitations[i].stuUsername + 
									"\" target=\"_blank\">" + invitations[i].stuName + "</a>向您投递了简历！</p>";
				}
				$(".message").html(strHtml);
			},"json");
		});
	});
</script>
<div id="header">
	<nav id="wrap">
		<div class="logo">工大人才网</div>
		<ul>
			<li><a href="servlet/EPHomeServlet">首页</a></li>
			<li><a><font color="white">公司资料</font></a>
				<ul>
					<li><a href="EPData.jsp">填写资料</a></li>
					<li><a href="servlet/EPModifyDataServlet">修改资料</a></li>
					<li><a href="servlet/EPShowDataServlet">查看资料</a></li>
				</ul></li>
			<li><a><font color="white">职位发布</font></a>
				<ul>
					<li><a href="EPPostJob.jsp">发布职位</a></li>
					<li><a href="servlet/EPModifyPostJobServlet?res=all">修改职位</a></li>
					<li><a href="servlet/EPShowPostJobServlet?res=all">查看职位</a></li>
				</ul></li>
			<li><a href="servlet/EPShowStoreServlet">我的收藏</a></li>
			<li><a href="servlet/EPShowJobWantedServlet" class="flag">投递申请</a>
				<div class="message"></div></li>
		</ul>
		<div class="user">
			<ul>
				<li><a href="servlet/MainPageServlet">退出</a></li>
			</ul>
		</div>
	</nav>
</div>
