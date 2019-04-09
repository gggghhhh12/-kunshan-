package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.StuUserDao;

public class stuLoginServlet extends HttpServlet {

	/**
	 * ѧ����¼
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
		String username = request.getParameter("username");//�õ���¼���û���
		String password = request.getParameter("password");//�õ���¼������
//		System.out.println(username + " " + password);
		try {
			StuUserDao ud = new StuUserDao();
			Boolean ischecked = ud.getChecked(username);
			
			String DbPassword = ud.getPassword(username);
			if(ischecked&&password != null && password.equals(DbPassword)){
				//����¼�ɹ������û�������session��
				HttpSession session = request.getSession();
				String userlevel = ud.getuserlevel(username);
				session.setAttribute("stuUser", username);
				session.setAttribute("userlevel", userlevel);
				session.setAttribute("flag", "login_success");
				
				//ҳ��ת���¼�����
				request.getRequestDispatcher("StuHomeServlet").forward(request, response);
			}else if(ischecked){
				//����¼ʧ�ܣ��򷵻ص�¼����
				request.setAttribute("error", "��¼ʧ�ܣ������û���������");
				request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "��¼ʧ��,��ȴ�����Ա���ͨ��");
				request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
