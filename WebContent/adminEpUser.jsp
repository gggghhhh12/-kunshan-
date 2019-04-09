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

<title>企业账号管理</title>

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
			
			$(".tablelist a").click(function(){
	  			if(!confirm("确定要删除吗？")){
					return;
				}
				var epUsername = $(this).attr("id");
				$.get("servlet/AdminDelEPUserServlet",{"epUsername":epUsername},function(data,statusText){
				},"json");
				$(this).parent().parent().empty();
			});
			$(".tablelist input").click(function(){
				var userlevel = $(this).val();
				var username = $(this).attr("name");
				$.get("servlet/AdminChangeLevelServlet",{"userlevel":userlevel,"username":username,"usertype":"ep"},function(data,statusText){},"json");
			});
			goPage(1,10);
		});
	</script>
  <script type="text/javascript" src="./js/fenye.js">
  </script>
</head>

<body>
	<nav>企业账号管理</nav>
	<br>
	<div class="tablelist">
		<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;">
			<tr>
				<th width="10%">用户名</th>
				<th width="15%">公司名</th>
				<th width="10%">公司代码</th>
				<th width="15%">性质</th>
				<th width="10%">公司地点</th>
				<th width="15%">公司邮箱</th>
				<th width="15%">联系电话</th>
				<th width="10%">操作</th>
			</tr>
			</table>
			<table style="width: 98%; border-collapse: collapse; border-spacing: 0;" id="idData">
			<c:forEach var="EPdata" items="${EPDatas }">
				<tr>
					<td width="10%">${EPdata.EPusername }</td>
					<td width="15%">${EPdata.EPname }</td>
					<td width="10%">${EPdata.EPcode }</td>
					<td width="15%">${EPdata.EPnature }</td>
					<td width="10%">${EPdata.EPaddr }</td>
					<td width="15%">${EPdata.EPemail }</td>
					<td width="15%">${EPdata.EPtel }</td>
					<td width="10%"><a style="cursor: pointer"
						id="${EPdata.EPusername }">删除</a> <c:if
							test='${EPlevels[EPdata.EPusername]=="0" }'>
							<input name="${EPdata.EPusername }" type="radio" value="0"
								checked="checked">普通
							<input name="${EPdata.EPusername }" type="radio" value="1">会员
						</c:if> <c:if test='${EPlevels[EPdata.EPusername]=="1" }'>
							<input name="${EPdata.EPusername }" type="radio" value="0">普通
							<input name="${EPdata.EPusername }" type="radio" value="1"
								checked="checked">会员
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
		<table width="98%" align="center">
        <tr>
        <td>
        <div id="changePages" name="changePages"></div>
        </td>
        </tr>
         </table>
	</div>
</body>
</html>
