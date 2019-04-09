package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.AdminUserDao;

public class AdminLoginServlet extends HttpServlet {

	/**
	 * 管理员登录
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
		//获取表单中的用户名和密码
		String adminUsername = request.getParameter("adminname");
		String adminPassword = request.getParameter("adminpassword");
		//获取数据库中的密码
		AdminUserDao aud = new AdminUserDao();
		String DbPassword = "";
		try {
			DbPassword = aud.getPassword(adminUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(DbPassword.equals(adminPassword)){
			//将用户名存入session
			HttpSession session = request.getSession();
			session.setAttribute("adminUser", adminUsername);
			session.setAttribute("flag", "login_success");
			request.getRequestDispatcher("../adminHome.jsp").forward(request, response);
		}else{
			request.setAttribute("error", "登录失败,请检查用户名和密码");
			request.getRequestDispatcher("../adminLogin.jsp").forward(request, response);
		}
	}

}
