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

import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.model.JobWanted;

public class StuJobWantedServlet extends HttpServlet {

	/**
	 *  学生发送求职请求
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
		//从前端获取到要求职的公司用户名
		String epUsername = request.getParameter("epUsername");
		String epJobname=request.getParameter("epJobname");
//		System.out.println(epUsername);
		//从session中获取stuUser
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//将求职的公司添加进数据库
		JobWantedDao jwd = new JobWantedDao();
		JobWanted jw = new JobWanted();
		jw.setStuUsername(stuUsername);
		jw.setEpUsername(epUsername);
		jw.setEpJobname(epJobname);
		try {
			if(jwd.isExist(jw)){
				JSONObject json = new JSONObject();
				json.put("info", "您已经向此公司发出过申请");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}else{
				jwd.addJobWanted(jw);
				//将callback转化为json送向前端
				JSONObject json = new JSONObject();
				json.put("info", "成功向此公司发出求职请求");
				PrintWriter pw = response.getWriter();
				pw.print(json.toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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
