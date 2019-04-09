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

<title>求职意向</title>

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
<script type="text/javascript" src="./js/jobintention.js">
	</script>
</head>

<body>
	<%@ include file="stuNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>我的简历</h2>
		</div>
		<div class="info">
			<!--求职意向-->
			<div class="jobintention">
				<h4>求职意向</h4>
				<hr>
				<form action="servlet/JobIntentionServlet" method="post">
					<dl>
						<dt>关键词</dt>
						<dd>
							<input type="text" name="keyword"
								style="width: 420px; height: 30px"
								placeholder="如：行业、特长，不超过10个字，用空格分开">
						</dd>
					</dl>
					<dl>
						<dt>个人评价</dt>
						<dd>
							<textarea id="evaluation" name="evaluation"
								style="width: 420px; height: 100px"
								placeholder="输入自己的个人评价，120字以内"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>求职地点</dt>
						<dd>
							<input id="place" type="text" name="place"
								style="width: 120px; height: 30px"><a class="acity"></a>
						</dd>
					</dl>
					<dl>
						<dt>行业</dt>
						<dd>
							<input id="trade" type="text" name="trade"
								style="width: 120px; height: 30px"><a class="atrade"></a>
						</dd>
					</dl>
					<dl style="cursor: pointer">
						<dt>期望月薪</dt>
						<dd>
							<select name="salary">
								<option value="2000以下">2000以下</option>
								<option value="2000-2999">2000-2999</option>
								<option value="3000-4499">3000-4499</option>
								<option value="4500-5999">4500-5999</option>
								<option value="6000-7999">6000-7999</option>
								<option value="8000-9999">8000-9999</option>
								<option value="10000-14999">10000-14999</option>
								<option value="15000以上">15000以上</option>
							</select>
						</dd>
						<dd>&nbsp;人民币</dd>
					</dl>
					<div class="button">
						<input style="cursor: pointer" type="submit" value="保 存"
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

	<div class="trade" style="cursor: pointer">
		<div style="cursor: pointer" class="cancel">取消</div>
		<dl>
			<dt>计算机|互联网</dt>
			<dd>
				<span>IT</span><span>计算机软件</span><span>计算机硬件</span><span>计算机服务</span><span>通信/电信</span><span>互联网/电子商务</span>
				<span>游戏</span><span>电子技术</span><span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>金融|银行</dt>
			<dd>
				<span>会计/审计</span><span>金融/投资/证券</span><span>银行</span><span>保险</span><span>信托/担保/拍卖/典当</span>
			</dd>
		</dl>
		<dl>
			<dt>制药|医疗</dt>
			<dd>
				<span>制药|生物工程</span><span>医疗/护理/卫生</span><span>医疗设备/器械</span>
			</dd>
		</dl>
		<dl>
			<dt>广告|媒体</dt>
			<dd>
				<span>广告</span><span>公关/市场推广/会展</span><span>影视/媒体/艺术/文化传播</span><span>文字媒体/出版</span><span>印刷/包装/造纸</span>
			</dd>
		</dl>
		<dl>
			<dt>房地产|建筑</dt>
			<dd>
				<span>房地产</span><span>建筑/建材/工程</span><span>家居/室内设计/装潢</span><span>物业管理/商业中心</span>
			</dd>
		</dl>
		<dl>
			<dt>教育|培训</dt>
			<dd>
				<span>中介服务</span><span>外包服务</span><span>检测/认证</span><span>法律</span><span>教育/培训/院校</span><span>学术/科研</span>
			</dd>
		</dl>
		<dl>
			<dt>服务业</dt>
			<dd>
				<span>餐饮业</span><span>酒店/旅游</span><span>娱乐/休闲体育</span><span>美容/保健</span><span>生活服务</span>
			</dd>
		</dl>
		<dl>
			<dt>能源|原材料</dt>
			<dd>
				<span>石油/化工/矿产/地质</span><span>采掘业/冶炼</span><span>电力/电气/水利</span><span>新能源</span><span>原材料和加工</span>
			</dd>
		</dl>
		<dl>
			<dt>物流|运输</dt>
			<dd>
				<span>交通/运输/物流</span><span>航天/航空</span>
			</dd>
		</dl>
		<dl>
			<dt>政府|非盈利机构|其他</dt>
			<dd>
				<span>政府/公共事业</span><span>非盈利机构</span><span>环保</span><span>农/林/牧/渔</span><span>多元化业务集团公司</span>
			</dd>
		</dl>
	</div>
</body>
</html>
