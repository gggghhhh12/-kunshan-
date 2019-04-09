package priv.zx.ecruit.controller;
/**
 * 查找出向该公司投递简历的毕业生servlet
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

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.JobWantedDao;

public class EPShowInviteServlet extends HttpServlet {

	/**
	 *  企业获取收到邀请的学生
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
		//获取session中的EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//查找出向该公司投递简历的毕业生
		JobWantedDao jwd = new JobWantedDao();
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		try {
			arrStuUsername = jwd.queryInvite(epUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将stuUsername和stuName转化为json
		if(arrStuUsername.size() > 0){
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject member = null;
			for (String stuUsername : arrStuUsername) {
				member = new JSONObject();
				member.put("stuUsername", stuUsername);
				BasicInfoDao bid = new BasicInfoDao();
				try {
					member.put("stuName", bid.getBasicInfo(stuUsername).getName());
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
