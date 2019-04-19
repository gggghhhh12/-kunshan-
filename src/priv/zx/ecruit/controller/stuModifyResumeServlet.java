package priv.zx.ecruit.controller;

/**
 * ѧ���޸ļ�����Ϣ
 * �����ݿ���ԭ�ȵļ�����Ϣ��д������servlet�������޸�
 */
import java.io.IOException;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
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

public class stuModifyResumeServlet extends HttpServlet {

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

		//��������������Ϣ��������Ϣ����ְ�������
		BasicInfo bi = new BasicInfo();
		Education edu = new Education();
		JobIntention ji  = new JobIntention();
		//���������ݿ��������
		BasicInfoDao bid = new BasicInfoDao();
		EducationDao ed = new EducationDao();
		JobIntentionDao jid = new JobIntentionDao();
		//��session�л���û���
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("stuUser");
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
		Date birthday =new Date();
		Date entertime =new Date();
		Date gradtime=new Date();
		String birthday2,entertime2,gradtime2;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			birthday= new Date(bi.getBirthday().getTime());
			entertime=new Date( edu.getEnterTime().getTime());
			gradtime=new Date(edu.getGradTime().getTime());
		} catch (NullPointerException l) {
			birthday2=df.format(birthday);
			entertime2=df.format(entertime);
			gradtime2=df.format(gradtime);
			try {
				birthday=df.parse(birthday2);
				entertime=df.parse(entertime2);
				gradtime=df.parse(gradtime2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l.printStackTrace();
		}
		
		//int birYear = c.get(Calendar.YEAR);
		//int birMonth = c.get(Calendar.MONTH)+1;
		//int birDay = c.get(Calendar.DATE);
		//request.setAttribute("birYear", birYear);
		//request.setAttribute("birMonth", birMonth);
		//request.setAttribute("birDay", birDay);
		//�������ڣ�������ʼ����ʱ��
		request.setAttribute("birthday", birthday);
		request.setAttribute("entertime", entertime);
		request.setAttribute("gradtime", gradtime);
		//�ѽ�����ʼ���ڷֽ�Ϊ�ꡢ�£�����ҳ��ʹ��
	
		//c.setTime(edu.getEnterTime());
		//int entYear = c.get(Calendar.YEAR);
		//int entMonth = c.get(Calendar.MONTH)+1;
		//c.setTime(edu.getGradTime());
		//int gradYear = c.get(Calendar.YEAR);
		//int gradMonth = c.get(Calendar.MONTH)+1;
		
		//request.setAttribute("entYear", entYear);
		//request.setAttribute("entMonth", entMonth);
		//request.setAttribute("gradYear", gradYear);
		//request.setAttribute("gradMonth", gradMonth);
		//ҳ��ת��stuResume.jsp
		request.getRequestDispatcher("../stuModifyResume.jsp").forward(request, response);
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
