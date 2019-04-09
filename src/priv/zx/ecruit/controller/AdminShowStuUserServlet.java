package priv.zx.ecruit.controller;

/**
 * 查找出所有的用户servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.model.BasicInfo;

public class AdminShowStuUserServlet extends HttpServlet {

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

		//查找出所有的用户
		BasicInfoDao bid = new BasicInfoDao();
		HashMap<String,String> mapUserlevel=new HashMap<String,String>();
		ArrayList<BasicInfo> arrBasicInfo = new ArrayList<BasicInfo>();
		try {
			arrBasicInfo = bid.queryAll();
			mapUserlevel=bid.queryAllUserlevel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("basicInfos", arrBasicInfo);
		request.setAttribute("userlevels", mapUserlevel);
		//转到adminStuUser.jsp页面
		request.getRequestDispatcher("../adminStuUser.jsp").forward(request, response);
		
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
