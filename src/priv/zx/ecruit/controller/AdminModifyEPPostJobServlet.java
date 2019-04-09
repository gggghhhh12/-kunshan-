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

public class AdminModifyEPPostJobServlet extends HttpServlet{
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
		System.out.println("����AdminModifyEPPostJobServlet");
		//�˹�˾������ְλ�б�
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		//����EPPostJobDaoְλҪ�����ݿ��������
		EPPostJobDao eppjd = new EPPostJobDao();
		//��session�л�ȡ��ҵ�û���
		String EPusername = (String) request.getParameter("epusername");
		String JobName = (String) request.getParameter("jobname");
		System.out.println(EPusername);
		System.out.println(JobName);
		//�����ݿ��в��ҳ��ù�˾������ְλҪ��
		try {
			eppjs = eppjd.getEPPostJob(EPusername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(EPPostJob item : eppjs)
		{
			if(item.getJobname().equals(JobName))
			{
				request.setAttribute("EPPostJob", item);
				System.out.println("break");
				break;
			}
		}
		//ת��EPModifyPostJob.jspҳ��
		request.getRequestDispatcher("../AdminModifyEPpostJob.jsp").forward(request, response);
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
