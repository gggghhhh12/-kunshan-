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

<title>新闻管理</title>

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
</head>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			goPage(1,5);
			//添加功能
			$(".add").click(function(){
				$(".mask").show();
			});

			$(".mask .yes,.mask .no").click(function(){
				$(".mask").hide();
			});
			
			$(".mask .yes").click(function(){
				var id = $("#newsId").val();
				var title = $("#newsTitle").val();
				var content = $("#newsContent").val();
				var date = new Date();
				var time = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
				$.get("servlet/AdminAddNewsServlet",{"newsId":id,"newsTitle":title,"newsContent":content},function(data,statusText){
					alert("添加成功!");
					$(".tablelist table").append("<tr>" +
													"<td width=\"5%\"><input type=\"checkbox\" name=\"news\" value= \"" + id + "\"></input></td><td width=\"5%\">" + 
													id + "</td><td width=\"20%\">" + title + "</td><td width=\"40%\">" +content + 
													"</td><td width=\"20%\">" + time + "</td><td width=\"10%\"><a>删除</a></td>" + 
												"</tr>");
				},"json");
			});
			
			//修改功能
			$(".mask1 .yes,.mask1 .no").click(function(){
				$(".mask1").hide();
			});
			
			$(".modify").click(function(){
				$("input:checkbox").each(function(){
					if($(this).attr("checked") == "checked"){
						$(".mask1").show();
						var id = $(this).attr("value");
						var title = $(this).parent().next().next().text();
						var content = $(this).parent().next().next().next().text();
						$("#newsId1").val(id);
						$("#newsTitle1").val(title);
						$("#newsContent1").val(content);
					}
				});
			});
			
			$(".mask1 .yes").click(function(){
				var id = $("#newsId1").val();
				var title = $("#newsTitle1").val();
				var content = $("#newsContent1").val();
				$.get("servlet/AdminModifyNewesServlet",{"newsId":id,"newsTitle":title,"newsContent":content},function(data,statusText){
					alert("修改成功!");
					$("input:checkbox").each(function(){
						if($(this).attr("checked") == "checked"){
							$(this).parent().next().next().text(title);
							$(this).parent().next().next().next().text(content);
						}
					});
				},"json");
			});
			
			//删除功能
			$(".tablelist").on("click","a",function(){
	  			if(!confirm("确定要删除吗？")){
					return;
				}
				var id = $(this).parent().prev().prev().prev().prev().text();
				$.get("servlet/AdminDelNewsServlet",{"newsId":id},function(){
				},"json");
				$(this).parent().parent().empty();
			});
			
		
		});
	</script>
	<script type="text/javascript" src="./js/fenye.js"></script>
<body>
	<nav>新闻管理</nav>
	<div class="tool">
		<ul>
			<li style="cursor: pointer" class="add"><input type="button"
				value="添加"></input></li>
			<li style="cursor: pointer" class="modify"><input type="button"
				value="修改"></input></li>
		</ul>
	</div>
	<div class="tablelist">
		<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;">
			<tr>
				<th width="5%"></th>
				<th width="5%">编号</th>
				<th width="20%">新闻标题</th>
				<th width="40%">新闻内容</th>
				<th width="20%">发布时间</th>
				<th width="10%">操作</th>
			</tr>
			</table>
			<table
			style="width: 98%; border-collapse: collapse; border-spacing: 0;" id= "idData">
			<c:forEach var="n" items="${news }">
				<tr>
					<td width="5%"><input type="checkbox" name="news"
						value="${n.news_id }"></input></td>
					<td width="5%">${n.news_id }</td>
					<td width="20%">${n.news_title }</td>
					<td width="40%">${n.news_content }</td>
					<td width="20%">${n.news_time }</td>
					<td style="cursor: pointer" width="10%"><a>删除</a></td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	<div class="tablelist2">
	<table width="98%" align="center">
            <tr><td><div id="changePages" name="changePages"></div></td></tr>
        </table>
	</div>

	<div class="mask">
		<dl>
			<dt>新闻编号</dt>
			<dd>
				<input type="text" id="newsId" style="height: 30px; width: 300px"></input>
			</dd>
		</dl>
		<dl>
			<dt>新闻标题</dt>
			<dd>
				<input type="text" id="newsTitle" style="height: 30px; width: 300px"></input>
			</dd>
		</dl>
		<dl>
			<dt>新闻内容</dt>
			<dd>
				<textarea id="newsContent" style="height: 100px; width: 300px"></textarea>
			</dd>
		</dl>
		<div>
			<input style="cursor: pointer" class="yes" type="button" value="添加"></input>
			<input style="cursor: pointer" class="no" type="button" value="取消"></input>
		</div>
	</div>

	<div class="mask1">
		<dl>
			<dt>新闻编号</dt>
			<dd>
				<input type="text" id="newsId1" style="height: 30px; width: 300px"
					readonly="readonly"></input>
			</dd>
		</dl>
		<dl>
			<dt>新闻标题</dt>
			<dd>
				<input type="text" id="newsTitle1"
					style="height: 30px; width: 300px"></input>
			</dd>
		</dl>
		<dl>
			<dt>新闻内容</dt>
			<dd>
				<textarea id="newsContent1" style="height: 100px; width: 300px"></textarea>
			</dd>
		</dl>
		<div>
			<input style="cursor: pointer" class="yes" type="button" value="修改"></input>
			<input style="cursor: pointer" class="no" type="button" value="取消"></input>
		</div>
	</div>
</body>
</html>
