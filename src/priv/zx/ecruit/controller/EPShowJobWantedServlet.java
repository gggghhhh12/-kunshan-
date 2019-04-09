package priv.zx.ecruit.controller;
/**
 * 查询出向该公司投递简历的毕业生
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.JobWantedDao;
import priv.zx.ecruit.model.BasicInfo;

public class EPShowJobWantedServlet extends HttpServlet {

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

		//获取session中的EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//查找出向该公司投递简历的毕业生
		JobWantedDao jwd = new JobWantedDao();
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		ArrayList<String> Stored = new ArrayList<String>();
		ArrayList<String> usernames = new ArrayList<String>();
		EPStoreDao esd = new EPStoreDao();
		try {
			Stored  = esd.queryEPStore(epUsername);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!Stored.isEmpty())
		{
			request.setAttribute("Stored", Stored);
		}		
		
		try {
			arrStuUsername = jwd.queryInvite1(epUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//查询出该毕业生的信息
		if(arrStuUsername.size() > 0){
			BasicInfoDao bid = new BasicInfoDao();
			ArrayList<BasicInfo> arrBid = new ArrayList<BasicInfo>();
			ArrayList<String> arrJobName = new ArrayList<String>();
			ArrayList<String> arrJobflag = new ArrayList<String>();
			BasicInfo bi = null;
			for (String username1 : arrStuUsername) {
				String username = username1.split("_")[0];
				usernames.add(username);
				arrJobName.add(username1.split("_")[1]);
				arrJobflag.add(username1.split("_")[2]);
				bi = new BasicInfo();
				try {
					bi = bid.getBasicInfo(username);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				arrBid.add(bi);
			}
			//将arrBid存入request
			request.setAttribute("stus", arrBid);
			request.setAttribute("Jobs", arrJobName);
			request.setAttribute("flags", arrJobflag);
			request.setAttribute("unames", usernames);
		}
		request.getRequestDispatcher("../epShowJobWanted.jsp").forward(request, response);
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
