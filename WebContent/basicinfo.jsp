<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>我的简历</title>

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

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/basicinfo.js"></script>
<script type="text/javascript">
	</script>
</head>

<body>
	<%@ include file="stuNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>我的简历</h2>
		</div>
		<div class="info">
			<!--基本信息-->
			<div class="basicinfo">
				<h4>基本信息</h4>
				<hr>
				<form action="servlet/BasicinfoServlet" method="post">
					<dl>
						<dt>姓名</dt>
						<dd>
							<input type="text" id="name" name="name"
								style="width: 120px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>性别</dt>
						<dd>
							男<input type="radio" name="sex" value="男">&nbsp;
						</dd>
						<dd>
							女<input type="radio" name="sex" value="女">
						</dd>
					</dl>
					<dl>
						<dt>民族</dt>
						<dd>
							<input type="text" name="nation"
								style="width: 120px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>出生日期</dt>
						<dd>
							<!-- <input type="text" name="biryear" style="width:60px;height:30px">年
						<input type="text" name="birmonth" style="width:60px;height:30px">月
						<input type="text" name="birday" style="width:60px;height:30px">日 -->
							<input type="date" style="width: 180px; height: 30px"
								placeholder="请输入出生年月" id="birthday" name="birthday">
						</dd>


					</dl>
					<dl>
						<dt>手机号码</dt>
						<dd>
							<input type="text" name="tel" style="width: 180px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>Email</dt>
						<dd>
							<input type="text" name="email"
								style="width: 180px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>居住地</dt>
						<dd>
							<input id="liveaddr" type="text" name="liveaddr"
								style="width: 120px; height: 30px"><a class="acity"></a>
						</dd>
					</dl>
					<dl>
						<dt>户口</dt>
						<dd>
							<input id="residence" type="text" name="residence"
								style="width: 120px; height: 30px"><a class="acity1"></a>
						</dd>
					</dl>
					<div class="button">
						<input style="cursor: pointer" type="submit" value="下一步"
							onclick="return myfunction()">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer"></div>

	<div class="city" style="cursor: pointer">
		<div style="cursor: pointer" class="cancel">取消</div>
		<dl>
			<dt>热门城市</dt>
			<dd>
				<span>北京</span><span>上海</span><span>广州</span><span>武汉</span><span>西安</span><span>杭州</span><span>南京</span>
				<span>成都</span><span>重庆</span><span>东莞</span><span>大连</span><span>沈阳</span><br>
				<span>苏州</span><span>昆明</span> <span>长沙</span><span>合肥</span><span>宁波</span><span>郑州</span><span>天津</span><span>青岛</span><span>济南</span>
				<span>哈尔滨</span><span>长春</span><span>鄂尔多斯</span>
			</dd>
		</dl>
		<dl>
			<dt>ABCDE</dt>
			<dd>
				<span>鞍山</span><span>保定</span><span>包头</span><span>北京</span><span>长春</span><span>长沙</span><span>常熟</span>
				<span>常州</span><span>常德</span><span>重庆</span><span>大连</span><span>丹东</span><span>大庆</span><span>东莞</span><span>鄂尔多斯</span>
			</dd>
		</dl>
		<dl>
			<dt>FGH</dt>
			<dd style="cursor: pointer">
				<span>佛山</span><span>抚顺</span><span>福州</span><span>赣州</span><span>广州</span><span>贵阳</span><span>海口</span><span>邯郸</span>
				<span>杭州</span><span>哈尔滨</span><span>合肥</span><span>衡阳</span><span>呼和浩特</span><span>淮安</span><span>惠州</span><span>湖州</span>
			</dd>
		</dl>
		<dl>
			<dt>JKLM</dt>
			<dd>
				<span>江门</span><span>嘉兴</span><span>吉林</span><span>济南</span><span>荆州</span><span>金华</span><span>济宁</span><span>九江</span>
				<span>昆明</span><span>廊坊</span><span>兰州</span><span>连云港</span><span>临沂</span><span>柳州</span><span>洛阳</span><span>昆山</span><span>绵阳</span>
			</dd>
		</dl>
		<dl>
			<dt>NQRS</dt>
			<dd>
				<span>南昌</span><span>南京</span><span>南宁</span><span>南通</span><span>宁波</span><span>青岛</span><span>秦皇岛</span><span>清远</span>
				<span>泉州</span><span>三亚</span><span>上海</span><span>汕头</span><span>绍兴</span><span>沈阳</span><span>深圳</span><span>石家庄</span><span>苏州</span>
			</dd>
		</dl>
		<dl>
			<dt>TWX</dt>
			<dd>
				<span>唐山</span><span>太原</span><span>台州</span><span>泰州</span><span>天津</span><span>铁岭</span><span>潍坊</span><span>威海</span>
				<span>温州</span><span>武汉</span><span>芜湖</span><span>乌鲁木齐</span><br>
				<span>无锡</span><span>厦门</span><span>西安</span><span>湘潭</span> <span>襄阳</span><span>咸阳</span><span>徐州</span>
			</dd>
		</dl>
		<dl>
			<dt>YZ</dt>
			<dd>
				<span>盐城</span><span>扬州</span><span>烟台</span><span>宜昌</span><span>银川</span><span>营口</span><span>义乌</span><span>张家港</span>
				<span>漳州</span><span>湛江</span><span>肇庆</span><span>郑州</span><span>镇江</span><span>中山</span><span>珠海</span><span>淄博</span>
			</dd>
		</dl>
	</div>

	<div class="city1" style="cursor: pointer">
		<div style="cursor: pointer" class="cancel">取消</div>
		<dl>
			<dt>热门城市</dt>
			<dd>
				<span>北京</span><span>上海</span><span>广州</span><span>武汉</span><span>西安</span><span>杭州</span><span>南京</span>
				<span>成都</span><span>重庆</span><span>东莞</span><span>大连</span><span>沈阳</span><br>
				<span>苏州</span><span>昆明</span> <span>长沙</span><span>合肥</span><span>宁波</span><span>郑州</span><span>天津</span><span>青岛</span><span>济南</span>
				<span>哈尔滨</span><span>长春</span><span>鄂尔多斯</span>
			</dd>
		</dl>
		<dl>
			<dt>ABCDE</dt>
			<dd>
				<span>鞍山</span><span>保定</span><span>包头</span><span>北京</span><span>长春</span><span>长沙</span><span>常熟</span>
				<span>常州</span><span>常德</span><span>重庆</span><span>大连</span><span>丹东</span><span>大庆</span><span>东莞</span><span>鄂尔多斯</span>
			</dd>
		</dl>
		<dl>
			<dt>FGH</dt>
			<dd>
				<span>佛山</span><span>抚顺</span><span>福州</span><span>赣州</span><span>广州</span><span>贵阳</span><span>海口</span><span>邯郸</span>
				<span>杭州</span><span>哈尔滨</span><span>合肥</span><span>衡阳</span><span>呼和浩特</span><span>淮安</span><span>惠州</span><span>湖州</span>
			</dd>
		</dl>
		<dl>
			<dt>JKLM</dt>
			<dd>
				<span>江门</span><span>嘉兴</span><span>吉林</span><span>济南</span><span>荆州</span><span>金华</span><span>济宁</span><span>九江</span>
				<span>昆明</span><span>廊坊</span><span>兰州</span><span>连云港</span><span>临沂</span><span>柳州</span><span>洛阳</span><span>昆山</span><span>绵阳</span>
			</dd>
		</dl>
		<dl>
			<dt>NQRS</dt>
			<dd>
				<span>南昌</span><span>南京</span><span>南宁</span><span>南通</span><span>宁波</span><span>青岛</span><span>秦皇岛</span><span>清远</span>
				<span>泉州</span><span>三亚</span><span>上海</span><span>汕头</span><span>绍兴</span><span>沈阳</span><span>深圳</span><span>石家庄</span><span>苏州</span>
			</dd>
		</dl>
		<dl>
			<dt>TWX</dt>
			<dd>
				<span>唐山</span><span>太原</span><span>台州</span><span>泰州</span><span>天津</span><span>铁岭</span><span>潍坊</span><span>威海</span>
				<span>温州</span><span>武汉</span><span>芜湖</span><span>乌鲁木齐</span><br>
				<span>无锡</span><span>厦门</span><span>西安</span><span>湘潭</span> <span>襄阳</span><span>咸阳</span><span>徐州</span>
			</dd>
		</dl>
		<dl>
			<dt>YZ</dt>
			<dd>
				<span>盐城</span><span>扬州</span><span>烟台</span><span>宜昌</span><span>银川</span><span>营口</span><span>义乌</span><span>张家港</span>
				<span>漳州</span><span>湛江</span><span>肇庆</span><span>郑州</span><span>镇江</span><span>中山</span><span>珠海</span><span>淄博</span>
			</dd>
		</dl>
	</div>
</body>
</html>
