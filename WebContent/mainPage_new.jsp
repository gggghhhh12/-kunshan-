<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="priv.zx.ecruit.model.News"%>
<%@page import="priv.zx.ecruit.dao.NewsDao"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
						action="servlet/stuLoginServlet" method="get">
						<input type="text" id="search_input" class="search_input"
							tabindex="1" maxlength="64" autocomplete="off" value=""
							placeholder="搜索职位、公司或地点" /> <input type="submit"
							id="search_button" class="search_button" value="搜索" />
					</form>
					<input type="hidden" id="search_py" value="" /> <input
						type="hidden" id="isIndex" value="true" />
				</div>
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
					<li><a class="hot_tab" id="hot_tab">热门职位</a></li>
					<li><a class="new_tab" id="new_tab">最新职位</a></li>
				</ul>
				<div class="job_list_wrapper">
					<div class="job_list1" style="display: block">
						<ul class="position_list_ul">
							<c:forEach var="EPPostJob" items="${EPPostJobs}" begin="1" end="6">
								<li>
								    <div class="position_list_li">
								        <div class="position_li_top">
								            <div class="position_name"><a href="servlet/StuJobDetail?EPusername=${EPPostJob.EPusername }&jobname=${EPPostJob.jobname}">${EPPostJob.jobname}</a></div>
								            <span class="salary">${EPPostJob.jobsalary}</span>
								            <div class="position_info">${EPPostJob.jobdiploma}</div>
								            <div class="labels">
								                <c:forEach var="wordCut" items="${EPPostJob.benefits}">
								                    <span class="wordCut">${wordCut}</span>
								                </c:forEach>
								            </div>
								            <div></div>
								        </div>
								        <div class="position_li_bottom">
								            <img src="./images/boshi.png" width="50px" height="50px">
								            <div>
								                <div class="company_name">${EPPostJob.EPname }</div>
								                <div class="company_wordcut"></div>
								            </div>
								        
								        </div>
								    </div>
								</li>
							</c:forEach>
						</ul>
						</div>

					<div class="job_list2" style="display: block">
						<ul class="position_list_ul">
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">电气工程师</div>
									<span class="salary"> 4.5k-8k </span>
									<div class="position_info">本科</div>
									<div class="labels">
										<span class="wordCut">工业自动化</span> <span class="wordCut">电气自动化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/wenhong.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">江苏省文洪机械股份有限公司</div>
										<div class="company_wordcut">全自动平压平模切机、全自动平压平烫金机、江苏</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">电气工程师</div>
									<span class="salary"> 10k-12k </span>
									<div class="position_info">硕士</div>
									<div class="labels">
										<span class="wordCut">电气工程</span> <span class="wordCut">自动化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/jixing.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">江苏吉星新材料有限公司</div>
										<div class="company_wordcut">蓝宝石晶体生长、晶体加工、江苏</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">产品设计</div>
									<span class="salary"> 薪资面议 </span>
									<div class="position_info">本科</div>
									<div class="labels">
										<span class="wordCut">电气工程自动化</span> <span class="wordCut">机械设计制造及自动化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/zhenjiang.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">江苏省镇江船厂（集团）有限公司</div>
										<div class="company_wordcut">三维设计、精度造船、智能焊接、江苏</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">电气工程师</div>
									<span class="salary"> 4.4k-10k </span>
									<div class="position_info">本科</div>
									<div class="labels">
										<span class="wordCut">电气自动化</span> <span class="wordCut">机电一体化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/gongdajiqiren.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">工大机器人集团泰州智能制造研究院</div>
										<div class="company_wordcut">智能制造、创新研究</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">产品研发</div>
									<span class="salary"> 6k以上 </span>
									<div class="position_info">本科</div>
									<div class="labels">
										<span class="wordCut">机械设计制造及其自动化</span> <span class="wordCut">电气工程及其自动化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/shanbo.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">山东山博机电集团有限公司</div>
										<div class="company_wordcut">微电机厂、仪器仪表、山东</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">产品工程师</div>
									<span class="salary"> 2.8k-3.5k </span>
									<div class="position_info">本科</div>
									<div class="labels">
										<span class="wordCut">机械专业</span> <span class="wordCut">机械制造</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/jianxin.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">宁波建新华谊铝业有限公司</div>
										<div class="company_wordcut">高新科技、宁波</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="list_more_parent">
					<a class="list_more">查看更多</a>
				</div>
				<ul class="job_tab">
					<li><a class="high-job">高端职位</a></li>
				</ul>
				<div class="job_list_wrapper">
					<div class="job_list" style="display: block">
						<ul class="position_list_ul">
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">机械工程师</div>
									<span class="salary"> 4k-6k </span>
									<div class="position_info">本科及以上</div>
									<div class="labels">
										<span class="wordCut">机械设计</span> <span class="wordCut">自动化电气</span>

									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/boshi.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">哈尔滨博实自动化股份有限公司</div>
										<div class="company_wordcut">化工、冶金、金属加工、哈尔滨</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">研发人员</div>
									<span class="salary"> 5k-6k </span>
									<div class="position_info">本科/硕士</div>
									<div class="labels">
										<span class="wordCut">机械工程</span> <span class="wordCut">电子</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/bingqi.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">中国兵器工业集团有限公司</div>
										<div class="company_wordcut">工程机械产品</div>
									</div>
								</div>
							</li>
							<li class="position_list_li">
								<div class="position_li_top">
									<div class="position_name">电气工程师</div>
									<span class="salary"> 10k-12k </span>
									<div class="position_info">硕士</div>
									<div class="labels">
										<span class="wordCut">电气工程</span> <span class="wordCut">自动化</span>
									</div>
								</div>
								<div class="position_li_bottom">
									<img src="./images/jixing.png" width="50" height="50">
									<div class="bottom_right">
										<div class="company_name">江苏吉星新材料有限公司</div>
										<div class="company_wordcut">蓝宝石晶体生长、晶体加工、江苏</div>
									</div>
								</div>
							</li>

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
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/ande.png" alt="公司Logo" width="80"
										height="80">
									</a>
									<p class="company_name">北京安德建奇数字设备股份有限公司</p>
									<p>
										<span> 数字控制精密电加工/100人 </span>
									</p>
									<p class="advantage">
										有德有才，破格使用；有德少才，培养使用；少德有才，限制使用；无德无才，坚决不用</p>
								</div>
								<div class="bottom">
									<p class="green">5个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/zhongneng.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">黑龙江省中能控制工程股份有限公司</p>
									<p>
										<span> 供热设备、信息系统集成及服务/150-500人 </span>
									</p>
									<p class="advantage">艰苦奋斗、积极乐观、公开透明、互利互赢</p>
								</div>
								<div class="bottom">
									<p class="green">8个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/pingyang.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">山西平阳重工机械有限责任公司</p>
									<p>
										<span> 现代化军工企业、高端煤机生产企业 </span>
									</p>
									<p class="advantage">继往开来，承古求新，走出困境，再创辉煌</p>
								</div>
								<div class="bottom">
									<p class="green">6个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/zhenhiangdongfang.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">镇江东方电热有限公司 江苏瑞吉泰油气工程有限公司</p>
									<p>
										<span> 高性能电加热器、家用电器/1000-1500人 </span>
									</p>
									<p class="advantage">以质量求生存，以创新求效益，以诚信求发展</p>
								</div>
								<div class="bottom">
									<p class="green">5个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/bingqi.png" alt="公司Logo" width="80"
										height="80">
									</a>
									<p class="company_name">中国兵器工业集团有限公司 哈尔滨第一机械集团有限公司</p>
									<p>
										<span> 兵器工业/2000人 </span>
									</p>
									<p class="advantage">用户第一、质量至上、满意服务、诚信为本</p>
								</div>
								<div class="bottom">
									<p class="green">14个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/boshi.png" alt="公司Logo" width="80"
										height="80">
									</a>
									<p class="company_name">哈尔滨博实自动化股份有限公司</p>
									<p>
										<span> 化工、冶金、金属加工/1000人 </span>
									</p>
									<p class="advantage">2014年荣登“福布斯中国上市潜力企业“100强</p>
								</div>
								<div class="bottom">
									<p class="green">18个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/hangtiankeji.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">航天科技控股集团股份有限公司哈尔滨公司</p>
									<p>
										<span> 拥有航天背景汽车电子企业/上市公司/500-1000人 </span>
									</p>
									<p class="advantage">成为世界一流的创新型汽车电子企业</p>
								</div>
								<div class="bottom">
									<p class="green">6个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/shengliyoutian.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">胜利油田胜利石油装备有限公司</p>
									<p>
										<span> 集设计、研发、加工制造、油田服务于一体/1000-5000人 </span>
									</p>
									<p class="advantage">取得质量体系、环境管理体系和职业健康安全体系认证证书</p>
								</div>
								<div class="bottom">
									<p class="green">25个</p>
									<p>在招职位</p>
								</div>
							</li>
							<li class="company_list_li">
								<div class="top">
									<a> <img src="./images/jianxin.png" alt="公司Logo"
										width="80" height="80">
									</a>
									<p class="company_name">宁波建新华谊铝业有限公司</p>
									<p>
										<span> 高新技术企业/100-500人 </span>
									</p>
									<p class="advantage">惠及员工、利于天下</p>
								</div>
								<div class="bottom">
									<p class="green">5个</p>
									<p>在招职位</p>
								</div>
							</li>
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
						<span class="tel">服务热线：0451 8626 6861 (8:30 -17:30)</span> <a
							class="tel">企业服务邮箱：hitzhineng@163.com</span>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
