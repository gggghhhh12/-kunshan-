package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EPUserDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.dao.StuWantedDao;

public class AdminDelEPUserServlet extends HttpServlet {

	/**
	 * 管理员删除企业用户
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
		//删除该公司相关的记录
		CommentDao cd = new CommentDao();
		JobWantedDao jwd = new JobWantedDao();
		StuWantedDao swd = new StuWantedDao();
		EPStoreDao epsd = new EPStoreDao();
		StuStoreDao ssd = new StuStoreDao();
		EPPostJobDao eppjd = new EPPostJobDao();
		EPDataDao epdd = new EPDataDao();
		EPUserDao epud  = new EPUserDao();
		try {
			cd.delCommentOfEP(epUsername);
			jwd.delJobWantedOfEP(epUsername);
			swd.delStuWantedOfEP(epUsername);
			epsd.delEPStore(epUsername);
			ssd.delStuStoreOfEP(epUsername);
			eppjd.delEPPostJob(epUsername);
			epdd.delEPData(epUsername);
			epud.delEPUser(epUsername);
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

		doGet(request, response);
	}

}
