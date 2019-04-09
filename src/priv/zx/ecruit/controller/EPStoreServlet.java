package priv.zx.ecruit.controller;

/**
 * 将毕业生添加进公司收藏servlet
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.model.EPStore;

public class EPStoreServlet extends HttpServlet {

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
		//得到json中的stuUsername
		String stuUsername = request.getParameter("stuUsername");
//		System.out.println(stuUsername);
		//获得session中的EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//创建EPStore对象
		EPStore eps = new EPStore();
		eps.setEpUsername(epUsername);
		eps.setStuUsername(stuUsername);
		//判断是否已经收藏过
		//将callback转换为json，传回前端
		EPStoreDao epsd = new EPStoreDao();
		JSONObject json = new JSONObject();
		try {
			if(epsd.isExist(eps)){
				json.put("callback", "您已经收藏过此人");
			}else{
				epsd.addEPStore(eps);
				json.put("callback", "成功收藏此人");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
