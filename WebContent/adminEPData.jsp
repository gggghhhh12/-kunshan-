<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>企业信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
		<%@ include file="./css/adminNewsManage.css"%>
	</style>
	
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			goPage(1,10);
			$("select").val($("body input").val());
			$(".tablelist a").click(function(){
				if($(this).text()=="通过审核"){
					if(!confirm("确定要通关审核？")){
						return;
					}
					var epUsername = $(this).attr("id");
					$.post("servlet/AdminChangeEPCheckServlet",{"epUsername":epUsername},function(){
					},"json");
					$(this).parent().parent().empty();
				}
			});
			$("select").bind("change", function() {
				$("form").submit();
		  	})
		});
	</script>
	<script src="./js/fenye.js"></script>
  </head>
  
  <body>
  <%
  String times=(String)request.getAttribute("times"); 
  %>
  <%if(times!=null){ %>
  <input type="hidden" value="<%=times%>">
    <nav>企业信息管理 
    <form action="servlet/AdminLatestEPDataServlet" method="post" style="margin:0px;display:inline;" target="rightFrame">

    	<select name="times" id="times">
	        <option value="1" selected>当天</option>   
	        <option value="3">近三天</option>   
	        <option value="7">近一周</option>   
	        <option value="31">近一个月</option>               
      	</select>  

     </form>
     </nav>
     <%}else{ %>
     <nav>企业信息管理 </nav>
     <%} %>
    <br>
	<div class="tablelist">
		<table style="width:98%; border-collapse:collapse; border-spacing:0;"> 
			<tr>
				<th width="5%">用户名</th><th width="15%">公司名</th><th width="10%">公司代码</th><th width="15%">性质</th><th width="5%">企业规模</th>
				<th width="10%">公司地点</th><th width="15%">公司邮箱</th><th width="15%">联系电话</th><th width="10%">操作</th>
			</tr>
			</table>
			<table style="width:98%; border-collapse:collapse; border-spacing:0;"id="idData">
			<%String uncheck=(String)request.getAttribute("uncheck");
			if(uncheck==null){ %>
			<c:forEach var="EPData" items="${EPDatas }">
				<tr>
					<td width="5%">${EPData.EPusername }</td><td width="15%">${EPData.EPname }</td><td width="10%">${EPData.EPcode }</td><td width="15%">${EPData.EPnature }</td><td width="5%">${EPData.EPscale }</td>
					<td width="10%">${EPData.EPaddr }</td><td width="15%">${EPData.EPemail }</td><td width="15%">${EPData.EPtel }</td><td width="10%"><a id="${EPData.EPusername }">删除</a>&nbsp;<a href = "servlet/adminModifyEPDataServlet?epusername=${EPData.EPusername}">修改</a></td>
				</tr>
			</c:forEach>
			<%}else{ %>
			<c:forEach var="EPData" items="${EPDatas }">
				<tr>
					<td width="5%">${EPData.EPusername }</td><td width="15%">${EPData.EPname }</td><td width="10%">${EPData.EPcode }</td><td width="15%">${EPData.EPnature }</td><td width="5%">${EPData.EPscale }</td>
					<td width="10%">${EPData.EPaddr }</td><td width="15%">${EPData.EPemail }</td><td width="15%">${EPData.EPtel }</td><td width="10%"><a id="${EPData.EPusername }">通过审核</a>&nbsp;<a href = "servlet/adminModifyEPDataServlet?epusername=${EPData.EPusername}">修改</a></td>
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
