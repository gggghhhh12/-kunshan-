package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.model.EPStore;

public class EPDelStoreServlet extends HttpServlet {

	/**
	 * 企业删除 收藏的Servlet
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

		request.setCharacterEncoding("utf-8");
		//获取json中的stuUsername
		String stuUsername = request.getParameter("stuUsername");
//		System.out.println(stuUsername);
		//获取session中的EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//创建EPStore对象
		EPStore eps = new EPStore();
		eps.setEpUsername(epUsername);
		eps.setStuUsername(stuUsername);
		//删除该收藏记录
		EPStoreDao epsd = new EPStoreDao();
		try {
			epsd.delEPStore(eps);
		} catch (SQLException e) {
			e.printStackTrace();
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
