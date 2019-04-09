package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.model.Education;

public class EducationServlet extends HttpServlet {

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

		doPost(request,response);
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

		request.setCharacterEncoding("utf-8");
		//从教育经历表单中得到教育信息
		//String eduyear = request.getParameter("eduyear");
		//String edumonth = request.getParameter("edumonth");
		//String eduyear1 = request.getParameter("eduyear1");
		//String edumonth1 = request.getParameter("edumonth1");
		String eduschool = request.getParameter("eduschool");
		String edumajor = request.getParameter("edumajor");
		String edudiploma = request.getParameter("edudiploma");
		String englevel = request.getParameter("englevel");
		String eduduty = request.getParameter("eduduty");
		String eduaward = request.getParameter("eduaward");
		String eduprictise = request.getParameter("eduprictise");
		String abroad = request.getParameter("abroad");
		String enterTime=request.getParameter("entertime");
		//String enterTime = eduyear + "-" + edumonth;
		//String gradTime = eduyear1 + "-" + edumonth1;
		String gradTime = request.getParameter("gradtime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//从session中获取登录名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
		//将教育信息添加到edu对象中
		Education edu = new Education();
		edu.setUsername(username);
		try {
			edu.setEnterTime(sdf.parse(enterTime));
			edu.setGradTime(sdf.parse(gradTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		edu.setEduschool(eduschool);
		edu.setEdumajor(edumajor);
		edu.setEdudiploma(edudiploma);
		edu.setEnglevel(englevel);
		edu.setEduduty(eduduty);
		edu.setEduaward(eduaward);
		edu.setEduprictise(eduprictise);
		edu.setAbroad(abroad);
		//将edu对象添加到数据库中
		EducationDao ed = new EducationDao();
		try {
			//System.out.println(ed.getEducation(username).getUsername());
			if(ed.getEducation(username)!=null)
			{
				ed.updateEducation(edu);
			}
			else{
				ed.addEducation(edu);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//跳转到jobintention.jsp页面
		request.getRequestDispatcher("../jobintention.jsp").forward(request, response);
	}

}
