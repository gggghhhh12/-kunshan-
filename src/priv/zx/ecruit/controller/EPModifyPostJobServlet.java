package priv.zx.ecruit.controller;

/**
 * 将数据库中原职位信息提取到表单，方便修改操作的执行
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class EPModifyPostJobServlet extends HttpServlet {

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

		//创建EPPostJob职位对象列表
		ArrayList<EPPostJob> eppjs=new ArrayList<EPPostJob>();
		//创建EPPostJobDao职位要求数据库操作对象
		EPPostJobDao eppjd = new EPPostJobDao();
		//从session中获取企业用户名
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		String res=request.getParameter("res");
		//从数据库中查找出该公司发布的职位要求
		try {
			if(res.equals("all")){
				eppjs = eppjd.getEPPostJobAll(EPusername);
			}
			else{
				eppjs = eppjd.getEPPostJob(EPusername);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//将职位要求对象放入request中
		request.setAttribute("EPPostJobs", eppjs);
		//转向EPModifyPostJob.jsp页面
		request.getRequestDispatcher("../EPModifyPostJob.jsp").forward(request, response);
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
