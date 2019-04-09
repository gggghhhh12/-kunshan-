package priv.zx.ecruit.controller;
//修改密码servlet
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPUserDao;
import priv.zx.ecruit.dao.StuUserDao;
import priv.zx.ecruit.model.EPUser;
import priv.zx.ecruit.model.User;

public class ModifyPasswordServlet extends HttpServlet {
	
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
		//获取路径中的usertype参数
		String usertype = request.getParameter("usertype");
		//获取修改密码表单的数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newPassword");
//		System.out.println(usertype + " " + username + " " + password + " " + newpassword);
		//根据用户的不同进行不同的操作
		if("enterprise".equals(usertype)){
			try {
				//若用户为企业用户
				EPUserDao epud = new EPUserDao();
				//判断用户名是否存在于数据库中
				if(!epud.isExist(username)){
					//若用户名不存在于数据库中，则跳转回原页面
					request.setAttribute("error", "用户名不存在");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
				//判断密码是否是原密码
				if(password.equals(epud.getEPpassword(username))){
					//若密码和原密码一致，则可以进行修改密码操作
					EPUser epu = new EPUser();
					epu.setEPusername(username);
					epu.setEPpassword(newpassword);
					epud.updateEPUser(epu);
				}else{
					//若密码和原密码不一致，则跳转回原页面
					request.setAttribute("error", "密码与原密码不一致");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				//若用户为毕业生用户
				StuUserDao sud = new StuUserDao();
				//判断用户名是否在数据库中
				if(!sud.isExist(username)){
					//若用户名不在数据库中，则跳转回原页面
					request.setAttribute("error", "用户名不存在");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
				//判断密码是否为原密码
				if(password.equals(sud.getPassword(username))){
					//若密码和原密码一致，则继续进行修改密码操作
					User u = new User();
					u.setUsername(username);
					u.setPassword(newpassword);
					sud.updateUser(u);
				}else{
					//若密码和原密码不一致，则跳转会原页面
					request.setAttribute("error", "密码和原密码不一致");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//转向首页
		request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
	}

}
