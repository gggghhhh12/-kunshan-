package priv.zx.ecruit.controller;

/**
 * 搜索出简历页面信息的servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;

public class StuResumeDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		//取得url中的stuUsername
		String stuUsername = request.getParameter("stuUsername");
//		System.out.println(stuUsername);
		//创建三个数据库操作对象
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//创建信息对象
		BasicInfo bi = new BasicInfo();
		Education e = new Education();
		JobIntention ji = new JobIntention();
		//从数据库中搜索出信息
		try {
			bi = bid.getBasicInfo(stuUsername);
			e = ed.getEducation(stuUsername);
			ji = jid.getJobIntention(stuUsername);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//存入request
		request.setAttribute("basicInfo", bi);
		request.setAttribute("education", e);
		request.setAttribute("jobIntention", ji);
		//获得年龄
		Calendar c = Calendar.getInstance();
		int now = c.get(Calendar.YEAR);
		c.setTime(bi.getBirthday());
		int bir = c.get(Calendar.YEAR);
		int age = now - bir;
		request.setAttribute("age", age);
		//对校内职务字符串eduduty进行处理
		String eduDuty = e.getEduduty();
		String[] eduDutys = eduDuty.split("\r\n");
		StringBuilder sbDuty = new StringBuilder();
		for(int i = 0;i < eduDutys.length; i++){
			sbDuty.append(eduDutys[i].toCharArray());
			sbDuty.append("<br>".toCharArray());
		}
		request.setAttribute("eduduty", sbDuty);
		//对在校奖励字符串eduaward进行处理
		String eduAward = e.getEduaward();
		String[] eduAwards = eduAward.split("\r\n");
		StringBuilder sbAward = new StringBuilder();
		for(int i = 0;i < eduAwards.length; i++){
			sbAward.append(eduAwards[i].toCharArray());
			sbAward.append("<br>".toCharArray());
		}
		request.setAttribute("eduaward", sbAward);
		//对社会实践字符串eduprictise进行处理
		String eduPrictise = e.getEduprictise();
		String[] eduPrictises = eduPrictise.split("\r\n");
		StringBuilder sbPrictise = new StringBuilder();
		for(int i = 0;i < eduPrictises.length;i++){
			sbPrictise.append(eduPrictises[i]);
			sbPrictise.append("<br>");
		}
		request.setAttribute("eduprictise", sbPrictise);
		//对自我评价字符串evaluation进行处理
		String evaluation = ji.getEvaluation();
		char[] evaluations = evaluation.toCharArray();
		StringBuilder sbEvaluation = new StringBuilder();
		for(int i = 0;i < evaluations.length; i++){
			sbEvaluation.append(evaluations[i]);
			if((i+1)%35 == 0){				
				sbEvaluation.append("<br>".toCharArray());
			}
		}
		request.setAttribute("evaluation", sbEvaluation);
		//转向stuResumeDetail.jsp页面
		request.getRequestDispatcher("../stuResumeDetail.jsp").forward(request, response);
		
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
