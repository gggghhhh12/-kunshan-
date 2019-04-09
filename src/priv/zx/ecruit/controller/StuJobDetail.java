package priv.zx.ecruit.controller;

/**
 * 获取点击进入职位的具体信息以及推荐相似职位servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.Comment;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

public class StuJobDetail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//将带段落的字符串s转换成有格式的字符串，方便在页面显示
		private StringBuilder getFormatString(String s){
			//按回车符将原字符串分成段落字符串
			String[] params = s.split("\r\n");
			//将每段字符串格式化输出，每行30字
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < params.length;i++){
				char[] letters = params[i].toCharArray();
				for(int j = 0;j < letters.length; j++){
					if(letters[j] == ' '){
						sb.append("&nbsp;".toCharArray());
					}else{
						sb.append(letters[j]);
					}
					if((j+1)%30 == 0){
						sb.append("<br>".toCharArray());
					}
				}
				sb.append("<br>".toCharArray());
			}		
			return sb;
		}
		
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//获得url中的EPusername信息
		String EPusername = request.getParameter("EPusername");
		String jobName=request.getParameter("jobname");
		jobName=jobName.replace(" ", "+");
		System.out.print(EPusername+" "+jobName);
		//创建EPDataDao和EPPostJobDao这两个数据库操作对象
		EPDataDao epdd = new EPDataDao();
		EPPostJobDao eppjd = new EPPostJobDao();
		//创建EPData和EPPostJob对象
		EPData epd = new EPData();
		EPPostJob eppj = new EPPostJob();
		//搜索对应企业账户名的企业信息
		try {
			epd = epdd.getEPData(EPusername);
			eppj = eppjd.getEPPostJob(EPusername,jobName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将搜索出的企业信息放入request
		request.setAttribute("epData", epd);
		request.setAttribute("epPostJob", eppj);
		if(epd != null && eppj != null){
			//将公司福利字符串格式化，便于EL输出
			String benefits = eppj.getBenefits();
			String[] arrBenefit = benefits.split("\r\n");
			request.setAttribute("benefits", arrBenefit);
			//将职位描述字符串格式化，便于EL输出
			StringBuilder jobdescribe = getFormatString(eppj.getJobdescribe());
			request.setAttribute("describe", jobdescribe);
			//将岗位职责字符串格式化，便于EL输出
			StringBuilder jobduty = getFormatString(eppj.getJobduty());
			request.setAttribute("duty", jobduty);
			//将任职要求字符串格式化，便于EL输出
			StringBuilder techrequest = getFormatString(eppj.getTechrequest());
			request.setAttribute("request", techrequest);
			//将公司信息字符串格式化，便于EL输出
			StringBuilder epInfo = getFormatString(epd.getEPintroduction());
			request.setAttribute("epInfo", epInfo);
		}
		
		//推荐相似职位功能实现
		String addr = epd.getEPaddr();//地址
		String trade = epd.getEPtrade();//行业
		String jobkind = eppj.getJobkind();//职能类别
		ArrayList<String> arrUsername = new ArrayList<String>();
		try {
			arrUsername = eppjd.queryRecommend(addr, trade, jobkind);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		arrUsername.remove(EPusername);
		//提取要显示的信息
		if(arrUsername.size() > 0){
			ArrayList<StuSelectResult> arrRecommend = new ArrayList<StuSelectResult>();
			StuSelectResult ssr = null;
			//创建EPData和EPPostJob对象
			EPData epd1 = new EPData();
			EPPostJob eppj1 = new EPPostJob();
			for (String username : arrUsername) {
				ssr = new StuSelectResult();
				ssr.setEPusername(username);
				try {
					epd1 = epdd.getEPData(username);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ssr.setJobaddr(epd1.getEPaddr());
				ssr.setEPname(epd1.getEPname());
				try {
					System.out.println(username);
					 ArrayList<EPPostJob> tmp=eppjd.getEPPostJob(username);
					 if(tmp.size()==0){
						 continue;
					 }
					eppj1 = tmp.get(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ssr.setJobname(eppj1.getJobname());
				ssr.setSalary(eppj1.getJobsalary());
				arrRecommend.add(ssr);
			}
			request.setAttribute("recommends", arrRecommend);
			request.setAttribute("strRecommends", arrRecommend.toString());
		}
		
		//显示该公司现有的评论
		CommentDao cd = new CommentDao();
		ArrayList<Comment> arrComm = new ArrayList<Comment>();
		try {
			arrComm = cd.queryComment(EPusername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(arrComm.size() > 0){
			request.setAttribute("comments", arrComm);
		}
		//转向stuJobDetile.jsp页面
		request.getRequestDispatcher("../stuJobDetail.jsp").forward(request, response);
	} 

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
