package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;

public class AdminLatestEPDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLatestEPDataServlet() {
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
		EPDataDao epdd = new EPDataDao();
		String times=request.getParameter("times");
		if(times==null){
			times="1";
		}
//		System.out.println(times);
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		try {
			arrEPData = epdd.queryLatestEPData(times);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("EPDatas", arrEPData);
		request.setAttribute("times", times);
		request.getRequestDispatcher("../adminEPData.jsp").forward(request, response);
	}

}
