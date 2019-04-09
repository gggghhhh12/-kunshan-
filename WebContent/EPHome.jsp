<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>企业首页</title>

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
 
file="./css/EPHome.css"%>
</style>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
		var i = 25;
		$(document).ready(function(){
			var msg=$("#msg").val();
			if(msg!='null'&&msg!='undefined'&&msg!=""){
				alert(msg);
			}
			$(".btn input").click(function(){
				$(".selectResult .selectContent").empty();
				var address = $(".condition #address").val();
				var trade = $(".condition #trade").val();
				var salary = $(".condition #salary").val();
				var diploma = $(".condition #diploma").val();
				$.get("servlet/EPSelectServlet",{"address":address,"trade":trade,"salary":salary,"diploma":diploma},function(data,statusText){
					var result = data.result;
					var strResult = "";
					for(var i = 0;i < result.length;i++){
						strResult += "<span class=\"t1\"><a href=\"servlet/StuResumeDetailServlet?stuUsername=" + 
									result[i].stuUsername + "\" target=\"_blank\">" + result[i].stuName + "</a></span>" +
									"<span class=\"t2\">" + result[i].sex + "</span>" +
									"<span class=\"t3\">" + result[i].school + "</span>" +
									"<span class=\"t4\">" + result[i].major + "</span>" + "<hr>";
					}
					$(".selectResult .selectContent").html(strResult);
				},"json");
			});
			
			$(".recommend .change").click(function(){
				var strRecommends = "${strRecommends}";
				strRecommends = strRecommends.substring(1, strRecommends.length-1).replace(/\s+/g,"");
				var arr = strRecommends.split(",");
				var j = 0;
				var strHtml = "";
				if(arr != null && arr.length >= 25 ){
					$(".recommend .recdContent").empty();
					while(j < 5){
						if(i >= arr.length){
							i = 0;
						}
						strHtml += "<div class=\"single\"><span class=\"s1\"><a href=\"servlet/StuResumeDetailServlet?stuUsername=" + arr[i] + 
									"\" target=\"_blank\">" + arr[i+1] + "</a></span>" +
									"<span class=\"s2\">" + arr[i+2] + "</span>" +
									"<span class=\"s3\">" + arr[i+3] + "</span>" +
									"<span class=\"s4\">" + arr[i+4] + "</span></div>";
						j ++;
						i += 5;
					}
				}
				$(".recommend .recdContent").html(strHtml);
			});
			
			$(".condition dl dd .acity").click(function(){
				$(".city").show();
			});
			
			$(".city dl dd span").click(function(){
				var city = $(this).text();
				$(".condition dl dd #address").val(city);
				$(".city").hide();
			});
			
			$(".city .cancel").click(function(){
				$(".city").hide();
			});
			
			$(".condition dl dd .atrade").click(function(){
				$(".trade").show();
			});
			
			$(".trade dl dd span").click(function(){
				var trade = $(this).text();
				$(".condition dl dd #trade").val(trade);
				$(".trade").hide();
			});
			
			$(".trade .cancel").click(function(){
				$(".trade").hide();
			});
		});
	</script>
</head>

