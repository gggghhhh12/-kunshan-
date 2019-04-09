package priv.zx.ecruit.controller;

/**
 * 处理修改职位要求的servlet，将修改后的职位要求放入数据库中
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPPostJob;

public class EPHandleModifyPostJobServlet extends HttpServlet {

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

		doPost(request,response);
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

		request.setCharacterEncoding("utf-8");
		//获得表单中修改后的职位信息
		String jobname = request.getParameter("jobname");
		String jobsalary = request.getParameter("jobsalary");
		String jobdiploma = request.getParameter("jobdiploma");
		String engrequest = request.getParameter("engrequest");
		String reqnum = request.getParameter("reqnum");
		String benefits = request.getParameter("benefits");
		String jobdescribe = request.getParameter("jobdescribe");
		String jobduty = request.getParameter("jobduty");
		String techrequest = request.getParameter("techrequest");
		String jobkind = request.getParameter("jobkind");
		String jobaddr = request.getParameter("jobaddr");
		//从session中获得企业用户名
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//创建EPPostJob对象，并将属性添加进对象
		EPPostJob eppj = new EPPostJob();
		eppj.setEPusername(EPusername);
		eppj.setJobname(jobname);
		eppj.setJobsalary(jobsalary);
		eppj.setJobdiploma(jobdiploma);
		eppj.setEngrequest(engrequest);
		eppj.setReqnum(Integer.parseInt(reqnum));
		eppj.setPostdate(new Date());
		eppj.setBenefits(benefits);
		eppj.setJobdescribe(jobdescribe);
		eppj.setJobduty(jobduty);
		eppj.setTechrequest(techrequest);
		eppj.setJobkind(jobkind);
		eppj.setJobaddr(jobaddr);
		//创建EPPostJobDao数据库操作对象,并更新数据项
		try {
			EPPostJobDao eppjd = new EPPostJobDao();
			eppjd.updateEPPostJob(eppj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("msg","职位修改成功！");
		//转向EPHome.jsp页面
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
		
	}

}
