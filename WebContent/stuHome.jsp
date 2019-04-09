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

<title>毕业生首页</title>

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
file="./css/stuHome.css"%>
</style>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/stuHomeFenye.js"></script>
<script type="text/javascript" src="./js/fenye.js"></script>
</head>

<body>
	<input type="hidden" id="msg"
		value="<%=(String)request.getAttribute("msg") %>">
	<%String userlevel = (String)session.getAttribute("userlevel"); %>
	<input type="hidden" name="userlevel" value="<%=userlevel %>">
	<%@ include file="stuNav.jsp"%>
	<div id="left">
		<br> <br> <br> <br> <img src="images/robot.jpg"
			style="width: 100%; height: auto;">

	</div>
	<div id="mainbody">
		<div class="select">
			<form action="servlet/stuSelectServlet" method="post">
				<div class="text">
					<input id="address" type="text" name="address"
						style="height: 30px; width: 160px" placeholder="工作地点"></input> <a
						class="aplace"></a>
				</div>
				<div class="text">
					<input id="jobkind" type="text" name="jobkind"
						style="height: 30px; width: 160px" placeholder="职能类别"></input> <a
						class="ajobkind"></a>
				</div>
				<div class="text">
					<input id="trade" type="text" name="trade"
						style="height: 30px; width: 160px" placeholder="行业类别"></input> <a
						class="atrade"></a>
				</div>
				<div class="submit">
					<input style="cursor: pointer" type="submit" value="搜索"></input>
				</div>
			</form>
			<div class="option">
				<dl id="salary">
					<dt>月薪范围</dt>
					<dd>不限</dd>
					<dd>2000以下</dd>
					<dd>2000-2999</dd>
					<dd>3000-4499</dd>
					<dd>4500-5999</dd>
					<dd>6000-7999</dd>
					<dd>8000-9999</dd>
					<dd>10000-14999</dd>
					<dd>15000以上</dd>
				</dl>
				<dl id="nature">
					<dt>公司性质</dt>
					<dd>不限</dd>
					<dd>股份制公司</dd>
					<dd>股份制合作制公司</dd>
					<dd>外资</dd>
					<dd>中外合资</dd>
					<dd>国营</dd>
					<dd>私营</dd>
				</dl>
				<dl id="diploma">
					<dt>学历要求</dt>
					<dd>不限</dd>
					<dd>专科及以上</dd>
					<dd>本科及以上</dd>
					<dd>硕士及以上</dd>
					<dd>博士及以上</dd>
				</dl>
				<dl id="scale">
					<dt>公司规模</dt>
					<dd>不限</dd>
					<dd>大型</dd>
					<dd>中型</dd>
					<dd>小型</dd>
				</dl>
			</div>
			<div class="result">
				<div class="resulttitle">
				<table>
				<tr>
				<th width=25% class="t1">职位名</th> 
				<th  width=25% class="t2">公司名</th> 
				<th  width=25% class="t3">工作地点</th> 
				<th  width=25%  class="t4">薪资</th> 
				</tr>
				<table class="resultcontext" id="idData">
					<c:forEach var="ssr" items="${arrSsr }">
                      <tr>
						<td width=25% class="t1"><a
							href="servlet/StuJobDetail?EPusername=${ssr.EPusername }&jobname=${ssr.jobname }"
							target="_blank">${ssr.jobname }</a></td>
						<% 
						if(userlevel.equals("0"))
						{
					%>
						<td  width=25% class="t2">XXX公司</td>
						<%}else{ %>
						<td width=25% class="t2">${ssr.EPname }</td>
						<%} %>
						<td width=25% class="t3">${ssr.jobaddr }</td>
						<td width=25% class="t4">${ssr.salary }</td>
						</tr>
					</c:forEach>
					
					</table>
				</table>
				<table  align="center" style="width:100%">
        <tr>
        <td>
				</div>
			 <div id="changePages" name="changePages" style="TEXT-ALIGN:CENTER;"></div>
        </td>
        </tr>
         </table>
				<div class="error">${error }</div>
			</div>
		</div>

		<div class="recommend">
			<div class="title">
				<h4>新鲜的职位</h4>
				<hr align="center" size="3px" color="#E64" noshade>
			</div>
			<div class="newjob">
				<c:forEach var="lastestJob" items="${lastestJobs }">
					<dl>
						<dt>
							<a
								href="servlet/StuJobDetail?EPusername=${lastestJob.epUsername }&jobname=${lastestJob.jobName  }"
								target="_blank">${lastestJob.jobName }</a>
						</dt>
						<% 
						if(userlevel.equals("0"))
						{
						%>
						<dd>XXX公司</dd>
						<%}else{ %>

						<dd>${lastestJob.epName }</dd>
						<%} %>
						<dd>${lastestJob.postDate }</dd>
					</dl>
					<hr align="center" size="2px" color="#DDDDDD" noshade>
				</c:forEach>
			</div>

			<div class="tradeAndCount">
				<h4>各行业发布职位排名</h4>
				<hr align="center" size="3px" color="#E64" noshade>
				<c:forEach var="count" items="${counts }">
					<span class="s1">行业:${count.trade }</span>
					<span class="s2">职位量:${count.count }</span>
					<span class="s3"> 占总量比重:<fmt:formatNumber type="number"
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

	<div class="jobkind">
		<div style="cursor: pointer" class="cancel">取消</div>
		<dl>
			<dt>计算机|互联网</dt>
			<dd>
				<span>高级软件工程师</span><span>软件工程师</span><span>UI设计师</span><span>算法工程师</span><span>ERP工程师</span><span>需求工程师</span>
				<span>系统分析师</span><br>
				<span>数据库工程师</span><span>测试工程师</span><span>硬件工程师</span><span>网站营运</span><span>产品总监</span><span>产品主管</span><span>产品专员</span><span>项目总监</span><span>项目主管</span><span>项目专员</span><br>
				<span>通信技术工程师</span><span>电信网络工程师</span> <span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>销售|客服</dt>
			<dd>
				<span>销售总监</span><span>销售经理</span><span>销售主管</span><span>大客户管理</span><span>客户经理</span><span>区域销售总监</span>
				<span>区域销售主管</span><span>大客户销售</span><br>
				<span>销售代表</span><span>客户代表</span><span>电话销售</span><span>销售行政经理</span>
				<span>业务分析经理</span><span>商务经理</span><span>客服总监</span><span>客服经理</span><br>
				<span>客服专员</span><span>VIP专员</span><span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>金融|银行</dt>
			<dd>
				<span>财务总监</span><span>财务顾问</span><span>财务经理</span><span>审计主管</span><span>审计专员</span><span>财务分析员</span><span>财务管理员</span>
				<span>证券分析师</span><span>期货操盘手</span><br>
				<span>金融研究员</span><span>金融产品经理</span><span>金融产品销售</span><span>理财顾问</span>
				<span>风险管理</span><span>行长/副行长</span><span>银行客户总监</span><span>业务经理</span><br>
				<span>业务专员</span><span>资产评估师</span> <span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>生产|物流</dt>
			<dd>
				<span>总工程师</span><span>项目总监</span><span>项目主管</span><span>项目工程师</span><span>生产总监</span><span>生产主管</span><span>生产组长</span>
				<span>生产文员</span><span>质量检测员</span><br>
				<span>工业工程师</span><span>材料工程师</span><span>机械工程师</span><span>结构工程师</span>
				<span>模具工程师</span><span>机电工程师</span><span>维修工程师</span><span>技工</span><br>
				<span>物流总监</span><span>物流主管</span><span>物流专员</span> <span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>生物|制药</dt>
			<dd>
				<span>生物工程</span><span>化学分析测试员</span><span>医药研发工程师</span><span>临床学术推广</span><span>临床研究员</span><span>临床数据分析员</span>
				<span>化工工程师</span><span>化工实验技术员</span>
			</dd>
		</dl>
		<dl>
			<dt>广告|媒体</dt>
			<dd>
				<span>广告客户总监</span><span>广告客户经理</span><span>广告客户主管</span><span>创意设计</span><span>广告制作执行</span><span>美术指导</span>
				<span>文案/策划</span><br>
				<span>业务发展人员</span><span>企业策划人员</span><span>艺术总监</span><span>影视策划</span><span>导演/编导</span>
				<span>后期制作</span><span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>建筑|房地产</dt>
			<dd>
				<span>高级建筑工程师</span><span>建筑工程师</span><span>建筑设计师</span><span>市政工程师</span><span>土建工程师</span><span>室内设计</span>
				<span>景观设计</span><span>安全员</span><span>资料员</span><span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>人事/行政</dt>
			<dd>
				<span>人事总监</span><span>人事经理</span><span>人事主管</span><span>人事专员</span><span>行政总监</span><span>行政经理</span><span>行政主管</span>
				<span>行政专员</span><span>秘书</span><span>其他</span>
			</dd>
		</dl>
		<dl>
			<dt>咨询|法律</dt>
			<dd>
				<span>专业顾问</span><span>咨询总监</span><span>咨询经理</span><span>专业培训师</span><span>咨询员</span><span>调研员</span><span>律师</span>
				<span>律师助理</span><span>法务经理</span><span>法务专员</span><span>法务助理</span>
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
				<span>交通</span><span>物流</span><span>物流</span>运输<span>航天/航空</span>
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
