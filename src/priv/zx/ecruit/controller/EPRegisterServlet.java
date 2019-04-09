package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPUserDao;
import priv.zx.ecruit.model.EPUser;

public class EPRegisterServlet extends HttpServlet {

	/**
	 *  ��ҵע��
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

		doPost(request,response);
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
		request.setCharacterEncoding("utf-8");
		//����ҵע����л����ҵ�û��������롢ȷ������
		String EPname = request.getParameter("EPname");
		String EPpassword = request.getParameter("EPpassword");
		String EPconfirmPassword = request.getParameter("EPconfirmPassword");
//		System.out.println(EPname + " " + EPpassword + " " + EPconfirmPassword);
		//�ж��û����Ƿ��Ѿ���ע��
		EPUserDao epud = new EPUserDao();
		try {
			if(epud.isExist(EPname)){
				request.setAttribute("error", "�û����Ѿ���ע��");
				request.getRequestDispatcher("../EPRegister.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�ж����������Ƿ�һ��
		if(!EPpassword.equals(EPconfirmPassword)){
			request.setAttribute("error", "�������벻һ��");
			request.getRequestDispatcher("../EPRegister.jsp").forward(request, response);
			return;
		}
		//����ҵ�˺���Ϣ��ӽ����ݿ���
		EPUser epu = new EPUser();
		epu.setEPusername(EPname);
		epu.setEPpassword(EPpassword);
		request.setAttribute("msg", "ע��ɹ�����ȴ�����Ա���ͨ����");
		try {
			epud.addEPUser(epu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��mainPage.jspҳ��
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
