package priv.zx.ecruit.controller;
/**
 * 毕业生注册servlet
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
		String username = request.getParameter("username");//得到注册表单中的用户名
		StuUserDao ud = new StuUserDao();
		try {
			//判断用户名是否已经被注册
			if(ud.isExist(username)){
				request.setAttribute("error", "用户名已经被注册");
				request.getRequestDispatcher("../stuRegister.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//得到注册表单中的密码和确认密码
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//判断密码和确认密码是否一致
		if(!password.equals(confirmPassword)){
			request.setAttribute("error", "两次密码不一致");
			request.getRequestDispatcher("../stuRegister.jsp").forward(request, response);
			return;
		}
		//将账号添加进数据库
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		try {
			ud.addUser(u);
			request.setAttribute("msg", "注册成功，请等待管理员审核通过！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向mainPage.jsp页面
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
