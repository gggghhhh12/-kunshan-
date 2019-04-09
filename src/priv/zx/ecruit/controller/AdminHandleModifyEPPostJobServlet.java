package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;

public class AdminHandleModifyEPPostJobServlet extends HttpServlet{

	/**
	 *  ��ҵ�޸���Ϣ 
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
		System.out.println("����AdminHandleModifyEPPostJobServlet");
		request.setCharacterEncoding("utf-8");
		//��ñ����޸ĺ��ְλ��Ϣ
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
		//��session�л����ҵ�û���
		
		String EPusername = request.getParameter("EPUsername");
		//����EPPostJob���󣬲���������ӽ�����
		EPPostJob eppj = new EPPostJob();
		eppj.setEPusername(EPusername);
		System.out.println(EPusername);
		eppj.setJobname(jobname);
		System.out.println(jobname);
		eppj.setJobsalary(jobsalary);
		System.out.println(jobsalary);
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
		System.out.println(eppj.toString());
		//����EPPostJobDao���ݿ��������,������������
		try {
			EPPostJobDao eppjd = new EPPostJobDao();
			eppjd.updateEPPostJob(eppj);
			System.out.println("SUCCESS");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��EPHome.jspҳ��
		//request.getRequestDispatcher("EPHomeServlet").forward(request, response);
		request.getRequestDispatcher("AdminShowPostJobServlet").forward(request, response);
		
	}

}
