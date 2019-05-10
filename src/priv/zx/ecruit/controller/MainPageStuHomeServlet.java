package priv.zx.ecruit.controller;

/**
 * ���·�����ְλ�͸���ҵ����ְλ����servlet
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
import priv.zx.ecruit.model.LastestPostJob;
import priv.zx.ecruit.model.TradeAndCount;

public class MainPageStuHomeServlet extends HttpServlet {

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

		//����EPPostJobDao�����EPDateDao����
		EPPostJobDao eppjd = new EPPostJobDao();
		EPDataDao epdd = new EPDataDao();
		//����EPPostJob�����EPDate����
		EPPostJob eppj = new EPPostJob();
		EPData epd = new EPData();
		
		//��ѯ�����·�����ְλ��˾�û���
		ArrayList<String> arrEPusername = new ArrayList<String>();
		try {
			arrEPusername = eppjd.queryLastestPost();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��ѯҪ��ʾ������
		if(arrEPusername.size() > 0){
			ArrayList<LastestPostJob> arrLpj = new ArrayList<LastestPostJob>();
			LastestPostJob lpj = null;
			for (String EPusername : arrEPusername) {
				lpj = new LastestPostJob();
				lpj.setEpUsername(EPusername);
				try {
					epd = epdd.getEPData(EPusername);
					eppj = eppjd.getEPPostJob(EPusername).get(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(epd != null && eppj != null){


					lpj.setEpName(epd.getEPname());
					lpj.setJobName(eppj.getJobname());
					lpj.setPostDate(eppj.getPostdate());
					arrLpj.add(lpj);
				}
			}
			//��arrLpj����request
			System.out.println(arrLpj);
			request.setAttribute("lastestJobs2", arrLpj);
		}
		
		//��ѯ������ҵ�ķ���ְλ����
		try {
			int count = eppjd.getCount();
			if(count > 0){
				ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
				arrCount = eppjd.queryCount();
				request.setAttribute("counts", arrCount);
				request.setAttribute("sum", count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��stuHome.jspҳ��
		request.getRequestDispatcher("../stuHome.jsp").forward(request, response);
		
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
