package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

/**
 * Servlet implementation class EPjobsServlet
 * ��ҵְλ����
 */
@WebServlet("/EPjobsServlet")
public class EPjobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EPjobsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//ȡ��url�е�stuUsername
		String EPusername = request.getParameter("EPusername");
		//��session�л�ȡ��ҵ���û���
		HttpSession session = request.getSession();
		String stuUsername = (String) session.getAttribute("stuUser");
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
		ArrayList<EPPostJob> arrEPStore = null;
		try {
			arrEPStore = eppjd.getEPPostJob(EPusername);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//����arrEpUsername
		for (EPPostJob es : arrEPStore) {
			ssr = new StuSelectResult();
			ssr.setEPusername(es.getEPusername());
			//����epUsername��ѯ��EPData
			try {
				epd = epdd.getEPData(es.getEPusername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ssr.setEPname(epd.getEPname());
			ssr.setJobaddr(es.getJobaddr());
			ssr.setJobname(es.getJobname());	
			ssr.setSalary(es.getJobsalary());
			//��ssr����arrSsr
			arrSsr.add(ssr);
		}
				//��arrSsr����request
		request.setAttribute("epUsernames", arrSsr);	
		//��ѯ�Ƽ���ְλ����
		//����StuStoreDao���ݿ��������
		StuStoreDao ssd = new StuStoreDao();
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
				//����EPDataDao��EPPostJobDao����
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
		request.getRequestDispatcher("../EPjobs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
