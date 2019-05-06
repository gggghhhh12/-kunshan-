package priv.zx.ecruit.controller;

/**
 * ��ȡ�������ְλ�ľ�����Ϣ�Լ��Ƽ�����ְλservlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.zx.ecruit.dao.CommentDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.model.Comment;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.StuSelectResult;

public class StuJobDetail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//����������ַ���sת�����и�ʽ���ַ�����������ҳ����ʾ
		private StringBuilder getFormatString(String s){
			//���س�����ԭ�ַ����ֳɶ����ַ���
			String[] params = s.split("\r\n");
			//��ÿ���ַ�����ʽ�������ÿ��30��
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i < params.length;i++){
				char[] letters = params[i].toCharArray();
				for(int j = 0;j < letters.length; j++){
					if(letters[j] == ' '){
						sb.append("&nbsp;".toCharArray());
					}else{
						sb.append(letters[j]);
					}
				}
				sb.append("<br>".toCharArray());
			}		
			return sb;
		}
		
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
		request.setCharacterEncoding("utf-8");
		//���url�е�EPusername��Ϣ
		String EPusername = request.getParameter("EPusername");
		String jobName=request.getParameter("jobname");
		String jobName2=jobName;
		jobName=jobName.replace(" ", "+");
		System.out.print(EPusername+" "+jobName);
		//����EPDataDao��EPPostJobDao���������ݿ��������
		EPDataDao epdd = new EPDataDao();
		EPPostJobDao eppjd = new EPPostJobDao();
		EPPostJobDao    eppjd6=new EPPostJobDao();
		//����EPData��EPPostJob����
		EPData epd = new EPData();
		EPPostJob eppj = new EPPostJob();
		//������Ӧ��ҵ�˻�������ҵ��Ϣ
		
		try {
			epd = epdd.getEPData(EPusername);
			eppj = eppjd.getEPPostJob(EPusername,jobName);
			eppjd6.updateHitCount(EPusername, jobName2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//������������ҵ��Ϣ����request
		request.setAttribute("epData", epd);
		request.setAttribute("epPostJob", eppj);
		if(epd != null && eppj != null){
			//����˾�����ַ�����ʽ��������EL���
			String benefits = eppj.getBenefits();
			String[] arrBenefit = benefits.split("\r\n");
			request.setAttribute("benefits", arrBenefit);
			//��ְλ�����ַ�����ʽ��������EL���
			StringBuilder jobdescribe = getFormatString(eppj.getJobdescribe());
			request.setAttribute("describe", jobdescribe);
			//����λְ���ַ�����ʽ��������EL���
			StringBuilder jobduty = getFormatString(eppj.getJobduty());
			request.setAttribute("duty", jobduty);
			//����ְҪ���ַ�����ʽ��������EL���
			StringBuilder techrequest = getFormatString(eppj.getTechrequest());
			request.setAttribute("request", techrequest);
			//����˾��Ϣ�ַ�����ʽ��������EL���
			StringBuilder epInfo = getFormatString(epd.getEPintroduction());
			request.setAttribute("epInfo", epInfo);
		}
		
		//�Ƽ�����ְλ����ʵ��
		String addr = epd.getEPaddr();//��ַ
		String trade = epd.getEPtrade();//��ҵ
		String jobkind = eppj.getJobkind();//ְ�����
		ArrayList<String> arrUsername = new ArrayList<String>();
		try {
			arrUsername = eppjd.queryRecommend(addr, trade, jobkind);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		arrUsername.remove(EPusername);
		//��ȡҪ��ʾ����Ϣ
		if(arrUsername.size() > 0){
			ArrayList<StuSelectResult> arrRecommend = new ArrayList<StuSelectResult>();
			StuSelectResult ssr = null;
			//����EPData��EPPostJob����
			EPData epd1 = new EPData();
			EPPostJob eppj1 = new EPPostJob();
			for (String username : arrUsername) {
				ssr = new StuSelectResult();
				ssr.setEPusername(username);
				try {
					epd1 = epdd.getEPData(username);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ssr.setJobaddr(epd1.getEPaddr());
				ssr.setEPname(epd1.getEPname());
				try {
					System.out.println(username);
					 ArrayList<EPPostJob> tmp=eppjd.getEPPostJob(username);
					 if(tmp.size()==0){
						 continue;
					 }
					eppj1 = tmp.get(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ssr.setJobname(eppj1.getJobname());
				ssr.setSalary(eppj1.getJobsalary());
				arrRecommend.add(ssr);
			}
			request.setAttribute("recommends", arrRecommend);
			request.setAttribute("strRecommends", arrRecommend.toString());
			
		}
		
		//��ʾ�ù�˾���е�����
		CommentDao cd = new CommentDao();
		ArrayList<Comment> arrComm = new ArrayList<Comment>();
		try {
			arrComm = cd.queryComment(EPusername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(arrComm.size() > 0){
			request.setAttribute("comments", arrComm);
		}
		//ת��stuJobDetile.jspҳ��
		
		request.getRequestDispatcher("../stuJobDetail.jsp").forward(request, response);
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
