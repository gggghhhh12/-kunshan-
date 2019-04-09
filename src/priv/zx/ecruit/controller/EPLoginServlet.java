package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPUserDao;

public class EPLoginServlet extends HttpServlet {

	/**
	 * 企业登录
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
		//从企业登录表单中获取登录信息
		String EPname = request.getParameter("EPname");
		String EPpassword = request.getParameter("EPpassword");
		
//		System.out.println(EPname + " " + EPpassword);
		try {
			//获取数据库中的密码
			EPUserDao epud = new EPUserDao();
			String DbEPpassword = epud.getEPpassword(EPname);
			boolean ischecked = epud.getIsChecked(EPname);
			//判断表单中密码和数据库中密码是否一致
			if(ischecked&&EPpassword.equals(DbEPpassword)){
				//若一致，将企业用户名存入session，并转向登录成功后的界面
				HttpSession session = request.getSession();
				String EPlevel = epud.getEplevel(EPname);
				session.setAttribute("EPUser", EPname);
				session.setAttribute("EPlevel", EPlevel);
				session.setAttribute("flag", "login_success");
				request.getRequestDispatcher("EPHomeServlet").forward(request, response);
			}else if(ischecked){
				//若不一致，则返回登录页面
				request.setAttribute("error", "登录失败，请检查用户名和密码");
				request.getRequestDispatcher("../EPLogin.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error", "登录失败，等待管理员审核通过");
				request.getRequestDispatcher("../EPLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
