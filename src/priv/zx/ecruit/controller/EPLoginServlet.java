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
	 * ��ҵ��¼
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
		//����ҵ��¼���л�ȡ��¼��Ϣ
		String EPname = request.getParameter("EPname");
		String EPpassword = request.getParameter("EPpassword");
		
//		System.out.println(EPname + " " + EPpassword);
		try {
			//��ȡ���ݿ��е�����
			EPUserDao epud = new EPUserDao();
			String DbEPpassword = epud.getEPpassword(EPname);
			boolean ischecked = epud.getIsChecked(EPname);
			//�жϱ�����������ݿ��������Ƿ�һ��
			if(ischecked&&EPpassword.equals(DbEPpassword)){
				//��һ�£�����ҵ�û�������session����ת���¼�ɹ���Ľ���
				HttpSession session = request.getSession();
				String EPlevel = epud.getEplevel(EPname);
				session.setAttribute("EPUser", EPname);
				session.setAttribute("EPlevel", EPlevel);
				session.setAttribute("flag", "login_success");
				request.getRequestDispatcher("EPHomeServlet").forward(request, response);
			}else if(ischecked){
				//����һ�£��򷵻ص�¼ҳ��
				request.setAttribute("error", "��¼ʧ�ܣ������û���������");
				request.getRequestDispatcher("../EPLogin.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error", "��¼ʧ�ܣ��ȴ�����Ա���ͨ��");
				request.getRequestDispatcher("../EPLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
