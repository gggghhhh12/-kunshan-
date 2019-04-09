package priv.zx.ecruit.controller;

/**
 * ��ѯ�ñ�ҵ���ղع�˾��servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EPPostJobDao;
import priv.zx.ecruit.dao.StuStoreDao;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPPostJob;
import priv.zx.ecruit.model.EPStore;
import priv.zx.ecruit.model.StuSelectResult;

public class StuShowStoreServlet extends HttpServlet {

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

		//������˾�û���list
		ArrayList<EPStore> arrEPStore = new ArrayList<EPStore>();
		//����StuStoreDao���ݿ��������
		StuStoreDao ssd = new StuStoreDao();
		//��session�л�ȡ��ҵ���û���
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
		try {
			//�����ݿ��в�ѯ�˱�ҵ���û����ղصĹ�˾�˻���
			arrEPStore = ssd.queryStuStore(stuUsername);
			session.setAttribute("arrEPStore", arrEPStore);
			//���arrEpUsernameΪ��
			if(arrEPStore.size() == 0){
				//������Ϣ��ǰ��ҳ��
				request.setAttribute("info", "����ʱ���ղصĹ�˾");
			}else{
				//������ѯ�����List
				ArrayList<StuSelectResult> arrSsr = new ArrayList<StuSelectResult>();
				//����StuSelectResult����
				StuSelectResult ssr = null;
				//����EPData����
				EPData epd = new EPData();
				//����EPPostJob����
				EPPostJob eppj = new EPPostJob();
				//����EPDataDao��EPPostJobDao����
				EPDataDao epdd = new EPDataDao();
				EPPostJobDao eppjd = new EPPostJobDao();
				//����arrEpUsername
				for (EPStore es : arrEPStore) {
					ssr = new StuSelectResult();
					ssr.setEPusername(es.getEpUsername());
					//����epUsername��ѯ��EPData
					epd = epdd.getEPData(es.getEpUsername());
					ssr.setEPname(epd.getEPname());
					ssr.setJobname(es.getJobname());
					//����epUsername��ѯ��EPPostJob
					eppj = eppjd.getEPPostJob(es.getEpUsername(),ssr.getJobname());
					ssr.setJobaddr(eppj.getJobaddr());
					ssr.setSalary(eppj.getJobsalary());
					//��ssr����arrSsr
					arrSsr.add(ssr);
				}
				//��arrSsr����request
				request.setAttribute("epUsernames", arrSsr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//��ѯ�Ƽ���ְλ����
		if(arrEPStore.size() > 0){
			//���ñ�ҵ�����ղصĹ�˾
			//��ѯ�ղصĹ�˾�г��ֹ��ĵ�ַ��ְ�����
			ArrayList<String> arrAddr = new ArrayList<String>();
			ArrayList<String> arrJobkind = new ArrayList<String>();
			//��ѯ�Ƽ��Ĺ�˾�û���
			ArrayList<String> arrUsername = new ArrayList<String>();
			try {
				arrAddr = ssd.queryAddr(stuUsername);
				arrJobkind = ssd.queryJobkind(stuUsername);
				arrUsername = ssd.queryRecommend(arrAddr, arrJobkind);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(arrUsername != null){
				ArrayList<StuSelectResult> arrRecommend = new ArrayList<StuSelectResult>();
				StuSelectResult ssr = null;
				//����EPDataDao��EPPostJobDao����
				EPDataDao epdd = new EPDataDao();
				EPPostJobDao eppjd = new EPPostJobDao();
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
						eppj1 = eppjd.getEPPostJob(username).get(0);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					ssr.setJobname(eppj1.getJobname());
					ssr.setSalary(eppj1.getJobsalary());
					arrRecommend.add(ssr);
				}
				request.setAttribute("recommends1", arrRecommend);
				request.setAttribute("strRecommends1", arrRecommend.toString());
			}
		}
		
		//ת��stuShowStore.jspҳ��
		request.getRequestDispatcher("../stuShowStore.jsp").forward(request, response);
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

		
	}

}
