package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.JobWantedDao;


public class EPChangeToStoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sdhfjgjsdfg");

		String stuUsername = request.getParameter("stuUsername");
		
		JobWantedDao jwd = new JobWantedDao();
		//从session中获取企业用户名
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		try {
			jwd.InsertToStore(epUsername, stuUsername);
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
