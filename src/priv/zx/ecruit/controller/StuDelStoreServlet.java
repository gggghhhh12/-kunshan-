package priv.zx.ecruit.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.model.StuStore;

public class StuDelStoreServlet extends HttpServlet {

	/**
	 *  学生删除收藏的公司
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
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获得url中的EPusername
		System.out.println("kdhfgfjgjsdfghk");
		String EPusername = request.getParameter("EPusername");
		EPusername=URLDecoder.decode(EPusername,"UTF-8");
//		EPusername=new String(EPusername.getBytes("ISO-88591-1"));
		System.out.println(EPusername);
		String jobname=EPusername.split("___")[1];
		EPusername=EPusername.split("___")[0];
		System.out.println(EPusername+" "+jobname);
		HttpSession session = request.getSession();
		//取出session中的stuUser
		String stuUsername = (String) session.getAttribute("stuUser");
		//从数据库表中删除此记录
		StuStoreDao ssd = new StuStoreDao();
		StuStore ss = new StuStore();
		ss.setStuUsername(stuUsername);
		ss.setEpUsername(EPusername);
		ss.setJobname(jobname);
		try {
			ssd.delStuStore(ss);
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
