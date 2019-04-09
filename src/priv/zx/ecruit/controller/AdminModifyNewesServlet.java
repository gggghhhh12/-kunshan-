package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.NewsDao;
import priv.zx.ecruit.model.News;

public class AdminModifyNewesServlet extends HttpServlet {

	/**
	 * 管理员修改新闻
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
		//获取json中的newsId,newsTitle,newsContent
		String newsId = request.getParameter("newsId");
		String newsTitle = request.getParameter("newsTitle");
		String newsContent = request.getParameter("newsContent");
//		System.out.println(newsId + " " + newsTitle + " " + newsContent);
		//获取当前的时间
		Date newsTime = new Date();
		//修改数据库中此条新闻的标题和内容
		NewsDao nd = new NewsDao();
		News n = new News();
		n.setNews_id(newsId);
		n.setNews_title(newsTitle);
		n.setNews_content(newsContent);
		n.setNews_time(newsTime);
		try {
			nd.modifyNews(n);
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

		doPost(request,response);
	}

}
