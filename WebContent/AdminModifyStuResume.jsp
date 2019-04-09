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

<title>管理员修改学生简历</title>

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
<script type="text/javascript">
		$(document).ready(function(){
			$(".basicinfo dl dd .acity").click(function(){
				$(".city").show();
			});

			$(".city dl dd span").click(function(){
				var liveaddr = $(this).text();
				$(".basicinfo #liveaddr").val(liveaddr);
				$(".city").hide();
			});

			$(".city .cancel").click(function(){
				$(".city").hide();
			});

			$(".basicinfo dl dd .acity1").click(function(){
				$(".city1").show();
			});

			$(".city1 dl dd span").click(function(){
				
				var liveaddr = $(this).text();
				$(".basicinfo #residence").val(liveaddr);
				$(".city1").hide();
			});

			$(".city1 .cancel").click(function(){
				$(".city1").hide();
			});
			
			$(".jobintention dl dd .acity2").click(function(){
				$(".city2").show();
			});

			$(".city2 dl dd span").click(function(){
				var place = $(this).text();
				$(".jobintention #place").val(place);
				$(".city2").hide();
			});

			$(".city2 .cancel").click(function(){
				$(".city2").hide();
			});
			
			$(".jobintention dl dd .atrade1").click(function(){
				$(".trade1").show();
			});
			
			$(".trade1 dl dd span").click(function(){
				var trade = $(this).text();
				$(".jobintention #trade").val(trade);
				$(".trade1").hide();
			});
			
			$(".trade1 .cancel").click(function(){
				$(".trade1").hide();
			});
		});
	</script>
</head>

