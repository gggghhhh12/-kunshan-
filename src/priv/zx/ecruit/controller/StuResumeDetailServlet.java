package priv.zx.ecruit.controller;

/**
 * ����������ҳ����Ϣ��servlet
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
		//ȡ��url�е�stuUsername
		String stuUsername = request.getParameter("stuUsername");
//		System.out.println(stuUsername);
		//�����������ݿ��������
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//������Ϣ����
		BasicInfo bi = new BasicInfo();
		Education e = new Education();
		JobIntention ji = new JobIntention();
		//�����ݿ�����������Ϣ
		try {
			bi = bid.getBasicInfo(stuUsername);
			e = ed.getEducation(stuUsername);
			ji = jid.getJobIntention(stuUsername);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//����request
		request.setAttribute("basicInfo", bi);
		request.setAttribute("education", e);
		request.setAttribute("jobIntention", ji);
		//�������
		Calendar c = Calendar.getInstance();
		int now = c.get(Calendar.YEAR);
		c.setTime(bi.getBirthday());
		int bir = c.get(Calendar.YEAR);
		int age = now - bir;
		request.setAttribute("age", age);
		//��У��ְ���ַ���eduduty���д���
		String eduDuty = e.getEduduty();
		String[] eduDutys = eduDuty.split("\r\n");
		StringBuilder sbDuty = new StringBuilder();
		for(int i = 0;i < eduDutys.length; i++){
			sbDuty.append(eduDutys[i].toCharArray());
			sbDuty.append("<br>".toCharArray());
		}
		request.setAttribute("eduduty", sbDuty);
		//����У�����ַ���eduaward���д���
		String eduAward = e.getEduaward();
		String[] eduAwards = eduAward.split("\r\n");
		StringBuilder sbAward = new StringBuilder();
		for(int i = 0;i < eduAwards.length; i++){
			sbAward.append(eduAwards[i].toCharArray());
			sbAward.append("<br>".toCharArray());
		}
		request.setAttribute("eduaward", sbAward);
		//�����ʵ���ַ���eduprictise���д���
		String eduPrictise = e.getEduprictise();
		String[] eduPrictises = eduPrictise.split("\r\n");
		StringBuilder sbPrictise = new StringBuilder();
		for(int i = 0;i < eduPrictises.length;i++){
			sbPrictise.append(eduPrictises[i]);
			sbPrictise.append("<br>");
		}
		request.setAttribute("eduprictise", sbPrictise);
		//�����������ַ���evaluation���д���
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
		//ת��stuResumeDetail.jspҳ��
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
