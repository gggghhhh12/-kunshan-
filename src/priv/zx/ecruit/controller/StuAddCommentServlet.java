package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.model.Comment;

public class StuAddCommentServlet extends HttpServlet {

	/**
	 *  学生添加对公司的评论
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
		//获取json中的信息
		String stuUsername = request.getParameter("stuUsername");
		String epUsername = request.getParameter("epUsername");
		String relation = request.getParameter("relation");
		String content = request.getParameter("content");
//		System.out.println(stuUsername + " " + epUsername + " " + relation + " " + content );
		Date date = new Date();
		//创建Comment对象
		Comment c = new Comment();
		c.setStuUsername(stuUsername);
		c.setEpUsername(epUsername);
		c.setRelation(relation);
		c.setContent(content);
		c.setDate(date);
		//将comment添加进数据库
		CommentDao cd = new CommentDao();
		try {
			cd.addComment(c);
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
