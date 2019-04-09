<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>公司职位</title>

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
 
file="./css/stuShowStore.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		var i = 15;
		$(document).ready(function(){
			$(".storeContent .t5").click(function(){
				var epUsername = $(this).attr("id");
				epUsername=encodeURI(epUsername);
				$.get("servlet/StuDelStoreServlet?EPusername="+epUsername,function(data,statusText){
					
				},"json");
				$(this).parent().remove();
			});
			
			$(".recommend .change").click(function(){
				var userlevel=$("body input[name='userlevel']").val();
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
		});
	</script>
</head>

<body>
	<%String userlevel = (String)session.getAttribute("userlevel"); %>
	<div id="header">
		<%@ include file="stuNav.jsp"%>
	</div>
	<div id="mainbody">
		<div class="store">
			<h4>公司职位列表</h4>
			<hr align="center" size="2px" color="#999" noshade>
			<div class="storeContent">
				<c:forEach var="epUsername" items="${epUsernames }">
					<div class="single">
						<span class="t1"><a
							href="servlet/StuJobDetail?EPusername=${epUsername.EPusername }&jobname=${epUsername.jobname }"
							target="_blank">${epUsername.jobname }</a></span>
						<%-- <span class="t2">${epUsername.EPname }</span> --%>
						<span class="t6">${epUsername.jobaddr }</span> <span class="t4">${epUsername.salary }/月</span>
						<hr align="center" size="2px" color="#DCE0E7" noshade>
					</div>
				</c:forEach>
			</div>
			<div class="info">${info }</div>
		</div>
		<div class="recommend">
			<h4>可能感兴趣的职位</h4>
			<c:if test="${!empty recommends1 }">
				<div class="recdContent">

					<span class="s1"> <a
						href="servlet/StuJobDetail?EPusername=${recommends1[0].EPusername }&jobname=${recommends1[0].jobname }"
						target="_blank">${recommends1[0].jobname }</a>
					</span> <span class="s2">${recommends1[0].salary }</span>
					<%
					  if(userlevel.equals("0")){
					%>
					<span class="s3"> XXX公司</span>
					<%}else{ %>
					<span class="s3">${recommends1[0].EPname }</span>
					<%} %>
					<span class="s4">${recommends1[0].jobaddr }</span>
					<hr>

					<span class="s1"> <a
						href="servlet/StuJobDetail?EPusername=${recommends1[1].EPusername }&jobname=${recommends1[1].jobname }"
						target="_blank">${recommends1[1].jobname }</a>
					</span> <span class="s2">${recommends1[1].salary }</span>
					<%
					  if(userlevel.equals("0")){
					%>
					<span class="s3"> XXX公司</span>
					<%}else{ %>
					<span class="s3">${recommends1[1].EPname }</span>
					<%} %>
					<span class="s4">${recommends1[1].jobaddr }</span>
					<hr>
					<span class="s1"> <a
						href="servlet/StuJobDetail?EPusername=${recommends1[2].EPusername }&jobname=${recommends1[2].jobname }"
						target="_blank">${recommends1[2].jobname }</a>
					</span> <span class="s2">${recommends1[2].salary }</span>
					<%
					  if(userlevel.equals("0")){
					%>
					<span class="s3"> XXX公司</span>
					<%}else{ %>
					<span class="s3">${recommends1[2].EPname }</span>
					<%} %>
					<span class="s4">${recommends1[2].jobaddr }</span>
					<hr>
				</div>
			</c:if>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
