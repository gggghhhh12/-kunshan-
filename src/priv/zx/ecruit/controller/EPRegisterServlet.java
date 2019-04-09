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
	 *  企业注册
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
		//从企业注册表单中获得企业用户名、密码、确认密码
		String EPname = request.getParameter("EPname");
		String EPpassword = request.getParameter("EPpassword");
		String EPconfirmPassword = request.getParameter("EPconfirmPassword");
//		System.out.println(EPname + " " + EPpassword + " " + EPconfirmPassword);
		//判断用户名是否已经被注册
		EPUserDao epud = new EPUserDao();
		try {
			if(epud.isExist(EPname)){
				request.setAttribute("error", "用户名已经被注册");
				request.getRequestDispatcher("../EPRegister.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//判断两次密码是否一致
		if(!EPpassword.equals(EPconfirmPassword)){
			request.setAttribute("error", "两次密码不一致");
			request.getRequestDispatcher("../EPRegister.jsp").forward(request, response);
			return;
		}
		//将企业账号信息添加进数据库中
		EPUser epu = new EPUser();
		epu.setEPusername(EPname);
		epu.setEPpassword(EPpassword);
		request.setAttribute("msg", "注册成功，请等待管理员审核通过！");
		try {
			epud.addEPUser(epu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向mainPage.jsp页面
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
