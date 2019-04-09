package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.JobIntention;

public class JobIntentionServlet extends HttpServlet {

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
		//获得求职意向表单中的信息
		String keyword = request.getParameter("keyword");
		String evaluation = request.getParameter("evaluation");
		String place = request.getParameter("place");
		String trade = request.getParameter("trade");
		String salary = request.getParameter("salary");
//		System.out.println(keyword + " " + evaluation + " " + place + " " + trade + " " + salary);
		//从session中获得用户名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
		//创建求职意向对象，并赋予属性
		JobIntention ji= new JobIntention();
		ji.setUsername(username);
		ji.setKeyword(keyword);
		ji.setEvaluation(evaluation);
		ji.setPlace(place);
		ji.setTrade(trade);
		ji.setSalary(salary);
		ji.setDate(new Date());
		//将此用户的求职意向添加进求职意向表中
		JobIntentionDao jid = new JobIntentionDao();
		try {
			if(jid.getJobIntention(username)!=null){
				jid.updateJobIntention(ji);
			}
			else{
				jid.addJobIntention(ji);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转到主页stuHome.jsp
		request.getRequestDispatcher("StuHomeServlet").forward(request, response);
	}

}
