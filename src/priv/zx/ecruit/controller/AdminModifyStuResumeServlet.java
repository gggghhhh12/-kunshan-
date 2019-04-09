package priv.zx.ecruit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.zx.ecruit.dao.BasicInfoDao;
import priv.zx.ecruit.dao.EducationDao;
import priv.zx.ecruit.dao.JobIntentionDao;
import priv.zx.ecruit.model.BasicInfo;
import priv.zx.ecruit.model.Education;
import priv.zx.ecruit.model.JobIntention;

public class AdminModifyStuResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifyStuResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��������������Ϣ��������Ϣ����ְ�������
				BasicInfo bi = new BasicInfo();
				Education edu = new Education();
				JobIntention ji  = new JobIntention();
				//���������ݿ��������
				BasicInfoDao bid = new BasicInfoDao();
				EducationDao ed = new EducationDao();
				JobIntentionDao jid = new JobIntentionDao();
				//��session�л���û���
				
				String username = request.getParameter("username");
				System.out.println(username);
				//�����ݿ��в��ҳ�������Ϣ
				try {
					bi = bid.getBasicInfo(username);
					edu = ed.getEducation(username);
					ji = jid.getJobIntention(username);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//��������Ϣ����request��
				request.setAttribute("basicInfo", bi);
				request.setAttribute("education", edu);
				request.setAttribute("jobIntention", ji);
				//�����շֽ�Ϊ�ꡢ�¡��մ���request������ҳ��ʹ��
				Calendar c = Calendar.getInstance();
				c.setTime(bi.getBirthday());
				int birYear = c.get(Calendar.YEAR);
				int birMonth = c.get(Calendar.MONTH)+1;
				int birDay = c.get(Calendar.DATE);
				request.setAttribute("birYear", birYear);
				request.setAttribute("birMonth", birMonth);
				request.setAttribute("birDay", birDay);
				//�ѽ�����ʼ���ڷֽ�Ϊ�ꡢ�£�����ҳ��ʹ��
				c.setTime(edu.getEnterTime());
				int entYear = c.get(Calendar.YEAR);
				int entMonth = c.get(Calendar.MONTH)+1;
				c.setTime(edu.getGradTime());
				int gradYear = c.get(Calendar.YEAR);
				int gradMonth = c.get(Calendar.MONTH)+1;
				request.setAttribute("entYear", entYear);
				request.setAttribute("entMonth", entMonth);
				request.setAttribute("gradYear", gradYear);
				request.setAttribute("gradMonth", gradMonth);
				//ҳ��ת��stuResume.jsp
				request.getRequestDispatcher("../AdminModifyStuResume.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
