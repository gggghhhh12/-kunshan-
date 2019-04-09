<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@page import="priv.zx.ecruit.model.News"%>
<%@page import="priv.zx.ecruit.dao.NewsDao"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
<%@
include
 
file="./css/mainPage.css"%>
</style>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="MainPage1.css">


<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<style type="text/css">
#apDiv1 {
	margin-top: 10px width: 200px;
	height: 200px;
	z-index: 3;
}
</style>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>


</head>
<body>
	<div id="header">
		<nav>
			<div class="logo">
				<a href="servlet/MainPageServlet">工大智能人才招聘系统</a>
			</div>

			<ul>
				<li><a><font color="white">注册</font></a>
					<ul>
						<li><a href="stuRegister.jsp">毕业生注册</a></li>
						<li><a href="EPRegister.jsp">企业注册</a></li>
					</ul></li>
				<li><a href="CompanyIntroduce.html"><font color="white">了解我们</font></a></li>
			</ul>

		</nav>
	</div>

	<!-- 主体内容 -->

	<div class=content>
		<!-- 左侧职位与薪资显示 -->
		<div class="content1">
			<div class="container">

				<br>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#home">自动化</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#menu1">人工智能</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#menu2">大数据</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">


					<div id="home" class="container tab-pane active">
						<br>
						<table class="table table-hover">
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>

						</table>
					</div>



					<div id="menu1" class="container tab-pane fade">
						<br>

						<table class="table table-hover">
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>

						</table>
					</div>




					<div id="menu2" class="container tab-pane fade">
						<br>


						<table class="table table-hover">
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>
							<tr>
								<td>Java研发工程师</td>
								<td>5000-10000</td>
							</tr>

						</table>
					</div>
				</div>
			</div>


		</div>


		<!-- 登陆框 -->
		<div class="content2">

			<div id="TabbedPanels1" class="TabbedPanels">
				<ul class="TabbedPanelsTabGroup">
					<li class="TabbedPanelsTab" tabindex="0">学生登录</li>
					<li class="TabbedPanelsTab" tabindex="0">企业登录</li>
				</ul>
				<div class="TabbedPanelsContentGroup">
					<div class="TabbedPanelsContent">
						<div class="login1">
							<div class="login-top">
								<h1>登录窗口</h1>
								<form action="servlet/stuLoginServlet" method="post">
									<input type="text" name="username" placeholder="username">
									<input type="password" name="password" placeholder="password">
									<div class="forgot">
										<div class="error">${error }</div>
										<a href="modifyPassword.jsp?usertype=student">修改密码</a> <input
											style="cursor: pointer" type="submit" value="登录">
									</div>
								</form>
							</div>
							<div class="login-bottom">
								<h3>
									新用户 &nbsp;<a href="stuRegister.jsp">注册</a>&nbsp;
								</h3>
							</div>
						</div>
					</div>
					<div class="TabbedPanelsContent">
						<div class="login1">
							<div class="login-top">
								<h1>登录窗口</h1>
								<form action="servlet/EPLoginServlet" method="post">
									<input type="text" name="EPname" placeholder="username">
									<input type="password" name="EPpassword" placeholder="password">
									<div class="forgot">
										<div class="error">${error }</div>
										<a href="modifyPassword.jsp?usertype=enterprise">修改密码</a> <input
											style="cursor: pointer" type="submit" value="登录">
									</div>
								</form>
							</div>
							<div class="login-bottom">
								<h3>
									新用户 &nbsp;<a href="EPRegister.jsp">注册</a>&nbsp;
								</h3>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>


	<!-- 
<div class = "content3">
<div class = "introduce">
<h3>&nbsp;&nbsp;&nbsp;人才强，则企业强</h3>
<p><font font-size = "5px">“中国智造”和“新一代人工智能”的国家重大发展战略催生了一大批民族企业的强势崛起。高精尖的人才成为当今智能制造、机器人、大数据、人工智能、高端软件研发企业的核心竞争力。而一方面具有多年丰富工作经验的高端人才趋向一线城市，
另一方面高校毕业生与企业技术人才需求又具有较大差异。这些问题导致科技型企业人才的招聘和培养成本越发高启，极大的制约了企业的发展。</font></p>
<h3>我们是工大人才网</h3>
依托于哈工大强大的学科群、哈工大国家大学科技园、哈工大宁波技术转移中心、昆山（哈尔滨）人才技术产业中心，哈工大科技园教育中心与哈工大智能机器人联合打造工大人才网，并致力于长三角高新技术企业人才深度服务。针对企业对自动化、机器人集成开发、大数据、人工智能、高端软件人才实际技术需求：
1）从东三省各大学相关专业应届毕业生中选拔、培养（培训）人才；
2）严格考核挑选出合格人才进入工大人才网；
3）为企业组织人才选拔筛选活动；
4）持续跟踪企业吸纳人才成长情况和企业高端人才发展规划，提供定制化专有化人才服务。
<h3>高精尖，我们不一样</h3><br>
依托并专注于哈工大机电、电气、控制、计算机、通信等国内顶尖学科群，吸纳哈工大科技园、机器人集团、大数据集团、材料焊接集团、环境集团等众多产业平台的人才服务体系和人才需求标准，构建出一套集优势学科、高水平师资、产教融合一体化人才选拔、培养和企业人才服务体系。以工大人才网为纽带，搭建企业人才全方位服务和哈工大优质教育服务之间的桥梁。
	

