package priv.zx.ecruit.controller;
/**
 * 查询出所有的职位信息servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class AdminShowPostJobServlet extends HttpServlet {

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

		//查询出所有的职位信息
		EPPostJobDao eppjd = new EPPostJobDao();
		ArrayList<EPPostJob> arrEPPostJob = new ArrayList<EPPostJob>();
		try {
			arrEPPostJob = eppjd.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("EPPostjobs", arrEPPostJob);
		//转向adminEPPostJob.jsp
		request.getRequestDispatcher("../adminEPPostJob.jsp").forward(request, response);
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
