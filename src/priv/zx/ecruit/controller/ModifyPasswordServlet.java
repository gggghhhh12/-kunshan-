package priv.zx.ecruit.controller;
//�޸�����servlet
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
		//��ȡ·���е�usertype����
		String usertype = request.getParameter("usertype");
		//��ȡ�޸������������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newPassword");
//		System.out.println(usertype + " " + username + " " + password + " " + newpassword);
		//�����û��Ĳ�ͬ���в�ͬ�Ĳ���
		if("enterprise".equals(usertype)){
			try {
				//���û�Ϊ��ҵ�û�
				EPUserDao epud = new EPUserDao();
				//�ж��û����Ƿ���������ݿ���
				if(!epud.isExist(username)){
					//���û��������������ݿ��У�����ת��ԭҳ��
					request.setAttribute("error", "�û���������");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
				//�ж������Ƿ���ԭ����
				if(password.equals(epud.getEPpassword(username))){
					//�������ԭ����һ�£�����Խ����޸��������
					EPUser epu = new EPUser();
					epu.setEPusername(username);
					epu.setEPpassword(newpassword);
					epud.updateEPUser(epu);
				}else{
					//�������ԭ���벻һ�£�����ת��ԭҳ��
					request.setAttribute("error", "������ԭ���벻һ��");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				//���û�Ϊ��ҵ���û�
				StuUserDao sud = new StuUserDao();
				//�ж��û����Ƿ������ݿ���
				if(!sud.isExist(username)){
					//���û����������ݿ��У�����ת��ԭҳ��
					request.setAttribute("error", "�û���������");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
				//�ж������Ƿ�Ϊԭ����
				if(password.equals(sud.getPassword(username))){
					//�������ԭ����һ�£�����������޸��������
					User u = new User();
					u.setUsername(username);
					u.setPassword(newpassword);
					sud.updateUser(u);
				}else{
					//�������ԭ���벻һ�£�����ת��ԭҳ��
					request.setAttribute("error", "�����ԭ���벻һ��");
					request.getRequestDispatcher("../modifyPassword.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//ת����ҳ
		request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
	}

}
