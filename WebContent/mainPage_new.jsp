<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="priv.zx.ecruit.model.News"%>
<%@page import="priv.zx.ecruit.dao.NewsDao"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
<style type="text/css">
<%@include file="./css/mystyle.css"%>
</style>
<style type="text/css">
<%@include file="./css/mainPage.css"%>
</style>
<style type="text/css">
<%@include file="./css/link.css"%>
</style>
<style type="text/css">
<%@include file="./css/mainPageTop.css"%>
</style>
<style type="text/css">
<%@include file="./css/mainPageSearch.css"%>
</style>
<style type="text/css">
<%@include file="./css/mainPageFooter.css"%>
</style>
<style type="text/css">
<%@ include file="./css/test.css"%>
<%@include file="./css/demo.css"%>
</style>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/link.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var msg = $("#msg").val();
		if (msg != 'null' && msg != 'undefined' && msg != "") {
			alert(msg);
		}
		$(".job_list2").hide();
		$("#hot_tab").css("color", "red");
		$("#hot-company").css("color", "blue");
		$("#hot_tab").click(function() {
			$("#hot_tab").css("color", "red");
			$("#new_tab").css("color", "darkgrey");
			$(".job_list1").show();
			$(".job_list2").hide();
		});
		$("#new_tab").click(function() {
			$("#hot_tab").css("color", "darkgrey");
			$("#new_tab").css("color", "red");
			$(".job_list2").show();
			$(".job_list1").hide();
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var i = 0;
		var clone = $(".banner .img li").first().clone();//克隆第一张图片
		$(".banner .img").append(clone);//复制到列表最后
		var size = $(".banner .img li").size();

		for (var j = 0; j < size - 1; j++) {
			$(".banner .num").append("<li></li>");
		}

		$(".banner .num li").first().addClass("on");

		/*自动轮播*/

		var t = setInterval(function() {
			i++;
			move();
		}, 2000);

		/*鼠标悬停事件*/

		$(".banner").hover(function() {
			clearInterval(t);//鼠标悬停时清除定时器
		}, function() {
			t = setInterval(function() {
				i++;
				move();
			}, 2000); //鼠标移出时清除定时器
		});
		/*鼠标滑入原点事件*/
		$(".banner .num li").hover(function() {

			var index = $(this).index();//获取当前索引值
			i = index;
			$(".banner .img").stop().animate({
				left : -index * 500
			}, 500);
			$(this).addClass("on").siblings().removeClass("on");
		});

		/*向左按钮*/
		$(".banner .btn_l").click(function() {
			i--;
			move();
		})

		/*向右按钮*/
		$(".banner .btn_r").click(function() {
			i++;
			move();
		})

		/*移动事件*/
		function move() {
			if (i == size) {
				$(".banner .img").css({
					left : 0
				});
				i = 1;
			}
			if (i == -1) {
				$(".banner .img").css({
					left : -(size - 1) * 768
				});
				i = size - 2;
			}
			$(".banner .img").stop().animate({
				left : -i * 768
			}, 768);

			if (i == size - 1) {
				$(".banner .num li").eq(0).addClass("on").siblings().removeClass("on");
			} else {
				$(".banner .num li").eq(i).addClass("on").siblings().removeClass("on");
			}
		}
	});
