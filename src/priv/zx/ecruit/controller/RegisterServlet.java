package priv.zx.ecruit.controller;
/**
 * ��ҵ��ע��servlet
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
		String username = request.getParameter("username");//�õ�ע����е��û���
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
		u.setUserDate(date);
		
		//System.out.println(new Date());
		try {
			
			ud.addUser(u);
			//System.out.println(checkResult.get(0).getStartTime());
			//System.out.println(checkResult.get(0).getEndTime());
			
			if(date.compareTo(checkResult.get(0).getStartTime())>0&&date.compareTo(checkResult.get(0).getEndTime())<0)
			{
				check.updateUserCheck(username);
				for (int i = 0; i < userUnCheck.size(); i++) //����ǰû��ͨ����˵�ȫ��ͨ�����
				{
					System.out.println(userUnCheck.get(i).getUsername());
					check.updateUserCheck(userUnCheck.get(i).getUsername());
			}
				
				request.setAttribute("msg", "ע��ɹ������¼");
			}
			else{
			request.setAttribute("msg", "ע��ɹ�����ȴ�����Ա���ͨ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��mainPage.jspҳ��
		request.getRequestDispatcher("MainPageServlet").forward(request, response);
	}

}
