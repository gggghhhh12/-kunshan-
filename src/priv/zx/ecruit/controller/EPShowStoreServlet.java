package priv.zx.ecruit.controller;

/**
 * ��ʾ��˾�ղص���servlet
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
import priv.zx.ecruit.dao.EPStoreDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.EPSelectResult;
import priv.zx.ecruit.model.Education;

public class EPShowStoreServlet extends HttpServlet {

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

		//�����ղصı�ҵ���û���list
		ArrayList<String> arrStuUsername = new ArrayList<String>();
		//����EPStoreDao���ݿ��������
		EPStoreDao epsd = new EPStoreDao();
		//��session�л�ȡ�ù�˾��EPUser
		HttpSession session = request.getSession();
		String epUsername = (String) session.getAttribute("EPUser");
		//��ѯ���ù�˾�ղصĹ�˾
		try {
			arrStuUsername = epsd.queryEPStore(epUsername);
			session.setAttribute("storeStuUsernames", arrStuUsername);
			//�ж��Ƿ�Ϊ��
			if(arrStuUsername.size() == 0){
				//��Ϊ��
				request.setAttribute("info", "����ʱ���ղصı�ҵ��");
			}else{
				//����EPSelectResult��list
				ArrayList<EPSelectResult> arrEPsr = new ArrayList<EPSelectResult>();
				//����EPSelectResult����
				EPSelectResult epsr = null;
				//����BasicInfo��Education����
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				//����BasicInfoDao��EducationDao����
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				//��ѯ��Ҫ��ʾ����Ϣ
				for (String stuUsername : arrStuUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(stuUsername);
					bi = bid.getBasicInfo(stuUsername);
					epsr.setStuName(bi.getName());
					epsr.setSex(bi.getSex());
					e = ed.getEducation(stuUsername);
					epsr.setSchool(e.getEduschool());
					epsr.setMajor(e.getEdumajor());
					arrEPsr.add(epsr);
				}
				//��arrEPsr����request
				request.setAttribute("stuUsers", arrEPsr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//�Ƽ�����
		//���ù�˾�ղع���ҵ��
		if(arrStuUsername.size() > 0){
			//��ѯ���ղصı�ҵ������ְ�ص����ְ���
			ArrayList<String> arrPlace = new ArrayList<String>();
			ArrayList<String> arrTrade = new ArrayList<String>();
			ArrayList<String> arrUsername = new ArrayList<String>();
			try {
				arrPlace = epsd.queryPlace(epUsername);
				arrTrade = epsd.queryTrade(epUsername);
				//��ѯ��Ҫ�Ƽ��ı�ҵ��
				arrUsername = epsd.queryRecommend(arrPlace, arrTrade);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//����EPSelectResult�����list
			if(arrUsername.size() > 0){
				ArrayList<EPSelectResult> arrRecommend = new ArrayList<EPSelectResult>();
				EPSelectResult epsr = null;
				//����ѧ����Ϣ����
				BasicInfo bi = new BasicInfo();
				Education e = new Education();
				//����ѧ����Ϣ�����ݿ��������
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				for (String username : arrUsername) {
					epsr = new EPSelectResult();
					epsr.setStuUsername(username);
					//��ѯ��BasicInfo
					try {
						bi = bid.getBasicInfo(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					epsr.setStuName(bi.getName());
					epsr.setSex(bi.getSex());
					//��ѯ��Education
					try {
						e = ed.getEducation(username);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					epsr.setSchool(e.getEduschool());
					epsr.setMajor(e.getEdumajor());
					arrRecommend.add(epsr);
				}
				request.setAttribute("recommends1", arrRecommend);
				request.setAttribute("strRecommends1", arrRecommend.toString());
			}
		}
		//ת��epShowStore.jspҳ��
		request.getRequestDispatcher("../epShowStore.jsp").forward(request, response);
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
