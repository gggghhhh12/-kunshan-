package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.JobWantedDao;

/**
 * Servlet implementation class EPChangeMessageStatusServlet
 */
public class EPChangeMessageStatusServlet extends HttpServlet {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuUsername = request.getParameter("stuUsername");
		String epJobname = request.getParameter("epJobname");
		//创建EPData公司资料对象
		JobWantedDao jwd = new JobWantedDao();
		//从session中获取企业用户名
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		try {
			jwd.ChangeMessageStutes(stuUsername, epUsername, epJobname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
