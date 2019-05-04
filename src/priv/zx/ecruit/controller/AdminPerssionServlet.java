package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.AdminCheckDao;
import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.model.AdminPerssion;
import priv.zx.ecruit.model.BasicInfo;

/**
 * Servlet implementation class AdminCheckStuServlet
 */
@WebServlet("/AdminPerssionServlet")
public class AdminPerssionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPerssionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		AdminCheckDao perss=new AdminCheckDao();
		String startTime =request.getParameter("start_time");
		String endTime=request.getParameter("end_time");
		AdminPerssion perssion=new AdminPerssion();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			perssion.setEndTime(sdf.parse(endTime));
			perssion.setStartTime(sdf.parse(startTime));
			perssion.setdaytime(new Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			perss.addPerssionTime(perssion);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//×ªµ½adminStuUser.jspÒ³Ãæ
		request.getRequestDispatcher("../adminStuUser.jsp").forward(request, response);
	}

}
