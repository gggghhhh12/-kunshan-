package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

/**
 * Servlet implementation class AdminLatestJobServlet
 */
public class AdminLatestJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLatestJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String times=request.getParameter("times");
		if(times==null){
			times="1";
		}
		EPPostJobDao eppjd = new EPPostJobDao();
		ArrayList<EPPostJob> arrEPPostJob = new ArrayList<EPPostJob>();
		try {
			arrEPPostJob = eppjd.queryLatestAll(times);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("EPPostjobs", arrEPPostJob);
		request.setAttribute("times", times);
		//×ªÏòadminEPPostJob.jsp
		request.getRequestDispatcher("../adminEPPostJob.jsp").forward(request, response);
	}

}
