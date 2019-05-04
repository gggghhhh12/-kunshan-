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

<title>毕业生账号管理</title>

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
 
file="./css/adminNewsManage.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
	  $(document).ready(function(){
		  goPage(1,10);
			$("form").on("submit",function(){
				var start_time=document.getElementById("start_time").value;
				var end_time=document.getElementById("end_time").value;
	          if(start_time==''||end_time==''){
	        	  alert("时间不能为空");
	        	  return false;
	          }
	          else{
	        	  alert("权限已开启");
	        	  return true;
	          }
	         });
	  		$(".tablelist a").click(function(){
	  			if($(this).text()=="删除"){
	  				if(!confirm("确定要删除吗？")){
						return;
					}
			  		var stuUsername = $(this).attr("id");
			  		$.get("servlet/AdminDelStuUser",{"stuUsername":stuUsername},function(data,statusText){
			  		},"json");
			  		$(this).parent().parent().empty();
	  			}
	  			if($(this).text()=="审核通过"){
	  				if(!confirm("确定要通过审核吗？")){
						return;
					}
			  		var stuUsername = $(this).attr("id");
			  		$.post("servlet/AdminChangeStuCheckServlet",{"username":stuUsername},function(data,statusText){
			  		},"json");
			  		$(this).parent().parent().empty();
	  			}
	  		});
			$(".tablelist input").click(function(){
				var userlevel = $(this).val();
				var username = $(this).attr("name");
				$.get("servlet/AdminChangeLevelServlet",{"userlevel":userlevel,"username":username,"usertype":"user"},function(data,statusText){},"json");
			});
		
			
	  });
	</script>
	<script type="text/javascript  " src="./js/fenye.js"></script>
</head>


<body>
	<nav>毕业生账号管理</nav>
	<br>
	<form action="servlet/AdminPerssionServlet" method="post">
	<div class="changequanxian" style="width: 98%; border-collapse: collapse; border-spacing: 0;">
	审核权限:
	<input type="date" style="width: 180px; height: 30px" placeholder="开始时间" id="start_time" name="start_time">
	<input type="date" style="width: 180px; height: 30px" placeholder="结束时间" id="end_time" name="end_time">
	<button id="btn" name="btn">确认</button>
	</div>
	</form>
	<div class="tablelist">
		<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;">
			<tr>
				<th width="20%">用户名</th>
				<th width="20%">姓名</th>
				<th width="20%">性别</th>
				<th width="20%">籍贯</th>
				<th width="20%">操作</th>
			</tr>
			</table>
			<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;" id="idData">
			<%String uncheck=(String)request.getAttribute("uncheck");
			if(uncheck==null){
			%>
			
			<c:forEach var="basicInfo" items="${basicInfos }">
				<tr>
					<td width="20%">${basicInfo.username }</td>
					<td width="20%">${basicInfo.name }</td>
					<td width="20%">${basicInfo.sex }</td>
					<td width="20%">${basicInfo.residence }</td>
					<td width="20%"><a style="cursor: pointer"
						id="${basicInfo.username }">删除</a> <c:if
							test='${userlevels[basicInfo.username]=="0" }'>
							<input name="${basicInfo.username }" type="radio" value="0"
								checked="checked">普通
							<input name="${basicInfo.username }" type="radio" value="1">会员
						</c:if> <c:if test='${userlevels[basicInfo.username]=="1" }'>
							<input name="${basicInfo.username }" type="radio" value="0">普通
							<input name="${basicInfo.username}" type="radio" value="1"
								checked="checked">会员
						</c:if></td>
				</tr>
			</c:forEach>
			<%}else{ %>
			<tr><th></th></tr>
			<c:forEach var="basicInfo" items="${basicInfos }">
				<tr>
					<td width="20%">${basicInfo.username }</td>
					<td width="20%">${basicInfo.name }</td>
					<td width="20%">${basicInfo.sex }</td>
					<td width="20%">${basicInfo.residence }</td>
					<td width="20%"><a style="cursor: pointer"
						id="${basicInfo.username }">审核通过</a></td>
				</tr>
			</c:forEach>
			<%} %>
		</table>
		<table width="98%" align="center">
            <tr><td><div id="changePages" name="changePages"></div></td></tr>
        </table>
		
	</div>
</body>
</html>
