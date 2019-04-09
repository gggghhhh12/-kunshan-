package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.dao.StuWantedDao;

public class AdminDelResumeServlet extends HttpServlet {

	/**
	 * ����Աɾ������
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
		//��ȡjson�е�stuUsername
		String stuUsername = request.getParameter("stuUsername");

		//ɾ���ñ�ҵ��������صļ�¼��ɾ������������ְ���������еļ�¼Ҳ��ɾ��
		JobWantedDao jwd = new JobWantedDao();
		StuWantedDao swd = new StuWantedDao();
		EPStoreDao epsd = new EPStoreDao();
		JobIntentionDao jid = new JobIntentionDao();
		EducationDao ed = new EducationDao();
		BasicInfoDao bid = new BasicInfoDao();
		try {
			jwd.delJobWantedOfStu(stuUsername);
			swd.delStuWantedOfStu(stuUsername);
			epsd.delEPStoreOfStu(stuUsername);
			jid.delJobIntention(stuUsername);
			ed.delEducation(stuUsername);
			bid.delBasicInfo(stuUsername);
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
