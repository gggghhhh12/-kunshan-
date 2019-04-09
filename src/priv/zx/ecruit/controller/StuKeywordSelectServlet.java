package priv.zx.ecruit.controller;

/**
 * ��ҵ��ͨ���ؼ���������˾
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

public class StuKeywordSelectServlet extends HttpServlet {

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
		//��ȡ���е�keyword
		String keyword = request.getParameter("keyword");
//		System.out.println(keyword);
		//����EPDataDao���󣬲�ѯ���д˹ؼ��ֵĹ�˾
		
		EPDataDao epdd = new EPDataDao();
		ArrayList<String> arrEPusername = new ArrayList<String>();
		try {
			arrEPusername = epdd.queryEPusesnames(keyword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//����˾��Ϣ��װ��StuSelectResult����
		if(arrEPusername.size() > 0){
			EPPostJobDao eppjd = new EPPostJobDao();
			EPData epd = new EPData();
			ArrayList<EPPostJob> eppjs = new ArrayList<EPPostJob>();
			ArrayList<StuSelectResult> arrSsr = new ArrayList<StuSelectResult>();
			StuSelectResult ssr = null;
			for (String epUsername : arrEPusername) {
				try {
					epd = epdd.getEPData(epUsername);
					eppjs = eppjd.getEPPostJob(epUsername);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for(int i=0;i<eppjs.size();i++){
					if(epd !=null && eppjs.get(i) != null){
						ssr = new StuSelectResult();
						ssr.setEPusername(epUsername);
						ssr.setEPname(epd.getEPname());
						ssr.setJobaddr(epd.getEPaddr());
						ssr.setJobname(eppjs.get(i).getJobname());
						ssr.setSalary(eppjs.get(i).getJobsalary());
						arrSsr.add(ssr);
				}
				}
			}
			request.setAttribute("arrSsr", arrSsr);
		}else{
			request.setAttribute("error", "���޲�ѯ���");
		}
		//ͨ��StuHomeServletת��stuHome.jspҳ��
		request.getRequestDispatcher("StuHomeServlet").forward(request, response);
	}

}