</script>
</head>
<body>
	<input type="hidden" id="msg"
		value="<%=(String) request.getAttribute("msg")%>">
	<div id="header">
		<nav>
			<div class="shouye">
				<a href="servlet/MainPageServlet"><img src="./images/logo.jpg"
					width="120" height="50"></a> <a href="stuRegister.jsp">个人版 </a><font
					color="white">|</font> <a href="EPRegister.jsp">企业版</a>
			</div>
			<ul>
				<li><a>注册</a>
					<ul>
						<li><a href="stuRegister.jsp">毕业生注册</a></li>
						<li><a href="EPRegister.jsp">企业注册</a></li>
					</ul></li>
				<li><a>登录</a>
					<ul>
						<li><a href="stuLogin.jsp">毕业生登录</a></li>
						<li><a href="EPLogin.jsp">企业登录</a></li>
						<li><a href="adminLogin.jsp">管理员登录</a></li>
					</ul></li>
				<li><a href="CompanyIntroduce.html"><font color="white">了解我们</font></a></li>
			</ul>
		</nav>
	</div>
	<div id="tnav">

		<div class="inner">
			<div class="tnav_l">
				<a href="mainPage.jsp" class="logo">
					<h1>人才网</h1>
				</a>
				<div class="suggestCity">
					<strong>全国站</strong>
					<!--<em id="changeCity_btn">[切换城市]</em> -->
				</div>
			</div>
			<ul class="tnav_wrap">
				<li><a class="current" href="mainPage.jsp">首页</a></li>
				<li><a rel="nofollow" href="EPRegister.jsp">公司</a></li>
				<li><a rel="nofollow" href="stuRegister.jsp">校园</a></li>
				<li><a rel="nofollow" href="stuRegister.jsp">评论</a></li>
			</ul>

		</div>
	</div>
	<!-- 页面搜索START -->
	<div class="container clearfix  has-login-toolbar " id="container">
		<div class="contain-header">
			<!-- 搜索 -->
			<div class="search-wrapper">
				<div class="search_box">
					<form id="searchForm" class="searchForm clearfix" name="searchForm"
						action="servlet/StuSearchJobByKeyWordServlet" method="post">
						<input type="text" id="search_input" class="search_input"
							tabindex="1" maxlength="64" name="keyword" autocomplete="off" value=""
							placeholder="搜索职位、公司或地点" />
							 <input type="submit" id="search_button" class="search_button" value="搜索" />
					</form>
					<input type="hidden" id="search_py" value="" /> <input
						type="hidden" id="isIndex" value="true" />
		
				<dl class="hotSearch">
					<dt>热门搜索</dt>
					<dd>
						<a href="stuRegister.jsp" class="highlight" target="_blank">java</a>
					</dd>
					<dd>
						<a href="stuRegister.jsp" class="highlight" target="_blank">产品经理</a>
					</dd>
					<dd>
						<a href="stuRegister.jsp" class="highlight" target="_blank">区块链</a>
					</dd>
					<dd>
						<a href="stuRegister.jsp" class="highlight" target="_blank">c++</a>
					</dd>
				</dl>
			</div>
		</div>




		<div class="container-body">
			<div class="melon">
				<div class="dorp">
					<div class="left_list">
						<div class="left_jishu">
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">
								技术</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">Java</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">PHP</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">c++</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">
								区块链</div>
							<div class="jishumenu1"
								style="width: 40px; background-color: #fff; float: left;">&nbsp;</div>
							<div class="jishumenu1"
								style="width: 40px; background-color: #fff; float: left; margin-top: 16px;">
								<img src="./images/2.png" style="float: left;">
							</div>
							<div class="dorp_con">
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">后端开发</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">Java</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">C++</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">PHP</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">数据挖掘</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">搜索算法</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">精确推荐</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">C</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">移动开发</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">HTML5</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">Android</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">IOS</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">WP</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">移动开发其他</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">前端开发</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">web前端</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">Flash</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">html5</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">JavaScript</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">U3D</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">COCOS2D-X</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">前端开发其他</a>
									</dd>
								</dl>
							</div>
							<div class="shade"></div>
							<div style="clear: both;"></div>
						</div>
						<div class="left_chanpin">
							<div class="jishumenu"
								style="width: 70px; background-color: #fff; float: left;">
								产品</div>
							<div class="jishumenu1"
								style="width: 90px; background-color: #fff; float: left;">
								产品总监</div>
							<div class="jishumenu1"
								style="width: 90px; background-color: #fff; float: left;">
								产品经理</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">&nbsp;</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">&nbsp;</div>

							<div class="jishumenu1"
								style="width: 40px; background-color: #fff; float: left; margin-top: 16px;">
								<img src="./images/2.png" style="float: left;">
							</div>
							<div class="dorp_con2">
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">产品经理</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">网页产品经理</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">移动产品经理</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">数据产品经理</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">游戏策划</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">产品设计师</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">网页产品设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">无线产品设计师</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">高端职位</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">产品部经理</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">产品总监</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">游戏制作人</a>
									</dd>
								</dl>

							</div>
							<div class="shade1"></div>
							<div style="clear: both;"></div>
						</div>
						<div class="left_sheji">
							<div class="jishumenu"
								style="width: 70px; background-color: #fff; float: left;">
								设计</div>
							<div class="jishumenu1"
								style="width: 90px; background-color: #fff; float: left;">UI设计师</div>
							<div class="jishumenu1"
								style="width: 90px; background-color: #fff; float: left;">交互设计</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">&nbsp;</div>
							<div class="jishumenu1"
								style="width: 70px; background-color: #fff; float: left;">&nbsp;</div>

							<div class="jishumenu1"
								style="width: 40px; background-color: #fff; float: left; margin-top: 16px;">
								<img src="./images/2.png" style="float: left;">
							</div>
							<div class="dorp_con3">
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">视觉设计</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">视觉设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">网页设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">Flash设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">UI设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">APP设计师</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">交互设计</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">交互设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">无线交互设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">网页交互设计师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">硬件交互设计师</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<span
											style="position: relative; display: inline-block; padding: 0; color: #333; font-size: 14px;">用户研究</span>
									</dt>
									<dd>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">数据分析师</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">用户研究员</a>
										<a class="curr"
											style="display: inline-block; position: relative; margin-left: -1px; padding: 11px; font-size: 14px; line-height: 28px; color: #777; white-space: nowrap;">游戏数值策划</a>
									</dd>
								</dl>

							</div>
							<div class="shade2"></div>
							<div style="clear: both;"></div>
						</div>
					</div>
				</div>

				<div class="banner">
					<ul class="img">
						<li><img src="./images/pit1.png" alt="" /></li>
						<li><img src="./images/pit2.png" alt="" /></li>
						<li><img src="./images/pit3.png" alt="" /></li>
					</ul>
					<ul class="num">
					</ul>
					<div class="btn btn_l">&lt;</div>
					<div class="btn btn_r">&gt;</div>
				</div>
				<div class="clear"></div>
			</div>

			<div class="jobcom">
				<ul class="job_tab">
				<li><a class="hot_tab" id="hot_tab">最新职位</a></li>
					<li><a class="new_tab" id="new_tab">热门职位</a></li>
				
				</ul>
				<!-- 最新职位 -->
				<div class="job_list_wrapper">
					<div class="job_list1" style="display: block">
						<ul class="position_list_ul">
							<c:forEach var="newJob" items="${newJobs }" begin="0" end="5">
								<li>
								    <div class="position_list_li">
								        <div class="position_li_top">
								            <div class="position_name"><a href="javascript:location.href=encodeURI('servlet/StuJobDetail?EPusername=${newJob.EPusername }&jobname=${newJob.jobname }')"  target="_blank">${newJob.jobname}</a></div>
								            <span class="salary">${newJob.jobsalary}</span>
								            <div class="position_info">${newJob.jobdiploma}</div>
								            <div class="labels">
								                <c:forEach var="wordCut" items="${newJob.benefits}">
								                    <span class="wordCut">${wordCut}</span>
								                </c:forEach>
								            </div>
								            <div></div>
								        </div>
								        <div class="position_li_bottom">
								            <img src="./images/boshi.png" width="50px" height="50px">
								            <div>
								                <div class="company_name">${newJob.EPname }</div>
								                <div class="company_wordcut"></div>
								            </div>
								        
								        </div>
								    </div>
								</li>
							</c:forEach>
						</ul></div>
						</div>
