package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.model.AdminResume;

/**
 * Servlet implementation class AdminCheckResumeServlet
 */
@WebServlet("/AdminCheckResumeServlet")
public class AdminCheckResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCheckResumeServlet() {
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
		
		//查找出所有用户的简历
		BasicInfoDao bid = new BasicInfoDao();
		ArrayList<AdminResume> arrResume = new ArrayList<AdminResume>();
		try {
			arrResume = bid.queryUncheckResumes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将简历存入request
		request.setAttribute("resumes", arrResume);
		request.setAttribute("uncheck", "uncheck");
		//转向adminResumeManage.jsp页面
		request.getRequestDispatcher("../adminResumeManage.jsp").forward(request, response);
	}

}
