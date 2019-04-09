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
            $("select").val($("body input").val());
            $(".tablelist a").click(function(){
                if($(this).text()=="通过审核"){
                    if(!confirm("确定要通过审核吗？")){
                        return;
                    }
                    var jobname = $(this).attr("name");
                    var epUsername = $(this).attr("id");
                    var jobname = $(this).attr("name");
                    $.post("servlet/AdminChangeJobCheckServlet",

{"EPusername":epUsername,"jobname":jobname},function(data,statusText){
                    },"json");
                    $(this).parent().parent().empty();
                }
                if($(this).text()=="删除"){
                    if(!confirm("确定要删除吗？")){
                        return;
                    }
                    var jobname = $(this).attr("name");
                    var epUsername = $(this).attr("id");
                    $.get("servlet/AdminDelEPPostJobServlet",

{"epUsername":epUsername},function(data,statusText){
                    },"json");
                    $(this).parent().parent().empty();
                }
            });
            $("select").bind("change", function() {
                $("form").submit();
            })
        });
    </script>
    <script type="text/javascript" src="./js/fenye.js">
    </script>
  </head>
  
  <body>
    <%String times=(String)request.getAttribute("times"); 
  %>
  <%if(times!=null){ %>
  <input type="hidden" value="<%=times%>">
    <nav>企业职位管理 
    <form action="servlet/AdminLatestJobServlet" method="post" style="margin:0px;display:inline;" target="rightFrame">

        <select name="times" id="times">
            <option value="1" selected>当天</option>   
            <option value="3">近三天</option>   
            <option value="7">近一周</option>   
            <option value="31">近一个月</option>               
        </select>  

     </form>
     </nav>
     <%}else{ %>
     <nav>企业职位管理 </nav>
     <%} %>
    <br>
    <div class="tablelist">
    <table style="width:98%; border-collapse:collapse; border-spacing:0;"> 
            <tr><th width="10%">公司用户名</th><th width="15%">职位名</th><th width="10%">薪水</th><th width="15%">学历要求</th>
                <th width="10%">招聘人数</th><th width="10%">职位类别</th><th width="10%">上班地点</th><th width="10%">操作</th>
            </tr>
    </table>
    <table style="width:98%; border-collapse:collapse; border-spacing:0;" id="idData"> 
            <% String uncheck=(String)request.getAttribute("uncheck");
            if(uncheck!=null){
            %>
            <c:forEach var="EPPostjob" items="${EPPostjobs}">
                <tr>
                    <td width="10%">${EPPostjob.EPusername}</td>
                    <td width="15%">${EPPostjob.jobname }</td>
                    <td width="10%">${EPPostjob.jobsalary }</td>
                    <td width="15%">${EPPostjob.jobdiploma }</td>
                    <td width="10%">${EPPostjob.reqnum }</td>
                    <td width="10%">${EPPostjob.jobkind }</td>
                    <td width="10%">${EPPostjob.jobaddr }</td>
                    <td width="10%">
                        <a name="${EPPostjob.jobname }" id="${EPPostjob.EPusername }">通过审核</a>&nbsp;
                        <a href = "servlet/AdminModifyEPPostJobServlet?epusername=${EPPostjob.EPusername }&jobname=${EPPostjob.jobname }">修改</a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
            <%}else{ %>
                <c:forEach var="EPPostjob" items="${EPPostjobs}">
                <tr>
                     <td width="10%">${EPPostjob.EPusername}</td>
                    <td width="15%">${EPPostjob.jobname }</td>
                    <td width="10%">${EPPostjob.jobsalary }</td>
                    <td width="15%">${EPPostjob.jobdiploma }</td>
                    <td width="10%">${EPPostjob.reqnum }</td>
                    <td width="10%">${EPPostjob.jobkind }</td>
                    <td width="10%">${EPPostjob.jobaddr }</td>
                    <td width="10%">
                        <a name="${EPPostjob.jobname }" id="${EPPostjob.EPusername}">删除</a>&nbsp;
                        <a href ="servlet/AdminModifyEPPostJobServlet?epusername=${EPPostjob.EPusername }&jobname=${EPPostjob.jobname }">修改</a>&nbsp;
                    </td>
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