<!-- 热门职位 -->
					<div class="job_list2" style="display: block">
						<ul class="position_list_ul">
							<c:forEach var="hotJob" items="${hotJobs}" begin="0" end="5">
								<li>
								    <div class="position_list_li">
								        <div class="position_li_top">
								            <div class="position_name"><a href="servlet/StuJobDetail?EPusername=${hotJob.EPusername }&jobname=${hotJob.jobname }" target="_blank">${hotJob.jobname}</a></div>
								            <span class="salary">${hotJob.jobsalary}</span>
								            <div class="position_info">${hotJob.jobdiploma}</div>
								            <div class="labels">
								                <c:forEach var="wordCut" items="${hotJob.benefits}">
								                    <span class="wordCut">${wordCut}</span>
								                </c:forEach>
								            </div>
								            <div></div>
								        </div>
								        <div class="position_li_bottom">
								            <img src="./images/boshi.png" width="50px" height="50px">
								            <div>
								                <div class="company_name">${hotJob.EPname }</div>
								                <div class="company_wordcut"></div>
								            </div>
								        
								        </div>
								    </div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="list_more_parent">
					<a class="list_more">查看更多</a>
				</div>
				<ul class="job_tab">
					<li><a class="high-job">高端职位</a></li>
				</ul>
<!-- 高端职位 -->
				<div class="job_list_wrapper">
					<div class="job_list" style="display: block">
						<ul class="position_list_ul">
							<c:forEach var="highSalary" items="${highSalarys}" begin="0" end="2">
								<li>
								    <div class="position_list_li">
								        <div class="position_li_top">
								            <div class="position_name"><a href="servlet/StuJobDetail?EPusername=${highSalary.EPusername }&jobname=${highSalary.jobname }" target="_blank">${highSalary.jobname}</a></div>
								            <span class="salary">${highSalary.jobsalary}</span>
								            <div class="position_info">${highSalary.jobdiploma}</div>
								            <div class="labels">
								                <c:forEach var="wordCut" items="${highSalary.benefits}">
								                    <span class="wordCut">${wordCut}</span>
								                </c:forEach>
								            </div>
								            <div></div>
								        </div>
								        <div class="position_li_bottom">
								            <img src="./images/boshi.png" width="50px" height="50px">
								            <div>
								                <div class="company_name">${highSalary.EPname }</div>
								                <div class="company_wordcut"></div>
								            </div>
								        
								        </div>
								    </div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="list_more_parent">
					<a class="list_more">查看更多</a>
				</div>
				<ul class="company_tab">
					<li><a id="hot-company">热门公司</a></li>
				</ul>
				<div class="company_list_wrapper">
					<div class="company_list">
						<ul class="company_list_ul">
						<c:forEach var="vipEp" items="${vipEps}" begin="0" end="5">
							<li class="company_list_li">
								
								<div class="top">
									<a> <img src="./images/ande.png" alt="公司Logo" width="80"
										height="80">
									</a>
									<p class="company_name">${vipEp.EPname }</p>
									<p>
										<span>${vipEp.EPnature } </span>
									</p>
									<p class="advantage">
										${vipEp.EPintroduction }</p>
								</div>
								<div class="bottom">
									<!--  <p class="green">5个</p>-->
									<p>${vipEp.count }</p>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="list_more_parent">
					<a class="list_more">查看更多</a>
				</div>

			</div>
			<div class="news2">
				<div class="news">
					<div class="jobnews"
						style="float: left; width: 45%; margin: 0; padding: 0; position: relative; z-index: 1003;">
						<ul class="jobtip"
							style="margin-top: 52px; margin-bottom: 20px; border-bottom: 1px solid dimgray;">
							<li
								style="display: inline-block; padding: 14px 0; margin-right: 58px; font-size: 16px; color: darkgrey;">
								<a id="job_news" style="color: orange;">行业资讯</a>
							</li>
						</ul>
					</div>
					<div class="newest"
						style="float: right; width: 45%; margin: 0; padding: 0; position: relative; z-index: 1003;">
						<ul class="jobnewest"
							style="margin-top: 52px; margin-bottom: 20px; border-bottom: 1px solid dimgray;">
							<li
								style="display: inline-block; padding: 14px 0; margin-right: 58px; font-size: 16px; color: darkgrey;">
								<a id="latest_news" style="color: orange;">最新通知</a>
							</li>
						</ul>
					</div>
				</div>

				<div class="newscontent">
					<div class="jobc"
						style="float: left; width: 45%; margin: 0; padding: 0; position: relative; z-index: 1003;">
						<ul class="tc"
							style="margin-top: 5px; margin-bottom: 20px; border: 1px solid #EAEEED;">
							<li
								style="display: inline-block; padding: 14px 0; margin-right: 58px; font-size: 16px; color: darkgrey;">
							</li>
						</ul>
					</div>
					<div class="newc"
						style="float: right; width: 45%; margin: 0; padding: 0; position: relative; z-index: 1003;">
						<ul class="jc"
							style="margin-top: 5px; margin-bottom: 20px; border: 1px solid #EAEEED;">
							<li
								style="display: inline-block; padding: 14px 0; margin-right: 58px; font-size: 16px; color: darkgrey;">
							</li>
						</ul>
					</div>
				</div>


			</div>
			<div class="linkbox showlinkbox" id="linkbox">
				<span class="current">友情链接</span>
				<dl>
					<dt></dt>
					<dd class="links">
						<a href="stuRegister.jsp">拉勾网</a> <a href="stuRegister.jsp">拉勾网</a>
						<a href="stuRegister.jsp">拉勾网</a> <a href="stuRegister.jsp">拉勾网</a>
					</dd>
					<span rel="nofollow" href="javascript:;" class="expansion">展开<i></i></span>
				</dl>
			</div>
		</div>
	</div>
	<!-- 页脚start -->
	<div id="footer">
		<div class="wrapper">
			<div class="wechat">
				<div class="footer_column">
					<div class="footer_item item_code">

						<img src="./images/timg.jpg" width="130" height="130">
						人才网公众号
					</div>
				</div>
				<div class="footer_right">
					<div class="footer_business">
						<p class="footer_title">企业服务</p>
						<a rel="nofollow">公司搜索</a> <a rel="nofollow">行业大家说</a> <a
							rel="nofollow">线下活动</a> <a rel="nofollow">高校联系</a>
					</div>
					<div class="footer_business">
						<p class="footer_title">用户帮助</p>
					</div>
					<div class="footer_business">
						<p class="footer_title">关于我们</p>
						<span class="tel">服务热线：0451 8626 6861 (8:30 -17:30)</span> 
						<span class="tel">企业服务邮箱：hitzhineng@163.com</span>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
