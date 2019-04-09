package priv.zx.ecruit.controller;

/**
 * �鿴����servlet
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;

public class StuResumeServlet extends HttpServlet {

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
		
		//��������������Ϣ��������Ϣ����ְ�������
		BasicInfo bi = new BasicInfo();
		Education edu = new Education();
		JobIntention ji  = new JobIntention();
		//���������ݿ��������
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//��session�л���û���
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
		//�����ݿ��в��ҳ�������Ϣ
		try {
			bi = bid.getBasicInfo(username);
			edu = ed.getEducation(username);
			ji = jid.getJobIntention(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��������Ϣ����request��
		request.setAttribute("basicInfo", bi);
		request.setAttribute("education", edu);
		request.setAttribute("jobIntention", ji);
		if(bi != null && edu != null && ji != null){
			//��У��ְ���ַ���eduduty���д���
			String eduDuty = edu.getEduduty();
			String[] eduDutys = eduDuty.split("\r\n");
			StringBuilder sbDuty = new StringBuilder();
			for(int i = 0;i < eduDutys.length; i++){
				sbDuty.append(eduDutys[i].toCharArray());
				sbDuty.append("<br>".toCharArray());
			}
			request.setAttribute("eduduty", sbDuty);
			//����У�����ַ���eduaward���д���
			String eduAward = edu.getEduaward();
			String[] eduAwards = eduAward.split("\r\n");
			StringBuilder sbAward = new StringBuilder();
			for(int i = 0;i < eduAwards.length; i++){
				sbAward.append(eduAwards[i].toCharArray());
				sbAward.append("<br>".toCharArray());
			}
			request.setAttribute("eduaward", sbAward);
			//�����ʵ���ַ���eduprictise���д���
			String eduPrictise = edu.getEduprictise();
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
				if((i+1)%30 == 0){				
					sbEvaluation.append("<br>".toCharArray());
				}
			}
			request.setAttribute("evaluation", sbEvaluation);
			//��Ȩ��status���д���
			int status = ji.getStatus();
			if(status == 0){
				request.setAttribute("auth", "(������)");
			}else{
				request.setAttribute("auth", "(����)");
			}
		}
		//ҳ��ת��stuResume.jsp
		request.getRequestDispatcher("../stuResume.jsp").forward(request, response);
		
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
