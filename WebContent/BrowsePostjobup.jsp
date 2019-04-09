<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName

()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员职位管理</title>
    
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
		});
	</script>
    <script type="text/javascript" src="./js/fenye.js">
    </script>
  </head>
  
  <body>
    <div class="tablelist">
    <table style="width:98%; border-collapse:collapse; border-spacing:0;"> 
            <tr>
            <th width="25%">职位名</th>
            <th width="25%">学历要求</th>
            <th width="25%">招聘人数</th>
            <th width="25%">职位类别</th>
            </tr>
    </table>
    <table style="width:98%; border-collapse:collapse; border-spacing:0;" id="idData"> 
          
            <c:forEach var="browseEpDataUp" items="${browseEpDataUps}">
                <tr>
                    <td width="25%">${browseEpDataUp.jobname }</td>
                    <td width="25%">${browseEpDataUp.jobdiploma }</td>
                    <td width="25%">${browseEpDataUp.reqnum }</td>
                    <td width="25%">${browseEpDataUp.jobkind }</td>
                
                </tr>
            </c:forEach>
        </table>
        <table width="98%" align="center">
        <tr><td><div id="changePages" name="changePages"></div></td></tr>
    </table>
    </div>
  </body>
</html>
