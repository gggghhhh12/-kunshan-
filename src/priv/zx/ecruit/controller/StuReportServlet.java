package priv.zx.ecruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.ReportDao;
import priv.zx.ecruit.model.Report;

public class StuReportServlet extends HttpServlet {

	/**
	 * ѧ��Ͷ�߹�˾
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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//�õ���ǰ�˴�����Ҫ�ٱ��Ĺ�˾�û���
		String epUsername = request.getParameter("epUsername");
		//�õ�session�е�stuUsername
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
//		System.out.println(stuUsername + " " + epUsername);
		//����Report����
		Report r = new Report();
		r.setStuUsername(stuUsername);
		r.setEpUsername(epUsername);
		//����ReportDao����
		ReportDao rd = new ReportDao();
		String reportMsg = "";
		try {
			if(rd.isExist(r)){
				reportMsg = "���Ѿ��ٱ�������ҵ";
			}else{
				rd.addReport(r);
				reportMsg = "�ٱ��ɹ�";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��������Ϣת��Ϊjson����ǰ��
		JSONObject json = new JSONObject();
		json.put("reportMsg", reportMsg);
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
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
