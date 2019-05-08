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

<title>发布职位</title>

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
			$(".basicinfo dl dd .ajobkind").click(function(){
				$(".jobkind").show();
			});
			
			$(".jobkind dl dd span").click(function(){
				var jobkind = $(this).text();
				$(".basicinfo dl dd #jobkind").val(jobkind);
				$(".jobkind").hide();
			});
			
			$(".jobkind .cancel").click(function(){
				$(".jobkind").hide();
			});
			$("form").submit(function(){
			    alert("已提交，待管理员审核");
			  });
			$(".button1 input").click(function(){
				var jobname=$("#jobname").val();
				if(jobname=="null"||jobname==""){
					alert("请输入职位名称！");
					return;
				}
				 $("form").submit();
			});
			
		});	
	</script>
</head>

<body>
	<%@ include file="EPNav.jsp"%>
	<div id="mainbody">
		<div class="title">
			<h2>发布职位</h2>
		</div>
		<div class="info">
			<!--职位要求-->
			<div class="basicinfo">
				<h4>职位要求</h4>
				<hr>
				<form action="servlet/EPPostJobServlet" method="post">
					<dl>
						<dt>职位名称</dt>
						<dd>
							<input id="jobname" type="text" name="jobname"
								style="width: 200px; height: 30px">
						</dd>
					</dl>
					<dl>
						<dt>职位薪资</dt>
						<dd>
							<select name="jobsalary">
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
						<dd>&nbsp;人民币/月</dd>
					</dl>
					<dl>
						<dt>学历要求</dt>
						<dd>
							<select name="jobdiploma">
								<option value="不限学历">不限学历</option>
								<option value="专科及以上">专科及以上</option>
								<option value="本科及以上">本科及以上</option>
								<option value="硕士及以上">硕士及以上</option>
								<option value="博士及以上">博士及以上</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>英语等级要求</dt>
						<dd>
							<select name="engrequest">
								<option value="无要求">无要求</option>
								<option value="英语四级">英语四级</option>
								<option value="英语六级">英语六级</option>
								<option value="专业四级">专业四级</option>
								<option value="专业八级">专业八级</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>招聘人数</dt>
						<dd>
							<input type="text" name="reqnum" value="1"
								style="width: 50px; height: 30px">&nbsp;人
						</dd>
					</dl>
					<dl>
						<dt>公司福利</dt>
						<dd>
							<input type="text" name="benefits"
								style="width: 300px; height: 30px"
								placeholder="不超过20个字，每个词之间用空格隔开">
						</dd>
					</dl>
					<dl>
						<dt>职位描述</dt>
						<dd>
							<textarea name="jobdescribe" style="width: 300px; height: 100px"
								placeholder="不超过1000字"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>岗位职责</dt>
						<dd>
							<textarea name="jobduty" style="width: 300px; height: 100px"
								placeholder="不超过1000字，按点换行"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>技术要求</dt>
						<dd>
							<textarea name="techrequest" style="width: 300px; height: 100px"
								placeholder="不超过1000字，按点换行"></textarea>
						</dd>
					</dl>
					<dl>
						<dt>职能类别</dt>
						<dd>
							<input id="jobkind" type="text" name="jobkind"
								style="width: 200px; height: 30px"><a class="ajobkind"></a>
						</dd>
					</dl>
					<dl>
						<dt>上班地址</dt>
						<dd>
							<input type="text" name="jobaddr"
								style="width: 300px; height: 30px">
						</dd>
					</dl>
					<div class="button1">
						<input type="button" value="提交">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="footer"></div>

	<div class="jobkind">
		<div style="cursor: pointer" class="cancel">取消</div>
		<dl>
			<dt>计算机|互联网</dt>
			<dd>
				<span>高级软件工程师</span><span>软件工程师</span><span>UI设计师</span><span>算法工程师</span><span>ERP工程师</span><span>需求工程师</span>
				<span>系统分析师</span><br>
				<span>数据库工程师</span><span>测试工程师</span><span>硬件工程师</span><span>网站营运</span><span>产品总监</span><span>产品主管</span><br>
				<span>产品专员</span><span>项目总监</span><span>项目主管</span><span>项目专员</span><span>通信技术工程师</span><span>电信网络工程师</span>
				<span>其他</span>
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
				<span>生物工程</span><span>化学分析测试员</span><span>医药研发工程师</span><span>临床学术推广</span><span>临床研究员</span><span>临床数据分析员</span><br>
				<span>化工工程师</span><span>化工实验技术员</span><span>其他</span>
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
</body>
</html>
