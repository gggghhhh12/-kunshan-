package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

public class AdminModifyStuResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifyStuResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//创建简历基本信息、教育信息、求职意向对象
				BasicInfo bi = new BasicInfo();
				Education edu = new Education();
				JobIntention ji  = new JobIntention();
				//创建各数据库操作对象
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				JobIntentionDao jid = new JobIntentionDao();
				//从session中获得用户名
				
				String username = request.getParameter("username");
				System.out.println(username);
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
				Calendar c = Calendar.getInstance();
				c.setTime(bi.getBirthday());
				int birYear = c.get(Calendar.YEAR);
				int birMonth = c.get(Calendar.MONTH)+1;
				int birDay = c.get(Calendar.DATE);
				request.setAttribute("birYear", birYear);
				request.setAttribute("birMonth", birMonth);
				request.setAttribute("birDay", birDay);
				//把教育起始日期分解为年、月，方便页面使用
				c.setTime(edu.getEnterTime());
				int entYear = c.get(Calendar.YEAR);
				int entMonth = c.get(Calendar.MONTH)+1;
				c.setTime(edu.getGradTime());
				int gradYear = c.get(Calendar.YEAR);
				int gradMonth = c.get(Calendar.MONTH)+1;
				request.setAttribute("entYear", entYear);
				request.setAttribute("entMonth", entMonth);
				request.setAttribute("gradYear", gradYear);
				request.setAttribute("gradMonth", gradMonth);
				//页面转到stuResume.jsp
				request.getRequestDispatcher("../AdminModifyStuResume.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
