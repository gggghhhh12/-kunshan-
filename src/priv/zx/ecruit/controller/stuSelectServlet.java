package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

public class stuSelectServlet extends HttpServlet {

	/**
	 * ѧ�����ݼ�������
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

		request.setCharacterEncoding("utf-8");
		//�õ����е�����
		String address = request.getParameter("address");
		String jobkind = request.getParameter("jobkind");
		String trade = request.getParameter("trade");
//		System.out.println(address + " " + jobkind + " " + trade);
		
		//�������������
		try {
			//������ҵ�û�����ArrayList
			ArrayList<String> usernames = new ArrayList<String>();
			//����EPPostJobDao���ݿ��������
			EPPostJobDao eppjd = new EPPostJobDao();
			//����StuSelectResult���󣬽�Ҫ��ʾ����Ϣ������װ
			ArrayList<StuSelectResult> arrSsr = new ArrayList<StuSelectResult>();
			arrSsr = eppjd.queryEPPostJob(address, jobkind, trade);
			Set<String> set=new HashSet<String>();
			for(StuSelectResult job:arrSsr){
				set.add(job.getEPusername());
			}
			for(String username:set){
				usernames.add(username);
			}
			//��usernames����session��
			HttpSession session = request.getSession();
			session.setAttribute("EPusernames", usernames);
			session.setAttribute("arrSsr", arrSsr);
//			//����EPData��EPPostJob���󣬲�������Ӧ��˾�û�����EPData��EPPostJob
//			ArrayList<EPData> epds = new ArrayList<EPData>();
//			ArrayList<EPPostJob> eppjs = new ArrayList<EPPostJob>();
			if(usernames.size() > 0){
//				for (String username : usernames) {
//					EPData epd = new EPData();
////					EPPostJob eppj = new EPPostJob();
//					//����EPDataDao���ݿ����������в���
//					EPDataDao epdd = new EPDataDao();
//					epd = epdd.getEPData(username);
//					epds.add(epd);
//					//ʹ��EPPostJobDao���ݿ����������в���
//					ArrayList<EPPostJob> eppjlist = eppjd.getEPPostJob(username);
//					for(int i=0;i<eppjlist.size();i++){
//						eppjs.add(eppjlist.get(i));
//					}
//				}
//				
//				StuSelectResult ssr = null;
//				for (int i = 0; i < epds.size(); i++) {
//					ssr = new StuSelectResult();
//					ssr.setEPusername(eppjs.get(i).getEPusername());
//					ssr.setJobname(eppjs.get(i).getJobname());
//					ssr.setEPname(epds.get(i).getEPname());
//					ssr.setJobaddr(epds.get(i).getEPaddr());
//					ssr.setSalary(eppjs.get(i).getJobsalary());
//					arrSsr.add(ssr);
//				}
				//��arrSsr����request��
				request.setAttribute("arrSsr", arrSsr);
			}else{
				request.setAttribute("error", "���޽��");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//ͨ��StuHomeServletת��stuHome.jspҳ��
		request.getRequestDispatcher("StuHomeServlet").forward(request, response);
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
