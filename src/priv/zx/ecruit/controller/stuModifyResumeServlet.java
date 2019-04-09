package priv.zx.ecruit.controller;

/**
 * 学生修改简历信息
 * 将数据库中原先的简历信息填写到表单中servlet，方便修改
 */
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;

public class stuModifyResumeServlet extends HttpServlet {

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

		//创建简历基本信息、教育信息、求职意向对象
		BasicInfo bi = new BasicInfo();
		Education edu = new Education();
		JobIntention ji  = new JobIntention();
		//创建各数据库操作对象
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//从session中获得用户名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
		//从数据库中查找出简历信息
		try {
			bi = bid.getBasicInfo(username);
			edu = ed.getEducation(username);
			ji = jid.getJobIntention(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将简历信息放入request中
		request.setAttribute("basicInfo", bi);
		request.setAttribute("education", edu);
		request.setAttribute("jobIntention", ji);
		//把生日分解为年、月、日存入request，方便页面使用
		
		Date birthday= new Date(bi.getBirthday().getTime());
		Date entertime=new Date( edu.getEnterTime().getTime());
		Date gradtime=new Date(edu.getGradTime().getTime());
		//int birYear = c.get(Calendar.YEAR);
		//int birMonth = c.get(Calendar.MONTH)+1;
		//int birDay = c.get(Calendar.DATE);
		//request.setAttribute("birYear", birYear);
		//request.setAttribute("birMonth", birMonth);
		//request.setAttribute("birDay", birDay);
		//出生日期，教育开始结束时间
		request.setAttribute("birthday", birthday);
		request.setAttribute("entertime", entertime);
		request.setAttribute("gradtime", gradtime);
		//把教育起始日期分解为年、月，方便页面使用
	
		//c.setTime(edu.getEnterTime());
		//int entYear = c.get(Calendar.YEAR);
		//int entMonth = c.get(Calendar.MONTH)+1;
		//c.setTime(edu.getGradTime());
		//int gradYear = c.get(Calendar.YEAR);
		//int gradMonth = c.get(Calendar.MONTH)+1;
		
		//request.setAttribute("entYear", entYear);
		//request.setAttribute("entMonth", entMonth);
		//request.setAttribute("gradYear", gradYear);
		//request.setAttribute("gradMonth", gradMonth);
		//页面转到stuResume.jsp
		request.getRequestDispatcher("../stuModifyResume.jsp").forward(request, response);
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
