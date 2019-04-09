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

<title>我的收藏</title>

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
 
file="./css/epShowStore.css"%>
</style>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
	  	var i = 15;
	  	$(document).ready(function(){
	  		$(".storeContent .t5").click(function(){
	  			if(!confirm("确定要删除吗？")){
					return;
				}
	  			var stuUsername = $(this).attr("id");
	  			$.get("servlet/EPDelStoreServlet",{stuUsername:stuUsername},function(data,statusText){
	  				
	  			},"json");
	  			$(this).parent().remove();
	  		});
	  		
	  		$(".recommend .change").click(function(){
				var strRecommends = "${strRecommends1}";
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
						strHtml += "<span class=\"s1\"><a href=\"servlet/StuResumeDetailServlet?stuUsername=" + arr[i] + 
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
	  	});
	  </script>
</head>

<body>
	<div id="header">
		<%@ include file="EPNav.jsp"%>
	</div>
	<div id="mainbody">
		<div class="store">
			<h4>本公司收藏的毕业生</h4>
			<hr align="center" size="2px" color="#999" noshade>
			<div class="storeContent">
				<c:forEach var="stuUser" items="${stuUsers }">
					<div class="single">
						<span class="t1"><a
							href="servlet/StuResumeDetailServlet?stuUsername=${stuUser.stuUsername }"
							target="_blank">${stuUser.stuName }</a></span> <span class="t2">${stuUser.sex }</span>
						<span class="t3">${stuUser.school }</span> <span class="t4">${stuUser.major }</span>
						<span class="t5" id="${stuUser.stuUsername }">取消收藏</span>
						<hr align="center" size="2px" color="#DCE0E7" noshade>
					</div>
				</c:forEach>
			</div>
			<div class="info">${info }</div>
		</div>
		<div class="recommend">
			<h4>可能感兴趣的人</h4>
			<div class="change">换一换</div>
			<div class="recdContent">
				<span class="s1"> <a
					href="servlet/StuResumeDetailServlet?stuUsername=${recommends1[0].stuUsername }"
					target="_blank">${recommends1[0].stuName }</a>
				</span> <span class="s2">${recommends1[0].sex }</span> <span class="s3">${recommends1[0].school }</span>
				<span class="s4">${recommends1[0].major }</span>
				<hr>
				<span class="s1"> <a
					href="servlet/StuResumeDetailServlet?stuUsername=${recommends1[1].stuUsername }"
					target="_blank">${recommends1[1].stuName }</a>
				</span> <span class="s2">${recommends1[1].sex }</span> <span class="s3">${recommends1[1].school }</span>
				<span class="s4">${recommends1[1].major }</span>
				<hr>
				<span class="s1"> <a
					href="servlet/StuResumeDetailServlet?stuUsername=${recommends1[2].stuUsername }"
					target="_blank">${recommends1[2].stuName }</a>
				</span> <span class="s2">${recommends1[2].sex }</span> <span class="s3">${recommends1[2].school }</span>
				<span class="s4">${recommends1[2].major }</span>
				<hr>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