<body>

	<div id="mainbody">
		<div class="title">
			<h2>管理员修改学生简历</h2>
		</div>
		<div class="info">
			<form action="servlet/AdminHandleModifyResumeServlet" method="post">
				<!--基本信息-->
				<div class="basicinfo">
					<h4>基本信息</h4>
					<hr>
					<dl>
						<dt>姓名</dt>
						<input type="hidden" name="username"
							value="${basicInfo.username }">
						<dd>
							<input type="text" name="name" style="width: 120px; height: 30px"
								value="${basicInfo.name }">
						</dd>
					</dl>
					<dl>
						<dt>性别</dt>
						<dd>
							男<input type="radio" name="sex" value="男"
								<c:if test="${basicInfo.sex eq '男'}"> checked="checked"</c:if>>&nbsp;
						</dd>
						<dd>
							女<input type="radio" name="sex" value="女"
								<c:if test="${basicInfo.sex eq '女'}"> checked="checked"</c:if>>
						</dd>
					</dl>
					<dl>
						<dt>民族</dt>
						<dd>
							<input type="text" name="nation"
								style="width: 120px; height: 30px" value="${basicInfo.nation }">
						</dd>
					</dl>
					<dl>
						<dt>出生日期</dt>
						<dd>
							<input type="text" name="biryear"
								style="width: 60px; height: 30px" value="${birYear }">年
							<input type="text" name="birmonth"
								style="width: 60px; height: 30px" value="${birMonth }">月
							<input type="text" name="birday"
								style="width: 60px; height: 30px" value="${birDay }">日
						</dd>
					</dl>
					<dl>
						<dt>手机号码</dt>
						<dd>
							<input type="text" name="tel" style="width: 180px; height: 30px"
								value="${basicInfo.tel }">
						</dd>
					</dl>
					<dl>
						<dt>Email</dt>
						<dd>
							<input type="text" name="email"
								style="width: 180px; height: 30px" value="${basicInfo.email }">
						</dd>
					</dl>
					<dl>
						<dt>居住地</dt>
						<dd>
							<input id="liveaddr" type="text" name="liveaddr"
								style="width: 120px; height: 30px"
								value="${basicInfo.liveaddr }"><a class="acity"></a>
						</dd>
					</dl>
					<dl>
						<dt>户口</dt>
						<dd>
							<input id="residence" type="text" name="residence"
								style="width: 120px; height: 30px"
								value="${basicInfo.residence }"><a class="acity1"></a>
						</dd>
					</dl>
				</div>
				<!--教育经历-->
				<div class="education">
					<h4>教育经历</h4>
					<hr>
					<dl>
						<dt>时间</dt>
						<dd>
							<input type="text" name="eduyear"
								style="width: 60px; height: 30px" value="${entYear }">年
							<input type="text" name="edumonth"
								style="width: 60px; height: 30px" value="${entMonth }">月&nbsp;到&nbsp;
							<input type="text" name="eduyear1"
								style="width: 60px; height: 30px" value="${gradYear }">年
							<input type="text" name="edumonth1"
								style="width: 60px; height: 30px" value="${gradMonth }">月&nbsp;(最高学历)
						</dd>
					</dl>
					<dl>
						<dt>学校</dt>
						<dd>
							<input type="text" name="eduschool"
								style="width: 240px; height: 30px"
								value="${education.eduschool }">
						</dd>
					</dl>
					<dl>
						<dt>专业</dt>
						<dd>
							<input type="text" name="edumajor"
								style="width: 240px; height: 30px"
								value="${education.edumajor }">
						</dd>
					</dl>
					<dl>
						<dt>学历</dt>
						<dd>
							<select name="edudiploma">
								<option value="本科"
									<c:if test="${education.edudiploma eq '本科'}"> selected="selected"</c:if>>本科</option>
								<option value="硕士"
									<c:if test="${education.edudiploma eq '硕士'}"> selected="selected"</c:if>>硕士</option>
								<option value="博士"
									<c:if test="${education.edudiploma eq '博士'}"> selected="selected"</c:if>>博士</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>英语等级</dt>
						<dd>
							<select name="englevel">
								<option value="未参加"
									<c:if test="${education.englevel eq '未参加'}"> selected="selected"</c:if>>未参加</option>
								<option value="未通过"
									<c:if test="${education.englevel eq '未通过'}"> selected="selected"</c:if>>未通过</option>
								<option value="英语四级"
									<c:if test="${education.englevel eq '英语四级'}"> selected="selected"</c:if>>英语四级</option>
								<option value="英语六级"
									<c:if test="${education.englevel eq '英语六级'}"> selected="selected"</c:if>>英语六级</option>
								<option value="专业四级"
									<c:if test="${education.englevel eq '专业四级'}"> selected="selected"</c:if>>专业四级</option>
								<option value="专业八级"
									<c:if test="${education.englevel eq '专业八级'}"> selected="selected"</c:if>>专业八级</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>校内职务</dt>
						<dd>
							<textarea name="eduduty" style="width: 300px; height: 100px">${education.eduduty }</textarea>
						</dd>
					</dl>
					<dl>
						<dt>在校奖励</dt>
						<dd>
							<textarea name="eduaward" style="width: 300px; height: 100px">${education.eduaward }</textarea>
						</dd>
					</dl>
					<dl>
						<dt>社会实践</dt>
						<dd>
							<textarea name="eduprictise" style="width: 300px; height: 100px">${education.eduprictise }</textarea>
						</dd>
					</dl>
					<dl>
						<dt>海外学习经历</dt>
						<dd>
							<input type="radio" name="abroad" value="是"
								<c:if test="${education.abroad eq '是'}"> checked="checked"</c:if>>是&nbsp;
						</dd>
						<dd>
							<input type="radio" name="abroad" value="否"
								<c:if test="${education.abroad eq '否'}"> checked="checked"</c:if>>否
						</dd>
					</dl>
				</div>
				<!--求职意向-->
				<div class="jobintention">
					<h4>求职意向</h4>
					<hr>
					<dl>
						<dt>关键词</dt>
						<dd>
							<input type="text" name="keyword"
								style="width: 420px; height: 30px"
								value="${jobIntention.keyword }">
						</dd>
					</dl>
					<dl>
						<dt>个人评价</dt>
						<dd>
							<textarea name="evaluation" style="width: 420px; height: 100px">${jobIntention.evaluation }</textarea>
						</dd>
					</dl>
					<dl>
						<dt>求职地点</dt>
						<dd>
							<input id="place" type="text" name="place"
								style="width: 120px; height: 30px"
								value="${jobIntention.place }"><a class="acity2"></a>
						</dd>
					</dl>
					<dl>
						<dt>行业</dt>
						<dd>
							<input id="trade" type="text" name="trade"
								style="width: 120px; height: 30px"
								value="${jobIntention.trade }"><a class="atrade1"></a>
						</dd>
					</dl>
					<dl>
						<dt>期望月薪</dt>
						<dd>
							<select name="salary">
								<option value="1500以下"
									<c:if test="${jobIntention.salary eq '1500以下'}"> selected="selected"</c:if>>1500以下</option>
								<option value="1500-1999"
									<c:if test="${jobIntention.salary eq '1500-1999'}"> selected="selected"</c:if>>1500-1900</option>
								<option value="1999-2999"
									<c:if test="${jobIntention.salary eq '1999-2999'}"> selected="selected"</c:if>>1999-2999</option>
								<option value="3000-4499"
									<c:if test="${jobIntention.salary eq '3000-4499'}"> selected="selected"</c:if>>3000-4499</option>
								<option value="4500-5999"
									<c:if test="${jobIntention.salary eq '4500-5999'}"> selected="selected"</c:if>>4500-5999</option>
								<option value="6000-7999"
									<c:if test="${jobIntention.salary eq '6000-7999'}"> selected="selected"</c:if>>6000-7999</option>
								<option value="8000-9999"
									<c:if test="${jobIntention.salary eq '8000-9999'}"> selected="selected"</c:if>>8000-9999</option>
								<option value="10000-14999"
									<c:if test="${jobIntention.salary eq '10000-14999'}"> selected="selected"</c:if>>10000-14999</option>
								<option value="15000以上"
									<c:if test="${jobIntention.salary eq '15000以上'}"> selected="selected"</c:if>>15000以上</option>
							</select>
						</dd>
						<dd>&nbsp;人民币</dd>
					</dl>
					<div class="button">
						<input style="cursor: pointer" type="submit" value="保 存">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="footer"></div>

	<div class="city">
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

	<div class="city1">
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

	<div class="city2">
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

	<div class="trade1">
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
