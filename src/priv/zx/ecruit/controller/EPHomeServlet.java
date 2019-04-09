package priv.zx.ecruit.controller;

/**
 * EPHome.jspҳ����˲��Ƽ�����servlet
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EPDataDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPData;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.TradeAndCount;

public class EPHomeServlet extends HttpServlet {

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

		request.setCharacterEncoding("utf-8");
		//��session�л�ȡ��˾�û���
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//����EPDataDao����
		EPDataDao epdd = new EPDataDao();
		//����EPData����
		EPData epd = new EPData();
		//���ҳ��ù�˾����Ϣ
		try {
			epd = epdd.getEPData(epUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JobIntentionDao jid = new JobIntentionDao();
		if(epd != null){
			//���ҳ����ϸù�˾���˲ţ�������ְ�ص����ҵ��
			ArrayList<String> arrStuUsername = new ArrayList<String>();
			try {
				arrStuUsername = jid.queryRecommend(epd.getEPaddr(), epd.getEPtrade());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//���Ƽ����˵���Ϣת��ΪEPSelectResult����
			if(arrStuUsername.size() > 0){
				ArrayList<EPSelectResult> arrEPSr = new ArrayList<EPSelectResult>();
				EPSelectResult epsr = null;
				//����BasicInfoDao��EducationDao����
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				//����BasicInfo��Education����
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				for (String stuUsername : arrStuUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(stuUsername);
					try {
						bi = bid.getBasicInfo(stuUsername);
						e = ed.getEducation(stuUsername);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(bi != null && e!= null){
						epsr.setStuName(bi.getName());
						epsr.setSex(bi.getSex());
						epsr.setSchool(e.getEduschool());
						epsr.setMajor(e.getEdumajor());
						arrEPSr.add(epsr);
					}
				}
				//�����д���request
				request.setAttribute("recommends", arrEPSr);
				request.setAttribute("strRecommends", arrEPSr.toString());
			}else{
				request.setAttribute("error", "�����Ƽ�");
			}
		}else{
			request.setAttribute("error", "�������ƹ�˾��Ϣ");
		}
		
		//��������ҵ��ְ����
		try {
			int count = jid.getCount();
			if(count > 0){
				ArrayList<TradeAndCount> arrCount = new ArrayList<TradeAndCount>();
				arrCount = jid.queryCount();
				request.setAttribute("counts", arrCount);
				request.setAttribute("sum", count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ת��EPHome.jspҳ��
		request.getRequestDispatcher("../EPHome.jsp").forward(request, response);
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
