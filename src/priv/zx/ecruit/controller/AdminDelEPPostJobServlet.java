package priv.zx.ecruit.controller;

/**
 * 删除职位 servlet
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.dao.StuWantedDao;

public class AdminDelEPPostJobServlet extends HttpServlet {

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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		//获取json中的epUsername
		String epUsername = request.getParameter("epUsername");
		//删除职位有关的记录
		CommentDao cd = new CommentDao();
		JobWantedDao jwd = new JobWantedDao();
		StuWantedDao swd = new StuWantedDao();
		StuStoreDao ssd = new StuStoreDao();
		EPPostJobDao eppjd = new EPPostJobDao();
		try {
			cd.delCommentOfEP(epUsername);
			jwd.delJobWantedOfEP(epUsername);
			swd.delStuWantedOfEP(epUsername);
			ssd.delStuStoreOfEP(epUsername);
			eppjd.delEPPostJob(epUsername);
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
