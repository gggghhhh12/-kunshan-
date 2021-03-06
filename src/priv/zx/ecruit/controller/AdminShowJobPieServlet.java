package priv.zx.ecruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.TradeAndCount;

public class AdminShowJobPieServlet extends HttpServlet {

	/**
	 *  展示所有的职位信息
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
		//查询出各行业职位的发布情况
		EPPostJobDao eppjd = new EPPostJobDao();
		ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
		int sum = 0;
		try {
			arrCount = eppjd.queryCount();
			sum = eppjd.getCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//转化为json格式
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject member = null;
		for (TradeAndCount tac : arrCount) {
			member = new JSONObject();
			member.put("trade", tac.getTrade());
			member.put("rate", (float)tac.getCount()/sum);
			array.add(member);
		}
		json.put("result", array);
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
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
