package priv.zx.ecruit.controller;
/**
 * ����Ա������ٱ���Ϣ��ɾ��
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.ReportDao;
import priv.zx.ecruit.model.Report;

public class AdminDelReportServlet extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");
		//��json  �л�ȡ����ǰ��ѧ��������ҵ
		String stuUsername = request.getParameter("stuUsername");
		String epUsername = request.getParameter("epUsername");

		//����Report����
		Report r = new Report();
		r.setStuUsername(stuUsername);
		r.setEpUsername(epUsername);
		//����ReportDao����
		ReportDao rd = new ReportDao();
		// �����ݿ��н���ɾ��
		try {
			rd.delReport(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
