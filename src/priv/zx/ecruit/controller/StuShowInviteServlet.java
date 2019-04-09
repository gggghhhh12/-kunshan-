package priv.zx.ecruit.controller;

/**
 * 显示面试邀请的servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.StuWantedDao;

public class StuShowInviteServlet extends HttpServlet {

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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
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
		//将arrEPname转换为json
		if(arrEPusername.size() > 0){
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject member = null;
			//创建EPDataDao对象
			EPDataDao epdd = new EPDataDao();
			for (String epUsername : arrEPusername) {
				member = new JSONObject();
				member.put("epUsername", epUsername);
				try {
					member.put("epName", epdd.getEPData(epUsername).getEPname());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				array.add(member);
			}
			json.put("invitations", array);
			PrintWriter pw = response.getWriter();
			pw.print(json.toString());
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
