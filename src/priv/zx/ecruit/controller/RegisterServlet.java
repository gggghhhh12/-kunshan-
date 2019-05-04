package priv.zx.ecruit.controller;
/**
 * 毕业生注册servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.AdminCheckDao;
import priv.zx.ecruit.dao.StuUserDao;
import priv.zx.ecruit.model.AdminPerssion;
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
		Date date=new Date();
		StuUserDao ud = new StuUserDao();
		AdminCheckDao check= new AdminCheckDao();
		ArrayList<AdminPerssion> checkResult = null;
		ArrayList<User> userUnCheck=null;
		try {
			userUnCheck=check.userUnCheck();
			checkResult = check.searchPerssionTime();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
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
		u.setUserDate(date);
		
		//System.out.println(new Date());
		try {
			
			ud.addUser(u);
			//System.out.println(checkResult.get(0).getStartTime());
			//System.out.println(checkResult.get(0).getEndTime());
			
			if(date.compareTo(checkResult.get(0).getStartTime())>0&&date.compareTo(checkResult.get(0).getEndTime())<0)
			{
				check.updateUserCheck(username);
				for (int i = 0; i < userUnCheck.size(); i++) //让以前没有通过审核的全部通过审核
				{
					System.out.println(userUnCheck.get(i).getUsername());
					check.updateUserCheck(userUnCheck.get(i).getUsername());
			}
				
				request.setAttribute("msg", "注册成功，请登录");
			}
			else{
			request.setAttribute("msg", "注册成功，请等待管理员审核通过！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转向mainPage.jsp页面
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
