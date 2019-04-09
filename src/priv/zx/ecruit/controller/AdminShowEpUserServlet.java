package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.model.EPData;

public class AdminShowEpUserServlet extends HttpServlet {

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

		//查找出所有注册的公司用户
		EPDataDao epdd = new EPDataDao();
		ArrayList<EPData> arrEPData = new ArrayList<EPData>();
		HashMap<String,String> mapEPlevel=new HashMap<String,String>();
		try {
			arrEPData = epdd.queryAll();
			mapEPlevel=epdd.queryAllEPlevel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("EPDatas", arrEPData);
		request.setAttribute("EPlevels", mapEPlevel);
		//转向adminEpUser.jsp页面
		request.getRequestDispatcher("../adminEpUser.jsp").forward(request, response);
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
