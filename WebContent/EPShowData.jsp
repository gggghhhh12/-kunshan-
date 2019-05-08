<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查看公司资料</title>

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
 
file="./css/stuResume.css"%>
</style>
</head>

<body>
	<div id="header">
		<%@ include file="EPNav.jsp"%>
	</div>
	<div id="mainbody">
		<div class="title">
			<h2>查看公司资料</h2>
		</div>
		<div class="info">
			<!--公司资料-->
			<div class="basicinfo">
				<h4>公司资料</h4>
				<hr>
				<dl>
					<dt>公司名称:</dt>
					<dd>${EPData.EPname }</dd>
				</dl>
				<dl>
					<dt>公司性质:</dt>
					<dd>${EPData.EPnature }</dd>
				</dl>
				<dl>
					<dt>公司机构代码:</dt>
					<dd>${EPData.EPcode }</dd>
				</dl>
				<dl>
					<dt>所属行业:</dt>
					<dd>${EPData.EPtrade }</dd>
				</dl>
				<dl>
					<dt>公司规模:</dt>
					<dd>${EPData.EPscale }</dd>
				</dl>
				<dl>
					<dt>公司所在地:</dt>
					<dd>${EPData.EPaddr }</dd>
				</dl>
				<dl>
					<dt>联系人:</dt>
					<dd>${EPData.EPcontact }</dd>
				</dl>
				<dl>
					<dt>电子邮箱:</dt>
					<dd>${EPData.EPemail }</dd>
				</dl>
				<dl>
					<dt>固定电话:</dt>
					<dd>${EPData.EPtel }</dd>
				</dl>
				<dl>
					<dt>手机号码:</dt>
					<dd>${EPData.EPmobile }</dd>
				</dl>
				<dl>
					<dt>邮政编码:</dt>
					<dd>${EPData.EPpostalcode }</dd>
				</dl>
				<dl>
					<dt class="top">公司简介:</dt>
					<dd>${introduction }</dd>
				</dl>
				<dl>
				    <dt>企业logo:</dt>
				    <dd><img src="/picupload/${EPData.EPlogo}" width=100px height=100px></dd>
				</dl>
				
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