<body>
	<input type="hidden" id="msg"
		value="<%=(String)request.getAttribute("msg") %>">
	<div id="header">
		<%@ include file="EPNav.jsp"%>
	</div>
	<div id="left">
		<br> <br> <br> <br> <img src="images/robot.jpg"
			style="width: 100%; height: auto%;">

	</div>
	<div id="mainbody">
		<div class="select">
			<div class="condition">
				<dl>
					<dt>求职地点</dt>
					<dd>
						<input type="text" id="address" style="height: 30px; width: 130px"
							placeholder="求职地点"></input><a class="acity"></a>&nbsp;&nbsp;
					</dd>
				</dl>
				<dl>
					<dt>&nbsp;&nbsp;求职行业</dt>
					<dd>
						<input type="text" id="trade" style="height: 30px; width: 130px"
							placeholder="求职行业"></input><a class="atrade"></a>
					</dd>
				</dl>
				<dl>
					<dt>期望月薪</dt>
					<dd>
						<select id="salary" style="height: 35px; width: 165px">
							<option value="不限">不限</option>
							<option value="2000以下">2000以下</option>
							<option value="3000-4499">3000-4499</option>
							<option value="4500-5999">4500-5999</option>
							<option value="6000-7999">6000-7999</option>
							<option value="8000-9999">8000-9999</option>
							<option value="10000-14999">10000-14999</option>
							<option value="15000以上">15000以上</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>最高学历</dt>
					<dd>
						<select id="diploma" style="height: 35px; width: 165px">
							<option value="不限">不限</option>
							<option value="专科">专科</option>
							<option value="本科">本科</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
						</select>
					</dd>
				</dl>
				<div class="btn">
					<input type="button" value="搜索"></input>
				</div>
			</div>
			<div class="selectResult">
				<div class="resultTitle">
					<span class="t1">姓名</span> <span class="t2">性别</span> <span
						class="t3">学校</span> <span class="t4">专业</span>
				</div>
				<div class="selectContent">请选择搜索条件</div>
			</div>
		</div>



		<div class="recommend">
			<div class="title">
				<h4>可能感兴趣的人</h4>
				<div class="change">换一换</div>
				<hr width="260px" align="center" size="3px" color="#E64" noshade>
			</div>
			<div class="recdContent">
				<div>${error }</div>
				<c:if test="${!empty recommends }">
					<div class="single">
						<span class="s1"> <a
							href="servlet/StuResumeDetailServlet?stuUsername=${recommends[0].stuUsername }"
							target="_blank">${recommends[0].stuName }</a>
						</span> <span class="s2">${recommends[0].sex }</span> <span class="s3">${recommends[0].school }</span>
						<span class="s4">${recommends[0].major }</span>
					</div>
					<div class="single">
						<span class="s1"> <a
							href="servlet/StuResumeDetailServlet?stuUsername=${recommends[1].stuUsername }"
							target="_blank">${recommends[1].stuName }</a>
						</span> <span class="s2">${recommends[1].sex }</span> <span class="s3">${recommends[1].school }</span>
						<span class="s4">${recommends[1].major }</span>
					</div>
					<div class="single">
						<span class="s1"> <a
							href="servlet/StuResumeDetailServlet?stuUsername=${recommends[2].stuUsername }"
							target="_blank">${recommends[2].stuName }</a>
						</span> <span class="s2">${recommends[2].sex }</span> <span class="s3">${recommends[2].school }</span>
						<span class="s4">${recommends[2].major }</span>
					</div>
					<div class="single">
						<span class="s1"> <a
							href="servlet/StuResumeDetailServlet?stuUsername=${recommends[3].stuUsername }"
							target="_blank">${recommends[3].stuName }</a>
						</span> <span class="s2">${recommends[3].sex }</span> <span class="s3">${recommends[3].school }</span>
						<span class="s4">${recommends[3].major }</span>
					</div>
					<div class="single">
						<span class="s1"> <a
							href="servlet/StuResumeDetailServlet?stuUsername=${recommends[4].stuUsername }"
							target="_blank">${recommends[4].stuName }</a>
						</span> <span class="s2">${recommends[4].sex }</span> <span class="s3">${recommends[4].school }</span>
						<span class="s4">${recommends[4].major }</span>
					</div>
				</c:if>
			</div>
			<div class="tradeAndCount">
				<h4>本月各行业求职人数排名</h4>
				<hr align="center" size="3px" color="#E64" noshade>
				<c:forEach var="count" items="${counts }">
					<span class="r1">行业:${count.trade }</span>
					<span class="r2">求职人数:${count.count }人</span>
					<span class="r3"> 占总人数比重:<fmt:formatNumber type="number"
							value="${count.count/sum*100 }" maxFractionDigits="2"></fmt:formatNumber>%
					</span>
					<hr align="center" size="2px" color="#DDDDDD" noshade>
				</c:forEach>
			</div>
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
				<span>温州</span><span>武汉</span><span>芜湖</span><span>乌鲁木齐</span><span>无锡</span><span>厦门</span><span>西安</span><span>湘潭</span>
				<span>襄阳</span><span>咸阳</span><span>徐州</span>
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

	<div class="trade">
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
