package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class EPShowPostJobServlet extends HttpServlet {

	/**
	 *  ��ҵչʾ������ְλ
	 */
	private static final long serialVersionUID = 1L;
	
	//����������ַ���sת�����и�ʽ���ַ�����������ҳ����ʾ
	private StringBuilder getFormatString(String s){
		//���س�����ԭ�ַ����ֳɶ����ַ���
		String[] params = s.split("\r\n");
		//��ÿ���ַ�����ʽ�������ÿ��30��
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

		//����EPPostJobְλ�����б�
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		//����EPPostJobDaoְλҪ�����ݿ��������
		EPPostJobDao eppjd = new EPPostJobDao();
		//��session�л�ȡ��ҵ�û���
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		String res=request.getParameter("res");
		//�����ݿ��в��ҳ��ù�˾������ְλҪ��
		try {
			if(res.equals("all")){
				eppjs = eppjd.getEPPostJobAll(EPusername);
			}
			else{
				eppjs = eppjd.getEPPostJob(EPusername);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��ְλҪ��������request��
		request.setAttribute("EPPostJobs", eppjs);
//		if(eppj != null){
//			//��ְλ������ʽ���ַ���
//			StringBuilder sbJobdescribe = new StringBuilder();
//			sbJobdescribe = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("jobdescribe", sbJobdescribe);
//			//����λְ���ʽ���ַ���
//			StringBuilder sbJobduty = new StringBuilder();
//			sbJobduty = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("jobduty", sbJobduty);
//			//������Ҫ���ʽ���ַ���
//			StringBuilder sbTechrequest = new StringBuilder();
//			sbTechrequest = getFormatString(eppj.getJobdescribe());
//			request.setAttribute("techrequest", sbTechrequest);
//			//������Ȩ��״̬
//			int status = eppj.getStatus();
//			if(status == 0){
//				request.setAttribute("auth", "(�¼�״̬)");
//			}else{
//				request.setAttribute("auth", "(�ϼ�״̬)");
//			}
//		}
		//ת��EPShowPostJob.jspҳ��
		request.getRequestDispatcher("../EPShowPostJob.jsp").forward(request, response);
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
