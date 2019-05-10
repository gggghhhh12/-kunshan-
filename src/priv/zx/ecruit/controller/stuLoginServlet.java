package priv.zx.ecruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.StuUserDao;

public class stuLoginServlet extends HttpServlet {

	/**
	 * 学生登录
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
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");//得到登录的用户名
		String password = request.getParameter("password");//得到登录的密码
		HttpSession session =request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode");
		System.out.print(verificationCode);
		String checkcode = request.getParameter("checkcode");
		PrintWriter out = response.getWriter();
		if(checkcode.isEmpty()==true){
			request.setAttribute("error", "登录失败，验证码错误");
			request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
		}
		else{
		try {
			StuUserDao ud = new StuUserDao();
			Boolean ischecked = ud.getChecked(username);
			
			String DbPassword = ud.getPassword(username);
			if(ischecked&&password != null && password.equals(DbPassword)&&checkcode.equals(verificationCode.toLowerCase())){
				//若登录成功，则将用户名放入session中
				String userlevel = ud.getuserlevel(username);
				session.setAttribute("stuUser", username);
				session.setAttribute("userlevel", userlevel);
				session.setAttribute("flag", "login_success");
				out.println(1);
				//页面转向登录后界面
				request.getRequestDispatcher("MainPageStuHomeServlet").forward(request, response);
				
			}else if(!checkcode.toLowerCase().equals(verificationCode.toLowerCase())&&ischecked&&password != null && password.equals(DbPassword)){
				
				request.setAttribute("error", "登录失败，验证码错误");
				request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
			}
			
			else if(ischecked){
				//若登录失败，则返回登录界面
				request.setAttribute("error", "登录失败，请检查用户名和密码");
				request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "登录失败,请等待管理员审核通过");
				request.getRequestDispatcher("../stuLogin.jsp").forward(request, response);
			}
			out.flush();
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}

}
