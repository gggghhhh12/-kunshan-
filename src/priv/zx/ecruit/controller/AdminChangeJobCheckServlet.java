package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.AdminCheckDao;

public class AdminChangeJobCheckServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		AdminCheckDao acd = new AdminCheckDao();
		String EPusername = request.getParameter("EPusername");
		String Jobname = request.getParameter("jobname");
		System.out.println(EPusername+" "+Jobname);
		try {
			acd.updateJobCheck(EPusername, Jobname);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher("../adminEPData.jsp").forward(request, response);
		
	
		
		
	}


}
