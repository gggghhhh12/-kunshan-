package priv.zx.ecruit.controller;

/**
 * 查找出向该毕业生发出邀请的公司servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.StuWantedDao;
import priv.zx.ecruit.model.EPData;

public class StuShowStuWantedServlet extends HttpServlet {

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

		//获取session中的stuUser
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		//查找出发出邀请的公司用户名
		ArrayList<String> arrEPusername = new ArrayList<String>();
		StuWantedDao swd = new StuWantedDao();
		try {
			arrEPusername = swd.queryInvite(stuUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//查找出公司的信息
		if(arrEPusername.size() > 0){
			ArrayList<EPData> arrEPData = new ArrayList<EPData>();
			EPData epd = null;
			EPDataDao epdd = new EPDataDao();
			for (String epUsername : arrEPusername) {
				epd = new EPData();
				try {
					epd = epdd.getEPData(epUsername);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				arrEPData.add(epd);
			}
			request.setAttribute("eps", arrEPData);
		}
		//转向stuShowStuWanted.jsp页面
		request.getRequestDispatcher("../stuShowStuWanted.jsp").forward(request, response);
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