</div>

<div class = "gangwei" align = "center">
<table border = "1px">
	<h3>重点服务的就业岗位</h3>
	<tr><td align = "center">大专（高职）层次人才</td></tr>
	<tr><td>岗位类型1：电气、自动化产线装调工程师: 为智能制造、自动化等企业选拔、培养自动化设备（产线）装调工程师</td></tr>
	<tr><td>岗位类型2：电气、自动化、机械研发（助理）工程师 : 为智能制造、自动化等企业选拔、培养电气设计、机械结构设计、PLC设计、机器人编程等工程师；</td></tr>
	<tr><td>人才专业：机电一体化、电气自动化、工业机器人、机械制造等</td></tr>
	<tr><td>本科层次人才</td></tr>
	<tr><td>岗位类型1：机器人编程、机器人系统集成、自动化产线设计、产线测试工程师。<br>
  			为自动化、机器人、智能制造企业选拔、培养方案设计、研发、测试工程师。
	</td></tr>
	<tr><td>人才专业：电气自动化、自动控制、机械制造、机电一体化、车辆工程、电子信息等。</td></tr>
	<tr><td>岗位类型2：人工智能、大数据、计算机软件研发。</td></tr>
	<tr><td>人才专业：计算机科学、软件工程、数学、信息科学等。</td></tr>
	<tr><td>人才定制化服务</td></tr>
	<tr><td>研究生高层次人才：针对哈工大、哈工程、哈理工等黑龙江省重点大学的各个学科专业研究生和博士生，为企业进行高端人才的对接和猎聘服务。
其他人才服务：根据企业人才专业需求，定制化选拔人才服务。
	</td></tr>
</table>

</div>
<div class = "patener" align = "center">
<h3>合作高校</h3>
<table border = "1px" align = "center">
	<tr>
	<td align = "center">本科高校</td>
	<td align = "center">高职大专</td>
	</tr>
	
	<tr>
	<td>1.	哈尔滨工业大学</td>
	<td>1．	佳木斯职业学院</td>
	</tr>
	
	<tr>
	<td>2.	哈尔滨理工大学</td>
	<td>2．	黑龙江林业职业技术学院</td>
	</tr>
	
	<tr>
	<td>3.	黑龙江大学</td>
	<td>3．	大庆职业学院</td>
	</tr>
	
	<tr>
	<td>4.	哈尔滨商业大学</td>
	<td>4.	伊春职业学院</td>
	</tr>
	
	<tr>
	<td>5.	黑龙江科技大学</td>
	<td>5.	黑龙江建筑职业技术学院</td>
	</tr>
	
	<tr>
	<td>6.	黑龙江工程学院</td>
	<td>6．	黑龙江农业经济职业学院</td>
	</tr>
	
	<tr>
	<td>7.	哈尔滨工程大学</td>
	<td>7．	黑龙江农业职业技术学院</td>
	</tr>
	
	<tr>
	<td>8.	东北林业大学</td>
	<td>8．	黑龙江农业工程职业学院</td>
	</tr>
	
	<tr>
	<td>9.	东北农业大学</td>
	<td>9．	黑龙江农垦职业学院</td>
	</tr>
	
	<tr>
	<td>10.	东北石油大学</td>
	<td>10.	哈尔滨职业技术学院</td>
	</tr>
	
	<tr>
	<td>11.	佳木斯大学</td>
	<td>11. 大兴安岭职业技术学院</td>
	</tr>
	
	<tr>
	<td>12.	齐齐哈尔工程学院</td>
	<td>12.黑龙江职业学院</td>
	</tr>
	
	</table>
		
	
</div>

<div class = "rencaiwang">
<h3>工大人才网服务平台</h3>
<p>
平台运行一年来已经为多个企业提供了人才服务，
包括：杰士德、通祐电梯、哈工大义乌人工智能研究院、苏州峰之建、旭东机械、哈工大机器人集团等，
所有人才到企业后并得到企业高度认可。
</p>
<table>
	<tr height = "50px">
	<td><img  src="images/1.jpg" width= "100%" height = "200"></td>
	<td><img  src="images/1.jpg" width= "100%" height = "200"></td>
	<td><img  src="images/1.jpg" width= "100%" height = "200"></td>
	<td><img  src="images/1.jpg" width= "100%" height = "200"></td>
	<td><img  src="images/1.jpg" width= "100%" height = "200"></td>
	</tr>
</table>
</div>
</div>
 -->



	<!-- 底部信息 -->




	<div id="footer">
		@工大人才网<br> 未经许可 不许转载本站任何信息
	</div>
</body>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
