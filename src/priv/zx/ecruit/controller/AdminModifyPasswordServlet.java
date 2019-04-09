package priv.zx.ecruit.controller;

/**
 * 修改管理员密码servlet
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.AdminUserDao;
import priv.zx.ecruit.model.AdminUser;

public class AdminModifyPasswordServlet extends HttpServlet {

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

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取表单中的内容
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		//判断两次密码是否一致
		if(!newPassword.equals(confirmPassword)){
			request.setAttribute("error", "两次密码不一致");
			request.getRequestDispatcher("../adminUserManage.jsp").forward(request, response);
			return;
		}

		//创建AdminUserDao对象
		AdminUserDao aud = new AdminUserDao();
		try {
			if(password.equals(aud.getPassword(username))){
				AdminUser au = new AdminUser();
				au.setAdminUsername(username);
				au.setAdminPassword(newPassword);
				aud.updateAdminUser(au);
				request.getRequestDispatcher("../adminLogin.jsp").forward(request, response);

				
			}else{
				request.setAttribute("error", "原密码错误");
				request.getRequestDispatcher("../adminUserManage.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
