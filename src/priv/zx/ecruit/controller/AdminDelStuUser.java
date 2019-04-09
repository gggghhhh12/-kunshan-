package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.dao.StuUserDao;
import priv.zx.ecruit.dao.StuWantedDao;

public class AdminDelStuUser extends HttpServlet {

	/**
	 * 管理员删除学生用户
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
		//从json中获取该毕业生的stuUsername
		String stuUsername = request.getParameter("stuUsername");

		//删除数据库中与该毕业生所有有关的记录
		CommentDao cd = new CommentDao();
		JobWantedDao jwd = new JobWantedDao();
		StuWantedDao swd = new StuWantedDao();
		StuStoreDao ssd = new StuStoreDao();
		EPStoreDao epsd  = new EPStoreDao();
		JobIntentionDao jid = new JobIntentionDao();
		EducationDao ed = new EducationDao();
		BasicInfoDao bid = new BasicInfoDao();
		StuUserDao sud = new StuUserDao();
		try {
			cd.delCommentOfStu(stuUsername);
			jwd.delJobWantedOfStu(stuUsername);
			swd.delStuWantedOfStu(stuUsername);
			ssd.delStuStore(stuUsername);
			epsd.delEPStoreOfStu(stuUsername);
			jid.delJobIntention(stuUsername);
			ed.delEducation(stuUsername);
			bid.delBasicInfo(stuUsername);
			sud.delStuUser(stuUsername);
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
