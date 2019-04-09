package priv.zx.ecruit.controller;

/**
 * �����ݿ���ԭְλ��Ϣ��ȡ�����������޸Ĳ�����ִ��
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class BrowseModifyStatusServlet extends HttpServlet {

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

		//����EPPostJobְλ�����б�
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		//����EPPostJobDaoְλҪ�����ݿ��������
		EPPostJobDao eppjd = new EPPostJobDao();
		//��session�л�ȡ��ҵ�û���
		HttpSession session = request.getSession();
		String browseUsername = (String) session.getAttribute("browseUsername");
		//�����ݿ��в��ҳ��ù�˾������ְλҪ��
		try {
				eppjs = eppjd.getEPPostJobAllup(browseUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��ְλҪ��������request��
		request.setAttribute("browseEpDataUps", eppjs);
		//ת��EPModifyPostJob.jspҳ��
		request.getRequestDispatcher("../BrowsePostjobup.jsp").forward(request, response);
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

		doGet(request,response);
	}

}
