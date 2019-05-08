package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.db.gethighSalary;
import priv.zx.ecruit.model.EPPostJob;

public class EPPostJobServlet extends HttpServlet {

	/**
	 * ��ҵ����ְλ
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
		//��ְλ�������л�ȡ������Ϣ
		String jobname = request.getParameter("jobname");
		String jobsalary = request.getParameter("jobsalary");
		String jobsalary2=jobsalary;
		gethighSalary gethighSalary1=new gethighSalary();
		int highSalary=gethighSalary1.getHighJobSalary(jobsalary2);
		String jobdiploma = request.getParameter("jobdiploma");
		String engrequest = request.getParameter("engrequest");
		String reqnum = request.getParameter("reqnum");
		if(reqnum==null||reqnum.endsWith("")||!isnum(reqnum)){
			reqnum="1";
		}
		String benefits = request.getParameter("benefits");
		String jobdescribe = request.getParameter("jobdescribe");
		String jobduty = request.getParameter("jobduty");
		String techrequest = request.getParameter("techrequest");
		String jobkind = request.getParameter("jobkind");
		String jobaddr = request.getParameter("jobaddr");
//		System.out.println(jobname + " " + jobsalary + " " + jobdiploma + " " + engrequest + " " +
//				reqnum + " " + benefits + " " + jobdescribe + " " + jobduty + " " + techrequest + " " +
//				jobkind + " " + jobaddr);
		//��session�л�ȡ��ҵ�˻���
		HttpSession session = request.getSession();
		String EPusername = (String) session.getAttribute("EPUser");
		//����EPPostJob���󣬲���������ӽ�����
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
		eppj.sethighSalary(highSalary);
		//����EPPostJobDao���󣬲���eppj������ӽ����ݿ�
		try {
			EPPostJobDao eppjd = new EPPostJobDao();
			eppjd.addEPPostJob(eppj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��EPHome.jspҳ��
		request.getRequestDispatcher("EPHomeServlet").forward(request, response);
	}
	public boolean isnum(String num){
		char[] chas=num.toCharArray();
		for(int i=0;i<chas.length;i++){
			if(chas[i]>'9'||chas[i]<'0'){
				return false;
			}
		}
		return true;
	}
}
