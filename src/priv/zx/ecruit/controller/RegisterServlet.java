package priv.zx.ecruit.controller;
/**
 * ��ҵ��ע��servlet
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.StuUserDao;
import priv.zx.ecruit.model.User;

public class RegisterServlet extends HttpServlet {

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
		String username = request.getParameter("username");//�õ�ע����е��û���
		StuUserDao ud = new StuUserDao();
		try {
			//�ж��û����Ƿ��Ѿ���ע��
			if(ud.isExist(username)){
				request.setAttribute("error", "�û����Ѿ���ע��");
				request.getRequestDispatcher("../stuRegister.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//�õ�ע����е������ȷ������
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//�ж������ȷ�������Ƿ�һ��
		if(!password.equals(confirmPassword)){
			request.setAttribute("error", "�������벻һ��");
			request.getRequestDispatcher("../stuRegister.jsp").forward(request, response);
			return;
		}
		//���˺���ӽ����ݿ�
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		try {
			ud.addUser(u);
			request.setAttribute("msg", "ע��ɹ�����ȴ�����Ա���ͨ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��mainPage.jspҳ��
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
