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
	 * 学生投诉公司
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
		//得到从前端传来的要举报的公司用户名
		String epUsername = request.getParameter("epUsername");
		//得到session中的stuUsername
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
//		System.out.println(stuUsername + " " + epUsername);
		//创建Report对象
		Report r = new Report();
		r.setStuUsername(stuUsername);
		r.setEpUsername(epUsername);
		//创建ReportDao对象
		ReportDao rd = new ReportDao();
		String reportMsg = "";
		try {
			if(rd.isExist(r)){
				reportMsg = "您已经举报过该企业";
			}else{
				rd.addReport(r);
				reportMsg = "举报成功";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将反馈信息转化为json传回前端
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
